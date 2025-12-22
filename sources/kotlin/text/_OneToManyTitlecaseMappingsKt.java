package kotlin.text;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: _OneToManyTitlecaseMappings.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��\f\n��\n\u0002\u0010\u000e\n\u0002\u0010\f\n��\u001a\f\u0010��\u001a\u00020\u0001*\u00020\u0002H��¨\u0006\u0003"}, d2 = {"titlecaseImpl", "", "", "kotlin-stdlib"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/text/_OneToManyTitlecaseMappingsKt.class */
public final class _OneToManyTitlecaseMappingsKt {
    @NotNull
    public static final String titlecaseImpl(char $this$titlecaseImpl) {
        String strValueOf = String.valueOf($this$titlecaseImpl);
        if (strValueOf == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String uppercase = strValueOf.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(uppercase, "(this as java.lang.Strin….toUpperCase(Locale.ROOT)");
        if (uppercase.length() <= 1) {
            return String.valueOf(Character.toTitleCase($this$titlecaseImpl));
        }
        if ($this$titlecaseImpl == 329) {
            return uppercase;
        }
        char cCharAt = uppercase.charAt(0);
        if (uppercase == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = uppercase.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
        if (strSubstring == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = strSubstring.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.Strin….toLowerCase(Locale.ROOT)");
        return String.valueOf(cCharAt) + lowerCase;
    }
}
