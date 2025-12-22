package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: NewCapturedType.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/NewCapturedTypeConstructor.class */
public final class NewCapturedTypeConstructor implements CapturedTypeConstructor {

    @NotNull
    private final TypeProjection projection;

    @Nullable
    private Function0<? extends List<? extends UnwrappedType>> supertypesComputation;

    @Nullable
    private final NewCapturedTypeConstructor original;

    @Nullable
    private final TypeParameterDescriptor typeParameter;

    @NotNull
    private final Lazy _supertypes$delegate;

    public NewCapturedTypeConstructor(@NotNull TypeProjection projection, @Nullable Function0<? extends List<? extends UnwrappedType>> function0, @Nullable NewCapturedTypeConstructor original, @Nullable TypeParameterDescriptor typeParameter) {
        Intrinsics.checkNotNullParameter(projection, "projection");
        this.projection = projection;
        this.supertypesComputation = function0;
        this.original = original;
        this.typeParameter = typeParameter;
        this._supertypes$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<List<? extends UnwrappedType>>() { // from class: kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedTypeConstructor$_supertypes$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @Nullable
            public final List<? extends UnwrappedType> invoke() {
                Function0 function02 = this.this$0.supertypesComputation;
                if (function02 == null) {
                    return null;
                }
                return (List) function02.invoke();
            }
        });
    }

    public /* synthetic */ NewCapturedTypeConstructor(TypeProjection typeProjection, Function0 function0, NewCapturedTypeConstructor newCapturedTypeConstructor, TypeParameterDescriptor typeParameterDescriptor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeProjection, (i & 2) != 0 ? null : function0, (i & 4) != 0 ? null : newCapturedTypeConstructor, (i & 8) != 0 ? null : typeParameterDescriptor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor
    @NotNull
    public TypeProjection getProjection() {
        return this.projection;
    }

    public /* synthetic */ NewCapturedTypeConstructor(TypeProjection typeProjection, List list, NewCapturedTypeConstructor newCapturedTypeConstructor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeProjection, list, (i & 4) != 0 ? null : newCapturedTypeConstructor);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NewCapturedTypeConstructor(@NotNull TypeProjection projection, @NotNull final List<? extends UnwrappedType> supertypes, @Nullable NewCapturedTypeConstructor original) {
        this(projection, new Function0<List<? extends UnwrappedType>>() { // from class: kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedTypeConstructor.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final List<? extends UnwrappedType> invoke() {
                return supertypes;
            }
        }, original, null, 8, null);
        Intrinsics.checkNotNullParameter(projection, "projection");
        Intrinsics.checkNotNullParameter(supertypes, "supertypes");
    }

    private final List<UnwrappedType> get_supertypes() {
        return (List) this._supertypes$delegate.getValue();
    }

    public final void initializeSupertypes(@NotNull final List<? extends UnwrappedType> supertypes) {
        Intrinsics.checkNotNullParameter(supertypes, "supertypes");
        boolean z = this.supertypesComputation == null;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Already initialized! oldValue = " + this.supertypesComputation + ", newValue = " + supertypes);
        }
        this.supertypesComputation = new Function0<List<? extends UnwrappedType>>() { // from class: kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedTypeConstructor.initializeSupertypes.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final List<? extends UnwrappedType> invoke() {
                return supertypes;
            }
        };
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: getSupertypes */
    public List<UnwrappedType> mo3835getSupertypes() {
        List<UnwrappedType> list = get_supertypes();
        return list == null ? CollectionsKt.emptyList() : list;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public List<TypeParameterDescriptor> getParameters() {
        return CollectionsKt.emptyList();
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
        KotlinType type = getProjection().getType();
        Intrinsics.checkNotNullExpressionValue(type, "projection.type");
        return TypeUtilsKt.getBuiltIns(type);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public NewCapturedTypeConstructor refine(@NotNull final KotlinTypeRefiner kotlinTypeRefiner) {
        Function0<List<? extends UnwrappedType>> function0;
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        TypeProjection typeProjectionRefine = getProjection().refine(kotlinTypeRefiner);
        Intrinsics.checkNotNullExpressionValue(typeProjectionRefine, "projection.refine(kotlinTypeRefiner)");
        TypeProjection typeProjection = typeProjectionRefine;
        if (this.supertypesComputation == null) {
            function0 = null;
        } else {
            typeProjection = typeProjection;
            function0 = new Function0<List<? extends UnwrappedType>>() { // from class: kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedTypeConstructor$refine$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final List<? extends UnwrappedType> invoke() {
                    Iterable $this$map$iv = this.this$0.mo3835getSupertypes();
                    KotlinTypeRefiner kotlinTypeRefiner2 = kotlinTypeRefiner;
                    Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                    for (Object item$iv$iv : $this$map$iv) {
                        UnwrappedType it = (UnwrappedType) item$iv$iv;
                        destination$iv$iv.add(it.refine(kotlinTypeRefiner2));
                    }
                    return (List) destination$iv$iv;
                }
            };
        }
        NewCapturedTypeConstructor newCapturedTypeConstructor = this.original;
        return new NewCapturedTypeConstructor(typeProjection, function0, newCapturedTypeConstructor == null ? this : newCapturedTypeConstructor, this.typeParameter);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other == null ? null : other.getClass())) {
            return false;
        }
        if (other == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.types.checker.NewCapturedTypeConstructor");
        }
        NewCapturedTypeConstructor newCapturedTypeConstructor = this.original;
        NewCapturedTypeConstructor newCapturedTypeConstructor2 = newCapturedTypeConstructor == null ? this : newCapturedTypeConstructor;
        NewCapturedTypeConstructor newCapturedTypeConstructor3 = ((NewCapturedTypeConstructor) other).original;
        return newCapturedTypeConstructor2 == (newCapturedTypeConstructor3 == null ? (NewCapturedTypeConstructor) other : newCapturedTypeConstructor3);
    }

    public int hashCode() {
        NewCapturedTypeConstructor newCapturedTypeConstructor = this.original;
        return newCapturedTypeConstructor == null ? super.hashCode() : newCapturedTypeConstructor.hashCode();
    }

    @NotNull
    public String toString() {
        return "CapturedType(" + getProjection() + ')';
    }
}
