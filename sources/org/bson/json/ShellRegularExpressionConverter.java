package org.bson.json;

import org.bson.BsonRegularExpression;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ShellRegularExpressionConverter.class */
class ShellRegularExpressionConverter implements Converter<BsonRegularExpression> {
    ShellRegularExpressionConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(BsonRegularExpression value, StrictJsonWriter writer) {
        String escaped = value.getPattern().equals("") ? "(?:)" : value.getPattern().replace("/", "\\/");
        writer.writeRaw("/" + escaped + "/" + value.getOptions());
    }
}
