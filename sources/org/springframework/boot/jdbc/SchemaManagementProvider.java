package org.springframework.boot.jdbc;

import javax.sql.DataSource;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/jdbc/SchemaManagementProvider.class */
public interface SchemaManagementProvider {
    SchemaManagement getSchemaManagement(DataSource dataSource);
}
