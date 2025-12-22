package org.bson.json;

import org.bson.types.Decimal128;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ExtendedJsonDecimal128Converter.class */
class ExtendedJsonDecimal128Converter implements Converter<Decimal128> {
    ExtendedJsonDecimal128Converter() {
    }

    @Override // org.bson.json.Converter
    public void convert(Decimal128 value, StrictJsonWriter writer) {
        writer.writeStartObject();
        writer.writeName("$numberDecimal");
        writer.writeString(value.toString());
        writer.writeEndObject();
    }
}
