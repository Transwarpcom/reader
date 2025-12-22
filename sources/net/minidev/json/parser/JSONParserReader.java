package net.minidev.json.parser;

import java.io.IOException;
import java.io.Reader;
import net.minidev.json.JSONValue;
import net.minidev.json.writer.JsonReaderI;

/* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/parser/JSONParserReader.class */
class JSONParserReader extends JSONParserStream {
    private Reader in;

    public JSONParserReader(int permissiveMode) {
        super(permissiveMode);
    }

    public Object parse(Reader in) throws ParseException {
        return parse(in, JSONValue.defaultReader.DEFAULT);
    }

    public <T> T parse(Reader reader, JsonReaderI<T> jsonReaderI) throws ParseException {
        this.base = jsonReaderI.base;
        this.in = reader;
        return (T) super.parse(jsonReaderI);
    }

    @Override // net.minidev.json.parser.JSONParserBase
    protected void read() throws IOException {
        int i = this.in.read();
        this.c = i == -1 ? (char) 26 : (char) i;
        this.pos++;
    }

    @Override // net.minidev.json.parser.JSONParserBase
    protected void readS() throws IOException {
        this.sb.append(this.c);
        int i = this.in.read();
        if (i == -1) {
            this.c = (char) 26;
        } else {
            this.c = (char) i;
            this.pos++;
        }
    }

    @Override // net.minidev.json.parser.JSONParserBase
    protected void readNoEnd() throws ParseException, IOException {
        int i = this.in.read();
        if (i == -1) {
            throw new ParseException(this.pos - 1, 3, "EOF");
        }
        this.c = (char) i;
    }
}
