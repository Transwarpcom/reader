package org.springframework.context;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/Lifecycle.class */
public interface Lifecycle {
    void start();

    void stop();

    boolean isRunning();
}
