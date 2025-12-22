package cn.hutool.core.text.finder;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/text/finder/Finder.class */
public interface Finder {
    public static final int INDEX_NOT_FOUND = -1;

    int start(int i);

    int end(int i);

    default Finder reset() {
        return this;
    }
}
