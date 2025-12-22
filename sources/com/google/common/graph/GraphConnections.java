package com.google.common.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/graph/GraphConnections.class */
interface GraphConnections<N, V> {
    Set<N> adjacentNodes();

    Set<N> predecessors();

    Set<N> successors();

    V value(N n);

    void removePredecessor(N n);

    @CanIgnoreReturnValue
    V removeSuccessor(N n);

    void addPredecessor(N n, V v);

    @CanIgnoreReturnValue
    V addSuccessor(N n, V v);
}
