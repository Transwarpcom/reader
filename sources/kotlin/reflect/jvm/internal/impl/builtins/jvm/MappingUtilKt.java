package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: mappingUtil.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/jvm/MappingUtilKt.class */
public final class MappingUtilKt {
    @NotNull
    public static final TypeConstructorSubstitution createMappedTypeParametersSubstitution(@NotNull ClassDescriptor from, @NotNull ClassDescriptor to) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        boolean z = from.getDeclaredTypeParameters().size() == to.getDeclaredTypeParameters().size();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError(from + " and " + to + " should have same number of type parameters, but " + from.getDeclaredTypeParameters().size() + " / " + to.getDeclaredTypeParameters().size() + " found");
        }
        TypeConstructorSubstitution.Companion companion = TypeConstructorSubstitution.Companion;
        Iterable declaredTypeParameters = from.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(declaredTypeParameters, "from.declaredTypeParameters");
        Iterable $this$map$iv = declaredTypeParameters;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            TypeParameterDescriptor p0 = (TypeParameterDescriptor) item$iv$iv;
            destination$iv$iv.add(p0.getTypeConstructor());
        }
        ArrayList arrayList = (List) destination$iv$iv;
        Iterable declaredTypeParameters2 = to.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(declaredTypeParameters2, "to.declaredTypeParameters");
        Iterable $this$map$iv2 = declaredTypeParameters2;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            TypeParameterDescriptor it = (TypeParameterDescriptor) item$iv$iv2;
            SimpleType defaultType = it.getDefaultType();
            Intrinsics.checkNotNullExpressionValue(defaultType, "it.defaultType");
            destination$iv$iv2.add(TypeUtilsKt.asTypeProjection(defaultType));
        }
        return TypeConstructorSubstitution.Companion.createByConstructorsMap$default(companion, MapsKt.toMap(CollectionsKt.zip(arrayList, (List) destination$iv$iv2)), false, 2, null);
    }
}
