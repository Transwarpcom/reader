package org.springframework.expression.spel;

/* loaded from: reader.jar:BOOT-INF/lib/spring-expression-5.1.8.RELEASE.jar:org/springframework/expression/spel/InternalParseException.class */
public class InternalParseException extends RuntimeException {
    public InternalParseException(SpelParseException cause) {
        super(cause);
    }

    @Override // java.lang.Throwable
    public SpelParseException getCause() {
        return (SpelParseException) super.getCause();
    }
}
