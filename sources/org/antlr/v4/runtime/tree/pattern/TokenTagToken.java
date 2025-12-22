package org.antlr.v4.runtime.tree.pattern;

import org.antlr.v4.runtime.CommonToken;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/pattern/TokenTagToken.class */
public class TokenTagToken extends CommonToken {
    private final String tokenName;
    private final String label;

    public TokenTagToken(String tokenName, int type) {
        this(tokenName, type, null);
    }

    public TokenTagToken(String tokenName, int type, String label) {
        super(type);
        this.tokenName = tokenName;
        this.label = label;
    }

    public final String getTokenName() {
        return this.tokenName;
    }

    public final String getLabel() {
        return this.label;
    }

    @Override // org.antlr.v4.runtime.CommonToken, org.antlr.v4.runtime.Token
    public String getText() {
        if (this.label != null) {
            return "<" + this.label + ":" + this.tokenName + ">";
        }
        return "<" + this.tokenName + ">";
    }

    @Override // org.antlr.v4.runtime.CommonToken
    public String toString() {
        return this.tokenName + ":" + this.type;
    }
}
