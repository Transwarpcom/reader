package kotlin.reflect.jvm.internal.impl.name;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CallableId.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/name/CallableId.class */
public final class CallableId {

    @NotNull
    private static final Companion Companion = new Companion(null);

    @NotNull
    private final FqName packageName;

    @Nullable
    private final FqName className;

    @NotNull
    private final Name callableName;

    @Nullable
    private final FqName pathToLocal;

    @Deprecated
    @NotNull
    private static final Name LOCAL_NAME;

    @Deprecated
    @NotNull
    private static final FqName PACKAGE_FQ_NAME_FOR_LOCAL;

    public int hashCode() {
        int result = this.packageName.hashCode();
        return (((((result * 31) + (this.className == null ? 0 : this.className.hashCode())) * 31) + this.callableName.hashCode()) * 31) + (this.pathToLocal == null ? 0 : this.pathToLocal.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CallableId)) {
            return false;
        }
        CallableId callableId = (CallableId) other;
        return Intrinsics.areEqual(this.packageName, callableId.packageName) && Intrinsics.areEqual(this.className, callableId.className) && Intrinsics.areEqual(this.callableName, callableId.callableName) && Intrinsics.areEqual(this.pathToLocal, callableId.pathToLocal);
    }

    public CallableId(@NotNull FqName packageName, @Nullable FqName className, @NotNull Name callableName, @Nullable FqName pathToLocal) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(callableName, "callableName");
        this.packageName = packageName;
        this.className = className;
        this.callableName = callableName;
        this.pathToLocal = pathToLocal;
    }

    public /* synthetic */ CallableId(FqName fqName, FqName fqName2, Name name, FqName fqName3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fqName, fqName2, name, (i & 8) != 0 ? null : fqName3);
    }

    @NotNull
    public final FqName getPackageName() {
        return this.packageName;
    }

    @Nullable
    public final FqName getClassName() {
        return this.className;
    }

    @NotNull
    public final Name getCallableName() {
        return this.callableName;
    }

    /* compiled from: CallableId.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/name/CallableId$Companion.class */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        Name nameSpecial = Name.special("<local>");
        Intrinsics.checkNotNullExpressionValue(nameSpecial, "special(\"<local>\")");
        LOCAL_NAME = nameSpecial;
        FqName fqName = FqName.topLevel(LOCAL_NAME);
        Intrinsics.checkNotNullExpressionValue(fqName, "topLevel(LOCAL_NAME)");
        PACKAGE_FQ_NAME_FOR_LOCAL = fqName;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CallableId(@NotNull FqName packageName, @NotNull Name callableName) {
        this(packageName, null, callableName, null, 8, null);
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(callableName, "callableName");
    }

    @NotNull
    public String toString() {
        StringBuilder $this$toString_u24lambda_u2d0 = new StringBuilder();
        String strAsString = getPackageName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "packageName.asString()");
        $this$toString_u24lambda_u2d0.append(StringsKt.replace$default(strAsString, '.', '/', false, 4, (Object) null));
        $this$toString_u24lambda_u2d0.append("/");
        if (getClassName() != null) {
            $this$toString_u24lambda_u2d0.append(getClassName());
            $this$toString_u24lambda_u2d0.append(".");
        }
        $this$toString_u24lambda_u2d0.append(getCallableName());
        String string = $this$toString_u24lambda_u2d0.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
