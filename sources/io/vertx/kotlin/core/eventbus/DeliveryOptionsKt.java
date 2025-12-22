package io.vertx.kotlin.core.eventbus;

import io.vertx.core.eventbus.DeliveryOptions;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeliveryOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\"\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010$\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\t\n\u0002\b\u0003\u001aI\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\n\u001aG\u0010\u000b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"DeliveryOptions", "Lio/vertx/core/eventbus/DeliveryOptions;", "codecName", "", "headers", "", "localOnly", "", "sendTimeout", "", "(Ljava/lang/String;Ljava/util/Map;Ljava/lang/Boolean;Ljava/lang/Long;)Lio/vertx/core/eventbus/DeliveryOptions;", "deliveryOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/eventbus/DeliveryOptionsKt.class */
public final class DeliveryOptionsKt {
    @NotNull
    public static /* synthetic */ DeliveryOptions deliveryOptionsOf$default(String str, Map map, Boolean bool, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            map = (Map) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            l = (Long) null;
        }
        return deliveryOptionsOf(str, map, bool, l);
    }

    @NotNull
    public static final DeliveryOptions deliveryOptionsOf(@Nullable String codecName, @Nullable Map<String, String> map, @Nullable Boolean localOnly, @Nullable Long sendTimeout) {
        DeliveryOptions $this$apply = new DeliveryOptions();
        if (codecName != null) {
            $this$apply.setCodecName(codecName);
        }
        if (map != null) {
            for (Map.Entry item : map.entrySet()) {
                $this$apply.addHeader(item.getKey(), item.getValue());
            }
        }
        if (localOnly != null) {
            $this$apply.setLocalOnly(localOnly.booleanValue());
        }
        if (sendTimeout != null) {
            $this$apply.setSendTimeout(sendTimeout.longValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "deliveryOptionsOf(codecName, headers, localOnly, sendTimeout)"))
    @NotNull
    public static /* synthetic */ DeliveryOptions DeliveryOptions$default(String str, Map map, Boolean bool, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            map = (Map) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            l = (Long) null;
        }
        return DeliveryOptions(str, map, bool, l);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "deliveryOptionsOf(codecName, headers, localOnly, sendTimeout)"))
    @NotNull
    public static final DeliveryOptions DeliveryOptions(@Nullable String codecName, @Nullable Map<String, String> map, @Nullable Boolean localOnly, @Nullable Long sendTimeout) {
        DeliveryOptions $this$apply = new DeliveryOptions();
        if (codecName != null) {
            $this$apply.setCodecName(codecName);
        }
        if (map != null) {
            for (Map.Entry item : map.entrySet()) {
                $this$apply.addHeader(item.getKey(), item.getValue());
            }
        }
        if (localOnly != null) {
            $this$apply.setLocalOnly(localOnly.booleanValue());
        }
        if (sendTimeout != null) {
            $this$apply.setSendTimeout(sendTimeout.longValue());
        }
        return $this$apply;
    }
}
