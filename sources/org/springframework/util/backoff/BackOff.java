package org.springframework.util.backoff;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/util/backoff/BackOff.class */
public interface BackOff {
    BackOffExecution start();
}
