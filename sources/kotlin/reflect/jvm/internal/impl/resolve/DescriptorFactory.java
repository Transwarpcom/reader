package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collections;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ReceiverParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ExtensionReceiver;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/DescriptorFactory.class */
public class DescriptorFactory {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 12:
            case 23:
            case 25:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            default:
                i2 = 3;
                break;
            case 12:
            case 23:
            case 25:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 3:
            case 7:
            case 13:
            case 15:
            case 17:
            default:
                objArr[0] = "propertyDescriptor";
                break;
            case 1:
            case 4:
            case 8:
            case 14:
            case 16:
            case 18:
            case 30:
                objArr[0] = "annotations";
                break;
            case 2:
            case 5:
            case 9:
                objArr[0] = "parameterAnnotations";
                break;
            case 6:
            case 11:
            case 19:
                objArr[0] = "sourceElement";
                break;
            case 10:
                objArr[0] = "visibility";
                break;
            case 12:
            case 23:
            case 25:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorFactory";
                break;
            case 20:
                objArr[0] = "containingClass";
                break;
            case 21:
                objArr[0] = PackageDocumentBase.DCTags.source;
                break;
            case 22:
            case 24:
                objArr[0] = "enumClass";
                break;
            case 26:
            case 27:
            case 28:
                objArr[0] = "descriptor";
                break;
            case 29:
                objArr[0] = "owner";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorFactory";
                break;
            case 12:
                objArr[1] = "createSetter";
                break;
            case 23:
                objArr[1] = "createEnumValuesMethod";
                break;
            case 25:
                objArr[1] = "createEnumValueOfMethod";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            default:
                objArr[2] = "createDefaultSetter";
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[2] = "createSetter";
                break;
            case 12:
            case 23:
            case 25:
                break;
            case 13:
            case 14:
                objArr[2] = "createDefaultGetter";
                break;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                objArr[2] = "createGetter";
                break;
            case 20:
            case 21:
                objArr[2] = "createPrimaryConstructorForObject";
                break;
            case 22:
                objArr[2] = "createEnumValuesMethod";
                break;
            case 24:
                objArr[2] = "createEnumValueOfMethod";
                break;
            case 26:
                objArr[2] = "isEnumValuesMethod";
                break;
            case 27:
                objArr[2] = "isEnumValueOfMethod";
                break;
            case 28:
                objArr[2] = "isEnumSpecialMethod";
                break;
            case 29:
            case 30:
                objArr[2] = "createExtensionReceiverParameterForCallable";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            default:
                throw new IllegalArgumentException(str2);
            case 12:
            case 23:
            case 25:
                throw new IllegalStateException(str2);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/DescriptorFactory$DefaultClassConstructorDescriptor.class */
    private static class DefaultClassConstructorDescriptor extends ClassConstructorDescriptorImpl {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "containingClass";
                    break;
                case 1:
                    objArr[0] = PackageDocumentBase.DCTags.source;
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorFactory$DefaultClassConstructorDescriptor";
            objArr[2] = Constants.CONSTRUCTOR_NAME;
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DefaultClassConstructorDescriptor(@NotNull ClassDescriptor containingClass, @NotNull SourceElement source, boolean freedomForSealedInterfacesSupported) {
            super(containingClass, null, Annotations.Companion.getEMPTY(), true, CallableMemberDescriptor.Kind.DECLARATION, source);
            if (containingClass == null) {
                $$$reportNull$$$0(0);
            }
            if (source == null) {
                $$$reportNull$$$0(1);
            }
            initialize(Collections.emptyList(), DescriptorUtils.getDefaultConstructorVisibility(containingClass, freedomForSealedInterfacesSupported));
        }
    }

    @NotNull
    public static PropertySetterDescriptorImpl createDefaultSetter(@NotNull PropertyDescriptor propertyDescriptor, @NotNull Annotations annotations, @NotNull Annotations parameterAnnotations) {
        if (propertyDescriptor == null) {
            $$$reportNull$$$0(0);
        }
        if (annotations == null) {
            $$$reportNull$$$0(1);
        }
        if (parameterAnnotations == null) {
            $$$reportNull$$$0(2);
        }
        return createSetter(propertyDescriptor, annotations, parameterAnnotations, true, false, false, propertyDescriptor.getSource());
    }

