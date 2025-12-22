package org.springframework.util.concurrent;

import org.springframework.lang.Nullable;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/util/concurrent/SuccessCallback.class */
public interface SuccessCallback<T> {
    void onSuccess(@Nullable T t);
}
