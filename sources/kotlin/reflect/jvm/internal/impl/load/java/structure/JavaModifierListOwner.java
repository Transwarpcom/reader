package kotlin.reflect.jvm.internal.impl.load.java.structure;

import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import org.jetbrains.annotations.NotNull;

/* compiled from: javaElements.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/structure/JavaModifierListOwner.class */
public interface JavaModifierListOwner extends JavaElement {
    boolean isAbstract();

    boolean isStatic();

    boolean isFinal();

    @NotNull
    Visibility getVisibility();
}
