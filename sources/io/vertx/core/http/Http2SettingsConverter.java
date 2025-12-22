package io.vertx.core.http;

import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/Http2SettingsConverter.class */
class Http2SettingsConverter {
    Http2SettingsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, Http2Settings obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "headerTableSize":
                    if (member.getValue() instanceof Number) {
                        obj.setHeaderTableSize(((Number) member.getValue()).longValue());
                        break;
                    } else {
                        break;
                    }
                case "initialWindowSize":
                    if (member.getValue() instanceof Number) {
                        obj.setInitialWindowSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "maxConcurrentStreams":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxConcurrentStreams(((Number) member.getValue()).longValue());
                        break;
                    } else {
                        break;
                    }
                case "maxFrameSize":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxFrameSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "maxHeaderListSize":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxHeaderListSize(((Number) member.getValue()).longValue());
                        break;
                    } else {
                        break;
                    }
                case "pushEnabled":
                    if (member.getValue() instanceof Boolean) {
                        obj.setPushEnabled(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(Http2Settings obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(Http2Settings obj, Map<String, Object> json) {
        json.put("headerTableSize", Long.valueOf(obj.getHeaderTableSize()));
        json.put("initialWindowSize", Integer.valueOf(obj.getInitialWindowSize()));
        json.put("maxConcurrentStreams", Long.valueOf(obj.getMaxConcurrentStreams()));
        json.put("maxFrameSize", Integer.valueOf(obj.getMaxFrameSize()));
        json.put("maxHeaderListSize", Long.valueOf(obj.getMaxHeaderListSize()));
        json.put("pushEnabled", Boolean.valueOf(obj.isPushEnabled()));
    }
}
