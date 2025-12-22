package org.mozilla.javascript;

import io.vertx.core.cli.UsageMessageFormatter;
import java.io.IOException;
import java.io.Reader;
import org.mozilla.javascript.Token;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/TokenStream.class */
class TokenStream {
    private static final int EOF_CHAR = -1;
    private static final char BYTE_ORDER_MARK = 65279;
    private boolean dirtyLine;
    String regExpFlags;
    private double number;
    private boolean isBinary;
    private boolean isOldOctal;
    private boolean isOctal;
    private boolean isHex;
    private int quoteChar;
    private int stringBufferTop;
    private int ungetCursor;
    int lineno;
    private String sourceString;
    private Reader sourceReader;
    private char[] sourceBuffer;
    private int sourceEnd;
    int sourceCursor;
    int cursor;
    int tokenBeg;
    int tokenEnd;
    Token.CommentType commentType;
    private boolean xmlIsAttribute;
    private boolean xmlIsTagContent;
    private int xmlOpenTagsCount;
    private Parser parser;
    static final /* synthetic */ boolean $assertionsDisabled;
    private StringBuilder rawString = new StringBuilder();
    private String string = "";
    private char[] stringBuffer = new char[128];
    private ObjToIntMap allStrings = new ObjToIntMap(50);
    private final int[] ungetBuffer = new int[3];
    private boolean hitEOF = false;
    private int lineStart = 0;
    private int lineEndChar = -1;
    private String commentPrefix = "";
    private int commentCursor = -1;

    static {
        $assertionsDisabled = !TokenStream.class.desiredAssertionStatus();
    }

    TokenStream(Parser parser, Reader sourceReader, String sourceString, int lineno) throws RuntimeException {
        this.parser = parser;
        this.lineno = lineno;
        if (sourceReader != null) {
            if (sourceString != null) {
                Kit.codeBug();
            }
            this.sourceReader = sourceReader;
            this.sourceBuffer = new char[512];
            this.sourceEnd = 0;
        } else {
            if (sourceString == null) {
                Kit.codeBug();
            }
            this.sourceString = sourceString;
            this.sourceEnd = sourceString.length();
        }
        this.cursor = 0;
        this.sourceCursor = 0;
    }

    String tokenToString(int token) {
        return "";
    }

    static boolean isKeyword(String s, int version, boolean isStrict) {
        return 0 != stringToKeyword(s, version, isStrict);
    }

    private static int stringToKeyword(String name, int version, boolean isStrict) {
        if (version < 200) {
            return stringToKeywordForJS(name);
        }
        return stringToKeywordForES(name, isStrict);
    }

    private static int stringToKeywordForJS(String name) {
        int id;
        switch (name) {
            case "break":
                id = 121;
                break;
            case "case":
                id = 116;
                break;
            case "continue":
                id = 122;
                break;
            case "default":
                id = 117;
                break;
            case "delete":
                id = 31;
                break;
            case "do":
                id = 119;
                break;
            case "else":
                id = 114;
                break;
            case "export":
                id = 128;
                break;
            case "false":
                id = 44;
                break;
            case "for":
                id = 120;
                break;
            case "function":
                id = 110;
                break;
            case "if":
                id = 113;
                break;
            case "in":
                id = 52;
                break;
            case "let":
                id = 154;
                break;
            case "new":
                id = 30;
                break;
            case "null":
                id = 42;
                break;
            case "return":
                id = 4;
                break;
            case "switch":
                id = 115;
                break;
            case "this":
                id = 43;
                break;
            case "true":
                id = 45;
                break;
            case "typeof":
                id = 32;
                break;
            case "var":
                id = 123;
                break;
            case "void":
                id = 127;
                break;
            case "while":
                id = 118;
                break;
            case "with":
                id = 124;
                break;
            case "yield":
                id = 73;
                break;
            case "abstract":
                id = 128;
                break;
            case "boolean":
                id = 128;
                break;
            case "byte":
                id = 128;
                break;
            case "catch":
                id = 125;
                break;
            case "char":
                id = 128;
                break;
            case "class":
                id = 128;
                break;
            case "const":
                id = 155;
                break;
            case "debugger":
                id = 161;
                break;
            case "double":
                id = 128;
                break;
            case "enum":
                id = 128;
                break;
            case "extends":
                id = 128;
                break;
            case "final":
                id = 128;
                break;
            case "finally":
                id = 126;
                break;
            case "float":
                id = 128;
                break;
            case "goto":
                id = 128;
                break;
            case "implements":
                id = 128;
                break;
            case "import":
                id = 128;
                break;
            case "instanceof":
                id = 53;
                break;
            case "int":
                id = 128;
                break;
            case "interface":
                id = 128;
                break;
            case "long":
                id = 128;
                break;
            case "native":
                id = 128;
                break;
            case "package":
                id = 128;
                break;
            case "private":
                id = 128;
                break;
            case "protected":
                id = 128;
                break;
            case "public":
                id = 128;
                break;
            case "short":
                id = 128;
                break;
            case "static":
                id = 128;
                break;
            case "super":
                id = 128;
                break;
            case "synchronized":
                id = 128;
                break;
            case "throw":
                id = 50;
                break;
            case "throws":
                id = 128;
                break;
            case "transient":
                id = 128;
                break;
            case "try":
                id = 82;
                break;
            case "volatile":
                id = 128;
                break;
            default:
                id = 0;
                break;
        }
        if (id == 0) {
            return 0;
        }
        return id & 255;
    }

