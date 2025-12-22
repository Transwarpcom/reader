package io.vertx.ext.bridge;

import io.vertx.core.json.JsonObject;
import java.util.Map;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-bridge-common-3.8.5.jar:io/vertx/ext/bridge/PermittedOptionsConverter.class */
public class PermittedOptionsConverter {
    public static void fromJson(Iterable<Map.Entry<String, Object>> json, PermittedOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "address":
                    if (member.getValue() instanceof String) {
                        obj.setAddress((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "addressRegex":
                    if (member.getValue() instanceof String) {
                        obj.setAddressRegex((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "match":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setMatch(((JsonObject) member.getValue()).copy());
                        break;
                    } else {
                        break;
                    }
                case "requiredAuthority":
                    if (member.getValue() instanceof String) {
                        obj.setRequiredAuthority((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    public static void toJson(PermittedOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    public static void toJson(PermittedOptions obj, Map<String, Object> json) {
        if (obj.getAddress() != null) {
            json.put("address", obj.getAddress());
        }
        if (obj.getAddressRegex() != null) {
            json.put("addressRegex", obj.getAddressRegex());
        }
        if (obj.getMatch() != null) {
            json.put(BeanDefinitionParserDelegate.ARG_TYPE_MATCH_ATTRIBUTE, obj.getMatch());
        }
        if (obj.getRequiredAuthority() != null) {
            json.put("requiredAuthority", obj.getRequiredAuthority());
        }
    }
}
