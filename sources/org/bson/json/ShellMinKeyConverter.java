package org.bson.json;

import org.bson.BsonMinKey;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ShellMinKeyConverter.class */
class ShellMinKeyConverter implements Converter<BsonMinKey> {
    ShellMinKeyConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(BsonMinKey value, StrictJsonWriter writer) {
        writer.writeRaw("MinKey");
    }
}
