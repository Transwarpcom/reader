package io.vertx.core.dns;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/dns/DnsClientOptions.class */
public class DnsClientOptions {
    public static final int DEFAULT_PORT = -1;
    public static final String DEFAULT_HOST = null;
    public static final long DEFAULT_QUERY_TIMEOUT = 5000;
    public static final boolean DEFAULT_LOG_ENABLED = false;
    public static final boolean DEFAULT_RECURSION_DESIRED = true;
    private int port;
    private String host;
    private long queryTimeout;
    private boolean logActivity;
    private boolean recursionDesired;

    public DnsClientOptions() {
        this.port = -1;
        this.host = DEFAULT_HOST;
        this.queryTimeout = 5000L;
        this.logActivity = false;
        this.recursionDesired = true;
    }

    public DnsClientOptions(JsonObject json) {
        this.port = -1;
        this.host = DEFAULT_HOST;
        this.queryTimeout = 5000L;
        this.logActivity = false;
        this.recursionDesired = true;
        DnsClientOptionsConverter.fromJson(json, this);
    }

    public DnsClientOptions(DnsClientOptions other) {
        this.port = -1;
        this.host = DEFAULT_HOST;
        this.queryTimeout = 5000L;
        this.logActivity = false;
        this.recursionDesired = true;
        this.port = other.port;
        this.host = other.host;
        this.queryTimeout = other.queryTimeout;
        this.logActivity = other.logActivity;
        this.recursionDesired = other.recursionDesired;
    }

    public int getPort() {
        return this.port;
    }

    public DnsClientOptions setPort(int port) {
        if (port < 1 && port != -1) {
            throw new IllegalArgumentException("DNS client port " + port + " must be > 0 or equal to DEFAULT_PORT");
        }
        this.port = port;
        return this;
    }

    public String getHost() {
        return this.host;
    }

    public DnsClientOptions setHost(String host) {
        this.host = host;
        return this;
    }

    public long getQueryTimeout() {
        return this.queryTimeout;
    }

    public DnsClientOptions setQueryTimeout(long queryTimeout) {
        if (queryTimeout < 1) {
            throw new IllegalArgumentException("queryTimeout must be > 0");
        }
        this.queryTimeout = queryTimeout;
        return this;
    }

    public boolean getLogActivity() {
        return this.logActivity;
    }

    public DnsClientOptions setLogActivity(boolean logActivity) {
        this.logActivity = logActivity;
        return this;
    }

    public boolean isRecursionDesired() {
        return this.recursionDesired;
    }

    public DnsClientOptions setRecursionDesired(boolean recursionDesired) {
        this.recursionDesired = recursionDesired;
        return this;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        DnsClientOptionsConverter.toJson(this, json);
        return json;
    }
}
