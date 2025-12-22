package org.mozilla.javascript.ast;

import cn.hutool.core.text.StrPool;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ast/ObjectLiteral.class */
public class ObjectLiteral extends AstNode implements DestructuringForm {
    private static final List<ObjectProperty> NO_ELEMS = Collections.unmodifiableList(new ArrayList());
    private List<ObjectProperty> elements;
    boolean isDestructuring;

    public ObjectLiteral() {
        this.type = 67;
    }

    public ObjectLiteral(int pos) {
        super(pos);
        this.type = 67;
    }

    public ObjectLiteral(int pos, int len) {
        super(pos, len);
        this.type = 67;
    }

    public List<ObjectProperty> getElements() {
        return this.elements != null ? this.elements : NO_ELEMS;
    }

    public void setElements(List<ObjectProperty> elements) {
        if (elements == null) {
            this.elements = null;
            return;
        }
        if (this.elements != null) {
            this.elements.clear();
        }
        for (ObjectProperty o : elements) {
            addElement(o);
        }
    }

    public void addElement(ObjectProperty element) {
        assertNotNull(element);
        if (this.elements == null) {
            this.elements = new ArrayList();
        }
        this.elements.add(element);
        element.setParent(this);
    }

    @Override // org.mozilla.javascript.ast.DestructuringForm
    public void setIsDestructuring(boolean destructuring) {
        this.isDestructuring = destructuring;
    }

    @Override // org.mozilla.javascript.ast.DestructuringForm
    public boolean isDestructuring() {
        return this.isDestructuring;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
        sb.append(StrPool.DELIM_START);
        if (this.elements != null) {
            printList(this.elements, sb);
        }
        sb.append("}");
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            for (ObjectProperty prop : getElements()) {
                prop.visit(v);
            }
        }
    }
}
