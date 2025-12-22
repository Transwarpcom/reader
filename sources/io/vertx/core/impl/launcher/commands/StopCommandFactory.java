package io.vertx.core.impl.launcher.commands;

import io.vertx.core.spi.launcher.DefaultCommandFactory;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/commands/StopCommandFactory.class */
public class StopCommandFactory extends DefaultCommandFactory<StopCommand> {
    public StopCommandFactory() {
        super(StopCommand.class, StopCommand::new);
    }
}
