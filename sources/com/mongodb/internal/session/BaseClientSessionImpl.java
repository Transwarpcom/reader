package com.mongodb.internal.session;

import com.mongodb.ClientSessionOptions;
import com.mongodb.assertions.Assertions;
import com.mongodb.session.ClientSession;
import com.mongodb.session.ServerSession;
import org.bson.BsonDocument;
import org.bson.BsonTimestamp;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/session/BaseClientSessionImpl.class */
public class BaseClientSessionImpl implements ClientSession {
    private static final String CLUSTER_TIME_KEY = "clusterTime";
    private final ServerSessionPool serverSessionPool;
    private final ServerSession serverSession;
    private final Object originator;
    private final ClientSessionOptions options;
    private BsonDocument clusterTime;
    private BsonTimestamp operationTime;
    private volatile boolean closed = false;

    public BaseClientSessionImpl(ServerSessionPool serverSessionPool, Object originator, ClientSessionOptions options) {
        this.serverSessionPool = serverSessionPool;
        this.serverSession = serverSessionPool.get();
        this.originator = originator;
        this.options = options;
    }

    @Override // com.mongodb.session.ClientSession
    public ClientSessionOptions getOptions() {
        return this.options;
    }

    @Override // com.mongodb.session.ClientSession
    public boolean isCausallyConsistent() {
        Boolean causallyConsistent = this.options.isCausallyConsistent();
        if (causallyConsistent == null) {
            return true;
        }
        return causallyConsistent.booleanValue();
    }

    @Override // com.mongodb.session.ClientSession
    public Object getOriginator() {
        return this.originator;
    }

    @Override // com.mongodb.session.ClientSession
    public BsonDocument getClusterTime() {
        return this.clusterTime;
    }

    @Override // com.mongodb.session.ClientSession
    public BsonTimestamp getOperationTime() {
        return this.operationTime;
    }

    @Override // com.mongodb.session.ClientSession
    public ServerSession getServerSession() {
        Assertions.isTrue("open", !this.closed);
        return this.serverSession;
    }

    @Override // com.mongodb.session.ClientSession
    public void advanceOperationTime(BsonTimestamp newOperationTime) {
        Assertions.isTrue("open", !this.closed);
        this.operationTime = greaterOf(newOperationTime);
    }

    @Override // com.mongodb.session.ClientSession
    public void advanceClusterTime(BsonDocument newClusterTime) {
        Assertions.isTrue("open", !this.closed);
        this.clusterTime = greaterOf(newClusterTime);
    }

    private BsonDocument greaterOf(BsonDocument newClusterTime) {
        if (newClusterTime == null) {
            return this.clusterTime;
        }
        if (this.clusterTime == null) {
            return newClusterTime;
        }
        return newClusterTime.getTimestamp(CLUSTER_TIME_KEY).compareTo(this.clusterTime.getTimestamp(CLUSTER_TIME_KEY)) > 0 ? newClusterTime : this.clusterTime;
    }

    private BsonTimestamp greaterOf(BsonTimestamp newOperationTime) {
        if (newOperationTime == null) {
            return this.operationTime;
        }
        if (this.operationTime == null) {
            return newOperationTime;
        }
        return newOperationTime.compareTo(this.operationTime) > 0 ? newOperationTime : this.operationTime;
    }

    @Override // com.mongodb.session.ClientSession, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.closed) {
            this.closed = true;
            this.serverSessionPool.release(this.serverSession);
        }
    }
}
