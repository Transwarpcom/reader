package io.vertx.core.datagram;

import io.vertx.core.json.JsonObject;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/datagram/DatagramSocketOptionsConverter.class */
class DatagramSocketOptionsConverter {
    DatagramSocketOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, DatagramSocketOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "broadcast":
                    if (member.getValue() instanceof Boolean) {
                        obj.setBroadcast(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "ipV6":
                    if (member.getValue() instanceof Boolean) {
                        obj.setIpV6(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "loopbackModeDisabled":
                    if (member.getValue() instanceof Boolean) {
                        obj.setLoopbackModeDisabled(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "multicastNetworkInterface":
                    if (member.getValue() instanceof String) {
                        obj.setMulticastNetworkInterface((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "multicastTimeToLive":
                    if (member.getValue() instanceof Number) {
                        obj.setMulticastTimeToLive(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(DatagramSocketOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(DatagramSocketOptions obj, Map<String, Object> json) {
        json.put("broadcast", Boolean.valueOf(obj.isBroadcast()));
        json.put("ipV6", Boolean.valueOf(obj.isIpV6()));
        json.put("loopbackModeDisabled", Boolean.valueOf(obj.isLoopbackModeDisabled()));
        if (obj.getMulticastNetworkInterface() != null) {
            json.put("multicastNetworkInterface", obj.getMulticastNetworkInterface());
        }
        json.put("multicastTimeToLive", Integer.valueOf(obj.getMulticastTimeToLive()));
    }
}
