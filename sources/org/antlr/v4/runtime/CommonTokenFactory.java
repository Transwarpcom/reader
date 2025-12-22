package org.antlr.v4.runtime;

import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.Pair;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/CommonTokenFactory.class */
public class CommonTokenFactory implements TokenFactory<CommonToken> {
    public static final TokenFactory<CommonToken> DEFAULT = new CommonTokenFactory();
    protected final boolean copyText;

    @Override // org.antlr.v4.runtime.TokenFactory
    public /* bridge */ /* synthetic */ Token create(Pair x0, int x1, String x2, int x3, int x4, int x5, int x6, int x7) {
        return create((Pair<TokenSource, CharStream>) x0, x1, x2, x3, x4, x5, x6, x7);
    }

    public CommonTokenFactory(boolean copyText) {
        this.copyText = copyText;
    }

    public CommonTokenFactory() {
        this(false);
    }

    @Override // org.antlr.v4.runtime.TokenFactory
    public CommonToken create(Pair<TokenSource, CharStream> source, int type, String text, int channel, int start, int stop, int line, int charPositionInLine) {
        CommonToken t = new CommonToken(source, type, channel, start, stop);
        t.setLine(line);
        t.setCharPositionInLine(charPositionInLine);
        if (text != null) {
            t.setText(text);
        } else if (this.copyText && source.b != null) {
            t.setText(source.b.getText(Interval.of(start, stop)));
        }
        return t;
    }

    @Override // org.antlr.v4.runtime.TokenFactory
    public CommonToken create(int type, String text) {
        return new CommonToken(type, text);
    }
}
