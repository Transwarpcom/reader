package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMapper;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationQualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers;
import kotlin.reflect.jvm.internal.impl.load.java.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNamesKt;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.PossiblyExternalAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypeWithEnhancement;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.RawType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.JavaTypeEnhancementState;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: signatureEnhancement.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/SignatureEnhancement.class */
public final class SignatureEnhancement {

    @NotNull
    private final AnnotationTypeQualifierResolver annotationTypeQualifierResolver;

    @NotNull
    private final JavaTypeEnhancementState javaTypeEnhancementState;

    @NotNull
    private final JavaTypeEnhancement typeEnhancement;

    public SignatureEnhancement(@NotNull AnnotationTypeQualifierResolver annotationTypeQualifierResolver, @NotNull JavaTypeEnhancementState javaTypeEnhancementState, @NotNull JavaTypeEnhancement typeEnhancement) {
        Intrinsics.checkNotNullParameter(annotationTypeQualifierResolver, "annotationTypeQualifierResolver");
        Intrinsics.checkNotNullParameter(javaTypeEnhancementState, "javaTypeEnhancementState");
        Intrinsics.checkNotNullParameter(typeEnhancement, "typeEnhancement");
        this.annotationTypeQualifierResolver = annotationTypeQualifierResolver;
        this.javaTypeEnhancementState = javaTypeEnhancementState;
        this.typeEnhancement = typeEnhancement;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x006b, code lost:
    
        if (r0.equals("MAYBE") == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0078, code lost:
    
        if (r0.equals("NEVER") == false) goto L27;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus extractNullabilityTypeFromArgument(kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor r6, boolean r7) {
        /*
            r5 = this;
            r0 = r6
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r0 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.firstArgument(r0)
            r10 = r0
            r0 = r10
            boolean r0 = r0 instanceof kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue
            if (r0 == 0) goto L16
            r0 = r10
            kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue r0 = (kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue) r0
            goto L17
        L16:
            r0 = 0
        L17:
            r9 = r0
            r0 = r9
            if (r0 != 0) goto L2a
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r0 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus
            r1 = r0
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r2 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NOT_NULL
            r3 = r7
            r1.<init>(r2, r3)
            return r0
        L2a:
            r0 = r9
            r8 = r0
            r0 = r8
            kotlin.reflect.jvm.internal.impl.name.Name r0 = r0.getEnumEntryName()
            java.lang.String r0 = r0.asString()
            r9 = r0
            r0 = r9
            int r0 = r0.hashCode()
            switch(r0) {
                case 73135176: goto L64;
                case 74175084: goto L71;
                case 433141802: goto L7e;
                case 1933739535: goto L8b;
                default: goto Lc2;
            }
        L64:
            r0 = r9
            java.lang.String r1 = "MAYBE"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto La6
            goto Lc2
        L71:
            r0 = r9
            java.lang.String r1 = "NEVER"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto La6
            goto Lc2
        L7e:
            r0 = r9
            java.lang.String r1 = "UNKNOWN"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto Lb4
            goto Lc2
        L8b:
            r0 = r9
            java.lang.String r1 = "ALWAYS"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L98
            goto Lc2
        L98:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r0 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus
            r1 = r0
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r2 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NOT_NULL
            r3 = r7
            r1.<init>(r2, r3)
            goto Lc3
        La6:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r0 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus
            r1 = r0
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r2 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NULLABLE
            r3 = r7
            r1.<init>(r2, r3)
            goto Lc3
        Lb4:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r0 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus
            r1 = r0
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r2 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.FORCE_FLEXIBILITY
            r3 = r7
            r1.<init>(r2, r3)
            goto Lc3
        Lc2:
            r0 = 0
        Lc3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.extractNullabilityTypeFromArgument(kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor, boolean):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus");
    }

    @Nullable
    public final NullabilityQualifierWithMigrationStatus extractNullability(@NotNull AnnotationDescriptor annotationDescriptor, boolean areImprovementsEnabled, boolean typeParameterBounds) {
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusExtractNullabilityFromKnownAnnotations;
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        NullabilityQualifierWithMigrationStatus it = extractNullabilityFromKnownAnnotations(annotationDescriptor, areImprovementsEnabled, typeParameterBounds);
        if (it != null) {
            return it;
        }
        AnnotationDescriptor typeQualifierAnnotation = this.annotationTypeQualifierResolver.resolveTypeQualifierAnnotation(annotationDescriptor);
        if (typeQualifierAnnotation == null) {
            return null;
        }
        ReportLevel jsr305State = this.annotationTypeQualifierResolver.resolveJsr305AnnotationState(annotationDescriptor);
        if (jsr305State.isIgnore() || (nullabilityQualifierWithMigrationStatusExtractNullabilityFromKnownAnnotations = extractNullabilityFromKnownAnnotations(typeQualifierAnnotation, areImprovementsEnabled, typeParameterBounds)) == null) {
            return null;
        }
        return NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatusExtractNullabilityFromKnownAnnotations, null, jsr305State.isWarning(), 1, null);
    }

    private final NullabilityQualifierWithMigrationStatus extractNullabilityFromKnownAnnotations(AnnotationDescriptor annotationDescriptor, boolean areImprovementsEnabled, boolean typeParameterBounds) {
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus;
        FqName annotationFqName = annotationDescriptor.getFqName();
        if (annotationFqName == null) {
            return null;
        }
        boolean isForWarningOnly = (annotationDescriptor instanceof LazyJavaAnnotationDescriptor) && (((LazyJavaAnnotationDescriptor) annotationDescriptor).isFreshlySupportedTypeUseAnnotation() || typeParameterBounds) && !areImprovementsEnabled;
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusJspecifyMigrationStatus = jspecifyMigrationStatus(annotationFqName);
        if (nullabilityQualifierWithMigrationStatusJspecifyMigrationStatus == null) {
            NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusCommonMigrationStatus = commonMigrationStatus(annotationFqName, annotationDescriptor, isForWarningOnly);
            if (nullabilityQualifierWithMigrationStatusCommonMigrationStatus != null) {
                nullabilityQualifierWithMigrationStatus = nullabilityQualifierWithMigrationStatusCommonMigrationStatus;
            } else {
                return null;
            }
        } else {
            nullabilityQualifierWithMigrationStatus = nullabilityQualifierWithMigrationStatusJspecifyMigrationStatus;
        }
        NullabilityQualifierWithMigrationStatus migrationStatus = nullabilityQualifierWithMigrationStatus;
        if (!migrationStatus.isForWarningOnly() && (annotationDescriptor instanceof PossiblyExternalAnnotationDescriptor) && ((PossiblyExternalAnnotationDescriptor) annotationDescriptor).isIdeExternalAnnotation()) {
            return NullabilityQualifierWithMigrationStatus.copy$default(migrationStatus, null, true, 1, null);
        }
        return migrationStatus;
    }

    private final NullabilityQualifierWithMigrationStatus jspecifyMigrationStatus(FqName annotationFqName) {
        if (this.javaTypeEnhancementState.getJspecifyReportLevel() == ReportLevel.IGNORE) {
            return null;
        }
        boolean isForWarningOnly = this.javaTypeEnhancementState.getJspecifyReportLevel() == ReportLevel.WARN;
        if (Intrinsics.areEqual(annotationFqName, JvmAnnotationNamesKt.getJSPECIFY_NULLABLE())) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, isForWarningOnly);
        }
        if (Intrinsics.areEqual(annotationFqName, JvmAnnotationNamesKt.getJSPECIFY_NULLNESS_UNKNOWN())) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.FORCE_FLEXIBILITY, isForWarningOnly);
        }
        return null;
    }

    private final NullabilityQualifierWithMigrationStatus commonMigrationStatus(FqName annotationFqName, AnnotationDescriptor annotationDescriptor, boolean isForWarningOnly) {
        if (JvmAnnotationNamesKt.getNULLABLE_ANNOTATIONS().contains(annotationFqName)) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, isForWarningOnly);
        }
        if (JvmAnnotationNamesKt.getNOT_NULL_ANNOTATIONS().contains(annotationFqName)) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, isForWarningOnly);
        }
        if (Intrinsics.areEqual(annotationFqName, JvmAnnotationNamesKt.getJAVAX_NONNULL_ANNOTATION())) {
            return extractNullabilityTypeFromArgument(annotationDescriptor, isForWarningOnly);
        }
        if (Intrinsics.areEqual(annotationFqName, JvmAnnotationNamesKt.getCOMPATQUAL_NULLABLE_ANNOTATION()) && this.javaTypeEnhancementState.getEnableCompatqualCheckerFrameworkAnnotations()) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, isForWarningOnly);
        }
        if (Intrinsics.areEqual(annotationFqName, JvmAnnotationNamesKt.getCOMPATQUAL_NONNULL_ANNOTATION()) && this.javaTypeEnhancementState.getEnableCompatqualCheckerFrameworkAnnotations()) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, isForWarningOnly);
        }
        if (Intrinsics.areEqual(annotationFqName, JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NON_NULL_ANNOTATION())) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, true);
        }
        if (Intrinsics.areEqual(annotationFqName, JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NULLABLE_ANNOTATION())) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, true);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final <D extends CallableMemberDescriptor> Collection<D> enhanceSignatures(@NotNull LazyJavaResolverContext c, @NotNull Collection<? extends D> platformSignatures) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(platformSignatures, "platformSignatures");
        Collection<? extends D> $this$map$iv = platformSignatures;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            CallableMemberDescriptor it = (CallableMemberDescriptor) item$iv$iv;
            destination$iv$iv.add(enhanceSignature(it, c));
        }
        return (List) destination$iv$iv;
    }

    private final <D extends CallableMemberDescriptor> Annotations getDefaultAnnotations(D d, LazyJavaResolverContext c) {
        ClassifierDescriptor topLevelClassifier = DescriptorUtilKt.getTopLevelContainingClassifier(d);
        if (topLevelClassifier == null) {
            return d.getAnnotations();
        }
        LazyJavaClassDescriptor lazyJavaClassDescriptor = topLevelClassifier instanceof LazyJavaClassDescriptor ? (LazyJavaClassDescriptor) topLevelClassifier : null;
        Iterable moduleAnnotations = lazyJavaClassDescriptor == null ? null : lazyJavaClassDescriptor.getModuleAnnotations();
        List<JavaAnnotation> list = (Collection) moduleAnnotations;
        if (list == null || list.isEmpty()) {
            return d.getAnnotations();
        }
        Iterable $this$map$iv = moduleAnnotations;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            JavaAnnotation it = (JavaAnnotation) item$iv$iv;
            destination$iv$iv.add(new LazyJavaAnnotationDescriptor(c, it, true));
        }
        List moduleAnnotationDescriptors = (List) destination$iv$iv;
        return Annotations.Companion.create(CollectionsKt.plus((Iterable) d.getAnnotations(), (Iterable) moduleAnnotationDescriptors));
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final <D extends kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor> D enhanceSignature(D r9, kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r10) {
        /*
            Method dump skipped, instructions count: 1231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature(kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext):kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor");
    }

    @NotNull
    public final List<KotlinType> enhanceTypeParameterBounds(@NotNull TypeParameterDescriptor typeParameter, @NotNull List<? extends KotlinType> bounds, @NotNull LazyJavaResolverContext context) {
        Intrinsics.checkNotNullParameter(typeParameter, "typeParameter");
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        Intrinsics.checkNotNullParameter(context, "context");
        List<? extends KotlinType> $this$map$iv = bounds;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            KotlinType bound = (KotlinType) item$iv$iv;
            destination$iv$iv.add(TypeUtilsKt.contains(bound, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceTypeParameterBounds$1$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(@NotNull UnwrappedType it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it instanceof RawType;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(UnwrappedType unwrappedType) {
                    return Boolean.valueOf(invoke2(unwrappedType));
                }
            }) ? bound : SignatureParts.enhance$default(new SignatureParts(this, typeParameter, bound, CollectionsKt.emptyList(), false, context, AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS, true), null, 1, null).getType());
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public final KotlinType enhanceSuperType(@NotNull KotlinType type, @NotNull LazyJavaResolverContext context) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(context, "context");
        return SignatureParts.enhance$default(new SignatureParts(null, type, CollectionsKt.emptyList(), false, context, AnnotationQualifierApplicabilityType.TYPE_USE, false, 64, null), null, 1, null).getType();
    }

    /* compiled from: signatureEnhancement.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/SignatureEnhancement$SignatureParts.class */
    private final class SignatureParts {

        @Nullable
        private final Annotated typeContainer;

        @NotNull
        private final KotlinType fromOverride;

        @NotNull
        private final Collection<KotlinType> fromOverridden;
        private final boolean isCovariant;

        @NotNull
        private final LazyJavaResolverContext containerContext;

        @NotNull
        private final AnnotationQualifierApplicabilityType containerApplicabilityType;
        private final boolean typeParameterBounds;

        /* JADX WARN: Multi-variable type inference failed */
        public SignatureParts(@Nullable SignatureEnhancement this$0, @NotNull Annotated typeContainer, @NotNull KotlinType fromOverride, Collection<? extends KotlinType> fromOverridden, @NotNull boolean isCovariant, @NotNull LazyJavaResolverContext containerContext, AnnotationQualifierApplicabilityType containerApplicabilityType, boolean typeParameterBounds) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(fromOverride, "fromOverride");
            Intrinsics.checkNotNullParameter(fromOverridden, "fromOverridden");
            Intrinsics.checkNotNullParameter(containerContext, "containerContext");
            Intrinsics.checkNotNullParameter(containerApplicabilityType, "containerApplicabilityType");
            SignatureEnhancement.this = this$0;
            this.typeContainer = typeContainer;
            this.fromOverride = fromOverride;
            this.fromOverridden = fromOverridden;
            this.isCovariant = isCovariant;
            this.containerContext = containerContext;
            this.containerApplicabilityType = containerApplicabilityType;
            this.typeParameterBounds = typeParameterBounds;
        }

        public /* synthetic */ SignatureParts(Annotated annotated, KotlinType kotlinType, Collection collection, boolean z, LazyJavaResolverContext lazyJavaResolverContext, AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(SignatureEnhancement.this, annotated, kotlinType, collection, z, lazyJavaResolverContext, annotationQualifierApplicabilityType, (i & 64) != 0 ? false : z2);
        }

        private final boolean isForVarargParameter() {
            Object $this$safeAs$iv = this.typeContainer;
            Object obj = $this$safeAs$iv;
            if (!(obj instanceof ValueParameterDescriptor)) {
                obj = null;
            }
            ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) obj;
            return (valueParameterDescriptor == null ? null : valueParameterDescriptor.getVarargElementType()) != null;
        }

        public static /* synthetic */ PartEnhancementResult enhance$default(SignatureParts signatureParts, TypeEnhancementInfo typeEnhancementInfo, int i, Object obj) {
            if ((i & 1) != 0) {
                typeEnhancementInfo = null;
            }
            return signatureParts.enhance(typeEnhancementInfo);
        }

        @NotNull
        public final PartEnhancementResult enhance(@Nullable final TypeEnhancementInfo predefined) {
            final Function1 qualifiers = computeIndexedQualifiersForOverride();
            Function1 qualifiersWithPredefined = predefined == null ? null : new Function1<Integer, JavaTypeQualifiers>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$enhance$qualifiersWithPredefined$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ JavaTypeQualifiers invoke(Integer num) {
                    return invoke(num.intValue());
                }

                @NotNull
                public final JavaTypeQualifiers invoke(int index) {
                    JavaTypeQualifiers javaTypeQualifiers = predefined.getMap().get(Integer.valueOf(index));
                    return javaTypeQualifiers == null ? qualifiers.invoke(Integer.valueOf(index)) : javaTypeQualifiers;
                }
            };
            boolean containsFunctionN = TypeUtils.contains(this.fromOverride, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$enhance$containsFunctionN$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(UnwrappedType it) {
                    ClassifierDescriptor classifier = it.getConstructor().mo3831getDeclarationDescriptor();
                    if (classifier == null) {
                        return false;
                    }
                    return Boolean.valueOf(Intrinsics.areEqual(classifier.getName(), JavaToKotlinClassMap.INSTANCE.getFUNCTION_N_FQ_NAME().shortName()) && Intrinsics.areEqual(DescriptorUtilsKt.fqNameOrNull(classifier), JavaToKotlinClassMap.INSTANCE.getFUNCTION_N_FQ_NAME()));
                }
            });
            JavaTypeEnhancement $this$enhance_u24lambda_u2d2 = SignatureEnhancement.this.typeEnhancement;
            KotlinType enhanced = $this$enhance_u24lambda_u2d2.enhance(this.fromOverride, qualifiersWithPredefined == null ? qualifiers : qualifiersWithPredefined);
            PartEnhancementResult partEnhancementResult = enhanced == null ? null : new PartEnhancementResult(enhanced, true, containsFunctionN);
            if (partEnhancementResult != null) {
                return partEnhancementResult;
            }
            return new PartEnhancementResult(this.fromOverride, false, containsFunctionN);
        }

