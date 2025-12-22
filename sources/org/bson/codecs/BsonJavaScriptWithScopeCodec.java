package org.bson.codecs;

import org.bson.BsonDocument;
import org.bson.BsonJavaScriptWithScope;
import org.bson.BsonReader;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BsonJavaScriptWithScopeCodec.class */
public class BsonJavaScriptWithScopeCodec implements Codec<BsonJavaScriptWithScope> {
    private final Codec<BsonDocument> documentCodec;

    public BsonJavaScriptWithScopeCodec(Codec<BsonDocument> documentCodec) {
        this.documentCodec = documentCodec;
    }

    @Override // org.bson.codecs.Decoder
    public BsonJavaScriptWithScope decode(BsonReader bsonReader, DecoderContext decoderContext) {
        String code = bsonReader.readJavaScriptWithScope();
        BsonDocument scope = this.documentCodec.decode(bsonReader, decoderContext);
        return new BsonJavaScriptWithScope(code, scope);
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, BsonJavaScriptWithScope codeWithScope, EncoderContext encoderContext) {
        writer.writeJavaScriptWithScope(codeWithScope.getCode());
        this.documentCodec.encode(writer, codeWithScope.getScope(), encoderContext);
    }

    @Override // org.bson.codecs.Encoder
    public Class<BsonJavaScriptWithScope> getEncoderClass() {
        return BsonJavaScriptWithScope.class;
    }
}
