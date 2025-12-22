package io.vertx.core.net;

import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/NetworkOptionsConverter.class */
class NetworkOptionsConverter {
    NetworkOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, NetworkOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "logActivity":
                    if (member.getValue() instanceof Boolean) {
                        obj.setLogActivity(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "receiveBufferSize":
                    if (member.getValue() instanceof Number) {
                        obj.setReceiveBufferSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "reuseAddress":
                    if (member.getValue() instanceof Boolean) {
                        obj.setReuseAddress(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "reusePort":
                    if (member.getValue() instanceof Boolean) {
                        obj.setReusePort(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "sendBufferSize":
                    if (member.getValue() instanceof Number) {
                        obj.setSendBufferSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "trafficClass":
                    if (member.getValue() instanceof Number) {
                        obj.setTrafficClass(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(NetworkOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(NetworkOptions obj, Map<String, Object> json) {
        json.put("logActivity", Boolean.valueOf(obj.getLogActivity()));
        json.put("receiveBufferSize", Integer.valueOf(obj.getReceiveBufferSize()));
        json.put("reuseAddress", Boolean.valueOf(obj.isReuseAddress()));
        json.put("reusePort", Boolean.valueOf(obj.isReusePort()));
        json.put("sendBufferSize", Integer.valueOf(obj.getSendBufferSize()));
        json.put("trafficClass", Integer.valueOf(obj.getTrafficClass()));
    }
}
