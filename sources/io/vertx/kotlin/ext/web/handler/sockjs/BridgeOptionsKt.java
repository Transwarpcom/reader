package io.vertx.kotlin.ext.web.handler.sockjs;

import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BridgeOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��$\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\u001a\u0085\u0001\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fH\u0007¢\u0006\u0002\u0010\u000e\u001a\u0083\u0001\u0010\u000f\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\u000e¨\u0006\u0010"}, d2 = {"BridgeOptions", "Lio/vertx/ext/web/handler/sockjs/BridgeOptions;", "inboundPermitted", "", "Lio/vertx/ext/bridge/PermittedOptions;", "inboundPermitteds", "maxAddressLength", "", "maxHandlersPerSocket", "outboundPermitted", "outboundPermitteds", "pingTimeout", "", "replyTimeout", "(Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Long;Ljava/lang/Long;)Lio/vertx/ext/web/handler/sockjs/BridgeOptions;", "bridgeOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/web/handler/sockjs/BridgeOptionsKt.class */
public final class BridgeOptionsKt {
    @NotNull
    public static /* synthetic */ BridgeOptions bridgeOptionsOf$default(Iterable iterable, Iterable iterable2, Integer num, Integer num2, Iterable iterable3, Iterable iterable4, Long l, Long l2, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 16) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 32) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 64) != 0) {
            l = (Long) null;
        }
        if ((i & 128) != 0) {
            l2 = (Long) null;
        }
        return bridgeOptionsOf(iterable, iterable2, num, num2, iterable3, iterable4, l, l2);
    }

    @NotNull
    public static final BridgeOptions bridgeOptionsOf(@Nullable Iterable<? extends PermittedOptions> iterable, @Nullable Iterable<? extends PermittedOptions> iterable2, @Nullable Integer maxAddressLength, @Nullable Integer maxHandlersPerSocket, @Nullable Iterable<? extends PermittedOptions> iterable3, @Nullable Iterable<? extends PermittedOptions> iterable4, @Nullable Long pingTimeout, @Nullable Long replyTimeout) {
        BridgeOptions $this$apply = new BridgeOptions();
        if (iterable != null) {
            $this$apply.setInboundPermitted(CollectionsKt.toList(iterable));
        }
        if (iterable2 != null) {
            for (PermittedOptions item : iterable2) {
                $this$apply.addInboundPermitted(item);
            }
        }
        if (maxAddressLength != null) {
            $this$apply.setMaxAddressLength(maxAddressLength.intValue());
        }
        if (maxHandlersPerSocket != null) {
            $this$apply.setMaxHandlersPerSocket(maxHandlersPerSocket.intValue());
        }
        if (iterable3 != null) {
            $this$apply.setOutboundPermitted(CollectionsKt.toList(iterable3));
        }
        if (iterable4 != null) {
            for (PermittedOptions item2 : iterable4) {
                $this$apply.addOutboundPermitted(item2);
            }
        }
        if (pingTimeout != null) {
            $this$apply.setPingTimeout(pingTimeout.longValue());
        }
        if (replyTimeout != null) {
            $this$apply.setReplyTimeout(replyTimeout.longValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bridgeOptionsOf(inboundPermitted, inboundPermitteds, maxAddressLength, maxHandlersPerSocket, outboundPermitted, outboundPermitteds, pingTimeout, replyTimeout)"))
    @NotNull
    public static /* synthetic */ BridgeOptions BridgeOptions$default(Iterable iterable, Iterable iterable2, Integer num, Integer num2, Iterable iterable3, Iterable iterable4, Long l, Long l2, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 16) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 32) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 64) != 0) {
            l = (Long) null;
        }
        if ((i & 128) != 0) {
            l2 = (Long) null;
        }
        return BridgeOptions(iterable, iterable2, num, num2, iterable3, iterable4, l, l2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "bridgeOptionsOf(inboundPermitted, inboundPermitteds, maxAddressLength, maxHandlersPerSocket, outboundPermitted, outboundPermitteds, pingTimeout, replyTimeout)"))
    @NotNull
    public static final BridgeOptions BridgeOptions(@Nullable Iterable<? extends PermittedOptions> iterable, @Nullable Iterable<? extends PermittedOptions> iterable2, @Nullable Integer maxAddressLength, @Nullable Integer maxHandlersPerSocket, @Nullable Iterable<? extends PermittedOptions> iterable3, @Nullable Iterable<? extends PermittedOptions> iterable4, @Nullable Long pingTimeout, @Nullable Long replyTimeout) {
        BridgeOptions $this$apply = new BridgeOptions();
        if (iterable != null) {
            $this$apply.setInboundPermitted(CollectionsKt.toList(iterable));
        }
        if (iterable2 != null) {
            for (PermittedOptions item : iterable2) {
                $this$apply.addInboundPermitted(item);
            }
        }
        if (maxAddressLength != null) {
            $this$apply.setMaxAddressLength(maxAddressLength.intValue());
        }
        if (maxHandlersPerSocket != null) {
            $this$apply.setMaxHandlersPerSocket(maxHandlersPerSocket.intValue());
        }
        if (iterable3 != null) {
            $this$apply.setOutboundPermitted(CollectionsKt.toList(iterable3));
        }
        if (iterable4 != null) {
            for (PermittedOptions item2 : iterable4) {
                $this$apply.addOutboundPermitted(item2);
            }
        }
        if (pingTimeout != null) {
            $this$apply.setPingTimeout(pingTimeout.longValue());
        }
        if (replyTimeout != null) {
            $this$apply.setReplyTimeout(replyTimeout.longValue());
        }
        return $this$apply;
    }
}
