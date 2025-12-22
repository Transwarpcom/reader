package org.aopalliance.intercept;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/aopalliance/intercept/MethodInterceptor.class */
public interface MethodInterceptor extends Interceptor {
    Object invoke(MethodInvocation methodInvocation) throws Throwable;
}
