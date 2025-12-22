package org.antlr.v4.runtime;

import java.util.BitSet;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/ANTLRErrorListener.class */
public interface ANTLRErrorListener {
    void syntaxError(Recognizer<?, ?> recognizer, Object obj, int i, int i2, String str, RecognitionException recognitionException);

    void reportAmbiguity(Parser parser, DFA dfa, int i, int i2, boolean z, BitSet bitSet, ATNConfigSet aTNConfigSet);

    void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i2, BitSet bitSet, ATNConfigSet aTNConfigSet);

    void reportContextSensitivity(Parser parser, DFA dfa, int i, int i2, int i3, ATNConfigSet aTNConfigSet);
}
