package com.mongodb.event;

import com.mongodb.annotations.Immutable;
import com.mongodb.assertions.Assertions;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Immutable
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/CommandEventMulticaster.class */
public final class CommandEventMulticaster implements CommandListener {
    private static final Logger LOGGER = Loggers.getLogger("protocol.event");
    private final List<CommandListener> commandListeners;

    public CommandEventMulticaster(List<CommandListener> commandListeners) {
        Assertions.notNull("commandListeners", commandListeners);
        Assertions.isTrue("All CommandListener instances are non-null", !commandListeners.contains(null));
        this.commandListeners = new ArrayList(commandListeners);
    }

    public List<CommandListener> getCommandListeners() {
        return Collections.unmodifiableList(this.commandListeners);
    }

    @Override // com.mongodb.event.CommandListener
    public void commandStarted(CommandStartedEvent event) {
        for (CommandListener cur : this.commandListeners) {
            try {
                cur.commandStarted(event);
            } catch (Exception e) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn(String.format("Exception thrown raising command started event to listener %s", cur), e);
                }
            }
        }
    }

    @Override // com.mongodb.event.CommandListener
    public void commandSucceeded(CommandSucceededEvent event) {
        for (CommandListener cur : this.commandListeners) {
            try {
                cur.commandSucceeded(event);
            } catch (Exception e) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn(String.format("Exception thrown raising command succeeded event to listener %s", cur), e);
                }
            }
        }
    }

    @Override // com.mongodb.event.CommandListener
    public void commandFailed(CommandFailedEvent event) {
        for (CommandListener cur : this.commandListeners) {
            try {
                cur.commandFailed(event);
            } catch (Exception e) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn(String.format("Exception thrown raising command failed event to listener %s", cur), e);
                }
            }
        }
    }
}
