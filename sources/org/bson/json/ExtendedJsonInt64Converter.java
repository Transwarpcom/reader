package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ExtendedJsonInt64Converter.class */
class ExtendedJsonInt64Converter implements Converter<Long> {
    ExtendedJsonInt64Converter() {
    }

    @Override // org.bson.json.Converter
    public void convert(Long value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeName("$numberLong");
        writer.writeString(Long.toString(value.longValue()));
        writer.writeEndObject();
    }
}
