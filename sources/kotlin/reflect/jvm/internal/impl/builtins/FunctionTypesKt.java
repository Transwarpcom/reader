package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.List;
import kotlin.TuplesKt;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.BuiltInAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: functionTypes.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/FunctionTypesKt.class */
public final class FunctionTypesKt {
    public static final boolean isFunctionType(@NotNull KotlinType $this$isFunctionType) {
        Intrinsics.checkNotNullParameter($this$isFunctionType, "<this>");
        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = $this$isFunctionType.getConstructor().mo3831getDeclarationDescriptor();
        return (classifierDescriptorMo3831getDeclarationDescriptor == null ? null : getFunctionalClassKind(classifierDescriptorMo3831getDeclarationDescriptor)) == FunctionClassKind.Function;
    }

    public static final boolean isSuspendFunctionType(@NotNull KotlinType $this$isSuspendFunctionType) {
        Intrinsics.checkNotNullParameter($this$isSuspendFunctionType, "<this>");
        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = $this$isSuspendFunctionType.getConstructor().mo3831getDeclarationDescriptor();
        return (classifierDescriptorMo3831getDeclarationDescriptor == null ? null : getFunctionalClassKind(classifierDescriptorMo3831getDeclarationDescriptor)) == FunctionClassKind.SuspendFunction;
    }

    public static final boolean isBuiltinFunctionalType(@NotNull KotlinType $this$isBuiltinFunctionalType) {
        Intrinsics.checkNotNullParameter($this$isBuiltinFunctionalType, "<this>");
        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = $this$isBuiltinFunctionalType.getConstructor().mo3831getDeclarationDescriptor();
        return Intrinsics.areEqual((Object) (classifierDescriptorMo3831getDeclarationDescriptor == null ? null : Boolean.valueOf(isBuiltinFunctionalClassDescriptor(classifierDescriptorMo3831getDeclarationDescriptor))), (Object) true);
    }

    public static final boolean isBuiltinFunctionalClassDescriptor(@NotNull DeclarationDescriptor $this$isBuiltinFunctionalClassDescriptor) {
        Intrinsics.checkNotNullParameter($this$isBuiltinFunctionalClassDescriptor, "<this>");
        FunctionClassKind functionalClassKind = getFunctionalClassKind($this$isBuiltinFunctionalClassDescriptor);
        return functionalClassKind == FunctionClassKind.Function || functionalClassKind == FunctionClassKind.SuspendFunction;
    }

    public static final boolean isBuiltinExtensionFunctionalType(@NotNull KotlinType $this$isBuiltinExtensionFunctionalType) {
        Intrinsics.checkNotNullParameter($this$isBuiltinExtensionFunctionalType, "<this>");
        return isBuiltinFunctionalType($this$isBuiltinExtensionFunctionalType) && isTypeAnnotatedWithExtensionFunctionType($this$isBuiltinExtensionFunctionalType);
    }

    private static final boolean isTypeAnnotatedWithExtensionFunctionType(KotlinType $this$isTypeAnnotatedWithExtensionFunctionType) {
        return $this$isTypeAnnotatedWithExtensionFunctionType.getAnnotations().mo3547findAnnotation(StandardNames.FqNames.extensionFunctionType) != null;
    }

    @Nullable
    public static final FunctionClassKind getFunctionalClassKind(@NotNull DeclarationDescriptor $this$getFunctionalClassKind) {
        Intrinsics.checkNotNullParameter($this$getFunctionalClassKind, "<this>");
        if (($this$getFunctionalClassKind instanceof ClassDescriptor) && KotlinBuiltIns.isUnderKotlinPackage($this$getFunctionalClassKind)) {
            return getFunctionalClassKind(DescriptorUtilsKt.getFqNameUnsafe($this$getFunctionalClassKind));
        }
        return null;
    }

