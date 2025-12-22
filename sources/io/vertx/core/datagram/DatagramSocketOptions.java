package io.vertx.core.datagram;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.NetworkOptions;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/datagram/DatagramSocketOptions.class */
public class DatagramSocketOptions extends NetworkOptions {
    public static final boolean DEFAULT_BROADCAST = false;
    public static final boolean DEFAULT_LOOPBACK_MODE_DISABLED = true;
    public static final int DEFAULT_MULTICAST_TIME_TO_LIVE = -1;
    public static final String DEFAULT_MULTICAST_NETWORK_INTERFACE = null;
    public static final boolean DEFAULT_REUSE_ADDRESS = false;
    public static final boolean DEFAULT_IPV6 = false;
    private boolean broadcast;
    private boolean loopbackModeDisabled;
    private int multicastTimeToLive;
    private String multicastNetworkInterface;
    private boolean ipV6;

    public DatagramSocketOptions() {
        init();
        setReuseAddress(false);
    }

    public DatagramSocketOptions(DatagramSocketOptions other) {
        super(other);
        this.broadcast = other.isBroadcast();
        this.loopbackModeDisabled = other.isLoopbackModeDisabled();
        this.multicastTimeToLive = other.getMulticastTimeToLive();
        this.multicastNetworkInterface = other.getMulticastNetworkInterface();
        this.ipV6 = other.isIpV6();
    }

    public DatagramSocketOptions(JsonObject json) {
        super(json);
        init();
        DatagramSocketOptionsConverter.fromJson(json, this);
    }

    private void init() {
        this.broadcast = false;
        this.loopbackModeDisabled = true;
        this.multicastTimeToLive = -1;
        this.multicastNetworkInterface = DEFAULT_MULTICAST_NETWORK_INTERFACE;
        this.ipV6 = false;
    }

    @Override // io.vertx.core.net.NetworkOptions
    public int getSendBufferSize() {
        return super.getSendBufferSize();
    }

    @Override // io.vertx.core.net.NetworkOptions
    public DatagramSocketOptions setSendBufferSize(int sendBufferSize) {
        super.setSendBufferSize(sendBufferSize);
        return this;
    }

    @Override // io.vertx.core.net.NetworkOptions
    public int getReceiveBufferSize() {
        return super.getReceiveBufferSize();
    }

    @Override // io.vertx.core.net.NetworkOptions
    public DatagramSocketOptions setReceiveBufferSize(int receiveBufferSize) {
        super.setReceiveBufferSize(receiveBufferSize);
        return this;
    }

    @Override // io.vertx.core.net.NetworkOptions
    public DatagramSocketOptions setReuseAddress(boolean reuseAddress) {
        super.setReuseAddress(reuseAddress);
        return this;
    }

    @Override // io.vertx.core.net.NetworkOptions
    public DatagramSocketOptions setReusePort(boolean reusePort) {
        return (DatagramSocketOptions) super.setReusePort(reusePort);
    }

    @Override // io.vertx.core.net.NetworkOptions
    public int getTrafficClass() {
        return super.getTrafficClass();
    }

    @Override // io.vertx.core.net.NetworkOptions
    public DatagramSocketOptions setTrafficClass(int trafficClass) {
        super.setTrafficClass(trafficClass);
        return this;
    }

    public boolean isBroadcast() {
        return this.broadcast;
    }

    public DatagramSocketOptions setBroadcast(boolean broadcast) {
        this.broadcast = broadcast;
        return this;
    }

    public boolean isLoopbackModeDisabled() {
        return this.loopbackModeDisabled;
    }

    public DatagramSocketOptions setLoopbackModeDisabled(boolean loopbackModeDisabled) {
        this.loopbackModeDisabled = loopbackModeDisabled;
        return this;
    }

    public int getMulticastTimeToLive() {
        return this.multicastTimeToLive;
    }

    public DatagramSocketOptions setMulticastTimeToLive(int multicastTimeToLive) {
        Arguments.require(multicastTimeToLive >= 0, "multicastTimeToLive must be >= 0");
        this.multicastTimeToLive = multicastTimeToLive;
        return this;
    }

    public String getMulticastNetworkInterface() {
        return this.multicastNetworkInterface;
    }

    public DatagramSocketOptions setMulticastNetworkInterface(String multicastNetworkInterface) {
        this.multicastNetworkInterface = multicastNetworkInterface;
        return this;
    }

    public boolean isIpV6() {
        return this.ipV6;
    }

    public DatagramSocketOptions setIpV6(boolean ipV6) {
        this.ipV6 = ipV6;
        return this;
    }

    @Override // io.vertx.core.net.NetworkOptions
    public DatagramSocketOptions setLogActivity(boolean logEnabled) {
        return (DatagramSocketOptions) super.setLogActivity(logEnabled);
    }

    @Override // io.vertx.core.net.NetworkOptions
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DatagramSocketOptions) || !super.equals(o)) {
            return false;
        }
        DatagramSocketOptions that = (DatagramSocketOptions) o;
        if (this.broadcast != that.broadcast || this.ipV6 != that.ipV6 || this.loopbackModeDisabled != that.loopbackModeDisabled || this.multicastTimeToLive != that.multicastTimeToLive) {
            return false;
        }
        if (this.multicastNetworkInterface != null) {
            if (!this.multicastNetworkInterface.equals(that.multicastNetworkInterface)) {
                return false;
            }
            return true;
        }
        if (that.multicastNetworkInterface != null) {
            return false;
        }
        return true;
    }

    @Override // io.vertx.core.net.NetworkOptions
    public int hashCode() {
        int result = super.hashCode();
        return (31 * ((31 * ((31 * ((31 * ((31 * result) + (this.broadcast ? 1 : 0))) + (this.loopbackModeDisabled ? 1 : 0))) + this.multicastTimeToLive)) + (this.multicastNetworkInterface != null ? this.multicastNetworkInterface.hashCode() : 0))) + (this.ipV6 ? 1 : 0);
    }
}
