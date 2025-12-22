package org.springframework.boot.jta.bitronix;

import javax.sql.XADataSource;
import org.springframework.boot.jdbc.XADataSourceWrapper;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/jta/bitronix/BitronixXADataSourceWrapper.class */
public class BitronixXADataSourceWrapper implements XADataSourceWrapper {
    @Override // org.springframework.boot.jdbc.XADataSourceWrapper
    /* renamed from: wrapDataSource, reason: merged with bridge method [inline-methods] */
    public PoolingDataSourceBean mo5588wrapDataSource(XADataSource dataSource) throws Exception {
        PoolingDataSourceBean pool = new PoolingDataSourceBean();
        pool.setDataSource(dataSource);
        return pool;
    }
}
