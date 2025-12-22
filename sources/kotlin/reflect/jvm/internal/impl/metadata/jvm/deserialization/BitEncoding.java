package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import org.jetbrains.annotations.NotNull;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/BitEncoding.class */
public class BitEncoding {
    private static final boolean FORCE_8TO7_ENCODING;
    static final /* synthetic */ boolean $assertionsDisabled;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 2:
            case 4:
            case 5:
            case 7:
            case 9:
            case 11:
            case 13:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 1:
            case 3:
            case 6:
            case 8:
            case 10:
            case 12:
            case 14:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 2:
            case 4:
            case 5:
            case 7:
            case 9:
            case 11:
            case 13:
            default:
                i2 = 3;
                break;
            case 1:
            case 3:
            case 6:
            case 8:
            case 10:
            case 12:
            case 14:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 2:
            case 4:
            case 5:
            case 7:
            case 9:
            case 11:
            case 13:
            default:
                objArr[0] = "data";
                break;
            case 1:
            case 3:
            case 6:
            case 8:
            case 10:
            case 12:
            case 14:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/BitEncoding";
                break;
        }
        switch (i) {
            case 0:
            case 2:
            case 4:
            case 5:
            case 7:
            case 9:
            case 11:
            case 13:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/BitEncoding";
                break;
            case 1:
                objArr[1] = "encodeBytes";
                break;
            case 3:
                objArr[1] = "encode8to7";
                break;
            case 6:
                objArr[1] = "splitBytesToStringArray";
                break;
            case 8:
                objArr[1] = "decodeBytes";
                break;
            case 10:
                objArr[1] = "dropMarker";
                break;
            case 12:
                objArr[1] = "combineStringArrayIntoBytes";
                break;
            case 14:
                objArr[1] = "decode7to8";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[2] = "encodeBytes";
                break;
            case 1:
            case 3:
            case 6:
            case 8:
            case 10:
            case 12:
            case 14:
                break;
            case 2:
                objArr[2] = "encode8to7";
                break;
            case 4:
                objArr[2] = "addModuloByte";
                break;
            case 5:
                objArr[2] = "splitBytesToStringArray";
                break;
            case 7:
                objArr[2] = "decodeBytes";
                break;
            case 9:
                objArr[2] = "dropMarker";
                break;
            case 11:
                objArr[2] = "combineStringArrayIntoBytes";
                break;
            case 13:
                objArr[2] = "decode7to8";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 2:
            case 4:
            case 5:
            case 7:
            case 9:
            case 11:
            case 13:
            default:
                throw new IllegalArgumentException(str2);
            case 1:
            case 3:
            case 6:
            case 8:
            case 10:
            case 12:
            case 14:
                throw new IllegalStateException(str2);
        }
    }

    static {
        String use8to7;
        $assertionsDisabled = !BitEncoding.class.desiredAssertionStatus();
        try {
            use8to7 = System.getProperty("kotlin.jvm.serialization.use8to7");
        } catch (SecurityException e) {
            use8to7 = null;
        }
        FORCE_8TO7_ENCODING = "true".equals(use8to7);
    }

    private BitEncoding() {
    }

    private static void addModuloByte(@NotNull byte[] data, int increment) {
        if (data == null) {
            $$$reportNull$$$0(4);
        }
        int n = data.length;
        for (int i = 0; i < n; i++) {
            data[i] = (byte) ((data[i] + increment) & 127);
        }
    }

    @NotNull
    public static byte[] decodeBytes(@NotNull String[] data) {
        if (data == null) {
            $$$reportNull$$$0(7);
        }
        if (data.length > 0 && !data[0].isEmpty()) {
            char possibleMarker = data[0].charAt(0);
            if (possibleMarker == 0) {
                byte[] bArrStringsToBytes = UtfEncodingKt.stringsToBytes(dropMarker(data));
                if (bArrStringsToBytes == null) {
                    $$$reportNull$$$0(8);
                }
                return bArrStringsToBytes;
            }
            if (possibleMarker == 65535) {
                data = dropMarker(data);
            }
        }
        byte[] bytes = combineStringArrayIntoBytes(data);
        addModuloByte(bytes, 127);
        return decode7to8(bytes);
    }

    @NotNull
    private static String[] dropMarker(@NotNull String[] data) {
        if (data == null) {
            $$$reportNull$$$0(9);
        }
        String[] result = (String[]) data.clone();
        result[0] = result[0].substring(1);
        if (result == null) {
            $$$reportNull$$$0(10);
        }
        return result;
    }

    @NotNull
    private static byte[] combineStringArrayIntoBytes(@NotNull String[] data) {
        if (data == null) {
            $$$reportNull$$$0(11);
        }
        int resultLength = 0;
        for (String s : data) {
            if (!$assertionsDisabled && s.length() > 65535) {
                throw new AssertionError("String is too long: " + s.length());
            }
            resultLength += s.length();
        }
        byte[] result = new byte[resultLength];
        int p = 0;
        for (String s2 : data) {
            int n = s2.length();
            for (int i = 0; i < n; i++) {
                int i2 = p;
                p++;
                result[i2] = (byte) s2.charAt(i);
            }
        }
        if (result == null) {
            $$$reportNull$$$0(12);
        }
        return result;
    }

    @NotNull
    private static byte[] decode7to8(@NotNull byte[] data) {
        if (data == null) {
            $$$reportNull$$$0(13);
        }
        int resultLength = (7 * data.length) / 8;
        byte[] result = new byte[resultLength];
        int byteIndex = 0;
        int bit = 0;
        for (int i = 0; i < resultLength; i++) {
            int firstPart = (data[byteIndex] & 255) >>> bit;
            byteIndex++;
            int secondPart = (data[byteIndex] & ((1 << (bit + 1)) - 1)) << (7 - bit);
            result[i] = (byte) (firstPart + secondPart);
            if (bit == 6) {
                byteIndex++;
                bit = 0;
            } else {
                bit++;
            }
        }
        if (result == null) {
            $$$reportNull$$$0(14);
        }
        return result;
    }
}
