package io.vertx.core.spi.cluster;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/cluster/NodeListener.class */
public interface NodeListener {
    void nodeAdded(String str);

    void nodeLeft(String str);
}
