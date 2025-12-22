package org.bson.internal;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/internal/Base64.class */
public final class Base64 {
    private static final int BYTES_PER_UNENCODED_BLOCK = 3;
    private static final int BYTES_PER_ENCODED_BLOCK = 4;
    private static final int SIX_BIT_MASK = 63;
    private static final byte PAD = 61;
    private static final byte[] ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final int[] DECODE_TABLE = new int[128];

    static {
        for (int i = 0; i < ENCODE_TABLE.length; i++) {
            DECODE_TABLE[ENCODE_TABLE[i]] = i;
        }
    }

    public static byte[] decode(String s) {
        int delta = s.endsWith("==") ? 2 : s.endsWith("=") ? 1 : 0;
        byte[] buffer = new byte[((s.length() * 3) / 4) - delta];
        int pos = 0;
        for (int i = 0; i < s.length(); i += 4) {
            int c0 = DECODE_TABLE[s.charAt(i)];
            int c1 = DECODE_TABLE[s.charAt(i + 1)];
            int i2 = pos;
            int pos2 = pos + 1;
            buffer[i2] = (byte) (((c0 << 2) | (c1 >> 4)) & 255);
            if (pos2 >= buffer.length) {
                return buffer;
            }
            int c2 = DECODE_TABLE[s.charAt(i + 2)];
            int pos3 = pos2 + 1;
            buffer[pos2] = (byte) (((c1 << 4) | (c2 >> 2)) & 255);
            if (pos3 >= buffer.length) {
                return buffer;
            }
            int c3 = DECODE_TABLE[s.charAt(i + 3)];
            pos = pos3 + 1;
            buffer[pos3] = (byte) (((c2 << 6) | c3) & 255);
        }
        return buffer;
    }

    public static String encode(byte[] in) {
        int modulus = 0;
        int bitWorkArea = 0;
        int numEncodedBytes = ((in.length / 3) * 4) + (in.length % 3 == 0 ? 0 : 4);
        byte[] buffer = new byte[numEncodedBytes];
        int pos = 0;
        for (int b : in) {
            modulus = (modulus + 1) % 3;
            if (b < 0) {
                b += 256;
            }
            bitWorkArea = (bitWorkArea << 8) + b;
            if (0 == modulus) {
                int i = pos;
                int pos2 = pos + 1;
                buffer[i] = ENCODE_TABLE[(bitWorkArea >> 18) & 63];
                int pos3 = pos2 + 1;
                buffer[pos2] = ENCODE_TABLE[(bitWorkArea >> 12) & 63];
                int pos4 = pos3 + 1;
                buffer[pos3] = ENCODE_TABLE[(bitWorkArea >> 6) & 63];
                pos = pos4 + 1;
                buffer[pos4] = ENCODE_TABLE[bitWorkArea & 63];
            }
        }
        switch (modulus) {
            case 1:
                int i2 = pos;
                int pos5 = pos + 1;
                buffer[i2] = ENCODE_TABLE[(bitWorkArea >> 2) & 63];
                int pos6 = pos5 + 1;
                buffer[pos5] = ENCODE_TABLE[(bitWorkArea << 4) & 63];
                buffer[pos6] = 61;
                buffer[pos6 + 1] = 61;
                break;
            case 2:
                int i3 = pos;
                int pos7 = pos + 1;
                buffer[i3] = ENCODE_TABLE[(bitWorkArea >> 10) & 63];
                int pos8 = pos7 + 1;
                buffer[pos7] = ENCODE_TABLE[(bitWorkArea >> 4) & 63];
                buffer[pos8] = ENCODE_TABLE[(bitWorkArea << 2) & 63];
                buffer[pos8 + 1] = 61;
                break;
        }
        return byteArrayToString(buffer);
    }

    private static String byteArrayToString(byte[] buffer) {
        return new String(buffer, 0, 0, buffer.length);
    }

    private Base64() {
    }
}
