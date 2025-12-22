package io.vertx.kotlin.mysqlclient;

import ch.qos.logback.classic.ClassicConstants;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import io.vertx.core.net.ProxyOptions;
import io.vertx.ext.web.handler.FormLoginHandler;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.SslMode;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;

/* compiled from: MySQLConnectOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��x\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0010\u001c\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000f\u001aÅ\u0005\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\t2\u0016\b\u0002\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010+2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\t2\n\b\u0002\u00100\u001a\u0004\u0018\u0001012\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00104\u001a\u0004\u0018\u00010\t2\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00106\u001a\u0004\u0018\u00010\r2\n\b\u0002\u00107\u001a\u0004\u0018\u00010\t2\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00109\u001a\u0004\u0018\u0001012\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010;\u001a\u0004\u0018\u00010<2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010I\u001aÃ\u0005\u0010J\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\t2\u0016\b\u0002\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010+2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\t2\n\b\u0002\u00100\u001a\u0004\u0018\u0001012\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00104\u001a\u0004\u0018\u00010\t2\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00106\u001a\u0004\u0018\u00010\r2\n\b\u0002\u00107\u001a\u0004\u0018\u00010\t2\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00109\u001a\u0004\u0018\u0001012\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010;\u001a\u0004\u0018\u00010<2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010I¨\u0006K"}, d2 = {"MySQLConnectOptions", "Lio/vertx/mysqlclient/MySQLConnectOptions;", "cachePreparedStatements", "", "characterEncoding", "", "charset", "collation", "connectTimeout", "", "crlPaths", "", "crlValues", "Lio/vertx/core/buffer/Buffer;", "database", "enabledCipherSuites", "enabledSecureTransportProtocols", "host", "hostnameVerificationAlgorithm", "idleTimeout", "idleTimeoutUnit", "Ljava/util/concurrent/TimeUnit;", "jdkSslEngineOptions", "Lio/vertx/core/net/JdkSSLEngineOptions;", "keyStoreOptions", "Lio/vertx/core/net/JksOptions;", "localAddress", "logActivity", "metricsName", "openSslEngineOptions", "Lio/vertx/core/net/OpenSSLEngineOptions;", FormLoginHandler.DEFAULT_PASSWORD_PARAM, "pemKeyCertOptions", "Lio/vertx/core/net/PemKeyCertOptions;", "pemTrustOptions", "Lio/vertx/core/net/PemTrustOptions;", "pfxKeyCertOptions", "Lio/vertx/core/net/PfxOptions;", "pfxTrustOptions", RtspHeaders.Values.PORT, "preparedStatementCacheMaxSize", "preparedStatementCacheSqlLimit", PackageDocumentBase.OPFAttributes.properties, "", "proxyOptions", "Lio/vertx/core/net/ProxyOptions;", "receiveBufferSize", "reconnectAttempts", "reconnectInterval", "", "reuseAddress", "reusePort", "sendBufferSize", "serverRsaPublicKeyPath", "serverRsaPublicKeyValue", "soLinger", "ssl", "sslHandshakeTimeout", "sslHandshakeTimeoutUnit", "sslMode", "Lio/vertx/mysqlclient/SslMode;", "tcpCork", "tcpFastOpen", "tcpKeepAlive", "tcpNoDelay", "tcpQuickAck", "trafficClass", "trustAll", "trustStoreOptions", "useAffectedRows", "useAlpn", "usePooledBuffers", ClassicConstants.USER_MDC_KEY, "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/String;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/concurrent/TimeUnit;Lio/vertx/core/net/JdkSSLEngineOptions;Lio/vertx/core/net/JksOptions;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Lio/vertx/core/net/OpenSSLEngineOptions;Ljava/lang/String;Lio/vertx/core/net/PemKeyCertOptions;Lio/vertx/core/net/PemTrustOptions;Lio/vertx/core/net/PfxOptions;Lio/vertx/core/net/PfxOptions;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/Map;Lio/vertx/core/net/ProxyOptions;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Lio/vertx/core/buffer/Buffer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;Lio/vertx/mysqlclient/SslMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Lio/vertx/core/net/JksOptions;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;)Lio/vertx/mysqlclient/MySQLConnectOptions;", "mySQLConnectOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/mysqlclient/MySQLConnectOptionsKt.class */
public final class MySQLConnectOptionsKt {
    @NotNull
    public static /* synthetic */ MySQLConnectOptions mySQLConnectOptionsOf$default(Boolean bool, String str, String str2, String str3, Integer num, Iterable iterable, Iterable iterable2, String str4, Iterable iterable3, Iterable iterable4, String str5, String str6, Integer num2, TimeUnit timeUnit, JdkSSLEngineOptions jdkSSLEngineOptions, JksOptions jksOptions, String str7, Boolean bool2, String str8, OpenSSLEngineOptions openSSLEngineOptions, String str9, PemKeyCertOptions pemKeyCertOptions, PemTrustOptions pemTrustOptions, PfxOptions pfxOptions, PfxOptions pfxOptions2, Integer num3, Integer num4, Integer num5, Map map, ProxyOptions proxyOptions, Integer num6, Integer num7, Long l, Boolean bool3, Boolean bool4, Integer num8, String str10, Buffer buffer, Integer num9, Boolean bool5, Long l2, TimeUnit timeUnit2, SslMode sslMode, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10, Integer num10, Boolean bool11, JksOptions jksOptions2, Boolean bool12, Boolean bool13, Boolean bool14, String str11, int i, int i2, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
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
            num = (Integer) null;
        }
        if ((i & 32) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 64) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 128) != 0) {
            str4 = (String) null;
        }
        if ((i & 256) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 512) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 1024) != 0) {
            str5 = (String) null;
        }
        if ((i & 2048) != 0) {
            str6 = (String) null;
        }
        if ((i & 4096) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 8192) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 16384) != 0) {
            jdkSSLEngineOptions = (JdkSSLEngineOptions) null;
        }
        if ((i & 32768) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i & 65536) != 0) {
            str7 = (String) null;
        }
        if ((i & 131072) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 262144) != 0) {
            str8 = (String) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            openSSLEngineOptions = (OpenSSLEngineOptions) null;
        }
        if ((i & 1048576) != 0) {
            str9 = (String) null;
        }
        if ((i & 2097152) != 0) {
            pemKeyCertOptions = (PemKeyCertOptions) null;
        }
        if ((i & 4194304) != 0) {
            pemTrustOptions = (PemTrustOptions) null;
        }
        if ((i & 8388608) != 0) {
            pfxOptions = (PfxOptions) null;
        }
        if ((i & 16777216) != 0) {
            pfxOptions2 = (PfxOptions) null;
        }
        if ((i & 33554432) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 67108864) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 134217728) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 268435456) != 0) {
            map = (Map) null;
        }
        if ((i & 536870912) != 0) {
            proxyOptions = (ProxyOptions) null;
        }
        if ((i & 1073741824) != 0) {
            num6 = (Integer) null;
        }
        if ((i & Integer.MIN_VALUE) != 0) {
            num7 = (Integer) null;
        }
        if ((i2 & 1) != 0) {
            l = (Long) null;
        }
        if ((i2 & 2) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i2 & 4) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i2 & 8) != 0) {
            num8 = (Integer) null;
        }
        if ((i2 & 16) != 0) {
            str10 = (String) null;
        }
        if ((i2 & 32) != 0) {
            buffer = (Buffer) null;
        }
        if ((i2 & 64) != 0) {
            num9 = (Integer) null;
        }
        if ((i2 & 128) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i2 & 256) != 0) {
            l2 = (Long) null;
        }
        if ((i2 & 512) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i2 & 1024) != 0) {
            sslMode = (SslMode) null;
        }
        if ((i2 & 2048) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i2 & 4096) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i2 & 8192) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i2 & 16384) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i2 & 32768) != 0) {
            bool10 = (Boolean) null;
        }
        if ((i2 & 65536) != 0) {
            num10 = (Integer) null;
        }
        if ((i2 & 131072) != 0) {
            bool11 = (Boolean) null;
        }
        if ((i2 & 262144) != 0) {
            jksOptions2 = (JksOptions) null;
        }
        if ((i2 & Opcodes.ASM8) != 0) {
            bool12 = (Boolean) null;
        }
        if ((i2 & 1048576) != 0) {
            bool13 = (Boolean) null;
        }
        if ((i2 & 2097152) != 0) {
            bool14 = (Boolean) null;
        }
        if ((i2 & 4194304) != 0) {
            str11 = (String) null;
        }
        return mySQLConnectOptionsOf(bool, str, str2, str3, num, iterable, iterable2, str4, iterable3, iterable4, str5, str6, num2, timeUnit, jdkSSLEngineOptions, jksOptions, str7, bool2, str8, openSSLEngineOptions, str9, pemKeyCertOptions, pemTrustOptions, pfxOptions, pfxOptions2, num3, num4, num5, map, proxyOptions, num6, num7, l, bool3, bool4, num8, str10, buffer, num9, bool5, l2, timeUnit2, sslMode, bool6, bool7, bool8, bool9, bool10, num10, bool11, jksOptions2, bool12, bool13, bool14, str11);
    }

    @NotNull
    public static final MySQLConnectOptions mySQLConnectOptionsOf(@Nullable Boolean cachePreparedStatements, @Nullable String characterEncoding, @Nullable String charset, @Nullable String collation, @Nullable Integer connectTimeout, @Nullable Iterable<String> iterable, @Nullable Iterable<? extends Buffer> iterable2, @Nullable String database, @Nullable Iterable<String> iterable3, @Nullable Iterable<String> iterable4, @Nullable String host, @Nullable String hostnameVerificationAlgorithm, @Nullable Integer idleTimeout, @Nullable TimeUnit idleTimeoutUnit, @Nullable JdkSSLEngineOptions jdkSslEngineOptions, @Nullable JksOptions keyStoreOptions, @Nullable String localAddress, @Nullable Boolean logActivity, @Nullable String metricsName, @Nullable OpenSSLEngineOptions openSslEngineOptions, @Nullable String password, @Nullable PemKeyCertOptions pemKeyCertOptions, @Nullable PemTrustOptions pemTrustOptions, @Nullable PfxOptions pfxKeyCertOptions, @Nullable PfxOptions pfxTrustOptions, @Nullable Integer port, @Nullable Integer preparedStatementCacheMaxSize, @Nullable Integer preparedStatementCacheSqlLimit, @Nullable Map<String, String> map, @Nullable ProxyOptions proxyOptions, @Nullable Integer receiveBufferSize, @Nullable Integer reconnectAttempts, @Nullable Long reconnectInterval, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable Integer sendBufferSize, @Nullable String serverRsaPublicKeyPath, @Nullable Buffer serverRsaPublicKeyValue, @Nullable Integer soLinger, @Nullable Boolean ssl, @Nullable Long sslHandshakeTimeout, @Nullable TimeUnit sslHandshakeTimeoutUnit, @Nullable SslMode sslMode, @Nullable Boolean tcpCork, @Nullable Boolean tcpFastOpen, @Nullable Boolean tcpKeepAlive, @Nullable Boolean tcpNoDelay, @Nullable Boolean tcpQuickAck, @Nullable Integer trafficClass, @Nullable Boolean trustAll, @Nullable JksOptions trustStoreOptions, @Nullable Boolean useAffectedRows, @Nullable Boolean useAlpn, @Nullable Boolean usePooledBuffers, @Nullable String user) {
        MySQLConnectOptions $this$apply = new MySQLConnectOptions();
        if (cachePreparedStatements != null) {
            $this$apply.setCachePreparedStatements(cachePreparedStatements.booleanValue());
        }
        if (characterEncoding != null) {
            $this$apply.setCharacterEncoding(characterEncoding);
        }
        if (charset != null) {
            $this$apply.setCharset(charset);
        }
        if (collation != null) {
            $this$apply.setCollation(collation);
        }
        if (connectTimeout != null) {
            $this$apply.setConnectTimeout(connectTimeout.intValue());
        }
        if (iterable != null) {
            for (String item : iterable) {
                $this$apply.addCrlPath(item);
            }
        }
        if (iterable2 != null) {
            for (Buffer item2 : iterable2) {
                $this$apply.addCrlValue(item2);
            }
        }
        if (database != null) {
            $this$apply.setDatabase(database);
        }
        if (iterable3 != null) {
            for (String item3 : iterable3) {
                $this$apply.addEnabledCipherSuite(item3);
            }
        }
        if (iterable4 != null) {
            $this$apply.setEnabledSecureTransportProtocols(CollectionsKt.toSet(iterable4));
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (hostnameVerificationAlgorithm != null) {
            $this$apply.setHostnameVerificationAlgorithm(hostnameVerificationAlgorithm);
        }
        if (idleTimeout != null) {
            $this$apply.setIdleTimeout(idleTimeout.intValue());
        }
        if (idleTimeoutUnit != null) {
            $this$apply.setIdleTimeoutUnit(idleTimeoutUnit);
        }
        if (jdkSslEngineOptions != null) {
            $this$apply.setJdkSslEngineOptions(jdkSslEngineOptions);
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
        if (metricsName != null) {
            $this$apply.setMetricsName(metricsName);
        }
        if (openSslEngineOptions != null) {
            $this$apply.setOpenSslEngineOptions(openSslEngineOptions);
        }
        if (password != null) {
            $this$apply.setPassword(password);
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
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (preparedStatementCacheMaxSize != null) {
            $this$apply.setPreparedStatementCacheMaxSize(preparedStatementCacheMaxSize.intValue());
        }
        if (preparedStatementCacheSqlLimit != null) {
            $this$apply.setPreparedStatementCacheSqlLimit(preparedStatementCacheSqlLimit.intValue());
        }
        if (map != null) {
            $this$apply.setProperties(map);
        }
        if (proxyOptions != null) {
            $this$apply.setProxyOptions(proxyOptions);
        }
        if (receiveBufferSize != null) {
            $this$apply.setReceiveBufferSize(receiveBufferSize.intValue());
        }
        if (reconnectAttempts != null) {
            $this$apply.setReconnectAttempts(reconnectAttempts.intValue());
        }
        if (reconnectInterval != null) {
            $this$apply.setReconnectInterval(reconnectInterval.longValue());
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
        if (serverRsaPublicKeyPath != null) {
            $this$apply.setServerRsaPublicKeyPath(serverRsaPublicKeyPath);
        }
        if (serverRsaPublicKeyValue != null) {
            $this$apply.setServerRsaPublicKeyValue(serverRsaPublicKeyValue);
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
        if (sslMode != null) {
            $this$apply.setSslMode(sslMode);
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
        if (useAffectedRows != null) {
            $this$apply.setUseAffectedRows(useAffectedRows.booleanValue());
        }
        if (useAlpn != null) {
            $this$apply.setUseAlpn(useAlpn.booleanValue());
        }
        if (usePooledBuffers != null) {
            $this$apply.setUsePooledBuffers(usePooledBuffers.booleanValue());
        }
        if (user != null) {
            $this$apply.setUser(user);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "mySQLConnectOptionsOf(cachePreparedStatements, characterEncoding, charset, collation, connectTimeout, crlPaths, crlValues, database, enabledCipherSuites, enabledSecureTransportProtocols, host, hostnameVerificationAlgorithm, idleTimeout, idleTimeoutUnit, jdkSslEngineOptions, keyStoreOptions, localAddress, logActivity, metricsName, openSslEngineOptions, password, pemKeyCertOptions, pemTrustOptions, pfxKeyCertOptions, pfxTrustOptions, port, preparedStatementCacheMaxSize, preparedStatementCacheSqlLimit, properties, proxyOptions, receiveBufferSize, reconnectAttempts, reconnectInterval, reuseAddress, reusePort, sendBufferSize, serverRsaPublicKeyPath, serverRsaPublicKeyValue, soLinger, ssl, sslHandshakeTimeout, sslHandshakeTimeoutUnit, sslMode, tcpCork, tcpFastOpen, tcpKeepAlive, tcpNoDelay, tcpQuickAck, trafficClass, trustAll, trustStoreOptions, useAffectedRows, useAlpn, usePooledBuffers, user)"))
    @NotNull
    public static /* synthetic */ MySQLConnectOptions MySQLConnectOptions$default(Boolean bool, String str, String str2, String str3, Integer num, Iterable iterable, Iterable iterable2, String str4, Iterable iterable3, Iterable iterable4, String str5, String str6, Integer num2, TimeUnit timeUnit, JdkSSLEngineOptions jdkSSLEngineOptions, JksOptions jksOptions, String str7, Boolean bool2, String str8, OpenSSLEngineOptions openSSLEngineOptions, String str9, PemKeyCertOptions pemKeyCertOptions, PemTrustOptions pemTrustOptions, PfxOptions pfxOptions, PfxOptions pfxOptions2, Integer num3, Integer num4, Integer num5, Map map, ProxyOptions proxyOptions, Integer num6, Integer num7, Long l, Boolean bool3, Boolean bool4, Integer num8, String str10, Buffer buffer, Integer num9, Boolean bool5, Long l2, TimeUnit timeUnit2, SslMode sslMode, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10, Integer num10, Boolean bool11, JksOptions jksOptions2, Boolean bool12, Boolean bool13, Boolean bool14, String str11, int i, int i2, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
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
            num = (Integer) null;
        }
        if ((i & 32) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 64) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 128) != 0) {
            str4 = (String) null;
        }
        if ((i & 256) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 512) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 1024) != 0) {
            str5 = (String) null;
        }
        if ((i & 2048) != 0) {
            str6 = (String) null;
        }
        if ((i & 4096) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 8192) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 16384) != 0) {
            jdkSSLEngineOptions = (JdkSSLEngineOptions) null;
        }
        if ((i & 32768) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i & 65536) != 0) {
            str7 = (String) null;
        }
        if ((i & 131072) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 262144) != 0) {
            str8 = (String) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            openSSLEngineOptions = (OpenSSLEngineOptions) null;
        }
        if ((i & 1048576) != 0) {
            str9 = (String) null;
        }
        if ((i & 2097152) != 0) {
            pemKeyCertOptions = (PemKeyCertOptions) null;
        }
        if ((i & 4194304) != 0) {
            pemTrustOptions = (PemTrustOptions) null;
        }
        if ((i & 8388608) != 0) {
            pfxOptions = (PfxOptions) null;
        }
        if ((i & 16777216) != 0) {
            pfxOptions2 = (PfxOptions) null;
        }
        if ((i & 33554432) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 67108864) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 134217728) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 268435456) != 0) {
            map = (Map) null;
        }
        if ((i & 536870912) != 0) {
            proxyOptions = (ProxyOptions) null;
        }
        if ((i & 1073741824) != 0) {
            num6 = (Integer) null;
        }
        if ((i & Integer.MIN_VALUE) != 0) {
            num7 = (Integer) null;
        }
        if ((i2 & 1) != 0) {
            l = (Long) null;
        }
        if ((i2 & 2) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i2 & 4) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i2 & 8) != 0) {
            num8 = (Integer) null;
        }
        if ((i2 & 16) != 0) {
            str10 = (String) null;
        }
        if ((i2 & 32) != 0) {
            buffer = (Buffer) null;
        }
        if ((i2 & 64) != 0) {
            num9 = (Integer) null;
        }
        if ((i2 & 128) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i2 & 256) != 0) {
            l2 = (Long) null;
        }
        if ((i2 & 512) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i2 & 1024) != 0) {
            sslMode = (SslMode) null;
        }
        if ((i2 & 2048) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i2 & 4096) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i2 & 8192) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i2 & 16384) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i2 & 32768) != 0) {
            bool10 = (Boolean) null;
        }
        if ((i2 & 65536) != 0) {
            num10 = (Integer) null;
        }
        if ((i2 & 131072) != 0) {
            bool11 = (Boolean) null;
        }
        if ((i2 & 262144) != 0) {
            jksOptions2 = (JksOptions) null;
        }
        if ((i2 & Opcodes.ASM8) != 0) {
            bool12 = (Boolean) null;
        }
        if ((i2 & 1048576) != 0) {
            bool13 = (Boolean) null;
        }
        if ((i2 & 2097152) != 0) {
            bool14 = (Boolean) null;
        }
        if ((i2 & 4194304) != 0) {
            str11 = (String) null;
        }
        return MySQLConnectOptions(bool, str, str2, str3, num, iterable, iterable2, str4, iterable3, iterable4, str5, str6, num2, timeUnit, jdkSSLEngineOptions, jksOptions, str7, bool2, str8, openSSLEngineOptions, str9, pemKeyCertOptions, pemTrustOptions, pfxOptions, pfxOptions2, num3, num4, num5, map, proxyOptions, num6, num7, l, bool3, bool4, num8, str10, buffer, num9, bool5, l2, timeUnit2, sslMode, bool6, bool7, bool8, bool9, bool10, num10, bool11, jksOptions2, bool12, bool13, bool14, str11);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "mySQLConnectOptionsOf(cachePreparedStatements, characterEncoding, charset, collation, connectTimeout, crlPaths, crlValues, database, enabledCipherSuites, enabledSecureTransportProtocols, host, hostnameVerificationAlgorithm, idleTimeout, idleTimeoutUnit, jdkSslEngineOptions, keyStoreOptions, localAddress, logActivity, metricsName, openSslEngineOptions, password, pemKeyCertOptions, pemTrustOptions, pfxKeyCertOptions, pfxTrustOptions, port, preparedStatementCacheMaxSize, preparedStatementCacheSqlLimit, properties, proxyOptions, receiveBufferSize, reconnectAttempts, reconnectInterval, reuseAddress, reusePort, sendBufferSize, serverRsaPublicKeyPath, serverRsaPublicKeyValue, soLinger, ssl, sslHandshakeTimeout, sslHandshakeTimeoutUnit, sslMode, tcpCork, tcpFastOpen, tcpKeepAlive, tcpNoDelay, tcpQuickAck, trafficClass, trustAll, trustStoreOptions, useAffectedRows, useAlpn, usePooledBuffers, user)"))
    @NotNull
    public static final MySQLConnectOptions MySQLConnectOptions(@Nullable Boolean cachePreparedStatements, @Nullable String characterEncoding, @Nullable String charset, @Nullable String collation, @Nullable Integer connectTimeout, @Nullable Iterable<String> iterable, @Nullable Iterable<? extends Buffer> iterable2, @Nullable String database, @Nullable Iterable<String> iterable3, @Nullable Iterable<String> iterable4, @Nullable String host, @Nullable String hostnameVerificationAlgorithm, @Nullable Integer idleTimeout, @Nullable TimeUnit idleTimeoutUnit, @Nullable JdkSSLEngineOptions jdkSslEngineOptions, @Nullable JksOptions keyStoreOptions, @Nullable String localAddress, @Nullable Boolean logActivity, @Nullable String metricsName, @Nullable OpenSSLEngineOptions openSslEngineOptions, @Nullable String password, @Nullable PemKeyCertOptions pemKeyCertOptions, @Nullable PemTrustOptions pemTrustOptions, @Nullable PfxOptions pfxKeyCertOptions, @Nullable PfxOptions pfxTrustOptions, @Nullable Integer port, @Nullable Integer preparedStatementCacheMaxSize, @Nullable Integer preparedStatementCacheSqlLimit, @Nullable Map<String, String> map, @Nullable ProxyOptions proxyOptions, @Nullable Integer receiveBufferSize, @Nullable Integer reconnectAttempts, @Nullable Long reconnectInterval, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable Integer sendBufferSize, @Nullable String serverRsaPublicKeyPath, @Nullable Buffer serverRsaPublicKeyValue, @Nullable Integer soLinger, @Nullable Boolean ssl, @Nullable Long sslHandshakeTimeout, @Nullable TimeUnit sslHandshakeTimeoutUnit, @Nullable SslMode sslMode, @Nullable Boolean tcpCork, @Nullable Boolean tcpFastOpen, @Nullable Boolean tcpKeepAlive, @Nullable Boolean tcpNoDelay, @Nullable Boolean tcpQuickAck, @Nullable Integer trafficClass, @Nullable Boolean trustAll, @Nullable JksOptions trustStoreOptions, @Nullable Boolean useAffectedRows, @Nullable Boolean useAlpn, @Nullable Boolean usePooledBuffers, @Nullable String user) {
        MySQLConnectOptions $this$apply = new MySQLConnectOptions();
        if (cachePreparedStatements != null) {
            $this$apply.setCachePreparedStatements(cachePreparedStatements.booleanValue());
        }
        if (characterEncoding != null) {
            $this$apply.setCharacterEncoding(characterEncoding);
        }
        if (charset != null) {
            $this$apply.setCharset(charset);
        }
        if (collation != null) {
            $this$apply.setCollation(collation);
        }
        if (connectTimeout != null) {
            $this$apply.setConnectTimeout(connectTimeout.intValue());
        }
        if (iterable != null) {
            for (String item : iterable) {
                $this$apply.addCrlPath(item);
            }
        }
        if (iterable2 != null) {
            for (Buffer item2 : iterable2) {
                $this$apply.addCrlValue(item2);
            }
        }
        if (database != null) {
            $this$apply.setDatabase(database);
        }
        if (iterable3 != null) {
            for (String item3 : iterable3) {
                $this$apply.addEnabledCipherSuite(item3);
            }
        }
        if (iterable4 != null) {
            $this$apply.setEnabledSecureTransportProtocols(CollectionsKt.toSet(iterable4));
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (hostnameVerificationAlgorithm != null) {
            $this$apply.setHostnameVerificationAlgorithm(hostnameVerificationAlgorithm);
        }
        if (idleTimeout != null) {
            $this$apply.setIdleTimeout(idleTimeout.intValue());
        }
        if (idleTimeoutUnit != null) {
            $this$apply.setIdleTimeoutUnit(idleTimeoutUnit);
        }
        if (jdkSslEngineOptions != null) {
            $this$apply.setJdkSslEngineOptions(jdkSslEngineOptions);
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
        if (metricsName != null) {
            $this$apply.setMetricsName(metricsName);
        }
        if (openSslEngineOptions != null) {
            $this$apply.setOpenSslEngineOptions(openSslEngineOptions);
        }
        if (password != null) {
            $this$apply.setPassword(password);
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
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (preparedStatementCacheMaxSize != null) {
            $this$apply.setPreparedStatementCacheMaxSize(preparedStatementCacheMaxSize.intValue());
        }
        if (preparedStatementCacheSqlLimit != null) {
            $this$apply.setPreparedStatementCacheSqlLimit(preparedStatementCacheSqlLimit.intValue());
        }
        if (map != null) {
            $this$apply.setProperties(map);
        }
        if (proxyOptions != null) {
            $this$apply.setProxyOptions(proxyOptions);
        }
        if (receiveBufferSize != null) {
            $this$apply.setReceiveBufferSize(receiveBufferSize.intValue());
        }
        if (reconnectAttempts != null) {
            $this$apply.setReconnectAttempts(reconnectAttempts.intValue());
        }
        if (reconnectInterval != null) {
            $this$apply.setReconnectInterval(reconnectInterval.longValue());
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
        if (serverRsaPublicKeyPath != null) {
            $this$apply.setServerRsaPublicKeyPath(serverRsaPublicKeyPath);
        }
        if (serverRsaPublicKeyValue != null) {
            $this$apply.setServerRsaPublicKeyValue(serverRsaPublicKeyValue);
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
        if (sslMode != null) {
            $this$apply.setSslMode(sslMode);
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
        if (useAffectedRows != null) {
            $this$apply.setUseAffectedRows(useAffectedRows.booleanValue());
        }
        if (useAlpn != null) {
            $this$apply.setUseAlpn(useAlpn.booleanValue());
        }
        if (usePooledBuffers != null) {
            $this$apply.setUsePooledBuffers(usePooledBuffers.booleanValue());
        }
        if (user != null) {
            $this$apply.setUser(user);
        }
        return $this$apply;
    }
}
