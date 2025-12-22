package org.antlr.v4.runtime.tree;

import org.antlr.v4.runtime.ParserRuleContext;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/ParseTreeWalker.class */
public class ParseTreeWalker {
    public static final ParseTreeWalker DEFAULT = new ParseTreeWalker();

    public void walk(ParseTreeListener listener, ParseTree t) {
        if (t instanceof ErrorNode) {
            listener.visitErrorNode((ErrorNode) t);
            return;
        }
        if (t instanceof TerminalNode) {
            listener.visitTerminal((TerminalNode) t);
            return;
        }
        RuleNode r = (RuleNode) t;
        enterRule(listener, r);
        int n = r.getChildCount();
        for (int i = 0; i < n; i++) {
            walk(listener, r.getChild(i));
        }
        exitRule(listener, r);
    }

    protected void enterRule(ParseTreeListener listener, RuleNode r) {
        ParserRuleContext ctx = (ParserRuleContext) r.getRuleContext();
        listener.enterEveryRule(ctx);
        ctx.enterRule(listener);
    }

    protected void exitRule(ParseTreeListener listener, RuleNode r) {
        ParserRuleContext ctx = (ParserRuleContext) r.getRuleContext();
        ctx.exitRule(listener);
        listener.exitEveryRule(ctx);
    }
}
