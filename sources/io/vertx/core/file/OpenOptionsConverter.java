package io.vertx.core.file;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/file/OpenOptionsConverter.class */
class OpenOptionsConverter {
    OpenOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, OpenOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "append":
                    if (member.getValue() instanceof Boolean) {
                        obj.setAppend(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "create":
                    if (member.getValue() instanceof Boolean) {
                        obj.setCreate(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "createNew":
                    if (member.getValue() instanceof Boolean) {
                        obj.setCreateNew(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "deleteOnClose":
                    if (member.getValue() instanceof Boolean) {
                        obj.setDeleteOnClose(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "dsync":
                    if (member.getValue() instanceof Boolean) {
                        obj.setDsync(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "perms":
                    if (member.getValue() instanceof String) {
                        obj.setPerms((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "read":
                    if (member.getValue() instanceof Boolean) {
                        obj.setRead(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "sparse":
                    if (member.getValue() instanceof Boolean) {
                        obj.setSparse(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "sync":
                    if (member.getValue() instanceof Boolean) {
                        obj.setSync(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "truncateExisting":
                    if (member.getValue() instanceof Boolean) {
                        obj.setTruncateExisting(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "write":
                    if (member.getValue() instanceof Boolean) {
                        obj.setWrite(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(OpenOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(OpenOptions obj, Map<String, Object> json) {
        json.put(RtspHeaders.Values.APPEND, Boolean.valueOf(obj.isAppend()));
        json.put("create", Boolean.valueOf(obj.isCreate()));
        json.put("createNew", Boolean.valueOf(obj.isCreateNew()));
        json.put("deleteOnClose", Boolean.valueOf(obj.isDeleteOnClose()));
        json.put("dsync", Boolean.valueOf(obj.isDsync()));
        if (obj.getPerms() != null) {
            json.put("perms", obj.getPerms());
        }
        json.put("read", Boolean.valueOf(obj.isRead()));
        json.put("sparse", Boolean.valueOf(obj.isSparse()));
        json.put("sync", Boolean.valueOf(obj.isSync()));
        json.put("truncateExisting", Boolean.valueOf(obj.isTruncateExisting()));
        json.put("write", Boolean.valueOf(obj.isWrite()));
    }
}
