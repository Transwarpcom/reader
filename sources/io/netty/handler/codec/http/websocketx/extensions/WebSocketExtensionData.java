package io.netty.handler.codec.http.websocketx.extensions;

import java.util.Collections;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/websocketx/extensions/WebSocketExtensionData.class */
public final class WebSocketExtensionData {
    private final String name;
    private final Map<String, String> parameters;

    public WebSocketExtensionData(String name, Map<String, String> parameters) {
        if (name == null) {
            throw new NullPointerException("name");
        }
        if (parameters == null) {
            throw new NullPointerException("parameters");
        }
        this.name = name;
        this.parameters = Collections.unmodifiableMap(parameters);
    }

    public String name() {
        return this.name;
    }

    public Map<String, String> parameters() {
        return this.parameters;
    }
}
