package kotlinx.coroutines.selects;

import ch.qos.logback.core.CoreConstants;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CompletionHandlerBase;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobCancellingNode;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectBuilder;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;
import org.springframework.cglib.core.Constants;

/* compiled from: Select.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��®\u0001\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0001\u0018��*\u0006\b��\u0010\u0001 ��2\u00020Y2\b\u0012\u0004\u0012\u00028��0Z2\b\u0012\u0004\u0012\u00028��0[2\b\u0012\u0004\u0012\u00028��0\u00022\u00060Bj\u0002`C:\u0004TUVWB\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ.\u0010\u0011\u001a\u00020\b2\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\rH\u0082\b¢\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u000eH\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\n\u0018\u00010\u0015j\u0004\u0018\u0001`\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u0019H\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\bH\u0002¢\u0006\u0004\b\u001d\u0010\fJ8\u0010!\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u001e2\u001c\u0010\u0010\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u000e0 H\u0016ø\u0001��¢\u0006\u0004\b!\u0010\"J\u0019\u0010%\u001a\u0004\u0018\u00010\u000e2\u0006\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\b%\u0010&J\u0017\u0010(\u001a\u00020\b2\u0006\u0010'\u001a\u00020\u0019H\u0016¢\u0006\u0004\b(\u0010\u001cJ \u0010+\u001a\u00020\b2\f\u0010*\u001a\b\u0012\u0004\u0012\u00028��0)H\u0016ø\u0001��¢\u0006\u0004\b+\u0010,J\u000f\u0010.\u001a\u00020-H\u0016¢\u0006\u0004\b.\u0010/J\u000f\u00101\u001a\u000200H\u0016¢\u0006\u0004\b1\u00102J\u001b\u00105\u001a\u0004\u0018\u00010\u000e2\b\u00104\u001a\u0004\u0018\u000103H\u0016¢\u0006\u0004\b5\u00106J5\u00108\u001a\u00020\b*\u0002072\u001c\u0010\u0010\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u000e0 H\u0096\u0002ø\u0001��¢\u0006\u0004\b8\u00109JG\u00108\u001a\u00020\b\"\u0004\b\u0001\u0010:*\b\u0012\u0004\u0012\u00028\u00010;2\"\u0010\u0010\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u000e0<H\u0096\u0002ø\u0001��¢\u0006\u0004\b8\u0010=J[\u00108\u001a\u00020\b\"\u0004\b\u0001\u0010>\"\u0004\b\u0002\u0010:*\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020?2\u0006\u0010@\u001a\u00028\u00012\"\u0010\u0010\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u000e0<H\u0096\u0002ø\u0001��¢\u0006\u0004\b8\u0010AR\u001c\u0010F\u001a\n\u0018\u00010Bj\u0004\u0018\u0001`C8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bD\u0010ER\u001a\u0010I\u001a\b\u0012\u0004\u0012\u00028��0\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bG\u0010HR\u0014\u0010M\u001a\u00020J8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bK\u0010LR\u0014\u0010N\u001a\u0002008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bN\u00102R(\u0010R\u001a\u0004\u0018\u00010\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u00068B@BX\u0082\u000e¢\u0006\f\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010\nR\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010S\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006X"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl;", "R", "Lkotlin/coroutines/Continuation;", "uCont", Constants.CONSTRUCTOR_NAME, "(Lkotlin/coroutines/Continuation;)V", "Lkotlinx/coroutines/DisposableHandle;", "handle", "", "disposeOnSelect", "(Lkotlinx/coroutines/DisposableHandle;)V", "doAfterSelect", "()V", "Lkotlin/Function0;", "", "value", "block", "doResume", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "getResult", "()Ljava/lang/Object;", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "", "e", "handleBuilderException", "(Ljava/lang/Throwable;)V", "initCancellability", "", "timeMillis", "Lkotlin/Function1;", "onTimeout", "(JLkotlin/jvm/functions/Function1;)V", "Lkotlinx/coroutines/internal/AtomicDesc;", "desc", "performAtomicTrySelect", "(Lkotlinx/coroutines/internal/AtomicDesc;)Ljava/lang/Object;", "exception", "resumeSelectWithException", "Lkotlin/Result;", CacheOperationExpressionEvaluator.RESULT_VARIABLE, "resumeWith", "(Ljava/lang/Object;)V", "", "toString", "()Ljava/lang/String;", "", "trySelect", "()Z", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "otherOp", "trySelectOther", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectClause0;", "invoke", "(Lkotlinx/coroutines/selects/SelectClause0;Lkotlin/jvm/functions/Function1;)V", OperatorName.RESTORE, "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/selects/SelectClause1;Lkotlin/jvm/functions/Function2;)V", "P", "Lkotlinx/coroutines/selects/SelectClause2;", "param", "(Lkotlinx/coroutines/selects/SelectClause2;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "callerFrame", "getCompletion", "()Lkotlin/coroutines/Continuation;", "completion", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", CoreConstants.CONTEXT_SCOPE_VALUE, "isSelected", "getParentHandle", "()Lkotlinx/coroutines/DisposableHandle;", "setParentHandle", "parentHandle", "Lkotlin/coroutines/Continuation;", "AtomicSelectOp", "DisposeNode", "PairSelectOp", "SelectOnCancelling", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/selects/SelectBuilder;", "Lkotlinx/coroutines/selects/SelectInstance;"})
@PublishedApi
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/selects/SelectBuilderImpl.class */
public final class SelectBuilderImpl<R> extends LockFreeLinkedListHead implements SelectBuilder<R>, SelectInstance<R>, Continuation<R>, CoroutineStackFrame {

