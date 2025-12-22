package okio;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: Okio.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 5, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\u001a\r\u0010��\u001a\u00020\u0001H\u0007¢\u0006\u0002\b\u0002\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\u0003\u001a\u00020\u0005*\u00020\u0006¨\u0006\u0007"}, d2 = {"blackholeSink", "Lokio/Sink;", "blackhole", "buffer", "Lokio/BufferedSink;", "Lokio/BufferedSource;", "Lokio/Source;", "okio"}, xs = "okio/Okio")
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/Okio__OkioKt.class */
final /* synthetic */ class Okio__OkioKt {
    @NotNull
    public static final BufferedSource buffer(@NotNull Source buffer) {
        Intrinsics.checkNotNullParameter(buffer, "$this$buffer");
        return new RealBufferedSource(buffer);
    }

    @NotNull
    public static final BufferedSink buffer(@NotNull Sink buffer) {
        Intrinsics.checkNotNullParameter(buffer, "$this$buffer");
        return new RealBufferedSink(buffer);
    }

    @JvmName(name = "blackhole")
    @NotNull
    public static final Sink blackhole() {
        return new BlackholeSink();
    }
}
