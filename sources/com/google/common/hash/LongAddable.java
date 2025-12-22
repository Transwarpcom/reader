package com.google.common.hash;

/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/hash/LongAddable.class */
interface LongAddable {
    void increment();

    void add(long j);

    long sum();
}
