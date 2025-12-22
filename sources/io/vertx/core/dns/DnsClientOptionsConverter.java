package io.vertx.core.dns;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/dns/DnsClientOptionsConverter.class */
public class DnsClientOptionsConverter {
    public static void fromJson(Iterable<Map.Entry<String, Object>> json, DnsClientOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "host":
                    if (member.getValue() instanceof String) {
                        obj.setHost((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "logActivity":
                    if (member.getValue() instanceof Boolean) {
                        obj.setLogActivity(((Boolean) member.getValue()).booleanValue());
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
                case "queryTimeout":
                    if (member.getValue() instanceof Number) {
                        obj.setQueryTimeout(((Number) member.getValue()).longValue());
                        break;
                    } else {
                        break;
                    }
                case "recursionDesired":
                    if (member.getValue() instanceof Boolean) {
                        obj.setRecursionDesired(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    public static void toJson(DnsClientOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    public static void toJson(DnsClientOptions obj, Map<String, Object> json) {
        if (obj.getHost() != null) {
            json.put("host", obj.getHost());
        }
        json.put("logActivity", Boolean.valueOf(obj.getLogActivity()));
        json.put(RtspHeaders.Values.PORT, Integer.valueOf(obj.getPort()));
        json.put("queryTimeout", Long.valueOf(obj.getQueryTimeout()));
        json.put("recursionDesired", Boolean.valueOf(obj.isRecursionDesired()));
    }
}
