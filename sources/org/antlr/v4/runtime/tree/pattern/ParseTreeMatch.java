package org.antlr.v4.runtime.tree.pattern;

import java.util.Collections;
import java.util.List;
import org.antlr.v4.runtime.misc.MultiMap;
import org.antlr.v4.runtime.tree.ParseTree;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/pattern/ParseTreeMatch.class */
public class ParseTreeMatch {
    private final ParseTree tree;
    private final ParseTreePattern pattern;
    private final MultiMap<String, ParseTree> labels;
    private final ParseTree mismatchedNode;

    public ParseTreeMatch(ParseTree tree, ParseTreePattern pattern, MultiMap<String, ParseTree> labels, ParseTree mismatchedNode) {
        if (tree == null) {
            throw new IllegalArgumentException("tree cannot be null");
        }
        if (pattern == null) {
            throw new IllegalArgumentException("pattern cannot be null");
        }
        if (labels == null) {
            throw new IllegalArgumentException("labels cannot be null");
        }
        this.tree = tree;
        this.pattern = pattern;
        this.labels = labels;
        this.mismatchedNode = mismatchedNode;
    }

    public ParseTree get(String label) {
        List<ParseTree> parseTrees = (List) this.labels.get(label);
        if (parseTrees == null || parseTrees.size() == 0) {
            return null;
        }
        return parseTrees.get(parseTrees.size() - 1);
    }

    public List<ParseTree> getAll(String label) {
        List<ParseTree> nodes = (List) this.labels.get(label);
        if (nodes == null) {
            return Collections.emptyList();
        }
        return nodes;
    }

    public MultiMap<String, ParseTree> getLabels() {
        return this.labels;
    }

    public ParseTree getMismatchedNode() {
        return this.mismatchedNode;
    }

    public boolean succeeded() {
        return this.mismatchedNode == null;
    }

    public ParseTreePattern getPattern() {
        return this.pattern;
    }

    public ParseTree getTree() {
        return this.tree;
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = succeeded() ? "succeeded" : "failed";
        objArr[1] = Integer.valueOf(getLabels().size());
        return String.format("Match %s; found %d labels", objArr);
    }
}
