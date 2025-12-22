package org.bson.codecs;

import org.bson.BsonReader;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/DecoderContext.class */
public final class DecoderContext {
    private static final DecoderContext DEFAULT_CONTEXT = builder().build();
    private final boolean checkedDiscriminator;

    public boolean hasCheckedDiscriminator() {
        return this.checkedDiscriminator;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/DecoderContext$Builder.class */
    public static final class Builder {
        private boolean checkedDiscriminator;

        private Builder() {
        }

        public boolean hasCheckedDiscriminator() {
            return this.checkedDiscriminator;
        }

        public Builder checkedDiscriminator(boolean checkedDiscriminator) {
            this.checkedDiscriminator = checkedDiscriminator;
            return this;
        }

        public DecoderContext build() {
            return new DecoderContext(this);
        }
    }

    public <T> T decodeWithChildContext(Decoder<T> decoder, BsonReader reader) {
        return decoder.decode(reader, DEFAULT_CONTEXT);
    }

    private DecoderContext(Builder builder) {
        this.checkedDiscriminator = builder.hasCheckedDiscriminator();
    }
}
