package org.springframework.context;

import org.springframework.context.ConfigurableApplicationContext;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/ApplicationContextInitializer.class */
public interface ApplicationContextInitializer<C extends ConfigurableApplicationContext> {
    void initialize(C c);
}
