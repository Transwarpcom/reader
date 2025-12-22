package com.mongodb.operation;

import com.mongodb.MongoCommandException;
import com.mongodb.MongoException;
import com.mongodb.MongoNamespace;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.ServerCursor;
import com.mongodb.assertions.Assertions;
import com.mongodb.binding.ConnectionSource;
import com.mongodb.connection.Connection;
import com.mongodb.connection.QueryResult;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.internal.validator.NoOpFieldNameValidator;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.FieldNameValidator;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/QueryBatchCursor.class */
class QueryBatchCursor<T> implements BatchCursor<T> {
    private static final FieldNameValidator NO_OP_FIELD_NAME_VALIDATOR = new NoOpFieldNameValidator();
    private final MongoNamespace namespace;
    private final ServerAddress serverAddress;
    private final int limit;
    private final Decoder<T> decoder;
    private final long maxTimeMS;
    private int batchSize;
    private ConnectionSource connectionSource;
    private ServerCursor serverCursor;
    private List<T> nextBatch;
    private int count;
    private volatile boolean closed;

    QueryBatchCursor(QueryResult<T> firstQueryResult, int limit, int batchSize, Decoder<T> decoder) {
        this(firstQueryResult, limit, batchSize, decoder, null);
    }

    QueryBatchCursor(QueryResult<T> firstQueryResult, int limit, int batchSize, Decoder<T> decoder, ConnectionSource connectionSource) {
        this(firstQueryResult, limit, batchSize, 0L, decoder, connectionSource, null);
    }

    QueryBatchCursor(QueryResult<T> firstQueryResult, int limit, int batchSize, long maxTimeMS, Decoder<T> decoder, ConnectionSource connectionSource, Connection connection) {
        Assertions.isTrueArgument("maxTimeMS >= 0", maxTimeMS >= 0);
        this.maxTimeMS = maxTimeMS;
        this.namespace = firstQueryResult.getNamespace();
        this.serverAddress = firstQueryResult.getAddress();
        this.limit = limit;
        this.batchSize = batchSize;
        this.decoder = (Decoder) Assertions.notNull("decoder", decoder);
        if (firstQueryResult.getCursor() != null) {
            Assertions.notNull("connectionSource", connectionSource);
        }
        if (connectionSource != null) {
            this.connectionSource = connectionSource.retain();
        } else {
            this.connectionSource = null;
        }
        initFromQueryResult(firstQueryResult);
        if (limitReached()) {
            killCursor(connection);
        }
        if (this.serverCursor == null && this.connectionSource != null) {
            this.connectionSource.release();
            this.connectionSource = null;
        }
    }

