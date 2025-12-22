package io.vertx.core.net;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/TCPSSLOptionsConverter.class */
class TCPSSLOptionsConverter {
    TCPSSLOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, TCPSSLOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
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
                        obj.setEnabledSecureTransportProtocols(list);
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

    static void toJson(TCPSSLOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(TCPSSLOptions obj, Map<String, Object> json) {
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
        if (obj.getTrustStoreOptions() != null) {
            json.put("trustStoreOptions", obj.getTrustStoreOptions().toJson());
        }
        json.put("useAlpn", Boolean.valueOf(obj.isUseAlpn()));
        json.put("usePooledBuffers", Boolean.valueOf(obj.isUsePooledBuffers()));
    }
}
