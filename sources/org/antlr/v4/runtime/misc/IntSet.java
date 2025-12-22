package org.antlr.v4.runtime.misc;

import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/IntSet.class */
public interface IntSet {
    void add(int i);

    IntSet addAll(IntSet intSet);

    IntSet and(IntSet intSet);

    IntSet complement(IntSet intSet);

    IntSet or(IntSet intSet);

    IntSet subtract(IntSet intSet);

    int size();

    boolean isNil();

    boolean equals(Object obj);

    boolean contains(int i);

    void remove(int i);

    List<Integer> toList();

    String toString();
}
