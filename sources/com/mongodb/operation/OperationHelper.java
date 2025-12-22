package com.mongodb.operation;

import com.mongodb.MongoClientException;
import com.mongodb.MongoException;
import com.mongodb.MongoNamespace;
import com.mongodb.ReadConcern;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.AsyncBatchCursor;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncConnectionSource;
import com.mongodb.binding.AsyncReadBinding;
import com.mongodb.binding.AsyncWriteBinding;
import com.mongodb.binding.ConnectionSource;
import com.mongodb.binding.ReadBinding;
import com.mongodb.binding.ReferenceCounted;
import com.mongodb.binding.WriteBinding;
import com.mongodb.bulk.DeleteRequest;
import com.mongodb.bulk.IndexRequest;
import com.mongodb.bulk.UpdateRequest;
import com.mongodb.bulk.WriteRequest;
import com.mongodb.client.model.Collation;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.Connection;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.QueryResult;
import com.mongodb.connection.ServerDescription;
import com.mongodb.connection.ServerType;
import com.mongodb.connection.ServerVersion;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.session.SessionContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/OperationHelper.class */
final class OperationHelper {
    public static final Logger LOGGER = Loggers.getLogger("operation");

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/OperationHelper$AsyncCallableWithConnection.class */
    interface AsyncCallableWithConnection {
        void call(AsyncConnection asyncConnection, Throwable th);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/OperationHelper$AsyncCallableWithConnectionAndSource.class */
    interface AsyncCallableWithConnectionAndSource {
        void call(AsyncConnectionSource asyncConnectionSource, AsyncConnection asyncConnection, Throwable th);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/OperationHelper$CallableWithConnection.class */
    interface CallableWithConnection<T> {
        T call(Connection connection);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/OperationHelper$CallableWithConnectionAndSource.class */
    interface CallableWithConnectionAndSource<T> {
        T call(ConnectionSource connectionSource, Connection connection);
    }

    static void validateReadConcern(Connection connection, ReadConcern readConcern) {
        if (!ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connection.getDescription()) && !readConcern.isServerDefault()) {
            throw new IllegalArgumentException(String.format("ReadConcern not supported by server version: %s", connection.getDescription().getServerVersion()));
        }
    }

    static void validateReadConcern(AsyncConnection connection, ReadConcern readConcern, AsyncCallableWithConnection callable) {
        Throwable throwable = null;
        if (!ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connection.getDescription()) && !readConcern.isServerDefault()) {
            throwable = new IllegalArgumentException(String.format("ReadConcern not supported by server version: %s", connection.getDescription().getServerVersion()));
        }
        callable.call(connection, throwable);
    }

    static void validateReadConcern(final AsyncConnectionSource source, AsyncConnection connection, ReadConcern readConcern, final AsyncCallableWithConnectionAndSource callable) {
        validateReadConcern(connection, readConcern, new AsyncCallableWithConnection() { // from class: com.mongodb.operation.OperationHelper.1
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
            public void call(AsyncConnection connection2, Throwable t) {
                callable.call(source, connection2, t);
            }
        });
    }

    static void validateCollation(Connection connection, Collation collation) {
        validateCollation(connection.getDescription(), collation);
    }

    static void validateCollation(ConnectionDescription connectionDescription, Collation collation) {
        if (collation != null && !ServerVersionHelper.serverIsAtLeastVersionThreeDotFour(connectionDescription)) {
            throw new IllegalArgumentException(String.format("Collation not supported by server version: %s", connectionDescription.getServerVersion()));
        }
    }

    static void validateCollationAndWriteConcern(ConnectionDescription connectionDescription, Collation collation, WriteConcern writeConcern) {
        if (collation != null && !ServerVersionHelper.serverIsAtLeastVersionThreeDotFour(connectionDescription)) {
            throw new IllegalArgumentException(String.format("Collation not supported by server version: %s", connectionDescription.getServerVersion()));
        }
        if (collation != null && !writeConcern.isAcknowledged()) {
            throw new MongoClientException("Specifying collation with an unacknowledged WriteConcern is not supported");
        }
    }

    static void validateCollation(AsyncConnection connection, Collation collation, AsyncCallableWithConnection callable) {
        Throwable throwable = null;
        if (!ServerVersionHelper.serverIsAtLeastVersionThreeDotFour(connection.getDescription()) && collation != null) {
            throwable = new IllegalArgumentException(String.format("Collation not supported by server version: %s", connection.getDescription().getServerVersion()));
        }
        callable.call(connection, throwable);
    }

