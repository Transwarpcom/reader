package kotlinx.coroutines.internal;

import io.legado.app.constant.Action;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlinx.coroutines.internal.Segment;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* compiled from: ConcurrentLinkedList.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\b \u0018��*\u000e\b��\u0010\u0001*\b\u0012\u0004\u0012\u00028��0��2\b\u0012\u0004\u0012\u00028��0\u001bB!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00018��\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\f\u001a\u00020\tH��¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\tH��¢\u0006\u0004\b\u0010\u0010\u000bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0017\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u000b¨\u0006\u001a"}, d2 = {"Lkotlinx/coroutines/internal/Segment;", "S", "", "id", Action.prev, "", "pointers", Constants.CONSTRUCTOR_NAME, "(JLkotlinx/coroutines/internal/Segment;I)V", "", "decPointers$kotlinx_coroutines_core", "()Z", "decPointers", "", "onSlotCleaned", "()V", "tryIncPointers$kotlinx_coroutines_core", "tryIncPointers", OperatorName.SET_LINE_CAPSTYLE, "getId", "()J", "getMaxSlots", "()I", "maxSlots", "getRemoved", "removed", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/Segment.class */
public abstract class Segment<S extends Segment<S>> extends ConcurrentLinkedListNode<S> {
    private final long id;

    @NotNull
    private volatile /* synthetic */ int cleanedAndPointers;
    private static final /* synthetic */ AtomicIntegerFieldUpdater cleanedAndPointers$FU = AtomicIntegerFieldUpdater.newUpdater(Segment.class, "cleanedAndPointers");

    public abstract int getMaxSlots();

    public Segment(long id, @Nullable S s, int pointers) {
        super(s);
        this.id = id;
        this.cleanedAndPointers = pointers << 16;
    }

    public final long getId() {
        return this.id;
    }

    @Override // kotlinx.coroutines.internal.ConcurrentLinkedListNode
    public boolean getRemoved() {
        return this.cleanedAndPointers == getMaxSlots() && !isTail();
    }

    public final boolean tryIncPointers$kotlinx_coroutines_core() {
        int cur$iv;
        do {
            cur$iv = this.cleanedAndPointers;
            if (!(cur$iv != getMaxSlots() || isTail())) {
                return false;
            }
        } while (!cleanedAndPointers$FU.compareAndSet(this, cur$iv, cur$iv + 65536));
        return true;
    }

    public final boolean decPointers$kotlinx_coroutines_core() {
        return cleanedAndPointers$FU.addAndGet(this, -65536) == getMaxSlots() && !isTail();
    }

    public final void onSlotCleaned() {
        if (cleanedAndPointers$FU.incrementAndGet(this) != getMaxSlots() || isTail()) {
            return;
        }
        remove();
    }
}
