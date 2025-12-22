package org.antlr.v4.runtime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.antlr.v4.runtime.misc.Interval;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/BufferedTokenStream.class */
public class BufferedTokenStream implements TokenStream {
    protected TokenSource tokenSource;
    protected List<Token> tokens = new ArrayList(100);
    protected int p = -1;
    protected boolean fetchedEOF;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !BufferedTokenStream.class.desiredAssertionStatus();
    }

    public BufferedTokenStream(TokenSource tokenSource) {
        if (tokenSource == null) {
            throw new NullPointerException("tokenSource cannot be null");
        }
        this.tokenSource = tokenSource;
    }

    @Override // org.antlr.v4.runtime.TokenStream
    public TokenSource getTokenSource() {
        return this.tokenSource;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public int index() {
        return this.p;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public int mark() {
        return 0;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public void release(int marker) {
    }

    @Deprecated
    public void reset() {
        seek(0);
    }

    @Override // org.antlr.v4.runtime.IntStream
    public void seek(int index) {
        lazyInit();
        this.p = adjustSeekIndex(index);
    }

    @Override // org.antlr.v4.runtime.IntStream
    public int size() {
        return this.tokens.size();
    }

    @Override // org.antlr.v4.runtime.IntStream
    public void consume() {
        boolean skipEofCheck;
        if (this.p >= 0) {
            if (this.fetchedEOF) {
                skipEofCheck = this.p < this.tokens.size() - 1;
            } else {
                skipEofCheck = this.p < this.tokens.size();
            }
        } else {
            skipEofCheck = false;
        }
        if (!skipEofCheck && LA(1) == -1) {
            throw new IllegalStateException("cannot consume EOF");
        }
        if (sync(this.p + 1)) {
            this.p = adjustSeekIndex(this.p + 1);
        }
    }

    protected boolean sync(int i) {
        if (!$assertionsDisabled && i < 0) {
            throw new AssertionError();
        }
        int n = (i - this.tokens.size()) + 1;
        if (n > 0) {
            int fetched = fetch(n);
            return fetched >= n;
        }
        return true;
    }

    protected int fetch(int n) {
        if (this.fetchedEOF) {
            return 0;
        }
        for (int i = 0; i < n; i++) {
            Token t = this.tokenSource.nextToken();
            if (t instanceof WritableToken) {
                ((WritableToken) t).setTokenIndex(this.tokens.size());
            }
            this.tokens.add(t);
            if (t.getType() == -1) {
                this.fetchedEOF = true;
                return i + 1;
            }
        }
        return n;
    }

    @Override // org.antlr.v4.runtime.TokenStream
    public Token get(int i) {
        if (i < 0 || i >= this.tokens.size()) {
            throw new IndexOutOfBoundsException("token index " + i + " out of range 0.." + (this.tokens.size() - 1));
        }
        return this.tokens.get(i);
    }

    public List<Token> get(int start, int stop) {
        if (start < 0 || stop < 0) {
            return null;
        }
        lazyInit();
        List<Token> subset = new ArrayList<>();
        if (stop >= this.tokens.size()) {
            stop = this.tokens.size() - 1;
        }
        for (int i = start; i <= stop; i++) {
            Token t = this.tokens.get(i);
            if (t.getType() == -1) {
                break;
            }
            subset.add(t);
        }
        return subset;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public int LA(int i) {
        return LT(i).getType();
    }

    protected Token LB(int k) {
        if (this.p - k < 0) {
            return null;
        }
        return this.tokens.get(this.p - k);
    }

    @Override // org.antlr.v4.runtime.TokenStream
    public Token LT(int k) {
        lazyInit();
        if (k == 0) {
            return null;
        }
        if (k < 0) {
            return LB(-k);
        }
        int i = (this.p + k) - 1;
        sync(i);
        if (i >= this.tokens.size()) {
            return this.tokens.get(this.tokens.size() - 1);
        }
        return this.tokens.get(i);
    }

    protected int adjustSeekIndex(int i) {
        return i;
    }

    protected final void lazyInit() {
        if (this.p == -1) {
            setup();
        }
    }

    protected void setup() {
        sync(0);
        this.p = adjustSeekIndex(0);
    }

    public void setTokenSource(TokenSource tokenSource) {
        this.tokenSource = tokenSource;
        this.tokens.clear();
        this.p = -1;
        this.fetchedEOF = false;
    }

    public List<Token> getTokens() {
        return this.tokens;
    }

    public List<Token> getTokens(int start, int stop) {
        return getTokens(start, stop, (Set<Integer>) null);
    }

    public List<Token> getTokens(int start, int stop, Set<Integer> types) {
        lazyInit();
        if (start < 0 || stop >= this.tokens.size() || stop < 0 || start >= this.tokens.size()) {
            throw new IndexOutOfBoundsException("start " + start + " or stop " + stop + " not in 0.." + (this.tokens.size() - 1));
        }
        if (start > stop) {
            return null;
        }
        List<Token> filteredTokens = new ArrayList<>();
        for (int i = start; i <= stop; i++) {
            Token t = this.tokens.get(i);
            if (types == null || types.contains(Integer.valueOf(t.getType()))) {
                filteredTokens.add(t);
            }
        }
        if (filteredTokens.isEmpty()) {
            filteredTokens = null;
        }
        return filteredTokens;
    }

    public List<Token> getTokens(int start, int stop, int ttype) {
        HashSet<Integer> s = new HashSet<>(ttype);
        s.add(Integer.valueOf(ttype));
        return getTokens(start, stop, s);
    }

    protected int nextTokenOnChannel(int i, int channel) {
        sync(i);
        if (i >= size()) {
            return size() - 1;
        }
        Token token = this.tokens.get(i);
        while (true) {
            Token token2 = token;
            if (token2.getChannel() != channel) {
                if (token2.getType() == -1) {
                    return i;
                }
                i++;
                sync(i);
                token = this.tokens.get(i);
            } else {
                return i;
            }
        }
    }

    protected int previousTokenOnChannel(int i, int channel) {
        sync(i);
        if (i >= size()) {
            return size() - 1;
        }
        while (i >= 0) {
            Token token = this.tokens.get(i);
            if (token.getType() == -1 || token.getChannel() == channel) {
                return i;
            }
            i--;
        }
        return i;
    }

    public List<Token> getHiddenTokensToRight(int tokenIndex, int channel) {
        lazyInit();
        if (tokenIndex < 0 || tokenIndex >= this.tokens.size()) {
            throw new IndexOutOfBoundsException(tokenIndex + " not in 0.." + (this.tokens.size() - 1));
        }
        int nextOnChannel = nextTokenOnChannel(tokenIndex + 1, 0);
        int from = tokenIndex + 1;
        int to = nextOnChannel == -1 ? size() - 1 : nextOnChannel;
        return filterForChannel(from, to, channel);
    }

    public List<Token> getHiddenTokensToRight(int tokenIndex) {
        return getHiddenTokensToRight(tokenIndex, -1);
    }

    public List<Token> getHiddenTokensToLeft(int tokenIndex, int channel) {
        int prevOnChannel;
        lazyInit();
        if (tokenIndex < 0 || tokenIndex >= this.tokens.size()) {
            throw new IndexOutOfBoundsException(tokenIndex + " not in 0.." + (this.tokens.size() - 1));
        }
        if (tokenIndex == 0 || (prevOnChannel = previousTokenOnChannel(tokenIndex - 1, 0)) == tokenIndex - 1) {
            return null;
        }
        int from = prevOnChannel + 1;
        int to = tokenIndex - 1;
        return filterForChannel(from, to, channel);
    }

    public List<Token> getHiddenTokensToLeft(int tokenIndex) {
        return getHiddenTokensToLeft(tokenIndex, -1);
    }

    protected List<Token> filterForChannel(int from, int to, int channel) {
        List<Token> hidden = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            Token t = this.tokens.get(i);
            if (channel == -1) {
                if (t.getChannel() != 0) {
                    hidden.add(t);
                }
            } else if (t.getChannel() == channel) {
                hidden.add(t);
            }
        }
        if (hidden.size() == 0) {
            return null;
        }
        return hidden;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public String getSourceName() {
        return this.tokenSource.getSourceName();
    }

    @Override // org.antlr.v4.runtime.TokenStream
    public String getText() {
        return getText(Interval.of(0, size() - 1));
    }

    @Override // org.antlr.v4.runtime.TokenStream
    public String getText(Interval interval) {
        int start = interval.a;
        int stop = interval.b;
        if (start < 0 || stop < 0) {
            return "";
        }
        fill();
        if (stop >= this.tokens.size()) {
            stop = this.tokens.size() - 1;
        }
        StringBuilder buf = new StringBuilder();
        for (int i = start; i <= stop; i++) {
            Token t = this.tokens.get(i);
            if (t.getType() == -1) {
                break;
            }
            buf.append(t.getText());
        }
        return buf.toString();
    }

    @Override // org.antlr.v4.runtime.TokenStream
    public String getText(RuleContext ctx) {
        return getText(ctx.getSourceInterval());
    }

    @Override // org.antlr.v4.runtime.TokenStream
    public String getText(Token start, Token stop) {
        if (start != null && stop != null) {
            return getText(Interval.of(start.getTokenIndex(), stop.getTokenIndex()));
        }
        return "";
    }

    public void fill() {
        int fetched;
        lazyInit();
        do {
            fetched = fetch(1000);
        } while (fetched >= 1000);
    }
}
