package org.springframework.aop;

import java.lang.reflect.Method;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/AfterReturningAdvice.class */
public interface AfterReturningAdvice extends AfterAdvice {
    void afterReturning(@Nullable Object obj, Method method, Object[] objArr, @Nullable Object obj2) throws Throwable;
}
