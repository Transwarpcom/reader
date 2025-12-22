package org.antlr.v4.runtime.dfa;

import org.antlr.v4.runtime.VocabularyImpl;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/dfa/LexerDFASerializer.class */
public class LexerDFASerializer extends DFASerializer {
    public LexerDFASerializer(DFA dfa) {
        super(dfa, VocabularyImpl.EMPTY_VOCABULARY);
    }

    @Override // org.antlr.v4.runtime.dfa.DFASerializer
    protected String getEdgeLabel(int i) {
        return new StringBuilder(OperatorName.SHOW_TEXT_LINE).appendCodePoint(i).append(OperatorName.SHOW_TEXT_LINE).toString();
    }
}
