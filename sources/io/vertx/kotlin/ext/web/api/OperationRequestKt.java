package io.vertx.kotlin.ext.web.api;

import ch.qos.logback.classic.ClassicConstants;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: OperationRequest.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a8\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0007\u001a6\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¨\u0006\t"}, d2 = {"OperationRequest", "Lio/vertx/ext/web/api/OperationRequest;", "extra", "Lio/vertx/core/json/JsonObject;", "headers", "Lio/vertx/core/MultiMap;", "params", ClassicConstants.USER_MDC_KEY, "operationRequestOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/web/api/OperationRequestKt.class */
public final class OperationRequestKt {
    @NotNull
    public static /* synthetic */ OperationRequest operationRequestOf$default(JsonObject jsonObject, MultiMap multiMap, JsonObject jsonObject2, JsonObject jsonObject3, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            multiMap = (MultiMap) null;
        }
        if ((i & 4) != 0) {
            jsonObject2 = (JsonObject) null;
        }
        if ((i & 8) != 0) {
            jsonObject3 = (JsonObject) null;
        }
        return operationRequestOf(jsonObject, multiMap, jsonObject2, jsonObject3);
    }

    @NotNull
    public static final OperationRequest operationRequestOf(@Nullable JsonObject extra, @Nullable MultiMap headers, @Nullable JsonObject params, @Nullable JsonObject user) {
        OperationRequest $this$apply = new OperationRequest();
        if (extra != null) {
            $this$apply.setExtra(extra);
        }
        if (headers != null) {
            $this$apply.setHeaders(headers);
        }
        if (params != null) {
            $this$apply.setParams(params);
        }
        if (user != null) {
            $this$apply.setUser(user);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "operationRequestOf(extra, headers, params, user)"))
    @NotNull
    public static /* synthetic */ OperationRequest OperationRequest$default(JsonObject jsonObject, MultiMap multiMap, JsonObject jsonObject2, JsonObject jsonObject3, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            multiMap = (MultiMap) null;
        }
        if ((i & 4) != 0) {
            jsonObject2 = (JsonObject) null;
        }
        if ((i & 8) != 0) {
            jsonObject3 = (JsonObject) null;
        }
        return OperationRequest(jsonObject, multiMap, jsonObject2, jsonObject3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "operationRequestOf(extra, headers, params, user)"))
    @NotNull
    public static final OperationRequest OperationRequest(@Nullable JsonObject extra, @Nullable MultiMap headers, @Nullable JsonObject params, @Nullable JsonObject user) {
        OperationRequest $this$apply = new OperationRequest();
        if (extra != null) {
            $this$apply.setExtra(extra);
        }
        if (headers != null) {
            $this$apply.setHeaders(headers);
        }
        if (params != null) {
            $this$apply.setParams(params);
        }
        if (user != null) {
            $this$apply.setUser(user);
        }
        return $this$apply;
    }
}
