package io.vertx.core.cli;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.LinkedHashSet;
import java.util.Map;
import org.springframework.validation.DefaultBindingErrorProcessor;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/OptionConverter.class */
class OptionConverter {
    OptionConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, Option obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "argName":
                    if (member.getValue() instanceof String) {
                        obj.setArgName((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "choices":
                    if (member.getValue() instanceof JsonArray) {
                        LinkedHashSet<String> list = new LinkedHashSet<>();
                        ((Iterable) member.getValue()).forEach(item -> {
                            if (item instanceof String) {
                                list.add((String) item);
                            }
                        });
                        obj.setChoices(list);
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
                case "flag":
                    if (member.getValue() instanceof Boolean) {
                        obj.setFlag(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "help":
                    if (member.getValue() instanceof Boolean) {
                        obj.setHelp(((Boolean) member.getValue()).booleanValue());
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
                case "longName":
                    if (member.getValue() instanceof String) {
                        obj.setLongName((String) member.getValue());
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
                case "shortName":
                    if (member.getValue() instanceof String) {
                        obj.setShortName((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "singleValued":
                    if (member.getValue() instanceof Boolean) {
                        obj.setSingleValued(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(Option obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(Option obj, Map<String, Object> json) {
        if (obj.getArgName() != null) {
            json.put("argName", obj.getArgName());
        }
        if (obj.getChoices() != null) {
            JsonArray array = new JsonArray();
            obj.getChoices().forEach(item -> {
                array.add(item);
            });
            json.put("choices", array);
        }
        if (obj.getDefaultValue() != null) {
            json.put("defaultValue", obj.getDefaultValue());
        }
        if (obj.getDescription() != null) {
            json.put("description", obj.getDescription());
        }
        json.put("flag", Boolean.valueOf(obj.isFlag()));
        json.put("help", Boolean.valueOf(obj.isHelp()));
        json.put("hidden", Boolean.valueOf(obj.isHidden()));
        if (obj.getLongName() != null) {
            json.put("longName", obj.getLongName());
        }
        json.put("multiValued", Boolean.valueOf(obj.isMultiValued()));
        if (obj.getName() != null) {
            json.put("name", obj.getName());
        }
        json.put(DefaultBindingErrorProcessor.MISSING_FIELD_ERROR_CODE, Boolean.valueOf(obj.isRequired()));
        if (obj.getShortName() != null) {
            json.put("shortName", obj.getShortName());
        }
        json.put("singleValued", Boolean.valueOf(obj.isSingleValued()));
    }
}
