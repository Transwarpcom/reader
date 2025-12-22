package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/impl/VariableDescriptorWithInitializerImpl.class */
public abstract class VariableDescriptorWithInitializerImpl extends VariableDescriptorImpl {
    private final boolean isVar;
    protected NullableLazyValue<ConstantValue<?>> compileTimeInitializer;
    static final /* synthetic */ boolean $assertionsDisabled;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        switch (i) {
            case 0:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 1:
                objArr[0] = "annotations";
                break;
            case 2:
                objArr[0] = "name";
                break;
            case 3:
                objArr[0] = PackageDocumentBase.DCTags.source;
                break;
            case 4:
                objArr[0] = "compileTimeInitializer";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/VariableDescriptorWithInitializerImpl";
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            default:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 4:
                objArr[2] = "setCompileTimeInitializer";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    static {
        $assertionsDisabled = !VariableDescriptorWithInitializerImpl.class.desiredAssertionStatus();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VariableDescriptorWithInitializerImpl(@NotNull DeclarationDescriptor containingDeclaration, @NotNull Annotations annotations, @NotNull Name name, @Nullable KotlinType outType, boolean isVar, @NotNull SourceElement source) {
        super(containingDeclaration, annotations, name, outType, source);
        if (containingDeclaration == null) {
            $$$reportNull$$$0(0);
        }
        if (annotations == null) {
            $$$reportNull$$$0(1);
        }
        if (name == null) {
            $$$reportNull$$$0(2);
        }
        if (source == null) {
            $$$reportNull$$$0(3);
        }
        this.isVar = isVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public boolean isVar() {
        return this.isVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    @Nullable
    /* renamed from: getCompileTimeInitializer */
    public ConstantValue<?> mo3574getCompileTimeInitializer() {
        if (this.compileTimeInitializer != null) {
            return this.compileTimeInitializer.invoke();
        }
        return null;
    }

    public void setCompileTimeInitializer(@NotNull NullableLazyValue<ConstantValue<?>> compileTimeInitializer) {
        if (compileTimeInitializer == null) {
            $$$reportNull$$$0(4);
        }
        if (!$assertionsDisabled && isVar()) {
            throw new AssertionError("Constant value for variable initializer should be recorded only for final variables: " + getName());
        }
        this.compileTimeInitializer = compileTimeInitializer;
    }
}
