package org.antlr.v4.runtime;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/Token.class */
public interface Token {
    public static final int INVALID_TYPE = 0;
    public static final int EPSILON = -2;
    public static final int MIN_USER_TOKEN_TYPE = 1;
    public static final int EOF = -1;
    public static final int DEFAULT_CHANNEL = 0;
    public static final int HIDDEN_CHANNEL = 1;
    public static final int MIN_USER_CHANNEL_VALUE = 2;

    String getText();

    int getType();

    int getLine();

    int getCharPositionInLine();

    int getChannel();

    int getTokenIndex();

    int getStartIndex();

    int getStopIndex();

    TokenSource getTokenSource();

    CharStream getInputStream();
}
