package io.vertx.kotlin.ext.web.api;

import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.api.OperationResponse;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: OperationResponse.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\"\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a=\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\n\u001a;\u0010\u000b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"OperationResponse", "Lio/vertx/ext/web/api/OperationResponse;", "headers", "Lio/vertx/core/MultiMap;", "payload", "Lio/vertx/core/buffer/Buffer;", "statusCode", "", "statusMessage", "", "(Lio/vertx/core/MultiMap;Lio/vertx/core/buffer/Buffer;Ljava/lang/Integer;Ljava/lang/String;)Lio/vertx/ext/web/api/OperationResponse;", "operationResponseOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/web/api/OperationResponseKt.class */
public final class OperationResponseKt {
    @NotNull
    public static /* synthetic */ OperationResponse operationResponseOf$default(MultiMap multiMap, Buffer buffer, Integer num, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            multiMap = (MultiMap) null;
        }
        if ((i & 2) != 0) {
            buffer = (Buffer) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        return operationResponseOf(multiMap, buffer, num, str);
    }

    @NotNull
    public static final OperationResponse operationResponseOf(@Nullable MultiMap headers, @Nullable Buffer payload, @Nullable Integer statusCode, @Nullable String statusMessage) {
        OperationResponse $this$apply = new OperationResponse();
        if (headers != null) {
            $this$apply.setHeaders(headers);
        }
        if (payload != null) {
            $this$apply.setPayload(payload);
        }
        if (statusCode != null) {
            $this$apply.setStatusCode(statusCode);
        }
        if (statusMessage != null) {
            $this$apply.setStatusMessage(statusMessage);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "operationResponseOf(headers, payload, statusCode, statusMessage)"))
    @NotNull
    public static /* synthetic */ OperationResponse OperationResponse$default(MultiMap multiMap, Buffer buffer, Integer num, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            multiMap = (MultiMap) null;
        }
        if ((i & 2) != 0) {
            buffer = (Buffer) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        return OperationResponse(multiMap, buffer, num, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "operationResponseOf(headers, payload, statusCode, statusMessage)"))
    @NotNull
    public static final OperationResponse OperationResponse(@Nullable MultiMap headers, @Nullable Buffer payload, @Nullable Integer statusCode, @Nullable String statusMessage) {
        OperationResponse $this$apply = new OperationResponse();
        if (headers != null) {
            $this$apply.setHeaders(headers);
        }
        if (payload != null) {
            $this$apply.setPayload(payload);
        }
        if (statusCode != null) {
            $this$apply.setStatusCode(statusCode);
        }
        if (statusMessage != null) {
            $this$apply.setStatusMessage(statusMessage);
        }
        return $this$apply;
    }
}
