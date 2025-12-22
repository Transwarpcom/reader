package org.antlr.v4.runtime.atn;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/WildcardTransition.class */
public final class WildcardTransition extends Transition {
    public WildcardTransition(ATNState target) {
        super(target);
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public int getSerializationType() {
        return 9;
    }

    @Override // org.antlr.v4.runtime.atn.Transition
    public boolean matches(int symbol, int minVocabSymbol, int maxVocabSymbol) {
        return symbol >= minVocabSymbol && symbol <= maxVocabSymbol;
    }

    public String toString() {
        return ".";
    }
}
