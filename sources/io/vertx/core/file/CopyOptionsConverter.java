package io.vertx.core.file;

import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/file/CopyOptionsConverter.class */
class CopyOptionsConverter {
    CopyOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, CopyOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "atomicMove":
                    if (member.getValue() instanceof Boolean) {
                        obj.setAtomicMove(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "copyAttributes":
                    if (member.getValue() instanceof Boolean) {
                        obj.setCopyAttributes(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "nofollowLinks":
                    if (member.getValue() instanceof Boolean) {
                        obj.setNofollowLinks(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "replaceExisting":
                    if (member.getValue() instanceof Boolean) {
                        obj.setReplaceExisting(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(CopyOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(CopyOptions obj, Map<String, Object> json) {
        json.put("atomicMove", Boolean.valueOf(obj.isAtomicMove()));
        json.put("copyAttributes", Boolean.valueOf(obj.isCopyAttributes()));
        json.put("nofollowLinks", Boolean.valueOf(obj.isNofollowLinks()));
        json.put("replaceExisting", Boolean.valueOf(obj.isReplaceExisting()));
    }
}
