package org.bson.codecs;

import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/EncoderContext.class */
public final class EncoderContext {
    private static final EncoderContext DEFAULT_CONTEXT = builder().build();
    private final boolean encodingCollectibleDocument;

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/EncoderContext$Builder.class */
    public static final class Builder {
        private boolean encodingCollectibleDocument;

        private Builder() {
        }

        public Builder isEncodingCollectibleDocument(boolean encodingCollectibleDocument) {
            this.encodingCollectibleDocument = encodingCollectibleDocument;
            return this;
        }

        public EncoderContext build() {
            return new EncoderContext(this);
        }
    }

    public boolean isEncodingCollectibleDocument() {
        return this.encodingCollectibleDocument;
    }

    public <T> void encodeWithChildContext(Encoder<T> encoder, BsonWriter writer, T value) {
        encoder.encode(writer, value, DEFAULT_CONTEXT);
    }

    public EncoderContext getChildContext() {
        return DEFAULT_CONTEXT;
    }

    private EncoderContext(Builder builder) {
        this.encodingCollectibleDocument = builder.encodingCollectibleDocument;
    }
}
