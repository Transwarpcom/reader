package io.vertx.core.spi;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/VerticleFactory.class */
public interface VerticleFactory {
    String prefix();

    Verticle createVerticle(String str, ClassLoader classLoader) throws Exception;

    static String removePrefix(String identifer) {
        int pos = identifer.indexOf(58);
        if (pos != -1) {
            if (pos == identifer.length() - 1) {
                throw new IllegalArgumentException("Invalid identifier: " + identifer);
            }
            return identifer.substring(pos + 1);
        }
        return identifer;
    }

    default int order() {
        return 0;
    }

    default boolean requiresResolve() {
        return false;
    }

    default boolean blockingCreate() {
        return false;
    }

    default void resolve(String identifier, DeploymentOptions deploymentOptions, ClassLoader classLoader, Promise<String> resolution) {
        resolution.complete(identifier);
    }

    default void init(Vertx vertx) {
    }

    default void close() {
    }
}
