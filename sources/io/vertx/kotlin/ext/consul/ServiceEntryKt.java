package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.Check;
import io.vertx.ext.consul.Node;
import io.vertx.ext.consul.Service;
import io.vertx.ext.consul.ServiceEntry;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ServiceEntry.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"�� \n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a2\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007\u001a0\u0010\t\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¨\u0006\n"}, d2 = {"ServiceEntry", "Lio/vertx/ext/consul/ServiceEntry;", "checks", "", "Lio/vertx/ext/consul/Check;", "node", "Lio/vertx/ext/consul/Node;", "service", "Lio/vertx/ext/consul/Service;", "serviceEntryOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/ServiceEntryKt.class */
public final class ServiceEntryKt {
    @NotNull
    public static /* synthetic */ ServiceEntry serviceEntryOf$default(Iterable iterable, Node node, Service service, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            node = (Node) null;
        }
        if ((i & 4) != 0) {
            service = (Service) null;
        }
        return serviceEntryOf(iterable, node, service);
    }

    @NotNull
    public static final ServiceEntry serviceEntryOf(@Nullable Iterable<? extends Check> iterable, @Nullable Node node, @Nullable Service service) {
        ServiceEntry $this$apply = new ServiceEntry();
        if (iterable != null) {
            $this$apply.setChecks(CollectionsKt.toList(iterable));
        }
        if (node != null) {
            $this$apply.setNode(node);
        }
        if (service != null) {
            $this$apply.setService(service);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "serviceEntryOf(checks, node, service)"))
    @NotNull
    public static /* synthetic */ ServiceEntry ServiceEntry$default(Iterable iterable, Node node, Service service, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            node = (Node) null;
        }
        if ((i & 4) != 0) {
            service = (Service) null;
        }
        return ServiceEntry(iterable, node, service);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "serviceEntryOf(checks, node, service)"))
    @NotNull
    public static final ServiceEntry ServiceEntry(@Nullable Iterable<? extends Check> iterable, @Nullable Node node, @Nullable Service service) {
        ServiceEntry $this$apply = new ServiceEntry();
        if (iterable != null) {
            $this$apply.setChecks(CollectionsKt.toList(iterable));
        }
        if (node != null) {
            $this$apply.setNode(node);
        }
        if (service != null) {
            $this$apply.setService(service);
        }
        return $this$apply;
    }
}
