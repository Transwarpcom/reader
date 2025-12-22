package org.springframework.aop;

import java.lang.reflect.Method;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/IntroductionAwareMethodMatcher.class */
public interface IntroductionAwareMethodMatcher extends MethodMatcher {
    boolean matches(Method method, Class<?> cls, boolean z);
}
