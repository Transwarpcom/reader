package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;
import org.jetbrains.annotations.NotNull;

/* compiled from: GivenFunctionsMemberScope.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/scopes/GivenFunctionsMemberScope.class */
public abstract class GivenFunctionsMemberScope extends MemberScopeImpl {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(GivenFunctionsMemberScope.class), "allDescriptors", "getAllDescriptors()Ljava/util/List;"))};

    @NotNull
    private final ClassDescriptor containingClass;

    @NotNull
    private final NotNullLazyValue allDescriptors$delegate;

    @NotNull
    protected abstract List<FunctionDescriptor> computeDeclaredFunctions();

    @NotNull
    protected final ClassDescriptor getContainingClass() {
        return this.containingClass;
    }

    public GivenFunctionsMemberScope(@NotNull StorageManager storageManager, @NotNull ClassDescriptor containingClass) {
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(containingClass, "containingClass");
        this.containingClass = containingClass;
        this.allDescriptors$delegate = storageManager.createLazyValue(new Function0<List<? extends DeclarationDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope$allDescriptors$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final List<? extends DeclarationDescriptor> invoke() {
                List fromCurrent = this.this$0.computeDeclaredFunctions();
                return CollectionsKt.plus((Collection) fromCurrent, (Iterable) this.this$0.createFakeOverrides(fromCurrent));
            }
        });
    }

    private final List<DeclarationDescriptor> getAllDescriptors() {
        return (List) StorageKt.getValue(this.allDescriptors$delegate, this, (KProperty<?>) $$delegatedProperties[0]);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    public Collection<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
        return !kindFilter.acceptsKinds(DescriptorKindFilter.CALLABLES.getKindMask()) ? CollectionsKt.emptyList() : getAllDescriptors();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        Iterable $this$filterIsInstanceAnd$iv = getAllDescriptors();
        Collection destination$iv$iv = new SmartList();
        for (Object element$iv$iv : $this$filterIsInstanceAnd$iv) {
            if (element$iv$iv instanceof SimpleFunctionDescriptor) {
                SimpleFunctionDescriptor it = (SimpleFunctionDescriptor) element$iv$iv;
                if (Intrinsics.areEqual(it.getName(), name)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
        }
        return destination$iv$iv;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        Iterable $this$filterIsInstanceAnd$iv = getAllDescriptors();
        Collection destination$iv$iv = new SmartList();
        for (Object element$iv$iv : $this$filterIsInstanceAnd$iv) {
            if (element$iv$iv instanceof PropertyDescriptor) {
                PropertyDescriptor it = (PropertyDescriptor) element$iv$iv;
                if (Intrinsics.areEqual(it.getName(), name)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
        }
        return destination$iv$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<DeclarationDescriptor> createFakeOverrides(List<? extends FunctionDescriptor> list) {
        List listEmptyList;
        Object obj;
        Object obj2;
        final ArrayList result = new ArrayList(3);
        Iterable iterableMo3835getSupertypes = this.containingClass.getTypeConstructor().mo3835getSupertypes();
        Intrinsics.checkNotNullExpressionValue(iterableMo3835getSupertypes, "containingClass.typeConstructor.supertypes");
        Iterable<KotlinType> $this$flatMap$iv = iterableMo3835getSupertypes;
        Collection destination$iv$iv = new ArrayList();
        for (KotlinType it : $this$flatMap$iv) {
            Iterable list$iv$iv = ResolutionScope.DefaultImpls.getContributedDescriptors$default(it.getMemberScope(), null, null, 3, null);
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        Iterable $this$filterIsInstance$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof CallableMemberDescriptor) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        Iterable allSuperDescriptors = (List) destination$iv$iv2;
        Iterable $this$groupBy$iv = allSuperDescriptors;
        Map destination$iv$iv3 = new LinkedHashMap();
        for (Object element$iv$iv2 : $this$groupBy$iv) {
            CallableMemberDescriptor it2 = (CallableMemberDescriptor) element$iv$iv2;
            Name name = it2.getName();
            Object value$iv$iv$iv = destination$iv$iv3.get(name);
            if (value$iv$iv$iv == null) {
                ArrayList arrayList = new ArrayList();
                destination$iv$iv3.put(name, arrayList);
                obj2 = arrayList;
            } else {
                obj2 = value$iv$iv$iv;
            }
            List list$iv$iv2 = (List) obj2;
            list$iv$iv2.add(element$iv$iv2);
        }
        for (Map.Entry entry : destination$iv$iv3.entrySet()) {
            Name name2 = (Name) entry.getKey();
            Iterable group = (List) entry.getValue();
            Iterable $this$groupBy$iv2 = group;
            Map destination$iv$iv4 = new LinkedHashMap();
            for (Object element$iv$iv3 : $this$groupBy$iv2) {
                CallableMemberDescriptor it3 = (CallableMemberDescriptor) element$iv$iv3;
                Boolean boolValueOf = Boolean.valueOf(it3 instanceof FunctionDescriptor);
                Object value$iv$iv$iv2 = destination$iv$iv4.get(boolValueOf);
                if (value$iv$iv$iv2 == null) {
                    ArrayList arrayList2 = new ArrayList();
                    destination$iv$iv4.put(boolValueOf, arrayList2);
                    obj = arrayList2;
                } else {
                    obj = value$iv$iv$iv2;
                }
                List list$iv$iv3 = (List) obj;
                list$iv$iv3.add(element$iv$iv3);
            }
            for (Map.Entry entry2 : destination$iv$iv4.entrySet()) {
                boolean isFunction = ((Boolean) entry2.getKey()).booleanValue();
                List descriptors = (List) entry2.getValue();
                OverridingUtil overridingUtil = OverridingUtil.DEFAULT;
                Name name3 = name2;
                List list2 = descriptors;
                if (isFunction) {
                    List<? extends FunctionDescriptor> $this$filter$iv = list;
                    Collection destination$iv$iv5 = new ArrayList();
                    for (Object element$iv$iv4 : $this$filter$iv) {
                        FunctionDescriptor it4 = (FunctionDescriptor) element$iv$iv4;
                        if (Intrinsics.areEqual(it4.getName(), name2)) {
                            destination$iv$iv5.add(element$iv$iv4);
                        }
                    }
                    ArrayList arrayList3 = (List) destination$iv$iv5;
                    overridingUtil = overridingUtil;
                    name3 = name3;
                    list2 = list2;
                    listEmptyList = arrayList3;
                } else {
                    listEmptyList = CollectionsKt.emptyList();
                }
                overridingUtil.generateOverridesInFunctionGroup(name3, list2, listEmptyList, this.containingClass, new NonReportingOverrideStrategy() { // from class: kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope.createFakeOverrides.4
                    @Override // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
                    public void addFakeOverride(@NotNull CallableMemberDescriptor fakeOverride) {
                        Intrinsics.checkNotNullParameter(fakeOverride, "fakeOverride");
                        OverridingUtil.resolveUnknownVisibilityForMember(fakeOverride, null);
                        result.add(fakeOverride);
                    }

                    @Override // kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy
                    protected void conflict(@NotNull CallableMemberDescriptor fromSuper, @NotNull CallableMemberDescriptor fromCurrent) {
                        Intrinsics.checkNotNullParameter(fromSuper, "fromSuper");
                        Intrinsics.checkNotNullParameter(fromCurrent, "fromCurrent");
                        throw new IllegalStateException(("Conflict in scope of " + this.getContainingClass() + ": " + fromSuper + " vs " + fromCurrent).toString());
                    }
                });
            }
        }
        return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(result);
    }
}
