package net.minidev.json.reader;

import java.io.IOException;
import net.minidev.json.JSONStyle;

/* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/reader/JsonWriterI.class */
public interface JsonWriterI<T> {
    <E extends T> void writeJSONString(E e, Appendable appendable, JSONStyle jSONStyle) throws IOException;
}
