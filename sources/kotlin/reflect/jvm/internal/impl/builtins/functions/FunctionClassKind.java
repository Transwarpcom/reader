package kotlin.reflect.jvm.internal.impl.builtins.functions;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: FunctionClassKind.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/functions/FunctionClassKind.class */
public enum FunctionClassKind {
    Function(StandardNames.BUILT_INS_PACKAGE_FQ_NAME, "Function", false, false),
    SuspendFunction(StandardNames.COROUTINES_PACKAGE_FQ_NAME_RELEASE, "SuspendFunction", true, false),
    KFunction(StandardNames.KOTLIN_REFLECT_FQ_NAME, "KFunction", false, true),
    KSuspendFunction(StandardNames.KOTLIN_REFLECT_FQ_NAME, "KSuspendFunction", true, true);


    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final FqName packageFqName;

    @NotNull
    private final String classNamePrefix;
    private final boolean isSuspendType;
    private final boolean isReflectType;

    FunctionClassKind(FqName packageFqName, String classNamePrefix, boolean isSuspendType, boolean isReflectType) {
        this.packageFqName = packageFqName;
        this.classNamePrefix = classNamePrefix;
        this.isSuspendType = isSuspendType;
        this.isReflectType = isReflectType;
    }

    @NotNull
    public final FqName getPackageFqName() {
        return this.packageFqName;
    }

    @NotNull
    public final String getClassNamePrefix() {
        return this.classNamePrefix;
    }

    @NotNull
    public final Name numberedClassName(int arity) {
        Name nameIdentifier = Name.identifier(Intrinsics.stringPlus(this.classNamePrefix, Integer.valueOf(arity)));
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(\"$classNamePrefix$arity\")");
        return nameIdentifier;
    }

    /* compiled from: FunctionClassKind.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/functions/FunctionClassKind$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final FunctionClassKind byClassNamePrefix(@NotNull FqName packageFqName, @NotNull String className) {
            Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
            Intrinsics.checkNotNullParameter(className, "className");
            for (FunctionClassKind functionClassKind : FunctionClassKind.values()) {
                if (Intrinsics.areEqual(functionClassKind.getPackageFqName(), packageFqName) && StringsKt.startsWith$default(className, functionClassKind.getClassNamePrefix(), false, 2, (Object) null)) {
                    return functionClassKind;
                }
            }
            return null;
        }

        /* compiled from: FunctionClassKind.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/functions/FunctionClassKind$Companion$KindWithArity.class */
        public static final class KindWithArity {

            @NotNull
            private final FunctionClassKind kind;
            private final int arity;

            @NotNull
            public final FunctionClassKind component1() {
                return this.kind;
            }

            public final int component2() {
                return this.arity;
            }

            @NotNull
            public String toString() {
                return "KindWithArity(kind=" + this.kind + ", arity=" + this.arity + ')';
            }

            public int hashCode() {
                int result = this.kind.hashCode();
                return (result * 31) + this.arity;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof KindWithArity)) {
                    return false;
                }
                KindWithArity kindWithArity = (KindWithArity) other;
                return this.kind == kindWithArity.kind && this.arity == kindWithArity.arity;
            }

            public KindWithArity(@NotNull FunctionClassKind kind, int arity) {
                Intrinsics.checkNotNullParameter(kind, "kind");
                this.kind = kind;
                this.arity = arity;
            }

            @NotNull
            public final FunctionClassKind getKind() {
                return this.kind;
            }
        }

        @Nullable
        public final KindWithArity parseClassName(@NotNull String className, @NotNull FqName packageFqName) {
            Intrinsics.checkNotNullParameter(className, "className");
            Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
            FunctionClassKind kind = byClassNamePrefix(packageFqName, className);
            if (kind == null) {
                return null;
            }
            String prefix = kind.getClassNamePrefix();
            String strSubstring = className.substring(prefix.length());
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            Integer num = toInt(strSubstring);
            if (num == null) {
                return null;
            }
            int arity = num.intValue();
            return new KindWithArity(kind, arity);
        }

        @JvmStatic
        @Nullable
        public final FunctionClassKind getFunctionalClassKind(@NotNull String className, @NotNull FqName packageFqName) {
            Intrinsics.checkNotNullParameter(className, "className");
            Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
            KindWithArity className2 = parseClassName(className, packageFqName);
            if (className2 == null) {
                return null;
            }
            return className2.getKind();
        }

        private final Integer toInt(String s) {
            if (s.length() == 0) {
                return null;
            }
            int result = 0;
            int i = 0;
            int length = s.length();
            while (i < length) {
                char c = s.charAt(i);
                i++;
                int d = c - '0';
                boolean z = 0 <= d && d <= 9;
                if (!z) {
                    return null;
                }
                result = (result * 10) + d;
            }
            return Integer.valueOf(result);
        }
    }
}
