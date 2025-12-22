package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Pipe.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\"2\u0006\u0010\u0017\u001a\u00020\u0010J\r\u0010\u0017\u001a\u00020\u0010H\u0007¢\u0006\u0002\b$J\r\u0010\u001b\u001a\u00020\u001cH\u0007¢\u0006\u0002\b%J&\u0010&\u001a\u00020\"*\u00020\u00102\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\"0(¢\u0006\u0002\b)H\u0082\bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u00020\u00108G¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0012R\u001a\u0010\u0018\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR\u0013\u0010\u001b\u001a\u00020\u001c8G¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001f\u0010\f\"\u0004\b \u0010\u000e¨\u0006*"}, d2 = {"Lokio/Pipe;", "", "maxBufferSize", "", "(J)V", "buffer", "Lokio/Buffer;", "getBuffer$okio", "()Lokio/Buffer;", "canceled", "", "getCanceled$okio", "()Z", "setCanceled$okio", "(Z)V", "foldedSink", "Lokio/Sink;", "getFoldedSink$okio", "()Lokio/Sink;", "setFoldedSink$okio", "(Lokio/Sink;)V", "getMaxBufferSize$okio", "()J", "sink", "sinkClosed", "getSinkClosed$okio", "setSinkClosed$okio", PackageDocumentBase.DCTags.source, "Lokio/Source;", "()Lokio/Source;", "sourceClosed", "getSourceClosed$okio", "setSourceClosed$okio", "cancel", "", "fold", "-deprecated_sink", "-deprecated_source", "forward", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "okio"})
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/Pipe.class */
public final class Pipe {

    @NotNull
    private final Buffer buffer = new Buffer();
    private boolean canceled;
    private boolean sinkClosed;
    private boolean sourceClosed;

    @Nullable
    private Sink foldedSink;

    @NotNull
    private final Sink sink;

    @NotNull
    private final Source source;
    private final long maxBufferSize;

    public final long getMaxBufferSize$okio() {
        return this.maxBufferSize;
    }

