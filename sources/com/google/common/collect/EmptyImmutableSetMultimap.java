package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible(serializable = true)
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/EmptyImmutableSetMultimap.class */
class EmptyImmutableSetMultimap extends ImmutableSetMultimap<Object, Object> {
    static final EmptyImmutableSetMultimap INSTANCE = new EmptyImmutableSetMultimap();
    private static final long serialVersionUID = 0;

    private EmptyImmutableSetMultimap() {
        super(ImmutableMap.of(), 0, null);
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
