package org.antlr.v4.runtime;

import java.util.ArrayList;
import java.util.Collection;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNType;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/LexerInterpreter.class */
public class LexerInterpreter extends Lexer {
    protected final String grammarFileName;
    protected final ATN atn;

    @Deprecated
    protected final String[] tokenNames;
    protected final String[] ruleNames;
    protected final String[] channelNames;
    protected final String[] modeNames;
    private final Vocabulary vocabulary;
    protected final DFA[] _decisionToDFA;
    protected final PredictionContextCache _sharedContextCache;

    @Deprecated
    public LexerInterpreter(String grammarFileName, Collection<String> tokenNames, Collection<String> ruleNames, Collection<String> modeNames, ATN atn, CharStream input) {
        this(grammarFileName, VocabularyImpl.fromTokenNames((String[]) tokenNames.toArray(new String[tokenNames.size()])), ruleNames, new ArrayList(), modeNames, atn, input);
    }

    @Deprecated
    public LexerInterpreter(String grammarFileName, Vocabulary vocabulary, Collection<String> ruleNames, Collection<String> modeNames, ATN atn, CharStream input) {
        this(grammarFileName, vocabulary, ruleNames, new ArrayList(), modeNames, atn, input);
    }

    public LexerInterpreter(String grammarFileName, Vocabulary vocabulary, Collection<String> ruleNames, Collection<String> channelNames, Collection<String> modeNames, ATN atn, CharStream input) {
        super(input);
        this._sharedContextCache = new PredictionContextCache();
        if (atn.grammarType != ATNType.LEXER) {
            throw new IllegalArgumentException("The ATN must be a lexer ATN.");
        }
        this.grammarFileName = grammarFileName;
        this.atn = atn;
        this.tokenNames = new String[atn.maxTokenType];
        for (int i = 0; i < this.tokenNames.length; i++) {
            this.tokenNames[i] = vocabulary.getDisplayName(i);
        }
        this.ruleNames = (String[]) ruleNames.toArray(new String[ruleNames.size()]);
        this.channelNames = (String[]) channelNames.toArray(new String[channelNames.size()]);
        this.modeNames = (String[]) modeNames.toArray(new String[modeNames.size()]);
        this.vocabulary = vocabulary;
        this._decisionToDFA = new DFA[atn.getNumberOfDecisions()];
        for (int i2 = 0; i2 < this._decisionToDFA.length; i2++) {
            this._decisionToDFA[i2] = new DFA(atn.getDecisionState(i2), i2);
        }
        this._interp = new LexerATNSimulator(this, atn, this._decisionToDFA, this._sharedContextCache);
    }

    @Override // org.antlr.v4.runtime.Recognizer
    public ATN getATN() {
        return this.atn;
    }

    @Override // org.antlr.v4.runtime.Recognizer
    public String getGrammarFileName() {
        return this.grammarFileName;
    }

    @Override // org.antlr.v4.runtime.Lexer, org.antlr.v4.runtime.Recognizer
    @Deprecated
    public String[] getTokenNames() {
        return this.tokenNames;
    }

    @Override // org.antlr.v4.runtime.Recognizer
    public String[] getRuleNames() {
        return this.ruleNames;
    }

    @Override // org.antlr.v4.runtime.Lexer
    public String[] getChannelNames() {
        return this.channelNames;
    }

    @Override // org.antlr.v4.runtime.Lexer
    public String[] getModeNames() {
        return this.modeNames;
    }

    @Override // org.antlr.v4.runtime.Recognizer
    public Vocabulary getVocabulary() {
        if (this.vocabulary != null) {
            return this.vocabulary;
        }
        return super.getVocabulary();
    }
}
