package org.bson.json;

import org.bson.types.Decimal128;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ShellDecimal128Converter.class */
class ShellDecimal128Converter implements Converter<Decimal128> {
    ShellDecimal128Converter() {
    }

    @Override // org.bson.json.Converter
    public void convert(Decimal128 value, StrictJsonWriter writer) {
        writer.writeRaw(String.format("NumberDecimal(\"%s\")", value.toString()));
    }
}
