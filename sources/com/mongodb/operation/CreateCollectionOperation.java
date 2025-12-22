package com.mongodb.operation;

import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncWriteBinding;
import com.mongodb.binding.WriteBinding;
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.ValidationAction;
import com.mongodb.client.model.ValidationLevel;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.Connection;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.internal.operation.WriteConcernHelper;
import com.mongodb.operation.OperationHelper;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/CreateCollectionOperation.class */
public class CreateCollectionOperation implements AsyncWriteOperation<Void>, WriteOperation<Void> {
    private final String databaseName;
    private final String collectionName;
    private final WriteConcern writeConcern;
    private boolean capped;
    private long sizeInBytes;
    private boolean autoIndex;
    private long maxDocuments;
    private Boolean usePowerOf2Sizes;
    private BsonDocument storageEngineOptions;
    private BsonDocument indexOptionDefaults;
    private BsonDocument validator;
    private ValidationLevel validationLevel;
    private ValidationAction validationAction;
    private Collation collation;

    public CreateCollectionOperation(String databaseName, String collectionName) {
        this(databaseName, collectionName, null);
    }

    public CreateCollectionOperation(String databaseName, String collectionName, WriteConcern writeConcern) {
        this.capped = false;
        this.sizeInBytes = 0L;
        this.autoIndex = true;
        this.maxDocuments = 0L;
        this.usePowerOf2Sizes = null;
        this.validationLevel = null;
        this.validationAction = null;
        this.collation = null;
        this.databaseName = (String) Assertions.notNull("databaseName", databaseName);
        this.collectionName = (String) Assertions.notNull("collectionName", collectionName);
        this.writeConcern = writeConcern;
    }

    public String getCollectionName() {
        return this.collectionName;
    }

    public WriteConcern getWriteConcern() {
        return this.writeConcern;
    }

    public boolean isAutoIndex() {
        return this.autoIndex;
    }

    public CreateCollectionOperation autoIndex(boolean autoIndex) {
        this.autoIndex = autoIndex;
        return this;
    }

    public long getMaxDocuments() {
        return this.maxDocuments;
    }

    public CreateCollectionOperation maxDocuments(long maxDocuments) {
        this.maxDocuments = maxDocuments;
        return this;
    }

    public boolean isCapped() {
        return this.capped;
    }

    public CreateCollectionOperation capped(boolean capped) {
        this.capped = capped;
        return this;
    }

    public long getSizeInBytes() {
        return this.sizeInBytes;
    }

    public CreateCollectionOperation sizeInBytes(long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
        return this;
    }

    @Deprecated
    public Boolean isUsePowerOf2Sizes() {
        return this.usePowerOf2Sizes;
    }

    @Deprecated
    public CreateCollectionOperation usePowerOf2Sizes(Boolean usePowerOf2Sizes) {
        this.usePowerOf2Sizes = usePowerOf2Sizes;
        return this;
    }

    public BsonDocument getStorageEngineOptions() {
        return this.storageEngineOptions;
    }

    public CreateCollectionOperation storageEngineOptions(BsonDocument storageEngineOptions) {
        this.storageEngineOptions = storageEngineOptions;
        return this;
    }

    public BsonDocument getIndexOptionDefaults() {
        return this.indexOptionDefaults;
    }

    public CreateCollectionOperation indexOptionDefaults(BsonDocument indexOptionDefaults) {
        this.indexOptionDefaults = indexOptionDefaults;
        return this;
    }

    public BsonDocument getValidator() {
        return this.validator;
    }

    public CreateCollectionOperation validator(BsonDocument validator) {
        this.validator = validator;
        return this;
    }

    public ValidationLevel getValidationLevel() {
        return this.validationLevel;
    }

    public CreateCollectionOperation validationLevel(ValidationLevel validationLevel) {
        this.validationLevel = validationLevel;
        return this;
    }

    public ValidationAction getValidationAction() {
        return this.validationAction;
    }

    public CreateCollectionOperation validationAction(ValidationAction validationAction) {
        this.validationAction = validationAction;
        return this;
    }

    public Collation getCollation() {
        return this.collation;
    }

    public CreateCollectionOperation collation(Collation collation) {
        this.collation = collation;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.operation.WriteOperation
    public Void execute(final WriteBinding binding) {
        return (Void) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnection<Void>() { // from class: com.mongodb.operation.CreateCollectionOperation.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnection
            public Void call(Connection connection) {
                OperationHelper.validateCollation(connection, CreateCollectionOperation.this.collation);
                CommandOperationHelper.executeWrappedCommandProtocol(binding, CreateCollectionOperation.this.databaseName, CreateCollectionOperation.this.getCommand(connection.getDescription()), connection, CommandOperationHelper.writeConcernErrorTransformer());
                return null;
            }
        });
    }

    @Override // com.mongodb.operation.AsyncWriteOperation
    public void executeAsync(final AsyncWriteBinding binding, final SingleResultCallback<Void> callback) {
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.CreateCollectionOperation.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
            public void call(AsyncConnection connection, Throwable t) {
                SingleResultCallback<Void> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                if (t != null) {
                    errHandlingCallback.onResult(null, t);
                } else {
                    final SingleResultCallback<Void> wrappedCallback = OperationHelper.releasingCallback(errHandlingCallback, connection);
                    OperationHelper.validateCollation(connection, CreateCollectionOperation.this.collation, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.CreateCollectionOperation.2.1
                        @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
                        public void call(AsyncConnection connection2, Throwable t2) {
                            if (t2 != null) {
                                wrappedCallback.onResult(null, t2);
                            } else {
                                CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, CreateCollectionOperation.this.databaseName, CreateCollectionOperation.this.getCommand(connection2.getDescription()), connection2, CommandOperationHelper.writeConcernErrorTransformer(), wrappedCallback);
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand(ConnectionDescription description) {
        BsonDocument document = new BsonDocument("create", new BsonString(this.collectionName));
        DocumentHelper.putIfFalse(document, "autoIndexId", this.autoIndex);
        document.put("capped", (BsonValue) BsonBoolean.valueOf(this.capped));
        if (this.capped) {
            DocumentHelper.putIfNotZero(document, "size", this.sizeInBytes);
            DocumentHelper.putIfNotZero(document, "max", this.maxDocuments);
        }
        if (this.usePowerOf2Sizes != null) {
            document.put("flags", (BsonValue) new BsonInt32(1));
        }
        if (this.storageEngineOptions != null) {
            document.put("storageEngine", (BsonValue) this.storageEngineOptions);
        }
        if (this.indexOptionDefaults != null) {
            document.put("indexOptionDefaults", (BsonValue) this.indexOptionDefaults);
        }
        if (this.validator != null) {
            document.put("validator", (BsonValue) this.validator);
        }
        if (this.validationLevel != null) {
            document.put("validationLevel", (BsonValue) new BsonString(this.validationLevel.getValue()));
        }
        if (this.validationAction != null) {
            document.put("validationAction", (BsonValue) new BsonString(this.validationAction.getValue()));
        }
        WriteConcernHelper.appendWriteConcernToCommand(this.writeConcern, document, description);
        if (this.collation != null) {
            document.put("collation", (BsonValue) this.collation.asDocument());
        }
        return document;
    }
}
