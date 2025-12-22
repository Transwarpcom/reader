package com.mongodb.internal.connection;

import com.mongodb.MongoNamespace;
import com.mongodb.ReadPreference;
import com.mongodb.assertions.Assertions;
import com.mongodb.connection.ByteBufferBsonOutput;
import com.mongodb.connection.ClusterConnectionMode;
import com.mongodb.connection.ServerType;
import com.mongodb.connection.ServerVersion;
import com.mongodb.connection.SplittablePayload;
import com.mongodb.internal.connection.RequestMessage;
import com.mongodb.internal.validator.MappedFieldNameValidator;
import com.mongodb.session.SessionContext;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.BsonArray;
import org.bson.BsonBinaryWriter;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonElement;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.BsonWriter;
import org.bson.FieldNameValidator;
import org.bson.codecs.EncoderContext;
import org.bson.io.BsonOutput;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/CommandMessage.class */
public final class CommandMessage extends RequestMessage {
    private final MongoNamespace namespace;
    private final BsonDocument command;
    private final FieldNameValidator commandFieldNameValidator;
    private final ReadPreference readPreference;
    private final SplittablePayload payload;
    private final FieldNameValidator payloadFieldNameValidator;
    private final boolean responseExpected;
    private final ClusterConnectionMode clusterConnectionMode;

    @Override // com.mongodb.internal.connection.RequestMessage
    public /* bridge */ /* synthetic */ RequestMessage.EncodingMetadata getEncodingMetadata() {
        return super.getEncodingMetadata();
    }

    @Override // com.mongodb.internal.connection.RequestMessage
    public /* bridge */ /* synthetic */ void encode(BsonOutput bsonOutput, SessionContext sessionContext) {
        super.encode(bsonOutput, sessionContext);
    }

    @Override // com.mongodb.internal.connection.RequestMessage
    public /* bridge */ /* synthetic */ MessageSettings getSettings() {
        return super.getSettings();
    }

    @Override // com.mongodb.internal.connection.RequestMessage
    public /* bridge */ /* synthetic */ OpCode getOpCode() {
        return super.getOpCode();
    }

    @Override // com.mongodb.internal.connection.RequestMessage
    public /* bridge */ /* synthetic */ int getId() {
        return super.getId();
    }

    CommandMessage(MongoNamespace namespace, BsonDocument command, FieldNameValidator commandFieldNameValidator, ReadPreference readPreference, MessageSettings settings) {
        this(namespace, command, commandFieldNameValidator, readPreference, settings, true, null, null, ClusterConnectionMode.MULTIPLE);
    }

    CommandMessage(MongoNamespace namespace, BsonDocument command, FieldNameValidator commandFieldNameValidator, ReadPreference readPreference, MessageSettings settings, boolean responseExpected, SplittablePayload payload, FieldNameValidator payloadFieldNameValidator, ClusterConnectionMode clusterConnectionMode) {
        super(namespace.getFullName(), getOpCode(settings), settings);
        this.namespace = namespace;
        this.command = command;
        this.commandFieldNameValidator = commandFieldNameValidator;
        this.readPreference = readPreference;
        this.responseExpected = responseExpected;
        this.payload = payload;
        this.payloadFieldNameValidator = payloadFieldNameValidator;
        this.clusterConnectionMode = clusterConnectionMode;
    }

    BsonDocument getCommandDocument(ByteBufferBsonOutput bsonOutput) {
        BsonDocument commandBsonDocument;
        ByteBufBsonDocument byteBufBsonDocument = ByteBufBsonDocument.createOne(bsonOutput, getEncodingMetadata().getFirstDocumentPosition());
        if (useOpMsg() && containsPayload()) {
            commandBsonDocument = byteBufBsonDocument.toBsonDocument();
            int payloadStartPosition = getEncodingMetadata().getFirstDocumentPosition() + byteBufBsonDocument.getSizeInBytes() + 1 + 4 + this.payload.getPayloadName().getBytes(Charset.forName("UTF-8")).length + 1;
            commandBsonDocument.append(this.payload.getPayloadName(), new BsonArray(ByteBufBsonDocument.createList(bsonOutput, payloadStartPosition)));
        } else {
            commandBsonDocument = byteBufBsonDocument;
        }
        if (commandBsonDocument.containsKey("$query")) {
            commandBsonDocument = commandBsonDocument.getDocument("$query");
        }
        return commandBsonDocument;
    }

    boolean containsPayload() {
        return this.payload != null;
    }

    boolean isResponseExpected() {
        Assertions.isTrue("The message must be encoded before determining if a response is expected", getEncodingMetadata() != null);
        return !useOpMsg() || requireOpMsgResponse();
    }

    MongoNamespace getNamespace() {
        return this.namespace;
    }

