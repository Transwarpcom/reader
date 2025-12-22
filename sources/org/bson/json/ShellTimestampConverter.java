package org.bson.json;

import org.bson.BsonTimestamp;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ShellTimestampConverter.class */
class ShellTimestampConverter implements Converter<BsonTimestamp> {
    ShellTimestampConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(BsonTimestamp value, StrictJsonWriter writer) {
        writer.writeRaw(String.format("Timestamp(%d, %d)", Integer.valueOf(value.getTime()), Integer.valueOf(value.getInc())));
    }
}
