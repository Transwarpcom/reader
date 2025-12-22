package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.TokenStream;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/ErrorInfo.class */
public class ErrorInfo extends DecisionEventInfo {
    public ErrorInfo(int decision, ATNConfigSet configs, TokenStream input, int startIndex, int stopIndex, boolean fullCtx) {
        super(decision, configs, input, startIndex, stopIndex, fullCtx);
    }
}
