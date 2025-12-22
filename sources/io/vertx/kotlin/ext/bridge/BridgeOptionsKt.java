package io.vertx.kotlin.ext.bridge;

import io.vertx.ext.bridge.BridgeOptions;
import io.vertx.ext.bridge.PermittedOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BridgeOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0014\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a,\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0007\u001a*\u0010\u0006\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¨\u0006\u0007"}, d2 = {"BridgeOptions", "Lio/vertx/ext/bridge/BridgeOptions;", "inboundPermitteds", "", "Lio/vertx/ext/bridge/PermittedOptions;", "outboundPermitteds", "bridgeOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/bridge/BridgeOptionsKt.class */
public final class BridgeOptionsKt {
    @NotNull
    public static /* synthetic */ BridgeOptions bridgeOptionsOf$default(Iterable iterable, Iterable iterable2, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            iterable2 = (Iterable) null;
        }
        return bridgeOptionsOf(iterable, iterable2);
    }

    @NotNull
    public static final BridgeOptions bridgeOptionsOf(@Nullable Iterable<? extends PermittedOptions> iterable, @Nullable Iterable<? extends PermittedOptions> iterable2) {
        BridgeOptions $this$apply = new BridgeOptions();
        if (iterable != null) {
            $this$apply.setInboundPermitteds(CollectionsKt.toList(iterable));
        }
        if (iterable2 != null) {
            $this$apply.setOutboundPermitteds(CollectionsKt.toList(iterable2));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bridgeOptionsOf(inboundPermitteds, outboundPermitteds)"))
    @NotNull
    public static /* synthetic */ BridgeOptions BridgeOptions$default(Iterable iterable, Iterable iterable2, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            iterable2 = (Iterable) null;
        }
        return BridgeOptions(iterable, iterable2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bridgeOptionsOf(inboundPermitteds, outboundPermitteds)"))
    @NotNull
    public static final BridgeOptions BridgeOptions(@Nullable Iterable<? extends PermittedOptions> iterable, @Nullable Iterable<? extends PermittedOptions> iterable2) {
        BridgeOptions $this$apply = new BridgeOptions();
        if (iterable != null) {
            $this$apply.setInboundPermitteds(CollectionsKt.toList(iterable));
        }
        if (iterable2 != null) {
            $this$apply.setOutboundPermitteds(CollectionsKt.toList(iterable2));
        }
        return $this$apply;
    }
}
