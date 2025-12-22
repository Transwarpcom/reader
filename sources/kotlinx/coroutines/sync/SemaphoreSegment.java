package kotlinx.coroutines.sync;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import io.legado.app.constant.Action;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlinx.coroutines.internal.Segment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* compiled from: Semaphore.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\b\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u001fB!\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010��\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\u000bJ,\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0086\b¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\f2\u0006\u0010\b\u001a\u00020\u0004H\u0086\b¢\u0006\u0004\b\u0012\u0010\u0013J$\u0010\u0014\u001a\u0004\u0018\u00010\f2\u0006\u0010\b\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0086\b¢\u0006\u0004\b\u0014\u0010\u0015J\"\u0010\u0016\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0086\b¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001d\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreSegment;", "", "id", Action.prev, "", "pointers", Constants.CONSTRUCTOR_NAME, "(JLkotlinx/coroutines/sync/SemaphoreSegment;I)V", "index", "", "cancel", "(I)V", "", "expected", "value", "", "cas", "(ILjava/lang/Object;Ljava/lang/Object;)Z", BeanUtil.PREFIX_GETTER_GET, "(I)Ljava/lang/Object;", "getAndSet", "(ILjava/lang/Object;)Ljava/lang/Object;", "set", "(ILjava/lang/Object;)V", "", "toString", "()Ljava/lang/String;", "getMaxSlots", "()I", "maxSlots", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/Segment;"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/sync/SemaphoreSegment.class */
final class SemaphoreSegment extends Segment<SemaphoreSegment> {

    @NotNull
    /* synthetic */ AtomicReferenceArray acquirers;

    public SemaphoreSegment(long id, @Nullable SemaphoreSegment prev, int pointers) {
        super(id, prev, pointers);
        this.acquirers = new AtomicReferenceArray(SemaphoreKt.SEGMENT_SIZE);
    }

    @Override // kotlinx.coroutines.internal.Segment
    public int getMaxSlots() {
        return SemaphoreKt.SEGMENT_SIZE;
    }

    @Nullable
    public final Object get(int index) {
        return this.acquirers.get(index);
    }

    public final void set(int index, @Nullable Object value) {
        this.acquirers.set(index, value);
    }

    public final boolean cas(int index, @Nullable Object expected, @Nullable Object value) {
        return this.acquirers.compareAndSet(index, expected, value);
    }

    @Nullable
    public final Object getAndSet(int index, @Nullable Object value) {
        return this.acquirers.getAndSet(index, value);
    }

    public final void cancel(int index) {
        this.acquirers.set(index, SemaphoreKt.CANCELLED);
        onSlotCleaned();
    }

    @NotNull
    public String toString() {
        return "SemaphoreSegment[id=" + getId() + ", hashCode=" + hashCode() + ']';
    }
}
