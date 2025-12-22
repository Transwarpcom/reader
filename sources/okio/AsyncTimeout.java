package okio;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.ext.web.handler.StaticHandler;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AsyncTimeout.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018�� \u001b2\u00020\u0001:\u0002\u001b\u001cB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0001J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u0004J\u0012\u0010\u000e\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0014J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\fH\u0014J%\u0010\u0016\u001a\u0002H\u0017\"\u0004\b��\u0010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0019H\u0086\bø\u0001��¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\u0005\u001a\u0004\u0018\u00010��X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n��\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001d"}, d2 = {"Lokio/AsyncTimeout;", "Lokio/Timeout;", "()V", "inQueue", "", "next", "timeoutAt", "", "access$newTimeoutException", "Ljava/io/IOException;", "cause", "enter", "", "exit", "newTimeoutException", "remainingNanos", "now", "sink", "Lokio/Sink;", PackageDocumentBase.DCTags.source, "Lokio/Source;", "timedOut", "withTimeout", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Companion", "Watchdog", "okio"})
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/AsyncTimeout.class */
public class AsyncTimeout extends Timeout {
    private boolean inQueue;
    private AsyncTimeout next;
    private long timeoutAt;
    private static final int TIMEOUT_WRITE_SIZE = 65536;
    private static AsyncTimeout head;
    public static final Companion Companion = new Companion(null);
    private static final long IDLE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(60);
    private static final long IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(IDLE_TIMEOUT_MILLIS);

    public final void enter() {
        if (!(!this.inQueue)) {
            throw new IllegalStateException("Unbalanced enter/exit".toString());
        }
        long timeoutNanos = timeoutNanos();
        boolean hasDeadline = hasDeadline();
        if (timeoutNanos == 0 && !hasDeadline) {
            return;
        }
        this.inQueue = true;
        Companion.scheduleTimeout(this, timeoutNanos, hasDeadline);
    }

    public final boolean exit() {
        if (!this.inQueue) {
            return false;
        }
        this.inQueue = false;
        return Companion.cancelScheduledTimeout(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long remainingNanos(long now) {
        return this.timeoutAt - now;
    }

    protected void timedOut() {
    }

    @NotNull
    public final Sink sink(@NotNull final Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        return new Sink() { // from class: okio.AsyncTimeout.sink.1
            @Override // okio.Sink
            public void write(@NotNull Buffer source, long byteCount) throws IOException {
                Intrinsics.checkNotNullParameter(source, "source");
                Util.checkOffsetAndCount(source.size(), 0L, byteCount);
                long j = byteCount;
                while (true) {
                    long remaining = j;
                    if (remaining > 0) {
                        long toWrite = 0;
                        Segment segment = source.head;
                        Intrinsics.checkNotNull(segment);
                        while (true) {
                            Segment s = segment;
                            if (toWrite >= 65536) {
                                break;
                            }
                            int segmentSize = s.limit - s.pos;
                            toWrite += segmentSize;
                            if (toWrite >= remaining) {
                                toWrite = remaining;
                                break;
                            } else {
                                segment = s.next;
                                Intrinsics.checkNotNull(segment);
                            }
                        }
                        AsyncTimeout this_$iv = AsyncTimeout.this;
                        boolean throwOnTimeout$iv = false;
                        this_$iv.enter();
                        try {
                            try {
                                sink.write(source, toWrite);
                                Unit unit = Unit.INSTANCE;
                                throwOnTimeout$iv = true;
                                boolean timedOut$iv = this_$iv.exit();
                                if (timedOut$iv) {
                                    throw this_$iv.access$newTimeoutException(null);
                                }
                                j = remaining - toWrite;
                            } catch (IOException e$iv) {
                                if (!this_$iv.exit()) {
                                    throw e$iv;
                                }
                                throw this_$iv.access$newTimeoutException(e$iv);
                            }
                        } catch (Throwable th) {
                            boolean timedOut$iv2 = this_$iv.exit();
                            if (!timedOut$iv2 || !throwOnTimeout$iv) {
                                throw th;
                            }
                            throw this_$iv.access$newTimeoutException(null);
                        }
                    } else {
                        return;
                    }
                }
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() throws IOException {
                AsyncTimeout this_$iv = AsyncTimeout.this;
                boolean throwOnTimeout$iv = false;
                this_$iv.enter();
                try {
                    try {
                        sink.flush();
                        Unit unit = Unit.INSTANCE;
                        throwOnTimeout$iv = true;
                        boolean timedOut$iv = this_$iv.exit();
                        if (timedOut$iv) {
                            throw this_$iv.access$newTimeoutException(null);
                        }
                    } catch (IOException e$iv) {
                        if (!this_$iv.exit()) {
                            throw e$iv;
                        }
                        throw this_$iv.access$newTimeoutException(e$iv);
                    }
                } catch (Throwable th) {
                    boolean timedOut$iv2 = this_$iv.exit();
                    if (!timedOut$iv2 || !throwOnTimeout$iv) {
                        throw th;
                    }
                    throw this_$iv.access$newTimeoutException(null);
                }
            }

            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                AsyncTimeout this_$iv = AsyncTimeout.this;
                boolean throwOnTimeout$iv = false;
                this_$iv.enter();
                try {
                    try {
                        sink.close();
                        Unit unit = Unit.INSTANCE;
                        throwOnTimeout$iv = true;
                        boolean timedOut$iv = this_$iv.exit();
                        if (timedOut$iv) {
                            throw this_$iv.access$newTimeoutException(null);
                        }
                    } catch (IOException e$iv) {
                        if (!this_$iv.exit()) {
                            throw e$iv;
                        }
                        throw this_$iv.access$newTimeoutException(e$iv);
                    }
                } catch (Throwable th) {
                    boolean timedOut$iv2 = this_$iv.exit();
                    if (!timedOut$iv2 || !throwOnTimeout$iv) {
                        throw th;
                    }
                    throw this_$iv.access$newTimeoutException(null);
                }
            }

            @Override // okio.Sink
            @NotNull
            public AsyncTimeout timeout() {
                return AsyncTimeout.this;
            }

            @NotNull
            public String toString() {
                return "AsyncTimeout.sink(" + sink + ')';
            }
        };
    }

    @NotNull
    public final Source source(@NotNull final Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new Source() { // from class: okio.AsyncTimeout.source.1
            @Override // okio.Source
            public long read(@NotNull Buffer sink, long byteCount) throws IOException {
                Intrinsics.checkNotNullParameter(sink, "sink");
                AsyncTimeout this_$iv = AsyncTimeout.this;
                boolean throwOnTimeout$iv = false;
                this_$iv.enter();
                try {
                    try {
                        long result$iv = source.read(sink, byteCount);
                        throwOnTimeout$iv = true;
                        boolean timedOut$iv = this_$iv.exit();
                        if (timedOut$iv) {
                            throw this_$iv.access$newTimeoutException(null);
                        }
                        return result$iv;
                    } catch (IOException e$iv) {
                        if (this_$iv.exit()) {
                            throw this_$iv.access$newTimeoutException(e$iv);
                        }
                        throw e$iv;
                    }
                } catch (Throwable th) {
                    boolean timedOut$iv2 = this_$iv.exit();
                    if (timedOut$iv2 && throwOnTimeout$iv) {
                        throw this_$iv.access$newTimeoutException(null);
                    }
                    throw th;
                }
            }

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                AsyncTimeout this_$iv = AsyncTimeout.this;
                boolean throwOnTimeout$iv = false;
                this_$iv.enter();
                try {
                    try {
                        source.close();
                        Unit unit = Unit.INSTANCE;
                        throwOnTimeout$iv = true;
                        boolean timedOut$iv = this_$iv.exit();
                        if (timedOut$iv) {
                            throw this_$iv.access$newTimeoutException(null);
                        }
                    } catch (IOException e$iv) {
                        if (!this_$iv.exit()) {
                            throw e$iv;
                        }
                        throw this_$iv.access$newTimeoutException(e$iv);
                    }
                } catch (Throwable th) {
                    boolean timedOut$iv2 = this_$iv.exit();
                    if (!timedOut$iv2 || !throwOnTimeout$iv) {
                        throw th;
                    }
                    throw this_$iv.access$newTimeoutException(null);
                }
            }

            @Override // okio.Source
            @NotNull
            public AsyncTimeout timeout() {
                return AsyncTimeout.this;
            }

            @NotNull
            public String toString() {
                return "AsyncTimeout.source(" + source + ')';
            }
        };
    }

    public final <T> T withTimeout(@NotNull Function0<? extends T> block) throws IOException {
        Intrinsics.checkNotNullParameter(block, "block");
        boolean throwOnTimeout = false;
        enter();
        try {
            try {
                T tInvoke = block.invoke();
                throwOnTimeout = true;
                InlineMarker.finallyStart(1);
                boolean timedOut = exit();
                if (timedOut) {
                    throw access$newTimeoutException(null);
                }
                InlineMarker.finallyEnd(1);
                return tInvoke;
            } catch (IOException e) {
                if (exit()) {
                    throw access$newTimeoutException(e);
                }
                throw e;
            }
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            boolean timedOut2 = exit();
            if (timedOut2 && throwOnTimeout) {
                throw access$newTimeoutException(null);
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    @PublishedApi
    @NotNull
    public final IOException access$newTimeoutException(@Nullable IOException cause) {
        return newTimeoutException(cause);
    }

    @NotNull
    protected IOException newTimeoutException(@Nullable IOException cause) {
        InterruptedIOException e = new InterruptedIOException(RtspHeaders.Values.TIMEOUT);
        if (cause != null) {
            e.initCause(cause);
        }
        return e;
    }

    /* compiled from: AsyncTimeout.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\b\u0002\u0018��2\u00020\u0001B\u0007\b��¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lokio/AsyncTimeout$Watchdog;", "Ljava/lang/Thread;", "()V", "run", "", "okio"})
    /* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/AsyncTimeout$Watchdog.class */
    private static final class Watchdog extends Thread {
        public Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            AsyncTimeout asyncTimeoutAwaitTimeout$okio;
            while (true) {
                try {
                    synchronized (AsyncTimeout.class) {
                        asyncTimeoutAwaitTimeout$okio = AsyncTimeout.Companion.awaitTimeout$okio();
                        if (asyncTimeoutAwaitTimeout$okio == AsyncTimeout.head) {
                            AsyncTimeout.head = (AsyncTimeout) null;
                            return;
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                    if (asyncTimeoutAwaitTimeout$okio != null) {
                        asyncTimeoutAwaitTimeout$okio.timedOut();
                    }
                } catch (InterruptedException e) {
                }
            }
        }
    }

    /* compiled from: AsyncTimeout.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\n\u001a\u0004\u0018\u00010\tH��¢\u0006\u0002\b\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0002J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n��R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0013"}, d2 = {"Lokio/AsyncTimeout$Companion;", "", "()V", "IDLE_TIMEOUT_MILLIS", "", "IDLE_TIMEOUT_NANOS", "TIMEOUT_WRITE_SIZE", "", "head", "Lokio/AsyncTimeout;", "awaitTimeout", "awaitTimeout$okio", "cancelScheduledTimeout", "", "node", "scheduleTimeout", "", "timeoutNanos", "hasDeadline", "okio"})
    /* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/AsyncTimeout$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void scheduleTimeout(AsyncTimeout node, long timeoutNanos, boolean hasDeadline) {
            synchronized (AsyncTimeout.class) {
                if (AsyncTimeout.head == null) {
                    AsyncTimeout.head = new AsyncTimeout();
                    new Watchdog().start();
                }
                long now = System.nanoTime();
                if (timeoutNanos != 0 && hasDeadline) {
                    node.timeoutAt = now + Math.min(timeoutNanos, node.deadlineNanoTime() - now);
                } else if (timeoutNanos != 0) {
                    node.timeoutAt = now + timeoutNanos;
                } else if (hasDeadline) {
                    node.timeoutAt = node.deadlineNanoTime();
                } else {
                    throw new AssertionError();
                }
                long remainingNanos = node.remainingNanos(now);
                AsyncTimeout asyncTimeout = AsyncTimeout.head;
                Intrinsics.checkNotNull(asyncTimeout);
                AsyncTimeout prev = asyncTimeout;
                while (prev.next != null) {
                    AsyncTimeout asyncTimeout2 = prev.next;
                    Intrinsics.checkNotNull(asyncTimeout2);
                    if (remainingNanos < asyncTimeout2.remainingNanos(now)) {
                        break;
                    }
                    AsyncTimeout asyncTimeout3 = prev.next;
                    Intrinsics.checkNotNull(asyncTimeout3);
                    prev = asyncTimeout3;
                }
                node.next = prev.next;
                prev.next = node;
                if (prev == AsyncTimeout.head) {
                    AsyncTimeout.class.notify();
                }
                Unit unit = Unit.INSTANCE;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean cancelScheduledTimeout(AsyncTimeout node) {
            synchronized (AsyncTimeout.class) {
                for (AsyncTimeout prev = AsyncTimeout.head; prev != null; prev = prev.next) {
                    if (prev.next == node) {
                        prev.next = node.next;
                        node.next = (AsyncTimeout) null;
                        return false;
                    }
                }
                return true;
            }
        }

        @Nullable
        public final AsyncTimeout awaitTimeout$okio() throws InterruptedException {
            AsyncTimeout asyncTimeout = AsyncTimeout.head;
            Intrinsics.checkNotNull(asyncTimeout);
            AsyncTimeout node = asyncTimeout.next;
            if (node != null) {
                long waitNanos = node.remainingNanos(System.nanoTime());
                if (waitNanos <= 0) {
                    AsyncTimeout asyncTimeout2 = AsyncTimeout.head;
                    Intrinsics.checkNotNull(asyncTimeout2);
                    asyncTimeout2.next = node.next;
                    node.next = (AsyncTimeout) null;
                    return node;
                }
                long waitMillis = waitNanos / StaticHandler.DEFAULT_MAX_AVG_SERVE_TIME_NS;
                AsyncTimeout.class.wait(waitMillis, (int) (waitNanos - (waitMillis * StaticHandler.DEFAULT_MAX_AVG_SERVE_TIME_NS)));
                return null;
            }
            long startNanos = System.nanoTime();
            AsyncTimeout.class.wait(AsyncTimeout.IDLE_TIMEOUT_MILLIS);
            AsyncTimeout asyncTimeout3 = AsyncTimeout.head;
            Intrinsics.checkNotNull(asyncTimeout3);
            if (asyncTimeout3.next == null && System.nanoTime() - startNanos >= AsyncTimeout.IDLE_TIMEOUT_NANOS) {
                return AsyncTimeout.head;
            }
            return null;
        }
    }
}
