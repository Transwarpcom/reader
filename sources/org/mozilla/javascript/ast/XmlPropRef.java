package org.mozilla.javascript.ast;

import cn.hutool.core.text.StrPool;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ast/XmlPropRef.class */
public class XmlPropRef extends XmlRef {
    private Name propName;

    public XmlPropRef() {
        this.type = 80;
    }

    public XmlPropRef(int pos) {
        super(pos);
        this.type = 80;
    }

    public XmlPropRef(int pos, int len) {
        super(pos, len);
        this.type = 80;
    }

    public Name getPropName() {
        return this.propName;
    }

    public void setPropName(Name propName) {
        assertNotNull(propName);
        this.propName = propName;
        propName.setParent(this);
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
        if (isAttributeAccess()) {
            sb.append(StrPool.AT);
        }
        if (this.namespace != null) {
            sb.append(this.namespace.toSource(0));
            sb.append("::");
        }
        sb.append(this.propName.toSource(0));
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            if (this.namespace != null) {
                this.namespace.visit(v);
            }
            this.propName.visit(v);
        }
    }
}
