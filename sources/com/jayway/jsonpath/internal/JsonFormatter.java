package com.jayway.jsonpath.internal;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/JsonFormatter.class */
public class JsonFormatter {
    private static final String INDENT = "   ";
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final int MODE_SINGLE = 100;
    private static final int MODE_DOUBLE = 101;
    private static final int MODE_ESCAPE_SINGLE = 102;
    private static final int MODE_ESCAPE_DOUBLE = 103;
    private static final int MODE_BETWEEN = 104;

    private static void appendIndent(StringBuilder sb, int count) {
        while (count > 0) {
            sb.append(INDENT);
            count--;
        }
    }

    public static String prettyPrint(String input) {
        String input2 = input.replaceAll("[\\r\\n]", "");
        StringBuilder output = new StringBuilder(input2.length() * 2);
        int mode = 104;
        int depth = 0;
        for (int i = 0; i < input2.length(); i++) {
            char ch2 = input2.charAt(i);
            switch (mode) {
                case 100:
                    output.append(ch2);
                    switch (ch2) {
                        case '\'':
                            mode = 104;
                            break;
                        case '\\':
                            mode = 102;
                            break;
                    }
                case 101:
                    output.append(ch2);
                    switch (ch2) {
                        case '\"':
                            mode = 104;
                            break;
                        case '\\':
                            mode = 103;
                            break;
                    }
                case 102:
                    output.append(ch2);
                    mode = 100;
                    break;
                case 103:
                    output.append(ch2);
                    mode = 101;
                    break;
                case 104:
                    switch (ch2) {
                        case ' ':
                            break;
                        case '\"':
                            output.append(ch2);
                            mode = 101;
                            break;
                        case '\'':
                            output.append(ch2);
                            mode = 100;
                            break;
                        case ',':
                            output.append(ch2);
                            output.append(NEW_LINE);
                            appendIndent(output, depth);
                            break;
                        case ':':
                            output.append(" : ");
                            break;
                        case '[':
                        case '{':
                            output.append(ch2);
                            output.append(NEW_LINE);
                            depth++;
                            appendIndent(output, depth);
                            break;
                        case ']':
                        case '}':
                            output.append(NEW_LINE);
                            depth--;
                            appendIndent(output, depth);
                            output.append(ch2);
                            break;
                        default:
                            output.append(ch2);
                            break;
                    }
            }
        }
        return output.toString();
    }
}
