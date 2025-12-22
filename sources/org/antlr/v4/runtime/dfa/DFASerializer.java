package org.antlr.v4.runtime.dfa;

import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/dfa/DFASerializer.class */
public class DFASerializer {
    private final DFA dfa;
    private final Vocabulary vocabulary;

    @Deprecated
    public DFASerializer(DFA dfa, String[] tokenNames) {
        this(dfa, VocabularyImpl.fromTokenNames(tokenNames));
    }

    public DFASerializer(DFA dfa, Vocabulary vocabulary) {
        this.dfa = dfa;
        this.vocabulary = vocabulary;
    }

    public String toString() {
        if (this.dfa.s0 == null) {
            return null;
        }
        StringBuilder buf = new StringBuilder();
        List<DFAState> states = this.dfa.getStates();
        for (DFAState s : states) {
            int n = s.edges != null ? s.edges.length : 0;
            for (int i = 0; i < n; i++) {
                DFAState t = s.edges[i];
                if (t != null && t.stateNumber != Integer.MAX_VALUE) {
                    buf.append(getStateString(s));
                    String label = getEdgeLabel(i);
                    buf.append("-").append(label).append("->").append(getStateString(t)).append('\n');
                }
            }
        }
        String output = buf.toString();
        if (output.length() == 0) {
            return null;
        }
        return output;
    }

    protected String getEdgeLabel(int i) {
        return this.vocabulary.getDisplayName(i - 1);
    }

    protected String getStateString(DFAState s) {
        int n = s.stateNumber;
        String baseStateStr = (s.isAcceptState ? ":" : "") + OperatorName.CLOSE_AND_STROKE + n + (s.requiresFullContext ? "^" : "");
        if (s.isAcceptState) {
            if (s.predicates != null) {
                return baseStateStr + ParameterizedMessage.ERROR_SEPARATOR + Arrays.toString(s.predicates);
            }
            return baseStateStr + ParameterizedMessage.ERROR_SEPARATOR + s.prediction;
        }
        return baseStateStr;
    }
}
