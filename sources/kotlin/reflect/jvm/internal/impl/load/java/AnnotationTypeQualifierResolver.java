package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.KotlinTarget;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationTargetMapper;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.JavaTypeEnhancementState;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnnotationTypeQualifierResolver.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/AnnotationTypeQualifierResolver.class */
public final class AnnotationTypeQualifierResolver {

    @NotNull
    private final JavaTypeEnhancementState javaTypeEnhancementState;

    @NotNull
    private final MemoizedFunctionToNullable<ClassDescriptor, AnnotationDescriptor> resolvedNicknames;

    /* compiled from: AnnotationTypeQualifierResolver.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/AnnotationTypeQualifierResolver$TypeQualifierWithApplicability.class */
    public static final class TypeQualifierWithApplicability {

        @NotNull
        private final AnnotationDescriptor typeQualifier;
        private final int applicability;

        public TypeQualifierWithApplicability(@NotNull AnnotationDescriptor typeQualifier, int applicability) {
            Intrinsics.checkNotNullParameter(typeQualifier, "typeQualifier");
            this.typeQualifier = typeQualifier;
            this.applicability = applicability;
        }

        @NotNull
        public final AnnotationDescriptor component1() {
            return this.typeQualifier;
        }

        @NotNull
        public final List<AnnotationQualifierApplicabilityType> component2() {
            AnnotationQualifierApplicabilityType[] annotationQualifierApplicabilityTypeArrValues = AnnotationQualifierApplicabilityType.values();
            Collection destination$iv$iv = new ArrayList();
            for (AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType : annotationQualifierApplicabilityTypeArrValues) {
                if (isApplicableTo(annotationQualifierApplicabilityType)) {
                    destination$iv$iv.add(annotationQualifierApplicabilityType);
                }
            }
            return (List) destination$iv$iv;
        }

        private final boolean isApplicableTo(AnnotationQualifierApplicabilityType elementType) {
            if (isApplicableConsideringMask(elementType)) {
                return true;
            }
            return isApplicableConsideringMask(AnnotationQualifierApplicabilityType.TYPE_USE) && elementType != AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS;
        }

        private final boolean isApplicableConsideringMask(AnnotationQualifierApplicabilityType elementType) {
            return (this.applicability & (1 << elementType.ordinal())) != 0;
        }
    }

