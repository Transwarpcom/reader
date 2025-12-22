package org.springframework.boot.autoconfigure.data.jpa;

import javax.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.AbstractDependsOnBeanFactoryPostProcessor;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/data/jpa/EntityManagerFactoryDependsOnPostProcessor.class */
public class EntityManagerFactoryDependsOnPostProcessor extends AbstractDependsOnBeanFactoryPostProcessor {
    public EntityManagerFactoryDependsOnPostProcessor(String... dependsOn) {
        super(EntityManagerFactory.class, AbstractEntityManagerFactoryBean.class, dependsOn);
    }
}
