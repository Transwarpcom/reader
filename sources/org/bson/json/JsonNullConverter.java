package org.bson.json;

import org.bson.BsonNull;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonNullConverter.class */
class JsonNullConverter implements Converter<BsonNull> {
    JsonNullConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(BsonNull value, StrictJsonWriter writer) {
        writer.writeNull();
    }
}
