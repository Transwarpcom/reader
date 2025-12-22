package io.vertx.core.dns;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.AddressResolver;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/dns/AddressResolverOptions.class */
public class AddressResolverOptions {
    public static final boolean DEFAULT_OPT_RESOURCE_ENABLED = true;
    public static final int DEFAULT_CACHE_MIN_TIME_TO_LIVE = 0;
    public static final int DEFAULT_CACHE_MAX_TIME_TO_LIVE = Integer.MAX_VALUE;
    public static final int DEFAULT_CACHE_NEGATIVE_TIME_TO_LIVE = 0;
    public static final int DEFAULT_QUERY_TIMEOUT = 5000;
    public static final int DEFAULT_MAX_QUERIES = 4;
    public static final boolean DEFAULT_RD_FLAG = true;
    private String hostsPath;
    private Buffer hostsValue;
    private List<String> servers;
    private boolean optResourceEnabled;
    private int cacheMinTimeToLive;
    private int cacheMaxTimeToLive;
    private int cacheNegativeTimeToLive;
    private long queryTimeout;
    private int maxQueries;
    private boolean rdFlag;
    private List<String> searchDomains;
    private int ndots;
    private boolean rotateServers;
    public static final List<String> DEFAULT_SERVERS = null;
    public static final List<String> DEFAULT_SEACH_DOMAINS = null;
    public static final int DEFAULT_NDOTS = AddressResolver.DEFAULT_NDOTS_RESOLV_OPTION;
    public static final boolean DEFAULT_ROTATE_SERVERS = AddressResolver.DEFAULT_ROTATE_RESOLV_OPTION;

    public AddressResolverOptions() {
        this.servers = DEFAULT_SERVERS;
        this.optResourceEnabled = true;
        this.cacheMinTimeToLive = 0;
        this.cacheMaxTimeToLive = Integer.MAX_VALUE;
        this.cacheNegativeTimeToLive = 0;
        this.queryTimeout = 5000L;
        this.maxQueries = 4;
        this.rdFlag = true;
        this.searchDomains = DEFAULT_SEACH_DOMAINS;
        this.ndots = DEFAULT_NDOTS;
        this.rotateServers = DEFAULT_ROTATE_SERVERS;
    }

    public AddressResolverOptions(AddressResolverOptions other) {
        this.hostsPath = other.hostsPath;
        this.hostsValue = other.hostsValue != null ? other.hostsValue.copy() : null;
        this.servers = other.servers != null ? new ArrayList(other.servers) : null;
        this.optResourceEnabled = other.optResourceEnabled;
        this.cacheMinTimeToLive = other.cacheMinTimeToLive;
        this.cacheMaxTimeToLive = other.cacheMaxTimeToLive;
        this.cacheNegativeTimeToLive = other.cacheNegativeTimeToLive;
        this.queryTimeout = other.queryTimeout;
        this.maxQueries = other.maxQueries;
        this.rdFlag = other.rdFlag;
        this.searchDomains = other.searchDomains != null ? new ArrayList(other.searchDomains) : null;
        this.ndots = other.ndots;
        this.rotateServers = other.rotateServers;
    }

    public AddressResolverOptions(JsonObject json) {
        this();
        AddressResolverOptionsConverter.fromJson(json, this);
    }

    public String getHostsPath() {
        return this.hostsPath;
    }

    public AddressResolverOptions setHostsPath(String hostsPath) {
        this.hostsPath = hostsPath;
        return this;
    }

    public Buffer getHostsValue() {
        return this.hostsValue;
    }

    public AddressResolverOptions setHostsValue(Buffer hostsValue) {
        this.hostsValue = hostsValue;
        return this;
    }

    public List<String> getServers() {
        return this.servers;
    }

    public AddressResolverOptions setServers(List<String> servers) {
        this.servers = servers;
        return this;
    }

    public AddressResolverOptions addServer(String server) {
        if (this.servers == null) {
            this.servers = new ArrayList();
        }
        this.servers.add(server);
        return this;
    }

    public boolean isOptResourceEnabled() {
        return this.optResourceEnabled;
    }

    public AddressResolverOptions setOptResourceEnabled(boolean optResourceEnabled) {
        this.optResourceEnabled = optResourceEnabled;
        return this;
    }

    public int getCacheMinTimeToLive() {
        return this.cacheMinTimeToLive;
    }

