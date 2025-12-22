package kotlin.reflect.jvm.internal.impl.protobuf;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/Utf8.class */
final class Utf8 {
    public static boolean isValidUtf8(byte[] bytes) {
        return isValidUtf8(bytes, 0, bytes.length);
    }

    public static boolean isValidUtf8(byte[] bytes, int index, int limit) {
        return partialIsValidUtf8(bytes, index, limit) == 0;
    }

    public static int partialIsValidUtf8(int state, byte[] bytes, int index, int limit) {
        if (state != 0) {
            if (index >= limit) {
                return state;
            }
            int byte1 = (byte) state;
            if (byte1 < -32) {
                if (byte1 < -62) {
                    return -1;
                }
                index++;
                if (bytes[index] > -65) {
                    return -1;
                }
            } else if (byte1 < -16) {
                int byte2 = (byte) ((state >> 8) ^ (-1));
                if (byte2 == 0) {
                    index++;
                    byte2 = bytes[index];
                    if (index >= limit) {
                        return incompleteStateFor(byte1, byte2);
                    }
                }
                if (byte2 > -65) {
                    return -1;
                }
                if (byte1 == -32 && byte2 < -96) {
                    return -1;
                }
                if (byte1 == -19 && byte2 >= -96) {
                    return -1;
                }
                int i = index;
                index++;
                if (bytes[i] > -65) {
                    return -1;
                }
            } else {
                int byte22 = (byte) ((state >> 8) ^ (-1));
                int byte3 = 0;
                if (byte22 == 0) {
                    index++;
                    byte22 = bytes[index];
                    if (index >= limit) {
                        return incompleteStateFor(byte1, byte22);
                    }
                } else {
                    byte3 = (byte) (state >> 16);
                }
                if (byte3 == 0) {
                    int i2 = index;
                    index++;
                    byte3 = bytes[i2];
                    if (index >= limit) {
                        return incompleteStateFor(byte1, byte22, byte3);
                    }
                }
                if (byte22 > -65 || (((byte1 << 28) + (byte22 - (-112))) >> 30) != 0 || byte3 > -65) {
                    return -1;
                }
                int i3 = index;
                index++;
                if (bytes[i3] > -65) {
                    return -1;
                }
            }
        }
        return partialIsValidUtf8(bytes, index, limit);
    }

    public static int partialIsValidUtf8(byte[] bytes, int index, int limit) {
        while (index < limit && bytes[index] >= 0) {
            index++;
        }
        if (index >= limit) {
            return 0;
        }
        return partialIsValidUtf8NonAscii(bytes, index, limit);
    }

    private static int partialIsValidUtf8NonAscii(byte[] bytes, int index, int limit) {
        while (index < limit) {
            int i = index;
            index++;
            byte b = bytes[i];
            if (b < 0) {
                if (b < -32) {
                    if (index >= limit) {
                        return b;
                    }
                    if (b < -62) {
                        return -1;
                    }
                    index++;
                    if (bytes[index] > -65) {
                        return -1;
                    }
                } else if (b < -16) {
                    if (index >= limit - 1) {
                        return incompleteStateFor(bytes, index, limit);
                    }
                    int index2 = index + 1;
                    byte b2 = bytes[index];
                    if (b2 > -65) {
                        return -1;
                    }
                    if (b == -32 && b2 < -96) {
                        return -1;
                    }
                    if (b == -19 && b2 >= -96) {
                        return -1;
                    }
                    index = index2 + 1;
                    if (bytes[index2] > -65) {
                        return -1;
                    }
                } else {
                    if (index >= limit - 2) {
                        return incompleteStateFor(bytes, index, limit);
                    }
                    int index3 = index + 1;
                    byte b3 = bytes[index];
                    if (b3 > -65 || (((b << 28) + (b3 - (-112))) >> 30) != 0) {
                        return -1;
                    }
                    int index4 = index3 + 1;
                    if (bytes[index3] > -65) {
                        return -1;
                    }
                    index = index4 + 1;
                    if (bytes[index4] > -65) {
                        return -1;
                    }
                }
            }
        }
        return 0;
    }

    private static int incompleteStateFor(int byte1) {
        if (byte1 > -12) {
            return -1;
        }
        return byte1;
    }

    private static int incompleteStateFor(int byte1, int byte2) {
        if (byte1 > -12 || byte2 > -65) {
            return -1;
        }
        return byte1 ^ (byte2 << 8);
    }

    private static int incompleteStateFor(int byte1, int byte2, int byte3) {
        if (byte1 > -12 || byte2 > -65 || byte3 > -65) {
            return -1;
        }
        return (byte1 ^ (byte2 << 8)) ^ (byte3 << 16);
    }

    private static int incompleteStateFor(byte[] bytes, int index, int limit) {
        byte b = bytes[index - 1];
        switch (limit - index) {
            case 0:
                return incompleteStateFor(b);
            case 1:
                return incompleteStateFor(b, bytes[index]);
            case 2:
                return incompleteStateFor(b, bytes[index], bytes[index + 1]);
            default:
                throw new AssertionError();
        }
    }
}
