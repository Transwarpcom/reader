package org.springframework.boot.autoconfigure.flyway;

import org.flywaydb.core.Flyway;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/flyway/FlywayMigrationStrategy.class */
public interface FlywayMigrationStrategy {
    void migrate(Flyway flyway);
}
