package okio;

import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: InflaterSource.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0012\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u0017\u0010��\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086\b¨\u0006\u0005"}, d2 = {"inflate", "Lokio/InflaterSource;", "Lokio/Source;", "inflater", "Ljava/util/zip/Inflater;", "okio"})
@JvmName(name = "-InflaterSourceExtensions")
/* renamed from: okio.-InflaterSourceExtensions, reason: invalid class name */
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/-InflaterSourceExtensions.class */
public final class InflaterSourceExtensions {
    public static /* synthetic */ InflaterSource inflate$default(Source inflate, Inflater inflater, int i, Object obj) {
        if ((i & 1) != 0) {
            inflater = new Inflater();
        }
        Intrinsics.checkNotNullParameter(inflate, "$this$inflate");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return new InflaterSource(inflate, inflater);
    }

    @NotNull
    public static final InflaterSource inflate(@NotNull Source inflate, @NotNull Inflater inflater) {
        Intrinsics.checkNotNullParameter(inflate, "$this$inflate");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return new InflaterSource(inflate, inflater);
    }
}
