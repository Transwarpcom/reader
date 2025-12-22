package org.apache.commons.lang3.builder;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/builder/Diffable.class */
public interface Diffable<T> {
    DiffResult diff(T t);
}
