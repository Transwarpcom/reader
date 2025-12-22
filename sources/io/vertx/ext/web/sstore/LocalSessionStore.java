package io.vertx.ext.web.sstore;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.sstore.impl.LocalSessionStoreImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/sstore/LocalSessionStore.class */
public interface LocalSessionStore extends SessionStore {
    public static final long DEFAULT_REAPER_INTERVAL = 1000;
    public static final String DEFAULT_SESSION_MAP_NAME = "vertx-web.sessions";

    static LocalSessionStore create(Vertx vertx) {
        LocalSessionStoreImpl store = new LocalSessionStoreImpl();
        store.init(vertx, new JsonObject().put("reaperInterval", (Long) 1000L).put("mapName", "vertx-web.sessions"));
        return store;
    }

    static LocalSessionStore create(Vertx vertx, String sessionMapName) {
        LocalSessionStoreImpl store = new LocalSessionStoreImpl();
        store.init(vertx, new JsonObject().put("reaperInterval", (Long) 1000L).put("mapName", sessionMapName));
        return store;
    }

    static LocalSessionStore create(Vertx vertx, String sessionMapName, long reaperInterval) {
        LocalSessionStoreImpl store = new LocalSessionStoreImpl();
        store.init(vertx, new JsonObject().put("reaperInterval", Long.valueOf(reaperInterval)).put("mapName", sessionMapName));
        return store;
    }
}
