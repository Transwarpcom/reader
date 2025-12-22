package mu;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/* compiled from: KMarkerFactory.kt */
@Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lmu/KMarkerFactory;", "", "()V", "getMarker", "Lorg/slf4j/Marker;", "Lmu/Marker;", "name", "", "kotlin-logging"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-logging-1.6.24.jar:mu/KMarkerFactory.class */
public final class KMarkerFactory {
    public static final KMarkerFactory INSTANCE = new KMarkerFactory();

    private KMarkerFactory() {
    }

    @NotNull
    public final Marker getMarker(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Marker marker = MarkerFactory.getMarker(name);
        Intrinsics.checkExpressionValueIsNotNull(marker, "MarkerFactory.getMarker(name)");
        return marker;
    }
}
