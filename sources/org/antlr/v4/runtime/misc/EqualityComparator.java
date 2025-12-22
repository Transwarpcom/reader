package org.antlr.v4.runtime.misc;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/EqualityComparator.class */
public interface EqualityComparator<T> {
    int hashCode(T t);

    boolean equals(T t, T t2);
}
