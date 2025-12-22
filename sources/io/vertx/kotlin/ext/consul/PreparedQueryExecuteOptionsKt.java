package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.PreparedQueryExecuteOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PreparedQueryExecuteOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"PreparedQueryExecuteOptions", "Lio/vertx/ext/consul/PreparedQueryExecuteOptions;", "limit", "", "near", "", "(Ljava/lang/Integer;Ljava/lang/String;)Lio/vertx/ext/consul/PreparedQueryExecuteOptions;", "preparedQueryExecuteOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/PreparedQueryExecuteOptionsKt.class */
public final class PreparedQueryExecuteOptionsKt {
    @NotNull
    public static /* synthetic */ PreparedQueryExecuteOptions preparedQueryExecuteOptionsOf$default(Integer num, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return preparedQueryExecuteOptionsOf(num, str);
    }

    @NotNull
    public static final PreparedQueryExecuteOptions preparedQueryExecuteOptionsOf(@Nullable Integer limit, @Nullable String near) {
        PreparedQueryExecuteOptions $this$apply = new PreparedQueryExecuteOptions();
        if (limit != null) {
            $this$apply.setLimit(limit.intValue());
        }
        if (near != null) {
            $this$apply.setNear(near);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "preparedQueryExecuteOptionsOf(limit, near)"))
    @NotNull
    public static /* synthetic */ PreparedQueryExecuteOptions PreparedQueryExecuteOptions$default(Integer num, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return PreparedQueryExecuteOptions(num, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "preparedQueryExecuteOptionsOf(limit, near)"))
    @NotNull
    public static final PreparedQueryExecuteOptions PreparedQueryExecuteOptions(@Nullable Integer limit, @Nullable String near) {
        PreparedQueryExecuteOptions $this$apply = new PreparedQueryExecuteOptions();
        if (limit != null) {
            $this$apply.setLimit(limit.intValue());
        }
        if (near != null) {
            $this$apply.setNear(near);
        }
        return $this$apply;
    }
}
