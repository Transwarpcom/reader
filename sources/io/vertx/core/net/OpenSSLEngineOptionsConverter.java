package io.vertx.core.net;

import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/OpenSSLEngineOptionsConverter.class */
class OpenSSLEngineOptionsConverter {
    OpenSSLEngineOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, OpenSSLEngineOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "sessionCacheEnabled":
                    if (member.getValue() instanceof Boolean) {
                        obj.setSessionCacheEnabled(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(OpenSSLEngineOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(OpenSSLEngineOptions obj, Map<String, Object> json) {
        json.put("sessionCacheEnabled", Boolean.valueOf(obj.isSessionCacheEnabled()));
    }
}
