package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: NameResolverUtil.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/NameResolverUtilKt.class */
public final class NameResolverUtilKt {
    @NotNull
    public static final ClassId getClassId(@NotNull NameResolver $this$getClassId, int index) {
        Intrinsics.checkNotNullParameter($this$getClassId, "<this>");
        ClassId classIdFromString = ClassId.fromString($this$getClassId.getQualifiedClassName(index), $this$getClassId.isLocalClassName(index));
        Intrinsics.checkNotNullExpressionValue(classIdFromString, "fromString(getQualifiedClassName(index), isLocalClassName(index))");
        return classIdFromString;
    }

    @NotNull
    public static final Name getName(@NotNull NameResolver $this$getName, int index) {
        Intrinsics.checkNotNullParameter($this$getName, "<this>");
        Name nameGuessByFirstCharacter = Name.guessByFirstCharacter($this$getName.getString(index));
        Intrinsics.checkNotNullExpressionValue(nameGuessByFirstCharacter, "guessByFirstCharacter(getString(index))");
        return nameGuessByFirstCharacter;
    }
}
