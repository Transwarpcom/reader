package org.springframework.aop.interceptor;

import java.lang.reflect.Method;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/interceptor/AsyncUncaughtExceptionHandler.class */
public interface AsyncUncaughtExceptionHandler {
    void handleUncaughtException(Throwable th, Method method, Object... objArr);
}
