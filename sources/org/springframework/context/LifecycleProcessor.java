package org.springframework.context;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/LifecycleProcessor.class */
public interface LifecycleProcessor extends Lifecycle {
    void onRefresh();

    void onClose();
}
