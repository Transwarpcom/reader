package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptorKt;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinTypeFactory.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/KotlinTypeFactory.class */
public final class KotlinTypeFactory {

    @NotNull
    public static final KotlinTypeFactory INSTANCE = new KotlinTypeFactory();

    @NotNull
    private static final Function1<KotlinTypeRefiner, SimpleType> EMPTY_REFINED_TYPE_FACTORY = new Function1() { // from class: kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory$EMPTY_REFINED_TYPE_FACTORY$1
        @Override // kotlin.jvm.functions.Function1
        @Nullable
        public final Void invoke(@NotNull KotlinTypeRefiner noName_0) {
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            return null;
        }
    };

    private KotlinTypeFactory() {
    }

    private final MemberScope computeMemberScope(TypeConstructor constructor, List<? extends TypeProjection> list, KotlinTypeRefiner kotlinTypeRefiner) {
        ClassifierDescriptor descriptor = constructor.mo3831getDeclarationDescriptor();
        if (descriptor instanceof TypeParameterDescriptor) {
            return ((TypeParameterDescriptor) descriptor).getDefaultType().getMemberScope();
        }
        if (descriptor instanceof ClassDescriptor) {
            KotlinTypeRefiner refinerToUse = kotlinTypeRefiner == null ? DescriptorUtilsKt.getKotlinTypeRefiner(DescriptorUtilsKt.getModule(descriptor)) : kotlinTypeRefiner;
            if (list.isEmpty()) {
                return ModuleAwareClassDescriptorKt.getRefinedUnsubstitutedMemberScopeIfPossible((ClassDescriptor) descriptor, refinerToUse);
            }
            return ModuleAwareClassDescriptorKt.getRefinedMemberScopeIfPossible((ClassDescriptor) descriptor, TypeConstructorSubstitution.Companion.create(constructor, list), refinerToUse);
        }
        if (descriptor instanceof TypeAliasDescriptor) {
            MemberScope memberScopeCreateErrorScope = ErrorUtils.createErrorScope(Intrinsics.stringPlus("Scope for abbreviation: ", ((TypeAliasDescriptor) descriptor).getName()), true);
            Intrinsics.checkNotNullExpressionValue(memberScopeCreateErrorScope, "createErrorScope(\"Scope for abbreviation: ${descriptor.name}\", true)");
            return memberScopeCreateErrorScope;
        }
        if (constructor instanceof IntersectionTypeConstructor) {
            return ((IntersectionTypeConstructor) constructor).createScopeForKotlinType();
        }
        throw new IllegalStateException("Unsupported classifier: " + descriptor + " for constructor: " + constructor);
    }

    public static /* synthetic */ SimpleType simpleType$default(Annotations annotations, TypeConstructor typeConstructor, List list, boolean z, KotlinTypeRefiner kotlinTypeRefiner, int i, Object obj) {
        if ((i & 16) != 0) {
            kotlinTypeRefiner = null;
        }
        return simpleType(annotations, typeConstructor, list, z, kotlinTypeRefiner);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final SimpleType simpleType(@NotNull final Annotations annotations, @NotNull final TypeConstructor constructor, @NotNull final List<? extends TypeProjection> arguments, final boolean nullable, @Nullable KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        if (annotations.isEmpty() && arguments.isEmpty() && !nullable && constructor.mo3831getDeclarationDescriptor() != null) {
            ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = constructor.mo3831getDeclarationDescriptor();
            Intrinsics.checkNotNull(classifierDescriptorMo3831getDeclarationDescriptor);
            SimpleType defaultType = classifierDescriptorMo3831getDeclarationDescriptor.getDefaultType();
            Intrinsics.checkNotNullExpressionValue(defaultType, "constructor.declarationDescriptor!!.defaultType");
            return defaultType;
        }
        KotlinTypeFactory kotlinTypeFactory = INSTANCE;
        return simpleTypeWithNonTrivialMemberScope(annotations, constructor, arguments, nullable, INSTANCE.computeMemberScope(constructor, arguments, kotlinTypeRefiner), new Function1<KotlinTypeRefiner, SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory.simpleType.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final SimpleType invoke(@NotNull KotlinTypeRefiner refiner) {
                Intrinsics.checkNotNullParameter(refiner, "refiner");
                ExpandedTypeOrRefinedConstructor expandedTypeOrRefinedConstructor = KotlinTypeFactory.INSTANCE.refineConstructor(constructor, refiner, arguments);
                if (expandedTypeOrRefinedConstructor == null) {
                    return null;
                }
                SimpleType it = expandedTypeOrRefinedConstructor.getExpandedType();
                if (it != null) {
                    return it;
                }
                KotlinTypeFactory kotlinTypeFactory2 = KotlinTypeFactory.INSTANCE;
                Annotations annotations2 = annotations;
                TypeConstructor refinedConstructor = expandedTypeOrRefinedConstructor.getRefinedConstructor();
                Intrinsics.checkNotNull(refinedConstructor);
                return KotlinTypeFactory.simpleType(annotations2, refinedConstructor, arguments, nullable, refiner);
            }
        });
    }

