package org.springframework.aop.target.dynamic;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/target/dynamic/Refreshable.class */
public interface Refreshable {
    void refresh();

    long getRefreshCount();

    long getLastRefreshTime();
}
