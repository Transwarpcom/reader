package org.bson.json;

import org.bson.BsonMinKey;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ExtendedJsonMinKeyConverter.class */
class ExtendedJsonMinKeyConverter implements Converter<BsonMinKey> {
    ExtendedJsonMinKeyConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(BsonMinKey value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeNumber("$minKey", CustomBooleanEditor.VALUE_1);
        writer.writeEndObject();
    }
}
