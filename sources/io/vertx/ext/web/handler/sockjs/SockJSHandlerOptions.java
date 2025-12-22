package io.vertx.ext.web.handler.sockjs;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@DataObject
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/SockJSHandlerOptions.class */
public class SockJSHandlerOptions {
    public static final long DEFAULT_SESSION_TIMEOUT = 5000;
    public static final boolean DEFAULT_INSERT_JSESSIONID = true;
    public static final long DEFAULT_HEARTBEAT_INTERVAL = 25000;
    public static final int DEFAULT_MAX_BYTES_STREAMING = 131072;
    public static final String DEFAULT_LIBRARY_URL = "//cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js";
    private long sessionTimeout;
    private boolean insertJSESSIONID;
    private long heartbeatInterval;
    private int maxBytesStreaming;
    private String libraryURL;
    private Set<String> disabledTransports;

    public SockJSHandlerOptions(SockJSHandlerOptions other) {
        this.disabledTransports = new HashSet();
        throw new UnsupportedOperationException("todo");
    }

    public SockJSHandlerOptions() {
        this.disabledTransports = new HashSet();
        this.sessionTimeout = 5000L;
        this.insertJSESSIONID = true;
        this.heartbeatInterval = DEFAULT_HEARTBEAT_INTERVAL;
        this.maxBytesStreaming = 131072;
        this.libraryURL = DEFAULT_LIBRARY_URL;
    }

    public SockJSHandlerOptions(JsonObject json) {
        this.disabledTransports = new HashSet();
        this.sessionTimeout = json.getLong("sessionTimeout", 5000L).longValue();
        this.insertJSESSIONID = json.getBoolean("insertJSESSIONID", true).booleanValue();
        this.heartbeatInterval = json.getLong("heartbeatInterval", Long.valueOf(DEFAULT_HEARTBEAT_INTERVAL)).longValue();
        this.maxBytesStreaming = json.getInteger("maxBytesStreaming", 131072).intValue();
        this.libraryURL = json.getString("libraryURL", DEFAULT_LIBRARY_URL);
        JsonArray arr = json.getJsonArray("disabledTransports");
        if (arr != null) {
            Iterator<Object> it = arr.iterator();
            while (it.hasNext()) {
                Object str = it.next();
                if (str instanceof String) {
                    String sstr = (String) str;
                    this.disabledTransports.add(sstr);
                } else {
                    throw new IllegalArgumentException("Invalid type " + str.getClass() + " in disabledTransports array");
                }
            }
        }
    }

    public long getSessionTimeout() {
        return this.sessionTimeout;
    }

    public SockJSHandlerOptions setSessionTimeout(long sessionTimeout) {
        if (sessionTimeout < 1) {
            throw new IllegalArgumentException("sessionTimeout must be > 0");
        }
        this.sessionTimeout = sessionTimeout;
        return this;
    }

    public boolean isInsertJSESSIONID() {
        return this.insertJSESSIONID;
    }

    public SockJSHandlerOptions setInsertJSESSIONID(boolean insertJSESSIONID) {
        this.insertJSESSIONID = insertJSESSIONID;
        return this;
    }

    public long getHeartbeatInterval() {
        return this.heartbeatInterval;
    }

    public SockJSHandlerOptions setHeartbeatInterval(long heartbeatInterval) {
        if (heartbeatInterval < 1) {
            throw new IllegalArgumentException("heartbeatInterval must be > 0");
        }
        this.heartbeatInterval = heartbeatInterval;
        return this;
    }

    public int getMaxBytesStreaming() {
        return this.maxBytesStreaming;
    }

    public SockJSHandlerOptions setMaxBytesStreaming(int maxBytesStreaming) {
        if (maxBytesStreaming < 1) {
            throw new IllegalArgumentException("maxBytesStreaming must be > 0");
        }
        this.maxBytesStreaming = maxBytesStreaming;
        return this;
    }

    public String getLibraryURL() {
        return this.libraryURL;
    }

    public SockJSHandlerOptions setLibraryURL(String libraryURL) {
        this.libraryURL = libraryURL;
        return this;
    }

    public SockJSHandlerOptions addDisabledTransport(String subProtocol) {
        this.disabledTransports.add(subProtocol);
        return this;
    }

    public Set<String> getDisabledTransports() {
        return this.disabledTransports;
    }
}
