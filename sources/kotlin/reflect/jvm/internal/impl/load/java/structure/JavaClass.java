package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: javaElements.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/structure/JavaClass.class */
public interface JavaClass extends JavaClassifier, JavaModifierListOwner, JavaTypeParameterListOwner {
    @Nullable
    FqName getFqName();

    @NotNull
    Collection<JavaClassifierType> getSupertypes();

    @NotNull
    Collection<Name> getInnerClassNames();

    @Nullable
    JavaClass getOuterClass();

    boolean isInterface();

    boolean isAnnotationType();

    boolean isEnum();

    boolean isRecord();

    boolean isSealed();

    @NotNull
    Collection<JavaClassifierType> getPermittedTypes();

    @Nullable
    LightClassOriginKind getLightClassOriginKind();

    @NotNull
    Collection<JavaMethod> getMethods();

    @NotNull
    Collection<JavaField> getFields();

    @NotNull
    Collection<JavaConstructor> getConstructors();

    @NotNull
    Collection<JavaRecordComponent> getRecordComponents();

    boolean hasDefaultConstructor();
}
