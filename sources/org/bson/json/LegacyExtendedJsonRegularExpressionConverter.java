package org.bson.json;

import org.bson.BsonRegularExpression;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/LegacyExtendedJsonRegularExpressionConverter.class */
class LegacyExtendedJsonRegularExpressionConverter implements Converter<BsonRegularExpression> {
    LegacyExtendedJsonRegularExpressionConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(BsonRegularExpression value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeString("$regex", value.getPattern());
        writer.writeString("$options", value.getOptions());
        writer.writeEndObject();
    }
}
