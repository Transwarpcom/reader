package com.mongodb.internal.connection;

import com.mongodb.annotations.Immutable;
import com.mongodb.annotations.NotThreadSafe;
import com.mongodb.connection.ServerType;
import com.mongodb.connection.ServerVersion;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/MessageSettings.class */
final class MessageSettings {
    private static final int DEFAULT_MAX_DOCUMENT_SIZE = 16777216;
    private static final int DEFAULT_MAX_MESSAGE_SIZE = 33554432;
    private static final int DEFAULT_MAX_BATCH_COUNT = 1000;
    private final int maxDocumentSize;
    private final int maxMessageSize;
    private final int maxBatchCount;
    private final ServerVersion serverVersion;
    private final ServerType serverType;

    public static Builder builder() {
        return new Builder();
    }

    @NotThreadSafe
    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/MessageSettings$Builder.class */
    public static final class Builder {
        private int maxDocumentSize = 16777216;
        private int maxMessageSize = MessageSettings.DEFAULT_MAX_MESSAGE_SIZE;
        private int maxBatchCount = 1000;
        private ServerVersion serverVersion;
        private ServerType serverType;

        public MessageSettings build() {
            return new MessageSettings(this);
        }

        public Builder maxDocumentSize(int maxDocumentSize) {
            this.maxDocumentSize = maxDocumentSize;
            return this;
        }

        public Builder maxMessageSize(int maxMessageSize) {
            this.maxMessageSize = maxMessageSize;
            return this;
        }

        public Builder maxBatchCount(int maxBatchCount) {
            this.maxBatchCount = maxBatchCount;
            return this;
        }

        public Builder serverVersion(ServerVersion serverVersion) {
            this.serverVersion = serverVersion;
            return this;
        }

        public Builder serverType(ServerType serverType) {
            this.serverType = serverType;
            return this;
        }
    }

    public int getMaxDocumentSize() {
        return this.maxDocumentSize;
    }

    public int getMaxMessageSize() {
        return this.maxMessageSize;
    }

    public int getMaxBatchCount() {
        return this.maxBatchCount;
    }

    public ServerVersion getServerVersion() {
        return this.serverVersion;
    }

    public ServerType getServerType() {
        return this.serverType;
    }

    private MessageSettings(Builder builder) {
        this.maxDocumentSize = builder.maxDocumentSize;
        this.maxMessageSize = builder.maxMessageSize;
        this.maxBatchCount = builder.maxBatchCount;
        this.serverVersion = builder.serverVersion;
        this.serverType = builder.serverType;
    }
}
