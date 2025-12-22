package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefinerKt;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DescriptorUtils.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/descriptorUtil/DescriptorUtilsKt.class */
public final class DescriptorUtilsKt {

    @NotNull
    private static final Name RETENTION_PARAMETER_NAME;

    static {
        Name nameIdentifier = Name.identifier("value");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(\"value\")");
        RETENTION_PARAMETER_NAME = nameIdentifier;
    }

    @NotNull
    public static final FqNameUnsafe getFqNameUnsafe(@NotNull DeclarationDescriptor $this$fqNameUnsafe) {
        Intrinsics.checkNotNullParameter($this$fqNameUnsafe, "<this>");
        FqNameUnsafe fqName = DescriptorUtils.getFqName($this$fqNameUnsafe);
        Intrinsics.checkNotNullExpressionValue(fqName, "getFqName(this)");
        return fqName;
    }

    @NotNull
    public static final FqName getFqNameSafe(@NotNull DeclarationDescriptor $this$fqNameSafe) {
        Intrinsics.checkNotNullParameter($this$fqNameSafe, "<this>");
        FqName fqNameSafe = DescriptorUtils.getFqNameSafe($this$fqNameSafe);
        Intrinsics.checkNotNullExpressionValue(fqNameSafe, "getFqNameSafe(this)");
        return fqNameSafe;
    }

    @NotNull
    public static final ModuleDescriptor getModule(@NotNull DeclarationDescriptor $this$module) {
        Intrinsics.checkNotNullParameter($this$module, "<this>");
        ModuleDescriptor containingModule = DescriptorUtils.getContainingModule($this$module);
        Intrinsics.checkNotNullExpressionValue(containingModule, "getContainingModule(this)");
        return containingModule;
    }

