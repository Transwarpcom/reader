package org.antlr.v4.runtime.tree;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/TerminalNodeImpl.class */
public class TerminalNodeImpl implements TerminalNode {
    public Token symbol;
    public ParseTree parent;

    public TerminalNodeImpl(Token symbol) {
        this.symbol = symbol;
    }

    @Override // org.antlr.v4.runtime.tree.Tree
    public ParseTree getChild(int i) {
        return null;
    }

    @Override // org.antlr.v4.runtime.tree.TerminalNode
    public Token getSymbol() {
        return this.symbol;
    }

    @Override // org.antlr.v4.runtime.tree.Tree
    public ParseTree getParent() {
        return this.parent;
    }

    @Override // org.antlr.v4.runtime.tree.ParseTree
    public void setParent(RuleContext parent) {
        this.parent = parent;
    }

    @Override // org.antlr.v4.runtime.tree.Tree
    public Token getPayload() {
        return this.symbol;
    }

    @Override // org.antlr.v4.runtime.tree.SyntaxTree
    public Interval getSourceInterval() {
        if (this.symbol == null) {
            return Interval.INVALID;
        }
        int tokenIndex = this.symbol.getTokenIndex();
        return new Interval(tokenIndex, tokenIndex);
    }

    @Override // org.antlr.v4.runtime.tree.Tree
    public int getChildCount() {
        return 0;
    }

    @Override // org.antlr.v4.runtime.tree.ParseTree
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
        return visitor.visitTerminal(this);
    }

    @Override // org.antlr.v4.runtime.tree.ParseTree
    public String getText() {
        return this.symbol.getText();
    }

    @Override // org.antlr.v4.runtime.tree.ParseTree
    public String toStringTree(Parser parser) {
        return toString();
    }

    public String toString() {
        return this.symbol.getType() == -1 ? "<EOF>" : this.symbol.getText();
    }

    @Override // org.antlr.v4.runtime.tree.Tree
    public String toStringTree() {
        return toString();
    }
}
