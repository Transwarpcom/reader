package kotlin.reflect.jvm.internal.impl.load.java.structure;

import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.Nullable;

/* compiled from: annotationArguments.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/structure/JavaEnumValueAnnotationArgument.class */
public interface JavaEnumValueAnnotationArgument extends JavaAnnotationArgument {
    @Nullable
    ClassId getEnumClassId();

    @Nullable
    Name getEntryName();
}
