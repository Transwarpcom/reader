package io.vertx.kotlin.ext.web.client;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.streams.ReadStream;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.multipart.MultipartForm;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: HttpRequest.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��@\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a'\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a/\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a/\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0006\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001a1\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u001a/\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a/\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0013H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0014\u001a5\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0016H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"sendAwait", "Lio/vertx/ext/web/client/HttpResponse;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lio/vertx/ext/web/client/HttpRequest;", "(Lio/vertx/ext/web/client/HttpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendBufferAwait", NCXDocumentV3.XHTMLTgs.body, "Lio/vertx/core/buffer/Buffer;", "(Lio/vertx/ext/web/client/HttpRequest;Lio/vertx/core/buffer/Buffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendFormAwait", "Lio/vertx/core/MultiMap;", "(Lio/vertx/ext/web/client/HttpRequest;Lio/vertx/core/MultiMap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendJsonAwait", "", "(Lio/vertx/ext/web/client/HttpRequest;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendJsonObjectAwait", "Lio/vertx/core/json/JsonObject;", "(Lio/vertx/ext/web/client/HttpRequest;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendMultipartFormAwait", "Lio/vertx/ext/web/multipart/MultipartForm;", "(Lio/vertx/ext/web/client/HttpRequest;Lio/vertx/ext/web/multipart/MultipartForm;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendStreamAwait", "Lio/vertx/core/streams/ReadStream;", "(Lio/vertx/ext/web/client/HttpRequest;Lio/vertx/core/streams/ReadStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/web/client/HttpRequestKt.class */
public final class HttpRequestKt {
    @Nullable
    public static final <T> Object sendStreamAwait(@NotNull final HttpRequest<T> httpRequest, @NotNull final ReadStream<Buffer> readStream, @NotNull Continuation<? super HttpResponse<T>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpResponse<T>>>, Unit>() { // from class: io.vertx.kotlin.ext.web.client.HttpRequestKt.sendStreamAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<HttpResponse<T>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                httpRequest.sendStream(readStream, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <T> Object sendBufferAwait(@NotNull final HttpRequest<T> httpRequest, @NotNull final Buffer body, @NotNull Continuation<? super HttpResponse<T>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpResponse<T>>>, Unit>() { // from class: io.vertx.kotlin.ext.web.client.HttpRequestKt.sendBufferAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<HttpResponse<T>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                httpRequest.sendBuffer(body, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <T> Object sendJsonObjectAwait(@NotNull final HttpRequest<T> httpRequest, @NotNull final JsonObject body, @NotNull Continuation<? super HttpResponse<T>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpResponse<T>>>, Unit>() { // from class: io.vertx.kotlin.ext.web.client.HttpRequestKt.sendJsonObjectAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<HttpResponse<T>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                httpRequest.sendJsonObject(body, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <T> Object sendJsonAwait(@NotNull final HttpRequest<T> httpRequest, @Nullable final Object body, @NotNull Continuation<? super HttpResponse<T>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpResponse<T>>>, Unit>() { // from class: io.vertx.kotlin.ext.web.client.HttpRequestKt.sendJsonAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<HttpResponse<T>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                httpRequest.sendJson(body, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <T> Object sendFormAwait(@NotNull final HttpRequest<T> httpRequest, @NotNull final MultiMap body, @NotNull Continuation<? super HttpResponse<T>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpResponse<T>>>, Unit>() { // from class: io.vertx.kotlin.ext.web.client.HttpRequestKt.sendFormAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<HttpResponse<T>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                httpRequest.sendForm(body, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <T> Object sendMultipartFormAwait(@NotNull final HttpRequest<T> httpRequest, @NotNull final MultipartForm body, @NotNull Continuation<? super HttpResponse<T>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpResponse<T>>>, Unit>() { // from class: io.vertx.kotlin.ext.web.client.HttpRequestKt.sendMultipartFormAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<HttpResponse<T>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                httpRequest.sendMultipartForm(body, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <T> Object sendAwait(@NotNull final HttpRequest<T> httpRequest, @NotNull Continuation<? super HttpResponse<T>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpResponse<T>>>, Unit>() { // from class: io.vertx.kotlin.ext.web.client.HttpRequestKt.sendAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<HttpResponse<T>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                httpRequest.send(it);
            }
        }, continuation);
    }
}
