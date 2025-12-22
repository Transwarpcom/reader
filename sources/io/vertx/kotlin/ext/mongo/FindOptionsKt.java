package io.vertx.kotlin.ext.mongo;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: FindOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aI\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\t\u001aG\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"FindOptions", "Lio/vertx/ext/mongo/FindOptions;", "batchSize", "", "fields", "Lio/vertx/core/json/JsonObject;", "limit", "skip", "sort", "(Ljava/lang/Integer;Lio/vertx/core/json/JsonObject;Ljava/lang/Integer;Ljava/lang/Integer;Lio/vertx/core/json/JsonObject;)Lio/vertx/ext/mongo/FindOptions;", "findOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/mongo/FindOptionsKt.class */
public final class FindOptionsKt {
    @NotNull
    public static /* synthetic */ FindOptions findOptionsOf$default(Integer num, JsonObject jsonObject, Integer num2, Integer num3, JsonObject jsonObject2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 4) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 8) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 16) != 0) {
            jsonObject2 = (JsonObject) null;
        }
        return findOptionsOf(num, jsonObject, num2, num3, jsonObject2);
    }

    @NotNull
    public static final FindOptions findOptionsOf(@Nullable Integer batchSize, @Nullable JsonObject fields, @Nullable Integer limit, @Nullable Integer skip, @Nullable JsonObject sort) {
        FindOptions $this$apply = new FindOptions();
        if (batchSize != null) {
            $this$apply.setBatchSize(batchSize.intValue());
        }
        if (fields != null) {
            $this$apply.setFields(fields);
        }
        if (limit != null) {
            $this$apply.setLimit(limit.intValue());
        }
        if (skip != null) {
            $this$apply.setSkip(skip.intValue());
        }
        if (sort != null) {
            $this$apply.setSort(sort);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "findOptionsOf(batchSize, fields, limit, skip, sort)"))
    @NotNull
    public static /* synthetic */ FindOptions FindOptions$default(Integer num, JsonObject jsonObject, Integer num2, Integer num3, JsonObject jsonObject2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 4) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 8) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 16) != 0) {
            jsonObject2 = (JsonObject) null;
        }
        return FindOptions(num, jsonObject, num2, num3, jsonObject2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "findOptionsOf(batchSize, fields, limit, skip, sort)"))
    @NotNull
    public static final FindOptions FindOptions(@Nullable Integer batchSize, @Nullable JsonObject fields, @Nullable Integer limit, @Nullable Integer skip, @Nullable JsonObject sort) {
        FindOptions $this$apply = new FindOptions();
        if (batchSize != null) {
            $this$apply.setBatchSize(batchSize.intValue());
        }
        if (fields != null) {
            $this$apply.setFields(fields);
        }
        if (limit != null) {
            $this$apply.setLimit(limit.intValue());
        }
        if (skip != null) {
            $this$apply.setSkip(skip.intValue());
        }
        if (sort != null) {
            $this$apply.setSort(sort);
        }
        return $this$apply;
    }
}
