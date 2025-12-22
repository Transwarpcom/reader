package org.antlr.v4.runtime.atn;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/RuleTransition.class */
public final class RuleTransition extends Transition {
    public final int ruleIndex;
    public final int precedence;
    public ATNState followState;

    @Deprecated
    public RuleTransition(RuleStartState ruleStart, int ruleIndex, ATNState followState) {
        this(ruleStart, ruleIndex, 0, followState);
    }

    public RuleTransition(RuleStartState ruleStart, int ruleIndex, int precedence, ATNState followState) {
        super(ruleStart);
        this.ruleIndex = ruleIndex;
        this.precedence = precedence;
        this.followState = followState;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public int getSerializationType() {
        return 3;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public boolean isEpsilon() {
        return true;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public boolean matches(int symbol, int minVocabSymbol, int maxVocabSymbol) {
        return false;
    }
}
