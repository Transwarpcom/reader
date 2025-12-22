package org.bson.codecs;

import java.util.HashMap;
import java.util.Map;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/ValueCodecProvider.class */
public class ValueCodecProvider implements CodecProvider {
    private final Map<Class<?>, Codec<?>> codecs = new HashMap();

    public ValueCodecProvider() {
        addCodecs();
    }

    @Override // org.bson.codecs.configuration.CodecProvider
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        return (Codec) this.codecs.get(clazz);
    }

    private void addCodecs() {
        addCodec(new BinaryCodec());
        addCodec(new BooleanCodec());
        addCodec(new DateCodec());
        addCodec(new DoubleCodec());
        addCodec(new IntegerCodec());
        addCodec(new LongCodec());
        addCodec(new MinKeyCodec());
        addCodec(new MaxKeyCodec());
        addCodec(new CodeCodec());
        addCodec(new Decimal128Codec());
        addCodec(new BigDecimalCodec());
        addCodec(new ObjectIdCodec());
        addCodec(new CharacterCodec());
        addCodec(new StringCodec());
        addCodec(new SymbolCodec());
        addCodec(new UuidCodec());
        addCodec(new ByteCodec());
        addCodec(new PatternCodec());
        addCodec(new ShortCodec());
        addCodec(new ByteArrayCodec());
        addCodec(new FloatCodec());
        addCodec(new AtomicBooleanCodec());
        addCodec(new AtomicIntegerCodec());
        addCodec(new AtomicLongCodec());
    }

    private <T> void addCodec(Codec<T> codec) {
        this.codecs.put(codec.getEncoderClass(), codec);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 0;
    }
}
