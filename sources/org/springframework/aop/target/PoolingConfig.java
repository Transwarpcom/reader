package org.springframework.aop.target;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/target/PoolingConfig.class */
public interface PoolingConfig {
    int getMaxSize();

    int getActiveCount() throws UnsupportedOperationException;

    int getIdleCount() throws UnsupportedOperationException;
}
