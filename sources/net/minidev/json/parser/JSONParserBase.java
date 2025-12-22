package net.minidev.json.parser;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import net.minidev.json.writer.JsonReader;
import net.minidev.json.writer.JsonReaderI;
import okio.internal.BufferKt;

/* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/parser/JSONParserBase.class */
abstract class JSONParserBase {
    protected char c;
    JsonReader base;
    public static final byte EOI = 26;
    protected static final char MAX_STOP = '~';
    private String lastKey;
    protected static boolean[] stopAll = new boolean[126];
    protected static boolean[] stopArray = new boolean[126];
    protected static boolean[] stopKey = new boolean[126];
    protected static boolean[] stopValue = new boolean[126];
    protected static boolean[] stopX = new boolean[126];
    protected final MSB sb = new MSB(15);
    protected Object xo;
    protected String xs;
    protected int pos;
    protected final boolean acceptLeadinZero;
    protected final boolean acceptNaN;
    protected final boolean acceptNonQuote;
    protected final boolean acceptSimpleQuote;
    protected final boolean acceptUselessComma;
    protected final boolean checkTaillingData;
    protected final boolean checkTaillingSpace;
    protected final boolean ignoreControlChar;
    protected final boolean useHiPrecisionFloat;
    protected final boolean useIntegerStorage;
    protected final boolean reject127;
    protected final boolean unrestictBigDigit;

    protected abstract void read() throws IOException;

    protected abstract void readNoEnd() throws ParseException, IOException;

    protected abstract void readNQString(boolean[] zArr) throws IOException;

    protected abstract Object readNumber(boolean[] zArr) throws ParseException, IOException;

    abstract void readS() throws IOException;

    protected abstract void readString() throws ParseException, IOException;

    static {
        boolean[] zArr = stopKey;
        stopKey[26] = true;
        zArr[58] = true;
        boolean[] zArr2 = stopValue;
        boolean[] zArr3 = stopValue;
        stopValue[26] = true;
        zArr3[125] = true;
        zArr2[44] = true;
        boolean[] zArr4 = stopArray;
        boolean[] zArr5 = stopArray;
        stopArray[26] = true;
        zArr5[93] = true;
        zArr4[44] = true;
        stopX[26] = true;
        boolean[] zArr6 = stopAll;
        stopAll[58] = true;
        zArr6[44] = true;
        boolean[] zArr7 = stopAll;
        boolean[] zArr8 = stopAll;
        stopAll[26] = true;
        zArr8[125] = true;
        zArr7[93] = true;
    }

    public JSONParserBase(int permissiveMode) {
        this.acceptNaN = (permissiveMode & 4) > 0;
        this.acceptNonQuote = (permissiveMode & 2) > 0;
        this.acceptSimpleQuote = (permissiveMode & 1) > 0;
        this.ignoreControlChar = (permissiveMode & 8) > 0;
        this.useIntegerStorage = (permissiveMode & 16) > 0;
        this.acceptLeadinZero = (permissiveMode & 32) > 0;
        this.acceptUselessComma = (permissiveMode & 64) > 0;
        this.useHiPrecisionFloat = (permissiveMode & 128) > 0;
        this.checkTaillingData = (permissiveMode & 768) != 768;
        this.checkTaillingSpace = (permissiveMode & 512) == 0;
        this.reject127 = (permissiveMode & 1024) > 0;
        this.unrestictBigDigit = (permissiveMode & 2048) > 0;
    }

    public void checkControleChar() throws ParseException {
        if (this.ignoreControlChar) {
            return;
        }
        int l = this.xs.length();
        for (int i = 0; i < l; i++) {
            char c = this.xs.charAt(i);
            if (c >= 0) {
                if (c <= 31) {
                    throw new ParseException(this.pos + i, 0, Character.valueOf(c));
                }
                if (c == 127 && this.reject127) {
                    throw new ParseException(this.pos + i, 0, Character.valueOf(c));
                }
            }
        }
    }

