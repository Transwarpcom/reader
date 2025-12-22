package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.atn.SemanticContext;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/PrecedencePredicateTransition.class */
public final class PrecedencePredicateTransition extends AbstractPredicateTransition {
    public final int precedence;

    public PrecedencePredicateTransition(ATNState target, int precedence) {
        super(target);
        this.precedence = precedence;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public int getSerializationType() {
        return 10;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public boolean isEpsilon() {
        return true;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public boolean matches(int symbol, int minVocabSymbol, int maxVocabSymbol) {
        return false;
    }

    public SemanticContext.PrecedencePredicate getPredicate() {
        return new SemanticContext.PrecedencePredicate(this.precedence);
    }

    public String toString() {
        return this.precedence + " >= _p";
    }
}
