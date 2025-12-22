package org.antlr.v4.runtime.tree;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/AbstractParseTreeVisitor.class */
public abstract class AbstractParseTreeVisitor<T> implements ParseTreeVisitor<T> {
    @Override // org.antlr.v4.runtime.tree.ParseTreeVisitor
    public T visit(ParseTree parseTree) {
        return (T) parseTree.accept(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.antlr.v4.runtime.tree.ParseTreeVisitor
    public T visitChildren(RuleNode ruleNode) {
        Object objDefaultResult = defaultResult();
        int childCount = ruleNode.getChildCount();
        for (int i = 0; i < childCount && shouldVisitNextChild(ruleNode, objDefaultResult); i++) {
            objDefaultResult = aggregateResult(objDefaultResult, ruleNode.getChild(i).accept(this));
        }
        return (T) objDefaultResult;
    }

    @Override // org.antlr.v4.runtime.tree.ParseTreeVisitor
    public T visitTerminal(TerminalNode node) {
        return defaultResult();
    }

    @Override // org.antlr.v4.runtime.tree.ParseTreeVisitor
    public T visitErrorNode(ErrorNode node) {
        return defaultResult();
    }

    protected T defaultResult() {
        return null;
    }

    protected T aggregateResult(T aggregate, T nextResult) {
        return nextResult;
    }

    protected boolean shouldVisitNextChild(RuleNode node, T currentResult) {
        return true;
    }
}
