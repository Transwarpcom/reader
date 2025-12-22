package org.apache.commons.lang3;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/CharUtils.class */
public class CharUtils {
    private static final String[] CHAR_STRING_ARRAY = new String[128];
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final char LF = '\n';
    public static final char CR = '\r';
    public static final char NUL = 0;

    static {
        char c = 0;
        while (true) {
            char c2 = c;
            if (c2 < CHAR_STRING_ARRAY.length) {
                CHAR_STRING_ARRAY[c2] = String.valueOf(c2);
                c = (char) (c2 + 1);
            } else {
                return;
            }
        }
    }

    @Deprecated
    public static Character toCharacterObject(char ch2) {
        return Character.valueOf(ch2);
    }

    public static Character toCharacterObject(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return Character.valueOf(str.charAt(0));
    }

    public static char toChar(Character ch2) {
        Validate.isTrue(ch2 != null, "The Character must not be null", new Object[0]);
        return ch2.charValue();
    }

    public static char toChar(Character ch2, char defaultValue) {
        if (ch2 == null) {
            return defaultValue;
        }
        return ch2.charValue();
    }

    public static char toChar(String str) {
        Validate.isTrue(StringUtils.isNotEmpty(str), "The String must not be empty", new Object[0]);
        return str.charAt(0);
    }

    public static char toChar(String str, char defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        return str.charAt(0);
    }

    public static int toIntValue(char ch2) {
        if (!isAsciiNumeric(ch2)) {
            throw new IllegalArgumentException("The character " + ch2 + " is not in the range '0' - '9'");
        }
        return ch2 - '0';
    }

    public static int toIntValue(char ch2, int defaultValue) {
        if (!isAsciiNumeric(ch2)) {
            return defaultValue;
        }
        return ch2 - '0';
    }

    public static int toIntValue(Character ch2) {
        Validate.isTrue(ch2 != null, "The character must not be null", new Object[0]);
        return toIntValue(ch2.charValue());
    }

    public static int toIntValue(Character ch2, int defaultValue) {
        if (ch2 == null) {
            return defaultValue;
        }
        return toIntValue(ch2.charValue(), defaultValue);
    }

    public static String toString(char ch2) {
        if (ch2 < 128) {
            return CHAR_STRING_ARRAY[ch2];
        }
        return new String(new char[]{ch2});
    }

    public static String toString(Character ch2) {
        if (ch2 == null) {
            return null;
        }
        return toString(ch2.charValue());
    }

    public static String unicodeEscaped(char ch2) {
        return "\\u" + HEX_DIGITS[(ch2 >> '\f') & 15] + HEX_DIGITS[(ch2 >> '\b') & 15] + HEX_DIGITS[(ch2 >> 4) & 15] + HEX_DIGITS[ch2 & 15];
    }

    public static String unicodeEscaped(Character ch2) {
        if (ch2 == null) {
            return null;
        }
        return unicodeEscaped(ch2.charValue());
    }

    public static boolean isAscii(char ch2) {
        return ch2 < 128;
    }

    public static boolean isAsciiPrintable(char ch2) {
        return ch2 >= ' ' && ch2 < 127;
    }

    public static boolean isAsciiControl(char ch2) {
        return ch2 < ' ' || ch2 == 127;
    }

    public static boolean isAsciiAlpha(char ch2) {
        return isAsciiAlphaUpper(ch2) || isAsciiAlphaLower(ch2);
    }

    public static boolean isAsciiAlphaUpper(char ch2) {
        return ch2 >= 'A' && ch2 <= 'Z';
    }

    public static boolean isAsciiAlphaLower(char ch2) {
        return ch2 >= 'a' && ch2 <= 'z';
    }

    public static boolean isAsciiNumeric(char ch2) {
        return ch2 >= '0' && ch2 <= '9';
    }

    public static boolean isAsciiAlphanumeric(char ch2) {
        return isAsciiAlpha(ch2) || isAsciiNumeric(ch2);
    }

    public static int compare(char x, char y) {
        return x - y;
    }
}
