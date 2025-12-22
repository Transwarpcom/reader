package org.antlr.v4.runtime.tree.xpath;

import java.util.Collection;
import org.antlr.v4.runtime.tree.ParseTree;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/xpath/XPathElement.class */
public abstract class XPathElement {
    protected String nodeName;
    protected boolean invert;

    public abstract Collection<ParseTree> evaluate(ParseTree parseTree);

    public XPathElement(String nodeName) {
        this.nodeName = nodeName;
    }

    public String toString() {
        String inv = this.invert ? "!" : "";
        return getClass().getSimpleName() + "[" + inv + this.nodeName + "]";
    }
}
