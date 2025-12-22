package io.vertx.core.impl.launcher;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/VertxLifecycleHooks.class */
public interface VertxLifecycleHooks {
    void afterConfigParsed(JsonObject jsonObject);

    void beforeStartingVertx(VertxOptions vertxOptions);

    void afterStartingVertx(Vertx vertx);

    void beforeDeployingVerticle(DeploymentOptions deploymentOptions);

    void beforeStoppingVertx(Vertx vertx);

    void afterStoppingVertx();

    void handleDeployFailed(Vertx vertx, String str, DeploymentOptions deploymentOptions, Throwable th);
}
