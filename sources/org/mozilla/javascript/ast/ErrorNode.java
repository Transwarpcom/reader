package org.mozilla.javascript.ast;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ast/ErrorNode.class */
public class ErrorNode extends AstNode {
    private String message;

    public ErrorNode() {
        this.type = -1;
    }

    public ErrorNode(int pos) {
        super(pos);
        this.type = -1;
    }

    public ErrorNode(int pos, int len) {
        super(pos, len);
        this.type = -1;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int depth) {
        return "";
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor v) {
        v.visit(this);
    }
}
