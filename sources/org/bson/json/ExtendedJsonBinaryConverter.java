package org.bson.json;

import io.netty.handler.codec.http.HttpHeaders;
import org.bson.BsonBinary;
import org.bson.internal.Base64;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ExtendedJsonBinaryConverter.class */
class ExtendedJsonBinaryConverter implements Converter<BsonBinary> {
    ExtendedJsonBinaryConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(BsonBinary value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeStartObject("$binary");
        writer.writeString(HttpHeaders.Values.BASE64, Base64.encode(value.getData()));
        writer.writeString("subType", String.format("%02X", Byte.valueOf(value.getType())));
        writer.writeEndObject();
        writer.writeEndObject();
    }
}
