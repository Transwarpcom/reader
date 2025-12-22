package io.vertx.kotlin.ext.mongo;

import io.vertx.ext.mongo.BulkWriteOptions;
import io.vertx.ext.mongo.WriteOption;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BulkWriteOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"BulkWriteOptions", "Lio/vertx/ext/mongo/BulkWriteOptions;", "ordered", "", "writeOption", "Lio/vertx/ext/mongo/WriteOption;", "(Ljava/lang/Boolean;Lio/vertx/ext/mongo/WriteOption;)Lio/vertx/ext/mongo/BulkWriteOptions;", "bulkWriteOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/mongo/BulkWriteOptionsKt.class */
public final class BulkWriteOptionsKt {
    @NotNull
    public static /* synthetic */ BulkWriteOptions bulkWriteOptionsOf$default(Boolean bool, WriteOption writeOption, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            writeOption = (WriteOption) null;
        }
        return bulkWriteOptionsOf(bool, writeOption);
    }

    @NotNull
    public static final BulkWriteOptions bulkWriteOptionsOf(@Nullable Boolean ordered, @Nullable WriteOption writeOption) {
        BulkWriteOptions $this$apply = new BulkWriteOptions();
        if (ordered != null) {
            $this$apply.setOrdered(ordered.booleanValue());
        }
        if (writeOption != null) {
            $this$apply.setWriteOption(writeOption);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bulkWriteOptionsOf(ordered, writeOption)"))
    @NotNull
    public static /* synthetic */ BulkWriteOptions BulkWriteOptions$default(Boolean bool, WriteOption writeOption, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            writeOption = (WriteOption) null;
        }
        return BulkWriteOptions(bool, writeOption);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bulkWriteOptionsOf(ordered, writeOption)"))
    @NotNull
    public static final BulkWriteOptions BulkWriteOptions(@Nullable Boolean ordered, @Nullable WriteOption writeOption) {
        BulkWriteOptions $this$apply = new BulkWriteOptions();
        if (ordered != null) {
            $this$apply.setOrdered(ordered.booleanValue());
        }
        if (writeOption != null) {
            $this$apply.setWriteOption(writeOption);
        }
        return $this$apply;
    }
}
