package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.BlockingQueryOptions;
import io.vertx.ext.consul.NodeQueryOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: NodeQueryOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a \u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007\u001a\u001e\u0010\u0006\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¨\u0006\u0007"}, d2 = {"NodeQueryOptions", "Lio/vertx/ext/consul/NodeQueryOptions;", "blockingOptions", "Lio/vertx/ext/consul/BlockingQueryOptions;", "near", "", "nodeQueryOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/NodeQueryOptionsKt.class */
public final class NodeQueryOptionsKt {
    @NotNull
    public static /* synthetic */ NodeQueryOptions nodeQueryOptionsOf$default(BlockingQueryOptions blockingQueryOptions, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            blockingQueryOptions = (BlockingQueryOptions) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return nodeQueryOptionsOf(blockingQueryOptions, str);
    }

    @NotNull
    public static final NodeQueryOptions nodeQueryOptionsOf(@Nullable BlockingQueryOptions blockingOptions, @Nullable String near) {
        NodeQueryOptions $this$apply = new NodeQueryOptions();
        if (blockingOptions != null) {
            $this$apply.setBlockingOptions(blockingOptions);
        }
        if (near != null) {
            $this$apply.setNear(near);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "nodeQueryOptionsOf(blockingOptions, near)"))
    @NotNull
    public static /* synthetic */ NodeQueryOptions NodeQueryOptions$default(BlockingQueryOptions blockingQueryOptions, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            blockingQueryOptions = (BlockingQueryOptions) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return NodeQueryOptions(blockingQueryOptions, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "nodeQueryOptionsOf(blockingOptions, near)"))
    @NotNull
    public static final NodeQueryOptions NodeQueryOptions(@Nullable BlockingQueryOptions blockingOptions, @Nullable String near) {
        NodeQueryOptions $this$apply = new NodeQueryOptions();
        if (blockingOptions != null) {
            $this$apply.setBlockingOptions(blockingOptions);
        }
        if (near != null) {
            $this$apply.setNear(near);
        }
        return $this$apply;
    }
}
