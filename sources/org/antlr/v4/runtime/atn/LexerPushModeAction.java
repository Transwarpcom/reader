package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.misc.MurmurHash;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/LexerPushModeAction.class */
public final class LexerPushModeAction implements LexerAction {
    private final int mode;

    public LexerPushModeAction(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return this.mode;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public LexerActionType getActionType() {
        return LexerActionType.PUSH_MODE;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public boolean isPositionDependent() {
        return false;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public void execute(Lexer lexer) {
        lexer.pushMode(this.mode);
    }

    public int hashCode() {
        int hash = MurmurHash.initialize();
        return MurmurHash.finish(MurmurHash.update(MurmurHash.update(hash, getActionType().ordinal()), this.mode), 2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof LexerPushModeAction) && this.mode == ((LexerPushModeAction) obj).mode;
    }

    public String toString() {
        return String.format("pushMode(%d)", Integer.valueOf(this.mode));
    }
}
