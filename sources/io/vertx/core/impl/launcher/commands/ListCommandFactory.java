package io.vertx.core.impl.launcher.commands;

import io.vertx.core.spi.launcher.DefaultCommandFactory;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/commands/ListCommandFactory.class */
public class ListCommandFactory extends DefaultCommandFactory<ListCommand> {
    public ListCommandFactory() {
        super(ListCommand.class, ListCommand::new);
    }
}
