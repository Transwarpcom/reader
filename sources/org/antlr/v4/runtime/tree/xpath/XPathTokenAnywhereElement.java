package org.antlr.v4.runtime.tree.xpath;

import java.util.Collection;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Trees;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/xpath/XPathTokenAnywhereElement.class */
public class XPathTokenAnywhereElement extends XPathElement {
    protected int tokenType;

    public XPathTokenAnywhereElement(String tokenName, int tokenType) {
        super(tokenName);
        this.tokenType = tokenType;
    }

    @Override // org.antlr.v4.runtime.tree.xpath.XPathElement
    public Collection<ParseTree> evaluate(ParseTree t) {
        return Trees.findAllTokenNodes(t, this.tokenType);
    }
}
