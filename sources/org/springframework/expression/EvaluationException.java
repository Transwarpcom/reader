package org.springframework.expression;

/* loaded from: reader.jar:BOOT-INF/lib/spring-expression-5.1.8.RELEASE.jar:org/springframework/expression/EvaluationException.class */
public class EvaluationException extends ExpressionException {
    public EvaluationException(String message) {
        super(message);
    }

    public EvaluationException(String message, Throwable cause) {
        super(message, cause);
    }

    public EvaluationException(int position, String message) {
        super(position, message);
    }

    public EvaluationException(String expressionString, String message) {
        super(expressionString, message);
    }

    public EvaluationException(int position, String message, Throwable cause) {
        super(position, message, cause);
    }
}
