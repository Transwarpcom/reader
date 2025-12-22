package kotlinx.coroutines.internal;

import ch.qos.logback.core.CoreConstants;
import io.netty.handler.codec.rtsp.RtspHeaders;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ThreadContext.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018��2\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001J\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0003R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n��R \u0010\u0007\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\t0\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��R\u0018\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/internal/ThreadState;", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", OperatorName.ENDPATH, "", "(Lkotlin/coroutines/CoroutineContext;I)V", "elements", "", "Lkotlinx/coroutines/ThreadContextElement;", "[Lkotlinx/coroutines/ThreadContextElement;", "i", "values", "[Ljava/lang/Object;", RtspHeaders.Values.APPEND, "", "element", "value", "restore", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/ThreadState.class */
final class ThreadState {

    @JvmField
    @NotNull
    public final CoroutineContext context;

    @NotNull
    private final Object[] values;

    @NotNull
    private final ThreadContextElement<Object>[] elements;
    private int i;

    public ThreadState(@NotNull CoroutineContext context, int n) {
        this.context = context;
        this.values = new Object[n];
        this.elements = new ThreadContextElement[n];
    }

    public final void append(@NotNull ThreadContextElement<?> threadContextElement, @Nullable Object value) {
        this.values[this.i] = value;
        ThreadContextElement<Object>[] threadContextElementArr = this.elements;
        int i = this.i;
        this.i = i + 1;
        threadContextElementArr[i] = threadContextElement;
    }

    public final void restore(@NotNull CoroutineContext context) {
        int length = this.elements.length - 1;
        if (0 <= length) {
            do {
                int i = length;
                length--;
                ThreadContextElement<Object> threadContextElement = this.elements[i];
                Intrinsics.checkNotNull(threadContextElement);
                threadContextElement.restoreThreadContext(context, this.values[i]);
            } while (0 <= length);
        }
    }
}
