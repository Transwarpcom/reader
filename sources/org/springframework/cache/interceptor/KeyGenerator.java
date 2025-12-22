package org.springframework.cache.interceptor;

import java.lang.reflect.Method;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/cache/interceptor/KeyGenerator.class */
public interface KeyGenerator {
    Object generate(Object obj, Method method, Object... objArr);
}
