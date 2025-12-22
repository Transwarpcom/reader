package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.UtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaStaticClassScope.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaStaticClassScope.class */
public final class LazyJavaStaticClassScope extends LazyJavaStaticScope {

    @NotNull
    private final JavaClass jClass;

    @NotNull
    private final LazyJavaClassDescriptor ownerDescriptor;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    public LazyJavaClassDescriptor getOwnerDescriptor() {
        return this.ownerDescriptor;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaStaticClassScope(@NotNull LazyJavaResolverContext c, @NotNull JavaClass jClass, @NotNull LazyJavaClassDescriptor ownerDescriptor) {
        super(c);
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        Intrinsics.checkNotNullParameter(ownerDescriptor, "ownerDescriptor");
        this.jClass = jClass;
        this.ownerDescriptor = ownerDescriptor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    public ClassDeclaredMemberIndex computeMemberIndex() {
        return new ClassDeclaredMemberIndex(this.jClass, new Function1<JavaMember, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.computeMemberIndex.1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(@NotNull JavaMember it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.isStatic();
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(JavaMember javaMember) {
                return Boolean.valueOf(invoke2(javaMember));
            }
        });
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    protected Set<Name> computeFunctionNames(@NotNull DescriptorKindFilter kindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        Set $this$computeFunctionNames_u24lambda_u2d0 = CollectionsKt.toMutableSet(getDeclaredMemberIndex().invoke().getMethodNames());
        LazyJavaStaticClassScope parentJavaStaticClassScope = UtilKt.getParentJavaStaticClassScope(getOwnerDescriptor());
        Set<Name> functionNames = parentJavaStaticClassScope == null ? null : parentJavaStaticClassScope.getFunctionNames();
        if (functionNames == null) {
            functionNames = SetsKt.emptySet();
        }
        $this$computeFunctionNames_u24lambda_u2d0.addAll(functionNames);
        if (this.jClass.isEnum()) {
            $this$computeFunctionNames_u24lambda_u2d0.addAll(CollectionsKt.listOf((Object[]) new Name[]{StandardNames.ENUM_VALUE_OF, StandardNames.ENUM_VALUES}));
        }
        $this$computeFunctionNames_u24lambda_u2d0.addAll(getC().getComponents().getSyntheticPartsProvider().getStaticFunctionNames(getOwnerDescriptor()));
        return $this$computeFunctionNames_u24lambda_u2d0;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    protected Set<Name> computePropertyNames(@NotNull DescriptorKindFilter kindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        Set $this$computePropertyNames_u24lambda_u2d1 = CollectionsKt.toMutableSet(getDeclaredMemberIndex().invoke().getFieldNames());
        flatMapJavaStaticSupertypesScopes(getOwnerDescriptor(), $this$computePropertyNames_u24lambda_u2d1, new Function1<MemberScope, Collection<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope$computePropertyNames$1$1
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Collection<Name> invoke(@NotNull MemberScope it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getVariableNames();
            }
        });
        return $this$computePropertyNames_u24lambda_u2d1;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    protected Set<Name> computeClassNames(@NotNull DescriptorKindFilter kindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        return SetsKt.emptySet();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @Nullable
    /* renamed from: getContributedClassifier */
    public ClassifierDescriptor mo3858getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeNonDeclaredFunctions(@NotNull Collection<SimpleFunctionDescriptor> result, @NotNull Name name) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(name, "name");
        Set functionsFromSupertypes = getStaticFunctionsFromJavaSuperClasses(name, getOwnerDescriptor());
        Collection<? extends SimpleFunctionDescriptor> collectionResolveOverridesForStaticMembers = DescriptorResolverUtils.resolveOverridesForStaticMembers(name, functionsFromSupertypes, result, getOwnerDescriptor(), getC().getComponents().getErrorReporter(), getC().getComponents().getKotlinTypeChecker().getOverridingUtil());
        Intrinsics.checkNotNullExpressionValue(collectionResolveOverridesForStaticMembers, "resolveOverridesForStaticMembers(\n            name,\n            functionsFromSupertypes,\n            result,\n            ownerDescriptor,\n            c.components.errorReporter,\n            c.components.kotlinTypeChecker.overridingUtil\n        )");
        result.addAll(collectionResolveOverridesForStaticMembers);
        if (this.jClass.isEnum()) {
            if (!Intrinsics.areEqual(name, StandardNames.ENUM_VALUE_OF)) {
                if (Intrinsics.areEqual(name, StandardNames.ENUM_VALUES)) {
                    SimpleFunctionDescriptor simpleFunctionDescriptorCreateEnumValuesMethod = DescriptorFactory.createEnumValuesMethod(getOwnerDescriptor());
                    Intrinsics.checkNotNullExpressionValue(simpleFunctionDescriptorCreateEnumValuesMethod, "createEnumValuesMethod(ownerDescriptor)");
                    result.add(simpleFunctionDescriptorCreateEnumValuesMethod);
                    return;
                }
                return;
            }
            SimpleFunctionDescriptor simpleFunctionDescriptorCreateEnumValueOfMethod = DescriptorFactory.createEnumValueOfMethod(getOwnerDescriptor());
            Intrinsics.checkNotNullExpressionValue(simpleFunctionDescriptorCreateEnumValueOfMethod, "createEnumValueOfMethod(ownerDescriptor)");
            result.add(simpleFunctionDescriptorCreateEnumValueOfMethod);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeImplicitlyDeclaredFunctions(@NotNull Collection<SimpleFunctionDescriptor> result, @NotNull Name name) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(name, "name");
        getC().getComponents().getSyntheticPartsProvider().generateStaticFunctions(getOwnerDescriptor(), name, result);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticScope, kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeNonDeclaredProperties(@NotNull final Name name, @NotNull Collection<PropertyDescriptor> result) {
        Object obj;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(result, "result");
        Iterable propertiesFromSupertypes = flatMapJavaStaticSupertypesScopes(getOwnerDescriptor(), new LinkedHashSet(), new Function1<MemberScope, Collection<? extends PropertyDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope$computeNonDeclaredProperties$propertiesFromSupertypes$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Collection<? extends PropertyDescriptor> invoke(@NotNull MemberScope it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getContributedVariables(name, NoLookupLocation.WHEN_GET_SUPER_MEMBERS);
            }
        });
        if (!result.isEmpty()) {
            Collection<? extends PropertyDescriptor> collectionResolveOverridesForStaticMembers = DescriptorResolverUtils.resolveOverridesForStaticMembers(name, (Collection) propertiesFromSupertypes, result, getOwnerDescriptor(), getC().getComponents().getErrorReporter(), getC().getComponents().getKotlinTypeChecker().getOverridingUtil());
            Intrinsics.checkNotNullExpressionValue(collectionResolveOverridesForStaticMembers, "resolveOverridesForStaticMembers(\n                    name,\n                    propertiesFromSupertypes,\n                    result,\n                    ownerDescriptor,\n                    c.components.errorReporter,\n                    c.components.kotlinTypeChecker.overridingUtil\n                )");
            result.addAll(collectionResolveOverridesForStaticMembers);
            return;
        }
        Iterable $this$groupBy$iv = propertiesFromSupertypes;
        Map destination$iv$iv = new LinkedHashMap();
        for (Object element$iv$iv : $this$groupBy$iv) {
            PropertyDescriptor it = (PropertyDescriptor) element$iv$iv;
            PropertyDescriptor realOriginal = getRealOriginal(it);
            Object value$iv$iv$iv = destination$iv$iv.get(realOriginal);
            if (value$iv$iv$iv == null) {
                ArrayList arrayList = new ArrayList();
                destination$iv$iv.put(realOriginal, arrayList);
                obj = arrayList;
            } else {
                obj = value$iv$iv$iv;
            }
            List list$iv$iv = (List) obj;
            list$iv$iv.add(element$iv$iv);
        }
        Collection destination$iv$iv2 = new ArrayList();
        Iterator it2 = destination$iv$iv.entrySet().iterator();
        while (it2.hasNext()) {
            Iterable iterableResolveOverridesForStaticMembers = DescriptorResolverUtils.resolveOverridesForStaticMembers(name, (Collection) ((Map.Entry) it2.next()).getValue(), result, getOwnerDescriptor(), getC().getComponents().getErrorReporter(), getC().getComponents().getKotlinTypeChecker().getOverridingUtil());
            Intrinsics.checkNotNullExpressionValue(iterableResolveOverridesForStaticMembers, "resolveOverridesForStaticMembers(\n                    name, it.value, result, ownerDescriptor, c.components.errorReporter,\n                    c.components.kotlinTypeChecker.overridingUtil\n                )");
            Iterable list$iv$iv2 = iterableResolveOverridesForStaticMembers;
            CollectionsKt.addAll(destination$iv$iv2, list$iv$iv2);
        }
        result.addAll((List) destination$iv$iv2);
    }

    private final Set<SimpleFunctionDescriptor> getStaticFunctionsFromJavaSuperClasses(Name name, ClassDescriptor descriptor) {
        LazyJavaStaticClassScope staticScope = UtilKt.getParentJavaStaticClassScope(descriptor);
        return staticScope == null ? SetsKt.emptySet() : CollectionsKt.toSet(staticScope.getContributedFunctions(name, NoLookupLocation.WHEN_GET_SUPER_MEMBERS));
    }

    private final <R> Set<R> flatMapJavaStaticSupertypesScopes(final ClassDescriptor root, final Set<R> set, final Function1<? super MemberScope, ? extends Collection<? extends R>> function1) {
        DFS.dfs(CollectionsKt.listOf(root), new DFS.Neighbors<ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.flatMapJavaStaticSupertypesScopes.1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            @NotNull
            public final Iterable<ClassDescriptor> getNeighbors(ClassDescriptor it) {
                Collection<KotlinType> collectionMo3835getSupertypes = it.getTypeConstructor().mo3835getSupertypes();
                Intrinsics.checkNotNullExpressionValue(collectionMo3835getSupertypes, "it.typeConstructor.supertypes");
                return SequencesKt.asIterable(SequencesKt.mapNotNull(CollectionsKt.asSequence(collectionMo3835getSupertypes), new Function1<KotlinType, ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.flatMapJavaStaticSupertypesScopes.1.1
                    @Override // kotlin.jvm.functions.Function1
                    @Nullable
                    public final ClassDescriptor invoke(KotlinType supertype) {
                        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = supertype.getConstructor().mo3831getDeclarationDescriptor();
                        if (classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassDescriptor) {
                            return (ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor;
                        }
                        return null;
                    }
                }));
            }
        }, new DFS.AbstractNodeHandler<ClassDescriptor, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.flatMapJavaStaticSupertypesScopes.2
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            /* renamed from: result, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ Object mo3664result() {
                result();
                return Unit.INSTANCE;
            }

            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(@NotNull ClassDescriptor current) {
                Intrinsics.checkNotNullParameter(current, "current");
                if (current == root) {
                    return true;
                }
                MemberScope staticScope = current.getStaticScope();
                Intrinsics.checkNotNullExpressionValue(staticScope, "current.staticScope");
                if (staticScope instanceof LazyJavaStaticScope) {
                    set.addAll((Collection) function1.invoke(staticScope));
                    return false;
                }
                return true;
            }

            public void result() {
            }
        });
        return set;
    }

    private final PropertyDescriptor getRealOriginal(PropertyDescriptor $this$realOriginal) {
        if ($this$realOriginal.getKind().isReal()) {
            return $this$realOriginal;
        }
        Iterable overriddenDescriptors = $this$realOriginal.getOverriddenDescriptors();
        Intrinsics.checkNotNullExpressionValue(overriddenDescriptors, "this.overriddenDescriptors");
        Iterable $this$map$iv = overriddenDescriptors;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            PropertyDescriptor it = (PropertyDescriptor) item$iv$iv;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            destination$iv$iv.add(getRealOriginal(it));
        }
        return (PropertyDescriptor) CollectionsKt.single(CollectionsKt.distinct((List) destination$iv$iv));
    }
}