    private static final FunctionClassKind getFunctionalClassKind(FqNameUnsafe $this$getFunctionalClassKind) {
        if (!$this$getFunctionalClassKind.isSafe() || $this$getFunctionalClassKind.isRoot()) {
            return null;
        }
        FunctionClassKind.Companion companion = FunctionClassKind.Companion;
        String strAsString = $this$getFunctionalClassKind.shortName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "shortName().asString()");
        FqName fqNameParent = $this$getFunctionalClassKind.toSafe().parent();
        Intrinsics.checkNotNullExpressionValue(fqNameParent, "toSafe().parent()");
        return companion.getFunctionalClassKind(strAsString, fqNameParent);
    }

    @Nullable
    public static final KotlinType getReceiverTypeFromFunctionType(@NotNull KotlinType $this$getReceiverTypeFromFunctionType) {
        Intrinsics.checkNotNullParameter($this$getReceiverTypeFromFunctionType, "<this>");
        boolean zIsBuiltinFunctionalType = isBuiltinFunctionalType($this$getReceiverTypeFromFunctionType);
        if (_Assertions.ENABLED && !zIsBuiltinFunctionalType) {
            throw new AssertionError(Intrinsics.stringPlus("Not a function type: ", $this$getReceiverTypeFromFunctionType));
        }
        if (isTypeAnnotatedWithExtensionFunctionType($this$getReceiverTypeFromFunctionType)) {
            return ((TypeProjection) CollectionsKt.first((List) $this$getReceiverTypeFromFunctionType.getArguments())).getType();
        }
        return null;
    }

    @NotNull
    public static final KotlinType getReturnTypeFromFunctionType(@NotNull KotlinType $this$getReturnTypeFromFunctionType) {
        Intrinsics.checkNotNullParameter($this$getReturnTypeFromFunctionType, "<this>");
        boolean zIsBuiltinFunctionalType = isBuiltinFunctionalType($this$getReturnTypeFromFunctionType);
        if (_Assertions.ENABLED && !zIsBuiltinFunctionalType) {
            throw new AssertionError(Intrinsics.stringPlus("Not a function type: ", $this$getReturnTypeFromFunctionType));
        }
        KotlinType type = ((TypeProjection) CollectionsKt.last((List) $this$getReturnTypeFromFunctionType.getArguments())).getType();
        Intrinsics.checkNotNullExpressionValue(type, "arguments.last().type");
        return type;
    }

    @NotNull
    public static final List<TypeProjection> getValueParameterTypesFromFunctionType(@NotNull KotlinType $this$getValueParameterTypesFromFunctionType) {
        Intrinsics.checkNotNullParameter($this$getValueParameterTypesFromFunctionType, "<this>");
        boolean zIsBuiltinFunctionalType = isBuiltinFunctionalType($this$getValueParameterTypesFromFunctionType);
        if (_Assertions.ENABLED && !zIsBuiltinFunctionalType) {
            throw new AssertionError(Intrinsics.stringPlus("Not a function type: ", $this$getValueParameterTypesFromFunctionType));
        }
        List arguments = $this$getValueParameterTypesFromFunctionType.getArguments();
        int first = isBuiltinExtensionFunctionalType($this$getValueParameterTypesFromFunctionType) ? 1 : 0;
        int last = arguments.size() - 1;
        boolean z = first <= last;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError(Intrinsics.stringPlus("Not an exact function type: ", $this$getValueParameterTypesFromFunctionType));
        }
        return arguments.subList(first, last);
    }

    @Nullable
    public static final Name extractParameterNameFromFunctionTypeArgument(@NotNull KotlinType $this$extractParameterNameFromFunctionTypeArgument) {
        String it;
        Intrinsics.checkNotNullParameter($this$extractParameterNameFromFunctionTypeArgument, "<this>");
        AnnotationDescriptor annotation = $this$extractParameterNameFromFunctionTypeArgument.getAnnotations().mo3547findAnnotation(StandardNames.FqNames.parameterName);
        if (annotation == null) {
            return null;
        }
        Object objSingleOrNull = CollectionsKt.singleOrNull(annotation.getAllValueArguments().values());
        StringValue stringValue = objSingleOrNull instanceof StringValue ? (StringValue) objSingleOrNull : null;
        String str = (stringValue == null || (it = stringValue.getValue()) == null || !Name.isValidIdentifier(it)) ? null : it;
        String name = str;
        if (name == null) {
            return null;
        }
        return Name.identifier(name);
    }

    @NotNull
    public static final List<TypeProjection> getFunctionTypeArgumentProjections(@Nullable KotlinType receiverType, @NotNull List<? extends KotlinType> parameterTypes, @Nullable List<Name> list, @NotNull KotlinType returnType, @NotNull KotlinBuiltIns builtIns) {
        Name it;
        KotlinType kotlinTypeReplaceAnnotations;
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        ArrayList arguments = new ArrayList(parameterTypes.size() + (receiverType != null ? 1 : 0) + 1);
        kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arguments, receiverType == null ? null : TypeUtilsKt.asTypeProjection(receiverType));
        List<? extends KotlinType> $this$mapIndexedTo$iv = parameterTypes;
        int index$iv = 0;
        for (Object item$iv : $this$mapIndexedTo$iv) {
            ArrayList arrayList = arguments;
            int index = index$iv;
            index$iv++;
            if (index < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            KotlinType type = (KotlinType) item$iv;
            Name name = (list == null || (it = list.get(index)) == null || it.isSpecial()) ? null : it;
            Name name2 = name;
            if (name2 != null) {
                FqName fqName = StandardNames.FqNames.parameterName;
                Name nameIdentifier = Name.identifier("name");
                String strAsString = name2.asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "name.asString()");
                BuiltInAnnotationDescriptor parameterNameAnnotation = new BuiltInAnnotationDescriptor(builtIns, fqName, MapsKt.mapOf(TuplesKt.to(nameIdentifier, new StringValue(strAsString))));
                kotlinTypeReplaceAnnotations = TypeUtilsKt.replaceAnnotations(type, Annotations.Companion.create(CollectionsKt.plus(type.getAnnotations(), parameterNameAnnotation)));
            } else {
                kotlinTypeReplaceAnnotations = type;
            }
            KotlinType typeToUse = kotlinTypeReplaceAnnotations;
            arrayList.add(TypeUtilsKt.asTypeProjection(typeToUse));
        }
        arguments.add(TypeUtilsKt.asTypeProjection(returnType));
        return arguments;
    }

    public static /* synthetic */ SimpleType createFunctionType$default(KotlinBuiltIns kotlinBuiltIns, Annotations annotations, KotlinType kotlinType, List list, List list2, KotlinType kotlinType2, boolean z, int i, Object obj) {
        if ((i & 64) != 0) {
            z = false;
        }
        return createFunctionType(kotlinBuiltIns, annotations, kotlinType, list, list2, kotlinType2, z);
    }

    @JvmOverloads
    @NotNull
    public static final SimpleType createFunctionType(@NotNull KotlinBuiltIns builtIns, @NotNull Annotations annotations, @Nullable KotlinType receiverType, @NotNull List<? extends KotlinType> parameterTypes, @Nullable List<Name> list, @NotNull KotlinType returnType, boolean suspendFunction) {
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        List arguments = getFunctionTypeArgumentProjections(receiverType, parameterTypes, list, returnType, builtIns);
        int parameterCount = receiverType == null ? parameterTypes.size() : parameterTypes.size() + 1;
        ClassDescriptor classDescriptor = getFunctionDescriptor(builtIns, parameterCount, suspendFunction);
        Annotations typeAnnotations = receiverType != null ? withExtensionFunctionAnnotation(annotations, builtIns) : annotations;
        KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
        return KotlinTypeFactory.simpleNotNullType(typeAnnotations, classDescriptor, arguments);
    }

    @NotNull
    public static final Annotations withExtensionFunctionAnnotation(@NotNull Annotations $this$withExtensionFunctionAnnotation, @NotNull KotlinBuiltIns builtIns) {
        Intrinsics.checkNotNullParameter($this$withExtensionFunctionAnnotation, "<this>");
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        if ($this$withExtensionFunctionAnnotation.hasAnnotation(StandardNames.FqNames.extensionFunctionType)) {
            return $this$withExtensionFunctionAnnotation;
        }
        return Annotations.Companion.create(CollectionsKt.plus($this$withExtensionFunctionAnnotation, new BuiltInAnnotationDescriptor(builtIns, StandardNames.FqNames.extensionFunctionType, MapsKt.emptyMap())));
    }

    @NotNull
    public static final ClassDescriptor getFunctionDescriptor(@NotNull KotlinBuiltIns builtIns, int parameterCount, boolean isSuspendFunction) {
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        ClassDescriptor suspendFunction = isSuspendFunction ? builtIns.getSuspendFunction(parameterCount) : builtIns.getFunction(parameterCount);
        Intrinsics.checkNotNullExpressionValue(suspendFunction, "if (isSuspendFunction) builtIns.getSuspendFunction(parameterCount) else builtIns.getFunction(parameterCount)");
        return suspendFunction;
    }
}
