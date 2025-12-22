package org.antlr.v4.runtime.tree.xpath;

import java.util.ArrayList;
import java.util.Collection;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Trees;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/xpath/XPathWildcardAnywhereElement.class */
public class XPathWildcardAnywhereElement extends XPathElement {
    public XPathWildcardAnywhereElement() {
        super("*");
    }

    @Override // org.antlr.v4.runtime.tree.xpath.XPathElement
    public Collection<ParseTree> evaluate(ParseTree t) {
        return this.invert ? new ArrayList() : Trees.getDescendants(t);
    }
}
