package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaToKotlinClassMapper.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/jvm/JavaToKotlinClassMapper.class */
public final class JavaToKotlinClassMapper {

    @NotNull
    public static final JavaToKotlinClassMapper INSTANCE = new JavaToKotlinClassMapper();

    private JavaToKotlinClassMapper() {
    }

    @NotNull
    public final Collection<ClassDescriptor> mapPlatformClass(@NotNull FqName fqName, @NotNull KotlinBuiltIns builtIns) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        ClassDescriptor kotlinAnalog = mapJavaToKotlin$default(this, fqName, builtIns, null, 4, null);
        if (kotlinAnalog == null) {
            return SetsKt.emptySet();
        }
        FqName kotlinMutableAnalogFqName = JavaToKotlinClassMap.INSTANCE.readOnlyToMutable(DescriptorUtilsKt.getFqNameUnsafe(kotlinAnalog));
        if (kotlinMutableAnalogFqName == null) {
            return SetsKt.setOf(kotlinAnalog);
        }
        ClassDescriptor builtInClassByFqName = builtIns.getBuiltInClassByFqName(kotlinMutableAnalogFqName);
        Intrinsics.checkNotNullExpressionValue(builtInClassByFqName, "builtIns.getBuiltInClassByFqName(kotlinMutableAnalogFqName)");
        return CollectionsKt.listOf((Object[]) new ClassDescriptor[]{kotlinAnalog, builtInClassByFqName});
    }

    public static /* synthetic */ ClassDescriptor mapJavaToKotlin$default(JavaToKotlinClassMapper javaToKotlinClassMapper, FqName fqName, KotlinBuiltIns kotlinBuiltIns, Integer num, int i, Object obj) {
        if ((i & 4) != 0) {
            num = null;
        }
        return javaToKotlinClassMapper.mapJavaToKotlin(fqName, kotlinBuiltIns, num);
    }

    @Nullable
    public final ClassDescriptor mapJavaToKotlin(@NotNull FqName fqName, @NotNull KotlinBuiltIns builtIns, @Nullable Integer functionTypeArity) {
        ClassId classIdMapJavaToKotlin;
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        if (functionTypeArity == null || !Intrinsics.areEqual(fqName, JavaToKotlinClassMap.INSTANCE.getFUNCTION_N_FQ_NAME())) {
            classIdMapJavaToKotlin = JavaToKotlinClassMap.INSTANCE.mapJavaToKotlin(fqName);
        } else {
            StandardNames standardNames = StandardNames.INSTANCE;
            classIdMapJavaToKotlin = StandardNames.getFunctionClassId(functionTypeArity.intValue());
        }
        ClassId kotlinClassId = classIdMapJavaToKotlin;
        if (kotlinClassId != null) {
            return builtIns.getBuiltInClassByFqName(kotlinClassId.asSingleFqName());
        }
        return null;
    }

    public final boolean isMutable(@NotNull ClassDescriptor mutable) {
        Intrinsics.checkNotNullParameter(mutable, "mutable");
        return JavaToKotlinClassMap.INSTANCE.isMutable(DescriptorUtils.getFqName(mutable));
    }

    public final boolean isMutable(@NotNull KotlinType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        ClassDescriptor classDescriptor = TypeUtils.getClassDescriptor(type);
        return classDescriptor != null && isMutable(classDescriptor);
    }

    public final boolean isReadOnly(@NotNull ClassDescriptor readOnly) {
        Intrinsics.checkNotNullParameter(readOnly, "readOnly");
        return JavaToKotlinClassMap.INSTANCE.isReadOnly(DescriptorUtils.getFqName(readOnly));
    }

    public final boolean isReadOnly(@NotNull KotlinType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        ClassDescriptor classDescriptor = TypeUtils.getClassDescriptor(type);
        return classDescriptor != null && isReadOnly(classDescriptor);
    }

    @NotNull
    public final ClassDescriptor convertMutableToReadOnly(@NotNull ClassDescriptor mutable) {
        Intrinsics.checkNotNullParameter(mutable, "mutable");
        FqNameUnsafe it = DescriptorUtils.getFqName(mutable);
        FqName oppositeClassFqName$iv = JavaToKotlinClassMap.INSTANCE.mutableToReadOnly(it);
        if (oppositeClassFqName$iv != null) {
            ClassDescriptor builtInClassByFqName = DescriptorUtilsKt.getBuiltIns(mutable).getBuiltInClassByFqName(oppositeClassFqName$iv);
            Intrinsics.checkNotNullExpressionValue(builtInClassByFqName, "descriptor.builtIns.getBuiltInClassByFqName(oppositeClassFqName)");
            return builtInClassByFqName;
        }
        throw new IllegalArgumentException("Given class " + mutable + " is not a mutable collection");
    }

    @NotNull
    public final ClassDescriptor convertReadOnlyToMutable(@NotNull ClassDescriptor readOnly) {
        Intrinsics.checkNotNullParameter(readOnly, "readOnly");
        FqNameUnsafe it = DescriptorUtils.getFqName(readOnly);
        FqName oppositeClassFqName$iv = JavaToKotlinClassMap.INSTANCE.readOnlyToMutable(it);
        if (oppositeClassFqName$iv != null) {
            ClassDescriptor builtInClassByFqName = DescriptorUtilsKt.getBuiltIns(readOnly).getBuiltInClassByFqName(oppositeClassFqName$iv);
            Intrinsics.checkNotNullExpressionValue(builtInClassByFqName, "descriptor.builtIns.getBuiltInClassByFqName(oppositeClassFqName)");
            return builtInClassByFqName;
        }
        throw new IllegalArgumentException("Given class " + readOnly + " is not a read-only collection");
    }
}
