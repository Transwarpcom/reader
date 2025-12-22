package okio.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: -Utf8.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a\n\u0010��\u001a\u00020\u0001*\u00020\u0002\u001a\u001e\u0010\u0003\u001a\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"commonAsUtf8ToByteArray", "", "", "commonToUtf8String", "beginIndex", "", "endIndex", "okio"})
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/internal/_Utf8Kt.class */
public final class _Utf8Kt {
    public static /* synthetic */ String commonToUtf8String$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        return commonToUtf8String(bArr, i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x04cc  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0494  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.String commonToUtf8String(@org.jetbrains.annotations.NotNull byte[] r6, int r7, int r8) {
        /*
            Method dump skipped, instructions count: 2288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal._Utf8Kt.commonToUtf8String(byte[], int, int):java.lang.String");
    }

    @NotNull
    public static final byte[] commonAsUtf8ToByteArray(@NotNull String commonAsUtf8ToByteArray) {
        char cCharAt;
        Intrinsics.checkNotNullParameter(commonAsUtf8ToByteArray, "$this$commonAsUtf8ToByteArray");
        byte[] bytes = new byte[4 * commonAsUtf8ToByteArray.length()];
        int length = commonAsUtf8ToByteArray.length();
        for (int index = 0; index < length; index++) {
            char b0 = commonAsUtf8ToByteArray.charAt(index);
            if (Intrinsics.compare((int) b0, 128) >= 0) {
                int size = index;
                int endIndex$iv = commonAsUtf8ToByteArray.length();
                int index$iv = index;
                while (index$iv < endIndex$iv) {
                    char c$iv = commonAsUtf8ToByteArray.charAt(index$iv);
                    if (Intrinsics.compare((int) c$iv, 128) < 0) {
                        byte c = (byte) c$iv;
                        int i = size;
                        size = i + 1;
                        bytes[i] = c;
                        index$iv++;
                        while (index$iv < endIndex$iv && Intrinsics.compare((int) commonAsUtf8ToByteArray.charAt(index$iv), 128) < 0) {
                            int i2 = index$iv;
                            index$iv++;
                            byte c2 = (byte) commonAsUtf8ToByteArray.charAt(i2);
                            int i3 = size;
                            size = i3 + 1;
                            bytes[i3] = c2;
                        }
                    } else if (Intrinsics.compare((int) c$iv, 2048) < 0) {
                        byte c3 = (byte) ((c$iv >> 6) | 192);
                        int i4 = size;
                        int size2 = i4 + 1;
                        bytes[i4] = c3;
                        byte c4 = (byte) ((c$iv & '?') | 128);
                        size = size2 + 1;
                        bytes[size2] = c4;
                        index$iv++;
                    } else if (55296 > c$iv || 57343 < c$iv) {
                        byte c5 = (byte) ((c$iv >> '\f') | 224);
                        int i5 = size;
                        int size3 = i5 + 1;
                        bytes[i5] = c5;
                        byte c6 = (byte) (((c$iv >> 6) & 63) | 128);
                        int size4 = size3 + 1;
                        bytes[size3] = c6;
                        byte c7 = (byte) ((c$iv & '?') | 128);
                        size = size4 + 1;
                        bytes[size4] = c7;
                        index$iv++;
                    } else if (Intrinsics.compare((int) c$iv, 56319) > 0 || endIndex$iv <= index$iv + 1 || 56320 > (cCharAt = commonAsUtf8ToByteArray.charAt(index$iv + 1)) || 57343 < cCharAt) {
                        int i6 = size;
                        size = i6 + 1;
                        bytes[i6] = 63;
                        index$iv++;
                    } else {
                        int codePoint$iv = ((c$iv << '\n') + commonAsUtf8ToByteArray.charAt(index$iv + 1)) - 56613888;
                        byte c8 = (byte) ((codePoint$iv >> 18) | 240);
                        int i7 = size;
                        int size5 = i7 + 1;
                        bytes[i7] = c8;
                        byte c9 = (byte) (((codePoint$iv >> 12) & 63) | 128);
                        int size6 = size5 + 1;
                        bytes[size5] = c9;
                        byte c10 = (byte) (((codePoint$iv >> 6) & 63) | 128);
                        int size7 = size6 + 1;
                        bytes[size6] = c10;
                        byte c11 = (byte) ((codePoint$iv & 63) | 128);
                        size = size7 + 1;
                        bytes[size7] = c11;
                        index$iv += 2;
                    }
                }
                byte[] bArrCopyOf = Arrays.copyOf(bytes, size);
                Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
                return bArrCopyOf;
            }
            bytes[index] = (byte) b0;
        }
        byte[] bArrCopyOf2 = Arrays.copyOf(bytes, commonAsUtf8ToByteArray.length());
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf2, "java.util.Arrays.copyOf(this, newSize)");
        return bArrCopyOf2;
    }
}
