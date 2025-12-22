package org.jsoup.helper;

import ch.qos.logback.classic.spi.CallerData;
import cn.hutool.core.text.StrPool;
import io.netty.handler.codec.http.HttpHeaders;
import io.vertx.core.cli.UsageMessageFormatter;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpURLConnection;
import java.net.IDN;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import javax.annotation.Nullable;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.UncheckedIOException;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.internal.ConstrainableInputStream;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.parser.TokenQueue;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/helper/HttpConnection.class */
public class HttpConnection implements Connection {
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String DEFAULT_UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36";
    private static final String USER_AGENT = "User-Agent";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public static final String FORM_URL_ENCODED = "application/x-www-form-urlencoded";
    private static final int HTTP_TEMP_REDIR = 307;
    private static final String DefaultUploadType = "application/octet-stream";
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    private Request req;

    @Nullable
    private Connection.Response res;

    public static Connection connect(String url) {
        Connection con = new HttpConnection();
        con.url(url);
        return con;
    }

    public static Connection connect(URL url) {
        Connection con = new HttpConnection();
        con.url(url);
        return con;
    }

    public HttpConnection() {
        this.req = new Request();
    }

    HttpConnection(Request copy) {
        this.req = new Request(copy);
    }

    private static String encodeUrl(String url) {
        try {
            URL u = new URL(url);
            return encodeUrl(u).toExternalForm();
        } catch (Exception e) {
            return url;
        }
    }

