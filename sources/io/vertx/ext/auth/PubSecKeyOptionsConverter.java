package io.vertx.ext.auth;

import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/PubSecKeyOptionsConverter.class */
public class PubSecKeyOptionsConverter {
    public static void fromJson(Iterable<Map.Entry<String, Object>> json, PubSecKeyOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "algorithm":
                    if (member.getValue() instanceof String) {
                        obj.setAlgorithm((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "certificate":
                    if (member.getValue() instanceof Boolean) {
                        obj.setCertificate(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "publicKey":
                    if (member.getValue() instanceof String) {
                        obj.setPublicKey((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "secretKey":
                    if (member.getValue() instanceof String) {
                        obj.setSecretKey((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "symmetric":
                    if (member.getValue() instanceof Boolean) {
                        obj.setSymmetric(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    public static void toJson(PubSecKeyOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    public static void toJson(PubSecKeyOptions obj, Map<String, Object> json) {
        if (obj.getAlgorithm() != null) {
            json.put("algorithm", obj.getAlgorithm());
        }
        json.put("certificate", Boolean.valueOf(obj.isCertificate()));
        if (obj.getPublicKey() != null) {
            json.put("publicKey", obj.getPublicKey());
        }
        if (obj.getSecretKey() != null) {
            json.put("secretKey", obj.getSecretKey());
        }
        json.put("symmetric", Boolean.valueOf(obj.isSymmetric()));
    }
}
