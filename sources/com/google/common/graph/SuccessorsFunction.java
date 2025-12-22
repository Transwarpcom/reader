package com.google.common.graph;

import com.google.common.annotations.Beta;

@Beta
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/graph/SuccessorsFunction.class */
public interface SuccessorsFunction<N> {
    Iterable<? extends N> successors(N n);
}
