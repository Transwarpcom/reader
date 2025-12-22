package okhttp3.logging;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;

/* compiled from: utf8.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\f\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\u001a\f\u0010��\u001a\u00020\u0001*\u00020\u0002H��¨\u0006\u0003"}, d2 = {"isProbablyUtf8", "", "Lokio/Buffer;", "okhttp-logging-interceptor"})
/* loaded from: reader.jar:BOOT-INF/lib/logging-interceptor-4.1.0.jar:okhttp3/logging/Utf8Kt.class */
public final class Utf8Kt {
    public static final boolean isProbablyUtf8(@NotNull Buffer isProbablyUtf8) {
        Intrinsics.checkParameterIsNotNull(isProbablyUtf8, "$this$isProbablyUtf8");
        try {
            Buffer prefix = new Buffer();
            long byteCount = RangesKt.coerceAtMost(isProbablyUtf8.size(), 64L);
            isProbablyUtf8.copyTo(prefix, 0L, byteCount);
            for (int i = 0; i < 16; i++) {
                if (!prefix.exhausted()) {
                    int codePoint = prefix.readUtf8CodePoint();
                    if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                        return false;
                    }
                } else {
                    return true;
                }
            }
            return true;
        } catch (EOFException e) {
            return false;
        }
    }
}
