package org.springframework.aop;

import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/TargetClassAware.class */
public interface TargetClassAware {
    @Nullable
    Class<?> getTargetClass();
}