    static void validateCollation(final AsyncConnectionSource source, AsyncConnection connection, Collation collation, final AsyncCallableWithConnectionAndSource callable) {
        validateCollation(connection, collation, new AsyncCallableWithConnection() { // from class: com.mongodb.operation.OperationHelper.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
            public void call(AsyncConnection connection2, Throwable t) {
                callable.call(source, connection2, t);
            }
        });
    }

    static void validateWriteRequestCollations(ConnectionDescription connectionDescription, List<? extends WriteRequest> requests, WriteConcern writeConcern) {
        Collation collation = null;
        for (WriteRequest request : requests) {
            if (request instanceof UpdateRequest) {
                collation = ((UpdateRequest) request).getCollation();
            } else if (request instanceof DeleteRequest) {
                collation = ((DeleteRequest) request).getCollation();
            }
            if (collation != null) {
                break;
            }
        }
        validateCollationAndWriteConcern(connectionDescription, collation, writeConcern);
    }

    static void validateWriteRequests(ConnectionDescription connectionDescription, Boolean bypassDocumentValidation, List<? extends WriteRequest> requests, WriteConcern writeConcern) {
        checkBypassDocumentValidationIsSupported(connectionDescription, bypassDocumentValidation, writeConcern);
        validateWriteRequestCollations(connectionDescription, requests, writeConcern);
    }

    static void validateWriteRequests(AsyncConnection connection, Boolean bypassDocumentValidation, List<? extends WriteRequest> requests, WriteConcern writeConcern, AsyncCallableWithConnection callable) {
        try {
            validateWriteRequests(connection.getDescription(), bypassDocumentValidation, requests, writeConcern);
            callable.call(connection, null);
        } catch (Throwable t) {
            callable.call(connection, t);
        }
    }

    static void validateIndexRequestCollations(Connection connection, List<IndexRequest> requests) {
        for (IndexRequest request : requests) {
            if (request.getCollation() != null) {
                validateCollation(connection, request.getCollation());
                return;
            }
        }
    }

    static void validateIndexRequestCollations(AsyncConnection connection, List<IndexRequest> requests, final AsyncCallableWithConnection callable) {
        boolean calledTheCallable = false;
        Iterator<IndexRequest> it = requests.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            IndexRequest request = it.next();
            if (request.getCollation() != null) {
                calledTheCallable = true;
                validateCollation(connection, request.getCollation(), new AsyncCallableWithConnection() { // from class: com.mongodb.operation.OperationHelper.3
                    @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
                    public void call(AsyncConnection connection2, Throwable t) {
                        callable.call(connection2, t);
                    }
                });
                break;
            }
        }
        if (!calledTheCallable) {
            callable.call(connection, null);
        }
    }

    static void validateReadConcernAndCollation(Connection connection, ReadConcern readConcern, Collation collation) {
        validateReadConcern(connection, readConcern);
        validateCollation(connection, collation);
    }

    static void validateReadConcernAndCollation(AsyncConnection connection, ReadConcern readConcern, final Collation collation, final AsyncCallableWithConnection callable) {
        validateReadConcern(connection, readConcern, new AsyncCallableWithConnection() { // from class: com.mongodb.operation.OperationHelper.4
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
            public void call(AsyncConnection connection2, Throwable t) {
                if (t != null) {
                    callable.call(connection2, t);
                } else {
                    OperationHelper.validateCollation(connection2, collation, callable);
                }
            }
        });
    }

    static void validateReadConcernAndCollation(final AsyncConnectionSource source, AsyncConnection connection, ReadConcern readConcern, Collation collation, final AsyncCallableWithConnectionAndSource callable) {
        validateReadConcernAndCollation(connection, readConcern, collation, new AsyncCallableWithConnection() { // from class: com.mongodb.operation.OperationHelper.5
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
            public void call(AsyncConnection connection2, Throwable t) {
                callable.call(source, connection2, t);
            }
        });
    }

