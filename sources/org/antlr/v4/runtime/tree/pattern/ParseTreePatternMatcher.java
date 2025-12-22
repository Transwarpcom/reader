package org.antlr.v4.runtime.tree.pattern;

import cn.hutool.core.text.StrPool;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ListTokenSource;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserInterpreter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.MultiMap;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/pattern/ParseTreePatternMatcher.class */
public class ParseTreePatternMatcher {
    private final Lexer lexer;
    private final Parser parser;
    protected String start = "<";
    protected String stop = ">";
    protected String escape = StrPool.BACKSLASH;

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/pattern/ParseTreePatternMatcher$StartRuleDoesNotConsumeFullPattern.class */
    public static class StartRuleDoesNotConsumeFullPattern extends RuntimeException {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/pattern/ParseTreePatternMatcher$CannotInvokeStartRule.class */
    public static class CannotInvokeStartRule extends RuntimeException {
        public CannotInvokeStartRule(Throwable e) {
            super(e);
        }
    }

    public ParseTreePatternMatcher(Lexer lexer, Parser parser) {
        this.lexer = lexer;
        this.parser = parser;
    }

    public void setDelimiters(String start, String stop, String escapeLeft) {
        if (start == null || start.isEmpty()) {
            throw new IllegalArgumentException("start cannot be null or empty");
        }
        if (stop == null || stop.isEmpty()) {
            throw new IllegalArgumentException("stop cannot be null or empty");
        }
        this.start = start;
        this.stop = stop;
        this.escape = escapeLeft;
    }

    public boolean matches(ParseTree tree, String pattern, int patternRuleIndex) {
        ParseTreePattern p = compile(pattern, patternRuleIndex);
        return matches(tree, p);
    }

    public boolean matches(ParseTree tree, ParseTreePattern pattern) {
        MultiMap<String, ParseTree> labels = new MultiMap<>();
        ParseTree mismatchedNode = matchImpl(tree, pattern.getPatternTree(), labels);
        return mismatchedNode == null;
    }

    public ParseTreeMatch match(ParseTree tree, String pattern, int patternRuleIndex) {
        ParseTreePattern p = compile(pattern, patternRuleIndex);
        return match(tree, p);
    }

    public ParseTreeMatch match(ParseTree tree, ParseTreePattern pattern) {
        MultiMap<String, ParseTree> labels = new MultiMap<>();
        ParseTree mismatchedNode = matchImpl(tree, pattern.getPatternTree(), labels);
        return new ParseTreeMatch(tree, pattern, labels, mismatchedNode);
    }

    public ParseTreePattern compile(String pattern, int patternRuleIndex) {
        List<? extends Token> tokenList = tokenize(pattern);
        ListTokenSource tokenSrc = new ListTokenSource(tokenList);
        CommonTokenStream tokens = new CommonTokenStream(tokenSrc);
        ParserInterpreter parserInterp = new ParserInterpreter(this.parser.getGrammarFileName(), this.parser.getVocabulary(), Arrays.asList(this.parser.getRuleNames()), this.parser.getATNWithBypassAlts(), tokens);
        try {
            parserInterp.setErrorHandler(new BailErrorStrategy());
            ParseTree tree = parserInterp.parse(patternRuleIndex);
            if (tokens.LA(1) != -1) {
                throw new StartRuleDoesNotConsumeFullPattern();
            }
            return new ParseTreePattern(this, pattern, patternRuleIndex, tree);
        } catch (RecognitionException re) {
            throw re;
        } catch (ParseCancellationException e) {
            throw ((RecognitionException) e.getCause());
        } catch (Exception e2) {
            throw new CannotInvokeStartRule(e2);
        }
    }

    public Lexer getLexer() {
        return this.lexer;
    }

    public Parser getParser() {
        return this.parser;
    }

