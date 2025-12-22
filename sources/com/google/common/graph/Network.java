package com.google.common.graph;

import com.google.common.annotations.Beta;
import java.util.Optional;
import java.util.Set;

@Beta
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/graph/Network.class */
public interface Network<N, E> extends SuccessorsFunction<N>, PredecessorsFunction<N> {
    Set<N> nodes();

    Set<E> edges();

    Graph<N> asGraph();

    boolean isDirected();

    boolean allowsParallelEdges();

    boolean allowsSelfLoops();

    ElementOrder<N> nodeOrder();

    ElementOrder<E> edgeOrder();

    Set<N> adjacentNodes(N n);

    Set<N> predecessors(N n);

    @Override // com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    Set<N> successors(N n);

    Set<E> incidentEdges(N n);

    Set<E> inEdges(N n);

    Set<E> outEdges(N n);

    int degree(N n);

    int inDegree(N n);

    int outDegree(N n);

    EndpointPair<N> incidentNodes(E e);

    Set<E> adjacentEdges(E e);

    Set<E> edgesConnecting(N n, N n2);

    Set<E> edgesConnecting(EndpointPair<N> endpointPair);

    Optional<E> edgeConnecting(N n, N n2);

    Optional<E> edgeConnecting(EndpointPair<N> endpointPair);

    E edgeConnectingOrNull(N n, N n2);

    E edgeConnectingOrNull(EndpointPair<N> endpointPair);

    boolean hasEdgeConnecting(N n, N n2);

    boolean hasEdgeConnecting(EndpointPair<N> endpointPair);

    boolean equals(Object obj);

    int hashCode();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    /* bridge */ /* synthetic */ default Iterable successors(Object obj) {
        return successors((Network<N, E>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* bridge */ /* synthetic */ default Iterable predecessors(Object obj) {
        return predecessors((Network<N, E>) obj);
    }
}
