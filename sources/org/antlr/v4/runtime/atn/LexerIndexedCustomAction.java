package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.misc.MurmurHash;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/LexerIndexedCustomAction.class */
public final class LexerIndexedCustomAction implements LexerAction {
    private final int offset;
    private final LexerAction action;

    public LexerIndexedCustomAction(int offset, LexerAction action) {
        this.offset = offset;
        this.action = action;
    }

    public int getOffset() {
        return this.offset;
    }

    public LexerAction getAction() {
        return this.action;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public LexerActionType getActionType() {
        return this.action.getActionType();
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public boolean isPositionDependent() {
        return true;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public void execute(Lexer lexer) {
        this.action.execute(lexer);
    }

    public int hashCode() {
        int hash = MurmurHash.initialize();
        return MurmurHash.finish(MurmurHash.update(MurmurHash.update(hash, this.offset), this.action), 2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LexerIndexedCustomAction)) {
            return false;
        }
        LexerIndexedCustomAction other = (LexerIndexedCustomAction) obj;
        return this.offset == other.offset && this.action.equals(other.action);
    }
}
