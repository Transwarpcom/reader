package io.vertx.ext.web.impl;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.impl.HttpUtils;
import io.vertx.core.http.impl.ServerCookie;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Locale;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.codec.impl.BodyCodecImpl;
import io.vertx.ext.web.handler.impl.HttpStatusException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/RoutingContextImpl.class */
public class RoutingContextImpl extends RoutingContextImplBase {
    private final RouterImpl router;
    private Map<String, Object> data;
    private Map<String, String> pathParams;
    private MultiMap queryParams;
    private AtomicInteger handlerSeq;
    private Map<Integer, Handler<Void>> headersEndHandlers;
    private Map<Integer, Handler<Void>> bodyEndHandlers;
    private Throwable failure;
    private int statusCode;
    private String normalisedPath;
    private String acceptableContentType;
    private ParsableHeaderValuesContainer parsedHeaders;
    private Map<String, Cookie> cookies;
    private Buffer body;
    private Set<FileUpload> fileUploads;
    private Session session;
    private User user;
    private static final String DEFAULT_404 = "<html><body><h1>Resource not found</h1></body></html>";

    public RoutingContextImpl(String mountPoint, RouterImpl router, HttpServerRequest request, Set<RouteImpl> routes) {
        super(mountPoint, request, routes);
        this.handlerSeq = new AtomicInteger();
        this.statusCode = -1;
        this.router = router;
        fillParsedHeaders(request);
        if (request.path().length() == 0) {
            fail(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL);
        } else if (request.path().charAt(0) != '/') {
            fail(404);
        }
    }

    private String ensureNotNull(String string) {
        return string == null ? "" : string;
    }

    private void fillParsedHeaders(HttpServerRequest request) {
        String accept = request.getHeader("Accept");
        String acceptCharset = request.getHeader("Accept-Charset");
        String acceptEncoding = request.getHeader("Accept-Encoding");
        String acceptLanguage = request.getHeader("Accept-Language");
        String contentType = ensureNotNull(request.getHeader("Content-Type"));
        this.parsedHeaders = new ParsableHeaderValuesContainer(HeaderParser.sort(HeaderParser.convertToParsedHeaderValues(accept, ParsableMIMEValue::new)), HeaderParser.sort(HeaderParser.convertToParsedHeaderValues(acceptCharset, ParsableHeaderValue::new)), HeaderParser.sort(HeaderParser.convertToParsedHeaderValues(acceptEncoding, ParsableHeaderValue::new)), HeaderParser.sort(HeaderParser.convertToParsedHeaderValues(acceptLanguage, ParsableLanguageValue::new)), new ParsableMIMEValue(contentType));
    }

