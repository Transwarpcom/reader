package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/DeclarationDescriptor.class */
public interface DeclarationDescriptor extends Named, Annotated {
    @NotNull
    DeclarationDescriptor getOriginal();

    @Nullable
    DeclarationDescriptor getContainingDeclaration();

    <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d);
}
