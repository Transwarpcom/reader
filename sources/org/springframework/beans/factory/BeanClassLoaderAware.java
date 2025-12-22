package org.springframework.beans.factory;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/BeanClassLoaderAware.class */
public interface BeanClassLoaderAware extends Aware {
    void setBeanClassLoader(ClassLoader classLoader);
}
