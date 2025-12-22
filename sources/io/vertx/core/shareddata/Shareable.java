package io.vertx.core.shareddata;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/Shareable.class */
public interface Shareable {
    default Shareable copy() {
        return this;
    }
}
