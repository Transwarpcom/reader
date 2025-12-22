package ch.qos.logback.classic.net.server;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.net.server.Client;

/* loaded from: reader.jar:BOOT-INF/lib/logback-classic-1.2.3.jar:ch/qos/logback/classic/net/server/RemoteAppenderClient.class */
interface RemoteAppenderClient extends Client {
    void setLoggerContext(LoggerContext loggerContext);
}
