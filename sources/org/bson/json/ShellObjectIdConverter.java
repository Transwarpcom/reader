package org.bson.json;

import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ShellObjectIdConverter.class */
class ShellObjectIdConverter implements Converter<ObjectId> {
    ShellObjectIdConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(ObjectId value, StrictJsonWriter writer) {
        writer.writeRaw(String.format("ObjectId(\"%s\")", value.toHexString()));
    }
}
