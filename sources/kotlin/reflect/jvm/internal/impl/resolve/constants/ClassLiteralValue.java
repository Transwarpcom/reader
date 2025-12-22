package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ClassLiteralValue.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/constants/ClassLiteralValue.class */
public final class ClassLiteralValue {

    @NotNull
    private final ClassId classId;
    private final int arrayNestedness;

    @NotNull
    public final ClassId component1() {
        return this.classId;
    }

    public final int component2() {
        return this.arrayNestedness;
    }

    public int hashCode() {
        int result = this.classId.hashCode();
        return (result * 31) + this.arrayNestedness;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClassLiteralValue)) {
            return false;
        }
        ClassLiteralValue classLiteralValue = (ClassLiteralValue) other;
        return Intrinsics.areEqual(this.classId, classLiteralValue.classId) && this.arrayNestedness == classLiteralValue.arrayNestedness;
    }

    public ClassLiteralValue(@NotNull ClassId classId, int arrayNestedness) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        this.classId = classId;
        this.arrayNestedness = arrayNestedness;
    }

    @NotNull
    public final ClassId getClassId() {
        return this.classId;
    }

    public final int getArrayNestedness() {
        return this.arrayNestedness;
    }

    @NotNull
    public String toString() {
        StringBuilder $this$toString_u24lambda_u2d2 = new StringBuilder();
        int arrayNestedness = getArrayNestedness();
        for (int i = 0; i < arrayNestedness; i++) {
            $this$toString_u24lambda_u2d2.append("kotlin/Array<");
        }
        $this$toString_u24lambda_u2d2.append(getClassId());
        int arrayNestedness2 = getArrayNestedness();
        for (int i2 = 0; i2 < arrayNestedness2; i2++) {
            $this$toString_u24lambda_u2d2.append(">");
        }
        String string = $this$toString_u24lambda_u2d2.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
