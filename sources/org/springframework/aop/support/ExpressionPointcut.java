package org.springframework.aop.support;

import org.springframework.aop.Pointcut;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/support/ExpressionPointcut.class */
public interface ExpressionPointcut extends Pointcut {
    @Nullable
    String getExpression();
}
