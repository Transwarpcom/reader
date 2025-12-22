package io.vertx.kotlin.ext.sql;

import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.UpdateResult;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: UpdateResult.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0003\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"UpdateResult", "Lio/vertx/ext/sql/UpdateResult;", "keys", "Lio/vertx/core/json/JsonArray;", "updated", "", "(Lio/vertx/core/json/JsonArray;Ljava/lang/Integer;)Lio/vertx/ext/sql/UpdateResult;", "updateResultOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/sql/UpdateResultKt.class */
public final class UpdateResultKt {
    @NotNull
    public static /* synthetic */ UpdateResult updateResultOf$default(JsonArray jsonArray, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonArray = (JsonArray) null;
        }
        if ((i & 2) != 0) {
            num = (Integer) null;
        }
        return updateResultOf(jsonArray, num);
    }

    @NotNull
    public static final UpdateResult updateResultOf(@Nullable JsonArray keys, @Nullable Integer updated) {
        UpdateResult $this$apply = new UpdateResult();
        if (keys != null) {
            $this$apply.setKeys(keys);
        }
        if (updated != null) {
            $this$apply.setUpdated(updated.intValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "updateResultOf(keys, updated)"))
    @NotNull
    public static /* synthetic */ UpdateResult UpdateResult$default(JsonArray jsonArray, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonArray = (JsonArray) null;
        }
        if ((i & 2) != 0) {
            num = (Integer) null;
        }
        return UpdateResult(jsonArray, num);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "updateResultOf(keys, updated)"))
    @NotNull
    public static final UpdateResult UpdateResult(@Nullable JsonArray keys, @Nullable Integer updated) {
        UpdateResult $this$apply = new UpdateResult();
        if (keys != null) {
            $this$apply.setKeys(keys);
        }
        if (updated != null) {
            $this$apply.setUpdated(updated.intValue());
        }
        return $this$apply;
    }
}
