package org.springframework.context.annotation;

import org.springframework.core.type.AnnotatedTypeMetadata;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/annotation/Condition.class */
public interface Condition {
    boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata);
}
