package io.vertx.kotlin.core.http;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import io.vertx.core.net.ProxyOptions;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;

/* compiled from: HttpClientOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��t\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u001a\u001a»\u0007\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00032\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00032\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010/\u001a\u0004\u0018\u0001002\n\b\u0002\u00101\u001a\u0004\u0018\u0001022\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\n\b\u0002\u00107\u001a\u0004\u0018\u0001062\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010<\u001a\u0004\u0018\u00010=2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010E\u001a\u0004\u0018\u00010F2\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010K\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010L\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010O\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010T\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010U\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010V\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010W\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010X\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010]\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010^\u001a¹\u0007\u0010_\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00032\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00032\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010/\u001a\u0004\u0018\u0001002\n\b\u0002\u00101\u001a\u0004\u0018\u0001022\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\n\b\u0002\u00107\u001a\u0004\u0018\u0001062\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010<\u001a\u0004\u0018\u00010=2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010E\u001a\u0004\u0018\u00010F2\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010K\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010L\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010O\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010T\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010U\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010V\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010W\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010X\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010]\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010^¨\u0006`"}, d2 = {"HttpClientOptions", "Lio/vertx/core/http/HttpClientOptions;", "alpnVersions", "", "Lio/vertx/core/http/HttpVersion;", "connectTimeout", "", "crlPaths", "", "crlValues", "Lio/vertx/core/buffer/Buffer;", "decoderInitialBufferSize", "defaultHost", "defaultPort", "enabledCipherSuites", "enabledSecureTransportProtocols", "forceSni", "", "http2ClearTextUpgrade", "http2ConnectionWindowSize", "http2KeepAliveTimeout", "http2MaxPoolSize", "http2MultiplexingLimit", "idleTimeout", "idleTimeoutUnit", "Ljava/util/concurrent/TimeUnit;", "initialSettings", "Lio/vertx/core/http/Http2Settings;", "jdkSslEngineOptions", "Lio/vertx/core/net/JdkSSLEngineOptions;", "keepAlive", "keepAliveTimeout", "keyStoreOptions", "Lio/vertx/core/net/JksOptions;", "localAddress", "logActivity", "maxChunkSize", "maxHeaderSize", "maxInitialLineLength", "maxPoolSize", "maxRedirects", "maxWaitQueueSize", "maxWebSocketFrameSize", "maxWebSocketMessageSize", "maxWebsocketFrameSize", "maxWebsocketMessageSize", "metricsName", "openSslEngineOptions", "Lio/vertx/core/net/OpenSSLEngineOptions;", "pemKeyCertOptions", "Lio/vertx/core/net/PemKeyCertOptions;", "pemTrustOptions", "Lio/vertx/core/net/PemTrustOptions;", "pfxKeyCertOptions", "Lio/vertx/core/net/PfxOptions;", "pfxTrustOptions", "pipelining", "pipeliningLimit", "poolCleanerPeriod", "protocolVersion", "proxyOptions", "Lio/vertx/core/net/ProxyOptions;", "receiveBufferSize", "reuseAddress", "reusePort", "sendBufferSize", "sendUnmaskedFrames", "soLinger", "ssl", "sslHandshakeTimeout", "", "sslHandshakeTimeoutUnit", "tcpCork", "tcpFastOpen", "tcpKeepAlive", "tcpNoDelay", "tcpQuickAck", "trafficClass", "trustAll", "trustStoreOptions", "tryUseCompression", "tryUsePerFrameWebSocketCompression", "tryUsePerFrameWebsocketCompression", "tryUsePerMessageWebSocketCompression", "tryUsePerMessageWebsocketCompression", "useAlpn", "usePooledBuffers", "verifyHost", "webSocketCompressionAllowClientNoContext", "webSocketCompressionLevel", "webSocketCompressionRequestServerNoContext", "websocketCompressionAllowClientNoContext", "websocketCompressionLevel", "websocketCompressionRequestServerNoContext", "(Ljava/lang/Iterable;Ljava/lang/Integer;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/concurrent/TimeUnit;Lio/vertx/core/http/Http2Settings;Lio/vertx/core/net/JdkSSLEngineOptions;Ljava/lang/Boolean;Ljava/lang/Integer;Lio/vertx/core/net/JksOptions;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lio/vertx/core/net/OpenSSLEngineOptions;Lio/vertx/core/net/PemKeyCertOptions;Lio/vertx/core/net/PemTrustOptions;Lio/vertx/core/net/PfxOptions;Lio/vertx/core/net/PfxOptions;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lio/vertx/core/http/HttpVersion;Lio/vertx/core/net/ProxyOptions;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Lio/vertx/core/net/JksOptions;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;)Lio/vertx/core/http/HttpClientOptions;", "httpClientOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/http/HttpClientOptionsKt.class */
public final class HttpClientOptionsKt {
    @NotNull
    public static /* synthetic */ HttpClientOptions httpClientOptionsOf$default(Iterable iterable, Integer num, Iterable iterable2, Iterable iterable3, Integer num2, String str, Integer num3, Iterable iterable4, Iterable iterable5, Boolean bool, Boolean bool2, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, TimeUnit timeUnit, Http2Settings http2Settings, JdkSSLEngineOptions jdkSSLEngineOptions, Boolean bool3, Integer num9, JksOptions jksOptions, String str2, Boolean bool4, Integer num10, Integer num11, Integer num12, Integer num13, Integer num14, Integer num15, Integer num16, Integer num17, Integer num18, Integer num19, String str3, OpenSSLEngineOptions openSSLEngineOptions, PemKeyCertOptions pemKeyCertOptions, PemTrustOptions pemTrustOptions, PfxOptions pfxOptions, PfxOptions pfxOptions2, Boolean bool5, Integer num20, Integer num21, HttpVersion httpVersion, ProxyOptions proxyOptions, Integer num22, Boolean bool6, Boolean bool7, Integer num23, Boolean bool8, Integer num24, Boolean bool9, Long l, TimeUnit timeUnit2, Boolean bool10, Boolean bool11, Boolean bool12, Boolean bool13, Boolean bool14, Integer num25, Boolean bool15, JksOptions jksOptions2, Boolean bool16, Boolean bool17, Boolean bool18, Boolean bool19, Boolean bool20, Boolean bool21, Boolean bool22, Boolean bool23, Boolean bool24, Integer num26, Boolean bool25, Boolean bool26, Integer num27, Boolean bool27, int i, int i2, int i3, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            num = (Integer) null;
        }
        if ((i & 4) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 8) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 16) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 32) != 0) {
            str = (String) null;
        }
        if ((i & 64) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 128) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 256) != 0) {
            iterable5 = (Iterable) null;
        }
        if ((i & 512) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 1024) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 2048) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 4096) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 8192) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 16384) != 0) {
            num7 = (Integer) null;
        }
        if ((i & 32768) != 0) {
            num8 = (Integer) null;
        }
        if ((i & 65536) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 131072) != 0) {
            http2Settings = (Http2Settings) null;
        }
        if ((i & 262144) != 0) {
            jdkSSLEngineOptions = (JdkSSLEngineOptions) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 1048576) != 0) {
            num9 = (Integer) null;
        }
        if ((i & 2097152) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i & 4194304) != 0) {
            str2 = (String) null;
        }
        if ((i & 8388608) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 16777216) != 0) {
            num10 = (Integer) null;
        }
        if ((i & 33554432) != 0) {
            num11 = (Integer) null;
        }
        if ((i & 67108864) != 0) {
            num12 = (Integer) null;
        }
        if ((i & 134217728) != 0) {
            num13 = (Integer) null;
        }
        if ((i & 268435456) != 0) {
            num14 = (Integer) null;
        }
        if ((i & 536870912) != 0) {
            num15 = (Integer) null;
        }
        if ((i & 1073741824) != 0) {
            num16 = (Integer) null;
        }
        if ((i & Integer.MIN_VALUE) != 0) {
            num17 = (Integer) null;
        }
        if ((i2 & 1) != 0) {
            num18 = (Integer) null;
        }
        if ((i2 & 2) != 0) {
            num19 = (Integer) null;
        }
        if ((i2 & 4) != 0) {
            str3 = (String) null;
        }
        if ((i2 & 8) != 0) {
            openSSLEngineOptions = (OpenSSLEngineOptions) null;
        }
        if ((i2 & 16) != 0) {
            pemKeyCertOptions = (PemKeyCertOptions) null;
        }
        if ((i2 & 32) != 0) {
            pemTrustOptions = (PemTrustOptions) null;
        }
        if ((i2 & 64) != 0) {
            pfxOptions = (PfxOptions) null;
        }
        if ((i2 & 128) != 0) {
            pfxOptions2 = (PfxOptions) null;
        }
        if ((i2 & 256) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i2 & 512) != 0) {
            num20 = (Integer) null;
        }
        if ((i2 & 1024) != 0) {
            num21 = (Integer) null;
        }
        if ((i2 & 2048) != 0) {
            httpVersion = (HttpVersion) null;
        }
        if ((i2 & 4096) != 0) {
            proxyOptions = (ProxyOptions) null;
        }
        if ((i2 & 8192) != 0) {
            num22 = (Integer) null;
        }
        if ((i2 & 16384) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i2 & 32768) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i2 & 65536) != 0) {
            num23 = (Integer) null;
        }
        if ((i2 & 131072) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i2 & 262144) != 0) {
            num24 = (Integer) null;
        }
        if ((i2 & Opcodes.ASM8) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i2 & 1048576) != 0) {
            l = (Long) null;
        }
        if ((i2 & 2097152) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i2 & 4194304) != 0) {
            bool10 = (Boolean) null;
        }
        if ((i2 & 8388608) != 0) {
            bool11 = (Boolean) null;
        }
        if ((i2 & 16777216) != 0) {
            bool12 = (Boolean) null;
        }
        if ((i2 & 33554432) != 0) {
            bool13 = (Boolean) null;
        }
        if ((i2 & 67108864) != 0) {
            bool14 = (Boolean) null;
        }
        if ((i2 & 134217728) != 0) {
            num25 = (Integer) null;
        }
        if ((i2 & 268435456) != 0) {
            bool15 = (Boolean) null;
        }
        if ((i2 & 536870912) != 0) {
            jksOptions2 = (JksOptions) null;
        }
        if ((i2 & 1073741824) != 0) {
            bool16 = (Boolean) null;
        }
        if ((i2 & Integer.MIN_VALUE) != 0) {
            bool17 = (Boolean) null;
        }
        if ((i3 & 1) != 0) {
            bool18 = (Boolean) null;
        }
        if ((i3 & 2) != 0) {
            bool19 = (Boolean) null;
        }
        if ((i3 & 4) != 0) {
            bool20 = (Boolean) null;
        }
        if ((i3 & 8) != 0) {
            bool21 = (Boolean) null;
        }
        if ((i3 & 16) != 0) {
            bool22 = (Boolean) null;
        }
        if ((i3 & 32) != 0) {
            bool23 = (Boolean) null;
        }
        if ((i3 & 64) != 0) {
            bool24 = (Boolean) null;
        }
        if ((i3 & 128) != 0) {
            num26 = (Integer) null;
        }
        if ((i3 & 256) != 0) {
            bool25 = (Boolean) null;
        }
        if ((i3 & 512) != 0) {
            bool26 = (Boolean) null;
        }
        if ((i3 & 1024) != 0) {
            num27 = (Integer) null;
        }
        if ((i3 & 2048) != 0) {
            bool27 = (Boolean) null;
        }
        return httpClientOptionsOf(iterable, num, iterable2, iterable3, num2, str, num3, iterable4, iterable5, bool, bool2, num4, num5, num6, num7, num8, timeUnit, http2Settings, jdkSSLEngineOptions, bool3, num9, jksOptions, str2, bool4, num10, num11, num12, num13, num14, num15, num16, num17, num18, num19, str3, openSSLEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxOptions, pfxOptions2, bool5, num20, num21, httpVersion, proxyOptions, num22, bool6, bool7, num23, bool8, num24, bool9, l, timeUnit2, bool10, bool11, bool12, bool13, bool14, num25, bool15, jksOptions2, bool16, bool17, bool18, bool19, bool20, bool21, bool22, bool23, bool24, num26, bool25, bool26, num27, bool27);
    }

    @NotNull
    public static final HttpClientOptions httpClientOptionsOf(@Nullable Iterable<? extends HttpVersion> iterable, @Nullable Integer connectTimeout, @Nullable Iterable<String> iterable2, @Nullable Iterable<? extends Buffer> iterable3, @Nullable Integer decoderInitialBufferSize, @Nullable String defaultHost, @Nullable Integer defaultPort, @Nullable Iterable<String> iterable4, @Nullable Iterable<String> iterable5, @Nullable Boolean forceSni, @Nullable Boolean http2ClearTextUpgrade, @Nullable Integer http2ConnectionWindowSize, @Nullable Integer http2KeepAliveTimeout, @Nullable Integer http2MaxPoolSize, @Nullable Integer http2MultiplexingLimit, @Nullable Integer idleTimeout, @Nullable TimeUnit idleTimeoutUnit, @Nullable Http2Settings initialSettings, @Nullable JdkSSLEngineOptions jdkSslEngineOptions, @Nullable Boolean keepAlive, @Nullable Integer keepAliveTimeout, @Nullable JksOptions keyStoreOptions, @Nullable String localAddress, @Nullable Boolean logActivity, @Nullable Integer maxChunkSize, @Nullable Integer maxHeaderSize, @Nullable Integer maxInitialLineLength, @Nullable Integer maxPoolSize, @Nullable Integer maxRedirects, @Nullable Integer maxWaitQueueSize, @Nullable Integer maxWebSocketFrameSize, @Nullable Integer maxWebSocketMessageSize, @Nullable Integer maxWebsocketFrameSize, @Nullable Integer maxWebsocketMessageSize, @Nullable String metricsName, @Nullable OpenSSLEngineOptions openSslEngineOptions, @Nullable PemKeyCertOptions pemKeyCertOptions, @Nullable PemTrustOptions pemTrustOptions, @Nullable PfxOptions pfxKeyCertOptions, @Nullable PfxOptions pfxTrustOptions, @Nullable Boolean pipelining, @Nullable Integer pipeliningLimit, @Nullable Integer poolCleanerPeriod, @Nullable HttpVersion protocolVersion, @Nullable ProxyOptions proxyOptions, @Nullable Integer receiveBufferSize, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable Integer sendBufferSize, @Nullable Boolean sendUnmaskedFrames, @Nullable Integer soLinger, @Nullable Boolean ssl, @Nullable Long sslHandshakeTimeout, @Nullable TimeUnit sslHandshakeTimeoutUnit, @Nullable Boolean tcpCork, @Nullable Boolean tcpFastOpen, @Nullable Boolean tcpKeepAlive, @Nullable Boolean tcpNoDelay, @Nullable Boolean tcpQuickAck, @Nullable Integer trafficClass, @Nullable Boolean trustAll, @Nullable JksOptions trustStoreOptions, @Nullable Boolean tryUseCompression, @Nullable Boolean tryUsePerFrameWebSocketCompression, @Nullable Boolean tryUsePerFrameWebsocketCompression, @Nullable Boolean tryUsePerMessageWebSocketCompression, @Nullable Boolean tryUsePerMessageWebsocketCompression, @Nullable Boolean useAlpn, @Nullable Boolean usePooledBuffers, @Nullable Boolean verifyHost, @Nullable Boolean webSocketCompressionAllowClientNoContext, @Nullable Integer webSocketCompressionLevel, @Nullable Boolean webSocketCompressionRequestServerNoContext, @Nullable Boolean websocketCompressionAllowClientNoContext, @Nullable Integer websocketCompressionLevel, @Nullable Boolean websocketCompressionRequestServerNoContext) throws NullPointerException {
        HttpClientOptions $this$apply = new HttpClientOptions();
        if (iterable != null) {
            $this$apply.setAlpnVersions(CollectionsKt.toList(iterable));
        }
        if (connectTimeout != null) {
            $this$apply.setConnectTimeout(connectTimeout.intValue());
        }
        if (iterable2 != null) {
            for (String item : iterable2) {
                $this$apply.addCrlPath(item);
            }
        }
        if (iterable3 != null) {
            for (Buffer item2 : iterable3) {
                $this$apply.addCrlValue(item2);
            }
        }
        if (decoderInitialBufferSize != null) {
            $this$apply.setDecoderInitialBufferSize(decoderInitialBufferSize.intValue());
        }
        if (defaultHost != null) {
            $this$apply.setDefaultHost(defaultHost);
        }
        if (defaultPort != null) {
            $this$apply.setDefaultPort(defaultPort.intValue());
        }
        if (iterable4 != null) {
            for (String item3 : iterable4) {
                $this$apply.addEnabledCipherSuite(item3);
            }
        }
        if (iterable5 != null) {
            $this$apply.setEnabledSecureTransportProtocols(CollectionsKt.toSet(iterable5));
        }
        if (forceSni != null) {
            $this$apply.setForceSni(forceSni.booleanValue());
        }
        if (http2ClearTextUpgrade != null) {
            $this$apply.setHttp2ClearTextUpgrade(http2ClearTextUpgrade.booleanValue());
        }
        if (http2ConnectionWindowSize != null) {
            $this$apply.setHttp2ConnectionWindowSize(http2ConnectionWindowSize.intValue());
        }
        if (http2KeepAliveTimeout != null) {
            $this$apply.setHttp2KeepAliveTimeout(http2KeepAliveTimeout.intValue());
        }
        if (http2MaxPoolSize != null) {
            $this$apply.setHttp2MaxPoolSize(http2MaxPoolSize.intValue());
        }
        if (http2MultiplexingLimit != null) {
            $this$apply.setHttp2MultiplexingLimit(http2MultiplexingLimit.intValue());
        }
        if (idleTimeout != null) {
            $this$apply.setIdleTimeout(idleTimeout.intValue());
        }
        if (idleTimeoutUnit != null) {
            $this$apply.setIdleTimeoutUnit(idleTimeoutUnit);
        }
        if (initialSettings != null) {
            $this$apply.setInitialSettings(initialSettings);
        }
        if (jdkSslEngineOptions != null) {
            $this$apply.setJdkSslEngineOptions(jdkSslEngineOptions);
        }
        if (keepAlive != null) {
            $this$apply.setKeepAlive(keepAlive.booleanValue());
        }
        if (keepAliveTimeout != null) {
            $this$apply.setKeepAliveTimeout(keepAliveTimeout.intValue());
        }
        if (keyStoreOptions != null) {
            $this$apply.setKeyStoreOptions(keyStoreOptions);
        }
        if (localAddress != null) {
            $this$apply.setLocalAddress(localAddress);
        }
        if (logActivity != null) {
            $this$apply.setLogActivity(logActivity.booleanValue());
        }
        if (maxChunkSize != null) {
            $this$apply.setMaxChunkSize(maxChunkSize.intValue());
        }
        if (maxHeaderSize != null) {
            $this$apply.setMaxHeaderSize(maxHeaderSize.intValue());
        }
        if (maxInitialLineLength != null) {
            $this$apply.setMaxInitialLineLength(maxInitialLineLength.intValue());
        }
        if (maxPoolSize != null) {
            $this$apply.setMaxPoolSize(maxPoolSize.intValue());
        }
        if (maxRedirects != null) {
            $this$apply.setMaxRedirects(maxRedirects.intValue());
        }
        if (maxWaitQueueSize != null) {
            $this$apply.setMaxWaitQueueSize(maxWaitQueueSize.intValue());
        }
        if (maxWebSocketFrameSize != null) {
            $this$apply.setMaxWebSocketFrameSize(maxWebSocketFrameSize.intValue());
        }
        if (maxWebSocketMessageSize != null) {
            $this$apply.setMaxWebSocketMessageSize(maxWebSocketMessageSize.intValue());
        }
        if (maxWebsocketFrameSize != null) {
            $this$apply.setMaxWebsocketFrameSize(maxWebsocketFrameSize.intValue());
        }
        if (maxWebsocketMessageSize != null) {
            $this$apply.setMaxWebsocketMessageSize(maxWebsocketMessageSize.intValue());
        }
        if (metricsName != null) {
            $this$apply.setMetricsName(metricsName);
        }
        if (openSslEngineOptions != null) {
            $this$apply.setOpenSslEngineOptions(openSslEngineOptions);
        }
        if (pemKeyCertOptions != null) {
            $this$apply.setPemKeyCertOptions(pemKeyCertOptions);
        }
        if (pemTrustOptions != null) {
            $this$apply.setPemTrustOptions(pemTrustOptions);
        }
        if (pfxKeyCertOptions != null) {
            $this$apply.setPfxKeyCertOptions(pfxKeyCertOptions);
        }
        if (pfxTrustOptions != null) {
            $this$apply.setPfxTrustOptions(pfxTrustOptions);
        }
        if (pipelining != null) {
            $this$apply.setPipelining(pipelining.booleanValue());
        }
        if (pipeliningLimit != null) {
            $this$apply.setPipeliningLimit(pipeliningLimit.intValue());
        }
        if (poolCleanerPeriod != null) {
            $this$apply.setPoolCleanerPeriod(poolCleanerPeriod.intValue());
        }
        if (protocolVersion != null) {
            $this$apply.setProtocolVersion(protocolVersion);
        }
        if (proxyOptions != null) {
            $this$apply.setProxyOptions(proxyOptions);
        }
        if (receiveBufferSize != null) {
            $this$apply.setReceiveBufferSize(receiveBufferSize.intValue());
        }
        if (reuseAddress != null) {
            $this$apply.setReuseAddress(reuseAddress.booleanValue());
        }
        if (reusePort != null) {
            $this$apply.setReusePort(reusePort.booleanValue());
        }
        if (sendBufferSize != null) {
            $this$apply.setSendBufferSize(sendBufferSize.intValue());
        }
        if (sendUnmaskedFrames != null) {
            $this$apply.setSendUnmaskedFrames(sendUnmaskedFrames.booleanValue());
        }
        if (soLinger != null) {
            $this$apply.setSoLinger(soLinger.intValue());
        }
        if (ssl != null) {
            $this$apply.setSsl(ssl.booleanValue());
        }
        if (sslHandshakeTimeout != null) {
            $this$apply.setSslHandshakeTimeout(sslHandshakeTimeout.longValue());
        }
        if (sslHandshakeTimeoutUnit != null) {
            $this$apply.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
        }
        if (tcpCork != null) {
            $this$apply.setTcpCork(tcpCork.booleanValue());
        }
        if (tcpFastOpen != null) {
            $this$apply.setTcpFastOpen(tcpFastOpen.booleanValue());
        }
        if (tcpKeepAlive != null) {
            $this$apply.setTcpKeepAlive(tcpKeepAlive.booleanValue());
        }
        if (tcpNoDelay != null) {
            $this$apply.setTcpNoDelay(tcpNoDelay.booleanValue());
        }
        if (tcpQuickAck != null) {
            $this$apply.setTcpQuickAck(tcpQuickAck.booleanValue());
        }
        if (trafficClass != null) {
            $this$apply.setTrafficClass(trafficClass.intValue());
        }
        if (trustAll != null) {
            $this$apply.setTrustAll(trustAll.booleanValue());
        }
        if (trustStoreOptions != null) {
            $this$apply.setTrustStoreOptions(trustStoreOptions);
        }
        if (tryUseCompression != null) {
            $this$apply.setTryUseCompression(tryUseCompression.booleanValue());
        }
        if (tryUsePerFrameWebSocketCompression != null) {
            $this$apply.setTryUsePerFrameWebSocketCompression(tryUsePerFrameWebSocketCompression.booleanValue());
        }
        if (tryUsePerFrameWebsocketCompression != null) {
            $this$apply.setTryUsePerFrameWebsocketCompression(tryUsePerFrameWebsocketCompression.booleanValue());
        }
        if (tryUsePerMessageWebSocketCompression != null) {
            $this$apply.setTryUsePerMessageWebSocketCompression(tryUsePerMessageWebSocketCompression.booleanValue());
        }
        if (tryUsePerMessageWebsocketCompression != null) {
            $this$apply.setTryUsePerMessageWebsocketCompression(tryUsePerMessageWebsocketCompression.booleanValue());
        }
        if (useAlpn != null) {
            $this$apply.setUseAlpn(useAlpn.booleanValue());
        }
        if (usePooledBuffers != null) {
            $this$apply.setUsePooledBuffers(usePooledBuffers.booleanValue());
        }
        if (verifyHost != null) {
            $this$apply.setVerifyHost(verifyHost.booleanValue());
        }
        if (webSocketCompressionAllowClientNoContext != null) {
            $this$apply.setWebSocketCompressionAllowClientNoContext(webSocketCompressionAllowClientNoContext.booleanValue());
        }
        if (webSocketCompressionLevel != null) {
            $this$apply.setWebSocketCompressionLevel(webSocketCompressionLevel.intValue());
        }
        if (webSocketCompressionRequestServerNoContext != null) {
            $this$apply.setWebSocketCompressionRequestServerNoContext(webSocketCompressionRequestServerNoContext.booleanValue());
        }
        if (websocketCompressionAllowClientNoContext != null) {
            $this$apply.setWebsocketCompressionAllowClientNoContext(websocketCompressionAllowClientNoContext.booleanValue());
        }
        if (websocketCompressionLevel != null) {
            $this$apply.setWebsocketCompressionLevel(websocketCompressionLevel.intValue());
        }
        if (websocketCompressionRequestServerNoContext != null) {
            $this$apply.setWebsocketCompressionRequestServerNoContext(websocketCompressionRequestServerNoContext.booleanValue());
        }
        Unit unit = Unit.INSTANCE;
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "httpClientOptionsOf(alpnVersions, connectTimeout, crlPaths, crlValues, decoderInitialBufferSize, defaultHost, defaultPort, enabledCipherSuites, enabledSecureTransportProtocols, forceSni, http2ClearTextUpgrade, http2ConnectionWindowSize, http2KeepAliveTimeout, http2MaxPoolSize, http2MultiplexingLimit, idleTimeout, idleTimeoutUnit, initialSettings, jdkSslEngineOptions, keepAlive, keepAliveTimeout, keyStoreOptions, localAddress, logActivity, maxChunkSize, maxHeaderSize, maxInitialLineLength, maxPoolSize, maxRedirects, maxWaitQueueSize, maxWebSocketFrameSize, maxWebSocketMessageSize, maxWebsocketFrameSize, maxWebsocketMessageSize, metricsName, openSslEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxKeyCertOptions, pfxTrustOptions, pipelining, pipeliningLimit, poolCleanerPeriod, protocolVersion, proxyOptions, receiveBufferSize, reuseAddress, reusePort, sendBufferSize, sendUnmaskedFrames, soLinger, ssl, sslHandshakeTimeout, sslHandshakeTimeoutUnit, tcpCork, tcpFastOpen, tcpKeepAlive, tcpNoDelay, tcpQuickAck, trafficClass, trustAll, trustStoreOptions, tryUseCompression, tryUsePerFrameWebSocketCompression, tryUsePerFrameWebsocketCompression, tryUsePerMessageWebSocketCompression, tryUsePerMessageWebsocketCompression, useAlpn, usePooledBuffers, verifyHost, webSocketCompressionAllowClientNoContext, webSocketCompressionLevel, webSocketCompressionRequestServerNoContext, websocketCompressionAllowClientNoContext, websocketCompressionLevel, websocketCompressionRequestServerNoContext)"))
    @NotNull
    public static /* synthetic */ HttpClientOptions HttpClientOptions$default(Iterable iterable, Integer num, Iterable iterable2, Iterable iterable3, Integer num2, String str, Integer num3, Iterable iterable4, Iterable iterable5, Boolean bool, Boolean bool2, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, TimeUnit timeUnit, Http2Settings http2Settings, JdkSSLEngineOptions jdkSSLEngineOptions, Boolean bool3, Integer num9, JksOptions jksOptions, String str2, Boolean bool4, Integer num10, Integer num11, Integer num12, Integer num13, Integer num14, Integer num15, Integer num16, Integer num17, Integer num18, Integer num19, String str3, OpenSSLEngineOptions openSSLEngineOptions, PemKeyCertOptions pemKeyCertOptions, PemTrustOptions pemTrustOptions, PfxOptions pfxOptions, PfxOptions pfxOptions2, Boolean bool5, Integer num20, Integer num21, HttpVersion httpVersion, ProxyOptions proxyOptions, Integer num22, Boolean bool6, Boolean bool7, Integer num23, Boolean bool8, Integer num24, Boolean bool9, Long l, TimeUnit timeUnit2, Boolean bool10, Boolean bool11, Boolean bool12, Boolean bool13, Boolean bool14, Integer num25, Boolean bool15, JksOptions jksOptions2, Boolean bool16, Boolean bool17, Boolean bool18, Boolean bool19, Boolean bool20, Boolean bool21, Boolean bool22, Boolean bool23, Boolean bool24, Integer num26, Boolean bool25, Boolean bool26, Integer num27, Boolean bool27, int i, int i2, int i3, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            num = (Integer) null;
        }
        if ((i & 4) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 8) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 16) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 32) != 0) {
            str = (String) null;
        }
        if ((i & 64) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 128) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 256) != 0) {
            iterable5 = (Iterable) null;
        }
        if ((i & 512) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 1024) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 2048) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 4096) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 8192) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 16384) != 0) {
            num7 = (Integer) null;
        }
        if ((i & 32768) != 0) {
            num8 = (Integer) null;
        }
        if ((i & 65536) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 131072) != 0) {
            http2Settings = (Http2Settings) null;
        }
        if ((i & 262144) != 0) {
            jdkSSLEngineOptions = (JdkSSLEngineOptions) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 1048576) != 0) {
            num9 = (Integer) null;
        }
        if ((i & 2097152) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i & 4194304) != 0) {
            str2 = (String) null;
        }
        if ((i & 8388608) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 16777216) != 0) {
            num10 = (Integer) null;
        }
        if ((i & 33554432) != 0) {
            num11 = (Integer) null;
        }
        if ((i & 67108864) != 0) {
            num12 = (Integer) null;
        }
        if ((i & 134217728) != 0) {
            num13 = (Integer) null;
        }
        if ((i & 268435456) != 0) {
            num14 = (Integer) null;
        }
        if ((i & 536870912) != 0) {
            num15 = (Integer) null;
        }
        if ((i & 1073741824) != 0) {
            num16 = (Integer) null;
        }
        if ((i & Integer.MIN_VALUE) != 0) {
            num17 = (Integer) null;
        }
        if ((i2 & 1) != 0) {
            num18 = (Integer) null;
        }
        if ((i2 & 2) != 0) {
            num19 = (Integer) null;
        }
        if ((i2 & 4) != 0) {
            str3 = (String) null;
        }
        if ((i2 & 8) != 0) {
            openSSLEngineOptions = (OpenSSLEngineOptions) null;
        }
        if ((i2 & 16) != 0) {
            pemKeyCertOptions = (PemKeyCertOptions) null;
        }
        if ((i2 & 32) != 0) {
            pemTrustOptions = (PemTrustOptions) null;
        }
        if ((i2 & 64) != 0) {
            pfxOptions = (PfxOptions) null;
        }
        if ((i2 & 128) != 0) {
            pfxOptions2 = (PfxOptions) null;
        }
        if ((i2 & 256) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i2 & 512) != 0) {
            num20 = (Integer) null;
        }
        if ((i2 & 1024) != 0) {
            num21 = (Integer) null;
        }
        if ((i2 & 2048) != 0) {
            httpVersion = (HttpVersion) null;
        }
        if ((i2 & 4096) != 0) {
            proxyOptions = (ProxyOptions) null;
        }
        if ((i2 & 8192) != 0) {
            num22 = (Integer) null;
        }
        if ((i2 & 16384) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i2 & 32768) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i2 & 65536) != 0) {
            num23 = (Integer) null;
        }
        if ((i2 & 131072) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i2 & 262144) != 0) {
            num24 = (Integer) null;
        }
        if ((i2 & Opcodes.ASM8) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i2 & 1048576) != 0) {
            l = (Long) null;
        }
        if ((i2 & 2097152) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i2 & 4194304) != 0) {
            bool10 = (Boolean) null;
        }
        if ((i2 & 8388608) != 0) {
            bool11 = (Boolean) null;
        }
        if ((i2 & 16777216) != 0) {
            bool12 = (Boolean) null;
        }
        if ((i2 & 33554432) != 0) {
            bool13 = (Boolean) null;
        }
        if ((i2 & 67108864) != 0) {
            bool14 = (Boolean) null;
        }
        if ((i2 & 134217728) != 0) {
            num25 = (Integer) null;
        }
        if ((i2 & 268435456) != 0) {
            bool15 = (Boolean) null;
        }
        if ((i2 & 536870912) != 0) {
            jksOptions2 = (JksOptions) null;
        }
        if ((i2 & 1073741824) != 0) {
            bool16 = (Boolean) null;
        }
        if ((i2 & Integer.MIN_VALUE) != 0) {
            bool17 = (Boolean) null;
        }
        if ((i3 & 1) != 0) {
            bool18 = (Boolean) null;
        }
        if ((i3 & 2) != 0) {
            bool19 = (Boolean) null;
        }
        if ((i3 & 4) != 0) {
            bool20 = (Boolean) null;
        }
        if ((i3 & 8) != 0) {
            bool21 = (Boolean) null;
        }
        if ((i3 & 16) != 0) {
            bool22 = (Boolean) null;
        }
        if ((i3 & 32) != 0) {
            bool23 = (Boolean) null;
        }
        if ((i3 & 64) != 0) {
            bool24 = (Boolean) null;
        }
        if ((i3 & 128) != 0) {
            num26 = (Integer) null;
        }
        if ((i3 & 256) != 0) {
            bool25 = (Boolean) null;
        }
        if ((i3 & 512) != 0) {
            bool26 = (Boolean) null;
        }
        if ((i3 & 1024) != 0) {
            num27 = (Integer) null;
        }
        if ((i3 & 2048) != 0) {
            bool27 = (Boolean) null;
        }
        return HttpClientOptions(iterable, num, iterable2, iterable3, num2, str, num3, iterable4, iterable5, bool, bool2, num4, num5, num6, num7, num8, timeUnit, http2Settings, jdkSSLEngineOptions, bool3, num9, jksOptions, str2, bool4, num10, num11, num12, num13, num14, num15, num16, num17, num18, num19, str3, openSSLEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxOptions, pfxOptions2, bool5, num20, num21, httpVersion, proxyOptions, num22, bool6, bool7, num23, bool8, num24, bool9, l, timeUnit2, bool10, bool11, bool12, bool13, bool14, num25, bool15, jksOptions2, bool16, bool17, bool18, bool19, bool20, bool21, bool22, bool23, bool24, num26, bool25, bool26, num27, bool27);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "httpClientOptionsOf(alpnVersions, connectTimeout, crlPaths, crlValues, decoderInitialBufferSize, defaultHost, defaultPort, enabledCipherSuites, enabledSecureTransportProtocols, forceSni, http2ClearTextUpgrade, http2ConnectionWindowSize, http2KeepAliveTimeout, http2MaxPoolSize, http2MultiplexingLimit, idleTimeout, idleTimeoutUnit, initialSettings, jdkSslEngineOptions, keepAlive, keepAliveTimeout, keyStoreOptions, localAddress, logActivity, maxChunkSize, maxHeaderSize, maxInitialLineLength, maxPoolSize, maxRedirects, maxWaitQueueSize, maxWebSocketFrameSize, maxWebSocketMessageSize, maxWebsocketFrameSize, maxWebsocketMessageSize, metricsName, openSslEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxKeyCertOptions, pfxTrustOptions, pipelining, pipeliningLimit, poolCleanerPeriod, protocolVersion, proxyOptions, receiveBufferSize, reuseAddress, reusePort, sendBufferSize, sendUnmaskedFrames, soLinger, ssl, sslHandshakeTimeout, sslHandshakeTimeoutUnit, tcpCork, tcpFastOpen, tcpKeepAlive, tcpNoDelay, tcpQuickAck, trafficClass, trustAll, trustStoreOptions, tryUseCompression, tryUsePerFrameWebSocketCompression, tryUsePerFrameWebsocketCompression, tryUsePerMessageWebSocketCompression, tryUsePerMessageWebsocketCompression, useAlpn, usePooledBuffers, verifyHost, webSocketCompressionAllowClientNoContext, webSocketCompressionLevel, webSocketCompressionRequestServerNoContext, websocketCompressionAllowClientNoContext, websocketCompressionLevel, websocketCompressionRequestServerNoContext)"))
    @NotNull
    public static final HttpClientOptions HttpClientOptions(@Nullable Iterable<? extends HttpVersion> iterable, @Nullable Integer connectTimeout, @Nullable Iterable<String> iterable2, @Nullable Iterable<? extends Buffer> iterable3, @Nullable Integer decoderInitialBufferSize, @Nullable String defaultHost, @Nullable Integer defaultPort, @Nullable Iterable<String> iterable4, @Nullable Iterable<String> iterable5, @Nullable Boolean forceSni, @Nullable Boolean http2ClearTextUpgrade, @Nullable Integer http2ConnectionWindowSize, @Nullable Integer http2KeepAliveTimeout, @Nullable Integer http2MaxPoolSize, @Nullable Integer http2MultiplexingLimit, @Nullable Integer idleTimeout, @Nullable TimeUnit idleTimeoutUnit, @Nullable Http2Settings initialSettings, @Nullable JdkSSLEngineOptions jdkSslEngineOptions, @Nullable Boolean keepAlive, @Nullable Integer keepAliveTimeout, @Nullable JksOptions keyStoreOptions, @Nullable String localAddress, @Nullable Boolean logActivity, @Nullable Integer maxChunkSize, @Nullable Integer maxHeaderSize, @Nullable Integer maxInitialLineLength, @Nullable Integer maxPoolSize, @Nullable Integer maxRedirects, @Nullable Integer maxWaitQueueSize, @Nullable Integer maxWebSocketFrameSize, @Nullable Integer maxWebSocketMessageSize, @Nullable Integer maxWebsocketFrameSize, @Nullable Integer maxWebsocketMessageSize, @Nullable String metricsName, @Nullable OpenSSLEngineOptions openSslEngineOptions, @Nullable PemKeyCertOptions pemKeyCertOptions, @Nullable PemTrustOptions pemTrustOptions, @Nullable PfxOptions pfxKeyCertOptions, @Nullable PfxOptions pfxTrustOptions, @Nullable Boolean pipelining, @Nullable Integer pipeliningLimit, @Nullable Integer poolCleanerPeriod, @Nullable HttpVersion protocolVersion, @Nullable ProxyOptions proxyOptions, @Nullable Integer receiveBufferSize, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable Integer sendBufferSize, @Nullable Boolean sendUnmaskedFrames, @Nullable Integer soLinger, @Nullable Boolean ssl, @Nullable Long sslHandshakeTimeout, @Nullable TimeUnit sslHandshakeTimeoutUnit, @Nullable Boolean tcpCork, @Nullable Boolean tcpFastOpen, @Nullable Boolean tcpKeepAlive, @Nullable Boolean tcpNoDelay, @Nullable Boolean tcpQuickAck, @Nullable Integer trafficClass, @Nullable Boolean trustAll, @Nullable JksOptions trustStoreOptions, @Nullable Boolean tryUseCompression, @Nullable Boolean tryUsePerFrameWebSocketCompression, @Nullable Boolean tryUsePerFrameWebsocketCompression, @Nullable Boolean tryUsePerMessageWebSocketCompression, @Nullable Boolean tryUsePerMessageWebsocketCompression, @Nullable Boolean useAlpn, @Nullable Boolean usePooledBuffers, @Nullable Boolean verifyHost, @Nullable Boolean webSocketCompressionAllowClientNoContext, @Nullable Integer webSocketCompressionLevel, @Nullable Boolean webSocketCompressionRequestServerNoContext, @Nullable Boolean websocketCompressionAllowClientNoContext, @Nullable Integer websocketCompressionLevel, @Nullable Boolean websocketCompressionRequestServerNoContext) throws NullPointerException {
        HttpClientOptions $this$apply = new HttpClientOptions();
        if (iterable != null) {
            $this$apply.setAlpnVersions(CollectionsKt.toList(iterable));
        }
        if (connectTimeout != null) {
            $this$apply.setConnectTimeout(connectTimeout.intValue());
        }
        if (iterable2 != null) {
            for (String item : iterable2) {
                $this$apply.addCrlPath(item);
            }
        }
        if (iterable3 != null) {
            for (Buffer item2 : iterable3) {
                $this$apply.addCrlValue(item2);
            }
        }
        if (decoderInitialBufferSize != null) {
            $this$apply.setDecoderInitialBufferSize(decoderInitialBufferSize.intValue());
        }
        if (defaultHost != null) {
            $this$apply.setDefaultHost(defaultHost);
        }
        if (defaultPort != null) {
            $this$apply.setDefaultPort(defaultPort.intValue());
        }
        if (iterable4 != null) {
            for (String item3 : iterable4) {
                $this$apply.addEnabledCipherSuite(item3);
            }
        }
        if (iterable5 != null) {
            $this$apply.setEnabledSecureTransportProtocols(CollectionsKt.toSet(iterable5));
        }
        if (forceSni != null) {
            $this$apply.setForceSni(forceSni.booleanValue());
        }
        if (http2ClearTextUpgrade != null) {
            $this$apply.setHttp2ClearTextUpgrade(http2ClearTextUpgrade.booleanValue());
        }
        if (http2ConnectionWindowSize != null) {
            $this$apply.setHttp2ConnectionWindowSize(http2ConnectionWindowSize.intValue());
        }
        if (http2KeepAliveTimeout != null) {
            $this$apply.setHttp2KeepAliveTimeout(http2KeepAliveTimeout.intValue());
        }
        if (http2MaxPoolSize != null) {
            $this$apply.setHttp2MaxPoolSize(http2MaxPoolSize.intValue());
        }
        if (http2MultiplexingLimit != null) {
            $this$apply.setHttp2MultiplexingLimit(http2MultiplexingLimit.intValue());
        }
        if (idleTimeout != null) {
            $this$apply.setIdleTimeout(idleTimeout.intValue());
        }
        if (idleTimeoutUnit != null) {
            $this$apply.setIdleTimeoutUnit(idleTimeoutUnit);
        }
        if (initialSettings != null) {
            $this$apply.setInitialSettings(initialSettings);
        }
        if (jdkSslEngineOptions != null) {
            $this$apply.setJdkSslEngineOptions(jdkSslEngineOptions);
        }
        if (keepAlive != null) {
            $this$apply.setKeepAlive(keepAlive.booleanValue());
        }
        if (keepAliveTimeout != null) {
            $this$apply.setKeepAliveTimeout(keepAliveTimeout.intValue());
        }
        if (keyStoreOptions != null) {
            $this$apply.setKeyStoreOptions(keyStoreOptions);
        }
        if (localAddress != null) {
            $this$apply.setLocalAddress(localAddress);
        }
        if (logActivity != null) {
            $this$apply.setLogActivity(logActivity.booleanValue());
        }
        if (maxChunkSize != null) {
            $this$apply.setMaxChunkSize(maxChunkSize.intValue());
        }
        if (maxHeaderSize != null) {
            $this$apply.setMaxHeaderSize(maxHeaderSize.intValue());
        }
        if (maxInitialLineLength != null) {
            $this$apply.setMaxInitialLineLength(maxInitialLineLength.intValue());
        }
        if (maxPoolSize != null) {
            $this$apply.setMaxPoolSize(maxPoolSize.intValue());
        }
        if (maxRedirects != null) {
            $this$apply.setMaxRedirects(maxRedirects.intValue());
        }
        if (maxWaitQueueSize != null) {
            $this$apply.setMaxWaitQueueSize(maxWaitQueueSize.intValue());
        }
        if (maxWebSocketFrameSize != null) {
            $this$apply.setMaxWebSocketFrameSize(maxWebSocketFrameSize.intValue());
        }
        if (maxWebSocketMessageSize != null) {
            $this$apply.setMaxWebSocketMessageSize(maxWebSocketMessageSize.intValue());
        }
        if (maxWebsocketFrameSize != null) {
            $this$apply.setMaxWebsocketFrameSize(maxWebsocketFrameSize.intValue());
        }
        if (maxWebsocketMessageSize != null) {
            $this$apply.setMaxWebsocketMessageSize(maxWebsocketMessageSize.intValue());
        }
        if (metricsName != null) {
            $this$apply.setMetricsName(metricsName);
        }
        if (openSslEngineOptions != null) {
            $this$apply.setOpenSslEngineOptions(openSslEngineOptions);
        }
        if (pemKeyCertOptions != null) {
            $this$apply.setPemKeyCertOptions(pemKeyCertOptions);
        }
        if (pemTrustOptions != null) {
            $this$apply.setPemTrustOptions(pemTrustOptions);
        }
        if (pfxKeyCertOptions != null) {
            $this$apply.setPfxKeyCertOptions(pfxKeyCertOptions);
        }
        if (pfxTrustOptions != null) {
            $this$apply.setPfxTrustOptions(pfxTrustOptions);
        }
        if (pipelining != null) {
            $this$apply.setPipelining(pipelining.booleanValue());
        }
        if (pipeliningLimit != null) {
            $this$apply.setPipeliningLimit(pipeliningLimit.intValue());
        }
        if (poolCleanerPeriod != null) {
            $this$apply.setPoolCleanerPeriod(poolCleanerPeriod.intValue());
        }
        if (protocolVersion != null) {
            $this$apply.setProtocolVersion(protocolVersion);
        }
        if (proxyOptions != null) {
            $this$apply.setProxyOptions(proxyOptions);
        }
        if (receiveBufferSize != null) {
            $this$apply.setReceiveBufferSize(receiveBufferSize.intValue());
        }
        if (reuseAddress != null) {
            $this$apply.setReuseAddress(reuseAddress.booleanValue());
        }
        if (reusePort != null) {
            $this$apply.setReusePort(reusePort.booleanValue());
        }
        if (sendBufferSize != null) {
            $this$apply.setSendBufferSize(sendBufferSize.intValue());
        }
        if (sendUnmaskedFrames != null) {
            $this$apply.setSendUnmaskedFrames(sendUnmaskedFrames.booleanValue());
        }
        if (soLinger != null) {
            $this$apply.setSoLinger(soLinger.intValue());
        }
        if (ssl != null) {
            $this$apply.setSsl(ssl.booleanValue());
        }
        if (sslHandshakeTimeout != null) {
            $this$apply.setSslHandshakeTimeout(sslHandshakeTimeout.longValue());
        }
        if (sslHandshakeTimeoutUnit != null) {
            $this$apply.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
        }
        if (tcpCork != null) {
            $this$apply.setTcpCork(tcpCork.booleanValue());
        }
        if (tcpFastOpen != null) {
            $this$apply.setTcpFastOpen(tcpFastOpen.booleanValue());
        }
        if (tcpKeepAlive != null) {
            $this$apply.setTcpKeepAlive(tcpKeepAlive.booleanValue());
        }
        if (tcpNoDelay != null) {
            $this$apply.setTcpNoDelay(tcpNoDelay.booleanValue());
        }
        if (tcpQuickAck != null) {
            $this$apply.setTcpQuickAck(tcpQuickAck.booleanValue());
        }
        if (trafficClass != null) {
            $this$apply.setTrafficClass(trafficClass.intValue());
        }
        if (trustAll != null) {
            $this$apply.setTrustAll(trustAll.booleanValue());
        }
        if (trustStoreOptions != null) {
            $this$apply.setTrustStoreOptions(trustStoreOptions);
        }
        if (tryUseCompression != null) {
            $this$apply.setTryUseCompression(tryUseCompression.booleanValue());
        }
        if (tryUsePerFrameWebSocketCompression != null) {
            $this$apply.setTryUsePerFrameWebSocketCompression(tryUsePerFrameWebSocketCompression.booleanValue());
        }
        if (tryUsePerFrameWebsocketCompression != null) {
            $this$apply.setTryUsePerFrameWebsocketCompression(tryUsePerFrameWebsocketCompression.booleanValue());
        }
        if (tryUsePerMessageWebSocketCompression != null) {
            $this$apply.setTryUsePerMessageWebSocketCompression(tryUsePerMessageWebSocketCompression.booleanValue());
        }
        if (tryUsePerMessageWebsocketCompression != null) {
            $this$apply.setTryUsePerMessageWebsocketCompression(tryUsePerMessageWebsocketCompression.booleanValue());
        }
        if (useAlpn != null) {
            $this$apply.setUseAlpn(useAlpn.booleanValue());
        }
        if (usePooledBuffers != null) {
            $this$apply.setUsePooledBuffers(usePooledBuffers.booleanValue());
        }
        if (verifyHost != null) {
            $this$apply.setVerifyHost(verifyHost.booleanValue());
        }
        if (webSocketCompressionAllowClientNoContext != null) {
            $this$apply.setWebSocketCompressionAllowClientNoContext(webSocketCompressionAllowClientNoContext.booleanValue());
        }
        if (webSocketCompressionLevel != null) {
            $this$apply.setWebSocketCompressionLevel(webSocketCompressionLevel.intValue());
        }
        if (webSocketCompressionRequestServerNoContext != null) {
            $this$apply.setWebSocketCompressionRequestServerNoContext(webSocketCompressionRequestServerNoContext.booleanValue());
        }
        if (websocketCompressionAllowClientNoContext != null) {
            $this$apply.setWebsocketCompressionAllowClientNoContext(websocketCompressionAllowClientNoContext.booleanValue());
        }
        if (websocketCompressionLevel != null) {
            $this$apply.setWebsocketCompressionLevel(websocketCompressionLevel.intValue());
        }
        if (websocketCompressionRequestServerNoContext != null) {
            $this$apply.setWebsocketCompressionRequestServerNoContext(websocketCompressionRequestServerNoContext.booleanValue());
        }
        Unit unit = Unit.INSTANCE;
        return $this$apply;
    }
}
