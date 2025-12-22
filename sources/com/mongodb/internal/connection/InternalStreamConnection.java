package com.mongodb.internal.connection;

import com.mongodb.MongoClientException;
import com.mongodb.MongoCompressor;
import com.mongodb.MongoException;
import com.mongodb.MongoInternalException;
import com.mongodb.MongoInterruptedException;
import com.mongodb.MongoSocketClosedException;
import com.mongodb.MongoSocketReadException;
import com.mongodb.MongoSocketReadTimeoutException;
import com.mongodb.MongoSocketWriteException;
import com.mongodb.ServerAddress;
import com.mongodb.annotations.NotThreadSafe;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.AsyncCompletionHandler;
import com.mongodb.connection.ByteBufferBsonOutput;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.ConnectionId;
import com.mongodb.connection.ServerId;
import com.mongodb.connection.Stream;
import com.mongodb.connection.StreamFactory;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import com.mongodb.event.CommandListener;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.session.SessionContext;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.nio.channels.ClosedByInterruptException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.bson.BsonBinaryReader;
import org.bson.ByteBuf;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.Decoder;
import org.bson.io.ByteBufferBsonInput;

@NotThreadSafe
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/InternalStreamConnection.class */
public class InternalStreamConnection implements InternalConnection {
    private final ServerId serverId;
    private final StreamFactory streamFactory;
    private final InternalConnectionInitializer connectionInitializer;
    private volatile ConnectionDescription description;
    private volatile Stream stream;
    private final AtomicBoolean isClosed = new AtomicBoolean();
    private final AtomicBoolean opened = new AtomicBoolean();
    private final List<MongoCompressor> compressorList;
    private final CommandListener commandListener;
    private volatile Compressor sendCompressor;
    private volatile Map<Byte, Compressor> compressorMap;
    private static final Set<String> SECURITY_SENSITIVE_COMMANDS = new HashSet(Arrays.asList("authenticate", "saslStart", "saslContinue", "getnonce", "createUser", "updateUser", "copydbgetnonce", "copydbsaslstart", "copydb"));
    private static final Logger LOGGER = Loggers.getLogger("connection");
    private static final Logger COMMAND_PROTOCOL_LOGGER = Loggers.getLogger("protocol.command");

