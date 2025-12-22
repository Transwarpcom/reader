package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/components/SignaturePropagator.class */
public interface SignaturePropagator {
    public static final SignaturePropagator DO_NOTHING = new SignaturePropagator() { // from class: kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator.1
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "method";
                    break;
                case 1:
                    objArr[0] = "owner";
                    break;
                case 2:
                    objArr[0] = "returnType";
                    break;
                case 3:
                    objArr[0] = "valueParameters";
                    break;
                case 4:
                    objArr[0] = "typeParameters";
                    break;
                case 5:
                    objArr[0] = "descriptor";
                    break;
                case 6:
                    objArr[0] = "signatureErrors";
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/SignaturePropagator$1";
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                default:
                    objArr[2] = "resolvePropagatedSignature";
                    break;
                case 5:
                case 6:
                    objArr[2] = "reportSignatureErrors";
                    break;
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator
        @NotNull
        public PropagatedSignature resolvePropagatedSignature(@NotNull JavaMethod method, @NotNull ClassDescriptor owner, @NotNull KotlinType returnType, @Nullable KotlinType receiverType, @NotNull List<ValueParameterDescriptor> valueParameters, @NotNull List<TypeParameterDescriptor> typeParameters) {
            if (method == null) {
                $$$reportNull$$$0(0);
            }
            if (owner == null) {
                $$$reportNull$$$0(1);
            }
            if (returnType == null) {
                $$$reportNull$$$0(2);
            }
            if (valueParameters == null) {
                $$$reportNull$$$0(3);
            }
            if (typeParameters == null) {
                $$$reportNull$$$0(4);
            }
            return new PropagatedSignature(returnType, receiverType, valueParameters, typeParameters, Collections.emptyList(), false);
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator
        public void reportSignatureErrors(@NotNull CallableMemberDescriptor descriptor, @NotNull List<String> signatureErrors) {
            if (descriptor == null) {
                $$$reportNull$$$0(5);
            }
            if (signatureErrors == null) {
                $$$reportNull$$$0(6);
            }
            throw new UnsupportedOperationException("Should not be called");
        }
    };

    @NotNull
    PropagatedSignature resolvePropagatedSignature(@NotNull JavaMethod javaMethod, @NotNull ClassDescriptor classDescriptor, @NotNull KotlinType kotlinType, @Nullable KotlinType kotlinType2, @NotNull List<ValueParameterDescriptor> list, @NotNull List<TypeParameterDescriptor> list2);

    void reportSignatureErrors(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull List<String> list);

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/components/SignaturePropagator$PropagatedSignature.class */
    public static class PropagatedSignature {
        private final KotlinType returnType;
        private final KotlinType receiverType;
        private final List<ValueParameterDescriptor> valueParameters;
        private final List<TypeParameterDescriptor> typeParameters;
        private final List<String> signatureErrors;
        private final boolean hasStableParameterNames;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str;
            int i2;
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                    str = "@NotNull method %s.%s must not return null";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                default:
                    i2 = 3;
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                    i2 = 2;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "returnType";
                    break;
                case 1:
                    objArr[0] = "valueParameters";
                    break;
                case 2:
                    objArr[0] = "typeParameters";
                    break;
                case 3:
                    objArr[0] = "signatureErrors";
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/components/SignaturePropagator$PropagatedSignature";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/SignaturePropagator$PropagatedSignature";
                    break;
                case 4:
                    objArr[1] = "getReturnType";
                    break;
                case 5:
                    objArr[1] = "getValueParameters";
                    break;
                case 6:
                    objArr[1] = "getTypeParameters";
                    break;
                case 7:
                    objArr[1] = "getErrors";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                default:
                    objArr[2] = Constants.CONSTRUCTOR_NAME;
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                    break;
            }
            String str2 = String.format(str, objArr);
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                default:
                    throw new IllegalArgumentException(str2);
                case 4:
                case 5:
                case 6:
                case 7:
                    throw new IllegalStateException(str2);
            }
        }

        public PropagatedSignature(@NotNull KotlinType returnType, @Nullable KotlinType receiverType, @NotNull List<ValueParameterDescriptor> valueParameters, @NotNull List<TypeParameterDescriptor> typeParameters, @NotNull List<String> signatureErrors, boolean hasStableParameterNames) {
            if (returnType == null) {
                $$$reportNull$$$0(0);
            }
            if (valueParameters == null) {
                $$$reportNull$$$0(1);
            }
            if (typeParameters == null) {
                $$$reportNull$$$0(2);
            }
            if (signatureErrors == null) {
                $$$reportNull$$$0(3);
            }
            this.returnType = returnType;
            this.receiverType = receiverType;
            this.valueParameters = valueParameters;
            this.typeParameters = typeParameters;
            this.signatureErrors = signatureErrors;
            this.hasStableParameterNames = hasStableParameterNames;
        }

        @NotNull
        public KotlinType getReturnType() {
            KotlinType kotlinType = this.returnType;
            if (kotlinType == null) {
                $$$reportNull$$$0(4);
            }
            return kotlinType;
        }

        @Nullable
        public KotlinType getReceiverType() {
            return this.receiverType;
        }

        @NotNull
        public List<ValueParameterDescriptor> getValueParameters() {
            List<ValueParameterDescriptor> list = this.valueParameters;
            if (list == null) {
                $$$reportNull$$$0(5);
            }
            return list;
        }

        @NotNull
        public List<TypeParameterDescriptor> getTypeParameters() {
            List<TypeParameterDescriptor> list = this.typeParameters;
            if (list == null) {
                $$$reportNull$$$0(6);
            }
            return list;
        }

        public boolean hasStableParameterNames() {
            return this.hasStableParameterNames;
        }

        @NotNull
        public List<String> getErrors() {
            List<String> list = this.signatureErrors;
            if (list == null) {
                $$$reportNull$$$0(7);
            }
            return list;
        }
    }
}
