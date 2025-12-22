package com.mongodb.session;

import com.mongodb.ClientSessionOptions;
import com.mongodb.annotations.NotThreadSafe;
import java.io.Closeable;
import org.bson.BsonDocument;
import org.bson.BsonTimestamp;

@NotThreadSafe
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/session/ClientSession.class */
public interface ClientSession extends Closeable {
    ClientSessionOptions getOptions();

    boolean isCausallyConsistent();

    Object getOriginator();

    ServerSession getServerSession();

    BsonTimestamp getOperationTime();

    void advanceOperationTime(BsonTimestamp bsonTimestamp);

    void advanceClusterTime(BsonDocument bsonDocument);

    BsonDocument getClusterTime();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();
}
