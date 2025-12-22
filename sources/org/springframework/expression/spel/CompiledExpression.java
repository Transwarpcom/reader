package org.springframework.expression.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-expression-5.1.8.RELEASE.jar:org/springframework/expression/spel/CompiledExpression.class */
public abstract class CompiledExpression {
    public abstract Object getValue(@Nullable Object obj, @Nullable EvaluationContext evaluationContext) throws EvaluationException;
}
