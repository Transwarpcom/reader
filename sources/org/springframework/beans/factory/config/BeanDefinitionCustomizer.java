package org.springframework.beans.factory.config;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/config/BeanDefinitionCustomizer.class */
public interface BeanDefinitionCustomizer {
    void customize(BeanDefinition beanDefinition);
}
