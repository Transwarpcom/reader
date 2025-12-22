package io.vertx.core.net;

import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/ClientOptionsBaseConverter.class */
class ClientOptionsBaseConverter {
    ClientOptionsBaseConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, ClientOptionsBase obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "connectTimeout":
                    if (member.getValue() instanceof Number) {
                        obj.setConnectTimeout(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "localAddress":
                    if (member.getValue() instanceof String) {
                        obj.setLocalAddress((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "metricsName":
                    if (member.getValue() instanceof String) {
                        obj.setMetricsName((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "proxyOptions":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setProxyOptions(new ProxyOptions((JsonObject) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "trustAll":
                    if (member.getValue() instanceof Boolean) {
                        obj.setTrustAll(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(ClientOptionsBase obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(ClientOptionsBase obj, Map<String, Object> json) {
        json.put("connectTimeout", Integer.valueOf(obj.getConnectTimeout()));
        if (obj.getLocalAddress() != null) {
            json.put("localAddress", obj.getLocalAddress());
        }
        if (obj.getMetricsName() != null) {
            json.put("metricsName", obj.getMetricsName());
        }
        if (obj.getProxyOptions() != null) {
            json.put("proxyOptions", obj.getProxyOptions().toJson());
        }
        json.put("trustAll", Boolean.valueOf(obj.isTrustAll()));
    }
}
