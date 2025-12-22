package com.mongodb.event;

import com.mongodb.assertions.Assertions;
import com.mongodb.connection.ConnectionDescription;
import java.util.concurrent.TimeUnit;
import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/CommandSucceededEvent.class */
public final class CommandSucceededEvent extends CommandEvent {
    private final BsonDocument response;
    private final long elapsedTimeNanos;

    public CommandSucceededEvent(int requestId, ConnectionDescription connectionDescription, String commandName, BsonDocument response, long elapsedTimeNanos) {
        super(requestId, connectionDescription, commandName);
        this.response = response;
        Assertions.isTrueArgument("elapsed time is not negative", elapsedTimeNanos >= 0);
        this.elapsedTimeNanos = elapsedTimeNanos;
    }

    public long getElapsedTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.elapsedTimeNanos, TimeUnit.NANOSECONDS);
    }

    public BsonDocument getResponse() {
        return this.response;
    }
}
