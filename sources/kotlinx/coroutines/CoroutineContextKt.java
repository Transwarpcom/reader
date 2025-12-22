package kotlinx.coroutines;

import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.DefaultScheduler;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CoroutineContext.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��H\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\b\u0010\u000b\u001a\u00020\fH��\u001a8\u0010\r\u001a\u0002H\u000e\"\u0004\b��\u0010\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0014H\u0080\b¢\u0006\u0002\u0010\u0015\u001a4\u0010\u0016\u001a\u0002H\u000e\"\u0004\b��\u0010\u000e2\u0006\u0010\u0017\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0014H\u0080\b¢\u0006\u0002\u0010\u0018\u001a\u0014\u0010\u0019\u001a\u00020\b*\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\bH\u0007\u001a\u0013\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001c*\u00020\u001dH\u0080\u0010\u001a(\u0010\u001e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001c*\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0017\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0012H��\"\u000e\u0010��\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��\"\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\u0006\"\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u00020\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006 "}, d2 = {"COROUTINES_SCHEDULER_PROPERTY_NAME", "", "DEBUG_THREAD_NAME_SEPARATOR", "useCoroutinesScheduler", "", "getUseCoroutinesScheduler", "()Z", "coroutineName", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineName", "(Lkotlin/coroutines/CoroutineContext;)Ljava/lang/String;", "createDefaultDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "withContinuationContext", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "continuation", "Lkotlin/coroutines/Continuation;", "countOrElement", "", "block", "Lkotlin/Function0;", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withCoroutineContext", CoreConstants.CONTEXT_SCOPE_VALUE, "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "newCoroutineContext", "Lkotlinx/coroutines/CoroutineScope;", "undispatchedCompletion", "Lkotlinx/coroutines/UndispatchedCoroutine;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "updateUndispatchedCompletion", "oldValue", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CoroutineContextKt.class */
public final class CoroutineContextKt {

    @NotNull
    public static final String COROUTINES_SCHEDULER_PROPERTY_NAME = "kotlinx.coroutines.scheduler";
    private static final boolean useCoroutinesScheduler;

    @NotNull
    private static final String DEBUG_THREAD_NAME_SEPARATOR = " @";

    public static final boolean getUseCoroutinesScheduler() {
        return useCoroutinesScheduler;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0061, code lost:
    
        if (r0.equals("on") == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0047, code lost:
    
        if (r0.equals("") == false) goto L17;
     */
    static {
        /*
            java.lang.String r0 = "kotlinx.coroutines.scheduler"
            java.lang.String r0 = kotlinx.coroutines.internal.SystemPropsKt.systemProp(r0)
            r4 = r0
            r0 = 0
            r5 = r0
            r0 = 0
            r6 = r0
            r0 = r4
            r7 = r0
            r0 = 0
            r8 = r0
            r0 = r7
            r9 = r0
            r0 = r9
            if (r0 == 0) goto L67
            r0 = r9
            int r0 = r0.hashCode()
            switch(r0) {
                case 0: goto L40;
                case 3551: goto L5a;
                case 109935: goto L4d;
                default: goto L6f;
            }
        L40:
            r0 = r9
            java.lang.String r1 = ""
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L67
            goto L6f
        L4d:
            r0 = r9
            java.lang.String r1 = "off"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L6b
            goto L6f
        L5a:
            r0 = r9
            java.lang.String r1 = "on"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L67
            goto L6f
        L67:
            r0 = 1
            goto L9c
        L6b:
            r0 = 0
            goto L9c
        L6f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r1.<init>()
            java.lang.String r1 = "System property 'kotlinx.coroutines.scheduler' has unrecognized value '"
            java.lang.StringBuilder r0 = r0.append(r1)
            r1 = r7
            java.lang.StringBuilder r0 = r0.append(r1)
            r1 = 39
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r10 = r0
            r0 = 0
            r11 = r0
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            r2 = r10
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L9c:
            kotlinx.coroutines.CoroutineContextKt.useCoroutinesScheduler = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CoroutineContextKt.m4165clinit():void");
    }

    @NotNull
    public static final CoroutineDispatcher createDefaultDispatcher() {
        return useCoroutinesScheduler ? DefaultScheduler.INSTANCE : CommonPool.INSTANCE;
    }

    @ExperimentalCoroutinesApi
    @NotNull
    public static final CoroutineContext newCoroutineContext(@NotNull CoroutineScope $this$newCoroutineContext, @NotNull CoroutineContext context) {
        CoroutineContext combined = $this$newCoroutineContext.getCoroutineContext().plus(context);
        CoroutineContext debug = DebugKt.getDEBUG() ? combined.plus(new CoroutineId(DebugKt.getCOROUTINE_ID().incrementAndGet())) : combined;
        return (combined == Dispatchers.getDefault() || combined.get(ContinuationInterceptor.Key) != null) ? debug : debug.plus(Dispatchers.getDefault());
    }

    public static final <T> T withCoroutineContext(@NotNull CoroutineContext context, @Nullable Object countOrElement, @NotNull Function0<? extends T> function0) {
        Object oldValue = ThreadContextKt.updateThreadContext(context, countOrElement);
        try {
            T tInvoke = function0.invoke();
            InlineMarker.finallyStart(1);
            ThreadContextKt.restoreThreadContext(context, oldValue);
            InlineMarker.finallyEnd(1);
            return tInvoke;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            ThreadContextKt.restoreThreadContext(context, oldValue);
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    public static final <T> T withContinuationContext(@NotNull Continuation<?> continuation, @Nullable Object countOrElement, @NotNull Function0<? extends T> function0) {
        UndispatchedCoroutine undispatchedCoroutineUpdateUndispatchedCompletion;
        CoroutineContext context = continuation.getContext();
        Object oldValue = ThreadContextKt.updateThreadContext(context, countOrElement);
        if (oldValue != ThreadContextKt.NO_THREAD_ELEMENTS) {
            undispatchedCoroutineUpdateUndispatchedCompletion = updateUndispatchedCompletion(continuation, context, oldValue);
        } else {
            undispatchedCoroutineUpdateUndispatchedCompletion = (UndispatchedCoroutine) null;
        }
        UndispatchedCoroutine undispatchedCompletion = undispatchedCoroutineUpdateUndispatchedCompletion;
        try {
            T tInvoke = function0.invoke();
            InlineMarker.finallyStart(1);
            if (undispatchedCompletion == null || undispatchedCompletion.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context, oldValue);
            }
            InlineMarker.finallyEnd(1);
            return tInvoke;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            if (undispatchedCompletion == null || undispatchedCompletion.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context, oldValue);
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    @Nullable
    public static final UndispatchedCoroutine<?> updateUndispatchedCompletion(@NotNull Continuation<?> continuation, @NotNull CoroutineContext context, @Nullable Object oldValue) {
        if (!(continuation instanceof CoroutineStackFrame)) {
            return null;
        }
        boolean potentiallyHasUndispatchedCorotuine = context.get(UndispatchedMarker.INSTANCE) != null;
        if (!potentiallyHasUndispatchedCorotuine) {
            return null;
        }
        UndispatchedCoroutine completion = undispatchedCompletion((CoroutineStackFrame) continuation);
        if (completion != null) {
            completion.saveThreadContext(context, oldValue);
        }
        return completion;
    }

    @Nullable
    public static final UndispatchedCoroutine<?> undispatchedCompletion(@NotNull CoroutineStackFrame $this$undispatchedCompletion) {
        CoroutineStackFrame completion;
        CoroutineStackFrame coroutineStackFrame = $this$undispatchedCompletion;
        while (true) {
            CoroutineStackFrame coroutineStackFrame2 = coroutineStackFrame;
            if ((coroutineStackFrame2 instanceof DispatchedCoroutine) || (completion = coroutineStackFrame2.getCallerFrame()) == null) {
                return null;
            }
            if (completion instanceof UndispatchedCoroutine) {
                return (UndispatchedCoroutine) completion;
            }
            coroutineStackFrame = completion;
        }
    }

    @Nullable
    public static final String getCoroutineName(@NotNull CoroutineContext $this$coroutineName) {
        CoroutineId coroutineId;
        String str;
        if (!DebugKt.getDEBUG() || (coroutineId = (CoroutineId) $this$coroutineName.get(CoroutineId.Key)) == null) {
            return null;
        }
        CoroutineName coroutineName = (CoroutineName) $this$coroutineName.get(CoroutineName.Key);
        if (coroutineName == null) {
            str = "coroutine";
        } else {
            String name = coroutineName.getName();
            str = name == null ? "coroutine" : name;
        }
        String coroutineName2 = str;
        return coroutineName2 + '#' + coroutineId.getId();
    }
}
