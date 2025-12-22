package org.springframework.boot.web.server;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/server/PortInUseException.class */
public class PortInUseException extends WebServerException {
    private final int port;

    public PortInUseException(int port) {
        super("Port " + port + " is already in use", null);
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }
}
