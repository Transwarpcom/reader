package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonStringConverter.class */
class JsonStringConverter implements Converter<String> {
    JsonStringConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(String value, StrictJsonWriter writer) {
        writer.writeString(value);
    }
}
