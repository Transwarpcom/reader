package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import org.jetbrains.annotations.NotNull;

/* compiled from: IntersectionType.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/TypeIntersector.class */
public final class TypeIntersector {

    @NotNull
    public static final TypeIntersector INSTANCE = new TypeIntersector();

    private TypeIntersector() {
    }

    @NotNull
    public final SimpleType intersectTypes$descriptors(@NotNull List<? extends SimpleType> types) {
        SimpleType simpleTypeMakeSimpleTypeDefinitelyNotNullOrNotNull$default;
        Intrinsics.checkNotNullParameter(types, "types");
        boolean z = types.size() > 1;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError(Intrinsics.stringPlus("Size should be at least 2, but it is ", Integer.valueOf(types.size())));
        }
        ArrayList inputTypes = new ArrayList();
        for (SimpleType type : types) {
            if (!(type.getConstructor() instanceof IntersectionTypeConstructor)) {
                inputTypes.add(type);
            } else {
                Iterable iterableMo3835getSupertypes = type.getConstructor().mo3835getSupertypes();
                Intrinsics.checkNotNullExpressionValue(iterableMo3835getSupertypes, "type.constructor.supertypes");
                Iterable $this$map$iv = iterableMo3835getSupertypes;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    KotlinType it = (KotlinType) item$iv$iv;
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    SimpleType it2 = FlexibleTypesKt.upperIfFlexible(it);
                    destination$iv$iv.add(type.isMarkedNullable() ? it2.makeNullableAsSpecified(true) : it2);
                }
                inputTypes.addAll((List) destination$iv$iv);
            }
        }
        ArrayList $this$fold$iv = inputTypes;
        ResultNullability resultNullabilityCombine = ResultNullability.START;
        for (Object element$iv : $this$fold$iv) {
            ResultNullability p0 = resultNullabilityCombine;
            UnwrappedType p1 = (UnwrappedType) element$iv;
            resultNullabilityCombine = p0.combine(p1);
        }
        ResultNullability resultNullability = resultNullabilityCombine;
        ArrayList $this$mapTo$iv = inputTypes;
        Collection destination$iv = new LinkedHashSet();
        for (Object item$iv : $this$mapTo$iv) {
            SimpleType it3 = (SimpleType) item$iv;
            if (resultNullability == ResultNullability.NOT_NULL) {
                simpleTypeMakeSimpleTypeDefinitelyNotNullOrNotNull$default = SpecialTypesKt.makeSimpleTypeDefinitelyNotNullOrNotNull$default(it3 instanceof NewCapturedType ? SpecialTypesKt.withNotNullProjection((NewCapturedType) it3) : it3, false, 1, null);
            } else {
                simpleTypeMakeSimpleTypeDefinitelyNotNullOrNotNull$default = it3;
            }
            destination$iv.add(simpleTypeMakeSimpleTypeDefinitelyNotNullOrNotNull$default);
        }
        LinkedHashSet correctNullability = (LinkedHashSet) destination$iv;
        return intersectTypesWithoutIntersectionType(correctNullability);
    }

    private final SimpleType intersectTypesWithoutIntersectionType(final Set<? extends SimpleType> set) {
        if (set.size() == 1) {
            return (SimpleType) CollectionsKt.single(set);
        }
        Function0 errorMessage = new Function0<String>() { // from class: kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector$intersectTypesWithoutIntersectionType$errorMessage$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final String invoke() {
                return Intrinsics.stringPlus("This collections cannot be empty! input types: ", CollectionsKt.joinToString$default(set, null, null, null, 0, null, null, 63, null));
            }
        };
        Collection filteredEqualTypes = filterTypes(set, new TypeIntersector$intersectTypesWithoutIntersectionType$filteredEqualTypes$1(this));
        boolean z = !filteredEqualTypes.isEmpty();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError(errorMessage.invoke());
        }
        SimpleType it = IntegerLiteralTypeConstructor.Companion.findIntersectionType(filteredEqualTypes);
        if (it != null) {
            return it;
        }
        Collection filteredSuperAndEqualTypes = filterTypes(filteredEqualTypes, new TypeIntersector$intersectTypesWithoutIntersectionType$filteredSuperAndEqualTypes$1(NewKotlinTypeChecker.Companion.getDefault()));
        boolean z2 = !filteredSuperAndEqualTypes.isEmpty();
        if (!_Assertions.ENABLED || z2) {
            return filteredSuperAndEqualTypes.size() < 2 ? (SimpleType) CollectionsKt.single(filteredSuperAndEqualTypes) : new IntersectionTypeConstructor(set).createType();
        }
        throw new AssertionError(errorMessage.invoke());
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.util.Collection<kotlin.reflect.jvm.internal.impl.types.SimpleType> filterTypes(java.util.Collection<? extends kotlin.reflect.jvm.internal.impl.types.SimpleType> r6, kotlin.jvm.functions.Function2<? super kotlin.reflect.jvm.internal.impl.types.SimpleType, ? super kotlin.reflect.jvm.internal.impl.types.SimpleType, java.lang.Boolean> r7) {
        /*
            r5 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = r0
            r2 = r6
            r1.<init>(r2)
            r8 = r0
            r0 = r8
            java.util.Iterator r0 = r0.iterator()
            r10 = r0
            r0 = r10
            java.lang.String r1 = "filteredTypes.iterator()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r0 = r10
            r9 = r0
        L1a:
            r0 = r9
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto Lbe
            r0 = r9
            java.lang.Object r0 = r0.next()
            kotlin.reflect.jvm.internal.impl.types.SimpleType r0 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r0
            r10 = r0
            r0 = r8
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            r12 = r0
            r0 = 0
            r13 = r0
            r0 = r12
            boolean r0 = r0 instanceof java.util.Collection
            if (r0 == 0) goto L52
            r0 = r12
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L52
            r0 = 0
            goto Lad
        L52:
            r0 = r12
            java.util.Iterator r0 = r0.iterator()
            r14 = r0
        L5b:
            r0 = r14
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto Lac
            r0 = r14
            java.lang.Object r0 = r0.next()
            r15 = r0
            r0 = r15
            kotlin.reflect.jvm.internal.impl.types.SimpleType r0 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r0
            r16 = r0
            r0 = 0
            r17 = r0
            r0 = r16
            r1 = r10
            if (r0 == r1) goto La4
            r0 = r7
            r1 = r16
            java.lang.String r2 = "lower"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r1 = r16
            r2 = r10
            java.lang.String r3 = "upper"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r2 = r10
            java.lang.Object r0 = r0.invoke(r1, r2)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto La4
            r0 = 1
            goto La5
        La4:
            r0 = 0
        La5:
            if (r0 == 0) goto L5b
            r0 = 1
            goto Lad
        Lac:
            r0 = 0
        Lad:
            r11 = r0
            r0 = r11
            if (r0 == 0) goto L1a
            r0 = r9
            r0.remove()
            goto L1a
        Lbe:
            r0 = r8
            java.util.Collection r0 = (java.util.Collection) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.filterTypes(java.util.Collection, kotlin.jvm.functions.Function2):java.util.Collection");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isStrictSupertype(KotlinType subtype, KotlinType supertype) {
        NewKotlinTypeCheckerImpl $this$isStrictSupertype_u24lambda_u2d6 = NewKotlinTypeChecker.Companion.getDefault();
        return $this$isStrictSupertype_u24lambda_u2d6.isSubtypeOf(subtype, supertype) && !$this$isStrictSupertype_u24lambda_u2d6.isSubtypeOf(supertype, subtype);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: IntersectionType.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/TypeIntersector$ResultNullability.class */
    private static final class ResultNullability {
        public static final ResultNullability START = new START("START", 0);
        public static final ResultNullability ACCEPT_NULL = new ACCEPT_NULL("ACCEPT_NULL", 1);
        public static final ResultNullability UNKNOWN = new UNKNOWN("UNKNOWN", 2);
        public static final ResultNullability NOT_NULL = new NOT_NULL("NOT_NULL", 3);
        private static final /* synthetic */ ResultNullability[] $VALUES = $values();

        @NotNull
        public abstract ResultNullability combine(@NotNull UnwrappedType unwrappedType);

        public static ResultNullability[] values() {
            return (ResultNullability[]) $VALUES.clone();
        }

        public static ResultNullability valueOf(String value) {
            return (ResultNullability) Enum.valueOf(ResultNullability.class, value);
        }

        private static final /* synthetic */ ResultNullability[] $values() {
            return new ResultNullability[]{START, ACCEPT_NULL, UNKNOWN, NOT_NULL};
        }

        public /* synthetic */ ResultNullability(String $enum$name, int $enum$ordinal, DefaultConstructorMarker $constructor_marker) {
            this($enum$name, $enum$ordinal);
        }

        /* compiled from: IntersectionType.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/TypeIntersector$ResultNullability$START.class */
        static final class START extends ResultNullability {
            START(String $enum$name, int $enum$ordinal) {
                super($enum$name, $enum$ordinal, null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            @NotNull
            public ResultNullability combine(@NotNull UnwrappedType nextType) {
                Intrinsics.checkNotNullParameter(nextType, "nextType");
                return getResultNullability(nextType);
            }
        }

        private ResultNullability(String $enum$name, int $enum$ordinal) {
        }

        /* compiled from: IntersectionType.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/TypeIntersector$ResultNullability$ACCEPT_NULL.class */
        static final class ACCEPT_NULL extends ResultNullability {
            ACCEPT_NULL(String $enum$name, int $enum$ordinal) {
                super($enum$name, $enum$ordinal, null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            @NotNull
            public ResultNullability combine(@NotNull UnwrappedType nextType) {
                Intrinsics.checkNotNullParameter(nextType, "nextType");
                return getResultNullability(nextType);
            }
        }

        /* compiled from: IntersectionType.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/TypeIntersector$ResultNullability$UNKNOWN.class */
        static final class UNKNOWN extends ResultNullability {
            UNKNOWN(String $enum$name, int $enum$ordinal) {
                super($enum$name, $enum$ordinal, null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            @NotNull
            public ResultNullability combine(@NotNull UnwrappedType nextType) {
                Intrinsics.checkNotNullParameter(nextType, "nextType");
                ResultNullability it = getResultNullability(nextType);
                return it == ResultNullability.ACCEPT_NULL ? this : it;
            }
        }

        /* compiled from: IntersectionType.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/TypeIntersector$ResultNullability$NOT_NULL.class */
        static final class NOT_NULL extends ResultNullability {
            NOT_NULL(String $enum$name, int $enum$ordinal) {
                super($enum$name, $enum$ordinal, null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            @NotNull
            public NOT_NULL combine(@NotNull UnwrappedType nextType) {
                Intrinsics.checkNotNullParameter(nextType, "nextType");
                return this;
            }
        }

        @NotNull
        protected final ResultNullability getResultNullability(@NotNull UnwrappedType $this$resultNullability) {
            Intrinsics.checkNotNullParameter($this$resultNullability, "<this>");
            return $this$resultNullability.isMarkedNullable() ? ACCEPT_NULL : NullabilityChecker.INSTANCE.isSubtypeOfAny($this$resultNullability) ? NOT_NULL : UNKNOWN;
        }
    }
}
