package com.google.common.graph;

import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/graph/BaseGraph.class */
interface BaseGraph<N> extends SuccessorsFunction<N>, PredecessorsFunction<N> {
    Set<N> nodes();

    Set<EndpointPair<N>> edges();

    boolean isDirected();

    boolean allowsSelfLoops();

    ElementOrder<N> nodeOrder();

    Set<N> adjacentNodes(N n);

    Set<N> predecessors(N n);

    @Override // com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    Set<N> successors(N n);

    Set<EndpointPair<N>> incidentEdges(N n);

    int degree(N n);

    int inDegree(N n);

    int outDegree(N n);

    boolean hasEdgeConnecting(N n, N n2);

    boolean hasEdgeConnecting(EndpointPair<N> endpointPair);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    /* bridge */ /* synthetic */ default Iterable successors(Object obj) {
        return successors((BaseGraph<N>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* bridge */ /* synthetic */ default Iterable predecessors(Object obj) {
        return predecessors((BaseGraph<N>) obj);
    }
}
