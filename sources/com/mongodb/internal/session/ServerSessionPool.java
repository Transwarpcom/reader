package com.mongodb.internal.session;

import com.mongodb.MongoException;
import com.mongodb.ReadPreference;
import com.mongodb.assertions.Assertions;
import com.mongodb.connection.Cluster;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.Connection;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.connection.ConcurrentPool;
import com.mongodb.internal.connection.NoOpSessionContext;
import com.mongodb.internal.validator.NoOpFieldNameValidator;
import com.mongodb.selector.ReadPreferenceServerSelector;
import com.mongodb.selector.ServerSelector;
import com.mongodb.session.ServerSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.bson.BsonArray;
import org.bson.BsonBinary;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWriter;
import org.bson.BsonWriter;
import org.bson.UuidRepresentation;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.UuidCodec;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/session/ServerSessionPool.class */
public class ServerSessionPool {
    private static final int END_SESSIONS_BATCH_SIZE = 10000;
    private final ConcurrentPool<ServerSessionImpl> serverSessionPool;
    private final Cluster cluster;
    private final Clock clock;
    private volatile boolean closing;
    private volatile boolean closed;
    private final List<BsonDocument> closedSessionIdentifiers;

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/session/ServerSessionPool$Clock.class */
    interface Clock {
        long millis();
    }

    public ServerSessionPool(Cluster cluster) {
        this(cluster, new Clock() { // from class: com.mongodb.internal.session.ServerSessionPool.1
            @Override // com.mongodb.internal.session.ServerSessionPool.Clock
            public long millis() {
                return System.currentTimeMillis();
            }
        });
    }

    public ServerSessionPool(Cluster cluster, Clock clock) {
        this.serverSessionPool = new ConcurrentPool<>(Integer.MAX_VALUE, new ServerSessionItemFactory());
        this.closedSessionIdentifiers = new ArrayList();
        this.cluster = cluster;
        this.clock = clock;
    }

    public ServerSession get() {
        Assertions.isTrue("server session pool is open", !this.closed);
        ServerSessionImpl serverSessionImpl = this.serverSessionPool.get();
        while (true) {
            ServerSessionImpl serverSession = serverSessionImpl;
            if (shouldPrune(serverSession)) {
                this.serverSessionPool.release(serverSession, true);
                serverSessionImpl = this.serverSessionPool.get();
            } else {
                return serverSession;
            }
        }
    }

    public void release(ServerSession serverSession) {
        this.serverSessionPool.release((ServerSessionImpl) serverSession);
        this.serverSessionPool.prune();
    }

    public void close() {
        try {
            this.closing = true;
            this.serverSessionPool.close();
            endClosedSessions();
        } finally {
            this.closed = true;
        }
    }

