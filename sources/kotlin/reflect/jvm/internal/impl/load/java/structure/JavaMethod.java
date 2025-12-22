package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: javaElements.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/structure/JavaMethod.class */
public interface JavaMethod extends JavaMember, JavaTypeParameterListOwner {
    @NotNull
    List<JavaValueParameter> getValueParameters();

    @NotNull
    JavaType getReturnType();

    @Nullable
    JavaAnnotationArgument getAnnotationParameterDefaultValue();

    boolean getHasAnnotationParameterDefaultValue();

    /* compiled from: javaElements.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/structure/JavaMethod$DefaultImpls.class */
    public static final class DefaultImpls {
        public static boolean getHasAnnotationParameterDefaultValue(@NotNull JavaMethod javaMethod) {
            Intrinsics.checkNotNullParameter(javaMethod, "this");
            return javaMethod.getAnnotationParameterDefaultValue() != null;
        }
    }
}
