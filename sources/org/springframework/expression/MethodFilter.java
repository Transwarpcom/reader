package org.springframework.expression;

import java.lang.reflect.Method;
import java.util.List;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-expression-5.1.8.RELEASE.jar:org/springframework/expression/MethodFilter.class */
public interface MethodFilter {
    List<Method> filter(List<Method> list);
}
