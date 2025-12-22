package org.mozilla.javascript.ast;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ast/XmlFragment.class */
public abstract class XmlFragment extends AstNode {
    public XmlFragment() {
        this.type = 146;
    }

    public XmlFragment(int pos) {
        super(pos);
        this.type = 146;
    }

    public XmlFragment(int pos, int len) {
        super(pos, len);
        this.type = 146;
    }
}
