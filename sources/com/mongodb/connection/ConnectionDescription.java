package com.mongodb.connection;

import com.mongodb.ServerAddress;
import com.mongodb.annotations.Immutable;
import com.mongodb.assertions.Assertions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ConnectionDescription.class */
public class ConnectionDescription {
    private final ConnectionId connectionId;
    private final ServerVersion serverVersion;
    private final ServerType serverType;
    private final int maxBatchCount;
    private final int maxDocumentSize;
    private final int maxMessageSize;
    private final List<String> compressors;
    private static final int DEFAULT_MAX_MESSAGE_SIZE = 33554432;
    private static final int DEFAULT_MAX_WRITE_BATCH_SIZE = 512;

    public ConnectionDescription(ServerId serverId) {
        this(new ConnectionId(serverId), new ServerVersion(), ServerType.UNKNOWN, 512, ServerDescription.getDefaultMaxDocumentSize(), DEFAULT_MAX_MESSAGE_SIZE, Collections.emptyList());
    }

    public ConnectionDescription(ConnectionId connectionId, ServerVersion serverVersion, ServerType serverType, int maxBatchCount, int maxDocumentSize, int maxMessageSize) {
        this(connectionId, serverVersion, serverType, maxBatchCount, maxDocumentSize, maxMessageSize, Collections.emptyList());
    }

    public ConnectionDescription(ConnectionId connectionId, ServerVersion serverVersion, ServerType serverType, int maxBatchCount, int maxDocumentSize, int maxMessageSize, List<String> compressors) {
        this.connectionId = connectionId;
        this.serverType = serverType;
        this.maxBatchCount = maxBatchCount;
        this.maxDocumentSize = maxDocumentSize;
        this.maxMessageSize = maxMessageSize;
        this.serverVersion = serverVersion;
        this.compressors = (List) Assertions.notNull("compressors", Collections.unmodifiableList(new ArrayList(compressors)));
    }

    public ConnectionDescription withConnectionId(ConnectionId connectionId) {
        Assertions.notNull("connectionId", connectionId);
        return new ConnectionDescription(connectionId, this.serverVersion, this.serverType, this.maxBatchCount, this.maxDocumentSize, this.maxMessageSize, this.compressors);
    }

    public ServerAddress getServerAddress() {
        return this.connectionId.getServerId().getAddress();
    }

    public ConnectionId getConnectionId() {
        return this.connectionId;
    }

    public ServerVersion getServerVersion() {
        return this.serverVersion;
    }

    public ServerType getServerType() {
        return this.serverType;
    }

    public int getMaxBatchCount() {
        return this.maxBatchCount;
    }

    public int getMaxDocumentSize() {
        return this.maxDocumentSize;
    }

    public int getMaxMessageSize() {
        return this.maxMessageSize;
    }

    public List<String> getCompressors() {
        return this.compressors;
    }

    public static int getDefaultMaxMessageSize() {
        return DEFAULT_MAX_MESSAGE_SIZE;
    }

    public static int getDefaultMaxWriteBatchSize() {
        return 512;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConnectionDescription that = (ConnectionDescription) o;
        if (this.maxBatchCount != that.maxBatchCount || this.maxDocumentSize != that.maxDocumentSize || this.maxMessageSize != that.maxMessageSize || !this.connectionId.equals(that.connectionId) || this.serverType != that.serverType || !this.serverVersion.equals(that.serverVersion) || !this.compressors.equals(that.compressors)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.connectionId.hashCode();
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + this.serverVersion.hashCode())) + this.serverType.hashCode())) + this.maxBatchCount)) + this.maxDocumentSize)) + this.maxMessageSize)) + this.compressors.hashCode();
    }
}
