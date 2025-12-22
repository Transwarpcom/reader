package io.vertx.core.file.impl;

import cn.hutool.core.io.FileUtil;
import io.vertx.core.VertxException;
import io.vertx.core.file.FileSystemOptions;
import io.vertx.core.net.impl.URIDecoder;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/file/impl/FileResolver.class */
public class FileResolver {
    public static final String DISABLE_FILE_CACHING_PROP_NAME = "vertx.disableFileCaching";
    public static final String DISABLE_CP_RESOLVING_PROP_NAME = "vertx.disableFileCPResolving";
    public static final String CACHE_DIR_BASE_PROP_NAME = "vertx.cacheDirBase";
    private static final String FILE_SEP = System.getProperty("file.separator");
    private static final boolean NON_UNIX_FILE_SEP;
    private static final String JAR_URL_SEP = "!/";
    private static final Pattern JAR_URL_SEP_PATTERN;
    private final File cwd;
    private File cacheDir;
    private Thread shutdownHook;
    private final boolean enableCaching;
    private final boolean enableCpResolving;
    private final String fileCacheDir;

    static {
        NON_UNIX_FILE_SEP = !FILE_SEP.equals("/");
        JAR_URL_SEP_PATTERN = Pattern.compile("!/");
    }

    public FileResolver() {
        this(new FileSystemOptions());
    }

    public FileResolver(boolean enableCaching) {
        this(new FileSystemOptions().setFileCachingEnabled(enableCaching));
    }

    public FileResolver(FileSystemOptions fileSystemOptions) {
        this.enableCaching = fileSystemOptions.isFileCachingEnabled();
        this.enableCpResolving = fileSystemOptions.isClassPathResolvingEnabled();
        this.fileCacheDir = fileSystemOptions.getFileCacheDir();
        String cwdOverride = System.getProperty("vertx.cwd");
        if (cwdOverride != null) {
            this.cwd = new File(cwdOverride).getAbsoluteFile();
        } else {
            this.cwd = null;
        }
        if (this.enableCpResolving) {
            setupCacheDir();
        }
    }

    public void close() throws IOException {
        synchronized (this) {
            if (this.shutdownHook != null) {
                try {
                    Runtime.getRuntime().removeShutdownHook(this.shutdownHook);
                } catch (IllegalStateException e) {
                }
            }
        }
        deleteCacheDir();
    }

    public File resolveFile(String fileName) {
        URL directoryContents;
        File file = new File(fileName);
        if (this.cwd != null && !file.isAbsolute()) {
            file = new File(this.cwd, fileName);
        }
        if (!this.enableCpResolving) {
            return file;
        }
        synchronized (this) {
            if (!file.exists()) {
                File cacheFile = new File(this.cacheDir, fileName);
                if (this.enableCaching && cacheFile.exists()) {
                    return cacheFile;
                }
                ClassLoader cl = getClassLoader();
                if (NON_UNIX_FILE_SEP) {
                    fileName = fileName.replace(FILE_SEP, "/");
                }
                String parentFileName = file.getParent();
                if (parentFileName != null && (directoryContents = cl.getResource(parentFileName)) != null) {
                    unpackUrlResource(directoryContents, parentFileName, cl, true);
                }
                URL url = cl.getResource(fileName);
                if (url != null) {
                    return unpackUrlResource(url, fileName, cl, false);
                }
            }
            return file;
        }
    }

    private File unpackUrlResource(URL url, String fileName, ClassLoader cl, boolean isDir) {
        String prot;
        prot = url.getProtocol();
        switch (prot) {
            case "file":
                return unpackFromFileURL(url, fileName, cl);
            case "jar":
                return unpackFromJarURL(url, fileName, cl);
            case "bundle":
            case "bundleentry":
            case "bundleresource":
            case "resource":
                return unpackFromBundleURL(url, isDir);
            default:
                throw new IllegalStateException("Invalid url protocol: " + prot);
        }
    }

