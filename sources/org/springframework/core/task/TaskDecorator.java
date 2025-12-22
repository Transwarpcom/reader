package org.springframework.core.task;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/task/TaskDecorator.class */
public interface TaskDecorator {
    Runnable decorate(Runnable runnable);
}