    @JvmStatic
    @NotNull
    public static final SimpleType computeExpandedType(@NotNull TypeAliasDescriptor $this$computeExpandedType, @NotNull List<? extends TypeProjection> arguments) {
        Intrinsics.checkNotNullParameter($this$computeExpandedType, "<this>");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        return new TypeAliasExpander(TypeAliasExpansionReportStrategy.DO_NOTHING.INSTANCE, false).expand(TypeAliasExpansion.Companion.create(null, $this$computeExpandedType, arguments), Annotations.Companion.getEMPTY());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ExpandedTypeOrRefinedConstructor refineConstructor(TypeConstructor constructor, KotlinTypeRefiner kotlinTypeRefiner, List<? extends TypeProjection> list) {
        ClassifierDescriptor basicDescriptor = constructor.mo3831getDeclarationDescriptor();
        ClassifierDescriptor descriptor = basicDescriptor == null ? null : kotlinTypeRefiner.refineDescriptor(basicDescriptor);
        if (descriptor == null) {
            return null;
        }
        if (descriptor instanceof TypeAliasDescriptor) {
            return new ExpandedTypeOrRefinedConstructor(computeExpandedType((TypeAliasDescriptor) descriptor, list), null);
        }
        TypeConstructor refinedConstructor = descriptor.getTypeConstructor().refine(kotlinTypeRefiner);
        Intrinsics.checkNotNullExpressionValue(refinedConstructor, "descriptor.typeConstructor.refine(kotlinTypeRefiner)");
        return new ExpandedTypeOrRefinedConstructor(null, refinedConstructor);
    }

    /* compiled from: KotlinTypeFactory.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/KotlinTypeFactory$ExpandedTypeOrRefinedConstructor.class */
    private static final class ExpandedTypeOrRefinedConstructor {

        @Nullable
        private final SimpleType expandedType;

        @Nullable
        private final TypeConstructor refinedConstructor;

        public ExpandedTypeOrRefinedConstructor(@Nullable SimpleType expandedType, @Nullable TypeConstructor refinedConstructor) {
            this.expandedType = expandedType;
            this.refinedConstructor = refinedConstructor;
        }

        @Nullable
        public final SimpleType getExpandedType() {
            return this.expandedType;
        }

        @Nullable
        public final TypeConstructor getRefinedConstructor() {
            return this.refinedConstructor;
        }
    }

    @JvmStatic
    @NotNull
    public static final SimpleType simpleTypeWithNonTrivialMemberScope(@NotNull final Annotations annotations, @NotNull final TypeConstructor constructor, @NotNull final List<? extends TypeProjection> arguments, final boolean nullable, @NotNull final MemberScope memberScope) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Intrinsics.checkNotNullParameter(memberScope, "memberScope");
        SimpleTypeImpl it = new SimpleTypeImpl(constructor, arguments, nullable, memberScope, new Function1<KotlinTypeRefiner, SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final SimpleType invoke(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
                Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
                ExpandedTypeOrRefinedConstructor expandedTypeOrRefinedConstructor = KotlinTypeFactory.INSTANCE.refineConstructor(constructor, kotlinTypeRefiner, arguments);
                if (expandedTypeOrRefinedConstructor == null) {
                    return null;
                }
                SimpleType it2 = expandedTypeOrRefinedConstructor.getExpandedType();
                if (it2 != null) {
                    return it2;
                }
                KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
                Annotations annotations2 = annotations;
                TypeConstructor refinedConstructor = expandedTypeOrRefinedConstructor.getRefinedConstructor();
                Intrinsics.checkNotNull(refinedConstructor);
                return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(annotations2, refinedConstructor, arguments, nullable, memberScope);
            }
        });
        if (annotations.isEmpty()) {
            return it;
        }
        return new AnnotatedSimpleType(it, annotations);
    }

    @JvmStatic
    @NotNull
    public static final SimpleType simpleTypeWithNonTrivialMemberScope(@NotNull Annotations annotations, @NotNull TypeConstructor constructor, @NotNull List<? extends TypeProjection> arguments, boolean nullable, @NotNull MemberScope memberScope, @NotNull Function1<? super KotlinTypeRefiner, ? extends SimpleType> refinedTypeFactory) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Intrinsics.checkNotNullParameter(memberScope, "memberScope");
        Intrinsics.checkNotNullParameter(refinedTypeFactory, "refinedTypeFactory");
        SimpleTypeImpl it = new SimpleTypeImpl(constructor, arguments, nullable, memberScope, refinedTypeFactory);
        if (annotations.isEmpty()) {
            return it;
        }
        return new AnnotatedSimpleType(it, annotations);
    }

    @JvmStatic
    @NotNull
    public static final SimpleType simpleNotNullType(@NotNull Annotations annotations, @NotNull ClassDescriptor descriptor, @NotNull List<? extends TypeProjection> arguments) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        KotlinTypeFactory kotlinTypeFactory = INSTANCE;
        TypeConstructor typeConstructor = descriptor.getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "descriptor.typeConstructor");
        return simpleType$default(annotations, typeConstructor, arguments, false, null, 16, null);
    }

    @JvmStatic
    @NotNull
    public static final UnwrappedType flexibleType(@NotNull SimpleType lowerBound, @NotNull SimpleType upperBound) {
        Intrinsics.checkNotNullParameter(lowerBound, "lowerBound");
        Intrinsics.checkNotNullParameter(upperBound, "upperBound");
        return Intrinsics.areEqual(lowerBound, upperBound) ? lowerBound : new FlexibleTypeImpl(lowerBound, upperBound);
    }

    @JvmStatic
    @NotNull
    public static final SimpleType integerLiteralType(@NotNull Annotations annotations, @NotNull IntegerLiteralTypeConstructor constructor, boolean nullable) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        KotlinTypeFactory kotlinTypeFactory = INSTANCE;
        List listEmptyList = CollectionsKt.emptyList();
        MemberScope memberScopeCreateErrorScope = ErrorUtils.createErrorScope("Scope for integer literal type", true);
        Intrinsics.checkNotNullExpressionValue(memberScopeCreateErrorScope, "createErrorScope(\"Scope for integer literal type\", true)");
        return simpleTypeWithNonTrivialMemberScope(annotations, constructor, listEmptyList, nullable, memberScopeCreateErrorScope);
    }
}
