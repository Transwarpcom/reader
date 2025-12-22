package org.springframework.core.task;

import java.util.concurrent.Executor;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/task/TaskExecutor.class */
public interface TaskExecutor extends Executor {
    @Override // java.util.concurrent.Executor
    void execute(Runnable runnable);
}
