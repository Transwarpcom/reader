package com.mongodb.operation;

import com.mongodb.MongoNamespace;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.model.Collation;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.internal.validator.CollectibleDocumentFieldNameValidator;
import com.mongodb.internal.validator.MappedFieldNameValidator;
import com.mongodb.internal.validator.NoOpFieldNameValidator;
import com.mongodb.operation.CommandOperationHelper;
import com.mongodb.session.SessionContext;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.FieldNameValidator;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/FindAndReplaceOperation.class */
public class FindAndReplaceOperation<T> extends BaseFindAndModifyOperation<T> {
    private final BsonDocument replacement;
    private BsonDocument filter;
    private BsonDocument projection;
    private BsonDocument sort;
    private long maxTimeMS;
    private boolean returnOriginal;
    private boolean upsert;
    private Boolean bypassDocumentValidation;
    private Collation collation;

    @Deprecated
    public FindAndReplaceOperation(MongoNamespace namespace, Decoder<T> decoder, BsonDocument replacement) {
        this(namespace, WriteConcern.ACKNOWLEDGED, false, decoder, replacement);
    }

    @Deprecated
    public FindAndReplaceOperation(MongoNamespace namespace, WriteConcern writeConcern, Decoder<T> decoder, BsonDocument replacement) {
        this(namespace, writeConcern, false, decoder, replacement);
    }

    public FindAndReplaceOperation(MongoNamespace namespace, WriteConcern writeConcern, boolean retryWrites, Decoder<T> decoder, BsonDocument replacement) {
        super(namespace, writeConcern, retryWrites, decoder);
        this.returnOriginal = true;
        this.replacement = (BsonDocument) Assertions.notNull("replacement", replacement);
    }

    public BsonDocument getReplacement() {
        return this.replacement;
    }

    public BsonDocument getFilter() {
        return this.filter;
    }

    public FindAndReplaceOperation<T> filter(BsonDocument filter) {
        this.filter = filter;
        return this;
    }

    public BsonDocument getProjection() {
        return this.projection;
    }

    public FindAndReplaceOperation<T> projection(BsonDocument projection) {
        this.projection = projection;
        return this;
    }

    public long getMaxTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxTimeMS, TimeUnit.MILLISECONDS);
    }

    public FindAndReplaceOperation<T> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    public BsonDocument getSort() {
        return this.sort;
    }

    public FindAndReplaceOperation<T> sort(BsonDocument sort) {
        this.sort = sort;
        return this;
    }

    public boolean isReturnOriginal() {
        return this.returnOriginal;
    }

    public FindAndReplaceOperation<T> returnOriginal(boolean returnOriginal) {
        this.returnOriginal = returnOriginal;
        return this;
    }

    public boolean isUpsert() {
        return this.upsert;
    }

    public FindAndReplaceOperation<T> upsert(boolean upsert) {
        this.upsert = upsert;
        return this;
    }

    public Boolean getBypassDocumentValidation() {
        return this.bypassDocumentValidation;
    }

    public FindAndReplaceOperation<T> bypassDocumentValidation(Boolean bypassDocumentValidation) {
        this.bypassDocumentValidation = bypassDocumentValidation;
        return this;
    }

    public Collation getCollation() {
        return this.collation;
    }

    public FindAndReplaceOperation<T> collation(Collation collation) {
        this.collation = collation;
        return this;
    }

    @Override // com.mongodb.operation.BaseFindAndModifyOperation
    protected String getDatabaseName() {
        return getNamespace().getDatabaseName();
    }

    @Override // com.mongodb.operation.BaseFindAndModifyOperation
    protected CommandOperationHelper.CommandCreator getCommandCreator(final SessionContext sessionContext) {
        return new CommandOperationHelper.CommandCreator() { // from class: com.mongodb.operation.FindAndReplaceOperation.1
            @Override // com.mongodb.operation.CommandOperationHelper.CommandCreator
            public BsonDocument create(ServerDescription serverDescription, ConnectionDescription connectionDescription) {
                OperationHelper.validateCollation(connectionDescription, FindAndReplaceOperation.this.collation);
                BsonDocument commandDocument = new BsonDocument("findAndModify", new BsonString(FindAndReplaceOperation.this.getNamespace().getCollectionName()));
                DocumentHelper.putIfNotNull(commandDocument, "query", FindAndReplaceOperation.this.getFilter());
                DocumentHelper.putIfNotNull(commandDocument, "fields", FindAndReplaceOperation.this.getProjection());
                DocumentHelper.putIfNotNull(commandDocument, "sort", FindAndReplaceOperation.this.getSort());
                commandDocument.put("new", (BsonValue) new BsonBoolean(!FindAndReplaceOperation.this.isReturnOriginal()));
                DocumentHelper.putIfTrue(commandDocument, "upsert", FindAndReplaceOperation.this.isUpsert());
                DocumentHelper.putIfNotZero(commandDocument, "maxTimeMS", FindAndReplaceOperation.this.getMaxTime(TimeUnit.MILLISECONDS));
                commandDocument.put("update", (BsonValue) FindAndReplaceOperation.this.getReplacement());
                if (FindAndReplaceOperation.this.bypassDocumentValidation != null && ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connectionDescription)) {
                    commandDocument.put("bypassDocumentValidation", (BsonValue) BsonBoolean.valueOf(FindAndReplaceOperation.this.bypassDocumentValidation.booleanValue()));
                }
                FindAndReplaceOperation.this.addWriteConcernToCommand(connectionDescription, commandDocument, sessionContext);
                if (FindAndReplaceOperation.this.collation != null) {
                    commandDocument.put("collation", (BsonValue) FindAndReplaceOperation.this.collation.asDocument());
                }
                FindAndReplaceOperation.this.addTxnNumberToCommand(serverDescription, connectionDescription, commandDocument, sessionContext);
                return commandDocument;
            }
        };
    }

    @Override // com.mongodb.operation.BaseFindAndModifyOperation
    protected FieldNameValidator getFieldNameValidator() {
        Map<String, FieldNameValidator> map = new HashMap<>();
        map.put("update", new CollectibleDocumentFieldNameValidator());
        return new MappedFieldNameValidator(new NoOpFieldNameValidator(), map);
    }
}
