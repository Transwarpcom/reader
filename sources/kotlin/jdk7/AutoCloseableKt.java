package kotlin.jdk7;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.Nullable;

/* compiled from: AutoCloseable.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010��\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0001\u001aH\u0010\u0005\u001a\u0002H\u0006\"\n\b��\u0010\u0007*\u0004\u0018\u00010\u0002\"\u0004\b\u0001\u0010\u0006*\u0002H\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\tH\u0087\bø\u0001��\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000b"}, d2 = {"closeFinally", "", "Ljava/lang/AutoCloseable;", "cause", "", "use", "R", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "block", "Lkotlin/Function1;", "(Ljava/lang/AutoCloseable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib-jdk7"}, pn = "kotlin")
@JvmName(name = "AutoCloseableKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-jdk7-1.5.21.jar:kotlin/jdk7/AutoCloseableKt.class */
public final class AutoCloseableKt {
    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final <T extends AutoCloseable, R> R use(T t, Function1<? super T, ? extends R> function1) throws Exception {
        Throwable exception = (Throwable) null;
        try {
            try {
                R rInvoke = function1.invoke(t);
                InlineMarker.finallyStart(1);
                closeFinally(t, exception);
                InlineMarker.finallyEnd(1);
                return rInvoke;
            } finally {
            }
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            closeFinally(t, exception);
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    @SinceKotlin(version = "1.2")
    @PublishedApi
    public static final void closeFinally(@Nullable AutoCloseable $this$closeFinally, @Nullable Throwable cause) throws Exception {
        if ($this$closeFinally != null) {
            if (cause != null) {
                try {
                    $this$closeFinally.close();
                    return;
                } catch (Throwable closeException) {
                    ExceptionsKt.addSuppressed(cause, closeException);
                    return;
                }
            }
            $this$closeFinally.close();
        }
    }
}
