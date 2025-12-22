package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.BlockingQueryOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BlockingQueryOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"BlockingQueryOptions", "Lio/vertx/ext/consul/BlockingQueryOptions;", "index", "", "wait", "", "(Ljava/lang/Long;Ljava/lang/String;)Lio/vertx/ext/consul/BlockingQueryOptions;", "blockingQueryOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/BlockingQueryOptionsKt.class */
public final class BlockingQueryOptionsKt {
    @NotNull
    public static /* synthetic */ BlockingQueryOptions blockingQueryOptionsOf$default(Long l, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return blockingQueryOptionsOf(l, str);
    }

    @NotNull
    public static final BlockingQueryOptions blockingQueryOptionsOf(@Nullable Long index, @Nullable String wait) {
        BlockingQueryOptions $this$apply = new BlockingQueryOptions();
        if (index != null) {
            $this$apply.setIndex(index.longValue());
        }
        if (wait != null) {
            $this$apply.setWait(wait);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "blockingQueryOptionsOf(index, wait)"))
    @NotNull
    public static /* synthetic */ BlockingQueryOptions BlockingQueryOptions$default(Long l, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return BlockingQueryOptions(l, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "blockingQueryOptionsOf(index, wait)"))
    @NotNull
    public static final BlockingQueryOptions BlockingQueryOptions(@Nullable Long index, @Nullable String wait) {
        BlockingQueryOptions $this$apply = new BlockingQueryOptions();
        if (index != null) {
            $this$apply.setIndex(index.longValue());
        }
        if (wait != null) {
            $this$apply.setWait(wait);
        }
        return $this$apply;
    }
}
