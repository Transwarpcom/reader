package org.antlr.v4.runtime.atn;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/ActionTransition.class */
public final class ActionTransition extends Transition {
    public final int ruleIndex;
    public final int actionIndex;
    public final boolean isCtxDependent;

    public ActionTransition(ATNState target, int ruleIndex) {
        this(target, ruleIndex, -1, false);
    }

    public ActionTransition(ATNState target, int ruleIndex, int actionIndex, boolean isCtxDependent) {
        super(target);
        this.ruleIndex = ruleIndex;
        this.actionIndex = actionIndex;
        this.isCtxDependent = isCtxDependent;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public int getSerializationType() {
        return 6;
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
        return "action_" + this.ruleIndex + ":" + this.actionIndex;
    }
}
