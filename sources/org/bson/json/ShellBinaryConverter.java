package org.bson.json;

import org.bson.BsonBinary;
import org.bson.internal.Base64;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ShellBinaryConverter.class */
class ShellBinaryConverter implements Converter<BsonBinary> {
    ShellBinaryConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(BsonBinary value, StrictJsonWriter writer) {
        writer.writeRaw(String.format("new BinData(%s, \"%s\")", Integer.toString(value.getType() & 255), Base64.encode(value.getData())));
    }
}
