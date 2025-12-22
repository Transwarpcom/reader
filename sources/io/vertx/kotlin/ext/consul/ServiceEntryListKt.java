package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.ServiceEntry;
import io.vertx.ext.consul.ServiceEntryList;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: ServiceEntryList.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001a\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a+\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0007\u001a)\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"ServiceEntryList", "Lio/vertx/ext/consul/ServiceEntryList;", "index", "", BeanDefinitionParserDelegate.LIST_ELEMENT, "", "Lio/vertx/ext/consul/ServiceEntry;", "(Ljava/lang/Long;Ljava/lang/Iterable;)Lio/vertx/ext/consul/ServiceEntryList;", "serviceEntryListOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/ServiceEntryListKt.class */
public final class ServiceEntryListKt {
    @NotNull
    public static /* synthetic */ ServiceEntryList serviceEntryListOf$default(Long l, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        return serviceEntryListOf(l, iterable);
    }

    @NotNull
    public static final ServiceEntryList serviceEntryListOf(@Nullable Long index, @Nullable Iterable<? extends ServiceEntry> iterable) {
        ServiceEntryList $this$apply = new ServiceEntryList();
        if (index != null) {
            $this$apply.setIndex(index.longValue());
        }
        if (iterable != null) {
            $this$apply.setList(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "serviceEntryListOf(index, list)"))
    @NotNull
    public static /* synthetic */ ServiceEntryList ServiceEntryList$default(Long l, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        return ServiceEntryList(l, iterable);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "serviceEntryListOf(index, list)"))
    @NotNull
    public static final ServiceEntryList ServiceEntryList(@Nullable Long index, @Nullable Iterable<? extends ServiceEntry> iterable) {
        ServiceEntryList $this$apply = new ServiceEntryList();
        if (index != null) {
            $this$apply.setIndex(index.longValue());
        }
        if (iterable != null) {
            $this$apply.setList(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }
}
