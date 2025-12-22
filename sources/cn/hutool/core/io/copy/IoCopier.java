package cn.hutool.core.io.copy;

import cn.hutool.core.io.StreamProgress;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/io/copy/IoCopier.class */
public abstract class IoCopier<S, T> {
    protected final int bufferSize;
    protected final long count;
    protected StreamProgress progress;
    protected boolean flushEveryBuffer;

    public abstract long copy(S s, T t);

    public IoCopier(int bufferSize, long count, StreamProgress progress) {
        this.bufferSize = bufferSize > 0 ? bufferSize : 8192;
        this.count = count <= 0 ? Long.MAX_VALUE : count;
        this.progress = progress;
    }

    protected int bufferSize(long count) {
        return (int) Math.min(this.bufferSize, count);
    }

    public IoCopier<S, T> setFlushEveryBuffer(boolean flushEveryBuffer) {
        this.flushEveryBuffer = flushEveryBuffer;
        return this;
    }
}
