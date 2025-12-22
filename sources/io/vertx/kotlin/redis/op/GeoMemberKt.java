package io.vertx.kotlin.redis.op;

import io.vertx.redis.op.GeoMember;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: GeoMember.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"GeoMember", "Lio/vertx/redis/op/GeoMember;", "latitude", "", "longitude", "member", "", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;)Lio/vertx/redis/op/GeoMember;", "geoMemberOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/op/GeoMemberKt.class */
public final class GeoMemberKt {
    @NotNull
    public static /* synthetic */ GeoMember geoMemberOf$default(Double d, Double d2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            d = (Double) null;
        }
        if ((i & 2) != 0) {
            d2 = (Double) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        return geoMemberOf(d, d2, str);
    }

    @NotNull
    public static final GeoMember geoMemberOf(@Nullable Double latitude, @Nullable Double longitude, @Nullable String member) {
        GeoMember $this$apply = new GeoMember();
        if (latitude != null) {
            $this$apply.setLatitude(latitude);
        }
        if (longitude != null) {
            $this$apply.setLongitude(longitude);
        }
        if (member != null) {
            $this$apply.setMember(member);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "geoMemberOf(latitude, longitude, member)"))
    @NotNull
    public static /* synthetic */ GeoMember GeoMember$default(Double d, Double d2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            d = (Double) null;
        }
        if ((i & 2) != 0) {
            d2 = (Double) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        return GeoMember(d, d2, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "geoMemberOf(latitude, longitude, member)"))
    @NotNull
    public static final GeoMember GeoMember(@Nullable Double latitude, @Nullable Double longitude, @Nullable String member) {
        GeoMember $this$apply = new GeoMember();
        if (latitude != null) {
            $this$apply.setLatitude(latitude);
        }
        if (longitude != null) {
            $this$apply.setLongitude(longitude);
        }
        if (member != null) {
            $this$apply.setMember(member);
        }
        return $this$apply;
    }
}
