package io.vertx.core.cli;

import io.vertx.core.json.JsonObject;
import java.util.Map;
import org.springframework.validation.DefaultBindingErrorProcessor;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/ArgumentConverter.class */
class ArgumentConverter {
    ArgumentConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, Argument obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "argName":
                    if (member.getValue() instanceof String) {
                        obj.setArgName((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "defaultValue":
                    if (member.getValue() instanceof String) {
                        obj.setDefaultValue((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "description":
                    if (member.getValue() instanceof String) {
                        obj.setDescription((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "hidden":
                    if (member.getValue() instanceof Boolean) {
                        obj.setHidden(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "index":
                    if (member.getValue() instanceof Number) {
                        obj.setIndex(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "multiValued":
                    if (member.getValue() instanceof Boolean) {
                        obj.setMultiValued(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "required":
                    if (member.getValue() instanceof Boolean) {
                        obj.setRequired(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(Argument obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(Argument obj, Map<String, Object> json) {
        if (obj.getArgName() != null) {
            json.put("argName", obj.getArgName());
        }
        if (obj.getDefaultValue() != null) {
            json.put("defaultValue", obj.getDefaultValue());
        }
        if (obj.getDescription() != null) {
            json.put("description", obj.getDescription());
        }
        json.put("hidden", Boolean.valueOf(obj.isHidden()));
        json.put("index", Integer.valueOf(obj.getIndex()));
        json.put("multiValued", Boolean.valueOf(obj.isMultiValued()));
        json.put(DefaultBindingErrorProcessor.MISSING_FIELD_ERROR_CODE, Boolean.valueOf(obj.isRequired()));
    }
}
