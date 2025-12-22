package org.jsoup;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieStore;
import java.net.Proxy;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocketFactory;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/Connection.class */
public interface Connection {

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/Connection$Base.class */
    public interface Base<T extends Base<T>> {
        URL url();

        T url(URL url);

        Method method();

        T method(Method method);

        @Nullable
        String header(String str);

        List<String> headers(String str);

        T header(String str, String str2);

        T addHeader(String str, String str2);

        boolean hasHeader(String str);

        boolean hasHeaderWithValue(String str, String str2);

        T removeHeader(String str);

        Map<String, String> headers();

        Map<String, List<String>> multiHeaders();

        @Nullable
        String cookie(String str);

        T cookie(String str, String str2);

        boolean hasCookie(String str);

        T removeCookie(String str);

        Map<String, String> cookies();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/Connection$KeyVal.class */
    public interface KeyVal {
        KeyVal key(String str);

        String key();

        KeyVal value(String str);

        String value();

        KeyVal inputStream(InputStream inputStream);

        @Nullable
        InputStream inputStream();

        boolean hasInputStream();

        KeyVal contentType(String str);

        @Nullable
        String contentType();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/Connection$Request.class */
    public interface Request extends Base<Request> {
        @Nullable
        Proxy proxy();

        Request proxy(@Nullable Proxy proxy);

        Request proxy(String str, int i);

        int timeout();

        Request timeout(int i);

        int maxBodySize();

        Request maxBodySize(int i);

        boolean followRedirects();

        Request followRedirects(boolean z);

        boolean ignoreHttpErrors();

        Request ignoreHttpErrors(boolean z);

        boolean ignoreContentType();

        Request ignoreContentType(boolean z);

        @Nullable
        SSLSocketFactory sslSocketFactory();

        void sslSocketFactory(SSLSocketFactory sSLSocketFactory);

        Request data(KeyVal keyVal);

        Collection<KeyVal> data();

        Request requestBody(@Nullable String str);

        @Nullable
        String requestBody();

        Request parser(Parser parser);

        Parser parser();

        Request postDataCharset(String str);

        String postDataCharset();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/Connection$Response.class */
    public interface Response extends Base<Response> {
        int statusCode();

        String statusMessage();

        @Nullable
        String charset();

        Response charset(String str);

        @Nullable
        String contentType();

        Document parse() throws IOException;

        String body();

        byte[] bodyAsBytes();

        Response bufferUp();

        BufferedInputStream bodyStream();
    }

    Connection newRequest();

    Connection url(URL url);

    Connection url(String str);

    Connection proxy(@Nullable Proxy proxy);

    Connection proxy(String str, int i);

    Connection userAgent(String str);

    Connection timeout(int i);

    Connection maxBodySize(int i);

    Connection referrer(String str);

    Connection followRedirects(boolean z);

    Connection method(Method method);

    Connection ignoreHttpErrors(boolean z);

    Connection ignoreContentType(boolean z);

    Connection sslSocketFactory(SSLSocketFactory sSLSocketFactory);

    Connection data(String str, String str2);

    Connection data(String str, String str2, InputStream inputStream);

    Connection data(String str, String str2, InputStream inputStream, String str3);

    Connection data(Collection<KeyVal> collection);

    Connection data(Map<String, String> map);

    Connection data(String... strArr);

    @Nullable
    KeyVal data(String str);

    Connection requestBody(String str);

    Connection header(String str, String str2);

    Connection headers(Map<String, String> map);

    Connection cookie(String str, String str2);

    Connection cookies(Map<String, String> map);

    Connection cookieStore(CookieStore cookieStore);

    CookieStore cookieStore();

    Connection parser(Parser parser);

    Connection postDataCharset(String str);

    Document get() throws IOException;

    Document post() throws IOException;

    Response execute() throws IOException;

    Request request();

    Connection request(Request request);

    Response response();

    Connection response(Response response);

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/Connection$Method.class */
    public enum Method {
        GET(false),
        POST(true),
        PUT(true),
        DELETE(false),
        PATCH(true),
        HEAD(false),
        OPTIONS(false),
        TRACE(false);

        private final boolean hasBody;

        Method(boolean hasBody) {
            this.hasBody = hasBody;
        }

        public final boolean hasBody() {
            return this.hasBody;
        }
    }
}
