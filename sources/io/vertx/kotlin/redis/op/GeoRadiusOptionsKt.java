package io.vertx.kotlin.redis.op;

import io.vertx.redis.op.GeoRadiusOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: GeoRadiusOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a=\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\b\u001a;\u0010\t\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"GeoRadiusOptions", "Lio/vertx/redis/op/GeoRadiusOptions;", "count", "", "withCoord", "", "withDist", "withHash", "(Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lio/vertx/redis/op/GeoRadiusOptions;", "geoRadiusOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/op/GeoRadiusOptionsKt.class */
public final class GeoRadiusOptionsKt {
    @NotNull
    public static /* synthetic */ GeoRadiusOptions geoRadiusOptionsOf$default(Long l, Boolean bool, Boolean bool2, Boolean bool3, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 8) != 0) {
            bool3 = (Boolean) null;
        }
        return geoRadiusOptionsOf(l, bool, bool2, bool3);
    }

    @NotNull
    public static final GeoRadiusOptions geoRadiusOptionsOf(@Nullable Long count, @Nullable Boolean withCoord, @Nullable Boolean withDist, @Nullable Boolean withHash) {
        GeoRadiusOptions $this$apply = new GeoRadiusOptions();
        if (count != null) {
            $this$apply.setCount(count);
        }
        if (withCoord != null) {
            $this$apply.setWithCoord(withCoord.booleanValue());
        }
        if (withDist != null) {
            $this$apply.setWithDist(withDist.booleanValue());
        }
        if (withHash != null) {
            $this$apply.setWithHash(withHash.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "geoRadiusOptionsOf(count, withCoord, withDist, withHash)"))
    @NotNull
    public static /* synthetic */ GeoRadiusOptions GeoRadiusOptions$default(Long l, Boolean bool, Boolean bool2, Boolean bool3, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 8) != 0) {
            bool3 = (Boolean) null;
        }
        return GeoRadiusOptions(l, bool, bool2, bool3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "geoRadiusOptionsOf(count, withCoord, withDist, withHash)"))
    @NotNull
    public static final GeoRadiusOptions GeoRadiusOptions(@Nullable Long count, @Nullable Boolean withCoord, @Nullable Boolean withDist, @Nullable Boolean withHash) {
        GeoRadiusOptions $this$apply = new GeoRadiusOptions();
        if (count != null) {
            $this$apply.setCount(count);
        }
        if (withCoord != null) {
            $this$apply.setWithCoord(withCoord.booleanValue());
        }
        if (withDist != null) {
            $this$apply.setWithDist(withDist.booleanValue());
        }
        if (withHash != null) {
            $this$apply.setWithHash(withHash.booleanValue());
        }
        return $this$apply;
    }
}
