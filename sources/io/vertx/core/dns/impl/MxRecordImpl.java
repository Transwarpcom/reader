package io.vertx.core.dns.impl;

import io.vertx.core.dns.MxRecord;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/dns/impl/MxRecordImpl.class */
public final class MxRecordImpl implements MxRecord, Comparable<MxRecord> {
    private final int priority;
    private final String name;

    public MxRecordImpl(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    @Override // io.vertx.core.dns.MxRecord
    public int priority() {
        return this.priority;
    }

    @Override // io.vertx.core.dns.MxRecord
    public String name() {
        return this.name;
    }

    public String toString() {
        return priority() + " " + name();
    }

    @Override // java.lang.Comparable
    public int compareTo(MxRecord o) {
        return Integer.valueOf(priority()).compareTo(Integer.valueOf(o.priority()));
    }
}
