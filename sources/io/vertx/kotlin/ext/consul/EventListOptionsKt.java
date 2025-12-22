package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.BlockingQueryOptions;
import io.vertx.ext.consul.EventListOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: EventListOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a \u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007\u001a\u001e\u0010\u0006\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¨\u0006\u0007"}, d2 = {"EventListOptions", "Lio/vertx/ext/consul/EventListOptions;", "blockingOptions", "Lio/vertx/ext/consul/BlockingQueryOptions;", "name", "", "eventListOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/EventListOptionsKt.class */
public final class EventListOptionsKt {
    @NotNull
    public static /* synthetic */ EventListOptions eventListOptionsOf$default(BlockingQueryOptions blockingQueryOptions, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            blockingQueryOptions = (BlockingQueryOptions) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return eventListOptionsOf(blockingQueryOptions, str);
    }

    @NotNull
    public static final EventListOptions eventListOptionsOf(@Nullable BlockingQueryOptions blockingOptions, @Nullable String name) {
        EventListOptions $this$apply = new EventListOptions();
        if (blockingOptions != null) {
            $this$apply.setBlockingOptions(blockingOptions);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "eventListOptionsOf(blockingOptions, name)"))
    @NotNull
    public static /* synthetic */ EventListOptions EventListOptions$default(BlockingQueryOptions blockingQueryOptions, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            blockingQueryOptions = (BlockingQueryOptions) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return EventListOptions(blockingQueryOptions, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "eventListOptionsOf(blockingOptions, name)"))
    @NotNull
    public static final EventListOptions EventListOptions(@Nullable BlockingQueryOptions blockingOptions, @Nullable String name) {
        EventListOptions $this$apply = new EventListOptions();
        if (blockingOptions != null) {
            $this$apply.setBlockingOptions(blockingOptions);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        return $this$apply;
    }
}
