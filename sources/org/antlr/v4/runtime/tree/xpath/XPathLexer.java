package org.antlr.v4.runtime.tree.xpath;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.LexerNoViableAltException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.misc.Interval;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/xpath/XPathLexer.class */
public class XPathLexer extends Lexer {
    public static final int TOKEN_REF = 1;
    public static final int RULE_REF = 2;
    public static final int ANYWHERE = 3;
    public static final int ROOT = 4;
    public static final int WILDCARD = 5;
    public static final int BANG = 6;
    public static final int ID = 7;
    public static final int STRING = 8;
    public static String[] modeNames = {"DEFAULT_MODE"};
    public static final String[] ruleNames = {"ANYWHERE", "ROOT", "WILDCARD", "BANG", OperatorName.BEGIN_INLINE_IMAGE_DATA, "NameChar", "NameStartChar", "STRING"};
    private static final String[] _LITERAL_NAMES = {null, null, null, "'//'", "'/'", "'*'", "'!'"};
    private static final String[] _SYMBOLIC_NAMES = {null, "TOKEN_REF", "RULE_REF", "ANYWHERE", "ROOT", "WILDCARD", "BANG", OperatorName.BEGIN_INLINE_IMAGE_DATA, "STRING"};
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    @Deprecated
    public static final String[] tokenNames = new String[_SYMBOLIC_NAMES.length];
    protected int line;
    protected int charPositionInLine;

    static {
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }
            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    @Override // org.antlr.v4.runtime.Recognizer
    public String getGrammarFileName() {
        return "XPathLexer.g4";
    }

    @Override // org.antlr.v4.runtime.Recognizer
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override // org.antlr.v4.runtime.Lexer
    public String[] getModeNames() {
        return modeNames;
    }

    @Override // org.antlr.v4.runtime.Lexer, org.antlr.v4.runtime.Recognizer
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override // org.antlr.v4.runtime.Recognizer
    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override // org.antlr.v4.runtime.Recognizer
    public ATN getATN() {
        return null;
    }

    public XPathLexer(CharStream input) {
        super(input);
        this.line = 1;
        this.charPositionInLine = 0;
    }

    @Override // org.antlr.v4.runtime.Lexer, org.antlr.v4.runtime.TokenSource
    public Token nextToken() {
        this._tokenStartCharIndex = this._input.index();
        CommonToken commonToken = null;
        while (true) {
            CommonToken t = commonToken;
            if (t == null) {
                switch (this._input.LA(1)) {
                    case -1:
                        return new CommonToken(-1, "<EOF>");
                    case 33:
                        consume();
                        commonToken = new CommonToken(6, "!");
                        break;
                    case 39:
                        String s = matchString();
                        commonToken = new CommonToken(8, s);
                        break;
                    case 42:
                        consume();
                        commonToken = new CommonToken(5, "*");
                        break;
                    case 47:
                        consume();
                        if (this._input.LA(1) == 47) {
                            consume();
                            commonToken = new CommonToken(3, "//");
                            break;
                        } else {
                            commonToken = new CommonToken(4, "/");
                            break;
                        }
                    default:
                        if (isNameStartChar(this._input.LA(1))) {
                            String id = matchID();
                            if (!Character.isUpperCase(id.charAt(0))) {
                                commonToken = new CommonToken(2, id);
                                break;
                            } else {
                                commonToken = new CommonToken(1, id);
                                break;
                            }
                        } else {
                            throw new LexerNoViableAltException(this, this._input, this._tokenStartCharIndex, null);
                        }
                }
            } else {
                t.setStartIndex(this._tokenStartCharIndex);
                t.setCharPositionInLine(this._tokenStartCharIndex);
                t.setLine(this.line);
                return t;
            }
        }
    }

    public void consume() {
        int curChar = this._input.LA(1);
        if (curChar == 10) {
            this.line++;
            this.charPositionInLine = 0;
        } else {
            this.charPositionInLine++;
        }
        this._input.consume();
    }

    @Override // org.antlr.v4.runtime.Lexer, org.antlr.v4.runtime.TokenSource
    public int getCharPositionInLine() {
        return this.charPositionInLine;
    }

    public String matchID() {
        int start = this._input.index();
        consume();
        while (isNameChar(this._input.LA(1))) {
            consume();
        }
        return this._input.getText(Interval.of(start, this._input.index() - 1));
    }

    public String matchString() {
        int start = this._input.index();
        consume();
        while (this._input.LA(1) != 39) {
            consume();
        }
        consume();
        return this._input.getText(Interval.of(start, this._input.index() - 1));
    }

    public boolean isNameChar(int c) {
        return Character.isUnicodeIdentifierPart(c);
    }

    public boolean isNameStartChar(int c) {
        return Character.isUnicodeIdentifierStart(c);
    }
}
