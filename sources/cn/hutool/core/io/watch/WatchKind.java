package cn.hutool.core.io.watch;

import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/io/watch/WatchKind.class */
public enum WatchKind {
    OVERFLOW(StandardWatchEventKinds.OVERFLOW),
    MODIFY(StandardWatchEventKinds.ENTRY_MODIFY),
    CREATE(StandardWatchEventKinds.ENTRY_CREATE),
    DELETE(StandardWatchEventKinds.ENTRY_DELETE);

    public static final WatchEvent.Kind<?>[] ALL = {OVERFLOW.getValue(), MODIFY.getValue(), CREATE.getValue(), DELETE.getValue()};
    private final WatchEvent.Kind<?> value;

    WatchKind(WatchEvent.Kind kind) {
        this.value = kind;
    }

    public WatchEvent.Kind<?> getValue() {
        return this.value;
    }
}
