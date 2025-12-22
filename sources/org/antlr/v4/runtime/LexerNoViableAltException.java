package org.antlr.v4.runtime;

import java.util.Locale;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.Utils;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/LexerNoViableAltException.class */
public class LexerNoViableAltException extends RecognitionException {
    private final int startIndex;
    private final ATNConfigSet deadEndConfigs;

    public LexerNoViableAltException(Lexer lexer, CharStream input, int startIndex, ATNConfigSet deadEndConfigs) {
        super(lexer, input, null);
        this.startIndex = startIndex;
        this.deadEndConfigs = deadEndConfigs;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public ATNConfigSet getDeadEndConfigs() {
        return this.deadEndConfigs;
    }

    @Override // org.antlr.v4.runtime.RecognitionException
    public CharStream getInputStream() {
        return (CharStream) super.getInputStream();
    }

    @Override // java.lang.Throwable
    public String toString() {
        String symbol = "";
        if (this.startIndex >= 0 && this.startIndex < getInputStream().size()) {
            symbol = Utils.escapeWhitespace(getInputStream().getText(Interval.of(this.startIndex, this.startIndex)), false);
        }
        return String.format(Locale.getDefault(), "%s('%s')", LexerNoViableAltException.class.getSimpleName(), symbol);
    }
}
