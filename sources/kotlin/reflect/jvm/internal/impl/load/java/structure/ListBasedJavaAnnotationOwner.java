package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: javaElements.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/structure/ListBasedJavaAnnotationOwner.class */
public interface ListBasedJavaAnnotationOwner extends JavaAnnotationOwner {

    /* compiled from: javaElements.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/structure/ListBasedJavaAnnotationOwner$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static JavaAnnotation findAnnotation(@NotNull ListBasedJavaAnnotationOwner listBasedJavaAnnotationOwner, @NotNull FqName fqName) {
            Object obj;
            Intrinsics.checkNotNullParameter(listBasedJavaAnnotationOwner, "this");
            Intrinsics.checkNotNullParameter(fqName, "fqName");
            Iterator<T> it = listBasedJavaAnnotationOwner.getAnnotations().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                Object next = it.next();
                JavaAnnotation it2 = (JavaAnnotation) next;
                ClassId classId = it2.getClassId();
                if (Intrinsics.areEqual(classId == null ? null : classId.asSingleFqName(), fqName)) {
                    obj = next;
                    break;
                }
            }
            return (JavaAnnotation) obj;
        }
    }
}
