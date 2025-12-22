package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: Synchronized.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u001c\n\u0002\b\u0003\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\u001a.\u0010��\u001a\u0002H\u0001\"\u0004\b��\u0010\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006H\u0087\b¢\u0006\u0002\u0010\u0007*\u0010\b\u0007\u0010\b\"\u00020\u00032\u00020\u0003B\u0002\b\t¨\u0006\n"}, d2 = {"synchronized", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "lock", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "block", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "SynchronizedObject", "Lkotlinx/coroutines/InternalCoroutinesApi;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/SynchronizedKt.class */
public final class SynchronizedKt {
    @InternalCoroutinesApi
    public static /* synthetic */ void SynchronizedObject$annotations() {
    }

    @InternalCoroutinesApi
    /* renamed from: synchronized, reason: not valid java name */
    public static final <T> T m4340synchronized(@NotNull Object lock, @NotNull Function0<? extends T> function0) {
        T tInvoke;
        synchronized (lock) {
            try {
                tInvoke = function0.invoke();
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        return tInvoke;
    }
}
