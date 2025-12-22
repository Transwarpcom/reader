package io.vertx.core.impl.launcher.commands;

import io.vertx.core.spi.launcher.DefaultCommandFactory;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/commands/RunCommandFactory.class */
public class RunCommandFactory extends DefaultCommandFactory<RunCommand> {
    public RunCommandFactory() {
        super(RunCommand.class, RunCommand::new);
    }
}
