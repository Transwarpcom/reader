package io.vertx.ext.web.codec.spi;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.WriteStream;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-common-3.8.5.jar:io/vertx/ext/web/codec/spi/BodyStream.class */
public interface BodyStream<T> extends WriteStream<Buffer>, Handler<Throwable> {
    Future<T> result();
}
