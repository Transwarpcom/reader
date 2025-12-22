package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import cn.hutool.core.text.StrPool;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotatedImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorImpl.class */
public abstract class DeclarationDescriptorImpl extends AnnotatedImpl implements DeclarationDescriptor {

    @NotNull
    private final Name name;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 4:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 2:
            case 3:
            case 5:
            case 6:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 4:
            default:
                i2 = 3;
                break;
            case 2:
            case 3:
            case 5:
            case 6:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            default:
                objArr[0] = "annotations";
                break;
            case 1:
                objArr[0] = "name";
                break;
            case 2:
            case 3:
            case 5:
            case 6:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorImpl";
                break;
            case 4:
                objArr[0] = "descriptor";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 4:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorImpl";
                break;
            case 2:
                objArr[1] = "getName";
                break;
            case 3:
                objArr[1] = "getOriginal";
                break;
            case 5:
            case 6:
                objArr[1] = "toString";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            default:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 2:
            case 3:
            case 5:
            case 6:
                break;
            case 4:
                objArr[2] = "toString";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 4:
            default:
                throw new IllegalArgumentException(str2);
            case 2:
            case 3:
            case 5:
            case 6:
                throw new IllegalStateException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeclarationDescriptorImpl(@NotNull Annotations annotations, @NotNull Name name) {
        super(annotations);
        if (annotations == null) {
            $$$reportNull$$$0(0);
        }
        if (name == null) {
            $$$reportNull$$$0(1);
        }
        this.name = name;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.Named
    @NotNull
    public Name getName() {
        Name name = this.name;
        if (name == null) {
            $$$reportNull$$$0(2);
        }
        return name;
    }

    @NotNull
    public DeclarationDescriptor getOriginal() {
        if (this == null) {
            $$$reportNull$$$0(3);
        }
        return this;
    }

    public String toString() {
        return toString(this);
    }

    @NotNull
    public static String toString(@NotNull DeclarationDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(4);
        }
        try {
            String str = DescriptorRenderer.DEBUG_TEXT.render(descriptor) + "[" + descriptor.getClass().getSimpleName() + StrPool.AT + Integer.toHexString(System.identityHashCode(descriptor)) + "]";
            if (str == null) {
                $$$reportNull$$$0(5);
            }
            return str;
        } catch (Throwable th) {
            String str2 = descriptor.getClass().getSimpleName() + " " + descriptor.getName();
            if (str2 == null) {
                $$$reportNull$$$0(6);
            }
            return str2;
        }
    }
}
