package io.vertx.core.http;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/StreamPriority.class */
public class StreamPriority {
    public static final int DEFAULT_DEPENDENCY = 0;
    public static final short DEFAULT_WEIGHT = 16;
    public static final boolean DEFAULT_EXCLUSIVE = false;
    private short weight;
    private int dependency;
    private boolean exclusive;

    public StreamPriority() {
        this.weight = (short) 16;
        this.dependency = 0;
        this.exclusive = false;
    }

    public StreamPriority(JsonObject json) {
        this.weight = json.getInteger("weight", 16).shortValue();
        this.dependency = json.getInteger("dependency", 0).intValue();
        this.exclusive = json.getBoolean("exclusive", false).booleanValue();
    }

    public StreamPriority(StreamPriority other) {
        this.weight = other.weight;
        this.dependency = other.dependency;
        this.exclusive = other.exclusive;
    }

    public short getWeight() {
        return this.weight;
    }

    public StreamPriority setWeight(short weight) {
        this.weight = weight;
        return this;
    }

    public int getDependency() {
        return this.dependency;
    }

    public StreamPriority setDependency(int dependency) {
        this.dependency = dependency;
        return this;
    }

    public boolean isExclusive() {
        return this.exclusive;
    }

    public StreamPriority setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
        return this;
    }

    public int hashCode() {
        int result = (31 * 1) + (this.exclusive ? 1231 : 1237);
        return (31 * ((31 * result) + this.dependency)) + this.weight;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StreamPriority other = (StreamPriority) obj;
        return this.exclusive == other.exclusive && this.dependency == other.dependency && this.weight == other.weight;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.put("weight", Short.valueOf(this.weight));
        json.put("dependency", Integer.valueOf(this.dependency));
        json.put("exclusive", Boolean.valueOf(this.exclusive));
        return json;
    }

    public String toString() {
        return "StreamPriority [weight=" + ((int) this.weight) + ", dependency=" + this.dependency + ", exclusive=" + this.exclusive + "]";
    }
}
