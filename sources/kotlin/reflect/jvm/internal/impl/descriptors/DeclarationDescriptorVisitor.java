package kotlin.reflect.jvm.internal.impl.descriptors;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/DeclarationDescriptorVisitor.class */
public interface DeclarationDescriptorVisitor<R, D> {
    R visitPackageFragmentDescriptor(PackageFragmentDescriptor packageFragmentDescriptor, D d);

    R visitPackageViewDescriptor(PackageViewDescriptor packageViewDescriptor, D d);

    R visitFunctionDescriptor(FunctionDescriptor functionDescriptor, D d);

    R visitTypeParameterDescriptor(TypeParameterDescriptor typeParameterDescriptor, D d);

    R visitClassDescriptor(ClassDescriptor classDescriptor, D d);

    R visitTypeAliasDescriptor(TypeAliasDescriptor typeAliasDescriptor, D d);

    R visitModuleDeclaration(ModuleDescriptor moduleDescriptor, D d);

    R visitConstructorDescriptor(ConstructorDescriptor constructorDescriptor, D d);

    R visitPropertyDescriptor(PropertyDescriptor propertyDescriptor, D d);

    R visitValueParameterDescriptor(ValueParameterDescriptor valueParameterDescriptor, D d);

    R visitPropertyGetterDescriptor(PropertyGetterDescriptor propertyGetterDescriptor, D d);

    R visitPropertySetterDescriptor(PropertySetterDescriptor propertySetterDescriptor, D d);

    R visitReceiverParameterDescriptor(ReceiverParameterDescriptor receiverParameterDescriptor, D d);
}
