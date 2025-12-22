package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonBooleanConverter.class */
class JsonBooleanConverter implements Converter<Boolean> {
    JsonBooleanConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(Boolean value, StrictJsonWriter writer) {
        writer.writeBoolean(value.booleanValue());
    }
}