    @Override // com.mongodb.operation.BatchCursor, java.util.Iterator
    public boolean hasNext() {
        if (this.closed) {
            throw new IllegalStateException("Cursor has been closed");
        }
        if (this.nextBatch != null) {
            return true;
        }
        if (limitReached()) {
            return false;
        }
        while (this.serverCursor != null) {
            getMore();
            if (this.closed) {
                throw new IllegalStateException("Cursor has been closed");
            }
            if (this.nextBatch != null) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Iterator
    public List<T> next() {
        if (this.closed) {
            throw new IllegalStateException("Iterator has been closed");
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        List<T> retVal = this.nextBatch;
        this.nextBatch = null;
        return retVal;
    }

    @Override // com.mongodb.operation.BatchCursor
    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    @Override // com.mongodb.operation.BatchCursor
    public int getBatchSize() {
        return this.batchSize;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override // com.mongodb.operation.BatchCursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.closed) {
            this.closed = true;
            try {
                killCursor();
            } finally {
                if (this.connectionSource != null) {
                    this.connectionSource.release();
                }
            }
        }
    }

    @Override // com.mongodb.operation.BatchCursor
    public List<T> tryNext() {
        if (this.closed) {
            throw new IllegalStateException("Cursor has been closed");
        }
        if (!tryHasNext()) {
            return null;
        }
        return next();
    }

    boolean tryHasNext() {
        if (this.nextBatch != null) {
            return true;
        }
        if (limitReached()) {
            return false;
        }
        if (this.serverCursor != null) {
            getMore();
        }
        return this.nextBatch != null;
    }

    @Override // com.mongodb.operation.BatchCursor
    public ServerCursor getServerCursor() {
        if (this.closed) {
            throw new IllegalStateException("Iterator has been closed");
        }
        return this.serverCursor;
    }

    @Override // com.mongodb.operation.BatchCursor
    public ServerAddress getServerAddress() {
        if (this.closed) {
            throw new IllegalStateException("Iterator has been closed");
        }
        return this.serverAddress;
    }

    private void getMore() {
        Connection connection = this.connectionSource.getConnection();
        try {
            if (ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connection.getDescription())) {
                try {
                    initFromCommandResult((BsonDocument) connection.command(this.namespace.getDatabaseName(), asGetMoreCommandDocument(), NO_OP_FIELD_NAME_VALIDATOR, ReadPreference.primary(), CommandResultDocumentCodec.create(this.decoder, "nextBatch"), this.connectionSource.getSessionContext()));
                } catch (MongoCommandException e) {
                    throw QueryHelper.translateCommandException(e, this.serverCursor);
                }
            } else {
                QueryResult<T> getMore = connection.getMore(this.namespace, this.serverCursor.getId(), CursorHelper.getNumberToReturn(this.limit, this.batchSize, this.count), this.decoder);
                initFromQueryResult(getMore);
            }
            if (limitReached()) {
                killCursor(connection);
            }
            if (this.serverCursor == null) {
                this.connectionSource.release();
                this.connectionSource = null;
            }
        } finally {
            connection.release();
        }
    }

    private BsonDocument asGetMoreCommandDocument() {
        BsonDocument document = new BsonDocument("getMore", new BsonInt64(this.serverCursor.getId())).append("collection", new BsonString(this.namespace.getCollectionName()));
        int batchSizeForGetMoreCommand = Math.abs(CursorHelper.getNumberToReturn(this.limit, this.batchSize, this.count));
        if (batchSizeForGetMoreCommand != 0) {
            document.append("batchSize", new BsonInt32(batchSizeForGetMoreCommand));
        }
        if (this.maxTimeMS != 0) {
            document.append("maxTimeMS", new BsonInt64(this.maxTimeMS));
        }
        return document;
    }

    private void initFromQueryResult(QueryResult<T> queryResult) {
        this.serverCursor = queryResult.getCursor();
        this.nextBatch = queryResult.getResults().isEmpty() ? null : queryResult.getResults();
        this.count += queryResult.getResults().size();
    }

    private void initFromCommandResult(BsonDocument getMoreCommandResultDocument) {
        QueryResult<T> queryResult = OperationHelper.getMoreCursorDocumentToQueryResult(getMoreCommandResultDocument.getDocument("cursor"), this.connectionSource.getServerDescription().getAddress());
        initFromQueryResult(queryResult);
    }

    private boolean limitReached() {
        return Math.abs(this.limit) != 0 && this.count >= Math.abs(this.limit);
    }

    private void killCursor() {
        if (this.serverCursor != null) {
            try {
                Connection connection = this.connectionSource.getConnection();
                try {
                    killCursor(connection);
                    connection.release();
                } catch (Throwable th) {
                    connection.release();
                    throw th;
                }
            } catch (MongoException e) {
            }
        }
    }

    private void killCursor(Connection connection) {
        if (this.serverCursor != null) {
            Assertions.notNull("connection", connection);
            if (ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connection.getDescription())) {
                connection.command(this.namespace.getDatabaseName(), asKillCursorsCommandDocument(), NO_OP_FIELD_NAME_VALIDATOR, ReadPreference.primary(), new BsonDocumentCodec(), this.connectionSource.getSessionContext());
            } else {
                connection.killCursor(this.namespace, Collections.singletonList(Long.valueOf(this.serverCursor.getId())));
            }
            this.serverCursor = null;
        }
    }

    private BsonDocument asKillCursorsCommandDocument() {
        return new BsonDocument("killCursors", new BsonString(this.namespace.getCollectionName())).append("cursors", new BsonArray(Collections.singletonList(new BsonInt64(this.serverCursor.getId()))));
    }
}
