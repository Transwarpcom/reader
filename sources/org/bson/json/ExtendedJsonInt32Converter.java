package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ExtendedJsonInt32Converter.class */
class ExtendedJsonInt32Converter implements Converter<Integer> {
    ExtendedJsonInt32Converter() {
    }

    @Override // org.bson.json.Converter
    public void convert(Integer value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeName("$numberInt");
        writer.writeString(Integer.toString(value.intValue()));
        writer.writeEndObject();
    }
}
