package io.vertx.kotlin.core.shareddata;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AsyncMap.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��(\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n��\u001a-\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u001a7\u0010\u0006\u001a\u0004\u0018\u0001H\u0003\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0007\u001a\u0002H\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a=\u0010\t\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0007\u001a\u0002H\u00022\u0006\u0010\n\u001a\u0002H\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001aE\u0010\t\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0007\u001a\u0002H\u00022\u0006\u0010\n\u001a\u0002H\u00032\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u001a?\u0010\u000f\u001a\u0004\u0018\u0001H\u0003\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0007\u001a\u0002H\u00022\u0006\u0010\n\u001a\u0002H\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001aG\u0010\u000f\u001a\u0004\u0018\u0001H\u0003\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0007\u001a\u0002H\u00022\u0006\u0010\n\u001a\u0002H\u00032\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u001a7\u0010\u0010\u001a\u0004\u0018\u0001H\u0003\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0007\u001a\u0002H\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a=\u0010\u0011\u001a\u00020\u0012\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0007\u001a\u0002H\u00022\u0006\u0010\n\u001a\u0002H\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001a?\u0010\u0013\u001a\u0004\u0018\u0001H\u0003\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0007\u001a\u0002H\u00022\u0006\u0010\n\u001a\u0002H\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001aE\u0010\u0014\u001a\u00020\u0012\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0007\u001a\u0002H\u00022\u0006\u0010\u0015\u001a\u0002H\u00032\u0006\u0010\u0016\u001a\u0002H\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0017\u001a-\u0010\u0018\u001a\u00020\u0019\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"clearAwait", "", OperatorName.STROKING_COLOR_CMYK, "V", "Lio/vertx/core/shareddata/AsyncMap;", "(Lio/vertx/core/shareddata/AsyncMap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAwait", OperatorName.NON_STROKING_CMYK, "(Lio/vertx/core/shareddata/AsyncMap;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "putAwait", OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT, "(Lio/vertx/core/shareddata/AsyncMap;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", RtspHeaders.Values.TTL, "", "(Lio/vertx/core/shareddata/AsyncMap;Ljava/lang/Object;Ljava/lang/Object;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "putIfAbsentAwait", "removeAwait", "removeIfPresentAwait", "", "replaceAwait", "replaceIfPresentAwait", "oldValue", "newValue", "(Lio/vertx/core/shareddata/AsyncMap;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sizeAwait", "", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/shareddata/AsyncMapKt.class */
public final class AsyncMapKt {
    @Nullable
    public static final <K, V> Object getAwait(@NotNull final AsyncMap<K, V> asyncMap, final K k, @NotNull Continuation<? super V> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<V>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.AsyncMapKt.getAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<V>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                asyncMap.get(k, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object putAwait(@NotNull final AsyncMap<K, V> asyncMap, final K k, final V v, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.AsyncMapKt.putAwait.2
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
                asyncMap.put(k, v, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.shareddata.AsyncMapKt.putAwait.2.1
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
    public static final <K, V> Object putAwait(@NotNull final AsyncMap<K, V> asyncMap, final K k, final V v, final long ttl, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.AsyncMapKt.putAwait.4
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
                asyncMap.put(k, v, ttl, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.shareddata.AsyncMapKt.putAwait.4.1
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
    public static final <K, V> Object putIfAbsentAwait(@NotNull final AsyncMap<K, V> asyncMap, final K k, final V v, @NotNull Continuation<? super V> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<V>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.AsyncMapKt.putIfAbsentAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void invoke(@NotNull Handler<AsyncResult<V>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                asyncMap.putIfAbsent(k, v, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object putIfAbsentAwait(@NotNull final AsyncMap<K, V> asyncMap, final K k, final V v, final long ttl, @NotNull Continuation<? super V> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<V>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.AsyncMapKt.putIfAbsentAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void invoke(@NotNull Handler<AsyncResult<V>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                asyncMap.putIfAbsent(k, v, ttl, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object removeAwait(@NotNull final AsyncMap<K, V> asyncMap, final K k, @NotNull Continuation<? super V> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<V>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.AsyncMapKt.removeAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<V>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                asyncMap.remove(k, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object removeIfPresentAwait(@NotNull final AsyncMap<K, V> asyncMap, final K k, final V v, @NotNull Continuation<? super Boolean> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Boolean>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.AsyncMapKt.removeIfPresentAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Boolean>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Boolean>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                asyncMap.removeIfPresent(k, v, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object replaceAwait(@NotNull final AsyncMap<K, V> asyncMap, final K k, final V v, @NotNull Continuation<? super V> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<V>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.AsyncMapKt.replaceAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void invoke(@NotNull Handler<AsyncResult<V>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                asyncMap.replace(k, v, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object replaceIfPresentAwait(@NotNull final AsyncMap<K, V> asyncMap, final K k, final V v, final V v2, @NotNull Continuation<? super Boolean> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Boolean>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.AsyncMapKt.replaceIfPresentAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Boolean>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Boolean>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                asyncMap.replaceIfPresent(k, v, v2, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object clearAwait(@NotNull final AsyncMap<K, V> asyncMap, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.AsyncMapKt.clearAwait.2
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
                asyncMap.clear(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.shareddata.AsyncMapKt.clearAwait.2.1
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
    public static final <K, V> Object sizeAwait(@NotNull final AsyncMap<K, V> asyncMap, @NotNull Continuation<? super Integer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Integer>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.AsyncMapKt.sizeAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Integer>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Integer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                asyncMap.size(it);
            }
        }, continuation);
    }
}
