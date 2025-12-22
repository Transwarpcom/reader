package org.antlr.v4.runtime.tree.pattern;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/pattern/ParseTreePattern.class */
public class ParseTreePattern {
    private final int patternRuleIndex;
    private final String pattern;
    private final ParseTree patternTree;
    private final ParseTreePatternMatcher matcher;

    public ParseTreePattern(ParseTreePatternMatcher matcher, String pattern, int patternRuleIndex, ParseTree patternTree) {
        this.matcher = matcher;
        this.patternRuleIndex = patternRuleIndex;
        this.pattern = pattern;
        this.patternTree = patternTree;
    }

    public ParseTreeMatch match(ParseTree tree) {
        return this.matcher.match(tree, this);
    }

    public boolean matches(ParseTree tree) {
        return this.matcher.match(tree, this).succeeded();
    }

    public List<ParseTreeMatch> findAll(ParseTree tree, String xpath) {
        Collection<ParseTree> subtrees = XPath.findAll(tree, xpath, this.matcher.getParser());
        List<ParseTreeMatch> matches = new ArrayList<>();
        for (ParseTree t : subtrees) {
            ParseTreeMatch match = match(t);
            if (match.succeeded()) {
                matches.add(match);
            }
        }
        return matches;
    }

    public ParseTreePatternMatcher getMatcher() {
        return this.matcher;
    }

    public String getPattern() {
        return this.pattern;
    }

    public int getPatternRuleIndex() {
        return this.patternRuleIndex;
    }

    public ParseTree getPatternTree() {
        return this.patternTree;
    }
}
