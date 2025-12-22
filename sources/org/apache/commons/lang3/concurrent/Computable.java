package org.apache.commons.lang3.concurrent;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/concurrent/Computable.class */
public interface Computable<I, O> {
    O compute(I i) throws InterruptedException;
}
