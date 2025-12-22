package kotlin.reflect.jvm.internal.impl.name;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: FqNamesUtil.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/name/FqNamesUtilKt.class */
public final class FqNamesUtilKt {

    /* compiled from: FqNamesUtil.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/name/FqNamesUtilKt$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[State.values().length];
            iArr[State.BEGINNING.ordinal()] = 1;
            iArr[State.AFTER_DOT.ordinal()] = 2;
            iArr[State.MIDDLE.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final boolean isSubpackageOf(@NotNull FqName $this$isSubpackageOf, @NotNull FqName packageName) {
        Intrinsics.checkNotNullParameter($this$isSubpackageOf, "<this>");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        if (Intrinsics.areEqual($this$isSubpackageOf, packageName) || packageName.isRoot()) {
            return true;
        }
        String strAsString = $this$isSubpackageOf.asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "this.asString()");
        String strAsString2 = packageName.asString();
        Intrinsics.checkNotNullExpressionValue(strAsString2, "packageName.asString()");
        return isSubpackageOf(strAsString, strAsString2);
    }

    private static final boolean isSubpackageOf(String subpackageNameStr, String packageNameStr) {
        return StringsKt.startsWith$default(subpackageNameStr, packageNameStr, false, 2, (Object) null) && subpackageNameStr.charAt(packageNameStr.length()) == '.';
    }

    @NotNull
    public static final FqName tail(@NotNull FqName $this$tail, @NotNull FqName prefix) {
        Intrinsics.checkNotNullParameter($this$tail, "<this>");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (!isSubpackageOf($this$tail, prefix) || prefix.isRoot()) {
            return $this$tail;
        }
        if (Intrinsics.areEqual($this$tail, prefix)) {
            FqName ROOT = FqName.ROOT;
            Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
            return ROOT;
        }
        String strAsString = $this$tail.asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "asString()");
        String strSubstring = strAsString.substring(prefix.asString().length() + 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return new FqName(strSubstring);
    }

    public static final boolean isValidJavaFqName(@Nullable String qualifiedName) {
        if (qualifiedName == null) {
            return false;
        }
        State state = State.BEGINNING;
        int i = 0;
        while (i < qualifiedName.length()) {
            char c = qualifiedName.charAt(i);
            i++;
            switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                case 1:
                case 2:
                    if (!Character.isJavaIdentifierPart(c)) {
                        return false;
                    }
                    state = State.MIDDLE;
                    break;
                case 3:
                    if (c == '.') {
                        state = State.AFTER_DOT;
                        break;
                    } else {
                        if (!Character.isJavaIdentifierPart(c)) {
                            return false;
                        }
                        break;
                    }
            }
        }
        return state != State.AFTER_DOT;
    }
}
