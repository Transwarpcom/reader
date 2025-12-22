package io.vertx.kotlin.redis.op;

import io.vertx.redis.op.LimitOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LimitOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0004\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0005\u001a#\u0010\u0006\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0007"}, d2 = {"LimitOptions", "Lio/vertx/redis/op/LimitOptions;", "count", "", "offset", "(Ljava/lang/Long;Ljava/lang/Long;)Lio/vertx/redis/op/LimitOptions;", "limitOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/op/LimitOptionsKt.class */
public final class LimitOptionsKt {
    @NotNull
    public static /* synthetic */ LimitOptions limitOptionsOf$default(Long l, Long l2, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            l2 = (Long) null;
        }
        return limitOptionsOf(l, l2);
    }

    @NotNull
    public static final LimitOptions limitOptionsOf(@Nullable Long count, @Nullable Long offset) {
        LimitOptions $this$apply = new LimitOptions();
        if (count != null) {
            $this$apply.setCount(count);
        }
        if (offset != null) {
            $this$apply.setOffset(offset);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "limitOptionsOf(count, offset)"))
    @NotNull
    public static /* synthetic */ LimitOptions LimitOptions$default(Long l, Long l2, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            l2 = (Long) null;
        }
        return LimitOptions(l, l2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "limitOptionsOf(count, offset)"))
    @NotNull
    public static final LimitOptions LimitOptions(@Nullable Long count, @Nullable Long offset) {
        LimitOptions $this$apply = new LimitOptions();
        if (count != null) {
            $this$apply.setCount(count);
        }
        if (offset != null) {
            $this$apply.setOffset(offset);
        }
        return $this$apply;
    }
}
