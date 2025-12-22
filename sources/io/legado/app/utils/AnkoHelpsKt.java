package io.legado.app.utils;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: AnkoHelps.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\u001a&\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0086\bø\u0001��\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0005"}, d2 = {"attempt", "Lio/legado/app/utils/AttemptResult;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, OperatorName.FILL_NON_ZERO, "Lkotlin/Function0;", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/utils/AnkoHelpsKt.class */
public final class AnkoHelpsKt {
    @NotNull
    public static final <T> AttemptResult<T> attempt(@NotNull Function0<? extends T> f) {
        Intrinsics.checkNotNullParameter(f, "f");
        Object value = null;
        Throwable error = null;
        try {
            value = f.invoke();
        } catch (Throwable t) {
            error = t;
        }
        return new AttemptResult<>(value, error);
    }
}
