package io.vertx.core.http.impl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientUpgradeHandler;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http2.Http2CodecUtil;
import io.netty.util.CharsetUtil;
import io.netty.util.collection.CharObjectMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Http2Settings;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/VertxHttp2ClientUpgradeCodec.class */
public class VertxHttp2ClientUpgradeCodec implements HttpClientUpgradeHandler.UpgradeCodec {
    private static final List<CharSequence> UPGRADE_HEADERS = Collections.singletonList(Http2CodecUtil.HTTP_UPGRADE_SETTINGS_HEADER);
    private final Http2Settings settings;

    public VertxHttp2ClientUpgradeCodec(Http2Settings settings) {
        this.settings = settings;
    }

    @Override // io.netty.handler.codec.http.HttpClientUpgradeHandler.UpgradeCodec
    public CharSequence protocol() {
        return "h2c";
    }

    @Override // io.netty.handler.codec.http.HttpClientUpgradeHandler.UpgradeCodec
    public Collection<CharSequence> setUpgradeHeaders(ChannelHandlerContext ctx, HttpRequest upgradeRequest) {
        io.netty.handler.codec.http2.Http2Settings nettySettings = new io.netty.handler.codec.http2.Http2Settings();
        HttpUtils.fromVertxInitialSettings(false, this.settings, nettySettings);
        Buffer buf = Buffer.buffer();
        for (CharObjectMap.PrimitiveEntry<Long> entry : nettySettings.entries()) {
            buf.appendUnsignedShort(entry.key());
            buf.appendUnsignedInt(entry.value().longValue());
        }
        String encodedSettings = new String(Base64.getUrlEncoder().encode(buf.getBytes()), CharsetUtil.UTF_8);
        upgradeRequest.headers().set(Http2CodecUtil.HTTP_UPGRADE_SETTINGS_HEADER, encodedSettings);
        return UPGRADE_HEADERS;
    }

    @Override // io.netty.handler.codec.http.HttpClientUpgradeHandler.UpgradeCodec
    public void upgradeTo(ChannelHandlerContext ctx, FullHttpResponse upgradeResponse) throws Exception {
    }
}
