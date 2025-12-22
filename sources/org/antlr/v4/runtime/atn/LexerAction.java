package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.Lexer;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/LexerAction.class */
public interface LexerAction {
    LexerActionType getActionType();

    boolean isPositionDependent();

    void execute(Lexer lexer);
}
