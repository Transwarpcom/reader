package io.vertx.kotlin.ext.healthchecks;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.healthchecks.Status;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Status.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"Status", "Lio/vertx/ext/healthchecks/Status;", "data", "Lio/vertx/core/json/JsonObject;", "ok", "", "procedureInError", "(Lio/vertx/core/json/JsonObject;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lio/vertx/ext/healthchecks/Status;", "statusOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/healthchecks/StatusKt.class */
public final class StatusKt {
    @NotNull
    public static /* synthetic */ Status statusOf$default(JsonObject jsonObject, Boolean bool, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            bool2 = (Boolean) null;
        }
        return statusOf(jsonObject, bool, bool2);
    }

    @NotNull
    public static final Status statusOf(@Nullable JsonObject data, @Nullable Boolean ok, @Nullable Boolean procedureInError) {
        Status $this$apply = new Status();
        if (data != null) {
            $this$apply.setData(data);
        }
        if (ok != null) {
            $this$apply.setOk(ok.booleanValue());
        }
        if (procedureInError != null) {
            $this$apply.setProcedureInError(procedureInError.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "statusOf(data, ok, procedureInError)"))
    @NotNull
    public static /* synthetic */ Status Status$default(JsonObject jsonObject, Boolean bool, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            bool2 = (Boolean) null;
        }
        return Status(jsonObject, bool, bool2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "statusOf(data, ok, procedureInError)"))
    @NotNull
    public static final Status Status(@Nullable JsonObject data, @Nullable Boolean ok, @Nullable Boolean procedureInError) {
        Status $this$apply = new Status();
        if (data != null) {
            $this$apply.setData(data);
        }
        if (ok != null) {
            $this$apply.setOk(ok.booleanValue());
        }
        if (procedureInError != null) {
            $this$apply.setProcedureInError(procedureInError.booleanValue());
        }
        return $this$apply;
    }
}
