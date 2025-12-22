package org.springframework.beans.factory.config;

import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/config/SingletonBeanRegistry.class */
public interface SingletonBeanRegistry {
    void registerSingleton(String str, Object obj);

    @Nullable
    Object getSingleton(String str);

    boolean containsSingleton(String str);

    String[] getSingletonNames();

    int getSingletonCount();

    Object getSingletonMutex();
}
