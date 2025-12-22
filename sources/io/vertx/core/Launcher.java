package io.vertx.core;

import io.vertx.core.impl.launcher.VertxCommandLauncher;
import io.vertx.core.impl.launcher.VertxLifecycleHooks;
import io.vertx.core.json.JsonObject;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/Launcher.class */
public class Launcher extends VertxCommandLauncher implements VertxLifecycleHooks {
    public static void main(String[] args) {
        new Launcher().dispatch(args);
    }

    public static void executeCommand(String cmd, String... args) {
        new Launcher().execute(cmd, args);
    }

    @Override // io.vertx.core.impl.launcher.VertxLifecycleHooks
    public void afterConfigParsed(JsonObject config) {
    }

    @Override // io.vertx.core.impl.launcher.VertxLifecycleHooks
    public void beforeStartingVertx(VertxOptions options) {
    }

    @Override // io.vertx.core.impl.launcher.VertxLifecycleHooks
    public void afterStartingVertx(Vertx vertx) {
    }

    @Override // io.vertx.core.impl.launcher.VertxLifecycleHooks
    public void beforeDeployingVerticle(DeploymentOptions deploymentOptions) {
    }

    @Override // io.vertx.core.impl.launcher.VertxLifecycleHooks
    public void beforeStoppingVertx(Vertx vertx) {
    }

    @Override // io.vertx.core.impl.launcher.VertxLifecycleHooks
    public void afterStoppingVertx() {
    }

    @Override // io.vertx.core.impl.launcher.VertxLifecycleHooks
    public void handleDeployFailed(Vertx vertx, String mainVerticle, DeploymentOptions deploymentOptions, Throwable cause) {
        vertx.close();
    }
}
