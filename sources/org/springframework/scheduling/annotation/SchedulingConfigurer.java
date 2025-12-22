package org.springframework.scheduling.annotation;

import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/scheduling/annotation/SchedulingConfigurer.class */
public interface SchedulingConfigurer {
    void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar);
}
