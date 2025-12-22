package org.antlr.v4.runtime;

import java.util.BitSet;
import java.util.Collection;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/ProxyErrorListener.class */
public class ProxyErrorListener implements ANTLRErrorListener {
    private final Collection<? extends ANTLRErrorListener> delegates;

    public ProxyErrorListener(Collection<? extends ANTLRErrorListener> delegates) {
        if (delegates == null) {
            throw new NullPointerException("delegates");
        }
        this.delegates = delegates;
    }

    @Override // org.antlr.v4.runtime.ANTLRErrorListener
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        for (ANTLRErrorListener listener : this.delegates) {
            listener.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
        }
    }

    @Override // org.antlr.v4.runtime.ANTLRErrorListener
    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
        for (ANTLRErrorListener listener : this.delegates) {
            listener.reportAmbiguity(recognizer, dfa, startIndex, stopIndex, exact, ambigAlts, configs);
        }
    }

    @Override // org.antlr.v4.runtime.ANTLRErrorListener
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
        for (ANTLRErrorListener listener : this.delegates) {
            listener.reportAttemptingFullContext(recognizer, dfa, startIndex, stopIndex, conflictingAlts, configs);
        }
    }

    @Override // org.antlr.v4.runtime.ANTLRErrorListener
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
        for (ANTLRErrorListener listener : this.delegates) {
            listener.reportContextSensitivity(recognizer, dfa, startIndex, stopIndex, prediction, configs);
        }
    }
}
