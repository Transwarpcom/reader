package kotlinx.coroutines;

import io.legado.app.constant.Action;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CancellableContinuation.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0003\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018��*\u0006\b��\u0010\u0001 ��2\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u0014\u0010\b\u001a\u00020\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH&J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH'J\b\u0010\u000f\u001a\u00020\fH'J1\u0010\u0010\u001a\u00020\f2'\u0010\u0011\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\f0\u0012j\u0002`\u0015H&J:\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00028��2#\u0010\u0018\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\f\u0018\u00010\u0012H'¢\u0006\u0002\u0010\u0019J#\u0010\u001a\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0017\u001a\u00028��2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u000eH'¢\u0006\u0002\u0010\u001cJF\u0010\u001a\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0017\u001a\u00028��2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000e2#\u0010\u0018\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\f\u0018\u00010\u0012H'¢\u0006\u0002\u0010\u001dJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001f\u001a\u00020\nH'J\u0019\u0010 \u001a\u00020\f*\u00020!2\u0006\u0010\u0017\u001a\u00028��H'¢\u0006\u0002\u0010\"J\u0014\u0010#\u001a\u00020\f*\u00020!2\u0006\u0010\u001f\u001a\u00020\nH'R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005R\u0012\u0010\u0007\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005¨\u0006$"}, d2 = {"Lkotlinx/coroutines/CancellableContinuation;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlin/coroutines/Continuation;", "isActive", "", "()Z", "isCancelled", "isCompleted", "cancel", "cause", "", "completeResume", "", "token", "", "initCancellability", "invokeOnCancellation", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", Action.resume, "value", "onCancellation", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "tryResume", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "tryResumeWithException", "exception", "resumeUndispatched", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CancellableContinuation.class */
public interface CancellableContinuation<T> extends Continuation<T> {
    boolean isActive();

    boolean isCompleted();

    boolean isCancelled();

    @InternalCoroutinesApi
    @Nullable
    Object tryResume(T t, @Nullable Object obj);

    @InternalCoroutinesApi
    @Nullable
    Object tryResume(T t, @Nullable Object obj, @Nullable Function1<? super Throwable, Unit> function1);

    @InternalCoroutinesApi
    @Nullable
    Object tryResumeWithException(@NotNull Throwable th);

    @InternalCoroutinesApi
    void completeResume(@NotNull Object obj);

    @InternalCoroutinesApi
    void initCancellability();

    boolean cancel(@Nullable Throwable th);

    void invokeOnCancellation(@NotNull Function1<? super Throwable, Unit> function1);

    @ExperimentalCoroutinesApi
    void resumeUndispatched(@NotNull CoroutineDispatcher coroutineDispatcher, T t);

    @ExperimentalCoroutinesApi
    void resumeUndispatchedWithException(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull Throwable th);

    @ExperimentalCoroutinesApi
    void resume(T t, @Nullable Function1<? super Throwable, Unit> function1);

    /* compiled from: CancellableContinuation.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CancellableContinuation$DefaultImpls.class */
    public static final class DefaultImpls {
        public static /* synthetic */ Object tryResume$default(CancellableContinuation cancellableContinuation, Object obj, Object obj2, int i, Object obj3) {
            if (obj3 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryResume");
            }
            if ((i & 2) != 0) {
                obj2 = null;
            }
            return cancellableContinuation.tryResume(obj, obj2);
        }

        public static /* synthetic */ boolean cancel$default(CancellableContinuation cancellableContinuation, Throwable th, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
            }
            if ((i & 1) != 0) {
                th = null;
            }
            return cancellableContinuation.cancel(th);
        }
    }
}
