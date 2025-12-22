package org.antlr.v4.runtime;

import org.antlr.v4.runtime.atn.ATNConfigSet;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/NoViableAltException.class */
public class NoViableAltException extends RecognitionException {
    private final ATNConfigSet deadEndConfigs;
    private final Token startToken;

    public NoViableAltException(Parser recognizer) {
        this(recognizer, recognizer.getInputStream(), recognizer.getCurrentToken(), recognizer.getCurrentToken(), null, recognizer._ctx);
    }

    public NoViableAltException(Parser recognizer, TokenStream input, Token startToken, Token offendingToken, ATNConfigSet deadEndConfigs, ParserRuleContext ctx) {
        super(recognizer, input, ctx);
        this.deadEndConfigs = deadEndConfigs;
        this.startToken = startToken;
        setOffendingToken(offendingToken);
    }

    public Token getStartToken() {
        return this.startToken;
    }

    public ATNConfigSet getDeadEndConfigs() {
        return this.deadEndConfigs;
    }
}
