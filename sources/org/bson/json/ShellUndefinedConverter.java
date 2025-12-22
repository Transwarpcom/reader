package org.bson.json;

import org.bson.BsonUndefined;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ShellUndefinedConverter.class */
class ShellUndefinedConverter implements Converter<BsonUndefined> {
    ShellUndefinedConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(BsonUndefined value, StrictJsonWriter writer) {
        writer.writeRaw("undefined");
    }
}
