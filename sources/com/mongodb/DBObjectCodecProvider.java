package com.mongodb;

import com.mongodb.assertions.Assertions;
import java.util.Date;
import java.util.List;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.Codec;
import org.bson.codecs.DateCodec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.BSONTimestamp;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/DBObjectCodecProvider.class */
public class DBObjectCodecProvider implements CodecProvider {
    private final BsonTypeClassMap bsonTypeClassMap;

    public DBObjectCodecProvider() {
        this(DBObjectCodec.getDefaultBsonTypeClassMap());
    }

    public DBObjectCodecProvider(BsonTypeClassMap bsonTypeClassMap) {
        this.bsonTypeClassMap = (BsonTypeClassMap) Assertions.notNull("bsonTypeClassMap", bsonTypeClassMap);
    }

    @Override // org.bson.codecs.configuration.CodecProvider
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == BSONTimestamp.class) {
            return new BSONTimestampCodec();
        }
        if (DBObject.class.isAssignableFrom(clazz) && !List.class.isAssignableFrom(clazz)) {
            return new DBObjectCodec(registry, this.bsonTypeClassMap);
        }
        if (Date.class.isAssignableFrom(clazz)) {
            return new DateCodec();
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
        return true;
    }

    public int hashCode() {
        return getClass().hashCode();
    }
}