    @NotNull
    public static PropertySetterDescriptorImpl createSetter(@NotNull PropertyDescriptor propertyDescriptor, @NotNull Annotations annotations, @NotNull Annotations parameterAnnotations, boolean isDefault, boolean isExternal, boolean isInline, @NotNull SourceElement sourceElement) {
        if (propertyDescriptor == null) {
            $$$reportNull$$$0(3);
        }
        if (annotations == null) {
            $$$reportNull$$$0(4);
        }
        if (parameterAnnotations == null) {
            $$$reportNull$$$0(5);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(6);
        }
        return createSetter(propertyDescriptor, annotations, parameterAnnotations, isDefault, isExternal, isInline, propertyDescriptor.getVisibility(), sourceElement);
    }

    @NotNull
    public static PropertySetterDescriptorImpl createSetter(@NotNull PropertyDescriptor propertyDescriptor, @NotNull Annotations annotations, @NotNull Annotations parameterAnnotations, boolean isDefault, boolean isExternal, boolean isInline, @NotNull DescriptorVisibility visibility, @NotNull SourceElement sourceElement) {
        if (propertyDescriptor == null) {
            $$$reportNull$$$0(7);
        }
        if (annotations == null) {
            $$$reportNull$$$0(8);
        }
        if (parameterAnnotations == null) {
            $$$reportNull$$$0(9);
        }
        if (visibility == null) {
            $$$reportNull$$$0(10);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(11);
        }
        PropertySetterDescriptorImpl setterDescriptor = new PropertySetterDescriptorImpl(propertyDescriptor, annotations, propertyDescriptor.getModality(), visibility, isDefault, isExternal, isInline, CallableMemberDescriptor.Kind.DECLARATION, null, sourceElement);
        ValueParameterDescriptorImpl parameter = PropertySetterDescriptorImpl.createSetterParameter(setterDescriptor, propertyDescriptor.getType(), parameterAnnotations);
        setterDescriptor.initialize(parameter);
        if (setterDescriptor == null) {
            $$$reportNull$$$0(12);
        }
        return setterDescriptor;
    }

    @NotNull
    public static PropertyGetterDescriptorImpl createDefaultGetter(@NotNull PropertyDescriptor propertyDescriptor, @NotNull Annotations annotations) {
        if (propertyDescriptor == null) {
            $$$reportNull$$$0(13);
        }
        if (annotations == null) {
            $$$reportNull$$$0(14);
        }
        return createGetter(propertyDescriptor, annotations, true, false, false);
    }

    @NotNull
    public static PropertyGetterDescriptorImpl createGetter(@NotNull PropertyDescriptor propertyDescriptor, @NotNull Annotations annotations, boolean isDefault, boolean isExternal, boolean isInline) {
        if (propertyDescriptor == null) {
            $$$reportNull$$$0(15);
        }
        if (annotations == null) {
            $$$reportNull$$$0(16);
        }
        return createGetter(propertyDescriptor, annotations, isDefault, isExternal, isInline, propertyDescriptor.getSource());
    }

    @NotNull
    public static PropertyGetterDescriptorImpl createGetter(@NotNull PropertyDescriptor propertyDescriptor, @NotNull Annotations annotations, boolean isDefault, boolean isExternal, boolean isInline, @NotNull SourceElement sourceElement) {
        if (propertyDescriptor == null) {
            $$$reportNull$$$0(17);
        }
        if (annotations == null) {
            $$$reportNull$$$0(18);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(19);
        }
        return new PropertyGetterDescriptorImpl(propertyDescriptor, annotations, propertyDescriptor.getModality(), propertyDescriptor.getVisibility(), isDefault, isExternal, isInline, CallableMemberDescriptor.Kind.DECLARATION, null, sourceElement);
    }

