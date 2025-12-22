package org.antlr.v4.runtime;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/CommonTokenStream.class */
public class CommonTokenStream extends BufferedTokenStream {
    protected int channel;

    public CommonTokenStream(TokenSource tokenSource) {
        super(tokenSource);
        this.channel = 0;
    }

    public CommonTokenStream(TokenSource tokenSource, int channel) {
        this(tokenSource);
        this.channel = channel;
    }

    @Override // org.antlr.v4.runtime.BufferedTokenStream
    protected int adjustSeekIndex(int i) {
        return nextTokenOnChannel(i, this.channel);
    }

    @Override // org.antlr.v4.runtime.BufferedTokenStream
    protected Token LB(int k) {
        if (k == 0 || this.p - k < 0) {
            return null;
        }
        int i = this.p;
        for (int n = 1; n <= k && i > 0; n++) {
            i = previousTokenOnChannel(i - 1, this.channel);
        }
        if (i < 0) {
            return null;
        }
        return this.tokens.get(i);
    }

    @Override // org.antlr.v4.runtime.BufferedTokenStream, org.antlr.v4.runtime.TokenStream
    public Token LT(int k) {
        lazyInit();
        if (k == 0) {
            return null;
        }
        if (k < 0) {
            return LB(-k);
        }
        int i = this.p;
        for (int n = 1; n < k; n++) {
            if (sync(i + 1)) {
                i = nextTokenOnChannel(i + 1, this.channel);
            }
        }
        return this.tokens.get(i);
    }

    public int getNumberOfOnChannelTokens() {
        int n = 0;
        fill();
        for (int i = 0; i < this.tokens.size(); i++) {
            Token t = this.tokens.get(i);
            if (t.getChannel() == this.channel) {
                n++;
            }
            if (t.getType() == -1) {
                break;
            }
        }
        return n;
    }
}
