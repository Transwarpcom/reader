package kotlinx.coroutines.internal;

import io.legado.app.constant.Action;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* compiled from: ConcurrentLinkedList.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\r\n\u0002\u0010��\n\u0002\b\t\b \u0018��*\u000e\b��\u0010\u0001*\b\u0012\u0004\u0012\u00028��0��2\u00020\u001aB\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00018��¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ \u0010\u000e\u001a\u0004\u0018\u00018��2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0086\b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0005¢\u0006\u0004\b\u0010\u0010\u0007J\u0015\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00028��¢\u0006\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\nR\u0016\u0010\u0017\u001a\u0004\u0018\u00018��8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0019\u001a\u0004\u0018\u00018��8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016R\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u001a8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0002\u001a\u0004\u0018\u00018��8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0016R\u0014\u0010 \u001a\u00020\b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\nR\u0014\u0010\"\u001a\u00028��8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0016¨\u0006#"}, d2 = {"Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "N", Action.prev, Constants.CONSTRUCTOR_NAME, "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)V", "", "cleanPrev", "()V", "", "markAsClosed", "()Z", "Lkotlin/Function0;", "", "onClosedAction", "nextOrIfClosed", "(Lkotlin/jvm/functions/Function0;)Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "remove", "value", "trySetNext", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)Z", "isTail", "getLeftmostAliveNode", "()Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "leftmostAliveNode", "getNext", "next", "", "getNextOrClosed", "()Ljava/lang/Object;", "nextOrClosed", "getPrev", "getRemoved", "removed", "getRightmostAliveNode", "rightmostAliveNode", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/ConcurrentLinkedListNode.class */
public abstract class ConcurrentLinkedListNode<N extends ConcurrentLinkedListNode<N>> {

    @NotNull
    private volatile /* synthetic */ Object _next = null;

    @NotNull
    private volatile /* synthetic */ Object _prev;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_next");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _prev$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_prev");

    public abstract boolean getRemoved();

    public ConcurrentLinkedListNode(@Nullable N n) {
        this._prev = n;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getNextOrClosed() {
        return this._next;
    }

    @Nullable
    public final N nextOrIfClosed(@NotNull Function0 onClosedAction) {
        Object it = getNextOrClosed();
        if (it == ConcurrentLinkedListKt.CLOSED) {
            onClosedAction.invoke();
            throw new KotlinNothingValueException();
        }
        return (N) it;
    }

    @Nullable
    public final N getNext() {
        Object it$iv = getNextOrClosed();
        if (it$iv == ConcurrentLinkedListKt.CLOSED) {
            return null;
        }
        return (N) it$iv;
    }

    public final boolean trySetNext(@NotNull N n) {
        return _next$FU.compareAndSet(this, null, n);
    }

    public final boolean isTail() {
        return getNext() == null;
    }

    @Nullable
    public final N getPrev() {
        return (N) this._prev;
    }

    public final void cleanPrev() {
        _prev$FU.lazySet(this, null);
    }

    public final boolean markAsClosed() {
        return _next$FU.compareAndSet(this, null, ConcurrentLinkedListKt.CLOSED);
    }

    public final void remove() {
        if (DebugKt.getASSERTIONS_ENABLED() && !getRemoved()) {
            throw new AssertionError();
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(!isTail())) {
                throw new AssertionError();
            }
        }
        while (true) {
            ConcurrentLinkedListNode prev = getLeftmostAliveNode();
            ConcurrentLinkedListNode next = getRightmostAliveNode();
            next._prev = prev;
            if (prev != null) {
                prev._next = next;
            }
            if (!next.getRemoved() && (prev == null || !prev.getRemoved())) {
                return;
            }
        }
    }

    private final N getLeftmostAliveNode() {
        N n;
        ConcurrentLinkedListNode prev = getPrev();
        while (true) {
            n = (N) prev;
            if (n == null || !n.getRemoved()) {
                break;
            }
            prev = (ConcurrentLinkedListNode) n._prev;
        }
        return n;
    }

    private final N getRightmostAliveNode() {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(!isTail())) {
                throw new AssertionError();
            }
        }
        ConcurrentLinkedListNode next = getNext();
        Intrinsics.checkNotNull(next);
        while (true) {
            N n = (N) next;
            if (n.getRemoved()) {
                next = n.getNext();
                Intrinsics.checkNotNull(next);
            } else {
                return n;
            }
        }
    }
}
