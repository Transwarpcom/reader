package io.vertx.ext.web.handler.sockjs;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@DataObject
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/BridgeOptions.class */
public class BridgeOptions {
    public static final int DEFAULT_MAX_ADDRESS_LENGTH = 200;
    public static final int DEFAULT_MAX_HANDLERS_PER_SOCKET = 1000;
    public static final long DEFAULT_PING_TIMEOUT = 10000;
    public static final long DEFAULT_REPLY_TIMEOUT = 30000;
    private int maxAddressLength;
    private int maxHandlersPerSocket;
    private long pingTimeout;
    private long replyTimeout;
    private List<io.vertx.ext.bridge.PermittedOptions> inboundPermitted;
    private List<io.vertx.ext.bridge.PermittedOptions> outboundPermitted;

    public BridgeOptions(BridgeOptions other) {
        this.inboundPermitted = new ArrayList();
        this.outboundPermitted = new ArrayList();
        this.maxAddressLength = other.maxAddressLength;
        this.maxHandlersPerSocket = other.maxHandlersPerSocket;
        this.pingTimeout = other.pingTimeout;
        this.replyTimeout = other.replyTimeout;
        this.inboundPermitted = new ArrayList(other.inboundPermitted);
        this.outboundPermitted = new ArrayList(other.outboundPermitted);
    }

    public BridgeOptions() {
        this.inboundPermitted = new ArrayList();
        this.outboundPermitted = new ArrayList();
        this.maxAddressLength = 200;
        this.maxHandlersPerSocket = 1000;
        this.pingTimeout = 10000L;
        this.replyTimeout = 30000L;
    }

    public BridgeOptions(JsonObject json) {
        this.inboundPermitted = new ArrayList();
        this.outboundPermitted = new ArrayList();
        this.maxAddressLength = json.getInteger("maxAddressLength", 200).intValue();
        this.maxHandlersPerSocket = json.getInteger("maxHandlersPerSocket", 1000).intValue();
        this.pingTimeout = json.getLong("pingTimeout", 10000L).longValue();
        this.replyTimeout = json.getLong("replyTimeout", 30000L).longValue();
        JsonArray arr = json.getJsonArray("inboundPermitteds");
        if (arr != null) {
            Iterator<Object> it = arr.iterator();
            while (it.hasNext()) {
                Object obj = it.next();
                if (obj instanceof JsonObject) {
                    JsonObject jobj = (JsonObject) obj;
                    this.inboundPermitted.add(new io.vertx.ext.bridge.PermittedOptions(jobj));
                } else {
                    throw new IllegalArgumentException("Invalid type " + obj.getClass() + " in inboundPermitteds array");
                }
            }
        }
        JsonArray arr2 = json.getJsonArray("outboundPermitteds");
        if (arr2 != null) {
            Iterator<Object> it2 = arr2.iterator();
            while (it2.hasNext()) {
                Object obj2 = it2.next();
                if (obj2 instanceof JsonObject) {
                    JsonObject jobj2 = (JsonObject) obj2;
                    this.outboundPermitted.add(new io.vertx.ext.bridge.PermittedOptions(jobj2));
                } else {
                    throw new IllegalArgumentException("Invalid type " + obj2.getClass() + " in outboundPermitteds array");
                }
            }
        }
    }

    public int getMaxAddressLength() {
        return this.maxAddressLength;
    }

    public BridgeOptions setMaxAddressLength(int maxAddressLength) {
        if (maxAddressLength < 1) {
            throw new IllegalArgumentException("maxAddressLength must be > 0");
        }
        this.maxAddressLength = maxAddressLength;
        return this;
    }

    public int getMaxHandlersPerSocket() {
        return this.maxHandlersPerSocket;
    }

    public BridgeOptions setMaxHandlersPerSocket(int maxHandlersPerSocket) {
        if (maxHandlersPerSocket < 1) {
            throw new IllegalArgumentException("maxHandlersPerSocket must be > 0");
        }
        this.maxHandlersPerSocket = maxHandlersPerSocket;
        return this;
    }

    public long getPingTimeout() {
        return this.pingTimeout;
    }

    public BridgeOptions setPingTimeout(long pingTimeout) {
        if (pingTimeout < 1) {
            throw new IllegalArgumentException("pingTimeout must be > 0");
        }
        this.pingTimeout = pingTimeout;
        return this;
    }

    public long getReplyTimeout() {
        return this.replyTimeout;
    }

    public BridgeOptions setReplyTimeout(long replyTimeout) {
        if (replyTimeout < 1) {
            throw new IllegalArgumentException("replyTimeout must be > 0");
        }
        this.replyTimeout = replyTimeout;
        return this;
    }

    public BridgeOptions addInboundPermitted(io.vertx.ext.bridge.PermittedOptions permitted) {
        this.inboundPermitted.add(permitted);
        return this;
    }

    public List<io.vertx.ext.bridge.PermittedOptions> getInboundPermitteds() {
        return this.inboundPermitted;
    }

    public void setInboundPermitted(List<io.vertx.ext.bridge.PermittedOptions> inboundPermitted) {
        this.inboundPermitted = inboundPermitted;
    }

    public BridgeOptions addOutboundPermitted(io.vertx.ext.bridge.PermittedOptions permitted) {
        this.outboundPermitted.add(permitted);
        return this;
    }

    public List<io.vertx.ext.bridge.PermittedOptions> getOutboundPermitteds() {
        return this.outboundPermitted;
    }

    public void setOutboundPermitted(List<io.vertx.ext.bridge.PermittedOptions> outboundPermitted) {
        this.outboundPermitted = outboundPermitted;
    }
}
