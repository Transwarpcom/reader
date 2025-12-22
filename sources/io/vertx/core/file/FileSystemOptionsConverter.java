package io.vertx.core.file;

import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/file/FileSystemOptionsConverter.class */
class FileSystemOptionsConverter {
    FileSystemOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, FileSystemOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "classPathResolvingEnabled":
                    if (member.getValue() instanceof Boolean) {
                        obj.setClassPathResolvingEnabled(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "fileCacheDir":
                    if (member.getValue() instanceof String) {
                        obj.setFileCacheDir((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "fileCachingEnabled":
                    if (member.getValue() instanceof Boolean) {
                        obj.setFileCachingEnabled(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(FileSystemOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(FileSystemOptions obj, Map<String, Object> json) {
        json.put("classPathResolvingEnabled", Boolean.valueOf(obj.isClassPathResolvingEnabled()));
        if (obj.getFileCacheDir() != null) {
            json.put("fileCacheDir", obj.getFileCacheDir());
        }
        json.put("fileCachingEnabled", Boolean.valueOf(obj.isFileCachingEnabled()));
    }
}
