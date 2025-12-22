package kotlin.reflect.jvm.internal.impl.builtins.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.util.OperatorNameConventions;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: FunctionInvokeDescriptor.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/functions/FunctionInvokeDescriptor.class */
public final class FunctionInvokeDescriptor extends SimpleFunctionDescriptorImpl {

    @NotNull
    public static final Factory Factory = new Factory(null);

    public /* synthetic */ FunctionInvokeDescriptor(DeclarationDescriptor container, FunctionInvokeDescriptor original, CallableMemberDescriptor.Kind callableKind, boolean isSuspend, DefaultConstructorMarker $constructor_marker) {
        this(container, original, callableKind, isSuspend);
    }

    private FunctionInvokeDescriptor(DeclarationDescriptor container, FunctionInvokeDescriptor original, CallableMemberDescriptor.Kind callableKind, boolean isSuspend) {
        super(container, original, Annotations.Companion.getEMPTY(), OperatorNameConventions.INVOKE, callableKind, SourceElement.NO_SOURCE);
        setOperator(true);
        setSuspend(isSuspend);
        setHasStableParameterNames(false);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    @Nullable
    protected FunctionDescriptor doSubstitute(@NotNull FunctionDescriptorImpl.CopyConfiguration configuration) {
        boolean z;
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        FunctionInvokeDescriptor substituted = (FunctionInvokeDescriptor) super.doSubstitute(configuration);
        if (substituted == null) {
            return null;
        }
        Iterable valueParameters = substituted.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "substituted.valueParameters");
        Iterable $this$none$iv = valueParameters;
        if (!($this$none$iv instanceof Collection) || !((Collection) $this$none$iv).isEmpty()) {
            Iterator it = $this$none$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    ValueParameterDescriptor it2 = (ValueParameterDescriptor) element$iv;
                    KotlinType type = it2.getType();
                    Intrinsics.checkNotNullExpressionValue(type, "it.type");
                    if (FunctionTypesKt.extractParameterNameFromFunctionTypeArgument(type) != null) {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
        } else {
            z = true;
        }
        if (z) {
            return substituted;
        }
        Iterable valueParameters2 = substituted.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters2, "substituted.valueParameters");
        Iterable $this$map$iv = valueParameters2;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ValueParameterDescriptor it3 = (ValueParameterDescriptor) item$iv$iv;
            KotlinType type2 = it3.getType();
            Intrinsics.checkNotNullExpressionValue(type2, "it.type");
            destination$iv$iv.add(FunctionTypesKt.extractParameterNameFromFunctionTypeArgument(type2));
        }
        List parameterNames = (List) destination$iv$iv;
        return substituted.replaceParameterNames(parameterNames);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    @NotNull
    protected FunctionDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor newOwner, @Nullable FunctionDescriptor original, @NotNull CallableMemberDescriptor.Kind kind, @Nullable Name newName, @NotNull Annotations annotations, @NotNull SourceElement source) {
        Intrinsics.checkNotNullParameter(newOwner, "newOwner");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(source, "source");
        return new FunctionInvokeDescriptor(newOwner, (FunctionInvokeDescriptor) original, kind, isSuspend());
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isInline() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isTailrec() {
        return false;
    }

    private final FunctionDescriptor replaceParameterNames(List<Name> list) {
        boolean z;
        Name parameterName;
        int indexShift = getValueParameters().size() - list.size();
        boolean z2 = indexShift == 0 || indexShift == 1;
        if (_Assertions.ENABLED && !z2) {
            throw new AssertionError("Assertion failed");
        }
        Iterable valueParameters = getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "valueParameters");
        Iterable $this$map$iv = valueParameters;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ValueParameterDescriptor it = (ValueParameterDescriptor) item$iv$iv;
            Name name = it.getName();
            Intrinsics.checkNotNullExpressionValue(name, "it.name");
            Name newName = name;
            int parameterIndex = it.getIndex();
            int nameIndex = parameterIndex - indexShift;
            if (nameIndex >= 0 && (parameterName = list.get(nameIndex)) != null) {
                newName = parameterName;
            }
            destination$iv$iv.add(it.copy(this, newName, parameterIndex));
        }
        List newValueParameters = (List) destination$iv$iv;
        FunctionDescriptorImpl.CopyConfiguration copyConfigurationNewCopyBuilder = newCopyBuilder(TypeSubstitutor.EMPTY);
        List<Name> $this$any$iv = list;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it2 = $this$any$iv.iterator();
            while (true) {
                if (it2.hasNext()) {
                    Object element$iv = it2.next();
                    if (((Name) element$iv) == null) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
        }
        FunctionDescriptorImpl.CopyConfiguration copyConfiguration = copyConfigurationNewCopyBuilder.setHasSynthesizedParameterNames(z).setValueParameters((List<ValueParameterDescriptor>) newValueParameters).setOriginal((CallableMemberDescriptor) getOriginal());
        Intrinsics.checkNotNullExpressionValue(copyConfiguration, "newCopyBuilder(TypeSubstitutor.EMPTY)\n                .setHasSynthesizedParameterNames(parameterNames.any { it == null })\n                .setValueParameters(newValueParameters)\n                .setOriginal(original)");
        FunctionDescriptor functionDescriptorDoSubstitute = super.doSubstitute(copyConfiguration);
        Intrinsics.checkNotNull(functionDescriptorDoSubstitute);
        return functionDescriptorDoSubstitute;
    }

    /* compiled from: FunctionInvokeDescriptor.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/functions/FunctionInvokeDescriptor$Factory.class */
    public static final class Factory {
        public /* synthetic */ Factory(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Factory() {
        }

        @NotNull
        public final FunctionInvokeDescriptor create(@NotNull FunctionClassDescriptor functionClass, boolean isSuspend) {
            Intrinsics.checkNotNullParameter(functionClass, "functionClass");
            List typeParameters = functionClass.getDeclaredTypeParameters();
            FunctionInvokeDescriptor result = new FunctionInvokeDescriptor(functionClass, null, CallableMemberDescriptor.Kind.DECLARATION, isSuspend, null);
            ReceiverParameterDescriptor thisAsReceiverParameter = functionClass.getThisAsReceiverParameter();
            List<? extends TypeParameterDescriptor> listEmptyList = CollectionsKt.emptyList();
            List $this$takeWhile$iv = typeParameters;
            ArrayList list$iv = new ArrayList();
            for (Object item$iv : $this$takeWhile$iv) {
                if (!(((TypeParameterDescriptor) item$iv).getVariance() == Variance.IN_VARIANCE)) {
                    break;
                }
                list$iv.add(item$iv);
            }
            Iterable $this$map$iv = CollectionsKt.withIndex(list$iv);
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                IndexedValue it = (IndexedValue) item$iv$iv;
                destination$iv$iv.add(FunctionInvokeDescriptor.Factory.createValueParameter(result, it.getIndex(), (TypeParameterDescriptor) it.getValue()));
            }
            result.initialize((ReceiverParameterDescriptor) null, thisAsReceiverParameter, listEmptyList, (List<ValueParameterDescriptor>) destination$iv$iv, (KotlinType) ((TypeParameterDescriptor) CollectionsKt.last(typeParameters)).getDefaultType(), Modality.ABSTRACT, DescriptorVisibilities.PUBLIC);
            result.setHasSynthesizedParameterNames(true);
            return result;
        }

        private final ValueParameterDescriptor createValueParameter(FunctionInvokeDescriptor containingDeclaration, int index, TypeParameterDescriptor typeParameter) {
            String lowerCase;
            String typeParameterName = typeParameter.getName().asString();
            Intrinsics.checkNotNullExpressionValue(typeParameterName, "typeParameter.name.asString()");
            if (Intrinsics.areEqual(typeParameterName, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE)) {
                lowerCase = "instance";
            } else if (Intrinsics.areEqual(typeParameterName, "E")) {
                lowerCase = "receiver";
            } else {
                lowerCase = typeParameterName.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.Strin….toLowerCase(Locale.ROOT)");
            }
            String name = lowerCase;
            Annotations empty = Annotations.Companion.getEMPTY();
            Name nameIdentifier = Name.identifier(name);
            Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(name)");
            SimpleType defaultType = typeParameter.getDefaultType();
            Intrinsics.checkNotNullExpressionValue(defaultType, "typeParameter.defaultType");
            SourceElement NO_SOURCE = SourceElement.NO_SOURCE;
            Intrinsics.checkNotNullExpressionValue(NO_SOURCE, "NO_SOURCE");
            return new ValueParameterDescriptorImpl(containingDeclaration, null, index, empty, nameIdentifier, defaultType, false, false, false, null, NO_SOURCE);
        }
    }
}
