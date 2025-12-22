package io.vertx.kotlin.redis.op;

import io.vertx.redis.op.BitFieldSetCommand;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BitFieldSetCommand.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"BitFieldSetCommand", "Lio/vertx/redis/op/BitFieldSetCommand;", "offset", "", "type", "", "value", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;)Lio/vertx/redis/op/BitFieldSetCommand;", "bitFieldSetCommandOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/op/BitFieldSetCommandKt.class */
public final class BitFieldSetCommandKt {
    @NotNull
    public static /* synthetic */ BitFieldSetCommand bitFieldSetCommandOf$default(Long l, String str, Long l2, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            l2 = (Long) null;
        }
        return bitFieldSetCommandOf(l, str, l2);
    }

    @NotNull
    public static final BitFieldSetCommand bitFieldSetCommandOf(@Nullable Long offset, @Nullable String type, @Nullable Long value) {
        BitFieldSetCommand $this$apply = new BitFieldSetCommand();
        if (offset != null) {
            $this$apply.setOffset(offset.longValue());
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        if (value != null) {
            $this$apply.setValue(value.longValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bitFieldSetCommandOf(offset, type, value)"))
    @NotNull
    public static /* synthetic */ BitFieldSetCommand BitFieldSetCommand$default(Long l, String str, Long l2, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            l2 = (Long) null;
        }
        return BitFieldSetCommand(l, str, l2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bitFieldSetCommandOf(offset, type, value)"))
    @NotNull
    public static final BitFieldSetCommand BitFieldSetCommand(@Nullable Long offset, @Nullable String type, @Nullable Long value) {
        BitFieldSetCommand $this$apply = new BitFieldSetCommand();
        if (offset != null) {
            $this$apply.setOffset(offset.longValue());
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        if (value != null) {
            $this$apply.setValue(value.longValue());
        }
        return $this$apply;
    }
}
