package org.antlr.v4.runtime;

import org.antlr.v4.runtime.misc.Interval;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/TokenStream.class */
public interface TokenStream extends IntStream {
    Token LT(int i);

    Token get(int i);

    TokenSource getTokenSource();

    String getText(Interval interval);

    String getText();

    String getText(RuleContext ruleContext);

    String getText(Token token, Token token2);
}
