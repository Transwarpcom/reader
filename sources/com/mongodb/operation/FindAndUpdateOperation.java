package com.mongodb.operation;

import com.mongodb.MongoNamespace;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.model.Collation;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.internal.validator.MappedFieldNameValidator;
import com.mongodb.internal.validator.NoOpFieldNameValidator;
import com.mongodb.internal.validator.UpdateFieldNameValidator;
import com.mongodb.operation.CommandOperationHelper;
import com.mongodb.session.SessionContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.bson.BsonArray;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.FieldNameValidator;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/FindAndUpdateOperation.class */
public class FindAndUpdateOperation<T> extends BaseFindAndModifyOperation<T> {
    private final BsonDocument update;
    private BsonDocument filter;
    private BsonDocument projection;
    private BsonDocument sort;
    private long maxTimeMS;
    private boolean returnOriginal;
    private boolean upsert;
    private Boolean bypassDocumentValidation;
    private Collation collation;
    private List<BsonDocument> arrayFilters;

    @Deprecated
    public FindAndUpdateOperation(MongoNamespace namespace, Decoder<T> decoder, BsonDocument update) {
        this(namespace, WriteConcern.ACKNOWLEDGED, false, decoder, update);
    }

    @Deprecated
    public FindAndUpdateOperation(MongoNamespace namespace, WriteConcern writeConcern, Decoder<T> decoder, BsonDocument update) {
        this(namespace, writeConcern, false, decoder, update);
    }

    public FindAndUpdateOperation(MongoNamespace namespace, WriteConcern writeConcern, boolean retryWrites, Decoder<T> decoder, BsonDocument update) {
        super(namespace, writeConcern, retryWrites, decoder);
        this.returnOriginal = true;
        this.update = (BsonDocument) Assertions.notNull("decoder", update);
    }

    public BsonDocument getUpdate() {
        return this.update;
    }

    public BsonDocument getFilter() {
        return this.filter;
    }

    public FindAndUpdateOperation<T> filter(BsonDocument filter) {
        this.filter = filter;
        return this;
    }

    public BsonDocument getProjection() {
        return this.projection;
    }

    public FindAndUpdateOperation<T> projection(BsonDocument projection) {
        this.projection = projection;
        return this;
    }

    public long getMaxTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxTimeMS, TimeUnit.MILLISECONDS);
    }

    public FindAndUpdateOperation<T> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    public BsonDocument getSort() {
        return this.sort;
    }

    public FindAndUpdateOperation<T> sort(BsonDocument sort) {
        this.sort = sort;
        return this;
    }

    public boolean isReturnOriginal() {
        return this.returnOriginal;
    }

    public FindAndUpdateOperation<T> returnOriginal(boolean returnOriginal) {
        this.returnOriginal = returnOriginal;
        return this;
    }

    public boolean isUpsert() {
        return this.upsert;
    }

    public FindAndUpdateOperation<T> upsert(boolean upsert) {
        this.upsert = upsert;
        return this;
    }

    public Boolean getBypassDocumentValidation() {
        return this.bypassDocumentValidation;
    }

    public FindAndUpdateOperation<T> bypassDocumentValidation(Boolean bypassDocumentValidation) {
        this.bypassDocumentValidation = bypassDocumentValidation;
        return this;
    }

    public Collation getCollation() {
        return this.collation;
    }

    public FindAndUpdateOperation<T> collation(Collation collation) {
        this.collation = collation;
        return this;
    }

    public FindAndUpdateOperation<T> arrayFilters(List<BsonDocument> arrayFilters) {
        this.arrayFilters = arrayFilters;
        return this;
    }

    public List<BsonDocument> getArrayFilters() {
        return this.arrayFilters;
    }

    @Override // com.mongodb.operation.BaseFindAndModifyOperation
    protected String getDatabaseName() {
        return getNamespace().getDatabaseName();
    }

    @Override // com.mongodb.operation.BaseFindAndModifyOperation
    protected FieldNameValidator getFieldNameValidator() {
        Map<String, FieldNameValidator> map = new HashMap<>();
        map.put("update", new UpdateFieldNameValidator());
        return new MappedFieldNameValidator(new NoOpFieldNameValidator(), map);
    }

    @Override // com.mongodb.operation.BaseFindAndModifyOperation
    protected CommandOperationHelper.CommandCreator getCommandCreator(final SessionContext sessionContext) {
        return new CommandOperationHelper.CommandCreator() { // from class: com.mongodb.operation.FindAndUpdateOperation.1
            @Override // com.mongodb.operation.CommandOperationHelper.CommandCreator
            public BsonDocument create(ServerDescription serverDescription, ConnectionDescription connectionDescription) {
                OperationHelper.validateCollation(connectionDescription, FindAndUpdateOperation.this.collation);
                BsonDocument commandDocument = new BsonDocument("findAndModify", new BsonString(FindAndUpdateOperation.this.getNamespace().getCollectionName()));
                DocumentHelper.putIfNotNull(commandDocument, "query", FindAndUpdateOperation.this.getFilter());
                DocumentHelper.putIfNotNull(commandDocument, "fields", FindAndUpdateOperation.this.getProjection());
                DocumentHelper.putIfNotNull(commandDocument, "sort", FindAndUpdateOperation.this.getSort());
                commandDocument.put("new", (BsonValue) new BsonBoolean(!FindAndUpdateOperation.this.isReturnOriginal()));
                DocumentHelper.putIfTrue(commandDocument, "upsert", FindAndUpdateOperation.this.isUpsert());
                DocumentHelper.putIfNotZero(commandDocument, "maxTimeMS", FindAndUpdateOperation.this.getMaxTime(TimeUnit.MILLISECONDS));
                commandDocument.put("update", (BsonValue) FindAndUpdateOperation.this.getUpdate());
                if (FindAndUpdateOperation.this.bypassDocumentValidation != null && ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connectionDescription)) {
                    commandDocument.put("bypassDocumentValidation", (BsonValue) BsonBoolean.valueOf(FindAndUpdateOperation.this.bypassDocumentValidation.booleanValue()));
                }
                FindAndUpdateOperation.this.addWriteConcernToCommand(connectionDescription, commandDocument, sessionContext);
                if (FindAndUpdateOperation.this.collation != null) {
                    commandDocument.put("collation", (BsonValue) FindAndUpdateOperation.this.collation.asDocument());
                }
                if (FindAndUpdateOperation.this.arrayFilters != null) {
                    commandDocument.put("arrayFilters", (BsonValue) new BsonArray(FindAndUpdateOperation.this.arrayFilters));
                }
                FindAndUpdateOperation.this.addTxnNumberToCommand(serverDescription, connectionDescription, commandDocument, sessionContext);
                return commandDocument;
            }
        };
    }
}
