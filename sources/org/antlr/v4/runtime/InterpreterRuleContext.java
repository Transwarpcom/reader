package org.antlr.v4.runtime;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/InterpreterRuleContext.class */
public class InterpreterRuleContext extends ParserRuleContext {
    protected int ruleIndex;

    public InterpreterRuleContext() {
        this.ruleIndex = -1;
    }

    public InterpreterRuleContext(ParserRuleContext parent, int invokingStateNumber, int ruleIndex) {
        super(parent, invokingStateNumber);
        this.ruleIndex = -1;
        this.ruleIndex = ruleIndex;
    }

    @Override // org.antlr.v4.runtime.RuleContext
    public int getRuleIndex() {
        return this.ruleIndex;
    }
}
