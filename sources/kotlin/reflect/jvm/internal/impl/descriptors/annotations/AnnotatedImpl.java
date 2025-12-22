package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotatedImpl.class */
public class AnnotatedImpl implements Annotated {
    private final Annotations annotations;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 1:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            default:
                i2 = 3;
                break;
            case 1:
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
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotatedImpl";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotatedImpl";
                break;
            case 1:
                objArr[1] = "getAnnotations";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 1:
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            default:
                throw new IllegalArgumentException(str2);
            case 1:
                throw new IllegalStateException(str2);
        }
    }

    public AnnotatedImpl(@NotNull Annotations annotations) {
        if (annotations == null) {
            $$$reportNull$$$0(0);
        }
        this.annotations = annotations;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    @NotNull
    public Annotations getAnnotations() {
        Annotations annotations = this.annotations;
        if (annotations == null) {
            $$$reportNull$$$0(1);
        }
        return annotations;
    }
}
