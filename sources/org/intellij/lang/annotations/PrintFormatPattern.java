package org.intellij.lang.annotations;

/* compiled from: PrintFormat.java */
/* loaded from: reader.jar:BOOT-INF/lib/annotations-13.0.jar:org/intellij/lang/annotations/PrintFormatPattern.class */
class PrintFormatPattern {

    @Language("RegExp")
    private static final String ARG_INDEX = "(?:\\d+\\$)?";

    @Language("RegExp")
    private static final String FLAGS = "(?:[-#+ 0,(<]*)?";

    @Language("RegExp")
    private static final String WIDTH = "(?:\\d+)?";

    @Language("RegExp")
    private static final String PRECISION = "(?:\\.\\d+)?";

    @Language("RegExp")
    private static final String CONVERSION = "(?:[tT])?(?:[a-zA-Z%])";

    @Language("RegExp")
    private static final String TEXT = "[^%]|%%";

    @Language("RegExp")
    static final String PRINT_FORMAT = "(?:[^%]|%%|(?:%(?:\\d+\\$)?(?:[-#+ 0,(<]*)?(?:\\d+)?(?:\\.\\d+)?(?:[tT])?(?:[a-zA-Z%])))*";

    PrintFormatPattern() {
    }
}
