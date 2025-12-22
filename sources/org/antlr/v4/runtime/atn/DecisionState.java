package org.antlr.v4.runtime.atn;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/DecisionState.class */
public abstract class DecisionState extends ATNState {
    public int decision = -1;
    public boolean nonGreedy;
}
