package kotlin.reflect.jvm.internal.impl.load.java;

import org.jetbrains.annotations.NotNull;

/* compiled from: AnnotationQualifierApplicabilityType.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/AnnotationQualifierApplicabilityType.class */
public enum AnnotationQualifierApplicabilityType {
    METHOD_RETURN_TYPE("METHOD"),
    VALUE_PARAMETER("PARAMETER"),
    FIELD("FIELD"),
    TYPE_USE("TYPE_USE"),
    TYPE_PARAMETER_BOUNDS("TYPE_USE"),
    TYPE_PARAMETER("TYPE_PARAMETER");


    @NotNull
    private final String javaTarget;

    AnnotationQualifierApplicabilityType(String javaTarget) {
        this.javaTarget = javaTarget;
    }

    @NotNull
    public final String getJavaTarget() {
        return this.javaTarget;
    }
}
