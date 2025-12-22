package org.antlr.v4.runtime.misc;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/MurmurHash.class */
public final class MurmurHash {
    private static final int DEFAULT_SEED = 0;

    public static int initialize() {
        return initialize(0);
    }

    public static int initialize(int seed) {
        return seed;
    }

    public static int update(int hash, int value) {
        int k = value * (-862048943);
        int hash2 = hash ^ (((k << 15) | (k >>> 17)) * 461845907);
        return (((hash2 << 13) | (hash2 >>> 19)) * 5) - 430675100;
    }

    public static int update(int hash, Object value) {
        return update(hash, value != null ? value.hashCode() : 0);
    }

    public static int finish(int hash, int numberOfWords) {
        int hash2 = hash ^ (numberOfWords * 4);
        int hash3 = (hash2 ^ (hash2 >>> 16)) * (-2048144789);
        int hash4 = (hash3 ^ (hash3 >>> 13)) * (-1028477387);
        return hash4 ^ (hash4 >>> 16);
    }

    public static <T> int hashCode(T[] data, int seed) {
        int hash = initialize(seed);
        for (T value : data) {
            hash = update(hash, value);
        }
        return finish(hash, data.length);
    }

    private MurmurHash() {
    }
}
