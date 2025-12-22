package org.springframework.context;

import java.util.EventListener;
import org.springframework.context.ApplicationEvent;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/ApplicationListener.class */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    void onApplicationEvent(E e);
}
