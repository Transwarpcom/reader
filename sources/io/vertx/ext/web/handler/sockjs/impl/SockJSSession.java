package io.vertx.ext.web.handler.sockjs.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.EncodeException;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.ConnectionBase;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.Shareable;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import io.vertx.core.streams.impl.InboundBuffer;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/SockJSSession.class */
class SockJSSession extends SockJSSocketBase implements Shareable {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) SockJSSession.class);
    private final LocalMap<String, SockJSSession> sessions;
    private final Deque<String> pendingWrites;
    private List<Handler<AsyncResult<Void>>> writeAcks;
    private final Context context;
    private final InboundBuffer<Buffer> pendingReads;
    private TransportListener listener;
    private boolean closed;
    private boolean openWritten;
    private final String id;
    private final long timeout;
    private final Handler<SockJSSocket> sockHandler;
    private long heartbeatID;
    private long timeoutTimerID;
    private int maxQueueSize;
    private int messagesSize;
    private Handler<Void> drainHandler;
    private Handler<Void> endHandler;
    private Handler<Throwable> exceptionHandler;
    private boolean handleCalled;
    private SocketAddress localAddress;
    private SocketAddress remoteAddress;
    private String uri;
    private MultiMap headers;
    private Context transportCtx;

    @Override // io.vertx.ext.web.handler.sockjs.impl.SockJSSocketBase, io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ SockJSSocket exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.WriteStream
    /* renamed from: drainHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ WriteStream<Buffer> drainHandler2(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream
    public /* bridge */ /* synthetic */ ReadStream<Buffer> endHandler(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.ext.web.handler.sockjs.impl.SockJSSocketBase, io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.ext.web.handler.sockjs.impl.SockJSSocketBase, io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WriteStream<Buffer> drainHandler(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WriteStream<Buffer> write(Buffer buffer, Handler handler) {
        return write(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.ext.web.handler.sockjs.impl.SockJSSocketBase, io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    SockJSSession(Vertx vertx, LocalMap<String, SockJSSession> sessions, RoutingContext rc, long heartbeatInterval, Handler<SockJSSocket> sockHandler) {
        this(vertx, sessions, rc, null, -1L, heartbeatInterval, sockHandler);
    }

    SockJSSession(Vertx vertx, LocalMap<String, SockJSSession> sessions, RoutingContext rc, String id, long timeout, long heartbeatInterval, Handler<SockJSSocket> sockHandler) {
        super(vertx, rc.session(), rc.user());
        this.pendingWrites = new LinkedList();
        this.timeoutTimerID = -1L;
        this.maxQueueSize = 65536;
        this.sessions = sessions;
        this.id = id;
        this.timeout = timeout;
        this.sockHandler = sockHandler;
        this.context = vertx.getOrCreateContext();
        this.pendingReads = new InboundBuffer<>(this.context);
        this.heartbeatID = vertx.setPeriodic(heartbeatInterval, tid -> {
            if (this.listener != null) {
                this.listener.sendFrame(OperatorName.CLOSE_PATH, null);
            }
        });
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
    public SockJSSocket write(Buffer buffer, Handler<AsyncResult<Void>> handler) {
        synchronized (this) {
            if (this.closed) {
                if (handler != null) {
                    this.context.runOnContext(v -> {
                        handler.handle(Future.failedFuture(ConnectionBase.CLOSED_EXCEPTION));
                    });
                }
                return this;
            }
            String msgStr = buffer.toString();
            this.pendingWrites.add(msgStr);
            this.messagesSize += msgStr.length();
            if (handler != null) {
                if (this.writeAcks == null) {
                    this.writeAcks = new ArrayList();
                }
                this.writeAcks.add(handler);
            }
            if (this.listener != null) {
                Context ctx = this.transportCtx;
                if (Vertx.currentContext() != ctx) {
                    ctx.runOnContext(v2 -> {
                        writePendingMessages();
                    });
                } else {
                    writePendingMessages();
                }
            }
            return this;
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public synchronized ReadStream<Buffer> handler2(Handler<Buffer> handler) {
        this.pendingReads.handler(handler);
        return this;
    }

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public ReadStream<Buffer> fetch2(long amount) {
        this.pendingReads.fetch(amount);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public synchronized ReadStream<Buffer> pause2() {
        this.pendingReads.pause();
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public synchronized ReadStream<Buffer> resume2() {
        this.pendingReads.resume();
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    public synchronized WriteStream<Buffer> setWriteQueueMaxSize2(int maxQueueSize) {
        if (maxQueueSize < 1) {
            throw new IllegalArgumentException("maxQueueSize must be >= 1");
        }
        this.maxQueueSize = maxQueueSize;
        return this;
    }

    @Override // io.vertx.core.streams.WriteStream
    public synchronized boolean writeQueueFull() {
        return this.messagesSize >= this.maxQueueSize;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.WriteStream
    public synchronized WriteStream<Buffer> drainHandler(Handler<Void> handler) {
        this.drainHandler = handler;
        return this;
    }

    @Override // io.vertx.ext.web.handler.sockjs.impl.SockJSSocketBase, io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public synchronized SockJSSession exceptionHandler(Handler<Throwable> handler) {
        this.exceptionHandler = handler;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream
    public synchronized ReadStream<Buffer> endHandler(Handler<Void> endHandler) {
        this.endHandler = endHandler;
        return this;
    }

    @Override // io.vertx.ext.web.handler.sockjs.impl.SockJSSocketBase, io.vertx.ext.web.handler.sockjs.SockJSSocket
    public void close() {
        synchronized (this) {
            if (!this.closed) {
                this.closed = true;
                handleClosed();
            }
            doClose();
        }
    }

    private synchronized void doClose() {
        Context ctx = this.transportCtx;
        if (ctx != Vertx.currentContext()) {
            ctx.runOnContext(v -> {
                doClose();
            });
        } else if (this.listener != null && this.handleCalled) {
            this.listener.sessionClosed();
        }
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
    public synchronized SocketAddress remoteAddress() {
        return this.remoteAddress;
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
    public synchronized SocketAddress localAddress() {
        return this.localAddress;
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
    public synchronized MultiMap headers() {
        return this.headers;
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
    public synchronized String uri() {
        return this.uri;
    }

    synchronized boolean isClosed() {
        return this.closed;
    }

    synchronized void resetListener() {
        this.listener = null;
        setTimer();
    }

    private void cancelTimer() {
        if (this.timeoutTimerID != -1) {
            this.vertx.cancelTimer(this.timeoutTimerID);
        }
    }

    private void setTimer() {
        if (this.timeout != -1) {
            cancelTimer();
            this.timeoutTimerID = this.vertx.setTimer(this.timeout, id1 -> {
                this.vertx.cancelTimer(this.heartbeatID);
                if (this.listener == null) {
                    shutdown();
                }
                if (this.listener != null) {
                    this.listener.close();
                }
            });
        }
    }

    private synchronized void writePendingMessages() throws EncodeException {
        if (this.listener != null) {
            String json = JsonCodec.encode(this.pendingWrites.toArray());
            this.pendingWrites.clear();
            if (this.writeAcks != null) {
                List<Handler<AsyncResult<Void>>> acks = this.writeAcks;
                this.writeAcks = null;
                this.listener.sendFrame("a" + json, ar -> {
                    acks.forEach(a -> {
                        a.handle(ar);
                    });
                });
            } else {
                this.listener.sendFrame("a" + json, null);
            }
            this.messagesSize = 0;
            if (this.drainHandler != null) {
                Handler<Void> dh = this.drainHandler;
                this.drainHandler = null;
                this.context.runOnContext(dh);
            }
        }
    }

    synchronized Context context() {
        return this.transportCtx;
    }

    synchronized void register(HttpServerRequest req, TransportListener lst) {
        this.transportCtx = this.vertx.getOrCreateContext();
        this.localAddress = req.localAddress();
        this.remoteAddress = req.remoteAddress();
        this.uri = req.uri();
        this.headers = BaseTransport.removeCookieHeaders(req.headers());
        if (this.closed) {
            writeClosed(lst);
            lst.close();
            return;
        }
        if (this.listener != null) {
            writeClosed(lst, 2010, "Another connection still open");
            lst.close();
            return;
        }
        cancelTimer();
        this.listener = lst;
        if (!this.openWritten) {
            writeOpen(lst);
            this.sockHandler.handle(this);
            this.handleCalled = true;
        }
        if (this.listener != null) {
            if (this.closed) {
                writeClosed(lst);
                this.listener = null;
                lst.close();
            } else if (!this.pendingWrites.isEmpty()) {
                writePendingMessages();
            }
        }
    }

    void shutdown() {
        super.close();
        if (this.heartbeatID != -1) {
            this.vertx.cancelTimer(this.heartbeatID);
        }
        if (this.timeoutTimerID != -1) {
            this.vertx.cancelTimer(this.timeoutTimerID);
        }
        if (this.id != null) {
            this.sessions.remove(this.id);
        }
        if (!this.closed) {
            this.closed = true;
            handleClosed();
        }
    }

    private void handleClosed() {
        this.pendingReads.clear();
        this.pendingWrites.clear();
        if (this.writeAcks != null) {
            this.writeAcks.forEach(handler -> {
                this.context.runOnContext(v -> {
                    handler.handle(Future.failedFuture(ConnectionBase.CLOSED_EXCEPTION));
                });
            });
            this.writeAcks.clear();
        }
        Handler<Void> handler2 = this.endHandler;
        if (handler2 != null) {
            Context context = this.context;
            handler2.getClass();
            context.runOnContext((v1) -> {
                r1.handle(v1);
            });
        }
    }

    private String[] parseMessageString(String msgs) {
        String[] parts;
        try {
            if (msgs.startsWith("[")) {
                parts = (String[]) JsonCodec.decodeValue(msgs, String[].class);
            } else {
                String str = (String) JsonCodec.decodeValue(msgs, String.class);
                parts = new String[]{str};
            }
            return parts;
        } catch (DecodeException e) {
            return null;
        }
    }

    synchronized boolean handleMessages(String messages) {
        String[] msgArr = parseMessageString(messages);
        if (msgArr == null) {
            return false;
        }
        handleMessages(msgArr);
        return true;
    }

    private synchronized void handleMessages(String[] messages) {
        if (this.context == Vertx.currentContext()) {
            for (String msg : messages) {
                this.pendingReads.write((InboundBuffer<Buffer>) Buffer.buffer(msg));
            }
            return;
        }
        this.context.runOnContext(v -> {
            handleMessages(messages);
        });
    }

    void handleException(Throwable t) {
        Handler<Throwable> eh;
        synchronized (this) {
            eh = this.exceptionHandler;
        }
        if (eh != null) {
            this.context.runOnContext(v -> {
                eh.handle(t);
            });
        } else {
            log.error("Unhandled exception", t);
        }
    }

    void writeClosed(TransportListener lst) {
        writeClosed(lst, 3000, "Go away!");
    }

    private void writeClosed(TransportListener lst, int code, String msg) {
        String sb = "c[" + code + ",\"" + msg + "\"]";
        lst.sendFrame(sb, null);
    }

    private synchronized void writeOpen(TransportListener lst) {
        lst.sendFrame("o", null);
        this.openWritten = true;
    }
}
