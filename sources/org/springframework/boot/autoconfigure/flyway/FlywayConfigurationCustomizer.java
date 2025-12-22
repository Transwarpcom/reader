package org.springframework.boot.autoconfigure.flyway;

import org.flywaydb.core.api.configuration.FluentConfiguration;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/flyway/FlywayConfigurationCustomizer.class */
public interface FlywayConfigurationCustomizer {
    void customize(FluentConfiguration configuration);
}
