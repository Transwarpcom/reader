package org.springframework.boot.autoconfigure.orm.jpa;

import java.util.Map;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/orm/jpa/HibernatePropertiesCustomizer.class */
public interface HibernatePropertiesCustomizer {
    void customize(Map<String, Object> hibernateProperties);
}
