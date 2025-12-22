package kotlinx.coroutines.flow.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AbstractSharedFlow.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��@\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018��*\f\b��\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\u00060\u0003j\u0002`\u0004B\u0005¢\u0006\u0002\u0010\u0005J\r\u0010\u0018\u001a\u00028��H\u0004¢\u0006\u0002\u0010\u0019J\r\u0010\u001a\u001a\u00028��H$¢\u0006\u0002\u0010\u0019J\u001d\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018��0\u000e2\u0006\u0010\u001c\u001a\u00020\bH$¢\u0006\u0002\u0010\u001dJ\u001d\u0010\u001e\u001a\u00020\u001f2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00020\u001f0!H\u0084\bJ\u0015\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00028��H\u0004¢\u0006\u0002\u0010$R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n��R\u001e\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b@BX\u0084\u000e¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n��R:\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018��\u0018\u00010\u000e2\u0010\u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018��\u0018\u00010\u000e@BX\u0084\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u0012\u0004\b\u0010\u0010\u0005\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006%"}, d2 = {"Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "S", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "()V", "_subscriptionCount", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "<set-?>", "nCollectors", "getNCollectors", "()I", "nextIndex", "", "slots", "getSlots$annotations", "getSlots", "()[Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "[Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "getSubscriptionCount", "()Lkotlinx/coroutines/flow/StateFlow;", "allocateSlot", "()Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "createSlot", "createSlotArray", "size", "(I)[Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "forEachSlotLocked", "", "block", "Lkotlin/Function1;", "freeSlot", "slot", "(Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;)V", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/internal/AbstractSharedFlow.class */
public abstract class AbstractSharedFlow<S extends AbstractSharedFlowSlot<?>> {

    @Nullable
    private S[] slots;
    private int nCollectors;
    private int nextIndex;

    @Nullable
    private MutableStateFlow<Integer> _subscriptionCount;

    protected static /* synthetic */ void getSlots$annotations() {
    }

    @NotNull
    protected abstract S createSlot();

    @NotNull
    protected abstract S[] createSlotArray(int i);

    @Nullable
    protected final S[] getSlots() {
        return this.slots;
    }

    protected final int getNCollectors() {
        return this.nCollectors;
    }

    @NotNull
    public final StateFlow<Integer> getSubscriptionCount() {
        MutableStateFlow mutableStateFlow;
        MutableStateFlow mutableStateFlow2;
        synchronized (this) {
            MutableStateFlow mutableStateFlow3 = this._subscriptionCount;
            if (mutableStateFlow3 != null) {
                mutableStateFlow = mutableStateFlow3;
            } else {
                MutableStateFlow it = StateFlowKt.MutableStateFlow(Integer.valueOf(getNCollectors()));
                this._subscriptionCount = it;
                mutableStateFlow = it;
            }
            mutableStateFlow2 = mutableStateFlow;
        }
        return mutableStateFlow2;
    }

    @NotNull
    protected final S allocateSlot() {
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        AbstractSharedFlowSlot abstractSharedFlowSlot;
        S s;
        MutableStateFlow<Integer> mutableStateFlow;
        synchronized (this) {
            AbstractSharedFlowSlot[] slots = getSlots();
            if (slots == null) {
                S[] sArr = (S[]) createSlotArray(2);
                this.slots = sArr;
                abstractSharedFlowSlotArr = sArr;
            } else if (getNCollectors() >= slots.length) {
                Object[] objArrCopyOf = Arrays.copyOf(slots, 2 * slots.length);
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
                this.slots = (S[]) ((AbstractSharedFlowSlot[]) objArrCopyOf);
                abstractSharedFlowSlotArr = (AbstractSharedFlowSlot[]) objArrCopyOf;
            } else {
                abstractSharedFlowSlotArr = slots;
            }
            AbstractSharedFlowSlot[] abstractSharedFlowSlotArr2 = abstractSharedFlowSlotArr;
            int i = this.nextIndex;
            do {
                AbstractSharedFlowSlot abstractSharedFlowSlot2 = abstractSharedFlowSlotArr2[i];
                if (abstractSharedFlowSlot2 == null) {
                    AbstractSharedFlowSlot abstractSharedFlowSlotCreateSlot = createSlot();
                    abstractSharedFlowSlotArr2[i] = abstractSharedFlowSlotCreateSlot;
                    abstractSharedFlowSlot = abstractSharedFlowSlotCreateSlot;
                } else {
                    abstractSharedFlowSlot = abstractSharedFlowSlot2;
                }
                s = (S) abstractSharedFlowSlot;
                i++;
                if (i >= abstractSharedFlowSlotArr2.length) {
                    i = 0;
                }
            } while (!s.allocateLocked(this));
            this.nextIndex = i;
            this.nCollectors = getNCollectors() + 1;
            mutableStateFlow = this._subscriptionCount;
        }
        if (mutableStateFlow != null) {
            StateFlowKt.increment(mutableStateFlow, 1);
        }
        return s;
    }

    protected final void freeSlot(@NotNull S s) {
        MutableStateFlow<Integer> mutableStateFlow;
        Continuation[] resumes;
        synchronized (this) {
            this.nCollectors = getNCollectors() - 1;
            mutableStateFlow = this._subscriptionCount;
            if (getNCollectors() == 0) {
                this.nextIndex = 0;
            }
            resumes = s.freeLocked(this);
        }
        int i = 0;
        int length = resumes.length;
        while (i < length) {
            Continuation cont = resumes[i];
            i++;
            if (cont != null) {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.Companion;
                cont.resumeWith(Result.m2105constructorimpl(unit));
            }
        }
        if (mutableStateFlow != null) {
            StateFlowKt.increment(mutableStateFlow, -1);
        }
    }

    protected final void forEachSlotLocked(@NotNull Function1<? super S, Unit> function1) {
        Object[] $this$forEach$iv;
        if (this.nCollectors == 0 || ($this$forEach$iv = this.slots) == null) {
            return;
        }
        for (Object element$iv : $this$forEach$iv) {
            if (element$iv != null) {
                function1.invoke(element$iv);
            }
        }
    }
}
