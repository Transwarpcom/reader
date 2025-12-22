package io.vertx.core.metrics;

import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/metrics/MetricsOptionsConverter.class */
class MetricsOptionsConverter {
    MetricsOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, MetricsOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "enabled":
                    if (member.getValue() instanceof Boolean) {
                        obj.setEnabled(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(MetricsOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(MetricsOptions obj, Map<String, Object> json) {
        json.put("enabled", Boolean.valueOf(obj.isEnabled()));
    }
}
