package io.vertx.core.dns.impl;

import io.vertx.core.dns.SrvRecord;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/dns/impl/SrvRecordImpl.class */
public final class SrvRecordImpl implements SrvRecord, Comparable<SrvRecord> {
    private final int priority;
    private final int weight;
    private final int port;
    private final String name;
    private final String protocol;
    private final String service;
    private final String target;

    public SrvRecordImpl(int priority, int weight, int port, String name, String protocol, String service, String target) {
        this.priority = priority;
        this.weight = weight;
        this.port = port;
        this.name = name;
        this.protocol = protocol;
        this.service = service;
        this.target = target;
    }

    @Override // io.vertx.core.dns.SrvRecord
    public int priority() {
        return this.priority;
    }

    @Override // io.vertx.core.dns.SrvRecord
    public int weight() {
        return this.weight;
    }

    @Override // io.vertx.core.dns.SrvRecord
    public int port() {
        return this.port;
    }

    @Override // io.vertx.core.dns.SrvRecord
    public String name() {
        return this.name;
    }

    @Override // io.vertx.core.dns.SrvRecord
    public String protocol() {
        return this.protocol;
    }

    @Override // io.vertx.core.dns.SrvRecord
    public String service() {
        return this.service;
    }

    @Override // io.vertx.core.dns.SrvRecord
    public String target() {
        return this.target;
    }

    @Override // java.lang.Comparable
    public int compareTo(SrvRecord o) {
        return Integer.valueOf(priority()).compareTo(Integer.valueOf(o.priority()));
    }
}
