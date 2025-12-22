package org.springframework.boot.web.servlet.server;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/servlet/server/ServletWebServerFactory.class */
public interface ServletWebServerFactory {
    WebServer getWebServer(ServletContextInitializer... initializers);
}
