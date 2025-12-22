package org.springframework.context.annotation;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/annotation/ScopedProxyCreator.class */
final class ScopedProxyCreator {
    private ScopedProxyCreator() {
    }

    public static BeanDefinitionHolder createScopedProxy(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry, boolean proxyTargetClass) {
        return ScopedProxyUtils.createScopedProxy(definitionHolder, registry, proxyTargetClass);
    }

    public static String getTargetBeanName(String originalBeanName) {
        return ScopedProxyUtils.getTargetBeanName(originalBeanName);
    }
}
