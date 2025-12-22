package org.mozilla.javascript.ast;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ast/TemplateCharacters.class */
public class TemplateCharacters extends AstNode {
    private String value;
    private String rawValue;

    public TemplateCharacters() {
        this.type = 171;
    }

    public TemplateCharacters(int pos) {
        super(pos);
        this.type = 171;
    }

    public TemplateCharacters(int pos, int len) {
        super(pos, len);
        this.type = 171;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRawValue() {
        return this.rawValue;
    }

    public void setRawValue(String rawValue) {
        assertNotNull(rawValue);
        this.rawValue = rawValue;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int depth) {
        return makeIndent(depth) + this.rawValue;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor v) {
        v.visit(this);
    }
}
