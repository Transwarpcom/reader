package org.springframework.aop;

import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/TargetSource.class */
public interface TargetSource extends TargetClassAware {
    @Override // org.springframework.aop.TargetClassAware
    @Nullable
    Class<?> getTargetClass();

    boolean isStatic();

    @Nullable
    Object getTarget() throws Exception;

    void releaseTarget(Object obj) throws Exception;
}
