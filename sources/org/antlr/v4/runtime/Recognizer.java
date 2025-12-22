package org.antlr.v4.runtime;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNSimulator;
import org.antlr.v4.runtime.atn.ParseInfo;
import org.antlr.v4.runtime.misc.Utils;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/Recognizer.class */
public abstract class Recognizer<Symbol, ATNInterpreter extends ATNSimulator> {
    public static final int EOF = -1;
    private static final Map<Vocabulary, Map<String, Integer>> tokenTypeMapCache = new WeakHashMap();
    private static final Map<String[], Map<String, Integer>> ruleIndexMapCache = new WeakHashMap();
    protected ATNInterpreter _interp;
    private List<ANTLRErrorListener> _listeners = new CopyOnWriteArrayList<ANTLRErrorListener>() { // from class: org.antlr.v4.runtime.Recognizer.1
        {
            add(ConsoleErrorListener.INSTANCE);
        }
    };
    private int _stateNumber = -1;

    @Deprecated
    public abstract String[] getTokenNames();

    public abstract String[] getRuleNames();

    public abstract String getGrammarFileName();

    public abstract ATN getATN();

    public abstract IntStream getInputStream();

    public abstract void setInputStream(IntStream intStream);

    public abstract TokenFactory<?> getTokenFactory();

    public abstract void setTokenFactory(TokenFactory<?> tokenFactory);

    public Vocabulary getVocabulary() {
        return VocabularyImpl.fromTokenNames(getTokenNames());
    }

    public Map<String, Integer> getTokenTypeMap() {
        Map<String, Integer> map;
        Vocabulary vocabulary = getVocabulary();
        synchronized (tokenTypeMapCache) {
            Map<String, Integer> result = tokenTypeMapCache.get(vocabulary);
            if (result == null) {
                Map<String, Integer> result2 = new HashMap<>();
                for (int i = 0; i <= getATN().maxTokenType; i++) {
                    String literalName = vocabulary.getLiteralName(i);
                    if (literalName != null) {
                        result2.put(literalName, Integer.valueOf(i));
                    }
                    String symbolicName = vocabulary.getSymbolicName(i);
                    if (symbolicName != null) {
                        result2.put(symbolicName, Integer.valueOf(i));
                    }
                }
                result2.put("EOF", -1);
                result = Collections.unmodifiableMap(result2);
                tokenTypeMapCache.put(vocabulary, result);
            }
            map = result;
        }
        return map;
    }

    public Map<String, Integer> getRuleIndexMap() {
        Map<String, Integer> map;
        String[] ruleNames = getRuleNames();
        if (ruleNames == null) {
            throw new UnsupportedOperationException("The current recognizer does not provide a list of rule names.");
        }
        synchronized (ruleIndexMapCache) {
            Map<String, Integer> result = ruleIndexMapCache.get(ruleNames);
            if (result == null) {
                result = Collections.unmodifiableMap(Utils.toMap(ruleNames));
                ruleIndexMapCache.put(ruleNames, result);
            }
            map = result;
        }
        return map;
    }

    public int getTokenType(String tokenName) {
        Integer ttype = getTokenTypeMap().get(tokenName);
        if (ttype != null) {
            return ttype.intValue();
        }
        return 0;
    }

    public String getSerializedATN() {
        throw new UnsupportedOperationException("there is no serialized ATN");
    }

    public ATNInterpreter getInterpreter() {
        return this._interp;
    }

    public ParseInfo getParseInfo() {
        return null;
    }

    public void setInterpreter(ATNInterpreter interpreter) {
        this._interp = interpreter;
    }

    public String getErrorHeader(RecognitionException e) {
        int line = e.getOffendingToken().getLine();
        int charPositionInLine = e.getOffendingToken().getCharPositionInLine();
        return "line " + line + ":" + charPositionInLine;
    }

    @Deprecated
    public String getTokenErrorDisplay(Token t) {
        if (t == null) {
            return "<no token>";
        }
        String s = t.getText();
        if (s == null) {
            if (t.getType() == -1) {
                s = "<EOF>";
            } else {
                s = "<" + t.getType() + ">";
            }
        }
        return OperatorName.SHOW_TEXT_LINE + s.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t") + OperatorName.SHOW_TEXT_LINE;
    }

    public void addErrorListener(ANTLRErrorListener listener) {
        if (listener == null) {
            throw new NullPointerException("listener cannot be null.");
        }
        this._listeners.add(listener);
    }

    public void removeErrorListener(ANTLRErrorListener listener) {
        this._listeners.remove(listener);
    }

    public void removeErrorListeners() {
        this._listeners.clear();
    }

    public List<? extends ANTLRErrorListener> getErrorListeners() {
        return this._listeners;
    }

    public ANTLRErrorListener getErrorListenerDispatch() {
        return new ProxyErrorListener(getErrorListeners());
    }

    public boolean sempred(RuleContext _localctx, int ruleIndex, int actionIndex) {
        return true;
    }

    public boolean precpred(RuleContext localctx, int precedence) {
        return true;
    }

    public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
    }

    public final int getState() {
        return this._stateNumber;
    }

    public final void setState(int atnState) {
        this._stateNumber = atnState;
    }
}
