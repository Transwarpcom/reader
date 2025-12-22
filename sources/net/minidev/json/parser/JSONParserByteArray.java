package net.minidev.json.parser;

import java.nio.charset.StandardCharsets;
import net.minidev.json.JSONValue;
import net.minidev.json.writer.JsonReaderI;

/* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/parser/JSONParserByteArray.class */
class JSONParserByteArray extends JSONParserMemory {
    private byte[] in;

    public JSONParserByteArray(int permissiveMode) {
        super(permissiveMode);
    }

    public Object parse(byte[] in) throws ParseException {
        return parse(in, JSONValue.defaultReader.DEFAULT);
    }

    public <T> T parse(byte[] bArr, JsonReaderI<T> jsonReaderI) throws ParseException {
        this.base = jsonReaderI.base;
        this.in = bArr;
        this.len = bArr.length;
        return (T) parse(jsonReaderI);
    }

    @Override // net.minidev.json.parser.JSONParserMemory
    protected void extractString(int beginIndex, int endIndex) {
        this.xs = new String(this.in, beginIndex, endIndex - beginIndex, StandardCharsets.UTF_8);
    }

    @Override // net.minidev.json.parser.JSONParserMemory
    protected void extractStringTrim(int start, int stop) {
        byte[] val = this.in;
        while (start < stop && val[start] <= 32) {
            start++;
        }
        while (start < stop && val[stop - 1] <= 32) {
            stop--;
        }
        this.xs = new String(this.in, start, stop - start, StandardCharsets.UTF_8);
    }

    @Override // net.minidev.json.parser.JSONParserMemory
    protected int indexOf(char c, int pos) {
        for (int i = pos; i < this.len; i++) {
            if (this.in[i] == ((byte) c)) {
                return i;
            }
        }
        return -1;
    }

    @Override // net.minidev.json.parser.JSONParserBase
    protected void read() {
        int i = this.pos + 1;
        this.pos = i;
        if (i >= this.len) {
            this.c = (char) 26;
        } else {
            this.c = (char) this.in[this.pos];
        }
    }

    @Override // net.minidev.json.parser.JSONParserBase
    protected void readS() {
        int i = this.pos + 1;
        this.pos = i;
        if (i >= this.len) {
            this.c = (char) 26;
        } else {
            this.c = (char) this.in[this.pos];
        }
    }

    @Override // net.minidev.json.parser.JSONParserBase
    protected void readNoEnd() throws ParseException {
        int i = this.pos + 1;
        this.pos = i;
        if (i >= this.len) {
            this.c = (char) 26;
            throw new ParseException(this.pos - 1, 3, "EOF");
        }
        this.c = (char) this.in[this.pos];
    }
}
