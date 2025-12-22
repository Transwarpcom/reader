package org.springframework.boot.web.server;

import org.springframework.boot.web.server.WebServerFactory;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/server/WebServerFactoryCustomizer.class */
public interface WebServerFactoryCustomizer<T extends WebServerFactory> {
    void customize(T factory);
}
