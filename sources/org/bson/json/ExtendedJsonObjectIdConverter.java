package org.bson.json;

import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ExtendedJsonObjectIdConverter.class */
class ExtendedJsonObjectIdConverter implements Converter<ObjectId> {
    ExtendedJsonObjectIdConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(ObjectId value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeString("$oid", value.toHexString());
        writer.writeEndObject();
    }
}
