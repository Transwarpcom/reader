package io.vertx.kotlin.redis.op;

import io.vertx.redis.op.BitFieldIncrbyCommand;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BitFieldIncrbyCommand.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"BitFieldIncrbyCommand", "Lio/vertx/redis/op/BitFieldIncrbyCommand;", "increment", "", "offset", "type", "", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;)Lio/vertx/redis/op/BitFieldIncrbyCommand;", "bitFieldIncrbyCommandOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/op/BitFieldIncrbyCommandKt.class */
public final class BitFieldIncrbyCommandKt {
    @NotNull
    public static /* synthetic */ BitFieldIncrbyCommand bitFieldIncrbyCommandOf$default(Long l, Long l2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            l2 = (Long) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        return bitFieldIncrbyCommandOf(l, l2, str);
    }

    @NotNull
    public static final BitFieldIncrbyCommand bitFieldIncrbyCommandOf(@Nullable Long increment, @Nullable Long offset, @Nullable String type) {
        BitFieldIncrbyCommand $this$apply = new BitFieldIncrbyCommand();
        if (increment != null) {
            $this$apply.setIncrement(increment.longValue());
        }
        if (offset != null) {
            $this$apply.setOffset(offset.longValue());
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bitFieldIncrbyCommandOf(increment, offset, type)"))
    @NotNull
    public static /* synthetic */ BitFieldIncrbyCommand BitFieldIncrbyCommand$default(Long l, Long l2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            l2 = (Long) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        return BitFieldIncrbyCommand(l, l2, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bitFieldIncrbyCommandOf(increment, offset, type)"))
    @NotNull
    public static final BitFieldIncrbyCommand BitFieldIncrbyCommand(@Nullable Long increment, @Nullable Long offset, @Nullable String type) {
        BitFieldIncrbyCommand $this$apply = new BitFieldIncrbyCommand();
        if (increment != null) {
            $this$apply.setIncrement(increment.longValue());
        }
        if (offset != null) {
            $this$apply.setOffset(offset.longValue());
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }
}
