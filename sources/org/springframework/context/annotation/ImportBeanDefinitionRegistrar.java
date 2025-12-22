package org.springframework.context.annotation;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.type.AnnotationMetadata;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/annotation/ImportBeanDefinitionRegistrar.class */
public interface ImportBeanDefinitionRegistrar {
    void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry);
}
