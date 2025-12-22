package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: RenderingUtils.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/renderer/RenderingUtilsKt.class */
public final class RenderingUtilsKt {
    @NotNull
    public static final String render(@NotNull Name $this$render) {
        Intrinsics.checkNotNullParameter($this$render, "<this>");
        if (shouldBeEscaped($this$render)) {
            String strAsString = $this$render.asString();
            Intrinsics.checkNotNullExpressionValue(strAsString, "asString()");
            return Intrinsics.stringPlus(String.valueOf('`') + strAsString, "`");
        }
        String strAsString2 = $this$render.asString();
        Intrinsics.checkNotNullExpressionValue(strAsString2, "asString()");
        return strAsString2;
    }

    private static final boolean shouldBeEscaped(Name $this$shouldBeEscaped) {
        boolean z;
        if ($this$shouldBeEscaped.isSpecial()) {
            return false;
        }
        CharSequence string = $this$shouldBeEscaped.asString();
        Intrinsics.checkNotNullExpressionValue(string, "asString()");
        if (!KeywordStringsGenerated.KEYWORDS.contains(string)) {
            CharSequence $this$any$iv = string;
            int i = 0;
            while (true) {
                if (i < $this$any$iv.length()) {
                    char element$iv = $this$any$iv.charAt(i);
                    if ((Character.isLetterOrDigit(element$iv) || element$iv == '_') ? false : true) {
                        z = true;
                        break;
                    }
                    i++;
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public static final String render(@NotNull FqNameUnsafe $this$render) {
        Intrinsics.checkNotNullParameter($this$render, "<this>");
        List<Name> listPathSegments = $this$render.pathSegments();
        Intrinsics.checkNotNullExpressionValue(listPathSegments, "pathSegments()");
        return renderFqName(listPathSegments);
    }

    @NotNull
    public static final String renderFqName(@NotNull List<Name> pathSegments) {
        Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
        StringBuilder $this$renderFqName_u24lambda_u2d1 = new StringBuilder();
        for (Name element : pathSegments) {
            if ($this$renderFqName_u24lambda_u2d1.length() > 0) {
                $this$renderFqName_u24lambda_u2d1.append(".");
            }
            $this$renderFqName_u24lambda_u2d1.append(render(element));
        }
        String string = $this$renderFqName_u24lambda_u2d1.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
