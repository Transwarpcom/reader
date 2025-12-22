package org.antlr.v4.runtime.atn;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/ATNDeserializationOptions.class */
public class ATNDeserializationOptions {
    private static final ATNDeserializationOptions defaultOptions = new ATNDeserializationOptions();
    private boolean readOnly;
    private boolean verifyATN;
    private boolean generateRuleBypassTransitions;

    static {
        defaultOptions.makeReadOnly();
    }

    public ATNDeserializationOptions() {
        this.verifyATN = true;
        this.generateRuleBypassTransitions = false;
    }

    public ATNDeserializationOptions(ATNDeserializationOptions options) {
        this.verifyATN = options.verifyATN;
        this.generateRuleBypassTransitions = options.generateRuleBypassTransitions;
    }

    public static ATNDeserializationOptions getDefaultOptions() {
        return defaultOptions;
    }

    public final boolean isReadOnly() {
        return this.readOnly;
    }

    public final void makeReadOnly() {
        this.readOnly = true;
    }

    public final boolean isVerifyATN() {
        return this.verifyATN;
    }

    public final void setVerifyATN(boolean verifyATN) {
        throwIfReadOnly();
        this.verifyATN = verifyATN;
    }

    public final boolean isGenerateRuleBypassTransitions() {
        return this.generateRuleBypassTransitions;
    }

    public final void setGenerateRuleBypassTransitions(boolean generateRuleBypassTransitions) {
        throwIfReadOnly();
        this.generateRuleBypassTransitions = generateRuleBypassTransitions;
    }

    protected void throwIfReadOnly() {
        if (isReadOnly()) {
            throw new IllegalStateException("The object is read only.");
        }
    }
}
