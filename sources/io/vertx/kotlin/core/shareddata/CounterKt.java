package io.vertx.kotlin.core.shareddata;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.shareddata.Counter;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Counter.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a%\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u001d\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u0015\u0010\f\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u0015\u0010\r\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u0015\u0010\u000e\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"addAndGetAwait", "", "Lio/vertx/core/shareddata/Counter;", "value", "(Lio/vertx/core/shareddata/Counter;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "compareAndSetAwait", "", "expected", "(Lio/vertx/core/shareddata/Counter;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decrementAndGetAwait", "(Lio/vertx/core/shareddata/Counter;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAndAddAwait", "getAndIncrementAwait", "getAwait", "incrementAndGetAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/shareddata/CounterKt.class */
public final class CounterKt {
    @Nullable
    public static final Object getAwait(@NotNull final Counter $this$getAwait, @NotNull Continuation<? super Long> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Long>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.CounterKt.getAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Long>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Long>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getAwait.get(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object incrementAndGetAwait(@NotNull final Counter $this$incrementAndGetAwait, @NotNull Continuation<? super Long> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Long>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.CounterKt.incrementAndGetAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Long>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Long>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$incrementAndGetAwait.incrementAndGet(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getAndIncrementAwait(@NotNull final Counter $this$getAndIncrementAwait, @NotNull Continuation<? super Long> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Long>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.CounterKt.getAndIncrementAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Long>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Long>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getAndIncrementAwait.getAndIncrement(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object decrementAndGetAwait(@NotNull final Counter $this$decrementAndGetAwait, @NotNull Continuation<? super Long> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Long>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.CounterKt.decrementAndGetAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Long>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Long>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$decrementAndGetAwait.decrementAndGet(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object addAndGetAwait(@NotNull final Counter $this$addAndGetAwait, final long value, @NotNull Continuation<? super Long> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Long>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.CounterKt.addAndGetAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Long>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Long>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$addAndGetAwait.addAndGet(value, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getAndAddAwait(@NotNull final Counter $this$getAndAddAwait, final long value, @NotNull Continuation<? super Long> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Long>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.CounterKt.getAndAddAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Long>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Long>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getAndAddAwait.getAndAdd(value, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object compareAndSetAwait(@NotNull final Counter $this$compareAndSetAwait, final long expected, final long value, @NotNull Continuation<? super Boolean> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Boolean>>, Unit>() { // from class: io.vertx.kotlin.core.shareddata.CounterKt.compareAndSetAwait.2
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
                $this$compareAndSetAwait.compareAndSet(expected, value, it);
            }
        }, continuation);
    }
}
