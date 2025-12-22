package com.mongodb.internal.connection;

import com.mongodb.ReadConcern;
import com.mongodb.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ReadConcernAwareNoOpSessionContext.class */
public final class ReadConcernAwareNoOpSessionContext extends NoOpSessionContext {
    private final ReadConcern readConcern;

    public ReadConcernAwareNoOpSessionContext(ReadConcern readConcern) {
        this.readConcern = (ReadConcern) Assertions.notNull("readConcern", readConcern);
    }

    @Override // com.mongodb.internal.connection.NoOpSessionContext, com.mongodb.session.SessionContext
    public ReadConcern getReadConcern() {
        return this.readConcern;
    }
}
