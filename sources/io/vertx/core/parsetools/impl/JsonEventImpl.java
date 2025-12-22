package io.vertx.core.parsetools.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.jackson.JacksonCodec;
import io.vertx.core.parsetools.JsonEvent;
import io.vertx.core.parsetools.JsonEventType;
import io.vertx.core.spi.json.JsonCodec;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/parsetools/impl/JsonEventImpl.class */
public class JsonEventImpl implements JsonEvent {
    private final JsonEventType type;
    private final String field;
    private final Object value;
    private final TokenBuffer buffer;

    public JsonEventImpl(JsonEventType type, String field, Object value) {
        this(type, field, value, null);
    }

    public JsonEventImpl(JsonEventType type, String field, Object value, TokenBuffer buffer) {
        this.type = type;
        this.field = field;
        this.value = value;
        this.buffer = buffer;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public JsonEventType type() {
        return this.type;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public String fieldName() {
        return this.field;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public Object value() {
        return this.value;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public boolean isNumber() {
        return this.value instanceof Number;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public boolean isString() {
        return this.value instanceof String;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public boolean isNull() {
        return this.type == JsonEventType.VALUE && this.value == null;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public boolean isObject() {
        return this.value instanceof JsonObject;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public boolean isArray() {
        return this.value instanceof JsonArray;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public <T> T mapTo(Class<T> cls) {
        if (this.buffer != null) {
            try {
                return (T) Json.mapper.readValue(this.buffer.asParser(), cls);
            } catch (Exception e) {
                throw new DecodeException(e.getMessage());
            }
        }
        return (T) JsonCodec.INSTANCE.fromValue(this.value, cls);
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public <T> T mapTo(TypeReference<T> typeReference) {
        if (this.buffer != null) {
            try {
                return (T) Json.mapper.readValue(this.buffer.asParser(), (TypeReference<?>) typeReference);
            } catch (Exception e) {
                throw new DecodeException(e.getMessage());
            }
        }
        return (T) JacksonCodec.fromValue(this.value, typeReference);
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public Integer integerValue() {
        if (this.value != null) {
            Number number = (Number) this.value;
            if (this.value instanceof Integer) {
                return (Integer) this.value;
            }
            return Integer.valueOf(number.intValue());
        }
        return null;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public Long longValue() {
        if (this.value != null) {
            Number number = (Number) this.value;
            if (this.value instanceof Integer) {
                return (Long) this.value;
            }
            return Long.valueOf(number.longValue());
        }
        return null;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public Float floatValue() {
        if (this.value != null) {
            Number number = (Number) this.value;
            if (this.value instanceof Float) {
                return (Float) this.value;
            }
            return Float.valueOf(number.floatValue());
        }
        return null;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public Double doubleValue() {
        if (this.value != null) {
            Number number = (Number) this.value;
            if (this.value instanceof Double) {
                return (Double) this.value;
            }
            return Double.valueOf(number.doubleValue());
        }
        return null;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public Boolean booleanValue() {
        return (Boolean) this.value;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public String stringValue() {
        return (String) this.value;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public Buffer binaryValue() {
        if (this.value != null) {
            return Buffer.buffer(Base64.getDecoder().decode((String) this.value));
        }
        return null;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public Instant instantValue() {
        if (this.value != null) {
            return Instant.from(DateTimeFormatter.ISO_INSTANT.parse((CharSequence) this.value));
        }
        return null;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public JsonObject objectValue() {
        return (JsonObject) this.value;
    }

    @Override // io.vertx.core.parsetools.JsonEvent
    public JsonArray arrayValue() {
        return (JsonArray) this.value;
    }
}
