package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.Node;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Node.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a8\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0007\u001a6\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¨\u0006\b"}, d2 = {"Node", "Lio/vertx/ext/consul/Node;", "address", "", "lanAddress", "name", "wanAddress", "nodeOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/NodeKt.class */
public final class NodeKt {
    @NotNull
    public static /* synthetic */ Node nodeOf$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            str3 = (String) null;
        }
        if ((i & 8) != 0) {
            str4 = (String) null;
        }
        return nodeOf(str, str2, str3, str4);
    }

    @NotNull
    public static final Node nodeOf(@Nullable String address, @Nullable String lanAddress, @Nullable String name, @Nullable String wanAddress) {
        Node $this$apply = new Node();
        if (address != null) {
            $this$apply.setAddress(address);
        }
        if (lanAddress != null) {
            $this$apply.setLanAddress(lanAddress);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (wanAddress != null) {
            $this$apply.setWanAddress(wanAddress);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "nodeOf(address, lanAddress, name, wanAddress)"))
    @NotNull
    public static /* synthetic */ Node Node$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            str3 = (String) null;
        }
        if ((i & 8) != 0) {
            str4 = (String) null;
        }
        return Node(str, str2, str3, str4);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "nodeOf(address, lanAddress, name, wanAddress)"))
    @NotNull
    public static final Node Node(@Nullable String address, @Nullable String lanAddress, @Nullable String name, @Nullable String wanAddress) {
        Node $this$apply = new Node();
        if (address != null) {
            $this$apply.setAddress(address);
        }
        if (lanAddress != null) {
            $this$apply.setLanAddress(lanAddress);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (wanAddress != null) {
            $this$apply.setWanAddress(wanAddress);
        }
        return $this$apply;
    }
}
