package io.vertx.core.spi.json;

import io.vertx.core.ServiceHelper;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.EncodeException;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/json/JsonCodec.class */
public interface JsonCodec {
    public static final JsonCodec INSTANCE = (JsonCodec) ServiceHelper.loadFactory(JsonCodec.class);

    <T> T fromString(String str, Class<T> cls) throws DecodeException;

    <T> T fromBuffer(Buffer buffer, Class<T> cls) throws DecodeException;

    <T> T fromValue(Object obj, Class<T> cls);

    String toString(Object obj, boolean z) throws EncodeException;

    Buffer toBuffer(Object obj, boolean z) throws EncodeException;

    default String toString(Object object) throws EncodeException {
        return toString(object, false);
    }

    default Buffer toBuffer(Object object) throws EncodeException {
        return toBuffer(object, false);
    }
}
