package io.vertx.kotlin.coroutines;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: VertxCoroutine.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00040\u0003B)\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\u0010\u0007\u001a\u00060\bj\u0002`\t\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u0010H\u0016J\u0011\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0096\u0002J\n\u0010'\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010'\u001a\u0004\u0018\u00010\u00022\u0006\u0010(\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0096\u0002J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\fH\u0016J\u0017\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010\u0019J\b\u0010-\u001a\u00020\u0010H\u0016J\b\u0010.\u001a\u00020\u0010H\u0016J\u0006\u0010/\u001a\u00020+R\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0015\u0010\u0007\u001a\u00060\bj\u0002`\t¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n��\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u001f\u0010 ¨\u00060"}, d2 = {"Lio/vertx/kotlin/coroutines/VertxScheduledFuture;", "Ljava/util/concurrent/ScheduledFuture;", "", "Lio/vertx/core/Handler;", "", "vertxContext", "Lio/vertx/core/Context;", "task", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "delay", "unit", "Ljava/util/concurrent/TimeUnit;", "(Lio/vertx/core/Context;Ljava/lang/Runnable;JLjava/util/concurrent/TimeUnit;)V", "completion", "Ljava/util/concurrent/atomic/AtomicReference;", "", "getCompletion", "()Ljava/util/concurrent/atomic/AtomicReference;", "getDelay", "()J", "id", "getId", "()Ljava/lang/Long;", "setId", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getTask", "()Ljava/lang/Runnable;", "getUnit", "()Ljava/util/concurrent/TimeUnit;", "getVertxContext", "()Lio/vertx/core/Context;", "cancel", "mayInterruptIfRunning", "compareTo", "", "other", "Ljava/util/concurrent/Delayed;", BeanUtil.PREFIX_GETTER_GET, RtspHeaders.Values.TIMEOUT, "u", "handle", "", PackageDocumentBase.OPFAttributes.event, "isCancelled", "isDone", "schedule", "vertx-lang-kotlin-coroutines"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-coroutines-3.8.5.jar:io/vertx/kotlin/coroutines/VertxScheduledFuture.class */
final class VertxScheduledFuture implements ScheduledFuture<Object>, Handler<Long> {

    @NotNull
    private final AtomicReference<Boolean> completion;

    @Nullable
    private Long id;

    @NotNull
    private final Context vertxContext;

    @NotNull
    private final Runnable task;
    private final long delay;

    @NotNull
    private final TimeUnit unit;

    public VertxScheduledFuture(@NotNull Context vertxContext, @NotNull Runnable task, long delay, @NotNull TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(vertxContext, "vertxContext");
        Intrinsics.checkParameterIsNotNull(task, "task");
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        this.vertxContext = vertxContext;
        this.task = task;
        this.delay = delay;
        this.unit = unit;
        this.completion = new AtomicReference<>();
    }

    @NotNull
    public final Context getVertxContext() {
        return this.vertxContext;
    }

    @NotNull
    public final Runnable getTask() {
        return this.task;
    }

    public final long getDelay() {
        return this.delay;
    }

    @NotNull
    public final TimeUnit getUnit() {
        return this.unit;
    }

    @NotNull
    public final AtomicReference<Boolean> getCompletion() {
        return this.completion;
    }

    @Nullable
    public final Long getId() {
        return this.id;
    }

    public final void setId(@Nullable Long l) {
        this.id = l;
    }

    public final void schedule() {
        Vertx owner = this.vertxContext.owner();
        this.id = Long.valueOf(owner.setTimer(this.unit.toMillis(this.delay), this));
    }

    @Override // java.util.concurrent.Future
    @Nullable
    public Object get() {
        return null;
    }

    @Override // java.util.concurrent.Future
    @Nullable
    public Object get(long timeout, @Nullable TimeUnit unit) {
        return null;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return Intrinsics.areEqual((Object) this.completion.get(), (Object) false);
    }

    @Override // io.vertx.core.Handler
    public void handle(@Nullable Long event) {
        if (this.completion.compareAndSet(null, true)) {
            this.task.run();
        }
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean mayInterruptIfRunning) {
        if (this.completion.compareAndSet(null, false)) {
            Vertx vertxOwner = this.vertxContext.owner();
            Long l = this.id;
            if (l == null) {
                Intrinsics.throwNpe();
            }
            return vertxOwner.cancelTimer(l.longValue());
        }
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return Intrinsics.areEqual((Object) this.completion.get(), (Object) true);
    }

    @Override // java.util.concurrent.Delayed
    public long getDelay(@NotNull TimeUnit u) {
        Intrinsics.checkParameterIsNotNull(u, "u");
        return u.convert(this.unit.toNanos(this.delay), TimeUnit.NANOSECONDS);
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull Delayed other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return (getDelay(TimeUnit.NANOSECONDS) > other.getDelay(TimeUnit.NANOSECONDS) ? 1 : (getDelay(TimeUnit.NANOSECONDS) == other.getDelay(TimeUnit.NANOSECONDS) ? 0 : -1));
    }
}
