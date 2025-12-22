package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonDoubleConverter.class */
class JsonDoubleConverter implements Converter<Double> {
    JsonDoubleConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(Double value, StrictJsonWriter writer) {
        writer.writeNumber(Double.toString(value.doubleValue()));
    }
}
