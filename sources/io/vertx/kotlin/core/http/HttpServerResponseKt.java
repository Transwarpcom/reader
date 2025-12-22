package io.vertx.kotlin.core.http;

import com.jayway.jsonpath.internal.function.text.Length;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: HttpServerResponse.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��4\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0006\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a%\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a%\u0010\u000b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u001a-\u0010\u000b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0012\u001a-\u0010\u000b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0014\u001a5\u0010\u000b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0015\u001a\u001d\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a%\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@ø\u0001��¢\u0006\u0002\u0010\u001a\u001a-\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0086@ø\u0001��¢\u0006\u0002\u0010\u001c\u001a\u001d\u0010\u001d\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a\u001d\u0010\u001d\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a%\u0010\u001d\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"endAwait", "", "Lio/vertx/core/http/HttpServerResponse;", "(Lio/vertx/core/http/HttpServerResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "chunk", "Lio/vertx/core/buffer/Buffer;", "(Lio/vertx/core/http/HttpServerResponse;Lio/vertx/core/buffer/Buffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Lio/vertx/core/http/HttpServerResponse;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "enc", "(Lio/vertx/core/http/HttpServerResponse;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pushAwait", "method", "Lio/vertx/core/http/HttpMethod;", "path", "(Lio/vertx/core/http/HttpServerResponse;Lio/vertx/core/http/HttpMethod;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "headers", "Lio/vertx/core/MultiMap;", "(Lio/vertx/core/http/HttpServerResponse;Lio/vertx/core/http/HttpMethod;Ljava/lang/String;Lio/vertx/core/MultiMap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "host", "(Lio/vertx/core/http/HttpServerResponse;Lio/vertx/core/http/HttpMethod;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/vertx/core/http/HttpServerResponse;Lio/vertx/core/http/HttpMethod;Ljava/lang/String;Ljava/lang/String;Lio/vertx/core/MultiMap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendFileAwait", "filename", "offset", "", "(Lio/vertx/core/http/HttpServerResponse;Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", Length.TOKEN_NAME, "(Lio/vertx/core/http/HttpServerResponse;Ljava/lang/String;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeAwait", "data", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/http/HttpServerResponseKt.class */
public final class HttpServerResponseKt {
    @Nullable
    public static final Object endAwait(@NotNull final HttpServerResponse $this$endAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.endAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$endAwait.end(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.endAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object writeAwait(@NotNull final HttpServerResponse $this$writeAwait, @NotNull final Buffer data, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.writeAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$writeAwait.write2(data, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.writeAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object writeAwait(@NotNull final HttpServerResponse $this$writeAwait, @NotNull final String chunk, @NotNull final String enc, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.writeAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$writeAwait.write(chunk, enc, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.writeAwait.4.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object writeAwait(@NotNull final HttpServerResponse $this$writeAwait, @NotNull final String chunk, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.writeAwait.6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$writeAwait.write(chunk, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.writeAwait.6.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object endAwait(@NotNull final HttpServerResponse $this$endAwait, @NotNull final String chunk, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.endAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$endAwait.end(chunk, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.endAwait.4.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object endAwait(@NotNull final HttpServerResponse $this$endAwait, @NotNull final String chunk, @NotNull final String enc, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.endAwait.6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$endAwait.end(chunk, enc, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.endAwait.6.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object endAwait(@NotNull final HttpServerResponse $this$endAwait, @NotNull final Buffer chunk, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.endAwait.8
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$endAwait.end2(chunk, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.endAwait.8.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object sendFileAwait(@NotNull final HttpServerResponse $this$sendFileAwait, @NotNull final String filename, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.sendFileAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$sendFileAwait.sendFile(filename, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.sendFileAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object sendFileAwait(@NotNull final HttpServerResponse $this$sendFileAwait, @NotNull final String filename, final long offset, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.sendFileAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$sendFileAwait.sendFile(filename, offset, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.sendFileAwait.4.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object sendFileAwait(@NotNull final HttpServerResponse $this$sendFileAwait, @NotNull final String filename, final long offset, final long length, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.sendFileAwait.6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$sendFileAwait.sendFile(filename, offset, length, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.sendFileAwait.6.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object pushAwait(@NotNull final HttpServerResponse $this$pushAwait, @NotNull final HttpMethod method, @NotNull final String host, @NotNull final String path, @NotNull Continuation<? super HttpServerResponse> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpServerResponse>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.pushAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<HttpServerResponse>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<HttpServerResponse>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$pushAwait.push(method, host, path, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object pushAwait(@NotNull final HttpServerResponse $this$pushAwait, @NotNull final HttpMethod method, @NotNull final String path, @NotNull final MultiMap headers, @NotNull Continuation<? super HttpServerResponse> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpServerResponse>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.pushAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<HttpServerResponse>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<HttpServerResponse>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$pushAwait.push(method, path, headers, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object pushAwait(@NotNull final HttpServerResponse $this$pushAwait, @NotNull final HttpMethod method, @NotNull final String path, @NotNull Continuation<? super HttpServerResponse> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpServerResponse>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.pushAwait.6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<HttpServerResponse>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<HttpServerResponse>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$pushAwait.push(method, path, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object pushAwait(@NotNull final HttpServerResponse $this$pushAwait, @NotNull final HttpMethod method, @NotNull final String host, @NotNull final String path, @NotNull final MultiMap headers, @NotNull Continuation<? super HttpServerResponse> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpServerResponse>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpServerResponseKt.pushAwait.8
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<HttpServerResponse>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<HttpServerResponse>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$pushAwait.push(method, host, path, headers, it);
            }
        }, continuation);
    }
}
