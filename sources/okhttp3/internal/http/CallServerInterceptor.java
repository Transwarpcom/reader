package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okio.BufferedSink;
import okio.Okio;
import org.jetbrains.annotations.NotNull;

/* compiled from: CallServerInterceptor.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��¨\u0006\t"}, d2 = {"Lokhttp3/internal/http/CallServerInterceptor;", "Lokhttp3/Interceptor;", "forWebSocket", "", "(Z)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/internal/http/CallServerInterceptor.class */
public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean forWebSocket) {
        this.forWebSocket = forWebSocket;
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        Response responseBuild;
        Intrinsics.checkNotNullParameter(chain, "chain");
        RealInterceptorChain realChain = (RealInterceptorChain) chain;
        Exchange exchange = realChain.getExchange$okhttp();
        Intrinsics.checkNotNull(exchange);
        Request request = realChain.getRequest$okhttp();
        RequestBody requestBody = request.body();
        long sentRequestMillis = System.currentTimeMillis();
        exchange.writeRequestHeaders(request);
        boolean invokeStartEvent = true;
        Response.Builder responseBuilder = (Response.Builder) null;
        if (HttpMethod.permitsRequestBody(request.method()) && requestBody != null) {
            if (StringsKt.equals("100-continue", request.header("Expect"), true)) {
                exchange.flushRequest();
                responseBuilder = exchange.readResponseHeaders(true);
                exchange.responseHeadersStart();
                invokeStartEvent = false;
            }
            if (responseBuilder == null) {
                if (requestBody.isDuplex()) {
                    exchange.flushRequest();
                    requestBody.writeTo(Okio.buffer(exchange.createRequestBody(request, true)));
                } else {
                    BufferedSink bufferedRequestBody = Okio.buffer(exchange.createRequestBody(request, false));
                    requestBody.writeTo(bufferedRequestBody);
                    bufferedRequestBody.close();
                }
            } else {
                exchange.noRequestBody();
                if (!exchange.getConnection$okhttp().isMultiplexed$okhttp()) {
                    exchange.noNewExchangesOnConnection();
                }
            }
        } else {
            exchange.noRequestBody();
        }
        if (requestBody == null || !requestBody.isDuplex()) {
            exchange.finishRequest();
        }
        if (responseBuilder == null) {
            Response.Builder responseHeaders = exchange.readResponseHeaders(false);
            Intrinsics.checkNotNull(responseHeaders);
            responseBuilder = responseHeaders;
            if (invokeStartEvent) {
                exchange.responseHeadersStart();
                invokeStartEvent = false;
            }
        }
        Response response = responseBuilder.request(request).handshake(exchange.getConnection$okhttp().handshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
        int code = response.code();
        if (code == 100) {
            Response.Builder responseBuilder2 = exchange.readResponseHeaders(false);
            Intrinsics.checkNotNull(responseBuilder2);
            if (invokeStartEvent) {
                exchange.responseHeadersStart();
            }
            response = responseBuilder2.request(request).handshake(exchange.getConnection$okhttp().handshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
            code = response.code();
        }
        exchange.responseHeadersEnd(response);
        if (this.forWebSocket && code == 101) {
            responseBuild = response.newBuilder().body(Util.EMPTY_RESPONSE).build();
        } else {
            responseBuild = response.newBuilder().body(exchange.openResponseBody(response)).build();
        }
        Response response2 = responseBuild;
        if (StringsKt.equals("close", response2.request().header("Connection"), true) || StringsKt.equals("close", Response.header$default(response2, "Connection", null, 2, null), true)) {
            exchange.noNewExchangesOnConnection();
        }
        if (code == 204 || code == 205) {
            ResponseBody responseBodyBody = response2.body();
            if ((responseBodyBody != null ? responseBodyBody.contentLength() : -1L) > 0) {
                StringBuilder sbAppend = new StringBuilder().append("HTTP ").append(code).append(" had non-zero Content-Length: ");
                ResponseBody responseBodyBody2 = response2.body();
                throw new ProtocolException(sbAppend.append(responseBodyBody2 != null ? Long.valueOf(responseBodyBody2.contentLength()) : null).toString());
            }
        }
        return response2;
    }
}
