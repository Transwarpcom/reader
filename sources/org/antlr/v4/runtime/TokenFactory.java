package org.antlr.v4.runtime;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Pair;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/TokenFactory.class */
public interface TokenFactory<Symbol extends Token> {
    Symbol create(Pair<TokenSource, CharStream> pair, int i, String str, int i2, int i3, int i4, int i5, int i6);

    Symbol create(int i, String str);
}
