package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.MaintenanceOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MaintenanceOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"MaintenanceOptions", "Lio/vertx/ext/consul/MaintenanceOptions;", "enable", "", "id", "", "reason", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)Lio/vertx/ext/consul/MaintenanceOptions;", "maintenanceOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/MaintenanceOptionsKt.class */
public final class MaintenanceOptionsKt {
    @NotNull
    public static /* synthetic */ MaintenanceOptions maintenanceOptionsOf$default(Boolean bool, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        return maintenanceOptionsOf(bool, str, str2);
    }

    @NotNull
    public static final MaintenanceOptions maintenanceOptionsOf(@Nullable Boolean enable, @Nullable String id, @Nullable String reason) {
        MaintenanceOptions $this$apply = new MaintenanceOptions();
        if (enable != null) {
            $this$apply.setEnable(enable.booleanValue());
        }
        if (id != null) {
            $this$apply.setId(id);
        }
        if (reason != null) {
            $this$apply.setReason(reason);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "maintenanceOptionsOf(enable, id, reason)"))
    @NotNull
    public static /* synthetic */ MaintenanceOptions MaintenanceOptions$default(Boolean bool, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        return MaintenanceOptions(bool, str, str2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "maintenanceOptionsOf(enable, id, reason)"))
    @NotNull
    public static final MaintenanceOptions MaintenanceOptions(@Nullable Boolean enable, @Nullable String id, @Nullable String reason) {
        MaintenanceOptions $this$apply = new MaintenanceOptions();
        if (enable != null) {
            $this$apply.setEnable(enable.booleanValue());
        }
        if (id != null) {
            $this$apply.setId(id);
        }
        if (reason != null) {
            $this$apply.setReason(reason);
        }
        return $this$apply;
    }
}
