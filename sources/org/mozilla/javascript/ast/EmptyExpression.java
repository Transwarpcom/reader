package org.mozilla.javascript.ast;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ast/EmptyExpression.class */
public class EmptyExpression extends AstNode {
    public EmptyExpression() {
        this.type = 129;
    }

    public EmptyExpression(int pos) {
        super(pos);
        this.type = 129;
    }

    public EmptyExpression(int pos, int len) {
        super(pos, len);
        this.type = 129;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int depth) {
        return makeIndent(depth);
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor v) {
        v.visit(this);
    }
}
