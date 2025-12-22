package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/FutureCallback.class */
public interface FutureCallback<V> {
    void onSuccess(V v);

    void onFailure(Throwable th);
}
