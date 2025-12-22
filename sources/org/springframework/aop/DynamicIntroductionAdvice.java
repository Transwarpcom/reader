package org.springframework.aop;

import org.aopalliance.aop.Advice;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/DynamicIntroductionAdvice.class */
public interface DynamicIntroductionAdvice extends Advice {
    boolean implementsInterface(Class<?> cls);
}
