package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonSymbolConverter.class */
class JsonSymbolConverter implements Converter<String> {
    JsonSymbolConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(String value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeString("$symbol", value);
        writer.writeEndObject();
    }
}
