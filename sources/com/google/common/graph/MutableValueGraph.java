package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@Beta
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/graph/MutableValueGraph.class */
public interface MutableValueGraph<N, V> extends ValueGraph<N, V> {
    @CanIgnoreReturnValue
    boolean addNode(N n);

    @CanIgnoreReturnValue
    V putEdgeValue(N n, N n2, V v);

    @CanIgnoreReturnValue
    V putEdgeValue(EndpointPair<N> endpointPair, V v);

    @CanIgnoreReturnValue
    boolean removeNode(N n);

    @CanIgnoreReturnValue
    V removeEdge(N n, N n2);

    @CanIgnoreReturnValue
    V removeEdge(EndpointPair<N> endpointPair);
}
