package org.antlr.v4.runtime.tree;

import org.antlr.v4.runtime.ParserRuleContext;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/ParseTreeListener.class */
public interface ParseTreeListener {
    void visitTerminal(TerminalNode terminalNode);

    void visitErrorNode(ErrorNode errorNode);

    void enterEveryRule(ParserRuleContext parserRuleContext);

    void exitEveryRule(ParserRuleContext parserRuleContext);
}
