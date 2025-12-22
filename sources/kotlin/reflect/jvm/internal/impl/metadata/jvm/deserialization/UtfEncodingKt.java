package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: utfEncoding.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/UtfEncodingKt.class */
public final class UtfEncodingKt {
    @NotNull
    public static final byte[] stringsToBytes(@NotNull String[] strings) {
        int si;
        Intrinsics.checkNotNullParameter(strings, "strings");
        int length = 0;
        for (String it : strings) {
            length += it.length();
        }
        int resultLength = length;
        byte[] result = new byte[resultLength];
        int i = 0;
        int i2 = 0;
        int length2 = strings.length;
        while (i2 < length2) {
            String s = strings[i2];
            i2++;
            int i3 = 0;
            int length3 = s.length() - 1;
            if (0 <= length3) {
                do {
                    si = i3;
                    i3++;
                    int i4 = i;
                    i = i4 + 1;
                    result[i4] = (byte) s.charAt(si);
                } while (si != length3);
            }
        }
        boolean z = i == result.length;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Should have reached the end");
        }
        return result;
    }
}
