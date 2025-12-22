package org.springframework.boot.task;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/task/TaskExecutorCustomizer.class */
public interface TaskExecutorCustomizer {
    void customize(ThreadPoolTaskExecutor taskExecutor);
}
