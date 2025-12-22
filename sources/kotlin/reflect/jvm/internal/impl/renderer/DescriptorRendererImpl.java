package kotlin.reflect.jvm.internal.impl.renderer;

import ch.qos.logback.classic.spi.CallerData;
import cn.hutool.core.text.StrPool;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PossiblyInnerType;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUseSiteTarget;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.AbbreviatedType;
import kotlin.reflect.jvm.internal.impl.types.ErrorType;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.UnresolvedType;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.WrappedType;
import kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: DescriptorRendererImpl.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/renderer/DescriptorRendererImpl.class */
public final class DescriptorRendererImpl extends DescriptorRenderer implements DescriptorRendererOptions {

    @NotNull
    private final DescriptorRendererOptionsImpl options;

    @NotNull
    private final Lazy functionTypeAnnotationsRenderer$delegate;

    /* compiled from: DescriptorRendererImpl.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/renderer/DescriptorRendererImpl$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[RenderingFormat.values().length];
            iArr[RenderingFormat.PLAIN.ordinal()] = 1;
            iArr[RenderingFormat.HTML.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ParameterNameRenderingPolicy.values().length];
            iArr2[ParameterNameRenderingPolicy.ALL.ordinal()] = 1;
            iArr2[ParameterNameRenderingPolicy.ONLY_NON_SYNTHESIZED.ordinal()] = 2;
            iArr2[ParameterNameRenderingPolicy.NONE.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public boolean getIncludeAnnotationArguments() {
        return this.options.getIncludeAnnotationArguments();
    }

    public boolean getIncludeEmptyAnnotationArguments() {
        return this.options.getIncludeEmptyAnnotationArguments();
    }

    public boolean getActualPropertiesInPrimaryConstructor() {
        return this.options.getActualPropertiesInPrimaryConstructor();
    }

    public boolean getAlwaysRenderModifiers() {
        return this.options.getAlwaysRenderModifiers();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    @NotNull
    public AnnotationArgumentsRenderingPolicy getAnnotationArgumentsRenderingPolicy() {
        return this.options.getAnnotationArgumentsRenderingPolicy();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setAnnotationArgumentsRenderingPolicy(@NotNull AnnotationArgumentsRenderingPolicy annotationArgumentsRenderingPolicy) {
        Intrinsics.checkNotNullParameter(annotationArgumentsRenderingPolicy, "<set-?>");
        this.options.setAnnotationArgumentsRenderingPolicy(annotationArgumentsRenderingPolicy);
    }

    @Nullable
    public Function1<AnnotationDescriptor, Boolean> getAnnotationFilter() {
        return this.options.getAnnotationFilter();
    }

    public boolean getBoldOnlyForNamesInHtml() {
        return this.options.getBoldOnlyForNamesInHtml();
    }

    public boolean getClassWithPrimaryConstructor() {
        return this.options.getClassWithPrimaryConstructor();
    }

    @NotNull
    public ClassifierNamePolicy getClassifierNamePolicy() {
        return this.options.getClassifierNamePolicy();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setClassifierNamePolicy(@NotNull ClassifierNamePolicy classifierNamePolicy) {
        Intrinsics.checkNotNullParameter(classifierNamePolicy, "<set-?>");
        this.options.setClassifierNamePolicy(classifierNamePolicy);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public boolean getDebugMode() {
        return this.options.getDebugMode();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setDebugMode(boolean z) {
        this.options.setDebugMode(z);
    }

    @Nullable
    public Function1<ValueParameterDescriptor, String> getDefaultParameterValueRenderer() {
        return this.options.getDefaultParameterValueRenderer();
    }

    public boolean getEachAnnotationOnNewLine() {
        return this.options.getEachAnnotationOnNewLine();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public boolean getEnhancedTypes() {
        return this.options.getEnhancedTypes();
    }

    @NotNull
    public Set<FqName> getExcludedAnnotationClasses() {
        return this.options.getExcludedAnnotationClasses();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    @NotNull
    public Set<FqName> getExcludedTypeAnnotationClasses() {
        return this.options.getExcludedTypeAnnotationClasses();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setExcludedTypeAnnotationClasses(@NotNull Set<FqName> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.options.setExcludedTypeAnnotationClasses(set);
    }

    public boolean getIncludeAdditionalModifiers() {
        return this.options.getIncludeAdditionalModifiers();
    }

    public boolean getIncludePropertyConstant() {
        return this.options.getIncludePropertyConstant();
    }

    public boolean getInformativeErrorType() {
        return this.options.getInformativeErrorType();
    }

    @NotNull
    public Set<DescriptorRendererModifier> getModifiers() {
        return this.options.getModifiers();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setModifiers(@NotNull Set<? extends DescriptorRendererModifier> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.options.setModifiers(set);
    }

    public boolean getNormalizedVisibilities() {
        return this.options.getNormalizedVisibilities();
    }

    @NotNull
    public OverrideRenderingPolicy getOverrideRenderingPolicy() {
        return this.options.getOverrideRenderingPolicy();
    }

    @NotNull
    public ParameterNameRenderingPolicy getParameterNameRenderingPolicy() {
        return this.options.getParameterNameRenderingPolicy();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setParameterNameRenderingPolicy(@NotNull ParameterNameRenderingPolicy parameterNameRenderingPolicy) {
        Intrinsics.checkNotNullParameter(parameterNameRenderingPolicy, "<set-?>");
        this.options.setParameterNameRenderingPolicy(parameterNameRenderingPolicy);
    }

    public boolean getParameterNamesInFunctionalTypes() {
        return this.options.getParameterNamesInFunctionalTypes();
    }

    public boolean getPresentableUnresolvedTypes() {
        return this.options.getPresentableUnresolvedTypes();
    }

    @NotNull
    public PropertyAccessorRenderingPolicy getPropertyAccessorRenderingPolicy() {
        return this.options.getPropertyAccessorRenderingPolicy();
    }

    public boolean getReceiverAfterName() {
        return this.options.getReceiverAfterName();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setReceiverAfterName(boolean z) {
        this.options.setReceiverAfterName(z);
    }

    public boolean getRenderCompanionObjectName() {
        return this.options.getRenderCompanionObjectName();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setRenderCompanionObjectName(boolean z) {
        this.options.setRenderCompanionObjectName(z);
    }

    public boolean getRenderConstructorDelegation() {
        return this.options.getRenderConstructorDelegation();
    }

    public boolean getRenderConstructorKeyword() {
        return this.options.getRenderConstructorKeyword();
    }

    public boolean getRenderDefaultAnnotationArguments() {
        return this.options.getRenderDefaultAnnotationArguments();
    }

    public boolean getRenderDefaultModality() {
        return this.options.getRenderDefaultModality();
    }

    public boolean getRenderDefaultVisibility() {
        return this.options.getRenderDefaultVisibility();
    }

    public boolean getRenderPrimaryConstructorParametersAsProperties() {
        return this.options.getRenderPrimaryConstructorParametersAsProperties();
    }

    public boolean getRenderTypeExpansions() {
        return this.options.getRenderTypeExpansions();
    }

    public boolean getRenderUnabbreviatedType() {
        return this.options.getRenderUnabbreviatedType();
    }

    public boolean getSecondaryConstructorsAsPrimary() {
        return this.options.getSecondaryConstructorsAsPrimary();
    }

    public boolean getStartFromDeclarationKeyword() {
        return this.options.getStartFromDeclarationKeyword();
    }

    public boolean getStartFromName() {
        return this.options.getStartFromName();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setStartFromName(boolean z) {
        this.options.setStartFromName(z);
    }

    @NotNull
    public RenderingFormat getTextFormat() {
        return this.options.getTextFormat();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setTextFormat(@NotNull RenderingFormat renderingFormat) {
        Intrinsics.checkNotNullParameter(renderingFormat, "<set-?>");
        this.options.setTextFormat(renderingFormat);
    }

    @NotNull
    public Function1<KotlinType, KotlinType> getTypeNormalizer() {
        return this.options.getTypeNormalizer();
    }

    public boolean getUninferredTypeParameterAsName() {
        return this.options.getUninferredTypeParameterAsName();
    }

    public boolean getUnitReturnType() {
        return this.options.getUnitReturnType();
    }

    @NotNull
    public DescriptorRenderer.ValueParametersHandler getValueParametersHandler() {
        return this.options.getValueParametersHandler();
    }

    public boolean getVerbose() {
        return this.options.getVerbose();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setVerbose(boolean z) {
        this.options.setVerbose(z);
    }

    public boolean getWithDefinedIn() {
        return this.options.getWithDefinedIn();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setWithDefinedIn(boolean z) {
        this.options.setWithDefinedIn(z);
    }

    public boolean getWithSourceFileForTopLevel() {
        return this.options.getWithSourceFileForTopLevel();
    }

    public boolean getWithoutReturnType() {
        return this.options.getWithoutReturnType();
    }

    public boolean getWithoutSuperTypes() {
        return this.options.getWithoutSuperTypes();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setWithoutSuperTypes(boolean z) {
        this.options.setWithoutSuperTypes(z);
    }

    public boolean getWithoutTypeParameters() {
        return this.options.getWithoutTypeParameters();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setWithoutTypeParameters(boolean z) {
        this.options.setWithoutTypeParameters(z);
    }

    @NotNull
    public final DescriptorRendererOptionsImpl getOptions() {
        return this.options;
    }

    public DescriptorRendererImpl(@NotNull DescriptorRendererOptionsImpl options) {
        Intrinsics.checkNotNullParameter(options, "options");
        this.options = options;
        boolean zIsLocked = this.options.isLocked();
        if (_Assertions.ENABLED && !zIsLocked) {
            throw new AssertionError("Assertion failed");
        }
        this.functionTypeAnnotationsRenderer$delegate = LazyKt.lazy(new Function0<DescriptorRendererImpl>() { // from class: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl$functionTypeAnnotationsRenderer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final DescriptorRendererImpl invoke() {
                return (DescriptorRendererImpl) this.this$0.withOptions(new Function1<DescriptorRendererOptions, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl$functionTypeAnnotationsRenderer$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DescriptorRendererOptions descriptorRendererOptions) {
                        invoke2(descriptorRendererOptions);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(@NotNull DescriptorRendererOptions withOptions) {
                        Intrinsics.checkNotNullParameter(withOptions, "$this$withOptions");
                        withOptions.setExcludedTypeAnnotationClasses(SetsKt.plus((Set) withOptions.getExcludedTypeAnnotationClasses(), (Iterable) CollectionsKt.listOf(StandardNames.FqNames.extensionFunctionType)));
                    }
                });
            }
        });
    }

    private final DescriptorRendererImpl getFunctionTypeAnnotationsRenderer() {
        return (DescriptorRendererImpl) this.functionTypeAnnotationsRenderer$delegate.getValue();
    }

    private final String renderKeyword(String keyword) {
        switch (WhenMappings.$EnumSwitchMapping$0[getTextFormat().ordinal()]) {
            case 1:
                return keyword;
            case 2:
                return getBoldOnlyForNamesInHtml() ? keyword : "<b>" + keyword + "</b>";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final String renderError(String keyword) {
        switch (WhenMappings.$EnumSwitchMapping$0[getTextFormat().ordinal()]) {
            case 1:
                return keyword;
            case 2:
                return "<font color=red><b>" + keyword + "</b></font>";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final String escape(String string) {
        return getTextFormat().escape(string);
    }

    private final String lt() {
        return escape("<");
    }

    private final String gt() {
        return escape(">");
    }

    private final String arrow() {
        switch (WhenMappings.$EnumSwitchMapping$0[getTextFormat().ordinal()]) {
            case 1:
                return escape("->");
            case 2:
                return "&rarr;";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    @NotNull
    public String renderMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        switch (WhenMappings.$EnumSwitchMapping$0[getTextFormat().ordinal()]) {
            case 1:
                return message;
            case 2:
                return "<i>" + message + "</i>";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    @NotNull
    public String renderName(@NotNull Name name, boolean rootRenderedElement) {
        Intrinsics.checkNotNullParameter(name, "name");
        String escaped = escape(RenderingUtilsKt.render(name));
        if (getBoldOnlyForNamesInHtml() && getTextFormat() == RenderingFormat.HTML && rootRenderedElement) {
            return "<b>" + escaped + "</b>";
        }
        return escaped;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderName(DeclarationDescriptor descriptor, StringBuilder builder, boolean rootRenderedElement) {
        Name name = descriptor.getName();
        Intrinsics.checkNotNullExpressionValue(name, "descriptor.name");
        builder.append(renderName(name, rootRenderedElement));
    }

    private final void renderCompanionObjectName(DeclarationDescriptor descriptor, StringBuilder builder) {
        if (getRenderCompanionObjectName()) {
            if (getStartFromName()) {
                builder.append("companion object");
            }
            renderSpaceIfNeeded(builder);
            DeclarationDescriptor containingDeclaration = descriptor.getContainingDeclaration();
            if (containingDeclaration != null) {
                builder.append("of ");
                Name name = containingDeclaration.getName();
                Intrinsics.checkNotNullExpressionValue(name, "containingDeclaration.name");
                builder.append(renderName(name, false));
            }
        }
        if (getVerbose() || !Intrinsics.areEqual(descriptor.getName(), SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT)) {
            if (!getStartFromName()) {
                renderSpaceIfNeeded(builder);
            }
            Name name2 = descriptor.getName();
            Intrinsics.checkNotNullExpressionValue(name2, "descriptor.name");
            builder.append(renderName(name2, true));
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    @NotNull
    public String renderFqName(@NotNull FqNameUnsafe fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        List<Name> listPathSegments = fqName.pathSegments();
        Intrinsics.checkNotNullExpressionValue(listPathSegments, "fqName.pathSegments()");
        return renderFqName(listPathSegments);
    }

    private final String renderFqName(List<Name> list) {
        return escape(RenderingUtilsKt.renderFqName(list));
    }

    @NotNull
    public String renderClassifierName(@NotNull ClassifierDescriptor klass) {
        Intrinsics.checkNotNullParameter(klass, "klass");
        if (ErrorUtils.isError(klass)) {
            return klass.getTypeConstructor().toString();
        }
        return getClassifierNamePolicy().renderClassifier(klass, this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    @NotNull
    public String renderType(@NotNull KotlinType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        StringBuilder $this$renderType_u24lambda_u2d0 = new StringBuilder();
        renderNormalizedType($this$renderType_u24lambda_u2d0, getTypeNormalizer().invoke(type));
        String string = $this$renderType_u24lambda_u2d0.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    private final void renderNormalizedType(StringBuilder $this$renderNormalizedType, KotlinType type) {
        UnwrappedType unwrappedTypeUnwrap = type.unwrap();
        AbbreviatedType abbreviated = unwrappedTypeUnwrap instanceof AbbreviatedType ? (AbbreviatedType) unwrappedTypeUnwrap : null;
        if (abbreviated != null) {
            if (getRenderTypeExpansions()) {
                renderNormalizedTypeAsIs($this$renderNormalizedType, abbreviated.getExpandedType());
                return;
            }
            renderNormalizedTypeAsIs($this$renderNormalizedType, abbreviated.getAbbreviation());
            if (getRenderUnabbreviatedType()) {
                renderAbbreviatedTypeExpansion($this$renderNormalizedType, abbreviated);
                return;
            }
            return;
        }
        renderNormalizedTypeAsIs($this$renderNormalizedType, type);
    }

    private final void renderAbbreviatedTypeExpansion(StringBuilder $this$renderAbbreviatedTypeExpansion, AbbreviatedType abbreviated) {
        if (getTextFormat() == RenderingFormat.HTML) {
            $this$renderAbbreviatedTypeExpansion.append("<font color=\"808080\"><i>");
        }
        $this$renderAbbreviatedTypeExpansion.append(" /* = ");
        renderNormalizedTypeAsIs($this$renderAbbreviatedTypeExpansion, abbreviated.getExpandedType());
        $this$renderAbbreviatedTypeExpansion.append(" */");
        if (getTextFormat() == RenderingFormat.HTML) {
            $this$renderAbbreviatedTypeExpansion.append("</i></font>");
        }
    }

    private final void renderNormalizedTypeAsIs(StringBuilder $this$renderNormalizedTypeAsIs, KotlinType type) {
        if ((type instanceof WrappedType) && getDebugMode() && !((WrappedType) type).isComputed()) {
            $this$renderNormalizedTypeAsIs.append("<Not computed yet>");
            return;
        }
        UnwrappedType unwrappedType = type.unwrap();
        if (!(unwrappedType instanceof FlexibleType)) {
            if (unwrappedType instanceof SimpleType) {
                renderSimpleType($this$renderNormalizedTypeAsIs, (SimpleType) unwrappedType);
                return;
            }
            return;
        }
        $this$renderNormalizedTypeAsIs.append(((FlexibleType) unwrappedType).render(this, this));
    }

    private final void renderSimpleType(StringBuilder $this$renderSimpleType, SimpleType type) {
        if (Intrinsics.areEqual(type, TypeUtils.CANT_INFER_FUNCTION_PARAM_TYPE) || TypeUtils.isDontCarePlaceholder(type)) {
            $this$renderSimpleType.append("???");
            return;
        }
        if (ErrorUtils.isUninferredParameter(type)) {
            if (getUninferredTypeParameterAsName()) {
                String string = ((ErrorUtils.UninferredParameterTypeConstructor) type.getConstructor()).getTypeParameterDescriptor().getName().toString();
                Intrinsics.checkNotNullExpressionValue(string, "type.constructor as UninferredParameterTypeConstructor).typeParameterDescriptor.name.toString()");
                $this$renderSimpleType.append(renderError(string));
                return;
            }
            $this$renderSimpleType.append("???");
            return;
        }
        if (KotlinTypeKt.isError(type)) {
            renderDefaultType($this$renderSimpleType, type);
        } else if (shouldRenderAsPrettyFunctionType(type)) {
            renderFunctionType($this$renderSimpleType, type);
        } else {
            renderDefaultType($this$renderSimpleType, type);
        }
    }

    private final boolean shouldRenderAsPrettyFunctionType(KotlinType type) {
        boolean z;
        if (FunctionTypesKt.isBuiltinFunctionalType(type)) {
            Iterable $this$none$iv = type.getArguments();
            if (!($this$none$iv instanceof Collection) || !((Collection) $this$none$iv).isEmpty()) {
                Iterator it = $this$none$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        TypeProjection it2 = (TypeProjection) element$iv;
                        if (it2.isStarProjection()) {
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
                return true;
            }
        }
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    @NotNull
    public String renderFlexibleType(@NotNull String lowerRendered, @NotNull String upperRendered, @NotNull KotlinBuiltIns builtIns) {
        Intrinsics.checkNotNullParameter(lowerRendered, "lowerRendered");
        Intrinsics.checkNotNullParameter(upperRendered, "upperRendered");
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        if (differsOnlyInNullability(lowerRendered, upperRendered)) {
            if (StringsKt.startsWith$default(upperRendered, "(", false, 2, (Object) null)) {
                return '(' + lowerRendered + ")!";
            }
            return Intrinsics.stringPlus(lowerRendered, "!");
        }
        ClassifierNamePolicy classifierNamePolicy = getClassifierNamePolicy();
        ClassDescriptor collection = builtIns.getCollection();
        Intrinsics.checkNotNullExpressionValue(collection, "builtIns.collection");
        String kotlinCollectionsPrefix = StringsKt.substringBefore$default(classifierNamePolicy.renderClassifier(collection, this), "Collection", (String) null, 2, (Object) null);
        String simpleCollection = replacePrefixes(lowerRendered, Intrinsics.stringPlus(kotlinCollectionsPrefix, "Mutable"), upperRendered, kotlinCollectionsPrefix, kotlinCollectionsPrefix + "(Mutable)");
        if (simpleCollection != null) {
            return simpleCollection;
        }
        String mutableEntry = replacePrefixes(lowerRendered, Intrinsics.stringPlus(kotlinCollectionsPrefix, "MutableMap.MutableEntry"), upperRendered, Intrinsics.stringPlus(kotlinCollectionsPrefix, "Map.Entry"), Intrinsics.stringPlus(kotlinCollectionsPrefix, "(Mutable)Map.(Mutable)Entry"));
        if (mutableEntry != null) {
            return mutableEntry;
        }
        ClassifierNamePolicy classifierNamePolicy2 = getClassifierNamePolicy();
        ClassDescriptor array = builtIns.getArray();
        Intrinsics.checkNotNullExpressionValue(array, "builtIns.array");
        String kotlinPrefix = StringsKt.substringBefore$default(classifierNamePolicy2.renderClassifier(array, this), "Array", (String) null, 2, (Object) null);
        String array2 = replacePrefixes(lowerRendered, Intrinsics.stringPlus(kotlinPrefix, escape("Array<")), upperRendered, Intrinsics.stringPlus(kotlinPrefix, escape("Array<out ")), Intrinsics.stringPlus(kotlinPrefix, escape("Array<(out) ")));
        return array2 != null ? array2 : '(' + lowerRendered + ".." + upperRendered + ')';
    }

    @NotNull
    public String renderTypeArguments(@NotNull List<? extends TypeProjection> typeArguments) {
        Intrinsics.checkNotNullParameter(typeArguments, "typeArguments");
        if (typeArguments.isEmpty()) {
            return "";
        }
        StringBuilder $this$renderTypeArguments_u24lambda_u2d2 = new StringBuilder();
        $this$renderTypeArguments_u24lambda_u2d2.append(lt());
        appendTypeProjections($this$renderTypeArguments_u24lambda_u2d2, typeArguments);
        $this$renderTypeArguments_u24lambda_u2d2.append(gt());
        String string = $this$renderTypeArguments_u24lambda_u2d2.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    private final void renderDefaultType(StringBuilder $this$renderDefaultType, KotlinType type) {
        renderAnnotations$default(this, $this$renderDefaultType, type, null, 2, null);
        if (KotlinTypeKt.isError(type)) {
            if ((type instanceof UnresolvedType) && getPresentableUnresolvedTypes()) {
                $this$renderDefaultType.append(((UnresolvedType) type).getPresentableName());
            } else if ((type instanceof ErrorType) && !getInformativeErrorType()) {
                $this$renderDefaultType.append(((ErrorType) type).getPresentableName());
            } else {
                $this$renderDefaultType.append(type.getConstructor().toString());
            }
            $this$renderDefaultType.append(renderTypeArguments(type.getArguments()));
        } else {
            renderTypeConstructorAndArguments$default(this, $this$renderDefaultType, type, null, 2, null);
        }
        if (type.isMarkedNullable()) {
            $this$renderDefaultType.append(CallerData.NA);
        }
        if (SpecialTypesKt.isDefinitelyNotNullType(type)) {
            $this$renderDefaultType.append("!!");
        }
    }

    static /* synthetic */ void renderTypeConstructorAndArguments$default(DescriptorRendererImpl descriptorRendererImpl, StringBuilder sb, KotlinType kotlinType, TypeConstructor typeConstructor, int i, Object obj) {
        if ((i & 2) != 0) {
            typeConstructor = kotlinType.getConstructor();
        }
        descriptorRendererImpl.renderTypeConstructorAndArguments(sb, kotlinType, typeConstructor);
    }

    private final void renderTypeConstructorAndArguments(StringBuilder $this$renderTypeConstructorAndArguments, KotlinType type, TypeConstructor typeConstructor) {
        PossiblyInnerType possiblyInnerType = TypeParameterUtilsKt.buildPossiblyInnerType(type);
        if (possiblyInnerType == null) {
            $this$renderTypeConstructorAndArguments.append(renderTypeConstructor(typeConstructor));
            $this$renderTypeConstructorAndArguments.append(renderTypeArguments(type.getArguments()));
        } else {
            renderPossiblyInnerType($this$renderTypeConstructorAndArguments, possiblyInnerType);
        }
    }

    private final void renderPossiblyInnerType(StringBuilder $this$renderPossiblyInnerType, PossiblyInnerType possiblyInnerType) {
        StringBuilder sbAppend;
        PossiblyInnerType it = possiblyInnerType.getOuterType();
        if (it == null) {
            sbAppend = null;
        } else {
            renderPossiblyInnerType($this$renderPossiblyInnerType, it);
            $this$renderPossiblyInnerType.append('.');
            Name name = possiblyInnerType.getClassifierDescriptor().getName();
            Intrinsics.checkNotNullExpressionValue(name, "possiblyInnerType.classifierDescriptor.name");
            sbAppend = $this$renderPossiblyInnerType.append(renderName(name, false));
        }
        if (sbAppend == null) {
            TypeConstructor typeConstructor = possiblyInnerType.getClassifierDescriptor().getTypeConstructor();
            Intrinsics.checkNotNullExpressionValue(typeConstructor, "possiblyInnerType.classifierDescriptor.typeConstructor");
            $this$renderPossiblyInnerType.append(renderTypeConstructor(typeConstructor));
        }
        $this$renderPossiblyInnerType.append(renderTypeArguments(possiblyInnerType.getArguments()));
    }

    @NotNull
    public String renderTypeConstructor(@NotNull TypeConstructor typeConstructor) {
        Intrinsics.checkNotNullParameter(typeConstructor, "typeConstructor");
        ClassifierDescriptor cd = typeConstructor.mo3831getDeclarationDescriptor();
        if (cd instanceof TypeParameterDescriptor ? true : cd instanceof ClassDescriptor ? true : cd instanceof TypeAliasDescriptor) {
            return renderClassifierName(cd);
        }
        if (cd == null) {
            return typeConstructor.toString();
        }
        throw new IllegalStateException(Intrinsics.stringPlus("Unexpected classifier: ", cd.getClass()).toString());
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    @NotNull
    public String renderTypeProjection(@NotNull TypeProjection typeProjection) {
        Intrinsics.checkNotNullParameter(typeProjection, "typeProjection");
        StringBuilder $this$renderTypeProjection_u24lambda_u2d4 = new StringBuilder();
        appendTypeProjections($this$renderTypeProjection_u24lambda_u2d4, CollectionsKt.listOf(typeProjection));
        String string = $this$renderTypeProjection_u24lambda_u2d4.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    private final void appendTypeProjections(StringBuilder $this$appendTypeProjections, List<? extends TypeProjection> list) {
        CollectionsKt.joinTo$default(list, $this$appendTypeProjections, ", ", null, null, 0, null, new Function1<TypeProjection, CharSequence>() { // from class: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.appendTypeProjections.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final CharSequence invoke(@NotNull TypeProjection it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (it.isStarProjection()) {
                    return "*";
                }
                DescriptorRendererImpl descriptorRendererImpl = DescriptorRendererImpl.this;
                KotlinType type = it.getType();
                Intrinsics.checkNotNullExpressionValue(type, "it.type");
                String type2 = descriptorRendererImpl.renderType(type);
                return it.getProjectionKind() == Variance.INVARIANT ? type2 : it.getProjectionKind() + ' ' + type2;
            }
        }, 60, null);
    }

    private final void renderFunctionType(StringBuilder $this$renderFunctionType, KotlinType type) {
        Name nameExtractParameterNameFromFunctionTypeArgument;
        int lengthBefore = $this$renderFunctionType.length();
        DescriptorRendererImpl $this$renderFunctionType_u24lambda_u2d5 = getFunctionTypeAnnotationsRenderer();
        renderAnnotations$default($this$renderFunctionType_u24lambda_u2d5, $this$renderFunctionType, type, null, 2, null);
        boolean hasAnnotations = $this$renderFunctionType.length() != lengthBefore;
        boolean isSuspend = FunctionTypesKt.isSuspendFunctionType(type);
        boolean isNullable = type.isMarkedNullable();
        KotlinType receiverType = FunctionTypesKt.getReceiverTypeFromFunctionType(type);
        boolean needParenthesis = isNullable || (hasAnnotations && receiverType != null);
        if (needParenthesis) {
            if (isSuspend) {
                $this$renderFunctionType.insert(lengthBefore, '(');
            } else {
                if (hasAnnotations) {
                    boolean zIsWhitespace = CharsKt.isWhitespace(StringsKt.last($this$renderFunctionType));
                    if (_Assertions.ENABLED && !zIsWhitespace) {
                        throw new AssertionError("Assertion failed");
                    }
                    if ($this$renderFunctionType.charAt(StringsKt.getLastIndex($this$renderFunctionType) - 1) != ')') {
                        $this$renderFunctionType.insert(StringsKt.getLastIndex($this$renderFunctionType), "()");
                    }
                }
                $this$renderFunctionType.append("(");
            }
        }
        renderModifier($this$renderFunctionType, isSuspend, "suspend");
        if (receiverType != null) {
            boolean surroundReceiver = (shouldRenderAsPrettyFunctionType(receiverType) && !receiverType.isMarkedNullable()) || hasModifiersOrAnnotations(receiverType);
            if (surroundReceiver) {
                $this$renderFunctionType.append("(");
            }
            renderNormalizedType($this$renderFunctionType, receiverType);
            if (surroundReceiver) {
                $this$renderFunctionType.append(")");
            }
            $this$renderFunctionType.append(".");
        }
        $this$renderFunctionType.append("(");
        List parameterTypes = FunctionTypesKt.getValueParameterTypesFromFunctionType(type);
        int i = 0;
        for (TypeProjection typeProjection : parameterTypes) {
            int index = i;
            i++;
            if (index > 0) {
                $this$renderFunctionType.append(", ");
            }
            if (getParameterNamesInFunctionalTypes()) {
                KotlinType type2 = typeProjection.getType();
                Intrinsics.checkNotNullExpressionValue(type2, "typeProjection.type");
                nameExtractParameterNameFromFunctionTypeArgument = FunctionTypesKt.extractParameterNameFromFunctionTypeArgument(type2);
            } else {
                nameExtractParameterNameFromFunctionTypeArgument = null;
            }
            Name name = nameExtractParameterNameFromFunctionTypeArgument;
            if (name != null) {
                $this$renderFunctionType.append(renderName(name, false));
                $this$renderFunctionType.append(": ");
            }
            $this$renderFunctionType.append(renderTypeProjection(typeProjection));
        }
        $this$renderFunctionType.append(") ").append(arrow()).append(" ");
        renderNormalizedType($this$renderFunctionType, FunctionTypesKt.getReturnTypeFromFunctionType(type));
        if (needParenthesis) {
            $this$renderFunctionType.append(")");
        }
        if (isNullable) {
            $this$renderFunctionType.append(CallerData.NA);
        }
    }

    private final boolean hasModifiersOrAnnotations(KotlinType $this$hasModifiersOrAnnotations) {
        return FunctionTypesKt.isSuspendFunctionType($this$hasModifiersOrAnnotations) || !$this$hasModifiersOrAnnotations.getAnnotations().isEmpty();
    }

    private final void appendDefinedIn(StringBuilder $this$appendDefinedIn, DeclarationDescriptor descriptor) {
        String sourceFileName;
        if ((descriptor instanceof PackageFragmentDescriptor) || (descriptor instanceof PackageViewDescriptor)) {
            return;
        }
        if (descriptor instanceof ModuleDescriptor) {
            $this$appendDefinedIn.append(" is a module");
            return;
        }
        DeclarationDescriptor containingDeclaration = descriptor.getContainingDeclaration();
        if (containingDeclaration != null && !(containingDeclaration instanceof ModuleDescriptor)) {
            $this$appendDefinedIn.append(" ").append(renderMessage("defined in")).append(" ");
            FqNameUnsafe fqName = DescriptorUtils.getFqName(containingDeclaration);
            Intrinsics.checkNotNullExpressionValue(fqName, "getFqName(containingDeclaration)");
            $this$appendDefinedIn.append(fqName.isRoot() ? "root package" : renderFqName(fqName));
            if (getWithSourceFileForTopLevel() && (containingDeclaration instanceof PackageFragmentDescriptor) && (descriptor instanceof DeclarationDescriptorWithSource) && (sourceFileName = ((DeclarationDescriptorWithSource) descriptor).getSource().getContainingFile().getName()) != null) {
                $this$appendDefinedIn.append(" ").append(renderMessage("in file")).append(" ").append(sourceFileName);
            }
        }
    }

    static /* synthetic */ void renderAnnotations$default(DescriptorRendererImpl descriptorRendererImpl, StringBuilder sb, Annotated annotated, AnnotationUseSiteTarget annotationUseSiteTarget, int i, Object obj) {
        if ((i & 2) != 0) {
            annotationUseSiteTarget = null;
        }
        descriptorRendererImpl.renderAnnotations(sb, annotated, annotationUseSiteTarget);
    }

    private final void renderAnnotations(StringBuilder $this$renderAnnotations, Annotated annotated, AnnotationUseSiteTarget target) {
        if (getModifiers().contains(DescriptorRendererModifier.ANNOTATIONS)) {
            Set excluded = annotated instanceof KotlinType ? getExcludedTypeAnnotationClasses() : getExcludedAnnotationClasses();
            Function1 annotationFilter = getAnnotationFilter();
            for (AnnotationDescriptor annotation : annotated.getAnnotations()) {
                if (!CollectionsKt.contains(excluded, annotation.getFqName()) && !isParameterName(annotation) && (annotationFilter == null || annotationFilter.invoke(annotation).booleanValue())) {
                    $this$renderAnnotations.append(renderAnnotation(annotation, target));
                    if (getEachAnnotationOnNewLine()) {
                        Intrinsics.checkNotNullExpressionValue($this$renderAnnotations.append('\n'), "append('\\n')");
                    } else {
                        $this$renderAnnotations.append(" ");
                    }
                }
            }
        }
    }

    private final boolean isParameterName(AnnotationDescriptor $this$isParameterName) {
        return Intrinsics.areEqual($this$isParameterName.getFqName(), StandardNames.FqNames.parameterName);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0081  */
    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String renderAnnotation(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor r12, @org.jetbrains.annotations.Nullable kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUseSiteTarget r13) {
        /*
            r11 = this;
            r0 = r12
            java.lang.String r1 = "annotation"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = 0
            r14 = r0
            r0 = 0
            r15 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r1.<init>()
            r15 = r0
            r0 = 0
            r16 = r0
            r0 = 0
            r17 = r0
            r0 = r15
            r18 = r0
            r0 = 0
            r19 = r0
            r0 = r18
            r1 = 64
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r13
            if (r0 == 0) goto L3c
            r0 = r18
            r1 = r13
            java.lang.String r1 = r1.getRenderName()
            java.lang.String r2 = ":"
            java.lang.String r1 = kotlin.jvm.internal.Intrinsics.stringPlus(r1, r2)
            java.lang.StringBuilder r0 = r0.append(r1)
        L3c:
            r0 = r12
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.getType()
            r20 = r0
            r0 = r18
            r1 = r11
            r2 = r20
            java.lang.String r1 = r1.renderType(r2)
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r11
            boolean r0 = r0.getIncludeAnnotationArguments()
            if (r0 == 0) goto La4
            r0 = r11
            r1 = r12
            java.util.List r0 = r0.renderAndSortAnnotationArguments(r1)
            r21 = r0
            r0 = r11
            boolean r0 = r0.getIncludeEmptyAnnotationArguments()
            if (r0 != 0) goto L81
            r0 = r21
            java.util.Collection r0 = (java.util.Collection) r0
            r22 = r0
            r0 = 0
            r23 = r0
            r0 = r22
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L7d
            r0 = 1
            goto L7e
        L7d:
            r0 = 0
        L7e:
            if (r0 == 0) goto La4
        L81:
            r0 = r21
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            r1 = r18
            java.lang.Appendable r1 = (java.lang.Appendable) r1
            java.lang.String r2 = ", "
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            java.lang.String r3 = "("
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            java.lang.String r4 = ")"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 112(0x70, float:1.57E-43)
            r9 = 0
            java.lang.Appendable r0 = kotlin.collections.CollectionsKt.joinTo$default(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9)
        La4:
            r0 = r11
            boolean r0 = r0.getVerbose()
            if (r0 == 0) goto Lcb
            r0 = r20
            boolean r0 = kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt.isError(r0)
            if (r0 != 0) goto Lc3
            r0 = r20
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r0 = r0.getConstructor()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r0 = r0.mo3831getDeclarationDescriptor()
            boolean r0 = r0 instanceof kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses.MockClassDescriptor
            if (r0 == 0) goto Lcb
        Lc3:
            r0 = r18
        */
        //  java.lang.String r1 = " /* annotation class not found */"
        /*
            java.lang.StringBuilder r0 = r0.append(r1)
        Lcb:
            r0 = r15
            java.lang.String r0 = r0.toString()
            r1 = r0
            java.lang.String r2 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderAnnotation(kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUseSiteTarget):java.lang.String");
    }

    private final List<String> renderAndSortAnnotationArguments(AnnotationDescriptor descriptor) {
        ClassConstructorDescriptor classConstructorDescriptorMo3498getUnsubstitutedPrimaryConstructor;
        ArrayList arrayList;
        Map allValueArguments = descriptor.getAllValueArguments();
        ClassDescriptor classDescriptor = getRenderDefaultAnnotationArguments() ? DescriptorUtilsKt.getAnnotationClass(descriptor) : null;
        Iterable valueParameters = (classDescriptor == null || (classConstructorDescriptorMo3498getUnsubstitutedPrimaryConstructor = classDescriptor.mo3498getUnsubstitutedPrimaryConstructor()) == null) ? null : classConstructorDescriptorMo3498getUnsubstitutedPrimaryConstructor.getValueParameters();
        Iterable iterable = valueParameters;
        if (iterable == null) {
            arrayList = null;
        } else {
            Iterable $this$filter$iv = iterable;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                ValueParameterDescriptor it = (ValueParameterDescriptor) element$iv$iv;
                if (it.declaresDefaultValue()) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            Iterable $this$map$iv = (List) destination$iv$iv;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                ValueParameterDescriptor it2 = (ValueParameterDescriptor) item$iv$iv;
                destination$iv$iv2.add(it2.getName());
            }
            arrayList = (List) destination$iv$iv2;
        }
        List listEmptyList = arrayList;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List parameterDescriptorsWithDefaultValue = listEmptyList;
        List $this$filter$iv2 = parameterDescriptorsWithDefaultValue;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            Name it3 = (Name) element$iv$iv2;
            Intrinsics.checkNotNullExpressionValue(it3, "it");
            if (!allValueArguments.containsKey(it3)) {
                destination$iv$iv3.add(element$iv$iv2);
            }
        }
        Iterable $this$map$iv2 = (List) destination$iv$iv3;
        Collection destination$iv$iv4 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            Name it4 = (Name) item$iv$iv2;
            destination$iv$iv4.add(Intrinsics.stringPlus(it4.asString(), " = ..."));
        }
        List defaultList = (List) destination$iv$iv4;
        Iterable $this$map$iv3 = allValueArguments.entrySet();
        Collection destination$iv$iv5 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10));
        for (Object item$iv$iv3 : $this$map$iv3) {
            Map.Entry $dstr$name$value = (Map.Entry) item$iv$iv3;
            Name name = (Name) $dstr$name$value.getKey();
            ConstantValue value = (ConstantValue) $dstr$name$value.getValue();
            destination$iv$iv5.add(name.asString() + " = " + (!parameterDescriptorsWithDefaultValue.contains(name) ? renderConstant(value) : "..."));
        }
        List argumentList = (List) destination$iv$iv5;
        return CollectionsKt.sorted(CollectionsKt.plus((Collection) defaultList, (Iterable) argumentList));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String renderConstant(ConstantValue<?> constantValue) {
        if (constantValue instanceof ArrayValue) {
            return CollectionsKt.joinToString$default(((ArrayValue) constantValue).getValue(), ", ", StrPool.DELIM_START, "}", 0, null, new Function1<ConstantValue<?>, CharSequence>() { // from class: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderConstant.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                @NotNull
                public final CharSequence invoke(@NotNull ConstantValue<?> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return DescriptorRendererImpl.this.renderConstant(it);
                }
            }, 24, null);
        }
        if (constantValue instanceof AnnotationValue) {
            return StringsKt.removePrefix(DescriptorRenderer.renderAnnotation$default(this, ((AnnotationValue) constantValue).getValue(), null, 2, null), (CharSequence) StrPool.AT);
        }
        if (constantValue instanceof KClassValue) {
            KClassValue.Value classValue = ((KClassValue) constantValue).getValue();
            if (classValue instanceof KClassValue.Value.LocalClass) {
                return ((KClassValue.Value.LocalClass) classValue).getType() + "::class";
            }
            if (!(classValue instanceof KClassValue.Value.NormalClass)) {
                throw new NoWhenBranchMatchedException();
            }
            String strAsString = ((KClassValue.Value.NormalClass) classValue).getClassId().asSingleFqName().asString();
            Intrinsics.checkNotNullExpressionValue(strAsString, "classValue.classId.asSingleFqName().asString()");
            String str = strAsString;
            for (int i = 0; i < ((KClassValue.Value.NormalClass) classValue).getArrayDimensions(); i++) {
                str = "kotlin.Array<" + str + '>';
            }
            return Intrinsics.stringPlus(str, "::class");
        }
        return constantValue.toString();
    }

    private final boolean renderVisibility(DescriptorVisibility visibility, StringBuilder builder) {
        DescriptorVisibility visibility2 = visibility;
        if (!getModifiers().contains(DescriptorRendererModifier.VISIBILITY)) {
            return false;
        }
        if (getNormalizedVisibilities()) {
            visibility2 = visibility2.normalize();
        }
        if (!getRenderDefaultVisibility() && Intrinsics.areEqual(visibility2, DescriptorVisibilities.DEFAULT_VISIBILITY)) {
            return false;
        }
        builder.append(renderKeyword(visibility2.getInternalDisplayName())).append(" ");
        return true;
    }

    private final void renderModality(Modality modality, StringBuilder builder, Modality defaultModality) {
        if (getRenderDefaultModality() || modality != defaultModality) {
            renderModifier(builder, getModifiers().contains(DescriptorRendererModifier.MODALITY), CapitalizeDecapitalizeKt.toLowerCaseAsciiOnly(modality.name()));
        }
    }

    private final Modality implicitModalityWithoutExtensions(MemberDescriptor $this$implicitModalityWithoutExtensions) {
        if ($this$implicitModalityWithoutExtensions instanceof ClassDescriptor) {
            return ((ClassDescriptor) $this$implicitModalityWithoutExtensions).getKind() == ClassKind.INTERFACE ? Modality.ABSTRACT : Modality.FINAL;
        }
        DeclarationDescriptor containingDeclaration = $this$implicitModalityWithoutExtensions.getContainingDeclaration();
        ClassDescriptor containingClassDescriptor = containingDeclaration instanceof ClassDescriptor ? (ClassDescriptor) containingDeclaration : null;
        if (containingClassDescriptor != null && ($this$implicitModalityWithoutExtensions instanceof CallableMemberDescriptor)) {
            Collection<? extends CallableMemberDescriptor> overriddenDescriptors = ((CallableMemberDescriptor) $this$implicitModalityWithoutExtensions).getOverriddenDescriptors();
            Intrinsics.checkNotNullExpressionValue(overriddenDescriptors, "this.overriddenDescriptors");
            if ((!overriddenDescriptors.isEmpty()) && containingClassDescriptor.getModality() != Modality.FINAL) {
                return Modality.OPEN;
            }
            if (containingClassDescriptor.getKind() != ClassKind.INTERFACE || Intrinsics.areEqual(((CallableMemberDescriptor) $this$implicitModalityWithoutExtensions).getVisibility(), DescriptorVisibilities.PRIVATE)) {
                return Modality.FINAL;
            }
            return ((CallableMemberDescriptor) $this$implicitModalityWithoutExtensions).getModality() == Modality.ABSTRACT ? Modality.ABSTRACT : Modality.OPEN;
        }
        return Modality.FINAL;
    }

    private final void renderModalityForCallable(CallableMemberDescriptor callable, StringBuilder builder) {
        if (!DescriptorUtils.isTopLevelDeclaration(callable) || callable.getModality() != Modality.FINAL) {
            if (getOverrideRenderingPolicy() == OverrideRenderingPolicy.RENDER_OVERRIDE && callable.getModality() == Modality.OPEN && overridesSomething(callable)) {
                return;
            }
            Modality modality = callable.getModality();
            Intrinsics.checkNotNullExpressionValue(modality, "callable.modality");
            renderModality(modality, builder, implicitModalityWithoutExtensions(callable));
        }
    }

    private final void renderOverride(CallableMemberDescriptor callableMember, StringBuilder builder) {
        if (getModifiers().contains(DescriptorRendererModifier.OVERRIDE) && overridesSomething(callableMember) && getOverrideRenderingPolicy() != OverrideRenderingPolicy.RENDER_OPEN) {
            renderModifier(builder, true, "override");
            if (getVerbose()) {
                builder.append("/*").append(callableMember.getOverriddenDescriptors().size()).append("*/ ");
            }
        }
    }

    private final void renderMemberKind(CallableMemberDescriptor callableMember, StringBuilder builder) {
        if (getModifiers().contains(DescriptorRendererModifier.MEMBER_KIND) && getVerbose() && callableMember.getKind() != CallableMemberDescriptor.Kind.DECLARATION) {
            builder.append("/*").append(CapitalizeDecapitalizeKt.toLowerCaseAsciiOnly(callableMember.getKind().name())).append("*/ ");
        }
    }

    private final void renderModifier(StringBuilder builder, boolean value, String modifier) {
        if (value) {
            builder.append(renderKeyword(modifier));
            builder.append(" ");
        }
    }

    private final void renderMemberModifiers(MemberDescriptor descriptor, StringBuilder builder) {
        renderModifier(builder, descriptor.isExternal(), "external");
        renderModifier(builder, getModifiers().contains(DescriptorRendererModifier.EXPECT) && descriptor.isExpect(), "expect");
        renderModifier(builder, getModifiers().contains(DescriptorRendererModifier.ACTUAL) && descriptor.isActual(), "actual");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ee  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void renderAdditionalModifiers(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r6, java.lang.StringBuilder r7) {
        /*
            Method dump skipped, instructions count: 291
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderAdditionalModifiers(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor, java.lang.StringBuilder):void");
    }

    private final void renderSuspendModifier(FunctionDescriptor functionDescriptor, StringBuilder builder) {
        renderModifier(builder, functionDescriptor.isSuspend(), "suspend");
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    @NotNull
    public String render(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkNotNullParameter(declarationDescriptor, "declarationDescriptor");
        StringBuilder $this$render_u24lambda_u2d16 = new StringBuilder();
        declarationDescriptor.accept(new RenderDeclarationDescriptorVisitor(this), $this$render_u24lambda_u2d16);
        if (getWithDefinedIn()) {
            appendDefinedIn($this$render_u24lambda_u2d16, declarationDescriptor);
        }
        String string = $this$render_u24lambda_u2d16.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderTypeParameter(TypeParameterDescriptor typeParameter, StringBuilder builder, boolean topLevel) {
        if (topLevel) {
            builder.append(lt());
        }
        if (getVerbose()) {
            builder.append("/*").append(typeParameter.getIndex()).append("*/ ");
        }
        renderModifier(builder, typeParameter.isReified(), "reified");
        String variance = typeParameter.getVariance().getLabel();
        renderModifier(builder, variance.length() > 0, variance);
        renderAnnotations$default(this, builder, typeParameter, null, 2, null);
        renderName(typeParameter, builder, topLevel);
        int upperBoundsCount = typeParameter.getUpperBounds().size();
        if ((upperBoundsCount > 1 && !topLevel) || upperBoundsCount == 1) {
            KotlinType upperBound = typeParameter.getUpperBounds().iterator().next();
            if (!KotlinBuiltIns.isDefaultBound(upperBound)) {
                StringBuilder sbAppend = builder.append(" : ");
                Intrinsics.checkNotNullExpressionValue(upperBound, "upperBound");
                sbAppend.append(renderType(upperBound));
            }
        } else if (topLevel) {
            boolean first = true;
            for (KotlinType upperBound2 : typeParameter.getUpperBounds()) {
                if (!KotlinBuiltIns.isDefaultBound(upperBound2)) {
                    if (first) {
                        builder.append(" : ");
                    } else {
                        builder.append(" & ");
                    }
                    Intrinsics.checkNotNullExpressionValue(upperBound2, "upperBound");
                    builder.append(renderType(upperBound2));
                    first = false;
                }
            }
        }
        if (topLevel) {
            builder.append(gt());
        }
    }

    private final void renderTypeParameters(List<? extends TypeParameterDescriptor> list, StringBuilder builder, boolean withSpace) {
        if (getWithoutTypeParameters()) {
            return;
        }
        if (!list.isEmpty()) {
            builder.append(lt());
            renderTypeParameterList(builder, list);
            builder.append(gt());
            if (withSpace) {
                builder.append(" ");
            }
        }
    }

    private final void renderTypeParameterList(StringBuilder builder, List<? extends TypeParameterDescriptor> list) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            TypeParameterDescriptor typeParameterDescriptor = iterator.next();
            renderTypeParameter(typeParameterDescriptor, builder, false);
            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderFunction(FunctionDescriptor function, StringBuilder builder) {
        if (!getStartFromName()) {
            if (!getStartFromDeclarationKeyword()) {
                renderAnnotations$default(this, builder, function, null, 2, null);
                DescriptorVisibility visibility = function.getVisibility();
                Intrinsics.checkNotNullExpressionValue(visibility, "function.visibility");
                renderVisibility(visibility, builder);
                renderModalityForCallable(function, builder);
                if (getIncludeAdditionalModifiers()) {
                    renderMemberModifiers(function, builder);
                }
                renderOverride(function, builder);
                if (getIncludeAdditionalModifiers()) {
                    renderAdditionalModifiers(function, builder);
                } else {
                    renderSuspendModifier(function, builder);
                }
                renderMemberKind(function, builder);
                if (getVerbose()) {
                    if (function.isHiddenToOvercomeSignatureClash()) {
                        builder.append("/*isHiddenToOvercomeSignatureClash*/ ");
                    }
                    if (function.isHiddenForResolutionEverywhereBesideSupercalls()) {
                        builder.append("/*isHiddenForResolutionEverywhereBesideSupercalls*/ ");
                    }
                }
            }
            builder.append(renderKeyword("fun")).append(" ");
            List<TypeParameterDescriptor> typeParameters = function.getTypeParameters();
            Intrinsics.checkNotNullExpressionValue(typeParameters, "function.typeParameters");
            renderTypeParameters(typeParameters, builder, true);
            renderReceiver(function, builder);
        }
        renderName(function, builder, true);
        List<ValueParameterDescriptor> valueParameters = function.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "function.valueParameters");
        renderValueParameters(valueParameters, function.hasSynthesizedParameterNames(), builder);
        renderReceiverAfterName(function, builder);
        KotlinType returnType = function.getReturnType();
        if (!getWithoutReturnType() && (getUnitReturnType() || returnType == null || !KotlinBuiltIns.isUnit(returnType))) {
            builder.append(": ").append(returnType == null ? "[NULL]" : renderType(returnType));
        }
        List<TypeParameterDescriptor> typeParameters2 = function.getTypeParameters();
        Intrinsics.checkNotNullExpressionValue(typeParameters2, "function.typeParameters");
        renderWhereSuffix(typeParameters2, builder);
    }

    private final void renderReceiverAfterName(CallableDescriptor callableDescriptor, StringBuilder builder) {
        ReceiverParameterDescriptor receiver;
        if (getReceiverAfterName() && (receiver = callableDescriptor.getExtensionReceiverParameter()) != null) {
            StringBuilder sbAppend = builder.append(" on ");
            KotlinType type = receiver.getType();
            Intrinsics.checkNotNullExpressionValue(type, "receiver.type");
            sbAppend.append(renderType(type));
        }
    }

    private final void renderReceiver(CallableDescriptor callableDescriptor, StringBuilder builder) {
        ReceiverParameterDescriptor receiver = callableDescriptor.getExtensionReceiverParameter();
        if (receiver != null) {
            renderAnnotations(builder, receiver, AnnotationUseSiteTarget.RECEIVER);
            KotlinType type = receiver.getType();
            Intrinsics.checkNotNullExpressionValue(type, "receiver.type");
            String result = renderType(type);
            if (shouldRenderAsPrettyFunctionType(type) && !TypeUtils.isNullableType(type)) {
                result = '(' + result + ')';
            }
            builder.append(result).append(".");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void renderConstructor(kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor r12, java.lang.StringBuilder r13) {
        /*
            Method dump skipped, instructions count: 511
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderConstructor(kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor, java.lang.StringBuilder):void");
    }

    private final void renderWhereSuffix(List<? extends TypeParameterDescriptor> list, StringBuilder builder) {
        if (getWithoutTypeParameters()) {
            return;
        }
        ArrayList upperBoundStrings = new ArrayList(0);
        for (TypeParameterDescriptor typeParameter : list) {
            List<KotlinType> upperBounds = typeParameter.getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(upperBounds, "typeParameter.upperBounds");
            Iterable $this$mapTo$iv = CollectionsKt.drop(upperBounds, 1);
            for (Object item$iv : $this$mapTo$iv) {
                ArrayList arrayList = upperBoundStrings;
                KotlinType it = (KotlinType) item$iv;
                StringBuilder sb = new StringBuilder();
                Name name = typeParameter.getName();
                Intrinsics.checkNotNullExpressionValue(name, "typeParameter.name");
                StringBuilder sbAppend = sb.append(renderName(name, false)).append(" : ");
                Intrinsics.checkNotNullExpressionValue(it, "it");
                arrayList.add(sbAppend.append(renderType(it)).toString());
            }
        }
        if (!upperBoundStrings.isEmpty()) {
            builder.append(" ").append(renderKeyword("where")).append(" ");
            CollectionsKt.joinTo$default(upperBoundStrings, builder, ", ", null, null, 0, null, null, 124, null);
        }
    }

    private final void renderValueParameters(Collection<? extends ValueParameterDescriptor> collection, boolean synthesizedParameterNames, StringBuilder builder) {
        boolean includeNames = shouldRenderParameterNames(synthesizedParameterNames);
        int parameterCount = collection.size();
        getValueParametersHandler().appendBeforeValueParameters(parameterCount, builder);
        int i = 0;
        for (ValueParameterDescriptor parameter : collection) {
            int index = i;
            i++;
            getValueParametersHandler().appendBeforeValueParameter(parameter, index, parameterCount, builder);
            renderValueParameter(parameter, includeNames, builder, false);
            getValueParametersHandler().appendAfterValueParameter(parameter, index, parameterCount, builder);
        }
        getValueParametersHandler().appendAfterValueParameters(parameterCount, builder);
    }

    private final boolean shouldRenderParameterNames(boolean synthesizedParameterNames) {
        switch (WhenMappings.$EnumSwitchMapping$1[getParameterNameRenderingPolicy().ordinal()]) {
            case 1:
                return true;
            case 2:
                return !synthesizedParameterNames;
            case 3:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void renderValueParameter(kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor r8, boolean r9, java.lang.StringBuilder r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderValueParameter(kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor, boolean, java.lang.StringBuilder, boolean):void");
    }

    static /* synthetic */ void renderValVarPrefix$default(DescriptorRendererImpl descriptorRendererImpl, VariableDescriptor variableDescriptor, StringBuilder sb, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        descriptorRendererImpl.renderValVarPrefix(variableDescriptor, sb, z);
    }

    private final void renderValVarPrefix(VariableDescriptor variable, StringBuilder builder, boolean isInPrimaryConstructor) {
        if (isInPrimaryConstructor || !(variable instanceof ValueParameterDescriptor)) {
            builder.append(renderKeyword(variable.isVar() ? "var" : "val")).append(" ");
        }
    }

    private final void renderVariable(VariableDescriptor variable, boolean includeName, StringBuilder builder, boolean topLevel, boolean isInPrimaryConstructor) {
        KotlinType realType = variable.getType();
        Intrinsics.checkNotNullExpressionValue(realType, "variable.type");
        ValueParameterDescriptor valueParameterDescriptor = variable instanceof ValueParameterDescriptor ? (ValueParameterDescriptor) variable : null;
        KotlinType varargElementType = valueParameterDescriptor == null ? null : valueParameterDescriptor.getVarargElementType();
        KotlinType typeToRender = varargElementType == null ? realType : varargElementType;
        renderModifier(builder, varargElementType != null, "vararg");
        if (isInPrimaryConstructor || (topLevel && !getStartFromName())) {
            renderValVarPrefix(variable, builder, isInPrimaryConstructor);
        }
        if (includeName) {
            renderName(variable, builder, topLevel);
            builder.append(": ");
        }
        builder.append(renderType(typeToRender));
        renderInitializer(variable, builder);
        if (getVerbose() && varargElementType != null) {
            builder.append(" /*").append(renderType(realType)).append("*/");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderProperty(PropertyDescriptor property, StringBuilder builder) {
        if (!getStartFromName()) {
            if (!getStartFromDeclarationKeyword()) {
                renderPropertyAnnotations(property, builder);
                DescriptorVisibility visibility = property.getVisibility();
                Intrinsics.checkNotNullExpressionValue(visibility, "property.visibility");
                renderVisibility(visibility, builder);
                renderModifier(builder, getModifiers().contains(DescriptorRendererModifier.CONST) && property.isConst(), "const");
                renderMemberModifiers(property, builder);
                renderModalityForCallable(property, builder);
                renderOverride(property, builder);
                renderModifier(builder, getModifiers().contains(DescriptorRendererModifier.LATEINIT) && property.isLateInit(), "lateinit");
                renderMemberKind(property, builder);
            }
            renderValVarPrefix$default(this, property, builder, false, 4, null);
            List<TypeParameterDescriptor> typeParameters = property.getTypeParameters();
            Intrinsics.checkNotNullExpressionValue(typeParameters, "property.typeParameters");
            renderTypeParameters(typeParameters, builder, true);
            renderReceiver(property, builder);
        }
        renderName(property, builder, true);
        StringBuilder sbAppend = builder.append(": ");
        KotlinType type = property.getType();
        Intrinsics.checkNotNullExpressionValue(type, "property.type");
        sbAppend.append(renderType(type));
        renderReceiverAfterName(property, builder);
        renderInitializer(property, builder);
        List<TypeParameterDescriptor> typeParameters2 = property.getTypeParameters();
        Intrinsics.checkNotNullExpressionValue(typeParameters2, "property.typeParameters");
        renderWhereSuffix(typeParameters2, builder);
    }

    private final void renderPropertyAnnotations(PropertyDescriptor property, StringBuilder builder) {
        if (getModifiers().contains(DescriptorRendererModifier.ANNOTATIONS)) {
            renderAnnotations$default(this, builder, property, null, 2, null);
            FieldDescriptor it = property.getBackingField();
            if (it != null) {
                renderAnnotations(builder, it, AnnotationUseSiteTarget.FIELD);
            }
            FieldDescriptor it2 = property.getDelegateField();
            if (it2 != null) {
                renderAnnotations(builder, it2, AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD);
            }
            if (getPropertyAccessorRenderingPolicy() == PropertyAccessorRenderingPolicy.NONE) {
                PropertyGetterDescriptor it3 = property.getGetter();
                if (it3 != null) {
                    renderAnnotations(builder, it3, AnnotationUseSiteTarget.PROPERTY_GETTER);
                }
                PropertySetterDescriptor setter = property.getSetter();
                if (setter != null) {
                    renderAnnotations(builder, setter, AnnotationUseSiteTarget.PROPERTY_SETTER);
                    List<ValueParameterDescriptor> valueParameters = setter.getValueParameters();
                    Intrinsics.checkNotNullExpressionValue(valueParameters, "setter.valueParameters");
                    ValueParameterDescriptor it4 = (ValueParameterDescriptor) CollectionsKt.single((List) valueParameters);
                    Intrinsics.checkNotNullExpressionValue(it4, "it");
                    renderAnnotations(builder, it4, AnnotationUseSiteTarget.SETTER_PARAMETER);
                }
            }
        }
    }

    private final void renderInitializer(VariableDescriptor variable, StringBuilder builder) {
        ConstantValue constant;
        if (getIncludePropertyConstant() && (constant = variable.mo3574getCompileTimeInitializer()) != null) {
            builder.append(" = ").append(escape(renderConstant(constant)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderTypeAlias(TypeAliasDescriptor typeAlias, StringBuilder builder) {
        renderAnnotations$default(this, builder, typeAlias, null, 2, null);
        DescriptorVisibility visibility = typeAlias.getVisibility();
        Intrinsics.checkNotNullExpressionValue(visibility, "typeAlias.visibility");
        renderVisibility(visibility, builder);
        renderMemberModifiers(typeAlias, builder);
        builder.append(renderKeyword("typealias")).append(" ");
        renderName(typeAlias, builder, true);
        List<TypeParameterDescriptor> declaredTypeParameters = typeAlias.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(declaredTypeParameters, "typeAlias.declaredTypeParameters");
        renderTypeParameters(declaredTypeParameters, builder, false);
        renderCapturedTypeParametersIfRequired(typeAlias, builder);
        builder.append(" = ").append(renderType(typeAlias.getUnderlyingType()));
    }

    private final void renderCapturedTypeParametersIfRequired(ClassifierDescriptorWithTypeParameters classifier, StringBuilder builder) {
        List typeParameters = classifier.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(typeParameters, "classifier.declaredTypeParameters");
        List typeConstructorParameters = classifier.getTypeConstructor().getParameters();
        Intrinsics.checkNotNullExpressionValue(typeConstructorParameters, "classifier.typeConstructor.parameters");
        if (getVerbose() && classifier.isInner() && typeConstructorParameters.size() > typeParameters.size()) {
            builder.append(" /*captured type parameters: ");
            renderTypeParameterList(builder, typeConstructorParameters.subList(typeParameters.size(), typeConstructorParameters.size()));
            builder.append("*/");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderClass(ClassDescriptor klass, StringBuilder builder) {
        ClassConstructorDescriptor primaryConstructor;
        boolean isEnumEntry = klass.getKind() == ClassKind.ENUM_ENTRY;
        if (!getStartFromName()) {
            renderAnnotations$default(this, builder, klass, null, 2, null);
            if (!isEnumEntry) {
                DescriptorVisibility visibility = klass.getVisibility();
                Intrinsics.checkNotNullExpressionValue(visibility, "klass.visibility");
                renderVisibility(visibility, builder);
            }
            if ((klass.getKind() != ClassKind.INTERFACE || klass.getModality() != Modality.ABSTRACT) && (!klass.getKind().isSingleton() || klass.getModality() != Modality.FINAL)) {
                Modality modality = klass.getModality();
                Intrinsics.checkNotNullExpressionValue(modality, "klass.modality");
                renderModality(modality, builder, implicitModalityWithoutExtensions(klass));
            }
            renderMemberModifiers(klass, builder);
            renderModifier(builder, getModifiers().contains(DescriptorRendererModifier.INNER) && klass.isInner(), "inner");
            renderModifier(builder, getModifiers().contains(DescriptorRendererModifier.DATA) && klass.isData(), "data");
            renderModifier(builder, getModifiers().contains(DescriptorRendererModifier.INLINE) && klass.isInline(), "inline");
            renderModifier(builder, getModifiers().contains(DescriptorRendererModifier.VALUE) && klass.isValue(), "value");
            renderModifier(builder, getModifiers().contains(DescriptorRendererModifier.FUN) && klass.isFun(), "fun");
            renderClassKindPrefix(klass, builder);
        }
        if (!DescriptorUtils.isCompanionObject(klass)) {
            if (!getStartFromName()) {
                renderSpaceIfNeeded(builder);
            }
            renderName(klass, builder, true);
        } else {
            renderCompanionObjectName(klass, builder);
        }
        if (isEnumEntry) {
            return;
        }
        List typeParameters = klass.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(typeParameters, "klass.declaredTypeParameters");
        renderTypeParameters(typeParameters, builder, false);
        renderCapturedTypeParametersIfRequired(klass, builder);
        if (!klass.getKind().isSingleton() && getClassWithPrimaryConstructor() && (primaryConstructor = klass.mo3498getUnsubstitutedPrimaryConstructor()) != null) {
            builder.append(" ");
            renderAnnotations$default(this, builder, primaryConstructor, null, 2, null);
            DescriptorVisibility visibility2 = primaryConstructor.getVisibility();
            Intrinsics.checkNotNullExpressionValue(visibility2, "primaryConstructor.visibility");
            renderVisibility(visibility2, builder);
            builder.append(renderKeyword(BeanDefinitionParserDelegate.AUTOWIRE_CONSTRUCTOR_VALUE));
            List<ValueParameterDescriptor> valueParameters = primaryConstructor.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(valueParameters, "primaryConstructor.valueParameters");
            renderValueParameters(valueParameters, primaryConstructor.hasSynthesizedParameterNames(), builder);
        }
        renderSuperTypes(klass, builder);
        renderWhereSuffix(typeParameters, builder);
    }

    private final void renderSuperTypes(ClassDescriptor klass, StringBuilder builder) {
        if (getWithoutSuperTypes() || KotlinBuiltIns.isNothing(klass.getDefaultType())) {
            return;
        }
        Collection supertypes = klass.getTypeConstructor().mo3835getSupertypes();
        Intrinsics.checkNotNullExpressionValue(supertypes, "klass.typeConstructor.supertypes");
        if (supertypes.isEmpty()) {
            return;
        }
        if (supertypes.size() == 1 && KotlinBuiltIns.isAnyOrNullableAny(supertypes.iterator().next())) {
            return;
        }
        renderSpaceIfNeeded(builder);
        builder.append(": ");
        CollectionsKt.joinTo$default(supertypes, builder, ", ", null, null, 0, null, new Function1<KotlinType, CharSequence>() { // from class: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderSuperTypes.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final CharSequence invoke(KotlinType it) {
                DescriptorRendererImpl descriptorRendererImpl = DescriptorRendererImpl.this;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                return descriptorRendererImpl.renderType(it);
            }
        }, 60, null);
    }

    private final void renderClassKindPrefix(ClassDescriptor klass, StringBuilder builder) {
        builder.append(renderKeyword(DescriptorRenderer.Companion.getClassifierKindPrefix(klass)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderPackageView(PackageViewDescriptor packageView, StringBuilder builder) {
        renderPackageHeader(packageView.getFqName(), PackageDocumentBase.OPFTags.packageTag, builder);
        if (getDebugMode()) {
            builder.append(" in context of ");
            renderName(packageView.getModule(), builder, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderPackageFragment(PackageFragmentDescriptor fragment, StringBuilder builder) {
        renderPackageHeader(fragment.getFqName(), "package-fragment", builder);
        if (getDebugMode()) {
            builder.append(" in ");
            renderName(fragment.getContainingDeclaration(), builder, false);
        }
    }

    private final void renderPackageHeader(FqName fqName, String fragmentOrView, StringBuilder builder) {
        builder.append(renderKeyword(fragmentOrView));
        FqNameUnsafe unsafe = fqName.toUnsafe();
        Intrinsics.checkNotNullExpressionValue(unsafe, "fqName.toUnsafe()");
        String fqNameString = renderFqName(unsafe);
        if (fqNameString.length() > 0) {
            builder.append(" ");
            builder.append(fqNameString);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderAccessorModifiers(PropertyAccessorDescriptor descriptor, StringBuilder builder) {
        renderMemberModifiers(descriptor, builder);
    }

    /* compiled from: DescriptorRendererImpl.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/renderer/DescriptorRendererImpl$RenderDeclarationDescriptorVisitor.class */
    private final class RenderDeclarationDescriptorVisitor implements DeclarationDescriptorVisitor<Unit, StringBuilder> {
        final /* synthetic */ DescriptorRendererImpl this$0;

        /* compiled from: DescriptorRendererImpl.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/renderer/DescriptorRendererImpl$RenderDeclarationDescriptorVisitor$WhenMappings.class */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[PropertyAccessorRenderingPolicy.values().length];
                iArr[PropertyAccessorRenderingPolicy.PRETTY.ordinal()] = 1;
                iArr[PropertyAccessorRenderingPolicy.DEBUG.ordinal()] = 2;
                iArr[PropertyAccessorRenderingPolicy.NONE.ordinal()] = 3;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public RenderDeclarationDescriptorVisitor(DescriptorRendererImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitValueParameterDescriptor(ValueParameterDescriptor descriptor, StringBuilder sb) {
            visitValueParameterDescriptor2(descriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitPropertyDescriptor(PropertyDescriptor descriptor, StringBuilder sb) {
            visitPropertyDescriptor2(descriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitPropertyGetterDescriptor(PropertyGetterDescriptor descriptor, StringBuilder sb) {
            visitPropertyGetterDescriptor2(descriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitPropertySetterDescriptor(PropertySetterDescriptor descriptor, StringBuilder sb) {
            visitPropertySetterDescriptor2(descriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitFunctionDescriptor(FunctionDescriptor descriptor, StringBuilder sb) {
            visitFunctionDescriptor2(descriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitReceiverParameterDescriptor(ReceiverParameterDescriptor descriptor, StringBuilder sb) {
            visitReceiverParameterDescriptor2(descriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitConstructorDescriptor(ConstructorDescriptor constructorDescriptor, StringBuilder sb) {
            visitConstructorDescriptor2(constructorDescriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitTypeParameterDescriptor(TypeParameterDescriptor descriptor, StringBuilder sb) {
            visitTypeParameterDescriptor2(descriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitPackageFragmentDescriptor(PackageFragmentDescriptor descriptor, StringBuilder sb) {
            visitPackageFragmentDescriptor2(descriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitPackageViewDescriptor(PackageViewDescriptor descriptor, StringBuilder sb) {
            visitPackageViewDescriptor2(descriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitModuleDeclaration(ModuleDescriptor descriptor, StringBuilder sb) {
            visitModuleDeclaration2(descriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitClassDescriptor(ClassDescriptor descriptor, StringBuilder sb) {
            visitClassDescriptor2(descriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitTypeAliasDescriptor(TypeAliasDescriptor descriptor, StringBuilder sb) {
            visitTypeAliasDescriptor2(descriptor, sb);
            return Unit.INSTANCE;
        }

        /* renamed from: visitValueParameterDescriptor, reason: avoid collision after fix types in other method */
        public void visitValueParameterDescriptor2(@NotNull ValueParameterDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            Intrinsics.checkNotNullParameter(builder, "builder");
            this.this$0.renderValueParameter(descriptor, true, builder, true);
        }

        /* renamed from: visitPropertyDescriptor, reason: avoid collision after fix types in other method */
        public void visitPropertyDescriptor2(@NotNull PropertyDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            Intrinsics.checkNotNullParameter(builder, "builder");
            this.this$0.renderProperty(descriptor, builder);
        }

        /* renamed from: visitPropertyGetterDescriptor, reason: avoid collision after fix types in other method */
        public void visitPropertyGetterDescriptor2(@NotNull PropertyGetterDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            Intrinsics.checkNotNullParameter(builder, "builder");
            visitPropertyAccessorDescriptor(descriptor, builder, "getter");
        }

        /* renamed from: visitPropertySetterDescriptor, reason: avoid collision after fix types in other method */
        public void visitPropertySetterDescriptor2(@NotNull PropertySetterDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            Intrinsics.checkNotNullParameter(builder, "builder");
            visitPropertyAccessorDescriptor(descriptor, builder, "setter");
        }

        private final void visitPropertyAccessorDescriptor(PropertyAccessorDescriptor descriptor, StringBuilder builder, String kind) {
            switch (WhenMappings.$EnumSwitchMapping$0[this.this$0.getPropertyAccessorRenderingPolicy().ordinal()]) {
                case 1:
                    this.this$0.renderAccessorModifiers(descriptor, builder);
                    builder.append(Intrinsics.stringPlus(kind, " for "));
                    DescriptorRendererImpl descriptorRendererImpl = this.this$0;
                    PropertyDescriptor correspondingProperty = descriptor.getCorrespondingProperty();
                    Intrinsics.checkNotNullExpressionValue(correspondingProperty, "descriptor.correspondingProperty");
                    descriptorRendererImpl.renderProperty(correspondingProperty, builder);
                    break;
                case 2:
                    visitFunctionDescriptor2((FunctionDescriptor) descriptor, builder);
                    break;
            }
        }

        /* renamed from: visitFunctionDescriptor, reason: avoid collision after fix types in other method */
        public void visitFunctionDescriptor2(@NotNull FunctionDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            Intrinsics.checkNotNullParameter(builder, "builder");
            this.this$0.renderFunction(descriptor, builder);
        }

        /* renamed from: visitReceiverParameterDescriptor, reason: avoid collision after fix types in other method */
        public void visitReceiverParameterDescriptor2(@NotNull ReceiverParameterDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            Intrinsics.checkNotNullParameter(builder, "builder");
            builder.append(descriptor.getName());
        }

        /* renamed from: visitConstructorDescriptor, reason: avoid collision after fix types in other method */
        public void visitConstructorDescriptor2(@NotNull ConstructorDescriptor constructorDescriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkNotNullParameter(constructorDescriptor, "constructorDescriptor");
            Intrinsics.checkNotNullParameter(builder, "builder");
            this.this$0.renderConstructor(constructorDescriptor, builder);
        }

        /* renamed from: visitTypeParameterDescriptor, reason: avoid collision after fix types in other method */
        public void visitTypeParameterDescriptor2(@NotNull TypeParameterDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            Intrinsics.checkNotNullParameter(builder, "builder");
            this.this$0.renderTypeParameter(descriptor, builder, true);
        }

        /* renamed from: visitPackageFragmentDescriptor, reason: avoid collision after fix types in other method */
        public void visitPackageFragmentDescriptor2(@NotNull PackageFragmentDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            Intrinsics.checkNotNullParameter(builder, "builder");
            this.this$0.renderPackageFragment(descriptor, builder);
        }

        /* renamed from: visitPackageViewDescriptor, reason: avoid collision after fix types in other method */
        public void visitPackageViewDescriptor2(@NotNull PackageViewDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            Intrinsics.checkNotNullParameter(builder, "builder");
            this.this$0.renderPackageView(descriptor, builder);
        }

        /* renamed from: visitModuleDeclaration, reason: avoid collision after fix types in other method */
        public void visitModuleDeclaration2(@NotNull ModuleDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            Intrinsics.checkNotNullParameter(builder, "builder");
            this.this$0.renderName(descriptor, builder, true);
        }

        /* renamed from: visitClassDescriptor, reason: avoid collision after fix types in other method */
        public void visitClassDescriptor2(@NotNull ClassDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            Intrinsics.checkNotNullParameter(builder, "builder");
            this.this$0.renderClass(descriptor, builder);
        }

        /* renamed from: visitTypeAliasDescriptor, reason: avoid collision after fix types in other method */
        public void visitTypeAliasDescriptor2(@NotNull TypeAliasDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            Intrinsics.checkNotNullParameter(builder, "builder");
            this.this$0.renderTypeAlias(descriptor, builder);
        }
    }

    private final void renderSpaceIfNeeded(StringBuilder builder) {
        int length = builder.length();
        if (length == 0 || builder.charAt(length - 1) != ' ') {
            builder.append(' ');
        }
    }

    private final String replacePrefixes(String lowerRendered, String lowerPrefix, String upperRendered, String upperPrefix, String foldedPrefix) {
        if (StringsKt.startsWith$default(lowerRendered, lowerPrefix, false, 2, (Object) null) && StringsKt.startsWith$default(upperRendered, upperPrefix, false, 2, (Object) null)) {
            int length = lowerPrefix.length();
            if (lowerRendered == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String lowerWithoutPrefix = lowerRendered.substring(length);
            Intrinsics.checkNotNullExpressionValue(lowerWithoutPrefix, "(this as java.lang.String).substring(startIndex)");
            int length2 = upperPrefix.length();
            if (upperRendered == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String upperWithoutPrefix = upperRendered.substring(length2);
            Intrinsics.checkNotNullExpressionValue(upperWithoutPrefix, "(this as java.lang.String).substring(startIndex)");
            String flexibleCollectionName = Intrinsics.stringPlus(foldedPrefix, lowerWithoutPrefix);
            if (Intrinsics.areEqual(lowerWithoutPrefix, upperWithoutPrefix)) {
                return flexibleCollectionName;
            }
            if (differsOnlyInNullability(lowerWithoutPrefix, upperWithoutPrefix)) {
                return Intrinsics.stringPlus(flexibleCollectionName, "!");
            }
            return null;
        }
        return null;
    }

    private final boolean differsOnlyInNullability(String lower, String upper) {
        return Intrinsics.areEqual(lower, StringsKt.replace$default(upper, CallerData.NA, "", false, 4, (Object) null)) || (StringsKt.endsWith$default(upper, CallerData.NA, false, 2, (Object) null) && Intrinsics.areEqual(Intrinsics.stringPlus(lower, CallerData.NA), upper)) || Intrinsics.areEqual(new StringBuilder().append('(').append(lower).append(")?").toString(), upper);
    }

    private final boolean overridesSomething(CallableMemberDescriptor callable) {
        return !callable.getOverriddenDescriptors().isEmpty();
    }
}
