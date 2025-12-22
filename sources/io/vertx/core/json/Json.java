package io.vertx.core.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.jackson.JacksonCodec;
import io.vertx.core.spi.json.JsonCodec;
import java.time.Instant;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/json/Json.class */
public class Json {

    @Deprecated
    public static ObjectMapper mapper = new ObjectMapper();

    @Deprecated
    public static ObjectMapper prettyMapper = new ObjectMapper();

    static {
        initialize();
    }

    private static void initialize() {
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        prettyMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        prettyMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        SimpleModule module = new SimpleModule();
        module.addSerializer(JsonObject.class, new JsonObjectSerializer());
        module.addSerializer(JsonArray.class, new JsonArraySerializer());
        module.addSerializer(Instant.class, new InstantSerializer());
        module.addDeserializer(Instant.class, new InstantDeserializer());
        module.addSerializer(byte[].class, new ByteArraySerializer());
        module.addDeserializer(byte[].class, new ByteArrayDeserializer());
        mapper.registerModule(module);
        prettyMapper.registerModule(module);
    }

    public static String encode(Object obj) throws EncodeException {
        return JsonCodec.INSTANCE.toString(obj);
    }

    public static Buffer encodeToBuffer(Object obj) throws EncodeException {
        return JsonCodec.INSTANCE.toBuffer(obj);
    }

    public static String encodePrettily(Object obj) throws EncodeException {
        return JsonCodec.INSTANCE.toString(obj, true);
    }

    public static <T> T decodeValue(String str, Class<T> cls) throws DecodeException {
        return (T) JsonCodec.INSTANCE.fromString(str, cls);
    }

    public static Object decodeValue(String str) throws DecodeException {
        return decodeValue(str, Object.class);
    }

    @Deprecated
    public static <T> T decodeValue(String str, TypeReference<T> typeReference) throws DecodeException {
        return (T) JacksonCodec.fromString(str, typeReference);
    }

    public static Object decodeValue(Buffer buf) throws DecodeException {
        return decodeValue(buf, Object.class);
    }

    @Deprecated
    public static <T> T decodeValue(Buffer buffer, TypeReference<T> typeReference) throws DecodeException {
        return (T) JacksonCodec.fromBuffer(buffer, typeReference);
    }

    public static <T> T decodeValue(Buffer buffer, Class<T> cls) throws DecodeException {
        return (T) JsonCodec.INSTANCE.fromBuffer(buffer, cls);
    }
}