    public void checkLeadinZero() throws ParseException {
        int len = this.xs.length();
        if (len == 1) {
            return;
        }
        if (len == 2) {
            if (this.xs.equals("00")) {
                throw new ParseException(this.pos, 6, this.xs);
            }
            return;
        }
        char c1 = this.xs.charAt(0);
        char c2 = this.xs.charAt(1);
        if (c1 == '-') {
            char c3 = this.xs.charAt(2);
            if (c2 == '0' && c3 >= '0' && c3 <= '9') {
                throw new ParseException(this.pos, 6, this.xs);
            }
            return;
        }
        if (c1 == '0' && c2 >= '0' && c2 <= '9') {
            throw new ParseException(this.pos, 6, this.xs);
        }
    }

    protected Number extractFloat() throws ParseException, NumberFormatException {
        if (!this.acceptLeadinZero) {
            checkLeadinZero();
        }
        try {
            if (!this.useHiPrecisionFloat) {
                return Float.valueOf(Float.parseFloat(this.xs));
            }
            if (this.xs.length() > 18) {
                BigDecimal big = new BigDecimal(this.xs);
                if (!this.unrestictBigDigit) {
                    double asDouble = Double.parseDouble(this.xs);
                    if (String.valueOf(asDouble).equals(this.xs)) {
                        return Double.valueOf(asDouble);
                    }
                }
                return big;
            }
            return Double.valueOf(Double.parseDouble(this.xs));
        } catch (NumberFormatException e) {
            throw new ParseException(this.pos, 1, this.xs);
        }
    }

    protected <T> T parse(JsonReaderI<T> jsonReaderI) throws ParseException {
        this.pos = -1;
        try {
            read();
            T t = (T) readFirst(jsonReaderI);
            if (this.checkTaillingData) {
                if (!this.checkTaillingSpace) {
                    skipSpace();
                }
                if (this.c != 26) {
                    throw new ParseException(this.pos - 1, 1, Character.valueOf(this.c));
                }
            }
            this.xs = null;
            this.xo = null;
            return t;
        } catch (IOException e) {
            throw new ParseException(this.pos, e);
        }
    }

    protected Number parseNumber(String s) throws ParseException {
        boolean neg;
        int max;
        boolean mustCheck;
        long r;
        boolean isBig;
        int p = 0;
        int l = s.length();
        int max2 = 19;
        if (s.charAt(0) == '-') {
            p = 0 + 1;
            max2 = 19 + 1;
            neg = true;
            if (!this.acceptLeadinZero && l >= 3 && s.charAt(1) == '0') {
                throw new ParseException(this.pos, 6, s);
            }
        } else {
            neg = false;
            if (!this.acceptLeadinZero && l >= 2 && s.charAt(0) == '0') {
                throw new ParseException(this.pos, 6, s);
            }
        }
        if (l < max2) {
            max = l;
            mustCheck = false;
        } else {
            if (l > max2) {
                return new BigInteger(s, 10);
            }
            max = l - 1;
            mustCheck = true;
        }
        long jCharAt = 0;
        while (true) {
            r = jCharAt;
            if (p >= max) {
                break;
            }
            int i = p;
            p++;
            jCharAt = (r * 10) + ('0' - s.charAt(i));
        }
        if (mustCheck) {
            if (r > BufferKt.OVERFLOW_ZONE) {
                isBig = false;
            } else if (r < BufferKt.OVERFLOW_ZONE) {
                isBig = true;
            } else if (neg) {
                isBig = s.charAt(p) > '8';
            } else {
                isBig = s.charAt(p) > '7';
            }
            if (isBig) {
                return new BigInteger(s, 10);
            }
            r = (r * 10) + ('0' - s.charAt(p));
        }
        if (neg) {
            if (this.useIntegerStorage && r >= -2147483648L) {
                return Integer.valueOf((int) r);
            }
            return Long.valueOf(r);
        }
        long r2 = -r;
        if (this.useIntegerStorage && r2 <= 2147483647L) {
            return Integer.valueOf((int) r2);
        }
        return Long.valueOf(r2);
    }