    @NotNull
    public static ClassConstructorDescriptorImpl createPrimaryConstructorForObject(@NotNull ClassDescriptor containingClass, @NotNull SourceElement source) {
        if (containingClass == null) {
            $$$reportNull$$$0(20);
        }
        if (source == null) {
            $$$reportNull$$$0(21);
        }
        return new DefaultClassConstructorDescriptor(containingClass, source, false);
    }

    @NotNull
    public static SimpleFunctionDescriptor createEnumValuesMethod(@NotNull ClassDescriptor enumClass) {
        if (enumClass == null) {
            $$$reportNull$$$0(22);
        }
        SimpleFunctionDescriptorImpl values = SimpleFunctionDescriptorImpl.create(enumClass, Annotations.Companion.getEMPTY(), StandardNames.ENUM_VALUES, CallableMemberDescriptor.Kind.SYNTHESIZED, enumClass.getSource());
        SimpleFunctionDescriptorImpl simpleFunctionDescriptorImplInitialize = values.initialize((ReceiverParameterDescriptor) null, (ReceiverParameterDescriptor) null, Collections.emptyList(), Collections.emptyList(), (KotlinType) DescriptorUtilsKt.getBuiltIns(enumClass).getArrayType(Variance.INVARIANT, enumClass.getDefaultType()), Modality.FINAL, DescriptorVisibilities.PUBLIC);
        if (simpleFunctionDescriptorImplInitialize == null) {
            $$$reportNull$$$0(23);
        }
        return simpleFunctionDescriptorImplInitialize;
    }

    @NotNull
    public static SimpleFunctionDescriptor createEnumValueOfMethod(@NotNull ClassDescriptor enumClass) {
        if (enumClass == null) {
            $$$reportNull$$$0(24);
        }
        SimpleFunctionDescriptorImpl valueOf = SimpleFunctionDescriptorImpl.create(enumClass, Annotations.Companion.getEMPTY(), StandardNames.ENUM_VALUE_OF, CallableMemberDescriptor.Kind.SYNTHESIZED, enumClass.getSource());
        ValueParameterDescriptor parameterDescriptor = new ValueParameterDescriptorImpl(valueOf, null, 0, Annotations.Companion.getEMPTY(), Name.identifier("value"), DescriptorUtilsKt.getBuiltIns(enumClass).getStringType(), false, false, false, null, enumClass.getSource());
        SimpleFunctionDescriptorImpl simpleFunctionDescriptorImplInitialize = valueOf.initialize((ReceiverParameterDescriptor) null, (ReceiverParameterDescriptor) null, Collections.emptyList(), Collections.singletonList(parameterDescriptor), (KotlinType) enumClass.getDefaultType(), Modality.FINAL, DescriptorVisibilities.PUBLIC);
        if (simpleFunctionDescriptorImplInitialize == null) {
            $$$reportNull$$$0(25);
        }
        return simpleFunctionDescriptorImplInitialize;
    }

    public static boolean isEnumValuesMethod(@NotNull FunctionDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(26);
        }
        return descriptor.getName().equals(StandardNames.ENUM_VALUES) && isEnumSpecialMethod(descriptor);
    }

    public static boolean isEnumValueOfMethod(@NotNull FunctionDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(27);
        }
        return descriptor.getName().equals(StandardNames.ENUM_VALUE_OF) && isEnumSpecialMethod(descriptor);
    }

    private static boolean isEnumSpecialMethod(@NotNull FunctionDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(28);
        }
        return descriptor.getKind() == CallableMemberDescriptor.Kind.SYNTHESIZED && DescriptorUtils.isEnumClass(descriptor.getContainingDeclaration());
    }

    @Nullable
    public static ReceiverParameterDescriptor createExtensionReceiverParameterForCallable(@NotNull CallableDescriptor owner, @Nullable KotlinType receiverParameterType, @NotNull Annotations annotations) {
        if (owner == null) {
            $$$reportNull$$$0(29);
        }
        if (annotations == null) {
            $$$reportNull$$$0(30);
        }
        if (receiverParameterType == null) {
            return null;
        }
        return new ReceiverParameterDescriptorImpl(owner, new ExtensionReceiver(owner, receiverParameterType, null), annotations);
    }
}
