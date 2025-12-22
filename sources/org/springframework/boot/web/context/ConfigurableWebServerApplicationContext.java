package org.springframework.boot.web.context;

import org.springframework.context.ConfigurableApplicationContext;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/context/ConfigurableWebServerApplicationContext.class */
public interface ConfigurableWebServerApplicationContext extends ConfigurableApplicationContext, WebServerApplicationContext {
    void setServerNamespace(String serverNamespace);
}
