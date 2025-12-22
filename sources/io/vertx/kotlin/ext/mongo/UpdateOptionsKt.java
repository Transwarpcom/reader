package io.vertx.kotlin.ext.mongo;

import io.vertx.ext.mongo.UpdateOptions;
import io.vertx.ext.mongo.WriteOption;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: UpdateOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a=\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\b\u001a;\u0010\t\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"UpdateOptions", "Lio/vertx/ext/mongo/UpdateOptions;", "multi", "", "returningNewDocument", "upsert", "writeOption", "Lio/vertx/ext/mongo/WriteOption;", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lio/vertx/ext/mongo/WriteOption;)Lio/vertx/ext/mongo/UpdateOptions;", "updateOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/mongo/UpdateOptionsKt.class */
public final class UpdateOptionsKt {
    @NotNull
    public static /* synthetic */ UpdateOptions updateOptionsOf$default(Boolean bool, Boolean bool2, Boolean bool3, WriteOption writeOption, int i, Object obj) {
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
            writeOption = (WriteOption) null;
        }
        return updateOptionsOf(bool, bool2, bool3, writeOption);
    }

    @NotNull
    public static final UpdateOptions updateOptionsOf(@Nullable Boolean multi, @Nullable Boolean returningNewDocument, @Nullable Boolean upsert, @Nullable WriteOption writeOption) {
        UpdateOptions $this$apply = new UpdateOptions();
        if (multi != null) {
            $this$apply.setMulti(multi.booleanValue());
        }
        if (returningNewDocument != null) {
            $this$apply.setReturningNewDocument(returningNewDocument.booleanValue());
        }
        if (upsert != null) {
            $this$apply.setUpsert(upsert.booleanValue());
        }
        if (writeOption != null) {
            $this$apply.setWriteOption(writeOption);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "updateOptionsOf(multi, returningNewDocument, upsert, writeOption)"))
    @NotNull
    public static /* synthetic */ UpdateOptions UpdateOptions$default(Boolean bool, Boolean bool2, Boolean bool3, WriteOption writeOption, int i, Object obj) {
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
            writeOption = (WriteOption) null;
        }
        return UpdateOptions(bool, bool2, bool3, writeOption);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "updateOptionsOf(multi, returningNewDocument, upsert, writeOption)"))
    @NotNull
    public static final UpdateOptions UpdateOptions(@Nullable Boolean multi, @Nullable Boolean returningNewDocument, @Nullable Boolean upsert, @Nullable WriteOption writeOption) {
        UpdateOptions $this$apply = new UpdateOptions();
        if (multi != null) {
            $this$apply.setMulti(multi.booleanValue());
        }
        if (returningNewDocument != null) {
            $this$apply.setReturningNewDocument(returningNewDocument.booleanValue());
        }
        if (upsert != null) {
            $this$apply.setUpsert(upsert.booleanValue());
        }
        if (writeOption != null) {
            $this$apply.setWriteOption(writeOption);
        }
        return $this$apply;
    }
}
