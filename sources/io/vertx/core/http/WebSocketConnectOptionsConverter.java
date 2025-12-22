package io.vertx.core.http;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/WebSocketConnectOptionsConverter.class */
public class WebSocketConnectOptionsConverter {
    public static void fromJson(Iterable<Map.Entry<String, Object>> json, WebSocketConnectOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "subProtocols":
                    if (member.getValue() instanceof JsonArray) {
                        ArrayList<String> list = new ArrayList<>();
                        ((Iterable) member.getValue()).forEach(item -> {
                            if (item instanceof String) {
                                list.add((String) item);
                            }
                        });
                        obj.setSubProtocols(list);
                        break;
                    } else {
                        break;
                    }
                case "version":
                    if (member.getValue() instanceof String) {
                        obj.setVersion(WebsocketVersion.valueOf((String) member.getValue()));
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    public static void toJson(WebSocketConnectOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    public static void toJson(WebSocketConnectOptions obj, Map<String, Object> json) {
        if (obj.getSubProtocols() != null) {
            JsonArray array = new JsonArray();
            obj.getSubProtocols().forEach(item -> {
                array.add(item);
            });
            json.put("subProtocols", array);
        }
        if (obj.getVersion() != null) {
            json.put("version", obj.getVersion().name());
        }
    }
}
