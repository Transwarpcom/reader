package org.mozilla.javascript.ast;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ast/EmptyStatement.class */
public class EmptyStatement extends AstNode {
    public EmptyStatement() {
        this.type = 129;
    }

    public EmptyStatement(int pos) {
        super(pos);
        this.type = 129;
    }

    public EmptyStatement(int pos, int len) {
        super(pos, len);
        this.type = 129;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth)).append(";\n");
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor v) {
        v.visit(this);
    }
}
