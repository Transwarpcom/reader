package org.springframework.boot.web.embedded.netty;

import java.util.function.Function;
import reactor.netty.http.server.HttpServer;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/embedded/netty/NettyServerCustomizer.class */
public interface NettyServerCustomizer extends Function<HttpServer, HttpServer> {
}
