package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/graph/ConfigurableMutableValueGraph.class */
final class ConfigurableMutableValueGraph<N, V> extends ConfigurableValueGraph<N, V> implements MutableValueGraph<N, V> {
    /*  JADX ERROR: Failed to decode insn: 0x0081: MOVE_MULTI
        java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
        	at java.base/java.lang.System.arraycopy(Native Method)
        	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:52)
        	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:137)
        	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
        	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:50)
        	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    @Override // com.google.common.graph.MutableValueGraph
    @com.google.errorprone.annotations.CanIgnoreReturnValue
    public V putEdgeValue(N r7, N r8, V r9) {
        /*
            r6 = this;
            r0 = r7
            java.lang.String r1 = "nodeU"
            java.lang.Object r0 = com.google.common.base.Preconditions.checkNotNull(r0, r1)
            r0 = r8
            java.lang.String r1 = "nodeV"
            java.lang.Object r0 = com.google.common.base.Preconditions.checkNotNull(r0, r1)
            r0 = r9
            java.lang.String r1 = "value"
            java.lang.Object r0 = com.google.common.base.Preconditions.checkNotNull(r0, r1)
            r0 = r6
            boolean r0 = r0.allowsSelfLoops()
            if (r0 != 0) goto L2f
            r0 = r7
            r1 = r8
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L28
            r0 = 1
            goto L29
            r0 = 0
            java.lang.String r1 = "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder."
            r2 = r7
            com.google.common.base.Preconditions.checkArgument(r0, r1, r2)
            r0 = r6
            com.google.common.graph.MapIteratorCache<N, com.google.common.graph.GraphConnections<N, V>> r0 = r0.nodeConnections
            r1 = r7
            java.lang.Object r0 = r0.get(r1)
            com.google.common.graph.GraphConnections r0 = (com.google.common.graph.GraphConnections) r0
            r10 = r0
            r0 = r10
            if (r0 != 0) goto L48
            r0 = r6
            r1 = r7
            com.google.common.graph.GraphConnections r0 = r0.addNodeInternal(r1)
            r10 = r0
            r0 = r10
            r1 = r8
            r2 = r9
            java.lang.Object r0 = r0.addSuccessor(r1, r2)
            r11 = r0
            r0 = r6
            com.google.common.graph.MapIteratorCache<N, com.google.common.graph.GraphConnections<N, V>> r0 = r0.nodeConnections
            r1 = r8
            java.lang.Object r0 = r0.get(r1)
            com.google.common.graph.GraphConnections r0 = (com.google.common.graph.GraphConnections) r0
            r12 = r0
            r0 = r12
            if (r0 != 0) goto L6c
            r0 = r6
            r1 = r8
            com.google.common.graph.GraphConnections r0 = r0.addNodeInternal(r1)
            r12 = r0
            r0 = r12
            r1 = r7
            r2 = r9
            r0.addPredecessor(r1, r2)
            r0 = r11
            if (r0 != 0) goto L89
            r0 = r6
            r1 = r0
            long r1 = r1.edgeCount
            r2 = 1
            long r1 = r1 + r2
            // decode failed: arraycopy: source index -1 out of bounds for object array[6]
            r0.edgeCount = r1
            com.google.common.graph.Graphs.checkPositive(r-1)
            r0 = r11
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.ConfigurableMutableValueGraph.putEdgeValue(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    /*  JADX ERROR: Failed to decode insn: 0x004F: MOVE_MULTI
        java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
        	at java.base/java.lang.System.arraycopy(Native Method)
        	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:52)
        	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:137)
        	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
        	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:50)
        	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    @Override // com.google.common.graph.MutableValueGraph
    @com.google.errorprone.annotations.CanIgnoreReturnValue
    public V removeEdge(N r7, N r8) {
        /*
            r6 = this;
            r0 = r7
            java.lang.String r1 = "nodeU"
            java.lang.Object r0 = com.google.common.base.Preconditions.checkNotNull(r0, r1)
            r0 = r8
            java.lang.String r1 = "nodeV"
            java.lang.Object r0 = com.google.common.base.Preconditions.checkNotNull(r0, r1)
            r0 = r6
            com.google.common.graph.MapIteratorCache<N, com.google.common.graph.GraphConnections<N, V>> r0 = r0.nodeConnections
            r1 = r7
            java.lang.Object r0 = r0.get(r1)
            com.google.common.graph.GraphConnections r0 = (com.google.common.graph.GraphConnections) r0
            r9 = r0
            r0 = r6
            com.google.common.graph.MapIteratorCache<N, com.google.common.graph.GraphConnections<N, V>> r0 = r0.nodeConnections
            r1 = r8
            java.lang.Object r0 = r0.get(r1)
            com.google.common.graph.GraphConnections r0 = (com.google.common.graph.GraphConnections) r0
            r10 = r0
            r0 = r9
            if (r0 == 0) goto L30
            r0 = r10
            if (r0 != 0) goto L32
            r0 = 0
            return r0
            r0 = r9
            r1 = r8
            java.lang.Object r0 = r0.removeSuccessor(r1)
            r11 = r0
            r0 = r11
            if (r0 == 0) goto L57
            r0 = r10
            r1 = r7
            r0.removePredecessor(r1)
            r0 = r6
            r1 = r0
            long r1 = r1.edgeCount
            r2 = 1
            long r1 = r1 - r2
            // decode failed: arraycopy: source index -1 out of bounds for object array[6]
            r0.edgeCount = r1
            com.google.common.graph.Graphs.checkNonNegative(r-1)
            r0 = r11
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.ConfigurableMutableValueGraph.removeEdge(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    ConfigurableMutableValueGraph(AbstractGraphBuilder<? super N> builder) {
        super(builder);
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public boolean addNode(N node) {
        Preconditions.checkNotNull(node, "node");
        if (containsNode(node)) {
            return false;
        }
        addNodeInternal(node);
        return true;
    }

    @CanIgnoreReturnValue
    private GraphConnections<N, V> addNodeInternal(N node) {
        GraphConnections<N, V> connections = newConnections();
        Preconditions.checkState(this.nodeConnections.put(node, connections) == null);
        return connections;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V putEdgeValue(EndpointPair<N> endpoints, V value) {
        validateEndpoints(endpoints);
        return putEdgeValue(endpoints.nodeU(), endpoints.nodeV(), value);
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public boolean removeNode(N node) {
        Preconditions.checkNotNull(node, "node");
        GraphConnections<N, V> connections = this.nodeConnections.get(node);
        if (connections == null) {
            return false;
        }
        if (allowsSelfLoops() && connections.removeSuccessor(node) != null) {
            connections.removePredecessor(node);
            this.edgeCount--;
        }
        for (N successor : connections.successors()) {
            this.nodeConnections.getWithoutCaching(successor).removePredecessor(node);
            this.edgeCount--;
        }
        if (isDirected()) {
            for (N predecessor : connections.predecessors()) {
                Preconditions.checkState(this.nodeConnections.getWithoutCaching(predecessor).removeSuccessor(node) != null);
                this.edgeCount--;
            }
        }
        this.nodeConnections.remove(node);
        Graphs.checkNonNegative(this.edgeCount);
        return true;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V removeEdge(EndpointPair<N> endpoints) {
        validateEndpoints(endpoints);
        return removeEdge(endpoints.nodeU(), endpoints.nodeV());
    }

    private GraphConnections<N, V> newConnections() {
        if (isDirected()) {
            return DirectedGraphConnections.of();
        }
        return UndirectedGraphConnections.of();
    }
}
