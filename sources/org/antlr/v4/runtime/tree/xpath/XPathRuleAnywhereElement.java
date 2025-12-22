package org.antlr.v4.runtime.tree.xpath;

import java.util.Collection;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Trees;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/xpath/XPathRuleAnywhereElement.class */
public class XPathRuleAnywhereElement extends XPathElement {
    protected int ruleIndex;

    public XPathRuleAnywhereElement(String ruleName, int ruleIndex) {
        super(ruleName);
        this.ruleIndex = ruleIndex;
    }

    @Override // org.antlr.v4.runtime.tree.xpath.XPathElement
    public Collection<ParseTree> evaluate(ParseTree t) {
        return Trees.findAllRuleNodes(t, this.ruleIndex);
    }
}
