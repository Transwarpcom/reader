package io.vertx.core.http;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/RequestOptionsConverter.class */
public class RequestOptionsConverter {
    public static void fromJson(Iterable<Map.Entry<String, Object>> json, RequestOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "headers":
                    if (member.getValue() instanceof JsonObject) {
                        ((Iterable) member.getValue()).forEach(entry -> {
                            if (entry.getValue() instanceof String) {
                                obj.addHeader((String) entry.getKey(), (String) entry.getValue());
                            }
                        });
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
                case "ssl":
                    if (member.getValue() instanceof Boolean) {
                        obj.setSsl((Boolean) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "uri":
                    if (member.getValue() instanceof String) {
                        obj.setURI((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    public static void toJson(RequestOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    public static void toJson(RequestOptions obj, Map<String, Object> json) {
        if (obj.getHost() != null) {
            json.put("host", obj.getHost());
        }
        json.put(RtspHeaders.Values.PORT, Integer.valueOf(obj.getPort()));
        if (obj.isSsl() != null) {
            json.put("ssl", obj.isSsl());
        }
        if (obj.getURI() != null) {
            json.put("uri", obj.getURI());
        }
    }
}
