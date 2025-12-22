package org.springframework.expression;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-expression-5.1.8.RELEASE.jar:org/springframework/expression/TypeLocator.class */
public interface TypeLocator {
    Class<?> findType(String str) throws EvaluationException;
}
