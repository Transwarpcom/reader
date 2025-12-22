package org.antlr.v4.runtime.atn;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/EpsilonTransition.class */
public final class EpsilonTransition extends Transition {
    private final int outermostPrecedenceReturn;

    public EpsilonTransition(ATNState target) {
        this(target, -1);
    }

    public EpsilonTransition(ATNState target, int outermostPrecedenceReturn) {
        super(target);
        this.outermostPrecedenceReturn = outermostPrecedenceReturn;
    }

    public int outermostPrecedenceReturn() {
        return this.outermostPrecedenceReturn;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public int getSerializationType() {
        return 1;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public boolean isEpsilon() {
        return true;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public boolean matches(int symbol, int minVocabSymbol, int maxVocabSymbol) {
        return false;
    }

    public String toString() {
        return "epsilon";
    }
}
