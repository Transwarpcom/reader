package org.apache.logging.log4j.spi;

import java.net.URI;

/* loaded from: reader.jar:BOOT-INF/lib/log4j-api-2.11.2.jar:org/apache/logging/log4j/spi/LoggerContextFactory.class */
public interface LoggerContextFactory {
    LoggerContext getContext(String str, ClassLoader classLoader, Object obj, boolean z);

    LoggerContext getContext(String str, ClassLoader classLoader, Object obj, boolean z, URI uri, String str2);

    void removeContext(LoggerContext loggerContext);
}
