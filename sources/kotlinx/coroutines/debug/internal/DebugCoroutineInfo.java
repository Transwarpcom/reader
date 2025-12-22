package kotlinx.coroutines.debug.internal;

import ch.qos.logback.core.CoreConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmName;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DebugCoroutineInfo.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0001\u0018��2\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\n¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\fR\u0019\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8G¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0011R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n��\u001a\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;", "", PackageDocumentBase.DCTags.source, "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;Lkotlin/coroutines/CoroutineContext;)V", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "creationStackBottom", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "getCreationStackBottom", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "creationStackTrace", "", "Ljava/lang/StackTraceElement;", "getCreationStackTrace", "()Ljava/util/List;", "lastObservedFrame", "getLastObservedFrame", "lastObservedStackTrace", "lastObservedThread", "Ljava/lang/Thread;", "getLastObservedThread", "()Ljava/lang/Thread;", "sequenceNumber", "", "getSequenceNumber", "()J", "state", "", "getState", "()Ljava/lang/String;", "kotlinx-coroutines-core"})
@PublishedApi
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/debug/internal/DebugCoroutineInfo.class */
public final class DebugCoroutineInfo {

    @NotNull
    private final CoroutineContext context;

    @Nullable
    private final CoroutineStackFrame creationStackBottom;
    private final long sequenceNumber;

    @NotNull
    private final List<StackTraceElement> creationStackTrace;

    @NotNull
    private final String state;

    @Nullable
    private final Thread lastObservedThread;

    @Nullable
    private final CoroutineStackFrame lastObservedFrame;

    @NotNull
    private final List<StackTraceElement> lastObservedStackTrace;

    public DebugCoroutineInfo(@NotNull DebugCoroutineInfoImpl source, @NotNull CoroutineContext context) {
        this.context = context;
        this.creationStackBottom = source.getCreationStackBottom();
        this.sequenceNumber = source.sequenceNumber;
        this.creationStackTrace = source.getCreationStackTrace();
        this.state = source.getState();
        this.lastObservedThread = source.lastObservedThread;
        this.lastObservedFrame = source.getLastObservedFrame$kotlinx_coroutines_core();
        this.lastObservedStackTrace = source.lastObservedStackTrace();
    }

    @NotNull
    public final CoroutineContext getContext() {
        return this.context;
    }

    @Nullable
    public final CoroutineStackFrame getCreationStackBottom() {
        return this.creationStackBottom;
    }

    public final long getSequenceNumber() {
        return this.sequenceNumber;
    }

    @NotNull
    public final List<StackTraceElement> getCreationStackTrace() {
        return this.creationStackTrace;
    }

    @NotNull
    public final String getState() {
        return this.state;
    }

    @Nullable
    public final Thread getLastObservedThread() {
        return this.lastObservedThread;
    }

    @Nullable
    public final CoroutineStackFrame getLastObservedFrame() {
        return this.lastObservedFrame;
    }

    @JvmName(name = "lastObservedStackTrace")
    @NotNull
    public final List<StackTraceElement> lastObservedStackTrace() {
        return this.lastObservedStackTrace;
    }
}
