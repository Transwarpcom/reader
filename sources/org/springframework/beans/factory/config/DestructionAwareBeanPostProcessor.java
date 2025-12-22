package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/config/DestructionAwareBeanPostProcessor.class */
public interface DestructionAwareBeanPostProcessor extends BeanPostProcessor {
    void postProcessBeforeDestruction(Object obj, String str) throws BeansException;

    default boolean requiresDestruction(Object bean) {
        return true;
    }
}
