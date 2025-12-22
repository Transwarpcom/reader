package io.vertx.kotlin.redis.op;

import io.vertx.redis.op.BitFieldGetCommand;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BitFieldGetCommand.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"BitFieldGetCommand", "Lio/vertx/redis/op/BitFieldGetCommand;", "offset", "", "type", "", "(Ljava/lang/Long;Ljava/lang/String;)Lio/vertx/redis/op/BitFieldGetCommand;", "bitFieldGetCommandOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/op/BitFieldGetCommandKt.class */
public final class BitFieldGetCommandKt {
    @NotNull
    public static /* synthetic */ BitFieldGetCommand bitFieldGetCommandOf$default(Long l, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return bitFieldGetCommandOf(l, str);
    }

    @NotNull
    public static final BitFieldGetCommand bitFieldGetCommandOf(@Nullable Long offset, @Nullable String type) {
        BitFieldGetCommand $this$apply = new BitFieldGetCommand();
        if (offset != null) {
            $this$apply.setOffset(offset.longValue());
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bitFieldGetCommandOf(offset, type)"))
    @NotNull
    public static /* synthetic */ BitFieldGetCommand BitFieldGetCommand$default(Long l, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return BitFieldGetCommand(l, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bitFieldGetCommandOf(offset, type)"))
    @NotNull
    public static final BitFieldGetCommand BitFieldGetCommand(@Nullable Long offset, @Nullable String type) {
        BitFieldGetCommand $this$apply = new BitFieldGetCommand();
        if (offset != null) {
            $this$apply.setOffset(offset.longValue());
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }
}
