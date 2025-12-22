package org.springframework.aop.framework;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/framework/AopProxyFactory.class */
public interface AopProxyFactory {
    AopProxy createAopProxy(AdvisedSupport advisedSupport) throws AopConfigException;
}