    private synchronized File unpackFromFileURL(URL url, String fileName, ClassLoader cl) throws IOException {
        File resource = new File(URIDecoder.decodeURIComponent(url.getPath(), false));
        boolean isDirectory = resource.isDirectory();
        File cacheFile = new File(this.cacheDir, fileName);
        if (!isDirectory) {
            cacheFile.getParentFile().mkdirs();
            try {
                if (this.enableCaching) {
                    Files.copy(resource.toPath(), cacheFile.toPath(), new CopyOption[0]);
                } else {
                    Files.copy(resource.toPath(), cacheFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (FileAlreadyExistsException e) {
            } catch (IOException e2) {
                throw new VertxException(e2);
            }
        } else {
            cacheFile.mkdirs();
            String[] listing = resource.list();
            for (String file : listing) {
                String subResource = fileName + "/" + file;
                URL url2 = cl.getResource(subResource);
                unpackFromFileURL(url2, subResource, cl);
            }
        }
        return cacheFile;
    }

    private synchronized File unpackFromJarURL(URL url, String fileName, ClassLoader cl) throws IOException {
        ZipFile zip;
        try {
            try {
                String path = url.getPath();
                int idx1 = path.lastIndexOf(FileUtil.JAR_PATH_EXT);
                if (idx1 == -1) {
                    idx1 = path.lastIndexOf(".zip!");
                }
                int idx2 = path.lastIndexOf(FileUtil.JAR_PATH_EXT, idx1 - 1);
                if (idx2 == -1) {
                    idx2 = path.lastIndexOf(".zip!", idx1 - 1);
                }
                if (idx2 == -1) {
                    zip = new ZipFile(new File(URIDecoder.decodeURIComponent(path.substring(5, idx1 + 4), false)));
                } else {
                    String s = path.substring(idx2 + 6, idx1 + 4);
                    zip = new ZipFile(resolveFile(s));
                }
                String inJarPath = path.substring(idx1 + 6);
                String[] parts = JAR_URL_SEP_PATTERN.split(inJarPath);
                StringBuilder prefixBuilder = new StringBuilder();
                for (int i = 0; i < parts.length - 1; i++) {
                    prefixBuilder.append(parts[i]).append("/");
                }
                String prefix = prefixBuilder.toString();
                Enumeration<? extends ZipEntry> entries = zip.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if (name.startsWith(prefix.isEmpty() ? fileName : prefix + fileName)) {
                        File file = new File(this.cacheDir, prefix.isEmpty() ? name : name.substring(prefix.length()));
                        if (name.endsWith("/")) {
                            file.mkdirs();
                        } else {
                            file.getParentFile().mkdirs();
                            try {
                                InputStream is = zip.getInputStream(entry);
                                Throwable th = null;
                                try {
                                    try {
                                        if (this.enableCaching) {
                                            Files.copy(is, file.toPath(), new CopyOption[0]);
                                        } else {
                                            Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                                        }
                                        if (is != null) {
                                            if (0 != 0) {
                                                try {
                                                    is.close();
                                                } catch (Throwable th2) {
                                                    th.addSuppressed(th2);
                                                }
                                            } else {
                                                is.close();
                                            }
                                        }
                                    } catch (Throwable th3) {
                                        if (is != null) {
                                            if (th != null) {
                                                try {
                                                    is.close();
                                                } catch (Throwable th4) {
                                                    th.addSuppressed(th4);
                                                }
                                            } else {
                                                is.close();
                                            }
                                        }
                                        throw th3;
                                    }
                                } finally {
                                }
                            } catch (FileAlreadyExistsException e) {
                            }
                        }
                    }
                }
                closeQuietly(zip);
                return new File(this.cacheDir, fileName);
            } catch (IOException e2) {
                throw new VertxException(e2);
            }
        } catch (Throwable th5) {
            closeQuietly(null);
            throw th5;
        }
    }

    private void closeQuietly(Closeable zip) throws IOException {
        if (zip != null) {
            try {
                zip.close();
            } catch (IOException e) {
            }
        }
    }

    private boolean isBundleUrlDirectory(URL url) {
        return url.toExternalForm().endsWith("/") || getClassLoader().getResource(new StringBuilder().append(url.getPath().substring(1)).append("/").toString()) != null;
    }

    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r11v1 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r12v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.nodes.InsnNode.getResult()" is null
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.collectRelatedVars(AbstractTypeConstraint.java:31)
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.<init>(AbstractTypeConstraint.java:19)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$1.<init>(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeMoveConstraint(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeConstraint(TypeSearch.java:361)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.collectConstraints(TypeSearch.java:341)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:60)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:116)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x00bb: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r11 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('is' java.io.InputStream)]) A[TRY_LEAVE], block:B:27:0x00bb */
    /* JADX WARN: Not initialized variable reg: 12, insn: 0x00c0: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r12 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:29:0x00c0 */
    /* JADX WARN: Type inference failed for: r11v1, names: [is], types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.Throwable] */
    private synchronized File unpackFromBundleURL(URL url, boolean isDir) throws IOException {
        ?? r11;
        ?? r12;
        try {
            File file = new File(this.cacheDir, url.getHost() + File.separator + url.getFile());
            file.getParentFile().mkdirs();
            if ((getClassLoader() != null && isBundleUrlDirectory(url)) || isDir) {
                file.mkdirs();
            } else {
                file.getParentFile().mkdirs();
                try {
                    try {
                        InputStream inputStreamOpenStream = url.openStream();
                        Throwable th = null;
                        if (this.enableCaching) {
                            Files.copy(inputStreamOpenStream, file.toPath(), new CopyOption[0]);
                        } else {
                            Files.copy(inputStreamOpenStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        }
                        if (inputStreamOpenStream != null) {
                            if (0 != 0) {
                                try {
                                    inputStreamOpenStream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            } else {
                                inputStreamOpenStream.close();
                            }
                        }
                    } catch (FileAlreadyExistsException e) {
                    }
                } catch (Throwable th3) {
                    if (r11 != 0) {
                        if (r12 != 0) {
                            try {
                                r11.close();
                            } catch (Throwable th4) {
                                r12.addSuppressed(th4);
                            }
                        } else {
                            r11.close();
                        }
                    }
                    throw th3;
                }
            }
            return new File(this.cacheDir, url.getHost() + File.separator + url.getFile());
        } catch (IOException e2) {
            throw new VertxException(e2);
        }
    }

    private ClassLoader getClassLoader() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = getClass().getClassLoader();
        }
        if (cl == null) {
            cl = Object.class.getClassLoader();
        }
        return cl;
    }

    private void setupCacheDir() {
        String cacheDirName = this.fileCacheDir + "/file-cache-" + UUID.randomUUID().toString();
        this.cacheDir = new File(cacheDirName);
        if (!this.cacheDir.mkdirs()) {
            throw new IllegalStateException("Failed to create cache dir: " + cacheDirName);
        }
        synchronized (this) {
            this.shutdownHook = new Thread(() -> {
                CountDownLatch latch = new CountDownLatch(1);
                new Thread(() -> {
                    try {
                        deleteCacheDir();
                    } catch (IOException e) {
                    }
                    latch.countDown();
                }).run();
                try {
                    latch.await(10L, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            Runtime.getRuntime().addShutdownHook(this.shutdownHook);
        }
    }

    private void deleteCacheDir() throws IOException {
        synchronized (this) {
            if (this.cacheDir == null || !this.cacheDir.exists()) {
                return;
            }
            Path path = this.cacheDir.toPath();
            this.cacheDir = null;
            FileSystemImpl.delete(path, true);
        }
    }
}
