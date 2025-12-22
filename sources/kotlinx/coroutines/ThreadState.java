package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* compiled from: Interruptible.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\b\u0002\u0018��2#\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00050\u001bj\u0002`\u001eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000f\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0096\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0005¢\u0006\u0004\b\u0011\u0010\u0007R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u0015R\u001c\u0010\u0018\u001a\n \u0017*\u0004\u0018\u00010\u00160\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lkotlinx/coroutines/ThreadState;", "Lkotlinx/coroutines/Job;", "job", Constants.CONSTRUCTOR_NAME, "(Lkotlinx/coroutines/Job;)V", "", "clearInterrupt", "()V", "", "state", "", "invalidState", "(I)Ljava/lang/Void;", "", "cause", "invoke", "(Ljava/lang/Throwable;)V", "setup", "Lkotlinx/coroutines/DisposableHandle;", "cancelHandle", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/Job;", "Ljava/lang/Thread;", "kotlin.jvm.PlatformType", "targetThread", "Ljava/lang/Thread;", "kotlinx-coroutines-core", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/ThreadState.class */
final class ThreadState implements Function1<Throwable, Unit> {

    @NotNull
    private final Job job;
    private static final /* synthetic */ AtomicIntegerFieldUpdater _state$FU = AtomicIntegerFieldUpdater.newUpdater(ThreadState.class, "_state");

    @Nullable
    private DisposableHandle cancelHandle;

    @NotNull
    private volatile /* synthetic */ int _state = 0;
    private final Thread targetThread = Thread.currentThread();

    public ThreadState(@NotNull Job job) {
        this.job = job;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:200)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:61)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:103)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    public final void setup() {
        /*
            r6 = this;
            r0 = r6
            r1 = r6
            kotlinx.coroutines.Job r1 = r1.job
            r2 = 1
            r3 = 1
            r4 = r6
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            kotlinx.coroutines.DisposableHandle r1 = r1.invokeOnCompletion(r2, r3, r4)
            r0.cancelHandle = r1
            r0 = r6
            r7 = r0
            r0 = 0
            r8 = r0
        L17:
            r0 = r7
            int r0 = r0._state
            r9 = r0
            r0 = 0
            r10 = r0
            r0 = r9
            r11 = r0
            r0 = r11
            switch(r0) {
                case 0: goto L44;
                case 1: goto L53;
                case 2: goto L52;
                case 3: goto L52;
                default: goto L53;
            }
        L44:
            r0 = r6
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r1 = kotlinx.coroutines.ThreadState._state$FU
            r2 = r0; r0 = r1; r1 = r2; 
            r2 = r9
            r3 = 0
            boolean r0 = r0.compareAndSet(r1, r2, r3)
            if (r0 == 0) goto L61
            return
        L52:
            return
        L53:
            r0 = r6
            r1 = r9
            java.lang.Void r0 = r0.invalidState(r1)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r1 = r0
            r1.<init>()
            throw r0
        L61:
            goto L17
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.ThreadState.setup():void");
    }

    public final void clearInterrupt() {
        while (true) {
            int state = this._state;
            switch (state) {
                case 0:
                    if (!_state$FU.compareAndSet(this, state, 1)) {
                        break;
                    } else {
                        DisposableHandle disposableHandle = this.cancelHandle;
                        if (disposableHandle == null) {
                            return;
                        }
                        disposableHandle.dispose();
                        return;
                    }
                case 1:
                default:
                    invalidState(state);
                    throw new KotlinNothingValueException();
                case 2:
                    break;
                case 3:
                    Thread.interrupted();
                    return;
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:200)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:61)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:103)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(@org.jetbrains.annotations.Nullable java.lang.Throwable r6) {
        /*
            r5 = this;
            r0 = r5
            r7 = r0
            r0 = 0
            r8 = r0
        L4:
            r0 = r7
            int r0 = r0._state
            r9 = r0
            r0 = 0
            r10 = r0
            r0 = r9
            r11 = r0
            r0 = r11
            switch(r0) {
                case 0: goto L34;
                case 1: goto L4f;
                case 2: goto L4f;
                case 3: goto L4f;
                default: goto L50;
            }
        L34:
            r0 = r5
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r1 = kotlinx.coroutines.ThreadState._state$FU
            r2 = r0; r0 = r1; r1 = r2; 
            r2 = r9
            r3 = 2
            boolean r0 = r0.compareAndSet(r1, r2, r3)
            if (r0 == 0) goto L5f
            r0 = r5
            java.lang.Thread r0 = r0.targetThread
            r0.interrupt()
            r0 = r5
            r1 = 3
            r0._state = r1
            return
        L4f:
            return
        L50:
            r0 = r5
            r1 = r9
            java.lang.Void r0 = r0.invalidState(r1)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r1 = r0
            r1.<init>()
            throw r0
        L5f:
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.ThreadState.invoke2(java.lang.Throwable):void");
    }

    private final Void invalidState(int state) {
        throw new IllegalStateException(Intrinsics.stringPlus("Illegal state ", Integer.valueOf(state)).toString());
    }
}
