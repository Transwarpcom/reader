package io.vertx.kotlin.coroutines;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: VertxCoroutine.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��@\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\u001a%\u0010��\u001a\u0002H\u0001\"\u0004\b��\u0010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a@\u0010\u0005\u001a\u0002H\u0001\"\u0004\b��\u0010\u00012'\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\u00010\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001aF\u0010\r\u001a\u0002H\u0001\"\u0004\b��\u0010\u00012-\u0010\u0002\u001a)\u0012\u001f\u0012\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u000e0\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a!\u0010\u000f\u001a\u0002H\u0001\"\u0004\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a\n\u0010\u0012\u001a\u00020\u0013*\u00020\u0014\u001a\n\u0010\u0012\u001a\u00020\u0013*\u00020\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"awaitBlocking", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitEvent", "Lkotlin/Function1;", "Lio/vertx/core/Handler;", "Lkotlin/ParameterName;", "name", OperatorName.CLOSE_PATH, "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitResult", "Lio/vertx/core/AsyncResult;", "await", "Lio/vertx/core/Future;", "(Lio/vertx/core/Future;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lio/vertx/core/Context;", "Lio/vertx/core/Vertx;", "vertx-lang-kotlin-coroutines"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-coroutines-3.8.5.jar:io/vertx/kotlin/coroutines/VertxCoroutineKt.class */
public final class VertxCoroutineKt {

