package org.antlr.v4.runtime.dfa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.atn.LexerActionExecutor;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.antlr.v4.runtime.misc.MurmurHash;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/dfa/DFAState.class */
public class DFAState {
    public int stateNumber;
    public ATNConfigSet configs;
    public DFAState[] edges;
    public boolean isAcceptState;
    public int prediction;
    public LexerActionExecutor lexerActionExecutor;
    public boolean requiresFullContext;
    public PredPrediction[] predicates;

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/dfa/DFAState$PredPrediction.class */
    public static class PredPrediction {
        public SemanticContext pred;
        public int alt;

        public PredPrediction(SemanticContext pred, int alt) {
            this.alt = alt;
            this.pred = pred;
        }

        public String toString() {
            return "(" + this.pred + ", " + this.alt + ")";
        }
    }

    public DFAState() {
        this.stateNumber = -1;
        this.configs = new ATNConfigSet();
        this.isAcceptState = false;
    }

    public DFAState(int stateNumber) {
        this.stateNumber = -1;
        this.configs = new ATNConfigSet();
        this.isAcceptState = false;
        this.stateNumber = stateNumber;
    }

    public DFAState(ATNConfigSet configs) {
        this.stateNumber = -1;
        this.configs = new ATNConfigSet();
        this.isAcceptState = false;
        this.configs = configs;
    }

    public Set<Integer> getAltSet() {
        Set<Integer> alts = new HashSet<>();
        if (this.configs != null) {
            Iterator i$ = this.configs.iterator();
            while (i$.hasNext()) {
                ATNConfig c = i$.next();
                alts.add(Integer.valueOf(c.alt));
            }
        }
        if (alts.isEmpty()) {
            return null;
        }
        return alts;
    }

    public int hashCode() {
        int hash = MurmurHash.initialize(7);
        return MurmurHash.finish(MurmurHash.update(hash, this.configs.hashCode()), 1);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DFAState)) {
            return false;
        }
        DFAState other = (DFAState) o;
        boolean sameSet = this.configs.equals(other.configs);
        return sameSet;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(this.stateNumber).append(":").append(this.configs);
        if (this.isAcceptState) {
            buf.append(ParameterizedMessage.ERROR_SEPARATOR);
            if (this.predicates != null) {
                buf.append(Arrays.toString(this.predicates));
            } else {
                buf.append(this.prediction);
            }
        }
        return buf.toString();
    }
}
