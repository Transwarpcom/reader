package com.google.common.graph;

import com.google.common.annotations.Beta;
import java.util.Set;

@Beta
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/graph/Graph.class */
public interface Graph<N> extends BaseGraph<N> {
    Set<N> nodes();

    @Override // com.google.common.graph.BaseGraph
    Set<EndpointPair<N>> edges();

    boolean isDirected();

    boolean allowsSelfLoops();

    ElementOrder<N> nodeOrder();

    Set<N> adjacentNodes(N n);

    Set<N> predecessors(N n);

    Set<N> successors(N n);

    @Override // com.google.common.graph.BaseGraph
    Set<EndpointPair<N>> incidentEdges(N n);

    @Override // com.google.common.graph.BaseGraph
    int degree(N n);

    @Override // com.google.common.graph.BaseGraph
    int inDegree(N n);

    @Override // com.google.common.graph.BaseGraph
    int outDegree(N n);

    @Override // com.google.common.graph.BaseGraph
    boolean hasEdgeConnecting(N n, N n2);

    @Override // com.google.common.graph.BaseGraph
    boolean hasEdgeConnecting(EndpointPair<N> endpointPair);

    boolean equals(Object obj);

    int hashCode();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    /* bridge */ /* synthetic */ default Iterable successors(Object obj) {
        return successors((Graph<N>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
    /* bridge */ /* synthetic */ default Iterable predecessors(Object obj) {
        return predecessors((Graph<N>) obj);
    }
}
