package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.BooleanValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.CharValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.DoubleValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.FloatValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.LongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ShortValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UIntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ULongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UShortValue;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: AnnotationDeserializer.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/AnnotationDeserializer.class */
public final class AnnotationDeserializer {

    @NotNull
    private final ModuleDescriptor module;

    @NotNull
    private final NotFoundClasses notFoundClasses;

    /* compiled from: AnnotationDeserializer.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/AnnotationDeserializer$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProtoBuf.Annotation.Argument.Value.Type.values().length];
            iArr[ProtoBuf.Annotation.Argument.Value.Type.BYTE.ordinal()] = 1;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.CHAR.ordinal()] = 2;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.SHORT.ordinal()] = 3;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.INT.ordinal()] = 4;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.LONG.ordinal()] = 5;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.FLOAT.ordinal()] = 6;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.DOUBLE.ordinal()] = 7;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.BOOLEAN.ordinal()] = 8;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.STRING.ordinal()] = 9;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.CLASS.ordinal()] = 10;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.ENUM.ordinal()] = 11;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.ANNOTATION.ordinal()] = 12;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.ARRAY.ordinal()] = 13;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public AnnotationDeserializer(@NotNull ModuleDescriptor module, @NotNull NotFoundClasses notFoundClasses) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(notFoundClasses, "notFoundClasses");
        this.module = module;
        this.notFoundClasses = notFoundClasses;
    }

    private final KotlinBuiltIns getBuiltIns() {
        return this.module.getBuiltIns();
    }

    @NotNull
    public final AnnotationDescriptor deserializeAnnotation(@NotNull ProtoBuf.Annotation proto, @NotNull NameResolver nameResolver) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        ClassDescriptor annotationClass = resolveClass(NameResolverUtilKt.getClassId(nameResolver, proto.getId()));
        Map arguments = MapsKt.emptyMap();
        if (proto.getArgumentCount() != 0 && !ErrorUtils.isError(annotationClass) && DescriptorUtils.isAnnotationClass(annotationClass)) {
            Collection<ClassConstructorDescriptor> constructors = annotationClass.getConstructors();
            Intrinsics.checkNotNullExpressionValue(constructors, "annotationClass.constructors");
            ClassConstructorDescriptor constructor = (ClassConstructorDescriptor) CollectionsKt.singleOrNull(constructors);
            if (constructor != null) {
                Iterable valueParameters = constructor.getValueParameters();
                Intrinsics.checkNotNullExpressionValue(valueParameters, "constructor.valueParameters");
                Iterable $this$associateBy$iv = valueParameters;
                int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
                Map destination$iv$iv = new LinkedHashMap(capacity$iv);
                for (Object element$iv$iv : $this$associateBy$iv) {
                    destination$iv$iv.put(((ValueParameterDescriptor) element$iv$iv).getName(), element$iv$iv);
                }
                Iterable argumentList = proto.getArgumentList();
                Intrinsics.checkNotNullExpressionValue(argumentList, "proto.argumentList");
                Iterable $this$mapNotNull$iv = argumentList;
                Collection destination$iv$iv2 = new ArrayList();
                for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                    ProtoBuf.Annotation.Argument it = (ProtoBuf.Annotation.Argument) element$iv$iv$iv;
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Pair<Name, ConstantValue<?>> pairResolveArgument = resolveArgument(it, destination$iv$iv, nameResolver);
                    if (pairResolveArgument != null) {
                        destination$iv$iv2.add(pairResolveArgument);
                    }
                }
                arguments = MapsKt.toMap((List) destination$iv$iv2);
            }
        }
        return new AnnotationDescriptorImpl(annotationClass.getDefaultType(), arguments, SourceElement.NO_SOURCE);
    }

    private final Pair<Name, ConstantValue<?>> resolveArgument(ProtoBuf.Annotation.Argument proto, Map<Name, ? extends ValueParameterDescriptor> map, NameResolver nameResolver) {
        ValueParameterDescriptor parameter = map.get(NameResolverUtilKt.getName(nameResolver, proto.getNameId()));
        if (parameter == null) {
            return null;
        }
        Name name = NameResolverUtilKt.getName(nameResolver, proto.getNameId());
        KotlinType type = parameter.getType();
        Intrinsics.checkNotNullExpressionValue(type, "parameter.type");
        ProtoBuf.Annotation.Argument.Value value = proto.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "proto.value");
        return new Pair<>(name, resolveValueAndCheckExpectedType(type, value, nameResolver));
    }

    private final ConstantValue<?> resolveValueAndCheckExpectedType(KotlinType expectedType, ProtoBuf.Annotation.Argument.Value value, NameResolver nameResolver) {
        ConstantValue it = resolveValue(expectedType, value, nameResolver);
        ConstantValue constantValue = doesValueConformToExpectedType(it, expectedType, value) ? it : null;
        if (constantValue != null) {
            return constantValue;
        }
        return ErrorValue.Companion.create("Unexpected argument value: actual type " + value.getType() + " != expected type " + expectedType);
    }

    @NotNull
    public final ConstantValue<?> resolveValue(@NotNull KotlinType expectedType, @NotNull ProtoBuf.Annotation.Argument.Value value, @NotNull NameResolver nameResolver) {
        Intrinsics.checkNotNullParameter(expectedType, "expectedType");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Boolean bool = Flags.IS_UNSIGNED.get(value.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "IS_UNSIGNED.get(value.flags)");
        boolean isUnsigned = bool.booleanValue();
        ProtoBuf.Annotation.Argument.Value.Type type = value.getType();
        switch (type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                byte $this$letIf$iv = (byte) value.getIntValue();
                return isUnsigned ? new UByteValue($this$letIf$iv) : new ByteValue($this$letIf$iv);
            case 2:
                return new CharValue((char) value.getIntValue());
            case 3:
                short $this$letIf$iv2 = (short) value.getIntValue();
                return isUnsigned ? new UShortValue($this$letIf$iv2) : new ShortValue($this$letIf$iv2);
            case 4:
                int $this$letIf$iv3 = (int) value.getIntValue();
                return isUnsigned ? new UIntValue($this$letIf$iv3) : new IntValue($this$letIf$iv3);
            case 5:
                long $this$letIf$iv4 = value.getIntValue();
                return isUnsigned ? new ULongValue($this$letIf$iv4) : new LongValue($this$letIf$iv4);
            case 6:
                return new FloatValue(value.getFloatValue());
            case 7:
                return new DoubleValue(value.getDoubleValue());
            case 8:
                return new BooleanValue(value.getIntValue() != 0);
            case 9:
                return new StringValue(nameResolver.getString(value.getStringValue()));
            case 10:
                return new KClassValue(NameResolverUtilKt.getClassId(nameResolver, value.getClassId()), value.getArrayDimensionCount());
            case 11:
                return new EnumValue(NameResolverUtilKt.getClassId(nameResolver, value.getClassId()), NameResolverUtilKt.getName(nameResolver, value.getEnumValueId()));
            case 12:
                ProtoBuf.Annotation annotation = value.getAnnotation();
                Intrinsics.checkNotNullExpressionValue(annotation, "value.annotation");
                return new AnnotationValue(deserializeAnnotation(annotation, nameResolver));
            case 13:
                ConstantValueFactory constantValueFactory = ConstantValueFactory.INSTANCE;
                Iterable arrayElementList = value.getArrayElementList();
                Intrinsics.checkNotNullExpressionValue(arrayElementList, "value.arrayElementList");
                Iterable $this$map$iv = arrayElementList;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    ProtoBuf.Annotation.Argument.Value it = (ProtoBuf.Annotation.Argument.Value) item$iv$iv;
                    SimpleType anyType = getBuiltIns().getAnyType();
                    Intrinsics.checkNotNullExpressionValue(anyType, "builtIns.anyType");
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    destination$iv$iv.add(resolveValue(anyType, it, nameResolver));
                }
                return constantValueFactory.createArrayValue((List<? extends ConstantValue<?>>) destination$iv$iv, expectedType);
            default:
                throw new IllegalStateException(("Unsupported annotation argument type: " + value.getType() + " (expected " + expectedType + ')').toString());
        }
    }

    private final boolean doesValueConformToExpectedType(ConstantValue<?> constantValue, KotlinType expectedType, ProtoBuf.Annotation.Argument.Value value) {
        ProtoBuf.Annotation.Argument.Value.Type type = value.getType();
        switch (type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 10:
                ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = expectedType.getConstructor().mo3831getDeclarationDescriptor();
                ClassDescriptor expectedClass = classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor : null;
                return expectedClass == null || KotlinBuiltIns.isKClass(expectedClass);
            case 11:
            case 12:
            default:
                return Intrinsics.areEqual(constantValue.getType(this.module), expectedType);
            case 13:
                if (!((constantValue instanceof ArrayValue) && ((ArrayValue) constantValue).getValue().size() == value.getArrayElementList().size())) {
                    throw new IllegalStateException(Intrinsics.stringPlus("Deserialized ArrayValue should have the same number of elements as the original array value: ", constantValue).toString());
                }
                KotlinType expectedElementType = getBuiltIns().getArrayElementType(expectedType);
                Intrinsics.checkNotNullExpressionValue(expectedElementType, "builtIns.getArrayElementType(expectedType)");
                Iterable $this$all$iv = CollectionsKt.getIndices(((ArrayValue) constantValue).getValue());
                if (($this$all$iv instanceof Collection) && ((Collection) $this$all$iv).isEmpty()) {
                    return true;
                }
                Iterator it = $this$all$iv.iterator();
                while (it.hasNext()) {
                    int element$iv = ((IntIterator) it).nextInt();
                    ConstantValue<?> constantValue2 = ((ArrayValue) constantValue).getValue().get(element$iv);
                    ProtoBuf.Annotation.Argument.Value arrayElement = value.getArrayElement(element$iv);
                    Intrinsics.checkNotNullExpressionValue(arrayElement, "value.getArrayElement(i)");
                    if (!doesValueConformToExpectedType(constantValue2, expectedElementType, arrayElement)) {
                        return false;
                    }
                }
                return true;
        }
    }

    private final ClassDescriptor resolveClass(ClassId classId) {
        return FindClassInModuleKt.findNonGenericClassAcrossDependencies(this.module, classId, this.notFoundClasses);
    }
}
