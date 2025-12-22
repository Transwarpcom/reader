package org.springframework.core.type;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/type/MethodMetadata.class */
public interface MethodMetadata extends AnnotatedTypeMetadata {
    String getMethodName();

    String getDeclaringClassName();

    String getReturnTypeName();

    boolean isAbstract();

    boolean isStatic();

    boolean isFinal();

    boolean isOverridable();
}
