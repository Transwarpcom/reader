package org.bson.codecs;

import org.bson.BsonInvalidOperationException;
import org.bson.BsonReader;
import org.bson.BsonType;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/NumberCodecHelper.class */
final class NumberCodecHelper {
    static int decodeInt(BsonReader reader) {
        int intValue;
        BsonType bsonType = reader.getCurrentBsonType();
        switch (bsonType) {
            case INT32:
                intValue = reader.readInt32();
                break;
            case INT64:
                long longValue = reader.readInt64();
                intValue = (int) longValue;
                if (longValue != intValue) {
                    throw invalidConversion(Integer.class, Long.valueOf(longValue));
                }
                break;
            case DOUBLE:
                double doubleValue = reader.readDouble();
                intValue = (int) doubleValue;
                if (doubleValue != intValue) {
                    throw invalidConversion(Integer.class, Double.valueOf(doubleValue));
                }
                break;
            default:
                throw new BsonInvalidOperationException(String.format("Invalid numeric type, found: %s", bsonType));
        }
        return intValue;
    }

    static long decodeLong(BsonReader reader) {
        long longValue;
        BsonType bsonType = reader.getCurrentBsonType();
        switch (bsonType) {
            case INT32:
                longValue = reader.readInt32();
                break;
            case INT64:
                longValue = reader.readInt64();
                break;
            case DOUBLE:
                double doubleValue = reader.readDouble();
                longValue = (long) doubleValue;
                if (doubleValue != longValue) {
                    throw invalidConversion(Long.class, Double.valueOf(doubleValue));
                }
                break;
            default:
                throw new BsonInvalidOperationException(String.format("Invalid numeric type, found: %s", bsonType));
        }
        return longValue;
    }

    static double decodeDouble(BsonReader reader) {
        double doubleValue;
        BsonType bsonType = reader.getCurrentBsonType();
        switch (bsonType) {
            case INT32:
                doubleValue = reader.readInt32();
                break;
            case INT64:
                long longValue = reader.readInt64();
                doubleValue = longValue;
                if (longValue != ((long) doubleValue)) {
                    throw invalidConversion(Double.class, Long.valueOf(longValue));
                }
                break;
            case DOUBLE:
                doubleValue = reader.readDouble();
                break;
            default:
                throw new BsonInvalidOperationException(String.format("Invalid numeric type, found: %s", bsonType));
        }
        return doubleValue;
    }

    private static <T extends Number> BsonInvalidOperationException invalidConversion(Class<T> clazz, Number value) {
        return new BsonInvalidOperationException(String.format("Could not convert `%s` to a %s without losing precision", value, clazz));
    }

    private NumberCodecHelper() {
    }
}
