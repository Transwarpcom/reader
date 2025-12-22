package io.vertx.kotlin.ext.mongo;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.BulkOperation;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BulkOperation.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aI\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\n\u001aG\u0010\u000b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"BulkOperation", "Lio/vertx/ext/mongo/BulkOperation;", "document", "Lio/vertx/core/json/JsonObject;", "filter", "multi", "", "type", "Lio/vertx/ext/mongo/BulkOperation$BulkOperationType;", "upsert", "(Lio/vertx/core/json/JsonObject;Lio/vertx/core/json/JsonObject;Ljava/lang/Boolean;Lio/vertx/ext/mongo/BulkOperation$BulkOperationType;Ljava/lang/Boolean;)Lio/vertx/ext/mongo/BulkOperation;", "bulkOperationOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/mongo/BulkOperationKt.class */
public final class BulkOperationKt {
    @NotNull
    public static /* synthetic */ BulkOperation bulkOperationOf$default(JsonObject jsonObject, JsonObject jsonObject2, Boolean bool, BulkOperation.BulkOperationType bulkOperationType, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            jsonObject2 = (JsonObject) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            bulkOperationType = (BulkOperation.BulkOperationType) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        return bulkOperationOf(jsonObject, jsonObject2, bool, bulkOperationType, bool2);
    }

    @NotNull
    public static final BulkOperation bulkOperationOf(@Nullable JsonObject document, @Nullable JsonObject filter, @Nullable Boolean multi, @Nullable BulkOperation.BulkOperationType type, @Nullable Boolean upsert) {
        BulkOperation $this$apply = new BulkOperation(new JsonObject());
        if (document != null) {
            $this$apply.setDocument(document);
        }
        if (filter != null) {
            $this$apply.setFilter(filter);
        }
        if (multi != null) {
            $this$apply.setMulti(multi.booleanValue());
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        if (upsert != null) {
            $this$apply.setUpsert(upsert.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bulkOperationOf(document, filter, multi, type, upsert)"))
    @NotNull
    public static /* synthetic */ BulkOperation BulkOperation$default(JsonObject jsonObject, JsonObject jsonObject2, Boolean bool, BulkOperation.BulkOperationType bulkOperationType, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            jsonObject2 = (JsonObject) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            bulkOperationType = (BulkOperation.BulkOperationType) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        return BulkOperation(jsonObject, jsonObject2, bool, bulkOperationType, bool2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bulkOperationOf(document, filter, multi, type, upsert)"))
    @NotNull
    public static final BulkOperation BulkOperation(@Nullable JsonObject document, @Nullable JsonObject filter, @Nullable Boolean multi, @Nullable BulkOperation.BulkOperationType type, @Nullable Boolean upsert) {
        BulkOperation $this$apply = new BulkOperation(new JsonObject());
        if (document != null) {
            $this$apply.setDocument(document);
        }
        if (filter != null) {
            $this$apply.setFilter(filter);
        }
        if (multi != null) {
            $this$apply.setMulti(multi.booleanValue());
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        if (upsert != null) {
            $this$apply.setUpsert(upsert.booleanValue());
        }
        return $this$apply;
    }
}
