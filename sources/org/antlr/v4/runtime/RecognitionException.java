package org.antlr.v4.runtime;

import org.antlr.v4.runtime.misc.IntervalSet;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/RecognitionException.class */
public class RecognitionException extends RuntimeException {
    private final Recognizer<?, ?> recognizer;
    private final RuleContext ctx;
    private final IntStream input;
    private Token offendingToken;
    private int offendingState;

    public RecognitionException(Recognizer<?, ?> recognizer, IntStream input, ParserRuleContext ctx) {
        this.offendingState = -1;
        this.recognizer = recognizer;
        this.input = input;
        this.ctx = ctx;
        if (recognizer != null) {
            this.offendingState = recognizer.getState();
        }
    }

    public RecognitionException(String message, Recognizer<?, ?> recognizer, IntStream input, ParserRuleContext ctx) {
        super(message);
        this.offendingState = -1;
        this.recognizer = recognizer;
        this.input = input;
        this.ctx = ctx;
        if (recognizer != null) {
            this.offendingState = recognizer.getState();
        }
    }

    public int getOffendingState() {
        return this.offendingState;
    }

    protected final void setOffendingState(int offendingState) {
        this.offendingState = offendingState;
    }

    public IntervalSet getExpectedTokens() {
        if (this.recognizer != null) {
            return this.recognizer.getATN().getExpectedTokens(this.offendingState, this.ctx);
        }
        return null;
    }

    public RuleContext getCtx() {
        return this.ctx;
    }

    public IntStream getInputStream() {
        return this.input;
    }

    public Token getOffendingToken() {
        return this.offendingToken;
    }

    protected final void setOffendingToken(Token offendingToken) {
        this.offendingToken = offendingToken;
    }

    public Recognizer<?, ?> getRecognizer() {
        return this.recognizer;
    }
}
