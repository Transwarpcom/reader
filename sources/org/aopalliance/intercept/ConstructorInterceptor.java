package org.aopalliance.intercept;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/aopalliance/intercept/ConstructorInterceptor.class */
public interface ConstructorInterceptor extends Interceptor {
    Object construct(ConstructorInvocation constructorInvocation) throws Throwable;
}
