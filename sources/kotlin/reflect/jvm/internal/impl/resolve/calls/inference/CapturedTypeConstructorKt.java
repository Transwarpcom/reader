package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.DelegatedTypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.IndexedParametersSubstitution;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CapturedTypeConstructor.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/calls/inference/CapturedTypeConstructorKt.class */
public final class CapturedTypeConstructorKt {
    @NotNull
    public static final KotlinType createCapturedType(@NotNull TypeProjection typeProjection) {
        Intrinsics.checkNotNullParameter(typeProjection, "typeProjection");
        return new CapturedType(typeProjection, null, false, null, 14, null);
    }

    public static final boolean isCaptured(@NotNull KotlinType $this$isCaptured) {
        Intrinsics.checkNotNullParameter($this$isCaptured, "<this>");
        return $this$isCaptured.getConstructor() instanceof CapturedTypeConstructor;
    }

    public static /* synthetic */ TypeSubstitution wrapWithCapturingSubstitution$default(TypeSubstitution typeSubstitution, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return wrapWithCapturingSubstitution(typeSubstitution, z);
    }

    @NotNull
    public static final TypeSubstitution wrapWithCapturingSubstitution(@NotNull TypeSubstitution $this$wrapWithCapturingSubstitution, boolean needApproximation) {
        Intrinsics.checkNotNullParameter($this$wrapWithCapturingSubstitution, "<this>");
        if ($this$wrapWithCapturingSubstitution instanceof IndexedParametersSubstitution) {
            TypeParameterDescriptor[] parameters = ((IndexedParametersSubstitution) $this$wrapWithCapturingSubstitution).getParameters();
            Iterable $this$map$iv = ArraysKt.zip(((IndexedParametersSubstitution) $this$wrapWithCapturingSubstitution).getArguments(), ((IndexedParametersSubstitution) $this$wrapWithCapturingSubstitution).getParameters());
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Pair it = (Pair) item$iv$iv;
                destination$iv$iv.add(createCapturedIfNeeded((TypeProjection) it.getFirst(), (TypeParameterDescriptor) it.getSecond()));
            }
            Collection $this$toTypedArray$iv = (List) destination$iv$iv;
            Object[] array = $this$toTypedArray$iv.toArray(new TypeProjection[0]);
            if (array == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            return new IndexedParametersSubstitution(parameters, (TypeProjection[]) array, needApproximation);
        }
        return new DelegatedTypeSubstitution(needApproximation, $this$wrapWithCapturingSubstitution) { // from class: kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt.wrapWithCapturingSubstitution.2
            final /* synthetic */ boolean $needApproximation;
            final /* synthetic */ TypeSubstitution $this_wrapWithCapturingSubstitution;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super($this$wrapWithCapturingSubstitution);
                this.$this_wrapWithCapturingSubstitution = $this$wrapWithCapturingSubstitution;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.DelegatedTypeSubstitution, kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
            public boolean approximateContravariantCapturedTypes() {
                return this.$needApproximation;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.DelegatedTypeSubstitution, kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
            @Nullable
            /* renamed from: get */
            public TypeProjection mo3923get(@NotNull KotlinType key) {
                Intrinsics.checkNotNullParameter(key, "key");
                TypeProjection typeProjectionMo3923get = super.mo3923get(key);
                if (typeProjectionMo3923get == null) {
                    return null;
                }
                ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = key.getConstructor().mo3831getDeclarationDescriptor();
                return CapturedTypeConstructorKt.createCapturedIfNeeded(typeProjectionMo3923get, classifierDescriptorMo3831getDeclarationDescriptor instanceof TypeParameterDescriptor ? (TypeParameterDescriptor) classifierDescriptorMo3831getDeclarationDescriptor : null);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TypeProjection createCapturedIfNeeded(final TypeProjection $this$createCapturedIfNeeded, TypeParameterDescriptor typeParameterDescriptor) {
        if (typeParameterDescriptor == null || $this$createCapturedIfNeeded.getProjectionKind() == Variance.INVARIANT) {
            return $this$createCapturedIfNeeded;
        }
        if (typeParameterDescriptor.getVariance() == $this$createCapturedIfNeeded.getProjectionKind()) {
            if ($this$createCapturedIfNeeded.isStarProjection()) {
                StorageManager NO_LOCKS = LockBasedStorageManager.NO_LOCKS;
                Intrinsics.checkNotNullExpressionValue(NO_LOCKS, "NO_LOCKS");
                return new TypeProjectionImpl(new LazyWrappedType(NO_LOCKS, new Function0<KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt.createCapturedIfNeeded.1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    @NotNull
                    public final KotlinType invoke() {
                        KotlinType type = $this$createCapturedIfNeeded.getType();
                        Intrinsics.checkNotNullExpressionValue(type, "this@createCapturedIfNeeded.type");
                        return type;
                    }
                }));
            }
            return new TypeProjectionImpl($this$createCapturedIfNeeded.getType());
        }
        return new TypeProjectionImpl(createCapturedType($this$createCapturedIfNeeded));
    }
}
