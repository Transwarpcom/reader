package org.antlr.v4.runtime.tree;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/Tree.class */
public interface Tree {
    Tree getParent();

    Object getPayload();

    Tree getChild(int i);

    int getChildCount();

    String toStringTree();
}
