package kotlin.concurrent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Timer.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��4\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001aM\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001��\u001aO\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001��\u001a\u001a\u0010\u0010\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001aM\u0010\u0010\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001��\u001aO\u0010\u0010\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001��\u001a'\u0010\u0011\u001a\u00020\f2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001��\u001a3\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001��\u001a;\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001��\u001a3\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001��\u001a;\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001��\u001a;\u0010\u0015\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001��\u001a;\u0010\u0015\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001��\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0016"}, d2 = {"fixedRateTimer", "Ljava/util/Timer;", "name", "", "daemon", "", "startAt", "Ljava/util/Date;", "period", "", "action", "Lkotlin/Function1;", "Ljava/util/TimerTask;", "", "Lkotlin/ExtensionFunctionType;", "initialDelay", "timer", "timerTask", "schedule", "time", "delay", "scheduleAtFixedRate", "kotlin-stdlib"})
@JvmName(name = "TimersKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/concurrent/TimersKt.class */
public final class TimersKt {
    @InlineOnly
    private static final TimerTask schedule(Timer $this$schedule, long delay, Function1<? super TimerTask, Unit> function1) {
        TimerTask task = new AnonymousClass1(function1);
        $this$schedule.schedule(task, delay);
        return task;
    }

    @InlineOnly
    private static final TimerTask schedule(Timer $this$schedule, Date time, Function1<? super TimerTask, Unit> function1) {
        TimerTask task = new AnonymousClass1(function1);
        $this$schedule.schedule(task, time);
        return task;
    }

    @InlineOnly
    private static final TimerTask schedule(Timer $this$schedule, long delay, long period, Function1<? super TimerTask, Unit> function1) {
        TimerTask task = new AnonymousClass1(function1);
        $this$schedule.schedule(task, delay, period);
        return task;
    }

    @InlineOnly
    private static final TimerTask schedule(Timer $this$schedule, Date time, long period, Function1<? super TimerTask, Unit> function1) {
        TimerTask task = new AnonymousClass1(function1);
        $this$schedule.schedule(task, time, period);
        return task;
    }

    @InlineOnly
    private static final TimerTask scheduleAtFixedRate(Timer $this$scheduleAtFixedRate, long delay, long period, Function1<? super TimerTask, Unit> function1) {
        TimerTask task = new AnonymousClass1(function1);
        $this$scheduleAtFixedRate.scheduleAtFixedRate(task, delay, period);
        return task;
    }

    @InlineOnly
    private static final TimerTask scheduleAtFixedRate(Timer $this$scheduleAtFixedRate, Date time, long period, Function1<? super TimerTask, Unit> function1) {
        TimerTask task = new AnonymousClass1(function1);
        $this$scheduleAtFixedRate.scheduleAtFixedRate(task, time, period);
        return task;
    }

    @PublishedApi
    @NotNull
    public static final Timer timer(@Nullable String name, boolean daemon) {
        return name == null ? new Timer(daemon) : new Timer(name, daemon);
    }

    static /* synthetic */ Timer timer$default(String name, boolean daemon, long initialDelay, long period, Function1 action, int i, Object obj) {
        if ((i & 1) != 0) {
            name = (String) null;
        }
        if ((i & 2) != 0) {
            daemon = false;
        }
        if ((i & 4) != 0) {
            initialDelay = 0;
        }
        Timer timer = timer(name, daemon);
        timer.schedule(new AnonymousClass1(action), initialDelay, period);
        return timer;
    }

    @InlineOnly
    private static final Timer timer(String name, boolean daemon, long initialDelay, long period, Function1<? super TimerTask, Unit> function1) {
        Timer timer = timer(name, daemon);
        timer.schedule(new AnonymousClass1(function1), initialDelay, period);
        return timer;
    }

    static /* synthetic */ Timer timer$default(String name, boolean daemon, Date startAt, long period, Function1 action, int i, Object obj) {
        if ((i & 1) != 0) {
            name = (String) null;
        }
        if ((i & 2) != 0) {
            daemon = false;
        }
        Timer timer = timer(name, daemon);
        timer.schedule(new AnonymousClass1(action), startAt, period);
        return timer;
    }

    @InlineOnly
    private static final Timer timer(String name, boolean daemon, Date startAt, long period, Function1<? super TimerTask, Unit> function1) {
        Timer timer = timer(name, daemon);
        timer.schedule(new AnonymousClass1(function1), startAt, period);
        return timer;
    }

    static /* synthetic */ Timer fixedRateTimer$default(String name, boolean daemon, long initialDelay, long period, Function1 action, int i, Object obj) {
        if ((i & 1) != 0) {
            name = (String) null;
        }
        if ((i & 2) != 0) {
            daemon = false;
        }
        if ((i & 4) != 0) {
            initialDelay = 0;
        }
        Timer timer = timer(name, daemon);
        timer.scheduleAtFixedRate(new AnonymousClass1(action), initialDelay, period);
        return timer;
    }

    @InlineOnly
    private static final Timer fixedRateTimer(String name, boolean daemon, long initialDelay, long period, Function1<? super TimerTask, Unit> function1) {
        Timer timer = timer(name, daemon);
        timer.scheduleAtFixedRate(new AnonymousClass1(function1), initialDelay, period);
        return timer;
    }

    static /* synthetic */ Timer fixedRateTimer$default(String name, boolean daemon, Date startAt, long period, Function1 action, int i, Object obj) {
        if ((i & 1) != 0) {
            name = (String) null;
        }
        if ((i & 2) != 0) {
            daemon = false;
        }
        Timer timer = timer(name, daemon);
        timer.scheduleAtFixedRate(new AnonymousClass1(action), startAt, period);
        return timer;
    }

    @InlineOnly
    private static final Timer fixedRateTimer(String name, boolean daemon, Date startAt, long period, Function1<? super TimerTask, Unit> function1) {
        Timer timer = timer(name, daemon);
        timer.scheduleAtFixedRate(new AnonymousClass1(function1), startAt, period);
        return timer;
    }

    /* compiled from: Timer.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u0011\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��*\u0001��\b\n\u0018��2\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"kotlin/concurrent/TimersKt$timerTask$1", "Ljava/util/TimerTask;", "run", "", "kotlin-stdlib"})
    /* renamed from: kotlin.concurrent.TimersKt$timerTask$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/concurrent/TimersKt$timerTask$1.class */
    public static final class AnonymousClass1 extends TimerTask {
        final /* synthetic */ Function1 $action;

        public AnonymousClass1(Function1 $captured_local_variable$0) {
            this.$action = $captured_local_variable$0;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            this.$action.invoke(this);
        }
    }

    @InlineOnly
    private static final TimerTask timerTask(Function1<? super TimerTask, Unit> function1) {
        return new AnonymousClass1(function1);
    }
}
