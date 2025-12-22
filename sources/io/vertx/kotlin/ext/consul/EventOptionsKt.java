package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.EventOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: EventOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a8\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0007\u001a6\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¨\u0006\b"}, d2 = {"EventOptions", "Lio/vertx/ext/consul/EventOptions;", "node", "", "payload", "service", "tag", "eventOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/EventOptionsKt.class */
public final class EventOptionsKt {
    @NotNull
    public static /* synthetic */ EventOptions eventOptionsOf$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            str3 = (String) null;
        }
        if ((i & 8) != 0) {
            str4 = (String) null;
        }
        return eventOptionsOf(str, str2, str3, str4);
    }

    @NotNull
    public static final EventOptions eventOptionsOf(@Nullable String node, @Nullable String payload, @Nullable String service, @Nullable String tag) {
        EventOptions $this$apply = new EventOptions();
        if (node != null) {
            $this$apply.setNode(node);
        }
        if (payload != null) {
            $this$apply.setPayload(payload);
        }
        if (service != null) {
            $this$apply.setService(service);
        }
        if (tag != null) {
            $this$apply.setTag(tag);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "eventOptionsOf(node, payload, service, tag)"))
    @NotNull
    public static /* synthetic */ EventOptions EventOptions$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            str3 = (String) null;
        }
        if ((i & 8) != 0) {
            str4 = (String) null;
        }
        return EventOptions(str, str2, str3, str4);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "eventOptionsOf(node, payload, service, tag)"))
    @NotNull
    public static final EventOptions EventOptions(@Nullable String node, @Nullable String payload, @Nullable String service, @Nullable String tag) {
        EventOptions $this$apply = new EventOptions();
        if (node != null) {
            $this$apply.setNode(node);
        }
        if (payload != null) {
            $this$apply.setPayload(payload);
        }
        if (service != null) {
            $this$apply.setService(service);
        }
        if (tag != null) {
            $this$apply.setTag(tag);
        }
        return $this$apply;
    }
}
