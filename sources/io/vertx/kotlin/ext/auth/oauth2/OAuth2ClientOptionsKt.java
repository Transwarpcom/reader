package io.vertx.kotlin.ext.auth.oauth2;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import io.vertx.core.net.ProxyOptions;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.oauth2.OAuth2ClientOptions;
import io.vertx.ext.auth.oauth2.OAuth2FlowType;
import io.vertx.ext.jwt.JWTOptions;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;

/* compiled from: OAuth2ClientOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0094\u0001\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b \u001a½\t\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010@2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B2\n\b\u0002\u0010C\u001a\u0004\u0018\u00010D2\n\b\u0002\u0010E\u001a\u0004\u0018\u00010D2\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010J\u001a\u0004\u0018\u00010K2\u0010\b\u0002\u0010L\u001a\n\u0012\u0004\u0012\u00020M\u0018\u00010\u00032\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010T\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010U\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010V\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010W\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010X\u001a\u0004\u0018\u00010Y2\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010]\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010^\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010_\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010`\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010a\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010b\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010c\u001a\u0004\u0018\u00010.2\n\b\u0002\u0010d\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010e\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010f\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010g\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010h\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010i\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010j\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010k\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010l\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010m\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010o\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010p\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010q\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010r\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010s\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010t\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010u\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010v\u001a\u0004\u0018\u00010\u0019H\u0007¢\u0006\u0002\u0010w\u001a»\t\u0010x\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010@2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B2\n\b\u0002\u0010C\u001a\u0004\u0018\u00010D2\n\b\u0002\u0010E\u001a\u0004\u0018\u00010D2\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010J\u001a\u0004\u0018\u00010K2\u0010\b\u0002\u0010L\u001a\n\u0012\u0004\u0012\u00020M\u0018\u00010\u00032\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010T\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010U\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010V\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010W\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010X\u001a\u0004\u0018\u00010Y2\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010]\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010^\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010_\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010`\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010a\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010b\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010c\u001a\u0004\u0018\u00010.2\n\b\u0002\u0010d\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010e\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010f\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010g\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010h\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010i\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010j\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010k\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010l\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010m\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010o\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010p\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010q\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010r\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010s\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010t\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010u\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010v\u001a\u0004\u0018\u00010\u0019¢\u0006\u0002\u0010w¨\u0006y"}, d2 = {"OAuth2ClientOptions", "Lio/vertx/ext/auth/oauth2/OAuth2ClientOptions;", "alpnVersions", "", "Lio/vertx/core/http/HttpVersion;", "authorizationPath", "", "clientID", "clientSecret", "clientSecretParameterName", "connectTimeout", "", "crlPaths", "crlValues", "Lio/vertx/core/buffer/Buffer;", "decoderInitialBufferSize", "defaultHost", "defaultPort", "enabledCipherSuites", "enabledSecureTransportProtocols", "extraParameters", "Lio/vertx/core/json/JsonObject;", "flow", "Lio/vertx/ext/auth/oauth2/OAuth2FlowType;", "forceSni", "", "headers", "http2ClearTextUpgrade", "http2ConnectionWindowSize", "http2KeepAliveTimeout", "http2MaxPoolSize", "http2MultiplexingLimit", "idleTimeout", "idleTimeoutUnit", "Ljava/util/concurrent/TimeUnit;", "initialSettings", "Lio/vertx/core/http/Http2Settings;", "introspectionPath", "jdkSslEngineOptions", "Lio/vertx/core/net/JdkSSLEngineOptions;", "jwkPath", "jwtOptions", "Lio/vertx/ext/jwt/JWTOptions;", "keepAlive", "keepAliveTimeout", "keyStoreOptions", "Lio/vertx/core/net/JksOptions;", "localAddress", "logActivity", "logoutPath", "maxChunkSize", "maxHeaderSize", "maxInitialLineLength", "maxPoolSize", "maxRedirects", "maxWaitQueueSize", "maxWebSocketFrameSize", "maxWebSocketMessageSize", "maxWebsocketFrameSize", "maxWebsocketMessageSize", "metricsName", "openSslEngineOptions", "Lio/vertx/core/net/OpenSSLEngineOptions;", "pemKeyCertOptions", "Lio/vertx/core/net/PemKeyCertOptions;", "pemTrustOptions", "Lio/vertx/core/net/PemTrustOptions;", "pfxKeyCertOptions", "Lio/vertx/core/net/PfxOptions;", "pfxTrustOptions", "pipelining", "pipeliningLimit", "poolCleanerPeriod", "protocolVersion", "proxyOptions", "Lio/vertx/core/net/ProxyOptions;", "pubSecKeys", "Lio/vertx/ext/auth/PubSecKeyOptions;", "receiveBufferSize", "reuseAddress", "reusePort", "revocationPath", "scopeSeparator", "sendBufferSize", "sendUnmaskedFrames", "site", "soLinger", "ssl", "sslHandshakeTimeout", "", "sslHandshakeTimeoutUnit", "tcpCork", "tcpFastOpen", "tcpKeepAlive", "tcpNoDelay", "tcpQuickAck", "tokenPath", "trafficClass", "trustAll", "trustStoreOptions", "tryUseCompression", "tryUsePerFrameWebSocketCompression", "tryUsePerFrameWebsocketCompression", "tryUsePerMessageWebSocketCompression", "tryUsePerMessageWebsocketCompression", "useAlpn", "useBasicAuthorizationHeader", "usePooledBuffers", "userAgent", "userInfoParameters", "userInfoPath", "validateIssuer", "verifyHost", "webSocketCompressionAllowClientNoContext", "webSocketCompressionLevel", "webSocketCompressionRequestServerNoContext", "websocketCompressionAllowClientNoContext", "websocketCompressionLevel", "websocketCompressionRequestServerNoContext", "(Ljava/lang/Iterable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Iterable;Ljava/lang/Iterable;Lio/vertx/core/json/JsonObject;Lio/vertx/ext/auth/oauth2/OAuth2FlowType;Ljava/lang/Boolean;Lio/vertx/core/json/JsonObject;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/concurrent/TimeUnit;Lio/vertx/core/http/Http2Settings;Ljava/lang/String;Lio/vertx/core/net/JdkSSLEngineOptions;Ljava/lang/String;Lio/vertx/ext/jwt/JWTOptions;Ljava/lang/Boolean;Ljava/lang/Integer;Lio/vertx/core/net/JksOptions;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lio/vertx/core/net/OpenSSLEngineOptions;Lio/vertx/core/net/PemKeyCertOptions;Lio/vertx/core/net/PemTrustOptions;Lio/vertx/core/net/PfxOptions;Lio/vertx/core/net/PfxOptions;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lio/vertx/core/http/HttpVersion;Lio/vertx/core/net/ProxyOptions;Ljava/lang/Iterable;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Lio/vertx/core/net/JksOptions;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Lio/vertx/core/json/JsonObject;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;)Lio/vertx/ext/auth/oauth2/OAuth2ClientOptions;", "oAuth2ClientOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/auth/oauth2/OAuth2ClientOptionsKt.class */
public final class OAuth2ClientOptionsKt {
    @NotNull
    public static /* synthetic */ OAuth2ClientOptions oAuth2ClientOptionsOf$default(Iterable iterable, String str, String str2, String str3, String str4, Integer num, Iterable iterable2, Iterable iterable3, Integer num2, String str5, Integer num3, Iterable iterable4, Iterable iterable5, JsonObject jsonObject, OAuth2FlowType oAuth2FlowType, Boolean bool, JsonObject jsonObject2, Boolean bool2, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, TimeUnit timeUnit, Http2Settings http2Settings, String str6, JdkSSLEngineOptions jdkSSLEngineOptions, String str7, JWTOptions jWTOptions, Boolean bool3, Integer num9, JksOptions jksOptions, String str8, Boolean bool4, String str9, Integer num10, Integer num11, Integer num12, Integer num13, Integer num14, Integer num15, Integer num16, Integer num17, Integer num18, Integer num19, String str10, OpenSSLEngineOptions openSSLEngineOptions, PemKeyCertOptions pemKeyCertOptions, PemTrustOptions pemTrustOptions, PfxOptions pfxOptions, PfxOptions pfxOptions2, Boolean bool5, Integer num20, Integer num21, HttpVersion httpVersion, ProxyOptions proxyOptions, Iterable iterable6, Integer num22, Boolean bool6, Boolean bool7, String str11, String str12, Integer num23, Boolean bool8, String str13, Integer num24, Boolean bool9, Long l, TimeUnit timeUnit2, Boolean bool10, Boolean bool11, Boolean bool12, Boolean bool13, Boolean bool14, String str14, Integer num25, Boolean bool15, JksOptions jksOptions2, Boolean bool16, Boolean bool17, Boolean bool18, Boolean bool19, Boolean bool20, Boolean bool21, Boolean bool22, Boolean bool23, String str15, JsonObject jsonObject3, String str16, Boolean bool24, Boolean bool25, Boolean bool26, Integer num26, Boolean bool27, Boolean bool28, Integer num27, Boolean bool29, int i, int i2, int i3, int i4, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            str3 = (String) null;
        }
        if ((i & 16) != 0) {
            str4 = (String) null;
        }
        if ((i & 32) != 0) {
            num = (Integer) null;
        }
        if ((i & 64) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 128) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 256) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 512) != 0) {
            str5 = (String) null;
        }
        if ((i & 1024) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 2048) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 4096) != 0) {
            iterable5 = (Iterable) null;
        }
        if ((i & 8192) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 16384) != 0) {
            oAuth2FlowType = (OAuth2FlowType) null;
        }
        if ((i & 32768) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 65536) != 0) {
            jsonObject2 = (JsonObject) null;
        }
        if ((i & 131072) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 262144) != 0) {
            num4 = (Integer) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 1048576) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 2097152) != 0) {
            num7 = (Integer) null;
        }
        if ((i & 4194304) != 0) {
            num8 = (Integer) null;
        }
        if ((i & 8388608) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 16777216) != 0) {
            http2Settings = (Http2Settings) null;
        }
        if ((i & 33554432) != 0) {
            str6 = (String) null;
        }
        if ((i & 67108864) != 0) {
            jdkSSLEngineOptions = (JdkSSLEngineOptions) null;
        }
        if ((i & 134217728) != 0) {
            str7 = (String) null;
        }
        if ((i & 268435456) != 0) {
            jWTOptions = (JWTOptions) null;
        }
        if ((i & 536870912) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 1073741824) != 0) {
            num9 = (Integer) null;
        }
        if ((i & Integer.MIN_VALUE) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i2 & 1) != 0) {
            str8 = (String) null;
        }
        if ((i2 & 2) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i2 & 4) != 0) {
            str9 = (String) null;
        }
        if ((i2 & 8) != 0) {
            num10 = (Integer) null;
        }
        if ((i2 & 16) != 0) {
            num11 = (Integer) null;
        }
        if ((i2 & 32) != 0) {
            num12 = (Integer) null;
        }
        if ((i2 & 64) != 0) {
            num13 = (Integer) null;
        }
        if ((i2 & 128) != 0) {
            num14 = (Integer) null;
        }
        if ((i2 & 256) != 0) {
            num15 = (Integer) null;
        }
        if ((i2 & 512) != 0) {
            num16 = (Integer) null;
        }
        if ((i2 & 1024) != 0) {
            num17 = (Integer) null;
        }
        if ((i2 & 2048) != 0) {
            num18 = (Integer) null;
        }
        if ((i2 & 4096) != 0) {
            num19 = (Integer) null;
        }
        if ((i2 & 8192) != 0) {
            str10 = (String) null;
        }
        if ((i2 & 16384) != 0) {
            openSSLEngineOptions = (OpenSSLEngineOptions) null;
        }
        if ((i2 & 32768) != 0) {
            pemKeyCertOptions = (PemKeyCertOptions) null;
        }
        if ((i2 & 65536) != 0) {
            pemTrustOptions = (PemTrustOptions) null;
        }
        if ((i2 & 131072) != 0) {
            pfxOptions = (PfxOptions) null;
        }
        if ((i2 & 262144) != 0) {
            pfxOptions2 = (PfxOptions) null;
        }
        if ((i2 & Opcodes.ASM8) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i2 & 1048576) != 0) {
            num20 = (Integer) null;
        }
        if ((i2 & 2097152) != 0) {
            num21 = (Integer) null;
        }
        if ((i2 & 4194304) != 0) {
            httpVersion = (HttpVersion) null;
        }
        if ((i2 & 8388608) != 0) {
            proxyOptions = (ProxyOptions) null;
        }
        if ((i2 & 16777216) != 0) {
            iterable6 = (Iterable) null;
        }
        if ((i2 & 33554432) != 0) {
            num22 = (Integer) null;
        }
        if ((i2 & 67108864) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i2 & 134217728) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i2 & 268435456) != 0) {
            str11 = (String) null;
        }
        if ((i2 & 536870912) != 0) {
            str12 = (String) null;
        }
        if ((i2 & 1073741824) != 0) {
            num23 = (Integer) null;
        }
        if ((i2 & Integer.MIN_VALUE) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i3 & 1) != 0) {
            str13 = (String) null;
        }
        if ((i3 & 2) != 0) {
            num24 = (Integer) null;
        }
        if ((i3 & 4) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i3 & 8) != 0) {
            l = (Long) null;
        }
        if ((i3 & 16) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i3 & 32) != 0) {
            bool10 = (Boolean) null;
        }
        if ((i3 & 64) != 0) {
            bool11 = (Boolean) null;
        }
        if ((i3 & 128) != 0) {
            bool12 = (Boolean) null;
        }
        if ((i3 & 256) != 0) {
            bool13 = (Boolean) null;
        }
        if ((i3 & 512) != 0) {
            bool14 = (Boolean) null;
        }
        if ((i3 & 1024) != 0) {
            str14 = (String) null;
        }
        if ((i3 & 2048) != 0) {
            num25 = (Integer) null;
        }
        if ((i3 & 4096) != 0) {
            bool15 = (Boolean) null;
        }
        if ((i3 & 8192) != 0) {
            jksOptions2 = (JksOptions) null;
        }
        if ((i3 & 16384) != 0) {
            bool16 = (Boolean) null;
        }
        if ((i3 & 32768) != 0) {
            bool17 = (Boolean) null;
        }
        if ((i3 & 65536) != 0) {
            bool18 = (Boolean) null;
        }
        if ((i3 & 131072) != 0) {
            bool19 = (Boolean) null;
        }
        if ((i3 & 262144) != 0) {
            bool20 = (Boolean) null;
        }
        if ((i3 & Opcodes.ASM8) != 0) {
            bool21 = (Boolean) null;
        }
        if ((i3 & 1048576) != 0) {
            bool22 = (Boolean) null;
        }
        if ((i3 & 2097152) != 0) {
            bool23 = (Boolean) null;
        }
        if ((i3 & 4194304) != 0) {
            str15 = (String) null;
        }
        if ((i3 & 8388608) != 0) {
            jsonObject3 = (JsonObject) null;
        }
        if ((i3 & 16777216) != 0) {
            str16 = (String) null;
        }
        if ((i3 & 33554432) != 0) {
            bool24 = (Boolean) null;
        }
        if ((i3 & 67108864) != 0) {
            bool25 = (Boolean) null;
        }
        if ((i3 & 134217728) != 0) {
            bool26 = (Boolean) null;
        }
        if ((i3 & 268435456) != 0) {
            num26 = (Integer) null;
        }
        if ((i3 & 536870912) != 0) {
            bool27 = (Boolean) null;
        }
        if ((i3 & 1073741824) != 0) {
            bool28 = (Boolean) null;
        }
        if ((i3 & Integer.MIN_VALUE) != 0) {
            num27 = (Integer) null;
        }
        if ((i4 & 1) != 0) {
            bool29 = (Boolean) null;
        }
        return oAuth2ClientOptionsOf(iterable, str, str2, str3, str4, num, iterable2, iterable3, num2, str5, num3, iterable4, iterable5, jsonObject, oAuth2FlowType, bool, jsonObject2, bool2, num4, num5, num6, num7, num8, timeUnit, http2Settings, str6, jdkSSLEngineOptions, str7, jWTOptions, bool3, num9, jksOptions, str8, bool4, str9, num10, num11, num12, num13, num14, num15, num16, num17, num18, num19, str10, openSSLEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxOptions, pfxOptions2, bool5, num20, num21, httpVersion, proxyOptions, iterable6, num22, bool6, bool7, str11, str12, num23, bool8, str13, num24, bool9, l, timeUnit2, bool10, bool11, bool12, bool13, bool14, str14, num25, bool15, jksOptions2, bool16, bool17, bool18, bool19, bool20, bool21, bool22, bool23, str15, jsonObject3, str16, bool24, bool25, bool26, num26, bool27, bool28, num27, bool29);
    }

    @NotNull
    public static final OAuth2ClientOptions oAuth2ClientOptionsOf(@Nullable Iterable<? extends HttpVersion> iterable, @Nullable String authorizationPath, @Nullable String clientID, @Nullable String clientSecret, @Nullable String clientSecretParameterName, @Nullable Integer connectTimeout, @Nullable Iterable<String> iterable2, @Nullable Iterable<? extends Buffer> iterable3, @Nullable Integer decoderInitialBufferSize, @Nullable String defaultHost, @Nullable Integer defaultPort, @Nullable Iterable<String> iterable4, @Nullable Iterable<String> iterable5, @Nullable JsonObject extraParameters, @Nullable OAuth2FlowType flow, @Nullable Boolean forceSni, @Nullable JsonObject headers, @Nullable Boolean http2ClearTextUpgrade, @Nullable Integer http2ConnectionWindowSize, @Nullable Integer http2KeepAliveTimeout, @Nullable Integer http2MaxPoolSize, @Nullable Integer http2MultiplexingLimit, @Nullable Integer idleTimeout, @Nullable TimeUnit idleTimeoutUnit, @Nullable Http2Settings initialSettings, @Nullable String introspectionPath, @Nullable JdkSSLEngineOptions jdkSslEngineOptions, @Nullable String jwkPath, @Nullable JWTOptions jwtOptions, @Nullable Boolean keepAlive, @Nullable Integer keepAliveTimeout, @Nullable JksOptions keyStoreOptions, @Nullable String localAddress, @Nullable Boolean logActivity, @Nullable String logoutPath, @Nullable Integer maxChunkSize, @Nullable Integer maxHeaderSize, @Nullable Integer maxInitialLineLength, @Nullable Integer maxPoolSize, @Nullable Integer maxRedirects, @Nullable Integer maxWaitQueueSize, @Nullable Integer maxWebSocketFrameSize, @Nullable Integer maxWebSocketMessageSize, @Nullable Integer maxWebsocketFrameSize, @Nullable Integer maxWebsocketMessageSize, @Nullable String metricsName, @Nullable OpenSSLEngineOptions openSslEngineOptions, @Nullable PemKeyCertOptions pemKeyCertOptions, @Nullable PemTrustOptions pemTrustOptions, @Nullable PfxOptions pfxKeyCertOptions, @Nullable PfxOptions pfxTrustOptions, @Nullable Boolean pipelining, @Nullable Integer pipeliningLimit, @Nullable Integer poolCleanerPeriod, @Nullable HttpVersion protocolVersion, @Nullable ProxyOptions proxyOptions, @Nullable Iterable<? extends PubSecKeyOptions> iterable6, @Nullable Integer receiveBufferSize, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable String revocationPath, @Nullable String scopeSeparator, @Nullable Integer sendBufferSize, @Nullable Boolean sendUnmaskedFrames, @Nullable String site, @Nullable Integer soLinger, @Nullable Boolean ssl, @Nullable Long sslHandshakeTimeout, @Nullable TimeUnit sslHandshakeTimeoutUnit, @Nullable Boolean tcpCork, @Nullable Boolean tcpFastOpen, @Nullable Boolean tcpKeepAlive, @Nullable Boolean tcpNoDelay, @Nullable Boolean tcpQuickAck, @Nullable String tokenPath, @Nullable Integer trafficClass, @Nullable Boolean trustAll, @Nullable JksOptions trustStoreOptions, @Nullable Boolean tryUseCompression, @Nullable Boolean tryUsePerFrameWebSocketCompression, @Nullable Boolean tryUsePerFrameWebsocketCompression, @Nullable Boolean tryUsePerMessageWebSocketCompression, @Nullable Boolean tryUsePerMessageWebsocketCompression, @Nullable Boolean useAlpn, @Nullable Boolean useBasicAuthorizationHeader, @Nullable Boolean usePooledBuffers, @Nullable String userAgent, @Nullable JsonObject userInfoParameters, @Nullable String userInfoPath, @Nullable Boolean validateIssuer, @Nullable Boolean verifyHost, @Nullable Boolean webSocketCompressionAllowClientNoContext, @Nullable Integer webSocketCompressionLevel, @Nullable Boolean webSocketCompressionRequestServerNoContext, @Nullable Boolean websocketCompressionAllowClientNoContext, @Nullable Integer websocketCompressionLevel, @Nullable Boolean websocketCompressionRequestServerNoContext) {
        OAuth2ClientOptions $this$apply = new OAuth2ClientOptions();
        if (iterable != null) {
            $this$apply.setAlpnVersions(CollectionsKt.toList(iterable));
        }
        if (authorizationPath != null) {
            $this$apply.setAuthorizationPath(authorizationPath);
        }
        if (clientID != null) {
            $this$apply.setClientID(clientID);
        }
        if (clientSecret != null) {
            $this$apply.setClientSecret(clientSecret);
        }
        if (clientSecretParameterName != null) {
            $this$apply.setClientSecretParameterName(clientSecretParameterName);
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
        if (extraParameters != null) {
            $this$apply.setExtraParameters(extraParameters);
        }
        if (flow != null) {
            $this$apply.setFlow(flow);
        }
        if (forceSni != null) {
            $this$apply.setForceSni(forceSni.booleanValue());
        }
        if (headers != null) {
            $this$apply.setHeaders(headers);
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
        if (introspectionPath != null) {
            $this$apply.setIntrospectionPath(introspectionPath);
        }
        if (jdkSslEngineOptions != null) {
            $this$apply.setJdkSslEngineOptions(jdkSslEngineOptions);
        }
        if (jwkPath != null) {
            $this$apply.setJwkPath(jwkPath);
        }
        if (jwtOptions != null) {
            $this$apply.setJWTOptions(jwtOptions);
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
        if (logoutPath != null) {
            $this$apply.setLogoutPath(logoutPath);
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
        if (iterable6 != null) {
            $this$apply.setPubSecKeys(CollectionsKt.toList(iterable6));
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
        if (revocationPath != null) {
            $this$apply.setRevocationPath(revocationPath);
        }
        if (scopeSeparator != null) {
            $this$apply.setScopeSeparator(scopeSeparator);
        }
        if (sendBufferSize != null) {
            $this$apply.setSendBufferSize(sendBufferSize.intValue());
        }
        if (sendUnmaskedFrames != null) {
            $this$apply.setSendUnmaskedFrames(sendUnmaskedFrames.booleanValue());
        }
        if (site != null) {
            $this$apply.setSite(site);
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
        if (tokenPath != null) {
            $this$apply.setTokenPath(tokenPath);
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
        if (useBasicAuthorizationHeader != null) {
            $this$apply.setUseBasicAuthorizationHeader(useBasicAuthorizationHeader.booleanValue());
        }
        if (usePooledBuffers != null) {
            $this$apply.setUsePooledBuffers(usePooledBuffers.booleanValue());
        }
        if (userAgent != null) {
            $this$apply.setUserAgent(userAgent);
        }
        if (userInfoParameters != null) {
            $this$apply.setUserInfoParameters(userInfoParameters);
        }
        if (userInfoPath != null) {
            $this$apply.setUserInfoPath(userInfoPath);
        }
        if (validateIssuer != null) {
            $this$apply.setValidateIssuer(validateIssuer.booleanValue());
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

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "oAuth2ClientOptionsOf(alpnVersions, authorizationPath, clientID, clientSecret, clientSecretParameterName, connectTimeout, crlPaths, crlValues, decoderInitialBufferSize, defaultHost, defaultPort, enabledCipherSuites, enabledSecureTransportProtocols, extraParameters, flow, forceSni, headers, http2ClearTextUpgrade, http2ConnectionWindowSize, http2KeepAliveTimeout, http2MaxPoolSize, http2MultiplexingLimit, idleTimeout, idleTimeoutUnit, initialSettings, introspectionPath, jdkSslEngineOptions, jwkPath, jwtOptions, keepAlive, keepAliveTimeout, keyStoreOptions, localAddress, logActivity, logoutPath, maxChunkSize, maxHeaderSize, maxInitialLineLength, maxPoolSize, maxRedirects, maxWaitQueueSize, maxWebSocketFrameSize, maxWebSocketMessageSize, maxWebsocketFrameSize, maxWebsocketMessageSize, metricsName, openSslEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxKeyCertOptions, pfxTrustOptions, pipelining, pipeliningLimit, poolCleanerPeriod, protocolVersion, proxyOptions, pubSecKeys, receiveBufferSize, reuseAddress, reusePort, revocationPath, scopeSeparator, sendBufferSize, sendUnmaskedFrames, site, soLinger, ssl, sslHandshakeTimeout, sslHandshakeTimeoutUnit, tcpCork, tcpFastOpen, tcpKeepAlive, tcpNoDelay, tcpQuickAck, tokenPath, trafficClass, trustAll, trustStoreOptions, tryUseCompression, tryUsePerFrameWebSocketCompression, tryUsePerFrameWebsocketCompression, tryUsePerMessageWebSocketCompression, tryUsePerMessageWebsocketCompression, useAlpn, useBasicAuthorizationHeader, usePooledBuffers, userAgent, userInfoParameters, userInfoPath, validateIssuer, verifyHost, webSocketCompressionAllowClientNoContext, webSocketCompressionLevel, webSocketCompressionRequestServerNoContext, websocketCompressionAllowClientNoContext, websocketCompressionLevel, websocketCompressionRequestServerNoContext)"))
    @NotNull
    public static /* synthetic */ OAuth2ClientOptions OAuth2ClientOptions$default(Iterable iterable, String str, String str2, String str3, String str4, Integer num, Iterable iterable2, Iterable iterable3, Integer num2, String str5, Integer num3, Iterable iterable4, Iterable iterable5, JsonObject jsonObject, OAuth2FlowType oAuth2FlowType, Boolean bool, JsonObject jsonObject2, Boolean bool2, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, TimeUnit timeUnit, Http2Settings http2Settings, String str6, JdkSSLEngineOptions jdkSSLEngineOptions, String str7, JWTOptions jWTOptions, Boolean bool3, Integer num9, JksOptions jksOptions, String str8, Boolean bool4, String str9, Integer num10, Integer num11, Integer num12, Integer num13, Integer num14, Integer num15, Integer num16, Integer num17, Integer num18, Integer num19, String str10, OpenSSLEngineOptions openSSLEngineOptions, PemKeyCertOptions pemKeyCertOptions, PemTrustOptions pemTrustOptions, PfxOptions pfxOptions, PfxOptions pfxOptions2, Boolean bool5, Integer num20, Integer num21, HttpVersion httpVersion, ProxyOptions proxyOptions, Iterable iterable6, Integer num22, Boolean bool6, Boolean bool7, String str11, String str12, Integer num23, Boolean bool8, String str13, Integer num24, Boolean bool9, Long l, TimeUnit timeUnit2, Boolean bool10, Boolean bool11, Boolean bool12, Boolean bool13, Boolean bool14, String str14, Integer num25, Boolean bool15, JksOptions jksOptions2, Boolean bool16, Boolean bool17, Boolean bool18, Boolean bool19, Boolean bool20, Boolean bool21, Boolean bool22, Boolean bool23, String str15, JsonObject jsonObject3, String str16, Boolean bool24, Boolean bool25, Boolean bool26, Integer num26, Boolean bool27, Boolean bool28, Integer num27, Boolean bool29, int i, int i2, int i3, int i4, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            str3 = (String) null;
        }
        if ((i & 16) != 0) {
            str4 = (String) null;
        }
        if ((i & 32) != 0) {
            num = (Integer) null;
        }
        if ((i & 64) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 128) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 256) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 512) != 0) {
            str5 = (String) null;
        }
        if ((i & 1024) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 2048) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 4096) != 0) {
            iterable5 = (Iterable) null;
        }
        if ((i & 8192) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 16384) != 0) {
            oAuth2FlowType = (OAuth2FlowType) null;
        }
        if ((i & 32768) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 65536) != 0) {
            jsonObject2 = (JsonObject) null;
        }
        if ((i & 131072) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 262144) != 0) {
            num4 = (Integer) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 1048576) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 2097152) != 0) {
            num7 = (Integer) null;
        }
        if ((i & 4194304) != 0) {
            num8 = (Integer) null;
        }
        if ((i & 8388608) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 16777216) != 0) {
            http2Settings = (Http2Settings) null;
        }
        if ((i & 33554432) != 0) {
            str6 = (String) null;
        }
        if ((i & 67108864) != 0) {
            jdkSSLEngineOptions = (JdkSSLEngineOptions) null;
        }
        if ((i & 134217728) != 0) {
            str7 = (String) null;
        }
        if ((i & 268435456) != 0) {
            jWTOptions = (JWTOptions) null;
        }
        if ((i & 536870912) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 1073741824) != 0) {
            num9 = (Integer) null;
        }
        if ((i & Integer.MIN_VALUE) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i2 & 1) != 0) {
            str8 = (String) null;
        }
        if ((i2 & 2) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i2 & 4) != 0) {
            str9 = (String) null;
        }
        if ((i2 & 8) != 0) {
            num10 = (Integer) null;
        }
        if ((i2 & 16) != 0) {
            num11 = (Integer) null;
        }
        if ((i2 & 32) != 0) {
            num12 = (Integer) null;
        }
        if ((i2 & 64) != 0) {
            num13 = (Integer) null;
        }
        if ((i2 & 128) != 0) {
            num14 = (Integer) null;
        }
        if ((i2 & 256) != 0) {
            num15 = (Integer) null;
        }
        if ((i2 & 512) != 0) {
            num16 = (Integer) null;
        }
        if ((i2 & 1024) != 0) {
            num17 = (Integer) null;
        }
        if ((i2 & 2048) != 0) {
            num18 = (Integer) null;
        }
        if ((i2 & 4096) != 0) {
            num19 = (Integer) null;
        }
        if ((i2 & 8192) != 0) {
            str10 = (String) null;
        }
        if ((i2 & 16384) != 0) {
            openSSLEngineOptions = (OpenSSLEngineOptions) null;
        }
        if ((i2 & 32768) != 0) {
            pemKeyCertOptions = (PemKeyCertOptions) null;
        }
        if ((i2 & 65536) != 0) {
            pemTrustOptions = (PemTrustOptions) null;
        }
        if ((i2 & 131072) != 0) {
            pfxOptions = (PfxOptions) null;
        }
        if ((i2 & 262144) != 0) {
            pfxOptions2 = (PfxOptions) null;
        }
        if ((i2 & Opcodes.ASM8) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i2 & 1048576) != 0) {
            num20 = (Integer) null;
        }
        if ((i2 & 2097152) != 0) {
            num21 = (Integer) null;
        }
        if ((i2 & 4194304) != 0) {
            httpVersion = (HttpVersion) null;
        }
        if ((i2 & 8388608) != 0) {
            proxyOptions = (ProxyOptions) null;
        }
        if ((i2 & 16777216) != 0) {
            iterable6 = (Iterable) null;
        }
        if ((i2 & 33554432) != 0) {
            num22 = (Integer) null;
        }
        if ((i2 & 67108864) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i2 & 134217728) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i2 & 268435456) != 0) {
            str11 = (String) null;
        }
        if ((i2 & 536870912) != 0) {
            str12 = (String) null;
        }
        if ((i2 & 1073741824) != 0) {
            num23 = (Integer) null;
        }
        if ((i2 & Integer.MIN_VALUE) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i3 & 1) != 0) {
            str13 = (String) null;
        }
        if ((i3 & 2) != 0) {
            num24 = (Integer) null;
        }
        if ((i3 & 4) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i3 & 8) != 0) {
            l = (Long) null;
        }
        if ((i3 & 16) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i3 & 32) != 0) {
            bool10 = (Boolean) null;
        }
        if ((i3 & 64) != 0) {
            bool11 = (Boolean) null;
        }
        if ((i3 & 128) != 0) {
            bool12 = (Boolean) null;
        }
        if ((i3 & 256) != 0) {
            bool13 = (Boolean) null;
        }
        if ((i3 & 512) != 0) {
            bool14 = (Boolean) null;
        }
        if ((i3 & 1024) != 0) {
            str14 = (String) null;
        }
        if ((i3 & 2048) != 0) {
            num25 = (Integer) null;
        }
        if ((i3 & 4096) != 0) {
            bool15 = (Boolean) null;
        }
        if ((i3 & 8192) != 0) {
            jksOptions2 = (JksOptions) null;
        }
        if ((i3 & 16384) != 0) {
            bool16 = (Boolean) null;
        }
        if ((i3 & 32768) != 0) {
            bool17 = (Boolean) null;
        }
        if ((i3 & 65536) != 0) {
            bool18 = (Boolean) null;
        }
        if ((i3 & 131072) != 0) {
            bool19 = (Boolean) null;
        }
        if ((i3 & 262144) != 0) {
            bool20 = (Boolean) null;
        }
        if ((i3 & Opcodes.ASM8) != 0) {
            bool21 = (Boolean) null;
        }
        if ((i3 & 1048576) != 0) {
            bool22 = (Boolean) null;
        }
        if ((i3 & 2097152) != 0) {
            bool23 = (Boolean) null;
        }
        if ((i3 & 4194304) != 0) {
            str15 = (String) null;
        }
        if ((i3 & 8388608) != 0) {
            jsonObject3 = (JsonObject) null;
        }
        if ((i3 & 16777216) != 0) {
            str16 = (String) null;
        }
        if ((i3 & 33554432) != 0) {
            bool24 = (Boolean) null;
        }
        if ((i3 & 67108864) != 0) {
            bool25 = (Boolean) null;
        }
        if ((i3 & 134217728) != 0) {
            bool26 = (Boolean) null;
        }
        if ((i3 & 268435456) != 0) {
            num26 = (Integer) null;
        }
        if ((i3 & 536870912) != 0) {
            bool27 = (Boolean) null;
        }
        if ((i3 & 1073741824) != 0) {
            bool28 = (Boolean) null;
        }
        if ((i3 & Integer.MIN_VALUE) != 0) {
            num27 = (Integer) null;
        }
        if ((i4 & 1) != 0) {
            bool29 = (Boolean) null;
        }
        return OAuth2ClientOptions(iterable, str, str2, str3, str4, num, iterable2, iterable3, num2, str5, num3, iterable4, iterable5, jsonObject, oAuth2FlowType, bool, jsonObject2, bool2, num4, num5, num6, num7, num8, timeUnit, http2Settings, str6, jdkSSLEngineOptions, str7, jWTOptions, bool3, num9, jksOptions, str8, bool4, str9, num10, num11, num12, num13, num14, num15, num16, num17, num18, num19, str10, openSSLEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxOptions, pfxOptions2, bool5, num20, num21, httpVersion, proxyOptions, iterable6, num22, bool6, bool7, str11, str12, num23, bool8, str13, num24, bool9, l, timeUnit2, bool10, bool11, bool12, bool13, bool14, str14, num25, bool15, jksOptions2, bool16, bool17, bool18, bool19, bool20, bool21, bool22, bool23, str15, jsonObject3, str16, bool24, bool25, bool26, num26, bool27, bool28, num27, bool29);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "oAuth2ClientOptionsOf(alpnVersions, authorizationPath, clientID, clientSecret, clientSecretParameterName, connectTimeout, crlPaths, crlValues, decoderInitialBufferSize, defaultHost, defaultPort, enabledCipherSuites, enabledSecureTransportProtocols, extraParameters, flow, forceSni, headers, http2ClearTextUpgrade, http2ConnectionWindowSize, http2KeepAliveTimeout, http2MaxPoolSize, http2MultiplexingLimit, idleTimeout, idleTimeoutUnit, initialSettings, introspectionPath, jdkSslEngineOptions, jwkPath, jwtOptions, keepAlive, keepAliveTimeout, keyStoreOptions, localAddress, logActivity, logoutPath, maxChunkSize, maxHeaderSize, maxInitialLineLength, maxPoolSize, maxRedirects, maxWaitQueueSize, maxWebSocketFrameSize, maxWebSocketMessageSize, maxWebsocketFrameSize, maxWebsocketMessageSize, metricsName, openSslEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxKeyCertOptions, pfxTrustOptions, pipelining, pipeliningLimit, poolCleanerPeriod, protocolVersion, proxyOptions, pubSecKeys, receiveBufferSize, reuseAddress, reusePort, revocationPath, scopeSeparator, sendBufferSize, sendUnmaskedFrames, site, soLinger, ssl, sslHandshakeTimeout, sslHandshakeTimeoutUnit, tcpCork, tcpFastOpen, tcpKeepAlive, tcpNoDelay, tcpQuickAck, tokenPath, trafficClass, trustAll, trustStoreOptions, tryUseCompression, tryUsePerFrameWebSocketCompression, tryUsePerFrameWebsocketCompression, tryUsePerMessageWebSocketCompression, tryUsePerMessageWebsocketCompression, useAlpn, useBasicAuthorizationHeader, usePooledBuffers, userAgent, userInfoParameters, userInfoPath, validateIssuer, verifyHost, webSocketCompressionAllowClientNoContext, webSocketCompressionLevel, webSocketCompressionRequestServerNoContext, websocketCompressionAllowClientNoContext, websocketCompressionLevel, websocketCompressionRequestServerNoContext)"))
    @NotNull
    public static final OAuth2ClientOptions OAuth2ClientOptions(@Nullable Iterable<? extends HttpVersion> iterable, @Nullable String authorizationPath, @Nullable String clientID, @Nullable String clientSecret, @Nullable String clientSecretParameterName, @Nullable Integer connectTimeout, @Nullable Iterable<String> iterable2, @Nullable Iterable<? extends Buffer> iterable3, @Nullable Integer decoderInitialBufferSize, @Nullable String defaultHost, @Nullable Integer defaultPort, @Nullable Iterable<String> iterable4, @Nullable Iterable<String> iterable5, @Nullable JsonObject extraParameters, @Nullable OAuth2FlowType flow, @Nullable Boolean forceSni, @Nullable JsonObject headers, @Nullable Boolean http2ClearTextUpgrade, @Nullable Integer http2ConnectionWindowSize, @Nullable Integer http2KeepAliveTimeout, @Nullable Integer http2MaxPoolSize, @Nullable Integer http2MultiplexingLimit, @Nullable Integer idleTimeout, @Nullable TimeUnit idleTimeoutUnit, @Nullable Http2Settings initialSettings, @Nullable String introspectionPath, @Nullable JdkSSLEngineOptions jdkSslEngineOptions, @Nullable String jwkPath, @Nullable JWTOptions jwtOptions, @Nullable Boolean keepAlive, @Nullable Integer keepAliveTimeout, @Nullable JksOptions keyStoreOptions, @Nullable String localAddress, @Nullable Boolean logActivity, @Nullable String logoutPath, @Nullable Integer maxChunkSize, @Nullable Integer maxHeaderSize, @Nullable Integer maxInitialLineLength, @Nullable Integer maxPoolSize, @Nullable Integer maxRedirects, @Nullable Integer maxWaitQueueSize, @Nullable Integer maxWebSocketFrameSize, @Nullable Integer maxWebSocketMessageSize, @Nullable Integer maxWebsocketFrameSize, @Nullable Integer maxWebsocketMessageSize, @Nullable String metricsName, @Nullable OpenSSLEngineOptions openSslEngineOptions, @Nullable PemKeyCertOptions pemKeyCertOptions, @Nullable PemTrustOptions pemTrustOptions, @Nullable PfxOptions pfxKeyCertOptions, @Nullable PfxOptions pfxTrustOptions, @Nullable Boolean pipelining, @Nullable Integer pipeliningLimit, @Nullable Integer poolCleanerPeriod, @Nullable HttpVersion protocolVersion, @Nullable ProxyOptions proxyOptions, @Nullable Iterable<? extends PubSecKeyOptions> iterable6, @Nullable Integer receiveBufferSize, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable String revocationPath, @Nullable String scopeSeparator, @Nullable Integer sendBufferSize, @Nullable Boolean sendUnmaskedFrames, @Nullable String site, @Nullable Integer soLinger, @Nullable Boolean ssl, @Nullable Long sslHandshakeTimeout, @Nullable TimeUnit sslHandshakeTimeoutUnit, @Nullable Boolean tcpCork, @Nullable Boolean tcpFastOpen, @Nullable Boolean tcpKeepAlive, @Nullable Boolean tcpNoDelay, @Nullable Boolean tcpQuickAck, @Nullable String tokenPath, @Nullable Integer trafficClass, @Nullable Boolean trustAll, @Nullable JksOptions trustStoreOptions, @Nullable Boolean tryUseCompression, @Nullable Boolean tryUsePerFrameWebSocketCompression, @Nullable Boolean tryUsePerFrameWebsocketCompression, @Nullable Boolean tryUsePerMessageWebSocketCompression, @Nullable Boolean tryUsePerMessageWebsocketCompression, @Nullable Boolean useAlpn, @Nullable Boolean useBasicAuthorizationHeader, @Nullable Boolean usePooledBuffers, @Nullable String userAgent, @Nullable JsonObject userInfoParameters, @Nullable String userInfoPath, @Nullable Boolean validateIssuer, @Nullable Boolean verifyHost, @Nullable Boolean webSocketCompressionAllowClientNoContext, @Nullable Integer webSocketCompressionLevel, @Nullable Boolean webSocketCompressionRequestServerNoContext, @Nullable Boolean websocketCompressionAllowClientNoContext, @Nullable Integer websocketCompressionLevel, @Nullable Boolean websocketCompressionRequestServerNoContext) {
        OAuth2ClientOptions $this$apply = new OAuth2ClientOptions();
        if (iterable != null) {
            $this$apply.setAlpnVersions(CollectionsKt.toList(iterable));
        }
        if (authorizationPath != null) {
            $this$apply.setAuthorizationPath(authorizationPath);
        }
        if (clientID != null) {
            $this$apply.setClientID(clientID);
        }
        if (clientSecret != null) {
            $this$apply.setClientSecret(clientSecret);
        }
        if (clientSecretParameterName != null) {
            $this$apply.setClientSecretParameterName(clientSecretParameterName);
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
        if (extraParameters != null) {
            $this$apply.setExtraParameters(extraParameters);
        }
        if (flow != null) {
            $this$apply.setFlow(flow);
        }
        if (forceSni != null) {
            $this$apply.setForceSni(forceSni.booleanValue());
        }
        if (headers != null) {
            $this$apply.setHeaders(headers);
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
        if (introspectionPath != null) {
            $this$apply.setIntrospectionPath(introspectionPath);
        }
        if (jdkSslEngineOptions != null) {
            $this$apply.setJdkSslEngineOptions(jdkSslEngineOptions);
        }
        if (jwkPath != null) {
            $this$apply.setJwkPath(jwkPath);
        }
        if (jwtOptions != null) {
            $this$apply.setJWTOptions(jwtOptions);
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
        if (logoutPath != null) {
            $this$apply.setLogoutPath(logoutPath);
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
        if (iterable6 != null) {
            $this$apply.setPubSecKeys(CollectionsKt.toList(iterable6));
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
        if (revocationPath != null) {
            $this$apply.setRevocationPath(revocationPath);
        }
        if (scopeSeparator != null) {
            $this$apply.setScopeSeparator(scopeSeparator);
        }
        if (sendBufferSize != null) {
            $this$apply.setSendBufferSize(sendBufferSize.intValue());
        }
        if (sendUnmaskedFrames != null) {
            $this$apply.setSendUnmaskedFrames(sendUnmaskedFrames.booleanValue());
        }
        if (site != null) {
            $this$apply.setSite(site);
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
        if (tokenPath != null) {
            $this$apply.setTokenPath(tokenPath);
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
        if (useBasicAuthorizationHeader != null) {
            $this$apply.setUseBasicAuthorizationHeader(useBasicAuthorizationHeader.booleanValue());
        }
        if (usePooledBuffers != null) {
            $this$apply.setUsePooledBuffers(usePooledBuffers.booleanValue());
        }
        if (userAgent != null) {
            $this$apply.setUserAgent(userAgent);
        }
        if (userInfoParameters != null) {
            $this$apply.setUserInfoParameters(userInfoParameters);
        }
        if (userInfoPath != null) {
            $this$apply.setUserInfoPath(userInfoPath);
        }
        if (validateIssuer != null) {
            $this$apply.setValidateIssuer(validateIssuer.booleanValue());
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
