package org.springframework.aop;

import java.lang.reflect.Method;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/MethodBeforeAdvice.class */
public interface MethodBeforeAdvice extends BeforeAdvice {
    void before(Method method, Object[] objArr, @Nullable Object obj) throws Throwable;
}
