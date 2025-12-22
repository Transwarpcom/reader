package org.springframework.boot.jdbc;

import javax.sql.DataSource;
import javax.sql.XADataSource;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/jdbc/XADataSourceWrapper.class */
public interface XADataSourceWrapper {
    /* renamed from: wrapDataSource */
    DataSource mo5588wrapDataSource(XADataSource dataSource) throws Exception;
}
