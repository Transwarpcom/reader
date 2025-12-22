package org.springframework.boot.autoconfigure.orm.jpa;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/orm/jpa/EntityManagerFactoryBuilderCustomizer.class */
public interface EntityManagerFactoryBuilderCustomizer {
    void customize(EntityManagerFactoryBuilder builder);
}
