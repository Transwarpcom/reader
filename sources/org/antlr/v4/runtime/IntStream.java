package org.antlr.v4.runtime;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/IntStream.class */
public interface IntStream {
    public static final int EOF = -1;
    public static final String UNKNOWN_SOURCE_NAME = "<unknown>";

    void consume();

    int LA(int i);

    int mark();

    void release(int i);

    int index();

    void seek(int i);

    int size();

    String getSourceName();
}
