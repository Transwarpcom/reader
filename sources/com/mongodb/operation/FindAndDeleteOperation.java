package com.mongodb.operation;

import com.mongodb.MongoNamespace;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.model.Collation;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.validator.NoOpFieldNameValidator;
import com.mongodb.operation.CommandOperationHelper;
import com.mongodb.session.SessionContext;
import java.util.concurrent.TimeUnit;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.FieldNameValidator;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/FindAndDeleteOperation.class */
public class FindAndDeleteOperation<T> extends BaseFindAndModifyOperation<T> {
    private BsonDocument filter;
    private BsonDocument projection;
    private BsonDocument sort;
    private long maxTimeMS;
    private Collation collation;

    @Deprecated
    public FindAndDeleteOperation(MongoNamespace namespace, Decoder<T> decoder) {
        this(namespace, WriteConcern.ACKNOWLEDGED, false, decoder);
    }

    @Deprecated
    public FindAndDeleteOperation(MongoNamespace namespace, WriteConcern writeConcern, Decoder<T> decoder) {
        this(namespace, writeConcern, false, decoder);
    }

    public FindAndDeleteOperation(MongoNamespace namespace, WriteConcern writeConcern, boolean retryWrites, Decoder<T> decoder) {
        super(namespace, writeConcern, retryWrites, decoder);
    }

    public BsonDocument getFilter() {
        return this.filter;
    }

    public FindAndDeleteOperation<T> filter(BsonDocument filter) {
        this.filter = filter;
        return this;
    }

    public BsonDocument getProjection() {
        return this.projection;
    }

    public FindAndDeleteOperation<T> projection(BsonDocument projection) {
        this.projection = projection;
        return this;
    }

    public long getMaxTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxTimeMS, TimeUnit.MILLISECONDS);
    }

    public FindAndDeleteOperation<T> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    public BsonDocument getSort() {
        return this.sort;
    }

    public FindAndDeleteOperation<T> sort(BsonDocument sort) {
        this.sort = sort;
        return this;
    }

    public Collation getCollation() {
        return this.collation;
    }

    public FindAndDeleteOperation<T> collation(Collation collation) {
        this.collation = collation;
        return this;
    }

    @Override // com.mongodb.operation.BaseFindAndModifyOperation
    protected String getDatabaseName() {
        return getNamespace().getDatabaseName();
    }

    @Override // com.mongodb.operation.BaseFindAndModifyOperation
    protected CommandOperationHelper.CommandCreator getCommandCreator(final SessionContext sessionContext) {
        return new CommandOperationHelper.CommandCreator() { // from class: com.mongodb.operation.FindAndDeleteOperation.1
            @Override // com.mongodb.operation.CommandOperationHelper.CommandCreator
            public BsonDocument create(ServerDescription serverDescription, ConnectionDescription connectionDescription) {
                OperationHelper.validateCollation(connectionDescription, FindAndDeleteOperation.this.collation);
                BsonDocument commandDocument = new BsonDocument("findAndModify", new BsonString(FindAndDeleteOperation.this.getNamespace().getCollectionName()));
                DocumentHelper.putIfNotNull(commandDocument, "query", FindAndDeleteOperation.this.getFilter());
                DocumentHelper.putIfNotNull(commandDocument, "fields", FindAndDeleteOperation.this.getProjection());
                DocumentHelper.putIfNotNull(commandDocument, "sort", FindAndDeleteOperation.this.getSort());
                DocumentHelper.putIfNotZero(commandDocument, "maxTimeMS", FindAndDeleteOperation.this.getMaxTime(TimeUnit.MILLISECONDS));
                commandDocument.put("remove", (BsonValue) BsonBoolean.TRUE);
                FindAndDeleteOperation.this.addWriteConcernToCommand(connectionDescription, commandDocument, sessionContext);
                if (FindAndDeleteOperation.this.collation != null) {
                    commandDocument.put("collation", (BsonValue) FindAndDeleteOperation.this.collation.asDocument());
                }
                FindAndDeleteOperation.this.addTxnNumberToCommand(serverDescription, connectionDescription, commandDocument, sessionContext);
                return commandDocument;
            }
        };
    }

    @Override // com.mongodb.operation.BaseFindAndModifyOperation
    protected FieldNameValidator getFieldNameValidator() {
        return new NoOpFieldNameValidator();
    }
}
