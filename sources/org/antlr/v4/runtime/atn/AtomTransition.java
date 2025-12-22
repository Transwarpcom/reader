package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.misc.IntervalSet;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/AtomTransition.class */
public final class AtomTransition extends Transition {
    public final int label;

    public AtomTransition(ATNState target, int label) {
        super(target);
        this.label = label;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public int getSerializationType() {
        return 5;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public IntervalSet label() {
        return IntervalSet.of(this.label);
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public boolean matches(int symbol, int minVocabSymbol, int maxVocabSymbol) {
        return this.label == symbol;
    }

    public String toString() {
        return String.valueOf(this.label);
    }
}
