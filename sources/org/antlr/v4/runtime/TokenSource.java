package org.antlr.v4.runtime;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/TokenSource.class */
public interface TokenSource {
    Token nextToken();

    int getLine();

    int getCharPositionInLine();

    CharStream getInputStream();

    String getSourceName();

    void setTokenFactory(TokenFactory<?> tokenFactory);

    TokenFactory<?> getTokenFactory();
}
