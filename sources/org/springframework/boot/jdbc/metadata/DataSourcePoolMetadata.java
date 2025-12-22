package org.springframework.boot.jdbc.metadata;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/jdbc/metadata/DataSourcePoolMetadata.class */
public interface DataSourcePoolMetadata {
    Float getUsage();

    Integer getActive();

    Integer getMax();

    Integer getMin();

    String getValidationQuery();

    Boolean getDefaultAutoCommit();
}
