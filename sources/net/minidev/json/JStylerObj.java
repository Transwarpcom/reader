package net.minidev.json;

import java.io.IOException;

/* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/JStylerObj.class */
class JStylerObj {
    public static final MPSimple MP_SIMPLE = new MPSimple();
    public static final MPTrue MP_TRUE = new MPTrue();
    public static final MPAgressive MP_AGGRESIVE = new MPAgressive();
    public static final EscapeLT ESCAPE_LT = new EscapeLT();
    public static final Escape4Web ESCAPE4Web = new Escape4Web();

    /* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/JStylerObj$MustProtect.class */
    public interface MustProtect {
        boolean mustBeProtect(String str);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/JStylerObj$StringProtector.class */
    public interface StringProtector {
        void escape(String str, Appendable appendable);
    }

    JStylerObj() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/JStylerObj$MPTrue.class */
    static class MPTrue implements MustProtect {
        private MPTrue() {
        }

        @Override // net.minidev.json.JStylerObj.MustProtect
        public boolean mustBeProtect(String s) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/JStylerObj$MPSimple.class */
    static class MPSimple implements MustProtect {
        private MPSimple() {
        }

        @Override // net.minidev.json.JStylerObj.MustProtect
        public boolean mustBeProtect(String s) {
            if (s == null) {
                return false;
            }
            int len = s.length();
            if (len == 0 || s.trim() != s) {
                return true;
            }
            char ch2 = s.charAt(0);
            if ((ch2 >= '0' && ch2 <= '9') || ch2 == '-') {
                return true;
            }
            for (int i = 0; i < len; i++) {
                char ch3 = s.charAt(i);
                if (JStylerObj.isSpace(ch3) || JStylerObj.isSpecial(ch3) || JStylerObj.isSpecialChar(ch3) || JStylerObj.isUnicode(ch3)) {
                    return true;
                }
            }
            if (JStylerObj.isKeyword(s)) {
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/JStylerObj$MPAgressive.class */
    static class MPAgressive implements MustProtect {
        private MPAgressive() {
        }

        @Override // net.minidev.json.JStylerObj.MustProtect
        public boolean mustBeProtect(String s) {
            char ch2;
            if (s == null) {
                return false;
            }
            int len = s.length();
            if (len == 0 || s.trim() != s) {
                return true;
            }
            char ch3 = s.charAt(0);
            if (JStylerObj.isSpecial(ch3) || JStylerObj.isUnicode(ch3)) {
                return true;
            }
            for (int i = 1; i < len; i++) {
                char ch4 = s.charAt(i);
                if (JStylerObj.isSpecialClose(ch4) || JStylerObj.isUnicode(ch4)) {
                    return true;
                }
            }
            if (JStylerObj.isKeyword(s)) {
                return true;
            }
            char ch5 = s.charAt(0);
            if ((ch5 >= '0' && ch5 <= '9') || ch5 == '-') {
                int p = 1;
                while (p < len) {
                    ch5 = s.charAt(p);
                    if (ch5 < '0' || ch5 > '9') {
                        break;
                    }
                    p++;
                }
                if (p == len) {
                    return true;
                }
                if (ch5 == '.') {
                    p++;
                }
                while (p < len) {
                    ch5 = s.charAt(p);
                    if (ch5 < '0' || ch5 > '9') {
                        break;
                    }
                    p++;
                }
                if (p == len) {
                    return true;
                }
                if (ch5 == 'E' || ch5 == 'e') {
                    p++;
                    if (p == len) {
                        return false;
                    }
                    char ch6 = s.charAt(p);
                    if (ch6 == '+' || ch6 == '-') {
                        p++;
                        s.charAt(p);
                    }
                }
                if (p == len) {
                    return false;
                }
                while (p < len && (ch2 = s.charAt(p)) >= '0' && ch2 <= '9') {
                    p++;
                }
                if (p == len) {
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    public static boolean isSpace(char c) {
        return c == '\r' || c == '\n' || c == '\t' || c == ' ';
    }

    public static boolean isSpecialChar(char c) {
        return c == '\b' || c == '\f' || c == '\n';
    }

    public static boolean isSpecialOpen(char c) {
        return c == '{' || c == '[' || c == ',' || c == ':';
    }

    public static boolean isSpecialClose(char c) {
        return c == '}' || c == ']' || c == ',' || c == ':';
    }

    public static boolean isSpecial(char c) {
        return c == '{' || c == '[' || c == ',' || c == '}' || c == ']' || c == ':' || c == '\'' || c == '\"';
    }

    public static boolean isUnicode(char c) {
        return (c >= 0 && c <= 31) || (c >= 127 && c <= 159) || (c >= 8192 && c <= 8447);
    }

    public static boolean isKeyword(String s) {
        if (s.length() < 3) {
            return false;
        }
        char c = s.charAt(0);
        if (c == 'n') {
            return s.equals("null");
        }
        if (c == 't') {
            return s.equals("true");
        }
        if (c == 'f') {
            return s.equals("false");
        }
        if (c == 'N') {
            return s.equals("NaN");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/JStylerObj$EscapeLT.class */
    static class EscapeLT implements StringProtector {
        private EscapeLT() {
        }

        @Override // net.minidev.json.JStylerObj.StringProtector
        public void escape(String s, Appendable out) throws IOException {
            try {
                int len = s.length();
                for (int i = 0; i < len; i++) {
                    char ch2 = s.charAt(i);
                    switch (ch2) {
                        case '\b':
                            out.append("\\b");
                            break;
                        case '\t':
                            out.append("\\t");
                            break;
                        case '\n':
                            out.append("\\n");
                            break;
                        case '\f':
                            out.append("\\f");
                            break;
                        case '\r':
                            out.append("\\r");
                            break;
                        case '\"':
                            out.append("\\\"");
                            break;
                        case '\\':
                            out.append("\\\\");
                            break;
                        default:
                            if ((ch2 >= 0 && ch2 <= 31) || ((ch2 >= 127 && ch2 <= 159) || (ch2 >= 8192 && ch2 <= 8447))) {
                                out.append("\\u");
                                out.append("0123456789ABCDEF".charAt((ch2 >> '\f') & 15));
                                out.append("0123456789ABCDEF".charAt((ch2 >> '\b') & 15));
                                out.append("0123456789ABCDEF".charAt((ch2 >> 4) & 15));
                                out.append("0123456789ABCDEF".charAt((ch2 >> 0) & 15));
                                break;
                            } else {
                                out.append(ch2);
                                break;
                            }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Impossible Exception");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/JStylerObj$Escape4Web.class */
    static class Escape4Web implements StringProtector {
        private Escape4Web() {
        }

        @Override // net.minidev.json.JStylerObj.StringProtector
        public void escape(String s, Appendable sb) throws IOException {
            try {
                int len = s.length();
                for (int i = 0; i < len; i++) {
                    char ch2 = s.charAt(i);
                    switch (ch2) {
                        case '\b':
                            sb.append("\\b");
                            break;
                        case '\t':
                            sb.append("\\t");
                            break;
                        case '\n':
                            sb.append("\\n");
                            break;
                        case '\f':
                            sb.append("\\f");
                            break;
                        case '\r':
                            sb.append("\\r");
                            break;
                        case '\"':
                            sb.append("\\\"");
                            break;
                        case '/':
                            sb.append("\\/");
                            break;
                        case '\\':
                            sb.append("\\\\");
                            break;
                        default:
                            if ((ch2 >= 0 && ch2 <= 31) || ((ch2 >= 127 && ch2 <= 159) || (ch2 >= 8192 && ch2 <= 8447))) {
                                sb.append("\\u");
                                sb.append("0123456789ABCDEF".charAt((ch2 >> '\f') & 15));
                                sb.append("0123456789ABCDEF".charAt((ch2 >> '\b') & 15));
                                sb.append("0123456789ABCDEF".charAt((ch2 >> 4) & 15));
                                sb.append("0123456789ABCDEF".charAt((ch2 >> 0) & 15));
                                break;
                            } else {
                                sb.append(ch2);
                                break;
                            }
                            break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Impossible Error");
            }
        }
    }
}