    @Override // com.mongodb.internal.connection.RequestMessage
    protected RequestMessage.EncodingMetadata encodeMessageBodyWithMetadata(BsonOutput bsonOutput, SessionContext sessionContext) {
        int commandStartPosition;
        int messageStartPosition = bsonOutput.getPosition() - 16;
        if (useOpMsg()) {
            int flagPosition = bsonOutput.getPosition();
            bsonOutput.writeInt32(0);
            bsonOutput.writeByte(0);
            commandStartPosition = bsonOutput.getPosition();
            addDocument(getCommandToEncode(), bsonOutput, this.commandFieldNameValidator, getExtraElements(sessionContext));
            if (this.payload != null) {
                bsonOutput.writeByte(1);
                int payloadBsonOutputStartPosition = bsonOutput.getPosition();
                bsonOutput.writeInt32(0);
                bsonOutput.writeCString(this.payload.getPayloadName());
                BsonWriterHelper.writePayload(new BsonBinaryWriter(bsonOutput, this.payloadFieldNameValidator), bsonOutput, getSettings(), messageStartPosition, this.payload);
                int payloadBsonOutputLength = bsonOutput.getPosition() - payloadBsonOutputStartPosition;
                bsonOutput.writeInt32(payloadBsonOutputStartPosition, payloadBsonOutputLength);
            }
            bsonOutput.writeInt32(flagPosition, getOpMsgFlagBits());
        } else {
            bsonOutput.writeInt32(getOpQueryFlagBits());
            bsonOutput.writeCString(this.namespace.getFullName());
            bsonOutput.writeInt32(0);
            bsonOutput.writeInt32(-1);
            commandStartPosition = bsonOutput.getPosition();
            if (this.payload == null) {
                addDocument(getCommandToEncode(), bsonOutput, this.commandFieldNameValidator, null);
            } else {
                addDocumentWithPayload(bsonOutput, messageStartPosition);
            }
        }
        return new RequestMessage.EncodingMetadata(commandStartPosition);
    }

    private FieldNameValidator getPayloadArrayFieldNameValidator() {
        Map<String, FieldNameValidator> rootMap = new HashMap<>();
        rootMap.put(this.payload.getPayloadName(), this.payloadFieldNameValidator);
        return new MappedFieldNameValidator(this.commandFieldNameValidator, rootMap);
    }

    private void addDocumentWithPayload(BsonOutput bsonOutput, int messageStartPosition) {
        BsonBinaryWriter bsonBinaryWriter = new BsonBinaryWriter(bsonOutput, getPayloadArrayFieldNameValidator());
        BsonWriter bsonWriter = new SplittablePayloadBsonWriter(bsonBinaryWriter, bsonOutput, messageStartPosition, getSettings(), this.payload);
        BsonDocument commandToEncode = getCommandToEncode();
        getCodec(commandToEncode).encode(bsonWriter, commandToEncode, EncoderContext.builder().build());
    }

    private int getOpMsgFlagBits() {
        return getOpMsgResponseExpectedFlagBit();
    }

    private int getOpMsgResponseExpectedFlagBit() {
        if (requireOpMsgResponse()) {
            return 0;
        }
        return 2;
    }

    private boolean requireOpMsgResponse() {
        if (this.responseExpected) {
            return true;
        }
        return this.payload != null && this.payload.hasAnotherSplit();
    }

    private int getOpQueryFlagBits() {
        return getOpQuerySlaveOkFlagBit();
    }

    private int getOpQuerySlaveOkFlagBit() {
        if (isSlaveOk()) {
            return 4;
        }
        return 0;
    }

    private boolean isSlaveOk() {
        return (this.readPreference != null && this.readPreference.isSlaveOk()) || isDirectConnectionToNonShardRouter();
    }

    private boolean isDirectConnectionToNonShardRouter() {
        return this.clusterConnectionMode == ClusterConnectionMode.SINGLE && getSettings().getServerType() != ServerType.SHARD_ROUTER;
    }

    private boolean useOpMsg() {
        return getOpCode().equals(OpCode.OP_MSG);
    }

    private BsonDocument getCommandToEncode() {
        BsonDocument commandToEncode = this.command;
        if (!useOpMsg() && this.readPreference != null && !this.readPreference.equals(ReadPreference.primary())) {
            commandToEncode = new BsonDocument("$query", this.command).append("$readPreference", this.readPreference.toDocument());
        }
        return commandToEncode;
    }

    private List<BsonElement> getExtraElements(SessionContext sessionContext) {
        List<BsonElement> extraElements = new ArrayList<>();
        extraElements.add(new BsonElement("$db", new BsonString(new MongoNamespace(getCollectionName()).getDatabaseName())));
        if (sessionContext.getClusterTime() != null) {
            extraElements.add(new BsonElement("$clusterTime", sessionContext.getClusterTime()));
        }
        if (sessionContext.hasSession() && this.responseExpected) {
            extraElements.add(new BsonElement("lsid", sessionContext.getSessionId()));
        }
        boolean firstMessageInTransaction = sessionContext.notifyMessageSent();
        if (sessionContext.hasActiveTransaction()) {
            extraElements.add(new BsonElement("txnNumber", new BsonInt64(sessionContext.getTransactionNumber())));
            if (firstMessageInTransaction) {
                extraElements.add(new BsonElement("startTransaction", BsonBoolean.TRUE));
                addReadConcernDocument(extraElements, sessionContext);
            }
            extraElements.add(new BsonElement("autocommit", BsonBoolean.FALSE));
        }
        if (this.readPreference != null) {
            if (!this.readPreference.equals(ReadPreference.primary())) {
                extraElements.add(new BsonElement("$readPreference", this.readPreference.toDocument()));
            } else if (isDirectConnectionToNonShardRouter()) {
                extraElements.add(new BsonElement("$readPreference", ReadPreference.primaryPreferred().toDocument()));
            }
        }
        return extraElements;
    }

    private void addReadConcernDocument(List<BsonElement> extraElements, SessionContext sessionContext) {
        BsonDocument readConcernDocument = ReadConcernHelper.getReadConcernDocument(sessionContext);
        if (!readConcernDocument.isEmpty()) {
            extraElements.add(new BsonElement("readConcern", readConcernDocument));
        }
    }

    private static OpCode getOpCode(MessageSettings settings) {
        return isServerVersionAtLeastThreeDotSix(settings) ? OpCode.OP_MSG : OpCode.OP_QUERY;
    }

    private static boolean isServerVersionAtLeastThreeDotSix(MessageSettings settings) {
        return settings.getServerVersion().compareTo(new ServerVersion(3, 6)) >= 0;
    }
}
