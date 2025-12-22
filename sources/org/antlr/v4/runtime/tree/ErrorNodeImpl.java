package org.antlr.v4.runtime.tree;

import org.antlr.v4.runtime.Token;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/ErrorNodeImpl.class */
public class ErrorNodeImpl extends TerminalNodeImpl implements ErrorNode {
    public ErrorNodeImpl(Token token) {
        super(token);
    }

    @Override // org.antlr.v4.runtime.tree.TerminalNodeImpl, org.antlr.v4.runtime.tree.ParseTree
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
        return visitor.visitErrorNode(this);
    }
}
