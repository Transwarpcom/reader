package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.atn.SemanticContext;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/PredicateTransition.class */
public final class PredicateTransition extends AbstractPredicateTransition {
    public final int ruleIndex;
    public final int predIndex;
    public final boolean isCtxDependent;

    public PredicateTransition(ATNState target, int ruleIndex, int predIndex, boolean isCtxDependent) {
        super(target);
        this.ruleIndex = ruleIndex;
        this.predIndex = predIndex;
        this.isCtxDependent = isCtxDependent;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public int getSerializationType() {
        return 4;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public boolean isEpsilon() {
        return true;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public boolean matches(int symbol, int minVocabSymbol, int maxVocabSymbol) {
        return false;
    }

    public SemanticContext.Predicate getPredicate() {
        return new SemanticContext.Predicate(this.ruleIndex, this.predIndex, this.isCtxDependent);
    }

    public String toString() {
        return "pred_" + this.ruleIndex + ":" + this.predIndex;
    }
}
