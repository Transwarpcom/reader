package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TestCoroutineContext.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��<\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n��\b\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u00012\u00060\u0002j\u0002`\u00032\u00020\u0004B%\u0012\n\u0010\u0005\u001a\u00060\u0002j\u0002`\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0011\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020��H\u0096\u0002J\b\u0010\u0005\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R \u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000bX\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0005\u001a\u00060\u0002j\u0002`\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0010\u0010\b\u001a\u00020\u00078��X\u0081\u0004¢\u0006\u0002\n��¨\u0006\u001b"}, d2 = {"Lkotlinx/coroutines/test/TimedRunnableObsolete;", "", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "run", "count", "", "time", "(Ljava/lang/Runnable;JJ)V", "heap", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "getHeap", "()Lkotlinx/coroutines/internal/ThreadSafeHeap;", "setHeap", "(Lkotlinx/coroutines/internal/ThreadSafeHeap;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "compareTo", "other", "", "toString", "", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/test/TimedRunnableObsolete.class */
final class TimedRunnableObsolete implements Comparable<TimedRunnableObsolete>, Runnable, ThreadSafeHeapNode {

    @NotNull
    private final Runnable run;
    private final long count;

    @JvmField
    public final long time;

    @Nullable
    private ThreadSafeHeap<?> heap;
    private int index;

    public TimedRunnableObsolete(@NotNull Runnable run, long count, long time) {
        this.run = run;
        this.count = count;
        this.time = time;
    }

    public /* synthetic */ TimedRunnableObsolete(Runnable runnable, long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(runnable, (i & 2) != 0 ? 0L : j, (i & 4) != 0 ? 0L : j2);
    }

    @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
    @Nullable
    public ThreadSafeHeap<?> getHeap() {
        return this.heap;
    }

    @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
    public void setHeap(@Nullable ThreadSafeHeap<?> threadSafeHeap) {
        this.heap = threadSafeHeap;
    }

    @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
    public int getIndex() {
        return this.index;
    }

    @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
    public void setIndex(int i) {
        this.index = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.run.run();
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull TimedRunnableObsolete other) {
        if (this.time == other.time) {
            return Intrinsics.compare(this.count, other.count);
        }
        return Intrinsics.compare(this.time, other.time);
    }

    @NotNull
    public String toString() {
        return "TimedRunnable(time=" + this.time + ", run=" + this.run + ')';
    }
}
