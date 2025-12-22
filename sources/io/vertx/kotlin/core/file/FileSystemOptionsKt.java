package io.vertx.kotlin.core.file;

import io.vertx.core.file.FileSystemOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: FileSystemOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"FileSystemOptions", "Lio/vertx/core/file/FileSystemOptions;", "classPathResolvingEnabled", "", "fileCacheDir", "", "fileCachingEnabled", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;)Lio/vertx/core/file/FileSystemOptions;", "fileSystemOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/file/FileSystemOptionsKt.class */
public final class FileSystemOptionsKt {
    @NotNull
    public static /* synthetic */ FileSystemOptions fileSystemOptionsOf$default(Boolean bool, String str, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            bool2 = (Boolean) null;
        }
        return fileSystemOptionsOf(bool, str, bool2);
    }

    @NotNull
    public static final FileSystemOptions fileSystemOptionsOf(@Nullable Boolean classPathResolvingEnabled, @Nullable String fileCacheDir, @Nullable Boolean fileCachingEnabled) {
        FileSystemOptions $this$apply = new FileSystemOptions();
        if (classPathResolvingEnabled != null) {
            $this$apply.setClassPathResolvingEnabled(classPathResolvingEnabled.booleanValue());
        }
        if (fileCacheDir != null) {
            $this$apply.setFileCacheDir(fileCacheDir);
        }
        if (fileCachingEnabled != null) {
            $this$apply.setFileCachingEnabled(fileCachingEnabled.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "fileSystemOptionsOf(classPathResolvingEnabled, fileCacheDir, fileCachingEnabled)"))
    @NotNull
    public static /* synthetic */ FileSystemOptions FileSystemOptions$default(Boolean bool, String str, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            bool2 = (Boolean) null;
        }
        return FileSystemOptions(bool, str, bool2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "fileSystemOptionsOf(classPathResolvingEnabled, fileCacheDir, fileCachingEnabled)"))
    @NotNull
    public static final FileSystemOptions FileSystemOptions(@Nullable Boolean classPathResolvingEnabled, @Nullable String fileCacheDir, @Nullable Boolean fileCachingEnabled) {
        FileSystemOptions $this$apply = new FileSystemOptions();
        if (classPathResolvingEnabled != null) {
            $this$apply.setClassPathResolvingEnabled(classPathResolvingEnabled.booleanValue());
        }
        if (fileCacheDir != null) {
            $this$apply.setFileCacheDir(fileCacheDir);
        }
        if (fileCachingEnabled != null) {
            $this$apply.setFileCachingEnabled(fileCachingEnabled.booleanValue());
        }
        return $this$apply;
    }
}
