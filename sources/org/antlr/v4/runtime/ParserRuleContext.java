package org.antlr.v4.runtime;

import cn.hutool.core.text.StrPool;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ErrorNodeImpl;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/ParserRuleContext.class */
public class ParserRuleContext extends RuleContext {
    public List<ParseTree> children;
    public Token start;
    public Token stop;
    public RecognitionException exception;

    public ParserRuleContext() {
    }

    public void copyFrom(ParserRuleContext ctx) {
        this.parent = ctx.parent;
        this.invokingState = ctx.invokingState;
        this.start = ctx.start;
        this.stop = ctx.stop;
        if (ctx.children != null) {
            this.children = new ArrayList();
            for (ParseTree child : ctx.children) {
                if (child instanceof ErrorNode) {
                    addChild((ErrorNode) child);
                }
            }
        }
    }

    public ParserRuleContext(ParserRuleContext parent, int invokingStateNumber) {
        super(parent, invokingStateNumber);
    }

    public void enterRule(ParseTreeListener listener) {
    }

    public void exitRule(ParseTreeListener listener) {
    }

    public <T extends ParseTree> T addAnyChild(T t) {
        if (this.children == null) {
            this.children = new ArrayList();
        }
        this.children.add(t);
        return t;
    }

    public RuleContext addChild(RuleContext ruleInvocation) {
        return (RuleContext) addAnyChild(ruleInvocation);
    }

    public TerminalNode addChild(TerminalNode t) {
        t.setParent(this);
        return (TerminalNode) addAnyChild(t);
    }

    public ErrorNode addErrorNode(ErrorNode errorNode) {
        errorNode.setParent(this);
        return (ErrorNode) addAnyChild(errorNode);
    }

    @Deprecated
    public TerminalNode addChild(Token matchedToken) {
        TerminalNodeImpl t = new TerminalNodeImpl(matchedToken);
        addAnyChild(t);
        t.setParent(this);
        return t;
    }

    @Deprecated
    public ErrorNode addErrorNode(Token badToken) {
        ErrorNodeImpl t = new ErrorNodeImpl(badToken);
        addAnyChild(t);
        t.setParent(this);
        return t;
    }

    public void removeLastChild() {
        if (this.children != null) {
            this.children.remove(this.children.size() - 1);
        }
    }

    @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.Tree
    public ParserRuleContext getParent() {
        return (ParserRuleContext) super.getParent();
    }

    @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.Tree
    public ParseTree getChild(int i) {
        if (this.children == null || i < 0 || i >= this.children.size()) {
            return null;
        }
        return this.children.get(i);
    }

    public <T extends ParseTree> T getChild(Class<? extends T> ctxType, int i) {
        if (this.children == null || i < 0 || i >= this.children.size()) {
            return null;
        }
        int j = -1;
        for (ParseTree o : this.children) {
            if (ctxType.isInstance(o)) {
                j++;
                if (j == i) {
                    return ctxType.cast(o);
                }
            }
        }
        return null;
    }

    public TerminalNode getToken(int ttype, int i) {
        if (this.children == null || i < 0 || i >= this.children.size()) {
            return null;
        }
        int j = -1;
        for (ParseTree o : this.children) {
            if (o instanceof TerminalNode) {
                TerminalNode tnode = (TerminalNode) o;
                Token symbol = tnode.getSymbol();
                if (symbol.getType() == ttype) {
                    j++;
                    if (j == i) {
                        return tnode;
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    public List<TerminalNode> getTokens(int ttype) {
        if (this.children == null) {
            return Collections.emptyList();
        }
        List<TerminalNode> tokens = null;
        for (ParseTree o : this.children) {
            if (o instanceof TerminalNode) {
                TerminalNode tnode = (TerminalNode) o;
                Token symbol = tnode.getSymbol();
                if (symbol.getType() == ttype) {
                    if (tokens == null) {
                        tokens = new ArrayList<>();
                    }
                    tokens.add(tnode);
                }
            }
        }
        if (tokens == null) {
            return Collections.emptyList();
        }
        return tokens;
    }

    public <T extends ParserRuleContext> T getRuleContext(Class<? extends T> ctxType, int i) {
        return (T) getChild(ctxType, i);
    }

    public <T extends ParserRuleContext> List<T> getRuleContexts(Class<? extends T> ctxType) {
        if (this.children == null) {
            return Collections.emptyList();
        }
        List<T> contexts = null;
        for (ParseTree o : this.children) {
            if (ctxType.isInstance(o)) {
                if (contexts == null) {
                    contexts = new ArrayList<>();
                }
                contexts.add(ctxType.cast(o));
            }
        }
        if (contexts == null) {
            return Collections.emptyList();
        }
        return contexts;
    }

    @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.Tree
    public int getChildCount() {
        if (this.children != null) {
            return this.children.size();
        }
        return 0;
    }

    @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.SyntaxTree
    public Interval getSourceInterval() {
        if (this.start == null) {
            return Interval.INVALID;
        }
        if (this.stop == null || this.stop.getTokenIndex() < this.start.getTokenIndex()) {
            return Interval.of(this.start.getTokenIndex(), this.start.getTokenIndex() - 1);
        }
        return Interval.of(this.start.getTokenIndex(), this.stop.getTokenIndex());
    }

    public Token getStart() {
        return this.start;
    }

    public Token getStop() {
        return this.stop;
    }

    public String toInfoString(Parser recognizer) {
        List<String> rules = recognizer.getRuleInvocationStack(this);
        Collections.reverse(rules);
        return "ParserRuleContext" + rules + StrPool.DELIM_START + "start=" + this.start + ", stop=" + this.stop + '}';
    }
}
