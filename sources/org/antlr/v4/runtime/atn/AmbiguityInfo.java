package org.antlr.v4.runtime.atn;

import java.util.BitSet;
import org.antlr.v4.runtime.TokenStream;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/AmbiguityInfo.class */
public class AmbiguityInfo extends DecisionEventInfo {
    public BitSet ambigAlts;

    public AmbiguityInfo(int decision, ATNConfigSet configs, BitSet ambigAlts, TokenStream input, int startIndex, int stopIndex, boolean fullCtx) {
        super(decision, configs, input, startIndex, stopIndex, fullCtx);
        this.ambigAlts = ambigAlts;
    }
}
