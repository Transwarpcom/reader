package io.vertx.core.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/StringEscapeUtils.class */
public class StringEscapeUtils {
    public static String escapeJava(String str) throws Exception {
        return escapeJavaStyleString(str, false, false);
    }

    public static void escapeJava(Writer out, String str) throws IOException {
        escapeJavaStyleString(out, str, false, false);
    }

    public static String escapeJavaScript(String str) throws Exception {
        return escapeJavaStyleString(str, true, true);
    }

    public static void escapeJavaScript(Writer out, String str) throws Exception {
        escapeJavaStyleString(out, str, true, true);
    }

    private static String escapeJavaStyleString(String str, boolean escapeSingleQuotes, boolean escapeForwardSlash) throws Exception {
        if (str == null) {
            return null;
        }
        StringWriter writer = new StringWriter(str.length() * 2);
        escapeJavaStyleString(writer, str, escapeSingleQuotes, escapeForwardSlash);
        return writer.toString();
    }

    private static void escapeJavaStyleString(Writer out, String str, boolean escapeSingleQuote, boolean escapeForwardSlash) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        }
        if (str == null) {
            return;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            char ch2 = str.charAt(i);
            if (ch2 > 4095) {
                out.write("\\u" + hex(ch2));
            } else if (ch2 > 255) {
                out.write("\\u0" + hex(ch2));
            } else if (ch2 > 127) {
                out.write("\\u00" + hex(ch2));
            } else if (ch2 < ' ') {
                switch (ch2) {
                    case '\b':
                        out.write(92);
                        out.write(98);
                        break;
                    case '\t':
                        out.write(92);
                        out.write(116);
                        break;
                    case '\n':
                        out.write(92);
                        out.write(110);
                        break;
                    case 11:
                    default:
                        if (ch2 > 15) {
                            out.write("\\u00" + hex(ch2));
                            break;
                        } else {
                            out.write("\\u000" + hex(ch2));
                            break;
                        }
                    case '\f':
                        out.write(92);
                        out.write(102);
                        break;
                    case '\r':
                        out.write(92);
                        out.write(114);
                        break;
                }
            } else {
                switch (ch2) {
                    case '\"':
                        out.write(92);
                        out.write(34);
                        break;
                    case '\'':
                        if (escapeSingleQuote) {
                            out.write(92);
                        }
                        out.write(39);
                        break;
                    case '/':
                        if (escapeForwardSlash) {
                            out.write(92);
                        }
                        out.write(47);
                        break;
                    case '\\':
                        out.write(92);
                        out.write(92);
                        break;
                    default:
                        out.write(ch2);
                        break;
                }
            }
        }
    }

    private static String hex(char ch2) {
        return Integer.toHexString(ch2).toUpperCase(Locale.ENGLISH);
    }

    public static String unescapeJava(String str) throws Exception {
        if (str == null) {
            return null;
        }
        StringWriter writer = new StringWriter(str.length());
        unescapeJava(writer, str);
        return writer.toString();
    }

    public static void unescapeJava(Writer out, String str) throws Exception {
        if (out == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        }
        if (str == null) {
            return;
        }
        int sz = str.length();
        StringBuilder unicode = new StringBuilder();
        boolean hadSlash = false;
        boolean inUnicode = false;
        for (int i = 0; i < sz; i++) {
            char ch2 = str.charAt(i);
            if (inUnicode) {
                unicode.append(ch2);
                if (unicode.length() == 4) {
                    int value = Integer.parseInt(unicode.toString(), 16);
                    out.write((char) value);
                    unicode.setLength(0);
                    inUnicode = false;
                    hadSlash = false;
                }
            } else if (hadSlash) {
                hadSlash = false;
                switch (ch2) {
                    case '\"':
                        out.write(34);
                        break;
                    case '\'':
                        out.write(39);
                        break;
                    case '\\':
                        out.write(92);
                        break;
                    case 'b':
                        out.write(8);
                        break;
                    case 'f':
                        out.write(12);
                        break;
                    case 'n':
                        out.write(10);
                        break;
                    case 'r':
                        out.write(13);
                        break;
                    case 't':
                        out.write(9);
                        break;
                    case 'u':
                        inUnicode = true;
                        break;
                    default:
                        out.write(ch2);
                        break;
                }
            } else if (ch2 == '\\') {
                hadSlash = true;
            } else {
                out.write(ch2);
            }
        }
        if (hadSlash) {
            out.write(92);
        }
    }

    public static String unescapeJavaScript(String str) throws Exception {
        return unescapeJava(str);
    }

    public static void unescapeJavaScript(Writer out, String str) throws Exception {
        unescapeJava(out, str);
    }
}