    private static int stringToKeywordForES(String name, boolean isStrict) {
        int id;
        id = 0;
        switch (name) {
            case "break":
                id = 121;
                break;
            case "case":
                id = 116;
                break;
            case "catch":
                id = 125;
                break;
            case "class":
                id = 128;
                break;
            case "const":
                id = 155;
                break;
            case "continue":
                id = 122;
                break;
            case "debugger":
                id = 161;
                break;
            case "default":
                id = 117;
                break;
            case "delete":
                id = 31;
                break;
            case "do":
                id = 119;
                break;
            case "else":
                id = 114;
                break;
            case "export":
                id = 128;
                break;
            case "extends":
                id = 128;
                break;
            case "finally":
                id = 126;
                break;
            case "for":
                id = 120;
                break;
            case "function":
                id = 110;
                break;
            case "if":
                id = 113;
                break;
            case "import":
                id = 128;
                break;
            case "in":
                id = 52;
                break;
            case "instanceof":
                id = 53;
                break;
            case "new":
                id = 30;
                break;
            case "return":
                id = 4;
                break;
            case "super":
                id = 128;
                break;
            case "switch":
                id = 115;
                break;
            case "this":
                id = 43;
                break;
            case "throw":
                id = 50;
                break;
            case "try":
                id = 82;
                break;
            case "typeof":
                id = 32;
                break;
            case "var":
                id = 123;
                break;
            case "void":
                id = 127;
                break;
            case "while":
                id = 118;
                break;
            case "with":
                id = 124;
                break;
            case "yield":
                id = 73;
                break;
            case "await":
                id = 128;
                break;
            case "enum":
                id = 128;
                break;
            case "implements":
                if (isStrict) {
                    id = 128;
                    break;
                }
                break;
            case "interface":
                if (isStrict) {
                    id = 128;
                    break;
                }
                break;
            case "package":
                if (isStrict) {
                    id = 128;
                    break;
                }
                break;
            case "private":
                if (isStrict) {
                    id = 128;
                    break;
                }
                break;
            case "protected":
                if (isStrict) {
                    id = 128;
                    break;
                }
                break;
            case "public":
                if (isStrict) {
                    id = 128;
                    break;
                }
                break;
            case "false":
                id = 44;
                break;
            case "null":
                id = 42;
                break;
            case "true":
                id = 45;
                break;
            case "let":
                id = 154;
                break;
            case "static":
                if (isStrict) {
                    id = 128;
                    break;
                }
                break;
            default:
                id = 0;
                break;
        }
        if (id == 0) {
            return 0;
        }
        return id & 255;
    }

    final String getSourceString() {
        return this.sourceString;
    }

    final int getLineno() {
        return this.lineno;
    }

    final String getString() {
        return this.string;
    }

    final char getQuoteChar() {
        return (char) this.quoteChar;
    }

    final double getNumber() {
        return this.number;
    }

    final boolean isNumberBinary() {
        return this.isBinary;
    }

    final boolean isNumberOldOctal() {
        return this.isOldOctal;
    }

    final boolean isNumberOctal() {
        return this.isOctal;
    }

    final boolean isNumberHex() {
        return this.isHex;
    }

    final boolean eof() {
        return this.hitEOF;
    }

