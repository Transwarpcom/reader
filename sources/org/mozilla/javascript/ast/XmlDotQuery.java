package org.mozilla.javascript.ast;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ast/XmlDotQuery.class */
public class XmlDotQuery extends InfixExpression {
    private int rp;

    public XmlDotQuery() {
        this.rp = -1;
        this.type = 147;
    }

    public XmlDotQuery(int pos) {
        super(pos);
        this.rp = -1;
        this.type = 147;
    }

    public XmlDotQuery(int pos, int len) {
        super(pos, len);
        this.rp = -1;
        this.type = 147;
    }

    public int getRp() {
        return this.rp;
    }

    public void setRp(int rp) {
        this.rp = rp;
    }

    @Override // org.mozilla.javascript.ast.InfixExpression, org.mozilla.javascript.ast.AstNode
    public String toSource(int depth) {
        return makeIndent(depth) + getLeft().toSource(0) + ".(" + getRight().toSource(0) + ")";
    }
}
