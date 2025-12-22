package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.Event;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Event.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n\u0002\b\t\u001am\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\f\u001ak\u0010\r\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\f¨\u0006\u000e"}, d2 = {"Event", "Lio/vertx/ext/consul/Event;", "id", "", "lTime", "", "name", "node", "payload", "service", "tag", "version", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lio/vertx/ext/consul/Event;", "eventOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/EventKt.class */
public final class EventKt {
    @NotNull
    public static /* synthetic */ Event eventOf$default(String str, Integer num, String str2, String str3, String str4, String str5, String str6, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            num = (Integer) null;
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
            str5 = (String) null;
        }
        if ((i & 64) != 0) {
            str6 = (String) null;
        }
        if ((i & 128) != 0) {
            num2 = (Integer) null;
        }
        return eventOf(str, num, str2, str3, str4, str5, str6, num2);
    }

    @NotNull
    public static final Event eventOf(@Nullable String id, @Nullable Integer lTime, @Nullable String name, @Nullable String node, @Nullable String payload, @Nullable String service, @Nullable String tag, @Nullable Integer version) {
        Event $this$apply = new Event();
        if (id != null) {
            $this$apply.setId(id);
        }
        if (lTime != null) {
            $this$apply.setLTime(lTime.intValue());
        }
        if (name != null) {
            $this$apply.setName(name);
        }
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
        if (version != null) {
            $this$apply.setVersion(version.intValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "eventOf(id, lTime, name, node, payload, service, tag, version)"))
    @NotNull
    public static /* synthetic */ Event Event$default(String str, Integer num, String str2, String str3, String str4, String str5, String str6, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            num = (Integer) null;
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
            str5 = (String) null;
        }
        if ((i & 64) != 0) {
            str6 = (String) null;
        }
        if ((i & 128) != 0) {
            num2 = (Integer) null;
        }
        return Event(str, num, str2, str3, str4, str5, str6, num2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "eventOf(id, lTime, name, node, payload, service, tag, version)"))
    @NotNull
    public static final Event Event(@Nullable String id, @Nullable Integer lTime, @Nullable String name, @Nullable String node, @Nullable String payload, @Nullable String service, @Nullable String tag, @Nullable Integer version) {
        Event $this$apply = new Event();
        if (id != null) {
            $this$apply.setId(id);
        }
        if (lTime != null) {
            $this$apply.setLTime(lTime.intValue());
        }
        if (name != null) {
            $this$apply.setName(name);
        }
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
        if (version != null) {
            $this$apply.setVersion(version.intValue());
        }
        return $this$apply;
    }
}
