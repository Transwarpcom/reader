package org.bson.json;

import org.bson.BsonRegularExpression;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ExtendedJsonRegularExpressionConverter.class */
class ExtendedJsonRegularExpressionConverter implements Converter<BsonRegularExpression> {
    ExtendedJsonRegularExpressionConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(BsonRegularExpression value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeStartObject("$regularExpression");
        writer.writeString("pattern", value.getPattern());
        writer.writeString("options", value.getOptions());
        writer.writeEndObject();
        writer.writeEndObject();
    }
}
