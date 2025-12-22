package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.misc.MurmurHash;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/LexerChannelAction.class */
public final class LexerChannelAction implements LexerAction {
    private final int channel;

    public LexerChannelAction(int channel) {
        this.channel = channel;
    }

    public int getChannel() {
        return this.channel;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public LexerActionType getActionType() {
        return LexerActionType.CHANNEL;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public boolean isPositionDependent() {
        return false;
    }

    @Override // org.antlr.v4.runtime.atn.LexerAction
    public void execute(Lexer lexer) {
        lexer.setChannel(this.channel);
    }

    public int hashCode() {
        int hash = MurmurHash.initialize();
        return MurmurHash.finish(MurmurHash.update(MurmurHash.update(hash, getActionType().ordinal()), this.channel), 2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof LexerChannelAction) && this.channel == ((LexerChannelAction) obj).channel;
    }

    public String toString() {
        return String.format("channel(%d)", Integer.valueOf(this.channel));
    }
}
