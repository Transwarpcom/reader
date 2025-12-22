package kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize;

import java.util.Iterator;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: capitalizeDecapitalize.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/util/capitalizeDecapitalize/CapitalizeDecapitalizeKt.class */
public final class CapitalizeDecapitalizeKt {
    @NotNull
    public static final String decapitalizeSmartForCompiler(@NotNull String $this$decapitalizeSmartForCompiler, boolean asciiOnly) {
        Object obj;
        Intrinsics.checkNotNullParameter($this$decapitalizeSmartForCompiler, "<this>");
        if (($this$decapitalizeSmartForCompiler.length() == 0) || !isUpperCaseCharAt($this$decapitalizeSmartForCompiler, 0, asciiOnly)) {
            return $this$decapitalizeSmartForCompiler;
        }
        if ($this$decapitalizeSmartForCompiler.length() == 1 || !isUpperCaseCharAt($this$decapitalizeSmartForCompiler, 1, asciiOnly)) {
            if (asciiOnly) {
                return decapitalizeAsciiOnly($this$decapitalizeSmartForCompiler);
            }
            if (!($this$decapitalizeSmartForCompiler.length() > 0)) {
                return $this$decapitalizeSmartForCompiler;
            }
            char p0 = $this$decapitalizeSmartForCompiler.charAt(0);
            char lowerCase = Character.toLowerCase(p0);
            String strSubstring = $this$decapitalizeSmartForCompiler.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            return String.valueOf(lowerCase) + strSubstring;
        }
        Iterable $this$firstOrNull$iv = StringsKt.getIndices($this$decapitalizeSmartForCompiler);
        Iterator<Integer> it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object element$iv = it.next();
                int it2 = ((Number) element$iv).intValue();
                if (!isUpperCaseCharAt($this$decapitalizeSmartForCompiler, it2, asciiOnly)) {
                    obj = element$iv;
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        Integer num = (Integer) obj;
        if (num == null) {
            return toLowerCase($this$decapitalizeSmartForCompiler, asciiOnly);
        }
        int secondWordStart = num.intValue() - 1;
        String strSubstring2 = $this$decapitalizeSmartForCompiler.substring(0, secondWordStart);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        String lowerCase2 = toLowerCase(strSubstring2, asciiOnly);
        String strSubstring3 = $this$decapitalizeSmartForCompiler.substring(secondWordStart);
        Intrinsics.checkNotNullExpressionValue(strSubstring3, "(this as java.lang.String).substring(startIndex)");
        return Intrinsics.stringPlus(lowerCase2, strSubstring3);
    }

    private static final boolean isUpperCaseCharAt(String $this$isUpperCaseCharAt, int index, boolean asciiOnly) {
        char c = $this$isUpperCaseCharAt.charAt(index);
        return asciiOnly ? 'A' <= c && c <= 'Z' : Character.isUpperCase(c);
    }

    private static final String toLowerCase(String string, boolean asciiOnly) {
        if (asciiOnly) {
            return toLowerCaseAsciiOnly(string);
        }
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = string.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.Strin….toLowerCase(Locale.ROOT)");
        return lowerCase;
    }

    @NotNull
    public static final String capitalizeAsciiOnly(@NotNull String $this$capitalizeAsciiOnly) {
        Intrinsics.checkNotNullParameter($this$capitalizeAsciiOnly, "<this>");
        if ($this$capitalizeAsciiOnly.length() == 0) {
            return $this$capitalizeAsciiOnly;
        }
        char c = $this$capitalizeAsciiOnly.charAt(0);
        boolean z = 'a' <= c && c <= 'z';
        if (z) {
            char upperCase = Character.toUpperCase(c);
            String strSubstring = $this$capitalizeAsciiOnly.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            return String.valueOf(upperCase) + strSubstring;
        }
        return $this$capitalizeAsciiOnly;
    }

    @NotNull
    public static final String decapitalizeAsciiOnly(@NotNull String $this$decapitalizeAsciiOnly) {
        Intrinsics.checkNotNullParameter($this$decapitalizeAsciiOnly, "<this>");
        if ($this$decapitalizeAsciiOnly.length() == 0) {
            return $this$decapitalizeAsciiOnly;
        }
        char c = $this$decapitalizeAsciiOnly.charAt(0);
        boolean z = 'A' <= c && c <= 'Z';
        if (z) {
            char lowerCase = Character.toLowerCase(c);
            String strSubstring = $this$decapitalizeAsciiOnly.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            return String.valueOf(lowerCase) + strSubstring;
        }
        return $this$decapitalizeAsciiOnly;
    }

    @NotNull
    public static final String toLowerCaseAsciiOnly(@NotNull String $this$toLowerCaseAsciiOnly) {
        Intrinsics.checkNotNullParameter($this$toLowerCaseAsciiOnly, "<this>");
        StringBuilder builder = new StringBuilder($this$toLowerCaseAsciiOnly.length());
        int i = 0;
        int length = $this$toLowerCaseAsciiOnly.length();
        while (i < length) {
            char c = $this$toLowerCaseAsciiOnly.charAt(i);
            i++;
            boolean z = 'A' <= c && c <= 'Z';
            builder.append(z ? Character.toLowerCase(c) : c);
        }
        String string = builder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "builder.toString()");
        return string;
    }
}
