package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerValueTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinTypePreparator.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/KotlinTypePreparator.class */
public abstract class KotlinTypePreparator {
    private final SimpleType transformToNewType(SimpleType type) {
        IntersectionTypeConstructor alternative;
        KotlinType kotlinTypeMakeNullable;
        KotlinType type2;
        TypeConstructor constructor = type.getConstructor();
        if (constructor instanceof CapturedTypeConstructorImpl) {
            TypeProjection it = ((CapturedTypeConstructorImpl) constructor).getProjection();
            TypeProjection typeProjection = it.getProjectionKind() == Variance.IN_VARIANCE ? it : null;
            UnwrappedType unwrappedTypeUnwrap = (typeProjection == null || (type2 = typeProjection.getType()) == null) ? null : type2.unwrap();
            UnwrappedType lowerType = unwrappedTypeUnwrap;
            if (((CapturedTypeConstructorImpl) constructor).getNewTypeConstructor() == null) {
                CapturedTypeConstructorImpl capturedTypeConstructorImpl = (CapturedTypeConstructorImpl) constructor;
                TypeProjection projection = ((CapturedTypeConstructorImpl) constructor).getProjection();
                Iterable $this$map$iv = ((CapturedTypeConstructorImpl) constructor).mo3835getSupertypes();
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    destination$iv$iv.add(((KotlinType) item$iv$iv).unwrap());
                }
                capturedTypeConstructorImpl.setNewTypeConstructor(new NewCapturedTypeConstructor(projection, (List) destination$iv$iv, null, 4, null));
            }
            CaptureStatus captureStatus = CaptureStatus.FOR_SUBTYPING;
            NewCapturedTypeConstructor newTypeConstructor = ((CapturedTypeConstructorImpl) constructor).getNewTypeConstructor();
            Intrinsics.checkNotNull(newTypeConstructor);
            return new NewCapturedType(captureStatus, newTypeConstructor, lowerType, type.getAnnotations(), type.isMarkedNullable(), false, 32, null);
        }
        if (constructor instanceof IntegerValueTypeConstructor) {
            Iterable $this$map$iv2 = ((IntegerValueTypeConstructor) constructor).mo3835getSupertypes();
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
            for (Object item$iv$iv2 : $this$map$iv2) {
                KotlinType kotlinTypeMakeNullableAsSpecified = TypeUtils.makeNullableAsSpecified((KotlinType) item$iv$iv2, type.isMarkedNullable());
                Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNullableAsSpecified, "makeNullableAsSpecified(it, type.isMarkedNullable)");
                destination$iv$iv2.add(kotlinTypeMakeNullableAsSpecified);
            }
            IntersectionTypeConstructor newConstructor = new IntersectionTypeConstructor((List) destination$iv$iv2);
            KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
            return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(type.getAnnotations(), newConstructor, CollectionsKt.emptyList(), false, type.getMemberScope());
        }
        if (!(constructor instanceof IntersectionTypeConstructor) || !type.isMarkedNullable()) {
            return type;
        }
        IntersectionTypeConstructor $this$transformComponents_u24default$iv = (IntersectionTypeConstructor) constructor;
        boolean changed$iv = false;
        Iterable $this$map$iv$iv = $this$transformComponents_u24default$iv.mo3835getSupertypes();
        Collection destination$iv$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv, 10));
        for (Object item$iv$iv$iv : $this$map$iv$iv) {
            KotlinType it$iv = (KotlinType) item$iv$iv$iv;
            if (1 != 0) {
                changed$iv = true;
                kotlinTypeMakeNullable = TypeUtilsKt.makeNullable(it$iv);
            } else {
                kotlinTypeMakeNullable = it$iv;
            }
            destination$iv$iv$iv.add(kotlinTypeMakeNullable);
        }
        List newSupertypes$iv = (List) destination$iv$iv$iv;
        if (changed$iv) {
            KotlinType alternative$iv = $this$transformComponents_u24default$iv.getAlternativeType();
            KotlinType updatedAlternative$iv = alternative$iv == null ? null : 1 != 0 ? TypeUtilsKt.makeNullable(alternative$iv) : alternative$iv;
            alternative = new IntersectionTypeConstructor(newSupertypes$iv).setAlternative(updatedAlternative$iv);
        } else {
            alternative = null;
        }
        IntersectionTypeConstructor intersectionTypeConstructor = alternative;
        IntersectionTypeConstructor newConstructor2 = intersectionTypeConstructor == null ? (IntersectionTypeConstructor) constructor : intersectionTypeConstructor;
        return newConstructor2.createType();
    }

    @NotNull
    public UnwrappedType prepareType(@NotNull KotlinTypeMarker type) {
        SimpleType simpleTypeFlexibleType;
        Intrinsics.checkNotNullParameter(type, "type");
        if (!(type instanceof KotlinType)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        UnwrappedType unwrappedType = ((KotlinType) type).unwrap();
        if (unwrappedType instanceof SimpleType) {
            simpleTypeFlexibleType = transformToNewType((SimpleType) unwrappedType);
        } else {
            if (!(unwrappedType instanceof FlexibleType)) {
                throw new NoWhenBranchMatchedException();
            }
            SimpleType newLower = transformToNewType(((FlexibleType) unwrappedType).getLowerBound());
            SimpleType newUpper = transformToNewType(((FlexibleType) unwrappedType).getUpperBound());
            if (newLower != ((FlexibleType) unwrappedType).getLowerBound() || newUpper != ((FlexibleType) unwrappedType).getUpperBound()) {
                KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
                simpleTypeFlexibleType = KotlinTypeFactory.flexibleType(newLower, newUpper);
            } else {
                simpleTypeFlexibleType = unwrappedType;
            }
        }
        return TypeWithEnhancementKt.inheritEnhancement(simpleTypeFlexibleType, unwrappedType);
    }

    /* compiled from: KotlinTypePreparator.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/KotlinTypePreparator$Default.class */
    public static final class Default extends KotlinTypePreparator {

        @NotNull
        public static final Default INSTANCE = new Default();

        private Default() {
        }
    }
}
