package org.antlr.v4.runtime.atn;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/StarLoopbackState.class */
public final class StarLoopbackState extends ATNState {
    public final StarLoopEntryState getLoopEntryState() {
        return (StarLoopEntryState) transition(0).target;
    }

    @Override // org.antlr.v4.runtime.atn.ATNState
    public int getStateType() {
        return 9;
    }
}
