package io.vertx.kotlin.core.shareddata;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.Counter;
import io.vertx.core.shareddata.Lock;
import io.vertx.core.shareddata.SharedData;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SharedData.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��0\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\u001a5\u0010��\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a5\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\t\u001a\u00020\n*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a5\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\f\u001a\u00020\n*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\r\u001a\u00020\u000e*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a%\u0010\u000f\u001a\u00020\u000e*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0012\u001a\u001d\u0010\u0013\u001a\u00020\u000e*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a%\u0010\u0014\u001a\u00020\u000e*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"getAsyncMapAwait", "Lio/vertx/core/shareddata/AsyncMap;", OperatorName.STROKING_COLOR_CMYK, "V", "Lio/vertx/core/shareddata/SharedData;", "name", "", "(Lio/vertx/core/shareddata/SharedData;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getClusterWideMapAwait", "getCounterAwait", "Lio/vertx/core/shareddata/Counter;", "getLocalAsyncMapAwait", "getLocalCounterAwait", "getLocalLockAwait", "Lio/vertx/core/shareddata/Lock;", "getLocalLockWithTimeoutAwait", RtspHeaders.Values.TIMEOUT, "", "(Lio/vertx/core/shareddata/SharedData;Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLockAwait", "getLockWithTimeoutAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/shareddata/SharedDataKt.class */
public final class SharedDataKt {
    @Nullable
    public static final <K, V> Object getClusterWideMapAwait(@NotNull final SharedData $this$getClusterWideMapAwait, @NotNull final String name, @NotNull Continuation<? super AsyncMap<K, V>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<AsyncMap<K, V>>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.SharedDataKt.getClusterWideMapAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<AsyncMap<K, V>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getClusterWideMapAwait.getClusterWideMap(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object getAsyncMapAwait(@NotNull final SharedData $this$getAsyncMapAwait, @NotNull final String name, @NotNull Continuation<? super AsyncMap<K, V>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<AsyncMap<K, V>>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.SharedDataKt.getAsyncMapAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<AsyncMap<K, V>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getAsyncMapAwait.getAsyncMap(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object getLocalAsyncMapAwait(@NotNull final SharedData $this$getLocalAsyncMapAwait, @NotNull final String name, @NotNull Continuation<? super AsyncMap<K, V>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<AsyncMap<K, V>>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.SharedDataKt.getLocalAsyncMapAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<AsyncMap<K, V>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getLocalAsyncMapAwait.getLocalAsyncMap(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getLockAwait(@NotNull final SharedData $this$getLockAwait, @NotNull final String name, @NotNull Continuation<? super Lock> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Lock>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.SharedDataKt.getLockAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Lock>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Lock>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getLockAwait.getLock(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getLockWithTimeoutAwait(@NotNull final SharedData $this$getLockWithTimeoutAwait, @NotNull final String name, final long timeout, @NotNull Continuation<? super Lock> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Lock>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.SharedDataKt.getLockWithTimeoutAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Lock>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Lock>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getLockWithTimeoutAwait.getLockWithTimeout(name, timeout, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getLocalLockAwait(@NotNull final SharedData $this$getLocalLockAwait, @NotNull final String name, @NotNull Continuation<? super Lock> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Lock>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.SharedDataKt.getLocalLockAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Lock>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Lock>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getLocalLockAwait.getLocalLock(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getLocalLockWithTimeoutAwait(@NotNull final SharedData $this$getLocalLockWithTimeoutAwait, @NotNull final String name, final long timeout, @NotNull Continuation<? super Lock> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Lock>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.SharedDataKt.getLocalLockWithTimeoutAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Lock>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Lock>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getLocalLockWithTimeoutAwait.getLocalLockWithTimeout(name, timeout, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getCounterAwait(@NotNull final SharedData $this$getCounterAwait, @NotNull final String name, @NotNull Continuation<? super Counter> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Counter>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.SharedDataKt.getCounterAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Counter>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Counter>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getCounterAwait.getCounter(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getLocalCounterAwait(@NotNull final SharedData $this$getLocalCounterAwait, @NotNull final String name, @NotNull Continuation<? super Counter> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Counter>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.SharedDataKt.getLocalCounterAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Counter>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Counter>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getLocalCounterAwait.getLocalCounter(name, it);
            }
        }, continuation);
    }
}
