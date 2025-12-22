package org.bson.json;

import cn.hutool.core.text.StrPool;
import org.bson.BsonRegularExpression;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonScanner.class */
class JsonScanner {
    private final JsonBuffer buffer;

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonScanner$NumberState.class */
    private enum NumberState {
        SAW_LEADING_MINUS,
        SAW_LEADING_ZERO,
        SAW_INTEGER_DIGITS,
        SAW_DECIMAL_POINT,
        SAW_FRACTION_DIGITS,
        SAW_EXPONENT_LETTER,
        SAW_EXPONENT_SIGN,
        SAW_EXPONENT_DIGITS,
        SAW_MINUS_I,
        DONE,
        INVALID
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonScanner$RegularExpressionState.class */
    private enum RegularExpressionState {
        IN_PATTERN,
        IN_ESCAPE_SEQUENCE,
        IN_OPTIONS,
        DONE,
        INVALID
    }

    JsonScanner(JsonBuffer buffer) {
        this.buffer = buffer;
    }

    JsonScanner(String json) {
        this(new JsonBuffer(json));
    }

    public void setBufferPosition(int newPosition) {
        this.buffer.setPosition(newPosition);
    }

    public int getBufferPosition() {
        return this.buffer.getPosition();
    }

    public JsonToken nextToken() {
        int c;
        int i = this.buffer.read();
        while (true) {
            c = i;
            if (c == -1 || !Character.isWhitespace(c)) {
                break;
            }
            i = this.buffer.read();
        }
        if (c == -1) {
            return new JsonToken(JsonTokenType.END_OF_FILE, "<eof>");
        }
        switch (c) {
            case 34:
            case 39:
                return scanString((char) c);
            case 40:
                return new JsonToken(JsonTokenType.LEFT_PAREN, "(");
            case 41:
                return new JsonToken(JsonTokenType.RIGHT_PAREN, ")");
            case 44:
                return new JsonToken(JsonTokenType.COMMA, ",");
            case 47:
                return scanRegularExpression();
            case 58:
                return new JsonToken(JsonTokenType.COLON, ":");
            case 91:
                return new JsonToken(JsonTokenType.BEGIN_ARRAY, "[");
            case 93:
                return new JsonToken(JsonTokenType.END_ARRAY, "]");
            case 123:
                return new JsonToken(JsonTokenType.BEGIN_OBJECT, StrPool.DELIM_START);
            case 125:
                return new JsonToken(JsonTokenType.END_OBJECT, "}");
            default:
                if (c == 45 || Character.isDigit(c)) {
                    return scanNumber((char) c);
                }
                if (c == 36 || c == 95 || Character.isLetter(c)) {
                    return scanUnquotedString();
                }
                int position = this.buffer.getPosition();
                this.buffer.unread(c);
                throw new JsonParseException("Invalid JSON input. Position: %d. Character: '%c'.", Integer.valueOf(position), Integer.valueOf(c));
        }
    }

    private JsonToken scanRegularExpression() {
        int start = this.buffer.getPosition() - 1;
        int options = -1;
        RegularExpressionState state = RegularExpressionState.IN_PATTERN;
        while (true) {
            int c = this.buffer.read();
            switch (state) {
                case IN_PATTERN:
                    switch (c) {
                        case -1:
                            state = RegularExpressionState.INVALID;
                            break;
                        case 47:
                            state = RegularExpressionState.IN_OPTIONS;
                            options = this.buffer.getPosition();
                            break;
                        case 92:
                            state = RegularExpressionState.IN_ESCAPE_SEQUENCE;
                            break;
                        default:
                            state = RegularExpressionState.IN_PATTERN;
                            break;
                    }
                case IN_ESCAPE_SEQUENCE:
                    state = RegularExpressionState.IN_PATTERN;
                    break;
                case IN_OPTIONS:
                    switch (c) {
                        case -1:
                        case 41:
                        case 44:
                        case 93:
                        case 125:
                            state = RegularExpressionState.DONE;
                            break;
                        case 105:
                        case 109:
                        case 115:
                        case 120:
                            state = RegularExpressionState.IN_OPTIONS;
                            break;
                        default:
                            if (Character.isWhitespace(c)) {
                                state = RegularExpressionState.DONE;
                                break;
                            } else {
                                state = RegularExpressionState.INVALID;
                                break;
                            }
                    }
            }
            switch (state) {
                case DONE:
                    this.buffer.unread(c);
                    int end = this.buffer.getPosition();
                    BsonRegularExpression regex = new BsonRegularExpression(this.buffer.substring(start + 1, options - 1), this.buffer.substring(options, end));
                    return new JsonToken(JsonTokenType.REGULAR_EXPRESSION, regex);
                case INVALID:
                    throw new JsonParseException("Invalid JSON regular expression. Position: %d.", Integer.valueOf(this.buffer.getPosition()));
            }
        }
    }

    private JsonToken scanUnquotedString() {
        int start = this.buffer.getPosition() - 1;
        int i = this.buffer.read();
        while (true) {
            int c = i;
            if (c == 36 || c == 95 || Character.isLetterOrDigit(c)) {
                i = this.buffer.read();
            } else {
                this.buffer.unread(c);
                String lexeme = this.buffer.substring(start, this.buffer.getPosition());
                return new JsonToken(JsonTokenType.UNQUOTED_STRING, lexeme);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:112:0x03fc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0406 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:? A[LOOP:0: B:8:0x0042->B:114:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.bson.json.JsonToken scanNumber(char r7) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 1143
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bson.json.JsonScanner.scanNumber(char):org.bson.json.JsonToken");
    }

    private JsonToken scanString(char quoteCharacter) {
        int c;
        StringBuilder sb = new StringBuilder();
        do {
            c = this.buffer.read();
            switch (c) {
                case 92:
                    c = this.buffer.read();
                    switch (c) {
                        case 34:
                            sb.append('\"');
                            break;
                        case 39:
                            sb.append('\'');
                            break;
                        case 47:
                            sb.append('/');
                            break;
                        case 92:
                            sb.append('\\');
                            break;
                        case 98:
                            sb.append('\b');
                            break;
                        case 102:
                            sb.append('\f');
                            break;
                        case 110:
                            sb.append('\n');
                            break;
                        case 114:
                            sb.append('\r');
                            break;
                        case 116:
                            sb.append('\t');
                            break;
                        case 117:
                            int u1 = this.buffer.read();
                            int u2 = this.buffer.read();
                            int u3 = this.buffer.read();
                            int u4 = this.buffer.read();
                            if (u4 != -1) {
                                String hex = new String(new char[]{(char) u1, (char) u2, (char) u3, (char) u4});
                                sb.append((char) Integer.parseInt(hex, 16));
                                break;
                            }
                            break;
                        default:
                            throw new JsonParseException("Invalid escape sequence in JSON string '\\%c'.", Integer.valueOf(c));
                    }
                default:
                    if (c == quoteCharacter) {
                        return new JsonToken(JsonTokenType.STRING, sb.toString());
                    }
                    if (c != -1) {
                        sb.append((char) c);
                        break;
                    }
                    break;
            }
        } while (c != -1);
        throw new JsonParseException("End of file in JSON string.");
    }
}
