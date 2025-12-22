package org.springframework.aop.framework.autoproxy;

import org.springframework.aop.TargetSource;
import org.springframework.lang.Nullable;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/framework/autoproxy/TargetSourceCreator.class */
public interface TargetSourceCreator {
    @Nullable
    TargetSource getTargetSource(Class<?> cls, String str);
}
