package org.antlr.v4.runtime;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.misc.IntegerStack;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.Pair;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/Lexer.class */
public abstract class Lexer extends Recognizer<Integer, LexerATNSimulator> implements TokenSource {
    public static final int DEFAULT_MODE = 0;
    public static final int MORE = -2;
    public static final int SKIP = -3;
    public static final int DEFAULT_TOKEN_CHANNEL = 0;
    public static final int HIDDEN = 1;
    public static final int MIN_CHAR_VALUE = 0;
    public static final int MAX_CHAR_VALUE = 1114111;
    public CharStream _input;
    protected Pair<TokenSource, CharStream> _tokenFactorySourcePair;
    public Token _token;
    public int _tokenStartLine;
    public int _tokenStartCharPositionInLine;
    public boolean _hitEOF;
    public int _channel;
    public int _type;
    public String _text;
    protected TokenFactory<?> _factory = CommonTokenFactory.DEFAULT;
    public int _tokenStartCharIndex = -1;
    public final IntegerStack _modeStack = new IntegerStack();
    public int _mode = 0;

    public Lexer() {
    }

    public Lexer(CharStream input) {
        this._input = input;
        this._tokenFactorySourcePair = new Pair<>(this, input);
    }

    public void reset() {
        if (this._input != null) {
            this._input.seek(0);
        }
        this._token = null;
        this._type = 0;
        this._channel = 0;
        this._tokenStartCharIndex = -1;
        this._tokenStartCharPositionInLine = -1;
        this._tokenStartLine = -1;
        this._text = null;
        this._hitEOF = false;
        this._mode = 0;
        this._modeStack.clear();
        getInterpreter().reset();
    }

    @Override // org.antlr.v4.runtime.TokenSource
    public Token nextToken() {
        int ttype;
        if (this._input == null) {
            throw new IllegalStateException("nextToken requires a non-null input stream.");
        }
        int tokenStartMarker = this._input.mark();
        while (!this._hitEOF) {
            try {
                this._token = null;
                this._channel = 0;
                this._tokenStartCharIndex = this._input.index();
                this._tokenStartCharPositionInLine = getInterpreter().getCharPositionInLine();
                this._tokenStartLine = getInterpreter().getLine();
                this._text = null;
                do {
                    this._type = 0;
                    try {
                        ttype = getInterpreter().match(this._input, this._mode);
                    } catch (LexerNoViableAltException e) {
                        notifyListeners(e);
                        recover(e);
                        ttype = -3;
                    }
                    if (this._input.LA(1) == -1) {
                        this._hitEOF = true;
                    }
                    if (this._type == 0) {
                        this._type = ttype;
                    }
                    if (this._type == -3) {
                        break;
                    }
                } while (this._type == -2);
                if (this._token == null) {
                    emit();
                }
                Token token = this._token;
                this._input.release(tokenStartMarker);
                return token;
            } catch (Throwable th) {
                this._input.release(tokenStartMarker);
                throw th;
            }
        }
        emitEOF();
        Token token2 = this._token;
        this._input.release(tokenStartMarker);
        return token2;
    }

    public void skip() {
        this._type = -3;
    }

    public void more() {
        this._type = -2;
    }

    public void mode(int m) {
        this._mode = m;
    }

    public void pushMode(int m) {
        this._modeStack.push(this._mode);
        mode(m);
    }

    public int popMode() {
        if (this._modeStack.isEmpty()) {
            throw new EmptyStackException();
        }
        mode(this._modeStack.pop());
        return this._mode;
    }

    @Override // org.antlr.v4.runtime.Recognizer, org.antlr.v4.runtime.TokenSource
    public void setTokenFactory(TokenFactory<?> factory) {
        this._factory = factory;
    }

    @Override // org.antlr.v4.runtime.Recognizer, org.antlr.v4.runtime.TokenSource
    public TokenFactory<? extends Token> getTokenFactory() {
        return this._factory;
    }

    @Override // org.antlr.v4.runtime.Recognizer
    public void setInputStream(IntStream input) {
        this._input = null;
        this._tokenFactorySourcePair = new Pair<>(this, this._input);
        reset();
        this._input = (CharStream) input;
        this._tokenFactorySourcePair = new Pair<>(this, this._input);
    }

