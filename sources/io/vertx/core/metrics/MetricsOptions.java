package io.vertx.core.metrics;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.VertxMetricsFactory;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/metrics/MetricsOptions.class */
public class MetricsOptions {
    public static final boolean DEFAULT_METRICS_ENABLED = false;
    private boolean enabled;
    private JsonObject json;
    private VertxMetricsFactory factory;

    public MetricsOptions() {
        this.enabled = false;
    }

    public MetricsOptions(MetricsOptions other) {
        this.enabled = other.isEnabled();
        this.factory = other.factory;
    }

    public MetricsOptions(JsonObject json) {
        this();
        MetricsOptionsConverter.fromJson(json, this);
        this.json = json.copy();
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public MetricsOptions setEnabled(boolean enable) {
        this.enabled = enable;
        return this;
    }

    public VertxMetricsFactory getFactory() {
        return this.factory;
    }

    public MetricsOptions setFactory(VertxMetricsFactory factory) {
        this.factory = factory;
        return this;
    }

    public JsonObject toJson() {
        return this.json != null ? this.json.copy() : new JsonObject();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MetricsOptions that = (MetricsOptions) o;
        if (this.enabled != that.enabled) {
            return false;
        }
        return this.json == null ? that.json == null : this.json.equals(that.json);
    }

    public int hashCode() {
        int result = this.enabled ? 1 : 0;
        return (31 * result) + (this.json != null ? this.json.hashCode() : 0);
    }

    public String toString() {
        return "MetricsOptions{enabled=" + this.enabled + ", json=" + this.json + '}';
    }
}
