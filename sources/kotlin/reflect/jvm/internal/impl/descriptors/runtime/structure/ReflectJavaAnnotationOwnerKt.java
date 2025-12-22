package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReflectJavaAnnotationOwner.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/structure/ReflectJavaAnnotationOwnerKt.class */
public final class ReflectJavaAnnotationOwnerKt {
    @NotNull
    public static final List<ReflectJavaAnnotation> getAnnotations(@NotNull Annotation[] $this$getAnnotations) {
        Intrinsics.checkNotNullParameter($this$getAnnotations, "<this>");
        Collection destination$iv$iv = new ArrayList($this$getAnnotations.length);
        for (Annotation annotation : $this$getAnnotations) {
            destination$iv$iv.add(new ReflectJavaAnnotation(annotation));
        }
        return (List) destination$iv$iv;
    }

    @Nullable
    public static final ReflectJavaAnnotation findAnnotation(@NotNull Annotation[] $this$findAnnotation, @NotNull FqName fqName) {
        Annotation annotation;
        Intrinsics.checkNotNullParameter($this$findAnnotation, "<this>");
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        int length = $this$findAnnotation.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                annotation = null;
                break;
            }
            Annotation annotation2 = $this$findAnnotation[i];
            if (Intrinsics.areEqual(ReflectClassUtilKt.getClassId(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation2))).asSingleFqName(), fqName)) {
                annotation = annotation2;
                break;
            }
            i++;
        }
        Annotation p0 = annotation;
        if (p0 == null) {
            return null;
        }
        return new ReflectJavaAnnotation(p0);
    }
}
