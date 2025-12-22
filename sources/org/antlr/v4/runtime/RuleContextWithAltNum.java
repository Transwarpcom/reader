package org.antlr.v4.runtime;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/RuleContextWithAltNum.class */
public class RuleContextWithAltNum extends ParserRuleContext {
    public int altNum;

    public RuleContextWithAltNum() {
        this.altNum = 0;
    }

    public RuleContextWithAltNum(ParserRuleContext parent, int invokingStateNumber) {
        super(parent, invokingStateNumber);
    }

    @Override // org.antlr.v4.runtime.RuleContext
    public int getAltNumber() {
        return this.altNum;
    }

    @Override // org.antlr.v4.runtime.RuleContext
    public void setAltNumber(int altNum) {
        this.altNum = altNum;
    }
}
