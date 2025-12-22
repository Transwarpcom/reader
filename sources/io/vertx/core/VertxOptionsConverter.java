package io.vertx.core;

import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.file.FileSystemOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.metrics.MetricsOptions;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/VertxOptionsConverter.class */
class VertxOptionsConverter {
    VertxOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, VertxOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "addressResolverOptions":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setAddressResolverOptions(new AddressResolverOptions((JsonObject) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "blockedThreadCheckInterval":
                    if (member.getValue() instanceof Number) {
                        obj.setBlockedThreadCheckInterval(((Number) member.getValue()).longValue());
                        break;
                    } else {
                        break;
                    }
                case "blockedThreadCheckIntervalUnit":
                    if (member.getValue() instanceof String) {
                        obj.setBlockedThreadCheckIntervalUnit(TimeUnit.valueOf((String) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "clusterHost":
                    if (member.getValue() instanceof String) {
                        obj.setClusterHost((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "clusterPingInterval":
                    if (member.getValue() instanceof Number) {
                        obj.setClusterPingInterval(((Number) member.getValue()).longValue());
                        break;
                    } else {
                        break;
                    }
                case "clusterPingReplyInterval":
                    if (member.getValue() instanceof Number) {
                        obj.setClusterPingReplyInterval(((Number) member.getValue()).longValue());
                        break;
                    } else {
                        break;
                    }
                case "clusterPort":
                    if (member.getValue() instanceof Number) {
                        obj.setClusterPort(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "clusterPublicHost":
                    if (member.getValue() instanceof String) {
                        obj.setClusterPublicHost((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "clusterPublicPort":
                    if (member.getValue() instanceof Number) {
                        obj.setClusterPublicPort(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "clustered":
                    if (member.getValue() instanceof Boolean) {
                        obj.setClustered(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "eventBusOptions":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setEventBusOptions(new EventBusOptions((JsonObject) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "eventLoopPoolSize":
                    if (member.getValue() instanceof Number) {
                        obj.setEventLoopPoolSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "fileResolverCachingEnabled":
                    if (member.getValue() instanceof Boolean) {
                        obj.setFileResolverCachingEnabled(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "fileSystemOptions":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setFileSystemOptions(new FileSystemOptions((JsonObject) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "haEnabled":
                    if (member.getValue() instanceof Boolean) {
                        obj.setHAEnabled(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "haGroup":
                    if (member.getValue() instanceof String) {
                        obj.setHAGroup((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "internalBlockingPoolSize":
                    if (member.getValue() instanceof Number) {
                        obj.setInternalBlockingPoolSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "maxEventLoopExecuteTime":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxEventLoopExecuteTime(((Number) member.getValue()).longValue());
                        break;
                    } else {
                        break;
                    }
                case "maxEventLoopExecuteTimeUnit":
                    if (member.getValue() instanceof String) {
                        obj.setMaxEventLoopExecuteTimeUnit(TimeUnit.valueOf((String) member.getValue()));
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
                case "metricsOptions":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setMetricsOptions(new MetricsOptions((JsonObject) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "preferNativeTransport":
                    if (member.getValue() instanceof Boolean) {
                        obj.setPreferNativeTransport(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "quorumSize":
                    if (member.getValue() instanceof Number) {
                        obj.setQuorumSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "warningExceptionTime":
                    if (member.getValue() instanceof Number) {
                        obj.setWarningExceptionTime(((Number) member.getValue()).longValue());
                        break;
                    } else {
                        break;
                    }
                case "warningExceptionTimeUnit":
                    if (member.getValue() instanceof String) {
                        obj.setWarningExceptionTimeUnit(TimeUnit.valueOf((String) member.getValue()));
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

    static void toJson(VertxOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(VertxOptions obj, Map<String, Object> json) {
        if (obj.getAddressResolverOptions() != null) {
            json.put("addressResolverOptions", obj.getAddressResolverOptions().toJson());
        }
        json.put("blockedThreadCheckInterval", Long.valueOf(obj.getBlockedThreadCheckInterval()));
        if (obj.getBlockedThreadCheckIntervalUnit() != null) {
            json.put("blockedThreadCheckIntervalUnit", obj.getBlockedThreadCheckIntervalUnit().name());
        }
        if (obj.getClusterHost() != null) {
            json.put("clusterHost", obj.getClusterHost());
        }
        json.put("clusterPingInterval", Long.valueOf(obj.getClusterPingInterval()));
        json.put("clusterPingReplyInterval", Long.valueOf(obj.getClusterPingReplyInterval()));
        json.put("clusterPort", Integer.valueOf(obj.getClusterPort()));
        if (obj.getClusterPublicHost() != null) {
            json.put("clusterPublicHost", obj.getClusterPublicHost());
        }
        json.put("clusterPublicPort", Integer.valueOf(obj.getClusterPublicPort()));
        json.put("clustered", Boolean.valueOf(obj.isClustered()));
        if (obj.getEventBusOptions() != null) {
            json.put("eventBusOptions", obj.getEventBusOptions().toJson());
        }
        json.put("eventLoopPoolSize", Integer.valueOf(obj.getEventLoopPoolSize()));
        json.put("fileResolverCachingEnabled", Boolean.valueOf(obj.isFileResolverCachingEnabled()));
        if (obj.getFileSystemOptions() != null) {
            json.put("fileSystemOptions", obj.getFileSystemOptions().toJson());
        }
        json.put("haEnabled", Boolean.valueOf(obj.isHAEnabled()));
        if (obj.getHAGroup() != null) {
            json.put("haGroup", obj.getHAGroup());
        }
        json.put("internalBlockingPoolSize", Integer.valueOf(obj.getInternalBlockingPoolSize()));
        json.put("maxEventLoopExecuteTime", Long.valueOf(obj.getMaxEventLoopExecuteTime()));
        if (obj.getMaxEventLoopExecuteTimeUnit() != null) {
            json.put("maxEventLoopExecuteTimeUnit", obj.getMaxEventLoopExecuteTimeUnit().name());
        }
        json.put("maxWorkerExecuteTime", Long.valueOf(obj.getMaxWorkerExecuteTime()));
        if (obj.getMaxWorkerExecuteTimeUnit() != null) {
            json.put("maxWorkerExecuteTimeUnit", obj.getMaxWorkerExecuteTimeUnit().name());
        }
        if (obj.getMetricsOptions() != null) {
            json.put("metricsOptions", obj.getMetricsOptions().toJson());
        }
        json.put("preferNativeTransport", Boolean.valueOf(obj.getPreferNativeTransport()));
        json.put("quorumSize", Integer.valueOf(obj.getQuorumSize()));
        json.put("warningExceptionTime", Long.valueOf(obj.getWarningExceptionTime()));
        if (obj.getWarningExceptionTimeUnit() != null) {
            json.put("warningExceptionTimeUnit", obj.getWarningExceptionTimeUnit().name());
        }
        json.put("workerPoolSize", Integer.valueOf(obj.getWorkerPoolSize()));
    }
}
