package com.mongodb.internal.connection;

import com.mongodb.connection.SplittablePayload;
import com.mongodb.internal.connection.MessageSettings;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.BsonElement;
import org.bson.BsonMaximumSizeExceededException;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.codecs.BsonValueCodecProvider;
import org.bson.codecs.Codec;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.io.BsonOutput;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/BsonWriterHelper.class */
final class BsonWriterHelper {
    private static final int DOCUMENT_HEADROOM = 16384;
    private static final CodecRegistry REGISTRY = CodecRegistries.fromProviders(new BsonValueCodecProvider());
    private static final EncoderContext ENCODER_CONTEXT = EncoderContext.builder().build();

    static void writeElements(BsonWriter writer, List<BsonElement> bsonElements) {
        for (BsonElement bsonElement : bsonElements) {
            writer.writeName(bsonElement.getName());
            getCodec(bsonElement.getValue()).encode(writer, bsonElement.getValue(), ENCODER_CONTEXT);
        }
    }

    static void writePayloadArray(BsonWriter writer, BsonOutput bsonOutput, MessageSettings settings, int messageStartPosition, SplittablePayload payload) {
        writer.writeStartArray(payload.getPayloadName());
        writePayload(writer, bsonOutput, getDocumentMessageSettings(settings), messageStartPosition, payload);
        writer.writeEndArray();
    }

    static void writePayload(BsonWriter writer, BsonOutput bsonOutput, MessageSettings settings, int messageStartPosition, SplittablePayload payload) {
        MessageSettings payloadSettings = getPayloadMessageSettings(payload.getPayloadType(), settings);
        for (int i = 0; i < payload.getPayload().size() && writeDocument(writer, bsonOutput, payloadSettings, payload.getPayload().get(i), messageStartPosition, i + 1); i++) {
            payload.setPosition(i + 1);
        }
        if (payload.getPosition() == 0) {
            throw new BsonMaximumSizeExceededException(String.format("Payload document size is larger than maximum of %d.", Integer.valueOf(payloadSettings.getMaxDocumentSize())));
        }
    }

    private static boolean writeDocument(BsonWriter writer, BsonOutput bsonOutput, MessageSettings settings, BsonDocument document, int messageStartPosition, int batchItemCount) {
        int currentPosition = bsonOutput.getPosition();
        getCodec(document).encode(writer, document, ENCODER_CONTEXT);
        int messageSize = bsonOutput.getPosition() - messageStartPosition;
        int documentSize = bsonOutput.getPosition() - currentPosition;
        if (exceedsLimits(settings, messageSize, documentSize, batchItemCount)) {
            bsonOutput.truncateToPosition(currentPosition);
            return false;
        }
        return true;
    }

    private static Codec<BsonValue> getCodec(BsonValue bsonValue) {
        return REGISTRY.get(bsonValue.getClass());
    }

    private static MessageSettings getPayloadMessageSettings(SplittablePayload.Type type, MessageSettings settings) {
        MessageSettings payloadMessageSettings = settings;
        if (type != SplittablePayload.Type.INSERT) {
            payloadMessageSettings = createMessageSettingsBuilder(settings).maxDocumentSize(settings.getMaxDocumentSize() + 16384).build();
        }
        return payloadMessageSettings;
    }

    private static MessageSettings getDocumentMessageSettings(MessageSettings settings) {
        return createMessageSettingsBuilder(settings).maxMessageSize(settings.getMaxDocumentSize() + 16384).build();
    }

    private static MessageSettings.Builder createMessageSettingsBuilder(MessageSettings settings) {
        return MessageSettings.builder().maxBatchCount(settings.getMaxBatchCount()).maxMessageSize(settings.getMaxMessageSize()).maxDocumentSize(settings.getMaxDocumentSize()).serverVersion(settings.getServerVersion());
    }

    private static boolean exceedsLimits(MessageSettings settings, int messageSize, int documentSize, int batchItemCount) {
        if (batchItemCount > settings.getMaxBatchCount() || messageSize > settings.getMaxMessageSize() || documentSize > settings.getMaxDocumentSize()) {
            return true;
        }
        return false;
    }

    private BsonWriterHelper() {
    }
}