    public InternalStreamConnection(ServerId serverId, StreamFactory streamFactory, List<MongoCompressor> compressorList, CommandListener commandListener, InternalConnectionInitializer connectionInitializer) {
        this.serverId = (ServerId) Assertions.notNull("serverId", serverId);
        this.streamFactory = (StreamFactory) Assertions.notNull("streamFactory", streamFactory);
        this.compressorList = (List) Assertions.notNull("compressorList", compressorList);
        this.compressorMap = createCompressorMap(compressorList);
        this.commandListener = commandListener;
        this.connectionInitializer = (InternalConnectionInitializer) Assertions.notNull("connectionInitializer", connectionInitializer);
        this.description = new ConnectionDescription(serverId);
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public ConnectionDescription getDescription() {
        return this.description;
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public void open() {
        Assertions.isTrue("Open already called", this.stream == null);
        this.stream = this.streamFactory.create(this.serverId.getAddress());
        try {
            this.stream.open();
            this.description = this.connectionInitializer.initialize(this);
            this.opened.set(true);
            this.sendCompressor = findSendCompressor(this.description);
            LOGGER.info(String.format("Opened connection [%s] to %s", getId(), this.serverId.getAddress()));
        } catch (Throwable t) {
            close();
            if (t instanceof MongoException) {
                throw ((MongoException) t);
            }
            throw new MongoException(t.toString(), t);
        }
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public void openAsync(final SingleResultCallback<Void> callback) {
        Assertions.isTrue("Open already called", this.stream == null, callback);
        try {
            this.stream = this.streamFactory.create(this.serverId.getAddress());
            this.stream.openAsync(new AsyncCompletionHandler<Void>() { // from class: com.mongodb.internal.connection.InternalStreamConnection.1
                @Override // com.mongodb.connection.AsyncCompletionHandler
                public void completed(Void aVoid) {
                    InternalStreamConnection.this.connectionInitializer.initializeAsync(InternalStreamConnection.this, new SingleResultCallback<ConnectionDescription>() { // from class: com.mongodb.internal.connection.InternalStreamConnection.1.1
                        @Override // com.mongodb.async.SingleResultCallback
                        public void onResult(ConnectionDescription result, Throwable t) {
                            if (t == null) {
                                InternalStreamConnection.this.description = result;
                                InternalStreamConnection.this.opened.set(true);
                                InternalStreamConnection.this.sendCompressor = InternalStreamConnection.this.findSendCompressor(InternalStreamConnection.this.description);
                                if (InternalStreamConnection.LOGGER.isInfoEnabled()) {
                                    InternalStreamConnection.LOGGER.info(String.format("Opened connection [%s] to %s", InternalStreamConnection.this.getId(), InternalStreamConnection.this.serverId.getAddress()));
                                }
                                callback.onResult(null, null);
                                return;
                            }
                            InternalStreamConnection.this.close();
                            callback.onResult(null, t);
                        }
                    });
                }

                @Override // com.mongodb.connection.AsyncCompletionHandler
                public void failed(Throwable t) {
                    callback.onResult(null, t);
                }
            });
        } catch (Throwable t) {
            callback.onResult(null, t);
        }
    }

    private Map<Byte, Compressor> createCompressorMap(List<MongoCompressor> compressorList) {
        Map<Byte, Compressor> compressorMap = new HashMap<>(this.compressorList.size());
        for (MongoCompressor mongoCompressor : compressorList) {
            Compressor compressor = createCompressor(mongoCompressor);
            compressorMap.put(Byte.valueOf(compressor.getId()), compressor);
        }
        return compressorMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Compressor findSendCompressor(ConnectionDescription description) {
        if (description.getCompressors().isEmpty()) {
            return null;
        }
        String firstCompressorName = description.getCompressors().get(0);
        for (Compressor compressor : this.compressorMap.values()) {
            if (compressor.getName().equals(firstCompressorName)) {
                return compressor;
            }
        }
        throw new MongoInternalException("Unexpected compressor negotiated: " + firstCompressorName);
    }

    private Compressor createCompressor(MongoCompressor mongoCompressor) {
        if (mongoCompressor.getName().equals("zlib")) {
            return new ZlibCompressor(mongoCompressor);
        }
        if (mongoCompressor.getName().equals("snappy")) {
            return new SnappyCompressor();
        }
        throw new MongoClientException("Unsupported compressor " + mongoCompressor.getName());
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public void close() {
        if (!this.isClosed.getAndSet(true)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("Closing connection %s", getId()));
            }
            if (this.stream != null) {
                this.stream.close();
            }
        }
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public boolean opened() {
        return this.opened.get();
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public boolean isClosed() {
        return this.isClosed.get();
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public <T> T sendAndReceive(CommandMessage commandMessage, Decoder<T> decoder, SessionContext sessionContext) {
        ByteBufferBsonOutput byteBufferBsonOutput = new ByteBufferBsonOutput(this);
        try {
            commandMessage.encode(byteBufferBsonOutput, sessionContext);
            CommandEventSender commandEventSenderCreateCommandEventSender = createCommandEventSender(commandMessage, byteBufferBsonOutput);
            commandEventSenderCreateCommandEventSender.sendStartedEvent();
            try {
                sendCommandMessage(commandMessage, byteBufferBsonOutput, sessionContext);
                if (commandMessage.isResponseExpected()) {
                    return (T) receiveCommandMessageResponse(commandMessage, decoder, commandEventSenderCreateCommandEventSender, sessionContext);
                }
                commandEventSenderCreateCommandEventSender.sendSucceededEventForOneWayCommand();
                return null;
            } catch (RuntimeException e) {
                commandEventSenderCreateCommandEventSender.sendFailedEvent(e);
                throw e;
            }
        } catch (RuntimeException e2) {
            byteBufferBsonOutput.close();
            throw e2;
        }
    }

    private void sendCommandMessage(CommandMessage message, ByteBufferBsonOutput bsonOutput, SessionContext sessionContext) {
        try {
            if (this.sendCompressor == null || SECURITY_SENSITIVE_COMMANDS.contains(message.getCommandDocument(bsonOutput).getFirstKey())) {
                sendMessage(bsonOutput.getByteBuffers(), message.getId());
            } else {
                CompressedMessage compressedMessage = new CompressedMessage(message.getOpCode(), bsonOutput.getByteBuffers(), this.sendCompressor, ProtocolHelper.getMessageSettings(this.description));
                ByteBufferBsonOutput compressedBsonOutput = new ByteBufferBsonOutput(this);
                compressedMessage.encode(compressedBsonOutput, sessionContext);
                try {
                    sendMessage(compressedBsonOutput.getByteBuffers(), message.getId());
                    compressedBsonOutput.close();
                } catch (Throwable th) {
                    compressedBsonOutput.close();
                    throw th;
                }
            }
        } finally {
            bsonOutput.close();
        }
    }

    private <T> T receiveCommandMessageResponse(CommandMessage commandMessage, Decoder<T> decoder, CommandEventSender commandEventSender, SessionContext sessionContext) {
        ResponseBuffers responseBuffersReceiveMessage = receiveMessage(commandMessage.getId());
        try {
            updateSessionContext(sessionContext, responseBuffersReceiveMessage);
            if (!ProtocolHelper.isCommandOk(responseBuffersReceiveMessage)) {
                throw ProtocolHelper.getCommandFailureException(responseBuffersReceiveMessage.getResponseDocument(commandMessage.getId(), new BsonDocumentCodec()), this.description.getServerAddress());
            }
            commandEventSender.sendSucceededEvent(responseBuffersReceiveMessage);
            T t = (T) getCommandResult(decoder, responseBuffersReceiveMessage, commandMessage.getId());
            responseBuffersReceiveMessage.close();
            return t;
        } catch (Throwable th) {
            responseBuffersReceiveMessage.close();
            throw th;
        }
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public <T> void sendAndReceiveAsync(CommandMessage message, Decoder<T> decoder, SessionContext sessionContext, SingleResultCallback<T> callback) {
        Assertions.notNull("stream is open", this.stream, callback);
        if (isClosed()) {
            callback.onResult(null, new MongoSocketClosedException("Can not read from a closed socket", getServerAddress()));
            return;
        }
        ByteBufferBsonOutput bsonOutput = new ByteBufferBsonOutput(this);
        ByteBufferBsonOutput compressedBsonOutput = new ByteBufferBsonOutput(this);
        try {
            message.encode(bsonOutput, sessionContext);
            CommandEventSender commandEventSender = createCommandEventSender(message, bsonOutput);
            commandEventSender.sendStartedEvent();
            if (this.sendCompressor == null || SECURITY_SENSITIVE_COMMANDS.contains(message.getCommandDocument(bsonOutput).getFirstKey())) {
                sendCommandMessageAsync(message.getId(), decoder, sessionContext, callback, bsonOutput, commandEventSender, message.isResponseExpected());
            } else {
                CompressedMessage compressedMessage = new CompressedMessage(message.getOpCode(), bsonOutput.getByteBuffers(), this.sendCompressor, ProtocolHelper.getMessageSettings(this.description));
                compressedMessage.encode(compressedBsonOutput, sessionContext);
                bsonOutput.close();
                sendCommandMessageAsync(message.getId(), decoder, sessionContext, callback, compressedBsonOutput, commandEventSender, message.isResponseExpected());
            }
        } catch (Throwable t) {
            bsonOutput.close();
            compressedBsonOutput.close();
            callback.onResult(null, t);
        }
    }

    private <T> void sendCommandMessageAsync(final int messageId, final Decoder<T> decoder, final SessionContext sessionContext, final SingleResultCallback<T> callback, final ByteBufferBsonOutput bsonOutput, final CommandEventSender commandEventSender, final boolean responseExpected) {
        sendMessageAsync(bsonOutput.getByteBuffers(), messageId, new SingleResultCallback<Void>() { // from class: com.mongodb.internal.connection.InternalStreamConnection.2
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(Void result, Throwable t) {
                bsonOutput.close();
                if (t != null) {
                    commandEventSender.sendFailedEvent(t);
                    callback.onResult(null, t);
                } else if (responseExpected) {
                    InternalStreamConnection.this.readAsync(16, InternalStreamConnection.this.new MessageHeaderCallback(new SingleResultCallback<ResponseBuffers>() { // from class: com.mongodb.internal.connection.InternalStreamConnection.2.1
                        @Override // com.mongodb.async.SingleResultCallback
                        public void onResult(ResponseBuffers responseBuffers, Throwable t2) {
                            try {
                                if (t2 == null) {
                                    try {
                                        InternalStreamConnection.this.updateSessionContext(sessionContext, responseBuffers);
                                        boolean commandOk = ProtocolHelper.isCommandOk(new BsonBinaryReader(new ByteBufferBsonInput(responseBuffers.getBodyByteBuffer())));
                                        responseBuffers.reset();
                                        if (!commandOk) {
                                            MongoException commandFailureException = ProtocolHelper.getCommandFailureException(responseBuffers.getResponseDocument(messageId, new BsonDocumentCodec()), InternalStreamConnection.this.description.getServerAddress());
                                            commandEventSender.sendFailedEvent(commandFailureException);
                                            throw commandFailureException;
                                        }
                                        commandEventSender.sendSucceededEvent(responseBuffers);
                                        callback.onResult(InternalStreamConnection.this.getCommandResult(decoder, responseBuffers, messageId), null);
                                        responseBuffers.close();
                                        return;
                                    } catch (Throwable localThrowable) {
                                        callback.onResult(null, localThrowable);
                                        responseBuffers.close();
                                        return;
                                    }
                                }
                                commandEventSender.sendFailedEvent(t2);
                                callback.onResult(null, t2);
                            } catch (Throwable th) {
                                responseBuffers.close();
                                throw th;
                            }
                        }
                    }));
                } else {
                    commandEventSender.sendSucceededEventForOneWayCommand();
                    callback.onResult(null, null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T getCommandResult(Decoder<T> decoder, ResponseBuffers responseBuffers, int messageId) {
        T result = new ReplyMessage(responseBuffers, decoder, messageId).getDocuments().get(0);
        MongoException writeConcernBasedError = ProtocolHelper.createSpecialWriteConcernException(responseBuffers, this.description.getServerAddress());
        if (writeConcernBasedError != null) {
            throw new MongoWriteConcernWithResponseException(writeConcernBasedError, result);
        }
        return result;
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public void sendMessage(List<ByteBuf> byteBuffers, int lastRequestId) {
        Assertions.notNull("stream is open", this.stream);
        if (isClosed()) {
            throw new MongoSocketClosedException("Cannot write to a closed stream", getServerAddress());
        }
        try {
            this.stream.write(byteBuffers);
        } catch (Exception e) {
            close();
            throw translateWriteException(e);
        }
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public ResponseBuffers receiveMessage(int responseTo) {
        Assertions.notNull("stream is open", this.stream);
        if (isClosed()) {
            throw new MongoSocketClosedException("Cannot read from a closed stream", getServerAddress());
        }
        try {
            return receiveResponseBuffers();
        } catch (Throwable t) {
            close();
            throw translateReadException(t);
        }
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public void sendMessageAsync(List<ByteBuf> byteBuffers, int lastRequestId, SingleResultCallback<Void> callback) {
        Assertions.notNull("stream is open", this.stream, callback);
        if (isClosed()) {
            callback.onResult(null, new MongoSocketClosedException("Can not read from a closed socket", getServerAddress()));
        } else {
            writeAsync(byteBuffers, ErrorHandlingResultCallback.errorHandlingCallback(callback, LOGGER));
        }
    }

    private void writeAsync(List<ByteBuf> byteBuffers, final SingleResultCallback<Void> callback) {
        this.stream.writeAsync(byteBuffers, new AsyncCompletionHandler<Void>() { // from class: com.mongodb.internal.connection.InternalStreamConnection.3
            @Override // com.mongodb.connection.AsyncCompletionHandler
            public void completed(Void v) {
                callback.onResult(null, null);
            }

            @Override // com.mongodb.connection.AsyncCompletionHandler
            public void failed(Throwable t) {
                InternalStreamConnection.this.close();
                callback.onResult(null, InternalStreamConnection.this.translateWriteException(t));
            }
        });
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public void receiveMessageAsync(int responseTo, final SingleResultCallback<ResponseBuffers> callback) {
        Assertions.isTrue("stream is open", this.stream != null, callback);
        if (isClosed()) {
            callback.onResult(null, new MongoSocketClosedException("Can not read from a closed socket", getServerAddress()));
            return;
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(String.format("Start receiving response on %s", getId()));
        }
        readAsync(16, new MessageHeaderCallback(new SingleResultCallback<ResponseBuffers>() { // from class: com.mongodb.internal.connection.InternalStreamConnection.4
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(ResponseBuffers result, Throwable t) {
                if (t != null) {
                    InternalStreamConnection.this.close();
                    callback.onResult(null, t);
                } else {
                    callback.onResult(result, null);
                }
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readAsync(int numBytes, final SingleResultCallback<ByteBuf> callback) {
        if (isClosed()) {
            callback.onResult(null, new MongoSocketClosedException("Cannot read from a closed stream", getServerAddress()));
            return;
        }
        try {
            this.stream.readAsync(numBytes, new AsyncCompletionHandler<ByteBuf>() { // from class: com.mongodb.internal.connection.InternalStreamConnection.5
                @Override // com.mongodb.connection.AsyncCompletionHandler
                public void completed(ByteBuf buffer) {
                    callback.onResult(buffer, null);
                }

                @Override // com.mongodb.connection.AsyncCompletionHandler
                public void failed(Throwable t) {
                    InternalStreamConnection.this.close();
                    callback.onResult(null, InternalStreamConnection.this.translateReadException(t));
                }
            });
        } catch (Exception e) {
            callback.onResult(null, translateReadException(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ConnectionId getId() {
        return this.description.getConnectionId();
    }

    private ServerAddress getServerAddress() {
        return this.description.getServerAddress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSessionContext(SessionContext sessionContext, ResponseBuffers responseBuffers) {
        sessionContext.advanceOperationTime(ProtocolHelper.getOperationTime(responseBuffers));
        sessionContext.advanceClusterTime(ProtocolHelper.getClusterTime(responseBuffers));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MongoException translateWriteException(Throwable e) {
        if (e instanceof MongoException) {
            return (MongoException) e;
        }
        if (e instanceof IOException) {
            return new MongoSocketWriteException("Exception sending message", getServerAddress(), e);
        }
        if (e instanceof InterruptedException) {
            return new MongoInternalException("Thread interrupted exception", e);
        }
        return new MongoInternalException("Unexpected exception", e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MongoException translateReadException(Throwable e) {
        if (e instanceof MongoException) {
            return (MongoException) e;
        }
        if (e instanceof SocketTimeoutException) {
            return new MongoSocketReadTimeoutException("Timeout while receiving message", getServerAddress(), e);
        }
        if (e instanceof InterruptedIOException) {
            return new MongoInterruptedException("Interrupted while receiving message", (InterruptedIOException) e);
        }
        if (e instanceof ClosedByInterruptException) {
            return new MongoInterruptedException("Interrupted while receiving message", (ClosedByInterruptException) e);
        }
        if (e instanceof IOException) {
            return new MongoSocketReadException("Exception receiving message", getServerAddress(), e);
        }
        if (e instanceof RuntimeException) {
            return new MongoInternalException("Unexpected runtime exception", e);
        }
        if (e instanceof InterruptedException) {
            return new MongoInternalException("Interrupted exception", e);
        }
        return new MongoInternalException("Unexpected exception", e);
    }

    private ResponseBuffers receiveResponseBuffers() throws IOException {
        ByteBuf messageHeaderBuffer = this.stream.read(16);
        try {
            MessageHeader messageHeader = new MessageHeader(messageHeaderBuffer, this.description.getMaxMessageSize());
            ByteBuf messageBuffer = this.stream.read(messageHeader.getMessageLength() - 16);
            if (messageHeader.getOpCode() == OpCode.OP_COMPRESSED.getValue()) {
                CompressedHeader compressedHeader = new CompressedHeader(messageBuffer, messageHeader);
                Compressor compressor = getCompressor(compressedHeader);
                ByteBuf buffer = getBuffer(compressedHeader.getUncompressedSize());
                compressor.uncompress(messageBuffer, buffer);
                buffer.flip();
                return new ResponseBuffers(new ReplyHeader(buffer, compressedHeader), buffer);
            }
            return new ResponseBuffers(new ReplyHeader(messageBuffer, messageHeader), messageBuffer);
        } finally {
            messageHeaderBuffer.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Compressor getCompressor(CompressedHeader compressedHeader) {
        Compressor compressor = this.compressorMap.get(Byte.valueOf(compressedHeader.getCompressorId()));
        if (compressor == null) {
            throw new MongoClientException("Unsupported compressor with identifier " + ((int) compressedHeader.getCompressorId()));
        }
        return compressor;
    }

    @Override // com.mongodb.connection.BufferProvider
    public ByteBuf getBuffer(int size) {
        Assertions.notNull("open", this.stream);
        return this.stream.getBuffer(size);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/InternalStreamConnection$MessageHeaderCallback.class */
    private class MessageHeaderCallback implements SingleResultCallback<ByteBuf> {
        private final SingleResultCallback<ResponseBuffers> callback;

        MessageHeaderCallback(SingleResultCallback<ResponseBuffers> callback) {
            this.callback = callback;
        }

        @Override // com.mongodb.async.SingleResultCallback
        public void onResult(ByteBuf result, Throwable t) {
            try {
                if (t != null) {
                    this.callback.onResult(null, t);
                    return;
                }
                try {
                    MessageHeader messageHeader = new MessageHeader(result, InternalStreamConnection.this.description.getMaxMessageSize());
                    InternalStreamConnection.this.readAsync(messageHeader.getMessageLength() - 16, new MessageCallback(messageHeader));
                    if (result != null) {
                        result.release();
                    }
                } catch (Throwable localThrowable) {
                    this.callback.onResult(null, localThrowable);
                    if (result != null) {
                        result.release();
                    }
                }
            } catch (Throwable th) {
                if (result != null) {
                    result.release();
                }
                throw th;
            }
        }

        /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/InternalStreamConnection$MessageHeaderCallback$MessageCallback.class */
        private class MessageCallback implements SingleResultCallback<ByteBuf> {
            private final MessageHeader messageHeader;

            MessageCallback(MessageHeader messageHeader) {
                this.messageHeader = messageHeader;
            }

            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(ByteBuf result, Throwable t) {
                ReplyHeader replyHeader;
                ByteBuf responseBuffer;
                if (t != null) {
                    MessageHeaderCallback.this.callback.onResult(null, t);
                    return;
                }
                try {
                    if (this.messageHeader.getOpCode() == OpCode.OP_COMPRESSED.getValue()) {
                        try {
                            CompressedHeader compressedHeader = new CompressedHeader(result, this.messageHeader);
                            Compressor compressor = InternalStreamConnection.this.getCompressor(compressedHeader);
                            ByteBuf buffer = InternalStreamConnection.this.getBuffer(compressedHeader.getUncompressedSize());
                            compressor.uncompress(result, buffer);
                            buffer.flip();
                            replyHeader = new ReplyHeader(buffer, compressedHeader);
                            responseBuffer = buffer;
                            result.release();
                        } catch (Throwable th) {
                            result.release();
                            throw th;
                        }
                    } else {
                        replyHeader = new ReplyHeader(result, this.messageHeader);
                        responseBuffer = result;
                    }
                    MessageHeaderCallback.this.callback.onResult(new ResponseBuffers(replyHeader, responseBuffer), null);
                } catch (Throwable localThrowable) {
                    MessageHeaderCallback.this.callback.onResult(null, localThrowable);
                }
            }
        }
    }

    private CommandEventSender createCommandEventSender(CommandMessage message, ByteBufferBsonOutput bsonOutput) {
        if (opened() && (this.commandListener != null || COMMAND_PROTOCOL_LOGGER.isDebugEnabled())) {
            return new LoggingCommandEventSender(SECURITY_SENSITIVE_COMMANDS, this.description, this.commandListener, message, bsonOutput, COMMAND_PROTOCOL_LOGGER);
        }
        return new NoOpCommandEventSender();
    }
}
