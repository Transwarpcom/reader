package io.vertx.ext.web.sstore;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.ServiceHelper;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.sstore.impl.ClusteredSessionStoreImpl;
import io.vertx.ext.web.sstore.impl.LocalSessionStoreImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/sstore/SessionStore.class */
public interface SessionStore {
    public static final int DEFAULT_SESSIONID_LENGTH = 16;

    @Fluent
    SessionStore init(Vertx vertx, JsonObject jsonObject);

    long retryTimeout();

    Session createSession(long j);

    Session createSession(long j, int i);

    void get(String str, Handler<AsyncResult<Session>> handler);

    void delete(String str, Handler<AsyncResult<Void>> handler);

    void put(Session session, Handler<AsyncResult<Void>> handler);

    void clear(Handler<AsyncResult<Void>> handler);

    void size(Handler<AsyncResult<Integer>> handler);

    void close();

    static SessionStore create(Vertx vertx) {
        return create(vertx, new JsonObject());
    }

    static SessionStore create(Vertx vertx, JsonObject options) {
        SessionStore defaultStore;
        try {
            SessionStore defaultStore2 = (SessionStore) ServiceHelper.loadFactoryOrNull(SessionStore.class);
            if (defaultStore2 != null) {
                return defaultStore2.init(vertx, options);
            }
        } catch (RuntimeException e) {
        }
        if (vertx.isClustered()) {
            defaultStore = new ClusteredSessionStoreImpl();
        } else {
            defaultStore = new LocalSessionStoreImpl();
        }
        return defaultStore.init(vertx, options);
    }
}
