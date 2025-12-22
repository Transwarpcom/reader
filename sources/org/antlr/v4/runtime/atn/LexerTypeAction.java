package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.misc.MurmurHash;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/LexerTypeAction.class */
public class LexerTypeAction implements LexerAction {
    private final int type;

    public LexerTypeAction(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public LexerActionType getActionType() {
        return LexerActionType.TYPE;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public boolean isPositionDependent() {
        return false;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public void execute(Lexer lexer) {
        lexer.setType(this.type);
    }

    public int hashCode() {
        int hash = MurmurHash.initialize();
        return MurmurHash.finish(MurmurHash.update(MurmurHash.update(hash, getActionType().ordinal()), this.type), 2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof LexerTypeAction) && this.type == ((LexerTypeAction) obj).type;
    }

    public String toString() {
        return String.format("type(%d)", Integer.valueOf(this.type));
    }
}
