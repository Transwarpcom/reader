package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/RelaxedExtendedJsonInt64Converter.class */
class RelaxedExtendedJsonInt64Converter implements Converter<Long> {
    RelaxedExtendedJsonInt64Converter() {
    }

    @Override // org.bson.json.Converter
    public void convert(Long value, StrictJsonWriter writer) {
        writer.writeNumber(Long.toString(value.longValue()));
    }
}