    @Override // io.vertx.ext.web.RoutingContext
    public HttpServerRequest request() {
        return this.request;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public HttpServerResponse response() {
        return this.request.response();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Throwable failure() {
        return this.failure;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public int statusCode() {
        return this.statusCode;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public boolean failed() {
        return (this.failure == null && this.statusCode == -1) ? false : true;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void next() {
        if (!iterateNext()) {
            checkHandleNoMatch();
        }
    }

    private void checkHandleNoMatch() {
        if (failed()) {
            unhandledFailure(this.statusCode, this.failure, this.router);
            return;
        }
        Handler<RoutingContext> handler = this.router.getErrorHandlerByStatusCode(this.matchFailure);
        this.statusCode = this.matchFailure;
        if (handler == null) {
            response().setStatusCode(this.matchFailure);
            if (request().method() != HttpMethod.HEAD && this.matchFailure == 404) {
                response().putHeader(HttpHeaderNames.CONTENT_TYPE, NCXDocumentV3.XHTMLAttributeValues.HTML_UTF8).end(DEFAULT_404);
                return;
            } else {
                response().end();
                return;
            }
        }
        handler.handle(this);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void fail(int statusCode) {
        this.statusCode = statusCode;
        doFail();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void fail(Throwable t) {
        fail(-1, t);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void fail(int statusCode, Throwable throwable) {
        this.statusCode = statusCode;
        this.failure = throwable == null ? new NullPointerException() : throwable;
        doFail();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public RoutingContext put(String key, Object obj) {
        getData().put(key, obj);
        return this;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Vertx vertx() {
        return this.router.vertx();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public <T> T get(String str) {
        return (T) getData().get(str);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public <T> T remove(String str) {
        return (T) getData().remove(str);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Map<String, Object> data() {
        return getData();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String normalisedPath() {
        if (this.normalisedPath == null) {
            String path = this.request.path();
            if (path == null) {
                this.normalisedPath = "/";
            } else {
                this.normalisedPath = HttpUtils.normalizePath(path);
            }
        }
        return this.normalisedPath;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Cookie getCookie(String name) {
        return CookieImpl.wrapIfNecessary((ServerCookie) this.request.getCookie(name));
    }

    @Override // io.vertx.ext.web.RoutingContext
    public RoutingContext addCookie(Cookie cookie) {
        return addCookie((io.vertx.core.http.Cookie) cookie);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public RoutingContext addCookie(io.vertx.core.http.Cookie cookie) {
        this.request.response().addCookie(cookie);
        return this;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Cookie removeCookie(String name, boolean invalidate) {
        ServerCookie cookie = (ServerCookie) this.request.response().removeCookie(name, invalidate);
        return CookieImpl.wrapIfNecessary(cookie);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public int cookieCount() {
        return this.request.cookieCount();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Set<Cookie> cookies() {
        return (Set) this.request.cookieMap().values().stream().map(c -> {
            return (ServerCookie) c;
        }).map(CookieImpl::wrapIfNecessary).collect(Collectors.toCollection(HashSet::new));
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Map<String, io.vertx.core.http.Cookie> cookieMap() {
        return this.request.cookieMap();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String getBodyAsString() {
        if (this.body != null) {
            return this.body.toString();
        }
        return null;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String getBodyAsString(String encoding) {
        if (this.body != null) {
            return this.body.toString(encoding);
        }
        return null;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public JsonObject getBodyAsJson() {
        if (this.body != null && this.body.length() > 1) {
            return BodyCodecImpl.JSON_OBJECT_DECODER.apply(this.body);
        }
        return null;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public JsonArray getBodyAsJsonArray() {
        if (this.body != null && this.body.length() > 1) {
            return BodyCodecImpl.JSON_ARRAY_DECODER.apply(this.body);
        }
        return null;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Buffer getBody() {
        return this.body;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void setBody(Buffer body) {
        this.body = body;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Set<FileUpload> fileUploads() {
        return getFileUploads();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void setSession(Session session) {
        this.session = session;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Session session() {
        return this.session;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public User user() {
        return this.user;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void setUser(User user) {
        this.user = user;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void clearUser() {
        this.user = null;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String getAcceptableContentType() {
        return this.acceptableContentType;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void setAcceptableContentType(String contentType) {
        this.acceptableContentType = contentType;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public ParsableHeaderValuesContainer parsedHeaders() {
        return this.parsedHeaders;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public int addHeadersEndHandler(Handler<Void> handler) {
        int seq = nextHandlerSeq();
        getHeadersEndHandlers().put(Integer.valueOf(seq), handler);
        return seq;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public boolean removeHeadersEndHandler(int handlerID) {
        return getHeadersEndHandlers().remove(Integer.valueOf(handlerID)) != null;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public int addBodyEndHandler(Handler<Void> handler) {
        int seq = nextHandlerSeq();
        getBodyEndHandlers().put(Integer.valueOf(seq), handler);
        return seq;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public boolean removeBodyEndHandler(int handlerID) {
        return getBodyEndHandlers().remove(Integer.valueOf(handlerID)) != null;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void reroute(HttpMethod method, String path) {
        if (path.charAt(0) != '/') {
            throw new IllegalArgumentException("path must start with '/'");
        }
        ((HttpServerRequestWrapper) this.request).changeTo(method, path);
        this.request.params().mo1932clear();
        this.normalisedPath = null;
        this.statusCode = -1;
        response().headers().mo1932clear();
        if (this.cookies != null) {
            this.cookies.clear();
        }
        if (this.headersEndHandlers != null) {
            this.headersEndHandlers.clear();
        }
        if (this.bodyEndHandlers != null) {
            this.bodyEndHandlers.clear();
        }
        this.failure = null;
        restart();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public List<Locale> acceptableLocales() {
        return this.parsedHeaders.acceptLanguage();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Map<String, String> pathParams() {
        return getPathParams();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String pathParam(String name) {
        return getPathParams().get(name);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public MultiMap queryParams() {
        return getQueryParams();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public List<String> queryParam(String query) {
        return getQueryParams().getAll(query);
    }

    private MultiMap getQueryParams() {
        if (this.queryParams == null) {
            try {
                this.queryParams = MultiMap.caseInsensitiveMultiMap();
                Map<String, List<String>> decodedParams = new QueryStringDecoder(this.request.uri()).parameters();
                for (Map.Entry<String, List<String>> entry : decodedParams.entrySet()) {
                    this.queryParams.mo1938add(entry.getKey(), (Iterable<String>) entry.getValue());
                }
            } catch (IllegalArgumentException e) {
                throw new HttpStatusException(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL, "Error while decoding query params", e);
            }
        }
        return this.queryParams;
    }

    private Map<String, String> getPathParams() {
        if (this.pathParams == null) {
            this.pathParams = new HashMap();
        }
        return this.pathParams;
    }

    private Map<Integer, Handler<Void>> getHeadersEndHandlers() {
        if (this.headersEndHandlers == null) {
            this.headersEndHandlers = new TreeMap(Collections.reverseOrder());
            response().headersEndHandler(v -> {
                this.headersEndHandlers.values().forEach(handler -> {
                    handler.handle(null);
                });
            });
        }
        return this.headersEndHandlers;
    }

    private Map<Integer, Handler<Void>> getBodyEndHandlers() {
        if (this.bodyEndHandlers == null) {
            this.bodyEndHandlers = new TreeMap(Collections.reverseOrder());
            response().bodyEndHandler(v -> {
                this.bodyEndHandlers.values().forEach(handler -> {
                    handler.handle(null);
                });
            });
        }
        return this.bodyEndHandlers;
    }

    private Set<FileUpload> getFileUploads() {
        if (this.fileUploads == null) {
            this.fileUploads = new HashSet();
        }
        return this.fileUploads;
    }

    private void doFail() {
        this.iter = this.router.iterator();
        this.currentRoute = null;
        next();
    }

    private Map<String, Object> getData() {
        if (this.data == null) {
            this.data = new HashMap();
        }
        return this.data;
    }

    private int nextHandlerSeq() {
        int seq = this.handlerSeq.incrementAndGet();
        if (seq == Integer.MAX_VALUE) {
            throw new IllegalStateException("Too many header/body end handlers!");
        }
        return seq;
    }
}
