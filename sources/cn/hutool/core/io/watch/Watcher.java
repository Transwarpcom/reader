package cn.hutool.core.io.watch;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/io/watch/Watcher.class */
public interface Watcher {
    void onCreate(WatchEvent<?> watchEvent, Path path);

    void onModify(WatchEvent<?> watchEvent, Path path);

    void onDelete(WatchEvent<?> watchEvent, Path path);

    void onOverflow(WatchEvent<?> watchEvent, Path path);
}
