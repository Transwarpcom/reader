package cn.hutool.core.io.watch;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/io/watch/WatchAction.class */
public interface WatchAction {
    void doAction(WatchEvent<?> watchEvent, Path path);
}
