package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypePreparator;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import org.jetbrains.annotations.NotNull;

/* compiled from: ClassicTypeCheckerContext.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/ClassicTypeCheckerContext.class */
public class ClassicTypeCheckerContext extends AbstractTypeCheckerContext {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private final boolean errorTypeEqualsToAnything;
    private final boolean stubTypeEqualsToAnything;
    private final boolean allowedTypeVariable;

    @NotNull
    private final KotlinTypeRefiner kotlinTypeRefiner;

    @NotNull
    private final KotlinTypePreparator kotlinTypePreparator;

    @NotNull
    private final ClassicTypeSystemContext typeSystemContext;

    public /* synthetic */ ClassicTypeCheckerContext(boolean z, boolean z2, boolean z3, KotlinTypeRefiner kotlinTypeRefiner, KotlinTypePreparator kotlinTypePreparator, ClassicTypeSystemContext classicTypeSystemContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? true : z3, (i & 8) != 0 ? KotlinTypeRefiner.Default.INSTANCE : kotlinTypeRefiner, (i & 16) != 0 ? KotlinTypePreparator.Default.INSTANCE : kotlinTypePreparator, (i & 32) != 0 ? SimpleClassicTypeSystemContext.INSTANCE : classicTypeSystemContext);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    @NotNull
    public ClassicTypeSystemContext getTypeSystemContext() {
        return this.typeSystemContext;
    }

    public ClassicTypeCheckerContext(boolean errorTypeEqualsToAnything, boolean stubTypeEqualsToAnything, boolean allowedTypeVariable, @NotNull KotlinTypeRefiner kotlinTypeRefiner, @NotNull KotlinTypePreparator kotlinTypePreparator, @NotNull ClassicTypeSystemContext typeSystemContext) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        Intrinsics.checkNotNullParameter(kotlinTypePreparator, "kotlinTypePreparator");
        Intrinsics.checkNotNullParameter(typeSystemContext, "typeSystemContext");
        this.errorTypeEqualsToAnything = errorTypeEqualsToAnything;
        this.stubTypeEqualsToAnything = stubTypeEqualsToAnything;
        this.allowedTypeVariable = allowedTypeVariable;
        this.kotlinTypeRefiner = kotlinTypeRefiner;
        this.kotlinTypePreparator = kotlinTypePreparator;
        this.typeSystemContext = typeSystemContext;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    @NotNull
    public KotlinTypeMarker refineType(@NotNull KotlinTypeMarker type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (!(type instanceof KotlinType)) {
            throw new IllegalArgumentException(ClassicTypeCheckerContextKt.errorMessage(type).toString());
        }
        return this.kotlinTypeRefiner.refineType((KotlinType) type);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    @NotNull
    public KotlinTypeMarker prepareType(@NotNull KotlinTypeMarker type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (!(type instanceof KotlinType)) {
            throw new IllegalArgumentException(ClassicTypeCheckerContextKt.errorMessage(type).toString());
        }
        return this.kotlinTypePreparator.prepareType(((KotlinType) type).unwrap());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isErrorTypeEqualsToAnything() {
        return this.errorTypeEqualsToAnything;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isStubTypeEqualsToAnything() {
        return this.stubTypeEqualsToAnything;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    @NotNull
    public AbstractTypeCheckerContext.SupertypesPolicy.DoCustomTransform substitutionSupertypePolicy(@NotNull SimpleTypeMarker type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return Companion.classicSubstitutionSupertypePolicy(getTypeSystemContext(), type);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isAllowedTypeVariable(@NotNull KotlinTypeMarker $this$isAllowedTypeVariable) {
        Intrinsics.checkNotNullParameter($this$isAllowedTypeVariable, "<this>");
        return ($this$isAllowedTypeVariable instanceof UnwrappedType) && this.allowedTypeVariable && (((UnwrappedType) $this$isAllowedTypeVariable).getConstructor() instanceof NewTypeVariableConstructor);
    }

    /* compiled from: ClassicTypeCheckerContext.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/ClassicTypeCheckerContext$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NotNull
        public final AbstractTypeCheckerContext.SupertypesPolicy.DoCustomTransform classicSubstitutionSupertypePolicy(@NotNull final ClassicTypeSystemContext $this$classicSubstitutionSupertypePolicy, @NotNull SimpleTypeMarker type) {
            Intrinsics.checkNotNullParameter($this$classicSubstitutionSupertypePolicy, "<this>");
            Intrinsics.checkNotNullParameter(type, "type");
            if (!(type instanceof SimpleType)) {
                throw new IllegalArgumentException(ClassicTypeCheckerContextKt.errorMessage(type).toString());
            }
            final TypeSubstitutor substitutor = TypeConstructorSubstitution.Companion.create((KotlinType) type).buildSubstitutor();
            return new AbstractTypeCheckerContext.SupertypesPolicy.DoCustomTransform() { // from class: kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeCheckerContext$Companion$classicSubstitutionSupertypePolicy$2
                @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext.SupertypesPolicy
                @NotNull
                /* renamed from: transformType */
                public SimpleTypeMarker mo3909transformType(@NotNull AbstractTypeCheckerContext context, @NotNull KotlinTypeMarker type2) {
                    Intrinsics.checkNotNullParameter(context, "context");
                    Intrinsics.checkNotNullParameter(type2, "type");
                    ClassicTypeSystemContext classicTypeSystemContext = $this$classicSubstitutionSupertypePolicy;
                    KotlinType kotlinTypeSafeSubstitute = substitutor.safeSubstitute((KotlinType) $this$classicSubstitutionSupertypePolicy.lowerBoundIfFlexible(type2), Variance.INVARIANT);
                    Intrinsics.checkNotNullExpressionValue(kotlinTypeSafeSubstitute, "substitutor.safeSubstitute(\n                        type.lowerBoundIfFlexible() as KotlinType,\n                        Variance.INVARIANT\n                    )");
                    SimpleTypeMarker simpleTypeMarkerAsSimpleType = classicTypeSystemContext.asSimpleType(kotlinTypeSafeSubstitute);
                    Intrinsics.checkNotNull(simpleTypeMarkerAsSimpleType);
                    return simpleTypeMarkerAsSimpleType;
                }
            };
        }
    }
}
