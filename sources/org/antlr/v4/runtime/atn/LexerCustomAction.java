package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.misc.MurmurHash;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/LexerCustomAction.class */
public final class LexerCustomAction implements LexerAction {
    private final int ruleIndex;
    private final int actionIndex;

    public LexerCustomAction(int ruleIndex, int actionIndex) {
        this.ruleIndex = ruleIndex;
        this.actionIndex = actionIndex;
    }

    public int getRuleIndex() {
        return this.ruleIndex;
    }

    public int getActionIndex() {
        return this.actionIndex;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public LexerActionType getActionType() {
        return LexerActionType.CUSTOM;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public boolean isPositionDependent() {
        return true;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public void execute(Lexer lexer) {
        lexer.action(null, this.ruleIndex, this.actionIndex);
    }

    public int hashCode() {
        int hash = MurmurHash.initialize();
        return MurmurHash.finish(MurmurHash.update(MurmurHash.update(MurmurHash.update(hash, getActionType().ordinal()), this.ruleIndex), this.actionIndex), 3);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LexerCustomAction)) {
            return false;
        }
        LexerCustomAction other = (LexerCustomAction) obj;
        return this.ruleIndex == other.ruleIndex && this.actionIndex == other.actionIndex;
    }
}
