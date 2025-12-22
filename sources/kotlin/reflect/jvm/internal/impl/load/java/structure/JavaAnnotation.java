package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: javaElements.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/structure/JavaAnnotation.class */
public interface JavaAnnotation extends JavaElement {
    @NotNull
    Collection<JavaAnnotationArgument> getArguments();

    @Nullable
    ClassId getClassId();

    boolean isIdeExternalAnnotation();

    boolean isFreshlySupportedTypeUseAnnotation();

    @Nullable
    JavaClass resolve();

    /* compiled from: javaElements.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/structure/JavaAnnotation$DefaultImpls.class */
    public static final class DefaultImpls {
        public static boolean isIdeExternalAnnotation(@NotNull JavaAnnotation javaAnnotation) {
            Intrinsics.checkNotNullParameter(javaAnnotation, "this");
            return false;
        }

        public static boolean isFreshlySupportedTypeUseAnnotation(@NotNull JavaAnnotation javaAnnotation) {
            Intrinsics.checkNotNullParameter(javaAnnotation, "this");
            return false;
        }
    }
}
