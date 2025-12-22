package org.antlr.v4.runtime.tree;

import java.util.IdentityHashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/ParseTreeProperty.class */
public class ParseTreeProperty<V> {
    protected Map<ParseTree, V> annotations = new IdentityHashMap();

    public V get(ParseTree node) {
        return this.annotations.get(node);
    }

    public void put(ParseTree node, V value) {
        this.annotations.put(node, value);
    }

    public V removeFrom(ParseTree node) {
        return this.annotations.remove(node);
    }
}
