package org.antlr.v4.runtime.tree;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/ParseTreeVisitor.class */
public interface ParseTreeVisitor<T> {
    T visit(ParseTree parseTree);

    T visitChildren(RuleNode ruleNode);

    T visitTerminal(TerminalNode terminalNode);

    T visitErrorNode(ErrorNode errorNode);
}
