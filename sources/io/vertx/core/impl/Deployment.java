package io.vertx.core.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Verticle;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/Deployment.class */
public interface Deployment {
    boolean addChild(Deployment deployment);

    void removeChild(Deployment deployment);

    void undeploy(Handler<AsyncResult<Void>> handler);

    void doUndeploy(ContextInternal contextInternal, Handler<AsyncResult<Void>> handler);

    String deploymentID();

    String verticleIdentifier();

    DeploymentOptions deploymentOptions();

    Set<Verticle> getVerticles();

    boolean isChild();
}
