package io.vertx.kotlin.kafka.admin;

import io.vertx.kafka.admin.MemberAssignment;
import io.vertx.kafka.admin.MemberDescription;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MemberDescription.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a8\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u0007\u001a6\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¨\u0006\t"}, d2 = {"MemberDescription", "Lio/vertx/kafka/admin/MemberDescription;", "assignment", "Lio/vertx/kafka/admin/MemberAssignment;", "clientId", "", "consumerId", "host", "memberDescriptionOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/admin/MemberDescriptionKt.class */
public final class MemberDescriptionKt {
    @NotNull
    public static /* synthetic */ MemberDescription memberDescriptionOf$default(MemberAssignment memberAssignment, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            memberAssignment = (MemberAssignment) null;
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
        return memberDescriptionOf(memberAssignment, str, str2, str3);
    }

    @NotNull
    public static final MemberDescription memberDescriptionOf(@Nullable MemberAssignment assignment, @Nullable String clientId, @Nullable String consumerId, @Nullable String host) {
        MemberDescription $this$apply = new MemberDescription();
        if (assignment != null) {
            $this$apply.setAssignment(assignment);
        }
        if (clientId != null) {
            $this$apply.setClientId(clientId);
        }
        if (consumerId != null) {
            $this$apply.setConsumerId(consumerId);
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "memberDescriptionOf(assignment, clientId, consumerId, host)"))
    @NotNull
    public static /* synthetic */ MemberDescription MemberDescription$default(MemberAssignment memberAssignment, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            memberAssignment = (MemberAssignment) null;
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
        return MemberDescription(memberAssignment, str, str2, str3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "memberDescriptionOf(assignment, clientId, consumerId, host)"))
    @NotNull
    public static final MemberDescription MemberDescription(@Nullable MemberAssignment assignment, @Nullable String clientId, @Nullable String consumerId, @Nullable String host) {
        MemberDescription $this$apply = new MemberDescription();
        if (assignment != null) {
            $this$apply.setAssignment(assignment);
        }
        if (clientId != null) {
            $this$apply.setClientId(clientId);
        }
        if (consumerId != null) {
            $this$apply.setConsumerId(consumerId);
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        return $this$apply;
    }
}
