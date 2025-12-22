package io.vertx.ext.web.client;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.codec.impl.BodyCodecImpl;
import java.util.List;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/HttpResponse.class */
public interface HttpResponse<T> {
    @CacheReturn
    HttpVersion version();

    @CacheReturn
    int statusCode();

    @CacheReturn
    String statusMessage();

    @CacheReturn
    MultiMap headers();

    String getHeader(String str);

    @CacheReturn
    MultiMap trailers();

    String getTrailer(String str);

    @CacheReturn
    List<String> cookies();

    @CacheReturn
    T body();

    @CacheReturn
    Buffer bodyAsBuffer();

    @CacheReturn
    List<String> followedRedirects();

    @CacheReturn
    JsonArray bodyAsJsonArray();

    @CacheReturn
    default String bodyAsString() {
        Buffer b = bodyAsBuffer();
        if (b != null) {
            return BodyCodecImpl.UTF8_DECODER.apply(b);
        }
        return null;
    }

    default String bodyAsString(String encoding) {
        Buffer b = bodyAsBuffer();
        if (b != null) {
            return b.toString(encoding);
        }
        return null;
    }

    @CacheReturn
    default JsonObject bodyAsJsonObject() {
        Buffer b = bodyAsBuffer();
        if (b != null) {
            return BodyCodecImpl.JSON_OBJECT_DECODER.apply(b);
        }
        return null;
    }

    default <R> R bodyAsJson(Class<R> cls) {
        Buffer bufferBodyAsBuffer = bodyAsBuffer();
        if (bufferBodyAsBuffer != null) {
            return (R) BodyCodecImpl.jsonDecoder(cls).apply(bufferBodyAsBuffer);
        }
        return null;
    }
}
