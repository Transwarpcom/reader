package org.antlr.v4.runtime;

import org.antlr.v4.runtime.misc.ParseCancellationException;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/BailErrorStrategy.class */
public class BailErrorStrategy extends DefaultErrorStrategy {
    @Override // org.antlr.v4.runtime.DefaultErrorStrategy, org.antlr.v4.runtime.ANTLRErrorStrategy
    public void recover(Parser recognizer, RecognitionException e) {
        ParserRuleContext context = recognizer.getContext();
        while (true) {
            ParserRuleContext context2 = context;
            if (context2 != null) {
                context2.exception = e;
                context = context2.getParent();
            } else {
                throw new ParseCancellationException(e);
            }
        }
    }

    @Override // org.antlr.v4.runtime.DefaultErrorStrategy, org.antlr.v4.runtime.ANTLRErrorStrategy
    public Token recoverInline(Parser recognizer) throws RecognitionException {
        InputMismatchException e = new InputMismatchException(recognizer);
        ParserRuleContext context = recognizer.getContext();
        while (true) {
            ParserRuleContext context2 = context;
            if (context2 != null) {
                context2.exception = e;
                context = context2.getParent();
            } else {
                throw new ParseCancellationException(e);
            }
        }
    }

    @Override // org.antlr.v4.runtime.DefaultErrorStrategy, org.antlr.v4.runtime.ANTLRErrorStrategy
    public void sync(Parser recognizer) {
    }
}
