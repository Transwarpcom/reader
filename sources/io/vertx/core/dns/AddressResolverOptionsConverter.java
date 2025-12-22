package io.vertx.core.dns;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/dns/AddressResolverOptionsConverter.class */
class AddressResolverOptionsConverter {
    AddressResolverOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, AddressResolverOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "cacheMaxTimeToLive":
                    if (member.getValue() instanceof Number) {
                        obj.setCacheMaxTimeToLive(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "cacheMinTimeToLive":
                    if (member.getValue() instanceof Number) {
                        obj.setCacheMinTimeToLive(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "cacheNegativeTimeToLive":
                    if (member.getValue() instanceof Number) {
                        obj.setCacheNegativeTimeToLive(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "hostsPath":
                    if (member.getValue() instanceof String) {
                        obj.setHostsPath((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "hostsValue":
                    if (member.getValue() instanceof String) {
                        obj.setHostsValue(Buffer.buffer(Base64.getDecoder().decode((String) member.getValue())));
                        break;
                    } else {
                        break;
                    }
                case "maxQueries":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxQueries(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "ndots":
                    if (member.getValue() instanceof Number) {
                        obj.setNdots(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "optResourceEnabled":
                    if (member.getValue() instanceof Boolean) {
                        obj.setOptResourceEnabled(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "queryTimeout":
                    if (member.getValue() instanceof Number) {
                        obj.setQueryTimeout(((Number) member.getValue()).longValue());
                        break;
                    } else {
                        break;
                    }
                case "rdFlag":
                    if (member.getValue() instanceof Boolean) {
                        obj.setRdFlag(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "rotateServers":
                    if (member.getValue() instanceof Boolean) {
                        obj.setRotateServers(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "searchDomains":
                    if (member.getValue() instanceof JsonArray) {
                        ArrayList<String> list = new ArrayList<>();
                        ((Iterable) member.getValue()).forEach(item -> {
                            if (item instanceof String) {
                                list.add((String) item);
                            }
                        });
                        obj.setSearchDomains(list);
                        break;
                    } else {
                        break;
                    }
                case "servers":
                    if (member.getValue() instanceof JsonArray) {
                        ArrayList<String> list2 = new ArrayList<>();
                        ((Iterable) member.getValue()).forEach(item2 -> {
                            if (item2 instanceof String) {
                                list2.add((String) item2);
                            }
                        });
                        obj.setServers(list2);
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(AddressResolverOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(AddressResolverOptions obj, Map<String, Object> json) {
        json.put("cacheMaxTimeToLive", Integer.valueOf(obj.getCacheMaxTimeToLive()));
        json.put("cacheMinTimeToLive", Integer.valueOf(obj.getCacheMinTimeToLive()));
        json.put("cacheNegativeTimeToLive", Integer.valueOf(obj.getCacheNegativeTimeToLive()));
        if (obj.getHostsPath() != null) {
            json.put("hostsPath", obj.getHostsPath());
        }
        if (obj.getHostsValue() != null) {
            json.put("hostsValue", Base64.getEncoder().encodeToString(obj.getHostsValue().getBytes()));
        }
        json.put("maxQueries", Integer.valueOf(obj.getMaxQueries()));
        json.put("ndots", Integer.valueOf(obj.getNdots()));
        json.put("optResourceEnabled", Boolean.valueOf(obj.isOptResourceEnabled()));
        json.put("queryTimeout", Long.valueOf(obj.getQueryTimeout()));
        json.put("rdFlag", Boolean.valueOf(obj.getRdFlag()));
        json.put("rotateServers", Boolean.valueOf(obj.isRotateServers()));
        if (obj.getSearchDomains() != null) {
            JsonArray array = new JsonArray();
            obj.getSearchDomains().forEach(item -> {
                array.add(item);
            });
            json.put("searchDomains", array);
        }
        if (obj.getServers() != null) {
            JsonArray array2 = new JsonArray();
            obj.getServers().forEach(item2 -> {
                array2.add(item2);
            });
            json.put("servers", array2);
        }
    }
}
