package io.vertx.kotlin.redis.op;

import io.vertx.redis.op.RangeLimitOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RangeLimitOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"RangeLimitOptions", "Lio/vertx/redis/op/RangeLimitOptions;", "count", "", "offset", "withscores", "", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;)Lio/vertx/redis/op/RangeLimitOptions;", "rangeLimitOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/op/RangeLimitOptionsKt.class */
public final class RangeLimitOptionsKt {
    @NotNull
    public static /* synthetic */ RangeLimitOptions rangeLimitOptionsOf$default(Long l, Long l2, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            l2 = (Long) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        return rangeLimitOptionsOf(l, l2, bool);
    }

    @NotNull
    public static final RangeLimitOptions rangeLimitOptionsOf(@Nullable Long count, @Nullable Long offset, @Nullable Boolean withscores) {
        RangeLimitOptions $this$apply = new RangeLimitOptions();
        if (count != null) {
            $this$apply.setCount(count);
        }
        if (offset != null) {
            $this$apply.setOffset(offset);
        }
        if (withscores != null) {
            $this$apply.setWithscores(withscores);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "rangeLimitOptionsOf(count, offset, withscores)"))
    @NotNull
    public static /* synthetic */ RangeLimitOptions RangeLimitOptions$default(Long l, Long l2, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            l2 = (Long) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        return RangeLimitOptions(l, l2, bool);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "rangeLimitOptionsOf(count, offset, withscores)"))
    @NotNull
    public static final RangeLimitOptions RangeLimitOptions(@Nullable Long count, @Nullable Long offset, @Nullable Boolean withscores) {
        RangeLimitOptions $this$apply = new RangeLimitOptions();
        if (count != null) {
            $this$apply.setCount(count);
        }
        if (offset != null) {
            $this$apply.setOffset(offset);
        }
        if (withscores != null) {
            $this$apply.setWithscores(withscores);
        }
        return $this$apply;
    }
}
