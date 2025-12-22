package org.springframework.util;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/util/ErrorHandler.class */
public interface ErrorHandler {
    void handleError(Throwable th);
}
