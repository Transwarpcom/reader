package org.antlr.v4.runtime.atn;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/StarLoopEntryState.class */
public final class StarLoopEntryState extends DecisionState {
    public StarLoopbackState loopBackState;
    public boolean isPrecedenceDecision;

    @Override // org.antlr.v4.runtime.atn.ATNState
    public int getStateType() {
        return 10;
    }
}
