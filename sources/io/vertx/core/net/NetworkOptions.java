package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/NetworkOptions.class */
public abstract class NetworkOptions {
    public static final int DEFAULT_SEND_BUFFER_SIZE = -1;
    public static final int DEFAULT_RECEIVE_BUFFER_SIZE = -1;
    public static final int DEFAULT_TRAFFIC_CLASS = -1;
    public static final boolean DEFAULT_REUSE_ADDRESS = true;
    public static final boolean DEFAULT_REUSE_PORT = false;
    public static final boolean DEFAULT_LOG_ENABLED = false;
    private int sendBufferSize;
    private int receiveBufferSize;
    private int trafficClass;
    private boolean reuseAddress;
    private boolean logActivity;
    private boolean reusePort;

    public NetworkOptions() {
        this.sendBufferSize = -1;
        this.receiveBufferSize = -1;
        this.reuseAddress = true;
        this.trafficClass = -1;
        this.logActivity = false;
        this.reusePort = false;
    }

    public NetworkOptions(NetworkOptions other) {
        this.sendBufferSize = other.getSendBufferSize();
        this.receiveBufferSize = other.getReceiveBufferSize();
        this.reuseAddress = other.isReuseAddress();
        this.reusePort = other.isReusePort();
        this.trafficClass = other.getTrafficClass();
        this.logActivity = other.logActivity;
    }

    public NetworkOptions(JsonObject json) {
        this();
        NetworkOptionsConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        NetworkOptionsConverter.toJson(this, json);
        return json;
    }

    public int getSendBufferSize() {
        return this.sendBufferSize;
    }

    public NetworkOptions setSendBufferSize(int sendBufferSize) {
        Arguments.require(sendBufferSize > 0 || sendBufferSize == -1, "sendBufferSize must be > 0");
        this.sendBufferSize = sendBufferSize;
        return this;
    }

    public int getReceiveBufferSize() {
        return this.receiveBufferSize;
    }

    public NetworkOptions setReceiveBufferSize(int receiveBufferSize) {
        Arguments.require(receiveBufferSize > 0 || receiveBufferSize == -1, "receiveBufferSize must be > 0");
        this.receiveBufferSize = receiveBufferSize;
        return this;
    }

    public boolean isReuseAddress() {
        return this.reuseAddress;
    }

    public NetworkOptions setReuseAddress(boolean reuseAddress) {
        this.reuseAddress = reuseAddress;
        return this;
    }

    public int getTrafficClass() {
        return this.trafficClass;
    }

    public NetworkOptions setTrafficClass(int trafficClass) {
        Arguments.requireInRange(trafficClass, -1, 255, "trafficClass tc must be 0 <= tc <= 255");
        this.trafficClass = trafficClass;
        return this;
    }

    public boolean getLogActivity() {
        return this.logActivity;
    }

    public NetworkOptions setLogActivity(boolean logActivity) {
        this.logActivity = logActivity;
        return this;
    }

    public boolean isReusePort() {
        return this.reusePort;
    }

    public NetworkOptions setReusePort(boolean reusePort) {
        this.reusePort = reusePort;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NetworkOptions)) {
            return false;
        }
        NetworkOptions that = (NetworkOptions) o;
        return this.receiveBufferSize == that.receiveBufferSize && this.reuseAddress == that.reuseAddress && this.reusePort == that.reusePort && this.sendBufferSize == that.sendBufferSize && this.trafficClass == that.trafficClass;
    }

    public int hashCode() {
        int result = this.sendBufferSize;
        return (31 * ((31 * ((31 * ((31 * result) + this.receiveBufferSize)) + this.trafficClass)) + (this.reuseAddress ? 1 : 0))) + (this.reusePort ? 1 : 0);
    }
}
