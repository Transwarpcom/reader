package cn.hutool.core.lang.tree.parser;

import cn.hutool.core.lang.tree.Tree;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/tree/parser/NodeParser.class */
public interface NodeParser<T, E> {
    void parse(T t, Tree<E> tree);
}
