package org.springframework.boot.jdbc;

import java.sql.Wrapper;
import javax.sql.DataSource;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.jdbc.datasource.DelegatingDataSource;
import org.springframework.util.ClassUtils;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/jdbc/DataSourceUnwrapper.class */
public final class DataSourceUnwrapper {
    private static final boolean DELEGATING_DATA_SOURCE_PRESENT = ClassUtils.isPresent("org.springframework.jdbc.datasource.DelegatingDataSource", DataSourceUnwrapper.class.getClassLoader());

    private DataSourceUnwrapper() {
    }

    public static <T> T unwrap(DataSource dataSource, Class<T> cls) {
        DataSource targetDataSource;
        if (cls.isInstance(dataSource)) {
            return cls.cast(dataSource);
        }
        T t = (T) safeUnwrap(dataSource, cls);
        if (t != null) {
            return t;
        }
        if (DELEGATING_DATA_SOURCE_PRESENT && (targetDataSource = DelegatingDataSourceUnwrapper.getTargetDataSource(dataSource)) != null) {
            return (T) unwrap(targetDataSource, cls);
        }
        if (AopUtils.isAopProxy(dataSource)) {
            Object singletonTarget = AopProxyUtils.getSingletonTarget(dataSource);
            if (singletonTarget instanceof DataSource) {
                return (T) unwrap((DataSource) singletonTarget, cls);
            }
            return null;
        }
        return null;
    }

    private static <S> S safeUnwrap(Wrapper wrapper, Class<S> cls) {
        try {
            if (wrapper.isWrapperFor(cls)) {
                return (S) wrapper.unwrap(cls);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/jdbc/DataSourceUnwrapper$DelegatingDataSourceUnwrapper.class */
    private static class DelegatingDataSourceUnwrapper {
        private DelegatingDataSourceUnwrapper() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static DataSource getTargetDataSource(DataSource dataSource) {
            if (dataSource instanceof DelegatingDataSource) {
                return ((DelegatingDataSource) dataSource).getTargetDataSource();
            }
            return null;
        }
    }
}
