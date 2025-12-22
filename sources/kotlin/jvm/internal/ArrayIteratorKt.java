package kotlin.jvm.internal;

import java.util.Iterator;
import kotlin.Metadata;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: ArrayIterator.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��\u0012\n��\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a%\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"iterator", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, BeanDefinitionParserDelegate.ARRAY_ELEMENT, "", "([Ljava/lang/Object;)Ljava/util/Iterator;", "kotlin-stdlib"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/jvm/internal/ArrayIteratorKt.class */
public final class ArrayIteratorKt {
    @NotNull
    public static final <T> Iterator<T> iterator(@NotNull T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return new ArrayIterator(array);
    }
}
