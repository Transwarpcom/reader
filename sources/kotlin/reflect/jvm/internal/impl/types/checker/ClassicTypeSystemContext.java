package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModalityUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedType;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.AbstractStubType;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType;
import kotlin.reflect.jvm.internal.impl.types.DynamicType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DynamicTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.FlexibleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContextKt;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemInferenceExtensionContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariableTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ClassicTypeSystemContext.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/ClassicTypeSystemContext.class */
public interface ClassicTypeSystemContext extends TypeSystemCommonBackendContext, TypeSystemInferenceExtensionContext {
    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    SimpleTypeMarker withNullability(@NotNull SimpleTypeMarker simpleTypeMarker, boolean z);

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    SimpleTypeMarker asSimpleType(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    SimpleTypeMarker upperBound(@NotNull FlexibleTypeMarker flexibleTypeMarker);

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    SimpleTypeMarker lowerBound(@NotNull FlexibleTypeMarker flexibleTypeMarker);

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    TypeConstructorMarker typeConstructor(@NotNull SimpleTypeMarker simpleTypeMarker);

    @NotNull
    KotlinTypeMarker createFlexibleType(@NotNull SimpleTypeMarker simpleTypeMarker, @NotNull SimpleTypeMarker simpleTypeMarker2);

    /* compiled from: ClassicTypeSystemContext.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/ClassicTypeSystemContext$DefaultImpls.class */
    public static final class DefaultImpls {
        @NotNull
        public static KotlinTypeMarker makeNullable(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return TypeSystemCommonBackendContext.DefaultImpls.makeNullable(classicTypeSystemContext, receiver);
        }

        public static boolean hasFlexibleNullability(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return TypeSystemInferenceExtensionContext.DefaultImpls.hasFlexibleNullability(classicTypeSystemContext, receiver);
        }

        public static boolean isDefinitelyNotNullType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return TypeSystemInferenceExtensionContext.DefaultImpls.isDefinitelyNotNullType(classicTypeSystemContext, receiver);
        }

        public static boolean isDynamic(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return TypeSystemInferenceExtensionContext.DefaultImpls.isDynamic(classicTypeSystemContext, receiver);
        }

        public static boolean isMarkedNullable(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return TypeSystemInferenceExtensionContext.DefaultImpls.isMarkedNullable(classicTypeSystemContext, receiver);
        }

        public static boolean isNothing(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return TypeSystemInferenceExtensionContext.DefaultImpls.isNothing(classicTypeSystemContext, receiver);
        }

        @NotNull
        public static SimpleTypeMarker lowerBoundIfFlexible(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return TypeSystemInferenceExtensionContext.DefaultImpls.lowerBoundIfFlexible(classicTypeSystemContext, receiver);
        }

        @NotNull
        public static TypeConstructorMarker typeConstructor(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return TypeSystemInferenceExtensionContext.DefaultImpls.typeConstructor(classicTypeSystemContext, receiver);
        }

        @NotNull
        public static SimpleTypeMarker upperBoundIfFlexible(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return TypeSystemInferenceExtensionContext.DefaultImpls.upperBoundIfFlexible(classicTypeSystemContext, receiver);
        }

        @Nullable
        public static List<SimpleTypeMarker> fastCorrespondingSupertypes(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker receiver, @NotNull TypeConstructorMarker constructor) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            Intrinsics.checkNotNullParameter(constructor, "constructor");
            return TypeSystemInferenceExtensionContext.DefaultImpls.fastCorrespondingSupertypes(classicTypeSystemContext, receiver, constructor);
        }

