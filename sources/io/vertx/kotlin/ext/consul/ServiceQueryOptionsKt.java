package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.BlockingQueryOptions;
import io.vertx.ext.consul.ServiceQueryOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ServiceQueryOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a,\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007\u001a*\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¨\u0006\b"}, d2 = {"ServiceQueryOptions", "Lio/vertx/ext/consul/ServiceQueryOptions;", "blockingOptions", "Lio/vertx/ext/consul/BlockingQueryOptions;", "near", "", "tag", "serviceQueryOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/ServiceQueryOptionsKt.class */
public final class ServiceQueryOptionsKt {
    @NotNull
    public static /* synthetic */ ServiceQueryOptions serviceQueryOptionsOf$default(BlockingQueryOptions blockingQueryOptions, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            blockingQueryOptions = (BlockingQueryOptions) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        return serviceQueryOptionsOf(blockingQueryOptions, str, str2);
    }

    @NotNull
    public static final ServiceQueryOptions serviceQueryOptionsOf(@Nullable BlockingQueryOptions blockingOptions, @Nullable String near, @Nullable String tag) {
        ServiceQueryOptions $this$apply = new ServiceQueryOptions();
        if (blockingOptions != null) {
            $this$apply.setBlockingOptions(blockingOptions);
        }
        if (near != null) {
            $this$apply.setNear(near);
        }
        if (tag != null) {
            $this$apply.setTag(tag);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "serviceQueryOptionsOf(blockingOptions, near, tag)"))
    @NotNull
    public static /* synthetic */ ServiceQueryOptions ServiceQueryOptions$default(BlockingQueryOptions blockingQueryOptions, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            blockingQueryOptions = (BlockingQueryOptions) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        return ServiceQueryOptions(blockingQueryOptions, str, str2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "serviceQueryOptionsOf(blockingOptions, near, tag)"))
    @NotNull
    public static final ServiceQueryOptions ServiceQueryOptions(@Nullable BlockingQueryOptions blockingOptions, @Nullable String near, @Nullable String tag) {
        ServiceQueryOptions $this$apply = new ServiceQueryOptions();
        if (blockingOptions != null) {
            $this$apply.setBlockingOptions(blockingOptions);
        }
        if (near != null) {
            $this$apply.setNear(near);
        }
        if (tag != null) {
            $this$apply.setTag(tag);
        }
        return $this$apply;
    }
}
