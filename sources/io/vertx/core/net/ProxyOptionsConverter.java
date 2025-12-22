package io.vertx.core.net;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.FormLoginHandler;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/ProxyOptionsConverter.class */
class ProxyOptionsConverter {
    ProxyOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, ProxyOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "host":
                    if (member.getValue() instanceof String) {
                        obj.setHost((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "password":
                    if (member.getValue() instanceof String) {
                        obj.setPassword((String) member.getValue());
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
                case "type":
                    if (member.getValue() instanceof String) {
                        obj.setType(ProxyType.valueOf((String) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "username":
                    if (member.getValue() instanceof String) {
                        obj.setUsername((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(ProxyOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(ProxyOptions obj, Map<String, Object> json) {
        if (obj.getHost() != null) {
            json.put("host", obj.getHost());
        }
        if (obj.getPassword() != null) {
            json.put(FormLoginHandler.DEFAULT_PASSWORD_PARAM, obj.getPassword());
        }
        json.put(RtspHeaders.Values.PORT, Integer.valueOf(obj.getPort()));
        if (obj.getType() != null) {
            json.put("type", obj.getType().name());
        }
        if (obj.getUsername() != null) {
            json.put(FormLoginHandler.DEFAULT_USERNAME_PARAM, obj.getUsername());
        }
    }
}
