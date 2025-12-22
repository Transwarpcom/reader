package org.springframework.boot.web.servlet;

import java.util.Map;
import javax.servlet.annotation.WebListener;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/servlet/WebListenerHandler.class */
class WebListenerHandler extends ServletComponentHandler {
    WebListenerHandler() {
        super(WebListener.class);
    }

    @Override // org.springframework.boot.web.servlet.ServletComponentHandler
    protected void doHandle(Map<String, Object> attributes, ScannedGenericBeanDefinition beanDefinition, BeanDefinitionRegistry registry) throws BeanDefinitionStoreException {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition((Class<?>) ServletListenerRegistrationBean.class);
        builder.addPropertyValue("listener", beanDefinition);
        registry.registerBeanDefinition(beanDefinition.getBeanClassName(), builder.getBeanDefinition());
    }
}
