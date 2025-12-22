package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.misc.IntervalSet;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/NotSetTransition.class */
public final class NotSetTransition extends SetTransition {
    public NotSetTransition(ATNState target, IntervalSet set) {
        super(target, set);
    }

    @Override // org.antlr.v4.runtime.atn.SetTransition, org.antlr.v4.runtime.atn.Transition
    public int getSerializationType() {
        return 8;
    }

    @Override // org.antlr.v4.runtime.atn.SetTransition, org.antlr.v4.runtime.atn.Transition
    public boolean matches(int symbol, int minVocabSymbol, int maxVocabSymbol) {
        return symbol >= minVocabSymbol && symbol <= maxVocabSymbol && !super.matches(symbol, minVocabSymbol, maxVocabSymbol);
    }

    @Override // org.antlr.v4.runtime.atn.SetTransition
    public String toString() {
        return '~' + super.toString();
    }
}
