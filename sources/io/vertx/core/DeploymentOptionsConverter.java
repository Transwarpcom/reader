package io.vertx.core;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/DeploymentOptionsConverter.class */
class DeploymentOptionsConverter {
    DeploymentOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, DeploymentOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "config":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setConfig(((JsonObject) member.getValue()).copy());
                        break;
                    } else {
                        break;
                    }
                case "extraClasspath":
                    if (member.getValue() instanceof JsonArray) {
                        ArrayList<String> list = new ArrayList<>();
                        ((Iterable) member.getValue()).forEach(item -> {
                            if (item instanceof String) {
                                list.add((String) item);
                            }
                        });
                        obj.setExtraClasspath(list);
                        break;
                    } else {
                        break;
                    }
                case "ha":
                    if (member.getValue() instanceof Boolean) {
                        obj.setHa(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "instances":
                    if (member.getValue() instanceof Number) {
                        obj.setInstances(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "isolatedClasses":
                    if (member.getValue() instanceof JsonArray) {
                        ArrayList<String> list2 = new ArrayList<>();
                        ((Iterable) member.getValue()).forEach(item2 -> {
                            if (item2 instanceof String) {
                                list2.add((String) item2);
                            }
                        });
                        obj.setIsolatedClasses(list2);
                        break;
                    } else {
                        break;
                    }
                case "isolationGroup":
                    if (member.getValue() instanceof String) {
                        obj.setIsolationGroup((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "maxWorkerExecuteTime":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxWorkerExecuteTime(((Number) member.getValue()).longValue());
                        break;
                    } else {
                        break;
                    }
                case "maxWorkerExecuteTimeUnit":
                    if (member.getValue() instanceof String) {
                        obj.setMaxWorkerExecuteTimeUnit(TimeUnit.valueOf((String) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "multiThreaded":
                    if (member.getValue() instanceof Boolean) {
                        obj.setMultiThreaded(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "worker":
                    if (member.getValue() instanceof Boolean) {
                        obj.setWorker(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "workerPoolName":
                    if (member.getValue() instanceof String) {
                        obj.setWorkerPoolName((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "workerPoolSize":
                    if (member.getValue() instanceof Number) {
                        obj.setWorkerPoolSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(DeploymentOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(DeploymentOptions obj, Map<String, Object> json) {
        if (obj.getConfig() != null) {
            json.put("config", obj.getConfig());
        }
        if (obj.getExtraClasspath() != null) {
            JsonArray array = new JsonArray();
            obj.getExtraClasspath().forEach(item -> {
                array.add(item);
            });
            json.put("extraClasspath", array);
        }
        json.put("ha", Boolean.valueOf(obj.isHa()));
        json.put("instances", Integer.valueOf(obj.getInstances()));
        if (obj.getIsolatedClasses() != null) {
            JsonArray array2 = new JsonArray();
            obj.getIsolatedClasses().forEach(item2 -> {
                array2.add(item2);
            });
            json.put("isolatedClasses", array2);
        }
        if (obj.getIsolationGroup() != null) {
            json.put("isolationGroup", obj.getIsolationGroup());
        }
        json.put("maxWorkerExecuteTime", Long.valueOf(obj.getMaxWorkerExecuteTime()));
        if (obj.getMaxWorkerExecuteTimeUnit() != null) {
            json.put("maxWorkerExecuteTimeUnit", obj.getMaxWorkerExecuteTimeUnit().name());
        }
        json.put("multiThreaded", Boolean.valueOf(obj.isMultiThreaded()));
        json.put("worker", Boolean.valueOf(obj.isWorker()));
        if (obj.getWorkerPoolName() != null) {
            json.put("workerPoolName", obj.getWorkerPoolName());
        }
        json.put("workerPoolSize", Integer.valueOf(obj.getWorkerPoolSize()));
    }
}
