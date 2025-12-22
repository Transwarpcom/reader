package io.vertx.ext.auth;

import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/SecretOptionsConverter.class */
public class SecretOptionsConverter {
    public static void fromJson(Iterable<Map.Entry<String, Object>> json, SecretOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "secret":
                    if (member.getValue() instanceof String) {
                        obj.setSecret((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "type":
                    if (member.getValue() instanceof String) {
                        obj.setType((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    public static void toJson(SecretOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    public static void toJson(SecretOptions obj, Map<String, Object> json) {
        if (obj.getSecret() != null) {
            json.put("secret", obj.getSecret());
        }
        if (obj.getType() != null) {
            json.put("type", obj.getType());
        }
    }
}
