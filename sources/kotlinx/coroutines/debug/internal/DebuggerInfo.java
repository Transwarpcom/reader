package kotlinx.coroutines.debug.internal;

import ch.qos.logback.core.CoreConstants;
import java.io.Serializable;
import java.lang.Thread;
import java.util.List;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineId;
import kotlinx.coroutines.CoroutineName;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DebuggerInfo.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0001\u0018��2\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\r¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u000fR\u0013\u0010\u0017\u001a\u0004\u0018\u00010\r¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u000fR\u0013\u0010\u0019\u001a\u0004\u0018\u00010\r¢\u0006\b\n��\u001a\u0004\b\u001a\u0010\u000fR\u0011\u0010\u001b\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\r¢\u0006\b\n��\u001a\u0004\b\u001f\u0010\u000f¨\u0006 "}, d2 = {"Lkotlinx/coroutines/debug/internal/DebuggerInfo;", "Ljava/io/Serializable;", PackageDocumentBase.DCTags.source, "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;Lkotlin/coroutines/CoroutineContext;)V", "coroutineId", "", "getCoroutineId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "dispatcher", "", "getDispatcher", "()Ljava/lang/String;", "lastObservedStackTrace", "", "Ljava/lang/StackTraceElement;", "getLastObservedStackTrace", "()Ljava/util/List;", "lastObservedThreadName", "getLastObservedThreadName", "lastObservedThreadState", "getLastObservedThreadState", "name", "getName", "sequenceNumber", "getSequenceNumber", "()J", "state", "getState", "kotlinx-coroutines-core"})
@PublishedApi
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/debug/internal/DebuggerInfo.class */
public final class DebuggerInfo implements Serializable {

    @Nullable
    private final Long coroutineId;

    @Nullable
    private final String dispatcher;

    @Nullable
    private final String name;

    @NotNull
    private final String state;

    @Nullable
    private final String lastObservedThreadState;

    @Nullable
    private final String lastObservedThreadName;

    @NotNull
    private final List<StackTraceElement> lastObservedStackTrace;
    private final long sequenceNumber;

    public DebuggerInfo(@NotNull DebugCoroutineInfoImpl source, @NotNull CoroutineContext context) {
        Thread.State state;
        CoroutineId coroutineId = (CoroutineId) context.get(CoroutineId.Key);
        this.coroutineId = coroutineId == null ? null : Long.valueOf(coroutineId.getId());
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) context.get(ContinuationInterceptor.Key);
        this.dispatcher = continuationInterceptor == null ? null : continuationInterceptor.toString();
        CoroutineName coroutineName = (CoroutineName) context.get(CoroutineName.Key);
        this.name = coroutineName == null ? null : coroutineName.getName();
        this.state = source.getState();
        Thread thread = source.lastObservedThread;
        String string = (thread == null || (state = thread.getState()) == null) ? null : state.toString();
        this.lastObservedThreadState = string;
        Thread thread2 = source.lastObservedThread;
        this.lastObservedThreadName = thread2 == null ? null : thread2.getName();
        this.lastObservedStackTrace = source.lastObservedStackTrace();
        this.sequenceNumber = source.sequenceNumber;
    }

    @Nullable
    public final Long getCoroutineId() {
        return this.coroutineId;
    }

    @Nullable
    public final String getDispatcher() {
        return this.dispatcher;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getState() {
        return this.state;
    }

    @Nullable
    public final String getLastObservedThreadState() {
        return this.lastObservedThreadState;
    }

    @Nullable
    public final String getLastObservedThreadName() {
        return this.lastObservedThreadName;
    }

    @NotNull
    public final List<StackTraceElement> getLastObservedStackTrace() {
        return this.lastObservedStackTrace;
    }

    public final long getSequenceNumber() {
        return this.sequenceNumber;
    }
}
