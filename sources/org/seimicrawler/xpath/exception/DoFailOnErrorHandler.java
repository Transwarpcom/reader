package org.seimicrawler.xpath.exception;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/exception/DoFailOnErrorHandler.class */
public class DoFailOnErrorHandler extends DefaultErrorStrategy {
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
}
