package retrofit2;

import java.io.IOException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

/* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/OkHttpCall.class */
final class OkHttpCall<T> implements Call<T> {
    private final RequestFactory requestFactory;
    private final Object[] args;
    private final Call.Factory callFactory;
    private final Converter<ResponseBody, T> responseConverter;
    private volatile boolean canceled;

    @GuardedBy("this")
    @Nullable
    private okhttp3.Call rawCall;

    @GuardedBy("this")
    @Nullable
    private Throwable creationFailure;

    @GuardedBy("this")
    private boolean executed;

    OkHttpCall(RequestFactory requestFactory, Object[] args, Call.Factory callFactory, Converter<ResponseBody, T> responseConverter) {
        this.requestFactory = requestFactory;
        this.args = args;
        this.callFactory = callFactory;
        this.responseConverter = responseConverter;
    }

    @Override // retrofit2.Call
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public OkHttpCall<T> m5942clone() {
        return new OkHttpCall<>(this.requestFactory, this.args, this.callFactory, this.responseConverter);
    }

    @Override // retrofit2.Call
    public synchronized Request request() {
        okhttp3.Call call = this.rawCall;
        if (call != null) {
            return call.request();
        }
        if (this.creationFailure != null) {
            if (this.creationFailure instanceof IOException) {
                throw new RuntimeException("Unable to create request.", this.creationFailure);
            }
            if (this.creationFailure instanceof RuntimeException) {
                throw ((RuntimeException) this.creationFailure);
            }
            throw ((Error) this.creationFailure);
        }
        try {
            okhttp3.Call callCreateRawCall = createRawCall();
            this.rawCall = callCreateRawCall;
            return callCreateRawCall.request();
        } catch (IOException e) {
            this.creationFailure = e;
            throw new RuntimeException("Unable to create request.", e);
        } catch (Error | RuntimeException e2) {
            Utils.throwIfFatal(e2);
            this.creationFailure = e2;
            throw e2;
        }
    }

