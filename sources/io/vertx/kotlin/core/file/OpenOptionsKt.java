package io.vertx.kotlin.core.file;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.file.OpenOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: OpenOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\u001a\u0091\u0001\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u000f\u001a\u008f\u0001\u0010\u0010\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000f¨\u0006\u0011"}, d2 = {"OpenOptions", "Lio/vertx/core/file/OpenOptions;", RtspHeaders.Values.APPEND, "", "create", "createNew", "deleteOnClose", "dsync", "perms", "", "read", "sparse", "sync", "truncateExisting", "write", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lio/vertx/core/file/OpenOptions;", "openOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/file/OpenOptionsKt.class */
public final class OpenOptionsKt {
    @NotNull
    public static /* synthetic */ OpenOptions openOptionsOf$default(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, String str, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 4) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 8) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 16) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i & 32) != 0) {
            str = (String) null;
        }
        if ((i & 64) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i & 128) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i & 256) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i & 512) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i & 1024) != 0) {
            bool10 = (Boolean) null;
        }
        return openOptionsOf(bool, bool2, bool3, bool4, bool5, str, bool6, bool7, bool8, bool9, bool10);
    }

    @NotNull
    public static final OpenOptions openOptionsOf(@Nullable Boolean append, @Nullable Boolean create, @Nullable Boolean createNew, @Nullable Boolean deleteOnClose, @Nullable Boolean dsync, @Nullable String perms, @Nullable Boolean read, @Nullable Boolean sparse, @Nullable Boolean sync, @Nullable Boolean truncateExisting, @Nullable Boolean write) {
        OpenOptions $this$apply = new OpenOptions();
        if (append != null) {
            $this$apply.setAppend(append.booleanValue());
        }
        if (create != null) {
            $this$apply.setCreate(create.booleanValue());
        }
        if (createNew != null) {
            $this$apply.setCreateNew(createNew.booleanValue());
        }
        if (deleteOnClose != null) {
            $this$apply.setDeleteOnClose(deleteOnClose.booleanValue());
        }
        if (dsync != null) {
            $this$apply.setDsync(dsync.booleanValue());
        }
        if (perms != null) {
            $this$apply.setPerms(perms);
        }
        if (read != null) {
            $this$apply.setRead(read.booleanValue());
        }
        if (sparse != null) {
            $this$apply.setSparse(sparse.booleanValue());
        }
        if (sync != null) {
            $this$apply.setSync(sync.booleanValue());
        }
        if (truncateExisting != null) {
            $this$apply.setTruncateExisting(truncateExisting.booleanValue());
        }
        if (write != null) {
            $this$apply.setWrite(write.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "openOptionsOf(append, create, createNew, deleteOnClose, dsync, perms, read, sparse, sync, truncateExisting, write)"))
    @NotNull
    public static /* synthetic */ OpenOptions OpenOptions$default(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, String str, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 4) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 8) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 16) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i & 32) != 0) {
            str = (String) null;
        }
        if ((i & 64) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i & 128) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i & 256) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i & 512) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i & 1024) != 0) {
            bool10 = (Boolean) null;
        }
        return OpenOptions(bool, bool2, bool3, bool4, bool5, str, bool6, bool7, bool8, bool9, bool10);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "openOptionsOf(append, create, createNew, deleteOnClose, dsync, perms, read, sparse, sync, truncateExisting, write)"))
    @NotNull
    public static final OpenOptions OpenOptions(@Nullable Boolean append, @Nullable Boolean create, @Nullable Boolean createNew, @Nullable Boolean deleteOnClose, @Nullable Boolean dsync, @Nullable String perms, @Nullable Boolean read, @Nullable Boolean sparse, @Nullable Boolean sync, @Nullable Boolean truncateExisting, @Nullable Boolean write) {
        OpenOptions $this$apply = new OpenOptions();
        if (append != null) {
            $this$apply.setAppend(append.booleanValue());
        }
        if (create != null) {
            $this$apply.setCreate(create.booleanValue());
        }
        if (createNew != null) {
            $this$apply.setCreateNew(createNew.booleanValue());
        }
        if (deleteOnClose != null) {
            $this$apply.setDeleteOnClose(deleteOnClose.booleanValue());
        }
        if (dsync != null) {
            $this$apply.setDsync(dsync.booleanValue());
        }
        if (perms != null) {
            $this$apply.setPerms(perms);
        }
        if (read != null) {
            $this$apply.setRead(read.booleanValue());
        }
        if (sparse != null) {
            $this$apply.setSparse(sparse.booleanValue());
        }
        if (sync != null) {
            $this$apply.setSync(sync.booleanValue());
        }
        if (truncateExisting != null) {
            $this$apply.setTruncateExisting(truncateExisting.booleanValue());
        }
        if (write != null) {
            $this$apply.setWrite(write.booleanValue());
        }
        return $this$apply;
    }
}
