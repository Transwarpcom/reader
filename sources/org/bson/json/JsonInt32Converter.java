package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonInt32Converter.class */
class JsonInt32Converter implements Converter<Integer> {
    JsonInt32Converter() {
    }

    @Override // org.bson.json.Converter
    public void convert(Integer value, StrictJsonWriter writer) {
        writer.writeNumber(Integer.toString(value.intValue()));
    }
}
