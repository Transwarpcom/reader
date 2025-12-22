package kotlin.reflect.full;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import kotlin.reflect.jvm.internal.KClassifierImpl;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionBase;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;

/* compiled from: KClassifiers.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��8\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001b\n��\u001a.\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002\u001a6\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000eH\u0007\"\u001e\u0010��\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"starProjectedType", "Lkotlin/reflect/KType;", "Lkotlin/reflect/KClassifier;", "getStarProjectedType$annotations", "(Lkotlin/reflect/KClassifier;)V", "getStarProjectedType", "(Lkotlin/reflect/KClassifier;)Lkotlin/reflect/KType;", "createKotlinType", "Lkotlin/reflect/jvm/internal/impl/types/SimpleType;", "typeAnnotations", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/Annotations;", "typeConstructor", "Lkotlin/reflect/jvm/internal/impl/types/TypeConstructor;", "arguments", "", "Lkotlin/reflect/KTypeProjection;", "nullable", "", "createType", "annotations", "", "kotlin-reflection"})
@JvmName(name = "KClassifiers")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/full/KClassifiers.class */
public final class KClassifiers {
    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getStarProjectedType$annotations(KClassifier kClassifier) {
    }

    public static /* synthetic */ KType createType$default(KClassifier kClassifier, List list, boolean z, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        return createType(kClassifier, list, z, list2);
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final KType createType(@NotNull KClassifier createType, @NotNull List<KTypeProjection> arguments, boolean nullable, @NotNull List<? extends Annotation> annotations) {
        ClassifierDescriptor descriptor;
        Intrinsics.checkNotNullParameter(createType, "$this$createType");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        KClassifier kClassifier = createType;
        if (!(kClassifier instanceof KClassifierImpl)) {
            kClassifier = null;
        }
        KClassifierImpl kClassifierImpl = (KClassifierImpl) kClassifier;
        if (kClassifierImpl == null || (descriptor = kClassifierImpl.getDescriptor()) == null) {
            throw new KotlinReflectionInternalError("Cannot create type for an unsupported classifier: " + createType + " (" + createType.getClass() + ')');
        }
        TypeConstructor typeConstructor = descriptor.getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "descriptor.typeConstructor");
        List parameters = typeConstructor.getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "typeConstructor.parameters");
        if (parameters.size() != arguments.size()) {
            throw new IllegalArgumentException("Class declares " + parameters.size() + " type parameters, but " + arguments.size() + " were provided.");
        }
        Annotations typeAnnotations = annotations.isEmpty() ? Annotations.Companion.getEMPTY() : Annotations.Companion.getEMPTY();
        return new KTypeImpl(createKotlinType(typeAnnotations, typeConstructor, arguments, nullable), null, 2, null);
    }

    private static final SimpleType createKotlinType(Annotations typeAnnotations, TypeConstructor typeConstructor, List<KTypeProjection> list, boolean nullable) {
        TypeProjectionBase starProjectionImpl;
        List parameters = typeConstructor.getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "typeConstructor.parameters");
        List<KTypeProjection> $this$mapIndexed$iv = list;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv, 10));
        int index$iv$iv = 0;
        for (Object item$iv$iv : $this$mapIndexed$iv) {
            int index = index$iv$iv;
            index$iv$iv++;
            if (index < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            KTypeProjection typeProjection = (KTypeProjection) item$iv$iv;
            KTypeImpl kTypeImpl = (KTypeImpl) typeProjection.getType();
            KotlinType type = kTypeImpl != null ? kTypeImpl.getType() : null;
            KVariance variance = typeProjection.getVariance();
            if (variance != null) {
                switch (variance) {
                    case INVARIANT:
                        Variance variance2 = Variance.INVARIANT;
                        Intrinsics.checkNotNull(type);
                        starProjectionImpl = new TypeProjectionImpl(variance2, type);
                        break;
                    case IN:
                        Variance variance3 = Variance.IN_VARIANCE;
                        Intrinsics.checkNotNull(type);
                        starProjectionImpl = new TypeProjectionImpl(variance3, type);
                        break;
                    case OUT:
                        Variance variance4 = Variance.OUT_VARIANCE;
                        Intrinsics.checkNotNull(type);
                        starProjectionImpl = new TypeProjectionImpl(variance4, type);
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            } else {
                TypeParameterDescriptor typeParameterDescriptor = parameters.get(index);
                Intrinsics.checkNotNullExpressionValue(typeParameterDescriptor, "parameters[index]");
                starProjectionImpl = new StarProjectionImpl(typeParameterDescriptor);
            }
            destination$iv$iv.add(starProjectionImpl);
        }
        return KotlinTypeFactory.simpleType$default(typeAnnotations, typeConstructor, (List) destination$iv$iv, nullable, null, 16, null);
    }

    @NotNull
    public static final KType getStarProjectedType(@NotNull KClassifier starProjectedType) {
        ClassifierDescriptor descriptor;
        Intrinsics.checkNotNullParameter(starProjectedType, "$this$starProjectedType");
        KClassifier kClassifier = starProjectedType;
        if (!(kClassifier instanceof KClassifierImpl)) {
            kClassifier = null;
        }
        KClassifierImpl kClassifierImpl = (KClassifierImpl) kClassifier;
        if (kClassifierImpl == null || (descriptor = kClassifierImpl.getDescriptor()) == null) {
            return createType$default(starProjectedType, null, false, null, 7, null);
        }
        TypeConstructor typeConstructor = descriptor.getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "descriptor.typeConstructor");
        List typeParameters = typeConstructor.getParameters();
        Intrinsics.checkNotNullExpressionValue(typeParameters, "descriptor.typeConstructor.parameters");
        if (typeParameters.isEmpty()) {
            return createType$default(starProjectedType, null, false, null, 7, null);
        }
        List $this$map$iv = typeParameters;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(KTypeProjection.Companion.getSTAR());
        }
        return createType$default(starProjectedType, (List) destination$iv$iv, false, null, 6, null);
    }
}