    public int getInUseCount() {
        return this.serverSessionPool.getInUseCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeSession(ServerSessionImpl serverSession) {
        serverSession.close();
        if (!this.closing) {
            return;
        }
        this.closedSessionIdentifiers.add(serverSession.getIdentifier());
        if (this.closedSessionIdentifiers.size() == 10000) {
            endClosedSessions();
        }
    }

    private void endClosedSessions() {
        if (this.closedSessionIdentifiers.isEmpty()) {
            return;
        }
        final List<ServerDescription> primaryPreferred = new ReadPreferenceServerSelector(ReadPreference.primaryPreferred()).select(this.cluster.getCurrentDescription());
        if (primaryPreferred.isEmpty()) {
            return;
        }
        Connection connection = this.cluster.selectServer(new ServerSelector() { // from class: com.mongodb.internal.session.ServerSessionPool.2
            @Override // com.mongodb.selector.ServerSelector
            public List<ServerDescription> select(ClusterDescription clusterDescription) {
                for (ServerDescription cur : clusterDescription.getServerDescriptions()) {
                    if (cur.getAddress().equals(((ServerDescription) primaryPreferred.get(0)).getAddress())) {
                        return Collections.singletonList(cur);
                    }
                }
                return Collections.emptyList();
            }
        }).getConnection();
        try {
            connection.command("admin", new BsonDocument("endSessions", new BsonArray(this.closedSessionIdentifiers)), new NoOpFieldNameValidator(), ReadPreference.primaryPreferred(), new BsonDocumentCodec(), NoOpSessionContext.INSTANCE);
            this.closedSessionIdentifiers.clear();
            connection.release();
        } catch (MongoException e) {
            this.closedSessionIdentifiers.clear();
            connection.release();
        } catch (Throwable th) {
            this.closedSessionIdentifiers.clear();
            connection.release();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldPrune(ServerSessionImpl serverSession) {
        Integer logicalSessionTimeoutMinutes = this.cluster.getCurrentDescription().getLogicalSessionTimeoutMinutes();
        if (logicalSessionTimeoutMinutes == null) {
            return false;
        }
        long currentTimeMillis = this.clock.millis();
        long timeSinceLastUse = currentTimeMillis - serverSession.getLastUsedAtMillis();
        long oneMinuteFromTimeout = TimeUnit.MINUTES.toMillis(logicalSessionTimeoutMinutes.intValue() - 1);
        return timeSinceLastUse > oneMinuteFromTimeout;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/session/ServerSessionPool$ServerSessionImpl.class */
    final class ServerSessionImpl implements ServerSession {
        private final BsonDocument identifier;
        private long transactionNumber = 0;
        private volatile long lastUsedAtMillis;
        private volatile boolean closed;

        ServerSessionImpl(BsonBinary identifier) {
            this.lastUsedAtMillis = ServerSessionPool.this.clock.millis();
            this.identifier = new BsonDocument("id", identifier);
        }

        void close() {
            this.closed = true;
        }

        long getLastUsedAtMillis() {
            return this.lastUsedAtMillis;
        }

        @Override // com.mongodb.session.ServerSession
        public long getTransactionNumber() {
            return this.transactionNumber;
        }

        @Override // com.mongodb.session.ServerSession
        public BsonDocument getIdentifier() {
            this.lastUsedAtMillis = ServerSessionPool.this.clock.millis();
            return this.identifier;
        }

        @Override // com.mongodb.session.ServerSession
        public long advanceTransactionNumber() {
            this.transactionNumber++;
            return this.transactionNumber;
        }

        @Override // com.mongodb.session.ServerSession
        public boolean isClosed() {
            return this.closed;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/session/ServerSessionPool$ServerSessionItemFactory.class */
    private final class ServerSessionItemFactory implements ConcurrentPool.ItemFactory<ServerSessionImpl> {
        private ServerSessionItemFactory() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.mongodb.internal.connection.ConcurrentPool.ItemFactory
        public ServerSessionImpl create(boolean initialize) {
            return ServerSessionPool.this.new ServerSessionImpl(createNewServerSessionIdentifier());
        }

        @Override // com.mongodb.internal.connection.ConcurrentPool.ItemFactory
        public void close(ServerSessionImpl serverSession) {
            ServerSessionPool.this.closeSession(serverSession);
        }

        @Override // com.mongodb.internal.connection.ConcurrentPool.ItemFactory
        public ConcurrentPool.Prune shouldPrune(ServerSessionImpl serverSession) {
            return ServerSessionPool.this.shouldPrune(serverSession) ? ConcurrentPool.Prune.YES : ConcurrentPool.Prune.STOP;
        }

        private BsonBinary createNewServerSessionIdentifier() {
            UuidCodec uuidCodec = new UuidCodec(UuidRepresentation.STANDARD);
            BsonDocument holder = new BsonDocument();
            BsonDocumentWriter bsonDocumentWriter = new BsonDocumentWriter(holder);
            bsonDocumentWriter.writeStartDocument();
            bsonDocumentWriter.writeName("id");
            uuidCodec.encode((BsonWriter) bsonDocumentWriter, UUID.randomUUID(), EncoderContext.builder().build());
            bsonDocumentWriter.writeEndDocument();
            return holder.getBinary("id");
        }
    }
}
