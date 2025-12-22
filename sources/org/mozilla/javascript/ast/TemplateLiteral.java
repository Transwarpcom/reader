package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ast/TemplateLiteral.class */
public class TemplateLiteral extends AstNode {
    private List<AstNode> elements;

    public TemplateLiteral() {
        this.type = 170;
    }

    public TemplateLiteral(int pos) {
        super(pos);
        this.type = 170;
    }

    public TemplateLiteral(int pos, int len) {
        super(pos, len);
        this.type = 170;
    }

    public List<TemplateCharacters> getTemplateStrings() {
        if (this.elements == null) {
            return Collections.emptyList();
        }
        List<TemplateCharacters> strings = new ArrayList<>();
        for (AstNode e : this.elements) {
            if (e.getType() == 171) {
                strings.add((TemplateCharacters) e);
            }
        }
        return Collections.unmodifiableList(strings);
    }

    public List<AstNode> getSubstitutions() {
        if (this.elements == null) {
            return Collections.emptyList();
        }
        List<AstNode> subs = new ArrayList<>();
        for (AstNode e : this.elements) {
            if (e.getType() != 171) {
                subs.add(e);
            }
        }
        return Collections.unmodifiableList(subs);
    }

    public List<AstNode> getElements() {
        return this.elements == null ? Collections.emptyList() : this.elements;
    }

    public void setElements(List<AstNode> elements) {
        if (elements == null) {
            this.elements = null;
            return;
        }
        if (this.elements != null) {
            this.elements.clear();
        }
        for (AstNode e : elements) {
            addElement(e);
        }
    }

    public void addElement(AstNode element) {
        assertNotNull(element);
        if (this.elements == null) {
            this.elements = new ArrayList();
        }
        this.elements.add(element);
        element.setParent(this);
    }

    public int getSize() {
        if (this.elements == null) {
            return 0;
        }
        return this.elements.size();
    }

    public AstNode getElement(int index) {
        if (this.elements == null) {
            throw new IndexOutOfBoundsException("no elements");
        }
        return this.elements.get(index);
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
        sb.append("`");
        for (AstNode e : getElements()) {
            if (e.getType() == 171) {
                sb.append(e.toSource(0));
            } else {
                sb.append("${").append(e.toSource(0)).append("}");
            }
        }
        sb.append("`");
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            for (AstNode e : getElements()) {
                e.visit(v);
            }
        }
    }
}
