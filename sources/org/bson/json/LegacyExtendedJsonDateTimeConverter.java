package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/LegacyExtendedJsonDateTimeConverter.class */
class LegacyExtendedJsonDateTimeConverter implements Converter<Long> {
    LegacyExtendedJsonDateTimeConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(Long value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeNumber("$date", Long.toString(value.longValue()));
        writer.writeEndObject();
    }
}