    protected ParseTree matchImpl(ParseTree tree, ParseTree patternTree, MultiMap<String, ParseTree> labels) {
        if (tree == null) {
            throw new IllegalArgumentException("tree cannot be null");
        }
        if (patternTree == null) {
            throw new IllegalArgumentException("patternTree cannot be null");
        }
        if ((tree instanceof TerminalNode) && (patternTree instanceof TerminalNode)) {
            TerminalNode t1 = (TerminalNode) tree;
            TerminalNode t2 = (TerminalNode) patternTree;
            ParseTree mismatchedNode = null;
            if (t1.getSymbol().getType() == t2.getSymbol().getType()) {
                if (t2.getSymbol() instanceof TokenTagToken) {
                    TokenTagToken tokenTagToken = (TokenTagToken) t2.getSymbol();
                    labels.map(tokenTagToken.getTokenName(), tree);
                    if (tokenTagToken.getLabel() != null) {
                        labels.map(tokenTagToken.getLabel(), tree);
                    }
                } else if (!t1.getText().equals(t2.getText()) && 0 == 0) {
                    mismatchedNode = t1;
                }
            } else if (0 == 0) {
                mismatchedNode = t1;
            }
            return mismatchedNode;
        }
        if ((tree instanceof ParserRuleContext) && (patternTree instanceof ParserRuleContext)) {
            ParserRuleContext r1 = (ParserRuleContext) tree;
            ParserRuleContext r2 = (ParserRuleContext) patternTree;
            ParseTree mismatchedNode2 = null;
            RuleTagToken ruleTagToken = getRuleTagToken(r2);
            if (ruleTagToken != null) {
                if (r1.getRuleContext().getRuleIndex() == r2.getRuleContext().getRuleIndex()) {
                    labels.map(ruleTagToken.getRuleName(), tree);
                    if (ruleTagToken.getLabel() != null) {
                        labels.map(ruleTagToken.getLabel(), tree);
                    }
                } else if (0 == 0) {
                    mismatchedNode2 = r1;
                }
                return mismatchedNode2;
            }
            if (r1.getChildCount() != r2.getChildCount()) {
                if (0 == 0) {
                    mismatchedNode2 = r1;
                }
                return mismatchedNode2;
            }
            int n = r1.getChildCount();
            for (int i = 0; i < n; i++) {
                ParseTree childMatch = matchImpl(r1.getChild(i), patternTree.getChild(i), labels);
                if (childMatch != null) {
                    return childMatch;
                }
            }
            return null;
        }
        return tree;
    }

    protected RuleTagToken getRuleTagToken(ParseTree t) {
        if (t instanceof RuleNode) {
            RuleNode r = (RuleNode) t;
            if (r.getChildCount() == 1 && (r.getChild(0) instanceof TerminalNode)) {
                TerminalNode c = (TerminalNode) r.getChild(0);
                if (c.getSymbol() instanceof RuleTagToken) {
                    return (RuleTagToken) c.getSymbol();
                }
                return null;
            }
            return null;
        }
        return null;
    }

    public List<? extends Token> tokenize(String pattern) {
        List<Chunk> chunks = split(pattern);
        List<Token> tokens = new ArrayList<>();
        for (Chunk chunk : chunks) {
            if (chunk instanceof TagChunk) {
                TagChunk tagChunk = (TagChunk) chunk;
                if (Character.isUpperCase(tagChunk.getTag().charAt(0))) {
                    Integer ttype = Integer.valueOf(this.parser.getTokenType(tagChunk.getTag()));
                    if (ttype.intValue() == 0) {
                        throw new IllegalArgumentException("Unknown token " + tagChunk.getTag() + " in pattern: " + pattern);
                    }
                    tokens.add(new TokenTagToken(tagChunk.getTag(), ttype.intValue(), tagChunk.getLabel()));
                } else if (Character.isLowerCase(tagChunk.getTag().charAt(0))) {
                    int ruleIndex = this.parser.getRuleIndex(tagChunk.getTag());
                    if (ruleIndex == -1) {
                        throw new IllegalArgumentException("Unknown rule " + tagChunk.getTag() + " in pattern: " + pattern);
                    }
                    int ruleImaginaryTokenType = this.parser.getATNWithBypassAlts().ruleToTokenType[ruleIndex];
                    tokens.add(new RuleTagToken(tagChunk.getTag(), ruleImaginaryTokenType, tagChunk.getLabel()));
                } else {
                    throw new IllegalArgumentException("invalid tag: " + tagChunk.getTag() + " in pattern: " + pattern);
                }
            } else {
                TextChunk textChunk = (TextChunk) chunk;
                ANTLRInputStream in = new ANTLRInputStream(textChunk.getText());
                this.lexer.setInputStream(in);
                Token tokenNextToken = this.lexer.nextToken();
                while (true) {
                    Token t = tokenNextToken;
                    if (t.getType() != -1) {
                        tokens.add(t);
                        tokenNextToken = this.lexer.nextToken();
                    }
                }
            }
        }
        return tokens;
    }

