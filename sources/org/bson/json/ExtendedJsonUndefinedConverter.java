package org.bson.json;

import org.bson.BsonUndefined;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ExtendedJsonUndefinedConverter.class */
class ExtendedJsonUndefinedConverter implements Converter<BsonUndefined> {
    ExtendedJsonUndefinedConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(BsonUndefined value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeBoolean("$undefined", true);
        writer.writeEndObject();
    }
}