    @Nullable
    public static final ClassDescriptor resolveTopLevelClass(@NotNull ModuleDescriptor $this$resolveTopLevelClass, @NotNull FqName topLevelClassFqName, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter($this$resolveTopLevelClass, "<this>");
        Intrinsics.checkNotNullParameter(topLevelClassFqName, "topLevelClassFqName");
        Intrinsics.checkNotNullParameter(location, "location");
        boolean z = !topLevelClassFqName.isRoot();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Assertion failed");
        }
        FqName fqNameParent = topLevelClassFqName.parent();
        Intrinsics.checkNotNullExpressionValue(fqNameParent, "topLevelClassFqName.parent()");
        MemberScope memberScope = $this$resolveTopLevelClass.getPackage(fqNameParent).getMemberScope();
        Name nameShortName = topLevelClassFqName.shortName();
        Intrinsics.checkNotNullExpressionValue(nameShortName, "topLevelClassFqName.shortName()");
        ClassifierDescriptor contributedClassifier = memberScope.mo3858getContributedClassifier(nameShortName, location);
        if (contributedClassifier instanceof ClassDescriptor) {
            return (ClassDescriptor) contributedClassifier;
        }
        return null;
    }

    @Nullable
    public static final ClassId getClassId(@Nullable ClassifierDescriptor $this$classId) {
        DeclarationDescriptor owner;
        ClassId classId;
        if ($this$classId == null || (owner = $this$classId.getContainingDeclaration()) == null) {
            return null;
        }
        if (owner instanceof PackageFragmentDescriptor) {
            return new ClassId(((PackageFragmentDescriptor) owner).getFqName(), $this$classId.getName());
        }
        if (!(owner instanceof ClassifierDescriptorWithTypeParameters) || (classId = getClassId((ClassifierDescriptor) owner)) == null) {
            return null;
        }
        return classId.createNestedClassId($this$classId.getName());
    }

    @Nullable
    public static final ClassDescriptor getSuperClassNotAny(@NotNull ClassDescriptor $this$getSuperClassNotAny) {
        Intrinsics.checkNotNullParameter($this$getSuperClassNotAny, "<this>");
        for (KotlinType supertype : $this$getSuperClassNotAny.getDefaultType().getConstructor().mo3835getSupertypes()) {
            if (!KotlinBuiltIns.isAnyOrNullableAny(supertype)) {
                ClassifierDescriptor superClassifier = supertype.getConstructor().mo3831getDeclarationDescriptor();
                if (DescriptorUtils.isClassOrEnumClass(superClassifier)) {
                    if (superClassifier == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                    }
                    return (ClassDescriptor) superClassifier;
                }
            }
        }
        return null;
    }

    @NotNull
    public static final KotlinBuiltIns getBuiltIns(@NotNull DeclarationDescriptor $this$builtIns) {
        Intrinsics.checkNotNullParameter($this$builtIns, "<this>");
        return getModule($this$builtIns).getBuiltIns();
    }

    public static final boolean declaresOrInheritsDefaultValue(@NotNull ValueParameterDescriptor $this$declaresOrInheritsDefaultValue) {
        Intrinsics.checkNotNullParameter($this$declaresOrInheritsDefaultValue, "<this>");
        Boolean boolIfAny = DFS.ifAny(CollectionsKt.listOf($this$declaresOrInheritsDefaultValue), new DFS.Neighbors<ValueParameterDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.declaresOrInheritsDefaultValue.1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            @NotNull
            public final Iterable<ValueParameterDescriptor> getNeighbors(ValueParameterDescriptor current) {
                Iterable $this$map$iv = current.getOverriddenDescriptors();
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    ValueParameterDescriptor p0 = (ValueParameterDescriptor) item$iv$iv;
                    destination$iv$iv.add(p0.getOriginal());
                }
                return (List) destination$iv$iv;
            }
        }, AnonymousClass2.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(boolIfAny, "ifAny(\n        listOf(this),\n        { current -> current.overriddenDescriptors.map(ValueParameterDescriptor::getOriginal) },\n        ValueParameterDescriptor::declaresDefaultValue\n    )");
        return boolIfAny.booleanValue();
    }

    /* compiled from: DescriptorUtils.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$declaresOrInheritsDefaultValue$2, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/descriptorUtil/DescriptorUtilsKt$declaresOrInheritsDefaultValue$2.class */
    /* synthetic */ class AnonymousClass2 extends FunctionReference implements Function1<ValueParameterDescriptor, Boolean> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2(@NotNull ValueParameterDescriptor p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return p0.declaresDefaultValue();
        }

        @Override // kotlin.jvm.internal.CallableReference
        @NotNull
        public final String getSignature() {
            return "declaresDefaultValue()Z";
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        @NotNull
        public final String getName() {
            return "declaresDefaultValue";
        }

        @Override // kotlin.jvm.internal.CallableReference
        @NotNull
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(ValueParameterDescriptor.class);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(ValueParameterDescriptor valueParameterDescriptor) {
            return Boolean.valueOf(invoke2(valueParameterDescriptor));
        }
    }

    @NotNull
    public static final Sequence<DeclarationDescriptor> getParentsWithSelf(@NotNull DeclarationDescriptor $this$parentsWithSelf) {
        Intrinsics.checkNotNullParameter($this$parentsWithSelf, "<this>");
        return SequencesKt.generateSequence($this$parentsWithSelf, new Function1<DeclarationDescriptor, DeclarationDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$parentsWithSelf$1
            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final DeclarationDescriptor invoke(@NotNull DeclarationDescriptor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getContainingDeclaration();
            }
        });
    }

    @NotNull
    public static final Sequence<DeclarationDescriptor> getParents(@NotNull DeclarationDescriptor $this$parents) {
        Intrinsics.checkNotNullParameter($this$parents, "<this>");
        return SequencesKt.drop(getParentsWithSelf($this$parents), 1);
    }

    @NotNull
    public static final CallableMemberDescriptor getPropertyIfAccessor(@NotNull CallableMemberDescriptor $this$propertyIfAccessor) {
        Intrinsics.checkNotNullParameter($this$propertyIfAccessor, "<this>");
        if (!($this$propertyIfAccessor instanceof PropertyAccessorDescriptor)) {
            return $this$propertyIfAccessor;
        }
        PropertyDescriptor correspondingProperty = ((PropertyAccessorDescriptor) $this$propertyIfAccessor).getCorrespondingProperty();
        Intrinsics.checkNotNullExpressionValue(correspondingProperty, "correspondingProperty");
        return correspondingProperty;
    }

    @Nullable
    public static final FqName fqNameOrNull(@NotNull DeclarationDescriptor $this$fqNameOrNull) {
        Intrinsics.checkNotNullParameter($this$fqNameOrNull, "<this>");
        FqNameUnsafe it = getFqNameUnsafe($this$fqNameOrNull);
        FqNameUnsafe fqNameUnsafe = it.isSafe() ? it : null;
        if (fqNameUnsafe == null) {
            return null;
        }
        return fqNameUnsafe.toSafe();
    }

    public static /* synthetic */ CallableMemberDescriptor firstOverridden$default(CallableMemberDescriptor callableMemberDescriptor, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return firstOverridden(callableMemberDescriptor, z, function1);
    }

    @Nullable
    public static final CallableMemberDescriptor firstOverridden(@NotNull CallableMemberDescriptor $this$firstOverridden, final boolean useOriginal, @NotNull final Function1<? super CallableMemberDescriptor, Boolean> predicate) {
        Intrinsics.checkNotNullParameter($this$firstOverridden, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        final Ref.ObjectRef result = new Ref.ObjectRef();
        return (CallableMemberDescriptor) DFS.dfs(CollectionsKt.listOf($this$firstOverridden), new DFS.Neighbors<CallableMemberDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.firstOverridden.1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            @NotNull
            public final Iterable<CallableMemberDescriptor> getNeighbors(CallableMemberDescriptor current) {
                CallableMemberDescriptor descriptor = useOriginal ? current == null ? null : current.getOriginal() : current;
                Collection<? extends CallableMemberDescriptor> overriddenDescriptors = descriptor == null ? null : descriptor.getOverriddenDescriptors();
                return overriddenDescriptors == null ? CollectionsKt.emptyList() : overriddenDescriptors;
            }
        }, new DFS.AbstractNodeHandler<CallableMemberDescriptor, CallableMemberDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.firstOverridden.2
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(@NotNull CallableMemberDescriptor current) {
                Intrinsics.checkNotNullParameter(current, "current");
                return result.element == null;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public void afterChildren(@NotNull CallableMemberDescriptor current) {
                Intrinsics.checkNotNullParameter(current, "current");
                if (result.element == null && predicate.invoke(current).booleanValue()) {
                    result.element = current;
                }
            }

            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            @Nullable
            /* renamed from: result */
            public CallableMemberDescriptor mo3664result() {
                return result.element;
            }
        });
    }

    @Nullable
    public static final ClassDescriptor getAnnotationClass(@NotNull AnnotationDescriptor $this$annotationClass) {
        Intrinsics.checkNotNullParameter($this$annotationClass, "<this>");
        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = $this$annotationClass.getType().getConstructor().mo3831getDeclarationDescriptor();
        if (classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassDescriptor) {
            return (ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor;
        }
        return null;
    }

    @Nullable
    public static final ConstantValue<?> firstArgument(@NotNull AnnotationDescriptor $this$firstArgument) {
        Intrinsics.checkNotNullParameter($this$firstArgument, "<this>");
        return (ConstantValue) CollectionsKt.firstOrNull($this$firstArgument.getAllValueArguments().values());
    }

    @NotNull
    public static final KotlinTypeRefiner getKotlinTypeRefiner(@NotNull ModuleDescriptor $this$getKotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter($this$getKotlinTypeRefiner, "<this>");
        kotlin.reflect.jvm.internal.impl.types.checker.Ref ref = (kotlin.reflect.jvm.internal.impl.types.checker.Ref) $this$getKotlinTypeRefiner.getCapability(KotlinTypeRefinerKt.getREFINER_CAPABILITY());
        KotlinTypeRefiner kotlinTypeRefiner = ref == null ? null : (KotlinTypeRefiner) ref.getValue();
        return kotlinTypeRefiner == null ? KotlinTypeRefiner.Default.INSTANCE : kotlinTypeRefiner;
    }

    public static final boolean isTypeRefinementEnabled(@NotNull ModuleDescriptor $this$isTypeRefinementEnabled) {
        Intrinsics.checkNotNullParameter($this$isTypeRefinementEnabled, "<this>");
        kotlin.reflect.jvm.internal.impl.types.checker.Ref ref = (kotlin.reflect.jvm.internal.impl.types.checker.Ref) $this$isTypeRefinementEnabled.getCapability(KotlinTypeRefinerKt.getREFINER_CAPABILITY());
        return (ref == null ? null : (KotlinTypeRefiner) ref.getValue()) != null;
    }
}
