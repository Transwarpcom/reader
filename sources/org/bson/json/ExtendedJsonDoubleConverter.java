package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ExtendedJsonDoubleConverter.class */
class ExtendedJsonDoubleConverter implements Converter<Double> {
    ExtendedJsonDoubleConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(Double value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeName("$numberDouble");
        writer.writeString(Double.toString(value.doubleValue()));
        writer.writeEndObject();
    }
}
