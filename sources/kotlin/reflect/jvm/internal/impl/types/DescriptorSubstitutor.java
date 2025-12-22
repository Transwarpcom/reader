package kotlin.reflect.jvm.internal.impl.types;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/DescriptorSubstitutor.class */
public class DescriptorSubstitutor {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 4:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            default:
                i2 = 3;
                break;
            case 4:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 5:
            default:
                objArr[0] = "typeParameters";
                break;
            case 1:
            case 6:
                objArr[0] = "originalSubstitution";
                break;
            case 2:
            case 7:
                objArr[0] = "newContainingDeclaration";
                break;
            case 3:
            case 8:
                objArr[0] = CacheOperationExpressionEvaluator.RESULT_VARIABLE;
                break;
            case 4:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/DescriptorSubstitutor";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/types/DescriptorSubstitutor";
                break;
            case 4:
                objArr[1] = "substituteTypeParameters";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            default:
                objArr[2] = "substituteTypeParameters";
                break;
            case 4:
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            default:
                throw new IllegalArgumentException(str2);
            case 4:
                throw new IllegalStateException(str2);
        }
    }

    @NotNull
    public static TypeSubstitutor substituteTypeParameters(@NotNull List<TypeParameterDescriptor> typeParameters, @NotNull TypeSubstitution originalSubstitution, @NotNull DeclarationDescriptor newContainingDeclaration, @NotNull List<TypeParameterDescriptor> result) {
        if (typeParameters == null) {
            $$$reportNull$$$0(0);
        }
        if (originalSubstitution == null) {
            $$$reportNull$$$0(1);
        }
        if (newContainingDeclaration == null) {
            $$$reportNull$$$0(2);
        }
        if (result == null) {
            $$$reportNull$$$0(3);
        }
        TypeSubstitutor substitutor = substituteTypeParameters(typeParameters, originalSubstitution, newContainingDeclaration, result, null);
        if (substitutor == null) {
            throw new AssertionError("Substitution failed");
        }
        if (substitutor == null) {
            $$$reportNull$$$0(4);
        }
        return substitutor;
    }

    @Nullable
    public static TypeSubstitutor substituteTypeParameters(@NotNull List<TypeParameterDescriptor> typeParameters, @NotNull TypeSubstitution originalSubstitution, @NotNull DeclarationDescriptor newContainingDeclaration, @NotNull List<TypeParameterDescriptor> result, @Nullable boolean[] wereChanges) {
        if (typeParameters == null) {
            $$$reportNull$$$0(5);
        }
        if (originalSubstitution == null) {
            $$$reportNull$$$0(6);
        }
        if (newContainingDeclaration == null) {
            $$$reportNull$$$0(7);
        }
        if (result == null) {
            $$$reportNull$$$0(8);
        }
        Map<TypeConstructor, TypeProjection> mutableSubstitution = new HashMap<>();
        Map<TypeParameterDescriptor, TypeParameterDescriptorImpl> substitutedMap = new HashMap<>();
        int index = 0;
        for (TypeParameterDescriptor descriptor : typeParameters) {
            int i = index;
            index++;
            TypeParameterDescriptorImpl substituted = TypeParameterDescriptorImpl.createForFurtherModification(newContainingDeclaration, descriptor.getAnnotations(), descriptor.isReified(), descriptor.getVariance(), descriptor.getName(), i, SourceElement.NO_SOURCE, descriptor.getStorageManager());
            mutableSubstitution.put(descriptor.getTypeConstructor(), new TypeProjectionImpl(substituted.getDefaultType()));
            substitutedMap.put(descriptor, substituted);
            result.add(substituted);
        }
        TypeSubstitutor substitutor = TypeSubstitutor.createChainedSubstitutor(originalSubstitution, TypeConstructorSubstitution.createByConstructorsMap(mutableSubstitution));
        for (TypeParameterDescriptor descriptor2 : typeParameters) {
            TypeParameterDescriptorImpl substituted2 = substitutedMap.get(descriptor2);
            for (KotlinType upperBound : descriptor2.getUpperBounds()) {
                KotlinType substitutedBound = substitutor.substitute(upperBound, Variance.IN_VARIANCE);
                if (substitutedBound == null) {
                    return null;
                }
                if (substitutedBound != upperBound && wereChanges != null) {
                    wereChanges[0] = true;
                }
                substituted2.addUpperBound(substitutedBound);
            }
            substituted2.setInitialized();
        }
        return substitutor;
    }
}
