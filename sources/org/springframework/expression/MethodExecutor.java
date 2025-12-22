package org.springframework.expression;

/* loaded from: reader.jar:BOOT-INF/lib/spring-expression-5.1.8.RELEASE.jar:org/springframework/expression/MethodExecutor.class */
public interface MethodExecutor {
    TypedValue execute(EvaluationContext evaluationContext, Object obj, Object... objArr) throws AccessException;
}
