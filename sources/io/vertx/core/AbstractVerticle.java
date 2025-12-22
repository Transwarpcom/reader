package io.vertx.core;

import io.vertx.core.json.JsonObject;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/AbstractVerticle.class */
public abstract class AbstractVerticle implements Verticle {
    protected Vertx vertx;
    protected Context context;

    @Override // io.vertx.core.Verticle
    public Vertx getVertx() {
        return this.vertx;
    }

    @Override // io.vertx.core.Verticle
    public void init(Vertx vertx, Context context) {
        this.vertx = vertx;
        this.context = context;
    }

    public String deploymentID() {
        return this.context.deploymentID();
    }

    public JsonObject config() {
        return this.context.config();
    }

    public List<String> processArgs() {
        return this.context.processArgs();
    }

    @Override // io.vertx.core.Verticle
    public void start(Future<Void> startFuture) throws Exception {
        start();
        startFuture.complete();
    }

    @Override // io.vertx.core.Verticle
    public void stop(Future<Void> stopFuture) throws Exception {
        stop();
        stopFuture.complete();
    }

    public void start() throws Exception {
    }

    public void stop() throws Exception {
    }
}