    protected <T> T readArray(JsonReaderI<T> mapper) throws ParseException, IOException {
        Object current = mapper.createArray();
        if (this.c != '[') {
            throw new RuntimeException("Internal Error");
        }
        read();
        boolean needData = false;
        if (this.c == ',' && !this.acceptUselessComma) {
            throw new ParseException(this.pos, 0, Character.valueOf(this.c));
        }
        while (true) {
            switch (this.c) {
                case '\t':
                case '\n':
                case '\r':
                case ' ':
                    read();
                    break;
                case 26:
                    throw new ParseException(this.pos - 1, 3, "EOF");
                case ',':
                    if (needData && !this.acceptUselessComma) {
                        throw new ParseException(this.pos, 0, Character.valueOf(this.c));
                    }
                    read();
                    needData = true;
                    break;
                    break;
                case ':':
                case '}':
                    throw new ParseException(this.pos, 0, Character.valueOf(this.c));
                case ']':
                    if (needData && !this.acceptUselessComma) {
                        throw new ParseException(this.pos, 0, Character.valueOf(this.c));
                    }
                    read();
                    return mapper.convert(current);
                default:
                    mapper.addValue(current, readMain(mapper, stopArray));
                    needData = false;
                    break;
            }
        }
    }

    protected <T> T readFirst(JsonReaderI<T> jsonReaderI) throws ParseException, IOException {
        while (true) {
            switch (this.c) {
                case '\t':
                case '\n':
                case '\r':
                case ' ':
                    read();
                case 11:
                case '\f':
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case '!':
                case '#':
                case '$':
                case '%':
                case '&':
                case '(':
                case ')':
                case '*':
                case '+':
                case ',':
                case '.':
                case '/':
                case ';':
                case '<':
                case '=':
                case '>':
                case '?':
                case '@':
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                case '\\':
                case '^':
                case '_':
                case '`':
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                case '|':
                default:
                    readNQString(stopX);
                    if (!this.acceptNonQuote) {
                        throw new ParseException(this.pos, 1, this.xs);
                    }
                    return jsonReaderI.convert(this.xs);
                case '\"':
                case '\'':
                    readString();
                    return jsonReaderI.convert(this.xs);
                case '-':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    this.xo = readNumber(stopX);
                    return jsonReaderI.convert(this.xo);
                case ':':
                case ']':
                case '}':
                    throw new ParseException(this.pos, 0, Character.valueOf(this.c));
                case 'N':
                    readNQString(stopX);
                    if (!this.acceptNaN) {
                        throw new ParseException(this.pos, 1, this.xs);
                    }
                    if ("NaN".equals(this.xs)) {
                        return jsonReaderI.convert(Float.valueOf(Float.NaN));
                    }
                    if (!this.acceptNonQuote) {
                        throw new ParseException(this.pos, 1, this.xs);
                    }
                    return jsonReaderI.convert(this.xs);
                case '[':
                    return (T) readArray(jsonReaderI);
                case 'f':
                    readNQString(stopX);
                    if ("false".equals(this.xs)) {
                        return jsonReaderI.convert(Boolean.FALSE);
                    }
                    if (!this.acceptNonQuote) {
                        throw new ParseException(this.pos, 1, this.xs);
                    }
                    return jsonReaderI.convert(this.xs);
                case 'n':
                    readNQString(stopX);
                    if ("null".equals(this.xs)) {
                        return null;
                    }
                    if (!this.acceptNonQuote) {
                        throw new ParseException(this.pos, 1, this.xs);
                    }
                    return jsonReaderI.convert(this.xs);
                case 't':
                    readNQString(stopX);
                    if ("true".equals(this.xs)) {
                        return jsonReaderI.convert(Boolean.TRUE);
                    }
                    if (!this.acceptNonQuote) {
                        throw new ParseException(this.pos, 1, this.xs);
                    }
                    return jsonReaderI.convert(this.xs);
                case '{':
                    return (T) readObject(jsonReaderI);
            }
        }
    }

