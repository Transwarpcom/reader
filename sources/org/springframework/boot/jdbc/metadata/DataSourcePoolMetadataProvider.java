package org.springframework.boot.jdbc.metadata;

import javax.sql.DataSource;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/jdbc/metadata/DataSourcePoolMetadataProvider.class */
public interface DataSourcePoolMetadataProvider {
    DataSourcePoolMetadata getDataSourcePoolMetadata(DataSource dataSource);
}
