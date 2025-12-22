package io.vertx.kotlin.core.http;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.core.streams.WriteStream;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.NCXDocumentV2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ServerWebSocket.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��D\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a'\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\t\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\r\u001a#\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a#\u0010\u0012\u001a\u00020\u0013*\u00020\u00022\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016\u001a\u001d\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\r\u001a\u001d\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\r\u001a\u001d\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\r\u001a\u001d\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u001c\u001a\u001d\u0010\u001d\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001fH\u0086@ø\u0001��¢\u0006\u0002\u0010 \u001a\u001d\u0010!\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u001c\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"closeAwait", "", "Lio/vertx/core/http/ServerWebSocket;", "(Lio/vertx/core/http/ServerWebSocket;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "statusCode", "", "(Lio/vertx/core/http/ServerWebSocket;SLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reason", "", "(Lio/vertx/core/http/ServerWebSocket;SLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "endAwait", "data", "Lio/vertx/core/buffer/Buffer;", "(Lio/vertx/core/http/ServerWebSocket;Lio/vertx/core/buffer/Buffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pipeToAwait", "dst", "Lio/vertx/core/streams/WriteStream;", "(Lio/vertx/core/http/ServerWebSocket;Lio/vertx/core/streams/WriteStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setHandshakeAwait", "", "future", "Lio/vertx/core/Future;", "(Lio/vertx/core/http/ServerWebSocket;Lio/vertx/core/Future;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeAwait", "writeBinaryMessageAwait", "writeFinalBinaryFrameAwait", "writeFinalTextFrameAwait", NCXDocumentV2.NCXTags.text, "(Lio/vertx/core/http/ServerWebSocket;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFrameAwait", "frame", "Lio/vertx/core/http/WebSocketFrame;", "(Lio/vertx/core/http/ServerWebSocket;Lio/vertx/core/http/WebSocketFrame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeTextMessageAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/http/ServerWebSocketKt.class */
public final class ServerWebSocketKt {
    @Nullable
    public static final Object endAwait(@NotNull final ServerWebSocket $this$endAwait, @NotNull final Buffer data, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.endAwait.2
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
                $this$endAwait.end(data, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.endAwait.2.1
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
    public static final Object pipeToAwait(@NotNull final ServerWebSocket $this$pipeToAwait, @NotNull final WriteStream<Buffer> writeStream, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.pipeToAwait.2
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
                $this$pipeToAwait.pipeTo(writeStream, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.pipeToAwait.2.1
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
    public static final Object endAwait(@NotNull final ServerWebSocket $this$endAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.endAwait.4
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
                $this$endAwait.end(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.endAwait.4.1
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
    public static final Object closeAwait(@NotNull final ServerWebSocket $this$closeAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.closeAwait.2
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
                $this$closeAwait.close(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.closeAwait.2.1
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
    public static final Object closeAwait(@NotNull final ServerWebSocket $this$closeAwait, final short statusCode, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.closeAwait.4
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
                $this$closeAwait.close(statusCode, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.closeAwait.4.1
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
    public static final Object closeAwait(@NotNull final ServerWebSocket $this$closeAwait, final short statusCode, @Nullable final String reason, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.closeAwait.6
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
                $this$closeAwait.close(statusCode, reason, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.closeAwait.6.1
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
    public static final Object writeAwait(@NotNull final ServerWebSocket $this$writeAwait, @NotNull final Buffer data, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.writeAwait.2
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
                $this$writeAwait.write(data, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.writeAwait.2.1
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
    public static final Object writeFrameAwait(@NotNull final ServerWebSocket $this$writeFrameAwait, @NotNull final WebSocketFrame frame, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.writeFrameAwait.2
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
                $this$writeFrameAwait.writeFrame(frame, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.writeFrameAwait.2.1
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
    public static final Object writeFinalTextFrameAwait(@NotNull final ServerWebSocket $this$writeFinalTextFrameAwait, @NotNull final String text, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.writeFinalTextFrameAwait.2
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
                $this$writeFinalTextFrameAwait.writeFinalTextFrame(text, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.writeFinalTextFrameAwait.2.1
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
    public static final Object writeFinalBinaryFrameAwait(@NotNull final ServerWebSocket $this$writeFinalBinaryFrameAwait, @NotNull final Buffer data, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.writeFinalBinaryFrameAwait.2
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
                $this$writeFinalBinaryFrameAwait.writeFinalBinaryFrame(data, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.writeFinalBinaryFrameAwait.2.1
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
    public static final Object writeBinaryMessageAwait(@NotNull final ServerWebSocket $this$writeBinaryMessageAwait, @NotNull final Buffer data, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.writeBinaryMessageAwait.2
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
                $this$writeBinaryMessageAwait.writeBinaryMessage(data, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.writeBinaryMessageAwait.2.1
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
    public static final Object writeTextMessageAwait(@NotNull final ServerWebSocket $this$writeTextMessageAwait, @NotNull final String text, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.writeTextMessageAwait.2
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
                $this$writeTextMessageAwait.writeTextMessage(text, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.writeTextMessageAwait.2.1
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
    public static final Object setHandshakeAwait(@NotNull final ServerWebSocket $this$setHandshakeAwait, @NotNull final Future<Integer> future, @NotNull Continuation<? super Integer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Integer>>, Unit>() { // from class: io.vertx.kotlin.core.http.ServerWebSocketKt.setHandshakeAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Integer>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Integer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$setHandshakeAwait.setHandshake(future, it);
            }
        }, continuation);
    }
}