    public AddressResolverOptions setCacheMinTimeToLive(int cacheMinTimeToLive) {
        if (cacheMinTimeToLive < 0) {
            throw new IllegalArgumentException("cacheMinTimeToLive must be >= 0");
        }
        this.cacheMinTimeToLive = cacheMinTimeToLive;
        return this;
    }

    public int getCacheMaxTimeToLive() {
        return this.cacheMaxTimeToLive;
    }

    public AddressResolverOptions setCacheMaxTimeToLive(int cacheMaxTimeToLive) {
        if (cacheMaxTimeToLive < 0) {
            throw new IllegalArgumentException("cacheMaxTimeToLive must be >= 0");
        }
        this.cacheMaxTimeToLive = cacheMaxTimeToLive;
        return this;
    }

    public int getCacheNegativeTimeToLive() {
        return this.cacheNegativeTimeToLive;
    }

    public AddressResolverOptions setCacheNegativeTimeToLive(int cacheNegativeTimeToLive) {
        if (cacheNegativeTimeToLive < 0) {
            throw new IllegalArgumentException("cacheNegativeTimeToLive must be >= 0");
        }
        this.cacheNegativeTimeToLive = cacheNegativeTimeToLive;
        return this;
    }

    public long getQueryTimeout() {
        return this.queryTimeout;
    }

    public AddressResolverOptions setQueryTimeout(long queryTimeout) {
        if (queryTimeout < 1) {
            throw new IllegalArgumentException("queryTimeout must be > 0");
        }
        this.queryTimeout = queryTimeout;
        return this;
    }

    public int getMaxQueries() {
        return this.maxQueries;
    }

    public AddressResolverOptions setMaxQueries(int maxQueries) {
        if (maxQueries < 1) {
            throw new IllegalArgumentException("maxQueries must be > 0");
        }
        this.maxQueries = maxQueries;
        return this;
    }

    public boolean getRdFlag() {
        return this.rdFlag;
    }

    public AddressResolverOptions setRdFlag(boolean rdFlag) {
        this.rdFlag = rdFlag;
        return this;
    }

    public List<String> getSearchDomains() {
        return this.searchDomains;
    }

    public AddressResolverOptions setSearchDomains(List<String> searchDomains) {
        this.searchDomains = searchDomains;
        return this;
    }

    public AddressResolverOptions addSearchDomain(String searchDomain) {
        if (this.searchDomains == null) {
            this.searchDomains = new ArrayList();
        }
        this.searchDomains.add(searchDomain);
        return this;
    }

    public int getNdots() {
        return this.ndots;
    }

    public AddressResolverOptions setNdots(int ndots) {
        if (ndots < -1) {
            throw new IllegalArgumentException("ndots must be >= -1");
        }
        this.ndots = ndots;
        return this;
    }

    public boolean isRotateServers() {
        return this.rotateServers;
    }

    public AddressResolverOptions setRotateServers(boolean rotateServers) {
        this.rotateServers = rotateServers;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddressResolverOptions that = (AddressResolverOptions) o;
        if (this.optResourceEnabled != that.optResourceEnabled || this.cacheMinTimeToLive != that.cacheMinTimeToLive || this.cacheMaxTimeToLive != that.cacheMaxTimeToLive || this.cacheNegativeTimeToLive != that.cacheNegativeTimeToLive || this.queryTimeout != that.queryTimeout || this.maxQueries != that.maxQueries || this.rdFlag != that.rdFlag || !Objects.equals(this.searchDomains, that.searchDomains) || this.ndots != that.ndots) {
            return false;
        }
        if (this.servers != null) {
            if (!this.servers.equals(that.servers)) {
                return false;
            }
        } else if (that.servers != null) {
            return false;
        }
        return this.rotateServers == that.rotateServers;
    }

    public int hashCode() {
        int result = this.optResourceEnabled ? 1 : 0;
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + (this.servers != null ? this.servers.hashCode() : 0))) + this.cacheMinTimeToLive)) + this.cacheMaxTimeToLive)) + this.cacheNegativeTimeToLive)) + Long.hashCode(this.queryTimeout))) + this.maxQueries)) + (this.searchDomains != null ? this.searchDomains.hashCode() : 0))) + this.ndots)) + Boolean.hashCode(this.rdFlag))) + Boolean.hashCode(this.rotateServers);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        AddressResolverOptionsConverter.toJson(this, json);
        return json;
    }
}
