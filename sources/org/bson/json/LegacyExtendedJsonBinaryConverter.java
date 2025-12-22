package org.bson.json;

import org.bson.BsonBinary;
import org.bson.internal.Base64;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/LegacyExtendedJsonBinaryConverter.class */
class LegacyExtendedJsonBinaryConverter implements Converter<BsonBinary> {
    LegacyExtendedJsonBinaryConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(BsonBinary value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeString("$binary", Base64.encode(value.getData()));
        writer.writeString("$type", String.format("%02X", Byte.valueOf(value.getType())));
        writer.writeEndObject();
    }
}
