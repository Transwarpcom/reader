package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.misc.MurmurHash;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/LexerPopModeAction.class */
public final class LexerPopModeAction implements LexerAction {
    public static final LexerPopModeAction INSTANCE = new LexerPopModeAction();

    private LexerPopModeAction() {
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public LexerActionType getActionType() {
        return LexerActionType.POP_MODE;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public boolean isPositionDependent() {
        return false;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public void execute(Lexer lexer) {
        lexer.popMode();
    }

    public int hashCode() {
        int hash = MurmurHash.initialize();
        return MurmurHash.finish(MurmurHash.update(hash, getActionType().ordinal()), 1);
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public String toString() {
        return "popMode";
    }
}
