package io.vertx.kotlin.coroutines;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: VertxCoroutine.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n��\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n��\b\u0002\u0018��2\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0014\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012H\u0016J\b\u0010\u0013\u001a\u00020\tH\u0016J\b\u0010\u0014\u001a\u00020\tH\u0016J6\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0016\"\u0004\b��\u0010\u00172\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u0002H\u0017\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J(\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00162\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u00122\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J2\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u00162\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u00122\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J6\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u00162\u000e\u0010\u0010\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u00122\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u001f\u001a\u00020\u000fH\u0016J\u0012\u0010 \u001a\f\u0012\b\u0012\u00060\u0011j\u0002`\u00120!H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\""}, d2 = {"Lio/vertx/kotlin/coroutines/VertxCoroutineExecutor;", "Ljava/util/concurrent/AbstractExecutorService;", "Ljava/util/concurrent/ScheduledExecutorService;", "vertxContext", "Lio/vertx/core/Context;", "(Lio/vertx/core/Context;)V", "getVertxContext", "()Lio/vertx/core/Context;", "awaitTermination", "", RtspHeaders.Values.TIMEOUT, "", "unit", "Ljava/util/concurrent/TimeUnit;", "execute", "", "command", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "isShutdown", "isTerminated", "schedule", "Ljava/util/concurrent/ScheduledFuture;", "V", "callable", "Ljava/util/concurrent/Callable;", "delay", "scheduleAtFixedRate", "initialDelay", "period", "scheduleWithFixedDelay", "shutdown", "shutdownNow", "", "vertx-lang-kotlin-coroutines"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-coroutines-3.8.5.jar:io/vertx/kotlin/coroutines/VertxCoroutineExecutor.class */
final class VertxCoroutineExecutor extends AbstractExecutorService implements ScheduledExecutorService {

    @NotNull
    private final Context vertxContext;

    @NotNull
    public final Context getVertxContext() {
        return this.vertxContext;
    }

    public VertxCoroutineExecutor(@NotNull Context vertxContext) {
        Intrinsics.checkParameterIsNotNull(vertxContext, "vertxContext");
        this.vertxContext = vertxContext;
    }

    @Override // java.util.concurrent.Executor
    public void execute(@NotNull final Runnable command) {
        Intrinsics.checkParameterIsNotNull(command, "command");
        if (!Intrinsics.areEqual(Vertx.currentContext(), this.vertxContext)) {
            this.vertxContext.runOnContext(new Handler<Void>() { // from class: io.vertx.kotlin.coroutines.VertxCoroutineExecutor.execute.1
                @Override // io.vertx.core.Handler
                public final void handle(Void it) {
                    command.run();
                }
            });
        } else {
            command.run();
        }
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    @NotNull
    public ScheduledFuture<?> schedule(@NotNull Runnable command, long delay, @NotNull TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(command, "command");
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        VertxScheduledFuture t = new VertxScheduledFuture(this.vertxContext, command, delay, unit);
        t.schedule();
        return t;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    @NotNull
    public ScheduledFuture<?> scheduleAtFixedRate(@NotNull Runnable command, long initialDelay, long period, @Nullable TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(command, "command");
        throw new UnsupportedOperationException("should not be called");
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    @NotNull
    public <V> ScheduledFuture<V> schedule(@Nullable Callable<V> callable, long delay, @Nullable TimeUnit unit) {
        throw new UnsupportedOperationException("should not be called");
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    @NotNull
    public ScheduledFuture<?> scheduleWithFixedDelay(@Nullable Runnable command, long initialDelay, long delay, @Nullable TimeUnit unit) {
        throw new UnsupportedOperationException("should not be called");
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        throw new UnsupportedOperationException("should not be called");
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        throw new UnsupportedOperationException("should not be called");
    }

    @Override // java.util.concurrent.ExecutorService
    @NotNull
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException("should not be called");
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        throw new UnsupportedOperationException("should not be called");
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long timeout, @Nullable TimeUnit unit) {
        throw new UnsupportedOperationException("should not be called");
    }
}
