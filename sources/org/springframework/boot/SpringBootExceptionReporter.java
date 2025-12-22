package org.springframework.boot;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/SpringBootExceptionReporter.class */
public interface SpringBootExceptionReporter {
    boolean reportException(Throwable failure);
}
