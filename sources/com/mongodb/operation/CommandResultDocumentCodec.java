package com.mongodb.operation;

import java.util.Collections;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonValue;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.Codec;
import org.bson.codecs.Decoder;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/CommandResultDocumentCodec.class */
class CommandResultDocumentCodec<T> extends BsonDocumentCodec {
    private final Decoder<T> payloadDecoder;
    private final List<String> fieldsContainingPayload;

    CommandResultDocumentCodec(CodecRegistry registry, Decoder<T> payloadDecoder, List<String> fieldsContainingPayload) {
        super(registry);
        this.payloadDecoder = payloadDecoder;
        this.fieldsContainingPayload = fieldsContainingPayload;
    }

    static <P> Codec<BsonDocument> create(Decoder<P> decoder, String fieldContainingPayload) {
        return create(decoder, (List<String>) Collections.singletonList(fieldContainingPayload));
    }

    static <P> Codec<BsonDocument> create(Decoder<P> decoder, List<String> fieldsContainingPayload) {
        CodecRegistry registry = CodecRegistries.fromProviders(new CommandResultCodecProvider(decoder, fieldsContainingPayload));
        return registry.get(BsonDocument.class);
    }

    @Override // org.bson.codecs.BsonDocumentCodec
    protected BsonValue readValue(BsonReader reader, DecoderContext decoderContext) {
        if (this.fieldsContainingPayload.contains(reader.getCurrentName())) {
            if (reader.getCurrentBsonType() == BsonType.DOCUMENT) {
                return new BsonDocumentWrapper(this.payloadDecoder.decode(reader, decoderContext), null);
            }
            if (reader.getCurrentBsonType() == BsonType.ARRAY) {
                return new CommandResultArrayCodec(getCodecRegistry(), this.payloadDecoder).decode(reader, decoderContext);
            }
        }
        return super.readValue(reader, decoderContext);
    }
}
