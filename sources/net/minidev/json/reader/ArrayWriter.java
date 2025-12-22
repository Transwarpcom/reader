package net.minidev.json.reader;

import java.io.IOException;
import net.minidev.json.JSONStyle;
import net.minidev.json.JSONValue;

/* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/reader/ArrayWriter.class */
public class ArrayWriter implements JsonWriterI<Object> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // net.minidev.json.reader.JsonWriterI
    public <E> void writeJSONString(E e, Appendable out, JSONStyle compression) throws IOException {
        compression.arrayStart(out);
        boolean needSep = false;
        for (Object o : (Object[]) e) {
            if (needSep) {
                compression.objectNext(out);
            } else {
                needSep = true;
            }
            JSONValue.writeJSONString(o, out, compression);
        }
        compression.arrayStop(out);
    }
}
