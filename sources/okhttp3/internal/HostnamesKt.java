package okhttp3.internal;

import java.net.IDN;
import java.net.InetAddress;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: hostnames.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 2, d1 = {"��&\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0002\u001a\"\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\f\u0010\r\u001a\u00020\u0001*\u00020\u0003H\u0002\u001a\f\u0010\u000e\u001a\u0004\u0018\u00010\u0003*\u00020\u0003¨\u0006\u000f"}, d2 = {"decodeIpv4Suffix", "", "input", "", "pos", "", "limit", "address", "", "addressOffset", "decodeIpv6", "Ljava/net/InetAddress;", "inet6AddressToAscii", "containsInvalidHostnameAsciiCodes", "toCanonicalHost", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/internal/HostnamesKt.class */
public final class HostnamesKt {
    @Nullable
    public static final String toCanonicalHost(@NotNull String toCanonicalHost) {
        InetAddress inetAddressDecodeIpv6;
        Intrinsics.checkNotNullParameter(toCanonicalHost, "$this$toCanonicalHost");
        if (StringsKt.contains$default((CharSequence) toCanonicalHost, (CharSequence) ":", false, 2, (Object) null)) {
            if (StringsKt.startsWith$default(toCanonicalHost, "[", false, 2, (Object) null) && StringsKt.endsWith$default(toCanonicalHost, "]", false, 2, (Object) null)) {
                inetAddressDecodeIpv6 = decodeIpv6(toCanonicalHost, 1, toCanonicalHost.length() - 1);
            } else {
                inetAddressDecodeIpv6 = decodeIpv6(toCanonicalHost, 0, toCanonicalHost.length());
            }
            if (inetAddressDecodeIpv6 != null) {
                InetAddress inetAddress = inetAddressDecodeIpv6;
                byte[] address = inetAddress.getAddress();
                if (address.length == 16) {
                    Intrinsics.checkNotNullExpressionValue(address, "address");
                    return inet6AddressToAscii(address);
                }
                if (address.length == 4) {
                    return inetAddress.getHostAddress();
                }
                throw new AssertionError("Invalid IPv6 address: '" + toCanonicalHost + '\'');
            }
            return null;
        }
        try {
            String ascii = IDN.toASCII(toCanonicalHost);
            Intrinsics.checkNotNullExpressionValue(ascii, "IDN.toASCII(host)");
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
            if (ascii == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String result = ascii.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(result, "(this as java.lang.String).toLowerCase(locale)");
            if (!(result.length() == 0) && !containsInvalidHostnameAsciiCodes(result)) {
                return result;
            }
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static final boolean containsInvalidHostnameAsciiCodes(String $this$containsInvalidHostnameAsciiCodes) {
        int length = $this$containsInvalidHostnameAsciiCodes.length();
        for (int i = 0; i < length; i++) {
            char c = $this$containsInvalidHostnameAsciiCodes.charAt(i);
            if (Intrinsics.compare((int) c, 31) <= 0 || Intrinsics.compare((int) c, 127) >= 0 || StringsKt.indexOf$default((CharSequence) " #%/:?@[\\]", c, 0, false, 6, (Object) null) != -1) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0104, code lost:
    
        if (r11 == r0.length) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x010a, code lost:
    
        if (r12 != (-1)) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x010d, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x010f, code lost:
    
        java.lang.System.arraycopy(r0, r12, r0, r0.length - (r11 - r12), r11 - r12);
        java.util.Arrays.fill(r0, r12, r12 + (r0.length - r11), (byte) 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0137, code lost:
    
        return java.net.InetAddress.getByAddress(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final java.net.InetAddress decodeIpv6(java.lang.String r7, int r8, int r9) {
        /*
            Method dump skipped, instructions count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.HostnamesKt.decodeIpv6(java.lang.String, int, int):java.net.InetAddress");
    }

    private static final boolean decodeIpv4Suffix(String input, int pos, int limit, byte[] address, int addressOffset) {
        int b = addressOffset;
        int i = pos;
        while (i < limit) {
            if (b == address.length) {
                return false;
            }
            if (b != addressOffset) {
                if (input.charAt(i) != '.') {
                    return false;
                }
                i++;
            }
            int value = 0;
            int groupOffset = i;
            while (i < limit) {
                char c = input.charAt(i);
                if (Intrinsics.compare((int) c, 48) < 0 || Intrinsics.compare((int) c, 57) > 0) {
                    break;
                }
                if (value == 0 && groupOffset != i) {
                    return false;
                }
                value = ((value * 10) + c) - 48;
                if (value > 255) {
                    return false;
                }
                i++;
            }
            int groupLength = i - groupOffset;
            if (groupLength == 0) {
                return false;
            }
            int i2 = b;
            b++;
            address[i2] = (byte) value;
        }
        return b == addressOffset + 4;
    }

    private static final String inet6AddressToAscii(byte[] address) {
        int longestRunOffset = -1;
        int longestRunLength = 0;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= address.length) {
                break;
            }
            while (i2 < 16 && address[i2] == 0 && address[i2 + 1] == 0) {
                i2 += 2;
            }
            int currentRunLength = i2 - i2;
            if (currentRunLength > longestRunLength && currentRunLength >= 4) {
                longestRunOffset = i2;
                longestRunLength = currentRunLength;
            }
            i = i2 + 2;
        }
        Buffer result = new Buffer();
        int i3 = 0;
        while (i3 < address.length) {
            if (i3 == longestRunOffset) {
                result.writeByte(58);
                i3 += longestRunLength;
                if (i3 == 16) {
                    result.writeByte(58);
                }
            } else {
                if (i3 > 0) {
                    result.writeByte(58);
                }
                int group = (Util.and(address[i3], 255) << 8) | Util.and(address[i3 + 1], 255);
                result.writeHexadecimalUnsignedLong(group);
                i3 += 2;
            }
        }
        return result.readUtf8();
    }
}
