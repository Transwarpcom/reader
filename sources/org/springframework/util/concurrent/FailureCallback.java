package org.springframework.util.concurrent;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/util/concurrent/FailureCallback.class */
public interface FailureCallback {
    void onFailure(Throwable th);
}
