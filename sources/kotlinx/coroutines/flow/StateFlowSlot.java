package kotlinx.coroutines.flow;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* compiled from: StateFlow.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\b\u0002\u0018��2\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0013B\u0007¢\u0006\u0004\b\u0001\u0010\u0002J\u001b\u0010\u0006\u001a\u00020\u00052\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0004\b\t\u0010\nJ)\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\f0\u000b2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\b¢\u0006\u0004\b\u000f\u0010\u0002J\r\u0010\u0010\u001a\u00020\u0005¢\u0006\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/flow/StateFlowSlot;", Constants.CONSTRUCTOR_NAME, "()V", "Lkotlinx/coroutines/flow/StateFlowImpl;", "flow", "", "allocateLocked", "(Lkotlinx/coroutines/flow/StateFlowImpl;)Z", "", "awaitPending", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "Lkotlin/coroutines/Continuation;", "freeLocked", "(Lkotlinx/coroutines/flow/StateFlowImpl;)[Lkotlin/coroutines/Continuation;", "makePending", "takePending", "()Z", "kotlinx-coroutines-core", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/StateFlowSlot.class */
final class StateFlowSlot extends AbstractSharedFlowSlot<StateFlowImpl<?>> {

    @NotNull
    volatile /* synthetic */ Object _state = null;
    static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(StateFlowSlot.class, Object.class, "_state");

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public boolean allocateLocked(@NotNull StateFlowImpl<?> stateFlowImpl) {
        if (this._state != null) {
            return false;
        }
        this._state = StateFlowKt.NONE;
        return true;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    @NotNull
    public Continuation<Unit>[] freeLocked(@NotNull StateFlowImpl<?> stateFlowImpl) {
        this._state = null;
        return AbstractSharedFlowKt.EMPTY_RESUMES;
    }

    public final void makePending() {
        while (true) {
            Object state = this._state;
            if (state == null || state == StateFlowKt.PENDING) {
                return;
            }
            if (state == StateFlowKt.NONE) {
                if (_state$FU.compareAndSet(this, state, StateFlowKt.PENDING)) {
                    return;
                }
            } else if (_state$FU.compareAndSet(this, state, StateFlowKt.NONE)) {
                CancellableContinuationImpl cancellableContinuationImpl = (CancellableContinuationImpl) state;
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.Companion;
                cancellableContinuationImpl.resumeWith(Result.m2105constructorimpl(unit));
                return;
            }
        }
    }

    public final boolean takePending() {
        Object state = _state$FU.getAndSet(this, StateFlowKt.NONE);
        Intrinsics.checkNotNull(state);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(!(state instanceof CancellableContinuationImpl))) {
                throw new AssertionError();
            }
        }
        return state == StateFlowKt.PENDING;
    }

    @Nullable
    public final Object awaitPending(@NotNull Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl cancellableContinuationImpl = cancellable$iv;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(!(this._state instanceof CancellableContinuationImpl))) {
                throw new AssertionError();
            }
        }
        if (!_state$FU.compareAndSet(this, StateFlowKt.NONE, cancellableContinuationImpl)) {
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(this._state == StateFlowKt.PENDING)) {
                    throw new AssertionError();
                }
            }
            CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
            Unit unit = Unit.INSTANCE;
            Result.Companion companion = Result.Companion;
            cancellableContinuationImpl2.resumeWith(Result.m2105constructorimpl(unit));
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }
}
