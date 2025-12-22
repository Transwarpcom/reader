package okhttp3.internal.connection;

import io.netty.handler.codec.rtsp.RtspHeaders;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;
import okio.AsyncTimeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RealCall.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��§\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010��\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001.\u0018��2\u00020\u0001:\u0002deB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u00101\u001a\u0002022\u0006\u0010\u0010\u001a\u00020\u000fJ!\u00103\u001a\u0002H4\"\n\b��\u00104*\u0004\u0018\u0001052\u0006\u00106\u001a\u0002H4H\u0002¢\u0006\u0002\u00107J\b\u00108\u001a\u000202H\u0002J\b\u00109\u001a\u000202H\u0016J\b\u0010:\u001a\u00020��H\u0016J\u0010\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>H\u0002J\u0010\u0010?\u001a\u0002022\u0006\u0010@\u001a\u00020AH\u0016J\u0016\u0010B\u001a\u0002022\u0006\u0010C\u001a\u00020\u00052\u0006\u0010D\u001a\u00020\u0007J\b\u0010E\u001a\u00020FH\u0016J\u0015\u0010G\u001a\u0002022\u0006\u0010H\u001a\u00020\u0007H��¢\u0006\u0002\bIJ\r\u0010J\u001a\u00020FH��¢\u0006\u0002\bKJ\u0015\u0010L\u001a\u00020\u001e2\u0006\u0010M\u001a\u00020NH��¢\u0006\u0002\bOJ\b\u0010P\u001a\u00020\u0007H\u0016J\b\u0010Q\u001a\u00020\u0007H\u0016J;\u0010R\u001a\u0002H4\"\n\b��\u00104*\u0004\u0018\u0001052\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010S\u001a\u00020\u00072\u0006\u0010T\u001a\u00020\u00072\u0006\u00106\u001a\u0002H4H��¢\u0006\u0004\bU\u0010VJ\u0019\u0010W\u001a\u0004\u0018\u0001052\b\u00106\u001a\u0004\u0018\u000105H��¢\u0006\u0002\bXJ\r\u0010Y\u001a\u00020ZH��¢\u0006\u0002\b[J\u000f\u0010\\\u001a\u0004\u0018\u00010]H��¢\u0006\u0002\b^J\b\u0010C\u001a\u00020\u0005H\u0016J\u0006\u0010_\u001a\u00020\u0007J\b\u0010-\u001a\u00020`H\u0016J\u0006\u00100\u001a\u000202J!\u0010a\u001a\u0002H4\"\n\b��\u00104*\u0004\u0018\u0001052\u0006\u0010b\u001a\u0002H4H\u0002¢\u0006\u0002\u00107J\b\u0010c\u001a\u00020ZH\u0002R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n��R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\"\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n��R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001aX\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010#\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n��R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b$\u0010%R\"\u0010&\u001a\u0004\u0018\u00010\u001e2\b\u0010\u000e\u001a\u0004\u0018\u00010\u001e@BX\u0080\u000e¢\u0006\b\n��\u001a\u0004\b'\u0010(R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010,\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0004\n\u0002\u0010/R\u000e\u00100\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n��¨\u0006f"}, d2 = {"Lokhttp3/internal/connection/RealCall;", "Lokhttp3/Call;", "client", "Lokhttp3/OkHttpClient;", "originalRequest", "Lokhttp3/Request;", "forWebSocket", "", "(Lokhttp3/OkHttpClient;Lokhttp3/Request;Z)V", "callStackTrace", "", "canceled", "getClient", "()Lokhttp3/OkHttpClient;", "<set-?>", "Lokhttp3/internal/connection/RealConnection;", "connection", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "connectionToCancel", "getConnectionToCancel", "setConnectionToCancel", "(Lokhttp3/internal/connection/RealConnection;)V", "eventListener", "Lokhttp3/EventListener;", "getEventListener$okhttp", "()Lokhttp3/EventListener;", "exchange", "Lokhttp3/internal/connection/Exchange;", "exchangeFinder", "Lokhttp3/internal/connection/ExchangeFinder;", "executed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "expectMoreExchanges", "getForWebSocket", "()Z", "interceptorScopedExchange", "getInterceptorScopedExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "getOriginalRequest", "()Lokhttp3/Request;", "requestBodyOpen", "responseBodyOpen", RtspHeaders.Values.TIMEOUT, "okhttp3/internal/connection/RealCall$timeout$1", "Lokhttp3/internal/connection/RealCall$timeout$1;", "timeoutEarlyExit", "acquireConnectionNoEvents", "", "callDone", "E", "Ljava/io/IOException;", "e", "(Ljava/io/IOException;)Ljava/io/IOException;", "callStart", "cancel", "clone", "createAddress", "Lokhttp3/Address;", "url", "Lokhttp3/HttpUrl;", "enqueue", "responseCallback", "Lokhttp3/Callback;", "enterNetworkInterceptorExchange", "request", "newExchangeFinder", "execute", "Lokhttp3/Response;", "exitNetworkInterceptorExchange", "closeExchange", "exitNetworkInterceptorExchange$okhttp", "getResponseWithInterceptorChain", "getResponseWithInterceptorChain$okhttp", "initExchange", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "initExchange$okhttp", "isCanceled", "isExecuted", "messageDone", "requestDone", "responseDone", "messageDone$okhttp", "(Lokhttp3/internal/connection/Exchange;ZZLjava/io/IOException;)Ljava/io/IOException;", "noMoreExchanges", "noMoreExchanges$okhttp", "redactedUrl", "", "redactedUrl$okhttp", "releaseConnectionNoEvents", "Ljava/net/Socket;", "releaseConnectionNoEvents$okhttp", "retryAfterFailure", "Lokio/AsyncTimeout;", "timeoutExit", "cause", "toLoggableString", "AsyncCall", "CallReference", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/internal/connection/RealCall.class */
public final class RealCall implements Call {
    private final RealConnectionPool connectionPool;

    @NotNull
    private final EventListener eventListener;
    private final AnonymousClass1 timeout;
    private final AtomicBoolean executed;
    private Object callStackTrace;
    private ExchangeFinder exchangeFinder;

    @Nullable
    private RealConnection connection;
    private boolean timeoutEarlyExit;

    @Nullable
    private Exchange interceptorScopedExchange;
    private boolean requestBodyOpen;
    private boolean responseBodyOpen;
    private boolean expectMoreExchanges;
    private volatile boolean canceled;
    private volatile Exchange exchange;

    @Nullable
    private volatile RealConnection connectionToCancel;

    @NotNull
    private final OkHttpClient client;

    @NotNull
    private final Request originalRequest;
    private final boolean forWebSocket;

    /* JADX WARN: Type inference failed for: r1v13, types: [okhttp3.internal.connection.RealCall$timeout$1] */
    public RealCall(@NotNull OkHttpClient client, @NotNull Request originalRequest, boolean forWebSocket) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(originalRequest, "originalRequest");
        this.client = client;
        this.originalRequest = originalRequest;
        this.forWebSocket = forWebSocket;
        this.connectionPool = this.client.connectionPool().getDelegate$okhttp();
        this.eventListener = this.client.eventListenerFactory().create(this);
        ?? r1 = new AsyncTimeout() { // from class: okhttp3.internal.connection.RealCall.timeout.1
            @Override // okio.AsyncTimeout
            protected void timedOut() {
                RealCall.this.cancel();
            }
        };
        r1.timeout(this.client.callTimeoutMillis(), TimeUnit.MILLISECONDS);
        Unit unit = Unit.INSTANCE;
        this.timeout = r1;
        this.executed = new AtomicBoolean();
        this.expectMoreExchanges = true;
    }

    @NotNull
    public final OkHttpClient getClient() {
        return this.client;
    }

    @NotNull
    public final Request getOriginalRequest() {
        return this.originalRequest;
    }

    public final boolean getForWebSocket() {
        return this.forWebSocket;
    }

    @NotNull
    public final EventListener getEventListener$okhttp() {
        return this.eventListener;
    }

    @Nullable
    public final RealConnection getConnection() {
        return this.connection;
    }

    @Nullable
    public final Exchange getInterceptorScopedExchange$okhttp() {
        return this.interceptorScopedExchange;
    }

    @Nullable
    public final RealConnection getConnectionToCancel() {
        return this.connectionToCancel;
    }

    public final void setConnectionToCancel(@Nullable RealConnection realConnection) {
        this.connectionToCancel = realConnection;
    }

    @Override // okhttp3.Call
    @NotNull
    public AsyncTimeout timeout() {
        return this.timeout;
    }

    @Override // okhttp3.Call
    @NotNull
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public RealCall m4583clone() {
        return new RealCall(this.client, this.originalRequest, this.forWebSocket);
    }

    @Override // okhttp3.Call
    @NotNull
    public Request request() {
        return this.originalRequest;
    }

    @Override // okhttp3.Call
    public void cancel() {
        if (this.canceled) {
            return;
        }
        this.canceled = true;
        Exchange exchange = this.exchange;
        if (exchange != null) {
            exchange.cancel();
        }
        RealConnection realConnection = this.connectionToCancel;
        if (realConnection != null) {
            realConnection.cancel();
        }
        this.eventListener.canceled(this);
    }

    @Override // okhttp3.Call
    public boolean isCanceled() {
        return this.canceled;
    }

    @Override // okhttp3.Call
    @NotNull
    public Response execute() {
        if (!this.executed.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed".toString());
        }
        enter();
        callStart();
        try {
            this.client.dispatcher().executed$okhttp(this);
            Response responseWithInterceptorChain$okhttp = getResponseWithInterceptorChain$okhttp();
            this.client.dispatcher().finished$okhttp(this);
            return responseWithInterceptorChain$okhttp;
        } catch (Throwable th) {
            this.client.dispatcher().finished$okhttp(this);
            throw th;
        }
    }

    @Override // okhttp3.Call
    public void enqueue(@NotNull Callback responseCallback) {
        Intrinsics.checkNotNullParameter(responseCallback, "responseCallback");
        if (!this.executed.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed".toString());
        }
        callStart();
        this.client.dispatcher().enqueue$okhttp(new AsyncCall(this, responseCallback));
    }

    @Override // okhttp3.Call
    public boolean isExecuted() {
        return this.executed.get();
    }

    private final void callStart() {
        this.callStackTrace = Platform.Companion.get().getStackTraceForCloseable("response.body().close()");
        this.eventListener.callStart(this);
    }

    @NotNull
    public final Response getResponseWithInterceptorChain$okhttp() throws IOException {
        List interceptors = new ArrayList();
        CollectionsKt.addAll(interceptors, this.client.interceptors());
        interceptors.add(new RetryAndFollowUpInterceptor(this.client));
        interceptors.add(new BridgeInterceptor(this.client.cookieJar()));
        interceptors.add(new CacheInterceptor(this.client.cache()));
        interceptors.add(ConnectInterceptor.INSTANCE);
        if (!this.forWebSocket) {
            CollectionsKt.addAll(interceptors, this.client.networkInterceptors());
        }
        interceptors.add(new CallServerInterceptor(this.forWebSocket));
        RealInterceptorChain chain = new RealInterceptorChain(this, interceptors, 0, null, this.originalRequest, this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis());
        try {
            try {
                Response response = chain.proceed(this.originalRequest);
                if (isCanceled()) {
                    Util.closeQuietly(response);
                    throw new IOException("Canceled");
                }
                noMoreExchanges$okhttp(null);
                return response;
            } catch (IOException e) {
                IOException iOExceptionNoMoreExchanges$okhttp = noMoreExchanges$okhttp(e);
                if (iOExceptionNoMoreExchanges$okhttp == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                }
                throw iOExceptionNoMoreExchanges$okhttp;
            }
        } catch (Throwable th) {
            if (0 == 0) {
                noMoreExchanges$okhttp(null);
            }
            throw th;
        }
    }

    public final void enterNetworkInterceptorExchange(@NotNull Request request, boolean newExchangeFinder) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (!(this.interceptorScopedExchange == null)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        synchronized (this) {
            if (!(!this.responseBodyOpen)) {
                throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()".toString());
            }
            if (!(!this.requestBodyOpen)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            Unit unit = Unit.INSTANCE;
        }
        if (newExchangeFinder) {
            this.exchangeFinder = new ExchangeFinder(this.connectionPool, createAddress(request.url()), this, this.eventListener);
        }
    }

    @NotNull
    public final Exchange initExchange$okhttp(@NotNull RealInterceptorChain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        synchronized (this) {
            if (!this.expectMoreExchanges) {
                throw new IllegalStateException("released".toString());
            }
            if (!(!this.responseBodyOpen)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            if (!(!this.requestBodyOpen)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            Unit unit = Unit.INSTANCE;
        }
        ExchangeFinder exchangeFinder = this.exchangeFinder;
        Intrinsics.checkNotNull(exchangeFinder);
        ExchangeCodec codec = exchangeFinder.find(this.client, chain);
        Exchange result = new Exchange(this, this.eventListener, exchangeFinder, codec);
        this.interceptorScopedExchange = result;
        this.exchange = result;
        synchronized (this) {
            this.requestBodyOpen = true;
            this.responseBodyOpen = true;
            Unit unit2 = Unit.INSTANCE;
        }
        if (this.canceled) {
            throw new IOException("Canceled");
        }
        return result;
    }

    public final void acquireConnectionNoEvents(@NotNull RealConnection connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        if (!Util.assertionsEnabled || Thread.holdsLock(connection)) {
            if (!(this.connection == null)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            this.connection = connection;
            connection.getCalls().add(new CallReference(this, this.callStackTrace));
            return;
        }
        StringBuilder sbAppend = new StringBuilder().append("Thread ");
        Thread threadCurrentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(threadCurrentThread, "Thread.currentThread()");
        throw new AssertionError(sbAppend.append(threadCurrentThread.getName()).append(" MUST hold lock on ").append(connection).toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <E extends java.io.IOException> E messageDone$okhttp(@org.jetbrains.annotations.NotNull okhttp3.internal.connection.Exchange r4, boolean r5, boolean r6, E r7) {
        /*
            r3 = this;
            r0 = r4
            java.lang.String r1 = "exchange"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r4
            r1 = r3
            okhttp3.internal.connection.Exchange r1 = r1.exchange
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            r1 = 1
            r0 = r0 ^ r1
            if (r0 == 0) goto L17
            r0 = r7
            return r0
        L17:
            r0 = 0
            r8 = r0
            r0 = 0
            r9 = r0
            r0 = r3
            r10 = r0
            r0 = 0
            r11 = r0
            r0 = 0
            r12 = r0
            r0 = r10
            monitor-enter(r0)
            r0 = 0
            r13 = r0
            r0 = r5
            if (r0 == 0) goto L38
            r0 = r3
            boolean r0 = r0.requestBodyOpen     // Catch: java.lang.Throwable -> L91
            if (r0 != 0) goto L43
        L38:
            r0 = r6
            if (r0 == 0) goto L86
            r0 = r3
            boolean r0 = r0.responseBodyOpen     // Catch: java.lang.Throwable -> L91
            if (r0 == 0) goto L86
        L43:
            r0 = r5
            if (r0 == 0) goto L4c
            r0 = r3
            r1 = 0
            r0.requestBodyOpen = r1     // Catch: java.lang.Throwable -> L91
        L4c:
            r0 = r6
            if (r0 == 0) goto L55
            r0 = r3
            r1 = 0
            r0.responseBodyOpen = r1     // Catch: java.lang.Throwable -> L91
        L55:
            r0 = r3
            boolean r0 = r0.requestBodyOpen     // Catch: java.lang.Throwable -> L91
            if (r0 != 0) goto L67
            r0 = r3
            boolean r0 = r0.responseBodyOpen     // Catch: java.lang.Throwable -> L91
            if (r0 != 0) goto L67
            r0 = 1
            goto L68
        L67:
            r0 = 0
        L68:
            r8 = r0
            r0 = r3
            boolean r0 = r0.requestBodyOpen     // Catch: java.lang.Throwable -> L91
            if (r0 != 0) goto L83
            r0 = r3
            boolean r0 = r0.responseBodyOpen     // Catch: java.lang.Throwable -> L91
            if (r0 != 0) goto L83
            r0 = r3
            boolean r0 = r0.expectMoreExchanges     // Catch: java.lang.Throwable -> L91
            if (r0 != 0) goto L83
            r0 = 1
            goto L84
        L83:
            r0 = 0
        L84:
            r9 = r0
        L86:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L91
            r12 = r0
            r0 = r10
            monitor-exit(r0)
            goto L99
        L91:
            r12 = move-exception
            r0 = r10
            monitor-exit(r0)
            r0 = r12
            throw r0
        L99:
            r0 = r8
            if (r0 == 0) goto Lb5
            r0 = r3
            r1 = 0
            okhttp3.internal.connection.Exchange r1 = (okhttp3.internal.connection.Exchange) r1
            r0.exchange = r1
            r0 = r3
            okhttp3.internal.connection.RealConnection r0 = r0.connection
            r1 = r0
            if (r1 == 0) goto Lb4
            r0.incrementSuccessCount$okhttp()
            goto Lb5
        Lb4:
        Lb5:
            r0 = r9
            if (r0 == 0) goto Lc1
            r0 = r3
            r1 = r7
            java.io.IOException r0 = r0.callDone(r1)
            return r0
        Lc1:
            r0 = r7
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealCall.messageDone$okhttp(okhttp3.internal.connection.Exchange, boolean, boolean, java.io.IOException):java.io.IOException");
    }

    @Nullable
    public final IOException noMoreExchanges$okhttp(@Nullable IOException e) {
        boolean callDone = false;
        synchronized (this) {
            if (this.expectMoreExchanges) {
                this.expectMoreExchanges = false;
                callDone = (this.requestBodyOpen || this.responseBodyOpen) ? false : true;
            }
            Unit unit = Unit.INSTANCE;
        }
        if (callDone) {
            return callDone(e);
        }
        return e;
    }

    private final <E extends IOException> E callDone(E e) throws IOException {
        Socket socketReleaseConnectionNoEvents$okhttp;
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            RealConnection realConnection = this.connection;
            if (realConnection != null) {
                if (!Util.assertionsEnabled || !Thread.holdsLock(realConnection)) {
                    synchronized (realConnection) {
                        socketReleaseConnectionNoEvents$okhttp = releaseConnectionNoEvents$okhttp();
                    }
                    if (this.connection == null) {
                        if (socketReleaseConnectionNoEvents$okhttp != null) {
                            Util.closeQuietly(socketReleaseConnectionNoEvents$okhttp);
                        }
                        this.eventListener.connectionReleased(this, realConnection);
                    } else {
                        if (!(socketReleaseConnectionNoEvents$okhttp == null)) {
                            throw new IllegalStateException("Check failed.".toString());
                        }
                    }
                } else {
                    StringBuilder sbAppend = new StringBuilder().append("Thread ");
                    Thread threadCurrentThread = Thread.currentThread();
                    Intrinsics.checkNotNullExpressionValue(threadCurrentThread, "Thread.currentThread()");
                    throw new AssertionError(sbAppend.append(threadCurrentThread.getName()).append(" MUST NOT hold lock on ").append(realConnection).toString());
                }
            }
            E e2 = (E) timeoutExit(e);
            if (e != null) {
                Intrinsics.checkNotNull(e2);
                this.eventListener.callFailed(this, e2);
            } else {
                this.eventListener.callEnd(this);
            }
            return e2;
        }
        StringBuilder sbAppend2 = new StringBuilder().append("Thread ");
        Thread threadCurrentThread2 = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(threadCurrentThread2, "Thread.currentThread()");
        throw new AssertionError(sbAppend2.append(threadCurrentThread2.getName()).append(" MUST NOT hold lock on ").append(this).toString());
    }

    @Nullable
    public final Socket releaseConnectionNoEvents$okhttp() {
        int i;
        RealConnection connection = this.connection;
        Intrinsics.checkNotNull(connection);
        if (!Util.assertionsEnabled || Thread.holdsLock(connection)) {
            List calls = connection.getCalls();
            int index$iv = 0;
            Iterator<Reference<RealCall>> it = calls.iterator();
            while (true) {
                if (it.hasNext()) {
                    Reference item$iv = it.next();
                    Reference it2 = item$iv;
                    if (Intrinsics.areEqual(it2.get(), this)) {
                        i = index$iv;
                        break;
                    }
                    index$iv++;
                } else {
                    i = -1;
                    break;
                }
            }
            int index = i;
            if (!(index != -1)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            calls.remove(index);
            this.connection = (RealConnection) null;
            if (calls.isEmpty()) {
                connection.setIdleAtNs$okhttp(System.nanoTime());
                if (this.connectionPool.connectionBecameIdle(connection)) {
                    return connection.socket();
                }
                return null;
            }
            return null;
        }
        StringBuilder sbAppend = new StringBuilder().append("Thread ");
        Thread threadCurrentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(threadCurrentThread, "Thread.currentThread()");
        throw new AssertionError(sbAppend.append(threadCurrentThread.getName()).append(" MUST hold lock on ").append(connection).toString());
    }

    private final <E extends IOException> E timeoutExit(E e) {
        if (!this.timeoutEarlyExit && exit()) {
            InterruptedIOException e2 = new InterruptedIOException(RtspHeaders.Values.TIMEOUT);
            if (e != null) {
                e2.initCause(e);
            }
            return e2;
        }
        return e;
    }

    public final void timeoutEarlyExit() {
        if (!(!this.timeoutEarlyExit)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.timeoutEarlyExit = true;
        exit();
    }

    public final void exitNetworkInterceptorExchange$okhttp(boolean closeExchange) {
        synchronized (this) {
            if (!this.expectMoreExchanges) {
                throw new IllegalStateException("released".toString());
            }
            Unit unit = Unit.INSTANCE;
        }
        if (closeExchange) {
            Exchange exchange = this.exchange;
            if (exchange != null) {
                exchange.detachWithViolence();
            }
        }
        this.interceptorScopedExchange = (Exchange) null;
    }

    private final Address createAddress(HttpUrl url) {
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) null;
        HostnameVerifier hostnameVerifier = (HostnameVerifier) null;
        CertificatePinner certificatePinner = (CertificatePinner) null;
        if (url.isHttps()) {
            sslSocketFactory = this.client.sslSocketFactory();
            hostnameVerifier = this.client.hostnameVerifier();
            certificatePinner = this.client.certificatePinner();
        }
        return new Address(url.host(), url.port(), this.client.dns(), this.client.socketFactory(), sslSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
    }

    public final boolean retryAfterFailure() {
        ExchangeFinder exchangeFinder = this.exchangeFinder;
        Intrinsics.checkNotNull(exchangeFinder);
        return exchangeFinder.retryAfterFailure();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String toLoggableString() {
        return (isCanceled() ? "canceled " : "") + (this.forWebSocket ? "web socket" : "call") + " to " + redactedUrl$okhttp();
    }

    @NotNull
    public final String redactedUrl$okhttp() {
        return this.originalRequest.url().redact();
    }

    /* compiled from: RealCall.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0080\u0004\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0012\u0010\u001a\u001a\u00020\u00172\n\u0010\u001b\u001a\u00060��R\u00020\u0006J\b\u0010\u001c\u001a\u00020\u0017H\u0016R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u001d"}, d2 = {"Lokhttp3/internal/connection/RealCall$AsyncCall;", "Ljava/lang/Runnable;", "responseCallback", "Lokhttp3/Callback;", "(Lokhttp3/internal/connection/RealCall;Lokhttp3/Callback;)V", "call", "Lokhttp3/internal/connection/RealCall;", "getCall", "()Lokhttp3/internal/connection/RealCall;", "<set-?>", "Ljava/util/concurrent/atomic/AtomicInteger;", "callsPerHost", "getCallsPerHost", "()Ljava/util/concurrent/atomic/AtomicInteger;", "host", "", "getHost", "()Ljava/lang/String;", "request", "Lokhttp3/Request;", "getRequest", "()Lokhttp3/Request;", "executeOn", "", "executorService", "Ljava/util/concurrent/ExecutorService;", "reuseCallsPerHostFrom", "other", "run", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/internal/connection/RealCall$AsyncCall.class */
    public final class AsyncCall implements Runnable {

        @NotNull
        private volatile AtomicInteger callsPerHost;
        private final Callback responseCallback;
        final /* synthetic */ RealCall this$0;

        public AsyncCall(@NotNull RealCall this$0, Callback responseCallback) {
            Intrinsics.checkNotNullParameter(responseCallback, "responseCallback");
            this.this$0 = this$0;
            this.responseCallback = responseCallback;
            this.callsPerHost = new AtomicInteger(0);
        }

        @NotNull
        public final AtomicInteger getCallsPerHost() {
            return this.callsPerHost;
        }

        public final void reuseCallsPerHostFrom(@NotNull AsyncCall other) {
            Intrinsics.checkNotNullParameter(other, "other");
            this.callsPerHost = other.callsPerHost;
        }

        @NotNull
        public final String getHost() {
            return this.this$0.getOriginalRequest().url().host();
        }

        @NotNull
        public final Request getRequest() {
            return this.this$0.getOriginalRequest();
        }

        @NotNull
        public final RealCall getCall() {
            return this.this$0;
        }

        public final void executeOn(@NotNull ExecutorService executorService) {
            Intrinsics.checkNotNullParameter(executorService, "executorService");
            Object $this$assertThreadDoesntHoldLock$iv = this.this$0.getClient().dispatcher();
            if (!Util.assertionsEnabled || !Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) {
                try {
                    try {
                        executorService.execute(this);
                    } catch (RejectedExecutionException e) {
                        InterruptedIOException ioException = new InterruptedIOException("executor rejected");
                        ioException.initCause(e);
                        this.this$0.noMoreExchanges$okhttp(ioException);
                        this.responseCallback.onFailure(this.this$0, ioException);
                        this.this$0.getClient().dispatcher().finished$okhttp(this);
                    }
                    return;
                } catch (Throwable th) {
                    this.this$0.getClient().dispatcher().finished$okhttp(this);
                    throw th;
                }
            }
            StringBuilder sbAppend = new StringBuilder().append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(threadCurrentThread, "Thread.currentThread()");
            throw new AssertionError(sbAppend.append(threadCurrentThread.getName()).append(" MUST NOT hold lock on ").append($this$assertThreadDoesntHoldLock$iv).toString());
        }

        /* JADX WARN: Finally extract failed */
        @Override // java.lang.Runnable
        public void run() {
            String name$iv = "OkHttp " + this.this$0.redactedUrl$okhttp();
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(currentThread, "currentThread");
            String oldName$iv = currentThread.getName();
            currentThread.setName(name$iv);
            try {
                boolean signalledCallback = false;
                enter();
                try {
                    try {
                        Response response = this.this$0.getResponseWithInterceptorChain$okhttp();
                        signalledCallback = true;
                        this.responseCallback.onResponse(this.this$0, response);
                        this.this$0.getClient().dispatcher().finished$okhttp(this);
                    } catch (IOException e) {
                        if (signalledCallback) {
                            Platform.Companion.get().log("Callback failure for " + this.this$0.toLoggableString(), 4, e);
                        } else {
                            this.responseCallback.onFailure(this.this$0, e);
                        }
                        this.this$0.getClient().dispatcher().finished$okhttp(this);
                    } catch (Throwable t) {
                        this.this$0.cancel();
                        if (!signalledCallback) {
                            IOException canceledException = new IOException("canceled due to " + t);
                            ExceptionsKt.addSuppressed(canceledException, t);
                            this.responseCallback.onFailure(this.this$0, canceledException);
                        }
                        throw t;
                    }
                } catch (Throwable th) {
                    this.this$0.getClient().dispatcher().finished$okhttp(this);
                    throw th;
                }
            } finally {
                currentThread.setName(oldName$iv);
            }
        }
    }

    /* compiled from: RealCall.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n\u0002\b\u0004\b��\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lokhttp3/internal/connection/RealCall$CallReference;", "Ljava/lang/ref/WeakReference;", "Lokhttp3/internal/connection/RealCall;", "referent", "callStackTrace", "", "(Lokhttp3/internal/connection/RealCall;Ljava/lang/Object;)V", "getCallStackTrace", "()Ljava/lang/Object;", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/internal/connection/RealCall$CallReference.class */
    public static final class CallReference extends WeakReference<RealCall> {

        @Nullable
        private final Object callStackTrace;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CallReference(@NotNull RealCall referent, @Nullable Object callStackTrace) {
            super(referent);
            Intrinsics.checkNotNullParameter(referent, "referent");
            this.callStackTrace = callStackTrace;
        }

        @Nullable
        public final Object getCallStackTrace() {
            return this.callStackTrace;
        }
    }
}
