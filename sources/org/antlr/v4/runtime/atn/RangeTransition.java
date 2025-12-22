package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.misc.IntervalSet;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/RangeTransition.class */
public final class RangeTransition extends Transition {
    public final int from;
    public final int to;

    public RangeTransition(ATNState target, int from, int to) {
        super(target);
        this.from = from;
        this.to = to;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public int getSerializationType() {
        return 2;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public IntervalSet label() {
        return IntervalSet.of(this.from, this.to);
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public boolean matches(int symbol, int minVocabSymbol, int maxVocabSymbol) {
        return symbol >= this.from && symbol <= this.to;
    }

    public String toString() {
        return new StringBuilder(OperatorName.SHOW_TEXT_LINE).appendCodePoint(this.from).append("'..'").appendCodePoint(this.to).append(OperatorName.SHOW_TEXT_LINE).toString();
    }
}
