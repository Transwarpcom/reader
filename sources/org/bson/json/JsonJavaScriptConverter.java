package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonJavaScriptConverter.class */
class JsonJavaScriptConverter implements Converter<String> {
    JsonJavaScriptConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(String value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeString("$code", value);
        writer.writeEndObject();
    }
}
