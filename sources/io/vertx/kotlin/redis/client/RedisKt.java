package io.vertx.kotlin.redis.client;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.streams.WriteStream;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.Request;
import io.vertx.redis.client.Response;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Redis.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��&\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a+\u0010��\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a\u0015\u0010\u0007\u001a\u00020\u0003*\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a#\u0010\t\u001a\u00020\n*\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\r\u001a\u001f\u0010\u000e\u001a\u0004\u0018\u00010\u0002*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"batchAwait", "", "Lio/vertx/redis/client/Response;", "Lio/vertx/redis/client/Redis;", "commands", "Lio/vertx/redis/client/Request;", "(Lio/vertx/redis/client/Redis;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "connectAwait", "(Lio/vertx/redis/client/Redis;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pipeToAwait", "", "dst", "Lio/vertx/core/streams/WriteStream;", "(Lio/vertx/redis/client/Redis;Lio/vertx/core/streams/WriteStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendAwait", "command", "(Lio/vertx/redis/client/Redis;Lio/vertx/redis/client/Request;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/client/RedisKt.class */
public final class RedisKt {
    @Nullable
    public static final Object pipeToAwait(@NotNull final Redis $this$pipeToAwait, @NotNull final WriteStream<Response> writeStream, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.redis.client.RedisKt.pipeToAwait.2
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
                $this$pipeToAwait.pipeTo(writeStream, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.redis.client.RedisKt.pipeToAwait.2.1
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
    public static final Object connectAwait(@NotNull final Redis $this$connectAwait, @NotNull Continuation<? super Redis> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Redis>>, Unit>() { // from class: io.vertx.kotlin.redis.client.RedisKt.connectAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Redis>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Redis>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$connectAwait.connect(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object sendAwait(@NotNull final Redis $this$sendAwait, @NotNull final Request command, @NotNull Continuation<? super Response> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Response>>, Unit>() { // from class: io.vertx.kotlin.redis.client.RedisKt.sendAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Response>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Response>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$sendAwait.send(command, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object batchAwait(@NotNull final Redis $this$batchAwait, @NotNull final List<? extends Request> list, @NotNull Continuation<? super List<? extends Response>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends Response>>>, Unit>() { // from class: io.vertx.kotlin.redis.client.RedisKt.batchAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends Response>>> handler) {
                invoke2((Handler<AsyncResult<List<Response>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<Response>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$batchAwait.batch(list, it);
            }
        }, continuation);
    }
}
