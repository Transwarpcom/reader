package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefinerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AbstractTypeConstructor.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/AbstractTypeConstructor.class */
public abstract class AbstractTypeConstructor implements TypeConstructor {
    private int hashCode;

    @NotNull
    private final NotNullLazyValue<Supertypes> supertypes;
    private final boolean shouldReportCyclicScopeWithCompanionWarning;

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: getDeclarationDescriptor */
    public abstract ClassifierDescriptor mo3831getDeclarationDescriptor();

    @NotNull
    protected abstract Collection<KotlinType> computeSupertypes();

    @NotNull
    protected abstract SupertypeLoopChecker getSupertypeLoopChecker();

    protected abstract boolean isSameClassifier(@NotNull ClassifierDescriptor classifierDescriptor);

    public AbstractTypeConstructor(@NotNull StorageManager storageManager) {
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        this.supertypes = storageManager.createLazyValueWithPostCompute(new Function0<Supertypes>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final AbstractTypeConstructor.Supertypes invoke() {
                return new AbstractTypeConstructor.Supertypes(this.this$0.computeSupertypes());
            }
        }, new Function1<Boolean, Supertypes>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$2
            @NotNull
            public final AbstractTypeConstructor.Supertypes invoke(boolean it) {
                return new AbstractTypeConstructor.Supertypes(CollectionsKt.listOf(ErrorUtils.ERROR_TYPE_FOR_LOOP_IN_SUPERTYPES));
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ AbstractTypeConstructor.Supertypes invoke(Boolean bool) {
                return invoke(bool.booleanValue());
            }
        }, new Function1<Supertypes, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AbstractTypeConstructor.Supertypes supertypes) {
                invoke2(supertypes);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull AbstractTypeConstructor.Supertypes supertypes) {
                Intrinsics.checkNotNullParameter(supertypes, "supertypes");
                SupertypeLoopChecker supertypeLoopChecker = this.this$0.getSupertypeLoopChecker();
                AbstractTypeConstructor abstractTypeConstructor = this.this$0;
                Collection<KotlinType> allSupertypes = supertypes.getAllSupertypes();
                final AbstractTypeConstructor abstractTypeConstructor2 = this.this$0;
                Function1<TypeConstructor, Iterable<? extends KotlinType>> function1 = new Function1<TypeConstructor, Iterable<? extends KotlinType>>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3$resultWithoutCycles$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    @NotNull
                    public final Iterable<KotlinType> invoke(@NotNull TypeConstructor it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return abstractTypeConstructor2.computeNeighbours(it, false);
                    }
                };
                final AbstractTypeConstructor abstractTypeConstructor3 = this.this$0;
                Collection resultWithoutCycles = supertypeLoopChecker.findLoopsInSupertypesAndDisconnect(abstractTypeConstructor, allSupertypes, function1, new Function1<KotlinType, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3$resultWithoutCycles$2
                    {
                        super(1);
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(@NotNull KotlinType it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        abstractTypeConstructor3.reportSupertypeLoopError(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KotlinType kotlinType) {
                        invoke2(kotlinType);
                        return Unit.INSTANCE;
                    }
                });
                if (resultWithoutCycles.isEmpty()) {
                    KotlinType it = this.this$0.defaultSupertypeIfEmpty();
                    Collection collectionListOf = it == null ? null : CollectionsKt.listOf(it);
                    if (collectionListOf == null) {
                        collectionListOf = CollectionsKt.emptyList();
                    }
                    resultWithoutCycles = collectionListOf;
                }
                if (this.this$0.getShouldReportCyclicScopeWithCompanionWarning()) {
                    final AbstractTypeConstructor abstractTypeConstructor4 = this.this$0;
                    Function1<TypeConstructor, Iterable<? extends KotlinType>> function12 = new Function1<TypeConstructor, Iterable<? extends KotlinType>>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3.2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        @NotNull
                        public final Iterable<KotlinType> invoke(@NotNull TypeConstructor it2) {
                            Intrinsics.checkNotNullParameter(it2, "it");
                            return abstractTypeConstructor4.computeNeighbours(it2, true);
                        }
                    };
                    final AbstractTypeConstructor abstractTypeConstructor5 = this.this$0;
                    this.this$0.getSupertypeLoopChecker().findLoopsInSupertypesAndDisconnect(this.this$0, resultWithoutCycles, function12, new Function1<KotlinType, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3.3
                        {
                            super(1);
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(@NotNull KotlinType it2) {
                            Intrinsics.checkNotNullParameter(it2, "it");
                            abstractTypeConstructor5.reportScopesLoopError(it2);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KotlinType kotlinType) {
                            invoke2(kotlinType);
                            return Unit.INSTANCE;
                        }
                    });
                }
                AbstractTypeConstructor abstractTypeConstructor6 = this.this$0;
                Collection collection = resultWithoutCycles;
                List<KotlinType> list = collection instanceof List ? (List) collection : null;
                supertypes.setSupertypesWithoutCycles(abstractTypeConstructor6.processSupertypesWithoutCycles(list == null ? CollectionsKt.toList(resultWithoutCycles) : list));
            }
        });
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: getSupertypes */
    public List<KotlinType> mo3835getSupertypes() {
        return this.supertypes.invoke().getSupertypesWithoutCycles();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public TypeConstructor refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return new ModuleViewTypeConstructor(this, kotlinTypeRefiner);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AbstractTypeConstructor.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/AbstractTypeConstructor$ModuleViewTypeConstructor.class */
    final class ModuleViewTypeConstructor implements TypeConstructor {

        @NotNull
        private final KotlinTypeRefiner kotlinTypeRefiner;

        @NotNull
        private final Lazy refinedSupertypes$delegate;
        final /* synthetic */ AbstractTypeConstructor this$0;

        public ModuleViewTypeConstructor(@NotNull AbstractTypeConstructor this$0, KotlinTypeRefiner kotlinTypeRefiner) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
            this.this$0 = this$0;
            this.kotlinTypeRefiner = kotlinTypeRefiner;
            LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
            final AbstractTypeConstructor abstractTypeConstructor = this.this$0;
            this.refinedSupertypes$delegate = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<List<? extends KotlinType>>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$ModuleViewTypeConstructor$refinedSupertypes$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final List<? extends KotlinType> invoke() {
                    return KotlinTypeRefinerKt.refineTypes(this.this$0.kotlinTypeRefiner, abstractTypeConstructor.mo3835getSupertypes());
                }
            });
        }

        private final List<KotlinType> getRefinedSupertypes() {
            return (List) this.refinedSupertypes$delegate.getValue();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        public List<TypeParameterDescriptor> getParameters() {
            List<TypeParameterDescriptor> parameters = this.this$0.getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "this@AbstractTypeConstructor.parameters");
            return parameters;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        /* renamed from: getSupertypes */
        public List<KotlinType> mo3835getSupertypes() {
            return getRefinedSupertypes();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return this.this$0.isDenotable();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        /* renamed from: getDeclarationDescriptor */
        public ClassifierDescriptor mo3831getDeclarationDescriptor() {
            return this.this$0.mo3831getDeclarationDescriptor();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        public KotlinBuiltIns getBuiltIns() {
            KotlinBuiltIns builtIns = this.this$0.getBuiltIns();
            Intrinsics.checkNotNullExpressionValue(builtIns, "this@AbstractTypeConstructor.builtIns");
            return builtIns;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        public TypeConstructor refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
            return this.this$0.refine(kotlinTypeRefiner);
        }

        public boolean equals(@Nullable Object other) {
            return this.this$0.equals(other);
        }

        public int hashCode() {
            return this.this$0.hashCode();
        }

        @NotNull
        public String toString() {
            return this.this$0.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AbstractTypeConstructor.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/AbstractTypeConstructor$Supertypes.class */
    static final class Supertypes {

        @NotNull
        private final Collection<KotlinType> allSupertypes;

        @NotNull
        private List<? extends KotlinType> supertypesWithoutCycles;

        /* JADX WARN: Multi-variable type inference failed */
        public Supertypes(@NotNull Collection<? extends KotlinType> allSupertypes) {
            Intrinsics.checkNotNullParameter(allSupertypes, "allSupertypes");
            this.allSupertypes = allSupertypes;
            this.supertypesWithoutCycles = CollectionsKt.listOf(ErrorUtils.ERROR_TYPE_FOR_LOOP_IN_SUPERTYPES);
        }

        @NotNull
        public final Collection<KotlinType> getAllSupertypes() {
            return this.allSupertypes;
        }

        @NotNull
        public final List<KotlinType> getSupertypesWithoutCycles() {
            return this.supertypesWithoutCycles;
        }

        public final void setSupertypesWithoutCycles(@NotNull List<? extends KotlinType> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.supertypesWithoutCycles = list;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Collection<KotlinType> computeNeighbours(TypeConstructor $this$computeNeighbours, boolean useCompanions) {
        AbstractTypeConstructor abstractClassifierDescriptor = $this$computeNeighbours instanceof AbstractTypeConstructor ? (AbstractTypeConstructor) $this$computeNeighbours : null;
        List listPlus = abstractClassifierDescriptor == null ? null : CollectionsKt.plus((Collection) abstractClassifierDescriptor.supertypes.invoke().getAllSupertypes(), (Iterable) abstractClassifierDescriptor.getAdditionalNeighboursInSupertypeGraph(useCompanions));
        if (listPlus != null) {
            return listPlus;
        }
        Collection<KotlinType> supertypes = $this$computeNeighbours.mo3835getSupertypes();
        Intrinsics.checkNotNullExpressionValue(supertypes, "supertypes");
        return supertypes;
    }

    protected void reportSupertypeLoopError(@NotNull KotlinType type) {
        Intrinsics.checkNotNullParameter(type, "type");
    }

    @NotNull
    protected List<KotlinType> processSupertypesWithoutCycles(@NotNull List<KotlinType> supertypes) {
        Intrinsics.checkNotNullParameter(supertypes, "supertypes");
        return supertypes;
    }

    protected void reportScopesLoopError(@NotNull KotlinType type) {
        Intrinsics.checkNotNullParameter(type, "type");
    }

    protected boolean getShouldReportCyclicScopeWithCompanionWarning() {
        return this.shouldReportCyclicScopeWithCompanionWarning;
    }

    @NotNull
    protected Collection<KotlinType> getAdditionalNeighboursInSupertypeGraph(boolean useCompanions) {
        return CollectionsKt.emptyList();
    }

    @Nullable
    protected KotlinType defaultSupertypeIfEmpty() {
        return null;
    }

    public int hashCode() {
        int iIdentityHashCode;
        int cachedHashCode = this.hashCode;
        if (cachedHashCode != 0) {
            return cachedHashCode;
        }
        ClassifierDescriptor descriptor = mo3831getDeclarationDescriptor();
        if (hasMeaningfulFqName(descriptor)) {
            iIdentityHashCode = DescriptorUtils.getFqName(descriptor).hashCode();
        } else {
            iIdentityHashCode = System.identityHashCode(this);
        }
        int computedHashCode = iIdentityHashCode;
        this.hashCode = computedHashCode;
        return computedHashCode;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TypeConstructor) || other.hashCode() != hashCode() || ((TypeConstructor) other).getParameters().size() != getParameters().size()) {
            return false;
        }
        ClassifierDescriptor myDescriptor = mo3831getDeclarationDescriptor();
        ClassifierDescriptor otherDescriptor = ((TypeConstructor) other).mo3831getDeclarationDescriptor();
        if (otherDescriptor == null || !hasMeaningfulFqName(myDescriptor) || !hasMeaningfulFqName(otherDescriptor)) {
            return false;
        }
        return isSameClassifier(otherDescriptor);
    }

    protected final boolean areFqNamesEqual(@NotNull ClassifierDescriptor first, @NotNull ClassifierDescriptor second) {
        Intrinsics.checkNotNullParameter(first, "first");
        Intrinsics.checkNotNullParameter(second, "second");
        if (!Intrinsics.areEqual(first.getName(), second.getName())) {
            return false;
        }
        DeclarationDescriptor a = first.getContainingDeclaration();
        DeclarationDescriptor containingDeclaration = second.getContainingDeclaration();
        while (true) {
            DeclarationDescriptor b = containingDeclaration;
            if (a != null && b != null) {
                if (a instanceof ModuleDescriptor) {
                    return b instanceof ModuleDescriptor;
                }
                if (b instanceof ModuleDescriptor) {
                    return false;
                }
                if (a instanceof PackageFragmentDescriptor) {
                    return (b instanceof PackageFragmentDescriptor) && Intrinsics.areEqual(((PackageFragmentDescriptor) a).getFqName(), ((PackageFragmentDescriptor) b).getFqName());
                }
                if ((b instanceof PackageFragmentDescriptor) || !Intrinsics.areEqual(a.getName(), b.getName())) {
                    return false;
                }
                a = a.getContainingDeclaration();
                containingDeclaration = b.getContainingDeclaration();
            } else {
                return true;
            }
        }
    }

    private final boolean hasMeaningfulFqName(ClassifierDescriptor descriptor) {
        return (ErrorUtils.isError(descriptor) || DescriptorUtils.isLocal(descriptor)) ? false : true;
    }
}
