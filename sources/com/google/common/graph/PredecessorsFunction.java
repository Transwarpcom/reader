package com.google.common.graph;

import com.google.common.annotations.Beta;

@Beta
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/graph/PredecessorsFunction.class */
public interface PredecessorsFunction<N> {
    Iterable<? extends N> predecessors(N n);
}
