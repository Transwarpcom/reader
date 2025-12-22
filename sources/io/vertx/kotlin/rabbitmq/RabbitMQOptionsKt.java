package io.vertx.kotlin.rabbitmq;

import ch.qos.logback.classic.ClassicConstants;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.ext.web.handler.FormLoginHandler;
import io.vertx.rabbitmq.RabbitMQOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RabbitMQOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��$\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\b\n��\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\u001aÁ\u0001\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0002\u0010\u0015\u001a¿\u0001\u0010\u0016\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\u0015¨\u0006\u0017"}, d2 = {"RabbitMQOptions", "Lio/vertx/rabbitmq/RabbitMQOptions;", "automaticRecoveryEnabled", "", "connectionRetries", "", "connectionRetryDelay", "", "connectionTimeout", "handshakeTimeout", "host", "", "includeProperties", "networkRecoveryInterval", FormLoginHandler.DEFAULT_PASSWORD_PARAM, RtspHeaders.Values.PORT, "requestedChannelMax", "requestedHeartbeat", "uri", ClassicConstants.USER_MDC_KEY, "virtualHost", "(Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lio/vertx/rabbitmq/RabbitMQOptions;", "rabbitMQOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/rabbitmq/RabbitMQOptionsKt.class */
public final class RabbitMQOptionsKt {
    @NotNull
    public static /* synthetic */ RabbitMQOptions rabbitMQOptionsOf$default(Boolean bool, Integer num, Long l, Integer num2, Integer num3, String str, Boolean bool2, Long l2, String str2, Integer num4, Integer num5, Integer num6, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            num = (Integer) null;
        }
        if ((i & 4) != 0) {
            l = (Long) null;
        }
        if ((i & 8) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 16) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 32) != 0) {
            str = (String) null;
        }
        if ((i & 64) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 128) != 0) {
            l2 = (Long) null;
        }
        if ((i & 256) != 0) {
            str2 = (String) null;
        }
        if ((i & 512) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 1024) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 2048) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 4096) != 0) {
            str3 = (String) null;
        }
        if ((i & 8192) != 0) {
            str4 = (String) null;
        }
        if ((i & 16384) != 0) {
            str5 = (String) null;
        }
        return rabbitMQOptionsOf(bool, num, l, num2, num3, str, bool2, l2, str2, num4, num5, num6, str3, str4, str5);
    }

    @NotNull
    public static final RabbitMQOptions rabbitMQOptionsOf(@Nullable Boolean automaticRecoveryEnabled, @Nullable Integer connectionRetries, @Nullable Long connectionRetryDelay, @Nullable Integer connectionTimeout, @Nullable Integer handshakeTimeout, @Nullable String host, @Nullable Boolean includeProperties, @Nullable Long networkRecoveryInterval, @Nullable String password, @Nullable Integer port, @Nullable Integer requestedChannelMax, @Nullable Integer requestedHeartbeat, @Nullable String uri, @Nullable String user, @Nullable String virtualHost) {
        RabbitMQOptions $this$apply = new RabbitMQOptions();
        if (automaticRecoveryEnabled != null) {
            $this$apply.setAutomaticRecoveryEnabled(automaticRecoveryEnabled.booleanValue());
        }
        if (connectionRetries != null) {
            $this$apply.setConnectionRetries(connectionRetries);
        }
        if (connectionRetryDelay != null) {
            $this$apply.setConnectionRetryDelay(connectionRetryDelay.longValue());
        }
        if (connectionTimeout != null) {
            $this$apply.setConnectionTimeout(connectionTimeout.intValue());
        }
        if (handshakeTimeout != null) {
            $this$apply.setHandshakeTimeout(handshakeTimeout.intValue());
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (includeProperties != null) {
            $this$apply.setIncludeProperties(includeProperties.booleanValue());
        }
        if (networkRecoveryInterval != null) {
            $this$apply.setNetworkRecoveryInterval(networkRecoveryInterval.longValue());
        }
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (requestedChannelMax != null) {
            $this$apply.setRequestedChannelMax(requestedChannelMax.intValue());
        }
        if (requestedHeartbeat != null) {
            $this$apply.setRequestedHeartbeat(requestedHeartbeat.intValue());
        }
        if (uri != null) {
            $this$apply.setUri(uri);
        }
        if (user != null) {
            $this$apply.setUser(user);
        }
        if (virtualHost != null) {
            $this$apply.setVirtualHost(virtualHost);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "rabbitMQOptionsOf(automaticRecoveryEnabled, connectionRetries, connectionRetryDelay, connectionTimeout, handshakeTimeout, host, includeProperties, networkRecoveryInterval, password, port, requestedChannelMax, requestedHeartbeat, uri, user, virtualHost)"))
    @NotNull
    public static /* synthetic */ RabbitMQOptions RabbitMQOptions$default(Boolean bool, Integer num, Long l, Integer num2, Integer num3, String str, Boolean bool2, Long l2, String str2, Integer num4, Integer num5, Integer num6, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            num = (Integer) null;
        }
        if ((i & 4) != 0) {
            l = (Long) null;
        }
        if ((i & 8) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 16) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 32) != 0) {
            str = (String) null;
        }
        if ((i & 64) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 128) != 0) {
            l2 = (Long) null;
        }
        if ((i & 256) != 0) {
            str2 = (String) null;
        }
        if ((i & 512) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 1024) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 2048) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 4096) != 0) {
            str3 = (String) null;
        }
        if ((i & 8192) != 0) {
            str4 = (String) null;
        }
        if ((i & 16384) != 0) {
            str5 = (String) null;
        }
        return RabbitMQOptions(bool, num, l, num2, num3, str, bool2, l2, str2, num4, num5, num6, str3, str4, str5);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "rabbitMQOptionsOf(automaticRecoveryEnabled, connectionRetries, connectionRetryDelay, connectionTimeout, handshakeTimeout, host, includeProperties, networkRecoveryInterval, password, port, requestedChannelMax, requestedHeartbeat, uri, user, virtualHost)"))
    @NotNull
    public static final RabbitMQOptions RabbitMQOptions(@Nullable Boolean automaticRecoveryEnabled, @Nullable Integer connectionRetries, @Nullable Long connectionRetryDelay, @Nullable Integer connectionTimeout, @Nullable Integer handshakeTimeout, @Nullable String host, @Nullable Boolean includeProperties, @Nullable Long networkRecoveryInterval, @Nullable String password, @Nullable Integer port, @Nullable Integer requestedChannelMax, @Nullable Integer requestedHeartbeat, @Nullable String uri, @Nullable String user, @Nullable String virtualHost) {
        RabbitMQOptions $this$apply = new RabbitMQOptions();
        if (automaticRecoveryEnabled != null) {
            $this$apply.setAutomaticRecoveryEnabled(automaticRecoveryEnabled.booleanValue());
        }
        if (connectionRetries != null) {
            $this$apply.setConnectionRetries(connectionRetries);
        }
        if (connectionRetryDelay != null) {
            $this$apply.setConnectionRetryDelay(connectionRetryDelay.longValue());
        }
        if (connectionTimeout != null) {
            $this$apply.setConnectionTimeout(connectionTimeout.intValue());
        }
        if (handshakeTimeout != null) {
            $this$apply.setHandshakeTimeout(handshakeTimeout.intValue());
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (includeProperties != null) {
            $this$apply.setIncludeProperties(includeProperties.booleanValue());
        }
        if (networkRecoveryInterval != null) {
            $this$apply.setNetworkRecoveryInterval(networkRecoveryInterval.longValue());
        }
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (requestedChannelMax != null) {
            $this$apply.setRequestedChannelMax(requestedChannelMax.intValue());
        }
        if (requestedHeartbeat != null) {
            $this$apply.setRequestedHeartbeat(requestedHeartbeat.intValue());
        }
        if (uri != null) {
            $this$apply.setUri(uri);
        }
        if (user != null) {
            $this$apply.setUser(user);
        }
        if (virtualHost != null) {
            $this$apply.setVirtualHost(virtualHost);
        }
        return $this$apply;
    }
}
