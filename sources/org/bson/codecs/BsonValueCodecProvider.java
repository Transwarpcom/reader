package org.bson.codecs;

import java.util.HashMap;
import java.util.Map;
import org.bson.BsonArray;
import org.bson.BsonBinary;
import org.bson.BsonBoolean;
import org.bson.BsonDateTime;
import org.bson.BsonDbPointer;
import org.bson.BsonDecimal128;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.BsonDouble;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonJavaScript;
import org.bson.BsonJavaScriptWithScope;
import org.bson.BsonMaxKey;
import org.bson.BsonMinKey;
import org.bson.BsonNull;
import org.bson.BsonObjectId;
import org.bson.BsonRegularExpression;
import org.bson.BsonString;
import org.bson.BsonSymbol;
import org.bson.BsonTimestamp;
import org.bson.BsonType;
import org.bson.BsonUndefined;
import org.bson.BsonValue;
import org.bson.RawBsonDocument;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BsonValueCodecProvider.class */
public class BsonValueCodecProvider implements CodecProvider {
    private static final BsonTypeClassMap DEFAULT_BSON_TYPE_CLASS_MAP;
    private final Map<Class<?>, Codec<?>> codecs = new HashMap();

    public BsonValueCodecProvider() {
        addCodecs();
    }

    public static Class<? extends BsonValue> getClassForBsonType(BsonType bsonType) {
        return DEFAULT_BSON_TYPE_CLASS_MAP.get(bsonType);
    }

    public static BsonTypeClassMap getBsonTypeClassMap() {
        return DEFAULT_BSON_TYPE_CLASS_MAP;
    }

    @Override // org.bson.codecs.configuration.CodecProvider
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (this.codecs.containsKey(clazz)) {
            return (Codec) this.codecs.get(clazz);
        }
        if (clazz == BsonJavaScriptWithScope.class) {
            return new BsonJavaScriptWithScopeCodec(registry.get(BsonDocument.class));
        }
        if (clazz == BsonValue.class) {
            return new BsonValueCodec(registry);
        }
        if (clazz == BsonDocumentWrapper.class) {
            return new BsonDocumentWrapperCodec(registry.get(BsonDocument.class));
        }
        if (clazz == RawBsonDocument.class) {
            return new RawBsonDocumentCodec();
        }
        if (BsonDocument.class.isAssignableFrom(clazz)) {
            return new BsonDocumentCodec(registry);
        }
        if (BsonArray.class.isAssignableFrom(clazz)) {
            return new BsonArrayCodec(registry);
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
    }

    private <T extends BsonValue> void addCodec(Codec<T> codec) {
        this.codecs.put(codec.getEncoderClass(), codec);
    }

    static {
        Map<BsonType, Class<?>> map = new HashMap<>();
        map.put(BsonType.NULL, BsonNull.class);
        map.put(BsonType.ARRAY, BsonArray.class);
        map.put(BsonType.BINARY, BsonBinary.class);
        map.put(BsonType.BOOLEAN, BsonBoolean.class);
        map.put(BsonType.DATE_TIME, BsonDateTime.class);
        map.put(BsonType.DB_POINTER, BsonDbPointer.class);
        map.put(BsonType.DOCUMENT, BsonDocument.class);
        map.put(BsonType.DOUBLE, BsonDouble.class);
        map.put(BsonType.INT32, BsonInt32.class);
        map.put(BsonType.INT64, BsonInt64.class);
        map.put(BsonType.DECIMAL128, BsonDecimal128.class);
        map.put(BsonType.MAX_KEY, BsonMaxKey.class);
        map.put(BsonType.MIN_KEY, BsonMinKey.class);
        map.put(BsonType.JAVASCRIPT, BsonJavaScript.class);
        map.put(BsonType.JAVASCRIPT_WITH_SCOPE, BsonJavaScriptWithScope.class);
        map.put(BsonType.OBJECT_ID, BsonObjectId.class);
        map.put(BsonType.REGULAR_EXPRESSION, BsonRegularExpression.class);
        map.put(BsonType.STRING, BsonString.class);
        map.put(BsonType.SYMBOL, BsonSymbol.class);
        map.put(BsonType.TIMESTAMP, BsonTimestamp.class);
        map.put(BsonType.UNDEFINED, BsonUndefined.class);
        DEFAULT_BSON_TYPE_CLASS_MAP = new BsonTypeClassMap(map);
    }
}
