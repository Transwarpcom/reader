package com.mongodb.operation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.codecs.BsonArrayCodec;
import org.bson.codecs.BsonBinaryCodec;
import org.bson.codecs.BsonBooleanCodec;
import org.bson.codecs.BsonDBPointerCodec;
import org.bson.codecs.BsonDateTimeCodec;
import org.bson.codecs.BsonDecimal128Codec;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.BsonDoubleCodec;
import org.bson.codecs.BsonInt32Codec;
import org.bson.codecs.BsonInt64Codec;
import org.bson.codecs.BsonJavaScriptCodec;
import org.bson.codecs.BsonJavaScriptWithScopeCodec;
import org.bson.codecs.BsonMaxKeyCodec;
import org.bson.codecs.BsonMinKeyCodec;
import org.bson.codecs.BsonNullCodec;
import org.bson.codecs.BsonObjectIdCodec;
import org.bson.codecs.BsonRegularExpressionCodec;
import org.bson.codecs.BsonStringCodec;
import org.bson.codecs.BsonSymbolCodec;
import org.bson.codecs.BsonTimestampCodec;
import org.bson.codecs.BsonUndefinedCodec;
import org.bson.codecs.Codec;
import org.bson.codecs.Decoder;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/CommandResultCodecProvider.class */
class CommandResultCodecProvider<P> implements CodecProvider {
    private final Map<Class<?>, Codec<?>> codecs = new HashMap();
    private final Decoder<P> payloadDecoder;
    private final List<String> fieldsContainingPayload;

    CommandResultCodecProvider(Decoder<P> payloadDecoder, List<String> fieldContainingPayload) {
        this.payloadDecoder = payloadDecoder;
        this.fieldsContainingPayload = fieldContainingPayload;
        addCodecs();
    }

    @Override // org.bson.codecs.configuration.CodecProvider
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (this.codecs.containsKey(clazz)) {
            return (Codec) this.codecs.get(clazz);
        }
        if (clazz == BsonArray.class) {
            return new BsonArrayCodec(registry);
        }
        if (clazz == BsonDocument.class) {
            return new CommandResultDocumentCodec(registry, this.payloadDecoder, this.fieldsContainingPayload);
        }
        return null;
    }

    private void addCodecs() {
        addCodec(new BsonNullCodec());
        addCodec(new BsonBinaryCodec());
        addCodec(new BsonBooleanCodec());
        addCodec(new BsonDateTimeCodec());
        addCodec(new BsonDBPointerCodec());
        addCodec(new BsonDoubleCodec());
        addCodec(new BsonInt32Codec());
        addCodec(new BsonInt64Codec());
        addCodec(new BsonDecimal128Codec());
        addCodec(new BsonMinKeyCodec());
        addCodec(new BsonMaxKeyCodec());
        addCodec(new BsonJavaScriptCodec());
        addCodec(new BsonObjectIdCodec());
        addCodec(new BsonRegularExpressionCodec());
        addCodec(new BsonStringCodec());
        addCodec(new BsonSymbolCodec());
        addCodec(new BsonTimestampCodec());
        addCodec(new BsonUndefinedCodec());
        addCodec(new BsonJavaScriptWithScopeCodec(new BsonDocumentCodec()));
    }

    private <T extends BsonValue> void addCodec(Codec<T> codec) {
        this.codecs.put(codec.getEncoderClass(), codec);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommandResultCodecProvider<?> that = (CommandResultCodecProvider) o;
        if (!this.fieldsContainingPayload.equals(that.fieldsContainingPayload) || !this.payloadDecoder.getClass().equals(that.payloadDecoder.getClass())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.payloadDecoder.getClass().hashCode();
        return (31 * result) + this.fieldsContainingPayload.hashCode();
    }
}