    protected Object readMain(JsonReaderI<?> mapper, boolean[] stop) throws ParseException, IOException {
        while (true) {
            switch (this.c) {
                case '\t':
                case '\n':
                case '\r':
                case ' ':
                    read();
                case 11:
                case '\f':
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case '!':
                case '#':
                case '$':
                case '%':
                case '&':
                case '(':
                case ')':
                case '*':
                case '+':
                case ',':
                case '.':
                case '/':
                case ';':
                case '<':
                case '=':
                case '>':
                case '?':
                case '@':
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                case '\\':
                case '^':
                case '_':
                case '`':
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                case '|':
                default:
                    readNQString(stop);
                    if (!this.acceptNonQuote) {
                        throw new ParseException(this.pos, 1, this.xs);
                    }
                    return this.xs;
                case '\"':
                case '\'':
                    readString();
                    return this.xs;
                case '-':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    return readNumber(stop);
                case ':':
                case ']':
                case '}':
                    throw new ParseException(this.pos, 0, Character.valueOf(this.c));
                case 'N':
                    readNQString(stop);
                    if (!this.acceptNaN) {
                        throw new ParseException(this.pos, 1, this.xs);
                    }
                    if ("NaN".equals(this.xs)) {
                        return Float.valueOf(Float.NaN);
                    }
                    if (!this.acceptNonQuote) {
                        throw new ParseException(this.pos, 1, this.xs);
                    }
                    return this.xs;
                case '[':
                    return readArray(mapper.startArray(this.lastKey));
                case 'f':
                    readNQString(stop);
                    if ("false".equals(this.xs)) {
                        return Boolean.FALSE;
                    }
                    if (!this.acceptNonQuote) {
                        throw new ParseException(this.pos, 1, this.xs);
                    }
                    return this.xs;
                case 'n':
                    readNQString(stop);
                    if ("null".equals(this.xs)) {
                        return null;
                    }
                    if (!this.acceptNonQuote) {
                        throw new ParseException(this.pos, 1, this.xs);
                    }
                    return this.xs;
                case 't':
                    readNQString(stop);
                    if ("true".equals(this.xs)) {
                        return Boolean.TRUE;
                    }
                    if (!this.acceptNonQuote) {
                        throw new ParseException(this.pos, 1, this.xs);
                    }
                    return this.xs;
                case '{':
                    return readObject(mapper.startObject(this.lastKey));
            }
        }
    }

    protected <T> T readObject(JsonReaderI<T> mapper) throws ParseException, IOException {
        if (this.c != '{') {
            throw new RuntimeException("Internal Error");
        }
        Object current = mapper.createObject();
        boolean needData = false;
        boolean acceptData = true;
        while (true) {
            read();
            switch (this.c) {
                case '\t':
                case '\n':
                case '\r':
                case ' ':
                    break;
                case '\"':
                case '\'':
                default:
                    if (this.c == '\"' || this.c == '\'') {
                        readString();
                    } else {
                        readNQString(stopKey);
                        if (!this.acceptNonQuote) {
                            throw new ParseException(this.pos, 1, this.xs);
                        }
                    }
                    String key = this.xs;
                    if (!acceptData) {
                        throw new ParseException(this.pos, 1, key);
                    }
                    skipSpace();
                    if (this.c != ':') {
                        if (this.c == 26) {
                            throw new ParseException(this.pos - 1, 3, null);
                        }
                        throw new ParseException(this.pos - 1, 0, Character.valueOf(this.c));
                    }
                    readNoEnd();
                    this.lastKey = key;
                    Object value = readMain(mapper, stopValue);
                    mapper.setValue(current, key, value);
                    this.lastKey = null;
                    skipSpace();
                    if (this.c == '}') {
                        read();
                        return mapper.convert(current);
                    }
                    if (this.c == 26) {
                        throw new ParseException(this.pos - 1, 3, null);
                    }
                    if (this.c == ',') {
                        needData = true;
                        acceptData = true;
                        break;
                    } else {
                        throw new ParseException(this.pos - 1, 1, Character.valueOf(this.c));
                    }
                case ',':
                    if (needData && !this.acceptUselessComma) {
                        throw new ParseException(this.pos, 0, Character.valueOf(this.c));
                    }
                    needData = true;
                    acceptData = true;
                    break;
                    break;
                case ':':
                case '[':
                case ']':
                case '{':
                    throw new ParseException(this.pos, 0, Character.valueOf(this.c));
                case '}':
                    if (needData && !this.acceptUselessComma) {
                        throw new ParseException(this.pos, 0, Character.valueOf(this.c));
                    }
                    read();
                    return mapper.convert(current);
            }
        }
    }

