package com.fasterxml.jackson.module.kotlin;

import java.lang.annotation.Annotation;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinModule.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\u001a\u000e\u0010\u0002\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\u0004\"\u000e\u0010��\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��¨\u0006\u0005"}, d2 = {"metadataFqName", "", "isKotlinClass", "", "Ljava/lang/Class;", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinModuleKt.class */
public final class KotlinModuleKt {

    @NotNull
    private static final String metadataFqName = "kotlin.Metadata";

    public static final boolean isKotlinClass(@NotNull Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Object[] declaredAnnotations = cls.getDeclaredAnnotations();
        Intrinsics.checkNotNullExpressionValue(declaredAnnotations, "declaredAnnotations");
        Object[] $this$any$iv = declaredAnnotations;
        for (Object element$iv : $this$any$iv) {
            Annotation it = (Annotation) element$iv;
            if (Intrinsics.areEqual(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(it)).getName(), metadataFqName)) {
                return true;
            }
        }
        return false;
    }
}
