package org.springframework.boot.web.reactive.server;

import org.springframework.boot.web.server.WebServer;
import org.springframework.http.server.reactive.HttpHandler;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/reactive/server/ReactiveWebServerFactory.class */
public interface ReactiveWebServerFactory {
    WebServer getWebServer(HttpHandler httpHandler);
}
