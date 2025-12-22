package io.vertx.core.net;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/PemKeyCertOptionsConverter.class */
class PemKeyCertOptionsConverter {
    PemKeyCertOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, PemKeyCertOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "certPath":
                    if (member.getValue() instanceof String) {
                        obj.setCertPath((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "certPaths":
                    if (member.getValue() instanceof JsonArray) {
                        ArrayList<String> list = new ArrayList<>();
                        ((Iterable) member.getValue()).forEach(item -> {
                            if (item instanceof String) {
                                list.add((String) item);
                            }
                        });
                        obj.setCertPaths(list);
                        break;
                    } else {
                        break;
                    }
                case "certValue":
                    if (member.getValue() instanceof String) {
                        obj.setCertValue(Buffer.buffer(Base64.getDecoder().decode((String) member.getValue())));
                        break;
                    } else {
                        break;
                    }
                case "certValues":
                    if (member.getValue() instanceof JsonArray) {
                        ArrayList<Buffer> list2 = new ArrayList<>();
                        ((Iterable) member.getValue()).forEach(item2 -> {
                            if (item2 instanceof String) {
                                list2.add(Buffer.buffer(Base64.getDecoder().decode((String) item2)));
                            }
                        });
                        obj.setCertValues(list2);
                        break;
                    } else {
                        break;
                    }
                case "keyPath":
                    if (member.getValue() instanceof String) {
                        obj.setKeyPath((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "keyPaths":
                    if (member.getValue() instanceof JsonArray) {
                        ArrayList<String> list3 = new ArrayList<>();
                        ((Iterable) member.getValue()).forEach(item3 -> {
                            if (item3 instanceof String) {
                                list3.add((String) item3);
                            }
                        });
                        obj.setKeyPaths(list3);
                        break;
                    } else {
                        break;
                    }
                case "keyValue":
                    if (member.getValue() instanceof String) {
                        obj.setKeyValue(Buffer.buffer(Base64.getDecoder().decode((String) member.getValue())));
                        break;
                    } else {
                        break;
                    }
                case "keyValues":
                    if (member.getValue() instanceof JsonArray) {
                        ArrayList<Buffer> list4 = new ArrayList<>();
                        ((Iterable) member.getValue()).forEach(item4 -> {
                            if (item4 instanceof String) {
                                list4.add(Buffer.buffer(Base64.getDecoder().decode((String) item4)));
                            }
                        });
                        obj.setKeyValues(list4);
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(PemKeyCertOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(PemKeyCertOptions obj, Map<String, Object> json) {
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
        if (obj.getKeyPaths() != null) {
            JsonArray array3 = new JsonArray();
            obj.getKeyPaths().forEach(item3 -> {
                array3.add(item3);
            });
            json.put("keyPaths", array3);
        }
        if (obj.getKeyValues() != null) {
            JsonArray array4 = new JsonArray();
            obj.getKeyValues().forEach(item4 -> {
                array4.add(Base64.getEncoder().encodeToString(item4.getBytes()));
            });
            json.put("keyValues", array4);
        }
    }
}
