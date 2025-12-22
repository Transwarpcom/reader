package org.springframework.boot.web.embedded.jetty;

import org.eclipse.jetty.server.Server;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/embedded/jetty/JettyServerCustomizer.class */
public interface JettyServerCustomizer {
    void customize(Server server);
}