    static URL encodeUrl(URL u) {
        URL u2 = punyUrl(u);
        try {
            String urlS = u2.toExternalForm();
            URI uri = new URI(urlS.replace(" ", "%20"));
            return new URL(uri.toASCIIString());
        } catch (MalformedURLException | URISyntaxException e) {
            return u2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static URL punyUrl(URL url) {
        if (!StringUtil.isAscii(url.getHost())) {
            try {
                String puny = IDN.toASCII(url.getHost());
                url = new URL(url.getProtocol(), puny, url.getPort(), url.getFile());
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return url;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String encodeMimeName(String val) {
        return val.replace(OperatorName.SHOW_TEXT_LINE_AND_SPACE, "%22");
    }

    @Override // org.jsoup.Connection
    public Connection newRequest() {
        return new HttpConnection(this.req);
    }

    private HttpConnection(Request req, Response res) {
        this.req = req;
        this.res = res;
    }

    @Override // org.jsoup.Connection
    public Connection url(URL url) {
        this.req.url(url);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection url(String url) {
        Validate.notEmpty(url, "Must supply a valid URL");
        try {
            this.req.url(new URL(encodeUrl(url)));
            return this;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Malformed URL: " + url, e);
        }
    }

    @Override // org.jsoup.Connection
    public Connection proxy(@Nullable Proxy proxy) {
        this.req.proxy(proxy);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection proxy(String host, int port) {
        this.req.proxy(host, port);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection userAgent(String userAgent) {
        Validate.notNull(userAgent, "User agent must not be null");
        this.req.header("User-Agent", userAgent);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection timeout(int millis) {
        this.req.timeout(millis);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection maxBodySize(int bytes) {
        this.req.maxBodySize(bytes);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection followRedirects(boolean followRedirects) {
        this.req.followRedirects(followRedirects);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection referrer(String referrer) {
        Validate.notNull(referrer, "Referrer must not be null");
        this.req.header("Referer", referrer);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection method(Connection.Method method) {
        this.req.method(method);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection ignoreHttpErrors(boolean ignoreHttpErrors) {
        this.req.ignoreHttpErrors(ignoreHttpErrors);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection ignoreContentType(boolean ignoreContentType) {
        this.req.ignoreContentType(ignoreContentType);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection data(String key, String value) {
        this.req.data((Connection.KeyVal) KeyVal.create(key, value));
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection sslSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.req.sslSocketFactory(sslSocketFactory);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection data(String key, String filename, InputStream inputStream) {
        this.req.data((Connection.KeyVal) KeyVal.create(key, filename, inputStream));
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection data(String key, String filename, InputStream inputStream, String contentType) {
        this.req.data(KeyVal.create(key, filename, inputStream).contentType(contentType));
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection data(Map<String, String> data) {
        Validate.notNull(data, "Data map must not be null");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            this.req.data((Connection.KeyVal) KeyVal.create(entry.getKey(), entry.getValue()));
        }
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection data(String... keyvals) {
        Validate.notNull(keyvals, "Data key value pairs must not be null");
        Validate.isTrue(keyvals.length % 2 == 0, "Must supply an even number of key value pairs");
        for (int i = 0; i < keyvals.length; i += 2) {
            String key = keyvals[i];
            String value = keyvals[i + 1];
            Validate.notEmpty(key, "Data key must not be empty");
            Validate.notNull(value, "Data value must not be null");
            this.req.data((Connection.KeyVal) KeyVal.create(key, value));
        }
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection data(Collection<Connection.KeyVal> data) {
        Validate.notNull(data, "Data collection must not be null");
        for (Connection.KeyVal entry : data) {
            this.req.data(entry);
        }
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection.KeyVal data(String key) {
        Validate.notEmpty(key, "Data key must not be empty");
        for (Connection.KeyVal keyVal : request().data()) {
            if (keyVal.key().equals(key)) {
                return keyVal;
            }
        }
        return null;
    }

    @Override // org.jsoup.Connection
    public Connection requestBody(String body) {
        this.req.requestBody(body);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection header(String name, String value) {
        this.req.header(name, value);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection headers(Map<String, String> headers) {
        Validate.notNull(headers, "Header map must not be null");
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            this.req.header(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection cookie(String name, String value) {
        this.req.cookie(name, value);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection cookies(Map<String, String> cookies) {
        Validate.notNull(cookies, "Cookie map must not be null");
        for (Map.Entry<String, String> entry : cookies.entrySet()) {
            this.req.cookie(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection cookieStore(CookieStore cookieStore) {
        this.req.cookieManager = new CookieManager(cookieStore, null);
        return this;
    }

    @Override // org.jsoup.Connection
    public CookieStore cookieStore() {
        return this.req.cookieManager.getCookieStore();
    }

    @Override // org.jsoup.Connection
    public Connection parser(Parser parser) {
        this.req.parser(parser);
        return this;
    }

    @Override // org.jsoup.Connection
    public Document get() throws IOException {
        this.req.method(Connection.Method.GET);
        execute();
        Validate.notNull(this.res);
        return this.res.parse();
    }

    @Override // org.jsoup.Connection
    public Document post() throws IOException {
        this.req.method(Connection.Method.POST);
        execute();
        Validate.notNull(this.res);
        return this.res.parse();
    }

    @Override // org.jsoup.Connection
    public Connection.Response execute() throws IOException {
        this.res = Response.execute(this.req);
        return this.res;
    }

    @Override // org.jsoup.Connection
    public Connection.Request request() {
        return this.req;
    }

    @Override // org.jsoup.Connection
    public Connection request(Connection.Request request) {
        this.req = (Request) request;
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection.Response response() {
        if (this.res == null) {
            throw new IllegalArgumentException("You must execute the request before getting a response.");
        }
        return this.res;
    }

    @Override // org.jsoup.Connection
    public Connection response(Connection.Response response) {
        this.res = response;
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection postDataCharset(String charset) {
        this.req.postDataCharset(charset);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/helper/HttpConnection$Base.class */
    static abstract class Base<T extends Connection.Base<T>> implements Connection.Base<T> {
        private static final URL UnsetUrl;
        URL url;
        Connection.Method method;
        Map<String, List<String>> headers;
        Map<String, String> cookies;

        static {
            try {
                UnsetUrl = new URL("http://undefined/");
            } catch (MalformedURLException e) {
                throw new IllegalStateException(e);
            }
        }

        private Base() {
            this.url = UnsetUrl;
            this.method = Connection.Method.GET;
            this.headers = new LinkedHashMap();
            this.cookies = new LinkedHashMap();
        }

        private Base(Base<T> copy) {
            this.url = UnsetUrl;
            this.method = Connection.Method.GET;
            this.url = copy.url;
            this.method = copy.method;
            this.headers = new LinkedHashMap();
            for (Map.Entry<String, List<String>> entry : copy.headers.entrySet()) {
                this.headers.put(entry.getKey(), new ArrayList(entry.getValue()));
            }
            this.cookies = new LinkedHashMap();
            this.cookies.putAll(copy.cookies);
        }

        @Override // org.jsoup.Connection.Base
        public URL url() {
            if (this.url == UnsetUrl) {
                throw new IllegalArgumentException("URL not set. Make sure to call #url(...) before executing the request.");
            }
            return this.url;
        }

        @Override // org.jsoup.Connection.Base
        public T url(URL url) {
            Validate.notNull(url, "URL must not be null");
            this.url = HttpConnection.punyUrl(url);
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public Connection.Method method() {
            return this.method;
        }

        @Override // org.jsoup.Connection.Base
        public T method(Connection.Method method) {
            Validate.notNull(method, "Method must not be null");
            this.method = method;
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public String header(String name) {
            Validate.notNull(name, "Header name must not be null");
            List<String> vals = getHeadersCaseInsensitive(name);
            if (vals.size() > 0) {
                return StringUtil.join(vals, ", ");
            }
            return null;
        }

        @Override // org.jsoup.Connection.Base
        public T addHeader(String name, String value) {
            Validate.notEmpty(name);
            String value2 = value == null ? "" : value;
            List<String> values = headers(name);
            if (values.isEmpty()) {
                values = new ArrayList();
                this.headers.put(name, values);
            }
            values.add(fixHeaderEncoding(value2));
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public List<String> headers(String name) {
            Validate.notEmpty(name);
            return getHeadersCaseInsensitive(name);
        }

        private static String fixHeaderEncoding(String val) {
            byte[] bytes = val.getBytes(HttpConnection.ISO_8859_1);
            if (!looksLikeUtf8(bytes)) {
                return val;
            }
            return new String(bytes, HttpConnection.UTF_8);
        }

        private static boolean looksLikeUtf8(byte[] input) {
            int end;
            int i = 0;
            if (input.length >= 3 && (input[0] & 255) == 239 && (input[1] & 255) == 187 && (input[2] & 255) == 191) {
                i = 3;
            }
            int j = input.length;
            while (i < j) {
                byte b = input[i];
                if ((b & 128) != 0) {
                    if ((b & 224) == 192) {
                        end = i + 1;
                    } else if ((b & 240) == 224) {
                        end = i + 2;
                    } else if ((b & 248) == 240) {
                        end = i + 3;
                    } else {
                        return false;
                    }
                    if (end >= input.length) {
                        return false;
                    }
                    while (i < end) {
                        i++;
                        if ((input[i] & 192) != 128) {
                            return false;
                        }
                    }
                }
                i++;
            }
            return true;
        }

        @Override // org.jsoup.Connection.Base
        public T header(String name, String value) {
            Validate.notEmpty(name, "Header name must not be empty");
            removeHeader(name);
            addHeader(name, value);
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public boolean hasHeader(String name) {
            Validate.notEmpty(name, "Header name must not be empty");
            return !getHeadersCaseInsensitive(name).isEmpty();
        }

        @Override // org.jsoup.Connection.Base
        public boolean hasHeaderWithValue(String name, String value) {
            Validate.notEmpty(name);
            Validate.notEmpty(value);
            List<String> values = headers(name);
            for (String candidate : values) {
                if (value.equalsIgnoreCase(candidate)) {
                    return true;
                }
            }
            return false;
        }

        @Override // org.jsoup.Connection.Base
        public T removeHeader(String name) {
            Validate.notEmpty(name, "Header name must not be empty");
            Map.Entry<String, List<String>> entry = scanHeaders(name);
            if (entry != null) {
                this.headers.remove(entry.getKey());
            }
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public Map<String, String> headers() {
            LinkedHashMap<String, String> map = new LinkedHashMap<>(this.headers.size());
            for (Map.Entry<String, List<String>> entry : this.headers.entrySet()) {
                String header = entry.getKey();
                List<String> values = entry.getValue();
                if (values.size() > 0) {
                    map.put(header, values.get(0));
                }
            }
            return map;
        }

        @Override // org.jsoup.Connection.Base
        public Map<String, List<String>> multiHeaders() {
            return this.headers;
        }

        private List<String> getHeadersCaseInsensitive(String name) {
            Validate.notNull(name);
            for (Map.Entry<String, List<String>> entry : this.headers.entrySet()) {
                if (name.equalsIgnoreCase(entry.getKey())) {
                    return entry.getValue();
                }
            }
            return Collections.emptyList();
        }

        @Nullable
        private Map.Entry<String, List<String>> scanHeaders(String name) {
            String lc = Normalizer.lowerCase(name);
            for (Map.Entry<String, List<String>> entry : this.headers.entrySet()) {
                if (Normalizer.lowerCase(entry.getKey()).equals(lc)) {
                    return entry;
                }
            }
            return null;
        }

        @Override // org.jsoup.Connection.Base
        public String cookie(String name) {
            Validate.notEmpty(name, "Cookie name must not be empty");
            return this.cookies.get(name);
        }

        @Override // org.jsoup.Connection.Base
        public T cookie(String name, String value) {
            Validate.notEmpty(name, "Cookie name must not be empty");
            Validate.notNull(value, "Cookie value must not be null");
            this.cookies.put(name, value);
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public boolean hasCookie(String name) {
            Validate.notEmpty(name, "Cookie name must not be empty");
            return this.cookies.containsKey(name);
        }

        @Override // org.jsoup.Connection.Base
        public T removeCookie(String name) {
            Validate.notEmpty(name, "Cookie name must not be empty");
            this.cookies.remove(name);
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public Map<String, String> cookies() {
            return this.cookies;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/helper/HttpConnection$Request.class */
    public static class Request extends Base<Connection.Request> implements Connection.Request {

        @Nullable
        private Proxy proxy;
        private int timeoutMilliseconds;
        private int maxBodySizeBytes;
        private boolean followRedirects;
        private final Collection<Connection.KeyVal> data;

        @Nullable
        private String body;
        private boolean ignoreHttpErrors;
        private boolean ignoreContentType;
        private Parser parser;
        private boolean parserDefined;
        private String postDataCharset;

        @Nullable
        private SSLSocketFactory sslSocketFactory;
        private CookieManager cookieManager;
        private volatile boolean executing;

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map cookies() {
            return super.cookies();
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base removeCookie(String str) {
            return super.removeCookie(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasCookie(String str) {
            return super.hasCookie(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base cookie(String str, String str2) {
            return super.cookie(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ String cookie(String str) {
            return super.cookie(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map multiHeaders() {
            return super.multiHeaders();
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map headers() {
            return super.headers();
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base removeHeader(String str) {
            return super.removeHeader(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasHeaderWithValue(String str, String str2) {
            return super.hasHeaderWithValue(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasHeader(String str) {
            return super.hasHeader(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base header(String str, String str2) {
            return super.header(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ List headers(String str) {
            return super.headers(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base addHeader(String str, String str2) {
            return super.addHeader(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ String header(String str) {
            return super.header(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base method(Connection.Method method) {
            return super.method(method);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Method method() {
            return super.method();
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base url(URL url) {
            return super.url(url);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ URL url() {
            return super.url();
        }

        static {
            System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        }

        Request() {
            super();
            this.body = null;
            this.ignoreHttpErrors = false;
            this.ignoreContentType = false;
            this.parserDefined = false;
            this.postDataCharset = DataUtil.defaultCharsetName;
            this.executing = false;
            this.timeoutMilliseconds = 30000;
            this.maxBodySizeBytes = 2097152;
            this.followRedirects = true;
            this.data = new ArrayList();
            this.method = Connection.Method.GET;
            addHeader("Accept-Encoding", "gzip");
            addHeader("User-Agent", HttpConnection.DEFAULT_UA);
            this.parser = Parser.htmlParser();
            this.cookieManager = new CookieManager();
        }

        Request(Request copy) {
            super(copy);
            this.body = null;
            this.ignoreHttpErrors = false;
            this.ignoreContentType = false;
            this.parserDefined = false;
            this.postDataCharset = DataUtil.defaultCharsetName;
            this.executing = false;
            this.proxy = copy.proxy;
            this.postDataCharset = copy.postDataCharset;
            this.timeoutMilliseconds = copy.timeoutMilliseconds;
            this.maxBodySizeBytes = copy.maxBodySizeBytes;
            this.followRedirects = copy.followRedirects;
            this.data = new ArrayList();
            this.data.addAll(copy.data());
            this.body = copy.body;
            this.ignoreHttpErrors = copy.ignoreHttpErrors;
            this.ignoreContentType = copy.ignoreContentType;
            this.parser = copy.parser.newInstance();
            this.parserDefined = copy.parserDefined;
            this.sslSocketFactory = copy.sslSocketFactory;
            this.cookieManager = copy.cookieManager;
            this.executing = false;
        }

        @Override // org.jsoup.Connection.Request
        public Proxy proxy() {
            return this.proxy;
        }

        @Override // org.jsoup.Connection.Request
        public Request proxy(@Nullable Proxy proxy) {
            this.proxy = proxy;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public Request proxy(String host, int port) {
            this.proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(host, port));
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public int timeout() {
            return this.timeoutMilliseconds;
        }

        @Override // org.jsoup.Connection.Request
        public Request timeout(int millis) {
            Validate.isTrue(millis >= 0, "Timeout milliseconds must be 0 (infinite) or greater");
            this.timeoutMilliseconds = millis;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public int maxBodySize() {
            return this.maxBodySizeBytes;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request maxBodySize(int bytes) {
            Validate.isTrue(bytes >= 0, "maxSize must be 0 (unlimited) or larger");
            this.maxBodySizeBytes = bytes;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public boolean followRedirects() {
            return this.followRedirects;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request followRedirects(boolean followRedirects) {
            this.followRedirects = followRedirects;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public boolean ignoreHttpErrors() {
            return this.ignoreHttpErrors;
        }

        @Override // org.jsoup.Connection.Request
        public SSLSocketFactory sslSocketFactory() {
            return this.sslSocketFactory;
        }

        @Override // org.jsoup.Connection.Request
        public void sslSocketFactory(SSLSocketFactory sslSocketFactory) {
            this.sslSocketFactory = sslSocketFactory;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request ignoreHttpErrors(boolean ignoreHttpErrors) {
            this.ignoreHttpErrors = ignoreHttpErrors;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public boolean ignoreContentType() {
            return this.ignoreContentType;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request ignoreContentType(boolean ignoreContentType) {
            this.ignoreContentType = ignoreContentType;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public Request data(Connection.KeyVal keyval) {
            Validate.notNull(keyval, "Key val must not be null");
            this.data.add(keyval);
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public Collection<Connection.KeyVal> data() {
            return this.data;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request requestBody(@Nullable String body) {
            this.body = body;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public String requestBody() {
            return this.body;
        }

        @Override // org.jsoup.Connection.Request
        public Request parser(Parser parser) {
            this.parser = parser;
            this.parserDefined = true;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public Parser parser() {
            return this.parser;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request postDataCharset(String charset) {
            Validate.notNull(charset, "Charset must not be null");
            if (!Charset.isSupported(charset)) {
                throw new IllegalCharsetNameException(charset);
            }
            this.postDataCharset = charset;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public String postDataCharset() {
            return this.postDataCharset;
        }

        CookieManager cookieManager() {
            return this.cookieManager;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/helper/HttpConnection$Response.class */
    public static class Response extends Base<Connection.Response> implements Connection.Response {
        private static final int MAX_REDIRECTS = 20;
        private static final String LOCATION = "Location";
        private final int statusCode;
        private final String statusMessage;

        @Nullable
        private ByteBuffer byteData;

        @Nullable
        private InputStream bodyStream;

        @Nullable
        private HttpURLConnection conn;

        @Nullable
        private String charset;

        @Nullable
        private final String contentType;
        private boolean executed;
        private boolean inputStreamRead;
        private int numRedirects;
        private final Request req;
        private static final Pattern xmlContentTypeRxp = Pattern.compile("(application|text)/\\w*\\+?xml.*");

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map cookies() {
            return super.cookies();
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base removeCookie(String str) {
            return super.removeCookie(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasCookie(String str) {
            return super.hasCookie(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base cookie(String str, String str2) {
            return super.cookie(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ String cookie(String str) {
            return super.cookie(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map multiHeaders() {
            return super.multiHeaders();
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map headers() {
            return super.headers();
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base removeHeader(String str) {
            return super.removeHeader(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasHeaderWithValue(String str, String str2) {
            return super.hasHeaderWithValue(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasHeader(String str) {
            return super.hasHeader(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base header(String str, String str2) {
            return super.header(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ List headers(String str) {
            return super.headers(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base addHeader(String str, String str2) {
            return super.addHeader(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ String header(String str) {
            return super.header(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base method(Connection.Method method) {
            return super.method(method);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Method method() {
            return super.method();
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base url(URL url) {
            return super.url(url);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ URL url() {
            return super.url();
        }

        Response() {
            super();
            this.executed = false;
            this.inputStreamRead = false;
            this.numRedirects = 0;
            this.statusCode = OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL;
            this.statusMessage = "Request not made";
            this.req = new Request();
            this.contentType = null;
        }

        static Response execute(Request req) throws IOException {
            return execute(req, null);
        }

        static Response execute(Request req, @Nullable Response previousResponse) throws IOException {
            synchronized (req) {
                Validate.isFalse(req.executing, "Multiple threads were detected trying to execute the same request concurrently. Make sure to use Connection#newRequest() and do not share an executing request between threads.");
                req.executing = true;
            }
            Validate.notNull(req, "Request must not be null");
            URL url = req.url();
            Validate.notNull(url, "URL must be specified to connect");
            String protocol = url.getProtocol();
            if (!protocol.equals("http") && !protocol.equals("https")) {
                throw new MalformedURLException("Only http & https protocols supported");
            }
            boolean methodHasBody = req.method().hasBody();
            boolean hasRequestBody = req.requestBody() != null;
            if (!methodHasBody) {
                Validate.isFalse(hasRequestBody, "Cannot set a request body for HTTP method " + req.method());
            }
            String mimeBoundary = null;
            if (req.data().size() > 0 && (!methodHasBody || hasRequestBody)) {
                serialiseRequestUrl(req);
            } else if (methodHasBody) {
                mimeBoundary = setOutputContentType(req);
            }
            long startTime = System.nanoTime();
            HttpURLConnection conn = createConnection(req);
            Response res = null;
            try {
                try {
                    conn.connect();
                    if (conn.getDoOutput()) {
                        writePost(req, conn.getOutputStream(), mimeBoundary);
                    }
                    int status = conn.getResponseCode();
                    Response res2 = new Response(conn, req, previousResponse);
                    if (res2.hasHeader("Location") && req.followRedirects()) {
                        if (status != 307) {
                            req.method(Connection.Method.GET);
                            req.data().clear();
                            req.requestBody(null);
                            req.removeHeader("Content-Type");
                        }
                        String location = res2.header("Location");
                        Validate.notNull(location);
                        if (location.startsWith("http:/") && location.charAt(6) != '/') {
                            location = location.substring(6);
                        }
                        URL redir = StringUtil.resolve(req.url(), location);
                        req.url(HttpConnection.encodeUrl(redir));
                        req.executing = false;
                        Response responseExecute = execute(req, res2);
                        req.executing = false;
                        return responseExecute;
                    }
                    if ((status < 200 || status >= 400) && !req.ignoreHttpErrors()) {
                        throw new HttpStatusException("HTTP error fetching URL", status, req.url().toString());
                    }
                    String contentType = res2.contentType();
                    if (contentType != null && !req.ignoreContentType() && !contentType.startsWith("text/") && !xmlContentTypeRxp.matcher(contentType).matches()) {
                        throw new UnsupportedMimeTypeException("Unhandled content type. Must be text/*, application/xml, or application/*+xml", contentType, req.url().toString());
                    }
                    if (contentType != null && xmlContentTypeRxp.matcher(contentType).matches() && !req.parserDefined) {
                        req.parser(Parser.xmlParser());
                    }
                    res2.charset = DataUtil.getCharsetFromContentType(res2.contentType);
                    if (conn.getContentLength() != 0 && req.method() != Connection.Method.HEAD) {
                        res2.bodyStream = conn.getErrorStream() != null ? conn.getErrorStream() : conn.getInputStream();
                        Validate.notNull(res2.bodyStream);
                        if (res2.hasHeaderWithValue("Content-Encoding", "gzip")) {
                            res2.bodyStream = new GZIPInputStream(res2.bodyStream);
                        } else if (res2.hasHeaderWithValue("Content-Encoding", "deflate")) {
                            res2.bodyStream = new InflaterInputStream(res2.bodyStream, new Inflater(true));
                        }
                        res2.bodyStream = ConstrainableInputStream.wrap(res2.bodyStream, 32768, req.maxBodySize()).timeout(startTime, req.timeout());
                    } else {
                        res2.byteData = DataUtil.emptyByteBuffer();
                    }
                    res2.executed = true;
                    return res2;
                } catch (IOException e) {
                    if (0 != 0) {
                        res.safeClose();
                    }
                    throw e;
                }
            } finally {
                req.executing = false;
            }
        }

        @Override // org.jsoup.Connection.Response
        public int statusCode() {
            return this.statusCode;
        }

        @Override // org.jsoup.Connection.Response
        public String statusMessage() {
            return this.statusMessage;
        }

        @Override // org.jsoup.Connection.Response
        public String charset() {
            return this.charset;
        }

        @Override // org.jsoup.Connection.Response
        public Response charset(String charset) {
            this.charset = charset;
            return this;
        }

        @Override // org.jsoup.Connection.Response
        public String contentType() {
            return this.contentType;
        }

        @Override // org.jsoup.Connection.Response
        public Document parse() throws IOException {
            Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before parsing response");
            if (this.byteData != null) {
                this.bodyStream = new ByteArrayInputStream(this.byteData.array());
                this.inputStreamRead = false;
            }
            Validate.isFalse(this.inputStreamRead, "Input stream already read and parsed, cannot re-read.");
            Document doc = DataUtil.parseInputStream(this.bodyStream, this.charset, this.url.toExternalForm(), this.req.parser());
            doc.connection(new HttpConnection(this.req, this));
            this.charset = doc.outputSettings().charset().name();
            this.inputStreamRead = true;
            safeClose();
            return doc;
        }

        private void prepareByteData() {
            Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
            if (this.bodyStream != null && this.byteData == null) {
                Validate.isFalse(this.inputStreamRead, "Request has already been read (with .parse())");
                try {
                    try {
                        this.byteData = DataUtil.readToByteBuffer(this.bodyStream, this.req.maxBodySize());
                        this.inputStreamRead = true;
                        safeClose();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                } catch (Throwable th) {
                    this.inputStreamRead = true;
                    safeClose();
                    throw th;
                }
            }
        }

        @Override // org.jsoup.Connection.Response
        public String body() {
            prepareByteData();
            Validate.notNull(this.byteData);
            String body = (this.charset == null ? DataUtil.UTF_8 : Charset.forName(this.charset)).decode(this.byteData).toString();
            this.byteData.rewind();
            return body;
        }

        @Override // org.jsoup.Connection.Response
        public byte[] bodyAsBytes() {
            prepareByteData();
            Validate.notNull(this.byteData);
            return this.byteData.array();
        }

        @Override // org.jsoup.Connection.Response
        public Connection.Response bufferUp() {
            prepareByteData();
            return this;
        }

        @Override // org.jsoup.Connection.Response
        public BufferedInputStream bodyStream() {
            Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
            Validate.isFalse(this.inputStreamRead, "Request has already been read");
            this.inputStreamRead = true;
            return ConstrainableInputStream.wrap(this.bodyStream, 32768, this.req.maxBodySize());
        }

        private static HttpURLConnection createConnection(Request req) throws IOException {
            URLConnection uRLConnectionOpenConnection;
            Proxy proxy = req.proxy();
            if (proxy == null) {
                uRLConnectionOpenConnection = req.url().openConnection();
            } else {
                uRLConnectionOpenConnection = req.url().openConnection(proxy);
            }
            HttpURLConnection conn = (HttpURLConnection) uRLConnectionOpenConnection;
            conn.setRequestMethod(req.method().name());
            conn.setInstanceFollowRedirects(false);
            conn.setConnectTimeout(req.timeout());
            conn.setReadTimeout(req.timeout() / 2);
            if (req.sslSocketFactory() != null && (conn instanceof HttpsURLConnection)) {
                ((HttpsURLConnection) conn).setSSLSocketFactory(req.sslSocketFactory());
            }
            if (req.method().hasBody()) {
                conn.setDoOutput(true);
            }
            CookieUtil.applyCookiesToRequest(req, conn);
            for (Map.Entry<String, List<String>> header : req.multiHeaders().entrySet()) {
                for (String value : header.getValue()) {
                    conn.addRequestProperty(header.getKey(), value);
                }
            }
            return conn;
        }

        private void safeClose() {
            if (this.bodyStream != null) {
                try {
                    this.bodyStream.close();
                } catch (IOException e) {
                } finally {
                    this.bodyStream = null;
                }
            }
            if (this.conn != null) {
                this.conn.disconnect();
                this.conn = null;
            }
        }

        private Response(HttpURLConnection conn, Request request, @Nullable Response previousResponse) throws IOException {
            super();
            this.executed = false;
            this.inputStreamRead = false;
            this.numRedirects = 0;
            this.conn = conn;
            this.req = request;
            this.method = Connection.Method.valueOf(conn.getRequestMethod());
            this.url = conn.getURL();
            this.statusCode = conn.getResponseCode();
            this.statusMessage = conn.getResponseMessage();
            this.contentType = conn.getContentType();
            Map<String, List<String>> resHeaders = createHeaderMap(conn);
            processResponseHeaders(resHeaders);
            CookieUtil.storeCookies(this.req, this.url, resHeaders);
            if (previousResponse != null) {
                for (Map.Entry<String, String> prevCookie : previousResponse.cookies().entrySet()) {
                    if (!hasCookie(prevCookie.getKey())) {
                        cookie(prevCookie.getKey(), prevCookie.getValue());
                    }
                }
                previousResponse.safeClose();
                this.numRedirects = previousResponse.numRedirects + 1;
                if (this.numRedirects >= 20) {
                    throw new IOException(String.format("Too many redirects occurred trying to load URL %s", previousResponse.url()));
                }
            }
        }

        private static LinkedHashMap<String, List<String>> createHeaderMap(HttpURLConnection conn) {
            LinkedHashMap<String, List<String>> headers = new LinkedHashMap<>();
            int i = 0;
            while (true) {
                String key = conn.getHeaderFieldKey(i);
                String val = conn.getHeaderField(i);
                if (key != null || val != null) {
                    i++;
                    if (key != null && val != null) {
                        if (headers.containsKey(key)) {
                            headers.get(key).add(val);
                        } else {
                            ArrayList<String> vals = new ArrayList<>();
                            vals.add(val);
                            headers.put(key, vals);
                        }
                    }
                } else {
                    return headers;
                }
            }
        }

        void processResponseHeaders(Map<String, List<String>> resHeaders) {
            for (Map.Entry<String, List<String>> entry : resHeaders.entrySet()) {
                String name = entry.getKey();
                if (name != null) {
                    List<String> values = entry.getValue();
                    if (name.equalsIgnoreCase("Set-Cookie")) {
                        for (String value : values) {
                            if (value != null) {
                                TokenQueue cd = new TokenQueue(value);
                                String cookieName = cd.chompTo("=").trim();
                                String cookieVal = cd.consumeTo(";").trim();
                                if (cookieName.length() > 0 && !this.cookies.containsKey(cookieName)) {
                                    cookie(cookieName, cookieVal);
                                }
                            }
                        }
                    }
                    Iterator<String> it = values.iterator();
                    while (it.hasNext()) {
                        addHeader(name, it.next());
                    }
                }
            }
        }

        @Nullable
        private static String setOutputContentType(Connection.Request req) {
            String contentType = req.header("Content-Type");
            String bound = null;
            if (contentType == null) {
                if (HttpConnection.needsMultipart(req)) {
                    bound = DataUtil.mimeBoundary();
                    req.header("Content-Type", "multipart/form-data; boundary=" + bound);
                } else {
                    req.header("Content-Type", "application/x-www-form-urlencoded; charset=" + req.postDataCharset());
                }
            } else if (contentType.contains("multipart/form-data") && !contentType.contains(HttpHeaders.Values.BOUNDARY)) {
                bound = DataUtil.mimeBoundary();
                req.header("Content-Type", "multipart/form-data; boundary=" + bound);
            }
            return bound;
        }

        private static void writePost(Connection.Request req, OutputStream outputStream, @Nullable String boundary) throws IOException {
            Collection<Connection.KeyVal> data = req.data();
            BufferedWriter w = new BufferedWriter(new OutputStreamWriter(outputStream, req.postDataCharset()));
            if (boundary != null) {
                for (Connection.KeyVal keyVal : data) {
                    w.write(UsageMessageFormatter.DEFAULT_LONG_OPT_PREFIX);
                    w.write(boundary);
                    w.write(StrPool.CRLF);
                    w.write("Content-Disposition: form-data; name=\"");
                    w.write(HttpConnection.encodeMimeName(keyVal.key()));
                    w.write(OperatorName.SHOW_TEXT_LINE_AND_SPACE);
                    InputStream input = keyVal.inputStream();
                    if (input != null) {
                        w.write("; filename=\"");
                        w.write(HttpConnection.encodeMimeName(keyVal.value()));
                        w.write("\"\r\nContent-Type: ");
                        String contentType = keyVal.contentType();
                        w.write(contentType != null ? contentType : "application/octet-stream");
                        w.write("\r\n\r\n");
                        w.flush();
                        DataUtil.crossStreams(input, outputStream);
                        outputStream.flush();
                    } else {
                        w.write("\r\n\r\n");
                        w.write(keyVal.value());
                    }
                    w.write(StrPool.CRLF);
                }
                w.write(UsageMessageFormatter.DEFAULT_LONG_OPT_PREFIX);
                w.write(boundary);
                w.write(UsageMessageFormatter.DEFAULT_LONG_OPT_PREFIX);
            } else {
                String body = req.requestBody();
                if (body != null) {
                    w.write(body);
                } else {
                    boolean first = true;
                    for (Connection.KeyVal keyVal2 : data) {
                        if (!first) {
                            w.append('&');
                        } else {
                            first = false;
                        }
                        w.write(URLEncoder.encode(keyVal2.key(), req.postDataCharset()));
                        w.write(61);
                        w.write(URLEncoder.encode(keyVal2.value(), req.postDataCharset()));
                    }
                }
            }
            w.close();
        }

        private static void serialiseRequestUrl(Connection.Request req) throws IOException {
            URL in = req.url();
            StringBuilder url = StringUtil.borrowBuilder();
            boolean first = true;
            url.append(in.getProtocol()).append("://").append(in.getAuthority()).append(in.getPath()).append(CallerData.NA);
            if (in.getQuery() != null) {
                url.append(in.getQuery());
                first = false;
            }
            for (Connection.KeyVal keyVal : req.data()) {
                Validate.isFalse(keyVal.hasInputStream(), "InputStream data not supported in URL query string.");
                if (!first) {
                    url.append('&');
                } else {
                    first = false;
                }
                url.append(URLEncoder.encode(keyVal.key(), DataUtil.defaultCharsetName)).append('=').append(URLEncoder.encode(keyVal.value(), DataUtil.defaultCharsetName));
            }
            req.url(new URL(StringUtil.releaseBuilder(url)));
            req.data().clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean needsMultipart(Connection.Request req) {
        for (Connection.KeyVal keyVal : req.data()) {
            if (keyVal.hasInputStream()) {
                return true;
            }
        }
        return false;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/helper/HttpConnection$KeyVal.class */
    public static class KeyVal implements Connection.KeyVal {
        private String key;
        private String value;

        @Nullable
        private InputStream stream;

        @Nullable
        private String contentType;

        public static KeyVal create(String key, String value) {
            return new KeyVal(key, value);
        }

        public static KeyVal create(String key, String filename, InputStream stream) {
            return new KeyVal(key, filename).inputStream(stream);
        }

        private KeyVal(String key, String value) {
            Validate.notEmpty(key, "Data key must not be empty");
            Validate.notNull(value, "Data value must not be null");
            this.key = key;
            this.value = value;
        }

        @Override // org.jsoup.Connection.KeyVal
        public KeyVal key(String key) {
            Validate.notEmpty(key, "Data key must not be empty");
            this.key = key;
            return this;
        }

        @Override // org.jsoup.Connection.KeyVal
        public String key() {
            return this.key;
        }

        @Override // org.jsoup.Connection.KeyVal
        public KeyVal value(String value) {
            Validate.notNull(value, "Data value must not be null");
            this.value = value;
            return this;
        }

        @Override // org.jsoup.Connection.KeyVal
        public String value() {
            return this.value;
        }

        @Override // org.jsoup.Connection.KeyVal
        public KeyVal inputStream(InputStream inputStream) {
            Validate.notNull(this.value, "Data input stream must not be null");
            this.stream = inputStream;
            return this;
        }

        @Override // org.jsoup.Connection.KeyVal
        public InputStream inputStream() {
            return this.stream;
        }

        @Override // org.jsoup.Connection.KeyVal
        public boolean hasInputStream() {
            return this.stream != null;
        }

        @Override // org.jsoup.Connection.KeyVal
        public Connection.KeyVal contentType(String contentType) {
            Validate.notEmpty(contentType);
            this.contentType = contentType;
            return this;
        }

        @Override // org.jsoup.Connection.KeyVal
        public String contentType() {
            return this.contentType;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}