    final int getToken() throws IOException {
        int c;
        boolean identifierStart;
        double dval;
        int c2;
        int c3;
        do {
            c = getChar();
            if (c == -1) {
                this.tokenBeg = this.cursor - 1;
                this.tokenEnd = this.cursor;
                return 0;
            }
            if (c == 10) {
                this.dirtyLine = false;
                this.tokenBeg = this.cursor - 1;
                this.tokenEnd = this.cursor;
                return 1;
            }
        } while (isJSSpace(c));
        if (c != 45) {
            this.dirtyLine = true;
        }
        this.tokenBeg = this.cursor - 1;
        this.tokenEnd = this.cursor;
        if (c == 64) {
            return 148;
        }
        boolean isUnicodeEscapeStart = false;
        if (c == 92) {
            c = getChar();
            if (c == 117) {
                identifierStart = true;
                isUnicodeEscapeStart = true;
                this.stringBufferTop = 0;
            } else {
                identifierStart = false;
                ungetChar(c);
                c = 92;
            }
        } else {
            identifierStart = Character.isJavaIdentifierStart((char) c);
            if (identifierStart) {
                this.stringBufferTop = 0;
                addToString(c);
            }
        }
        if (identifierStart) {
            boolean containsEscape = isUnicodeEscapeStart;
            while (true) {
                if (isUnicodeEscapeStart) {
                    int escapeVal = 0;
                    for (int i = 0; i != 4; i++) {
                        escapeVal = Kit.xDigitToInt(getChar(), escapeVal);
                        if (escapeVal < 0) {
                            break;
                        }
                    }
                    if (escapeVal < 0) {
                        this.parser.addError("msg.invalid.escape");
                        return -1;
                    }
                    addToString(escapeVal);
                    isUnicodeEscapeStart = false;
                } else {
                    c3 = getChar();
                    if (c3 == 92) {
                        int c4 = getChar();
                        if (c4 == 117) {
                            isUnicodeEscapeStart = true;
                            containsEscape = true;
                        } else {
                            this.parser.addError("msg.illegal.character", c4);
                            return -1;
                        }
                    } else {
                        if (c3 == -1 || c3 == 65279 || !Character.isJavaIdentifierPart((char) c3)) {
                            break;
                        }
                        addToString(c3);
                    }
                }
            }
            ungetChar(c3);
            String str = getStringFromBuffer();
            if (!containsEscape) {
                int result = stringToKeyword(str, this.parser.compilerEnv.getLanguageVersion(), this.parser.inUseStrictDirective());
                if (result != 0) {
                    if ((result == 154 || result == 73) && this.parser.compilerEnv.getLanguageVersion() < 170) {
                        this.string = result == 154 ? "let" : "yield";
                        result = 39;
                    }
                    this.string = (String) this.allStrings.intern(str);
                    if (result != 128) {
                        return result;
                    }
                    if (this.parser.compilerEnv.getLanguageVersion() >= 200) {
                        return result;
                    }
                    if (!this.parser.compilerEnv.isReservedKeywordAsIdentifier()) {
                        return result;
                    }
                }
            } else if (isKeyword(str, this.parser.compilerEnv.getLanguageVersion(), this.parser.inUseStrictDirective())) {
                str = convertLastCharToHex(str);
            }
            this.string = (String) this.allStrings.intern(str);
            return 39;
        }
        if (isDigit(c) || (c == 46 && isDigit(peekChar()))) {
            this.stringBufferTop = 0;
            int base = 10;
            this.isBinary = false;
            this.isOctal = false;
            this.isOldOctal = false;
            this.isHex = false;
            boolean es6 = this.parser.compilerEnv.getLanguageVersion() >= 200;
            if (c == 48) {
                c = getChar();
                if (c == 120 || c == 88) {
                    base = 16;
                    this.isHex = true;
                    c = getChar();
                } else if (es6 && (c == 111 || c == 79)) {
                    base = 8;
                    this.isOctal = true;
                    c = getChar();
                } else if (es6 && (c == 98 || c == 66)) {
                    base = 2;
                    this.isBinary = true;
                    c = getChar();
                } else if (isDigit(c)) {
                    base = 8;
                    this.isOldOctal = true;
                } else {
                    addToString(48);
                }
            }
            boolean isEmpty = true;
            if (base == 16) {
                while (0 <= Kit.xDigitToInt(c, 0)) {
                    addToString(c);
                    c = getChar();
                    isEmpty = false;
                }
            } else {
                while (48 <= c && c <= 57) {
                    if (base == 8 && c >= 56) {
                        if (this.isOldOctal) {
                            this.parser.addWarning("msg.bad.octal.literal", c == 56 ? "8" : "9");
                            base = 10;
                        } else {
                            this.parser.addError("msg.caught.nfe");
                            return -1;
                        }
                    } else if (base == 2 && c >= 50) {
                        this.parser.addError("msg.caught.nfe");
                        return -1;
                    }
                    addToString(c);
                    c = getChar();
                    isEmpty = false;
                }
            }
            if (isEmpty && (this.isBinary || this.isOctal || this.isHex)) {
                this.parser.addError("msg.caught.nfe");
                return -1;
            }
            boolean isInteger = true;
            if (base == 10 && (c == 46 || c == 101 || c == 69)) {
                isInteger = false;
                if (c == 46) {
                    do {
                        addToString(c);
                        c = getChar();
                    } while (isDigit(c));
                }
                if (c == 101 || c == 69) {
                    addToString(c);
                    c = getChar();
                    if (c == 43 || c == 45) {
                        addToString(c);
                        c = getChar();
                    }
                    if (!isDigit(c)) {
                        this.parser.addError("msg.missing.exponent");
                        return -1;
                    }
                    do {
                        addToString(c);
                        c = getChar();
                    } while (isDigit(c));
                }
            }
            ungetChar(c);
            String numString = getStringFromBuffer();
            this.string = numString;
            if (base == 10 && !isInteger) {
                try {
                    dval = Double.parseDouble(numString);
                } catch (NumberFormatException e) {
                    this.parser.addError("msg.caught.nfe");
                    return -1;
                }
            } else {
                dval = ScriptRuntime.stringPrefixToNumber(numString, 0, base);
            }
            this.number = dval;
            return 40;
        }
        if (c == 34 || c == 39) {
            this.quoteChar = c;
            this.stringBufferTop = 0;
            int c5 = getChar(false);
            while (c5 != this.quoteChar) {
                if (c5 == 10 || c5 == -1) {
                    ungetChar(c5);
                    this.tokenEnd = this.cursor;
                    this.parser.addError("msg.unterminated.string.lit");
                    return -1;
                }
                if (c5 == 92) {
                    c5 = getChar();
                    switch (c5) {
                        case 10:
                            c5 = getChar();
                            continue;
                        case 98:
                            c5 = 8;
                            break;
                        case 102:
                            c5 = 12;
                            break;
                        case 110:
                            c5 = 10;
                            break;
                        case 114:
                            c5 = 13;
                            break;
                        case 116:
                            c5 = 9;
                            break;
                        case 117:
                            int escapeStart = this.stringBufferTop;
                            addToString(117);
                            int escapeVal2 = 0;
                            for (int i2 = 0; i2 != 4; i2++) {
                                c5 = getChar();
                                escapeVal2 = Kit.xDigitToInt(c5, escapeVal2);
                                if (escapeVal2 < 0) {
                                    break;
                                }
                                addToString(c5);
                            }
                            this.stringBufferTop = escapeStart;
                            c5 = escapeVal2;
                            break;
                        case 118:
                            c5 = 11;
                            break;
                        case 120:
                            c5 = getChar();
                            int escapeVal3 = Kit.xDigitToInt(c5, 0);
                            if (escapeVal3 < 0) {
                                addToString(120);
                            } else {
                                c5 = getChar();
                                int escapeVal4 = Kit.xDigitToInt(c5, escapeVal3);
                                if (escapeVal4 < 0) {
                                    addToString(120);
                                    addToString(c5);
                                } else {
                                    c5 = escapeVal4;
                                    break;
                                }
                            }
                        default:
                            if (48 <= c5 && c5 < 56) {
                                int val = c5 - 48;
                                int c6 = getChar();
                                if (48 <= c6 && c6 < 56) {
                                    val = ((8 * val) + c6) - 48;
                                    c6 = getChar();
                                    if (48 <= c6 && c6 < 56 && val <= 31) {
                                        val = ((8 * val) + c6) - 48;
                                        c6 = getChar();
                                    }
                                }
                                ungetChar(c6);
                                c5 = val;
                                break;
                            }
                            break;
                    }
                }
                addToString(c5);
                c5 = getChar(false);
            }
            this.string = (String) this.allStrings.intern(getStringFromBuffer());
            return 41;
        }
        switch (c) {
            case 33:
                if (matchChar(61)) {
                    if (matchChar(61)) {
                        return 47;
                    }
                    return 13;
                }
                return 26;
            case 34:
            case 35:
            case 36:
            case 39:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 92:
            case 95:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            default:
                this.parser.addError("msg.illegal.character", c);
                return -1;
            case 37:
                if (matchChar(61)) {
                    return 102;
                }
                return 25;
            case 38:
                if (matchChar(38)) {
                    return 106;
                }
                if (matchChar(61)) {
                    return 94;
                }
                return 11;
            case 40:
                return 88;
            case 41:
                return 89;
            case 42:
                if (matchChar(61)) {
                    return 100;
                }
                return 23;
            case 43:
                if (matchChar(61)) {
                    return 98;
                }
                if (matchChar(43)) {
                    return 107;
                }
                return 21;
            case 44:
                return 90;
            case 45:
                if (matchChar(61)) {
                    c2 = 99;
                } else if (matchChar(45)) {
                    if (!this.dirtyLine && matchChar(62)) {
                        markCommentStart(UsageMessageFormatter.DEFAULT_LONG_OPT_PREFIX);
                        skipLine();
                        this.commentType = Token.CommentType.HTML;
                        return 162;
                    }
                    c2 = 108;
                } else {
                    c2 = 22;
                }
                this.dirtyLine = true;
                return c2;
            case 46:
                if (matchChar(46)) {
                    return 144;
                }
                if (matchChar(40)) {
                    return 147;
                }
                return 109;
            case 47:
                markCommentStart();
                if (matchChar(47)) {
                    this.tokenBeg = this.cursor - 2;
                    skipLine();
                    this.commentType = Token.CommentType.LINE;
                    return 162;
                }
                if (matchChar(42)) {
                    boolean lookForSlash = false;
                    this.tokenBeg = this.cursor - 2;
                    if (matchChar(42)) {
                        lookForSlash = true;
                        this.commentType = Token.CommentType.JSDOC;
                    } else {
                        this.commentType = Token.CommentType.BLOCK_COMMENT;
                    }
                    while (true) {
                        int c7 = getChar();
                        if (c7 == -1) {
                            this.tokenEnd = this.cursor - 1;
                            this.parser.addError("msg.unterminated.comment");
                            return 162;
                        }
                        if (c7 == 42) {
                            lookForSlash = true;
                        } else if (c7 == 47) {
                            if (lookForSlash) {
                                this.tokenEnd = this.cursor;
                                return 162;
                            }
                        } else {
                            lookForSlash = false;
                            this.tokenEnd = this.cursor;
                        }
                    }
                } else {
                    if (matchChar(61)) {
                        return 101;
                    }
                    return 24;
                }
            case 58:
                if (matchChar(58)) {
                    return 145;
                }
                return 104;
            case 59:
                return 83;
            case 60:
                if (matchChar(33)) {
                    if (matchChar(45)) {
                        if (matchChar(45)) {
                            this.tokenBeg = this.cursor - 4;
                            skipLine();
                            this.commentType = Token.CommentType.HTML;
                            return 162;
                        }
                        ungetCharIgnoreLineEnd(45);
                    }
                    ungetCharIgnoreLineEnd(33);
                }
                if (matchChar(60)) {
                    if (matchChar(61)) {
                        return 95;
                    }
                    return 18;
                }
                if (matchChar(61)) {
                    return 15;
                }
                return 14;
            case 61:
                if (matchChar(61)) {
                    if (matchChar(61)) {
                        return 46;
                    }
                    return 12;
                }
                if (matchChar(62)) {
                    return 165;
                }
                return 91;
            case 62:
                if (matchChar(62)) {
                    if (matchChar(62)) {
                        if (matchChar(61)) {
                            return 97;
                        }
                        return 20;
                    }
                    if (matchChar(61)) {
                        return 96;
                    }
                    return 19;
                }
                if (matchChar(61)) {
                    return 17;
                }
                return 16;
            case 63:
                return 103;
            case 91:
                return 84;
            case 93:
                return 85;
            case 94:
                if (matchChar(61)) {
                    return 93;
                }
                return 10;
            case 96:
                return 170;
            case 123:
                return 86;
            case 124:
                if (matchChar(124)) {
                    return 105;
                }
                if (matchChar(61)) {
                    return 92;
                }
                return 9;
            case 125:
                return 87;
            case 126:
                return 27;
        }
    }

