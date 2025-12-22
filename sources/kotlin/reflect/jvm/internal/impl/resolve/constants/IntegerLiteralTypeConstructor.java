package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: IntegerLiteralTypeConstructor.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/constants/IntegerLiteralTypeConstructor.class */
public final class IntegerLiteralTypeConstructor implements TypeConstructor {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private final long value;

    @NotNull
    private final ModuleDescriptor module;

    @NotNull
    private final Set<KotlinType> possibleTypes;

    @NotNull
    private final SimpleType type;

    @NotNull
    private final Lazy supertypes$delegate;

    public /* synthetic */ IntegerLiteralTypeConstructor(long value, ModuleDescriptor module, Set possibleTypes, DefaultConstructorMarker $constructor_marker) {
        this(value, module, possibleTypes);
    }

    /* compiled from: IntegerLiteralTypeConstructor.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/constants/IntegerLiteralTypeConstructor$Companion.class */
    public static final class Companion {

        /* compiled from: IntegerLiteralTypeConstructor.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/constants/IntegerLiteralTypeConstructor$Companion$Mode.class */
        private enum Mode {
            COMMON_SUPER_TYPE,
            INTERSECTION_TYPE
        }

        /* compiled from: IntegerLiteralTypeConstructor.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/constants/IntegerLiteralTypeConstructor$Companion$WhenMappings.class */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Mode.values().length];
                iArr[Mode.COMMON_SUPER_TYPE.ordinal()] = 1;
                iArr[Mode.INTERSECTION_TYPE.ordinal()] = 2;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final SimpleType findIntersectionType(@NotNull Collection<? extends SimpleType> types) {
            Intrinsics.checkNotNullParameter(types, "types");
            return findCommonSuperTypeOrIntersectionType(types, Mode.INTERSECTION_TYPE);
        }

        private final SimpleType findCommonSuperTypeOrIntersectionType(Collection<? extends SimpleType> collection, Mode mode) {
            if (collection.isEmpty()) {
                return null;
            }
            Collection<? extends SimpleType> $this$reduce$iv = collection;
            Iterator iterator$iv = $this$reduce$iv.iterator();
            if (!iterator$iv.hasNext()) {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
            Object next = iterator$iv.next();
            while (true) {
                Object accumulator$iv = next;
                if (iterator$iv.hasNext()) {
                    SimpleType right = (SimpleType) iterator$iv.next();
                    SimpleType left = (SimpleType) accumulator$iv;
                    next = IntegerLiteralTypeConstructor.Companion.fold(left, right, mode);
                } else {
                    return (SimpleType) accumulator$iv;
                }
            }
        }

        private final SimpleType fold(SimpleType left, SimpleType right, Mode mode) {
            if (left == null || right == null) {
                return null;
            }
            TypeConstructor leftConstructor = left.getConstructor();
            TypeConstructor rightConstructor = right.getConstructor();
            if ((leftConstructor instanceof IntegerLiteralTypeConstructor) && (rightConstructor instanceof IntegerLiteralTypeConstructor)) {
                return fold((IntegerLiteralTypeConstructor) leftConstructor, (IntegerLiteralTypeConstructor) rightConstructor, mode);
            }
            if (leftConstructor instanceof IntegerLiteralTypeConstructor) {
                return fold((IntegerLiteralTypeConstructor) leftConstructor, right);
            }
            if (rightConstructor instanceof IntegerLiteralTypeConstructor) {
                return fold((IntegerLiteralTypeConstructor) rightConstructor, left);
            }
            return null;
        }

        private final SimpleType fold(IntegerLiteralTypeConstructor left, IntegerLiteralTypeConstructor right, Mode mode) {
            Set setUnion;
            switch (WhenMappings.$EnumSwitchMapping$0[mode.ordinal()]) {
                case 1:
                    setUnion = CollectionsKt.intersect(left.getPossibleTypes(), right.getPossibleTypes());
                    break;
                case 2:
                    setUnion = CollectionsKt.union(left.getPossibleTypes(), right.getPossibleTypes());
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            Set possibleTypes = setUnion;
            IntegerLiteralTypeConstructor constructor = new IntegerLiteralTypeConstructor(left.value, left.module, possibleTypes, null);
            KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
            return KotlinTypeFactory.integerLiteralType(Annotations.Companion.getEMPTY(), constructor, false);
        }

        private final SimpleType fold(IntegerLiteralTypeConstructor left, SimpleType right) {
            if (left.getPossibleTypes().contains(right)) {
                return right;
            }
            return null;
        }
    }

    @NotNull
    public final Set<KotlinType> getPossibleTypes() {
        return this.possibleTypes;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private IntegerLiteralTypeConstructor(long value, ModuleDescriptor module, Set<? extends KotlinType> set) {
        KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
        this.type = KotlinTypeFactory.integerLiteralType(Annotations.Companion.getEMPTY(), this, false);
        this.supertypes$delegate = LazyKt.lazy(new Function0<List<SimpleType>>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor$supertypes$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final List<SimpleType> invoke() {
                SimpleType defaultType = this.this$0.getBuiltIns().getComparable().getDefaultType();
                Intrinsics.checkNotNullExpressionValue(defaultType, "builtIns.comparable.defaultType");
                List result = CollectionsKt.mutableListOf(TypeSubstitutionKt.replace$default(defaultType, CollectionsKt.listOf(new TypeProjectionImpl(Variance.IN_VARIANCE, this.this$0.type)), null, 2, null));
                if (!this.this$0.isContainsOnlyUnsignedTypes()) {
                    result.add(this.this$0.getBuiltIns().getNumberType());
                }
                return result;
            }
        });
        this.value = value;
        this.module = module;
        this.possibleTypes = set;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isContainsOnlyUnsignedTypes() {
        Iterable $this$all$iv = PrimitiveTypeUtilKt.getAllSignedLiteralTypes(this.module);
        if (($this$all$iv instanceof Collection) && ((Collection) $this$all$iv).isEmpty()) {
            return true;
        }
        for (Object element$iv : $this$all$iv) {
            KotlinType it = (KotlinType) element$iv;
            if (!(!getPossibleTypes().contains(it))) {
                return false;
            }
        }
        return true;
    }

    private final List<KotlinType> getSupertypes() {
        return (List) this.supertypes$delegate.getValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public List<TypeParameterDescriptor> getParameters() {
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: getSupertypes, reason: collision with other method in class */
    public Collection<KotlinType> mo3835getSupertypes() {
        return getSupertypes();
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
        return this.module.getBuiltIns();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public TypeConstructor refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return this;
    }

    @NotNull
    public String toString() {
        return Intrinsics.stringPlus("IntegerLiteralType", valueToString());
    }

    private final String valueToString() {
        return '[' + CollectionsKt.joinToString$default(this.possibleTypes, ",", null, null, 0, null, new Function1<KotlinType, CharSequence>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor.valueToString.1
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final CharSequence invoke(@NotNull KotlinType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.toString();
            }
        }, 30, null) + ']';
    }
}
