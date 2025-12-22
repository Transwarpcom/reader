package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: constantValues.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/constants/KClassValue.class */
public final class KClassValue extends ConstantValue<Value> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    /* compiled from: constantValues.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/constants/KClassValue$Value.class */
    public static abstract class Value {
        public /* synthetic */ Value(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* compiled from: constantValues.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/constants/KClassValue$Value$NormalClass.class */
        public static final class NormalClass extends Value {

            @NotNull
            private final ClassLiteralValue value;

            @NotNull
            public String toString() {
                return "NormalClass(value=" + this.value + ')';
            }

            public int hashCode() {
                return this.value.hashCode();
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof NormalClass) && Intrinsics.areEqual(this.value, ((NormalClass) other).value);
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NormalClass(@NotNull ClassLiteralValue value) {
                super(null);
                Intrinsics.checkNotNullParameter(value, "value");
                this.value = value;
            }

            @NotNull
            public final ClassLiteralValue getValue() {
                return this.value;
            }

            @NotNull
            public final ClassId getClassId() {
                return this.value.getClassId();
            }

            public final int getArrayDimensions() {
                return this.value.getArrayNestedness();
            }
        }

        private Value() {
        }

        /* compiled from: constantValues.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/constants/KClassValue$Value$LocalClass.class */
        public static final class LocalClass extends Value {

            @NotNull
            private final KotlinType type;

            @NotNull
            public String toString() {
                return "LocalClass(type=" + this.type + ')';
            }

            public int hashCode() {
                return this.type.hashCode();
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof LocalClass) && Intrinsics.areEqual(this.type, ((LocalClass) other).type);
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public LocalClass(@NotNull KotlinType type) {
                super(null);
                Intrinsics.checkNotNullParameter(type, "type");
                this.type = type;
            }

            @NotNull
            public final KotlinType getType() {
                return this.type;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KClassValue(@NotNull Value value) {
        super(value);
        Intrinsics.checkNotNullParameter(value, "value");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KClassValue(@NotNull ClassLiteralValue value) {
        this(new Value.NormalClass(value));
        Intrinsics.checkNotNullParameter(value, "value");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KClassValue(@NotNull ClassId classId, int arrayDimensions) {
        this(new ClassLiteralValue(classId, arrayDimensions));
        Intrinsics.checkNotNullParameter(classId, "classId");
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    @NotNull
    public KotlinType getType(@NotNull ModuleDescriptor module) {
        Intrinsics.checkNotNullParameter(module, "module");
        KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
        Annotations empty = Annotations.Companion.getEMPTY();
        ClassDescriptor kClass = module.getBuiltIns().getKClass();
        Intrinsics.checkNotNullExpressionValue(kClass, "module.builtIns.kClass");
        return KotlinTypeFactory.simpleNotNullType(empty, kClass, CollectionsKt.listOf(new TypeProjectionImpl(getArgumentType(module))));
    }

    @NotNull
    public final KotlinType getArgumentType(@NotNull ModuleDescriptor module) {
        Intrinsics.checkNotNullParameter(module, "module");
        Value value = getValue();
        if (value instanceof Value.LocalClass) {
            return ((Value.LocalClass) getValue()).getType();
        }
        if (!(value instanceof Value.NormalClass)) {
            throw new NoWhenBranchMatchedException();
        }
        ClassLiteralValue value2 = ((Value.NormalClass) getValue()).getValue();
        ClassId classId = value2.component1();
        int arrayDimensions = value2.component2();
        ClassDescriptor descriptor = FindClassInModuleKt.findClassAcrossModuleDependencies(module, classId);
        if (descriptor != null) {
            SimpleType defaultType = descriptor.getDefaultType();
            Intrinsics.checkNotNullExpressionValue(defaultType, "descriptor.defaultType");
            Object type = TypeUtilsKt.replaceArgumentsWithStarProjections(defaultType);
            for (int i = 0; i < arrayDimensions; i++) {
                Object arrayType = module.getBuiltIns().getArrayType(Variance.INVARIANT, (KotlinType) type);
                Intrinsics.checkNotNullExpressionValue(arrayType, "module.builtIns.getArrayType(Variance.INVARIANT, type)");
                type = arrayType;
            }
            return (KotlinType) type;
        }
        SimpleType simpleTypeCreateErrorType = ErrorUtils.createErrorType("Unresolved type: " + classId + " (arrayDimensions=" + arrayDimensions + ')');
        Intrinsics.checkNotNullExpressionValue(simpleTypeCreateErrorType, "createErrorType(\"Unresolved type: $classId (arrayDimensions=$arrayDimensions)\")");
        return simpleTypeCreateErrorType;
    }

    /* compiled from: constantValues.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/constants/KClassValue$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final ConstantValue<?> create(@NotNull KotlinType argumentType) {
            int arrayDimensions;
            Intrinsics.checkNotNullParameter(argumentType, "argumentType");
            if (KotlinTypeKt.isError(argumentType)) {
                return null;
            }
            KotlinType type = argumentType;
            int i = 0;
            while (true) {
                arrayDimensions = i;
                if (!KotlinBuiltIns.isArray(type)) {
                    break;
                }
                KotlinType type2 = ((TypeProjection) CollectionsKt.single((List) type.getArguments())).getType();
                Intrinsics.checkNotNullExpressionValue(type2, "type.arguments.single().type");
                type = type2;
                i = arrayDimensions + 1;
            }
            ClassifierDescriptor descriptor = type.getConstructor().mo3831getDeclarationDescriptor();
            if (descriptor instanceof ClassDescriptor) {
                ClassId classId = DescriptorUtilsKt.getClassId(descriptor);
                return classId == null ? new KClassValue(new Value.LocalClass(argumentType)) : new KClassValue(classId, arrayDimensions);
            }
            if (descriptor instanceof TypeParameterDescriptor) {
                ClassId classId2 = ClassId.topLevel(StandardNames.FqNames.any.toSafe());
                Intrinsics.checkNotNullExpressionValue(classId2, "topLevel(StandardNames.FqNames.any.toSafe())");
                return new KClassValue(classId2, 0);
            }
            return null;
        }
    }
}