        private final JavaTypeQualifiers extractQualifiers(KotlinType $this$extractQualifiers) {
            Pair pair;
            NullabilityQualifier nullabilityQualifier;
            MutabilityQualifier mutabilityQualifier;
            if (FlexibleTypesKt.isFlexible($this$extractQualifiers)) {
                FlexibleType it = FlexibleTypesKt.asFlexibleType($this$extractQualifiers);
                pair = new Pair(it.getLowerBound(), it.getUpperBound());
            } else {
                pair = new Pair($this$extractQualifiers, $this$extractQualifiers);
            }
            Pair pair2 = pair;
            KotlinType lower = (KotlinType) pair2.component1();
            KotlinType upper = (KotlinType) pair2.component2();
            JavaToKotlinClassMapper mapper = JavaToKotlinClassMapper.INSTANCE;
            if (lower.isMarkedNullable()) {
                nullabilityQualifier = NullabilityQualifier.NULLABLE;
            } else {
                nullabilityQualifier = !upper.isMarkedNullable() ? NullabilityQualifier.NOT_NULL : null;
            }
            if (mapper.isReadOnly(lower)) {
                mutabilityQualifier = MutabilityQualifier.READ_ONLY;
            } else {
                mutabilityQualifier = mapper.isMutable(upper) ? MutabilityQualifier.MUTABLE : null;
            }
            return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, $this$extractQualifiers.unwrap() instanceof NotNullTypeParameter, false, 8, null);
        }

        /* JADX WARN: Removed duplicated region for block: B:73:0x0202  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers extractQualifiersFromAnnotations(kotlin.reflect.jvm.internal.impl.types.KotlinType r9, boolean r10, kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers r11, kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r12, boolean r13) {
            /*
                Method dump skipped, instructions count: 620
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.SignatureParts.extractQualifiersFromAnnotations(kotlin.reflect.jvm.internal.impl.types.KotlinType, boolean, kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers, kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor, boolean):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers");
        }

        private static final <T> T extractQualifiersFromAnnotations$ifPresent(List<FqName> list, Annotations composedAnnotation, T t) {
            boolean z;
            List<FqName> $this$any$iv = list;
            if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
                Iterator<T> it = $this$any$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        FqName it2 = (FqName) element$iv;
                        if (composedAnnotation.mo3547findAnnotation(it2) != null) {
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
            if (z) {
                return t;
            }
            return null;
        }

        private static final <T> T extractQualifiersFromAnnotations$uniqueNotNull(T t, T t2) {
            if (t == null || t2 == null || Intrinsics.areEqual(t, t2)) {
                return t == null ? t2 : t;
            }
            return null;
        }

        private final NullabilityQualifierWithMigrationStatus computeNullabilityInfoInTheAbsenceOfExplicitAnnotation(NullabilityQualifierWithMigrationStatus nullabilityFromBoundsForTypeBasedOnTypeParameter, JavaDefaultQualifiers defaultTypeQualifier, TypeParameterDescriptor typeParameterForArgument) {
            NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus;
            NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus2;
            if (nullabilityFromBoundsForTypeBasedOnTypeParameter != null) {
                nullabilityQualifierWithMigrationStatus = nullabilityFromBoundsForTypeBasedOnTypeParameter;
            } else {
                nullabilityQualifierWithMigrationStatus = (defaultTypeQualifier == null || (nullabilityQualifierWithMigrationStatus2 = defaultTypeQualifier.getNullabilityQualifier()) == null) ? null : new NullabilityQualifierWithMigrationStatus(nullabilityQualifierWithMigrationStatus2.getQualifier(), nullabilityQualifierWithMigrationStatus2.isForWarningOnly());
            }
            NullabilityQualifierWithMigrationStatus result = nullabilityQualifierWithMigrationStatus;
            NullabilityQualifierWithMigrationStatus boundsFromTypeParameterForArgument = typeParameterForArgument == null ? null : boundsNullability(typeParameterForArgument);
            return boundsFromTypeParameterForArgument == null ? result : result == null ? boundsFromTypeParameterForArgument : mostSpecific(boundsFromTypeParameterForArgument, result);
        }

        private final NullabilityQualifierWithMigrationStatus mostSpecific(NullabilityQualifierWithMigrationStatus a, NullabilityQualifierWithMigrationStatus b) {
            if (a.getQualifier() == NullabilityQualifier.FORCE_FLEXIBILITY) {
                return b;
            }
            if (b.getQualifier() == NullabilityQualifier.FORCE_FLEXIBILITY) {
                return a;
            }
            if (a.getQualifier() == NullabilityQualifier.NULLABLE) {
                return b;
            }
            if (b.getQualifier() == NullabilityQualifier.NULLABLE) {
                return a;
            }
            boolean z = a.getQualifier() == b.getQualifier() && a.getQualifier() == NullabilityQualifier.NOT_NULL;
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError("Expected everything is NOT_NULL, but " + a + " and " + b + " are found");
            }
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null);
        }

        private final Pair<NullabilityQualifierWithMigrationStatus, Boolean> nullabilityInfoBoundsForTypeParameterUsage(KotlinType $this$nullabilityInfoBoundsForTypeParameterUsage) {
            ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = $this$nullabilityInfoBoundsForTypeParameterUsage.getConstructor().mo3831getDeclarationDescriptor();
            TypeParameterDescriptor typeParameterDescriptor = classifierDescriptorMo3831getDeclarationDescriptor instanceof TypeParameterDescriptor ? (TypeParameterDescriptor) classifierDescriptorMo3831getDeclarationDescriptor : null;
            NullabilityQualifierWithMigrationStatus typeParameterBoundsNullability = typeParameterDescriptor == null ? null : boundsNullability(typeParameterDescriptor);
            if (typeParameterBoundsNullability == null) {
                return new Pair<>(null, false);
            }
            return new Pair<>(new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, typeParameterBoundsNullability.isForWarningOnly()), Boolean.valueOf(typeParameterBoundsNullability.getQualifier() == NullabilityQualifier.NOT_NULL));
        }

        private final NullabilityQualifierWithMigrationStatus boundsNullability(TypeParameterDescriptor $this$boundsNullability) {
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4;
            boolean z5;
            if (!($this$boundsNullability instanceof LazyJavaTypeParameterDescriptor)) {
                return null;
            }
            Iterable upperBounds = ((LazyJavaTypeParameterDescriptor) $this$boundsNullability).getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(upperBounds, "upperBounds");
            Iterable $this$all$iv = upperBounds;
            if (!($this$all$iv instanceof Collection) || !((Collection) $this$all$iv).isEmpty()) {
                Iterator it = $this$all$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        KotlinType p0 = (KotlinType) element$iv;
                        if (!KotlinTypeKt.isError(p0)) {
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
                return null;
            }
            Iterable upperBounds2 = ((LazyJavaTypeParameterDescriptor) $this$boundsNullability).getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(upperBounds2, "upperBounds");
            Iterable $this$all$iv2 = upperBounds2;
            if (!($this$all$iv2 instanceof Collection) || !((Collection) $this$all$iv2).isEmpty()) {
                Iterator it2 = $this$all$iv2.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        Object element$iv2 = it2.next();
                        KotlinType p02 = (KotlinType) element$iv2;
                        if (!SignatureEnhancementKt.isNullabilityFlexible(p02)) {
                            z2 = false;
                            break;
                        }
                    } else {
                        z2 = true;
                        break;
                    }
                }
            } else {
                z2 = true;
            }
            if (z2) {
                Iterable upperBounds3 = ((LazyJavaTypeParameterDescriptor) $this$boundsNullability).getUpperBounds();
                Intrinsics.checkNotNullExpressionValue(upperBounds3, "upperBounds");
                Iterable $this$any$iv = upperBounds3;
                if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                    Iterator it3 = $this$any$iv.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            Object element$iv3 = it3.next();
                            KotlinType it4 = (KotlinType) element$iv3;
                            if ((it4 instanceof FlexibleTypeWithEnhancement) && !KotlinTypeKt.isNullable(((FlexibleTypeWithEnhancement) it4).getEnhancement())) {
                                z4 = true;
                                break;
                            }
                        } else {
                            z4 = false;
                            break;
                        }
                    }
                } else {
                    z4 = false;
                }
                if (z4) {
                    return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, true);
                }
                Iterable upperBounds4 = ((LazyJavaTypeParameterDescriptor) $this$boundsNullability).getUpperBounds();
                Intrinsics.checkNotNullExpressionValue(upperBounds4, "upperBounds");
                Iterable $this$any$iv2 = upperBounds4;
                if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
                    Iterator it5 = $this$any$iv2.iterator();
                    while (true) {
                        if (it5.hasNext()) {
                            Object element$iv4 = it5.next();
                            KotlinType it6 = (KotlinType) element$iv4;
                            if ((it6 instanceof FlexibleTypeWithEnhancement) && KotlinTypeKt.isNullable(((FlexibleTypeWithEnhancement) it6).getEnhancement())) {
                                z5 = true;
                                break;
                            }
                        } else {
                            z5 = false;
                            break;
                        }
                    }
                } else {
                    z5 = false;
                }
                if (z5) {
                    return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, true);
                }
                return null;
            }
            Iterable upperBounds5 = ((LazyJavaTypeParameterDescriptor) $this$boundsNullability).getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(upperBounds5, "upperBounds");
            Iterable $this$any$iv3 = upperBounds5;
            if (!($this$any$iv3 instanceof Collection) || !((Collection) $this$any$iv3).isEmpty()) {
                Iterator it7 = $this$any$iv3.iterator();
                while (true) {
                    if (it7.hasNext()) {
                        Object element$iv5 = it7.next();
                        KotlinType it8 = (KotlinType) element$iv5;
                        Intrinsics.checkNotNullExpressionValue(it8, "it");
                        if (!KotlinTypeKt.isNullable(it8)) {
                            z3 = true;
                            break;
                        }
                    } else {
                        z3 = false;
                        break;
                    }
                }
            } else {
                z3 = false;
            }
            NullabilityQualifier resultingQualifier = z3 ? NullabilityQualifier.NOT_NULL : NullabilityQualifier.NULLABLE;
            return new NullabilityQualifierWithMigrationStatus(resultingQualifier, false, 2, null);
        }

        private final NullabilityQualifierWithMigrationStatus extractNullability(Annotations $this$extractNullability, boolean areImprovementsEnabled, boolean typeParameterBounds) {
            Annotations $this$firstNotNullResult$iv = $this$extractNullability;
            SignatureEnhancement signatureEnhancement = SignatureEnhancement.this;
            for (Object element$iv : $this$firstNotNullResult$iv) {
                AnnotationDescriptor it = (AnnotationDescriptor) element$iv;
                NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusExtractNullability = signatureEnhancement.extractNullability(it, areImprovementsEnabled, typeParameterBounds);
                if (nullabilityQualifierWithMigrationStatusExtractNullability != null) {
                    return nullabilityQualifierWithMigrationStatusExtractNullability;
                }
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x00e8  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers> computeIndexedQualifiersForOverride() {
            /*
                Method dump skipped, instructions count: 604
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.SignatureParts.computeIndexedQualifiersForOverride():kotlin.jvm.functions.Function1");
        }

        private final List<TypeAndDefaultQualifiers> toIndexed(KotlinType $this$toIndexed) {
            ArrayList list = new ArrayList(1);
            toIndexed$add(this, list, $this$toIndexed, this.containerContext, null);
            return list;
        }

        private static final void toIndexed$add(SignatureParts this$0, ArrayList<TypeAndDefaultQualifiers> arrayList, KotlinType type, LazyJavaResolverContext ownerContext, TypeParameterDescriptor typeParameterForArgument) {
            LazyJavaResolverContext c = ContextKt.copyWithNewDefaultTypeQualifiers(ownerContext, type.getAnnotations());
            JavaTypeQualifiersByElementType defaultTypeQualifiers = c.getDefaultTypeQualifiers();
            JavaDefaultQualifiers defaultQualifiers = defaultTypeQualifiers == null ? null : defaultTypeQualifiers.get(this$0.typeParameterBounds ? AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS : AnnotationQualifierApplicabilityType.TYPE_USE);
            arrayList.add(new TypeAndDefaultQualifiers(type, defaultQualifiers, typeParameterForArgument, false));
            List<TypeProjection> arguments = type.getArguments();
            List<TypeParameterDescriptor> parameters = type.getConstructor().getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "type.constructor.parameters");
            for (Pair pair : CollectionsKt.zip(arguments, parameters)) {
                TypeProjection arg = (TypeProjection) pair.component1();
                TypeParameterDescriptor parameter = (TypeParameterDescriptor) pair.component2();
                if (arg.isStarProjection()) {
                    KotlinType type2 = arg.getType();
                    Intrinsics.checkNotNullExpressionValue(type2, "arg.type");
                    arrayList.add(new TypeAndDefaultQualifiers(type2, defaultQualifiers, parameter, true));
                } else {
                    KotlinType type3 = arg.getType();
                    Intrinsics.checkNotNullExpressionValue(type3, "arg.type");
                    toIndexed$add(this$0, arrayList, type3, c, parameter);
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:82:0x0349  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers computeQualifiersForOverride(kotlin.reflect.jvm.internal.impl.types.KotlinType r8, java.util.Collection<? extends kotlin.reflect.jvm.internal.impl.types.KotlinType> r9, kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers r10, boolean r11, kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r12, boolean r13) {
            /*
                Method dump skipped, instructions count: 900
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.SignatureParts.computeQualifiersForOverride(kotlin.reflect.jvm.internal.impl.types.KotlinType, java.util.Collection, kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers, boolean, kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor, boolean):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers");
        }
    }

    /* compiled from: signatureEnhancement.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/SignatureEnhancement$PartEnhancementResult.class */
    private static class PartEnhancementResult {

        @NotNull
        private final KotlinType type;
        private final boolean wereChanges;
        private final boolean containsFunctionN;

        public PartEnhancementResult(@NotNull KotlinType type, boolean wereChanges, boolean containsFunctionN) {
            Intrinsics.checkNotNullParameter(type, "type");
            this.type = type;
            this.wereChanges = wereChanges;
            this.containsFunctionN = containsFunctionN;
        }

        @NotNull
        public final KotlinType getType() {
            return this.type;
        }

        public final boolean getWereChanges() {
            return this.wereChanges;
        }

        public final boolean getContainsFunctionN() {
            return this.containsFunctionN;
        }
    }

    private final SignatureParts partsForValueParameter(CallableMemberDescriptor $this$partsForValueParameter, ValueParameterDescriptor parameterDescriptor, LazyJavaResolverContext methodContext, Function1<? super CallableMemberDescriptor, ? extends KotlinType> function1) {
        LazyJavaResolverContext lazyJavaResolverContext;
        SignatureEnhancement signatureEnhancement = this;
        CallableMemberDescriptor callableMemberDescriptor = $this$partsForValueParameter;
        ValueParameterDescriptor valueParameterDescriptor = parameterDescriptor;
        boolean z = false;
        if (parameterDescriptor == null) {
            lazyJavaResolverContext = methodContext;
        } else {
            LazyJavaResolverContext lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers = ContextKt.copyWithNewDefaultTypeQualifiers(methodContext, parameterDescriptor.getAnnotations());
            signatureEnhancement = signatureEnhancement;
            callableMemberDescriptor = callableMemberDescriptor;
            valueParameterDescriptor = valueParameterDescriptor;
            z = false;
            lazyJavaResolverContext = lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers == null ? methodContext : lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers;
        }
        return signatureEnhancement.parts(callableMemberDescriptor, valueParameterDescriptor, z, lazyJavaResolverContext, AnnotationQualifierApplicabilityType.VALUE_PARAMETER, function1);
    }

    private final SignatureParts parts(CallableMemberDescriptor $this$parts, Annotated typeContainer, boolean isCovariant, LazyJavaResolverContext containerContext, AnnotationQualifierApplicabilityType containerApplicabilityType, Function1<? super CallableMemberDescriptor, ? extends KotlinType> function1) {
        KotlinType kotlinTypeInvoke = function1.invoke($this$parts);
        Iterable overriddenDescriptors = $this$parts.getOverriddenDescriptors();
        Intrinsics.checkNotNullExpressionValue(overriddenDescriptors, "this.overriddenDescriptors");
        Iterable $this$map$iv = overriddenDescriptors;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            CallableMemberDescriptor it = (CallableMemberDescriptor) item$iv$iv;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            destination$iv$iv.add(function1.invoke(it));
        }
        return new SignatureParts(typeContainer, kotlinTypeInvoke, (List) destination$iv$iv, isCovariant, ContextKt.copyWithNewDefaultTypeQualifiers(containerContext, function1.invoke($this$parts).getAnnotations()), containerApplicabilityType, false, 64, null);
    }
}
