package org.springframework.expression;

/* loaded from: reader.jar:BOOT-INF/lib/spring-expression-5.1.8.RELEASE.jar:org/springframework/expression/BeanResolver.class */
public interface BeanResolver {
    Object resolve(EvaluationContext evaluationContext, String str) throws AccessException;
}
