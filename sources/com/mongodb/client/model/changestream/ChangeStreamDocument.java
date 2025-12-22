package com.mongodb.client.model.changestream;

import com.mongodb.MongoNamespace;
import com.mongodb.assertions.Assertions;
import com.mongodb.lang.Nullable;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonTimestamp;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/changestream/ChangeStreamDocument.class */
public final class ChangeStreamDocument<TDocument> {

    @BsonId
    private final BsonDocument resumeToken;
    private final BsonDocument namespaceDocument;
    private final TDocument fullDocument;
    private final BsonDocument documentKey;
    private final BsonTimestamp clusterTime;
    private final OperationType operationType;
    private final UpdateDescription updateDescription;

    @Deprecated
    public ChangeStreamDocument(BsonDocument resumeToken, MongoNamespace namespace, TDocument fullDocument, BsonDocument documentKey, OperationType operationType, UpdateDescription updateDescription) {
        this(resumeToken, namespace, fullDocument, documentKey, (BsonTimestamp) null, operationType, updateDescription);
    }

    @Deprecated
    public ChangeStreamDocument(BsonDocument resumeToken, MongoNamespace namespace, TDocument fullDocument, BsonDocument documentKey, @Nullable BsonTimestamp clusterTime, OperationType operationType, UpdateDescription updateDescription) {
        this(resumeToken, namespaceToDocument(namespace), fullDocument, documentKey, clusterTime, operationType, updateDescription);
    }

    @BsonCreator
    public ChangeStreamDocument(@BsonProperty("resumeToken") BsonDocument resumeToken, @BsonProperty("ns") BsonDocument namespaceDocument, @BsonProperty("fullDocument") TDocument fullDocument, @BsonProperty("documentKey") BsonDocument documentKey, @Nullable @BsonProperty("clusterTime") BsonTimestamp clusterTime, @BsonProperty("operationType") OperationType operationType, @BsonProperty("updateDescription") UpdateDescription updateDescription) {
        this.resumeToken = resumeToken;
        this.namespaceDocument = namespaceDocument;
        this.documentKey = documentKey;
        this.fullDocument = fullDocument;
        this.clusterTime = clusterTime;
        this.operationType = operationType;
        this.updateDescription = updateDescription;
    }

    private static BsonDocument namespaceToDocument(MongoNamespace namespace) {
        Assertions.notNull("namespace", namespace);
        return new BsonDocument("db", new BsonString(namespace.getDatabaseName())).append("coll", new BsonString(namespace.getCollectionName()));
    }

    public BsonDocument getResumeToken() {
        return this.resumeToken;
    }

    @Nullable
    @BsonIgnore
    public MongoNamespace getNamespace() {
        if (this.namespaceDocument == null || !this.namespaceDocument.containsKey("db") || !this.namespaceDocument.containsKey("coll")) {
            return null;
        }
        return new MongoNamespace(this.namespaceDocument.getString("db").getValue(), this.namespaceDocument.getString("coll").getValue());
    }

    @BsonProperty("ns")
    public BsonDocument getNamespaceDocument() {
        return this.namespaceDocument;
    }

    @Nullable
    @BsonIgnore
    public String getDatabaseName() {
        if (this.namespaceDocument == null || !this.namespaceDocument.containsKey("db")) {
            return null;
        }
        return this.namespaceDocument.getString("db").getValue();
    }

    public TDocument getFullDocument() {
        return this.fullDocument;
    }

    public BsonDocument getDocumentKey() {
        return this.documentKey;
    }

    @Nullable
    public BsonTimestamp getClusterTime() {
        return this.clusterTime;
    }

    public OperationType getOperationType() {
        return this.operationType;
    }

    public UpdateDescription getUpdateDescription() {
        return this.updateDescription;
    }

    public static <TFullDocument> Codec<ChangeStreamDocument<TFullDocument>> createCodec(Class<TFullDocument> fullDocumentClass, CodecRegistry codecRegistry) {
        return new ChangeStreamDocumentCodec(fullDocumentClass, codecRegistry);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChangeStreamDocument<?> that = (ChangeStreamDocument) o;
        if (this.resumeToken != null) {
            if (!this.resumeToken.equals(that.resumeToken)) {
                return false;
            }
        } else if (that.resumeToken != null) {
            return false;
        }
        if (this.namespaceDocument != null) {
            if (!this.namespaceDocument.equals(that.namespaceDocument)) {
                return false;
            }
        } else if (that.namespaceDocument != null) {
            return false;
        }
        if (this.fullDocument != null) {
            if (!this.fullDocument.equals(that.fullDocument)) {
                return false;
            }
        } else if (that.fullDocument != null) {
            return false;
        }
        if (this.documentKey != null) {
            if (!this.documentKey.equals(that.documentKey)) {
                return false;
            }
        } else if (that.documentKey != null) {
            return false;
        }
        if (this.clusterTime != null) {
            if (!this.clusterTime.equals(that.clusterTime)) {
                return false;
            }
        } else if (that.clusterTime != null) {
            return false;
        }
        if (this.operationType != that.operationType) {
            return false;
        }
        if (this.updateDescription != null) {
            if (!this.updateDescription.equals(that.updateDescription)) {
                return false;
            }
            return true;
        }
        if (that.updateDescription != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.resumeToken != null ? this.resumeToken.hashCode() : 0;
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + (this.namespaceDocument != null ? this.namespaceDocument.hashCode() : 0))) + (this.fullDocument != null ? this.fullDocument.hashCode() : 0))) + (this.documentKey != null ? this.documentKey.hashCode() : 0))) + (this.clusterTime != null ? this.clusterTime.hashCode() : 0))) + (this.operationType != null ? this.operationType.hashCode() : 0))) + (this.updateDescription != null ? this.updateDescription.hashCode() : 0);
    }

    public String toString() {
        return "ChangeStreamDocument{resumeToken=" + this.resumeToken + ", namespace=" + getNamespace() + ", fullDocument=" + this.fullDocument + ", documentKey=" + this.documentKey + ", clusterTime=" + this.clusterTime + ", operationType=" + this.operationType + ", updateDescription=" + this.updateDescription + "}";
    }
}
