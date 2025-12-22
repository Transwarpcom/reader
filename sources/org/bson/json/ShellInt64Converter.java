package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ShellInt64Converter.class */
class ShellInt64Converter implements Converter<Long> {
    ShellInt64Converter() {
    }

    @Override // org.bson.json.Converter
    public void convert(Long value, StrictJsonWriter writer) {
        if (value.longValue() >= -2147483648L && value.longValue() <= 2147483647L) {
            writer.writeRaw(String.format("NumberLong(%d)", value));
        } else {
            writer.writeRaw(String.format("NumberLong(\"%d\")", value));
        }
    }
}
