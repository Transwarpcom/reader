package org.springframework.aop.support;

import org.aopalliance.aop.Advice;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/support/AbstractGenericPointcutAdvisor.class */
public abstract class AbstractGenericPointcutAdvisor extends AbstractPointcutAdvisor {
    private Advice advice = EMPTY_ADVICE;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override // org.springframework.aop.Advisor
    public Advice getAdvice() {
        return this.advice;
    }

    public String toString() {
        return getClass().getName() + ": advice [" + getAdvice() + "]";
    }
}
