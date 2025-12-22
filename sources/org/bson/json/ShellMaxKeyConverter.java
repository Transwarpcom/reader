package org.bson.json;

import org.bson.BsonMaxKey;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ShellMaxKeyConverter.class */
class ShellMaxKeyConverter implements Converter<BsonMaxKey> {
    ShellMaxKeyConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(BsonMaxKey value, StrictJsonWriter writer) {
        writer.writeRaw("MaxKey");
    }
}