    public AnnotationTypeQualifierResolver(@NotNull StorageManager storageManager, @NotNull JavaTypeEnhancementState javaTypeEnhancementState) {
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(javaTypeEnhancementState, "javaTypeEnhancementState");
        this.javaTypeEnhancementState = javaTypeEnhancementState;
        this.resolvedNicknames = storageManager.createMemoizedFunctionWithNullableValues(new AnnotationTypeQualifierResolver$resolvedNicknames$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AnnotationDescriptor computeTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (!classDescriptor.getAnnotations().hasAnnotation(AnnotationQualifiersFqNamesKt.getTYPE_QUALIFIER_NICKNAME_FQNAME())) {
            return null;
        }
        Iterable $this$firstNotNullResult$iv = classDescriptor.getAnnotations();
        for (Object element$iv : $this$firstNotNullResult$iv) {
            AnnotationDescriptor p0 = (AnnotationDescriptor) element$iv;
            AnnotationDescriptor annotationDescriptorResolveTypeQualifierAnnotation = resolveTypeQualifierAnnotation(p0);
            if (annotationDescriptorResolveTypeQualifierAnnotation != null) {
                return annotationDescriptorResolveTypeQualifierAnnotation;
            }
        }
        return null;
    }

    private final AnnotationDescriptor resolveTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
            return null;
        }
        return this.resolvedNicknames.invoke(classDescriptor);
    }

    @Nullable
    public final AnnotationDescriptor resolveTypeQualifierAnnotation(@NotNull AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass;
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        if (this.javaTypeEnhancementState.getDisabledJsr305() || (annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor)) == null) {
            return null;
        }
        return AnnotationTypeQualifierResolverKt.isAnnotatedWithTypeQualifier(annotationClass) ? annotationDescriptor : resolveTypeQualifierNickname(annotationClass);
    }

    @Nullable
    public final JavaDefaultQualifiers resolveQualifierBuiltInDefaultAnnotation(@NotNull AnnotationDescriptor annotationDescriptor) {
        JavaDefaultQualifiers qualifierForDefaultingAnnotation;
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        if (this.javaTypeEnhancementState.getDisabledDefaultAnnotations() || (qualifierForDefaultingAnnotation = AnnotationQualifiersFqNamesKt.getBUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS().get(annotationDescriptor.getFqName())) == null) {
            return null;
        }
        ReportLevel it = resolveDefaultAnnotationState(annotationDescriptor);
        ReportLevel state = it != ReportLevel.IGNORE ? it : null;
        if (state == null) {
            return null;
        }
        return JavaDefaultQualifiers.copy$default(qualifierForDefaultingAnnotation, NullabilityQualifierWithMigrationStatus.copy$default(qualifierForDefaultingAnnotation.getNullabilityQualifier(), null, state.isWarning(), 1, null), null, false, 6, null);
    }

    private final ReportLevel resolveDefaultAnnotationState(AnnotationDescriptor annotationDescriptor) {
        if (AnnotationQualifiersFqNamesKt.getJSPECIFY_DEFAULT_ANNOTATIONS().containsKey(annotationDescriptor.getFqName())) {
            return this.javaTypeEnhancementState.getJspecifyReportLevel();
        }
        return resolveJsr305AnnotationState(annotationDescriptor);
    }

    @Nullable
    public final TypeQualifierWithApplicability resolveTypeQualifierDefaultAnnotation(@NotNull AnnotationDescriptor annotationDescriptor) {
        Object obj;
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        if (this.javaTypeEnhancementState.getDisabledJsr305()) {
            return null;
        }
        ClassDescriptor it = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        ClassDescriptor classDescriptor = (it != null && it.getAnnotations().hasAnnotation(AnnotationQualifiersFqNamesKt.getTYPE_QUALIFIER_DEFAULT_FQNAME())) ? it : null;
        ClassDescriptor typeQualifierDefaultAnnotatedClass = classDescriptor;
        if (typeQualifierDefaultAnnotatedClass == null) {
            return null;
        }
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        Intrinsics.checkNotNull(annotationClass);
        AnnotationDescriptor annotationDescriptorMo3547findAnnotation = annotationClass.getAnnotations().mo3547findAnnotation(AnnotationQualifiersFqNamesKt.getTYPE_QUALIFIER_DEFAULT_FQNAME());
        Intrinsics.checkNotNull(annotationDescriptorMo3547findAnnotation);
        Map $this$flatMap$iv = annotationDescriptorMo3547findAnnotation.getAllValueArguments();
        Collection destination$iv$iv = new ArrayList();
        for (Map.Entry element$iv$iv : $this$flatMap$iv.entrySet()) {
            Name parameter = element$iv$iv.getKey();
            ConstantValue<?> argument = element$iv$iv.getValue();
            Iterable list$iv$iv = Intrinsics.areEqual(parameter, JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME) ? mapJavaConstantToQualifierApplicabilityTypes(argument) : CollectionsKt.emptyList();
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        Iterable $this$fold$iv = (List) destination$iv$iv;
        int accumulator$iv = 0;
        for (Object element$iv : $this$fold$iv) {
            int acc = accumulator$iv;
            AnnotationQualifierApplicabilityType applicabilityType = (AnnotationQualifierApplicabilityType) element$iv;
            accumulator$iv = acc | (1 << applicabilityType.ordinal());
        }
        int elementTypesMask = accumulator$iv;
        Iterable $this$firstOrNull$iv = typeQualifierDefaultAnnotatedClass.getAnnotations();
        Iterator<AnnotationDescriptor> it2 = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            Object element$iv2 = it2.next();
            if (resolveTypeQualifierAnnotation((AnnotationDescriptor) element$iv2) != null) {
                obj = element$iv2;
                break;
            }
        }
        AnnotationDescriptor typeQualifier = (AnnotationDescriptor) obj;
        if (typeQualifier == null) {
            return null;
        }
        return new TypeQualifierWithApplicability(typeQualifier, elementTypesMask);
    }

    @Nullable
    public final TypeQualifierWithApplicability resolveAnnotation(@NotNull AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        ClassDescriptor annotatedClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        if (annotatedClass == null) {
            return null;
        }
        Annotations annotations = annotatedClass.getAnnotations();
        FqName TARGET_ANNOTATION = JvmAnnotationNames.TARGET_ANNOTATION;
        Intrinsics.checkNotNullExpressionValue(TARGET_ANNOTATION, "TARGET_ANNOTATION");
        AnnotationDescriptor target = annotations.mo3547findAnnotation(TARGET_ANNOTATION);
        if (target == null) {
            return null;
        }
        Map $this$flatMap$iv = target.getAllValueArguments();
        Collection destination$iv$iv = new ArrayList();
        for (Map.Entry element$iv$iv : $this$flatMap$iv.entrySet()) {
            ConstantValue<?> argument = element$iv$iv.getValue();
            Iterable list$iv$iv = mapKotlinConstantToQualifierApplicabilityTypes(argument);
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        Iterable $this$fold$iv = (List) destination$iv$iv;
        int accumulator$iv = 0;
        for (Object element$iv : $this$fold$iv) {
            int acc = accumulator$iv;
            AnnotationQualifierApplicabilityType applicabilityType = (AnnotationQualifierApplicabilityType) element$iv;
            accumulator$iv = acc | (1 << applicabilityType.ordinal());
        }
        int elementTypesMask = accumulator$iv;
        return new TypeQualifierWithApplicability(annotationDescriptor, elementTypesMask);
    }

    @NotNull
    public final ReportLevel resolveJsr305AnnotationState(@NotNull AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        ReportLevel it = resolveJsr305CustomState(annotationDescriptor);
        if (it != null) {
            return it;
        }
        return this.javaTypeEnhancementState.getGlobalJsr305Level();
    }

    @Nullable
    public final ReportLevel resolveJsr305CustomState(@NotNull AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        Map<String, ReportLevel> userDefinedLevelForSpecificJsr305Annotation = this.javaTypeEnhancementState.getUserDefinedLevelForSpecificJsr305Annotation();
        FqName fqName = annotationDescriptor.getFqName();
        ReportLevel it = userDefinedLevelForSpecificJsr305Annotation.get(fqName == null ? null : fqName.asString());
        if (it != null) {
            return it;
        }
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        if (annotationClass == null) {
            return null;
        }
        return migrationAnnotationStatus(annotationClass);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final ReportLevel migrationAnnotationStatus(ClassDescriptor $this$migrationAnnotationStatus) {
        AnnotationDescriptor annotationDescriptorMo3547findAnnotation = $this$migrationAnnotationStatus.getAnnotations().mo3547findAnnotation(AnnotationQualifiersFqNamesKt.getMIGRATION_ANNOTATION_FQNAME());
        ConstantValue<?> constantValueFirstArgument = annotationDescriptorMo3547findAnnotation == null ? null : DescriptorUtilsKt.firstArgument(annotationDescriptorMo3547findAnnotation);
        EnumValue enumValue = constantValueFirstArgument instanceof EnumValue ? (EnumValue) constantValueFirstArgument : null;
        if (enumValue == null) {
            return null;
        }
        ReportLevel it = this.javaTypeEnhancementState.getMigrationLevelForJsr305();
        if (it != null) {
            return it;
        }
        String strAsString = enumValue.getEnumEntryName().asString();
        switch (strAsString.hashCode()) {
            case -2137067054:
                if (strAsString.equals("IGNORE")) {
                    return ReportLevel.IGNORE;
                }
                return null;
            case -1838656823:
                if (strAsString.equals("STRICT")) {
                    return ReportLevel.STRICT;
                }
                return null;
            case 2656902:
                if (strAsString.equals("WARN")) {
                    return ReportLevel.WARN;
                }
                return null;
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> toKotlinTargetNames(String $this$toKotlinTargetNames) {
        Iterable $this$map$iv = JavaAnnotationTargetMapper.INSTANCE.mapJavaTargetArgumentByName($this$toKotlinTargetNames);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            KotlinTarget it = (KotlinTarget) item$iv$iv;
            destination$iv$iv.add(it.name());
        }
        return (List) destination$iv$iv;
    }

    private final List<AnnotationQualifierApplicabilityType> mapConstantToQualifierApplicabilityTypes(ConstantValue<?> constantValue, Function2<? super EnumValue, ? super AnnotationQualifierApplicabilityType, Boolean> function2) {
        AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType;
        if (!(constantValue instanceof ArrayValue)) {
            if (!(constantValue instanceof EnumValue)) {
                return CollectionsKt.emptyList();
            }
            AnnotationQualifierApplicabilityType[] annotationQualifierApplicabilityTypeArrValues = AnnotationQualifierApplicabilityType.values();
            int length = annotationQualifierApplicabilityTypeArrValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    annotationQualifierApplicabilityType = null;
                    break;
                }
                AnnotationQualifierApplicabilityType it = annotationQualifierApplicabilityTypeArrValues[i];
                if (function2.invoke(constantValue, it).booleanValue()) {
                    annotationQualifierApplicabilityType = it;
                    break;
                }
                i++;
            }
            return CollectionsKt.listOfNotNull(annotationQualifierApplicabilityType);
        }
        Iterable $this$flatMap$iv = ((ArrayValue) constantValue).getValue();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            Iterable list$iv$iv = mapConstantToQualifierApplicabilityTypes((ConstantValue) element$iv$iv, function2);
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        return (List) destination$iv$iv;
    }

    private final List<AnnotationQualifierApplicabilityType> mapJavaConstantToQualifierApplicabilityTypes(ConstantValue<?> constantValue) {
        return mapConstantToQualifierApplicabilityTypes(constantValue, new Function2<EnumValue, AnnotationQualifierApplicabilityType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.mapJavaConstantToQualifierApplicabilityTypes.1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(@NotNull EnumValue mapConstantToQualifierApplicabilityTypes, @NotNull AnnotationQualifierApplicabilityType it) {
                Intrinsics.checkNotNullParameter(mapConstantToQualifierApplicabilityTypes, "$this$mapConstantToQualifierApplicabilityTypes");
                Intrinsics.checkNotNullParameter(it, "it");
                return Intrinsics.areEqual(mapConstantToQualifierApplicabilityTypes.getEnumEntryName().getIdentifier(), it.getJavaTarget());
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Boolean invoke(EnumValue enumValue, AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType) {
                return Boolean.valueOf(invoke2(enumValue, annotationQualifierApplicabilityType));
            }
        });
    }

    private final List<AnnotationQualifierApplicabilityType> mapKotlinConstantToQualifierApplicabilityTypes(ConstantValue<?> constantValue) {
        return mapConstantToQualifierApplicabilityTypes(constantValue, new Function2<EnumValue, AnnotationQualifierApplicabilityType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.mapKotlinConstantToQualifierApplicabilityTypes.1
            {
                super(2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(@NotNull EnumValue mapConstantToQualifierApplicabilityTypes, @NotNull AnnotationQualifierApplicabilityType it) {
                Intrinsics.checkNotNullParameter(mapConstantToQualifierApplicabilityTypes, "$this$mapConstantToQualifierApplicabilityTypes");
                Intrinsics.checkNotNullParameter(it, "it");
                return AnnotationTypeQualifierResolver.this.toKotlinTargetNames(it.getJavaTarget()).contains(mapConstantToQualifierApplicabilityTypes.getEnumEntryName().getIdentifier());
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Boolean invoke(EnumValue enumValue, AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType) {
                return Boolean.valueOf(invoke2(enumValue, annotationQualifierApplicabilityType));
            }
        });
    }
}
