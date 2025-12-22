package org.antlr.v4.runtime.tree;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RuleContext;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/ParseTree.class */
public interface ParseTree extends SyntaxTree {
    @Override // org.antlr.v4.runtime.tree.Tree
    ParseTree getParent();

    @Override // org.antlr.v4.runtime.tree.Tree
    ParseTree getChild(int i);

    void setParent(RuleContext ruleContext);

    <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor);

    String getText();

    String toStringTree(Parser parser);
}
