package com.mongodb.event;

import com.mongodb.annotations.Immutable;
import java.util.List;

@Immutable
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/CommandListenerMulticaster.class */
public class CommandListenerMulticaster implements CommandListener {
    private final CommandEventMulticaster wrapped;

    public CommandListenerMulticaster(List<CommandListener> commandListeners) {
        this.wrapped = new CommandEventMulticaster(commandListeners);
    }

    public List<CommandListener> getCommandListeners() {
        return this.wrapped.getCommandListeners();
    }

    @Override // com.mongodb.event.CommandListener
    public void commandStarted(CommandStartedEvent event) {
        this.wrapped.commandStarted(event);
    }

    @Override // com.mongodb.event.CommandListener
    public void commandSucceeded(CommandSucceededEvent event) {
        this.wrapped.commandSucceeded(event);
    }

    @Override // com.mongodb.event.CommandListener
    public void commandFailed(CommandFailedEvent event) {
        this.wrapped.commandFailed(event);
    }
}
