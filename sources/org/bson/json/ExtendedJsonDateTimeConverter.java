package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ExtendedJsonDateTimeConverter.class */
class ExtendedJsonDateTimeConverter implements Converter<Long> {
    ExtendedJsonDateTimeConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(Long value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeStartObject("$date");
        writer.writeString("$numberLong", Long.toString(value.longValue()));
        writer.writeEndObject();
        writer.writeEndObject();
    }
}
