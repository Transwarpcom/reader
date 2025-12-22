package com.mongodb.event;

import com.mongodb.assertions.Assertions;
import com.mongodb.connection.ConnectionDescription;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/CommandFailedEvent.class */
public final class CommandFailedEvent extends CommandEvent {
    private final long elapsedTimeNanos;
    private final Throwable throwable;

    public CommandFailedEvent(int requestId, ConnectionDescription connectionDescription, String commandName, long elapsedTimeNanos, Throwable throwable) {
        super(requestId, connectionDescription, commandName);
        Assertions.isTrueArgument("elapsed time is not negative", elapsedTimeNanos >= 0);
        this.elapsedTimeNanos = elapsedTimeNanos;
        this.throwable = throwable;
    }

    public long getElapsedTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.elapsedTimeNanos, TimeUnit.NANOSECONDS);
    }

    public Throwable getThrowable() {
        return this.throwable;
    }
}
