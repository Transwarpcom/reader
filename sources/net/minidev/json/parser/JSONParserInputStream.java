package net.minidev.json.parser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import net.minidev.json.writer.JsonReaderI;

/* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/parser/JSONParserInputStream.class */
class JSONParserInputStream extends JSONParserReader {
    public JSONParserInputStream(int permissiveMode) {
        super(permissiveMode);
    }

    public Object parse(InputStream in) throws ParseException, UnsupportedEncodingException {
        InputStreamReader i2 = new InputStreamReader(in, "utf8");
        return super.parse(i2);
    }

    public <T> T parse(InputStream inputStream, JsonReaderI<T> jsonReaderI) throws ParseException, UnsupportedEncodingException {
        return (T) super.parse(new InputStreamReader(inputStream, "utf8"), jsonReaderI);
    }
}
