package mu;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: KotlinLoggingMDC.kt */
@Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 2, d1 = {"��\"\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\u001aN\u0010��\u001a\u0002H\u0001\"\u0004\b��\u0010\u00012*\u0010\u0002\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00040\u0003\"\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0086\b¢\u0006\u0002\u0010\b\u001a6\u0010��\u001a\u0002H\u0001\"\u0004\b��\u0010\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0086\b¢\u0006\u0002\u0010\t\u001a6\u0010��\u001a\u0002H\u0001\"\u0004\b��\u0010\u00012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0086\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"withLoggingContext", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "pair", "", "Lkotlin/Pair;", "", NCXDocumentV3.XHTMLTgs.body, "Lkotlin/Function0;", "([Lkotlin/Pair;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "(Lkotlin/Pair;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", BeanDefinitionParserDelegate.MAP_ELEMENT, "", "(Ljava/util/Map;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlin-logging"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-logging-1.6.24.jar:mu/KotlinLoggingMDCKt.class */
public final class KotlinLoggingMDCKt {
    public static final <T> T withLoggingContext(@NotNull Pair<String, String> pair, @NotNull Function0<? extends T> body) throws IllegalArgumentException {
        Intrinsics.checkParameterIsNotNull(pair, "pair");
        Intrinsics.checkParameterIsNotNull(body, "body");
        MDC.MDCCloseable mDCCloseablePutCloseable = MDC.putCloseable(pair.getFirst(), pair.getSecond());
        Throwable th = (Throwable) null;
        try {
            try {
                MDC.MDCCloseable mDCCloseable = mDCCloseablePutCloseable;
                T tInvoke = body.invoke();
                InlineMarker.finallyStart(1);
                CloseableKt.closeFinally(mDCCloseablePutCloseable, th);
                InlineMarker.finallyEnd(1);
                return tInvoke;
            } finally {
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(mDCCloseablePutCloseable, th);
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }

    public static final <T> T withLoggingContext(@NotNull Pair<String, String>[] pair, @NotNull Function0<? extends T> body) throws IllegalArgumentException {
        Intrinsics.checkParameterIsNotNull(pair, "pair");
        Intrinsics.checkParameterIsNotNull(body, "body");
        try {
            for (Pair<String, String> pair2 : pair) {
                MDC.put(pair2.getFirst(), pair2.getSecond());
            }
            T tInvoke = body.invoke();
            InlineMarker.finallyStart(1);
            for (Pair<String, String> pair3 : pair) {
                MDC.remove(pair3.getFirst());
            }
            InlineMarker.finallyEnd(1);
            return tInvoke;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            for (Pair<String, String> pair4 : pair) {
                MDC.remove(pair4.getFirst());
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    public static final <T> T withLoggingContext(@NotNull Map<String, String> map, @NotNull Function0<? extends T> body) throws IllegalArgumentException {
        Intrinsics.checkParameterIsNotNull(map, "map");
        Intrinsics.checkParameterIsNotNull(body, "body");
        try {
            for (Map.Entry element$iv : map.entrySet()) {
                MDC.put(element$iv.getKey(), element$iv.getValue());
            }
            T tInvoke = body.invoke();
            InlineMarker.finallyStart(1);
            for (Map.Entry element$iv2 : map.entrySet()) {
                MDC.remove(element$iv2.getKey());
            }
            InlineMarker.finallyEnd(1);
            return tInvoke;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            for (Map.Entry element$iv3 : map.entrySet()) {
                MDC.remove(element$iv3.getKey());
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }
}
