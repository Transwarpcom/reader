package org.bson.codecs;

import org.bson.Document;
import org.bson.Transformer;
import org.bson.assertions.Assertions;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.CodeWithScope;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/DocumentCodecProvider.class */
public class DocumentCodecProvider implements CodecProvider {
    private final BsonTypeClassMap bsonTypeClassMap;
    private final Transformer valueTransformer;

    public DocumentCodecProvider() {
        this(new BsonTypeClassMap());
    }

    public DocumentCodecProvider(Transformer valueTransformer) {
        this(new BsonTypeClassMap(), valueTransformer);
    }

    public DocumentCodecProvider(BsonTypeClassMap bsonTypeClassMap) {
        this(bsonTypeClassMap, null);
    }

    public DocumentCodecProvider(BsonTypeClassMap bsonTypeClassMap, Transformer valueTransformer) {
        this.bsonTypeClassMap = (BsonTypeClassMap) Assertions.notNull("bsonTypeClassMap", bsonTypeClassMap);
        this.valueTransformer = valueTransformer;
    }

    @Override // org.bson.codecs.configuration.CodecProvider
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == CodeWithScope.class) {
            return new CodeWithScopeCodec(registry.get(Document.class));
        }
        if (clazz == Document.class) {
            return new DocumentCodec(registry, this.bsonTypeClassMap, this.valueTransformer);
        }
        return null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DocumentCodecProvider that = (DocumentCodecProvider) o;
        if (!this.bsonTypeClassMap.equals(that.bsonTypeClassMap)) {
            return false;
        }
        if (this.valueTransformer != null) {
            if (!this.valueTransformer.equals(that.valueTransformer)) {
                return false;
            }
            return true;
        }
        if (that.valueTransformer != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.bsonTypeClassMap.hashCode();
        return (31 * result) + (this.valueTransformer != null ? this.valueTransformer.hashCode() : 0);
    }
}
