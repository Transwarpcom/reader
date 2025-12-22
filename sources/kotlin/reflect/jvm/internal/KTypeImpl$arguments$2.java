package kotlin.reflect.jvm.internal;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import org.jetbrains.annotations.NotNull;

/* compiled from: KTypeImpl.kt */
@Metadata(mv = {1, 5, 1}, k = 3, d1 = {"��\u000e\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010��\u001a\u0016\u0012\u0004\u0012\u00020\u0002 \u0003*\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lkotlin/reflect/KTypeProjection;", "kotlin.jvm.PlatformType", "invoke"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/KTypeImpl$arguments$2.class */
final class KTypeImpl$arguments$2 extends Lambda implements Function0<List<? extends KTypeProjection>> {
    final /* synthetic */ KTypeImpl this$0;
    final /* synthetic */ Function0 $computeJavaType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    KTypeImpl$arguments$2(KTypeImpl kTypeImpl, Function0 function0) {
        super(0);
        this.this$0 = kTypeImpl;
        this.$computeJavaType = function0;
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<? extends KTypeProjection> invoke() {
        KTypeProjection kTypeProjectionCovariant;
        List typeArguments = this.this$0.getType().getArguments();
        if (typeArguments.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        final Lazy parameterizedTypeArguments = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<List<? extends Type>>() { // from class: kotlin.reflect.jvm.internal.KTypeImpl$arguments$2$parameterizedTypeArguments$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final List<? extends Type> invoke() {
                Type javaType = this.this$0.this$0.getJavaType();
                Intrinsics.checkNotNull(javaType);
                return ReflectClassUtilKt.getParameterizedTypeArguments(javaType);
            }
        });
        final KProperty kProperty = null;
        List $this$mapIndexed$iv = typeArguments;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv, 10));
        int index$iv$iv = 0;
        for (Object item$iv$iv : $this$mapIndexed$iv) {
            final int i = index$iv$iv;
            index$iv$iv++;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TypeProjection typeProjection = (TypeProjection) item$iv$iv;
            if (typeProjection.isStarProjection()) {
                kTypeProjectionCovariant = KTypeProjection.Companion.getSTAR();
            } else {
                KotlinType type = typeProjection.getType();
                Intrinsics.checkNotNullExpressionValue(type, "typeProjection.type");
                KTypeImpl type2 = new KTypeImpl(type, this.$computeJavaType == null ? null : new Function0<Type>() { // from class: kotlin.reflect.jvm.internal.KTypeImpl$arguments$2$$special$$inlined$mapIndexed$lambda$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    @NotNull
                    public final Type invoke() {
                        Type type3;
                        Type javaType = this.this$0.getJavaType();
                        if (javaType instanceof Class) {
                            Class componentType = ((Class) javaType).isArray() ? ((Class) javaType).getComponentType() : Object.class;
                            Intrinsics.checkNotNullExpressionValue(componentType, "if (javaType.isArray) ja…Type else Any::class.java");
                            return componentType;
                        }
                        if (javaType instanceof GenericArrayType) {
                            if (i != 0) {
                                throw new KotlinReflectionInternalError("Array type has been queried for a non-0th argument: " + this.this$0);
                            }
                            Type genericComponentType = ((GenericArrayType) javaType).getGenericComponentType();
                            Intrinsics.checkNotNullExpressionValue(genericComponentType, "javaType.genericComponentType");
                            return genericComponentType;
                        }
                        if (javaType instanceof ParameterizedType) {
                            Lazy lazy = parameterizedTypeArguments;
                            KProperty kProperty2 = kProperty;
                            Type argument = (Type) ((List) lazy.getValue()).get(i);
                            if (argument instanceof WildcardType) {
                                Type[] lowerBounds = ((WildcardType) argument).getLowerBounds();
                                Intrinsics.checkNotNullExpressionValue(lowerBounds, "argument.lowerBounds");
                                type3 = (Type) ArraysKt.firstOrNull(lowerBounds);
                                if (type3 == null) {
                                    Type[] upperBounds = ((WildcardType) argument).getUpperBounds();
                                    Intrinsics.checkNotNullExpressionValue(upperBounds, "argument.upperBounds");
                                    type3 = (Type) ArraysKt.first(upperBounds);
                                }
                            } else {
                                type3 = argument;
                            }
                            Intrinsics.checkNotNullExpressionValue(type3, "if (argument !is Wildcar…ument.upperBounds.first()");
                            return type3;
                        }
                        throw new KotlinReflectionInternalError("Non-generic type has been queried for arguments: " + this.this$0);
                    }
                });
                switch (typeProjection.getProjectionKind()) {
                    case INVARIANT:
                        kTypeProjectionCovariant = KTypeProjection.Companion.invariant(type2);
                        break;
                    case IN_VARIANCE:
                        kTypeProjectionCovariant = KTypeProjection.Companion.contravariant(type2);
                        break;
                    case OUT_VARIANCE:
                        kTypeProjectionCovariant = KTypeProjection.Companion.covariant(type2);
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }
            destination$iv$iv.add(kTypeProjectionCovariant);
        }
        return (List) destination$iv$iv;
    }
}
