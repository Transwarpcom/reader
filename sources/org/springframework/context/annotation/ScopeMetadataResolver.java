package org.springframework.context.annotation;

import org.springframework.beans.factory.config.BeanDefinition;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/annotation/ScopeMetadataResolver.class */
public interface ScopeMetadataResolver {
    ScopeMetadata resolveScopeMetadata(BeanDefinition beanDefinition);
}
