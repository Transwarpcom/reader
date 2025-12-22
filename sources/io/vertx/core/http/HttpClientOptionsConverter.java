package io.vertx.core.http;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/HttpClientOptionsConverter.class */
class HttpClientOptionsConverter {
    HttpClientOptionsConverter() {
    }

    static void fromJson(Iterable<Map.Entry<String, Object>> json, HttpClientOptions obj) {
        for (Map.Entry<String, Object> member : json) {
            switch (member.getKey()) {
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
                case "decoderInitialBufferSize":
                    if (member.getValue() instanceof Number) {
                        obj.setDecoderInitialBufferSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "defaultHost":
                    if (member.getValue() instanceof String) {
                        obj.setDefaultHost((String) member.getValue());
                        break;
                    } else {
                        break;
                    }
                case "defaultPort":
                    if (member.getValue() instanceof Number) {
                        obj.setDefaultPort(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "forceSni":
                    if (member.getValue() instanceof Boolean) {
                        obj.setForceSni(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "http2ClearTextUpgrade":
                    if (member.getValue() instanceof Boolean) {
                        obj.setHttp2ClearTextUpgrade(((Boolean) member.getValue()).booleanValue());
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
                case "http2KeepAliveTimeout":
                    if (member.getValue() instanceof Number) {
                        obj.setHttp2KeepAliveTimeout(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "http2MaxPoolSize":
                    if (member.getValue() instanceof Number) {
                        obj.setHttp2MaxPoolSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "http2MultiplexingLimit":
                    if (member.getValue() instanceof Number) {
                        obj.setHttp2MultiplexingLimit(((Number) member.getValue()).intValue());
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
                case "keepAlive":
                    if (member.getValue() instanceof Boolean) {
                        obj.setKeepAlive(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "keepAliveTimeout":
                    if (member.getValue() instanceof Number) {
                        obj.setKeepAliveTimeout(((Number) member.getValue()).intValue());
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
                case "maxPoolSize":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxPoolSize(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "maxRedirects":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxRedirects(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "maxWaitQueueSize":
                    if (member.getValue() instanceof Number) {
                        obj.setMaxWaitQueueSize(((Number) member.getValue()).intValue());
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
                case "pipelining":
                    if (member.getValue() instanceof Boolean) {
                        obj.setPipelining(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "pipeliningLimit":
                    if (member.getValue() instanceof Number) {
                        obj.setPipeliningLimit(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "poolCleanerPeriod":
                    if (member.getValue() instanceof Number) {
                        obj.setPoolCleanerPeriod(((Number) member.getValue()).intValue());
                        break;
                    } else {
                        break;
                    }
                case "protocolVersion":
                    if (member.getValue() instanceof String) {
                        obj.setProtocolVersion(HttpVersion.valueOf((String) member.getValue()));
                        break;
                    } else {
                        break;
                    }
                case "sendUnmaskedFrames":
                    if (member.getValue() instanceof Boolean) {
                        obj.setSendUnmaskedFrames(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "tryUseCompression":
                    if (member.getValue() instanceof Boolean) {
                        obj.setTryUseCompression(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "tryUsePerFrameWebSocketCompression":
                    if (member.getValue() instanceof Boolean) {
                        obj.setTryUsePerFrameWebSocketCompression(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "tryUsePerFrameWebsocketCompression":
                    if (member.getValue() instanceof Boolean) {
                        obj.setTryUsePerFrameWebsocketCompression(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "tryUsePerMessageWebSocketCompression":
                    if (member.getValue() instanceof Boolean) {
                        obj.setTryUsePerMessageWebSocketCompression(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "tryUsePerMessageWebsocketCompression":
                    if (member.getValue() instanceof Boolean) {
                        obj.setTryUsePerMessageWebsocketCompression(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "verifyHost":
                    if (member.getValue() instanceof Boolean) {
                        obj.setVerifyHost(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "webSocketCompressionAllowClientNoContext":
                    if (member.getValue() instanceof Boolean) {
                        obj.setWebSocketCompressionAllowClientNoContext(((Boolean) member.getValue()).booleanValue());
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
                case "webSocketCompressionRequestServerNoContext":
                    if (member.getValue() instanceof Boolean) {
                        obj.setWebSocketCompressionRequestServerNoContext(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case "websocketCompressionAllowClientNoContext":
                    if (member.getValue() instanceof Boolean) {
                        obj.setWebsocketCompressionAllowClientNoContext(((Boolean) member.getValue()).booleanValue());
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
                case "websocketCompressionRequestServerNoContext":
                    if (member.getValue() instanceof Boolean) {
                        obj.setWebsocketCompressionRequestServerNoContext(((Boolean) member.getValue()).booleanValue());
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    static void toJson(HttpClientOptions obj, JsonObject json) {
        toJson(obj, json.getMap());
    }

    static void toJson(HttpClientOptions obj, Map<String, Object> json) {
        if (obj.getAlpnVersions() != null) {
            JsonArray array = new JsonArray();
            obj.getAlpnVersions().forEach(item -> {
                array.add(item.name());
            });
            json.put("alpnVersions", array);
        }
        json.put("decoderInitialBufferSize", Integer.valueOf(obj.getDecoderInitialBufferSize()));
        if (obj.getDefaultHost() != null) {
            json.put("defaultHost", obj.getDefaultHost());
        }
        json.put("defaultPort", Integer.valueOf(obj.getDefaultPort()));
        json.put("forceSni", Boolean.valueOf(obj.isForceSni()));
        json.put("http2ClearTextUpgrade", Boolean.valueOf(obj.isHttp2ClearTextUpgrade()));
        json.put("http2ConnectionWindowSize", Integer.valueOf(obj.getHttp2ConnectionWindowSize()));
        json.put("http2KeepAliveTimeout", Integer.valueOf(obj.getHttp2KeepAliveTimeout()));
        json.put("http2MaxPoolSize", Integer.valueOf(obj.getHttp2MaxPoolSize()));
        json.put("http2MultiplexingLimit", Integer.valueOf(obj.getHttp2MultiplexingLimit()));
        if (obj.getInitialSettings() != null) {
            json.put("initialSettings", obj.getInitialSettings().toJson());
        }
        json.put("keepAlive", Boolean.valueOf(obj.isKeepAlive()));
        json.put("keepAliveTimeout", Integer.valueOf(obj.getKeepAliveTimeout()));
        json.put("maxChunkSize", Integer.valueOf(obj.getMaxChunkSize()));
        json.put("maxHeaderSize", Integer.valueOf(obj.getMaxHeaderSize()));
        json.put("maxInitialLineLength", Integer.valueOf(obj.getMaxInitialLineLength()));
        json.put("maxPoolSize", Integer.valueOf(obj.getMaxPoolSize()));
        json.put("maxRedirects", Integer.valueOf(obj.getMaxRedirects()));
        json.put("maxWaitQueueSize", Integer.valueOf(obj.getMaxWaitQueueSize()));
        json.put("maxWebSocketFrameSize", Integer.valueOf(obj.getMaxWebSocketFrameSize()));
        json.put("maxWebSocketMessageSize", Integer.valueOf(obj.getMaxWebSocketMessageSize()));
        json.put("maxWebsocketFrameSize", Integer.valueOf(obj.getMaxWebsocketFrameSize()));
        json.put("maxWebsocketMessageSize", Integer.valueOf(obj.getMaxWebsocketMessageSize()));
        json.put("pipelining", Boolean.valueOf(obj.isPipelining()));
        json.put("pipeliningLimit", Integer.valueOf(obj.getPipeliningLimit()));
        json.put("poolCleanerPeriod", Integer.valueOf(obj.getPoolCleanerPeriod()));
        if (obj.getProtocolVersion() != null) {
            json.put("protocolVersion", obj.getProtocolVersion().name());
        }
        json.put("sendUnmaskedFrames", Boolean.valueOf(obj.isSendUnmaskedFrames()));
        json.put("tryUseCompression", Boolean.valueOf(obj.isTryUseCompression()));
        json.put("tryUsePerMessageWebSocketCompression", Boolean.valueOf(obj.getTryUsePerMessageWebSocketCompression()));
        json.put("tryUsePerMessageWebsocketCompression", Boolean.valueOf(obj.getTryUsePerMessageWebsocketCompression()));
        json.put("tryWebSocketDeflateFrameCompression", Boolean.valueOf(obj.getTryWebSocketDeflateFrameCompression()));
        json.put("tryWebsocketDeflateFrameCompression", Boolean.valueOf(obj.getTryWebsocketDeflateFrameCompression()));
        json.put("verifyHost", Boolean.valueOf(obj.isVerifyHost()));
        json.put("webSocketCompressionAllowClientNoContext", Boolean.valueOf(obj.getWebSocketCompressionAllowClientNoContext()));
        json.put("webSocketCompressionLevel", Integer.valueOf(obj.getWebSocketCompressionLevel()));
        json.put("webSocketCompressionRequestServerNoContext", Boolean.valueOf(obj.getWebSocketCompressionRequestServerNoContext()));
        json.put("websocketCompressionAllowClientNoContext", Boolean.valueOf(obj.getWebsocketCompressionAllowClientNoContext()));
        json.put("websocketCompressionLevel", Integer.valueOf(obj.getWebsocketCompressionLevel()));
        json.put("websocketCompressionRequestServerNoContext", Boolean.valueOf(obj.getWebsocketCompressionRequestServerNoContext()));
    }
}
