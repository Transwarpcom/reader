package io.vertx.core.http;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import java.util.Base64;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/GoAwayConverter.class */
class GoAwayConverter {
    GoAwayConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, GoAway obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "debugData":
                    if (member.getValue() instanceof String) {
                        obj.setDebugData(Buffer.buffer(Base64.getDecoder().decode((String) member.getValue())));
                        break;
                    } else {
                        break;
                    }
                case "errorCode":
                    if (member.getValue() instanceof Number) {
                        obj.setErrorCode(((Number) member.getValue()).longValue());
                        break;
                    } else {
                        break;
                    }
                case "lastStreamId":
                    if (member.getValue() instanceof Number) {
                        obj.setLastStreamId(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(GoAway obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(GoAway obj, Map<String, Object> json) {
        if (obj.getDebugData() != null) {
            json.put("debugData", Base64.getEncoder().encodeToString(obj.getDebugData().getBytes()));
        }
        json.put("errorCode", Long.valueOf(obj.getErrorCode()));
        json.put("lastStreamId", Integer.valueOf(obj.getLastStreamId()));
    }
}
