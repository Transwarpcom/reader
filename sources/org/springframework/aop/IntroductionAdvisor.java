package org.springframework.aop;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/IntroductionAdvisor.class */
public interface IntroductionAdvisor extends Advisor, IntroductionInfo {
    ClassFilter getClassFilter();

    void validateInterfaces() throws IllegalArgumentException;
}
