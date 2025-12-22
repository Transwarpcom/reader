package io.vertx.core.http;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/HttpServerOptionsConverter.class */
class HttpServerOptionsConverter {
    HttpServerOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, HttpServerOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
                case "acceptUnmaskedFrames":
                    if (member.getValue() instanceof Boolean) {
                        obj.setAcceptUnmaskedFrames(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "alpnVersions":
                    if (member.getValue() instanceof JsonArray) {
                        ArrayList<HttpVersion> list = new ArrayList<>();
                        ((Iterable) member.getValue()).forEach(item -> {
                            if (item instanceof String) {
                                list.add(HttpVersion.valueOf((String) item));
                            }
                        });
                        obj.setAlpnVersions(list);
                        break;
                    } else {
                        break;
                    }
                case "compressionLevel":
                    if (member.getValue() instanceof Number) {
                        obj.setCompressionLevel(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "compressionSupported":
                    if (member.getValue() instanceof Boolean) {
                        obj.setCompressionSupported(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "decoderInitialBufferSize":
                    if (member.getValue() instanceof Number) {
                        obj.setDecoderInitialBufferSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "decompressionSupported":
                    if (member.getValue() instanceof Boolean) {
                        obj.setDecompressionSupported(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "handle100ContinueAutomatically":
                    if (member.getValue() instanceof Boolean) {
                        obj.setHandle100ContinueAutomatically(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "http2ConnectionWindowSize":
                    if (member.getValue() instanceof Number) {
                        obj.setHttp2ConnectionWindowSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "initialSettings":
                    if (member.getValue() instanceof JsonObject) {
                        obj.setInitialSettings(new Http2Settings((JsonObject) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "maxChunkSize":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxChunkSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "maxHeaderSize":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxHeaderSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "maxInitialLineLength":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxInitialLineLength(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "maxWebSocketFrameSize":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxWebSocketFrameSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "maxWebSocketMessageSize":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxWebSocketMessageSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "maxWebsocketFrameSize":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxWebsocketFrameSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "maxWebsocketMessageSize":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxWebsocketMessageSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "perFrameWebSocketCompressionSupported":
                    if (member.getValue() instanceof Boolean) {
                        obj.setPerFrameWebSocketCompressionSupported(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "perFrameWebsocketCompressionSupported":
                    if (member.getValue() instanceof Boolean) {
                        obj.setPerFrameWebsocketCompressionSupported(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "perMessageWebSocketCompressionSupported":
                    if (member.getValue() instanceof Boolean) {
                        obj.setPerMessageWebSocketCompressionSupported(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "perMessageWebsocketCompressionSupported":
                    if (member.getValue() instanceof Boolean) {
                        obj.setPerMessageWebsocketCompressionSupported(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "webSocketAllowServerNoContext":
                    if (member.getValue() instanceof Boolean) {
                        obj.setWebSocketAllowServerNoContext(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "webSocketCompressionLevel":
                    if (member.getValue() instanceof Number) {
                        obj.setWebSocketCompressionLevel(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "webSocketPreferredClientNoContext":
                    if (member.getValue() instanceof Boolean) {
                        obj.setWebSocketPreferredClientNoContext(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "webSocketSubProtocols":
                    if (member.getValue() instanceof JsonArray) {
                        ArrayList<String> list2 = new ArrayList<>();
                        ((Iterable) member.getValue()).forEach(item2 -> {
                            if (item2 instanceof String) {
                                list2.add((String) item2);
                            }
                        });
                        obj.setWebSocketSubProtocols(list2);
                        break;
                    } else {
                        break;
                    }
                case "websocketAllowServerNoContext":
                    if (member.getValue() instanceof Boolean) {
                        obj.setWebsocketAllowServerNoContext(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "websocketCompressionLevel":
                    if (member.getValue() instanceof Number) {
                        obj.setWebsocketCompressionLevel(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "websocketPreferredClientNoContext":
                    if (member.getValue() instanceof Boolean) {
                        obj.setWebsocketPreferredClientNoContext(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "websocketSubProtocols":
                    if (member.getValue() instanceof String) {
                        obj.setWebsocketSubProtocols((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(HttpServerOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(HttpServerOptions obj, Map<String, Object> json) {
        json.put("acceptUnmaskedFrames", Boolean.valueOf(obj.isAcceptUnmaskedFrames()));
        if (obj.getAlpnVersions() != null) {
            JsonArray array = new JsonArray();
            obj.getAlpnVersions().forEach(item -> {
                array.add(item.name());
            });
            json.put("alpnVersions", array);
        }
        json.put("compressionLevel", Integer.valueOf(obj.getCompressionLevel()));
        json.put("compressionSupported", Boolean.valueOf(obj.isCompressionSupported()));
        json.put("decoderInitialBufferSize", Integer.valueOf(obj.getDecoderInitialBufferSize()));
        json.put("decompressionSupported", Boolean.valueOf(obj.isDecompressionSupported()));
        json.put("handle100ContinueAutomatically", Boolean.valueOf(obj.isHandle100ContinueAutomatically()));
        json.put("http2ConnectionWindowSize", Integer.valueOf(obj.getHttp2ConnectionWindowSize()));
        if (obj.getInitialSettings() != null) {
            json.put("initialSettings", obj.getInitialSettings().toJson());
        }
        json.put("maxChunkSize", Integer.valueOf(obj.getMaxChunkSize()));
        json.put("maxHeaderSize", Integer.valueOf(obj.getMaxHeaderSize()));
        json.put("maxInitialLineLength", Integer.valueOf(obj.getMaxInitialLineLength()));
        json.put("maxWebSocketFrameSize", Integer.valueOf(obj.getMaxWebSocketFrameSize()));
        json.put("maxWebSocketMessageSize", Integer.valueOf(obj.getMaxWebSocketMessageSize()));
        json.put("maxWebsocketFrameSize", Integer.valueOf(obj.getMaxWebsocketFrameSize()));
        json.put("maxWebsocketMessageSize", Integer.valueOf(obj.getMaxWebsocketMessageSize()));
        json.put("perFrameWebSocketCompressionSupported", Boolean.valueOf(obj.getPerFrameWebSocketCompressionSupported()));
        json.put("perFrameWebsocketCompressionSupported", Boolean.valueOf(obj.getPerFrameWebsocketCompressionSupported()));
        json.put("perMessageWebSocketCompressionSupported", Boolean.valueOf(obj.getPerMessageWebSocketCompressionSupported()));
        json.put("perMessageWebsocketCompressionSupported", Boolean.valueOf(obj.getPerMessageWebsocketCompressionSupported()));
        json.put("webSocketAllowServerNoContext", Boolean.valueOf(obj.getWebSocketAllowServerNoContext()));
        json.put("webSocketCompressionLevel", Integer.valueOf(obj.getWebSocketCompressionLevel()));
        json.put("webSocketPreferredClientNoContext", Boolean.valueOf(obj.getWebSocketPreferredClientNoContext()));
        if (obj.getWebSocketSubProtocols() != null) {
            JsonArray array2 = new JsonArray();
            obj.getWebSocketSubProtocols().forEach(item2 -> {
                array2.add(item2);
            });
            json.put("webSocketSubProtocols", array2);
        }
        json.put("websocketAllowServerNoContext", Boolean.valueOf(obj.getWebsocketAllowServerNoContext()));
        json.put("websocketCompressionLevel", Integer.valueOf(obj.getWebsocketCompressionLevel()));
        json.put("websocketPreferredClientNoContext", Boolean.valueOf(obj.getWebsocketPreferredClientNoContext()));
        if (obj.getWebsocketSubProtocols() != null) {
            json.put("websocketSubProtocols", obj.getWebsocketSubProtocols());
        }
    }
}
