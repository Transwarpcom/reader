package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.net.NetSocket;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpClientStream.class */
interface HttpClientStream {
    int id();

    Object metric();

    HttpVersion version();

    HttpConnection connection();

    Context getContext();

    void writeHead(HttpMethod httpMethod, String str, String str2, MultiMap multiMap, String str3, boolean z, ByteBuf byteBuf, boolean z2, StreamPriority streamPriority, Handler<Void> handler, Handler<AsyncResult<Void>> handler2);

    void writeBuffer(ByteBuf byteBuf, boolean z, Handler<AsyncResult<Void>> handler);

    void writeFrame(int i, int i2, ByteBuf byteBuf);

    void doSetWriteQueueMaxSize(int i);

    boolean isNotWritable();

    void doPause();

    void doFetch(long j);

    void reset(Throwable th);

    void beginRequest(HttpClientRequestImpl httpClientRequestImpl);

    void endRequest();

    NetSocket createNetSocket();

    StreamPriority priority();

    void updatePriority(StreamPriority streamPriority);
}
