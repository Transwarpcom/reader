package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanPostProcessor;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/support/MergedBeanDefinitionPostProcessor.class */
public interface MergedBeanDefinitionPostProcessor extends BeanPostProcessor {
    void postProcessMergedBeanDefinition(RootBeanDefinition rootBeanDefinition, Class<?> cls, String str);

    default void resetBeanDefinition(String beanName) {
    }
}