    @NotNull
    private final Continuation<R> uCont;

    @NotNull
    volatile /* synthetic */ Object _state = SelectKt.getNOT_SELECTED();

    @NotNull
    volatile /* synthetic */ Object _result = SelectKt.UNDECIDED;

    @NotNull
    private volatile /* synthetic */ Object _parentHandle = null;
    static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_state");
    static final /* synthetic */ AtomicReferenceFieldUpdater _result$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_result");

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        SelectBuilder.DefaultImpls.invoke(this, selectClause2, function2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SelectBuilderImpl(@NotNull Continuation<? super R> continuation) {
        this.uCont = continuation;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        Continuation<R> continuation = this.uCont;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    private final DisposableHandle getParentHandle() {
        return (DisposableHandle) this._parentHandle;
    }

    private final void setParentHandle(DisposableHandle value) {
        this._parentHandle = value;
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return this.uCont.getContext();
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    @NotNull
    public Continuation<R> getCompletion() {
        return this;
    }

    private final void doResume(Function0<? extends Object> function0, Function0<Unit> function02) {
        if (DebugKt.getASSERTIONS_ENABLED() && !isSelected()) {
            throw new AssertionError();
        }
        while (true) {
            Object result = this._result;
            if (result == SelectKt.UNDECIDED) {
                Object update = function0.invoke();
                if (_result$FU.compareAndSet(this, SelectKt.UNDECIDED, update)) {
                    return;
                }
            } else if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                if (_result$FU.compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.RESUMED)) {
                    function02.invoke();
                    return;
                }
            } else {
                throw new IllegalStateException("Already resumed");
            }
        }
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object result) {
        if (DebugKt.getASSERTIONS_ENABLED() && !isSelected()) {
            throw new AssertionError();
        }
        while (true) {
            Object result$iv = this._result;
            if (result$iv == SelectKt.UNDECIDED) {
                Object update$iv = CompletionStateKt.toState$default(result, null, 1, null);
                if (_result$FU.compareAndSet(this, SelectKt.UNDECIDED, update$iv)) {
                    return;
                }
            } else if (result$iv == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                if (_result$FU.compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.RESUMED)) {
                    if (Result.m2101isFailureimpl(result)) {
                        Continuation $this$resumeWithStackTrace$iv = this.uCont;
                        Throwable exception$iv = Result.m2103exceptionOrNullimpl(result);
                        Intrinsics.checkNotNull(exception$iv);
                        Result.Companion companion = Result.Companion;
                        $this$resumeWithStackTrace$iv.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure((DebugKt.getRECOVER_STACK_TRACES() && ($this$resumeWithStackTrace$iv instanceof CoroutineStackFrame)) ? StackTraceRecoveryKt.recoverFromStackFrame(exception$iv, (CoroutineStackFrame) $this$resumeWithStackTrace$iv) : exception$iv)));
                        return;
                    }
                    this.uCont.resumeWith(result);
                    return;
                }
            } else {
                throw new IllegalStateException("Already resumed");
            }
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public void resumeSelectWithException(@NotNull Throwable exception) {
        if (DebugKt.getASSERTIONS_ENABLED() && !isSelected()) {
            throw new AssertionError();
        }
        while (true) {
            Object result$iv = this._result;
            if (result$iv == SelectKt.UNDECIDED) {
                Continuation continuation$iv = this.uCont;
                if (_result$FU.compareAndSet(this, SelectKt.UNDECIDED, new CompletedExceptionally((DebugKt.getRECOVER_STACK_TRACES() && (continuation$iv instanceof CoroutineStackFrame)) ? StackTraceRecoveryKt.recoverFromStackFrame(exception, (CoroutineStackFrame) continuation$iv) : exception, false, 2, null))) {
                    return;
                }
            } else if (result$iv == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                if (_result$FU.compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.RESUMED)) {
                    Continuation continuationIntercepted = IntrinsicsKt.intercepted(this.uCont);
                    Result.Companion companion = Result.Companion;
                    continuationIntercepted.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(exception)));
                    return;
                }
            } else {
                throw new IllegalStateException("Already resumed");
            }
        }
    }

    @PublishedApi
    @Nullable
    public final Object getResult() throws Throwable {
        if (!isSelected()) {
            initCancellability();
        }
        Object result = this._result;
        if (result == SelectKt.UNDECIDED) {
            if (_result$FU.compareAndSet(this, SelectKt.UNDECIDED, IntrinsicsKt.getCOROUTINE_SUSPENDED())) {
                return IntrinsicsKt.getCOROUTINE_SUSPENDED();
            }
            result = this._result;
        }
        if (result == SelectKt.RESUMED) {
            throw new IllegalStateException("Already resumed");
        }
        if (result instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) result).cause;
        }
        return result;
    }

    private final void initCancellability() {
        Job parent = (Job) getContext().get(Job.Key);
        if (parent == null) {
            return;
        }
        CompletionHandlerBase $this$asHandler$iv = new SelectOnCancelling();
        DisposableHandle newRegistration = Job.DefaultImpls.invokeOnCompletion$default(parent, true, false, $this$asHandler$iv, 2, null);
        setParentHandle(newRegistration);
        if (isSelected()) {
            newRegistration.dispose();
        }
    }

    /* compiled from: Select.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0003\n��\b\u0082\u0004\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0096\u0002¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$SelectOnCancelling;", "Lkotlinx/coroutines/JobCancellingNode;", "(Lkotlinx/coroutines/selects/SelectBuilderImpl;)V", "invoke", "", "cause", "", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/selects/SelectBuilderImpl$SelectOnCancelling.class */
    private final class SelectOnCancelling extends JobCancellingNode {
        public SelectOnCancelling() {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke(th);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlinx.coroutines.CompletionHandlerBase
        public void invoke(@Nullable Throwable cause) {
            if (SelectBuilderImpl.this.trySelect()) {
                SelectBuilderImpl.this.resumeSelectWithException(getJob().getCancellationException());
            }
        }
    }

    @PublishedApi
    public final void handleBuilderException(@NotNull Throwable e) throws Throwable {
        if (trySelect()) {
            Result.Companion companion = Result.Companion;
            resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(e)));
        } else if (!(e instanceof CancellationException)) {
            Object result = getResult();
            if (result instanceof CompletedExceptionally) {
                Throwable exception$iv = ((CompletedExceptionally) result).cause;
                if ((!DebugKt.getRECOVER_STACK_TRACES() ? exception$iv : StackTraceRecoveryKt.unwrapImpl(exception$iv)) == (!DebugKt.getRECOVER_STACK_TRACES() ? e : StackTraceRecoveryKt.unwrapImpl(e))) {
                    return;
                }
            }
            CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), e);
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public boolean isSelected() {
        while (true) {
            Object state = this._state;
            if (state == SelectKt.getNOT_SELECTED()) {
                return false;
            }
            if (!(state instanceof OpDescriptor)) {
                return true;
            }
            ((OpDescriptor) state).perform(this);
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public void disposeOnSelect(@NotNull DisposableHandle handle) {
        DisposeNode node = new DisposeNode(handle);
        if (!isSelected()) {
            addLast(node);
            if (!isSelected()) {
                return;
            }
        }
        handle.dispose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doAfterSelect() {
        DisposableHandle parentHandle = getParentHandle();
        if (parentHandle != null) {
            parentHandle.dispose();
        }
        SelectBuilderImpl<R> this_$iv = this;
        LockFreeLinkedListNode nextNode = (LockFreeLinkedListNode) this_$iv.getNext();
        while (true) {
            LockFreeLinkedListNode cur$iv = nextNode;
            if (Intrinsics.areEqual(cur$iv, this_$iv)) {
                return;
            }
            if (cur$iv instanceof DisposeNode) {
                DisposeNode it = (DisposeNode) cur$iv;
                it.handle.dispose();
            }
            nextNode = cur$iv.getNextNode();
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public boolean trySelect() {
        Object result = trySelectOther(null);
        if (result == CancellableContinuationImplKt.RESUME_TOKEN) {
            return true;
        }
        if (result == null) {
            return false;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("Unexpected trySelectIdempotent result ", result).toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x005a, code lost:
    
        doAfterSelect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0061, code lost:
    
        return kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN;
     */
    @Override // kotlinx.coroutines.selects.SelectInstance
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object trySelectOther(@org.jetbrains.annotations.Nullable kotlinx.coroutines.internal.LockFreeLinkedListNode.PrepareOp r6) {
        /*
            r5 = this;
            r0 = r5
            r7 = r0
            r0 = 0
            r8 = r0
        L4:
            r0 = r7
            java.lang.Object r0 = r0._state
            r9 = r0
            r0 = 0
            r10 = r0
            r0 = r9
            java.lang.Object r1 = kotlinx.coroutines.selects.SelectKt.getNOT_SELECTED()
            if (r0 != r1) goto L62
            r0 = r6
            if (r0 != 0) goto L2d
            r0 = r5
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.coroutines.selects.SelectBuilderImpl._state$FU
            r2 = r0; r0 = r1; r1 = r2; 
            java.lang.Object r2 = kotlinx.coroutines.selects.SelectKt.getNOT_SELECTED()
            r3 = 0
            boolean r0 = r0.compareAndSet(r1, r2, r3)
            if (r0 != 0) goto L5a
            goto Ld5
        L2d:
            kotlinx.coroutines.selects.SelectBuilderImpl$PairSelectOp r0 = new kotlinx.coroutines.selects.SelectBuilderImpl$PairSelectOp
            r1 = r0
            r2 = r6
            r1.<init>(r2)
            r11 = r0
            r0 = r5
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.coroutines.selects.SelectBuilderImpl._state$FU
            r2 = r0; r0 = r1; r1 = r2; 
            java.lang.Object r2 = kotlinx.coroutines.selects.SelectKt.getNOT_SELECTED()
            r3 = r11
            boolean r0 = r0.compareAndSet(r1, r2, r3)
            if (r0 != 0) goto L4a
            goto Ld5
        L4a:
            r0 = r11
            r1 = r5
            java.lang.Object r0 = r0.perform(r1)
            r12 = r0
            r0 = r12
            if (r0 == 0) goto L5a
            r0 = r12
            return r0
        L5a:
            r0 = r5
            r0.doAfterSelect()
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN
            return r0
        L62:
            r0 = r9
            boolean r0 = r0 instanceof kotlinx.coroutines.internal.OpDescriptor
            if (r0 == 0) goto Lbf
            r0 = r6
            if (r0 == 0) goto Lb2
            r0 = r6
            kotlinx.coroutines.internal.AtomicOp r0 = r0.getAtomicOp()
            r11 = r0
            r0 = r11
            boolean r0 = r0 instanceof kotlinx.coroutines.selects.SelectBuilderImpl.AtomicSelectOp
            if (r0 == 0) goto La1
            r0 = r11
            kotlinx.coroutines.selects.SelectBuilderImpl$AtomicSelectOp r0 = (kotlinx.coroutines.selects.SelectBuilderImpl.AtomicSelectOp) r0
            kotlinx.coroutines.selects.SelectBuilderImpl<?> r0 = r0.impl
            r1 = r5
            if (r0 != r1) goto La1
            java.lang.String r0 = "Cannot use matching select clauses on the same object"
            r12 = r0
            r0 = 0
            r13 = r0
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            r2 = r12
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        La1:
            r0 = r11
            r1 = r9
            kotlinx.coroutines.internal.OpDescriptor r1 = (kotlinx.coroutines.internal.OpDescriptor) r1
            boolean r0 = r0.isEarlierThan(r1)
            if (r0 == 0) goto Lb2
            java.lang.Object r0 = kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC
            return r0
        Lb2:
            r0 = r9
            kotlinx.coroutines.internal.OpDescriptor r0 = (kotlinx.coroutines.internal.OpDescriptor) r0
            r1 = r5
            java.lang.Object r0 = r0.perform(r1)
            goto Ld4
        Lbf:
            r0 = r6
            if (r0 != 0) goto Lc5
            r0 = 0
            return r0
        Lc5:
            r0 = r9
            r1 = r6
            kotlinx.coroutines.internal.LockFreeLinkedListNode$AbstractAtomicDesc r1 = r1.desc
            if (r0 != r1) goto Ld2
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN
            return r0
        Ld2:
            r0 = 0
            return r0
        Ld4:
        Ld5:
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.selects.SelectBuilderImpl.trySelectOther(kotlinx.coroutines.internal.LockFreeLinkedListNode$PrepareOp):java.lang.Object");
    }

    /* compiled from: Select.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010��\n\u0002\b\u0002\b\u0002\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016R\u0018\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n��¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$PairSelectOp;", "Lkotlinx/coroutines/internal/OpDescriptor;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)V", "atomicOp", "Lkotlinx/coroutines/internal/AtomicOp;", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "perform", "", "affected", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/selects/SelectBuilderImpl$PairSelectOp.class */
    private static final class PairSelectOp extends OpDescriptor {

        @JvmField
        @NotNull
        public final LockFreeLinkedListNode.PrepareOp otherOp;

        public PairSelectOp(@NotNull LockFreeLinkedListNode.PrepareOp otherOp) {
            this.otherOp = otherOp;
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        @Nullable
        public Object perform(@Nullable Object affected) {
            if (affected == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.selects.SelectBuilderImpl<*>");
            }
            SelectBuilderImpl impl = (SelectBuilderImpl) affected;
            this.otherOp.finishPrepare();
            Object decision = this.otherOp.getAtomicOp().decide(null);
            Object update = decision == null ? this.otherOp.desc : SelectKt.getNOT_SELECTED();
            SelectBuilderImpl._state$FU.compareAndSet(impl, this, update);
            return decision;
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        @NotNull
        public AtomicOp<?> getAtomicOp() {
            return this.otherOp.getAtomicOp();
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    @Nullable
    public Object performAtomicTrySelect(@NotNull AtomicDesc desc) {
        return new AtomicSelectOp(this, desc).perform(null);
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    @NotNull
    public String toString() {
        return "SelectInstance(state=" + this._state + ", result=" + this._result + ')';
    }

    /* compiled from: Select.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018��2\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0019\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001c\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00022\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0010\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0002J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\rH\u0002R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n��R\u0014\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00048\u0006X\u0087\u0004¢\u0006\u0002\n��R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$AtomicSelectOp;", "Lkotlinx/coroutines/internal/AtomicOp;", "", "impl", "Lkotlinx/coroutines/selects/SelectBuilderImpl;", "desc", "Lkotlinx/coroutines/internal/AtomicDesc;", "(Lkotlinx/coroutines/selects/SelectBuilderImpl;Lkotlinx/coroutines/internal/AtomicDesc;)V", "opSequence", "", "getOpSequence", "()J", "complete", "", "affected", "failure", "completeSelect", "prepare", "prepareSelectOp", "toString", "", "undoPrepare", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/selects/SelectBuilderImpl$AtomicSelectOp.class */
    private static final class AtomicSelectOp extends AtomicOp<Object> {

        @JvmField
        @NotNull
        public final SelectBuilderImpl<?> impl;

        @JvmField
        @NotNull
        public final AtomicDesc desc;
        private final long opSequence = SelectKt.selectOpSequenceNumber.next();

        public AtomicSelectOp(@NotNull SelectBuilderImpl<?> selectBuilderImpl, @NotNull AtomicDesc desc) {
            this.impl = selectBuilderImpl;
            this.desc = desc;
            this.desc.setAtomicOp(this);
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public long getOpSequence() {
            return this.opSequence;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        @Nullable
        public Object prepare(@Nullable Object affected) {
            Object it;
            if (affected == null && (it = prepareSelectOp()) != null) {
                return it;
            }
            try {
                return this.desc.prepare(this);
            } catch (Throwable e) {
                if (affected == null) {
                    undoPrepare();
                }
                throw e;
            }
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public void complete(@Nullable Object affected, @Nullable Object failure) {
            completeSelect(failure);
            this.desc.complete(this, failure);
        }

        private final Object prepareSelectOp() {
            SelectBuilderImpl $this$loop$iv = this.impl;
            while (true) {
                Object state = $this$loop$iv._state;
                if (state == this) {
                    return null;
                }
                if (state instanceof OpDescriptor) {
                    ((OpDescriptor) state).perform(this.impl);
                } else if (state == SelectKt.getNOT_SELECTED()) {
                    if (SelectBuilderImpl._state$FU.compareAndSet(this.impl, SelectKt.getNOT_SELECTED(), this)) {
                        return null;
                    }
                } else {
                    return SelectKt.getALREADY_SELECTED();
                }
            }
        }

        private final void undoPrepare() {
            SelectBuilderImpl._state$FU.compareAndSet(this.impl, this, SelectKt.getNOT_SELECTED());
        }

        private final void completeSelect(Object failure) {
            boolean selectSuccess = failure == null;
            Object update = selectSuccess ? null : SelectKt.getNOT_SELECTED();
            if (SelectBuilderImpl._state$FU.compareAndSet(this.impl, this, update) && selectSuccess) {
                this.impl.doAfterSelect();
            }
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        @NotNull
        public String toString() {
            return "AtomicSelectOp(sequence=" + getOpSequence() + ')';
        }
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void invoke(@NotNull SelectClause0 $this$invoke, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        $this$invoke.registerSelectClause0(this, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <Q> void invoke(@NotNull SelectClause1<? extends Q> selectClause1, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        selectClause1.registerSelectClause1(this, function2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, P p, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        selectClause2.registerSelectClause2(this, p, function2);
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void onTimeout(long timeMillis, @NotNull final Function1<? super Continuation<? super R>, ? extends Object> function1) {
        if (timeMillis <= 0) {
            if (trySelect()) {
                UndispatchedKt.startCoroutineUnintercepted(function1, getCompletion());
            }
        } else {
            Runnable action = new Runnable() { // from class: kotlinx.coroutines.selects.SelectBuilderImpl$onTimeout$$inlined$Runnable$1
                @Override // java.lang.Runnable
                public final void run() throws Throwable {
                    if (this.this$0.trySelect()) {
                        CancellableKt.startCoroutineCancellable(function1, this.this$0.getCompletion());
                    }
                }
            };
            disposeOnSelect(DelayKt.getDelay(getContext()).invokeOnTimeout(timeMillis, action, getContext()));
        }
    }

    /* compiled from: Select.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n��¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$DisposeNode;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "handle", "Lkotlinx/coroutines/DisposableHandle;", "(Lkotlinx/coroutines/DisposableHandle;)V", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/selects/SelectBuilderImpl$DisposeNode.class */
    private static final class DisposeNode extends LockFreeLinkedListNode {

        @JvmField
        @NotNull
        public final DisposableHandle handle;

        public DisposeNode(@NotNull DisposableHandle handle) {
            this.handle = handle;
        }
    }
}
