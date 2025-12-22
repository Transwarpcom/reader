package io.vertx.core.file.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.CopyOptions;
import io.vertx.core.file.FileProps;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.FileSystemException;
import io.vertx.core.file.FileSystemProps;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileStore;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/file/impl/FileSystemImpl.class */
public class FileSystemImpl implements FileSystem {
    private static final CopyOptions DEFAULT_OPTIONS = new CopyOptions();
    protected final VertxInternal vertx;

    public FileSystemImpl(VertxInternal vertx) {
        this.vertx = vertx;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem copy(String from, String to, Handler<AsyncResult<Void>> handler) {
        return copy(from, to, DEFAULT_OPTIONS, handler);
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem copy(String from, String to, CopyOptions options, Handler<AsyncResult<Void>> handler) {
        copyInternal(from, to, options, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem copyBlocking(String from, String to) {
        copyInternal(from, to, DEFAULT_OPTIONS, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem copyRecursive(String from, String to, boolean recursive, Handler<AsyncResult<Void>> handler) {
        copyRecursiveInternal(from, to, recursive, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem copyRecursiveBlocking(String from, String to, boolean recursive) {
        copyRecursiveInternal(from, to, recursive, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem move(String from, String to, Handler<AsyncResult<Void>> handler) {
        return move(from, to, DEFAULT_OPTIONS, handler);
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem move(String from, String to, CopyOptions options, Handler<AsyncResult<Void>> handler) {
        moveInternal(from, to, options, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem moveBlocking(String from, String to) {
        moveInternal(from, to, DEFAULT_OPTIONS, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem truncate(String path, long len, Handler<AsyncResult<Void>> handler) {
        truncateInternal(path, len, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem truncateBlocking(String path, long len) {
        truncateInternal(path, len, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem chmod(String path, String perms, Handler<AsyncResult<Void>> handler) {
        chmodInternal(path, perms, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem chmodBlocking(String path, String perms) {
        chmodInternal(path, perms, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem chmodRecursive(String path, String perms, String dirPerms, Handler<AsyncResult<Void>> handler) {
        chmodInternal(path, perms, dirPerms, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem chmodRecursiveBlocking(String path, String perms, String dirPerms) {
        chmodInternal(path, perms, dirPerms, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem chown(String path, String user, String group, Handler<AsyncResult<Void>> handler) {
        chownInternal(path, user, group, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem chownBlocking(String path, String user, String group) {
        chownInternal(path, user, group, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem props(String path, Handler<AsyncResult<FileProps>> handler) {
        propsInternal(path, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileProps propsBlocking(String path) {
        return propsInternal(path, null).perform();
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem lprops(String path, Handler<AsyncResult<FileProps>> handler) {
        lpropsInternal(path, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileProps lpropsBlocking(String path) {
        return lpropsInternal(path, null).perform();
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem link(String link, String existing, Handler<AsyncResult<Void>> handler) {
        linkInternal(link, existing, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem linkBlocking(String link, String existing) {
        linkInternal(link, existing, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem symlink(String link, String existing, Handler<AsyncResult<Void>> handler) {
        symlinkInternal(link, existing, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem symlinkBlocking(String link, String existing) {
        symlinkInternal(link, existing, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem unlink(String link, Handler<AsyncResult<Void>> handler) {
        unlinkInternal(link, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem unlinkBlocking(String link) {
        unlinkInternal(link, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem readSymlink(String link, Handler<AsyncResult<String>> handler) {
        readSymlinkInternal(link, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public String readSymlinkBlocking(String link) {
        return readSymlinkInternal(link, null).perform();
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem delete(String path, Handler<AsyncResult<Void>> handler) {
        deleteInternal(path, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem deleteBlocking(String path) {
        deleteInternal(path, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem deleteRecursive(String path, boolean recursive, Handler<AsyncResult<Void>> handler) {
        deleteInternal(path, recursive, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem deleteRecursiveBlocking(String path, boolean recursive) {
        deleteInternal(path, recursive, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem mkdir(String path, Handler<AsyncResult<Void>> handler) {
        mkdirInternal(path, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem mkdirBlocking(String path) {
        mkdirInternal(path, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem mkdirs(String path, Handler<AsyncResult<Void>> handler) {
        mkdirInternal(path, true, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem mkdirsBlocking(String path) {
        mkdirInternal(path, true, (Handler<AsyncResult<Void>>) null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem mkdir(String path, String perms, Handler<AsyncResult<Void>> handler) {
        mkdirInternal(path, perms, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem mkdirBlocking(String path, String perms) {
        mkdirInternal(path, perms, (Handler<AsyncResult<Void>>) null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem mkdirs(String path, String perms, Handler<AsyncResult<Void>> handler) {
        mkdirInternal(path, perms, true, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem mkdirsBlocking(String path, String perms) {
        mkdirInternal(path, perms, true, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem readDir(String path, Handler<AsyncResult<List<String>>> handler) {
        readDirInternal(path, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public List<String> readDirBlocking(String path) {
        return readDirInternal(path, null).perform();
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem readDir(String path, String filter, Handler<AsyncResult<List<String>>> handler) {
        readDirInternal(path, filter, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public List<String> readDirBlocking(String path, String filter) {
        return readDirInternal(path, filter, null).perform();
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem readFile(String path, Handler<AsyncResult<Buffer>> handler) {
        readFileInternal(path, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public Buffer readFileBlocking(String path) {
        return readFileInternal(path, null).perform();
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem writeFile(String path, Buffer data, Handler<AsyncResult<Void>> handler) {
        writeFileInternal(path, data, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem writeFileBlocking(String path, Buffer data) {
        writeFileInternal(path, data, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem open(String path, OpenOptions options, Handler<AsyncResult<AsyncFile>> handler) {
        openInternal(path, options, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public AsyncFile openBlocking(String path, OpenOptions options) {
        return openInternal(path, options, null).perform();
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem createFile(String path, Handler<AsyncResult<Void>> handler) {
        createFileInternal(path, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem createFileBlocking(String path) {
        createFileInternal(path, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem createFile(String path, String perms, Handler<AsyncResult<Void>> handler) {
        createFileInternal(path, perms, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem createFileBlocking(String path, String perms) {
        createFileInternal(path, perms, null).perform();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem exists(String path, Handler<AsyncResult<Boolean>> handler) {
        existsInternal(path, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public boolean existsBlocking(String path) {
        return existsInternal(path, null).perform().booleanValue();
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem fsProps(String path, Handler<AsyncResult<FileSystemProps>> handler) {
        fsPropsInternal(path, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystemProps fsPropsBlocking(String path) {
        return fsPropsInternal(path, null).perform();
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem createTempDirectory(String prefix, Handler<AsyncResult<String>> handler) {
        createTempDirectoryInternal(null, prefix, null, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public String createTempDirectoryBlocking(String prefix) {
        return createTempDirectoryInternal(null, prefix, null, null).perform();
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem createTempDirectory(String prefix, String perms, Handler<AsyncResult<String>> handler) {
        createTempDirectoryInternal(null, prefix, perms, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public String createTempDirectoryBlocking(String prefix, String perms) {
        return createTempDirectoryInternal(null, prefix, perms, null).perform();
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem createTempDirectory(String dir, String prefix, String perms, Handler<AsyncResult<String>> handler) {
        createTempDirectoryInternal(dir, prefix, perms, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public String createTempDirectoryBlocking(String dir, String prefix, String perms) {
        return createTempDirectoryInternal(dir, prefix, perms, null).perform();
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem createTempFile(String prefix, String suffix, Handler<AsyncResult<String>> handler) {
        createTempFileInternal(null, prefix, suffix, null, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public String createTempFileBlocking(String prefix, String suffix) {
        return createTempFileInternal(null, prefix, suffix, null, null).perform();
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem createTempFile(String prefix, String suffix, String perms, Handler<AsyncResult<String>> handler) {
        createTempFileInternal(null, prefix, suffix, perms, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public String createTempFileBlocking(String prefix, String suffix, String perms) {
        return createTempFileInternal(null, prefix, suffix, perms, null).perform();
    }

    @Override // io.vertx.core.file.FileSystem
    public FileSystem createTempFile(String dir, String prefix, String suffix, String perms, Handler<AsyncResult<String>> handler) {
        createTempFileInternal(dir, prefix, suffix, perms, handler).run();
        return this;
    }

    @Override // io.vertx.core.file.FileSystem
    public String createTempFileBlocking(String dir, String prefix, String suffix, String perms) {
        return createTempFileInternal(dir, prefix, suffix, perms, null).perform();
    }

    private BlockingAction<Void> copyInternal(final String from, final String to, CopyOptions options, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        Objects.requireNonNull(options);
        Set<CopyOption> copyOptionSet = toCopyOptionSet(options);
        final CopyOption[] copyOptions = (CopyOption[]) copyOptionSet.toArray(new CopyOption[copyOptionSet.size()]);
        return new BlockingAction<Void>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public Void perform() throws IOException {
                try {
                    Path source = FileSystemImpl.this.vertx.resolveFile(from).toPath();
                    Path target = FileSystemImpl.this.vertx.resolveFile(to).toPath();
                    Files.copy(source, target, copyOptions);
                    return null;
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    private BlockingAction<Void> copyRecursiveInternal(final String from, final String to, final boolean recursive, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        return new BlockingAction<Void>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public Void perform() throws IOException {
                try {
                    final Path source = FileSystemImpl.this.vertx.resolveFile(from).toPath();
                    final Path target = FileSystemImpl.this.vertx.resolveFile(to).toPath();
                    if (recursive) {
                        Files.walkFileTree(source, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() { // from class: io.vertx.core.file.impl.FileSystemImpl.2.1
                            @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                                Path targetDir = target.resolve(source.relativize(dir));
                                try {
                                    Files.copy(dir, targetDir, new CopyOption[0]);
                                } catch (FileAlreadyExistsException e) {
                                    if (!Files.isDirectory(targetDir, new LinkOption[0])) {
                                        throw e;
                                    }
                                }
                                return FileVisitResult.CONTINUE;
                            }

                            @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                                Files.copy(file, target.resolve(source.relativize(file)), new CopyOption[0]);
                                return FileVisitResult.CONTINUE;
                            }
                        });
                    } else {
                        Files.copy(source, target, new CopyOption[0]);
                    }
                    return null;
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    private BlockingAction<Void> moveInternal(final String from, final String to, CopyOptions options, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        Objects.requireNonNull(options);
        Set<CopyOption> copyOptionSet = toCopyOptionSet(options);
        final CopyOption[] copyOptions = (CopyOption[]) copyOptionSet.toArray(new CopyOption[copyOptionSet.size()]);
        return new BlockingAction<Void>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public Void perform() throws IOException {
                try {
                    Path source = FileSystemImpl.this.vertx.resolveFile(from).toPath();
                    Path target = FileSystemImpl.this.vertx.resolveFile(to).toPath();
                    Files.move(source, target, copyOptions);
                    return null;
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    private BlockingAction<Void> truncateInternal(final String p, final long len, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(p);
        return new BlockingAction<Void>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public Void perform() throws IOException {
                RandomAccessFile raf = null;
                try {
                    String path = FileSystemImpl.this.vertx.resolveFile(p).getAbsolutePath();
                    if (len < 0) {
                        throw new FileSystemException("Cannot truncate file to size < 0");
                    }
                    if (!Files.exists(Paths.get(path, new String[0]), new LinkOption[0])) {
                        throw new FileSystemException("Cannot truncate file " + path + ". Does not exist");
                    }
                    try {
                        raf = new RandomAccessFile(path, "rw");
                        raf.setLength(len);
                        if (raf != null) {
                            raf.close();
                        }
                        return null;
                    } catch (Throwable th) {
                        if (raf != null) {
                            raf.close();
                        }
                        throw th;
                    }
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    private BlockingAction<Void> chmodInternal(String path, String perms, Handler<AsyncResult<Void>> handler) {
        return chmodInternal(path, perms, null, handler);
    }

    protected BlockingAction<Void> chmodInternal(final String path, String perms, String dirPerms, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(path);
        final Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(perms);
        final Set<PosixFilePermission> dirPermissions = dirPerms == null ? null : PosixFilePermissions.fromString(dirPerms);
        return new BlockingAction<Void>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public Void perform() throws IOException {
                try {
                    Path target = FileSystemImpl.this.vertx.resolveFile(path).toPath();
                    if (dirPermissions != null) {
                        Files.walkFileTree(target, new SimpleFileVisitor<Path>() { // from class: io.vertx.core.file.impl.FileSystemImpl.5.1
                            @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                                Files.setPosixFilePermissions(dir, dirPermissions);
                                return FileVisitResult.CONTINUE;
                            }

                            @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                                Files.setPosixFilePermissions(file, permissions);
                                return FileVisitResult.CONTINUE;
                            }
                        });
                    } else {
                        Files.setPosixFilePermissions(target, permissions);
                    }
                    return null;
                } catch (IOException e) {
                    throw new FileSystemException(e);
                } catch (SecurityException e2) {
                    throw new FileSystemException("Accessed denied for chmod on " + path);
                }
            }
        };
    }

    protected BlockingAction<Void> chownInternal(final String path, final String user, final String group, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(path);
        return new BlockingAction<Void>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public Void perform() throws IOException {
                try {
                    Path target = FileSystemImpl.this.vertx.resolveFile(path).toPath();
                    UserPrincipalLookupService service = target.getFileSystem().getUserPrincipalLookupService();
                    UserPrincipal userPrincipal = user == null ? null : service.lookupPrincipalByName(user);
                    GroupPrincipal groupPrincipal = group == null ? null : service.lookupPrincipalByGroupName(group);
                    if (groupPrincipal != null) {
                        PosixFileAttributeView view = (PosixFileAttributeView) Files.getFileAttributeView(target, PosixFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
                        if (view == null) {
                            throw new FileSystemException("Change group of file not supported");
                        }
                        view.setGroup(groupPrincipal);
                    }
                    if (userPrincipal != null) {
                        Files.setOwner(target, userPrincipal);
                    }
                    return null;
                } catch (IOException e) {
                    throw new FileSystemException(e);
                } catch (SecurityException e2) {
                    throw new FileSystemException("Accessed denied for chown on " + path);
                }
            }
        };
    }

    private BlockingAction<FileProps> propsInternal(String path, Handler<AsyncResult<FileProps>> handler) {
        return props(path, true, handler);
    }

    private BlockingAction<FileProps> lpropsInternal(String path, Handler<AsyncResult<FileProps>> handler) {
        return props(path, false, handler);
    }

    private BlockingAction<FileProps> props(final String path, final boolean followLinks, Handler<AsyncResult<FileProps>> handler) {
        Objects.requireNonNull(path);
        return new BlockingAction<FileProps>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public FileProps perform() throws IOException {
                BasicFileAttributes attrs;
                try {
                    Path target = FileSystemImpl.this.vertx.resolveFile(path).toPath();
                    if (followLinks) {
                        attrs = Files.readAttributes(target, (Class<BasicFileAttributes>) BasicFileAttributes.class, new LinkOption[0]);
                    } else {
                        attrs = Files.readAttributes(target, (Class<BasicFileAttributes>) BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
                    }
                    return new FilePropsImpl(attrs);
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    private BlockingAction<Void> linkInternal(String link, String existing, Handler<AsyncResult<Void>> handler) {
        return link(link, existing, false, handler);
    }

    private BlockingAction<Void> symlinkInternal(String link, String existing, Handler<AsyncResult<Void>> handler) {
        return link(link, existing, true, handler);
    }

    private BlockingAction<Void> link(final String link, final String existing, final boolean symbolic, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(link);
        Objects.requireNonNull(existing);
        return new BlockingAction<Void>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.8
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public Void perform() throws IOException {
                try {
                    Path source = FileSystemImpl.this.vertx.resolveFile(link).toPath();
                    Path target = FileSystemImpl.this.vertx.resolveFile(existing).toPath();
                    if (symbolic) {
                        Files.createSymbolicLink(source, target, new FileAttribute[0]);
                    } else {
                        Files.createLink(source, target);
                    }
                    return null;
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    private BlockingAction<Void> unlinkInternal(String link, Handler<AsyncResult<Void>> handler) {
        return deleteInternal(link, handler);
    }

    private BlockingAction<String> readSymlinkInternal(final String link, Handler<AsyncResult<String>> handler) {
        Objects.requireNonNull(link);
        return new BlockingAction<String>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.9
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public String perform() {
                try {
                    Path source = FileSystemImpl.this.vertx.resolveFile(link).toPath();
                    return Files.readSymbolicLink(source).toString();
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    private BlockingAction<Void> deleteInternal(String path, Handler<AsyncResult<Void>> handler) {
        return deleteInternal(path, false, handler);
    }

    private BlockingAction<Void> deleteInternal(final String path, final boolean recursive, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(path);
        return new BlockingAction<Void>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public Void perform() {
                try {
                    Path source = FileSystemImpl.this.vertx.resolveFile(path).toPath();
                    FileSystemImpl.delete(source, recursive);
                    return null;
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    public static void delete(Path path, boolean recursive) throws IOException {
        if (recursive) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() { // from class: io.vertx.core.file.impl.FileSystemImpl.11
                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
                    if (e == null) {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                    throw e;
                }
            });
        } else {
            Files.delete(path);
        }
    }

    private BlockingAction<Void> mkdirInternal(String path, Handler<AsyncResult<Void>> handler) {
        return mkdirInternal(path, null, false, handler);
    }

    private BlockingAction<Void> mkdirInternal(String path, boolean createParents, Handler<AsyncResult<Void>> handler) {
        return mkdirInternal(path, null, createParents, handler);
    }

    private BlockingAction<Void> mkdirInternal(String path, String perms, Handler<AsyncResult<Void>> handler) {
        return mkdirInternal(path, perms, false, handler);
    }

    protected BlockingAction<Void> mkdirInternal(final String path, String perms, final boolean createParents, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(path);
        final FileAttribute<?> attrs = perms == null ? null : PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString(perms));
        return new BlockingAction<Void>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.12
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public Void perform() throws IOException {
                try {
                    Path source = FileSystemImpl.this.vertx.resolveFile(path).toPath();
                    if (createParents) {
                        if (attrs != null) {
                            Files.createDirectories(source, attrs);
                        } else {
                            Files.createDirectories(source, new FileAttribute[0]);
                        }
                    } else if (attrs != null) {
                        Files.createDirectory(source, attrs);
                    } else {
                        Files.createDirectory(source, new FileAttribute[0]);
                    }
                    return null;
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    protected BlockingAction<String> createTempDirectoryInternal(final String parentDir, final String prefix, String perms, Handler<AsyncResult<String>> handler) {
        final FileAttribute<?> attrs = perms == null ? null : PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString(perms));
        return new BlockingAction<String>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.13
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public String perform() throws IOException {
                Path tmpDir;
                try {
                    if (parentDir != null) {
                        Path dir = FileSystemImpl.this.vertx.resolveFile(parentDir).toPath();
                        if (attrs != null) {
                            tmpDir = Files.createTempDirectory(dir, prefix, attrs);
                        } else {
                            tmpDir = Files.createTempDirectory(dir, prefix, new FileAttribute[0]);
                        }
                    } else if (attrs != null) {
                        tmpDir = Files.createTempDirectory(prefix, attrs);
                    } else {
                        tmpDir = Files.createTempDirectory(prefix, new FileAttribute[0]);
                    }
                    return tmpDir.toFile().getAbsolutePath();
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    protected BlockingAction<String> createTempFileInternal(final String parentDir, final String prefix, final String suffix, String perms, Handler<AsyncResult<String>> handler) {
        final FileAttribute<?> attrs = perms == null ? null : PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString(perms));
        return new BlockingAction<String>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.14
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public String perform() throws IOException {
                Path tmpFile;
                try {
                    if (parentDir != null) {
                        Path dir = FileSystemImpl.this.vertx.resolveFile(parentDir).toPath();
                        if (attrs != null) {
                            tmpFile = Files.createTempFile(dir, prefix, suffix, attrs);
                        } else {
                            tmpFile = Files.createTempFile(dir, prefix, suffix, new FileAttribute[0]);
                        }
                    } else if (attrs != null) {
                        tmpFile = Files.createTempFile(prefix, suffix, attrs);
                    } else {
                        tmpFile = Files.createTempFile(prefix, suffix, new FileAttribute[0]);
                    }
                    return tmpFile.toFile().getAbsolutePath();
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    private BlockingAction<List<String>> readDirInternal(String path, Handler<AsyncResult<List<String>>> handler) {
        return readDirInternal(path, null, handler);
    }

    private BlockingAction<List<String>> readDirInternal(final String p, final String filter, Handler<AsyncResult<List<String>>> handler) {
        Objects.requireNonNull(p);
        return new BlockingAction<List<String>>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.15
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public List<String> perform() {
                FilenameFilter fnFilter;
                File[] files;
                try {
                    File file = FileSystemImpl.this.vertx.resolveFile(p);
                    if (!file.exists()) {
                        throw new FileSystemException("Cannot read directory " + file + ". Does not exist");
                    }
                    if (!file.isDirectory()) {
                        throw new FileSystemException("Cannot read directory " + file + ". It's not a directory");
                    }
                    if (filter != null) {
                        fnFilter = new FilenameFilter() { // from class: io.vertx.core.file.impl.FileSystemImpl.15.1
                            @Override // java.io.FilenameFilter
                            public boolean accept(File dir, String name) {
                                return Pattern.matches(filter, name);
                            }
                        };
                    } else {
                        fnFilter = null;
                    }
                    if (fnFilter == null) {
                        files = file.listFiles();
                    } else {
                        files = file.listFiles(fnFilter);
                    }
                    List<String> ret = new ArrayList<>(files.length);
                    for (File f : files) {
                        ret.add(f.getCanonicalPath());
                    }
                    return ret;
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    private BlockingAction<Buffer> readFileInternal(final String path, Handler<AsyncResult<Buffer>> handler) {
        Objects.requireNonNull(path);
        return new BlockingAction<Buffer>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.16
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public Buffer perform() throws IOException {
                try {
                    Path target = FileSystemImpl.this.vertx.resolveFile(path).toPath();
                    byte[] bytes = Files.readAllBytes(target);
                    Buffer buff = Buffer.buffer(bytes);
                    return buff;
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    private BlockingAction<Void> writeFileInternal(final String path, final Buffer data, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(path);
        Objects.requireNonNull(data);
        return new BlockingAction<Void>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.17
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public Void perform() throws IOException {
                try {
                    Path target = FileSystemImpl.this.vertx.resolveFile(path).toPath();
                    Files.write(target, data.getBytes(), new OpenOption[0]);
                    return null;
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    private BlockingAction<AsyncFile> openInternal(final String p, final OpenOptions options, Handler<AsyncResult<AsyncFile>> handler) {
        Objects.requireNonNull(p);
        Objects.requireNonNull(options);
        return new BlockingAction<AsyncFile>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.18
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public AsyncFile perform() {
                String path = FileSystemImpl.this.vertx.resolveFile(p).getAbsolutePath();
                return FileSystemImpl.this.doOpen(path, options, this.context);
            }
        };
    }

    protected AsyncFile doOpen(String path, OpenOptions options, ContextInternal context) {
        return new AsyncFileImpl(this.vertx, path, options, context);
    }

    private BlockingAction<Void> createFileInternal(String path, Handler<AsyncResult<Void>> handler) {
        return createFileInternal(path, null, handler);
    }

    protected BlockingAction<Void> createFileInternal(final String p, String perms, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(p);
        final FileAttribute<?> attrs = perms == null ? null : PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString(perms));
        return new BlockingAction<Void>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.19
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public Void perform() throws IOException {
                try {
                    Path target = FileSystemImpl.this.vertx.resolveFile(p).toPath();
                    if (attrs != null) {
                        Files.createFile(target, attrs);
                    } else {
                        Files.createFile(target, new FileAttribute[0]);
                    }
                    return null;
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    private BlockingAction<Boolean> existsInternal(final String path, Handler<AsyncResult<Boolean>> handler) {
        Objects.requireNonNull(path);
        return new BlockingAction<Boolean>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.20
            File file;

            {
                this.file = FileSystemImpl.this.vertx.resolveFile(path);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public Boolean perform() {
                return Boolean.valueOf(this.file.exists());
            }
        };
    }

    private BlockingAction<FileSystemProps> fsPropsInternal(final String path, Handler<AsyncResult<FileSystemProps>> handler) {
        Objects.requireNonNull(path);
        return new BlockingAction<FileSystemProps>(handler) { // from class: io.vertx.core.file.impl.FileSystemImpl.21
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public FileSystemProps perform() throws IOException {
                try {
                    Path target = FileSystemImpl.this.vertx.resolveFile(path).toPath();
                    FileStore fs = Files.getFileStore(target);
                    return new FileSystemPropsImpl(fs.getTotalSpace(), fs.getUnallocatedSpace(), fs.getUsableSpace());
                } catch (IOException e) {
                    throw new FileSystemException(e);
                }
            }
        };
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/file/impl/FileSystemImpl$BlockingAction.class */
    protected abstract class BlockingAction<T> implements Handler<Promise<T>> {
        private final Handler<AsyncResult<T>> handler;
        protected final ContextInternal context;

        public abstract T perform();

        public BlockingAction(Handler<AsyncResult<T>> handler) {
            this.handler = handler;
            this.context = FileSystemImpl.this.vertx.getOrCreateContext();
        }

        public void run() {
            this.context.executeBlockingInternal(this, this.handler);
        }

        @Override // io.vertx.core.Handler
        public void handle(Promise<T> fut) {
            try {
                T result = perform();
                fut.complete(result);
            } catch (Exception e) {
                fut.fail(e);
            }
        }
    }

    static Set<CopyOption> toCopyOptionSet(CopyOptions copyOptions) {
        Set<CopyOption> copyOptionSet = new HashSet<>();
        if (copyOptions.isReplaceExisting()) {
            copyOptionSet.add(StandardCopyOption.REPLACE_EXISTING);
        }
        if (copyOptions.isCopyAttributes()) {
            copyOptionSet.add(StandardCopyOption.COPY_ATTRIBUTES);
        }
        if (copyOptions.isAtomicMove()) {
            copyOptionSet.add(StandardCopyOption.ATOMIC_MOVE);
        }
        if (copyOptions.isNofollowLinks()) {
            copyOptionSet.add(LinkOption.NOFOLLOW_LINKS);
        }
        return copyOptionSet;
    }
}
