package org.mozilla.javascript;

import java.util.ArrayList;
import java.util.List;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/NodeTransformer.class */
public class NodeTransformer {
    private ObjArray loops;
    private ObjArray loopEnds;
    private boolean hasFinally;

    public final void transform(ScriptNode tree, CompilerEnvirons env) throws RuntimeException {
        transform(tree, false, env);
    }

    public final void transform(ScriptNode tree, boolean inStrictMode, CompilerEnvirons env) throws RuntimeException {
        boolean useStrictMode = inStrictMode;
        if (env.getLanguageVersion() >= 200 && tree.isInStrictMode()) {
            useStrictMode = true;
        }
        transformCompilationUnit(tree, useStrictMode);
        for (int i = 0; i != tree.getFunctionCount(); i++) {
            FunctionNode fn = tree.getFunctionNode(i);
            transform(fn, useStrictMode, env);
        }
    }

    private void transformCompilationUnit(ScriptNode tree, boolean inStrictMode) throws RuntimeException {
        this.loops = new ObjArray();
        this.loopEnds = new ObjArray();
        this.hasFinally = false;
        boolean createScopeObjects = tree.getType() != 110 || ((FunctionNode) tree).requiresActivation();
        tree.flattenSymbolTable(!createScopeObjects);
        transformCompilationUnit_r(tree, tree, tree, createScopeObjects, inStrictMode);
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x04b5  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0607  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x06da  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x06e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void transformCompilationUnit_r(org.mozilla.javascript.ast.ScriptNode r8, org.mozilla.javascript.Node r9, org.mozilla.javascript.ast.Scope r10, boolean r11, boolean r12) throws java.lang.RuntimeException {
        /*
            Method dump skipped, instructions count: 1774
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NodeTransformer.transformCompilationUnit_r(org.mozilla.javascript.ast.ScriptNode, org.mozilla.javascript.Node, org.mozilla.javascript.ast.Scope, boolean, boolean):void");
    }

    protected void visitNew(Node node, ScriptNode tree) {
    }

    protected void visitCall(Node node, ScriptNode tree) {
    }

    protected Node visitLet(boolean createWith, Node parent, Node previous, Node scopeNode) throws RuntimeException {
        Node result;
        Node vars = scopeNode.getFirstChild();
        Node body = vars.getNext();
        scopeNode.removeChild(vars);
        scopeNode.removeChild(body);
        boolean isExpression = scopeNode.getType() == 159;
        if (createWith) {
            result = replaceCurrent(parent, previous, scopeNode, new Node(isExpression ? 160 : 130));
            ArrayList<Object> list = new ArrayList<>();
            Node objectLiteral = new Node(67);
            Node firstChild = vars.getFirstChild();
            while (true) {
                Node v = firstChild;
                if (v != null) {
                    Node current = v;
                    if (current.getType() == 159) {
                        List<?> destructuringNames = (List) current.getProp(22);
                        Node c = current.getFirstChild();
                        if (c.getType() != 154) {
                            throw Kit.codeBug();
                        }
                        if (isExpression) {
                            body = new Node(90, c.getNext(), body);
                        } else {
                            body = new Node(130, new Node(134, c.getNext()), body);
                        }
                        if (destructuringNames != null) {
                            list.addAll(destructuringNames);
                            for (int i = 0; i < destructuringNames.size(); i++) {
                                objectLiteral.addChildToBack(new Node(127, Node.newNumber(0.0d)));
                            }
                        }
                        current = c.getFirstChild();
                    }
                    if (current.getType() != 39) {
                        throw Kit.codeBug();
                    }
                    list.add(ScriptRuntime.getIndexObject(current.getString()));
                    Node init = current.getFirstChild();
                    if (init == null) {
                        init = new Node(127, Node.newNumber(0.0d));
                    }
                    objectLiteral.addChildToBack(init);
                    firstChild = v.getNext();
                } else {
                    objectLiteral.putProp(12, list.toArray());
                    result.addChildToBack(new Node(2, objectLiteral));
                    result.addChildToBack(new Node(124, body));
                    result.addChildToBack(new Node(3));
                    break;
                }
            }
        } else {
            result = replaceCurrent(parent, previous, scopeNode, new Node(isExpression ? 90 : 130));
            Node newVars = new Node(90);
            Node firstChild2 = vars.getFirstChild();
            while (true) {
                Node v2 = firstChild2;
                if (v2 != null) {
                    Node current2 = v2;
                    if (current2.getType() == 159) {
                        Node c2 = current2.getFirstChild();
                        if (c2.getType() != 154) {
                            throw Kit.codeBug();
                        }
                        if (isExpression) {
                            body = new Node(90, c2.getNext(), body);
                        } else {
                            body = new Node(130, new Node(134, c2.getNext()), body);
                        }
                        Scope.joinScopes((Scope) current2, (Scope) scopeNode);
                        current2 = c2.getFirstChild();
                    }
                    if (current2.getType() != 39) {
                        throw Kit.codeBug();
                    }
                    Node stringNode = Node.newString(current2.getString());
                    stringNode.setScope((Scope) scopeNode);
                    Node init2 = current2.getFirstChild();
                    if (init2 == null) {
                        init2 = new Node(127, Node.newNumber(0.0d));
                    }
                    newVars.addChildToBack(new Node(56, stringNode, init2));
                    firstChild2 = v2.getNext();
                } else if (isExpression) {
                    result.addChildToBack(newVars);
                    scopeNode.setType(90);
                    result.addChildToBack(scopeNode);
                    scopeNode.addChildToBack(body);
                    if (body instanceof Scope) {
                        Scope scopeParent = ((Scope) body).getParentScope();
                        ((Scope) body).setParentScope((Scope) scopeNode);
                        ((Scope) scopeNode).setParentScope(scopeParent);
                    }
                } else {
                    result.addChildToBack(new Node(134, newVars));
                    scopeNode.setType(130);
                    result.addChildToBack(scopeNode);
                    scopeNode.addChildrenToBack(body);
                    if (body instanceof Scope) {
                        Scope scopeParent2 = ((Scope) body).getParentScope();
                        ((Scope) body).setParentScope((Scope) scopeNode);
                        ((Scope) scopeNode).setParentScope(scopeParent2);
                    }
                }
            }
        }
        return result;
    }

    private static Node addBeforeCurrent(Node parent, Node previous, Node current, Node toAdd) throws RuntimeException {
        if (previous == null) {
            if (current != parent.getFirstChild()) {
                Kit.codeBug();
            }
            parent.addChildToFront(toAdd);
        } else {
            if (current != previous.getNext()) {
                Kit.codeBug();
            }
            parent.addChildAfter(toAdd, previous);
        }
        return toAdd;
    }

    private static Node replaceCurrent(Node parent, Node previous, Node current, Node replacement) throws RuntimeException {
        if (previous == null) {
            if (current != parent.getFirstChild()) {
                Kit.codeBug();
            }
            parent.replaceChild(current, replacement);
        } else if (previous.next == current) {
            parent.replaceChildAfter(previous, replacement);
        } else {
            parent.replaceChild(current, replacement);
        }
        return replacement;
    }
}
