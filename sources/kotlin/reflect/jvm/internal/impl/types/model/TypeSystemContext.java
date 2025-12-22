package kotlin.reflect.jvm.internal.impl.types.model;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeSystemContext.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/model/TypeSystemContext.class */
public interface TypeSystemContext extends TypeSystemOptimizationContext {
    @Nullable
    SimpleTypeMarker asSimpleType(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @Nullable
    FlexibleTypeMarker asFlexibleType(@NotNull KotlinTypeMarker kotlinTypeMarker);

    boolean isError(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @Nullable
    DynamicTypeMarker asDynamicType(@NotNull FlexibleTypeMarker flexibleTypeMarker);

    @NotNull
    SimpleTypeMarker upperBound(@NotNull FlexibleTypeMarker flexibleTypeMarker);

    @NotNull
    SimpleTypeMarker lowerBound(@NotNull FlexibleTypeMarker flexibleTypeMarker);

    @Nullable
    CapturedTypeMarker asCapturedType(@NotNull SimpleTypeMarker simpleTypeMarker);

    @Nullable
    DefinitelyNotNullTypeMarker asDefinitelyNotNullType(@NotNull SimpleTypeMarker simpleTypeMarker);

    @NotNull
    SimpleTypeMarker original(@NotNull DefinitelyNotNullTypeMarker definitelyNotNullTypeMarker);

    @NotNull
    KotlinTypeMarker makeDefinitelyNotNullOrNotNull(@NotNull KotlinTypeMarker kotlinTypeMarker);

    boolean isMarkedNullable(@NotNull SimpleTypeMarker simpleTypeMarker);

    boolean isMarkedNullable(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @NotNull
    SimpleTypeMarker withNullability(@NotNull SimpleTypeMarker simpleTypeMarker, boolean z);

    @NotNull
    TypeConstructorMarker typeConstructor(@NotNull SimpleTypeMarker simpleTypeMarker);

    @NotNull
    KotlinTypeMarker withNullability(@NotNull KotlinTypeMarker kotlinTypeMarker, boolean z);

    @NotNull
    CapturedTypeConstructorMarker typeConstructor(@NotNull CapturedTypeMarker capturedTypeMarker);

    @NotNull
    CaptureStatus captureStatus(@NotNull CapturedTypeMarker capturedTypeMarker);

    boolean isProjectionNotNull(@NotNull CapturedTypeMarker capturedTypeMarker);

    @NotNull
    TypeArgumentMarker projection(@NotNull CapturedTypeConstructorMarker capturedTypeConstructorMarker);

    int argumentsCount(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @NotNull
    TypeArgumentMarker getArgument(@NotNull KotlinTypeMarker kotlinTypeMarker, int i);

    @Nullable
    TypeArgumentMarker getArgumentOrNull(@NotNull SimpleTypeMarker simpleTypeMarker, int i);

    boolean isStubType(@NotNull SimpleTypeMarker simpleTypeMarker);

    @NotNull
    TypeArgumentMarker asTypeArgument(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @Nullable
    KotlinTypeMarker lowerType(@NotNull CapturedTypeMarker capturedTypeMarker);

    boolean isStarProjection(@NotNull TypeArgumentMarker typeArgumentMarker);

    @NotNull
    TypeVariance getVariance(@NotNull TypeArgumentMarker typeArgumentMarker);

    @NotNull
    KotlinTypeMarker getType(@NotNull TypeArgumentMarker typeArgumentMarker);

    int parametersCount(@NotNull TypeConstructorMarker typeConstructorMarker);

    @NotNull
    TypeParameterMarker getParameter(@NotNull TypeConstructorMarker typeConstructorMarker, int i);

    @NotNull
    Collection<KotlinTypeMarker> supertypes(@NotNull TypeConstructorMarker typeConstructorMarker);

    boolean isIntersection(@NotNull TypeConstructorMarker typeConstructorMarker);

    boolean isClassTypeConstructor(@NotNull TypeConstructorMarker typeConstructorMarker);

    boolean isIntegerLiteralTypeConstructor(@NotNull TypeConstructorMarker typeConstructorMarker);

    @Nullable
    TypeParameterMarker getTypeParameter(@NotNull TypeVariableTypeConstructorMarker typeVariableTypeConstructorMarker);

    @NotNull
    TypeVariance getVariance(@NotNull TypeParameterMarker typeParameterMarker);

    boolean hasRecursiveBounds(@NotNull TypeParameterMarker typeParameterMarker, @NotNull TypeConstructorMarker typeConstructorMarker);

    boolean areEqualTypeConstructors(@NotNull TypeConstructorMarker typeConstructorMarker, @NotNull TypeConstructorMarker typeConstructorMarker2);

    boolean isDenotable(@NotNull TypeConstructorMarker typeConstructorMarker);

    @NotNull
    SimpleTypeMarker lowerBoundIfFlexible(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @NotNull
    SimpleTypeMarker upperBoundIfFlexible(@NotNull KotlinTypeMarker kotlinTypeMarker);

    boolean isDynamic(@NotNull KotlinTypeMarker kotlinTypeMarker);

    boolean isDefinitelyNotNullType(@NotNull KotlinTypeMarker kotlinTypeMarker);

    boolean hasFlexibleNullability(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @NotNull
    TypeConstructorMarker typeConstructor(@NotNull KotlinTypeMarker kotlinTypeMarker);

    boolean isNullableType(@NotNull KotlinTypeMarker kotlinTypeMarker);

    boolean isNothing(@NotNull KotlinTypeMarker kotlinTypeMarker);

    boolean isClassType(@NotNull SimpleTypeMarker simpleTypeMarker);

    @Nullable
    List<SimpleTypeMarker> fastCorrespondingSupertypes(@NotNull SimpleTypeMarker simpleTypeMarker, @NotNull TypeConstructorMarker typeConstructorMarker);

    boolean isIntegerLiteralType(@NotNull SimpleTypeMarker simpleTypeMarker);

    @NotNull
    Collection<KotlinTypeMarker> possibleIntegerTypes(@NotNull SimpleTypeMarker simpleTypeMarker);

    boolean isCommonFinalClassConstructor(@NotNull TypeConstructorMarker typeConstructorMarker);

    @Nullable
    SimpleTypeMarker captureFromArguments(@NotNull SimpleTypeMarker simpleTypeMarker, @NotNull CaptureStatus captureStatus);

    @NotNull
    TypeArgumentListMarker asArgumentList(@NotNull SimpleTypeMarker simpleTypeMarker);

    @NotNull
    TypeArgumentMarker get(@NotNull TypeArgumentListMarker typeArgumentListMarker, int i);

    int size(@NotNull TypeArgumentListMarker typeArgumentListMarker);

    boolean isAnyConstructor(@NotNull TypeConstructorMarker typeConstructorMarker);

    boolean isNothingConstructor(@NotNull TypeConstructorMarker typeConstructorMarker);

    boolean isSingleClassifierType(@NotNull SimpleTypeMarker simpleTypeMarker);

    @NotNull
    KotlinTypeMarker intersectTypes(@NotNull List<? extends KotlinTypeMarker> list);

    boolean isPrimitiveType(@NotNull SimpleTypeMarker simpleTypeMarker);

    /* compiled from: TypeSystemContext.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/model/TypeSystemContext$DefaultImpls.class */
    public static final class DefaultImpls {
        public static boolean isMarkedNullable(@NotNull TypeSystemContext typeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(typeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return (receiver instanceof SimpleTypeMarker) && typeSystemContext.isMarkedNullable((SimpleTypeMarker) receiver);
        }

        @Nullable
        public static TypeArgumentMarker getArgumentOrNull(@NotNull TypeSystemContext typeSystemContext, @NotNull SimpleTypeMarker receiver, int index) {
            Intrinsics.checkNotNullParameter(typeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            boolean z = 0 <= index && index < typeSystemContext.argumentsCount(receiver);
            if (z) {
                return typeSystemContext.getArgument(receiver, index);
            }
            return null;
        }

        @NotNull
        public static SimpleTypeMarker lowerBoundIfFlexible(@NotNull TypeSystemContext typeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(typeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            FlexibleTypeMarker flexibleTypeMarkerAsFlexibleType = typeSystemContext.asFlexibleType(receiver);
            if (flexibleTypeMarkerAsFlexibleType != null) {
                return typeSystemContext.lowerBound(flexibleTypeMarkerAsFlexibleType);
            }
            SimpleTypeMarker simpleTypeMarkerAsSimpleType = typeSystemContext.asSimpleType(receiver);
            Intrinsics.checkNotNull(simpleTypeMarkerAsSimpleType);
            return simpleTypeMarkerAsSimpleType;
        }

        @NotNull
        public static SimpleTypeMarker upperBoundIfFlexible(@NotNull TypeSystemContext typeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(typeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            FlexibleTypeMarker flexibleTypeMarkerAsFlexibleType = typeSystemContext.asFlexibleType(receiver);
            if (flexibleTypeMarkerAsFlexibleType != null) {
                return typeSystemContext.upperBound(flexibleTypeMarkerAsFlexibleType);
            }
            SimpleTypeMarker simpleTypeMarkerAsSimpleType = typeSystemContext.asSimpleType(receiver);
            Intrinsics.checkNotNull(simpleTypeMarkerAsSimpleType);
            return simpleTypeMarkerAsSimpleType;
        }

        public static boolean isDynamic(@NotNull TypeSystemContext typeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(typeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            FlexibleTypeMarker flexibleTypeMarkerAsFlexibleType = typeSystemContext.asFlexibleType(receiver);
            return (flexibleTypeMarkerAsFlexibleType == null ? null : typeSystemContext.asDynamicType(flexibleTypeMarkerAsFlexibleType)) != null;
        }

        public static boolean isDefinitelyNotNullType(@NotNull TypeSystemContext typeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(typeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            SimpleTypeMarker simpleTypeMarkerAsSimpleType = typeSystemContext.asSimpleType(receiver);
            return (simpleTypeMarkerAsSimpleType == null ? null : typeSystemContext.asDefinitelyNotNullType(simpleTypeMarkerAsSimpleType)) != null;
        }

        public static boolean hasFlexibleNullability(@NotNull TypeSystemContext typeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(typeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return typeSystemContext.isMarkedNullable(typeSystemContext.lowerBoundIfFlexible(receiver)) != typeSystemContext.isMarkedNullable(typeSystemContext.upperBoundIfFlexible(receiver));
        }

        @NotNull
        public static TypeConstructorMarker typeConstructor(@NotNull TypeSystemContext typeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(typeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            SimpleTypeMarker simpleTypeMarkerAsSimpleType = typeSystemContext.asSimpleType(receiver);
            return typeSystemContext.typeConstructor(simpleTypeMarkerAsSimpleType == null ? typeSystemContext.lowerBoundIfFlexible(receiver) : simpleTypeMarkerAsSimpleType);
        }

        public static boolean isNothing(@NotNull TypeSystemContext typeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(typeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return typeSystemContext.isNothingConstructor(typeSystemContext.typeConstructor(receiver)) && !typeSystemContext.isNullableType(receiver);
        }

        public static boolean isClassType(@NotNull TypeSystemContext typeSystemContext, @NotNull SimpleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(typeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return typeSystemContext.isClassTypeConstructor(typeSystemContext.typeConstructor(receiver));
        }

        @Nullable
        public static List<SimpleTypeMarker> fastCorrespondingSupertypes(@NotNull TypeSystemContext typeSystemContext, @NotNull SimpleTypeMarker receiver, @NotNull TypeConstructorMarker constructor) {
            Intrinsics.checkNotNullParameter(typeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            Intrinsics.checkNotNullParameter(constructor, "constructor");
            return null;
        }

        public static boolean isIntegerLiteralType(@NotNull TypeSystemContext typeSystemContext, @NotNull SimpleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(typeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return typeSystemContext.isIntegerLiteralTypeConstructor(typeSystemContext.typeConstructor(receiver));
        }

        @NotNull
        public static TypeArgumentMarker get(@NotNull TypeSystemContext typeSystemContext, @NotNull TypeArgumentListMarker receiver, int index) {
            Intrinsics.checkNotNullParameter(typeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (receiver instanceof SimpleTypeMarker) {
                return typeSystemContext.getArgument((KotlinTypeMarker) receiver, index);
            }
            if (!(receiver instanceof ArgumentList)) {
                throw new IllegalStateException(("unknown type argument list type: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            TypeArgumentMarker typeArgumentMarker = ((ArgumentList) receiver).get(index);
            Intrinsics.checkNotNullExpressionValue(typeArgumentMarker, "get(index)");
            return typeArgumentMarker;
        }

        public static int size(@NotNull TypeSystemContext typeSystemContext, @NotNull TypeArgumentListMarker receiver) {
            Intrinsics.checkNotNullParameter(typeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (receiver instanceof SimpleTypeMarker) {
                return typeSystemContext.argumentsCount((KotlinTypeMarker) receiver);
            }
            if (receiver instanceof ArgumentList) {
                return ((ArgumentList) receiver).size();
            }
            throw new IllegalStateException(("unknown type argument list type: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
        }
    }
}
