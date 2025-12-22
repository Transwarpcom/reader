package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorBase.class */
public abstract class ClassDescriptorBase extends AbstractClassDescriptor {
    private final DeclarationDescriptor containingDeclaration;
    private final SourceElement source;
    private final boolean isExternal;

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
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            default:
                objArr[0] = "storageManager";
                break;
            case 1:
                objArr[0] = "containingDeclaration";
                break;
            case 2:
                objArr[0] = "name";
                break;
            case 3:
                objArr[0] = PackageDocumentBase.DCTags.source;
                break;
            case 4:
            case 5:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorBase";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorBase";
                break;
            case 4:
                objArr[1] = "getContainingDeclaration";
                break;
            case 5:
                objArr[1] = "getSource";
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
                throw new IllegalStateException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    protected ClassDescriptorBase(@NotNull StorageManager storageManager, @NotNull DeclarationDescriptor containingDeclaration, @NotNull Name name, @NotNull SourceElement source, boolean isExternal) {
        super(storageManager, name);
        if (storageManager == null) {
            $$$reportNull$$$0(0);
        }
        if (containingDeclaration == null) {
            $$$reportNull$$$0(1);
        }
        if (name == null) {
            $$$reportNull$$$0(2);
        }
        if (source == null) {
            $$$reportNull$$$0(3);
        }
        this.containingDeclaration = containingDeclaration;
        this.source = source;
        this.isExternal = isExternal;
    }

    public boolean isExternal() {
        return this.isExternal;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    public DeclarationDescriptor getContainingDeclaration() {
        DeclarationDescriptor declarationDescriptor = this.containingDeclaration;
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(4);
        }
        return declarationDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    @NotNull
    public SourceElement getSource() {
        SourceElement sourceElement = this.source;
        if (sourceElement == null) {
            $$$reportNull$$$0(5);
        }
        return sourceElement;
    }
}
