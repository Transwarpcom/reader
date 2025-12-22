package io.vertx.core.net;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.FormLoginHandler;
import java.util.Base64;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/JksOptionsConverter.class */
public class JksOptionsConverter {
    public static void fromJson(Iterable<Map.Entry<String, Object>> json, JksOptions obj) {
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
                case "value":
                    if (member.getValue() instanceof String) {
                        obj.setValue(Buffer.buffer(Base64.getDecoder().decode((String) member.getValue())));
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    public static void toJson(JksOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    public static void toJson(JksOptions obj, Map<String, Object> json) {
        if (obj.getPassword() != null) {
            json.put(FormLoginHandler.DEFAULT_PASSWORD_PARAM, obj.getPassword());
        }
        if (obj.getPath() != null) {
            json.put("path", obj.getPath());
        }
        if (obj.getValue() != null) {
            json.put("value", Base64.getEncoder().encodeToString(obj.getValue().getBytes()));
        }
    }
}
