package com.mongodb.operation;

import com.mongodb.MongoCommandException;
import com.mongodb.MongoException;
import com.mongodb.MongoNodeIsRecoveringException;
import com.mongodb.MongoNotPrimaryException;
import com.mongodb.MongoSocketException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncConnectionSource;
import com.mongodb.binding.AsyncReadBinding;
import com.mongodb.binding.AsyncWriteBinding;
import com.mongodb.binding.ConnectionSource;
import com.mongodb.binding.ReadBinding;
import com.mongodb.binding.WriteBinding;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.Connection;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.internal.operation.WriteConcernHelper;
import com.mongodb.internal.validator.NoOpFieldNameValidator;
import com.mongodb.lang.Nullable;
import com.mongodb.operation.OperationHelper;
import com.mongodb.session.SessionContext;
import java.util.Arrays;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.FieldNameValidator;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/CommandOperationHelper.class */
final class CommandOperationHelper {
    private static final List<Integer> RETRYABLE_ERROR_CODES = Arrays.asList(6, 7, 89, 91, 189, 9001, 13436, 13435, 11602, 11600, 10107);

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/CommandOperationHelper$CommandCreator.class */
    interface CommandCreator {
        BsonDocument create(ServerDescription serverDescription, ConnectionDescription connectionDescription);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/CommandOperationHelper$CommandTransformer.class */
    interface CommandTransformer<T, R> {
        R apply(T t, ServerAddress serverAddress);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/CommandOperationHelper$IdentityTransformer.class */
    static class IdentityTransformer<T> implements CommandTransformer<T, T> {
        IdentityTransformer() {
        }

        @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
        public T apply(T t, ServerAddress serverAddress) {
            return t;
        }
    }

    static CommandTransformer<BsonDocument, Void> writeConcernErrorTransformer() {
        return new CommandTransformer<BsonDocument, Void>() { // from class: com.mongodb.operation.CommandOperationHelper.1
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public Void apply(BsonDocument result, ServerAddress serverAddress) {
                WriteConcernHelper.throwOnWriteConcernError(result, serverAddress);
                return null;
            }
        };
    }

    static BsonDocument executeWrappedCommandProtocol(ReadBinding binding, String database, BsonDocument command) {
        return (BsonDocument) executeWrappedCommandProtocol(binding, database, command, new BsonDocumentCodec());
    }

    static <T> T executeWrappedCommandProtocol(ReadBinding readBinding, String str, BsonDocument bsonDocument, CommandTransformer<BsonDocument, T> commandTransformer) {
        return (T) executeWrappedCommandProtocol(readBinding, str, bsonDocument, new BsonDocumentCodec(), commandTransformer);
    }

