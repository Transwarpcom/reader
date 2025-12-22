package org.antlr.v4.runtime.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import org.antlr.v4.runtime.misc.IntegerStack;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/IterativeParseTreeWalker.class */
public class IterativeParseTreeWalker extends ParseTreeWalker {
    @Override // org.antlr.v4.runtime.tree.ParseTreeWalker
    public void walk(ParseTreeListener listener, ParseTree parseTree) {
        Deque<ParseTree> nodeStack = new ArrayDeque<>();
        IntegerStack indexStack = new IntegerStack();
        ParseTree currentNode = parseTree;
        int currentIndex = 0;
        while (currentNode != null) {
            if (currentNode instanceof ErrorNode) {
                listener.visitErrorNode((ErrorNode) currentNode);
            } else if (currentNode instanceof TerminalNode) {
                listener.visitTerminal((TerminalNode) currentNode);
            } else {
                RuleNode r = (RuleNode) currentNode;
                enterRule(listener, r);
            }
            if (currentNode.getChildCount() > 0) {
                nodeStack.push(currentNode);
                indexStack.push(currentIndex);
                currentIndex = 0;
                currentNode = currentNode.getChild(0);
            } else {
                while (true) {
                    if (currentNode instanceof RuleNode) {
                        exitRule(listener, (RuleNode) currentNode);
                    }
                    if (nodeStack.isEmpty()) {
                        currentNode = null;
                        currentIndex = 0;
                        break;
                    }
                    currentIndex++;
                    currentNode = nodeStack.peek().getChild(currentIndex);
                    if (currentNode != null) {
                        break;
                    }
                    currentNode = nodeStack.pop();
                    currentIndex = indexStack.pop();
                    if (currentNode == null) {
                        break;
                    }
                }
            }
        }
    }
}
