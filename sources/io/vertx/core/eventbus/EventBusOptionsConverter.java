package io.vertx.core.eventbus;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/EventBusOptionsConverter.class */
class EventBusOptionsConverter {
    EventBusOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, EventBusOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "acceptBacklog":
                    if (member.getValue() instanceof Number) {
                        obj.setAcceptBacklog(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "clientAuth":
                    if (member.getValue() instanceof String) {
                        obj.setClientAuth(ClientAuth.valueOf((String) member.getValue()));
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
                case "connectTimeout":
                    if (member.getValue() instanceof Number) {
                        obj.setConnectTimeout(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "crlPaths":
                    if (member.getValue() instanceof JsonArray) {
                        ((Iterable) member.getValue()).forEach(item -> {
                            if (item instanceof String) {
                                obj.addCrlPath((String) item);
                            }
                        });
                        break;
                    } else {
                        break;
                    }
                case "crlValues":
                    if (member.getValue() instanceof JsonArray) {
                        ((Iterable) member.getValue()).forEach(item2 -> {
                            if (item2 instanceof String) {
                                obj.addCrlValue(Buffer.buffer(Base64.getDecoder().decode((String) item2)));
                            }
                        });
                        break;
                    } else {
                        break;
                    }
                case "enabledCipherSuites":
                    if (member.getValue() instanceof JsonArray) {
                        ((Iterable) member.getValue()).forEach(item3 -> {
                            if (item3 instanceof String) {
                                obj.addEnabledCipherSuite((String) item3);
                            }
                        });
                        break;
                    } else {
                        break;
                    }
                case "enabledSecureTransportProtocols":
                    if (member.getValue() instanceof JsonArray) {
                        LinkedHashSet<String> list = new LinkedHashSet<>();
                        ((Iterable) member.getValue()).forEach(item4 -> {
                            if (item4 instanceof String) {
                                list.add((String) item4);
                            }
                        });
                        obj.setEnabledSecureTransportProtocols((Set<String>) list);
                        break;
                    } else {
                        break;
                    }
                case "host":
                    if (member.getValue() instanceof String) {
                        obj.setHost((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "idleTimeout":
                    if (member.getValue() instanceof Number) {
                        obj.setIdleTimeout(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "idleTimeoutUnit":
                    if (member.getValue() instanceof String) {
                        obj.setIdleTimeoutUnit(TimeUnit.valueOf((String) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "jdkSslEngineOptions":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setJdkSslEngineOptions(new JdkSSLEngineOptions((JsonObject) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "keyStoreOptions":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setKeyStoreOptions(new JksOptions((JsonObject) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "logActivity":
                    if (member.getValue() instanceof Boolean) {
                        obj.setLogActivity(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "openSslEngineOptions":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setOpenSslEngineOptions(new OpenSSLEngineOptions((JsonObject) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "pemKeyCertOptions":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setPemKeyCertOptions(new PemKeyCertOptions((JsonObject) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "pemTrustOptions":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setPemTrustOptions(new PemTrustOptions((JsonObject) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "pfxKeyCertOptions":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setPfxKeyCertOptions(new PfxOptions((JsonObject) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "pfxTrustOptions":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setPfxTrustOptions(new PfxOptions((JsonObject) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "port":
                    if (member.getValue() instanceof Number) {
                        obj.setPort(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "receiveBufferSize":
                    if (member.getValue() instanceof Number) {
                        obj.setReceiveBufferSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "reconnectAttempts":
                    if (member.getValue() instanceof Number) {
                        obj.setReconnectAttempts(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "reconnectInterval":
                    if (member.getValue() instanceof Number) {
                        obj.setReconnectInterval(((Number) member.getValue()).longValue());
                        break;
                    } else {
                        break;
                    }
                case "reuseAddress":
                    if (member.getValue() instanceof Boolean) {
                        obj.setReuseAddress(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "reusePort":
                    if (member.getValue() instanceof Boolean) {
                        obj.setReusePort(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "sendBufferSize":
                    if (member.getValue() instanceof Number) {
                        obj.setSendBufferSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "soLinger":
                    if (member.getValue() instanceof Number) {
                        obj.setSoLinger(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "ssl":
                    if (member.getValue() instanceof Boolean) {
                        obj.setSsl(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "sslHandshakeTimeout":
                    if (member.getValue() instanceof Number) {
                        obj.setSslHandshakeTimeout(((Number) member.getValue()).longValue());
                        break;
                    } else {
                        break;
                    }
                case "sslHandshakeTimeoutUnit":
                    if (member.getValue() instanceof String) {
                        obj.setSslHandshakeTimeoutUnit(TimeUnit.valueOf((String) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "tcpCork":
                    if (member.getValue() instanceof Boolean) {
                        obj.setTcpCork(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "tcpFastOpen":
                    if (member.getValue() instanceof Boolean) {
                        obj.setTcpFastOpen(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "tcpKeepAlive":
                    if (member.getValue() instanceof Boolean) {
                        obj.setTcpKeepAlive(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "tcpNoDelay":
                    if (member.getValue() instanceof Boolean) {
                        obj.setTcpNoDelay(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "tcpQuickAck":
                    if (member.getValue() instanceof Boolean) {
                        obj.setTcpQuickAck(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "trafficClass":
                    if (member.getValue() instanceof Number) {
                        obj.setTrafficClass(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "trustAll":
                    if (member.getValue() instanceof Boolean) {
                        obj.setTrustAll(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "trustStoreOptions":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setTrustStoreOptions(new JksOptions((JsonObject) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "useAlpn":
                    if (member.getValue() instanceof Boolean) {
                        obj.setUseAlpn(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "usePooledBuffers":
                    if (member.getValue() instanceof Boolean) {
                        obj.setUsePooledBuffers(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(EventBusOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(EventBusOptions obj, Map<String, Object> json) {
        json.put("acceptBacklog", Integer.valueOf(obj.getAcceptBacklog()));
        if (obj.getClientAuth() != null) {
            json.put("clientAuth", obj.getClientAuth().name());
        }
        json.put("clusterPingInterval", Long.valueOf(obj.getClusterPingInterval()));
        json.put("clusterPingReplyInterval", Long.valueOf(obj.getClusterPingReplyInterval()));
        if (obj.getClusterPublicHost() != null) {
            json.put("clusterPublicHost", obj.getClusterPublicHost());
        }
        json.put("clusterPublicPort", Integer.valueOf(obj.getClusterPublicPort()));
        json.put("clustered", Boolean.valueOf(obj.isClustered()));
        json.put("connectTimeout", Integer.valueOf(obj.getConnectTimeout()));
        if (obj.getCrlPaths() != null) {
            JsonArray array = new JsonArray();
            obj.getCrlPaths().forEach(item -> {
                array.add(item);
            });
            json.put("crlPaths", array);
        }
        if (obj.getCrlValues() != null) {
            JsonArray array2 = new JsonArray();
            obj.getCrlValues().forEach(item2 -> {
                array2.add(Base64.getEncoder().encodeToString(item2.getBytes()));
            });
            json.put("crlValues", array2);
        }
        if (obj.getEnabledCipherSuites() != null) {
            JsonArray array3 = new JsonArray();
            obj.getEnabledCipherSuites().forEach(item3 -> {
                array3.add(item3);
            });
            json.put("enabledCipherSuites", array3);
        }
        if (obj.getEnabledSecureTransportProtocols() != null) {
            JsonArray array4 = new JsonArray();
            obj.getEnabledSecureTransportProtocols().forEach(item4 -> {
                array4.add(item4);
            });
            json.put("enabledSecureTransportProtocols", array4);
        }
        if (obj.getHost() != null) {
            json.put("host", obj.getHost());
        }
        json.put("idleTimeout", Integer.valueOf(obj.getIdleTimeout()));
        if (obj.getIdleTimeoutUnit() != null) {
            json.put("idleTimeoutUnit", obj.getIdleTimeoutUnit().name());
        }
        if (obj.getJdkSslEngineOptions() != null) {
            json.put("jdkSslEngineOptions", obj.getJdkSslEngineOptions().toJson());
        }
        if (obj.getKeyStoreOptions() != null) {
            json.put("keyStoreOptions", obj.getKeyStoreOptions().toJson());
        }
        json.put("logActivity", Boolean.valueOf(obj.getLogActivity()));
        if (obj.getOpenSslEngineOptions() != null) {
            json.put("openSslEngineOptions", obj.getOpenSslEngineOptions().toJson());
        }
        if (obj.getPemKeyCertOptions() != null) {
            json.put("pemKeyCertOptions", obj.getPemKeyCertOptions().toJson());
        }
        if (obj.getPemTrustOptions() != null) {
            json.put("pemTrustOptions", obj.getPemTrustOptions().toJson());
        }
        if (obj.getPfxKeyCertOptions() != null) {
            json.put("pfxKeyCertOptions", obj.getPfxKeyCertOptions().toJson());
        }
        if (obj.getPfxTrustOptions() != null) {
            json.put("pfxTrustOptions", obj.getPfxTrustOptions().toJson());
        }
        json.put(RtspHeaders.Values.PORT, Integer.valueOf(obj.getPort()));
        json.put("receiveBufferSize", Integer.valueOf(obj.getReceiveBufferSize()));
        json.put("reconnectAttempts", Integer.valueOf(obj.getReconnectAttempts()));
        json.put("reconnectInterval", Long.valueOf(obj.getReconnectInterval()));
        json.put("reuseAddress", Boolean.valueOf(obj.isReuseAddress()));
        json.put("reusePort", Boolean.valueOf(obj.isReusePort()));
        json.put("sendBufferSize", Integer.valueOf(obj.getSendBufferSize()));
        json.put("soLinger", Integer.valueOf(obj.getSoLinger()));
        json.put("ssl", Boolean.valueOf(obj.isSsl()));
        json.put("sslHandshakeTimeout", Long.valueOf(obj.getSslHandshakeTimeout()));
        if (obj.getSslHandshakeTimeoutUnit() != null) {
            json.put("sslHandshakeTimeoutUnit", obj.getSslHandshakeTimeoutUnit().name());
        }
        json.put("tcpCork", Boolean.valueOf(obj.isTcpCork()));
        json.put("tcpFastOpen", Boolean.valueOf(obj.isTcpFastOpen()));
        json.put("tcpKeepAlive", Boolean.valueOf(obj.isTcpKeepAlive()));
        json.put("tcpNoDelay", Boolean.valueOf(obj.isTcpNoDelay()));
        json.put("tcpQuickAck", Boolean.valueOf(obj.isTcpQuickAck()));
        json.put("trafficClass", Integer.valueOf(obj.getTrafficClass()));
        json.put("trustAll", Boolean.valueOf(obj.isTrustAll()));
        if (obj.getTrustStoreOptions() != null) {
            json.put("trustStoreOptions", obj.getTrustStoreOptions().toJson());
        }
        json.put("useAlpn", Boolean.valueOf(obj.isUseAlpn()));
        json.put("usePooledBuffers", Boolean.valueOf(obj.isUsePooledBuffers()));
    }
}
