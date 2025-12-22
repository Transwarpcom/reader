package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

/* compiled from: Debug.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��(\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015H\u0081\b\u001a\b\u0010\u0016\u001a\u00020\u0013H��\"\u0014\u0010��\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007\"\u0014\u0010\b\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\u0003\"\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n��\"\u000e\u0010\f\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n��\"\u000e\u0010\r\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n��\"\u000e\u0010\u000e\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n��\"\u0014\u0010\u000f\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0003\"\u000e\u0010\u0011\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n��¨\u0006\u0017"}, d2 = {"ASSERTIONS_ENABLED", "", "getASSERTIONS_ENABLED", "()Z", "COROUTINE_ID", "Ljava/util/concurrent/atomic/AtomicLong;", "getCOROUTINE_ID", "()Ljava/util/concurrent/atomic/AtomicLong;", "DEBUG", "getDEBUG", "DEBUG_PROPERTY_NAME", "", "DEBUG_PROPERTY_VALUE_AUTO", "DEBUG_PROPERTY_VALUE_OFF", "DEBUG_PROPERTY_VALUE_ON", "RECOVER_STACK_TRACES", "getRECOVER_STACK_TRACES", "STACKTRACE_RECOVERY_PROPERTY_NAME", "assert", "", "value", "Lkotlin/Function0;", "resetCoroutineId", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/DebugKt.class */
public final class DebugKt {

    @NotNull
    public static final String DEBUG_PROPERTY_NAME = "kotlinx.coroutines.debug";

    @NotNull
    public static final String STACKTRACE_RECOVERY_PROPERTY_NAME = "kotlinx.coroutines.stacktrace.recovery";

    @NotNull
    public static final String DEBUG_PROPERTY_VALUE_AUTO = "auto";

    @NotNull
    public static final String DEBUG_PROPERTY_VALUE_ON = "on";

    @NotNull
    public static final String DEBUG_PROPERTY_VALUE_OFF = "off";
    private static final boolean ASSERTIONS_ENABLED = CoroutineId.class.desiredAssertionStatus();
    private static final boolean DEBUG;
    private static final boolean RECOVER_STACK_TRACES;

    @NotNull
    private static final AtomicLong COROUTINE_ID;

    public static final boolean getASSERTIONS_ENABLED() {
        return ASSERTIONS_ENABLED;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0064, code lost:
    
        if (r0.equals(kotlinx.coroutines.DebugKt.DEBUG_PROPERTY_VALUE_AUTO) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x007e, code lost:
    
        if (r0.equals("on") == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x008a, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0057, code lost:
    
        if (r0.equals("") == false) goto L21;
     */
    static {
        /*
            java.lang.Class<kotlinx.coroutines.CoroutineId> r0 = kotlinx.coroutines.CoroutineId.class
            boolean r0 = r0.desiredAssertionStatus()
            kotlinx.coroutines.DebugKt.ASSERTIONS_ENABLED = r0
            java.lang.String r0 = "kotlinx.coroutines.debug"
            java.lang.String r0 = kotlinx.coroutines.internal.SystemPropsKt.systemProp(r0)
            r5 = r0
            r0 = 0
            r6 = r0
            r0 = 0
            r7 = r0
            r0 = r5
            r8 = r0
            r0 = 0
            r9 = r0
            r0 = r8
            r10 = r0
            r0 = r10
            if (r0 == 0) goto L84
            r0 = r10
            int r0 = r0.hashCode()
            switch(r0) {
                case 0: goto L50;
                case 3551: goto L77;
                case 109935: goto L6a;
                case 3005871: goto L5d;
                default: goto L92;
            }
        L50:
            r0 = r10
            java.lang.String r1 = ""
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L8a
            goto L92
        L5d:
            r0 = r10
            java.lang.String r1 = "auto"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L84
            goto L92
        L6a:
            r0 = r10
            java.lang.String r1 = "off"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L8e
            goto L92
        L77:
            r0 = r10
            java.lang.String r1 = "on"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L8a
            goto L92
        L84:
            boolean r0 = getASSERTIONS_ENABLED()
            goto Lbf
        L8a:
            r0 = 1
            goto Lbf
        L8e:
            r0 = 0
            goto Lbf
        L92:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r1.<init>()
            java.lang.String r1 = "System property 'kotlinx.coroutines.debug' has unrecognized value '"
            java.lang.StringBuilder r0 = r0.append(r1)
            r1 = r8
            java.lang.StringBuilder r0 = r0.append(r1)
            r1 = 39
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r11 = r0
            r0 = 0
            r12 = r0
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            r2 = r11
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        Lbf:
            kotlinx.coroutines.DebugKt.DEBUG = r0
            boolean r0 = kotlinx.coroutines.DebugKt.DEBUG
            if (r0 == 0) goto Ld7
            java.lang.String r0 = "kotlinx.coroutines.stacktrace.recovery"
            r1 = 1
            boolean r0 = kotlinx.coroutines.internal.SystemPropsKt.systemProp(r0, r1)
            if (r0 == 0) goto Ld7
            r0 = 1
            goto Ld8
        Ld7:
            r0 = 0
        Ld8:
            kotlinx.coroutines.DebugKt.RECOVER_STACK_TRACES = r0
            java.util.concurrent.atomic.AtomicLong r0 = new java.util.concurrent.atomic.AtomicLong
            r1 = r0
            r2 = 0
            r1.<init>(r2)
            kotlinx.coroutines.DebugKt.COROUTINE_ID = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.DebugKt.m4176clinit():void");
    }

    public static final boolean getDEBUG() {
        return DEBUG;
    }

    public static final boolean getRECOVER_STACK_TRACES() {
        return RECOVER_STACK_TRACES;
    }

    @NotNull
    public static final AtomicLong getCOROUTINE_ID() {
        return COROUTINE_ID;
    }

    public static final void resetCoroutineId() {
        COROUTINE_ID.set(0L);
    }

    @InlineOnly
    /* renamed from: assert, reason: not valid java name */
    private static final void m4175assert(Function0<Boolean> function0) {
        if (getASSERTIONS_ENABLED() && !function0.invoke().booleanValue()) {
            throw new AssertionError();
        }
    }
}
