package io.vertx.ext.web.sstore;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.sstore.impl.ClusteredSessionStoreImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/sstore/ClusteredSessionStore.class */
public interface ClusteredSessionStore extends SessionStore {
    public static final String DEFAULT_SESSION_MAP_NAME = "vertx-web.sessions";
    public static final long DEFAULT_RETRY_TIMEOUT = 5000;

    static ClusteredSessionStore create(Vertx vertx, String sessionMapName) {
        ClusteredSessionStoreImpl store = new ClusteredSessionStoreImpl();
        store.init(vertx, new JsonObject().put("retryTimeout", (Long) 5000L).put("mapName", sessionMapName));
        return store;
    }

    static ClusteredSessionStore create(Vertx vertx, String sessionMapName, long retryTimeout) {
        ClusteredSessionStoreImpl store = new ClusteredSessionStoreImpl();
        store.init(vertx, new JsonObject().put("retryTimeout", Long.valueOf(retryTimeout)).put("mapName", sessionMapName));
        return store;
    }

    static ClusteredSessionStore create(Vertx vertx) {
        ClusteredSessionStoreImpl store = new ClusteredSessionStoreImpl();
        store.init(vertx, new JsonObject().put("retryTimeout", (Long) 5000L).put("mapName", "vertx-web.sessions"));
        return store;
    }

    static ClusteredSessionStore create(Vertx vertx, long retryTimeout) {
        ClusteredSessionStoreImpl store = new ClusteredSessionStoreImpl();
        store.init(vertx, new JsonObject().put("retryTimeout", Long.valueOf(retryTimeout)).put("mapName", "vertx-web.sessions"));
        return store;
    }
}
