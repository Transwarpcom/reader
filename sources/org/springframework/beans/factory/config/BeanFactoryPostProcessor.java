package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/config/BeanFactoryPostProcessor.class */
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException;
}
