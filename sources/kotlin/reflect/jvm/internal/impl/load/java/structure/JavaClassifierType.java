package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: javaTypes.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/structure/JavaClassifierType.class */
public interface JavaClassifierType extends JavaType {
    @Nullable
    JavaClassifier getClassifier();

    @NotNull
    List<JavaType> getTypeArguments();

    boolean isRaw();

    @NotNull
    String getClassifierQualifiedName();

    @NotNull
    String getPresentableText();
}
