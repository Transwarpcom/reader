package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.misc.IntervalSet;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/CodePointTransitions.class */
public abstract class CodePointTransitions {
    public static Transition createWithCodePoint(ATNState target, int codePoint) {
        if (Character.isSupplementaryCodePoint(codePoint)) {
            return new SetTransition(target, IntervalSet.of(codePoint));
        }
        return new AtomTransition(target, codePoint);
    }

    public static Transition createWithCodePointRange(ATNState target, int codePointFrom, int codePointTo) {
        if (Character.isSupplementaryCodePoint(codePointFrom) || Character.isSupplementaryCodePoint(codePointTo)) {
            return new SetTransition(target, IntervalSet.of(codePointFrom, codePointTo));
        }
        return new RangeTransition(target, codePointFrom, codePointTo);
    }
}
