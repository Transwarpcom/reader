package org.springframework.core.task;

import java.util.concurrent.RejectedExecutionException;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/task/TaskRejectedException.class */
public class TaskRejectedException extends RejectedExecutionException {
    public TaskRejectedException(String msg) {
        super(msg);
    }

    public TaskRejectedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
