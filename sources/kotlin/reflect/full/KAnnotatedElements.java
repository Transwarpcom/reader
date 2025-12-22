package kotlin.reflect.full;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KAnnotatedElement;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* compiled from: KAnnotatedElements.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��\u0016\n\u0002\b\u0002\n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\u001a \u0010��\u001a\u0004\u0018\u0001H\u0001\"\n\b��\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0087\b¢\u0006\u0002\u0010\u0004\u001a\u0019\u0010\u0005\u001a\u00020\u0006\"\n\b��\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0087\b¨\u0006\u0007"}, d2 = {"findAnnotation", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "Lkotlin/reflect/KAnnotatedElement;", "(Lkotlin/reflect/KAnnotatedElement;)Ljava/lang/annotation/Annotation;", "hasAnnotation", "", "kotlin-reflection"})
@JvmName(name = "KAnnotatedElements")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/full/KAnnotatedElements.class */
public final class KAnnotatedElements {
    @SinceKotlin(version = "1.1")
    public static final /* synthetic */ <T extends Annotation> T findAnnotation(KAnnotatedElement findAnnotation) {
        Object obj;
        Intrinsics.checkNotNullParameter(findAnnotation, "$this$findAnnotation");
        List<Annotation> $this$firstOrNull$iv = findAnnotation.getAnnotations();
        Iterator<T> it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object element$iv = it.next();
                Annotation it2 = (Annotation) element$iv;
                Intrinsics.reifiedOperationMarker(3, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE);
                if (it2 instanceof Annotation) {
                    obj = element$iv;
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        Intrinsics.reifiedOperationMarker(1, "T?");
        return (T) obj;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final /* synthetic */ <T extends Annotation> boolean hasAnnotation(KAnnotatedElement hasAnnotation) {
        Object obj;
        Intrinsics.checkNotNullParameter(hasAnnotation, "$this$hasAnnotation");
        Iterable $this$firstOrNull$iv$iv = hasAnnotation.getAnnotations();
        Iterator it = $this$firstOrNull$iv$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object element$iv$iv = it.next();
                Annotation it$iv = (Annotation) element$iv$iv;
                Intrinsics.reifiedOperationMarker(3, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE);
                if (it$iv instanceof Annotation) {
                    obj = element$iv$iv;
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        Intrinsics.reifiedOperationMarker(1, "T?");
        return ((Annotation) obj) != null;
    }
}
