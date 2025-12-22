package org.springframework.boot.context.properties;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/ConfigurationBeanFactoryMetadata.class */
public class ConfigurationBeanFactoryMetadata implements BeanFactoryPostProcessor {
    public static final String BEAN_NAME = ConfigurationBeanFactoryMetadata.class.getName();
    private ConfigurableListableBeanFactory beanFactory;
    private final Map<String, FactoryMetadata> beansFactoryMetadata = new HashMap();

    @Override // org.springframework.beans.factory.config.BeanFactoryPostProcessor
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        for (String name : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition definition = beanFactory.getBeanDefinition(name);
            String method = definition.getFactoryMethodName();
            String bean = definition.getFactoryBeanName();
            if (method != null && bean != null) {
                this.beansFactoryMetadata.put(name, new FactoryMetadata(bean, method));
            }
        }
    }

    public <A extends Annotation> Map<String, Object> getBeansWithFactoryAnnotation(Class<A> type) {
        Map<String, Object> result = new HashMap<>();
        for (String name : this.beansFactoryMetadata.keySet()) {
            if (findFactoryAnnotation(name, type) != null) {
                result.put(name, this.beanFactory.getBean(name));
            }
        }
        return result;
    }

    public <A extends Annotation> A findFactoryAnnotation(String str, Class<A> cls) throws IllegalArgumentException {
        Method methodFindFactoryMethod = findFactoryMethod(str);
        if (methodFindFactoryMethod != null) {
            return (A) AnnotationUtils.findAnnotation(methodFindFactoryMethod, (Class) cls);
        }
        return null;
    }

    public Method findFactoryMethod(String beanName) throws IllegalArgumentException {
        if (!this.beansFactoryMetadata.containsKey(beanName)) {
            return null;
        }
        AtomicReference<Method> found = new AtomicReference<>(null);
        FactoryMetadata metadata = this.beansFactoryMetadata.get(beanName);
        Class<?> factoryType = this.beanFactory.getType(metadata.getBean());
        String factoryMethod = metadata.getMethod();
        if (ClassUtils.isCglibProxyClass(factoryType)) {
            factoryType = factoryType.getSuperclass();
        }
        ReflectionUtils.doWithMethods(factoryType, method -> {
            if (method.getName().equals(factoryMethod)) {
                found.compareAndSet(null, method);
            }
        });
        return found.get();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/ConfigurationBeanFactoryMetadata$FactoryMetadata.class */
    private static class FactoryMetadata {
        private final String bean;
        private final String method;

        FactoryMetadata(String bean, String method) {
            this.bean = bean;
            this.method = method;
        }

        public String getBean() {
            return this.bean;
        }

        public String getMethod() {
            return this.method;
        }
    }
}