    protected void readString2() throws ParseException, IOException {
        char sep = this.c;
        while (true) {
            read();
            switch (this.c) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case '\b':
                case '\t':
                case '\n':
                case 11:
                case '\f':
                case '\r':
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                    if (!this.ignoreControlChar) {
                        throw new ParseException(this.pos, 0, Character.valueOf(this.c));
                    }
                    continue;
                case 26:
                    throw new ParseException(this.pos - 1, 3, null);
                case '\"':
                case '\'':
                    if (sep == this.c) {
                        read();
                        this.xs = this.sb.toString();
                        return;
                    } else {
                        this.sb.append(this.c);
                        continue;
                    }
                case '\\':
                    read();
                    switch (this.c) {
                        case '\"':
                            this.sb.append('\"');
                            break;
                        case '\'':
                            this.sb.append('\'');
                            break;
                        case '/':
                            this.sb.append('/');
                            break;
                        case '\\':
                            this.sb.append('\\');
                            break;
                        case 'b':
                            this.sb.append('\b');
                            break;
                        case 'f':
                            this.sb.append('\f');
                            break;
                        case 'n':
                            this.sb.append('\n');
                            break;
                        case 'r':
                            this.sb.append('\r');
                            break;
                        case 't':
                            this.sb.append('\t');
                            break;
                        case 'u':
                            this.sb.append(readUnicode(4));
                            break;
                        case 'x':
                            this.sb.append(readUnicode(2));
                            break;
                        default:
                            continue;
                    }
                case 127:
                    if (this.ignoreControlChar) {
                        continue;
                    } else if (this.reject127) {
                        throw new ParseException(this.pos, 0, Character.valueOf(this.c));
                    }
                    break;
            }
            this.sb.append(this.c);
        }
    }

    protected char readUnicode(int totalChars) throws ParseException, IOException {
        int i;
        int i2;
        int value = 0;
        for (int i3 = 0; i3 < totalChars; i3++) {
            int value2 = value * 16;
            read();
            if (this.c <= '9' && this.c >= '0') {
                i = value2;
                i2 = this.c - '0';
            } else if (this.c <= 'F' && this.c >= 'A') {
                i = value2;
                i2 = (this.c - 'A') + 10;
            } else if (this.c >= 'a' && this.c <= 'f') {
                i = value2;
                i2 = (this.c - 'a') + 10;
            } else {
                if (this.c == 26) {
                    throw new ParseException(this.pos, 3, "EOF");
                }
                throw new ParseException(this.pos, 4, Character.valueOf(this.c));
            }
            value = i + i2;
        }
        return (char) value;
    }

    protected void skipDigits() throws IOException {
        while (this.c >= '0' && this.c <= '9') {
            readS();
        }
    }

    protected void skipNQString(boolean[] stop) throws IOException {
        while (this.c != 26) {
            if (this.c >= 0 && this.c < '~' && stop[this.c]) {
                return;
            } else {
                readS();
            }
        }
    }

    protected void skipSpace() throws IOException {
        while (this.c <= ' ' && this.c != 26) {
            readS();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/parser/JSONParserBase$MSB.class */
    public static class MSB {
        char[] b;
        int p = -1;

        public MSB(int size) {
            this.b = new char[size];
        }

        public void append(char c) {
            this.p++;
            if (this.b.length <= this.p) {
                char[] t = new char[(this.b.length * 2) + 1];
                System.arraycopy(this.b, 0, t, 0, this.b.length);
                this.b = t;
            }
            this.b[this.p] = c;
        }

        public void append(int c) {
            this.p++;
            if (this.b.length <= this.p) {
                char[] t = new char[(this.b.length * 2) + 1];
                System.arraycopy(this.b, 0, t, 0, this.b.length);
                this.b = t;
            }
            this.b[this.p] = (char) c;
        }

        public String toString() {
            return new String(this.b, 0, this.p + 1);
        }

        public void clear() {
            this.p = -1;
        }
    }
}
