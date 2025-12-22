package kotlinx.coroutines;

import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CoroutineContext.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010��\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b��\u0018��*\u0006\b��\u0010\u0001 ��2\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\nH\u0014J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0018\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\nR\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/UndispatchedCoroutine;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/internal/ScopeCoroutine;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "uCont", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)V", "savedContext", "savedOldValue", "", "afterResume", "", "state", "clearThreadContext", "", "saveThreadContext", "oldValue", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/UndispatchedCoroutine.class */
public final class UndispatchedCoroutine<T> extends ScopeCoroutine<T> {

    @Nullable
    private CoroutineContext savedContext;

    @Nullable
    private Object savedOldValue;

    public UndispatchedCoroutine(@NotNull CoroutineContext context, @NotNull Continuation<? super T> continuation) {
        super(context.get(UndispatchedMarker.INSTANCE) == null ? context.plus(UndispatchedMarker.INSTANCE) : context, continuation);
    }

    public final void saveThreadContext(@NotNull CoroutineContext context, @Nullable Object oldValue) {
        this.savedContext = context;
        this.savedOldValue = oldValue;
    }

    public final boolean clearThreadContext() {
        if (this.savedContext == null) {
            return false;
        }
        this.savedContext = null;
        this.savedOldValue = null;
        return true;
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.AbstractCoroutine
    protected void afterResume(@Nullable Object state) {
        UndispatchedCoroutine undispatchedCoroutineUpdateUndispatchedCompletion;
        CoroutineContext context = this.savedContext;
        if (context != null) {
            ThreadContextKt.restoreThreadContext(context, this.savedOldValue);
            this.savedContext = null;
            this.savedOldValue = null;
        }
        Object result = CompletionStateKt.recoverResult(state, this.uCont);
        Continuation continuation$iv = this.uCont;
        CoroutineContext context$iv = continuation$iv.getContext();
        Object oldValue$iv = ThreadContextKt.updateThreadContext(context$iv, null);
        if (oldValue$iv != ThreadContextKt.NO_THREAD_ELEMENTS) {
            undispatchedCoroutineUpdateUndispatchedCompletion = CoroutineContextKt.updateUndispatchedCompletion(continuation$iv, context$iv, oldValue$iv);
        } else {
            undispatchedCoroutineUpdateUndispatchedCompletion = (UndispatchedCoroutine) null;
        }
        UndispatchedCoroutine undispatchedCompletion$iv = undispatchedCoroutineUpdateUndispatchedCompletion;
        try {
            this.uCont.resumeWith(result);
            Unit unit = Unit.INSTANCE;
            if (undispatchedCompletion$iv != null && !undispatchedCompletion$iv.clearThreadContext()) {
                return;
            }
            ThreadContextKt.restoreThreadContext(context$iv, oldValue$iv);
        } catch (Throwable th) {
            if (undispatchedCompletion$iv == null || undispatchedCompletion$iv.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context$iv, oldValue$iv);
            }
            throw th;
        }
    }
}