    @Override // org.antlr.v4.runtime.TokenSource
    public String getSourceName() {
        return this._input.getSourceName();
    }

    @Override // org.antlr.v4.runtime.Recognizer, org.antlr.v4.runtime.TokenSource
    public CharStream getInputStream() {
        return this._input;
    }

    public void emit(Token token) {
        this._token = token;
    }

    public Token emit() {
        Token t = this._factory.create(this._tokenFactorySourcePair, this._type, this._text, this._channel, this._tokenStartCharIndex, getCharIndex() - 1, this._tokenStartLine, this._tokenStartCharPositionInLine);
        emit(t);
        return t;
    }

    public Token emitEOF() {
        int cpos = getCharPositionInLine();
        int line = getLine();
        Token eof = this._factory.create(this._tokenFactorySourcePair, -1, null, 0, this._input.index(), this._input.index() - 1, line, cpos);
        emit(eof);
        return eof;
    }

    @Override // org.antlr.v4.runtime.TokenSource
    public int getLine() {
        return getInterpreter().getLine();
    }

    @Override // org.antlr.v4.runtime.TokenSource
    public int getCharPositionInLine() {
        return getInterpreter().getCharPositionInLine();
    }

    public void setLine(int line) {
        getInterpreter().setLine(line);
    }

    public void setCharPositionInLine(int charPositionInLine) {
        getInterpreter().setCharPositionInLine(charPositionInLine);
    }

    public int getCharIndex() {
        return this._input.index();
    }

    public String getText() {
        if (this._text != null) {
            return this._text;
        }
        return getInterpreter().getText(this._input);
    }

    public void setText(String text) {
        this._text = text;
    }

    public Token getToken() {
        return this._token;
    }

    public void setToken(Token _token) {
        this._token = _token;
    }

    public void setType(int ttype) {
        this._type = ttype;
    }

    public int getType() {
        return this._type;
    }

    public void setChannel(int channel) {
        this._channel = channel;
    }

    public int getChannel() {
        return this._channel;
    }

    public String[] getChannelNames() {
        return null;
    }

    public String[] getModeNames() {
        return null;
    }

    @Override // org.antlr.v4.runtime.Recognizer
    @Deprecated
    public String[] getTokenNames() {
        return null;
    }

    public List<? extends Token> getAllTokens() {
        List<Token> tokens = new ArrayList<>();
        Token tokenNextToken = nextToken();
        while (true) {
            Token t = tokenNextToken;
            if (t.getType() != -1) {
                tokens.add(t);
                tokenNextToken = nextToken();
            } else {
                return tokens;
            }
        }
    }

    public void recover(LexerNoViableAltException e) {
        if (this._input.LA(1) != -1) {
            getInterpreter().consume(this._input);
        }
    }

    public void notifyListeners(LexerNoViableAltException e) {
        String text = this._input.getText(Interval.of(this._tokenStartCharIndex, this._input.index()));
        String msg = "token recognition error at: '" + getErrorDisplay(text) + OperatorName.SHOW_TEXT_LINE;
        ANTLRErrorListener listener = getErrorListenerDispatch();
        listener.syntaxError(this, null, this._tokenStartLine, this._tokenStartCharPositionInLine, msg, e);
    }

    public String getErrorDisplay(String s) {
        StringBuilder buf = new StringBuilder();
        char[] arr$ = s.toCharArray();
        for (char c : arr$) {
            buf.append(getErrorDisplay(c));
        }
        return buf.toString();
    }

    public String getErrorDisplay(int c) {
        String s = String.valueOf((char) c);
        switch (c) {
            case -1:
                s = "<EOF>";
                break;
            case 9:
                s = "\\t";
                break;
            case 10:
                s = "\\n";
                break;
            case 13:
                s = "\\r";
                break;
        }
        return s;
    }

    public String getCharErrorDisplay(int c) {
        String s = getErrorDisplay(c);
        return OperatorName.SHOW_TEXT_LINE + s + OperatorName.SHOW_TEXT_LINE;
    }

    public void recover(RecognitionException re) {
        this._input.consume();
    }
}
