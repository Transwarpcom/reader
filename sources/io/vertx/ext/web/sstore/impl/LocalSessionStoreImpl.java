package io.vertx.ext.web.sstore.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.ext.auth.PRNG;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.sstore.AbstractSession;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.sstore.SessionStore;
import java.util.HashSet;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/sstore/impl/LocalSessionStoreImpl.class */
public class LocalSessionStoreImpl implements SessionStore, LocalSessionStore, Handler<Long> {
    private static final long DEFAULT_REAPER_INTERVAL = 1000;
    private static final String DEFAULT_SESSION_MAP_NAME = "vertx-web.sessions";
    private LocalMap<String, Session> localMap;
    private long reaperInterval;
    private PRNG random;
    private long timerID = -1;
    private boolean closed;
    protected Vertx vertx;

    @Override // io.vertx.ext.web.sstore.SessionStore
    public Session createSession(long timeout) {
        return new SharedDataSessionImpl(this.random, timeout, 16);
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public Session createSession(long timeout, int length) {
        return new SharedDataSessionImpl(this.random, timeout, length);
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public SessionStore init(Vertx vertx, JsonObject options) {
        this.random = new PRNG(vertx);
        this.vertx = vertx;
        this.reaperInterval = options.getLong("reaperInterval", 1000L).longValue();
        this.localMap = vertx.sharedData().getLocalMap(options.getString("mapName", "vertx-web.sessions"));
        setTimer();
        return this;
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public long retryTimeout() {
        return 0L;
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public void get(String id, Handler<AsyncResult<Session>> resultHandler) {
        resultHandler.handle(Future.succeededFuture(this.localMap.get(id)));
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public void delete(String id, Handler<AsyncResult<Void>> resultHandler) {
        this.localMap.remove(id);
        resultHandler.handle(Future.succeededFuture());
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public void put(Session session, Handler<AsyncResult<Void>> resultHandler) {
        AbstractSession oldSession = (AbstractSession) this.localMap.get(session.id());
        AbstractSession newSession = (AbstractSession) session;
        if (oldSession != null && oldSession.version() != newSession.version()) {
            resultHandler.handle(Future.failedFuture("Version mismatch"));
            return;
        }
        newSession.incrementVersion();
        this.localMap.put(session.id(), session);
        resultHandler.handle(Future.succeededFuture());
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public void clear(Handler<AsyncResult<Void>> resultHandler) {
        this.localMap.clear();
        resultHandler.handle(Future.succeededFuture());
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public void size(Handler<AsyncResult<Integer>> resultHandler) {
        resultHandler.handle(Future.succeededFuture(Integer.valueOf(this.localMap.size())));
    }

    @Override // io.vertx.ext.web.sstore.SessionStore
    public synchronized void close() {
        this.localMap.close();
        if (this.timerID != -1) {
            this.vertx.cancelTimer(this.timerID);
        }
        this.random.close();
        this.closed = true;
    }

    @Override // io.vertx.core.Handler
    public synchronized void handle(Long tid) {
        long now = System.currentTimeMillis();
        Set<String> toRemove = new HashSet<>();
        for (Session session : this.localMap.values()) {
            if (now - session.lastAccessed() > session.timeout()) {
                toRemove.add(session.id());
            }
        }
        for (String id : toRemove) {
            this.localMap.remove(id);
        }
        if (!this.closed) {
            setTimer();
        }
    }

    private void setTimer() {
        if (this.reaperInterval != 0) {
            this.timerID = this.vertx.setTimer(this.reaperInterval, this);
        }
    }
}
