package io.vertx.core.http.impl.pool;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/pool/ConnectResult.class */
public class ConnectResult<C> {
    private final C conn;
    private final long concurrency;
    private final long weight;

    public ConnectResult(C connection, long concurrency, long weight) {
        this.conn = connection;
        this.concurrency = concurrency;
        this.weight = weight;
    }

    public C connection() {
        return this.conn;
    }

    public long concurrency() {
        return this.concurrency;
    }

    public long weight() {
        return this.weight;
    }
}
