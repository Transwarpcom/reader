package kotlin;

import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* compiled from: Lazy.kt */
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u0014\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0004\n\u0002\u0010\u000b\n��\bf\u0018��*\u0006\b��\u0010\u0001 \u00012\u00020\u0002J\b\u0010\u0006\u001a\u00020\u0007H&R\u0012\u0010\u0003\u001a\u00028��X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"Lkotlin/Lazy;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "value", "getValue", "()Ljava/lang/Object;", "isInitialized", "", "kotlin-stdlib"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/Lazy.class */
public interface Lazy<T> {
    T getValue();

    boolean isInitialized();
}
