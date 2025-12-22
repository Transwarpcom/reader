package io.vertx.kotlin.kafka.client.common;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.kafka.client.common.Node;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Node.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n\u0002\b\u0007\u001aa\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\f\u001a_\u0010\r\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\f¨\u0006\u000e"}, d2 = {"Node", "Lio/vertx/kafka/client/common/Node;", "hasRack", "", "host", "", "id", "", "idString", "isEmpty", RtspHeaders.Values.PORT, "rack", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;)Lio/vertx/kafka/client/common/Node;", "nodeOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/client/common/NodeKt.class */
public final class NodeKt {
    @NotNull
    public static /* synthetic */ Node nodeOf$default(Boolean bool, String str, Integer num, String str2, Boolean bool2, Integer num2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 32) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 64) != 0) {
            str3 = (String) null;
        }
        return nodeOf(bool, str, num, str2, bool2, num2, str3);
    }

    @NotNull
    public static final Node nodeOf(@Nullable Boolean hasRack, @Nullable String host, @Nullable Integer id, @Nullable String idString, @Nullable Boolean isEmpty, @Nullable Integer port, @Nullable String rack) {
        Node $this$apply = new Node();
        if (hasRack != null) {
            $this$apply.setHasRack(hasRack.booleanValue());
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (id != null) {
            $this$apply.setId(id.intValue());
        }
        if (idString != null) {
            $this$apply.setIdString(idString);
        }
        if (isEmpty != null) {
            $this$apply.setIsEmpty(isEmpty.booleanValue());
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (rack != null) {
            $this$apply.setRack(rack);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "nodeOf(hasRack, host, id, idString, isEmpty, port, rack)"))
    @NotNull
    public static /* synthetic */ Node Node$default(Boolean bool, String str, Integer num, String str2, Boolean bool2, Integer num2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 32) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 64) != 0) {
            str3 = (String) null;
        }
        return Node(bool, str, num, str2, bool2, num2, str3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "nodeOf(hasRack, host, id, idString, isEmpty, port, rack)"))
    @NotNull
    public static final Node Node(@Nullable Boolean hasRack, @Nullable String host, @Nullable Integer id, @Nullable String idString, @Nullable Boolean isEmpty, @Nullable Integer port, @Nullable String rack) {
        Node $this$apply = new Node();
        if (hasRack != null) {
            $this$apply.setHasRack(hasRack.booleanValue());
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (id != null) {
            $this$apply.setId(id.intValue());
        }
        if (idString != null) {
            $this$apply.setIdString(idString);
        }
        if (isEmpty != null) {
            $this$apply.setIsEmpty(isEmpty.booleanValue());
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (rack != null) {
            $this$apply.setRack(rack);
        }
        return $this$apply;
    }
}
