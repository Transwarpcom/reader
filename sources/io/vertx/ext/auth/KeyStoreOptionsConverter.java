package io.vertx.ext.auth;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.FormLoginHandler;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/KeyStoreOptionsConverter.class */
public class KeyStoreOptionsConverter {
    public static void fromJson(Iterable<Map.Entry<String, Object>> json, KeyStoreOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "password":
                    if (member.getValue() instanceof String) {
                        obj.setPassword((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "path":
                    if (member.getValue() instanceof String) {
                        obj.setPath((String) member.getValue());
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

    public static void toJson(KeyStoreOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    public static void toJson(KeyStoreOptions obj, Map<String, Object> json) {
        if (obj.getPassword() != null) {
            json.put(FormLoginHandler.DEFAULT_PASSWORD_PARAM, obj.getPassword());
        }
        if (obj.getPath() != null) {
            json.put("path", obj.getPath());
        }
        if (obj.getType() != null) {
            json.put("type", obj.getType());
        }
    }
}
