package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.MutableClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: suspendFunctionTypes.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/SuspendFunctionTypesKt.class */
public final class SuspendFunctionTypesKt {

    @NotNull
    private static final MutableClassDescriptor FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL;

    @NotNull
    private static final MutableClassDescriptor FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE;

    static {
        ModuleDescriptor errorModule = ErrorUtils.getErrorModule();
        Intrinsics.checkNotNullExpressionValue(errorModule, "getErrorModule()");
        MutableClassDescriptor $this$FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL_u24lambda_u2d0 = new MutableClassDescriptor(new EmptyPackageFragmentDescriptor(errorModule, StandardNames.COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL), ClassKind.INTERFACE, false, false, StandardNames.CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL.shortName(), SourceElement.NO_SOURCE, LockBasedStorageManager.NO_LOCKS);
        $this$FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL_u24lambda_u2d0.setModality(Modality.ABSTRACT);
        $this$FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL_u24lambda_u2d0.setVisibility(DescriptorVisibilities.PUBLIC);
        TypeParameterDescriptor p0 = TypeParameterDescriptorImpl.createWithDefaultBound($this$FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL_u24lambda_u2d0, Annotations.Companion.getEMPTY(), false, Variance.IN_VARIANCE, Name.identifier(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE), 0, LockBasedStorageManager.NO_LOCKS);
        $this$FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL_u24lambda_u2d0.setTypeParameterDescriptors(CollectionsKt.listOf(p0));
        $this$FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL_u24lambda_u2d0.createTypeConstructor();
        FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL = $this$FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL_u24lambda_u2d0;
        ModuleDescriptor errorModule2 = ErrorUtils.getErrorModule();
        Intrinsics.checkNotNullExpressionValue(errorModule2, "getErrorModule()");
        MutableClassDescriptor $this$FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE_u24lambda_u2d2 = new MutableClassDescriptor(new EmptyPackageFragmentDescriptor(errorModule2, StandardNames.COROUTINES_PACKAGE_FQ_NAME_RELEASE), ClassKind.INTERFACE, false, false, StandardNames.CONTINUATION_INTERFACE_FQ_NAME_RELEASE.shortName(), SourceElement.NO_SOURCE, LockBasedStorageManager.NO_LOCKS);
        $this$FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE_u24lambda_u2d2.setModality(Modality.ABSTRACT);
        $this$FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE_u24lambda_u2d2.setVisibility(DescriptorVisibilities.PUBLIC);
        TypeParameterDescriptor p02 = TypeParameterDescriptorImpl.createWithDefaultBound($this$FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE_u24lambda_u2d2, Annotations.Companion.getEMPTY(), false, Variance.IN_VARIANCE, Name.identifier(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE), 0, LockBasedStorageManager.NO_LOCKS);
        $this$FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE_u24lambda_u2d2.setTypeParameterDescriptors(CollectionsKt.listOf(p02));
        $this$FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE_u24lambda_u2d2.createTypeConstructor();
        FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE = $this$FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE_u24lambda_u2d2;
    }

    @NotNull
    public static final SimpleType transformSuspendFunctionToRuntimeFunctionType(@NotNull KotlinType suspendFunType, boolean isReleaseCoroutines) {
        Intrinsics.checkNotNullParameter(suspendFunType, "suspendFunType");
        boolean zIsSuspendFunctionType = FunctionTypesKt.isSuspendFunctionType(suspendFunType);
        if (_Assertions.ENABLED && !zIsSuspendFunctionType) {
            throw new AssertionError(Intrinsics.stringPlus("This type should be suspend function type: ", suspendFunType));
        }
        KotlinBuiltIns builtIns = TypeUtilsKt.getBuiltIns(suspendFunType);
        Annotations annotations = suspendFunType.getAnnotations();
        KotlinType receiverTypeFromFunctionType = FunctionTypesKt.getReceiverTypeFromFunctionType(suspendFunType);
        Iterable $this$map$iv = FunctionTypesKt.getValueParameterTypesFromFunctionType(suspendFunType);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            TypeProjection p0 = (TypeProjection) item$iv$iv;
            destination$iv$iv.add(p0.getType());
        }
        ArrayList arrayList = (List) destination$iv$iv;
        KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
        Annotations empty = Annotations.Companion.getEMPTY();
        TypeConstructor typeConstructor = isReleaseCoroutines ? FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE.getTypeConstructor() : FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL.getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "if (isReleaseCoroutines) FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE.typeConstructor\n                    else FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL.typeConstructor");
        List listPlus = CollectionsKt.plus((Collection<? extends SimpleType>) arrayList, KotlinTypeFactory.simpleType$default(empty, typeConstructor, CollectionsKt.listOf(TypeUtilsKt.asTypeProjection(FunctionTypesKt.getReturnTypeFromFunctionType(suspendFunType))), false, null, 16, null));
        SimpleType nullableAnyType = TypeUtilsKt.getBuiltIns(suspendFunType).getNullableAnyType();
        Intrinsics.checkNotNullExpressionValue(nullableAnyType, "suspendFunType.builtIns.nullableAnyType");
        return FunctionTypesKt.createFunctionType$default(builtIns, annotations, receiverTypeFromFunctionType, listPlus, null, nullableAnyType, false, 64, null).makeNullableAsSpecified(suspendFunType.isMarkedNullable());
    }

    public static final boolean isContinuation(@Nullable FqName name, boolean isReleaseCoroutines) {
        return isReleaseCoroutines ? Intrinsics.areEqual(name, StandardNames.CONTINUATION_INTERFACE_FQ_NAME_RELEASE) : Intrinsics.areEqual(name, StandardNames.CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL);
    }
}
