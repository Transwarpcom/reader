package org.bson.json;

import org.bson.BsonTimestamp;
import org.bson.internal.UnsignedLongs;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ExtendedJsonTimestampConverter.class */
class ExtendedJsonTimestampConverter implements Converter<BsonTimestamp> {
    ExtendedJsonTimestampConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(BsonTimestamp value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeStartObject("$timestamp");
        writer.writeNumber("t", UnsignedLongs.toString(toUnsignedLong(value.getTime())));
        writer.writeNumber("i", UnsignedLongs.toString(toUnsignedLong(value.getInc())));
        writer.writeEndObject();
        writer.writeEndObject();
    }

    private long toUnsignedLong(int value) {
        return value & 4294967295L;
    }
}
