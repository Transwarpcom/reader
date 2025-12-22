package okhttp3;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.netty.handler.codec.http.HttpHeaders;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.NCXDocumentV3;
import okhttp3.Headers;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSource;
import okio.Source;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Response.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018��2\u00020\u0001:\u0001FB{\b��\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010��\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010��\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010��\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010\u0018J\u000f\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007¢\u0006\u0002\b+J\r\u0010\u001a\u001a\u00020\u001bH\u0007¢\u0006\u0002\b,J\u000f\u0010\u0011\u001a\u0004\u0018\u00010��H\u0007¢\u0006\u0002\b-J\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/J\b\u00101\u001a\u000202H\u0016J\r\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b3J\u000f\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0002\b4J\u001e\u00105\u001a\u0004\u0018\u00010\u00072\u0006\u00106\u001a\u00020\u00072\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u0007H\u0007J\r\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\b8J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070/2\u0006\u00106\u001a\u00020\u0007J\r\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\b9J\u000f\u0010\u0010\u001a\u0004\u0018\u00010��H\u0007¢\u0006\u0002\b:J\u0006\u0010;\u001a\u00020<J\u000e\u0010=\u001a\u00020\u000f2\u0006\u0010>\u001a\u00020\u0014J\u000f\u0010\u0012\u001a\u0004\u0018\u00010��H\u0007¢\u0006\u0002\b?J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\b@J\r\u0010\u0015\u001a\u00020\u0014H\u0007¢\u0006\u0002\bAJ\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\bBJ\r\u0010\u0013\u001a\u00020\u0014H\u0007¢\u0006\u0002\bCJ\b\u0010D\u001a\u00020\u0007H\u0016J\u0006\u0010E\u001a\u00020\rR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0007¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b8G¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001cR\u0015\u0010\u0011\u001a\u0004\u0018\u00010��8\u0007¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u001dR\u0013\u0010\b\u001a\u00020\t8\u0007¢\u0006\b\n��\u001a\u0004\b\b\u0010\u001eR\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00178\u0001X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u001fR\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0007¢\u0006\b\n��\u001a\u0004\b\n\u0010 R\u0013\u0010\f\u001a\u00020\r8\u0007¢\u0006\b\n��\u001a\u0004\b\f\u0010!R\u0011\u0010\"\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\b\"\u0010$R\u0011\u0010%\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\b%\u0010$R\u0010\u0010&\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n��R\u0013\u0010\u0006\u001a\u00020\u00078\u0007¢\u0006\b\n��\u001a\u0004\b\u0006\u0010'R\u0015\u0010\u0010\u001a\u0004\u0018\u00010��8\u0007¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u001dR\u0015\u0010\u0012\u001a\u0004\u0018\u00010��8\u0007¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u001dR\u0013\u0010\u0004\u001a\u00020\u00058\u0007¢\u0006\b\n��\u001a\u0004\b\u0004\u0010(R\u0013\u0010\u0015\u001a\u00020\u00148\u0007¢\u0006\b\n��\u001a\u0004\b\u0015\u0010)R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\u0002\u0010*R\u0013\u0010\u0013\u001a\u00020\u00148\u0007¢\u0006\b\n��\u001a\u0004\b\u0013\u0010)¨\u0006G"}, d2 = {"Lokhttp3/Response;", "Ljava/io/Closeable;", "request", "Lokhttp3/Request;", "protocol", "Lokhttp3/Protocol;", "message", "", "code", "", "handshake", "Lokhttp3/Handshake;", "headers", "Lokhttp3/Headers;", NCXDocumentV3.XHTMLTgs.body, "Lokhttp3/ResponseBody;", "networkResponse", "cacheResponse", "priorResponse", "sentRequestAtMillis", "", "receivedResponseAtMillis", "exchange", "Lokhttp3/internal/connection/Exchange;", "(Lokhttp3/Request;Lokhttp3/Protocol;Ljava/lang/String;ILokhttp3/Handshake;Lokhttp3/Headers;Lokhttp3/ResponseBody;Lokhttp3/Response;Lokhttp3/Response;Lokhttp3/Response;JJLokhttp3/internal/connection/Exchange;)V", "()Lokhttp3/ResponseBody;", "cacheControl", "Lokhttp3/CacheControl;", "()Lokhttp3/CacheControl;", "()Lokhttp3/Response;", "()I", "()Lokhttp3/internal/connection/Exchange;", "()Lokhttp3/Handshake;", "()Lokhttp3/Headers;", "isRedirect", "", "()Z", "isSuccessful", "lazyCacheControl", "()Ljava/lang/String;", "()Lokhttp3/Protocol;", "()J", "()Lokhttp3/Request;", "-deprecated_body", "-deprecated_cacheControl", "-deprecated_cacheResponse", "challenges", "", "Lokhttp3/Challenge;", "close", "", "-deprecated_code", "-deprecated_handshake", "header", "name", "defaultValue", "-deprecated_headers", "-deprecated_message", "-deprecated_networkResponse", "newBuilder", "Lokhttp3/Response$Builder;", "peekBody", "byteCount", "-deprecated_priorResponse", "-deprecated_protocol", "-deprecated_receivedResponseAtMillis", "-deprecated_request", "-deprecated_sentRequestAtMillis", "toString", HttpHeaders.Values.TRAILERS, "Builder", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/Response.class */
public final class Response implements Closeable {
    private CacheControl lazyCacheControl;

    @NotNull
    private final Request request;

    @NotNull
    private final Protocol protocol;

    @NotNull
    private final String message;
    private final int code;

    @Nullable
    private final Handshake handshake;

    @NotNull
    private final Headers headers;

    @Nullable
    private final ResponseBody body;

    @Nullable
    private final Response networkResponse;

    @Nullable
    private final Response cacheResponse;

    @Nullable
    private final Response priorResponse;
    private final long sentRequestAtMillis;
    private final long receivedResponseAtMillis;

    @Nullable
    private final Exchange exchange;

    @JvmOverloads
    @Nullable
    public final String header(@NotNull String name) {
        return header$default(this, name, null, 2, null);
    }

    public Response(@NotNull Request request, @NotNull Protocol protocol, @NotNull String message, int code, @Nullable Handshake handshake, @NotNull Headers headers, @Nullable ResponseBody body, @Nullable Response networkResponse, @Nullable Response cacheResponse, @Nullable Response priorResponse, long sentRequestAtMillis, long receivedResponseAtMillis, @Nullable Exchange exchange) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.request = request;
        this.protocol = protocol;
        this.message = message;
        this.code = code;
        this.handshake = handshake;
        this.headers = headers;
        this.body = body;
        this.networkResponse = networkResponse;
        this.cacheResponse = cacheResponse;
        this.priorResponse = priorResponse;
        this.sentRequestAtMillis = sentRequestAtMillis;
        this.receivedResponseAtMillis = receivedResponseAtMillis;
        this.exchange = exchange;
    }

    @JvmName(name = "request")
    @NotNull
    public final Request request() {
        return this.request;
    }

    @JvmName(name = "protocol")
    @NotNull
    public final Protocol protocol() {
        return this.protocol;
    }

    @JvmName(name = "message")
    @NotNull
    public final String message() {
        return this.message;
    }

    @JvmName(name = "code")
    public final int code() {
        return this.code;
    }

    @JvmName(name = "handshake")
    @Nullable
    public final Handshake handshake() {
        return this.handshake;
    }

    @JvmName(name = "headers")
    @NotNull
    public final Headers headers() {
        return this.headers;
    }

    @JvmName(name = NCXDocumentV3.XHTMLTgs.body)
    @Nullable
    public final ResponseBody body() {
        return this.body;
    }

    @JvmName(name = "networkResponse")
    @Nullable
    public final Response networkResponse() {
        return this.networkResponse;
    }

    @JvmName(name = "cacheResponse")
    @Nullable
    public final Response cacheResponse() {
        return this.cacheResponse;
    }

    @JvmName(name = "priorResponse")
    @Nullable
    public final Response priorResponse() {
        return this.priorResponse;
    }

    @JvmName(name = "sentRequestAtMillis")
    public final long sentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }

    @JvmName(name = "receivedResponseAtMillis")
    public final long receivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }

    @JvmName(name = "exchange")
    @Nullable
    public final Exchange exchange() {
        return this.exchange;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "request"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_request")
    @NotNull
    /* renamed from: -deprecated_request, reason: not valid java name */
    public final Request m4556deprecated_request() {
        return this.request;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "protocol"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_protocol")
    @NotNull
    /* renamed from: -deprecated_protocol, reason: not valid java name */
    public final Protocol m4557deprecated_protocol() {
        return this.protocol;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "code"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_code")
    /* renamed from: -deprecated_code, reason: not valid java name */
    public final int m4558deprecated_code() {
        return this.code;
    }

    public final boolean isSuccessful() {
        int i = this.code;
        return 200 <= i && 299 >= i;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "message"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_message")
    @NotNull
    /* renamed from: -deprecated_message, reason: not valid java name */
    public final String m4559deprecated_message() {
        return this.message;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "handshake"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_handshake")
    @Nullable
    /* renamed from: -deprecated_handshake, reason: not valid java name */
    public final Handshake m4560deprecated_handshake() {
        return this.handshake;
    }

    @NotNull
    public final List<String> headers(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.headers.values(name);
    }

    @JvmOverloads
    @Nullable
    public final String header(@NotNull String name, @Nullable String defaultValue) {
        Intrinsics.checkNotNullParameter(name, "name");
        String str = this.headers.get(name);
        return str != null ? str : defaultValue;
    }

    public static /* synthetic */ String header$default(Response response, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        return response.header(str, str2);
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "headers"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_headers")
    @NotNull
    /* renamed from: -deprecated_headers, reason: not valid java name */
    public final Headers m4561deprecated_headers() {
        return this.headers;
    }

    @NotNull
    public final Headers trailers() throws IOException {
        Exchange exchange = this.exchange;
        if (exchange == null) {
            throw new IllegalStateException("trailers not available".toString());
        }
        return exchange.trailers();
    }

    @NotNull
    public final ResponseBody peekBody(long byteCount) throws IOException {
        ResponseBody responseBody = this.body;
        Intrinsics.checkNotNull(responseBody);
        BufferedSource peeked = responseBody.source().peek();
        Buffer buffer = new Buffer();
        peeked.request(byteCount);
        buffer.write((Source) peeked, Math.min(byteCount, peeked.getBuffer().size()));
        return ResponseBody.Companion.create(buffer, this.body.contentType(), buffer.size());
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = NCXDocumentV3.XHTMLTgs.body), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_body")
    @Nullable
    /* renamed from: -deprecated_body, reason: not valid java name */
    public final ResponseBody m4562deprecated_body() {
        return this.body;
    }

    @NotNull
    public final Builder newBuilder() {
        return new Builder(this);
    }

    public final boolean isRedirect() {
        switch (this.code) {
            case OS2WindowsMetricsTable.WEIGHT_CLASS_LIGHT /* 300 */:
            case 301:
            case 302:
            case 303:
            case StatusLine.HTTP_TEMP_REDIRECT /* 307 */:
            case StatusLine.HTTP_PERM_REDIRECT /* 308 */:
                return true;
            case 304:
            case 305:
            case 306:
            default:
                return false;
        }
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "networkResponse"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_networkResponse")
    @Nullable
    /* renamed from: -deprecated_networkResponse, reason: not valid java name */
    public final Response m4563deprecated_networkResponse() {
        return this.networkResponse;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "cacheResponse"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_cacheResponse")
    @Nullable
    /* renamed from: -deprecated_cacheResponse, reason: not valid java name */
    public final Response m4564deprecated_cacheResponse() {
        return this.cacheResponse;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "priorResponse"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_priorResponse")
    @Nullable
    /* renamed from: -deprecated_priorResponse, reason: not valid java name */
    public final Response m4565deprecated_priorResponse() {
        return this.priorResponse;
    }

    @NotNull
    public final List<Challenge> challenges() {
        String str;
        Headers headers = this.headers;
        switch (this.code) {
            case 401:
                str = "WWW-Authenticate";
                break;
            case 407:
                str = "Proxy-Authenticate";
                break;
            default:
                return CollectionsKt.emptyList();
        }
        return okhttp3.internal.http.HttpHeaders.parseChallenges(headers, str);
    }

    @JvmName(name = "cacheControl")
    @NotNull
    public final CacheControl cacheControl() {
        CacheControl result = this.lazyCacheControl;
        if (result == null) {
            result = CacheControl.Companion.parse(this.headers);
            this.lazyCacheControl = result;
        }
        return result;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "cacheControl"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_cacheControl")
    @NotNull
    /* renamed from: -deprecated_cacheControl, reason: not valid java name */
    public final CacheControl m4566deprecated_cacheControl() {
        return cacheControl();
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "sentRequestAtMillis"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_sentRequestAtMillis")
    /* renamed from: -deprecated_sentRequestAtMillis, reason: not valid java name */
    public final long m4567deprecated_sentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "receivedResponseAtMillis"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_receivedResponseAtMillis")
    /* renamed from: -deprecated_receivedResponseAtMillis, reason: not valid java name */
    public final long m4568deprecated_receivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        ResponseBody responseBody = this.body;
        if (responseBody == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed".toString());
        }
        responseBody.close();
    }

    @NotNull
    public String toString() {
        return "Response{protocol=" + this.protocol + ", code=" + this.code + ", message=" + this.message + ", url=" + this.request.url() + '}';
    }

    /* compiled from: Response.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��l\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018��2\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010I\u001a\u00020��2\u0006\u0010J\u001a\u00020)2\u0006\u0010K\u001a\u00020)H\u0016J\u0012\u0010\u0006\u001a\u00020��2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010L\u001a\u00020\u0004H\u0016J\u0012\u0010\f\u001a\u00020��2\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010M\u001a\u00020N2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0002J\u001a\u0010O\u001a\u00020N2\u0006\u0010J\u001a\u00020)2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010\u0010\u001a\u00020��2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u001c\u001a\u00020��2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0018\u0010P\u001a\u00020��2\u0006\u0010J\u001a\u00020)2\u0006\u0010K\u001a\u00020)H\u0016J\u0010\u0010\"\u001a\u00020��2\u0006\u0010\"\u001a\u00020QH\u0016J\u0015\u0010R\u001a\u00020N2\u0006\u0010S\u001a\u00020\u0017H��¢\u0006\u0002\bTJ\u0010\u0010(\u001a\u00020��2\u0006\u0010(\u001a\u00020)H\u0016J\u0012\u0010.\u001a\u00020��2\b\u0010.\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u00101\u001a\u00020��2\b\u00101\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u00104\u001a\u00020��2\u0006\u00104\u001a\u000205H\u0016J\u0010\u0010:\u001a\u00020��2\u0006\u0010:\u001a\u00020;H\u0016J\u0010\u0010U\u001a\u00020��2\u0006\u0010J\u001a\u00020)H\u0016J\u0010\u0010@\u001a\u00020��2\u0006\u0010@\u001a\u00020AH\u0016J\u0010\u0010F\u001a\u00020��2\u0006\u0010F\u001a\u00020;H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0005R\u001a\u0010\u0010\u001a\u00020\u0011X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001c\u0010(\u001a\u0004\u0018\u00010)X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001c\u0010.\u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b/\u0010\u000e\"\u0004\b0\u0010\u0005R\u001c\u00101\u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b2\u0010\u000e\"\u0004\b3\u0010\u0005R\u001c\u00104\u001a\u0004\u0018\u000105X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010:\u001a\u00020;X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001c\u0010@\u001a\u0004\u0018\u00010AX\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001a\u0010F\u001a\u00020;X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\bG\u0010=\"\u0004\bH\u0010?¨\u0006V"}, d2 = {"Lokhttp3/Response$Builder;", "", "()V", "response", "Lokhttp3/Response;", "(Lokhttp3/Response;)V", NCXDocumentV3.XHTMLTgs.body, "Lokhttp3/ResponseBody;", "getBody$okhttp", "()Lokhttp3/ResponseBody;", "setBody$okhttp", "(Lokhttp3/ResponseBody;)V", "cacheResponse", "getCacheResponse$okhttp", "()Lokhttp3/Response;", "setCacheResponse$okhttp", "code", "", "getCode$okhttp", "()I", "setCode$okhttp", "(I)V", "exchange", "Lokhttp3/internal/connection/Exchange;", "getExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "setExchange$okhttp", "(Lokhttp3/internal/connection/Exchange;)V", "handshake", "Lokhttp3/Handshake;", "getHandshake$okhttp", "()Lokhttp3/Handshake;", "setHandshake$okhttp", "(Lokhttp3/Handshake;)V", "headers", "Lokhttp3/Headers$Builder;", "getHeaders$okhttp", "()Lokhttp3/Headers$Builder;", "setHeaders$okhttp", "(Lokhttp3/Headers$Builder;)V", "message", "", "getMessage$okhttp", "()Ljava/lang/String;", "setMessage$okhttp", "(Ljava/lang/String;)V", "networkResponse", "getNetworkResponse$okhttp", "setNetworkResponse$okhttp", "priorResponse", "getPriorResponse$okhttp", "setPriorResponse$okhttp", "protocol", "Lokhttp3/Protocol;", "getProtocol$okhttp", "()Lokhttp3/Protocol;", "setProtocol$okhttp", "(Lokhttp3/Protocol;)V", "receivedResponseAtMillis", "", "getReceivedResponseAtMillis$okhttp", "()J", "setReceivedResponseAtMillis$okhttp", "(J)V", "request", "Lokhttp3/Request;", "getRequest$okhttp", "()Lokhttp3/Request;", "setRequest$okhttp", "(Lokhttp3/Request;)V", "sentRequestAtMillis", "getSentRequestAtMillis$okhttp", "setSentRequestAtMillis$okhttp", "addHeader", "name", "value", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "checkPriorResponse", "", "checkSupportResponse", "header", "Lokhttp3/Headers;", "initExchange", "deferredTrailers", "initExchange$okhttp", "removeHeader", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/Response$Builder.class */
    public static class Builder {

        @Nullable
        private Request request;

        @Nullable
        private Protocol protocol;
        private int code;

        @Nullable
        private String message;

        @Nullable
        private Handshake handshake;

        @NotNull
        private Headers.Builder headers;

        @Nullable
        private ResponseBody body;

        @Nullable
        private Response networkResponse;

        @Nullable
        private Response cacheResponse;

        @Nullable
        private Response priorResponse;
        private long sentRequestAtMillis;
        private long receivedResponseAtMillis;

        @Nullable
        private Exchange exchange;

        @Nullable
        public final Request getRequest$okhttp() {
            return this.request;
        }

        public final void setRequest$okhttp(@Nullable Request request) {
            this.request = request;
        }

        @Nullable
        public final Protocol getProtocol$okhttp() {
            return this.protocol;
        }

        public final void setProtocol$okhttp(@Nullable Protocol protocol) {
            this.protocol = protocol;
        }

        public final int getCode$okhttp() {
            return this.code;
        }

        public final void setCode$okhttp(int i) {
            this.code = i;
        }

        @Nullable
        public final String getMessage$okhttp() {
            return this.message;
        }

        public final void setMessage$okhttp(@Nullable String str) {
            this.message = str;
        }

        @Nullable
        public final Handshake getHandshake$okhttp() {
            return this.handshake;
        }

        public final void setHandshake$okhttp(@Nullable Handshake handshake) {
            this.handshake = handshake;
        }

        @NotNull
        public final Headers.Builder getHeaders$okhttp() {
            return this.headers;
        }

        public final void setHeaders$okhttp(@NotNull Headers.Builder builder) {
            Intrinsics.checkNotNullParameter(builder, "<set-?>");
            this.headers = builder;
        }

        @Nullable
        public final ResponseBody getBody$okhttp() {
            return this.body;
        }

        public final void setBody$okhttp(@Nullable ResponseBody responseBody) {
            this.body = responseBody;
        }

        @Nullable
        public final Response getNetworkResponse$okhttp() {
            return this.networkResponse;
        }

        public final void setNetworkResponse$okhttp(@Nullable Response response) {
            this.networkResponse = response;
        }

        @Nullable
        public final Response getCacheResponse$okhttp() {
            return this.cacheResponse;
        }

        public final void setCacheResponse$okhttp(@Nullable Response response) {
            this.cacheResponse = response;
        }

        @Nullable
        public final Response getPriorResponse$okhttp() {
            return this.priorResponse;
        }

        public final void setPriorResponse$okhttp(@Nullable Response response) {
            this.priorResponse = response;
        }

        public final long getSentRequestAtMillis$okhttp() {
            return this.sentRequestAtMillis;
        }

        public final void setSentRequestAtMillis$okhttp(long j) {
            this.sentRequestAtMillis = j;
        }

        public final long getReceivedResponseAtMillis$okhttp() {
            return this.receivedResponseAtMillis;
        }

        public final void setReceivedResponseAtMillis$okhttp(long j) {
            this.receivedResponseAtMillis = j;
        }

        @Nullable
        public final Exchange getExchange$okhttp() {
            return this.exchange;
        }

        public final void setExchange$okhttp(@Nullable Exchange exchange) {
            this.exchange = exchange;
        }

        public Builder() {
            this.code = -1;
            this.headers = new Headers.Builder();
        }

        public Builder(@NotNull Response response) {
            Intrinsics.checkNotNullParameter(response, "response");
            this.code = -1;
            this.request = response.request();
            this.protocol = response.protocol();
            this.code = response.code();
            this.message = response.message();
            this.handshake = response.handshake();
            this.headers = response.headers().newBuilder();
            this.body = response.body();
            this.networkResponse = response.networkResponse();
            this.cacheResponse = response.cacheResponse();
            this.priorResponse = response.priorResponse();
            this.sentRequestAtMillis = response.sentRequestAtMillis();
            this.receivedResponseAtMillis = response.receivedResponseAtMillis();
            this.exchange = response.exchange();
        }

        @NotNull
        public Builder request(@NotNull Request request) {
            Intrinsics.checkNotNullParameter(request, "request");
            Builder $this$apply = this;
            $this$apply.request = request;
            return this;
        }

        @NotNull
        public Builder protocol(@NotNull Protocol protocol) {
            Intrinsics.checkNotNullParameter(protocol, "protocol");
            Builder $this$apply = this;
            $this$apply.protocol = protocol;
            return this;
        }

        @NotNull
        public Builder code(int code) {
            Builder $this$apply = this;
            $this$apply.code = code;
            return this;
        }

        @NotNull
        public Builder message(@NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Builder $this$apply = this;
            $this$apply.message = message;
            return this;
        }

        @NotNull
        public Builder handshake(@Nullable Handshake handshake) {
            Builder $this$apply = this;
            $this$apply.handshake = handshake;
            return this;
        }

        @NotNull
        public Builder header(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder $this$apply = this;
            $this$apply.headers.set(name, value);
            return this;
        }

        @NotNull
        public Builder addHeader(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder $this$apply = this;
            $this$apply.headers.add(name, value);
            return this;
        }

        @NotNull
        public Builder removeHeader(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            Builder $this$apply = this;
            $this$apply.headers.removeAll(name);
            return this;
        }

        @NotNull
        public Builder headers(@NotNull Headers headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            Builder $this$apply = this;
            $this$apply.headers = headers.newBuilder();
            return this;
        }

        @NotNull
        public Builder body(@Nullable ResponseBody body) {
            Builder $this$apply = this;
            $this$apply.body = body;
            return this;
        }

        @NotNull
        public Builder networkResponse(@Nullable Response networkResponse) {
            Builder $this$apply = this;
            $this$apply.checkSupportResponse("networkResponse", networkResponse);
            $this$apply.networkResponse = networkResponse;
            return this;
        }

        @NotNull
        public Builder cacheResponse(@Nullable Response cacheResponse) {
            Builder $this$apply = this;
            $this$apply.checkSupportResponse("cacheResponse", cacheResponse);
            $this$apply.cacheResponse = cacheResponse;
            return this;
        }

        private final void checkSupportResponse(String name, Response response) {
            if (response != null) {
                if (!(response.body() == null)) {
                    throw new IllegalArgumentException((name + ".body != null").toString());
                }
                if (!(response.networkResponse() == null)) {
                    throw new IllegalArgumentException((name + ".networkResponse != null").toString());
                }
                if (!(response.cacheResponse() == null)) {
                    throw new IllegalArgumentException((name + ".cacheResponse != null").toString());
                }
                if (!(response.priorResponse() == null)) {
                    throw new IllegalArgumentException((name + ".priorResponse != null").toString());
                }
            }
        }

        @NotNull
        public Builder priorResponse(@Nullable Response priorResponse) {
            Builder $this$apply = this;
            $this$apply.checkPriorResponse(priorResponse);
            $this$apply.priorResponse = priorResponse;
            return this;
        }

        private final void checkPriorResponse(Response response) {
            if (response != null) {
                if (!(response.body() == null)) {
                    throw new IllegalArgumentException("priorResponse.body != null".toString());
                }
            }
        }

        @NotNull
        public Builder sentRequestAtMillis(long sentRequestAtMillis) {
            Builder $this$apply = this;
            $this$apply.sentRequestAtMillis = sentRequestAtMillis;
            return this;
        }

        @NotNull
        public Builder receivedResponseAtMillis(long receivedResponseAtMillis) {
            Builder $this$apply = this;
            $this$apply.receivedResponseAtMillis = receivedResponseAtMillis;
            return this;
        }

        public final void initExchange$okhttp(@NotNull Exchange deferredTrailers) {
            Intrinsics.checkNotNullParameter(deferredTrailers, "deferredTrailers");
            this.exchange = deferredTrailers;
        }

        @NotNull
        public Response build() {
            if (!(this.code >= 0)) {
                throw new IllegalStateException(("code < 0: " + this.code).toString());
            }
            Request request = this.request;
            if (request == null) {
                throw new IllegalStateException("request == null".toString());
            }
            Protocol protocol = this.protocol;
            if (protocol == null) {
                throw new IllegalStateException("protocol == null".toString());
            }
            String str = this.message;
            if (str == null) {
                throw new IllegalStateException("message == null".toString());
            }
            return new Response(request, protocol, str, this.code, this.handshake, this.headers.build(), this.body, this.networkResponse, this.cacheResponse, this.priorResponse, this.sentRequestAtMillis, this.receivedResponseAtMillis, this.exchange);
        }
    }
}