        @Nullable
        public static TypeArgumentMarker getArgumentOrNull(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker receiver, int index) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return TypeSystemInferenceExtensionContext.DefaultImpls.getArgumentOrNull(classicTypeSystemContext, receiver, index);
        }

        public static boolean isClassType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return TypeSystemInferenceExtensionContext.DefaultImpls.isClassType(classicTypeSystemContext, receiver);
        }

        public static boolean isIntegerLiteralType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return TypeSystemInferenceExtensionContext.DefaultImpls.isIntegerLiteralType(classicTypeSystemContext, receiver);
        }

        @NotNull
        public static TypeArgumentMarker get(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeArgumentListMarker receiver, int index) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return TypeSystemInferenceExtensionContext.DefaultImpls.get(classicTypeSystemContext, receiver, index);
        }

        public static int size(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeArgumentListMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return TypeSystemInferenceExtensionContext.DefaultImpls.size(classicTypeSystemContext, receiver);
        }

        public static boolean isDenotable(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((TypeConstructor) receiver).isDenotable();
        }

        public static boolean isIntegerLiteralTypeConstructor(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return receiver instanceof IntegerLiteralTypeConstructor;
        }

        @Nullable
        public static TypeParameterMarker getTypeParameter(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeVariableTypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof NewTypeVariableConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((NewTypeVariableConstructor) receiver).getOriginalTypeParameter();
        }

        @NotNull
        public static Collection<KotlinTypeMarker> possibleIntegerTypes(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            TypeConstructorMarker typeConstructor = classicTypeSystemContext.typeConstructor(receiver);
            if (!(typeConstructor instanceof IntegerLiteralTypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((IntegerLiteralTypeConstructor) typeConstructor).getPossibleTypes();
        }

        @NotNull
        public static SimpleTypeMarker withNullability(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker receiver, boolean nullable) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((SimpleType) receiver).makeNullableAsSpecified(nullable);
        }

        public static boolean isError(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return KotlinTypeKt.isError((KotlinType) receiver);
        }

        public static boolean isStubType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return receiver instanceof AbstractStubType;
        }

        @Nullable
        public static KotlinTypeMarker lowerType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull CapturedTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof NewCapturedType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((NewCapturedType) receiver).getLowerType();
        }

        public static boolean isIntersection(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return receiver instanceof IntersectionTypeConstructor;
        }

        public static boolean identicalArguments(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker a, @NotNull SimpleTypeMarker b) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(a, "a");
            Intrinsics.checkNotNullParameter(b, "b");
            if (!(a instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + a + ", " + Reflection.getOrCreateKotlinClass(a.getClass())).toString());
            }
            if (b instanceof SimpleType) {
                return ((SimpleType) a).getArguments() == ((SimpleType) b).getArguments();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + b + ", " + Reflection.getOrCreateKotlinClass(b.getClass())).toString());
        }

        @Nullable
        public static SimpleTypeMarker asSimpleType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            UnwrappedType unwrappedTypeUnwrap = ((KotlinType) receiver).unwrap();
            return unwrappedTypeUnwrap instanceof SimpleType ? (SimpleType) unwrappedTypeUnwrap : null;
        }

        @Nullable
        public static FlexibleTypeMarker asFlexibleType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            UnwrappedType unwrappedTypeUnwrap = ((KotlinType) receiver).unwrap();
            return unwrappedTypeUnwrap instanceof FlexibleType ? (FlexibleType) unwrappedTypeUnwrap : null;
        }

        @Nullable
        public static DynamicTypeMarker asDynamicType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull FlexibleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof FlexibleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return receiver instanceof DynamicType ? (DynamicType) receiver : null;
        }

        @NotNull
        public static SimpleTypeMarker upperBound(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull FlexibleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof FlexibleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((FlexibleType) receiver).getUpperBound();
        }

        @NotNull
        public static SimpleTypeMarker lowerBound(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull FlexibleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof FlexibleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((FlexibleType) receiver).getLowerBound();
        }

        @Nullable
        public static CapturedTypeMarker asCapturedType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return receiver instanceof NewCapturedType ? (NewCapturedType) receiver : null;
        }

        @Nullable
        public static DefinitelyNotNullTypeMarker asDefinitelyNotNullType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return receiver instanceof DefinitelyNotNullType ? (DefinitelyNotNullType) receiver : null;
        }

        public static boolean isMarkedNullable(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((SimpleType) receiver).isMarkedNullable();
        }

        @NotNull
        public static TypeConstructorMarker typeConstructor(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((SimpleType) receiver).getConstructor();
        }

        @NotNull
        public static CapturedTypeConstructorMarker typeConstructor(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull CapturedTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof NewCapturedType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((NewCapturedType) receiver).getConstructor();
        }

        @NotNull
        public static TypeArgumentMarker projection(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull CapturedTypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof NewCapturedTypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((NewCapturedTypeConstructor) receiver).getProjection();
        }

        public static int argumentsCount(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((KotlinType) receiver).getArguments().size();
        }

        @NotNull
        public static TypeArgumentMarker getArgument(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver, int index) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((KotlinType) receiver).getArguments().get(index);
        }

        public static boolean isStarProjection(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeArgumentMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeProjection)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((TypeProjection) receiver).isStarProjection();
        }

        @NotNull
        public static TypeVariance getVariance(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeArgumentMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeProjection)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            Variance projectionKind = ((TypeProjection) receiver).getProjectionKind();
            Intrinsics.checkNotNullExpressionValue(projectionKind, "this.projectionKind");
            return TypeSystemContextKt.convertVariance(projectionKind);
        }

        @NotNull
        public static KotlinTypeMarker getType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeArgumentMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeProjection)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((TypeProjection) receiver).getType().unwrap();
        }

        public static int parametersCount(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((TypeConstructor) receiver).getParameters().size();
        }

        @NotNull
        public static TypeParameterMarker getParameter(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver, int index) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            TypeParameterDescriptor typeParameterDescriptor = ((TypeConstructor) receiver).getParameters().get(index);
            Intrinsics.checkNotNullExpressionValue(typeParameterDescriptor, "this.parameters[index]");
            return typeParameterDescriptor;
        }

        @NotNull
        public static Collection<KotlinTypeMarker> supertypes(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            Collection<KotlinType> collectionMo3835getSupertypes = ((TypeConstructor) receiver).mo3835getSupertypes();
            Intrinsics.checkNotNullExpressionValue(collectionMo3835getSupertypes, "this.supertypes");
            return collectionMo3835getSupertypes;
        }

        @NotNull
        public static TypeVariance getVariance(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeParameterMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeParameterDescriptor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            Variance variance = ((TypeParameterDescriptor) receiver).getVariance();
            Intrinsics.checkNotNullExpressionValue(variance, "this.variance");
            return TypeSystemContextKt.convertVariance(variance);
        }

        public static boolean hasRecursiveBounds(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeParameterMarker receiver, @NotNull TypeConstructorMarker selfConstructor) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            Intrinsics.checkNotNullParameter(selfConstructor, "selfConstructor");
            if (!(receiver instanceof TypeParameterDescriptor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            if (!(selfConstructor instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return TypeUtilsKt.hasTypeParameterRecursiveBounds$default((TypeParameterDescriptor) receiver, (TypeConstructor) selfConstructor, null, 4, null);
        }

        public static boolean areEqualTypeConstructors(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker c1, @NotNull TypeConstructorMarker c2) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(c1, "c1");
            Intrinsics.checkNotNullParameter(c2, "c2");
            if (!(c1 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + c1 + ", " + Reflection.getOrCreateKotlinClass(c1.getClass())).toString());
            }
            if (!(c2 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + c2 + ", " + Reflection.getOrCreateKotlinClass(c2.getClass())).toString());
            }
            return Intrinsics.areEqual(c1, c2);
        }

        public static boolean isClassTypeConstructor(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((TypeConstructor) receiver).mo3831getDeclarationDescriptor() instanceof ClassDescriptor;
        }

        public static boolean isCommonFinalClassConstructor(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = ((TypeConstructor) receiver).mo3831getDeclarationDescriptor();
            ClassDescriptor classDescriptor = classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor : null;
            return (classDescriptor == null || !ModalityUtilsKt.isFinalClass(classDescriptor) || classDescriptor.getKind() == ClassKind.ENUM_ENTRY || classDescriptor.getKind() == ClassKind.ANNOTATION_CLASS) ? false : true;
        }

        @NotNull
        public static TypeArgumentListMarker asArgumentList(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return (TypeArgumentListMarker) receiver;
        }

        @Nullable
        public static SimpleTypeMarker captureFromArguments(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker type, @NotNull CaptureStatus status) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(status, "status");
            if (!(type instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + type + ", " + Reflection.getOrCreateKotlinClass(type.getClass())).toString());
            }
            return NewCapturedTypeKt.captureFromArguments((SimpleType) type, status);
        }

        public static boolean isAnyConstructor(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return KotlinBuiltIns.isTypeConstructorForGivenClass((TypeConstructor) receiver, StandardNames.FqNames.any);
        }

        public static boolean isNothingConstructor(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return KotlinBuiltIns.isTypeConstructorForGivenClass((TypeConstructor) receiver, StandardNames.FqNames.nothing);
        }

        @NotNull
        public static TypeArgumentMarker asTypeArgument(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return TypeUtilsKt.asTypeProjection((KotlinType) receiver);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static boolean isSingleClassifierType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (receiver instanceof SimpleType) {
                return (KotlinTypeKt.isError((KotlinType) receiver) || (((SimpleType) receiver).getConstructor().mo3831getDeclarationDescriptor() instanceof TypeAliasDescriptor) || (((SimpleType) receiver).getConstructor().mo3831getDeclarationDescriptor() == null && !(receiver instanceof CapturedType) && !(receiver instanceof NewCapturedType) && !(receiver instanceof DefinitelyNotNullType) && !(((SimpleType) receiver).getConstructor() instanceof IntegerLiteralTypeConstructor))) ? false : true;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
        }

        @NotNull
        public static KotlinTypeMarker intersectTypes(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull List<? extends KotlinTypeMarker> types) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(types, "types");
            return IntersectionTypeKt.intersectTypes(types);
        }

        @NotNull
        public static KotlinTypeMarker createFlexibleType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker lowerBound, @NotNull SimpleTypeMarker upperBound) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(lowerBound, "lowerBound");
            Intrinsics.checkNotNullParameter(upperBound, "upperBound");
            if (!(lowerBound instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + classicTypeSystemContext + ", " + Reflection.getOrCreateKotlinClass(classicTypeSystemContext.getClass())).toString());
            }
            if (!(upperBound instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + classicTypeSystemContext + ", " + Reflection.getOrCreateKotlinClass(classicTypeSystemContext.getClass())).toString());
            }
            KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
            return KotlinTypeFactory.flexibleType((SimpleType) lowerBound, (SimpleType) upperBound);
        }

        @NotNull
        public static KotlinTypeMarker withNullability(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver, boolean nullable) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (receiver instanceof SimpleTypeMarker) {
                return classicTypeSystemContext.withNullability((SimpleTypeMarker) receiver, nullable);
            }
            if (receiver instanceof FlexibleTypeMarker) {
                return classicTypeSystemContext.createFlexibleType(classicTypeSystemContext.withNullability(classicTypeSystemContext.lowerBound((FlexibleTypeMarker) receiver), nullable), classicTypeSystemContext.withNullability(classicTypeSystemContext.upperBound((FlexibleTypeMarker) receiver), nullable));
            }
            throw new IllegalStateException("sealed".toString());
        }

        @NotNull
        public static AbstractTypeCheckerContext newBaseTypeCheckerContext(@NotNull ClassicTypeSystemContext classicTypeSystemContext, boolean errorTypesEqualToAnything, boolean stubTypesEqualToAnything) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            return new ClassicTypeCheckerContext(errorTypesEqualToAnything, stubTypesEqualToAnything, false, null, null, classicTypeSystemContext, 28, null);
        }

        @NotNull
        public static KotlinTypeMarker makeDefinitelyNotNullOrNotNull(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (receiver instanceof UnwrappedType) {
                return ClassicTypeSystemContextKt.makeDefinitelyNotNullOrNotNullInternal((UnwrappedType) receiver);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
        }

        public static boolean isProjectionNotNull(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull CapturedTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof NewCapturedType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((NewCapturedType) receiver).isProjectionNotNull();
        }

        @NotNull
        public static CaptureStatus captureStatus(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull CapturedTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof NewCapturedType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((NewCapturedType) receiver).getCaptureStatus();
        }

        public static boolean isNullableType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return TypeUtils.isNullableType((KotlinType) receiver);
        }

        @NotNull
        public static SimpleTypeMarker original(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull DefinitelyNotNullTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof DefinitelyNotNullType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((DefinitelyNotNullType) receiver).getOriginal();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static boolean isPrimitiveType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return KotlinBuiltIns.isPrimitiveType((KotlinType) receiver);
        }

        public static boolean hasAnnotation(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver, @NotNull FqName fqName) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            Intrinsics.checkNotNullParameter(fqName, "fqName");
            if (!(receiver instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return ((KotlinType) receiver).getAnnotations().hasAnnotation(fqName);
        }

        @Nullable
        public static TypeParameterMarker getTypeParameterClassifier(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = ((TypeConstructor) receiver).mo3831getDeclarationDescriptor();
            return classifierDescriptorMo3831getDeclarationDescriptor instanceof TypeParameterDescriptor ? (TypeParameterDescriptor) classifierDescriptorMo3831getDeclarationDescriptor : null;
        }

        public static boolean isInlineClass(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = ((TypeConstructor) receiver).mo3831getDeclarationDescriptor();
            ClassDescriptor classDescriptor = classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor : null;
            return Intrinsics.areEqual((Object) (classDescriptor == null ? null : Boolean.valueOf(InlineClassesUtilsKt.isInlineClass(classDescriptor))), (Object) true);
        }

        @NotNull
        public static KotlinTypeMarker getRepresentativeUpperBound(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeParameterMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeParameterDescriptor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return TypeUtilsKt.getRepresentativeUpperBound((TypeParameterDescriptor) receiver);
        }

        @Nullable
        public static KotlinTypeMarker getSubstitutedUnderlyingType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            return InlineClassesUtilsKt.substitutedUnderlyingType((KotlinType) receiver);
        }

        @Nullable
        public static PrimitiveType getPrimitiveType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = ((TypeConstructor) receiver).mo3831getDeclarationDescriptor();
            if (classifierDescriptorMo3831getDeclarationDescriptor == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            }
            return KotlinBuiltIns.getPrimitiveType((ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor);
        }

        @Nullable
        public static PrimitiveType getPrimitiveArrayType(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = ((TypeConstructor) receiver).mo3831getDeclarationDescriptor();
            if (classifierDescriptorMo3831getDeclarationDescriptor == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            }
            return KotlinBuiltIns.getPrimitiveArrayType((ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor);
        }

        public static boolean isUnderKotlinPackage(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver) {
            Boolean boolValueOf;
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            DeclarationDescriptor declarationDescriptorMo3831getDeclarationDescriptor = ((TypeConstructor) receiver).mo3831getDeclarationDescriptor();
            if (declarationDescriptorMo3831getDeclarationDescriptor == null) {
                boolValueOf = null;
            } else {
                DeclarationDescriptor p0 = declarationDescriptorMo3831getDeclarationDescriptor;
                boolValueOf = Boolean.valueOf(KotlinBuiltIns.isUnderKotlinPackage(p0));
            }
            return Intrinsics.areEqual((Object) boolValueOf, (Object) true);
        }

        @NotNull
        public static FqNameUnsafe getClassFqNameUnsafe(@NotNull ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker receiver) {
            Intrinsics.checkNotNullParameter(classicTypeSystemContext, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            if (!(receiver instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + receiver + ", " + Reflection.getOrCreateKotlinClass(receiver.getClass())).toString());
            }
            ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = ((TypeConstructor) receiver).mo3831getDeclarationDescriptor();
            if (classifierDescriptorMo3831getDeclarationDescriptor == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            }
            return DescriptorUtilsKt.getFqNameUnsafe((ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor);
        }
    }
}
