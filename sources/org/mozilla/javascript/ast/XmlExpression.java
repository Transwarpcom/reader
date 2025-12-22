package org.mozilla.javascript.ast;

import cn.hutool.core.text.StrPool;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ast/XmlExpression.class */
public class XmlExpression extends XmlFragment {
    private AstNode expression;
    private boolean isXmlAttribute;

    public XmlExpression() {
    }

    public XmlExpression(int pos) {
        super(pos);
    }

    public XmlExpression(int pos, int len) {
        super(pos, len);
    }

    public XmlExpression(int pos, AstNode expr) {
        super(pos);
        setExpression(expr);
    }

    public AstNode getExpression() {
        return this.expression;
    }

    public void setExpression(AstNode expression) {
        assertNotNull(expression);
        this.expression = expression;
        expression.setParent(this);
    }

    public boolean isXmlAttribute() {
        return this.isXmlAttribute;
    }

    public void setIsXmlAttribute(boolean isXmlAttribute) {
        this.isXmlAttribute = isXmlAttribute;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int depth) {
        return makeIndent(depth) + StrPool.DELIM_START + this.expression.toSource(depth) + "}";
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.expression.visit(v);
        }
    }
}
