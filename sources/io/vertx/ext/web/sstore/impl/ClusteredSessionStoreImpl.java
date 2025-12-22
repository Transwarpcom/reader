package io.vertx.ext.web.sstore.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.ext.auth.PRNG;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.sstore.AbstractSession;
import io.vertx.ext.web.sstore.ClusteredSessionStore;
import io.vertx.ext.web.sstore.SessionStore;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/sstore/impl/ClusteredSessionStoreImpl.class */
public class ClusteredSessionStoreImpl implements SessionStore, ClusteredSessionStore {
    private static final String DEFAULT_SESSION_MAP_NAME = "vertx-web.sessions";
    private static final long DEFAULT_RETRY_TIMEOUT = 5000;
    private Vertx vertx;
    private PRNG random;
    private String sessionMapName;
    private long retryTimeout;
    private volatile AsyncMap<String, Session> sessionMap;

    @Override // io.vertx.ext.web.sstore.SessionStore
    public SessionStore init(Vertx vertx, JsonObject options) {
        this.vertx = vertx;
        this.sessionMapName = options.getString("mapName", "vertx-web.sessions");
        this.retryTimeout = options.getLong("retryTimeout", 5000L).longValue();
        this.random = new PRNG(vertx);
        return this;
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public long retryTimeout() {
        return this.retryTimeout;
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public Session createSession(long timeout) {
        return new SharedDataSessionImpl(this.random, timeout, 16);
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public Session createSession(long timeout, int length) {
        return new SharedDataSessionImpl(this.random, timeout, length);
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public void get(String id, Handler<AsyncResult<Session>> resultHandler) {
        getMap(res -> {
            if (res.succeeded()) {
                ((AsyncMap) res.result()).get(id, res2 -> {
                    if (res2.succeeded()) {
                        AbstractSession session = (AbstractSession) res2.result();
                        if (session != null) {
                            session.setPRNG(this.random);
                        }
                        resultHandler.handle(Future.succeededFuture(res2.result()));
                        return;
                    }
                    resultHandler.handle(Future.failedFuture(res2.cause()));
                });
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public void delete(String id, Handler<AsyncResult<Void>> resultHandler) {
        getMap(res -> {
            if (res.succeeded()) {
                ((AsyncMap) res.result()).remove(id, res2 -> {
                    if (res2.succeeded()) {
                        resultHandler.handle(Future.succeededFuture());
                    } else {
                        resultHandler.handle(Future.failedFuture(res2.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public void put(Session session, Handler<AsyncResult<Void>> resultHandler) {
        getMap(res -> {
            if (res.succeeded()) {
                ((AsyncMap) res.result()).get(session.id(), old -> {
                    AbstractSession oldSession;
                    AbstractSession newSession = (AbstractSession) session;
                    if (old.succeeded()) {
                        oldSession = (AbstractSession) old.result();
                    } else {
                        oldSession = null;
                    }
                    if (oldSession != null && oldSession.version() != newSession.version()) {
                        resultHandler.handle(Future.failedFuture("Version mismatch"));
                    } else {
                        newSession.incrementVersion();
                        ((AsyncMap) res.result()).put(session.id(), session, session.timeout(), res2 -> {
                            if (res2.succeeded()) {
                                resultHandler.handle(Future.succeededFuture());
                            } else {
                                resultHandler.handle(Future.failedFuture(res2.cause()));
                            }
                        });
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public void clear(Handler<AsyncResult<Void>> resultHandler) {
        getMap(res -> {
            if (res.succeeded()) {
                ((AsyncMap) res.result()).clear(res2 -> {
                    if (res2.succeeded()) {
                        resultHandler.handle(Future.succeededFuture());
                    } else {
                        resultHandler.handle(Future.failedFuture(res2.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public void size(Handler<AsyncResult<Integer>> resultHandler) {
        getMap(res -> {
            if (res.succeeded()) {
                ((AsyncMap) res.result()).size(res2 -> {
                    if (res2.succeeded()) {
                        resultHandler.handle(Future.succeededFuture(res2.result()));
                    } else {
                        resultHandler.handle(Future.failedFuture(res2.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public void close() {
        this.random.close();
    }

    private void getMap(Handler<AsyncResult<AsyncMap<String, Session>>> resultHandler) {
        if (this.sessionMap == null) {
            this.vertx.sharedData().getClusterWideMap(this.sessionMapName, res -> {
                if (res.succeeded()) {
                    this.sessionMap = (AsyncMap) res.result();
                    resultHandler.handle(Future.succeededFuture(res.result()));
                } else {
                    resultHandler.handle(res);
                }
            });
        } else {
            resultHandler.handle(Future.succeededFuture(this.sessionMap));
        }
    }
}
