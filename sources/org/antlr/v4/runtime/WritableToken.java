package org.antlr.v4.runtime;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/WritableToken.class */
public interface WritableToken extends Token {
    void setText(String str);

    void setType(int i);

    void setLine(int i);

    void setCharPositionInLine(int i);

    void setChannel(int i);

    void setTokenIndex(int i);
}
