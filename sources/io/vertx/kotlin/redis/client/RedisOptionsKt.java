package io.vertx.kotlin.redis.client;

import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.ext.web.handler.FormLoginHandler;
import io.vertx.redis.client.RedisClientType;
import io.vertx.redis.client.RedisOptions;
import io.vertx.redis.client.RedisRole;
import io.vertx.redis.client.RedisSlaves;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RedisOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��@\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0097\u0001\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0007¢\u0006\u0002\u0010\u0015\u001a\u0095\u0001\u0010\u0016\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015¨\u0006\u0017"}, d2 = {"RedisOptions", "Lio/vertx/redis/client/RedisOptions;", "endpoint", "Lio/vertx/core/net/SocketAddress;", "endpoints", "", "masterName", "", "maxNestedArrays", "", "maxWaitingHandlers", "netClientOptions", "Lio/vertx/core/net/NetClientOptions;", FormLoginHandler.DEFAULT_PASSWORD_PARAM, "role", "Lio/vertx/redis/client/RedisRole;", "select", "type", "Lio/vertx/redis/client/RedisClientType;", "useSlave", "Lio/vertx/redis/client/RedisSlaves;", "(Lio/vertx/core/net/SocketAddress;Ljava/lang/Iterable;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lio/vertx/core/net/NetClientOptions;Ljava/lang/String;Lio/vertx/redis/client/RedisRole;Ljava/lang/Integer;Lio/vertx/redis/client/RedisClientType;Lio/vertx/redis/client/RedisSlaves;)Lio/vertx/redis/client/RedisOptions;", "redisOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/client/RedisOptionsKt.class */
public final class RedisOptionsKt {
    @NotNull
    public static /* synthetic */ RedisOptions redisOptionsOf$default(SocketAddress socketAddress, Iterable iterable, String str, Integer num, Integer num2, NetClientOptions netClientOptions, String str2, RedisRole redisRole, Integer num3, RedisClientType redisClientType, RedisSlaves redisSlaves, int i, Object obj) {
        if ((i & 1) != 0) {
            socketAddress = (SocketAddress) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            num = (Integer) null;
        }
        if ((i & 16) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 32) != 0) {
            netClientOptions = (NetClientOptions) null;
        }
        if ((i & 64) != 0) {
            str2 = (String) null;
        }
        if ((i & 128) != 0) {
            redisRole = (RedisRole) null;
        }
        if ((i & 256) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 512) != 0) {
            redisClientType = (RedisClientType) null;
        }
        if ((i & 1024) != 0) {
            redisSlaves = (RedisSlaves) null;
        }
        return redisOptionsOf(socketAddress, iterable, str, num, num2, netClientOptions, str2, redisRole, num3, redisClientType, redisSlaves);
    }

    @NotNull
    public static final RedisOptions redisOptionsOf(@Nullable SocketAddress endpoint, @Nullable Iterable<? extends SocketAddress> iterable, @Nullable String masterName, @Nullable Integer maxNestedArrays, @Nullable Integer maxWaitingHandlers, @Nullable NetClientOptions netClientOptions, @Nullable String password, @Nullable RedisRole role, @Nullable Integer select, @Nullable RedisClientType type, @Nullable RedisSlaves useSlave) {
        RedisOptions $this$apply = new RedisOptions();
        if (endpoint != null) {
            $this$apply.setEndpoint(endpoint);
        }
        if (iterable != null) {
            $this$apply.setEndpoints(CollectionsKt.toList(iterable));
        }
        if (masterName != null) {
            $this$apply.setMasterName(masterName);
        }
        if (maxNestedArrays != null) {
            $this$apply.setMaxNestedArrays(maxNestedArrays.intValue());
        }
        if (maxWaitingHandlers != null) {
            $this$apply.setMaxWaitingHandlers(maxWaitingHandlers.intValue());
        }
        if (netClientOptions != null) {
            $this$apply.setNetClientOptions(netClientOptions);
        }
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (role != null) {
            $this$apply.setRole(role);
        }
        if (select != null) {
            $this$apply.setSelect(select);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        if (useSlave != null) {
            $this$apply.setUseSlave(useSlave);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "redisOptionsOf(endpoint, endpoints, masterName, maxNestedArrays, maxWaitingHandlers, netClientOptions, password, role, select, type, useSlave)"))
    @NotNull
    public static /* synthetic */ RedisOptions RedisOptions$default(SocketAddress socketAddress, Iterable iterable, String str, Integer num, Integer num2, NetClientOptions netClientOptions, String str2, RedisRole redisRole, Integer num3, RedisClientType redisClientType, RedisSlaves redisSlaves, int i, Object obj) {
        if ((i & 1) != 0) {
            socketAddress = (SocketAddress) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            num = (Integer) null;
        }
        if ((i & 16) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 32) != 0) {
            netClientOptions = (NetClientOptions) null;
        }
        if ((i & 64) != 0) {
            str2 = (String) null;
        }
        if ((i & 128) != 0) {
            redisRole = (RedisRole) null;
        }
        if ((i & 256) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 512) != 0) {
            redisClientType = (RedisClientType) null;
        }
        if ((i & 1024) != 0) {
            redisSlaves = (RedisSlaves) null;
        }
        return RedisOptions(socketAddress, iterable, str, num, num2, netClientOptions, str2, redisRole, num3, redisClientType, redisSlaves);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "redisOptionsOf(endpoint, endpoints, masterName, maxNestedArrays, maxWaitingHandlers, netClientOptions, password, role, select, type, useSlave)"))
    @NotNull
    public static final RedisOptions RedisOptions(@Nullable SocketAddress endpoint, @Nullable Iterable<? extends SocketAddress> iterable, @Nullable String masterName, @Nullable Integer maxNestedArrays, @Nullable Integer maxWaitingHandlers, @Nullable NetClientOptions netClientOptions, @Nullable String password, @Nullable RedisRole role, @Nullable Integer select, @Nullable RedisClientType type, @Nullable RedisSlaves useSlave) {
        RedisOptions $this$apply = new RedisOptions();
        if (endpoint != null) {
            $this$apply.setEndpoint(endpoint);
        }
        if (iterable != null) {
            $this$apply.setEndpoints(CollectionsKt.toList(iterable));
        }
        if (masterName != null) {
            $this$apply.setMasterName(masterName);
        }
        if (maxNestedArrays != null) {
            $this$apply.setMaxNestedArrays(maxNestedArrays.intValue());
        }
        if (maxWaitingHandlers != null) {
            $this$apply.setMaxWaitingHandlers(maxWaitingHandlers.intValue());
        }
        if (netClientOptions != null) {
            $this$apply.setNetClientOptions(netClientOptions);
        }
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (role != null) {
            $this$apply.setRole(role);
        }
        if (select != null) {
            $this$apply.setSelect(select);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        if (useSlave != null) {
            $this$apply.setUseSlave(useSlave);
        }
        return $this$apply;
    }
}
