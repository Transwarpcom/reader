package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.misc.IntervalSet;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/SetTransition.class */
public class SetTransition extends Transition {
    public final IntervalSet set;

    public SetTransition(ATNState target, IntervalSet set) {
        super(target);
        this.set = set == null ? IntervalSet.of(0) : set;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public int getSerializationType() {
        return 7;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public IntervalSet label() {
        return this.set;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public boolean matches(int symbol, int minVocabSymbol, int maxVocabSymbol) {
        return this.set.contains(symbol);
    }

    public String toString() {
        return this.set.toString();
    }
}
