package org.springframework.expression;

/* loaded from: reader.jar:BOOT-INF/lib/spring-expression-5.1.8.RELEASE.jar:org/springframework/expression/AccessException.class */
public class AccessException extends Exception {
    public AccessException(String message) {
        super(message);
    }

    public AccessException(String message, Exception cause) {
        super(message, cause);
    }
}
