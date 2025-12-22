package okhttp3.internal.io;

import io.vertx.core.cli.converters.FromBasedConverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.PackageDocumentBase;
import okio.Okio;
import okio.Okio__JvmOkioKt;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.DocumentType;

/* compiled from: FileSystem.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018�� \u00142\u00020\u0001:\u0001\u0014J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0015"}, d2 = {"Lokhttp3/internal/io/FileSystem;", "", "appendingSink", "Lokio/Sink;", "file", "Ljava/io/File;", "delete", "", "deleteContents", "directory", "exists", "", "rename", FromBasedConverter.FROM, "to", "sink", "size", "", PackageDocumentBase.DCTags.source, "Lokio/Source;", "Companion", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/internal/io/FileSystem.class */
public interface FileSystem {
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    public static final FileSystem SYSTEM = new Companion.SystemFileSystem();

    @NotNull
    Source source(@NotNull File file) throws FileNotFoundException;

    @NotNull
    Sink sink(@NotNull File file) throws FileNotFoundException;

    @NotNull
    Sink appendingSink(@NotNull File file) throws FileNotFoundException;

    void delete(@NotNull File file) throws IOException;

    boolean exists(@NotNull File file);

    long size(@NotNull File file);

    void rename(@NotNull File file, @NotNull File file2) throws IOException;

    void deleteContents(@NotNull File file) throws IOException;

    /* compiled from: FileSystem.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001:\u0001\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001��¢\u0006\u0002\n��¨\u0006\u0001\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001¨\u0006\u0006"}, d2 = {"Lokhttp3/internal/io/FileSystem$Companion;", "", "()V", DocumentType.SYSTEM_KEY, "Lokhttp3/internal/io/FileSystem;", "SystemFileSystem", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/internal/io/FileSystem$Companion.class */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* compiled from: FileSystem.kt */
        @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\b\u0002\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0006H\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0016J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016¨\u0006\u0017"}, d2 = {"Lokhttp3/internal/io/FileSystem$Companion$SystemFileSystem;", "Lokhttp3/internal/io/FileSystem;", "()V", "appendingSink", "Lokio/Sink;", "file", "Ljava/io/File;", "delete", "", "deleteContents", "directory", "exists", "", "rename", FromBasedConverter.FROM, "to", "sink", "size", "", PackageDocumentBase.DCTags.source, "Lokio/Source;", "toString", "", "okhttp"})
        /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/internal/io/FileSystem$Companion$SystemFileSystem.class */
        private static final class SystemFileSystem implements FileSystem {
            @Override // okhttp3.internal.io.FileSystem
            @NotNull
            public Source source(@NotNull File file) throws FileNotFoundException {
                Intrinsics.checkNotNullParameter(file, "file");
                return Okio.source(file);
            }

            @Override // okhttp3.internal.io.FileSystem
            @NotNull
            public Sink sink(@NotNull File file) throws FileNotFoundException {
                Sink sinkSink$default;
                Intrinsics.checkNotNullParameter(file, "file");
                try {
                    sinkSink$default = Okio__JvmOkioKt.sink$default(file, false, 1, null);
                } catch (FileNotFoundException e) {
                    file.getParentFile().mkdirs();
                    sinkSink$default = Okio__JvmOkioKt.sink$default(file, false, 1, null);
                }
                return sinkSink$default;
            }

            @Override // okhttp3.internal.io.FileSystem
            @NotNull
            public Sink appendingSink(@NotNull File file) throws FileNotFoundException {
                Sink sinkAppendingSink;
                Intrinsics.checkNotNullParameter(file, "file");
                try {
                    sinkAppendingSink = Okio.appendingSink(file);
                } catch (FileNotFoundException e) {
                    file.getParentFile().mkdirs();
                    sinkAppendingSink = Okio.appendingSink(file);
                }
                return sinkAppendingSink;
            }

            @Override // okhttp3.internal.io.FileSystem
            public void delete(@NotNull File file) throws IOException {
                Intrinsics.checkNotNullParameter(file, "file");
                if (!file.delete() && file.exists()) {
                    throw new IOException("failed to delete " + file);
                }
            }

            @Override // okhttp3.internal.io.FileSystem
            public boolean exists(@NotNull File file) {
                Intrinsics.checkNotNullParameter(file, "file");
                return file.exists();
            }

            @Override // okhttp3.internal.io.FileSystem
            public long size(@NotNull File file) {
                Intrinsics.checkNotNullParameter(file, "file");
                return file.length();
            }

            @Override // okhttp3.internal.io.FileSystem
            public void rename(@NotNull File from, @NotNull File to) throws IOException {
                Intrinsics.checkNotNullParameter(from, "from");
                Intrinsics.checkNotNullParameter(to, "to");
                delete(to);
                if (!from.renameTo(to)) {
                    throw new IOException("failed to rename " + from + " to " + to);
                }
            }

            @Override // okhttp3.internal.io.FileSystem
            public void deleteContents(@NotNull File directory) throws IOException {
                Intrinsics.checkNotNullParameter(directory, "directory");
                File[] files = directory.listFiles();
                if (files == null) {
                    throw new IOException("not a readable directory: " + directory);
                }
                for (File file : files) {
                    Intrinsics.checkNotNullExpressionValue(file, "file");
                    if (file.isDirectory()) {
                        deleteContents(file);
                    }
                    if (!file.delete()) {
                        throw new IOException("failed to delete " + file);
                    }
                }
            }

            @NotNull
            public String toString() {
                return "FileSystem.SYSTEM";
            }
        }
    }
}
