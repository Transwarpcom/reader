package org.springframework.boot.web.server;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/server/WebServer.class */
public interface WebServer {
    void start() throws WebServerException;

    void stop() throws WebServerException;

    int getPort();
}
