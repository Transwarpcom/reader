package cn.hutool.core.io;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/io/StreamProgress.class */
public interface StreamProgress {
    void start();

    void progress(long j);

    void finish();
}
