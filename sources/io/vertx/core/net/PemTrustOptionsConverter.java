package io.vertx.core.net;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.Base64;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/PemTrustOptionsConverter.class */
class PemTrustOptionsConverter {
    PemTrustOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, PemTrustOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "certPaths":
                    if (member.getValue() instanceof JsonArray) {
                        ((Iterable) member.getValue()).forEach(item -> {
                            if (item instanceof String) {
                                obj.addCertPath((String) item);
                            }
                        });
                        break;
                    } else {
                        break;
                    }
                case "certValues":
                    if (member.getValue() instanceof JsonArray) {
                        ((Iterable) member.getValue()).forEach(item2 -> {
                            if (item2 instanceof String) {
                                obj.addCertValue(Buffer.buffer(Base64.getDecoder().decode((String) item2)));
                            }
                        });
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(PemTrustOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(PemTrustOptions obj, Map<String, Object> json) {
        if (obj.getCertPaths() != null) {
            JsonArray array = new JsonArray();
            obj.getCertPaths().forEach(item -> {
                array.add(item);
            });
            json.put("certPaths", array);
        }
        if (obj.getCertValues() != null) {
            JsonArray array2 = new JsonArray();
            obj.getCertValues().forEach(item2 -> {
                array2.add(Base64.getEncoder().encodeToString(item2.getBytes()));
            });
            json.put("certValues", array2);
        }
    }
}