    public Pipe(long maxBufferSize) {
        this.maxBufferSize = maxBufferSize;
        if (!(this.maxBufferSize >= 1)) {
            throw new IllegalArgumentException(("maxBufferSize < 1: " + this.maxBufferSize).toString());
        }
        this.sink = new Sink() { // from class: okio.Pipe.sink.1
            private final Timeout timeout = new Timeout();

            /* JADX WARN: Code restructure failed: missing block: B:37:0x0137, code lost:
            
                r0 = kotlin.Unit.INSTANCE;
             */
            @Override // okio.Sink
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void write(@org.jetbrains.annotations.NotNull okio.Buffer r8, long r9) {
                /*
                    Method dump skipped, instructions count: 637
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: okio.Pipe.AnonymousClass1.write(okio.Buffer, long):void");
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() {
                Sink sink = (Sink) null;
                Object lock$iv = Pipe.this.getBuffer$okio();
                synchronized (lock$iv) {
                    if (!(!Pipe.this.getSinkClosed$okio())) {
                        throw new IllegalStateException("closed".toString());
                    }
                    if (Pipe.this.getCanceled$okio()) {
                        throw new IOException("canceled");
                    }
                    Sink it = Pipe.this.getFoldedSink$okio();
                    if (it != null) {
                        sink = it;
                    } else if (Pipe.this.getSourceClosed$okio() && Pipe.this.getBuffer$okio().size() > 0) {
                        throw new IOException("source is closed");
                    }
                    Unit unit = Unit.INSTANCE;
                }
                Sink $this$forward$iv = sink;
                if ($this$forward$iv == null) {
                    return;
                }
                Pipe this_$iv = Pipe.this;
                Timeout this_$iv$iv = $this$forward$iv.timeout();
                Timeout other$iv$iv = this_$iv.sink().timeout();
                long originalTimeout$iv$iv = this_$iv$iv.timeoutNanos();
                this_$iv$iv.timeout(Timeout.Companion.minTimeout(other$iv$iv.timeoutNanos(), this_$iv$iv.timeoutNanos()), TimeUnit.NANOSECONDS);
                if (this_$iv$iv.hasDeadline()) {
                    long originalDeadline$iv$iv = this_$iv$iv.deadlineNanoTime();
                    if (other$iv$iv.hasDeadline()) {
                        this_$iv$iv.deadlineNanoTime(Math.min(this_$iv$iv.deadlineNanoTime(), other$iv$iv.deadlineNanoTime()));
                    }
                    try {
                        $this$forward$iv.flush();
                        this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                        if (!other$iv$iv.hasDeadline()) {
                            return;
                        }
                        this_$iv$iv.deadlineNanoTime(originalDeadline$iv$iv);
                        return;
                    } catch (Throwable th) {
                        this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                        if (other$iv$iv.hasDeadline()) {
                            this_$iv$iv.deadlineNanoTime(originalDeadline$iv$iv);
                        }
                        throw th;
                    }
                }
                if (other$iv$iv.hasDeadline()) {
                    this_$iv$iv.deadlineNanoTime(other$iv$iv.deadlineNanoTime());
                }
                try {
                    $this$forward$iv.flush();
                    this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                    if (!other$iv$iv.hasDeadline()) {
                        return;
                    }
                    this_$iv$iv.clearDeadline();
                } catch (Throwable th2) {
                    this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                    if (other$iv$iv.hasDeadline()) {
                        this_$iv$iv.clearDeadline();
                    }
                    throw th2;
                }
            }

            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                Sink sink = (Sink) null;
                Object lock$iv = Pipe.this.getBuffer$okio();
                synchronized (lock$iv) {
                    if (Pipe.this.getSinkClosed$okio()) {
                        return;
                    }
                    Sink it = Pipe.this.getFoldedSink$okio();
                    if (it != null) {
                        sink = it;
                    } else {
                        if (Pipe.this.getSourceClosed$okio() && Pipe.this.getBuffer$okio().size() > 0) {
                            throw new IOException("source is closed");
                        }
                        Pipe.this.setSinkClosed$okio(true);
                        Buffer buffer$okio = Pipe.this.getBuffer$okio();
                        if (buffer$okio == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                        }
                        buffer$okio.notifyAll();
                    }
                    Unit unit = Unit.INSTANCE;
                    Sink $this$forward$iv = sink;
                    if ($this$forward$iv == null) {
                        return;
                    }
                    Pipe this_$iv = Pipe.this;
                    Timeout this_$iv$iv = $this$forward$iv.timeout();
                    Timeout other$iv$iv = this_$iv.sink().timeout();
                    long originalTimeout$iv$iv = this_$iv$iv.timeoutNanos();
                    this_$iv$iv.timeout(Timeout.Companion.minTimeout(other$iv$iv.timeoutNanos(), this_$iv$iv.timeoutNanos()), TimeUnit.NANOSECONDS);
                    if (this_$iv$iv.hasDeadline()) {
                        long originalDeadline$iv$iv = this_$iv$iv.deadlineNanoTime();
                        if (other$iv$iv.hasDeadline()) {
                            this_$iv$iv.deadlineNanoTime(Math.min(this_$iv$iv.deadlineNanoTime(), other$iv$iv.deadlineNanoTime()));
                        }
                        try {
                            $this$forward$iv.close();
                            this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                            if (!other$iv$iv.hasDeadline()) {
                                return;
                            }
                            this_$iv$iv.deadlineNanoTime(originalDeadline$iv$iv);
                            return;
                        } catch (Throwable th) {
                            this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                            if (other$iv$iv.hasDeadline()) {
                                this_$iv$iv.deadlineNanoTime(originalDeadline$iv$iv);
                            }
                            throw th;
                        }
                    }
                    if (other$iv$iv.hasDeadline()) {
                        this_$iv$iv.deadlineNanoTime(other$iv$iv.deadlineNanoTime());
                    }
                    try {
                        $this$forward$iv.close();
                        this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                        if (!other$iv$iv.hasDeadline()) {
                            return;
                        }
                        this_$iv$iv.clearDeadline();
                    } catch (Throwable th2) {
                        this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                        if (other$iv$iv.hasDeadline()) {
                            this_$iv$iv.clearDeadline();
                        }
                        throw th2;
                    }
                }
            }

            @Override // okio.Sink
            @NotNull
            public Timeout timeout() {
                return this.timeout;
            }
        };
        this.source = new Source() { // from class: okio.Pipe.source.1
            private final Timeout timeout = new Timeout();

            @Override // okio.Source
            public long read(@NotNull Buffer sink, long byteCount) {
                Intrinsics.checkNotNullParameter(sink, "sink");
                Object lock$iv = Pipe.this.getBuffer$okio();
                synchronized (lock$iv) {
                    if (!(!Pipe.this.getSourceClosed$okio())) {
                        throw new IllegalStateException("closed".toString());
                    }
                    if (Pipe.this.getCanceled$okio()) {
                        throw new IOException("canceled");
                    }
                    while (Pipe.this.getBuffer$okio().size() == 0) {
                        if (Pipe.this.getSinkClosed$okio()) {
                            return -1L;
                        }
                        this.timeout.waitUntilNotified(Pipe.this.getBuffer$okio());
                        if (Pipe.this.getCanceled$okio()) {
                            throw new IOException("canceled");
                        }
                    }
                    long result = Pipe.this.getBuffer$okio().read(sink, byteCount);
                    Buffer buffer$okio = Pipe.this.getBuffer$okio();
                    if (buffer$okio == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                    }
                    buffer$okio.notifyAll();
                    return result;
                }
            }

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                Object lock$iv = Pipe.this.getBuffer$okio();
                synchronized (lock$iv) {
                    Pipe.this.setSourceClosed$okio(true);
                    Buffer buffer$okio = Pipe.this.getBuffer$okio();
                    if (buffer$okio == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                    }
                    buffer$okio.notifyAll();
                    Unit unit = Unit.INSTANCE;
                }
            }

            @Override // okio.Source
            @NotNull
            public Timeout timeout() {
                return this.timeout;
            }
        };
    }

    @NotNull
    public final Buffer getBuffer$okio() {
        return this.buffer;
    }

    public final boolean getCanceled$okio() {
        return this.canceled;
    }

    public final void setCanceled$okio(boolean z) {
        this.canceled = z;
    }

    public final boolean getSinkClosed$okio() {
        return this.sinkClosed;
    }

    public final void setSinkClosed$okio(boolean z) {
        this.sinkClosed = z;
    }

    public final boolean getSourceClosed$okio() {
        return this.sourceClosed;
    }

    public final void setSourceClosed$okio(boolean z) {
        this.sourceClosed = z;
    }

    @Nullable
    public final Sink getFoldedSink$okio() {
        return this.foldedSink;
    }

    public final void setFoldedSink$okio(@Nullable Sink sink) {
        this.foldedSink = sink;
    }

    @JvmName(name = "sink")
    @NotNull
    public final Sink sink() {
        return this.sink;
    }

    @JvmName(name = PackageDocumentBase.DCTags.source)
    @NotNull
    public final Source source() {
        return this.source;
    }

    public final void fold(@NotNull Sink sink) throws IOException {
        boolean closed;
        Buffer buffer;
        Intrinsics.checkNotNullParameter(sink, "sink");
        while (true) {
            Object lock$iv = this.buffer;
            synchronized (lock$iv) {
                if (!(this.foldedSink == null)) {
                    throw new IllegalStateException("sink already folded".toString());
                }
                if (this.canceled) {
                    this.foldedSink = sink;
                    throw new IOException("canceled");
                }
                if (this.buffer.exhausted()) {
                    this.sourceClosed = true;
                    this.foldedSink = sink;
                    return;
                }
                closed = this.sinkClosed;
                buffer = new Buffer();
                buffer.write(this.buffer, this.buffer.size());
                Buffer buffer2 = this.buffer;
                if (buffer2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                }
                buffer2.notifyAll();
                Unit unit = Unit.INSTANCE;
            }
            try {
                sink.write(buffer, buffer.size());
                if (closed) {
                    sink.close();
                } else {
                    sink.flush();
                }
            } catch (Throwable th) {
                synchronized (this.buffer) {
                    this.sourceClosed = true;
                    Buffer buffer3 = this.buffer;
                    if (buffer3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                    }
                    buffer3.notifyAll();
                    Unit unit2 = Unit.INSTANCE;
                    throw th;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void forward(Sink $this$forward, Function1<? super Sink, Unit> function1) {
        Timeout this_$iv = $this$forward.timeout();
        Timeout other$iv = sink().timeout();
        long originalTimeout$iv = this_$iv.timeoutNanos();
        this_$iv.timeout(Timeout.Companion.minTimeout(other$iv.timeoutNanos(), this_$iv.timeoutNanos()), TimeUnit.NANOSECONDS);
        if (this_$iv.hasDeadline()) {
            long originalDeadline$iv = this_$iv.deadlineNanoTime();
            if (other$iv.hasDeadline()) {
                this_$iv.deadlineNanoTime(Math.min(this_$iv.deadlineNanoTime(), other$iv.deadlineNanoTime()));
            }
            try {
                function1.invoke($this$forward);
                InlineMarker.finallyStart(1);
                this_$iv.timeout(originalTimeout$iv, TimeUnit.NANOSECONDS);
                if (other$iv.hasDeadline()) {
                    this_$iv.deadlineNanoTime(originalDeadline$iv);
                }
                InlineMarker.finallyEnd(1);
                return;
            } finally {
                InlineMarker.finallyStart(1);
                this_$iv.timeout(originalTimeout$iv, TimeUnit.NANOSECONDS);
                if (other$iv.hasDeadline()) {
                    this_$iv.deadlineNanoTime(originalDeadline$iv);
                }
                InlineMarker.finallyEnd(1);
            }
        }
        if (other$iv.hasDeadline()) {
            this_$iv.deadlineNanoTime(other$iv.deadlineNanoTime());
        }
        try {
            function1.invoke($this$forward);
            InlineMarker.finallyStart(1);
            this_$iv.timeout(originalTimeout$iv, TimeUnit.NANOSECONDS);
            if (other$iv.hasDeadline()) {
                this_$iv.clearDeadline();
            }
            InlineMarker.finallyEnd(1);
        } finally {
            InlineMarker.finallyStart(1);
            this_$iv.timeout(originalTimeout$iv, TimeUnit.NANOSECONDS);
            if (other$iv.hasDeadline()) {
                this_$iv.clearDeadline();
            }
            InlineMarker.finallyEnd(1);
        }
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "sink"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_sink")
    @NotNull
    /* renamed from: -deprecated_sink, reason: not valid java name */
    public final Sink m4671deprecated_sink() {
        return this.sink;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = PackageDocumentBase.DCTags.source), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_source")
    @NotNull
    /* renamed from: -deprecated_source, reason: not valid java name */
    public final Source m4672deprecated_source() {
        return this.source;
    }

    public final void cancel() {
        Object lock$iv = this.buffer;
        synchronized (lock$iv) {
            this.canceled = true;
            this.buffer.clear();
            Buffer buffer = this.buffer;
            if (buffer == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
            }
            buffer.notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }
}
