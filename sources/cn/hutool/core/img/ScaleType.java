package cn.hutool.core.img;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/img/ScaleType.class */
public enum ScaleType {
    DEFAULT(1),
    FAST(2),
    SMOOTH(4),
    REPLICATE(8),
    AREA_AVERAGING(16);

    private final int value;

    ScaleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
