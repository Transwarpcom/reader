package org.springframework.scheduling;

import org.springframework.core.task.AsyncTaskExecutor;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/scheduling/SchedulingTaskExecutor.class */
public interface SchedulingTaskExecutor extends AsyncTaskExecutor {
    default boolean prefersShortLivedTasks() {
        return true;
    }
}
