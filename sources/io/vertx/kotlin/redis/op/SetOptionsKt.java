package io.vertx.kotlin.redis.op;

import io.vertx.redis.op.SetOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SetOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a=\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\b\u001a;\u0010\t\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"SetOptions", "Lio/vertx/redis/op/SetOptions;", "ex", "", "nx", "", "px", "xx", "(Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/lang/Boolean;)Lio/vertx/redis/op/SetOptions;", "setOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/op/SetOptionsKt.class */
public final class SetOptionsKt {
    @NotNull
    public static /* synthetic */ SetOptions setOptionsOf$default(Long l, Boolean bool, Long l2, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            l2 = (Long) null;
        }
        if ((i & 8) != 0) {
            bool2 = (Boolean) null;
        }
        return setOptionsOf(l, bool, l2, bool2);
    }

    @NotNull
    public static final SetOptions setOptionsOf(@Nullable Long ex, @Nullable Boolean nx, @Nullable Long px, @Nullable Boolean xx) {
        SetOptions $this$apply = new SetOptions();
        if (ex != null) {
            $this$apply.setEX(ex.longValue());
        }
        if (nx != null) {
            $this$apply.setNX(nx.booleanValue());
        }
        if (px != null) {
            $this$apply.setPX(px.longValue());
        }
        if (xx != null) {
            $this$apply.setXX(xx.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "setOptionsOf(ex, nx, px, xx)"))
    @NotNull
    public static /* synthetic */ SetOptions SetOptions$default(Long l, Boolean bool, Long l2, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            l2 = (Long) null;
        }
        if ((i & 8) != 0) {
            bool2 = (Boolean) null;
        }
        return SetOptions(l, bool, l2, bool2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "setOptionsOf(ex, nx, px, xx)"))
    @NotNull
    public static final SetOptions SetOptions(@Nullable Long ex, @Nullable Boolean nx, @Nullable Long px, @Nullable Boolean xx) {
        SetOptions $this$apply = new SetOptions();
        if (ex != null) {
            $this$apply.setEX(ex.longValue());
        }
        if (nx != null) {
            $this$apply.setNX(nx.booleanValue());
        }
        if (px != null) {
            $this$apply.setPX(px.longValue());
        }
        if (xx != null) {
            $this$apply.setXX(xx.booleanValue());
        }
        return $this$apply;
    }
}
