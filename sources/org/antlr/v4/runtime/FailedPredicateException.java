package org.antlr.v4.runtime;

import java.util.Locale;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.AbstractPredicateTransition;
import org.antlr.v4.runtime.atn.PredicateTransition;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/FailedPredicateException.class */
public class FailedPredicateException extends RecognitionException {
    private final int ruleIndex;
    private final int predicateIndex;
    private final String predicate;

    public FailedPredicateException(Parser recognizer) {
        this(recognizer, null);
    }

    public FailedPredicateException(Parser recognizer, String predicate) {
        this(recognizer, predicate, null);
    }

    public FailedPredicateException(Parser recognizer, String predicate, String message) {
        super(formatMessage(predicate, message), recognizer, recognizer.getInputStream(), recognizer._ctx);
        ATNState s = recognizer.getInterpreter().atn.states.get(recognizer.getState());
        AbstractPredicateTransition trans = (AbstractPredicateTransition) s.transition(0);
        if (trans instanceof PredicateTransition) {
            this.ruleIndex = ((PredicateTransition) trans).ruleIndex;
            this.predicateIndex = ((PredicateTransition) trans).predIndex;
        } else {
            this.ruleIndex = 0;
            this.predicateIndex = 0;
        }
        this.predicate = predicate;
        setOffendingToken(recognizer.getCurrentToken());
    }

    public int getRuleIndex() {
        return this.ruleIndex;
    }

    public int getPredIndex() {
        return this.predicateIndex;
    }

    public String getPredicate() {
        return this.predicate;
    }

    private static String formatMessage(String predicate, String message) {
        return message != null ? message : String.format(Locale.getDefault(), "failed predicate: {%s}?", predicate);
    }
}
