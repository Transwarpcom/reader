package org.antlr.v4.runtime.tree.xpath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.v4.runtime.tree.Trees;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/xpath/XPathTokenElement.class */
public class XPathTokenElement extends XPathElement {
    protected int tokenType;

    public XPathTokenElement(String tokenName, int tokenType) {
        super(tokenName);
        this.tokenType = tokenType;
    }

    @Override // org.antlr.v4.runtime.tree.xpath.XPathElement
    public Collection<ParseTree> evaluate(ParseTree t) {
        List<ParseTree> nodes = new ArrayList<>();
        for (Tree c : Trees.getChildren(t)) {
            if (c instanceof TerminalNode) {
                TerminalNode tnode = (TerminalNode) c;
                if ((tnode.getSymbol().getType() == this.tokenType && !this.invert) || (tnode.getSymbol().getType() != this.tokenType && this.invert)) {
                    nodes.add(tnode);
                }
            }
        }
        return nodes;
    }
}
