package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.Coordinate;
import io.vertx.ext.consul.CoordinateList;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: CoordinateList.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001a\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a+\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0007\u001a)\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"CoordinateList", "Lio/vertx/ext/consul/CoordinateList;", "index", "", BeanDefinitionParserDelegate.LIST_ELEMENT, "", "Lio/vertx/ext/consul/Coordinate;", "(Ljava/lang/Long;Ljava/lang/Iterable;)Lio/vertx/ext/consul/CoordinateList;", "coordinateListOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/CoordinateListKt.class */
public final class CoordinateListKt {
    @NotNull
    public static /* synthetic */ CoordinateList coordinateListOf$default(Long l, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        return coordinateListOf(l, iterable);
    }

    @NotNull
    public static final CoordinateList coordinateListOf(@Nullable Long index, @Nullable Iterable<? extends Coordinate> iterable) {
        CoordinateList $this$apply = new CoordinateList();
        if (index != null) {
            $this$apply.setIndex(index.longValue());
        }
        if (iterable != null) {
            $this$apply.setList(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "coordinateListOf(index, list)"))
    @NotNull
    public static /* synthetic */ CoordinateList CoordinateList$default(Long l, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        return CoordinateList(l, iterable);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "coordinateListOf(index, list)"))
    @NotNull
    public static final CoordinateList CoordinateList(@Nullable Long index, @Nullable Iterable<? extends Coordinate> iterable) {
        CoordinateList $this$apply = new CoordinateList();
        if (index != null) {
            $this$apply.setIndex(index.longValue());
        }
        if (iterable != null) {
            $this$apply.setList(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }
}
