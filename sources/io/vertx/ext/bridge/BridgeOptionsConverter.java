package io.vertx.ext.bridge;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-bridge-common-3.8.5.jar:io/vertx/ext/bridge/BridgeOptionsConverter.class */
public class BridgeOptionsConverter {
    public static void fromJson(Iterable<Map.Entry<String, Object>> json, BridgeOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "inboundPermitteds":
                    if (member.getValue() instanceof JsonArray) {
                        ArrayList<PermittedOptions> list = new ArrayList<>();
                        ((Iterable) member.getValue()).forEach(item -> {
                            if (item instanceof JsonObject) {
                                list.add(new PermittedOptions((JsonObject) item));
                            }
                        });
                        obj.setInboundPermitteds(list);
                        break;
                    } else {
                        break;
                    }
                case "outboundPermitteds":
                    if (member.getValue() instanceof JsonArray) {
                        ArrayList<PermittedOptions> list2 = new ArrayList<>();
                        ((Iterable) member.getValue()).forEach(item2 -> {
                            if (item2 instanceof JsonObject) {
                                list2.add(new PermittedOptions((JsonObject) item2));
                            }
                        });
                        obj.setOutboundPermitteds(list2);
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    public static void toJson(BridgeOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    public static void toJson(BridgeOptions obj, Map<String, Object> json) {
        if (obj.getInboundPermitteds() != null) {
            JsonArray array = new JsonArray();
            obj.getInboundPermitteds().forEach(item -> {
                array.add(item.toJson());
            });
            json.put("inboundPermitteds", array);
        }
        if (obj.getOutboundPermitteds() != null) {
            JsonArray array2 = new JsonArray();
            obj.getOutboundPermitteds().forEach(item2 -> {
                array2.add(item2.toJson());
            });
            json.put("outboundPermitteds", array2);
        }
    }
}
