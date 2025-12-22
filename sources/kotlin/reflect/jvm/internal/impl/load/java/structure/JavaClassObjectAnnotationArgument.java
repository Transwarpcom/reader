package kotlin.reflect.jvm.internal.impl.load.java.structure;

import org.jetbrains.annotations.NotNull;

/* compiled from: annotationArguments.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/structure/JavaClassObjectAnnotationArgument.class */
public interface JavaClassObjectAnnotationArgument extends JavaAnnotationArgument {
    @NotNull
    JavaType getReferencedType();
}
