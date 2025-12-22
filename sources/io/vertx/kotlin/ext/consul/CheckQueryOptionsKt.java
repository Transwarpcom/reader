package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.BlockingQueryOptions;
import io.vertx.ext.consul.CheckQueryOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CheckQueryOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a \u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007\u001a\u001e\u0010\u0006\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¨\u0006\u0007"}, d2 = {"CheckQueryOptions", "Lio/vertx/ext/consul/CheckQueryOptions;", "blockingOptions", "Lio/vertx/ext/consul/BlockingQueryOptions;", "near", "", "checkQueryOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/CheckQueryOptionsKt.class */
public final class CheckQueryOptionsKt {
    @NotNull
    public static /* synthetic */ CheckQueryOptions checkQueryOptionsOf$default(BlockingQueryOptions blockingQueryOptions, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            blockingQueryOptions = (BlockingQueryOptions) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return checkQueryOptionsOf(blockingQueryOptions, str);
    }

    @NotNull
    public static final CheckQueryOptions checkQueryOptionsOf(@Nullable BlockingQueryOptions blockingOptions, @Nullable String near) {
        CheckQueryOptions $this$apply = new CheckQueryOptions();
        if (blockingOptions != null) {
            $this$apply.setBlockingOptions(blockingOptions);
        }
        if (near != null) {
            $this$apply.setNear(near);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "checkQueryOptionsOf(blockingOptions, near)"))
    @NotNull
    public static /* synthetic */ CheckQueryOptions CheckQueryOptions$default(BlockingQueryOptions blockingQueryOptions, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            blockingQueryOptions = (BlockingQueryOptions) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return CheckQueryOptions(blockingQueryOptions, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "checkQueryOptionsOf(blockingOptions, near)"))
    @NotNull
    public static final CheckQueryOptions CheckQueryOptions(@Nullable BlockingQueryOptions blockingOptions, @Nullable String near) {
        CheckQueryOptions $this$apply = new CheckQueryOptions();
        if (blockingOptions != null) {
            $this$apply.setBlockingOptions(blockingOptions);
        }
        if (near != null) {
            $this$apply.setNear(near);
        }
        return $this$apply;
    }
}
