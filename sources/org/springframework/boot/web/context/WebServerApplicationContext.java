package org.springframework.boot.web.context;

import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationContext;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/context/WebServerApplicationContext.class */
public interface WebServerApplicationContext extends ApplicationContext {
    WebServer getWebServer();

    String getServerNamespace();
}
