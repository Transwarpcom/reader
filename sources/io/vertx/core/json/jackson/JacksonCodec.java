package io.vertx.core.json.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBufInputStream;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.json.JsonCodec;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/json/jackson/JacksonCodec.class */
public class JacksonCodec implements JsonCodec {
    @Override // io.vertx.core.spi.json.JsonCodec
    public <T> T fromValue(Object obj, Class<T> cls) throws IllegalArgumentException {
        Object objConvertValue = Json.mapper.convertValue(obj, cls);
        if (cls == Object.class) {
            objConvertValue = adapt(objConvertValue);
        }
        return (T) objConvertValue;
    }

    public static <T> T fromValue(Object obj, TypeReference<T> typeReference) throws IllegalArgumentException {
        Object objConvertValue = Json.mapper.convertValue(obj, (TypeReference<?>) typeReference);
        if (typeReference.getType() == Object.class) {
            objConvertValue = adapt(objConvertValue);
        }
        return (T) objConvertValue;
    }

    @Override // io.vertx.core.spi.json.JsonCodec
    public <T> T fromString(String str, Class<T> cls) throws DecodeException {
        return (T) fromParser(createParser(str), cls);
    }

    public static <T> T fromString(String str, TypeReference<T> typeReference) throws DecodeException {
        return (T) fromParser(createParser(str), typeReference);
    }

    @Override // io.vertx.core.spi.json.JsonCodec
    public <T> T fromBuffer(Buffer buffer, Class<T> cls) throws DecodeException {
        return (T) fromParser(createParser(buffer), cls);
    }

    public static <T> T fromBuffer(Buffer buffer, TypeReference<T> typeReference) throws DecodeException {
        return (T) fromParser(createParser(buffer), typeReference);
    }

    private static JsonParser createParser(Buffer buf) {
        try {
            return Json.mapper.getFactory().createParser((InputStream) new ByteBufInputStream(buf.getByteBuf()));
        } catch (IOException e) {
            throw new DecodeException("Failed to decode:" + e.getMessage(), e);
        }
    }

    private static JsonParser createParser(String str) {
        try {
            return Json.mapper.getFactory().createParser(str);
        } catch (IOException e) {
            throw new DecodeException("Failed to decode:" + e.getMessage(), e);
        }
    }

    private static <T> T fromParser(JsonParser jsonParser, Class<T> cls) throws DecodeException {
        try {
            try {
                Object value = Json.mapper.readValue(jsonParser, cls);
                close(jsonParser);
                if (cls == Object.class) {
                    value = adapt(value);
                }
                return (T) value;
            } catch (Exception e) {
                throw new DecodeException("Failed to decode:" + e.getMessage(), e);
            }
        } catch (Throwable th) {
            close(jsonParser);
            throw th;
        }
    }

    private static <T> T fromParser(JsonParser jsonParser, TypeReference<T> typeReference) throws DecodeException {
        try {
            try {
                Object value = Json.mapper.readValue(jsonParser, (TypeReference<?>) typeReference);
                close(jsonParser);
                if (typeReference.getType() == Object.class) {
                    value = adapt(value);
                }
                return (T) value;
            } catch (Exception e) {
                throw new DecodeException("Failed to decode:" + e.getMessage(), e);
            }
        } catch (Throwable th) {
            close(jsonParser);
            throw th;
        }
    }

    private static void close(JsonParser parser) {
        try {
            parser.close();
        } catch (IOException e) {
        }
    }

    private static Object adapt(Object o) {
        try {
            if (o instanceof List) {
                List list = (List) o;
                return new JsonArray(list);
            }
            if (o instanceof Map) {
                Map<String, Object> map = (Map) o;
                return new JsonObject(map);
            }
            return o;
        } catch (Exception e) {
            throw new DecodeException("Failed to decode: " + e.getMessage());
        }
    }

    @Override // io.vertx.core.spi.json.JsonCodec
    public String toString(Object object, boolean pretty) throws EncodeException {
        try {
            ObjectMapper mapper = pretty ? Json.prettyMapper : Json.mapper;
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new EncodeException("Failed to encode as JSON: " + e.getMessage());
        }
    }

    @Override // io.vertx.core.spi.json.JsonCodec
    public Buffer toBuffer(Object object, boolean pretty) throws EncodeException {
        try {
            ObjectMapper mapper = pretty ? Json.prettyMapper : Json.mapper;
            return Buffer.buffer(mapper.writeValueAsBytes(object));
        } catch (Exception e) {
            throw new EncodeException("Failed to encode as JSON: " + e.getMessage());
        }
    }

    public static <T> T decodeValue(String str, TypeReference<T> typeReference) throws DecodeException {
        return (T) fromString(str, typeReference);
    }

    public static <T> T decodeValue(Buffer buffer, TypeReference<T> typeReference) throws DecodeException {
        return (T) fromBuffer(buffer, typeReference);
    }
}
