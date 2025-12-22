package org.bson.json;

import org.bson.BsonMaxKey;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ExtendedJsonMaxKeyConverter.class */
class ExtendedJsonMaxKeyConverter implements Converter<BsonMaxKey> {
    ExtendedJsonMaxKeyConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(BsonMaxKey value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeNumber("$maxKey", CustomBooleanEditor.VALUE_1);
        writer.writeEndObject();
    }
}
