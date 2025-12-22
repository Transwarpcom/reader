package io.vertx.core.http;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.util.AsciiString;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/HttpHeaders.class */
public final class HttpHeaders {
    private static final String DISABLE_HTTP_HEADERS_VALIDATION_PROP_NAME = "vertx.disableHttpHeadersValidation";
    public static final boolean DISABLE_HTTP_HEADERS_VALIDATION = Boolean.getBoolean(DISABLE_HTTP_HEADERS_VALIDATION_PROP_NAME);
    public static final CharSequence ACCEPT = HttpHeaderNames.ACCEPT;
    public static final CharSequence ACCEPT_CHARSET = HttpHeaderNames.ACCEPT_CHARSET;
    public static final CharSequence ACCEPT_ENCODING = HttpHeaderNames.ACCEPT_ENCODING;
    public static final CharSequence ACCEPT_LANGUAGE = HttpHeaderNames.ACCEPT_LANGUAGE;
    public static final CharSequence ACCEPT_RANGES = HttpHeaderNames.ACCEPT_RANGES;
    public static final CharSequence ACCEPT_PATCH = HttpHeaderNames.ACCEPT_PATCH;
    public static final CharSequence ACCESS_CONTROL_ALLOW_CREDENTIALS = HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS;
    public static final CharSequence ACCESS_CONTROL_ALLOW_HEADERS = HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS;
    public static final CharSequence ACCESS_CONTROL_ALLOW_METHODS = HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS;
    public static final CharSequence ACCESS_CONTROL_ALLOW_ORIGIN = HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN;
    public static final CharSequence ACCESS_CONTROL_EXPOSE_HEADERS = HttpHeaderNames.ACCESS_CONTROL_EXPOSE_HEADERS;
    public static final CharSequence ACCESS_CONTROL_MAX_AGE = HttpHeaderNames.ACCESS_CONTROL_MAX_AGE;
    public static final CharSequence ACCESS_CONTROL_REQUEST_HEADERS = HttpHeaderNames.ACCESS_CONTROL_REQUEST_HEADERS;
    public static final CharSequence ACCESS_CONTROL_REQUEST_METHOD = HttpHeaderNames.ACCESS_CONTROL_REQUEST_METHOD;
    public static final CharSequence AGE = HttpHeaderNames.AGE;
    public static final CharSequence ALLOW = HttpHeaderNames.ALLOW;
    public static final CharSequence AUTHORIZATION = HttpHeaderNames.AUTHORIZATION;
    public static final CharSequence CACHE_CONTROL = HttpHeaderNames.CACHE_CONTROL;
    public static final CharSequence CONNECTION = HttpHeaderNames.CONNECTION;
    public static final CharSequence CONTENT_BASE = HttpHeaderNames.CONTENT_BASE;
    public static final CharSequence CONTENT_DISPOSITION = HttpHeaderNames.CONTENT_DISPOSITION;
    public static final CharSequence CONTENT_ENCODING = HttpHeaderNames.CONTENT_ENCODING;
    public static final CharSequence CONTENT_LANGUAGE = HttpHeaderNames.CONTENT_LANGUAGE;
    public static final CharSequence CONTENT_LENGTH = HttpHeaderNames.CONTENT_LENGTH;
    public static final CharSequence CONTENT_LOCATION = HttpHeaderNames.CONTENT_LOCATION;
    public static final CharSequence CONTENT_TRANSFER_ENCODING = HttpHeaderNames.CONTENT_TRANSFER_ENCODING;
    public static final CharSequence CONTENT_MD5 = HttpHeaderNames.CONTENT_MD5;
    public static final CharSequence CONTENT_RANGE = HttpHeaderNames.CONTENT_RANGE;
    public static final CharSequence CONTENT_TYPE = HttpHeaderNames.CONTENT_TYPE;
    public static final CharSequence COOKIE = HttpHeaderNames.COOKIE;
    public static final CharSequence DATE = HttpHeaderNames.DATE;
    public static final CharSequence ETAG = HttpHeaderNames.ETAG;
    public static final CharSequence EXPECT = HttpHeaderNames.EXPECT;
    public static final CharSequence EXPIRES = HttpHeaderNames.EXPIRES;
    public static final CharSequence FROM = HttpHeaderNames.FROM;
    public static final CharSequence HOST = HttpHeaderNames.HOST;
    public static final CharSequence IF_MATCH = HttpHeaderNames.IF_MATCH;
    public static final CharSequence IF_MODIFIED_SINCE = HttpHeaderNames.IF_MODIFIED_SINCE;
    public static final CharSequence IF_NONE_MATCH = HttpHeaderNames.IF_NONE_MATCH;
    public static final CharSequence LAST_MODIFIED = HttpHeaderNames.LAST_MODIFIED;
    public static final CharSequence LOCATION = HttpHeaderNames.LOCATION;
    public static final CharSequence ORIGIN = HttpHeaderNames.ORIGIN;
    public static final CharSequence PROXY_AUTHENTICATE = HttpHeaderNames.PROXY_AUTHENTICATE;
    public static final CharSequence PROXY_AUTHORIZATION = HttpHeaderNames.PROXY_AUTHORIZATION;
    public static final CharSequence REFERER = HttpHeaderNames.REFERER;
    public static final CharSequence RETRY_AFTER = HttpHeaderNames.RETRY_AFTER;
    public static final CharSequence SERVER = HttpHeaderNames.SERVER;
    public static final CharSequence TRANSFER_ENCODING = HttpHeaderNames.TRANSFER_ENCODING;
    public static final CharSequence USER_AGENT = HttpHeaderNames.USER_AGENT;
    public static final CharSequence SET_COOKIE = HttpHeaderNames.SET_COOKIE;
    public static final CharSequence APPLICATION_X_WWW_FORM_URLENCODED = HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED;
    public static final CharSequence CHUNKED = HttpHeaderValues.CHUNKED;
    public static final CharSequence CLOSE = HttpHeaderValues.CLOSE;
    public static final CharSequence CONTINUE = HttpHeaderValues.CONTINUE;
    public static final CharSequence IDENTITY = HttpHeaderValues.IDENTITY;
    public static final CharSequence KEEP_ALIVE = HttpHeaderValues.KEEP_ALIVE;
    public static final CharSequence UPGRADE = HttpHeaderValues.UPGRADE;
    public static final CharSequence WEBSOCKET = HttpHeaderValues.WEBSOCKET;
    public static final CharSequence DEFLATE_GZIP = createOptimized("deflate, gzip");
    public static final CharSequence TEXT_HTML = createOptimized("text/html");
    public static final CharSequence GET = createOptimized("GET");

    public static CharSequence createOptimized(String value) {
        return new AsciiString(value);
    }

    private HttpHeaders() {
    }
}