    /* compiled from: VertxCoroutine.kt */
    @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��(\n��\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u0004\u0018\u00010\u0001\"\u0004\b��\u0010\u00022-\u0010\u0003\u001a)\u0012\u001f\u0012\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00060\u0005¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\fH\u0086@ø\u0001��"}, d2 = {"awaitResult", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "block", "Lkotlin/Function1;", "Lio/vertx/core/Handler;", "Lio/vertx/core/AsyncResult;", "Lkotlin/ParameterName;", "name", OperatorName.CLOSE_PATH, "", "continuation", "Lkotlin/coroutines/Continuation;"})
    @DebugMetadata(f = "VertxCoroutine.kt", l = {81}, i = {0}, s = {"L$0"}, n = {"block"}, m = "awaitResult", c = "io.vertx.kotlin.coroutines.VertxCoroutineKt")
    /* renamed from: io.vertx.kotlin.coroutines.VertxCoroutineKt$awaitResult$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-coroutines-3.8.5.jar:io/vertx/kotlin/coroutines/VertxCoroutineKt$awaitResult$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        /* synthetic */ Object result;
        int label;
        Object L$0;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object result) {
            this.result = result;
            this.label |= Integer.MIN_VALUE;
            return VertxCoroutineKt.awaitResult(null, this);
        }

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }
    }

    @Nullable
    public static final <T> Object awaitEvent(@NotNull Function1<? super Handler<T>, Unit> function1, @NotNull Continuation<? super T> continuation) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        final CancellableContinuationImpl cont = cancellable$iv;
        try {
            function1.invoke(new Handler<T>() { // from class: io.vertx.kotlin.coroutines.VertxCoroutineKt$awaitEvent$2$1
                @Override // io.vertx.core.Handler
                public final void handle(T t) {
                    CancellableContinuation cancellableContinuation = cont;
                    Result.Companion companion = Result.Companion;
                    cancellableContinuation.resumeWith(Result.m2105constructorimpl(t));
                }
            });
        } catch (Exception e) {
            Result.Companion companion = Result.Companion;
            cont.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(e)));
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object awaitResult(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super io.vertx.core.Handler<io.vertx.core.AsyncResult<T>>, kotlin.Unit> r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r6) throws java.lang.Throwable {
        /*
            r0 = r6
            boolean r0 = r0 instanceof io.vertx.kotlin.coroutines.VertxCoroutineKt.AnonymousClass1
            if (r0 == 0) goto L27
            r0 = r6
            io.vertx.kotlin.coroutines.VertxCoroutineKt$awaitResult$1 r0 = (io.vertx.kotlin.coroutines.VertxCoroutineKt.AnonymousClass1) r0
            r9 = r0
            r0 = r9
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r9
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L31
        L27:
            io.vertx.kotlin.coroutines.VertxCoroutineKt$awaitResult$1 r0 = new io.vertx.kotlin.coroutines.VertxCoroutineKt$awaitResult$1
            r1 = r0
            r2 = r6
            r1.<init>(r2)
            r9 = r0
        L31:
            r0 = r9
            java.lang.Object r0 = r0.result
            r8 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r10 = r0
            r0 = r9
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto L77;
                default: goto La6;
            }
        L58:
            r0 = r8
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r5
            r1 = r9
            r2 = r9
            r3 = r5
            r2.L$0 = r3
            r2 = r9
            r3 = 1
            r2.label = r3
            java.lang.Object r0 = awaitEvent(r0, r1)
            r1 = r0
            r2 = r10
            if (r1 != r2) goto L85
            r1 = r10
            return r1
        L77:
            r0 = r9
            java.lang.Object r0 = r0.L$0
            kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
            r5 = r0
            r0 = r8
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r8
        L85:
            io.vertx.core.AsyncResult r0 = (io.vertx.core.AsyncResult) r0
            r7 = r0
            r0 = r7
            boolean r0 = r0.succeeded()
            if (r0 == 0) goto L99
            r0 = r7
            java.lang.Object r0 = r0.result()
            return r0
        L99:
            r0 = r7
            java.lang.Throwable r0 = r0.cause()
            r1 = r0
            java.lang.String r2 = "asyncResult.cause()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            throw r0
        La6:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.vertx.kotlin.coroutines.VertxCoroutineKt.awaitResult(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static final <T> Object awaitBlocking(@NotNull final Function0<? extends T> function0, @NotNull Continuation<? super T> continuation) {
        return awaitResult(new Function1<Handler<AsyncResult<T>>, Unit>() { // from class: io.vertx.kotlin.coroutines.VertxCoroutineKt.awaitBlocking.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            public final void invoke(@NotNull final Handler<AsyncResult<T>> handler) {
                Intrinsics.checkParameterIsNotNull(handler, "handler");
                Context ctx = Vertx.currentContext();
                ctx.executeBlocking(new Handler<Promise<T>>() { // from class: io.vertx.kotlin.coroutines.VertxCoroutineKt.awaitBlocking.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(Promise<T> promise) {
                        promise.complete(function0.invoke());
                    }
                }, new Handler<AsyncResult<T>>() { // from class: io.vertx.kotlin.coroutines.VertxCoroutineKt.awaitBlocking.2.2
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<T> asyncResult) {
                        handler.handle(asyncResult);
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final <T> Object await(@NotNull Future<T> future, @NotNull Continuation<? super T> continuation) throws Throwable {
        if (future.succeeded()) {
            return future.result();
        }
        if (future.failed()) {
            Throwable thCause = future.cause();
            Intrinsics.checkExpressionValueIsNotNull(thCause, "cause()");
            throw thCause;
        }
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        final CancellableContinuationImpl cont = cancellable$iv;
        future.setHandler2(new Handler<AsyncResult<T>>() { // from class: io.vertx.kotlin.coroutines.VertxCoroutineKt$await$2$1
            @Override // io.vertx.core.Handler
            public final void handle(AsyncResult<T> asyncResult) {
                if (!asyncResult.succeeded()) {
                    CancellableContinuation cancellableContinuation = cont;
                    Throwable thCause2 = asyncResult.cause();
                    Intrinsics.checkExpressionValueIsNotNull(thCause2, "asyncResult.cause()");
                    Result.Companion companion = Result.Companion;
                    cancellableContinuation.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(thCause2)));
                    return;
                }
                CancellableContinuation cancellableContinuation2 = cont;
                T tResult = asyncResult.result();
                Result.Companion companion2 = Result.Companion;
                cancellableContinuation2.resumeWith(Result.m2105constructorimpl(tResult));
            }
        });
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
            return result;
        }
        return result;
    }

    @NotNull
    public static final CoroutineDispatcher dispatcher(@NotNull Vertx dispatcher) {
        Intrinsics.checkParameterIsNotNull(dispatcher, "$this$dispatcher");
        Context orCreateContext = dispatcher.getOrCreateContext();
        Intrinsics.checkExpressionValueIsNotNull(orCreateContext, "getOrCreateContext()");
        return dispatcher(orCreateContext);
    }

    @NotNull
    public static final CoroutineDispatcher dispatcher(@NotNull Context dispatcher) {
        Intrinsics.checkParameterIsNotNull(dispatcher, "$this$dispatcher");
        return ExecutorsKt.from((ExecutorService) new VertxCoroutineExecutor(dispatcher));
    }
}