    private static boolean isAlpha(int c) {
        return c <= 90 ? 65 <= c : 97 <= c && c <= 122;
    }

    static boolean isDigit(int c) {
        return 48 <= c && c <= 57;
    }

    static boolean isJSSpace(int c) {
        return c <= 127 ? c == 32 || c == 9 || c == 12 || c == 11 : c == 160 || c == 65279 || Character.getType((char) c) == 12;
    }

    private static boolean isJSFormatChar(int c) {
        return c > 127 && Character.getType((char) c) == 16;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0073, code lost:
    
        ungetChar(r12);
        r8.tokenEnd = r8.cursor - 1;
        r8.string = new java.lang.String(r8.stringBuffer, 0, r8.stringBufferTop);
        r8.parser.reportError("msg.unterminated.re.lit");
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a0, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void readRegExp(int r9) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 421
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.TokenStream.readRegExp(int):void");
    }

    String readAndClearRegExpFlags() {
        String flags = this.regExpFlags;
        this.regExpFlags = null;
        return flags;
    }

    boolean isXMLAttribute() {
        return this.xmlIsAttribute;
    }

    int getFirstXMLToken() throws IOException, RuntimeException {
        this.xmlOpenTagsCount = 0;
        this.xmlIsAttribute = false;
        this.xmlIsTagContent = false;
        if (!canUngetChar()) {
            return -1;
        }
        ungetChar(60);
        return getNextXMLToken();
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x0225, code lost:
    
        r4.stringBufferTop = 0;
        r4.string = null;
        r4.parser.addError("msg.XML.bad.form");
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0239, code lost:
    
        return -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    int getNextXMLToken() throws java.io.IOException, java.lang.RuntimeException {
        /*
            Method dump skipped, instructions count: 731
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.TokenStream.getNextXMLToken():int");
    }

    private boolean readQuotedString(int quote) throws IOException {
        int i = getChar();
        while (true) {
            int c = i;
            if (c != -1) {
                addToString(c);
                if (c == quote) {
                    return true;
                }
                i = getChar();
            } else {
                this.stringBufferTop = 0;
                this.string = null;
                this.parser.addError("msg.XML.bad.form");
                return false;
            }
        }
    }

    private boolean readXmlComment() throws IOException {
        int c = getChar();
        while (c != -1) {
            addToString(c);
            if (c == 45 && peekChar() == 45) {
                c = getChar();
                addToString(c);
                if (peekChar() == 62) {
                    int c2 = getChar();
                    addToString(c2);
                    return true;
                }
            } else {
                c = getChar();
            }
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readCDATA() throws IOException {
        int c = getChar();
        while (c != -1) {
            addToString(c);
            if (c == 93 && peekChar() == 93) {
                c = getChar();
                addToString(c);
                if (peekChar() == 62) {
                    int c2 = getChar();
                    addToString(c2);
                    return true;
                }
            } else {
                c = getChar();
            }
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readEntity() throws IOException {
        int declTags = 1;
        int i = getChar();
        while (true) {
            int c = i;
            if (c != -1) {
                addToString(c);
                switch (c) {
                    case 60:
                        declTags++;
                        break;
                    case 62:
                        declTags--;
                        if (declTags != 0) {
                            break;
                        } else {
                            return true;
                        }
                }
                i = getChar();
            } else {
                this.stringBufferTop = 0;
                this.string = null;
                this.parser.addError("msg.XML.bad.form");
                return false;
            }
        }
    }

    private boolean readPI() throws IOException {
        int i = getChar();
        while (true) {
            int c = i;
            if (c != -1) {
                addToString(c);
                if (c != 63 || peekChar() != 62) {
                    i = getChar();
                } else {
                    addToString(getChar());
                    return true;
                }
            } else {
                this.stringBufferTop = 0;
                this.string = null;
                this.parser.addError("msg.XML.bad.form");
                return false;
            }
        }
    }

    private String getStringFromBuffer() {
        this.tokenEnd = this.cursor;
        return new String(this.stringBuffer, 0, this.stringBufferTop);
    }

    private void addToString(int c) {
        int N = this.stringBufferTop;
        if (N == this.stringBuffer.length) {
            char[] tmp = new char[this.stringBuffer.length * 2];
            System.arraycopy(this.stringBuffer, 0, tmp, 0, N);
            this.stringBuffer = tmp;
        }
        this.stringBuffer[N] = (char) c;
        this.stringBufferTop = N + 1;
    }

    private boolean canUngetChar() {
        return this.ungetCursor == 0 || this.ungetBuffer[this.ungetCursor - 1] != 10;
    }

    private void ungetChar(int c) throws RuntimeException {
        if (this.ungetCursor != 0 && this.ungetBuffer[this.ungetCursor - 1] == 10) {
            Kit.codeBug();
        }
        int[] iArr = this.ungetBuffer;
        int i = this.ungetCursor;
        this.ungetCursor = i + 1;
        iArr[i] = c;
        this.cursor--;
    }

    private boolean matchChar(int test) throws IOException {
        int c = getCharIgnoreLineEnd();
        if (c == test) {
            this.tokenEnd = this.cursor;
            return true;
        }
        ungetCharIgnoreLineEnd(c);
        return false;
    }

    private int peekChar() throws IOException, RuntimeException {
        int c = getChar();
        ungetChar(c);
        return c;
    }

    private int getChar() throws IOException {
        return getChar(true);
    }

    private int getChar(boolean skipFormattingChars) throws IOException {
        return getChar(skipFormattingChars, false);
    }

    private int getChar(boolean skipFormattingChars, boolean ignoreLineEnd) throws IOException {
        int c;
        if (this.ungetCursor != 0) {
            this.cursor++;
            int[] iArr = this.ungetBuffer;
            int i = this.ungetCursor - 1;
            this.ungetCursor = i;
            return iArr[i];
        }
        while (true) {
            if (this.sourceString != null) {
                if (this.sourceCursor == this.sourceEnd) {
                    this.hitEOF = true;
                    return -1;
                }
                this.cursor++;
                String str = this.sourceString;
                int i2 = this.sourceCursor;
                this.sourceCursor = i2 + 1;
                c = str.charAt(i2);
            } else {
                if (this.sourceCursor == this.sourceEnd && !fillSourceBuffer()) {
                    this.hitEOF = true;
                    return -1;
                }
                this.cursor++;
                char[] cArr = this.sourceBuffer;
                int i3 = this.sourceCursor;
                this.sourceCursor = i3 + 1;
                c = cArr[i3];
            }
            if (!ignoreLineEnd && this.lineEndChar >= 0) {
                if (this.lineEndChar == 13 && c == 10) {
                    this.lineEndChar = 10;
                } else {
                    this.lineEndChar = -1;
                    this.lineStart = this.sourceCursor - 1;
                    this.lineno++;
                }
            }
            if (c <= 127) {
                if (c == 10 || c == 13) {
                    this.lineEndChar = c;
                    c = 10;
                }
            } else {
                if (c == 65279) {
                    return c;
                }
                if (!skipFormattingChars || !isJSFormatChar(c)) {
                    break;
                }
            }
        }
        if (ScriptRuntime.isJSLineTerminator(c)) {
            this.lineEndChar = c;
            c = 10;
        }
        return c;
    }

    private int getCharIgnoreLineEnd(boolean skipFormattingChars) throws IOException {
        return getChar(skipFormattingChars, true);
    }

    private int getCharIgnoreLineEnd() throws IOException {
        return getChar(true, true);
    }

    private void ungetCharIgnoreLineEnd(int c) {
        int[] iArr = this.ungetBuffer;
        int i = this.ungetCursor;
        this.ungetCursor = i + 1;
        iArr[i] = c;
        this.cursor--;
    }

    private void skipLine() throws IOException, RuntimeException {
        int c;
        do {
            c = getChar();
            if (c == -1) {
                break;
            }
        } while (c != 10);
        ungetChar(c);
        this.tokenEnd = this.cursor;
    }

    final int getOffset() {
        int n = this.sourceCursor - this.lineStart;
        if (this.lineEndChar >= 0) {
            n--;
        }
        return n;
    }

    private final int charAt(int index) {
        if (index < 0) {
            return -1;
        }
        if (this.sourceString != null) {
            if (index >= this.sourceEnd) {
                return -1;
            }
            return this.sourceString.charAt(index);
        }
        if (index >= this.sourceEnd) {
            int oldSourceCursor = this.sourceCursor;
            try {
                if (!fillSourceBuffer()) {
                    return -1;
                }
                index -= oldSourceCursor - this.sourceCursor;
            } catch (IOException e) {
                return -1;
            }
        }
        return this.sourceBuffer[index];
    }

    private final String substring(int beginIndex, int endIndex) {
        if (this.sourceString != null) {
            return this.sourceString.substring(beginIndex, endIndex);
        }
        int count = endIndex - beginIndex;
        return new String(this.sourceBuffer, beginIndex, count);
    }

    final String getLine() {
        int lineEnd;
        int lineEnd2 = this.sourceCursor;
        if (this.lineEndChar >= 0) {
            lineEnd = lineEnd2 - 1;
            if (this.lineEndChar == 10 && charAt(lineEnd - 1) == 13) {
                lineEnd--;
            }
        } else {
            int lineLength = lineEnd2 - this.lineStart;
            while (true) {
                int c = charAt(this.lineStart + lineLength);
                if (c == -1 || ScriptRuntime.isJSLineTerminator(c)) {
                    break;
                }
                lineLength++;
            }
            lineEnd = this.lineStart + lineLength;
        }
        return substring(this.lineStart, lineEnd);
    }

    final String getLine(int position, int[] linep) {
        if (!$assertionsDisabled && (position < 0 || position > this.cursor)) {
            throw new AssertionError();
        }
        if (!$assertionsDisabled && linep.length != 2) {
            throw new AssertionError();
        }
        int delta = (this.cursor + this.ungetCursor) - position;
        int cur = this.sourceCursor;
        if (delta > cur) {
            return null;
        }
        int end = 0;
        int lines = 0;
        while (delta > 0) {
            if (!$assertionsDisabled && cur <= 0) {
                throw new AssertionError();
            }
            int c = charAt(cur - 1);
            if (ScriptRuntime.isJSLineTerminator(c)) {
                if (c == 10 && charAt(cur - 2) == 13) {
                    delta--;
                    cur--;
                }
                lines++;
                end = cur - 1;
            }
            delta--;
            cur--;
        }
        int start = 0;
        int offset = 0;
        while (true) {
            if (cur <= 0) {
                break;
            }
            if (!ScriptRuntime.isJSLineTerminator(charAt(cur - 1))) {
                cur--;
                offset++;
            } else {
                start = cur;
                break;
            }
        }
        linep[0] = (this.lineno - lines) + (this.lineEndChar >= 0 ? 1 : 0);
        linep[1] = offset;
        if (lines == 0) {
            return getLine();
        }
        return substring(start, end);
    }

    private boolean fillSourceBuffer() throws IOException, RuntimeException {
        if (this.sourceString != null) {
            Kit.codeBug();
        }
        if (this.sourceEnd == this.sourceBuffer.length) {
            if (this.lineStart != 0 && !isMarkingComment()) {
                System.arraycopy(this.sourceBuffer, this.lineStart, this.sourceBuffer, 0, this.sourceEnd - this.lineStart);
                this.sourceEnd -= this.lineStart;
                this.sourceCursor -= this.lineStart;
                this.lineStart = 0;
            } else {
                char[] tmp = new char[this.sourceBuffer.length * 2];
                System.arraycopy(this.sourceBuffer, 0, tmp, 0, this.sourceEnd);
                this.sourceBuffer = tmp;
            }
        }
        int n = this.sourceReader.read(this.sourceBuffer, this.sourceEnd, this.sourceBuffer.length - this.sourceEnd);
        if (n < 0) {
            return false;
        }
        this.sourceEnd += n;
        return true;
    }

    public int getCursor() {
        return this.cursor;
    }

    public int getTokenBeg() {
        return this.tokenBeg;
    }

    public int getTokenEnd() {
        return this.tokenEnd;
    }

    public int getTokenLength() {
        return this.tokenEnd - this.tokenBeg;
    }

    public Token.CommentType getCommentType() {
        return this.commentType;
    }

    private void markCommentStart() {
        markCommentStart("");
    }

    private void markCommentStart(String prefix) {
        if (this.parser.compilerEnv.isRecordingComments() && this.sourceReader != null) {
            this.commentPrefix = prefix;
            this.commentCursor = this.sourceCursor - 1;
        }
    }

    private boolean isMarkingComment() {
        return this.commentCursor != -1;
    }

    final String getAndResetCurrentComment() {
        if (this.sourceString != null) {
            if (isMarkingComment()) {
                Kit.codeBug();
            }
            return this.sourceString.substring(this.tokenBeg, this.tokenEnd);
        }
        if (!isMarkingComment()) {
            Kit.codeBug();
        }
        StringBuilder comment = new StringBuilder(this.commentPrefix);
        comment.append(this.sourceBuffer, this.commentCursor, getTokenLength() - this.commentPrefix.length());
        this.commentCursor = -1;
        return comment.toString();
    }

    private static String convertLastCharToHex(String str) {
        int lastIndex = str.length() - 1;
        StringBuilder buf = new StringBuilder(str.substring(0, lastIndex));
        buf.append("\\u");
        String hexCode = Integer.toHexString(str.charAt(lastIndex));
        for (int i = 0; i < 4 - hexCode.length(); i++) {
            buf.append('0');
        }
        buf.append(hexCode);
        return buf.toString();
    }

    int readTemplateLiteral(boolean isTaggedLiteral) throws IOException {
        this.rawString.setLength(0);
        this.stringBufferTop = 0;
        boolean hasInvalidEscapeSequences = false;
        while (true) {
            int c = getTemplateLiteralChar();
            switch (c) {
                case -1:
                    this.string = hasInvalidEscapeSequences ? null : getStringFromBuffer();
                    this.tokenEnd = this.cursor - 1;
                    this.parser.reportError("msg.unexpected.eof");
                    return -1;
                case 36:
                    if (matchTemplateLiteralChar(123)) {
                        this.rawString.setLength(this.rawString.length() - 2);
                        this.string = hasInvalidEscapeSequences ? null : getStringFromBuffer();
                        this.tokenEnd = this.cursor - 1;
                        return 172;
                    }
                    addToString(c);
                    break;
                case 92:
                    int c2 = getTemplateLiteralChar();
                    switch (c2) {
                        case 10:
                        case 8232:
                        case 8233:
                            continue;
                        case 48:
                            int d = peekTemplateLiteralChar();
                            if (d >= 48 && d <= 57) {
                                if (isTaggedLiteral) {
                                    hasInvalidEscapeSequences = true;
                                    break;
                                } else {
                                    this.parser.reportError("msg.syntax");
                                    return -1;
                                }
                            } else {
                                c2 = 0;
                                break;
                            }
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                            if (isTaggedLiteral) {
                                hasInvalidEscapeSequences = true;
                                continue;
                            } else {
                                this.parser.reportError("msg.syntax");
                                return -1;
                            }
                        case 98:
                            c2 = 8;
                            break;
                        case 102:
                            c2 = 12;
                            break;
                        case 110:
                            c2 = 10;
                            break;
                        case 114:
                            c2 = 13;
                            break;
                        case 116:
                            c2 = 9;
                            break;
                        case 117:
                            int escapeVal = 0;
                            if (matchTemplateLiteralChar(123)) {
                                while (true) {
                                    if (peekTemplateLiteralChar() == 96) {
                                        escapeVal = -1;
                                    } else {
                                        int c3 = getTemplateLiteralChar();
                                        if (c3 != 125) {
                                            escapeVal = Kit.xDigitToInt(c3, escapeVal);
                                        }
                                    }
                                }
                                if (escapeVal < 0 || escapeVal > 1114111) {
                                    if (isTaggedLiteral) {
                                        hasInvalidEscapeSequences = true;
                                        break;
                                    } else {
                                        this.parser.reportError("msg.syntax");
                                        return -1;
                                    }
                                } else if (escapeVal > 65535) {
                                    addToString(Character.highSurrogate(escapeVal));
                                    addToString(Character.lowSurrogate(escapeVal));
                                    break;
                                } else {
                                    c2 = escapeVal;
                                    break;
                                }
                            } else {
                                int i = 0;
                                while (true) {
                                    if (i < 4) {
                                        if (peekTemplateLiteralChar() == 96) {
                                            escapeVal = -1;
                                        } else {
                                            escapeVal = Kit.xDigitToInt(getTemplateLiteralChar(), escapeVal);
                                            i++;
                                        }
                                    }
                                }
                                if (escapeVal < 0) {
                                    if (isTaggedLiteral) {
                                        hasInvalidEscapeSequences = true;
                                        break;
                                    } else {
                                        this.parser.reportError("msg.syntax");
                                        return -1;
                                    }
                                } else {
                                    c2 = escapeVal;
                                    break;
                                }
                            }
                            break;
                        case 118:
                            c2 = 11;
                            break;
                        case 120:
                            int escapeVal2 = 0;
                            int i2 = 0;
                            while (true) {
                                if (i2 < 2) {
                                    if (peekTemplateLiteralChar() == 96) {
                                        escapeVal2 = -1;
                                    } else {
                                        escapeVal2 = Kit.xDigitToInt(getTemplateLiteralChar(), escapeVal2);
                                        i2++;
                                    }
                                }
                            }
                            if (escapeVal2 < 0) {
                                if (isTaggedLiteral) {
                                    hasInvalidEscapeSequences = true;
                                    break;
                                } else {
                                    this.parser.reportError("msg.syntax");
                                    return -1;
                                }
                            } else {
                                c2 = escapeVal2;
                                break;
                            }
                    }
                    addToString(c2);
                    break;
                case 96:
                    this.rawString.setLength(this.rawString.length() - 1);
                    this.string = hasInvalidEscapeSequences ? null : getStringFromBuffer();
                    return 170;
                default:
                    addToString(c);
                    break;
            }
        }
    }

    String getRawString() {
        if (this.rawString.length() == 0) {
            return "";
        }
        return this.rawString.toString();
    }

    private int peekTemplateLiteralChar() throws IOException {
        int c = getTemplateLiteralChar();
        ungetTemplateLiteralChar(c);
        return c;
    }

    private boolean matchTemplateLiteralChar(int test) throws IOException {
        int c = getTemplateLiteralChar();
        if (c == test) {
            return true;
        }
        ungetTemplateLiteralChar(c);
        return false;
    }

    private void ungetTemplateLiteralChar(int c) {
        ungetCharIgnoreLineEnd(c);
        this.rawString.setLength(this.rawString.length() - 1);
    }

    private int getTemplateLiteralChar() throws IOException {
        int c = getCharIgnoreLineEnd(false);
        if (c == 10) {
            switch (this.lineEndChar) {
                case 13:
                    if (charAt(this.cursor) == 10) {
                        getCharIgnoreLineEnd(false);
                        break;
                    }
                    break;
                case 8232:
                case 8233:
                    c = this.lineEndChar;
                    break;
            }
            this.lineEndChar = -1;
            this.lineStart = this.sourceCursor - 1;
            this.lineno++;
        }
        this.rawString.append((char) c);
        return c;
    }
}
