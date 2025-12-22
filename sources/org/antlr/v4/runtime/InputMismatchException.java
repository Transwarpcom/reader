package org.antlr.v4.runtime;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/InputMismatchException.class */
public class InputMismatchException extends RecognitionException {
    public InputMismatchException(Parser recognizer) {
        super(recognizer, recognizer.getInputStream(), recognizer._ctx);
        setOffendingToken(recognizer.getCurrentToken());
    }

    public InputMismatchException(Parser recognizer, int state, ParserRuleContext ctx) {
        super(recognizer, recognizer.getInputStream(), ctx);
        setOffendingState(state);
        setOffendingToken(recognizer.getCurrentToken());
    }
}
