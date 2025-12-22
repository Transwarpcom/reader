package io.vertx.kotlin.core.dns;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.dns.AddressResolverOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AddressResolverOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��4\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\b\u0004\u001aµ\u0001\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\r2\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00132\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0013H\u0007¢\u0006\u0002\u0010\u0015\u001a³\u0001\u0010\u0016\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\r2\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00132\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0013¢\u0006\u0002\u0010\u0015¨\u0006\u0017"}, d2 = {"AddressResolverOptions", "Lio/vertx/core/dns/AddressResolverOptions;", "cacheMaxTimeToLive", "", "cacheMinTimeToLive", "cacheNegativeTimeToLive", "hostsPath", "", "hostsValue", "Lio/vertx/core/buffer/Buffer;", "maxQueries", "ndots", "optResourceEnabled", "", "queryTimeout", "", "rdFlag", "rotateServers", "searchDomains", "", "servers", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lio/vertx/core/buffer/Buffer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Iterable;Ljava/lang/Iterable;)Lio/vertx/core/dns/AddressResolverOptions;", "addressResolverOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/dns/AddressResolverOptionsKt.class */
public final class AddressResolverOptionsKt {
    @NotNull
    public static /* synthetic */ AddressResolverOptions addressResolverOptionsOf$default(Integer num, Integer num2, Integer num3, String str, Buffer buffer, Integer num4, Integer num5, Boolean bool, Long l, Boolean bool2, Boolean bool3, Iterable iterable, Iterable iterable2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 4) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            buffer = (Buffer) null;
        }
        if ((i & 32) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 64) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 128) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 256) != 0) {
            l = (Long) null;
        }
        if ((i & 512) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 1024) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 2048) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4096) != 0) {
            iterable2 = (Iterable) null;
        }
        return addressResolverOptionsOf(num, num2, num3, str, buffer, num4, num5, bool, l, bool2, bool3, iterable, iterable2);
    }

    @NotNull
    public static final AddressResolverOptions addressResolverOptionsOf(@Nullable Integer cacheMaxTimeToLive, @Nullable Integer cacheMinTimeToLive, @Nullable Integer cacheNegativeTimeToLive, @Nullable String hostsPath, @Nullable Buffer hostsValue, @Nullable Integer maxQueries, @Nullable Integer ndots, @Nullable Boolean optResourceEnabled, @Nullable Long queryTimeout, @Nullable Boolean rdFlag, @Nullable Boolean rotateServers, @Nullable Iterable<String> iterable, @Nullable Iterable<String> iterable2) {
        AddressResolverOptions $this$apply = new AddressResolverOptions();
        if (cacheMaxTimeToLive != null) {
            $this$apply.setCacheMaxTimeToLive(cacheMaxTimeToLive.intValue());
        }
        if (cacheMinTimeToLive != null) {
            $this$apply.setCacheMinTimeToLive(cacheMinTimeToLive.intValue());
        }
        if (cacheNegativeTimeToLive != null) {
            $this$apply.setCacheNegativeTimeToLive(cacheNegativeTimeToLive.intValue());
        }
        if (hostsPath != null) {
            $this$apply.setHostsPath(hostsPath);
        }
        if (hostsValue != null) {
            $this$apply.setHostsValue(hostsValue);
        }
        if (maxQueries != null) {
            $this$apply.setMaxQueries(maxQueries.intValue());
        }
        if (ndots != null) {
            $this$apply.setNdots(ndots.intValue());
        }
        if (optResourceEnabled != null) {
            $this$apply.setOptResourceEnabled(optResourceEnabled.booleanValue());
        }
        if (queryTimeout != null) {
            $this$apply.setQueryTimeout(queryTimeout.longValue());
        }
        if (rdFlag != null) {
            $this$apply.setRdFlag(rdFlag.booleanValue());
        }
        if (rotateServers != null) {
            $this$apply.setRotateServers(rotateServers.booleanValue());
        }
        if (iterable != null) {
            $this$apply.setSearchDomains(CollectionsKt.toList(iterable));
        }
        if (iterable2 != null) {
            $this$apply.setServers(CollectionsKt.toList(iterable2));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "addressResolverOptionsOf(cacheMaxTimeToLive, cacheMinTimeToLive, cacheNegativeTimeToLive, hostsPath, hostsValue, maxQueries, ndots, optResourceEnabled, queryTimeout, rdFlag, rotateServers, searchDomains, servers)"))
    @NotNull
    public static /* synthetic */ AddressResolverOptions AddressResolverOptions$default(Integer num, Integer num2, Integer num3, String str, Buffer buffer, Integer num4, Integer num5, Boolean bool, Long l, Boolean bool2, Boolean bool3, Iterable iterable, Iterable iterable2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 4) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            buffer = (Buffer) null;
        }
        if ((i & 32) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 64) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 128) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 256) != 0) {
            l = (Long) null;
        }
        if ((i & 512) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 1024) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 2048) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4096) != 0) {
            iterable2 = (Iterable) null;
        }
        return AddressResolverOptions(num, num2, num3, str, buffer, num4, num5, bool, l, bool2, bool3, iterable, iterable2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "addressResolverOptionsOf(cacheMaxTimeToLive, cacheMinTimeToLive, cacheNegativeTimeToLive, hostsPath, hostsValue, maxQueries, ndots, optResourceEnabled, queryTimeout, rdFlag, rotateServers, searchDomains, servers)"))
    @NotNull
    public static final AddressResolverOptions AddressResolverOptions(@Nullable Integer cacheMaxTimeToLive, @Nullable Integer cacheMinTimeToLive, @Nullable Integer cacheNegativeTimeToLive, @Nullable String hostsPath, @Nullable Buffer hostsValue, @Nullable Integer maxQueries, @Nullable Integer ndots, @Nullable Boolean optResourceEnabled, @Nullable Long queryTimeout, @Nullable Boolean rdFlag, @Nullable Boolean rotateServers, @Nullable Iterable<String> iterable, @Nullable Iterable<String> iterable2) {
        AddressResolverOptions $this$apply = new AddressResolverOptions();
        if (cacheMaxTimeToLive != null) {
            $this$apply.setCacheMaxTimeToLive(cacheMaxTimeToLive.intValue());
        }
        if (cacheMinTimeToLive != null) {
            $this$apply.setCacheMinTimeToLive(cacheMinTimeToLive.intValue());
        }
        if (cacheNegativeTimeToLive != null) {
            $this$apply.setCacheNegativeTimeToLive(cacheNegativeTimeToLive.intValue());
        }
        if (hostsPath != null) {
            $this$apply.setHostsPath(hostsPath);
        }
        if (hostsValue != null) {
            $this$apply.setHostsValue(hostsValue);
        }
        if (maxQueries != null) {
            $this$apply.setMaxQueries(maxQueries.intValue());
        }
        if (ndots != null) {
            $this$apply.setNdots(ndots.intValue());
        }
        if (optResourceEnabled != null) {
            $this$apply.setOptResourceEnabled(optResourceEnabled.booleanValue());
        }
        if (queryTimeout != null) {
            $this$apply.setQueryTimeout(queryTimeout.longValue());
        }
        if (rdFlag != null) {
            $this$apply.setRdFlag(rdFlag.booleanValue());
        }
        if (rotateServers != null) {
            $this$apply.setRotateServers(rotateServers.booleanValue());
        }
        if (iterable != null) {
            $this$apply.setSearchDomains(CollectionsKt.toList(iterable));
        }
        if (iterable2 != null) {
            $this$apply.setServers(CollectionsKt.toList(iterable2));
        }
        return $this$apply;
    }
}
