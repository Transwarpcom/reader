package kotlin.reflect.jvm.internal.impl.load.java.structure;

import org.jetbrains.annotations.NotNull;

/* compiled from: javaElements.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/structure/JavaField.class */
public interface JavaField extends JavaMember {
    boolean isEnumEntry();

    @NotNull
    JavaType getType();

    boolean getHasConstantNotNullInitializer();
}
