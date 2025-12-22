package io.vertx.core.spi.launcher;

import java.util.Collection;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/launcher/CommandFactoryLookup.class */
public interface CommandFactoryLookup {
    Collection<CommandFactory<?>> lookup();
}
