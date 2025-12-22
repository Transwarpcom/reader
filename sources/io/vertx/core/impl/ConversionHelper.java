package io.vertx.core.impl;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/ConversionHelper.class */
public class ConversionHelper {
    public static Object toObject(Object obj) {
        if (obj instanceof Map) {
            return toJsonObject((Map) obj);
        }
        if (obj instanceof List) {
            return toJsonArray((List) obj);
        }
        if (obj instanceof CharSequence) {
            return obj.toString();
        }
        return obj;
    }

    private static Object toJsonElement(Object obj) {
        if (obj instanceof Map) {
            return toJsonObject((Map) obj);
        }
        if (obj instanceof List) {
            return toJsonArray((List) obj);
        }
        if (obj instanceof CharSequence) {
            return obj.toString();
        }
        if (obj instanceof Buffer) {
            return Base64.getEncoder().encodeToString(((Buffer) obj).getBytes());
        }
        return obj;
    }

    public static JsonObject toJsonObject(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        Map<String, Object> map2 = new LinkedHashMap<>(map);
        map2.entrySet().forEach(e -> {
            e.setValue(toJsonElement(e.getValue()));
        });
        return new JsonObject(map2);
    }

    public static JsonArray toJsonArray(List<Object> list) {
        if (list == null) {
            return null;
        }
        List<Object> list2 = new ArrayList<>(list);
        for (int i = 0; i < list2.size(); i++) {
            list2.set(i, toJsonElement(list2.get(i)));
        }
        return new JsonArray(list2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T fromObject(Object obj) {
        if (obj instanceof JsonObject) {
            return (T) fromJsonObject((JsonObject) obj);
        }
        if (obj instanceof JsonArray) {
            return (T) fromJsonArray((JsonArray) obj);
        }
        return obj;
    }

    public static Map<String, Object> fromJsonObject(JsonObject json) {
        if (json == null) {
            return null;
        }
        Map<String, Object> map = new LinkedHashMap<>(json.getMap());
        map.entrySet().forEach(entry -> {
            entry.setValue(fromObject(entry.getValue()));
        });
        return map;
    }

    public static List<Object> fromJsonArray(JsonArray json) {
        if (json == null) {
            return null;
        }
        List<Object> list = new ArrayList<>(json.getList());
        for (int i = 0; i < list.size(); i++) {
            list.set(i, fromObject(list.get(i)));
        }
        return list;
    }
}
