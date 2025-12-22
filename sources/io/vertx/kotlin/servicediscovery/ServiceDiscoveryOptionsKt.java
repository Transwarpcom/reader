package io.vertx.kotlin.servicediscovery;

import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.ServiceDiscoveryOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ServiceDiscoveryOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aI\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\n\u001aG\u0010\u000b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"ServiceDiscoveryOptions", "Lio/vertx/servicediscovery/ServiceDiscoveryOptions;", "announceAddress", "", "autoRegistrationOfImporters", "", "backendConfiguration", "Lio/vertx/core/json/JsonObject;", "name", "usageAddress", "(Ljava/lang/String;Ljava/lang/Boolean;Lio/vertx/core/json/JsonObject;Ljava/lang/String;Ljava/lang/String;)Lio/vertx/servicediscovery/ServiceDiscoveryOptions;", "serviceDiscoveryOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/ServiceDiscoveryOptionsKt.class */
public final class ServiceDiscoveryOptionsKt {
    @NotNull
    public static /* synthetic */ ServiceDiscoveryOptions serviceDiscoveryOptionsOf$default(String str, Boolean bool, JsonObject jsonObject, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        if ((i & 16) != 0) {
            str3 = (String) null;
        }
        return serviceDiscoveryOptionsOf(str, bool, jsonObject, str2, str3);
    }

    @NotNull
    public static final ServiceDiscoveryOptions serviceDiscoveryOptionsOf(@Nullable String announceAddress, @Nullable Boolean autoRegistrationOfImporters, @Nullable JsonObject backendConfiguration, @Nullable String name, @Nullable String usageAddress) {
        ServiceDiscoveryOptions $this$apply = new ServiceDiscoveryOptions();
        if (announceAddress != null) {
            $this$apply.setAnnounceAddress(announceAddress);
        }
        if (autoRegistrationOfImporters != null) {
            $this$apply.setAutoRegistrationOfImporters(autoRegistrationOfImporters.booleanValue());
        }
        if (backendConfiguration != null) {
            $this$apply.setBackendConfiguration(backendConfiguration);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (usageAddress != null) {
            $this$apply.setUsageAddress(usageAddress);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "serviceDiscoveryOptionsOf(announceAddress, autoRegistrationOfImporters, backendConfiguration, name, usageAddress)"))
    @NotNull
    public static /* synthetic */ ServiceDiscoveryOptions ServiceDiscoveryOptions$default(String str, Boolean bool, JsonObject jsonObject, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        if ((i & 16) != 0) {
            str3 = (String) null;
        }
        return ServiceDiscoveryOptions(str, bool, jsonObject, str2, str3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "serviceDiscoveryOptionsOf(announceAddress, autoRegistrationOfImporters, backendConfiguration, name, usageAddress)"))
    @NotNull
    public static final ServiceDiscoveryOptions ServiceDiscoveryOptions(@Nullable String announceAddress, @Nullable Boolean autoRegistrationOfImporters, @Nullable JsonObject backendConfiguration, @Nullable String name, @Nullable String usageAddress) {
        ServiceDiscoveryOptions $this$apply = new ServiceDiscoveryOptions();
        if (announceAddress != null) {
            $this$apply.setAnnounceAddress(announceAddress);
        }
        if (autoRegistrationOfImporters != null) {
            $this$apply.setAutoRegistrationOfImporters(autoRegistrationOfImporters.booleanValue());
        }
        if (backendConfiguration != null) {
            $this$apply.setBackendConfiguration(backendConfiguration);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (usageAddress != null) {
            $this$apply.setUsageAddress(usageAddress);
        }
        return $this$apply;
    }
}