    @Override // retrofit2.Call
    public void enqueue(final Callback<T> callback) {
        okhttp3.Call call;
        Throwable failure;
        Utils.checkNotNull(callback, "callback == null");
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
            this.executed = true;
            call = this.rawCall;
            failure = this.creationFailure;
            if (call == null && failure == null) {
                try {
                    okhttp3.Call callCreateRawCall = createRawCall();
                    this.rawCall = callCreateRawCall;
                    call = callCreateRawCall;
                } catch (Throwable t) {
                    Utils.throwIfFatal(t);
                    this.creationFailure = t;
                    failure = t;
                }
            }
        }
        if (failure != null) {
            callback.onFailure(this, failure);
            return;
        }
        if (this.canceled) {
            call.cancel();
        }
        call.enqueue(new okhttp3.Callback() { // from class: retrofit2.OkHttpCall.1
            @Override // okhttp3.Callback
            public void onResponse(okhttp3.Call call2, okhttp3.Response rawResponse) {
                try {
                    Response<T> response = OkHttpCall.this.parseResponse(rawResponse);
                    try {
                        callback.onResponse(OkHttpCall.this, response);
                    } catch (Throwable t2) {
                        Utils.throwIfFatal(t2);
                        t2.printStackTrace();
                    }
                } catch (Throwable e) {
                    Utils.throwIfFatal(e);
                    callFailure(e);
                }
            }

            @Override // okhttp3.Callback
            public void onFailure(okhttp3.Call call2, IOException e) {
                callFailure(e);
            }

            private void callFailure(Throwable e) {
                try {
                    callback.onFailure(OkHttpCall.this, e);
                } catch (Throwable t2) {
                    Utils.throwIfFatal(t2);
                    t2.printStackTrace();
                }
            }
        });
    }

    @Override // retrofit2.Call
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    @Override // retrofit2.Call
    public Response<T> execute() throws IOException {
        okhttp3.Call call;
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
            this.executed = true;
            if (this.creationFailure != null) {
                if (this.creationFailure instanceof IOException) {
                    throw ((IOException) this.creationFailure);
                }
                if (this.creationFailure instanceof RuntimeException) {
                    throw ((RuntimeException) this.creationFailure);
                }
                throw ((Error) this.creationFailure);
            }
            call = this.rawCall;
            if (call == null) {
                try {
                    okhttp3.Call callCreateRawCall = createRawCall();
                    this.rawCall = callCreateRawCall;
                    call = callCreateRawCall;
                } catch (IOException | Error | RuntimeException e) {
                    Utils.throwIfFatal(e);
                    this.creationFailure = e;
                    throw e;
                }
            }
        }
        if (this.canceled) {
            call.cancel();
        }
        return parseResponse(call.execute());
    }

    private okhttp3.Call createRawCall() throws IOException {
        okhttp3.Call call = this.callFactory.newCall(this.requestFactory.create(this.args));
        if (call == null) {
            throw new NullPointerException("Call.Factory returned null.");
        }
        return call;
    }

    Response<T> parseResponse(okhttp3.Response rawResponse) throws IOException {
        ResponseBody rawBody = rawResponse.body();
        okhttp3.Response rawResponse2 = rawResponse.newBuilder().body(new NoContentResponseBody(rawBody.contentType(), rawBody.contentLength())).build();
        int code = rawResponse2.code();
        if (code < 200 || code >= 300) {
            try {
                ResponseBody bufferedBody = Utils.buffer(rawBody);
                Response<T> responseError = Response.error(bufferedBody, rawResponse2);
                rawBody.close();
                return responseError;
            } catch (Throwable th) {
                rawBody.close();
                throw th;
            }
        }
        if (code == 204 || code == 205) {
            rawBody.close();
            return Response.success((Object) null, rawResponse2);
        }
        ExceptionCatchingResponseBody catchingBody = new ExceptionCatchingResponseBody(rawBody);
        try {
            T body = this.responseConverter.convert(catchingBody);
            return Response.success(body, rawResponse2);
        } catch (RuntimeException e) {
            catchingBody.throwIfCaught();
            throw e;
        }
    }

    @Override // retrofit2.Call
    public void cancel() {
        okhttp3.Call call;
        this.canceled = true;
        synchronized (this) {
            call = this.rawCall;
        }
        if (call != null) {
            call.cancel();
        }
    }

    @Override // retrofit2.Call
    public boolean isCanceled() {
        boolean z;
        if (this.canceled) {
            return true;
        }
        synchronized (this) {
            z = this.rawCall != null && this.rawCall.isCanceled();
        }
        return z;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/OkHttpCall$NoContentResponseBody.class */
    static final class NoContentResponseBody extends ResponseBody {

        @Nullable
        private final MediaType contentType;
        private final long contentLength;

        NoContentResponseBody(@Nullable MediaType contentType, long contentLength) {
            this.contentType = contentType;
            this.contentLength = contentLength;
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.contentType;
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return this.contentLength;
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/OkHttpCall$ExceptionCatchingResponseBody.class */
    static final class ExceptionCatchingResponseBody extends ResponseBody {
        private final ResponseBody delegate;
        private final BufferedSource delegateSource;

        @Nullable
        IOException thrownException;

        ExceptionCatchingResponseBody(ResponseBody delegate) {
            this.delegate = delegate;
            this.delegateSource = Okio.buffer(new ForwardingSource(delegate.source()) { // from class: retrofit2.OkHttpCall.ExceptionCatchingResponseBody.1
                @Override // okio.ForwardingSource, okio.Source
                public long read(Buffer sink, long byteCount) throws IOException {
                    try {
                        return super.read(sink, byteCount);
                    } catch (IOException e) {
                        ExceptionCatchingResponseBody.this.thrownException = e;
                        throw e;
                    }
                }
            });
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.delegate.contentType();
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return this.delegate.contentLength();
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            return this.delegateSource;
        }

        @Override // okhttp3.ResponseBody, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
        }

        void throwIfCaught() throws IOException {
            if (this.thrownException != null) {
                throw this.thrownException;
            }
        }
    }
}
