package io.vertx.kotlin.redis.sentinel;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import io.vertx.redis.sentinel.RedisSentinel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RedisSentinel.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001e\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u0015\u0010\t\u001a\u00020\u0006*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\n\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u001d\u0010\f\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u0015\u0010\r\u001a\u00020\u000b*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\u000e\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u001d\u0010\u0010\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u001d\u0010\u0011\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"ckquorumAwait", "", "Lio/vertx/redis/sentinel/RedisSentinel;", "name", "(Lio/vertx/redis/sentinel/RedisSentinel;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "closeAwait", "", "(Lio/vertx/redis/sentinel/RedisSentinel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "failoverAwait", "flushConfigAwait", "getMasterAddrByNameAwait", "Lio/vertx/core/json/JsonArray;", "masterAwait", "mastersAwait", "resetAwait", "pattern", "sentinelsAwait", "slavesAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/sentinel/RedisSentinelKt.class */
public final class RedisSentinelKt {
    @Nullable
    public static final Object closeAwait(@NotNull final RedisSentinel $this$closeAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.redis.sentinel.RedisSentinelKt.closeAwait.2
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
                $this$closeAwait.close(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.redis.sentinel.RedisSentinelKt.closeAwait.2.1
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
    public static final Object mastersAwait(@NotNull final RedisSentinel $this$mastersAwait, @NotNull Continuation<? super JsonArray> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonArray>>, Unit>() { // from class: io.vertx.kotlin.redis.sentinel.RedisSentinelKt.mastersAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonArray>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonArray>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$mastersAwait.masters(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object masterAwait(@NotNull final RedisSentinel $this$masterAwait, @NotNull final String name, @NotNull Continuation<? super JsonArray> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonArray>>, Unit>() { // from class: io.vertx.kotlin.redis.sentinel.RedisSentinelKt.masterAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonArray>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonArray>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$masterAwait.master(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object slavesAwait(@NotNull final RedisSentinel $this$slavesAwait, @NotNull final String name, @NotNull Continuation<? super JsonArray> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonArray>>, Unit>() { // from class: io.vertx.kotlin.redis.sentinel.RedisSentinelKt.slavesAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonArray>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonArray>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$slavesAwait.slaves(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object sentinelsAwait(@NotNull final RedisSentinel $this$sentinelsAwait, @NotNull final String name, @NotNull Continuation<? super JsonArray> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonArray>>, Unit>() { // from class: io.vertx.kotlin.redis.sentinel.RedisSentinelKt.sentinelsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonArray>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonArray>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$sentinelsAwait.sentinels(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getMasterAddrByNameAwait(@NotNull final RedisSentinel $this$getMasterAddrByNameAwait, @NotNull final String name, @NotNull Continuation<? super JsonArray> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonArray>>, Unit>() { // from class: io.vertx.kotlin.redis.sentinel.RedisSentinelKt.getMasterAddrByNameAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonArray>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonArray>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getMasterAddrByNameAwait.getMasterAddrByName(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object resetAwait(@NotNull final RedisSentinel $this$resetAwait, @NotNull final String pattern, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.redis.sentinel.RedisSentinelKt.resetAwait.2
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
                $this$resetAwait.reset(pattern, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.redis.sentinel.RedisSentinelKt.resetAwait.2.1
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
    public static final Object failoverAwait(@NotNull final RedisSentinel $this$failoverAwait, @NotNull final String name, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.redis.sentinel.RedisSentinelKt.failoverAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$failoverAwait.failover(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object ckquorumAwait(@NotNull final RedisSentinel $this$ckquorumAwait, @NotNull final String name, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.redis.sentinel.RedisSentinelKt.ckquorumAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$ckquorumAwait.ckquorum(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object flushConfigAwait(@NotNull final RedisSentinel $this$flushConfigAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.redis.sentinel.RedisSentinelKt.flushConfigAwait.2
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
                $this$flushConfigAwait.flushConfig(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.redis.sentinel.RedisSentinelKt.flushConfigAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }
}