    public List<Chunk> split(String pattern) {
        int afterLastTag;
        int p = 0;
        int n = pattern.length();
        List<Chunk> chunks = new ArrayList<>();
        new StringBuilder();
        List<Integer> starts = new ArrayList<>();
        List<Integer> stops = new ArrayList<>();
        while (p < n) {
            if (p == pattern.indexOf(this.escape + this.start, p)) {
                p += this.escape.length() + this.start.length();
            } else if (p == pattern.indexOf(this.escape + this.stop, p)) {
                p += this.escape.length() + this.stop.length();
            } else if (p == pattern.indexOf(this.start, p)) {
                starts.add(Integer.valueOf(p));
                p += this.start.length();
            } else if (p == pattern.indexOf(this.stop, p)) {
                stops.add(Integer.valueOf(p));
                p += this.stop.length();
            } else {
                p++;
            }
        }
        if (starts.size() > stops.size()) {
            throw new IllegalArgumentException("unterminated tag in pattern: " + pattern);
        }
        if (starts.size() < stops.size()) {
            throw new IllegalArgumentException("missing start tag in pattern: " + pattern);
        }
        int ntags = starts.size();
        for (int i = 0; i < ntags; i++) {
            if (starts.get(i).intValue() >= stops.get(i).intValue()) {
                throw new IllegalArgumentException("tag delimiters out of order in pattern: " + pattern);
            }
        }
        if (ntags == 0) {
            String text = pattern.substring(0, n);
            chunks.add(new TextChunk(text));
        }
        if (ntags > 0 && starts.get(0).intValue() > 0) {
            String text2 = pattern.substring(0, starts.get(0).intValue());
            chunks.add(new TextChunk(text2));
        }
        for (int i2 = 0; i2 < ntags; i2++) {
            String tag = pattern.substring(starts.get(i2).intValue() + this.start.length(), stops.get(i2).intValue());
            String ruleOrToken = tag;
            String label = null;
            int colon = tag.indexOf(58);
            if (colon >= 0) {
                label = tag.substring(0, colon);
                ruleOrToken = tag.substring(colon + 1, tag.length());
            }
            chunks.add(new TagChunk(label, ruleOrToken));
            if (i2 + 1 < ntags) {
                String text3 = pattern.substring(stops.get(i2).intValue() + this.stop.length(), starts.get(i2 + 1).intValue());
                chunks.add(new TextChunk(text3));
            }
        }
        if (ntags > 0 && (afterLastTag = stops.get(ntags - 1).intValue() + this.stop.length()) < n) {
            String text4 = pattern.substring(afterLastTag, n);
            chunks.add(new TextChunk(text4));
        }
        for (int i3 = 0; i3 < chunks.size(); i3++) {
            Chunk c = chunks.get(i3);
            if (c instanceof TextChunk) {
                TextChunk tc = (TextChunk) c;
                String unescaped = tc.getText().replace(this.escape, "");
                if (unescaped.length() < tc.getText().length()) {
                    chunks.set(i3, new TextChunk(unescaped));
                }
            }
        }
        return chunks;
    }
}
