package kotlin.reflect.jvm.internal.impl.types;

import cn.hutool.core.text.StrPool;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.model.IntersectionTypeConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: IntersectionTypeConstructor.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/IntersectionTypeConstructor.class */
public final class IntersectionTypeConstructor implements TypeConstructor, IntersectionTypeConstructorMarker {

    @Nullable
    private KotlinType alternative;

    @NotNull
    private final LinkedHashSet<KotlinType> intersectedTypes;
    private final int hashCode;

    public IntersectionTypeConstructor(@NotNull Collection<? extends KotlinType> typesToIntersect) {
        Intrinsics.checkNotNullParameter(typesToIntersect, "typesToIntersect");
        boolean z = !typesToIntersect.isEmpty();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Attempt to create an empty intersection");
        }
        this.intersectedTypes = new LinkedHashSet<>(typesToIntersect);
        this.hashCode = this.intersectedTypes.hashCode();
    }

    private IntersectionTypeConstructor(Collection<? extends KotlinType> collection, KotlinType alternative) {
        this(collection);
        this.alternative = alternative;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public List<TypeParameterDescriptor> getParameters() {
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: getSupertypes */
    public Collection<KotlinType> mo3835getSupertypes() {
        return this.intersectedTypes;
    }

    @NotNull
    public final MemberScope createScopeForKotlinType() {
        return TypeIntersectionScope.Companion.create("member scope for intersection type", this.intersectedTypes);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public boolean isDenotable() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @Nullable
    /* renamed from: getDeclarationDescriptor */
    public ClassifierDescriptor mo3831getDeclarationDescriptor() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public KotlinBuiltIns getBuiltIns() {
        KotlinBuiltIns builtIns = this.intersectedTypes.iterator().next().getConstructor().getBuiltIns();
        Intrinsics.checkNotNullExpressionValue(builtIns, "intersectedTypes.iterator().next().constructor.builtIns");
        return builtIns;
    }

    @NotNull
    public String toString() {
        return makeDebugNameForIntersectionType(this.intersectedTypes);
    }

    private final String makeDebugNameForIntersectionType(Iterable<? extends KotlinType> iterable) {
        return CollectionsKt.joinToString$default(CollectionsKt.sortedWith(iterable, new Comparator<T>() { // from class: kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor$makeDebugNameForIntersectionType$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                KotlinType it = (KotlinType) t;
                KotlinType it2 = (KotlinType) t2;
                return ComparisonsKt.compareValues(it.toString(), it2.toString());
            }
        }), " & ", StrPool.DELIM_START, "}", 0, null, null, 56, null);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof IntersectionTypeConstructor) {
            return Intrinsics.areEqual(this.intersectedTypes, ((IntersectionTypeConstructor) other).intersectedTypes);
        }
        return false;
    }

    @NotNull
    public final SimpleType createType() {
        KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
        return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(Annotations.Companion.getEMPTY(), this, CollectionsKt.emptyList(), false, createScopeForKotlinType(), new Function1<KotlinTypeRefiner, SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor.createType.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final SimpleType invoke(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
                Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
                return IntersectionTypeConstructor.this.refine(kotlinTypeRefiner).createType();
            }
        });
    }

    public int hashCode() {
        return this.hashCode;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public IntersectionTypeConstructor refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        IntersectionTypeConstructor alternative;
        KotlinType kotlinTypeRefine;
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        boolean changed$iv = false;
        Iterable $this$map$iv$iv = mo3835getSupertypes();
        Collection destination$iv$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv, 10));
        for (Object item$iv$iv$iv : $this$map$iv$iv) {
            KotlinType it$iv = (KotlinType) item$iv$iv$iv;
            if (1 != 0) {
                changed$iv = true;
                kotlinTypeRefine = it$iv.refine(kotlinTypeRefiner);
            } else {
                kotlinTypeRefine = it$iv;
            }
            destination$iv$iv$iv.add(kotlinTypeRefine);
        }
        List newSupertypes$iv = (List) destination$iv$iv$iv;
        if (changed$iv) {
            KotlinType alternative$iv = getAlternativeType();
            KotlinType updatedAlternative$iv = alternative$iv == null ? null : 1 != 0 ? alternative$iv.refine(kotlinTypeRefiner) : alternative$iv;
            alternative = new IntersectionTypeConstructor(newSupertypes$iv).setAlternative(updatedAlternative$iv);
        } else {
            alternative = null;
        }
        IntersectionTypeConstructor intersectionTypeConstructor = alternative;
        return intersectionTypeConstructor == null ? this : intersectionTypeConstructor;
    }

    @NotNull
    public final IntersectionTypeConstructor setAlternative(@Nullable KotlinType alternative) {
        return new IntersectionTypeConstructor(this.intersectedTypes, alternative);
    }

    @Nullable
    public final KotlinType getAlternativeType() {
        return this.alternative;
    }
}
