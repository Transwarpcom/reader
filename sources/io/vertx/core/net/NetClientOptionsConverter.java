package io.vertx.core.net;

import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/NetClientOptionsConverter.class */
class NetClientOptionsConverter {
    NetClientOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, NetClientOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "hostnameVerificationAlgorithm":
                    if (member.getValue() instanceof String) {
                        obj.setHostnameVerificationAlgorithm((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "reconnectAttempts":
                    if (member.getValue() instanceof Number) {
                        obj.setReconnectAttempts(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "reconnectInterval":
                    if (member.getValue() instanceof Number) {
                        obj.setReconnectInterval(((Number) member.getValue()).longValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(NetClientOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(NetClientOptions obj, Map<String, Object> json) {
        if (obj.getHostnameVerificationAlgorithm() != null) {
            json.put("hostnameVerificationAlgorithm", obj.getHostnameVerificationAlgorithm());
        }
        json.put("reconnectAttempts", Integer.valueOf(obj.getReconnectAttempts()));
        json.put("reconnectInterval", Long.valueOf(obj.getReconnectInterval()));
    }
}
