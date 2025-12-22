package io.netty.util;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/AsyncMapping.class */
public interface AsyncMapping<IN, OUT> {
    Future<OUT> map(IN in, Promise<OUT> promise);
}
