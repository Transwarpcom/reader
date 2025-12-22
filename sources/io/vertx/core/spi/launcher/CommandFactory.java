package io.vertx.core.spi.launcher;

import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.spi.launcher.Command;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/launcher/CommandFactory.class */
public interface CommandFactory<C extends Command> {
    C create(CommandLine commandLine);

    CLI define();
}
