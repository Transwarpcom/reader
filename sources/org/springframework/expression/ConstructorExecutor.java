package org.springframework.expression;

/* loaded from: reader.jar:BOOT-INF/lib/spring-expression-5.1.8.RELEASE.jar:org/springframework/expression/ConstructorExecutor.class */
public interface ConstructorExecutor {
    TypedValue execute(EvaluationContext evaluationContext, Object... objArr) throws AccessException;
}
