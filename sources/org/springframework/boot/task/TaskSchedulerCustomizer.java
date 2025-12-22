package org.springframework.boot.task;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/task/TaskSchedulerCustomizer.class */
public interface TaskSchedulerCustomizer {
    void customize(ThreadPoolTaskScheduler taskScheduler);
}
