package io.vertx.kotlin.redis.op;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import io.vertx.redis.op.BitFieldGetCommand;
import io.vertx.redis.op.BitFieldIncrbyCommand;
import io.vertx.redis.op.BitFieldOptions;
import io.vertx.redis.op.BitFieldSetCommand;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BitFieldOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a,\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007\u001a*\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\t"}, d2 = {"BitFieldOptions", "Lio/vertx/redis/op/BitFieldOptions;", BeanUtil.PREFIX_GETTER_GET, "Lio/vertx/redis/op/BitFieldGetCommand;", "incrby", "Lio/vertx/redis/op/BitFieldIncrbyCommand;", "set", "Lio/vertx/redis/op/BitFieldSetCommand;", "bitFieldOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/op/BitFieldOptionsKt.class */
public final class BitFieldOptionsKt {
    @NotNull
    public static /* synthetic */ BitFieldOptions bitFieldOptionsOf$default(BitFieldGetCommand bitFieldGetCommand, BitFieldIncrbyCommand bitFieldIncrbyCommand, BitFieldSetCommand bitFieldSetCommand, int i, Object obj) {
        if ((i & 1) != 0) {
            bitFieldGetCommand = (BitFieldGetCommand) null;
        }
        if ((i & 2) != 0) {
            bitFieldIncrbyCommand = (BitFieldIncrbyCommand) null;
        }
        if ((i & 4) != 0) {
            bitFieldSetCommand = (BitFieldSetCommand) null;
        }
        return bitFieldOptionsOf(bitFieldGetCommand, bitFieldIncrbyCommand, bitFieldSetCommand);
    }

    @NotNull
    public static final BitFieldOptions bitFieldOptionsOf(@Nullable BitFieldGetCommand get, @Nullable BitFieldIncrbyCommand incrby, @Nullable BitFieldSetCommand set) {
        BitFieldOptions $this$apply = new BitFieldOptions();
        if (get != null) {
            $this$apply.setGet(get);
        }
        if (incrby != null) {
            $this$apply.setIncrby(incrby);
        }
        if (set != null) {
            $this$apply.setSet(set);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bitFieldOptionsOf(get, incrby, set)"))
    @NotNull
    public static /* synthetic */ BitFieldOptions BitFieldOptions$default(BitFieldGetCommand bitFieldGetCommand, BitFieldIncrbyCommand bitFieldIncrbyCommand, BitFieldSetCommand bitFieldSetCommand, int i, Object obj) {
        if ((i & 1) != 0) {
            bitFieldGetCommand = (BitFieldGetCommand) null;
        }
        if ((i & 2) != 0) {
            bitFieldIncrbyCommand = (BitFieldIncrbyCommand) null;
        }
        if ((i & 4) != 0) {
            bitFieldSetCommand = (BitFieldSetCommand) null;
        }
        return BitFieldOptions(bitFieldGetCommand, bitFieldIncrbyCommand, bitFieldSetCommand);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bitFieldOptionsOf(get, incrby, set)"))
    @NotNull
    public static final BitFieldOptions BitFieldOptions(@Nullable BitFieldGetCommand get, @Nullable BitFieldIncrbyCommand incrby, @Nullable BitFieldSetCommand set) {
        BitFieldOptions $this$apply = new BitFieldOptions();
        if (get != null) {
            $this$apply.setGet(get);
        }
        if (incrby != null) {
            $this$apply.setIncrby(incrby);
        }
        if (set != null) {
            $this$apply.setSet(set);
        }
        return $this$apply;
    }
}
