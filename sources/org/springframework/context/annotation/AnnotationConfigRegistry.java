package org.springframework.context.annotation;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/annotation/AnnotationConfigRegistry.class */
public interface AnnotationConfigRegistry {
    void register(Class<?>... clsArr);

    void scan(String... strArr);
}
