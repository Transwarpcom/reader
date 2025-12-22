package io.vertx.kotlin.core.http;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.GoAway;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: GoAway.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\b\n\u0002\b\u0003\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\b\u001a/\u0010\t\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"GoAway", "Lio/vertx/core/http/GoAway;", "debugData", "Lio/vertx/core/buffer/Buffer;", "errorCode", "", "lastStreamId", "", "(Lio/vertx/core/buffer/Buffer;Ljava/lang/Long;Ljava/lang/Integer;)Lio/vertx/core/http/GoAway;", "goAwayOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/http/GoAwayKt.class */
public final class GoAwayKt {
    @NotNull
    public static /* synthetic */ GoAway goAwayOf$default(Buffer buffer, Long l, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            buffer = (Buffer) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        return goAwayOf(buffer, l, num);
    }

    @NotNull
    public static final GoAway goAwayOf(@Nullable Buffer debugData, @Nullable Long errorCode, @Nullable Integer lastStreamId) {
        GoAway $this$apply = new GoAway();
        if (debugData != null) {
            $this$apply.setDebugData(debugData);
        }
        if (errorCode != null) {
            $this$apply.setErrorCode(errorCode.longValue());
        }
        if (lastStreamId != null) {
            $this$apply.setLastStreamId(lastStreamId.intValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "goAwayOf(debugData, errorCode, lastStreamId)"))
    @NotNull
    public static /* synthetic */ GoAway GoAway$default(Buffer buffer, Long l, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            buffer = (Buffer) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        return GoAway(buffer, l, num);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "goAwayOf(debugData, errorCode, lastStreamId)"))
    @NotNull
    public static final GoAway GoAway(@Nullable Buffer debugData, @Nullable Long errorCode, @Nullable Integer lastStreamId) {
        GoAway $this$apply = new GoAway();
        if (debugData != null) {
            $this$apply.setDebugData(debugData);
        }
        if (errorCode != null) {
            $this$apply.setErrorCode(errorCode.longValue());
        }
        if (lastStreamId != null) {
            $this$apply.setLastStreamId(lastStreamId.intValue());
        }
        return $this$apply;
    }
}
