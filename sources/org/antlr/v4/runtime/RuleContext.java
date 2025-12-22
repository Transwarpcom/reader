package org.antlr.v4.runtime;

import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.Trees;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/RuleContext.class */
public class RuleContext implements RuleNode {
    public static final ParserRuleContext EMPTY = new ParserRuleContext();
    public RuleContext parent;
    public int invokingState;

    public RuleContext() {
        this.invokingState = -1;
    }

    public RuleContext(RuleContext parent, int invokingState) {
        this.invokingState = -1;
        this.parent = parent;
        this.invokingState = invokingState;
    }

    public int depth() {
        int n = 0;
        RuleContext p = this;
        while (p != null) {
            p = p.parent;
            n++;
        }
        return n;
    }

    public boolean isEmpty() {
        return this.invokingState == -1;
    }

    @Override // org.antlr.v4.runtime.tree.SyntaxTree
    public Interval getSourceInterval() {
        return Interval.INVALID;
    }

    @Override // org.antlr.v4.runtime.tree.RuleNode
    public RuleContext getRuleContext() {
        return this;
    }

    @Override // org.antlr.v4.runtime.tree.Tree
    public RuleContext getParent() {
        return this.parent;
    }

    @Override // org.antlr.v4.runtime.tree.Tree
    public RuleContext getPayload() {
        return this;
    }

    @Override // org.antlr.v4.runtime.tree.ParseTree
    public String getText() {
        if (getChildCount() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getChildCount(); i++) {
            builder.append(getChild(i).getText());
        }
        return builder.toString();
    }

    public int getRuleIndex() {
        return -1;
    }

    public int getAltNumber() {
        return 0;
    }

    public void setAltNumber(int altNumber) {
    }

    @Override // org.antlr.v4.runtime.tree.ParseTree
    public void setParent(RuleContext parent) {
        this.parent = parent;
    }

    @Override // org.antlr.v4.runtime.tree.Tree
    public ParseTree getChild(int i) {
        return null;
    }

    @Override // org.antlr.v4.runtime.tree.Tree
    public int getChildCount() {
        return 0;
    }

    @Override // org.antlr.v4.runtime.tree.ParseTree
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
        return visitor.visitChildren(this);
    }

    @Override // org.antlr.v4.runtime.tree.ParseTree
    public String toStringTree(Parser recog) {
        return Trees.toStringTree(this, recog);
    }

    public String toStringTree(List<String> ruleNames) {
        return Trees.toStringTree(this, ruleNames);
    }

    @Override // org.antlr.v4.runtime.tree.Tree
    public String toStringTree() {
        return toStringTree((List<String>) null);
    }

    public String toString() {
        return toString((List<String>) null, (RuleContext) null);
    }

    public final String toString(Recognizer<?, ?> recog) {
        return toString(recog, ParserRuleContext.EMPTY);
    }

    public final String toString(List<String> ruleNames) {
        return toString(ruleNames, (RuleContext) null);
    }

    public String toString(Recognizer<?, ?> recog, RuleContext stop) {
        String[] ruleNames = recog != null ? recog.getRuleNames() : null;
        List<String> ruleNamesList = ruleNames != null ? Arrays.asList(ruleNames) : null;
        return toString(ruleNamesList, stop);
    }

    public String toString(List<String> ruleNames, RuleContext stop) {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (RuleContext p = this; p != null && p != stop; p = p.parent) {
            if (ruleNames == null) {
                if (!p.isEmpty()) {
                    buf.append(p.invokingState);
                }
            } else {
                int ruleIndex = p.getRuleIndex();
                String ruleName = (ruleIndex < 0 || ruleIndex >= ruleNames.size()) ? Integer.toString(ruleIndex) : ruleNames.get(ruleIndex);
                buf.append(ruleName);
            }
            if (p.parent != null && (ruleNames != null || !p.parent.isEmpty())) {
                buf.append(" ");
            }
        }
        buf.append("]");
        return buf.toString();
    }
}
