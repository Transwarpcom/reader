package io.vertx.ext.web.client;

import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/WebClientOptionsConverter.class */
public class WebClientOptionsConverter {
    public static void fromJson(Iterable<Map.Entry<String, Object>> json, WebClientOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "followRedirects":
                    if (member.getValue() instanceof Boolean) {
                        obj.setFollowRedirects(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "userAgent":
                    if (member.getValue() instanceof String) {
                        obj.setUserAgent((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "userAgentEnabled":
                    if (member.getValue() instanceof Boolean) {
                        obj.setUserAgentEnabled(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    public static void toJson(WebClientOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    public static void toJson(WebClientOptions obj, Map<String, Object> json) {
        json.put("followRedirects", Boolean.valueOf(obj.isFollowRedirects()));
        if (obj.getUserAgent() != null) {
            json.put("userAgent", obj.getUserAgent());
        }
        json.put("userAgentEnabled", Boolean.valueOf(obj.isUserAgentEnabled()));
    }
}
