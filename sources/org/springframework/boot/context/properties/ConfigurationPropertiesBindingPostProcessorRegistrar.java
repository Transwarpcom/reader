package org.springframework.boot.context.properties;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/ConfigurationPropertiesBindingPostProcessorRegistrar.class */
public class ConfigurationPropertiesBindingPostProcessorRegistrar implements ImportBeanDefinitionRegistrar {
    @Override // org.springframework.context.annotation.ImportBeanDefinitionRegistrar
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) throws BeanDefinitionStoreException {
        if (!registry.containsBeanDefinition(ConfigurationPropertiesBindingPostProcessor.BEAN_NAME)) {
            registerConfigurationPropertiesBindingPostProcessor(registry);
            registerConfigurationBeanFactoryMetadata(registry);
        }
    }

    private void registerConfigurationPropertiesBindingPostProcessor(BeanDefinitionRegistry registry) throws BeanDefinitionStoreException {
        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setBeanClass(ConfigurationPropertiesBindingPostProcessor.class);
        definition.setRole(2);
        registry.registerBeanDefinition(ConfigurationPropertiesBindingPostProcessor.BEAN_NAME, definition);
    }

    private void registerConfigurationBeanFactoryMetadata(BeanDefinitionRegistry registry) throws BeanDefinitionStoreException {
        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setBeanClass(ConfigurationBeanFactoryMetadata.class);
        definition.setRole(2);
        registry.registerBeanDefinition(ConfigurationBeanFactoryMetadata.BEAN_NAME, definition);
    }
}
