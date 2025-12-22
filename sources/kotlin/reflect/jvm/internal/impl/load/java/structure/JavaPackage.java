package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: javaElements.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/structure/JavaPackage.class */
public interface JavaPackage extends JavaAnnotationOwner, JavaElement {
    @NotNull
    FqName getFqName();

    @NotNull
    Collection<JavaPackage> getSubPackages();

    @NotNull
    Collection<JavaClass> getClasses(@NotNull Function1<? super Name, Boolean> function1);
}