    static <T> T executeWrappedCommandProtocol(ReadBinding readBinding, String str, BsonDocument bsonDocument, Decoder<T> decoder) {
        return (T) executeWrappedCommandProtocol(readBinding, str, bsonDocument, decoder, new IdentityTransformer());
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <D, T> T executeWrappedCommandProtocol(ReadBinding readBinding, String str, BsonDocument bsonDocument, Decoder<D> decoder, CommandTransformer<D, T> commandTransformer) {
        ConnectionSource readConnectionSource = readBinding.getReadConnectionSource();
        try {
            T t = (T) commandTransformer.apply(executeWrappedCommandProtocol(str, bsonDocument, decoder, readConnectionSource, readBinding.getReadPreference()), readConnectionSource.getServerDescription().getAddress());
            readConnectionSource.release();
            return t;
        } catch (Throwable th) {
            readConnectionSource.release();
            throw th;
        }
    }

    static BsonDocument executeWrappedCommandProtocol(ReadBinding binding, String database, BsonDocument command, Connection connection) {
        return (BsonDocument) executeWrappedCommandProtocol(binding, database, command, connection, new IdentityTransformer());
    }

    static <T> T executeWrappedCommandProtocol(ReadBinding readBinding, String str, BsonDocument bsonDocument, Connection connection, CommandTransformer<BsonDocument, T> commandTransformer) {
        return (T) executeWrappedCommandProtocol(readBinding, str, bsonDocument, new BsonDocumentCodec(), connection, commandTransformer);
    }

    static <T> T executeWrappedCommandProtocol(ReadBinding readBinding, String str, BsonDocument bsonDocument, Decoder<BsonDocument> decoder, Connection connection, CommandTransformer<BsonDocument, T> commandTransformer) {
        return (T) executeWrappedCommandProtocol(str, bsonDocument, decoder, connection, readBinding.getReadPreference(), commandTransformer, readBinding.getSessionContext());
    }

    static BsonDocument executeWrappedCommandProtocol(WriteBinding binding, String database, BsonDocument command) {
        return (BsonDocument) executeWrappedCommandProtocol(binding, database, command, new IdentityTransformer());
    }

    static <T> T executeWrappedCommandProtocol(WriteBinding writeBinding, String str, BsonDocument bsonDocument, Decoder<T> decoder) {
        return (T) executeWrappedCommandProtocol(writeBinding, str, bsonDocument, decoder, new IdentityTransformer());
    }

    static <T> T executeWrappedCommandProtocol(WriteBinding writeBinding, String str, BsonDocument bsonDocument, CommandTransformer<BsonDocument, T> commandTransformer) {
        return (T) executeWrappedCommandProtocol(writeBinding, str, bsonDocument, new BsonDocumentCodec(), commandTransformer);
    }

    static <D, T> T executeWrappedCommandProtocol(WriteBinding writeBinding, String str, BsonDocument bsonDocument, Decoder<D> decoder, CommandTransformer<D, T> commandTransformer) {
        return (T) executeWrappedCommandProtocol(writeBinding, str, bsonDocument, new NoOpFieldNameValidator(), decoder, commandTransformer);
    }

    static <T> T executeWrappedCommandProtocol(WriteBinding writeBinding, String str, BsonDocument bsonDocument, Connection connection, CommandTransformer<BsonDocument, T> commandTransformer) {
        return (T) executeWrappedCommandProtocol(writeBinding, str, bsonDocument, new BsonDocumentCodec(), connection, commandTransformer);
    }

    static <T> T executeWrappedCommandProtocol(WriteBinding writeBinding, String str, BsonDocument bsonDocument, Decoder<BsonDocument> decoder, Connection connection, CommandTransformer<BsonDocument, T> commandTransformer) {
        Assertions.notNull("binding", writeBinding);
        return (T) executeWrappedCommandProtocol(str, bsonDocument, decoder, connection, ReadPreference.primary(), commandTransformer, writeBinding.getSessionContext());
    }

    static <T> T executeWrappedCommandProtocol(WriteBinding writeBinding, String str, BsonDocument bsonDocument, FieldNameValidator fieldNameValidator, Decoder<BsonDocument> decoder, Connection connection, CommandTransformer<BsonDocument, T> commandTransformer) {
        Assertions.notNull("binding", writeBinding);
        return (T) executeWrappedCommandProtocol(str, bsonDocument, fieldNameValidator, decoder, connection, ReadPreference.primary(), commandTransformer, writeBinding.getSessionContext());
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <D, T> T executeWrappedCommandProtocol(WriteBinding writeBinding, String str, BsonDocument bsonDocument, FieldNameValidator fieldNameValidator, Decoder<D> decoder, CommandTransformer<D, T> commandTransformer) {
        ConnectionSource writeConnectionSource = writeBinding.getWriteConnectionSource();
        try {
            T t = (T) commandTransformer.apply(executeWrappedCommandProtocol(str, bsonDocument, fieldNameValidator, decoder, writeConnectionSource, ReadPreference.primary()), writeConnectionSource.getServerDescription().getAddress());
            writeConnectionSource.release();
            return t;
        } catch (Throwable th) {
            writeConnectionSource.release();
            throw th;
        }
    }

    static BsonDocument executeWrappedCommandProtocol(WriteBinding binding, String database, BsonDocument command, Connection connection) {
        Assertions.notNull("binding", binding);
        return (BsonDocument) executeWrappedCommandProtocol(database, command, new BsonDocumentCodec(), connection, ReadPreference.primary(), binding.getSessionContext());
    }

    private static <T> T executeWrappedCommandProtocol(String str, BsonDocument bsonDocument, Decoder<T> decoder, ConnectionSource connectionSource, ReadPreference readPreference) {
        return (T) executeWrappedCommandProtocol(str, bsonDocument, new NoOpFieldNameValidator(), decoder, connectionSource, readPreference);
    }

    private static <T> T executeWrappedCommandProtocol(String str, BsonDocument bsonDocument, FieldNameValidator fieldNameValidator, Decoder<T> decoder, ConnectionSource connectionSource, ReadPreference readPreference) {
        Connection connection = connectionSource.getConnection();
        try {
            T t = (T) executeWrappedCommandProtocol(str, bsonDocument, fieldNameValidator, decoder, connection, readPreference, new IdentityTransformer(), connectionSource.getSessionContext());
            connection.release();
            return t;
        } catch (Throwable th) {
            connection.release();
            throw th;
        }
    }

    private static <T> T executeWrappedCommandProtocol(String str, BsonDocument bsonDocument, Decoder<T> decoder, Connection connection, ReadPreference readPreference, SessionContext sessionContext) {
        return (T) executeWrappedCommandProtocol(str, bsonDocument, new NoOpFieldNameValidator(), decoder, connection, readPreference, new IdentityTransformer(), sessionContext);
    }

    private static <D, T> T executeWrappedCommandProtocol(String str, BsonDocument bsonDocument, Decoder<D> decoder, Connection connection, ReadPreference readPreference, CommandTransformer<D, T> commandTransformer, SessionContext sessionContext) {
        return (T) executeWrappedCommandProtocol(str, bsonDocument, new NoOpFieldNameValidator(), decoder, connection, readPreference, commandTransformer, sessionContext);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <D, T> T executeWrappedCommandProtocol(String str, BsonDocument bsonDocument, FieldNameValidator fieldNameValidator, Decoder<D> decoder, Connection connection, ReadPreference readPreference, CommandTransformer<D, T> commandTransformer, SessionContext sessionContext) {
        return (T) commandTransformer.apply(connection.command(str, bsonDocument, fieldNameValidator, readPreference, decoder, sessionContext), connection.getDescription().getServerAddress());
    }

    static void executeWrappedCommandProtocolAsync(AsyncReadBinding binding, String database, BsonDocument command, SingleResultCallback<BsonDocument> callback) {
        executeWrappedCommandProtocolAsync(binding, database, command, new BsonDocumentCodec(), callback);
    }

    static <T> void executeWrappedCommandProtocolAsync(AsyncReadBinding binding, String database, BsonDocument command, Decoder<T> decoder, SingleResultCallback<T> callback) {
        executeWrappedCommandProtocolAsync(binding, database, command, decoder, new IdentityTransformer(), callback);
    }

    static <T> void executeWrappedCommandProtocolAsync(AsyncReadBinding binding, String database, BsonDocument command, CommandTransformer<BsonDocument, T> transformer, SingleResultCallback<T> callback) {
        executeWrappedCommandProtocolAsync(binding, database, command, new BsonDocumentCodec(), transformer, callback);
    }

    static <D, T> void executeWrappedCommandProtocolAsync(AsyncReadBinding binding, String database, BsonDocument command, Decoder<D> decoder, CommandTransformer<D, T> transformer, SingleResultCallback<T> callback) {
        binding.getReadConnectionSource(new CommandProtocolExecutingCallback(database, command, new NoOpFieldNameValidator(), decoder, binding.getReadPreference(), transformer, binding.getSessionContext(), ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER)));
    }

    static <T> void executeWrappedCommandProtocolAsync(AsyncReadBinding binding, String database, BsonDocument command, AsyncConnection connection, CommandTransformer<BsonDocument, T> transformer, SingleResultCallback<T> callback) {
        executeWrappedCommandProtocolAsync(binding, database, command, new BsonDocumentCodec(), connection, transformer, callback);
    }

    static <T> void executeWrappedCommandProtocolAsync(AsyncReadBinding binding, String database, BsonDocument command, Decoder<BsonDocument> decoder, AsyncConnection connection, CommandTransformer<BsonDocument, T> transformer, SingleResultCallback<T> callback) {
        Assertions.notNull("binding", binding);
        executeWrappedCommandProtocolAsync(database, command, decoder, connection, binding.getReadPreference(), transformer, binding.getSessionContext(), callback);
    }

    static void executeWrappedCommandProtocolAsync(AsyncWriteBinding binding, String database, BsonDocument command, SingleResultCallback<BsonDocument> callback) {
        executeWrappedCommandProtocolAsync(binding, database, command, new BsonDocumentCodec(), callback);
    }

    static <T> void executeWrappedCommandProtocolAsync(AsyncWriteBinding binding, String database, BsonDocument command, Decoder<T> decoder, SingleResultCallback<T> callback) {
        executeWrappedCommandProtocolAsync(binding, database, command, decoder, new IdentityTransformer(), callback);
    }

    static <T> void executeWrappedCommandProtocolAsync(AsyncWriteBinding binding, String database, BsonDocument command, CommandTransformer<BsonDocument, T> transformer, SingleResultCallback<T> callback) {
        executeWrappedCommandProtocolAsync(binding, database, command, new BsonDocumentCodec(), transformer, callback);
    }

    static <D, T> void executeWrappedCommandProtocolAsync(AsyncWriteBinding binding, String database, BsonDocument command, Decoder<D> decoder, CommandTransformer<D, T> transformer, SingleResultCallback<T> callback) {
        executeWrappedCommandProtocolAsync(binding, database, command, new NoOpFieldNameValidator(), decoder, transformer, callback);
    }

    static <T> void executeWrappedCommandProtocolAsync(AsyncWriteBinding binding, String database, BsonDocument command, Decoder<BsonDocument> decoder, AsyncConnection connection, CommandTransformer<BsonDocument, T> transformer, SingleResultCallback<T> callback) {
        Assertions.notNull("binding", binding);
        executeWrappedCommandProtocolAsync(database, command, decoder, connection, ReadPreference.primary(), transformer, binding.getSessionContext(), callback);
    }

    static <T> void executeWrappedCommandProtocolAsync(AsyncWriteBinding binding, String database, BsonDocument command, FieldNameValidator fieldNameValidator, Decoder<BsonDocument> decoder, AsyncConnection connection, CommandTransformer<BsonDocument, T> transformer, SingleResultCallback<T> callback) {
        Assertions.notNull("binding", binding);
        executeWrappedCommandProtocolAsync(database, command, fieldNameValidator, decoder, connection, ReadPreference.primary(), transformer, binding.getSessionContext(), callback);
    }

    static <D, T> void executeWrappedCommandProtocolAsync(AsyncWriteBinding binding, String database, BsonDocument command, FieldNameValidator fieldNameValidator, Decoder<D> decoder, CommandTransformer<D, T> transformer, SingleResultCallback<T> callback) {
        binding.getWriteConnectionSource(new CommandProtocolExecutingCallback(database, command, fieldNameValidator, decoder, ReadPreference.primary(), transformer, binding.getSessionContext(), ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER)));
    }

    static void executeWrappedCommandProtocolAsync(AsyncWriteBinding binding, String database, BsonDocument command, AsyncConnection connection, SingleResultCallback<BsonDocument> callback) {
        executeWrappedCommandProtocolAsync(binding, database, command, connection, new IdentityTransformer(), callback);
    }

    static <T> void executeWrappedCommandProtocolAsync(AsyncWriteBinding binding, String database, BsonDocument command, AsyncConnection connection, CommandTransformer<BsonDocument, T> transformer, SingleResultCallback<T> callback) {
        Assertions.notNull("binding", binding);
        executeWrappedCommandProtocolAsync(database, command, new BsonDocumentCodec(), connection, ReadPreference.primary(), transformer, binding.getSessionContext(), callback);
    }

    private static <D, T> void executeWrappedCommandProtocolAsync(String database, BsonDocument command, Decoder<D> decoder, final AsyncConnection connection, ReadPreference readPreference, final CommandTransformer<D, T> transformer, SessionContext sessionContext, final SingleResultCallback<T> callback) {
        connection.commandAsync(database, command, new NoOpFieldNameValidator(), readPreference, decoder, sessionContext, new SingleResultCallback<D>() { // from class: com.mongodb.operation.CommandOperationHelper.2
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(D result, Throwable t) {
                if (t != null) {
                    callback.onResult(null, t);
                    return;
                }
                try {
                    callback.onResult(transformer.apply(result, connection.getDescription().getServerAddress()), null);
                } catch (Exception e) {
                    callback.onResult(null, e);
                }
            }
        });
    }

    private static <D, T> void executeWrappedCommandProtocolAsync(String database, BsonDocument command, FieldNameValidator fieldNameValidator, Decoder<D> decoder, final AsyncConnection connection, ReadPreference readPreference, final CommandTransformer<D, T> transformer, SessionContext sessionContext, final SingleResultCallback<T> callback) {
        connection.commandAsync(database, command, fieldNameValidator, readPreference, decoder, sessionContext, true, null, null, new SingleResultCallback<D>() { // from class: com.mongodb.operation.CommandOperationHelper.3
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(D result, Throwable t) {
                if (t != null) {
                    callback.onResult(null, t);
                    return;
                }
                try {
                    callback.onResult(transformer.apply(result, connection.getDescription().getServerAddress()), null);
                } catch (Exception e) {
                    callback.onResult(null, e);
                }
            }
        });
    }

    static <T, R> R executeRetryableCommand(final WriteBinding writeBinding, final String str, final ReadPreference readPreference, final FieldNameValidator fieldNameValidator, final Decoder<T> decoder, final CommandCreator commandCreator, final CommandTransformer<T, R> commandTransformer) {
        return (R) OperationHelper.withReleasableConnection(writeBinding, new OperationHelper.CallableWithConnectionAndSource<R>() { // from class: com.mongodb.operation.CommandOperationHelper.4
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnectionAndSource
            public R call(ConnectionSource connectionSource, Connection connection) {
                BsonDocument bsonDocumentCreate = null;
                try {
                    try {
                        bsonDocumentCreate = commandCreator.create(connectionSource.getServerDescription(), connection.getDescription());
                        R r = (R) commandTransformer.apply(connection.command(str, bsonDocumentCreate, fieldNameValidator, readPreference, decoder, writeBinding.getSessionContext()), connection.getDescription().getServerAddress());
                        connection.release();
                        return r;
                    } catch (MongoException e) {
                        if (!CommandOperationHelper.shouldAttemptToRetry(bsonDocumentCreate, e)) {
                            throw e;
                        }
                        connection.release();
                        final BsonDocument bsonDocument = bsonDocumentCreate;
                        return (R) OperationHelper.withReleasableConnection(writeBinding, e, new OperationHelper.CallableWithConnectionAndSource<R>() { // from class: com.mongodb.operation.CommandOperationHelper.4.1
                            @Override // com.mongodb.operation.OperationHelper.CallableWithConnectionAndSource
                            public R call(ConnectionSource connectionSource2, Connection connection2) {
                                try {
                                    try {
                                        if (!OperationHelper.canRetryWrite(connectionSource2.getServerDescription(), connection2.getDescription(), writeBinding.getSessionContext())) {
                                            throw e;
                                        }
                                        R r2 = (R) commandTransformer.apply(connection2.command(str, bsonDocument, fieldNameValidator, readPreference, decoder, writeBinding.getSessionContext()), connection2.getDescription().getServerAddress());
                                        connection2.release();
                                        return r2;
                                    } catch (MongoException e2) {
                                        throw e;
                                    }
                                } catch (Throwable th) {
                                    connection2.release();
                                    throw th;
                                }
                            }
                        });
                    }
                } catch (Throwable th) {
                    connection.release();
                    throw th;
                }
            }
        });
    }

    static <T, R> void executeRetryableCommand(final AsyncWriteBinding binding, final String database, final ReadPreference readPreference, final FieldNameValidator fieldNameValidator, final Decoder<T> commandResultDecoder, final CommandCreator commandCreator, final CommandTransformer<T, R> transformer, SingleResultCallback<R> originalCallback) {
        final SingleResultCallback<R> errorHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(originalCallback, OperationHelper.LOGGER);
        binding.getWriteConnectionSource(new SingleResultCallback<AsyncConnectionSource>() { // from class: com.mongodb.operation.CommandOperationHelper.5
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(final AsyncConnectionSource source, Throwable t) {
                if (t != null) {
                    errorHandlingCallback.onResult(null, t);
                } else {
                    source.getConnection(new SingleResultCallback<AsyncConnection>() { // from class: com.mongodb.operation.CommandOperationHelper.5.1
                        @Override // com.mongodb.async.SingleResultCallback
                        public void onResult(AsyncConnection connection, Throwable t2) {
                            if (t2 != null) {
                                OperationHelper.releasingCallback(errorHandlingCallback, source).onResult(null, t2);
                                return;
                            }
                            try {
                                BsonDocument command = commandCreator.create(source.getServerDescription(), connection.getDescription());
                                connection.commandAsync(database, command, fieldNameValidator, readPreference, commandResultDecoder, binding.getSessionContext(), CommandOperationHelper.createCommandCallback(binding, source, connection, database, readPreference, command, fieldNameValidator, commandResultDecoder, transformer, errorHandlingCallback));
                            } catch (Throwable t1) {
                                OperationHelper.releasingCallback(errorHandlingCallback, source, connection).onResult(null, t1);
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T, R> SingleResultCallback<T> createCommandCallback(final AsyncWriteBinding binding, final AsyncConnectionSource oldSource, final AsyncConnection oldConnection, final String database, final ReadPreference readPreference, final BsonDocument command, final FieldNameValidator fieldNameValidator, final Decoder<T> commandResultDecoder, final CommandTransformer<T, R> transformer, final SingleResultCallback<R> callback) {
        return new SingleResultCallback<T>() { // from class: com.mongodb.operation.CommandOperationHelper.6
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(T result, Throwable originalError) {
                SingleResultCallback<R> singleResultCallbackReleasingCallback = OperationHelper.releasingCallback(callback, oldSource, oldConnection);
                if (originalError != null) {
                    checkRetryableException(originalError, singleResultCallbackReleasingCallback);
                    return;
                }
                try {
                    singleResultCallbackReleasingCallback.onResult(transformer.apply(result, oldConnection.getDescription().getServerAddress()), null);
                } catch (Throwable transformError) {
                    checkRetryableException(transformError, singleResultCallbackReleasingCallback);
                }
            }

            private void checkRetryableException(Throwable originalError, SingleResultCallback<R> singleResultCallback) {
                if (!CommandOperationHelper.shouldAttemptToRetry(command, originalError)) {
                    singleResultCallback.onResult(null, originalError);
                    return;
                }
                oldConnection.release();
                oldSource.release();
                retryableCommand(originalError);
            }

            private void retryableCommand(final Throwable originalError) {
                OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnectionAndSource() { // from class: com.mongodb.operation.CommandOperationHelper.6.1
                    @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
                    public void call(AsyncConnectionSource source, AsyncConnection connection, Throwable t) {
                        if (t != null) {
                            callback.onResult(null, originalError);
                        } else if (!OperationHelper.canRetryWrite(source.getServerDescription(), connection.getDescription(), binding.getSessionContext())) {
                            OperationHelper.releasingCallback(callback, source, connection).onResult(null, originalError);
                        } else {
                            connection.commandAsync(database, command, fieldNameValidator, readPreference, commandResultDecoder, binding.getSessionContext(), new TransformingResultCallback(transformer, connection.getDescription().getServerAddress(), originalError, OperationHelper.releasingCallback(callback, source, connection)));
                        }
                    }
                });
            }
        };
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/CommandOperationHelper$TransformingResultCallback.class */
    static class TransformingResultCallback<T, R> implements SingleResultCallback<T> {
        private final CommandTransformer<T, R> transformer;
        private final ServerAddress serverAddress;
        private final Throwable originalError;
        private final SingleResultCallback<R> callback;

        TransformingResultCallback(CommandTransformer<T, R> transformer, ServerAddress serverAddress, Throwable originalError, SingleResultCallback<R> callback) {
            this.transformer = transformer;
            this.serverAddress = serverAddress;
            this.originalError = originalError;
            this.callback = callback;
        }

        @Override // com.mongodb.async.SingleResultCallback
        public void onResult(T result, Throwable t) {
            if (t != null) {
                this.callback.onResult(null, t);
                return;
            }
            try {
                R transformedResult = this.transformer.apply(result, this.serverAddress);
                this.callback.onResult(transformedResult, null);
            } catch (Throwable th) {
                this.callback.onResult(null, this.originalError);
            }
        }
    }

    static boolean isRetryableException(Throwable t) {
        if (!(t instanceof MongoException)) {
            return false;
        }
        if ((t instanceof MongoSocketException) || (t instanceof MongoNotPrimaryException) || (t instanceof MongoNodeIsRecoveringException)) {
            return true;
        }
        String errorMessage = t.getMessage();
        if (t instanceof MongoWriteConcernException) {
            errorMessage = ((MongoWriteConcernException) t).getWriteConcernError().getMessage();
        }
        if (errorMessage.contains("not master") || errorMessage.contains("node is recovering")) {
            return true;
        }
        return RETRYABLE_ERROR_CODES.contains(Integer.valueOf(((MongoException) t).getCode()));
    }

    static void rethrowIfNotNamespaceError(MongoCommandException e) {
        rethrowIfNotNamespaceError(e, null);
    }

    static <T> T rethrowIfNotNamespaceError(MongoCommandException e, T defaultValue) {
        if (!isNamespaceError(e)) {
            throw e;
        }
        return defaultValue;
    }

    static boolean isNamespaceError(Throwable t) {
        if (t instanceof MongoCommandException) {
            MongoCommandException e = (MongoCommandException) t;
            return e.getErrorMessage().contains("ns not found") || e.getErrorCode() == 26;
        }
        return false;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/CommandOperationHelper$CommandProtocolExecutingCallback.class */
    private static class CommandProtocolExecutingCallback<D, R> implements SingleResultCallback<AsyncConnectionSource> {
        private final String database;
        private final BsonDocument command;
        private final Decoder<D> decoder;
        private final ReadPreference readPreference;
        private final FieldNameValidator fieldNameValidator;
        private final CommandTransformer<D, R> transformer;
        private final SingleResultCallback<R> callback;
        private final SessionContext sessionContext;

        CommandProtocolExecutingCallback(String database, BsonDocument command, FieldNameValidator fieldNameValidator, Decoder<D> decoder, ReadPreference readPreference, CommandTransformer<D, R> transformer, SessionContext sessionContext, SingleResultCallback<R> callback) {
            this.database = database;
            this.command = command;
            this.fieldNameValidator = fieldNameValidator;
            this.decoder = decoder;
            this.readPreference = readPreference;
            this.transformer = transformer;
            this.sessionContext = sessionContext;
            this.callback = callback;
        }

        @Override // com.mongodb.async.SingleResultCallback
        public void onResult(final AsyncConnectionSource source, Throwable t) {
            if (t != null) {
                this.callback.onResult(null, t);
            } else {
                source.getConnection(new SingleResultCallback<AsyncConnection>() { // from class: com.mongodb.operation.CommandOperationHelper.CommandProtocolExecutingCallback.1
                    @Override // com.mongodb.async.SingleResultCallback
                    public void onResult(final AsyncConnection connection, Throwable t2) {
                        if (t2 != null) {
                            CommandProtocolExecutingCallback.this.callback.onResult(null, t2);
                        } else {
                            final SingleResultCallback<R> wrappedCallback = OperationHelper.releasingCallback(CommandProtocolExecutingCallback.this.callback, source, connection);
                            connection.commandAsync(CommandProtocolExecutingCallback.this.database, CommandProtocolExecutingCallback.this.command, CommandProtocolExecutingCallback.this.fieldNameValidator, CommandProtocolExecutingCallback.this.readPreference, CommandProtocolExecutingCallback.this.decoder, CommandProtocolExecutingCallback.this.sessionContext, new SingleResultCallback<D>() { // from class: com.mongodb.operation.CommandOperationHelper.CommandProtocolExecutingCallback.1.1
                                @Override // com.mongodb.async.SingleResultCallback
                                public void onResult(D response, Throwable t3) {
                                    if (t3 != null) {
                                        wrappedCallback.onResult(null, t3);
                                    } else {
                                        wrappedCallback.onResult(CommandProtocolExecutingCallback.this.transformer.apply(response, connection.getDescription().getServerAddress()), null);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldAttemptToRetry(@Nullable BsonDocument command, Throwable exception) {
        return shouldAttemptToRetry(command != null && (command.containsKey("txnNumber") || command.getFirstKey().equals("commitTransaction") || command.getFirstKey().equals("abortTransaction")), exception);
    }

    static boolean shouldAttemptToRetry(boolean retryWritesEnabled, Throwable exception) {
        return retryWritesEnabled && isRetryableException(exception);
    }

    private CommandOperationHelper() {
    }
}
