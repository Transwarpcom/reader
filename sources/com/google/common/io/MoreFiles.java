package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.graph.SuccessorsFunction;
import com.google.common.graph.Traverser;
import com.google.common.io.ByteSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.SecureDirectoryStream;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

@Beta
@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/MoreFiles.class */
public final class MoreFiles {
    private static final SuccessorsFunction<Path> FILE_TREE = new SuccessorsFunction<Path>() { // from class: com.google.common.io.MoreFiles.1
        @Override // com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
        public Iterable<Path> successors(Path path) {
            return MoreFiles.fileTreeChildren(path);
        }
    };

    private MoreFiles() {
    }

    public static ByteSource asByteSource(Path path, OpenOption... options) {
        return new PathByteSource(path, options);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/MoreFiles$PathByteSource.class */
    private static final class PathByteSource extends ByteSource {
        private static final LinkOption[] FOLLOW_LINKS = new LinkOption[0];
        private final Path path;
        private final OpenOption[] options;
        private final boolean followLinks;

        private PathByteSource(Path path, OpenOption... options) {
            this.path = (Path) Preconditions.checkNotNull(path);
            this.options = (OpenOption[]) options.clone();
            this.followLinks = followLinks(this.options);
        }

        private static boolean followLinks(OpenOption[] options) {
            for (OpenOption option : options) {
                if (option == LinkOption.NOFOLLOW_LINKS) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return java.nio.file.Files.newInputStream(this.path, this.options);
        }

        private BasicFileAttributes readAttributes() throws IOException {
            return java.nio.file.Files.readAttributes(this.path, BasicFileAttributes.class, this.followLinks ? FOLLOW_LINKS : new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            try {
                BasicFileAttributes attrs = readAttributes();
                if (attrs.isDirectory() || attrs.isSymbolicLink()) {
                    return Optional.absent();
                }
                return Optional.of(Long.valueOf(attrs.size()));
            } catch (IOException e) {
                return Optional.absent();
            }
        }

        @Override // com.google.common.io.ByteSource
        public long size() throws IOException {
            BasicFileAttributes attrs = readAttributes();
            if (attrs.isDirectory()) {
                throw new IOException("can't read: is a directory");
            }
            if (attrs.isSymbolicLink()) {
                throw new IOException("can't read: is a symbolic link");
            }
            return attrs.size();
        }

        @Override // com.google.common.io.ByteSource
        public byte[] read() throws IOException {
            SeekableByteChannel channel = java.nio.file.Files.newByteChannel(this.path, this.options);
            Throwable th = null;
            try {
                byte[] byteArray = ByteStreams.toByteArray(Channels.newInputStream(channel), channel.size());
                if (channel != null) {
                    if (0 != 0) {
                        try {
                            channel.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        channel.close();
                    }
                }
                return byteArray;
            } catch (Throwable th3) {
                if (channel != null) {
                    if (0 != 0) {
                        try {
                            channel.close();
                        } catch (Throwable th4) {
                            th.addSuppressed(th4);
                        }
                    } else {
                        channel.close();
                    }
                }
                throw th3;
            }
        }

        @Override // com.google.common.io.ByteSource
        public CharSource asCharSource(Charset charset) {
            if (this.options.length == 0) {
                return new ByteSource.AsCharSource(charset) { // from class: com.google.common.io.MoreFiles.PathByteSource.1
                    @Override // com.google.common.io.CharSource
                    public Stream<String> lines() throws IOException {
                        return java.nio.file.Files.lines(PathByteSource.this.path, this.charset);
                    }
                };
            }
            return super.asCharSource(charset);
        }

        public String toString() {
            return "MoreFiles.asByteSource(" + this.path + ", " + Arrays.toString(this.options) + ")";
        }
    }

    public static ByteSink asByteSink(Path path, OpenOption... options) {
        return new PathByteSink(path, options);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/MoreFiles$PathByteSink.class */
    private static final class PathByteSink extends ByteSink {
        private final Path path;
        private final OpenOption[] options;

        private PathByteSink(Path path, OpenOption... options) {
            this.path = (Path) Preconditions.checkNotNull(path);
            this.options = (OpenOption[]) options.clone();
        }

        @Override // com.google.common.io.ByteSink
        public OutputStream openStream() throws IOException {
            return java.nio.file.Files.newOutputStream(this.path, this.options);
        }

        public String toString() {
            return "MoreFiles.asByteSink(" + this.path + ", " + Arrays.toString(this.options) + ")";
        }
    }

    public static CharSource asCharSource(Path path, Charset charset, OpenOption... options) {
        return asByteSource(path, options).asCharSource(charset);
    }

    public static CharSink asCharSink(Path path, Charset charset, OpenOption... options) {
        return asByteSink(path, options).asCharSink(charset);
    }

    public static ImmutableList<Path> listFiles(Path dir) throws IOException {
        try {
            DirectoryStream<Path> stream = java.nio.file.Files.newDirectoryStream(dir);
            Throwable th = null;
            try {
                ImmutableList<Path> immutableListCopyOf = ImmutableList.copyOf(stream);
                if (stream != null) {
                    if (0 != 0) {
                        try {
                            stream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        stream.close();
                    }
                }
                return immutableListCopyOf;
            } finally {
            }
        } catch (DirectoryIteratorException e) {
            throw e.getCause();
        }
    }

    public static Traverser<Path> fileTraverser() {
        return Traverser.forTree(FILE_TREE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Iterable<Path> fileTreeChildren(Path dir) {
        if (java.nio.file.Files.isDirectory(dir, LinkOption.NOFOLLOW_LINKS)) {
            try {
                return listFiles(dir);
            } catch (IOException e) {
                throw new DirectoryIteratorException(e);
            }
        }
        return ImmutableList.of();
    }

    public static Predicate<Path> isDirectory(LinkOption... options) {
        final LinkOption[] optionsCopy = (LinkOption[]) options.clone();
        return new Predicate<Path>() { // from class: com.google.common.io.MoreFiles.2
            @Override // com.google.common.base.Predicate
            public boolean apply(Path input) {
                return java.nio.file.Files.isDirectory(input, optionsCopy);
            }

            public String toString() {
                return "MoreFiles.isDirectory(" + Arrays.toString(optionsCopy) + ")";
            }
        };
    }

    private static boolean isDirectory(SecureDirectoryStream<Path> dir, Path name, LinkOption... options) throws IOException {
        return ((BasicFileAttributeView) dir.getFileAttributeView(name, BasicFileAttributeView.class, options)).readAttributes().isDirectory();
    }

    public static Predicate<Path> isRegularFile(LinkOption... options) {
        final LinkOption[] optionsCopy = (LinkOption[]) options.clone();
        return new Predicate<Path>() { // from class: com.google.common.io.MoreFiles.3
            @Override // com.google.common.base.Predicate
            public boolean apply(Path input) {
                return java.nio.file.Files.isRegularFile(input, optionsCopy);
            }

            public String toString() {
                return "MoreFiles.isRegularFile(" + Arrays.toString(optionsCopy) + ")";
            }
        };
    }

    public static boolean equal(Path path1, Path path2) throws IOException {
        Preconditions.checkNotNull(path1);
        Preconditions.checkNotNull(path2);
        if (java.nio.file.Files.isSameFile(path1, path2)) {
            return true;
        }
        ByteSource source1 = asByteSource(path1, new OpenOption[0]);
        ByteSource source2 = asByteSource(path2, new OpenOption[0]);
        long len1 = source1.sizeIfKnown().or((Optional<Long>) 0L).longValue();
        long len2 = source2.sizeIfKnown().or((Optional<Long>) 0L).longValue();
        if (len1 != 0 && len2 != 0 && len1 != len2) {
            return false;
        }
        return source1.contentEquals(source2);
    }

    public static void touch(Path path) throws IOException {
        Preconditions.checkNotNull(path);
        try {
            java.nio.file.Files.setLastModifiedTime(path, FileTime.fromMillis(System.currentTimeMillis()));
        } catch (NoSuchFileException e) {
            try {
                java.nio.file.Files.createFile(path, new FileAttribute[0]);
            } catch (FileAlreadyExistsException e2) {
            }
        }
    }

    public static void createParentDirectories(Path path, FileAttribute<?>... attrs) throws IOException {
        Path normalizedAbsolutePath = path.toAbsolutePath().normalize();
        Path parent = normalizedAbsolutePath.getParent();
        if (parent != null && !java.nio.file.Files.isDirectory(parent, new LinkOption[0])) {
            java.nio.file.Files.createDirectories(parent, attrs);
            if (!java.nio.file.Files.isDirectory(parent, new LinkOption[0])) {
                throw new IOException("Unable to create parent directories of " + path);
            }
        }
    }

    public static String getFileExtension(Path path) {
        String fileName;
        int dotIndex;
        Path name = path.getFileName();
        return (name == null || (dotIndex = (fileName = name.toString()).lastIndexOf(46)) == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    public static String getNameWithoutExtension(Path path) {
        Path name = path.getFileName();
        if (name == null) {
            return "";
        }
        String fileName = name.toString();
        int dotIndex = fileName.lastIndexOf(46);
        return dotIndex == -1 ? fileName : fileName.substring(0, dotIndex);
    }

    public static void deleteRecursively(Path path, RecursiveDeleteOption... options) throws IOException {
        boolean sdsSupported;
        DirectoryStream<Path> parent;
        Throwable th;
        Path parentPath = getParentPath(path);
        if (parentPath == null) {
            throw new FileSystemException(path.toString(), null, "can't delete recursively");
        }
        Collection<IOException> exceptions = null;
        try {
            sdsSupported = false;
            parent = java.nio.file.Files.newDirectoryStream(parentPath);
            th = null;
        } catch (IOException e) {
            if (exceptions == null) {
                throw e;
            }
            exceptions.add(e);
        }
        try {
            try {
                if (parent instanceof SecureDirectoryStream) {
                    sdsSupported = true;
                    exceptions = deleteRecursivelySecure((SecureDirectoryStream) parent, path.getFileName());
                }
                if (parent != null) {
                    if (0 != 0) {
                        try {
                            parent.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        parent.close();
                    }
                }
                if (!sdsSupported) {
                    checkAllowsInsecure(path, options);
                    exceptions = deleteRecursivelyInsecure(path);
                }
                if (exceptions != null) {
                    throwDeleteFailed(path, exceptions);
                }
            } finally {
            }
        } catch (Throwable th3) {
            th = th3;
            throw th3;
        }
    }

    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r6v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r7v0 ??
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
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x005c: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r6 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('stream' java.nio.file.DirectoryStream<java.nio.file.Path>)])
 A[TRY_LEAVE], block:B:20:0x005c */
    /* JADX WARN: Not initialized variable reg: 7, insn: 0x0060: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:22:0x0060 */
    /* JADX WARN: Type inference failed for: r6v0, names: [stream], types: [java.nio.file.DirectoryStream, java.nio.file.DirectoryStream<java.nio.file.Path>] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.Throwable] */
    public static void deleteDirectoryContents(Path path, RecursiveDeleteOption... options) throws IOException {
        Collection<IOException> exceptions = null;
        try {
            try {
                DirectoryStream<Path> directoryStreamNewDirectoryStream = java.nio.file.Files.newDirectoryStream(path);
                Throwable th = null;
                if (directoryStreamNewDirectoryStream instanceof SecureDirectoryStream) {
                    exceptions = deleteDirectoryContentsSecure((SecureDirectoryStream) directoryStreamNewDirectoryStream);
                } else {
                    checkAllowsInsecure(path, options);
                    exceptions = deleteDirectoryContentsInsecure(directoryStreamNewDirectoryStream);
                }
                if (directoryStreamNewDirectoryStream != null) {
                    if (0 != 0) {
                        try {
                            directoryStreamNewDirectoryStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        directoryStreamNewDirectoryStream.close();
                    }
                }
            } finally {
            }
        } catch (IOException e) {
            if (exceptions == null) {
                throw e;
            }
            exceptions.add(e);
        }
        if (exceptions != null) {
            throwDeleteFailed(path, exceptions);
        }
    }

    private static Collection<IOException> deleteRecursivelySecure(SecureDirectoryStream<Path> dir, Path path) throws IOException {
        Collection<IOException> exceptions = null;
        try {
            if (isDirectory(dir, path, LinkOption.NOFOLLOW_LINKS)) {
                SecureDirectoryStream<Path> childDir = dir.newDirectoryStream(path, LinkOption.NOFOLLOW_LINKS);
                Throwable th = null;
                try {
                    try {
                        exceptions = deleteDirectoryContentsSecure(childDir);
                        if (childDir != null) {
                            if (0 != 0) {
                                try {
                                    childDir.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            } else {
                                childDir.close();
                            }
                        }
                        if (exceptions == null) {
                            dir.deleteDirectory(path);
                        }
                    } finally {
                    }
                } finally {
                }
            } else {
                dir.deleteFile(path);
            }
            return exceptions;
        } catch (IOException e) {
            return addException(exceptions, e);
        }
    }

    private static Collection<IOException> deleteDirectoryContentsSecure(SecureDirectoryStream<Path> dir) {
        Collection<IOException> exceptions = null;
        try {
            for (Path path : dir) {
                exceptions = concat(exceptions, deleteRecursivelySecure(dir, path.getFileName()));
            }
            return exceptions;
        } catch (DirectoryIteratorException e) {
            return addException(exceptions, e.getCause());
        }
    }

    private static Collection<IOException> deleteRecursivelyInsecure(Path path) throws IOException {
        Collection<IOException> exceptions = null;
        try {
            if (java.nio.file.Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
                DirectoryStream<Path> stream = java.nio.file.Files.newDirectoryStream(path);
                Throwable th = null;
                try {
                    try {
                        exceptions = deleteDirectoryContentsInsecure(stream);
                        if (stream != null) {
                            if (0 != 0) {
                                try {
                                    stream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            } else {
                                stream.close();
                            }
                        }
                    } finally {
                    }
                } finally {
                }
            }
            if (exceptions == null) {
                java.nio.file.Files.delete(path);
            }
            return exceptions;
        } catch (IOException e) {
            return addException(exceptions, e);
        }
    }

    private static Collection<IOException> deleteDirectoryContentsInsecure(DirectoryStream<Path> dir) {
        Collection<IOException> exceptions = null;
        try {
            for (Path entry : dir) {
                exceptions = concat(exceptions, deleteRecursivelyInsecure(entry));
            }
            return exceptions;
        } catch (DirectoryIteratorException e) {
            return addException(exceptions, e.getCause());
        }
    }

    private static Path getParentPath(Path path) {
        Path parent = path.getParent();
        if (parent != null) {
            return parent;
        }
        if (path.getNameCount() == 0) {
            return null;
        }
        return path.getFileSystem().getPath(".", new String[0]);
    }

    private static void checkAllowsInsecure(Path path, RecursiveDeleteOption[] options) throws InsecureRecursiveDeleteException {
        if (!Arrays.asList(options).contains(RecursiveDeleteOption.ALLOW_INSECURE)) {
            throw new InsecureRecursiveDeleteException(path.toString());
        }
    }

    private static Collection<IOException> addException(Collection<IOException> exceptions, IOException e) {
        if (exceptions == null) {
            exceptions = new ArrayList();
        }
        exceptions.add(e);
        return exceptions;
    }

    private static Collection<IOException> concat(Collection<IOException> exceptions, Collection<IOException> other) {
        if (exceptions == null) {
            return other;
        }
        if (other != null) {
            exceptions.addAll(other);
        }
        return exceptions;
    }

    private static void throwDeleteFailed(Path path, Collection<IOException> exceptions) throws FileSystemException {
        FileSystemException deleteFailed = new FileSystemException(path.toString(), null, "failed to delete one or more files; see suppressed exceptions for details");
        for (IOException e : exceptions) {
            deleteFailed.addSuppressed(e);
        }
        throw deleteFailed;
    }
}
