package io.vertx.kotlin.core.file;

import io.vertx.core.file.CopyOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CopyOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0006\u001a=\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0007\u001a;\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"CopyOptions", "Lio/vertx/core/file/CopyOptions;", "atomicMove", "", "copyAttributes", "nofollowLinks", "replaceExisting", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lio/vertx/core/file/CopyOptions;", "copyOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/file/CopyOptionsKt.class */
public final class CopyOptionsKt {
    @NotNull
    public static /* synthetic */ CopyOptions copyOptionsOf$default(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, int i, Object obj) {
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
        return copyOptionsOf(bool, bool2, bool3, bool4);
    }

    @NotNull
    public static final CopyOptions copyOptionsOf(@Nullable Boolean atomicMove, @Nullable Boolean copyAttributes, @Nullable Boolean nofollowLinks, @Nullable Boolean replaceExisting) {
        CopyOptions $this$apply = new CopyOptions();
        if (atomicMove != null) {
            $this$apply.setAtomicMove(atomicMove.booleanValue());
        }
        if (copyAttributes != null) {
            $this$apply.setCopyAttributes(copyAttributes.booleanValue());
        }
        if (nofollowLinks != null) {
            $this$apply.setNofollowLinks(nofollowLinks.booleanValue());
        }
        if (replaceExisting != null) {
            $this$apply.setReplaceExisting(replaceExisting.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "copyOptionsOf(atomicMove, copyAttributes, nofollowLinks, replaceExisting)"))
    @NotNull
    public static /* synthetic */ CopyOptions CopyOptions$default(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, int i, Object obj) {
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
        return CopyOptions(bool, bool2, bool3, bool4);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "copyOptionsOf(atomicMove, copyAttributes, nofollowLinks, replaceExisting)"))
    @NotNull
    public static final CopyOptions CopyOptions(@Nullable Boolean atomicMove, @Nullable Boolean copyAttributes, @Nullable Boolean nofollowLinks, @Nullable Boolean replaceExisting) {
        CopyOptions $this$apply = new CopyOptions();
        if (atomicMove != null) {
            $this$apply.setAtomicMove(atomicMove.booleanValue());
        }
        if (copyAttributes != null) {
            $this$apply.setCopyAttributes(copyAttributes.booleanValue());
        }
        if (nofollowLinks != null) {
            $this$apply.setNofollowLinks(nofollowLinks.booleanValue());
        }
        if (replaceExisting != null) {
            $this$apply.setReplaceExisting(replaceExisting.booleanValue());
        }
        return $this$apply;
    }
}
