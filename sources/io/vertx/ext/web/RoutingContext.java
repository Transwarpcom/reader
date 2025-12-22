package io.vertx.ext.web;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import java.util.List;
import java.util.Map;
import java.util.Set;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/RoutingContext.class */
public interface RoutingContext {
    @CacheReturn
    HttpServerRequest request();

    @CacheReturn
    HttpServerResponse response();

    void next();

    void fail(int i);

    void fail(Throwable th);

    void fail(int i, Throwable th);

    @Fluent
    RoutingContext put(String str, Object obj);

    <T> T get(String str);

    <T> T remove(String str);

    @GenIgnore({"permitted-type"})
    Map<String, Object> data();

    @CacheReturn
    Vertx vertx();

    String mountPoint();

    Route currentRoute();

    String normalisedPath();

    Cookie getCookie(String str);

    @Fluent
    RoutingContext addCookie(io.vertx.core.http.Cookie cookie);

    @GenIgnore
    @Fluent
    @Deprecated
    RoutingContext addCookie(Cookie cookie);

    Cookie removeCookie(String str, boolean z);

    int cookieCount();

    @Deprecated
    Set<Cookie> cookies();

    @GenIgnore
    Map<String, io.vertx.core.http.Cookie> cookieMap();

    String getBodyAsString();

    String getBodyAsString(String str);

    JsonObject getBodyAsJson();

    JsonArray getBodyAsJsonArray();

    Buffer getBody();

    Set<FileUpload> fileUploads();

    Session session();

    User user();

    @CacheReturn
    Throwable failure();

    @CacheReturn
    int statusCode();

    String getAcceptableContentType();

    @CacheReturn
    ParsedHeaderValues parsedHeaders();

    int addHeadersEndHandler(Handler<Void> handler);

    boolean removeHeadersEndHandler(int i);

    int addBodyEndHandler(Handler<Void> handler);

    boolean removeBodyEndHandler(int i);

    boolean failed();

    void setBody(Buffer buffer);

    void setSession(Session session);

    void setUser(User user);

    void clearUser();

    void setAcceptableContentType(String str);

    void reroute(HttpMethod httpMethod, String str);

    @Deprecated
    @CacheReturn
    List<Locale> acceptableLocales();

    Map<String, String> pathParams();

    String pathParam(String str);

    MultiMap queryParams();

    List<String> queryParam(String str);

    default Cookie removeCookie(String name) {
        return removeCookie(name, true);
    }

    default void reroute(String path) {
        reroute(request().method(), path);
    }

    @CacheReturn
    default List<LanguageHeader> acceptableLanguages() {
        return parsedHeaders().acceptLanguage();
    }

    @CacheReturn
    @Deprecated
    default Locale preferredLocale() {
        return preferredLanguage();
    }

    @CacheReturn
    default LanguageHeader preferredLanguage() {
        List<? extends LanguageHeader> acceptableLanguages = acceptableLanguages();
        if (acceptableLanguages.size() > 0) {
            return acceptableLanguages.get(0);
        }
        return null;
    }
}