    static void checkBypassDocumentValidationIsSupported(ConnectionDescription connectionDescription, Boolean bypassDocumentValidation, WriteConcern writeConcern) {
        if (bypassDocumentValidation != null && ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connectionDescription) && !writeConcern.isAcknowledged()) {
            throw new MongoClientException("Specifying bypassDocumentValidation with an unacknowledged WriteConcern is not supported");
        }
    }

    static boolean isRetryableWrite(boolean retryWrites, WriteConcern writeConcern, ServerDescription serverDescription, ConnectionDescription connectionDescription, SessionContext sessionContext) {
        if (!retryWrites) {
            return false;
        }
        if (!writeConcern.isAcknowledged()) {
            LOGGER.debug("retryWrites set to true but the writeConcern is unacknowledged.");
            return false;
        }
        if (sessionContext.hasActiveTransaction()) {
            LOGGER.debug("retryWrites set to true but in an active transaction.");
            return false;
        }
        return canRetryWrite(serverDescription, connectionDescription, sessionContext);
    }

    static boolean canRetryWrite(ServerDescription serverDescription, ConnectionDescription connectionDescription, SessionContext sessionContext) {
        if (connectionDescription.getServerVersion().compareTo(new ServerVersion(3, 6)) < 0) {
            LOGGER.debug("retryWrites set to true but the server does not support retryable writes.");
            return false;
        }
        if (serverDescription.getLogicalSessionTimeoutMinutes() == null) {
            LOGGER.debug("retryWrites set to true but the server does not have 3.6 feature compatibility enabled.");
            return false;
        }
        if (connectionDescription.getServerType().equals(ServerType.STANDALONE)) {
            LOGGER.debug("retryWrites set to true but the server is a standalone server.");
            return false;
        }
        if (!sessionContext.hasSession()) {
            LOGGER.debug("retryWrites set to true but there is no implicit session, likely because the MongoClient was created with multiple MongoCredential instances and sessions can only be used with a single MongoCredential");
            return false;
        }
        return true;
    }

    static <T> QueryBatchCursor<T> createEmptyBatchCursor(MongoNamespace namespace, Decoder<T> decoder, ServerAddress serverAddress, int batchSize) {
        return new QueryBatchCursor<>(new QueryResult(namespace, Collections.emptyList(), 0L, serverAddress), 0, batchSize, decoder);
    }

    static <T> AsyncBatchCursor<T> createEmptyAsyncBatchCursor(MongoNamespace namespace, ServerAddress serverAddress) {
        return new AsyncSingleBatchQueryCursor(new QueryResult(namespace, Collections.emptyList(), 0L, serverAddress));
    }

    static <T> BatchCursor<T> cursorDocumentToBatchCursor(BsonDocument cursorDocument, Decoder<T> decoder, ConnectionSource source, int batchSize) {
        return new QueryBatchCursor(cursorDocumentToQueryResult(cursorDocument, source.getServerDescription().getAddress()), 0, batchSize, decoder, source);
    }

    static <T> AsyncBatchCursor<T> cursorDocumentToAsyncBatchCursor(BsonDocument cursorDocument, Decoder<T> decoder, AsyncConnectionSource source, AsyncConnection connection, int batchSize) {
        return new AsyncQueryBatchCursor(cursorDocumentToQueryResult(cursorDocument, source.getServerDescription().getAddress()), 0, batchSize, 0L, decoder, source, connection);
    }

    static <T> QueryResult<T> cursorDocumentToQueryResult(BsonDocument cursorDocument, ServerAddress serverAddress) {
        return cursorDocumentToQueryResult(cursorDocument, serverAddress, "firstBatch");
    }

    static <T> QueryResult<T> getMoreCursorDocumentToQueryResult(BsonDocument cursorDocument, ServerAddress serverAddress) {
        return cursorDocumentToQueryResult(cursorDocument, serverAddress, "nextBatch");
    }

    private static <T> QueryResult<T> cursorDocumentToQueryResult(BsonDocument cursorDocument, ServerAddress serverAddress, String fieldNameContainingBatch) {
        long cursorId = ((BsonInt64) cursorDocument.get("id")).getValue();
        MongoNamespace queryResultNamespace = new MongoNamespace(cursorDocument.getString("ns").getValue());
        return new QueryResult<>(queryResultNamespace, BsonDocumentWrapperHelper.toList(cursorDocument, fieldNameContainingBatch), cursorId, serverAddress);
    }

    static <T> SingleResultCallback<T> releasingCallback(SingleResultCallback<T> wrapped, AsyncConnectionSource source) {
        return new ReferenceCountedReleasingWrappedCallback(wrapped, Collections.singletonList(source));
    }

    static <T> SingleResultCallback<T> releasingCallback(SingleResultCallback<T> wrapped, AsyncConnection connection) {
        return new ReferenceCountedReleasingWrappedCallback(wrapped, Collections.singletonList(connection));
    }

    static <T> SingleResultCallback<T> releasingCallback(SingleResultCallback<T> wrapped, AsyncConnectionSource source, AsyncConnection connection) {
        return new ReferenceCountedReleasingWrappedCallback(wrapped, Arrays.asList(connection, source));
    }

    static <T> SingleResultCallback<T> releasingCallback(SingleResultCallback<T> wrapped, AsyncReadBinding readBinding, AsyncConnectionSource source, AsyncConnection connection) {
        return new ReferenceCountedReleasingWrappedCallback(wrapped, Arrays.asList(readBinding, connection, source));
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/OperationHelper$ReferenceCountedReleasingWrappedCallback.class */
    private static class ReferenceCountedReleasingWrappedCallback<T> implements SingleResultCallback<T> {
        private final SingleResultCallback<T> wrapped;
        private final List<? extends ReferenceCounted> referenceCounted;

        ReferenceCountedReleasingWrappedCallback(SingleResultCallback<T> wrapped, List<? extends ReferenceCounted> referenceCounted) {
            this.wrapped = wrapped;
            this.referenceCounted = (List) Assertions.notNull("referenceCounted", referenceCounted);
        }

        @Override // com.mongodb.async.SingleResultCallback
        public void onResult(T result, Throwable t) {
            for (ReferenceCounted cur : this.referenceCounted) {
                cur.release();
            }
            this.wrapped.onResult(result, t);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/OperationHelper$ConnectionReleasingWrappedCallback.class */
    static class ConnectionReleasingWrappedCallback<T> implements SingleResultCallback<T> {
        private final SingleResultCallback<T> wrapped;
        private final AsyncConnectionSource source;
        private final AsyncConnection connection;

        ConnectionReleasingWrappedCallback(SingleResultCallback<T> wrapped, AsyncConnectionSource source, AsyncConnection connection) {
            this.wrapped = wrapped;
            this.source = (AsyncConnectionSource) Assertions.notNull(PackageDocumentBase.DCTags.source, source);
            this.connection = (AsyncConnection) Assertions.notNull("connection", connection);
        }

        @Override // com.mongodb.async.SingleResultCallback
        public void onResult(T result, Throwable t) {
            this.connection.release();
            this.source.release();
            this.wrapped.onResult(result, t);
        }

        public SingleResultCallback<T> releaseConnectionAndGetWrapped() {
            this.connection.release();
            this.source.release();
            return this.wrapped;
        }
    }

    static <T> T withConnection(ReadBinding readBinding, CallableWithConnection<T> callableWithConnection) {
        ConnectionSource readConnectionSource = readBinding.getReadConnectionSource();
        try {
            T t = (T) withConnectionSource(readConnectionSource, callableWithConnection);
            readConnectionSource.release();
            return t;
        } catch (Throwable th) {
            readConnectionSource.release();
            throw th;
        }
    }

    static <T> T withConnection(ReadBinding readBinding, CallableWithConnectionAndSource<T> callableWithConnectionAndSource) {
        ConnectionSource readConnectionSource = readBinding.getReadConnectionSource();
        try {
            T t = (T) withConnectionSource(readConnectionSource, callableWithConnectionAndSource);
            readConnectionSource.release();
            return t;
        } catch (Throwable th) {
            readConnectionSource.release();
            throw th;
        }
    }

    static <T> T withConnection(WriteBinding writeBinding, CallableWithConnection<T> callableWithConnection) {
        ConnectionSource writeConnectionSource = writeBinding.getWriteConnectionSource();
        try {
            T t = (T) withConnectionSource(writeConnectionSource, callableWithConnection);
            writeConnectionSource.release();
            return t;
        } catch (Throwable th) {
            writeConnectionSource.release();
            throw th;
        }
    }

    static <T> T withReleasableConnection(WriteBinding binding, CallableWithConnectionAndSource<T> callable) {
        ConnectionSource source = binding.getWriteConnectionSource();
        try {
            T tCall = callable.call(source, source.getConnection());
            source.release();
            return tCall;
        } catch (Throwable th) {
            source.release();
            throw th;
        }
    }

    static <T> T withReleasableConnection(WriteBinding binding, MongoException connectionException, CallableWithConnectionAndSource<T> callable) {
        ConnectionSource source = null;
        try {
            source = binding.getWriteConnectionSource();
            Connection connection = source.getConnection();
            try {
                T tCall = callable.call(source, connection);
                source.release();
                return tCall;
            } catch (Throwable th) {
                source.release();
                throw th;
            }
        } catch (Throwable th2) {
            if (source != null) {
                source.release();
            }
            throw connectionException;
        }
    }

    static <T> T withConnectionSource(ConnectionSource source, CallableWithConnection<T> callable) {
        Connection connection = source.getConnection();
        try {
            T tCall = callable.call(connection);
            connection.release();
            return tCall;
        } catch (Throwable th) {
            connection.release();
            throw th;
        }
    }

    static <T> T withConnectionSource(ConnectionSource source, CallableWithConnectionAndSource<T> callable) {
        Connection connection = source.getConnection();
        try {
            T tCall = callable.call(source, connection);
            connection.release();
            return tCall;
        } catch (Throwable th) {
            connection.release();
            throw th;
        }
    }

    static void withConnection(AsyncWriteBinding binding, AsyncCallableWithConnection callable) {
        binding.getWriteConnectionSource(ErrorHandlingResultCallback.errorHandlingCallback(new AsyncCallableWithConnectionCallback(callable), LOGGER));
    }

    static void withConnection(AsyncWriteBinding binding, AsyncCallableWithConnectionAndSource callable) {
        binding.getWriteConnectionSource(ErrorHandlingResultCallback.errorHandlingCallback(new AsyncCallableWithConnectionAndSourceCallback(callable), LOGGER));
    }

    static void withConnection(AsyncReadBinding binding, AsyncCallableWithConnection callable) {
        binding.getReadConnectionSource(ErrorHandlingResultCallback.errorHandlingCallback(new AsyncCallableWithConnectionCallback(callable), LOGGER));
    }

    static void withConnection(AsyncReadBinding binding, AsyncCallableWithConnectionAndSource callable) {
        binding.getReadConnectionSource(ErrorHandlingResultCallback.errorHandlingCallback(new AsyncCallableWithConnectionAndSourceCallback(callable), LOGGER));
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/OperationHelper$AsyncCallableWithConnectionCallback.class */
    private static class AsyncCallableWithConnectionCallback implements SingleResultCallback<AsyncConnectionSource> {
        private final AsyncCallableWithConnection callable;

        AsyncCallableWithConnectionCallback(AsyncCallableWithConnection callable) {
            this.callable = callable;
        }

        @Override // com.mongodb.async.SingleResultCallback
        public void onResult(AsyncConnectionSource source, Throwable t) {
            if (t == null) {
                OperationHelper.withConnectionSource(source, this.callable);
            } else {
                this.callable.call(null, t);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void withConnectionSource(final AsyncConnectionSource source, final AsyncCallableWithConnection callable) {
        source.getConnection(new SingleResultCallback<AsyncConnection>() { // from class: com.mongodb.operation.OperationHelper.6
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(AsyncConnection connection, Throwable t) {
                source.release();
                if (t != null) {
                    callable.call(null, t);
                } else {
                    callable.call(connection, null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void withConnectionSource(final AsyncConnectionSource source, final AsyncCallableWithConnectionAndSource callable) {
        source.getConnection(new SingleResultCallback<AsyncConnection>() { // from class: com.mongodb.operation.OperationHelper.7
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(AsyncConnection result, Throwable t) {
                callable.call(source, result, t);
            }
        });
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/OperationHelper$AsyncCallableWithConnectionAndSourceCallback.class */
    private static class AsyncCallableWithConnectionAndSourceCallback implements SingleResultCallback<AsyncConnectionSource> {
        private final AsyncCallableWithConnectionAndSource callable;

        AsyncCallableWithConnectionAndSourceCallback(AsyncCallableWithConnectionAndSource callable) {
            this.callable = callable;
        }

        @Override // com.mongodb.async.SingleResultCallback
        public void onResult(AsyncConnectionSource source, Throwable t) {
            if (t == null) {
                OperationHelper.withConnectionSource(source, this.callable);
            } else {
                this.callable.call(null, null, t);
            }
        }
    }

    private OperationHelper() {
    }
}
