package io.vertx.kotlin.pgclient;

import io.vertx.pgclient.PgNotification;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PgNotification.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"PgNotification", "Lio/vertx/pgclient/PgNotification;", "channel", "", "payload", "processId", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lio/vertx/pgclient/PgNotification;", "pgNotificationOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/pgclient/PgNotificationKt.class */
public final class PgNotificationKt {
    @NotNull
    public static /* synthetic */ PgNotification pgNotificationOf$default(String str, String str2, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        return pgNotificationOf(str, str2, num);
    }

    @NotNull
    public static final PgNotification pgNotificationOf(@Nullable String channel, @Nullable String payload, @Nullable Integer processId) {
        PgNotification $this$apply = new PgNotification();
        if (channel != null) {
            $this$apply.setChannel(channel);
        }
        if (payload != null) {
            $this$apply.setPayload(payload);
        }
        if (processId != null) {
            $this$apply.setProcessId(processId.intValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "pgNotificationOf(channel, payload, processId)"))
    @NotNull
    public static /* synthetic */ PgNotification PgNotification$default(String str, String str2, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        return PgNotification(str, str2, num);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "pgNotificationOf(channel, payload, processId)"))
    @NotNull
    public static final PgNotification PgNotification(@Nullable String channel, @Nullable String payload, @Nullable Integer processId) {
        PgNotification $this$apply = new PgNotification();
        if (channel != null) {
            $this$apply.setChannel(channel);
        }
        if (payload != null) {
            $this$apply.setPayload(payload);
        }
        if (processId != null) {
            $this$apply.setProcessId(processId.intValue());
        }
        return $this$apply;
    }
}
