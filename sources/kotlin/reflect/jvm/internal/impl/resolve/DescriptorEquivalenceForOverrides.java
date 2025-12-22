package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DescriptorEquivalenceForOverrides.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/DescriptorEquivalenceForOverrides.class */
public final class DescriptorEquivalenceForOverrides {

    @NotNull
    public static final DescriptorEquivalenceForOverrides INSTANCE = new DescriptorEquivalenceForOverrides();

    @JvmOverloads
    public final boolean areTypeParametersEquivalent(@NotNull TypeParameterDescriptor a, @NotNull TypeParameterDescriptor b, boolean allowCopiesFromTheSameDeclaration) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return areTypeParametersEquivalent$default(this, a, b, allowCopiesFromTheSameDeclaration, null, 8, null);
    }

    private DescriptorEquivalenceForOverrides() {
    }

    public static /* synthetic */ boolean areEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides, DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2, boolean z, boolean z2, int i, Object obj) {
        if ((i & 8) != 0) {
            z2 = true;
        }
        return descriptorEquivalenceForOverrides.areEquivalent(declarationDescriptor, declarationDescriptor2, z, z2);
    }

    public final boolean areEquivalent(@Nullable DeclarationDescriptor a, @Nullable DeclarationDescriptor b, boolean allowCopiesFromTheSameDeclaration, boolean distinguishExpectsAndNonExpects) {
        if ((a instanceof ClassDescriptor) && (b instanceof ClassDescriptor)) {
            return areClassesEquivalent((ClassDescriptor) a, (ClassDescriptor) b);
        }
        if ((a instanceof TypeParameterDescriptor) && (b instanceof TypeParameterDescriptor)) {
            return areTypeParametersEquivalent$default(this, (TypeParameterDescriptor) a, (TypeParameterDescriptor) b, allowCopiesFromTheSameDeclaration, null, 8, null);
        }
        if ((a instanceof CallableDescriptor) && (b instanceof CallableDescriptor)) {
            return areCallableDescriptorsEquivalent$default(this, (CallableDescriptor) a, (CallableDescriptor) b, allowCopiesFromTheSameDeclaration, distinguishExpectsAndNonExpects, false, KotlinTypeRefiner.Default.INSTANCE, 16, null);
        }
        return ((a instanceof PackageFragmentDescriptor) && (b instanceof PackageFragmentDescriptor)) ? Intrinsics.areEqual(((PackageFragmentDescriptor) a).getFqName(), ((PackageFragmentDescriptor) b).getFqName()) : Intrinsics.areEqual(a, b);
    }

    private final boolean areClassesEquivalent(ClassDescriptor a, ClassDescriptor b) {
        return Intrinsics.areEqual(a.getTypeConstructor(), b.getTypeConstructor());
    }

    public static /* synthetic */ boolean areTypeParametersEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides, TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 8) != 0) {
            function2 = new Function2<DeclarationDescriptor, DeclarationDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areTypeParametersEquivalent.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(@Nullable DeclarationDescriptor $noName_0, @Nullable DeclarationDescriptor $noName_1) {
                    return false;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Boolean invoke(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
                    return Boolean.valueOf(invoke2(declarationDescriptor, declarationDescriptor2));
                }
            };
        }
        return descriptorEquivalenceForOverrides.areTypeParametersEquivalent(typeParameterDescriptor, typeParameterDescriptor2, z, function2);
    }

    @JvmOverloads
    public final boolean areTypeParametersEquivalent(@NotNull TypeParameterDescriptor a, @NotNull TypeParameterDescriptor b, boolean allowCopiesFromTheSameDeclaration, @NotNull Function2<? super DeclarationDescriptor, ? super DeclarationDescriptor, Boolean> equivalentCallables) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        Intrinsics.checkNotNullParameter(equivalentCallables, "equivalentCallables");
        if (Intrinsics.areEqual(a, b)) {
            return true;
        }
        return !Intrinsics.areEqual(a.getContainingDeclaration(), b.getContainingDeclaration()) && ownersEquivalent(a, b, equivalentCallables, allowCopiesFromTheSameDeclaration) && a.getIndex() == b.getIndex();
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0029, code lost:
    
        return r6.getSource();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final kotlin.reflect.jvm.internal.impl.descriptors.SourceElement singleSource(kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor r4) {
        /*
            r3 = this;
            r0 = r3
            r5 = r0
            r0 = r4
            r6 = r0
        L4:
            r0 = r5
            r7 = r0
            r0 = r6
            r8 = r0
            r0 = r8
            boolean r0 = r0 instanceof kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
            if (r0 == 0) goto L22
            r0 = r8
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r0
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind r0 = r0.getKind()
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind r1 = kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind.FAKE_OVERRIDE
            if (r0 == r1) goto L2a
        L22:
            r0 = r8
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r0 = r0.getSource()
            return r0
        L2a:
            r0 = r8
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r0
            java.util.Collection r0 = r0.getOverriddenDescriptors()
            r10 = r0
            r0 = r10
            java.lang.String r1 = "overriddenDescriptors"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r0 = r10
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.lang.Object r0 = kotlin.collections.CollectionsKt.singleOrNull(r0)
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r0
            r9 = r0
            r0 = r9
            if (r0 != 0) goto L53
            r0 = 0
            goto L5f
        L53:
            r0 = r7
            r5 = r0
            r0 = r9
            kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor) r0
            r6 = r0
            goto L4
        L5f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.singleSource(kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor):kotlin.reflect.jvm.internal.impl.descriptors.SourceElement");
    }

    public static /* synthetic */ boolean areCallableDescriptorsEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides, CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, boolean z, boolean z2, boolean z3, KotlinTypeRefiner kotlinTypeRefiner, int i, Object obj) {
        if ((i & 8) != 0) {
            z2 = true;
        }
        if ((i & 16) != 0) {
            z3 = false;
        }
        return descriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent(callableDescriptor, callableDescriptor2, z, z2, z3, kotlinTypeRefiner);
    }

    public final boolean areCallableDescriptorsEquivalent(@NotNull final CallableDescriptor a, @NotNull final CallableDescriptor b, final boolean allowCopiesFromTheSameDeclaration, boolean distinguishExpectsAndNonExpects, boolean ignoreReturnType, @NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        if (Intrinsics.areEqual(a, b)) {
            return true;
        }
        if (!Intrinsics.areEqual(a.getName(), b.getName())) {
            return false;
        }
        if (distinguishExpectsAndNonExpects && (a instanceof MemberDescriptor) && (b instanceof MemberDescriptor) && ((MemberDescriptor) a).isExpect() != ((MemberDescriptor) b).isExpect()) {
            return false;
        }
        if ((Intrinsics.areEqual(a.getContainingDeclaration(), b.getContainingDeclaration()) && (!allowCopiesFromTheSameDeclaration || !Intrinsics.areEqual(singleSource(a), singleSource(b)))) || DescriptorUtils.isLocal(a) || DescriptorUtils.isLocal(b) || !ownersEquivalent(a, b, new Function2<DeclarationDescriptor, DeclarationDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent.1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(@Nullable DeclarationDescriptor $noName_0, @Nullable DeclarationDescriptor $noName_1) {
                return false;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Boolean invoke(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
                return Boolean.valueOf(invoke2(declarationDescriptor, declarationDescriptor2));
            }
        }, allowCopiesFromTheSameDeclaration)) {
            return false;
        }
        OverridingUtil overridingUtil = OverridingUtil.create(kotlinTypeRefiner, new KotlinTypeChecker.TypeConstructorEquality() { // from class: kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$overridingUtil$1
            @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker.TypeConstructorEquality
            public final boolean equals(@NotNull TypeConstructor c1, @NotNull TypeConstructor c2) {
                Intrinsics.checkNotNullParameter(c1, "c1");
                Intrinsics.checkNotNullParameter(c2, "c2");
                if (Intrinsics.areEqual(c1, c2)) {
                    return true;
                }
                ClassifierDescriptor d1 = c1.mo3831getDeclarationDescriptor();
                ClassifierDescriptor d2 = c2.mo3831getDeclarationDescriptor();
                if (!(d1 instanceof TypeParameterDescriptor) || !(d2 instanceof TypeParameterDescriptor)) {
                    return false;
                }
                boolean z = allowCopiesFromTheSameDeclaration;
                final CallableDescriptor callableDescriptor = a;
                final CallableDescriptor callableDescriptor2 = b;
                return DescriptorEquivalenceForOverrides.INSTANCE.areTypeParametersEquivalent((TypeParameterDescriptor) d1, (TypeParameterDescriptor) d2, z, new Function2<DeclarationDescriptor, DeclarationDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$overridingUtil$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final boolean invoke2(@Nullable DeclarationDescriptor x, @Nullable DeclarationDescriptor y) {
                        return Intrinsics.areEqual(x, callableDescriptor) && Intrinsics.areEqual(y, callableDescriptor2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Boolean invoke(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
                        return Boolean.valueOf(invoke2(declarationDescriptor, declarationDescriptor2));
                    }
                });
            }
        });
        Intrinsics.checkNotNullExpressionValue(overridingUtil, "a: CallableDescriptor,\n        b: CallableDescriptor,\n        allowCopiesFromTheSameDeclaration: Boolean,\n        distinguishExpectsAndNonExpects: Boolean = true,\n        ignoreReturnType: Boolean = false,\n        kotlinTypeRefiner: KotlinTypeRefiner\n    ): Boolean {\n        if (a == b) return true\n        if (a.name != b.name) return false\n        if (distinguishExpectsAndNonExpects && a is MemberDescriptor && b is MemberDescriptor && a.isExpect != b.isExpect) return false\n        if (a.containingDeclaration == b.containingDeclaration) {\n            if (!allowCopiesFromTheSameDeclaration) return false\n            if (a.singleSource() != b.singleSource()) return false\n        }\n\n        // Distinct locals are not equivalent\n        if (DescriptorUtils.isLocal(a) || DescriptorUtils.isLocal(b)) return false\n\n        if (!ownersEquivalent(a, b, { _, _ -> false }, allowCopiesFromTheSameDeclaration)) return false\n\n        val overridingUtil = OverridingUtil.create(kotlinTypeRefiner) eq@{ c1, c2 ->\n            if (c1 == c2) return@eq true\n\n            val d1 = c1.declarationDescriptor\n            val d2 = c2.declarationDescriptor\n\n            if (d1 !is TypeParameterDescriptor || d2 !is TypeParameterDescriptor) return@eq false\n\n            areTypeParametersEquivalent(d1, d2, allowCopiesFromTheSameDeclaration) { x, y -> x == a && y == b }\n        }");
        if (overridingUtil.isOverridableBy(a, b, null, !ignoreReturnType).getResult() == OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE) {
            if (overridingUtil.isOverridableBy(b, a, null, !ignoreReturnType).getResult() == OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE) {
                return true;
            }
        }
        return false;
    }

    private final boolean ownersEquivalent(DeclarationDescriptor a, DeclarationDescriptor b, Function2<? super DeclarationDescriptor, ? super DeclarationDescriptor, Boolean> function2, boolean allowCopiesFromTheSameDeclaration) {
        DeclarationDescriptor aOwner = a.getContainingDeclaration();
        DeclarationDescriptor bOwner = b.getContainingDeclaration();
        if ((aOwner instanceof CallableMemberDescriptor) || (bOwner instanceof CallableMemberDescriptor)) {
            return function2.invoke(aOwner, bOwner).booleanValue();
        }
        return areEquivalent$default(this, aOwner, bOwner, allowCopiesFromTheSameDeclaration, false, 8, null);
    }
}
