package io.vertx.core.net;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/NetServerOptionsConverter.class */
class NetServerOptionsConverter {
    NetServerOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, NetServerOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "acceptBacklog":
                    if (member.getValue() instanceof Number) {
                        obj.setAcceptBacklog(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "clientAuth":
                    if (member.getValue() instanceof String) {
                        obj.setClientAuth(ClientAuth.valueOf((String) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "clientAuthRequired":
                    if (member.getValue() instanceof Boolean) {
                        obj.setClientAuthRequired(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "host":
                    if (member.getValue() instanceof String) {
                        obj.setHost((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "port":
                    if (member.getValue() instanceof Number) {
                        obj.setPort(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "sni":
                    if (member.getValue() instanceof Boolean) {
                        obj.setSni(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(NetServerOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(NetServerOptions obj, Map<String, Object> json) {
        json.put("acceptBacklog", Integer.valueOf(obj.getAcceptBacklog()));
        if (obj.getClientAuth() != null) {
            json.put("clientAuth", obj.getClientAuth().name());
        }
        json.put("clientAuthRequired", Boolean.valueOf(obj.isClientAuthRequired()));
        if (obj.getHost() != null) {
            json.put("host", obj.getHost());
        }
        json.put(RtspHeaders.Values.PORT, Integer.valueOf(obj.getPort()));
        json.put("sni", Boolean.valueOf(obj.isSni()));
    }
}
