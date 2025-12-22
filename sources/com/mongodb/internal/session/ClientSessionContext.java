package com.mongodb.internal.session;

import com.mongodb.assertions.Assertions;
import com.mongodb.session.ClientSession;
import com.mongodb.session.SessionContext;
import org.bson.BsonDocument;
import org.bson.BsonTimestamp;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/session/ClientSessionContext.class */
public abstract class ClientSessionContext implements SessionContext {
    private ClientSession clientSession;

    public ClientSessionContext(ClientSession clientSession) {
        this.clientSession = (ClientSession) Assertions.notNull("clientSession", clientSession);
    }

    public ClientSession getClientSession() {
        return this.clientSession;
    }

    @Override // com.mongodb.session.SessionContext
    public boolean hasSession() {
        return true;
    }

    @Override // com.mongodb.session.SessionContext
    public BsonDocument getSessionId() {
        return this.clientSession.getServerSession().getIdentifier();
    }

    @Override // com.mongodb.session.SessionContext
    public boolean isCausallyConsistent() {
        return this.clientSession.isCausallyConsistent();
    }

    @Override // com.mongodb.session.SessionContext
    public long getTransactionNumber() {
        return this.clientSession.getServerSession().getTransactionNumber();
    }

    @Override // com.mongodb.session.SessionContext
    public long advanceTransactionNumber() {
        return this.clientSession.getServerSession().advanceTransactionNumber();
    }

    @Override // com.mongodb.session.SessionContext
    public BsonTimestamp getOperationTime() {
        return this.clientSession.getOperationTime();
    }

    @Override // com.mongodb.session.SessionContext
    public void advanceOperationTime(BsonTimestamp operationTime) {
        this.clientSession.advanceOperationTime(operationTime);
    }

    @Override // com.mongodb.session.SessionContext
    public BsonDocument getClusterTime() {
        return this.clientSession.getClusterTime();
    }

    @Override // com.mongodb.session.SessionContext
    public void advanceClusterTime(BsonDocument clusterTime) {
        this.clientSession.advanceClusterTime(clusterTime);
    }
}
