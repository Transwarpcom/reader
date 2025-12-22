package net.minidev.json.parser;

import net.minidev.json.JSONValue;
import net.minidev.json.writer.JsonReaderI;

/* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/parser/JSONParserString.class */
class JSONParserString extends JSONParserMemory {
    private String in;

    public JSONParserString(int permissiveMode) {
        super(permissiveMode);
    }

    public Object parse(String in) throws ParseException {
        return parse(in, JSONValue.defaultReader.DEFAULT);
    }

    public <T> T parse(String str, JsonReaderI<T> jsonReaderI) throws ParseException {
        this.base = jsonReaderI.base;
        this.in = str;
        this.len = str.length();
        return (T) parse(jsonReaderI);
    }

    @Override // net.minidev.json.parser.JSONParserMemory
    protected void extractString(int beginIndex, int endIndex) {
        this.xs = this.in.substring(beginIndex, endIndex);
    }

    @Override // net.minidev.json.parser.JSONParserMemory
    protected void extractStringTrim(int start, int stop) {
        while (start < stop - 1 && Character.isWhitespace(this.in.charAt(start))) {
            start++;
        }
        while (stop - 1 > start && Character.isWhitespace(this.in.charAt(stop - 1))) {
            stop--;
        }
        extractString(start, stop);
    }

    @Override // net.minidev.json.parser.JSONParserMemory
    protected int indexOf(char c, int pos) {
        return this.in.indexOf(c, pos);
    }

    @Override // net.minidev.json.parser.JSONParserBase
    protected void read() {
        int i = this.pos + 1;
        this.pos = i;
        if (i >= this.len) {
            this.c = (char) 26;
        } else {
            this.c = this.in.charAt(this.pos);
        }
    }

    @Override // net.minidev.json.parser.JSONParserBase
    protected void readS() {
        int i = this.pos + 1;
        this.pos = i;
        if (i >= this.len) {
            this.c = (char) 26;
        } else {
            this.c = this.in.charAt(this.pos);
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
        this.c = this.in.charAt(this.pos);
    }
}
