package kotlin.reflect.jvm.internal.impl.load.java.structure;

import org.jetbrains.annotations.NotNull;

/* compiled from: javaTypes.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/structure/JavaArrayType.class */
public interface JavaArrayType extends JavaType {
    @NotNull
    JavaType getComponentType();
}
