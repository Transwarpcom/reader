package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypePreparator;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: NewCapturedType.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/NewCapturedTypeKt.class */
public final class NewCapturedTypeKt {
    @Nullable
    public static final SimpleType captureFromArguments(@NotNull SimpleType type, @NotNull CaptureStatus status) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(status, "status");
        List it = captureArguments(type, status);
        if (it == null) {
            return null;
        }
        return replaceArguments(type, it);
    }

    private static final SimpleType replaceArguments(UnwrappedType $this$replaceArguments, List<? extends TypeProjection> list) {
        KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
        return KotlinTypeFactory.simpleType$default($this$replaceArguments.getAnnotations(), $this$replaceArguments.getConstructor(), list, $this$replaceArguments.isMarkedNullable(), null, 16, null);
    }

    private static final List<TypeProjection> captureArguments(UnwrappedType type, CaptureStatus status) {
        boolean z;
        UnwrappedType unwrappedTypeUnwrap;
        TypeProjection typeProjectionAsTypeProjection;
        if (type.getArguments().size() != type.getConstructor().getParameters().size()) {
            return null;
        }
        List arguments = type.getArguments();
        List $this$all$iv = arguments;
        if (!($this$all$iv instanceof Collection) || !$this$all$iv.isEmpty()) {
            Iterator it = $this$all$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    TypeProjection it2 = (TypeProjection) element$iv;
                    if (!(it2.getProjectionKind() == Variance.INVARIANT)) {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
        } else {
            z = true;
        }
        if (z) {
            return null;
        }
        List<TypeParameterDescriptor> parameters = type.getConstructor().getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "type.constructor.parameters");
        Iterable $this$map$iv = CollectionsKt.zip(arguments, parameters);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Pair $dstr$projection$parameter = (Pair) item$iv$iv;
            TypeProjection projection = (TypeProjection) $dstr$projection$parameter.component1();
            TypeParameterDescriptor parameter = (TypeParameterDescriptor) $dstr$projection$parameter.component2();
            if (projection.getProjectionKind() == Variance.INVARIANT) {
                typeProjectionAsTypeProjection = projection;
            } else {
                if (!projection.isStarProjection() && projection.getProjectionKind() == Variance.IN_VARIANCE) {
                    unwrappedTypeUnwrap = projection.getType().unwrap();
                } else {
                    unwrappedTypeUnwrap = (UnwrappedType) null;
                }
                UnwrappedType lowerType = unwrappedTypeUnwrap;
                Intrinsics.checkNotNullExpressionValue(parameter, "parameter");
                typeProjectionAsTypeProjection = TypeUtilsKt.asTypeProjection(new NewCapturedType(status, lowerType, projection, parameter));
            }
            destination$iv$iv.add(typeProjectionAsTypeProjection);
        }
        List capturedArguments = (List) destination$iv$iv;
        TypeSubstitutor substitutor = TypeConstructorSubstitution.Companion.create(type.getConstructor(), capturedArguments).buildSubstitutor();
        int i = 0;
        int size = arguments.size() - 1;
        if (0 <= size) {
            do {
                int index = i;
                i++;
                TypeProjection oldProjection = arguments.get(index);
                TypeProjection newProjection = (TypeProjection) capturedArguments.get(index);
                if (oldProjection.getProjectionKind() != Variance.INVARIANT) {
                    Iterable upperBounds = type.getConstructor().getParameters().get(index).getUpperBounds();
                    Intrinsics.checkNotNullExpressionValue(upperBounds, "type.constructor.parameters[index].upperBounds");
                    Iterable $this$mapTo$iv = upperBounds;
                    Collection destination$iv = (List) new ArrayList();
                    for (Object item$iv : $this$mapTo$iv) {
                        KotlinType it3 = (KotlinType) item$iv;
                        destination$iv.add(KotlinTypePreparator.Default.INSTANCE.prepareType(substitutor.safeSubstitute(it3, Variance.INVARIANT).unwrap()));
                    }
                    List capturedTypeSupertypes = (List) destination$iv;
                    if (!oldProjection.isStarProjection() && oldProjection.getProjectionKind() == Variance.OUT_VARIANCE) {
                        capturedTypeSupertypes.add(KotlinTypePreparator.Default.INSTANCE.prepareType(oldProjection.getType().unwrap()));
                    }
                    NewCapturedType capturedType = (NewCapturedType) newProjection.getType();
                    capturedType.getConstructor().initializeSupertypes(capturedTypeSupertypes);
                }
            } while (i <= size);
        }
        return capturedArguments;
    }
}
