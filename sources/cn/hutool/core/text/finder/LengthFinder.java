package cn.hutool.core.text.finder;

import cn.hutool.core.lang.Assert;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/text/finder/LengthFinder.class */
public class LengthFinder extends TextFinder {
    private static final long serialVersionUID = 1;
    private final int length;

    public LengthFinder(int length) {
        this.length = length;
    }

    @Override // cn.hutool.core.text.finder.Finder
    public int start(int from) throws IllegalArgumentException {
        Assert.notNull(this.text, "Text to find must be not null!", new Object[0]);
        int limit = getValidEndIndex();
        if (this.negative) {
            int result = from - this.length;
            if (result > limit) {
                return result;
            }
            return -1;
        }
        int result2 = from + this.length;
        if (result2 < limit) {
            return result2;
        }
        return -1;
    }

    @Override // cn.hutool.core.text.finder.Finder
    public int end(int start) {
        return start;
    }
}
