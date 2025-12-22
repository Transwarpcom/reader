package com.google.common.graph;

import com.google.common.base.Optional;

/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/graph/AbstractGraphBuilder.class */
abstract class AbstractGraphBuilder<N> {
    final boolean directed;
    boolean allowsSelfLoops = false;
    ElementOrder<N> nodeOrder = ElementOrder.insertion();
    Optional<Integer> expectedNodeCount = Optional.absent();

    AbstractGraphBuilder(boolean directed) {
        this.directed = directed;
    }
}
