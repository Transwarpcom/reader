package org.mozilla.javascript.ast;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ast/TaggedTemplateLiteral.class */
public class TaggedTemplateLiteral extends AstNode {
    private AstNode target;
    private AstNode templateLiteral;

    public TaggedTemplateLiteral() {
        this.type = 173;
    }

    public TaggedTemplateLiteral(int pos) {
        super(pos);
        this.type = 173;
    }

    public TaggedTemplateLiteral(int pos, int len) {
        super(pos, len);
        this.type = 173;
    }

    public AstNode getTarget() {
        return this.target;
    }

    public void setTarget(AstNode target) {
        this.target = target;
    }

    public AstNode getTemplateLiteral() {
        return this.templateLiteral;
    }

    public void setTemplateLiteral(AstNode templateLiteral) {
        this.templateLiteral = templateLiteral;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int depth) {
        return makeIndent(depth) + this.target.toSource(0) + this.templateLiteral.toSource(0);
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.target.visit(v);
            this.templateLiteral.visit(v);
        }
    }
}
