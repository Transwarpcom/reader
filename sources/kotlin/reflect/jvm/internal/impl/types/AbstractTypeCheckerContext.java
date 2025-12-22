package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.Set;
import kotlin._Assertions;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AbstractTypeChecker.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/AbstractTypeCheckerContext.class */
public abstract class AbstractTypeCheckerContext {
    private int argumentsDepth;
    private boolean supertypesLocked;

    @Nullable
    private ArrayDeque<SimpleTypeMarker> supertypesDeque;

    @Nullable
    private Set<SimpleTypeMarker> supertypesSet;

    /* compiled from: AbstractTypeChecker.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/AbstractTypeCheckerContext$LowerCapturedTypePolicy.class */
    public enum LowerCapturedTypePolicy {
        CHECK_ONLY_LOWER,
        CHECK_SUBTYPE_AND_LOWER,
        SKIP_LOWER
    }

    @NotNull
    public abstract TypeSystemContext getTypeSystemContext();

    @NotNull
    public abstract SupertypesPolicy substitutionSupertypePolicy(@NotNull SimpleTypeMarker simpleTypeMarker);

    public abstract boolean isErrorTypeEqualsToAnything();

    public abstract boolean isStubTypeEqualsToAnything();

    public abstract boolean isAllowedTypeVariable(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @NotNull
    public KotlinTypeMarker refineType(@NotNull KotlinTypeMarker type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return type;
    }

    @NotNull
    public KotlinTypeMarker prepareType(@NotNull KotlinTypeMarker type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return type;
    }

    public boolean customIsSubtypeOf(@NotNull KotlinTypeMarker subType, @NotNull KotlinTypeMarker superType) {
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superType, "superType");
        return true;
    }

    @NotNull
    public LowerCapturedTypePolicy getLowerCapturedTypePolicy(@NotNull SimpleTypeMarker subType, @NotNull CapturedTypeMarker superType) {
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superType, "superType");
        return LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER;
    }

    public static /* synthetic */ Boolean addSubtypeConstraint$default(AbstractTypeCheckerContext abstractTypeCheckerContext, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addSubtypeConstraint");
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return abstractTypeCheckerContext.addSubtypeConstraint(kotlinTypeMarker, kotlinTypeMarker2, z);
    }

    @Nullable
    public Boolean addSubtypeConstraint(@NotNull KotlinTypeMarker subType, @NotNull KotlinTypeMarker superType, boolean isFromNullabilityConstraint) {
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superType, "superType");
        return null;
    }

    @Nullable
    public final ArrayDeque<SimpleTypeMarker> getSupertypesDeque() {
        return this.supertypesDeque;
    }

    @Nullable
    public final Set<SimpleTypeMarker> getSupertypesSet() {
        return this.supertypesSet;
    }

    public final void initialize() {
        boolean z = !this.supertypesLocked;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError(Intrinsics.stringPlus("Supertypes were locked for ", Reflection.getOrCreateKotlinClass(getClass())));
        }
        this.supertypesLocked = true;
        if (this.supertypesDeque == null) {
            this.supertypesDeque = new ArrayDeque<>(4);
        }
        if (this.supertypesSet == null) {
            this.supertypesSet = SmartSet.Companion.create();
        }
    }

    public final void clear() {
        ArrayDeque<SimpleTypeMarker> arrayDeque = this.supertypesDeque;
        Intrinsics.checkNotNull(arrayDeque);
        arrayDeque.clear();
        Set<SimpleTypeMarker> set = this.supertypesSet;
        Intrinsics.checkNotNull(set);
        set.clear();
        this.supertypesLocked = false;
    }

    /* compiled from: AbstractTypeChecker.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/AbstractTypeCheckerContext$SupertypesPolicy.class */
    public static abstract class SupertypesPolicy {
        @NotNull
        /* renamed from: transformType */
        public abstract SimpleTypeMarker mo3909transformType(@NotNull AbstractTypeCheckerContext abstractTypeCheckerContext, @NotNull KotlinTypeMarker kotlinTypeMarker);

        public /* synthetic */ SupertypesPolicy(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private SupertypesPolicy() {
        }

        /* compiled from: AbstractTypeChecker.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/AbstractTypeCheckerContext$SupertypesPolicy$None.class */
        public static final class None extends SupertypesPolicy {

            @NotNull
            public static final None INSTANCE = new None();

            private None() {
                super(null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext.SupertypesPolicy
            /* renamed from: transformType, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ SimpleTypeMarker mo3909transformType(AbstractTypeCheckerContext context, KotlinTypeMarker type) {
                return (SimpleTypeMarker) transformType(context, type);
            }

            @NotNull
            public Void transformType(@NotNull AbstractTypeCheckerContext context, @NotNull KotlinTypeMarker type) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(type, "type");
                throw new UnsupportedOperationException("Should not be called");
            }
        }

        /* compiled from: AbstractTypeChecker.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/AbstractTypeCheckerContext$SupertypesPolicy$UpperIfFlexible.class */
        public static final class UpperIfFlexible extends SupertypesPolicy {

            @NotNull
            public static final UpperIfFlexible INSTANCE = new UpperIfFlexible();

            private UpperIfFlexible() {
                super(null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext.SupertypesPolicy
            @NotNull
            /* renamed from: transformType */
            public SimpleTypeMarker mo3909transformType(@NotNull AbstractTypeCheckerContext context, @NotNull KotlinTypeMarker type) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(type, "type");
                TypeSystemContext $this$transformType_u24lambda_u2d0 = context.getTypeSystemContext();
                return $this$transformType_u24lambda_u2d0.upperBoundIfFlexible(type);
            }
        }

        /* compiled from: AbstractTypeChecker.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/AbstractTypeCheckerContext$SupertypesPolicy$LowerIfFlexible.class */
        public static final class LowerIfFlexible extends SupertypesPolicy {

            @NotNull
            public static final LowerIfFlexible INSTANCE = new LowerIfFlexible();

            private LowerIfFlexible() {
                super(null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext.SupertypesPolicy
            @NotNull
            /* renamed from: transformType */
            public SimpleTypeMarker mo3909transformType(@NotNull AbstractTypeCheckerContext context, @NotNull KotlinTypeMarker type) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(type, "type");
                TypeSystemContext $this$transformType_u24lambda_u2d0 = context.getTypeSystemContext();
                return $this$transformType_u24lambda_u2d0.lowerBoundIfFlexible(type);
            }
        }

        /* compiled from: AbstractTypeChecker.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/AbstractTypeCheckerContext$SupertypesPolicy$DoCustomTransform.class */
        public static abstract class DoCustomTransform extends SupertypesPolicy {
            public DoCustomTransform() {
                super(null);
            }
        }
    }

    @JvmName(name = "isAllowedTypeVariableBridge")
    public final boolean isAllowedTypeVariableBridge(@NotNull KotlinTypeMarker type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return isAllowedTypeVariable(type);
    }
}
