package org.springframework.aop.aspectj;

import org.springframework.aop.PointcutAdvisor;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/aspectj/InstantiationModelAwarePointcutAdvisor.class */
public interface InstantiationModelAwarePointcutAdvisor extends PointcutAdvisor {
    boolean isLazy();

    boolean isAdviceInstantiated();
}
