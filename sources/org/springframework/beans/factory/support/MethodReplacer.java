package org.springframework.beans.factory.support;

import java.lang.reflect.Method;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/support/MethodReplacer.class */
public interface MethodReplacer {
    Object reimplement(Object obj, Method method, Object[] objArr) throws Throwable;
}
