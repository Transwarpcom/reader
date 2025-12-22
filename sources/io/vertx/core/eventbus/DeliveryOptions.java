package io.vertx.core.eventbus;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.core.MultiMap;
import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

@DataObject
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/DeliveryOptions.class */
public class DeliveryOptions {
    public static final long DEFAULT_TIMEOUT = 30000;
    public static final boolean DEFAULT_LOCAL_ONLY = false;
    private long timeout;
    private String codecName;
    private MultiMap headers;
    private boolean localOnly;

    public DeliveryOptions() {
        this.timeout = 30000L;
        this.localOnly = false;
    }

    public DeliveryOptions(DeliveryOptions other) {
        this.timeout = 30000L;
        this.localOnly = false;
        this.timeout = other.getSendTimeout();
        this.codecName = other.getCodecName();
        this.headers = other.getHeaders();
        this.localOnly = other.localOnly;
    }

    public DeliveryOptions(JsonObject json) {
        this.timeout = 30000L;
        this.localOnly = false;
        this.timeout = json.getLong(RtspHeaders.Values.TIMEOUT, 30000L).longValue();
        this.codecName = json.getString("codecName", null);
        JsonObject hdrs = json.getJsonObject("headers", null);
        if (hdrs != null) {
            this.headers = new CaseInsensitiveHeaders();
            Iterator<Map.Entry<String, Object>> it = hdrs.iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                if (!(entry.getValue() instanceof String)) {
                    throw new IllegalStateException("Invalid type for message header value " + entry.getValue().getClass());
                }
                this.headers.set(entry.getKey(), (String) entry.getValue());
            }
        }
        this.localOnly = json.getBoolean("localOnly", false).booleanValue();
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.put(RtspHeaders.Values.TIMEOUT, Long.valueOf(this.timeout));
        if (this.codecName != null) {
            json.put("codecName", this.codecName);
        }
        if (this.headers != null) {
            JsonObject hJson = new JsonObject();
            this.headers.entries().forEach(entry -> {
                hJson.put((String) entry.getKey(), (String) entry.getValue());
            });
            json.put("headers", hJson);
        }
        json.put("localOnly", Boolean.valueOf(this.localOnly));
        return json;
    }

    public long getSendTimeout() {
        return this.timeout;
    }

    public DeliveryOptions setSendTimeout(long timeout) {
        Arguments.require(timeout >= 1, "sendTimeout must be >= 1");
        this.timeout = timeout;
        return this;
    }

    public String getCodecName() {
        return this.codecName;
    }

    public DeliveryOptions setCodecName(String codecName) {
        this.codecName = codecName;
        return this;
    }

    public DeliveryOptions addHeader(String key, String value) {
        checkHeaders();
        Objects.requireNonNull(key, "no null key accepted");
        Objects.requireNonNull(value, "no null value accepted");
        this.headers.add(key, value);
        return this;
    }

    @GenIgnore
    public DeliveryOptions setHeaders(MultiMap headers) {
        this.headers = headers;
        return this;
    }

    @GenIgnore
    public MultiMap getHeaders() {
        return this.headers;
    }

    private void checkHeaders() {
        if (this.headers == null) {
            this.headers = new CaseInsensitiveHeaders();
        }
    }

    public boolean isLocalOnly() {
        return this.localOnly;
    }

    public DeliveryOptions setLocalOnly(boolean localOnly) {
        this.localOnly = localOnly;
        return this;
    }
}
