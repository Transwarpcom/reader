package io.netty.util.internal.logging;

import org.apache.log4j.Logger;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/logging/Log4JLoggerFactory.class */
public class Log4JLoggerFactory extends InternalLoggerFactory {
    public static final InternalLoggerFactory INSTANCE = new Log4JLoggerFactory();

    @Deprecated
    public Log4JLoggerFactory() {
    }

    @Override // io.netty.util.internal.logging.InternalLoggerFactory
    public InternalLogger newInstance(String name) {
        return new Log4JLogger(Logger.getLogger(name));
    }
}
