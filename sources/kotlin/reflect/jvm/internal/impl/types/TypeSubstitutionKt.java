package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeSubstitution.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/TypeSubstitutionKt.class */
public final class TypeSubstitutionKt {
    @JvmOverloads
    @NotNull
    public static final KotlinType replace(@NotNull KotlinType $this$replace, @NotNull List<? extends TypeProjection> newArguments, @NotNull Annotations newAnnotations) {
        Intrinsics.checkNotNullParameter($this$replace, "<this>");
        Intrinsics.checkNotNullParameter(newArguments, "newArguments");
        Intrinsics.checkNotNullParameter(newAnnotations, "newAnnotations");
        return replace$default($this$replace, newArguments, newAnnotations, null, 4, null);
    }

    public static /* synthetic */ KotlinType replace$default(KotlinType kotlinType, List list, Annotations annotations, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = kotlinType.getArguments();
        }
        if ((i & 2) != 0) {
            annotations = kotlinType.getAnnotations();
        }
        if ((i & 4) != 0) {
            list2 = list;
        }
        return replace(kotlinType, list, annotations, list2);
    }

    @JvmOverloads
    @NotNull
    public static final KotlinType replace(@NotNull KotlinType $this$replace, @NotNull List<? extends TypeProjection> newArguments, @NotNull Annotations newAnnotations, @NotNull List<? extends TypeProjection> newArgumentsForUpperBound) {
        Intrinsics.checkNotNullParameter($this$replace, "<this>");
        Intrinsics.checkNotNullParameter(newArguments, "newArguments");
        Intrinsics.checkNotNullParameter(newAnnotations, "newAnnotations");
        Intrinsics.checkNotNullParameter(newArgumentsForUpperBound, "newArgumentsForUpperBound");
        if ((newArguments.isEmpty() || newArguments == $this$replace.getArguments()) && newAnnotations == $this$replace.getAnnotations()) {
            return $this$replace;
        }
        UnwrappedType unwrapped = $this$replace.unwrap();
        if (unwrapped instanceof FlexibleType) {
            KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
            return KotlinTypeFactory.flexibleType(replace(((FlexibleType) unwrapped).getLowerBound(), newArguments, newAnnotations), replace(((FlexibleType) unwrapped).getUpperBound(), newArgumentsForUpperBound, newAnnotations));
        }
        if (unwrapped instanceof SimpleType) {
            return replace((SimpleType) unwrapped, newArguments, newAnnotations);
        }
        throw new NoWhenBranchMatchedException();
    }

    public static /* synthetic */ SimpleType replace$default(SimpleType simpleType, List list, Annotations annotations, int i, Object obj) {
        if ((i & 1) != 0) {
            list = simpleType.getArguments();
        }
        if ((i & 2) != 0) {
            annotations = simpleType.getAnnotations();
        }
        return replace(simpleType, (List<? extends TypeProjection>) list, annotations);
    }

    @JvmOverloads
    @NotNull
    public static final SimpleType replace(@NotNull SimpleType $this$replace, @NotNull List<? extends TypeProjection> newArguments, @NotNull Annotations newAnnotations) {
        Intrinsics.checkNotNullParameter($this$replace, "<this>");
        Intrinsics.checkNotNullParameter(newArguments, "newArguments");
        Intrinsics.checkNotNullParameter(newAnnotations, "newAnnotations");
        if (newArguments.isEmpty() && newAnnotations == $this$replace.getAnnotations()) {
            return $this$replace;
        }
        if (newArguments.isEmpty()) {
            return $this$replace.replaceAnnotations(newAnnotations);
        }
        KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
        return KotlinTypeFactory.simpleType$default(newAnnotations, $this$replace.getConstructor(), newArguments, $this$replace.isMarkedNullable(), null, 16, null);
    }

    @NotNull
    public static final SimpleType asSimpleType(@NotNull KotlinType $this$asSimpleType) {
        Intrinsics.checkNotNullParameter($this$asSimpleType, "<this>");
        UnwrappedType unwrappedTypeUnwrap = $this$asSimpleType.unwrap();
        SimpleType simpleType = unwrappedTypeUnwrap instanceof SimpleType ? (SimpleType) unwrappedTypeUnwrap : null;
        if (simpleType == null) {
            throw new IllegalStateException(Intrinsics.stringPlus("This is should be simple type: ", $this$asSimpleType).toString());
        }
        return simpleType;
    }
}
